package org.stroganov.servise.impl;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroganov.entities.User;
import org.stroganov.exceptions.UserNotExistException;
import org.stroganov.models.UserDTO;
import org.stroganov.repository.UserRepository;
import org.stroganov.servise.UserService;
import org.stroganov.util.TransitionObjectsService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    Logger logger = Logger.getLogger(UserServiceImpl.class);

    public static final String USER_NOT_EXIST_MESSAGE = "Пользователь не найден";
    UserRepository userRepository;

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
            logger.info(USER_NOT_EXIST_MESSAGE + ": " + userLogin);
            throw new UserNotExistException(USER_NOT_EXIST_MESSAGE);
        }
    }

    @Override
    public void blockUser(String userLogin) throws UserNotExistException {
        Optional<User> userOptional = findUserByUserLogin(userLogin);
        if (userOptional.isPresent()) {
            userRepository.blockUser(userOptional.get());
        } else {
            logger.info(USER_NOT_EXIST_MESSAGE + ": " + userLogin);
            throw new UserNotExistException(USER_NOT_EXIST_MESSAGE);
        }
    }

    @Override
    public void unBlockUser(String userLogin) throws UserNotExistException {
        Optional<User> userOptional = findUserByUserLogin(userLogin);
        if (userOptional.isPresent()) {
            userRepository.unblockUser(userOptional.get());
        } else {
            logger.info(USER_NOT_EXIST_MESSAGE + ": " + userLogin);
            throw new UserNotExistException(USER_NOT_EXIST_MESSAGE);
        }
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = TransitionObjectsService.getUser(userDTO);
        userRepository.save(user);
    }
}
