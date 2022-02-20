package org.stroganov.servise;

import org.springframework.stereotype.Service;
import org.stroganov.entities.User;
import org.stroganov.exceptions.UserNotExistException;
import org.stroganov.models.UserDTO;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findUserByUserLogin(String userLogin);

    void deleteUserByUserLogin(String userLogin) throws UserNotExistException;

    void blockUser(String userLogin) throws UserNotExistException;

    void unBlockUser(String userLogin) throws UserNotExistException;

    void addUser(UserDTO userDTO);
}
