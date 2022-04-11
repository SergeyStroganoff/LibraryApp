package org.stroganov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.stroganov.entities.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    @Override
    void delete(Book entity);

    Book findBookByNumberISBN(String numberISBN);

    List<Book> findBooksByBookNameContaining(String partOfName);

    @Query("select b from Book b where b.authorName.authorName = ?1")
    List<Book> findBooksByAuthorName_AuthorName(String name);  // changed

    List<Book> findBooksByYearPublishingBetween(int firstYear, int secondYear);  // changed

    List<Book> findBooksByYearPublishingIsAndPagesNumberIsAndBookName(int bookYear, int bookPages, String partBookName);  // changed

    /**
     * @param userLogin
     * @return
     */
    @Query("SELECT b FROM BookMark bm LEFT JOIN Book b on bm.book=b LEFT JOIN User u on bm.user=u WHERE u.login = :userLogin")
    List<Book> findBooksWithUserBookMarks(@Param("userLogin") String userLogin);
}
