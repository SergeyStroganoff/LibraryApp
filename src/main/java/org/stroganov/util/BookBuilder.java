package org.stroganov.util;

import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookBuilder {

    LibraryDAO libraryDAO;

    public BookBuilder(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }

    public Book buildBook(String[] booksParameters) {
        String numberISBN = booksParameters[0];
        String name = booksParameters[1];
        String authorName = booksParameters[2];
        Author author = libraryDAO.findAuthor(authorName);
        if (author == null) {
            author = new Author(1, authorName);
            libraryDAO.addAuthor(author);
        }
        int yearPublishing = Integer.parseInt(booksParameters[3]);
        int pagesNumber = Integer.parseInt(booksParameters[4]);
        return new Book(numberISBN, name, author, yearPublishing, pagesNumber);
    }

    public List<Book> getBookListFromTXTFile(String filePath) throws IOException {
        List<Book> bookList = new ArrayList<>();
        List<String> stringList = FileUtil.getListOfStringsFile(filePath);
        for (String string : stringList) {
            String[] booksParameters = string.split(";");
            bookList.add(buildBook(booksParameters));
        }
        return bookList;
    }
}

