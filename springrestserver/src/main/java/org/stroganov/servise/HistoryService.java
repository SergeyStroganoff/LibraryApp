package org.stroganov.servise;

import org.springframework.stereotype.Service;
import org.stroganov.entities.History;
import org.stroganov.exceptions.HistorySavingException;
import org.stroganov.models.HistoryDTO;

import java.util.List;
@Service
public interface HistoryService {
    void historyEventSave(HistoryDTO historyDTO) throws HistorySavingException;

    List<History> getAllHistoryEvents();

    List<History> getAllHistoryEventsByUser(String userLogin);
}
