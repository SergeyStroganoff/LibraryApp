package org.stroganov.util;

import org.stroganov.entities.*;
import org.stroganov.models.*;

public class TransitionObjectsService {

    private TransitionObjectsService() {
    }

    public static User getUser(UserDTO userDTO) {
        User user = new User();
        user.setUserID(userDTO.getUserID());
        user.setFullName(userDTO.getFullName());
        user.setLogin(userDTO.getLogin());
        user.setPasscodeHash(userDTO.getPasscodeHash());
        user.setAdmin(userDTO.isAdmin());
        user.setBlocked(userDTO.isBlocked());
        return user;
    }

    public static Author getAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setAuthorID(authorDTO.getAuthorID());
        author.setAuthorName(authorDTO.getAuthorName());
        return author;
    }


    public static BookMark getBookMark(BookMarkDTO bookMarkDTO) {
        return new BookMark(getBook(bookMarkDTO.getBook()), getUser(bookMarkDTO.getUser()), bookMarkDTO.getBookPageNumber());
    }

    public static Book getBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthor(getAuthor(bookDTO.getAuthor()));
        book.setBookName(bookDTO.getBookName());
        book.setNumberISBN(bookDTO.getNumberISBN());
        book.setPagesNumber(bookDTO.getPagesNumber());
        book.setYearPublishing(bookDTO.getYearPublishing());
        return book;
    }

    public static History getHistory(HistoryDTO historyDTO) {
        History history = new History();
        history.setId(historyDTO.getId());
        history.setEvent(historyDTO.getEvent());
        history.setLocalDateTime(historyDTO.getLocalDateTime());
        history.setUser(getUser(historyDTO.getUser()));
        return history;
    }
}
