package org.stroganov.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stroganov.entities.History;
import org.stroganov.exceptions.HistorySavingException;
import org.stroganov.models.HistoryDTO;
import org.stroganov.servise.impl.HistoryServiceImpl;
import org.stroganov.util.TransitionObjectsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HistoryController {

    public static final String SAVED_SUCCESSFULLY = "History event saved successfully";
    public static final String WAS_NOT_SAVED = "History event was not saved";

    @Autowired
    HistoryServiceImpl historyService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/history")
    // @JWTTokenNeeded
    public ResponseEntity<String> addHistoryEvent(@RequestBody HistoryDTO historyDTO) throws HistorySavingException {
        historyService.historyEventSave(historyDTO);
        return ResponseEntity.ok(SAVED_SUCCESSFULLY);
    }

    @GetMapping("/history")
    public ResponseEntity<List<HistoryDTO>> getAllHistory() {
        List<History> allHistory = historyService.getAllHistoryEvents();
        List<HistoryDTO> historyDTOList = TransitionObjectsService.getHistoryDTOList(allHistory);
        return ResponseEntity.ok(historyDTOList);
    }

    @GetMapping("/history/{userLogin}")
    public ResponseEntity<List<HistoryDTO>> getUserHistory(@PathVariable String userLogin) {
        List<History> allHistory = historyService.getAllHistoryEventsByUser(userLogin);
        List<HistoryDTO> historyDTOList = TransitionObjectsService.getHistoryDTOList(allHistory);
        return ResponseEntity.ok(historyDTOList);
    }
}
