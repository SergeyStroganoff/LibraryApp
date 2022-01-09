package org.stroganov.entities;

import jakarta.xml.bind.annotation.XmlRootElement;

import javax.persistence.*;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name = ("books"))
public class Book implements Serializable {
    @Id
    @Column(name = "id")
    private String numberISBN;
    @Column(name = "bookname")
    private String bookName;
    @ManyToOne()
    @JoinColumn(name = "authorName_authorid")
    private Author authorName;
    @Column(name = "yearpublishing")
    private int yearPublishing;
    @Column(name = "pagesnumber")
    private int pagesNumber;

    public Book(String numberISBN, String bookName, Author authorName, int yearPublishing, int pagesNumber) {
        this.numberISBN = numberISBN;
        this.bookName = bookName;
        this.authorName = authorName;
        this.yearPublishing = yearPublishing;
        this.pagesNumber = pagesNumber;
    }

    public Book() {
    }

    public String getNumberISBN() {
        return numberISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public Author getAuthor() {
        return authorName;
    }

    public int getYearPublishing() {
        return yearPublishing;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setAuthor(Author authorName) {
        this.authorName = authorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (yearPublishing != book.yearPublishing) return false;
        if (pagesNumber != book.pagesNumber) return false;
        if (!numberISBN.equals(book.numberISBN)) return false;
        if (!bookName.equals(book.bookName)) return false;
        return authorName.equals(book.authorName);
    }

    @Override
    public int hashCode() {
        int result = numberISBN.hashCode();
        result = 31 * result + bookName.hashCode();
        result = 31 * result + authorName.hashCode();
        result = 31 * result + yearPublishing;
        result = 31 * result + pagesNumber;
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "numberISBN='" + numberISBN + '\'' +
                ", bookName='" + bookName + '\'' +
                ", authorName=" + authorName +
                ", yearPublishing=" + yearPublishing +
                ", pagesNumber=" + pagesNumber +
                '}';
    }
}
