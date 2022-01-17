package org.stroganov.wsClient;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.stroganov.ws package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _User_QNAME = new QName("http://ws.stroganov.org/", "user");
    private final static QName _BookMark_QNAME = new QName("http://ws.stroganov.org/", "bookMark");
    private final static QName _IOException_QNAME = new QName("http://ws.stroganov.org/", "IOException");
    private final static QName _History_QNAME = new QName("http://ws.stroganov.org/", "history");
    private final static QName _Book_QNAME = new QName("http://ws.stroganov.org/", "book");
    private final static QName _Author_QNAME = new QName("http://ws.stroganov.org/", "author");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.stroganov.ws
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Author }
     */
    public Author createAuthor() {
        return new Author();
    }

    /**
     * Create an instance of {@link Book }
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link IOException }
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link History }
     */
    public History createHistory() {
        return new History();
    }

    /**
     * Create an instance of {@link User }
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link BookMark }
     */
    public BookMark createBookMark() {
        return new BookMark();
    }

    /**
     * Create an instance of {@link HistoryArray }
     */
    public HistoryArray createHistoryArray() {
        return new HistoryArray();
    }

    /**
     * Create an instance of {@link BookArray }
     */
    public BookArray createBookArray() {
        return new BookArray();
    }

    /**
     * Create an instance of {@link BookMarkArray }
     */
    public BookMarkArray createBookMarkArray() {
        return new BookMarkArray();
    }

    /**
     * Create an instance of {@link UserArray }
     */
    public UserArray createUserArray() {
        return new UserArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.stroganov.org/", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookMark }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.stroganov.org/", name = "bookMark")
    public JAXBElement<BookMark> createBookMark(BookMark value) {
        return new JAXBElement<BookMark>(_BookMark_QNAME, BookMark.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.stroganov.org/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link History }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.stroganov.org/", name = "history")
    public JAXBElement<History> createHistory(History value) {
        return new JAXBElement<History>(_History_QNAME, History.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Book }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.stroganov.org/", name = "book")
    public JAXBElement<Book> createBook(Book value) {
        return new JAXBElement<Book>(_Book_QNAME, Book.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Author }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ws.stroganov.org/", name = "author")
    public JAXBElement<Author> createAuthor(Author value) {
        return new JAXBElement<Author>(_Author_QNAME, Author.class, null, value);
    }

}
