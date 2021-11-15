package org.stroganov.entities;

import javax.persistence.*;

@Entity
@Table(name = ("books"))
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numberISBN_id")
    private String numberISBN;
    @Column(name = "book_name")
    private String bookName;
    @ManyToOne
    private Author authorName;
    @Column(name = "year_publishing")
    private int yearPublishing;
    @Column(name = "pages_number")
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
}
