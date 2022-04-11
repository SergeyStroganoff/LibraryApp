package org.stroganov.servise;

import org.stroganov.entities.BookMark;
import org.stroganov.exceptions.BookMarkServiceException;
import org.stroganov.exceptions.UserNotExistException;
import org.stroganov.models.BookMarkDTO;

import java.util.List;

public interface BookMarkService {
    void deleteBookMark(int bookMarkId) throws BookMarkServiceException;

    void addBookMark(BookMarkDTO bookMarkDTO) throws BookMarkServiceException;

    List<BookMark> findUsersBookMarks(String userLogin) throws UserNotExistException;
}
