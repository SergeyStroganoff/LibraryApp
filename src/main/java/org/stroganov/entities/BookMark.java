package org.stroganov.entities;

public class BookMark {
    Book book;
    User user;
    int bookPageNumber;

    public BookMark(Book book, User user, int pageNumber) {
        this.book = book;
        this.user = user;
        this.bookPageNumber = pageNumber;
    }
}


