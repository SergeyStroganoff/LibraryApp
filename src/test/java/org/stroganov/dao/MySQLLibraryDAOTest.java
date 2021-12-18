package org.stroganov.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;
import org.stroganov.util.HibernateUtil;

import java.io.IOException;
import java.util.List;

class MySQLLibraryDAOTest {
    public static LibraryDAO libraryDAO;

    @BeforeAll
    public static void before() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        libraryDAO = MySQLLibraryDAO.getInstance(sessionFactory);
    }

    @Test
    void addBook_ReturnTrue() throws IOException {
        // GIVEN
        Book testBook = new Book("99-99", "TEST BOOK", new Author(1, "Test Author"), 2021, 1);
        // WHEN
        boolean isAdded = libraryDAO.addBook(testBook);
        // THEN
        Assertions.assertTrue(isAdded);
    }

    @Test
    void findBook() {
        // GIVEN
        Book testBook = new Book("99-99", "TEST BOOK", new Author(1, "Test Author"), 2021, 1);
        // WHEN
        Book bookFromDB = libraryDAO.findBook("99-99");
        // THEN
        Assertions.assertEquals(testBook.getBookName(), bookFromDB.getBookName());
    }


    @Test
    void findBooksByPartName() {
        // GIVEN
        String partOfBookName = "OO";
        // WHEN
        List<Book> bookList = libraryDAO.findBooksByPartName("OO");
        // THEN
        Assertions.assertTrue(bookList.get(0).getBookName().contains(partOfBookName));
    }

    @Test
    void findBooksByPartAuthorName_ReturnBook() {
        // GIVEN
        String partOfAuthorName = "est";
        // WHEN
        List<Book> bookList = libraryDAO.findBooksByPartAuthorName(partOfAuthorName);
        // THEN
        Assertions.assertFalse(bookList.isEmpty());
        Assertions.assertTrue(bookList.get(0).getAuthor().getAuthorName().toLowerCase().contains(partOfAuthorName));
    }

    @Test
    void findBooksByYearsRange_Return_Not_Empty_ListOfBook() {
        // GIVEN
        String bookName = "TEST BOOK";
        // WHEN
        List<Book> bookList = libraryDAO.findBooksByYearsRange(2020, 2021);
        // THEN
        Assertions.assertFalse(bookList.isEmpty());
        Assertions.assertEquals(bookList.get(0).getBookName(), bookName);
    }

    @Test
    void findBooksByParameters_Return_Not_Empty_ListOfBook() {
        // GIVEN
        String bookName = "TEST BOOK";
        // WHEN
        List<Book> bookList = libraryDAO.findBooksByParameters(2021, 1, "T");
        // THEN
        Assertions.assertFalse(bookList.isEmpty());
        Assertions.assertEquals(bookList.get(0).getBookName(), bookName);
    }

    @Test
    void findBooksWithUserBookMarks_Return_Not_Empty_ListOfBook() {
        // GIVEN
        User user = libraryDAO.findUser("newadmin");
        // WHEN
        List<Book> bookList = libraryDAO.findBooksWithUserBookMarks(user);
        // THEN
        Assertions.assertFalse(bookList.isEmpty());
    }

    @Test
    void deleteBook_Return_True() throws IOException {
        // GIVEN
        Book testBook = new Book("99-99", "TEST BOOK", new Author(1, "Test Author"), 2021, 1);
        // WHEN
        boolean isDeleted = libraryDAO.deleteBook(testBook);
        Book bookFromDB = libraryDAO.findBook("99-99");
        // THEN
        Assertions.assertTrue(isDeleted);
        Assertions.assertNull(bookFromDB);
    }

    @Test
    void addUser_Return_True() throws IOException {
        // GIVEN
        User testUser = new User(1, "SMM", "user11", "123", false, true);
        // WHEN
        boolean isAdded = libraryDAO.addUser(testUser);
        User userFromDB = libraryDAO.findUser("test");
        // THEN
        Assertions.assertTrue(isAdded);
        Assertions.assertNotNull(userFromDB);
    }

    @Test
    void findUser_Return_TestUser() {
        // GIVEN
        String userName = "TEST USER";
        // WHEN
        User userFromDB = libraryDAO.findUser("test");
        // THEN
        Assertions.assertEquals(userFromDB.getFullName(), userName);
    }

    @Test
    void blockUser() throws IOException {
        // GIVEN
        User testUser = new User(1, "TEST USER", "test", "sdfsdf4", false, false);
        // WHEN
        boolean isUserWasBlocked = libraryDAO.blockUser(testUser);
        User userFromDB = libraryDAO.findUser("test");
        // THEN
        Assertions.assertTrue(isUserWasBlocked);
        Assertions.assertTrue(userFromDB.isBlocked());
    }

    @Test
    void unblockUser_Return_True() throws IOException {
        // GIVEN
        User testUser = new User(1, "TEST USER", "test", "sdfsdf4", false, false);
        // WHEN
        boolean isUserWasUnBlocked = libraryDAO.unblockUser(testUser);
        User userFromDB = libraryDAO.findUser("test");
        // THEN
        Assertions.assertTrue(isUserWasUnBlocked);
        Assertions.assertFalse(userFromDB.isBlocked());
    }

    @Test
    void deleteUser_Return_True() throws IOException {
        // GIVEN
        User testUser = new User(1, "TEST USER", "test", "sdfsdf4", false, false);
        // WHEN

        boolean isUserWasDeleted = libraryDAO.deleteUser(testUser);
        User userFromDB = libraryDAO.findUser("test");
        // THEN
        Assertions.assertTrue(isUserWasDeleted);
        Assertions.assertNull(userFromDB);
    }


    @Test
    void addBookMark_Return_True() throws IOException {
        // GIVEN
        Book testBook = new Book("99", "TEST BOOK", new Author(1, "Test Author"), 2021, 1);
        User testUser = new User(10, "TEST_USER", "nextTestUser", "erte", false, false);
        BookMark bookMark = new BookMark(testBook, testUser, 100);
        libraryDAO.addUser(testUser);
        libraryDAO.addBook(testBook);
        // WHEN
        boolean isAdded = libraryDAO.addBookMark(bookMark);
        // THEN
        Assertions.assertTrue(isAdded);
        libraryDAO.deleteUser(testUser);
        libraryDAO.deleteBook(testBook);
        libraryDAO.deleteBookMark(bookMark);
    }

    @Test
    void deleteBookMark() throws IOException {
        // GIVEN
        Book testBook = new Book("99", "TEST BOOK", new Author(1, "Test Author"), 2021, 1);
        User testUser = new User(10, "TEST_USER", "nextTestUser", "erte", false, false);
        BookMark bookMark = new BookMark(testBook, testUser, 100);
        libraryDAO.addUser(testUser);
        libraryDAO.addBook(testBook);
        libraryDAO.addBookMark(bookMark);
        // WHEN
        boolean isAdded = libraryDAO.deleteBookMark(bookMark);
        // THEN
        Assertions.assertTrue(isAdded);
    }

    @Test
    void addAuthor() throws IOException {
        // GIVEN
        Author author = new Author(1, "Test Author");
        // WHEN
        boolean isAdded = libraryDAO.addAuthor(author);
        // THEN
        Assertions.assertTrue(isAdded);
    }

    @Test
    void findAuthor_Then_Return_True() {
        // GIVEN
        String authorName = "Test Author";
        // WHEN
        Author author = libraryDAO.findAuthor(authorName);
        // THEN
        Assertions.assertEquals(author.getAuthorName(), authorName);
    }

    @Test
    void deleteAuthorWithAllHisBooks() throws IOException {
        // GIVEN
        String authorName = "Test Author";
        Author author = libraryDAO.findAuthor(authorName);
        // WHEN
        boolean isDeleted = libraryDAO.deleteAuthorWithAllHisBooks(author);
        // THEN
        Assertions.assertTrue(isDeleted);
    }

}