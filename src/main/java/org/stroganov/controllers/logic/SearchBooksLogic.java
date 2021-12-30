package org.stroganov.controllers.logic;

import org.stroganov.dao.LibraryDAO;
import org.stroganov.entities.Book;
import org.stroganov.util.DataManager;
import org.stroganov.util.StringValidator;

import java.util.ArrayList;
import java.util.List;

public class SearchBooksLogic {

    public List<Book> searchBooksByParameters(String ISBN, String bookName, String bookAuthorName,
                                              String bookPublishingYear, String bookPublishingYearSecond,
                                              String bookPagesNumber) {
        List<Book> bookList = new ArrayList<>();
        LibraryDAO libraryDAO = DataManager.getLibraryDAO();

        if (ISBN != null && !ISBN.isEmpty()) {
            bookList.add(libraryDAO.findBook(ISBN));
            return bookList;
        }

        if (bookPagesNumber != null && !bookPagesNumber.isEmpty()) {
            if (bookPublishingYear != null && bookName != null) {
                if (StringValidator.isStringNumberPage(bookPublishingYear) && StringValidator.isStringYear(bookPublishingYear)) {
                    int bookPublishingYearInt = Integer.parseInt(bookPublishingYear);
                    int bookPagesNumberInt = Integer.parseInt(bookPagesNumber);
                    return libraryDAO.findBooksByParameters(bookPublishingYearInt, bookPagesNumberInt, bookName);
                }
            }
        }

        if (bookPublishingYearSecond != null && !bookPublishingYearSecond.isEmpty()) {
            if (bookPublishingYear != null && !bookPublishingYear.isEmpty()) {
                if (StringValidator.isStringYear(bookPublishingYear) && StringValidator.isStringYear(bookPublishingYearSecond)) {
                    int bookPublishingYearInt = Integer.parseInt(bookPublishingYear);
                    int bookPublishingYearSecondInt = Integer.parseInt(bookPublishingYearSecond);
                    return libraryDAO.findBooksByYearsRange(bookPublishingYearInt, bookPublishingYearSecondInt);
                }
            }
        }

        if (bookName != null && !bookName.isEmpty()) {
            return libraryDAO.findBooksByPartName(bookName);
        }

        if (bookAuthorName != null && !bookAuthorName.isEmpty()) {
            return libraryDAO.findBooksByPartAuthorName(bookAuthorName);
        }
        return bookList;
    }
}
