package org.stroganov.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stroganov.entities.User;
import org.stroganov.models.UserDTO;
import org.stroganov.servise.UserService;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    public static final String BEARER = "Bearer";
    public static final String LIBRARY_SERVER = "LibraryServer";
    public static final String ADMIN = "admin";
    public static final long EXPIRATION_TIME = 45L;
    UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authentication")
    // QueryParam
    public ResponseEntity<String> authentication(UserDTO userDTO) {
        Optional<User> userOptional = userService.findUserByUserLogin(userDTO.getLogin());
        if (userOptional.isEmpty() || !userOptional.get().getPasscodeHash().equals(userDTO.getPasscodeHash())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        String newJWTToken = createJWTToken(userOptional.get());
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
