package org.stroganov.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name = ("bookmarks"))
public class BookMark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne
    private Book book;
    @OneToOne
    private User user;
    @Column(name = "bookpagenumber")
    private int bookPageNumber;

    public BookMark(Book book, User user, int pageNumber) {
        this.book = book;
        this.user = user;
        this.bookPageNumber = pageNumber;
    }

    public BookMark() {
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public int getBookPageNumber() {
        return bookPageNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBookPageNumber(int bookPageNumber) {
        this.bookPageNumber = bookPageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookMark bookMark = (BookMark) o;

        if (id != bookMark.id) return false;
        if (bookPageNumber != bookMark.bookPageNumber) return false;
        if (!book.equals(bookMark.book)) return false;
        return user.equals(bookMark.user);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + book.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + bookPageNumber;
        return result;
    }
}


