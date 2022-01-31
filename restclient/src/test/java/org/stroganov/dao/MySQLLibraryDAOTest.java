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
    void findBook() throws IOException {
        // GIVEN
        Book testBook = new Book("99-99", "TEST BOOK", new Author(1, "Test Author"), 2021, 1);
        libraryDAO.addBook(testBook);
        // WHEN
        Book bookFromDB = libraryDAO.findBook("99-99");
        libraryDAO.deleteBook(libraryDAO.findBook(testBook.getNumberISBN()));
        // THEN
        Assertions.assertEquals(testBook.getBookName(), bookFromDB.getBookName());
    }


    @Test
    void findBooksByPartName() throws IOException {
        // GIVEN
        Book testBook = new Book("99-99", "TEST BOOK", new Author(1, "Test Author"), 2021, 1);
        libraryDAO.addBook(testBook);
        String partOfBookName = "OO";
        // WHEN
        List<Book> bookList = libraryDAO.findBooksByPartName("OO");
        libraryDAO.deleteBook(libraryDAO.findBook(testBook.getNumberISBN()));
        // THEN
        Assertions.assertTrue(bookList.get(0).getBookName().contains(partOfBookName));
    }

    @Test
    void findBooksByPartAuthorName_ReturnBook() throws IOException {
        // GIVEN
        String partOfAuthorName = "est";
        // WHEN
        Book testBook = new Book("99-99", "TEST BOOK", new Author(1, "Test Author"), 2021, 1);
        libraryDAO.addBook(testBook);
        List<Book> bookList = libraryDAO.findBooksByPartAuthorName(partOfAuthorName);
        libraryDAO.deleteBook(libraryDAO.findBook(testBook.getNumberISBN()));
        // THEN
        Assertions.assertFalse(bookList.isEmpty());
        Assertions.assertTrue(bookList.get(0).getAuthor().getAuthorName().toLowerCase().contains(partOfAuthorName));
    }

    @Test
    void findBooksByYearsRange_Return_Not_Empty_ListOfBook() throws IOException {
        // GIVEN
        Book testBook = new Book("99-99", "TEST BOOK", new Author(1, "Test Author"), 21, 1);
        libraryDAO.addBook(testBook);
        // WHEN
        List<Book> bookList = libraryDAO.findBooksByYearsRange(20, 21);
        libraryDAO.deleteBook(libraryDAO.findBook(testBook.getNumberISBN()));
        // THEN
        Assertions.assertFalse(bookList.isEmpty());
        Assertions.assertEquals(bookList.get(0).getBookName(), testBook.getBookName());
    }

    @Test
    void findBooksByParameters_Return_Not_Empty_ListOfBook() throws IOException {
        // GIVEN
        Book testBook = new Book("99-99", "TEST BOOK", new Author(1, "Test Author"), 21, 1);
        libraryDAO.addBook(testBook);
        // WHEN
        List<Book> bookList = libraryDAO.findBooksByParameters(21, 1, "T");
        libraryDAO.deleteBook(libraryDAO.findBook(testBook.getNumberISBN()));
        // THEN
        Assertions.assertFalse(bookList.isEmpty());
        Assertions.assertEquals(bookList.get(0).getBookName(), testBook.getBookName());
    }

    @Test
    void findBooksWithUserBookMarks_Return_Not_Empty_ListOfBook() throws IOException {
        // GIVEN
        User testUser = new User(1, "SMM", "user11", "123", false, true);
        // WHEN
        libraryDAO.addUser(testUser);
        User userFromDB = libraryDAO.findUser(testUser.getLogin());
        // WHEN
        List<Book> bookList = libraryDAO.findBooksWithUserBookMarks(userFromDB);
        libraryDAO.deleteUser(userFromDB);
        // THEN
        Assertions.assertTrue(bookList.isEmpty());
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
        libraryDAO.deleteUser(testUser);
        boolean isAdded = libraryDAO.addUser(testUser);
        User userFromDB = libraryDAO.findUser(testUser.getLogin());
        libraryDAO.deleteUser(userFromDB);
        // THEN
        Assertions.assertTrue(isAdded);
        Assertions.assertNotNull(userFromDB);
    }

    @Test
    void findUser_Return_TestUser() throws IOException {

        User testUser = new User(1, "SMM", "user11", "123", false, true);
        // WHEN
        libraryDAO.deleteUser(testUser);
        boolean isAdded = libraryDAO.addUser(testUser);
        User userFromDB = libraryDAO.findUser(testUser.getLogin());
        if (isAdded) {
            libraryDAO.deleteUser(userFromDB);
        }
        Assertions.assertEquals(userFromDB.getFullName(), testUser.getFullName());
    }

    @Test
    void blockUser() throws IOException {
        // GIVEN
        User testUser = new User(1, "TEST USER", "test", "sdfsdf4", false, false);
        // WHEN
        libraryDAO.addUser(testUser);
        boolean isUserWasBlocked = libraryDAO.blockUser(testUser);
        User userFromDB = libraryDAO.findUser(testUser.getLogin());
        libraryDAO.deleteUser(testUser);
        // THEN
        Assertions.assertTrue(isUserWasBlocked);
        Assertions.assertTrue(userFromDB.isBlocked());
    }

    @Test
    void unblockUser_Return_True() throws IOException {
        // GIVEN
        User testUser = new User(1, "TEST USER", "test", "sdfsdf4", false, false);
        libraryDAO.addUser(testUser);
        libraryDAO.blockUser(testUser);
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
        libraryDAO.deleteBookMark(bookMark);
        boolean isAdded = libraryDAO.addBookMark(bookMark);
        // THEN
        Assertions.assertTrue(isAdded);
        libraryDAO.deleteUser(testUser);
        libraryDAO.deleteBook(testBook);
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
        Author author = new Author(1, "Author");
        libraryDAO.deleteAuthorWithAllHisBooks(libraryDAO.findAuthor(author.getAuthorName()));
        // WHEN
        boolean isAdded = libraryDAO.addAuthor(author);
        // THEN
        Assertions.assertTrue(isAdded);
    }

    @Test
    void findAuthor_Then_Return_True() throws IOException {
        // GIVEN
        Author testAuthor = new Author(1, "Author");
        boolean isAdded = libraryDAO.addAuthor(testAuthor);
        // WHEN
        Author author = libraryDAO.findAuthor(testAuthor.getAuthorName());
        if (isAdded) {
            libraryDAO.deleteAuthorWithAllHisBooks(author);
        }
        // THEN
        Assertions.assertEquals(author.getAuthorName(), testAuthor.getAuthorName());
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