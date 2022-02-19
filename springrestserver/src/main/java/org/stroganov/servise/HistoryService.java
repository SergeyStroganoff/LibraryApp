package org.stroganov.servise;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroganov.entities.History;
import org.stroganov.exceptions.HistorySavingException;
import org.stroganov.models.HistoryDTO;
import org.stroganov.repository.HistoryRepository;
import org.stroganov.util.TransitionObjectsService;

@Service
public class HistoryService {

    Logger logger = Logger.getLogger(HistoryService.class);

    HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void historyEventSave(HistoryDTO historyDTO) throws HistorySavingException {
        History history = TransitionObjectsService.getHistory(historyDTO);
        try {
            historyRepository.save(history);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new HistorySavingException(e.getMessage());
        }
    }
}
