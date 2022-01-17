package org.stroganov.wsClient;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bookMark complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="bookMark">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="book" type="{http://ws.stroganov.org/}book" minOccurs="0"/>
 *         &lt;element name="bookPageNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="user" type="{http://ws.stroganov.org/}user" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bookMark", propOrder = {
        "book",
        "bookPageNumber",
        "id",
        "user"
})
public class BookMark {

    protected Book book;
    protected int bookPageNumber;
    protected int id;
    protected User user;

    /**
     * Gets the value of the book property.
     *
     * @return possible object is
     * {@link Book }
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the value of the book property.
     *
     * @param value allowed object is
     *              {@link Book }
     */
    public void setBook(Book value) {
        this.book = value;
    }

    /**
     * Gets the value of the bookPageNumber property.
     */
    public int getBookPageNumber() {
        return bookPageNumber;
    }

    /**
     * Sets the value of the bookPageNumber property.
     */
    public void setBookPageNumber(int value) {
        this.bookPageNumber = value;
    }

    /**
     * Gets the value of the id property.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the user property.
     *
     * @return possible object is
     * {@link User }
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     *
     * @param value allowed object is
     *              {@link User }
     */
    public void setUser(User value) {
        this.user = value;
    }

}
