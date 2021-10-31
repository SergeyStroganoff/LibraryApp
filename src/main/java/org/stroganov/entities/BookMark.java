package org.stroganov.entities;

public class BookMark {
    private final Book book;
    private final User user;
    private final int bookPageNumber;

    public BookMark(Book book, User user, int pageNumber) {
        this.book = book;
        this.user = user;
        this.bookPageNumber = pageNumber;
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
}


