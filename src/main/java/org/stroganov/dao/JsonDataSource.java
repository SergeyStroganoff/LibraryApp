package org.stroganov.dao;

import org.stroganov.App;
import org.stroganov.JsonDBAPI.JsonDBLoader;
import org.stroganov.JsonDBAPI.JsonDBSaver;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.exceptions.DBExceptions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JsonDataSource implements LibraryDAO {

    private static JsonDataSource instance;
    private final List<Book> bookList;
    private final List<User> userList;
    private final List<BookMark> bookMarkList;
    private final List<Author> authorsList;
    private final JsonDBSaver jsonDBSaver;

    public static synchronized JsonDataSource getInstance() throws DBExceptions {
        if (instance == null) {
            instance = new JsonDataSource();
        }
        return instance;
    }

    private JsonDataSource() throws DBExceptions {
        JsonDBLoader jsonDBLoader = new JsonDBLoader(App.properties);
        jsonDBSaver = new JsonDBSaver(App.properties);
        bookList = jsonDBLoader.loadBooks();
        userList = jsonDBLoader.loadUsers();
        bookMarkList = jsonDBLoader.loadBookMarks();
        authorsList = jsonDBLoader.loadAuthors();
    }

    @Override
    public boolean addBook(Book book) {
        boolean isSaved;
        if (!bookList.contains(book)) {
            bookList.add(book);
            isSaved = jsonDBSaver.saveEntityListToJsonFormatFile(bookList);
            if (!authorsList.contains(book.getAuthor())) {
                authorsList.add(book.getAuthor());
                jsonDBSaver.saveEntityListToJsonFormatFile(authorsList);
            }
        } else isSaved = false;
        return isSaved;
    }

    @Override
    public boolean deleteBook(Book book) {
        if (bookList.contains(book)) {
            bookList.remove(book);
            return jsonDBSaver.saveEntityListToJsonFormatFile(bookList);
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
    public boolean addUser(User user) {
        boolean isSaved = false;
        if (!userList.contains(user)) {
            if (!userList.isEmpty()) {
                int maxCurrentID = 0;
                for (User user1 : userList) {
                    if (user1.getNumberID() > maxCurrentID) {
                        maxCurrentID = user.getNumberID();
                    }
                }
                user.setNumberID(++maxCurrentID);
            }
            userList.add(user);
            isSaved = jsonDBSaver.saveEntityListToJsonFormatFile(userList);
        }
        return isSaved;
    }

    @Override
    public User findUser(String userLogin) {
        return userList.stream()
                .filter(user -> user.getLogin().equals(userLogin))
                .findFirst().orElse(null);
    }

    @Override
    public boolean deleteUser(User user) {
        if (userList.contains(user)) {
            userList.remove(user);
            return jsonDBSaver.saveEntityListToJsonFormatFile(userList);
        }
        return false;
    }

    @Override
    public boolean blockUser(User user) {
        return changeBlockingFlagUser(user, true);
    }

    @Override
    public boolean unblockUser(User user) {
        return changeBlockingFlagUser(user, false);
    }

    private boolean changeBlockingFlagUser(User user, boolean b) {
        if (userList.contains(user)) {
            userList.get(userList.indexOf(user)).setBlocked(b);
            return jsonDBSaver.saveEntityListToJsonFormatFile(userList);
        }
        return false;
    }

    @Override
    public boolean addBookMark(BookMark bookMark) {
        boolean isSaved = false;
        if (!bookMarkList.contains(bookMark)) {
            bookMarkList.add(bookMark);
            isSaved = jsonDBSaver.saveEntityListToJsonFormatFile(bookMarkList);
        }
        return isSaved;
    }

    @Override
    public boolean deleteBookMark(BookMark bookMark) {
        if (bookMarkList.contains(bookMark)) {
            bookMarkList.remove(bookMark);
            return jsonDBSaver.saveEntityListToJsonFormatFile(bookMarkList);
        }
        return false;
    }

    @Override
    public boolean addAuthor(Author author) {
        boolean isSaved = false;
        if (!authorsList.contains(author)) {
            if (!authorsList.isEmpty()) {
                int maxCurrentID = authorsList.stream()
                        .max(Comparator.comparing(Author::getNumberID))
                        .get()
                        .getNumberID();
                author.setNumberID(++maxCurrentID);
            }
            authorsList.add(author);
            isSaved = jsonDBSaver.saveEntityListToJsonFormatFile(authorsList);
        }
        return isSaved;
    }

    @Override
    public Author findAuthor(String authorName) {
        return authorsList.stream()
                .filter(author -> author.getAuthorName().equals(authorName))
                .findFirst().orElse(null);
    }

    @Override
    public boolean deleteAuthorWithAllHisBooks(Author author) {
        if (authorsList.contains(author)) {
            bookList.removeIf(book -> book.getAuthor().equals(author));
            authorsList.remove(author);
            return jsonDBSaver.saveEntityListToJsonFormatFile(authorsList) && jsonDBSaver.saveEntityListToJsonFormatFile(bookList);
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
