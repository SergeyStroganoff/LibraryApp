package org.stroganov.dao;

import org.stroganov.App;
import org.stroganov.JsonDBAPI.JsonDBLoader;
import org.stroganov.JsonDBAPI.JsonDBSaver;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.exceptions.DBExceptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JsonLibraryDAO implements LibraryDAO {

    private static JsonLibraryDAO instance;
    private final List<Book> bookList;
    private final List<User> userList;
    private final List<BookMark> bookMarkList;
    private final List<Author> authorsList;
    private final JsonDBSaver jsonDBSaver;

    public static synchronized JsonLibraryDAO getInstance() throws DBExceptions {
        if (instance == null) {
            instance = new JsonLibraryDAO();
        }
        return instance;
    }

    private JsonLibraryDAO() throws DBExceptions {
        jsonDBSaver = new JsonDBSaver(App.properties);
        bookList = JsonDBLoader.loadEntities(App.properties.getProperty("booksJsonFile"), Book.class);
        userList = JsonDBLoader.loadEntities(App.properties.getProperty("usersJsonFile"), User.class);
        bookMarkList = JsonDBLoader.loadEntities(App.properties.getProperty("bookMarksJsonFile"), BookMark.class);
        authorsList = JsonDBLoader.loadEntities(App.properties.getProperty("authorsJsonFile"), Author.class);
    }

    @Override
    public boolean addBook(Book book) throws IOException {
        if (!bookList.contains(book)) {
            bookList.add(book);
            jsonDBSaver.saveEntityListToJsonFormatFile(bookList);
            addAuthor(book.getAuthor());
            return true;
        }
        return false;
    }

    @Override
    public boolean addBook(List<Book> bookList) throws IOException {
        boolean isSomeBookAdded = false;
        for (Book nextBook : bookList) {
            if (!bookList.contains(nextBook)) {
                bookList.add(nextBook);
                addAuthor(nextBook.getAuthor());
                isSomeBookAdded = true;
            }
            jsonDBSaver.saveEntityListToJsonFormatFile(bookList);
        }
        return isSomeBookAdded;
    }

    @Override
    public boolean deleteBook(Book book) throws IOException {
        if (bookList.contains(book)) {
            bookList.remove(book);
            jsonDBSaver.saveEntityListToJsonFormatFile(bookList);
            return true;
        }
        return false;
    }

    @Override
    public Book findBook(String numberISBN) {
        return bookList.stream()
                .filter(book -> book.getNumberISBN().equals(numberISBN))
                .findFirst().orElse(null);
    }

    @Override
    public List<Book> findBooksByPartName(String partOfName) {
        return bookList.stream()
                .filter(book -> book.getName().contains(partOfName))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addUser(User user) throws IOException {
        if (!userList.contains(user)) {
            if (!userList.isEmpty()) {
                int maxCurrentID = 0;
                for (User currentUser : userList) {
                    if (currentUser.getNumberID() > maxCurrentID) {
                        maxCurrentID = user.getNumberID();
                    }
                }
                user.setNumberID(++maxCurrentID);
            }
            userList.add(user);
            jsonDBSaver.saveEntityListToJsonFormatFile(userList);
            return true;
        }
        return false;
    }

    @Override
    public User findUser(String userLogin) {
        return userList.stream()
                .filter(user -> user.getLogin().equals(userLogin))
                .findFirst().orElse(null);
    }

    @Override
    public boolean deleteUser(User user) throws IOException {
        if (userList.contains(user)) {
            userList.remove(user);
            jsonDBSaver.saveEntityListToJsonFormatFile(userList);
            return true;
        }
        return false;
    }

    @Override
    public boolean blockUser(User user) throws IOException {
        return changeBlockingFlagUser(user, true);
    }

    @Override
    public boolean unblockUser(User user) throws IOException {
        return changeBlockingFlagUser(user, false);
    }

    private boolean changeBlockingFlagUser(User user, boolean b) throws IOException {
        if (userList.contains(user)) {
            userList.get(userList.indexOf(user)).setBlocked(b);
            jsonDBSaver.saveEntityListToJsonFormatFile(userList);
            return true;
        }
        return false;
    }

    @Override
    public boolean addBookMark(BookMark bookMark) throws IOException {
        if (!bookMarkList.contains(bookMark)) {
            bookMarkList.add(bookMark);
            jsonDBSaver.saveEntityListToJsonFormatFile(bookMarkList);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBookMark(BookMark bookMark) throws IOException {
        if (bookMarkList.contains(bookMark)) {
            bookMarkList.remove(bookMark);
            jsonDBSaver.saveEntityListToJsonFormatFile(bookMarkList);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAuthor(Author author) throws IOException {
        if (!authorsList.contains(author)) {
            if (!authorsList.isEmpty()) {
                int maxCurrentID = authorsList.stream()
                        .max(Comparator.comparing(Author::getNumberID))
                        .get()
                        .getNumberID();
                author.setNumberID(++maxCurrentID);
            }
            authorsList.add(author);
            jsonDBSaver.saveEntityListToJsonFormatFile(authorsList);
            return true;
        }
        return false;
    }

    @Override
    public Author findAuthor(String authorName) {
        return authorsList.stream()
                .filter(author -> author.getAuthorName().equals(authorName))
                .findFirst().orElse(null);
    }

    @Override
    public boolean deleteAuthorWithAllHisBooks(Author author) throws IOException {
        if (authorsList.contains(author)) {
            bookList.removeIf(book -> book.getAuthor().equals(author));
            authorsList.remove(author);
            jsonDBSaver.saveEntityListToJsonFormatFile(authorsList);
            jsonDBSaver.saveEntityListToJsonFormatFile(bookList);
            return true;
        }
        return false;
    }

    @Override
    public List<Book> findBooksByPartAuthorName(String partAuthorName) {
        return bookList.stream()
                .filter(book -> book.getAuthor().getAuthorName().contains(partAuthorName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByYearsRange(int firstYear, int secondYear) {
        return bookList.stream()
                .filter(book -> book.getYearPublishing() >= firstYear && book.getYearPublishing() <= secondYear)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByParameters(int bookYear, int bookPages, String partBookName) {
        return bookList.stream()
                .filter(book -> book.getYearPublishing() == bookYear && book.getPagesNumber() == bookPages && book.getName().contains(partBookName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksWithUserBookMarks(User user) {
        List<Book> book = new ArrayList<>();
        bookMarkList.stream()
                .filter(b -> b.getUser().equals(user))
                .forEach(b -> book.add(b.getBook()));
        return book;
    }
}
