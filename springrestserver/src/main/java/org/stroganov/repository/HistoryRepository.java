package org.stroganov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.stroganov.entities.History;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {

    List<History> findAllByUser_Login(String userLogin);
}
