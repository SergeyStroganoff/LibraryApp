package org.stroganov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMark, Integer> {

    List<BookMark> findBookMarksByUser(User user);

    BookMark findBookMarkById(int bookMarkId);
}
