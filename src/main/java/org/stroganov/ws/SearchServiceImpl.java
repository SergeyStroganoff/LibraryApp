package org.stroganov.ws;

import jakarta.jws.WebService;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.util.DataManager;

import java.util.List;

@WebService(endpointInterface = "org.stroganov.ws.SearchService")
public class SearchServiceImpl implements SearchService {

    private final LibraryDAO libraryDAO = DataManager.getLibraryDAO();

    @Override
    public Book findBook(String numberISBN) {
        return libraryDAO.findBook(numberISBN);
    }

    @Override
    public User findUser(String userLogin) {
        return libraryDAO.findUser(userLogin);
    }

    @Override
    public Book[] findBooksByPartName(String partOfName) {
        return libraryDAO.findBooksByPartName(partOfName).toArray(new Book[0]);
    }

    @Override
    public Author findAuthor(String authorName) {
        return libraryDAO.findAuthor(authorName);
    }

    @Override
    public Book[] findBooksByPartAuthorName(String partAuthorName) {
        return libraryDAO.findBooksByPartAuthorName(partAuthorName).toArray(new Book[0]);
    }

    @Override
    public Book[] findBooksByYearsRange(int firstYear, int secondYear) {
        return libraryDAO.findBooksByYearsRange(firstYear, secondYear).toArray(new Book[0]);
    }

    @Override
    public Book[] findBooksByParameters(int bookYear, int bookPages, String partBookName) {
        return libraryDAO.findBooksByParameters(bookYear, bookPages, partBookName).toArray(new Book[0]);
    }

    @Override
    public Book[] findBooksWithUserBookMarks(User user) {
        return libraryDAO.findBooksWithUserBookMarks(user).toArray(new Book[0]);
    }

    @Override
    public BookMark[] findUserBookMarks(User user) {
        return libraryDAO.findUserBookMarks(user).toArray(new BookMark[0]);
    }
}
