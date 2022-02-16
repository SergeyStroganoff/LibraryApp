package org.stroganov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.stroganov.entities.History;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}
