package org.stroganov.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stroganov.entities.User;
import org.stroganov.exceptions.UserNotExistException;
import org.stroganov.models.UserDTO;
import org.stroganov.servise.UserService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("user/{userLogin}")
    public ResponseEntity findUser(@PathVariable("userLogin") String userLogin) {
        Optional<User> user = userService.findUserByUserLogin(userLogin);
        if (user.isPresent()) {
            return ResponseEntity.ok(modelMapper.map(user.get(), UserDTO.class));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("admin/user/{userLogin}")
    public ResponseEntity deleteUser(@PathVariable("userLogin") String userLogin) throws UserNotExistException {
        userService.deleteUserByUserLogin(userLogin);
        return ResponseEntity.ok().build();
    }

    @PostMapping("admin/user/block/")
    public ResponseEntity blockUser(@RequestBody String userLogin) throws UserNotExistException {
        userService.blockUser(userLogin);
        return ResponseEntity.ok().build();
    }

    @PostMapping("admin/user/unblock/")
    public ResponseEntity unblockUser(@RequestBody String userLogin) throws UserNotExistException {
        userService.unBlockUser(userLogin);
        return ResponseEntity.ok().build();
    }

    @PostMapping("admin/user/")
    public ResponseEntity addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(modelMapper.map(userDTO, User.class));
        return ResponseEntity.ok().build();
    }
}
