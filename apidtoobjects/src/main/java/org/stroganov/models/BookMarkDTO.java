package org.stroganov.models;

import org.stroganov.entities.BookMark;

import java.io.Serializable;

public class BookMarkDTO implements Serializable {

    private int id;

    private BookDTO bookDTO;

    private UserDTO userDTO;

    private int bookPageNumber;

    public BookMarkDTO(BookDTO bookDTO, UserDTO userDTO, int pageNumber) {
        this.bookDTO = bookDTO;
        this.userDTO = userDTO;
        this.bookPageNumber = pageNumber;
    }

    public BookMarkDTO(BookMark bookMark) {
        this.bookDTO = new BookDTO(bookMark.getBook());
        this.userDTO = new UserDTO(bookMark.getUser());
        this.bookPageNumber = bookMark.getBookPageNumber();
    }

    public BookMarkDTO() {
    }

    public BookDTO getBook() {
        return bookDTO;
    }

    public UserDTO getUser() {
        return userDTO;
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

    public void setBook(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public void setUser(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void setBookPageNumber(int bookPageNumber) {
        this.bookPageNumber = bookPageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookMarkDTO bookMarkDTO = (BookMarkDTO) o;

        if (id != bookMarkDTO.id) return false;
        if (bookPageNumber != bookMarkDTO.bookPageNumber) return false;
        if (!bookDTO.equals(bookMarkDTO.bookDTO)) return false;
        return userDTO.equals(bookMarkDTO.userDTO);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + bookDTO.hashCode();
        result = 31 * result + userDTO.hashCode();
        result = 31 * result + bookPageNumber;
        return result;
    }
}


