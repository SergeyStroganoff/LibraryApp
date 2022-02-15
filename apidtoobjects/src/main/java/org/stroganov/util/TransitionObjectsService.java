package org.stroganov.util;

import org.stroganov.entities.*;
import org.stroganov.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<BookMarkDTO> getBookMarkDTOList(List<BookMark> bookMarkList) {
        return bookMarkList.stream().map(BookMarkDTO::new).collect(Collectors.toList());
    }

    public static List<HistoryDTO> getHistoryDTOList(List<History> allHistory) {
        return allHistory.stream().map(HistoryDTO::new).collect(Collectors.toList());
    }

    public static List<BookDTO> getBookDTOList(List<Book> bookList) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        bookList.forEach(book -> bookDTOList.add(new BookDTO(book)));
        return bookDTOList;
    }
}
