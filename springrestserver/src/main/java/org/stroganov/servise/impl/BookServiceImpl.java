package org.stroganov.servise.impl;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroganov.entities.Book;
import org.stroganov.exceptions.AddBookException;
import org.stroganov.exceptions.BookNotExistException;
import org.stroganov.models.BookDTO;
import org.stroganov.repository.BookRepository;
import org.stroganov.servise.BookService;
import org.stroganov.util.TransitionObjectsService;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void delete(String bookISBN) throws BookNotExistException {
        Optional<Book> book = bookRepository.findById(bookISBN);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
        } else {
            log.info("Book with bookISBN " + bookISBN + "was not found");
            throw new BookNotExistException("Book with bookISBN " + bookISBN + "was not found");
        }
    }

    @Override
    public void addBook(BookDTO bookDTO) throws AddBookException {
        Book book = modelMapper.map(bookDTO, Book.class);   //TransitionObjectsService.getBook(bookDTO);
        try {
            bookRepository.save(book);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new AddBookException(e);
        }
    }

    @Override
    public void addBooks(List<BookDTO> bookDTOList) throws AddBookException {
        List<Book> book = TransitionObjectsService.getBookList(bookDTOList);
        try {
            bookRepository.saveAllAndFlush(book);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new AddBookException(e);
        }
    }

    @Override
    public BookDTO findBook(String numberISBN) throws BookNotExistException {
        Optional<Book> book = bookRepository.findById(numberISBN);
        if (book.isPresent()) {
            return new BookDTO(book.get());
        } else {
            throw new BookNotExistException("Book not found");
        }
    }

    @Override
    public List<BookDTO> findBooksByPartName(String partOfName) {
        List<Book> bookList = bookRepository.findBooksByBookNameContaining(partOfName);
        return TransitionObjectsService.getBookDTOList(bookList);
    }

    @Override
    public List<BookDTO> findBooksByPartAuthorName(String partAuthorName) {
        List<Book> bookList = bookRepository.findBooksByAuthorName_AuthorName(partAuthorName);
        return TransitionObjectsService.getBookDTOList(bookList);
    }

    @Override
    public List<BookDTO> findBooksByYearsRange(int firstYear, int secondYear) {
        List<Book> bookList = bookRepository.findBooksByYearPublishingBetween(firstYear, secondYear);
        return TransitionObjectsService.getBookDTOList(bookList);
    }

    @Override
    public List<BookDTO> findBooksByParameters(int bookYear, int bookPages, String partBookName) {
        List<Book> bookList = bookRepository.findBooksByYearPublishingIsAndPagesNumberIsAndBookName(bookYear, bookPages, partBookName);
        return TransitionObjectsService.getBookDTOList(bookList);
    }

    @Override
    public List<BookDTO> findBooksWithUserBookMarks(String userLogin) {
        List<Book> bookList = bookRepository.findBooksWithUserBookMarks(userLogin);
        return TransitionObjectsService.getBookDTOList(bookList);
    }
}
