package org.stroganov.servise.impl;

import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository, ModelMapper modelMapper) {
        this.historyRepository = historyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void historyEventSave(HistoryDTO historyDTO) throws HistorySavingException {
        History history = modelMapper.map(historyDTO, History.class);  // TransitionObjectsService.getHistory(historyDTO);
        try {
            historyRepository.save(history);
        } catch (Exception e) {
            log.error(e.getMessage());
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
