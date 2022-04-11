package org.stroganov.servise.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroganov.entities.User;
import org.stroganov.exceptions.UserNotExistException;
import org.stroganov.repository.UserRepository;
import org.stroganov.servise.UserService;

import java.util.Optional;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    public static final String USER_NOT_EXIST_MESSAGE = "Пользователь не найден";
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserByUserLogin(String userLogin) {
        return userRepository.findById(userLogin);
    }

    @Override
    public void deleteUserByUserLogin(String userLogin) throws UserNotExistException {
        Optional<User> userOptional = findUserByUserLogin(userLogin);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            log.info(USER_NOT_EXIST_MESSAGE + ": " + userLogin);
            throw new UserNotExistException(USER_NOT_EXIST_MESSAGE);
        }
    }

    @Override
    public void blockUser(String userLogin) throws UserNotExistException {
        Optional<User> userOptional = findUserByUserLogin(userLogin);
        if (userOptional.isPresent()) {
            userRepository.blockUser(userOptional.get());
        } else {
            log.info(USER_NOT_EXIST_MESSAGE + ": " + userLogin);
            throw new UserNotExistException(USER_NOT_EXIST_MESSAGE);
        }
    }

    @Override
    public void unBlockUser(String userLogin) throws UserNotExistException {
        Optional<User> userOptional = findUserByUserLogin(userLogin);
        if (userOptional.isPresent()) {
            userRepository.unblockUser(userOptional.get());
        } else {
            log.info(USER_NOT_EXIST_MESSAGE + ": " + userLogin);
            throw new UserNotExistException(USER_NOT_EXIST_MESSAGE);
        }
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
}
