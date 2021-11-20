package org.stroganov.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.stroganov.entities.Book;
import org.stroganov.entities.User;
import org.stroganov.util.HibernateUtil;

import java.util.List;

class MySQLLibraryDAOTest {
    public static LibraryDAO libraryDAO;

    @BeforeAll
    public static void before() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        libraryDAO = MySQLLibraryDAO.getInstance(sessionFactory);
    }

    @Test
    void addBook() {
    }

    @Test
    void testAddBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void findBook() {
    }

    @Test
    void findBooksByPartName() {
    }

    @Test
    void findBooksByPartAuthorName_ReturnBook() {
        // GIVEN
        String partOfAuthorName = "роу";
        // WHEN
        List<Book> bookList = libraryDAO.findBooksByPartAuthorName(partOfAuthorName);
        // THEN
        Assertions.assertFalse(bookList.isEmpty());
        Assertions.assertTrue(bookList.get(0).getAuthor().getAuthorName().toLowerCase().contains(partOfAuthorName));
    }

    @Test
    void findBooksByYearsRange_Return_Not_Empty_ListOfBook() {
        // GIVEN
        String bookName = "Гарри Поттер. Полная коллекция";
        // WHEN
        List<Book> bookList = libraryDAO.findBooksByYearsRange(2016, 2018);
        // THEN
        Assertions.assertFalse(bookList.isEmpty());
        Assertions.assertEquals(bookList.get(0).getBookName(), bookName);
    }

    @Test
    void findBooksByParameters_Return_Not_Empty_ListOfBook() {
        // GIVEN
        String bookName = "Гарри Поттер. Полная коллекция";
        // WHEN
        List<Book> bookList = libraryDAO.findBooksByParameters(2017, 1856, "поттер");
        // THEN
        Assertions.assertFalse(bookList.isEmpty());
        Assertions.assertEquals(bookList.get(0).getBookName(), bookName);
    }

    @Test
    void findBooksWithUserBookMarks_Return_Not_Empty_ListOfBook() {
        // GIVEN
        String bookName = "Гарри Поттер. Полная коллекция";
        User user = libraryDAO.findUser("admin");
        // WHEN
        List<Book> bookList = libraryDAO.findBooksWithUserBookMarks(user);
        // THEN
        Assertions.assertFalse(bookList.isEmpty());
        Assertions.assertEquals(bookList.get(0).getBookName(), bookName);
    }

    @Test
    void addUser() {
    }

    @Test
    void findUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void blockUser() {
    }

    @Test
    void unblockUser() {
    }

    @Test
    void changeUserStatus() {
    }

    @Test
    void addBookMark() {
    }

    @Test
    void deleteBookMark() {
    }
}