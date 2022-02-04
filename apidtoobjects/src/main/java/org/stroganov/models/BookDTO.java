package org.stroganov.models;

import org.stroganov.entities.Book;

import java.io.Serializable;

public class BookDTO implements Serializable {

    private String numberISBN;

    private String bookName;

    private AuthorDTO authorDTOName;

    private int yearPublishing;

    private int pagesNumber;

    public BookDTO(String numberISBN, String bookName, AuthorDTO authorDTOName, int yearPublishing, int pagesNumber) {
        this.numberISBN = numberISBN;
        this.bookName = bookName;
        this.authorDTOName = authorDTOName;
        this.yearPublishing = yearPublishing;
        this.pagesNumber = pagesNumber;
    }

    public BookDTO(Book book) {
        this.numberISBN = book.getNumberISBN();
        this.bookName = book.getBookName();
        this.authorDTOName = new AuthorDTO(book.getAuthorName());
        this.yearPublishing = book.getYearPublishing();
        this.pagesNumber = book.getPagesNumber();
    }

    public BookDTO() {
    }

    public String getNumberISBN() {
        return numberISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public AuthorDTO getAuthor() {
        return authorDTOName;
    }

    public int getYearPublishing() {
        return yearPublishing;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setAuthor(AuthorDTO authorDTOName) {
        this.authorDTOName = authorDTOName;
    }

    public void setNumberISBN(String numberISBN) {
        this.numberISBN = numberISBN;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public AuthorDTO getAuthorName() {
        return authorDTOName;
    }

    public void setAuthorName(AuthorDTO authorDTOName) {
        this.authorDTOName = authorDTOName;
    }

    public void setYearPublishing(int yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDTO bookDTO = (BookDTO) o;

        if (yearPublishing != bookDTO.yearPublishing) return false;
        if (pagesNumber != bookDTO.pagesNumber) return false;
        if (!numberISBN.equals(bookDTO.numberISBN)) return false;
        if (!bookName.equals(bookDTO.bookName)) return false;
        return authorDTOName.equals(bookDTO.authorDTOName);
    }

    @Override
    public int hashCode() {
        int result = numberISBN.hashCode();
        result = 31 * result + bookName.hashCode();
        result = 31 * result + authorDTOName.hashCode();
        result = 31 * result + yearPublishing;
        result = 31 * result + pagesNumber;
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "numberISBN='" + numberISBN + '\'' +
                ", bookName='" + bookName + '\'' +
                ", authorName=" + authorDTOName +
                ", yearPublishing=" + yearPublishing +
                ", pagesNumber=" + pagesNumber +
                '}';
    }
}
