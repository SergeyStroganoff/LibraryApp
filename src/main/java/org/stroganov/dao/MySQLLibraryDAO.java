package org.stroganov.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.exceptions.DBExceptions;

import java.io.IOException;
import java.util.List;

public class MySQLLibraryDAO implements LibraryDAO {

    private final SessionFactory sessionFactory;
    private static MySQLLibraryDAO instance;
    private final Logger LOGGER = Logger.getLogger(MySQLLibraryDAO.class);

    public static synchronized MySQLLibraryDAO getInstance(SessionFactory sessionFactory) throws DBExceptions {
        if (instance == null) {
            instance = new MySQLLibraryDAO(sessionFactory);
        }
        return instance;
    }

    public MySQLLibraryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addBook(Book book) throws IOException {
        return false;
    }

    @Override
    public boolean addBook(List<Book> bookList) throws IOException {
        return false;
    }

    @Override
    public boolean deleteBook(Book book) throws IOException {
        return false;
    }

    @Override
    public Book findBook(String numberISBN) {
        return null;
    }

    @Override
    public List<Book> findBooksByPartName(String partOfName) {
        return null;
    }

    @Override
    public boolean addUser(User user) throws IOException {
        user = new User(0, "Иванов Григорий", "admin", "12345", false, true);
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(user);
        session.close();
        sessionFactory.close();
        return false;
    }

    @Override
    public User findUser(String userLogin) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE login = :userLogin");
            query.setParameter("userLogin", userLogin);
            transaction.commit();
            return (User) query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error("Hibernate query error when 'findUser' tried", e);
        }
        return null;
    }

    @Override
    public boolean deleteUser(User user) throws IOException {
        return false;
    }

    @Override
    public boolean blockUser(User user) throws IOException {
        return false;
    }

    @Override
    public boolean unblockUser(User user) throws IOException {
        return false;
    }

    @Override
    public boolean addBookMark(BookMark bookMark) throws IOException {
        return false;
    }

    @Override
    public boolean deleteBookMark(BookMark bookMark) throws IOException {
        return false;
    }

    @Override
    public boolean addAuthor(Author author) throws IOException {
        return false;
    }

    @Override
    public Author findAuthor(String authorName) {
        return null;
    }

    @Override
    public boolean deleteAuthorWithAllHisBooks(Author author) throws IOException {
        return false;
    }

    @Override
    public List<Book> findBooksByPartAuthorName(String partAuthorName) {
        return null;
    }

    @Override
    public List<Book> findBooksByYearsRange(int firstYear, int secondYear) {
        return null;
    }

    @Override
    public List<Book> findBooksByParameters(int bookYear, int bookPages, String partBookName) {
        return null;
    }

    @Override
    public List<Book> findBooksWithUserBookMarks(User user) {
        return null;
    }
}
