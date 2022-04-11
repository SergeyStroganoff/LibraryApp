package org.stroganov.servise.impl;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
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

import java.util.List;
import java.util.Optional;

@Service
@Log4j2(topic = "logger")
public class BookMarkServiceImpl implements BookMarkService {

    public static final String BOOK_MARK_NOT_FOUND = "Закладка не найдена";
    private final BookMarkRepository bookMarkRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookMarkServiceImpl(BookMarkRepository bookMarkRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.bookMarkRepository = bookMarkRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void deleteBookMark(int bookMarkId) throws BookMarkServiceException {
        Optional<BookMark> bookMarkOptional = bookMarkRepository.findById(bookMarkId);
        if (bookMarkOptional.isPresent()) {
            bookMarkRepository.delete(bookMarkOptional.get());
        } else {
            log.debug(BOOK_MARK_NOT_FOUND);
            throw new BookMarkServiceException(BOOK_MARK_NOT_FOUND);
        }
    }

    @Override
    public void addBookMark(BookMarkDTO bookMarkDTO) throws BookMarkServiceException {
        BookMark bookMark = modelMapper.map(bookMarkDTO, BookMark.class);
        try {
            bookMarkRepository.save(bookMark);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new BookMarkServiceException(e);
        }
    }

    @Override
    public List<BookMark> findUsersBookMarks(String userLogin) throws UserNotExistException {
        Optional<User> user = userRepository.findById(userLogin);
        if (user.isPresent()) {
            return bookMarkRepository.findBookMarksByUser(user.get());
        } else {
            String message = "User with login:" + userLogin + "not found";
            log.debug(message);
            throw new UserNotExistException(message);
        }
    }
}
