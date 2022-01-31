package org.stroganov.wsClient;

import jakarta.jws.HandlerChain;
import jakarta.xml.ws.WebServiceClient;
import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.*;

import java.io.IOException;
import java.util.List;

@WebServiceClient(name = "Client", targetNamespace = "http://example.org")
@HandlerChain(file = "clientHandler.xml")
public class LibraryWebServiceClient implements LibraryDAO {

    private static volatile LibraryWebServiceClient instance;
    private final BookServiceClient bookServiceClient;
    private final UserServiceClient userServiceClient;
    private final AdminServiceClient adminServiceClient;
    private final SearchServiceClient searchServiceClient;

    public LibraryWebServiceClient() {
        // QName qname = new QName("http://ws.stroganov.org/", "LibraryServerServiceImplService");
        // URL url = new URL("http://127.0.0.1:8082/LibraryService/services?wsdl");
        // Service service = Service.create(url, qname);
        // libraryService = service.getPort(LibraryClientService.class);

        bookServiceClient = new BookServiceImplService().getBookServiceImplPort();
        userServiceClient = new UserServiceImplService().getUserServiceImplPort();
        adminServiceClient = new AdminServiceImplService().getAdminServiceImplPort();
        searchServiceClient = new SearchServiceImplService().getSearchServiceImplPort();
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
            return bookServiceClient.addBook(book);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean addBooks(List<Book> bookList) throws IOException {
        BookArray bookArray = new BookArray();
        try {
            return bookServiceClient.addBookLIst(bookArray);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean deleteBook(Book book) throws IOException {
        try {
            return bookServiceClient.deleteBook(book);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public Book findBook(String numberISBN) {
        return searchServiceClient.findBook(numberISBN);
    }

    @Override
    public List<Book> findBooksByPartName(String partOfName) {
        BookArray bookArray = searchServiceClient.findBooksByPartName(partOfName);
        return bookArray.getItem();
    }

    @Override
    public boolean addUser(User user) throws IOException {
        try {
            return adminServiceClient.addUser(user);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public User findUser(String userLogin) {
        return searchServiceClient.findUser(userLogin);
    }

    @Override
    public boolean deleteUser(User user) throws IOException {
        try {
            return adminServiceClient.deleteUser(user);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean blockUser(User user) throws IOException {
        try {
            return adminServiceClient.blockUser(user);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean unblockUser(User user) throws IOException {
        try {
            return adminServiceClient.unblockUser(user);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean addBookMark(BookMark bookMark) throws IOException {
        try {
            return userServiceClient.addBookMark(bookMark);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }


    @Override
    public boolean deleteBookMark(BookMark bookMark) throws IOException {
        try {
            return userServiceClient.deleteBookMark(bookMark);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean addAuthor(Author author) throws IOException {
        try {
            return bookServiceClient.addAuthor(author);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }

    }

    @Override
    public Author findAuthor(String authorName) {
        return searchServiceClient.findAuthor(authorName);
    }

    @Override
    public boolean deleteAuthorWithAllHisBooks(Author author) throws IOException {
        try {
            return userServiceClient.deleteAuthorWithAllHisBooks(author);
        } catch (IOException_Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public List<Book> findBooksByPartAuthorName(String partAuthorName) {
        return searchServiceClient.findBooksByPartAuthorName(partAuthorName).getItem();

    }

    @Override
    public List<Book> findBooksByYearsRange(int firstYear, int secondYear) {
        return searchServiceClient.findBooksByYearsRange(firstYear, secondYear).getItem();
    }

    @Override
    public List<Book> findBooksByParameters(int bookYear, int bookPages, String partBookName) {
        return searchServiceClient.findBooksByParameters(bookYear, bookPages, partBookName).getItem();
    }

    @Override
    public List<Book> findBooksWithUserBookMarks(User user) {
        return searchServiceClient.findBooksWithUserBookMarks(user).getItem();

    }

    @Override
    public List<BookMark> findUserBookMarks(User user) {
        return searchServiceClient.findUserBookMarks(user).getItem();

    }

    @Override
    public boolean addHistoryEvent(History history) {
        return userServiceClient.addHistoryEvent(history);
    }

    @Override
    public List<History> getAllHistory() {
        return adminServiceClient.getAllHistory().getItem();
    }
}
