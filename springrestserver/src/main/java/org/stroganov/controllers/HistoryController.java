package org.stroganov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stroganov.entities.History;
import org.stroganov.models.HistoryDTO;
import org.stroganov.repository.HistoryRepository;
import org.stroganov.servise.HistoryService;
import org.stroganov.util.TransitionObjectsService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HistoryController {

    public static final String SAVED_SUCCESSFULLY = "History event saved successfully";
    public static final String WAS_NOT_SAVED = "History event was not saved";
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    HistoryService historyService;

    @PostMapping("/history")
    // @JWTTokenNeeded
    public ResponseEntity<String> addHistoryEvent(@RequestBody HistoryDTO historyDTO) {
        try {
            historyService.historyEventSave(historyDTO);
            return ResponseEntity.ok(SAVED_SUCCESSFULLY);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(WAS_NOT_SAVED);
        }
    }

    @GetMapping("/history")
    public ResponseEntity<List<HistoryDTO>> getAllHistory() {
        List<History> allHistory = historyRepository.findAll();
        List<HistoryDTO> historyDTOList = TransitionObjectsService.getHistoryDTOList(allHistory);
        return ResponseEntity.ok(historyDTOList);
    }

    @GetMapping("/history/{userLogin}")
    public ResponseEntity<List<HistoryDTO>> getUserHistory(@PathVariable String userLogin) {
        List<History> allHistory = historyRepository.findAllByUser_Login(userLogin);
        List<HistoryDTO> historyDTOList = TransitionObjectsService.getHistoryDTOList(allHistory);
        return ResponseEntity.ok(historyDTOList);
    }
}
