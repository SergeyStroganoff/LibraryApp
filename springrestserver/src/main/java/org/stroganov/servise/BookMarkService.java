package org.stroganov.servise;

import org.stroganov.entities.BookMark;
import org.stroganov.exceptions.BookMarkNotExistException;
import org.stroganov.exceptions.BookMarkSavingException;
import org.stroganov.exceptions.UserNotExistException;
import org.stroganov.models.BookMarkDTO;

import java.util.List;

public interface BookMarkService {
    void deleteBookMark(int bookMarkId) throws BookMarkNotExistException;

    void addBookMark(BookMarkDTO bookMarkDTO) throws BookMarkSavingException;

    List<BookMark> findUsersBookMarks(String userLogin) throws UserNotExistException;
}
