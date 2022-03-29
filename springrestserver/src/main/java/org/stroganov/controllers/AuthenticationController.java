package org.stroganov.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stroganov.entities.User;
import org.stroganov.models.AuthenticationRequestDto;
import org.stroganov.security.jwt.JwtTokenProvider;
import org.stroganov.servise.UserService;
import org.stroganov.utils.PasswordAuthentication;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Log4j2
public class AuthenticationController {

    public static final String BEARER = "Bearer_";
    public static final String LIBRARY_SERVER = "LibraryServer";
    public static final String ADMIN = "admin";
    public static final long EXPIRATION_TIME = 45L;


    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/authentication")
    // QueryParam
    public ResponseEntity<String> authentication(@RequestBody AuthenticationRequestDto requestDto) {
        Optional<User> userOptional = userService.findUserByUserLogin(requestDto.getLogin());
        if (userOptional.isEmpty() || !PasswordAuthentication.authenticate(requestDto.getPassword().toCharArray(), userOptional.get().getPasscodeHash())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // try {
        //     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getLogin(), requestDto.getPassword()));
        // } catch (AuthenticationException e) {
        //     throw new BadCredentialsException("Invalid username or password");
        // }

        String newJWTToken = jwtTokenProvider.createToken(userOptional.get());
        final ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put(BEARER, newJWTToken);
        return ResponseEntity.status(HttpStatus.OK)
                .body(json.toString());
    }

    private String createJWTToken(User user) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Jwts.builder()
                .setIssuer(LIBRARY_SERVER)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(EXPIRATION_TIME).atZone(ZoneId.systemDefault()).toInstant()))
                .setSubject(user.getLogin())
                .claim(ADMIN, user.isAdmin())
                .signWith(key)
                .compact();
    }
}
