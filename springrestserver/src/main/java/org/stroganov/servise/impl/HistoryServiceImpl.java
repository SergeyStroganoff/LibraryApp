package org.stroganov.servise.impl;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroganov.entities.History;
import org.stroganov.exceptions.HistorySavingException;
import org.stroganov.models.HistoryDTO;
import org.stroganov.repository.HistoryRepository;
import org.stroganov.servise.HistoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    Logger logger = Logger.getLogger(HistoryServiceImpl.class);
    HistoryRepository historyRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public void historyEventSave(HistoryDTO historyDTO) throws HistorySavingException {
        History history = modelMapper.map(historyDTO, History.class);  // TransitionObjectsService.getHistory(historyDTO);
        try {
            historyRepository.save(history);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new HistorySavingException(e.getMessage());
        }
    }

    @Override
    public List<History> getAllHistoryEvents() {
        return historyRepository.findAll();
    }

    @Override
    public List<History> getAllHistoryEventsByUser(String userLogin) {
        return historyRepository.findAllByUser_Login(userLogin);
    }
}
