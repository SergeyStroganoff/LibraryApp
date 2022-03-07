package org.stroganov.servise.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.exceptions.BookMarkServiceException;
import org.stroganov.exceptions.UserNotExistException;
import org.stroganov.models.BookMarkDTO;
import org.stroganov.repository.BookMarkRepository;
import org.stroganov.repository.UserRepository;
import org.stroganov.servise.BookMarkService;
import org.stroganov.util.TransitionObjectsService;

import java.util.List;
import java.util.Optional;

@Service
public class BookMarkServiceImpl implements BookMarkService {

    Logger logger = Logger.getLogger(BookMarkServiceImpl.class);
    BookMarkRepository bookMarkRepository;
    UserRepository userRepository;

    @Autowired
    public BookMarkServiceImpl(BookMarkRepository bookMarkRepository, UserRepository userRepository) {
        this.bookMarkRepository = bookMarkRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void deleteBookMark(int bookMarkId) throws BookMarkServiceException {
        Optional<BookMark> bookMarkOptional = bookMarkRepository.findById(bookMarkId);
        if (bookMarkOptional.isPresent()) {
            bookMarkRepository.delete(bookMarkOptional.get());
        } else {
            throw new BookMarkServiceException("Закладка не найдена");
        }
    }

    @Override
    public void addBookMark(BookMarkDTO bookMarkDTO) throws BookMarkServiceException {
        BookMark bookMark = TransitionObjectsService.getBookMark(bookMarkDTO);
        try {
            bookMarkRepository.save(bookMark);
        } catch (Exception e) {
            throw new BookMarkServiceException(e);
        }
    }

    @Override
    public List<BookMark> findUsersBookMarks(String userLogin) throws UserNotExistException {
        Optional<User> user = userRepository.findById(userLogin);
        if (user.isPresent()) {
            return bookMarkRepository.findBookMarksByUser(user.get());
        } else {
            throw new UserNotExistException("User with login:" + userLogin + "not found");
        }
    }
}
