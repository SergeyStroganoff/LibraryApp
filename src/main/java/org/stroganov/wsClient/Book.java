package org.stroganov.wsClient;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for book complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="book">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="author" type="{http://ws.stroganov.org/}author" minOccurs="0"/>
 *         &lt;element name="authorName" type="{http://ws.stroganov.org/}author" minOccurs="0"/>
 *         &lt;element name="bookName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberISBN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pagesNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="yearPublishing" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "book", propOrder = {
        "author",
        "authorName",
        "bookName",
        "numberISBN",
        "pagesNumber",
        "yearPublishing"
})
public class Book {

    protected Author author;
    protected Author authorName;
    protected String bookName;
    protected String numberISBN;
    protected int pagesNumber;
    protected int yearPublishing;

    /**
     * Gets the value of the author property.
     *
     * @return possible object is
     * {@link Author }
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     *
     * @param value allowed object is
     *              {@link Author }
     */
    public void setAuthor(Author value) {
        this.author = value;
    }

    /**
     * Gets the value of the authorName property.
     *
     * @return possible object is
     * {@link Author }
     */
    public Author getAuthorName() {
        return authorName;
    }

    /**
     * Sets the value of the authorName property.
     *
     * @param value allowed object is
     *              {@link Author }
     */
    public void setAuthorName(Author value) {
        this.authorName = value;
    }

    /**
     * Gets the value of the bookName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * Sets the value of the bookName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setBookName(String value) {
        this.bookName = value;
    }

    /**
     * Gets the value of the numberISBN property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNumberISBN() {
        return numberISBN;
    }

    /**
     * Sets the value of the numberISBN property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNumberISBN(String value) {
        this.numberISBN = value;
    }

    /**
     * Gets the value of the pagesNumber property.
     */
    public int getPagesNumber() {
        return pagesNumber;
    }

    /**
     * Sets the value of the pagesNumber property.
     */
    public void setPagesNumber(int value) {
        this.pagesNumber = value;
    }

    /**
     * Gets the value of the yearPublishing property.
     */
    public int getYearPublishing() {
        return yearPublishing;
    }

    /**
     * Sets the value of the yearPublishing property.
     */
    public void setYearPublishing(int value) {
        this.yearPublishing = value;
    }

}
