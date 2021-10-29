package org.stroganov.entities;

public class BookMark {
    private Book book;
    private User user;
    private int bookPageNumber;

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


