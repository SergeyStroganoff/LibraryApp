package org.stroganov.wsClient;

import jakarta.xml.ws.WebServiceClient;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.*;

import java.io.IOException;
import java.util.List;

@WebServiceClient(name = "Client", targetNamespace = "http://example.org")
public class LibraryWebServiceClient implements LibraryDAO {

    private static volatile LibraryWebServiceClient instance;
    private final LibraryService libraryService;

    public LibraryWebServiceClient() {
        // QName qname = new QName("http://ws.stroganov.org/", "LibraryServerServiceImplService");
        // URL url = new URL("http://127.0.0.1:8082/LibraryService/services?wsdl");
        // Service service = Service.create(url, qname);
        // libraryService = service.getPort(LibraryClientService.class);

        LibraryServerServiceImplService libraryServerServiceImplService = new LibraryServerServiceImplService();
        libraryService = libraryServerServiceImplService.getLibraryServerServiceImplPort();
    }

    public static synchronized LibraryWebServiceClient getInstance() {
        if (instance == null) {
            synchronized (LibraryWebServiceClient.class) {
                if (instance == null) {
                    instance = new LibraryWebServiceClient();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean addBook(Book book) throws IOException {
        try {
            return libraryService.addBook(book);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean addBooks(List<Book> bookList) throws IOException {
        BookArray bookArray = new BookArray();
        try {
            return libraryService.addBookLIst(bookArray);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean deleteBook(Book book) throws IOException {
        try {
            return libraryService.deleteBook(book);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public Book findBook(String numberISBN) {
        return libraryService.findBook(numberISBN);
    }

    @Override
    public List<Book> findBooksByPartName(String partOfName) {
        BookArray bookArray = libraryService.findBookByPartName(partOfName);
        return bookArray.getItem();
    }

    @Override
    public boolean addUser(User user) throws IOException {
        try {
            return libraryService.addUser(user);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public User findUser(String userLogin) {
        return libraryService.findUser(userLogin);
    }

    @Override
    public boolean deleteUser(User user) throws IOException {
        try {
            return libraryService.deleteUser(user);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean blockUser(User user) throws IOException {
        try {
            return libraryService.blockUser(user);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean unblockUser(User user) throws IOException {
        try {
            return libraryService.unblockUser(user);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean addBookMark(BookMark bookMark) throws IOException {
        try {
            return libraryService.addBookMark(bookMark);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean deleteBookMark(BookMark bookMark) throws IOException {
        try {
            return libraryService.deleteBookMark(bookMark);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean addAuthor(Author author) throws IOException {
        try {
            return libraryService.addAuthor(author);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public Author findAuthor(String authorName) {
        return libraryService.findAuthor(authorName);
    }

    @Override
    public boolean deleteAuthorWithAllHisBooks(Author author) throws IOException {
        try {
            return libraryService.deleteAuthorWithAllHisBooks(author);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public List<Book> findBooksByPartAuthorName(String partAuthorName) {
        return libraryService.findBooksByPartAuthorName(partAuthorName).getItem();

    }

    @Override
    public List<Book> findBooksByYearsRange(int firstYear, int secondYear) {
        return libraryService.findBooksByYearsRange(firstYear, secondYear).getItem();
    }

    @Override
    public List<Book> findBooksByParameters(int bookYear, int bookPages, String partBookName) {
        return libraryService.findBooksByParameters(bookYear, bookPages, partBookName).getItem();
    }

    @Override
    public List<Book> findBooksWithUserBookMarks(User user) {
        return libraryService.findBooksWithUserBookMarks(user).getItem();

    }

    @Override
    public List<BookMark> findUserBookMarks(User user) {
        return libraryService.findUserBookMarks(user).getItem();

    }

    @Override
    public boolean addHistoryEvent(History history) {
        return libraryService.addHistoryEvent(history);
    }

    @Override
    public List<History> getAllHistory() {
        return libraryService.getAllHistory().getItem();
    }
}
