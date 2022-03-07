package org.stroganov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.stroganov.entities.User;
import org.stroganov.exceptions.UserNotExistException;
import org.stroganov.models.UserDTO;
import org.stroganov.servise.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class UserController {

    private static final String USER_WAS_NOT_FOUND = "User was non found";
    @Autowired
    UserService userService;

    @GetMapping("user/{userLogin}")
    public ResponseEntity findUser(@PathVariable("userLogin") String userLogin) {
        Optional<User> user = userService.findUserByUserLogin(userLogin);
        if (user.isPresent()) {
            return ResponseEntity.ok(new UserDTO(user.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("user/{userLogin}")
    //@JWTTokenNeeded
    //@AdminStatusNeeded
    public ResponseEntity deleteUser(@PathVariable("userLogin") String userLogin) throws UserNotExistException {
            userService.deleteUserByUserLogin(userLogin);
            return ResponseEntity.ok().build();
    }

    @PostMapping("user/block/")
    // @JWTTokenNeeded
    // @AdminStatusNeeded
    public ResponseEntity blockUser(@RequestBody String userLogin) throws UserNotExistException {
            userService.blockUser(userLogin);
            return ResponseEntity.ok().build();
    }

    @PostMapping("user/unblock/")
    // @JWTTokenNeeded
    // @AdminStatusNeeded
    public ResponseEntity unblockUser(@RequestBody String userLogin) throws UserNotExistException {
            userService.unBlockUser(userLogin);
            return ResponseEntity.ok().build();
    }

    @PostMapping("user/")
    //@JWTTokenNeeded
    //@AdminStatusNeeded
    public ResponseEntity addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return ResponseEntity.ok().build();
    }
}
