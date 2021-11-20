package org.stroganov.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = ("bookmarks"))
public class BookMark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private Book book;
    @OneToOne(cascade = CascadeType.ALL)
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


