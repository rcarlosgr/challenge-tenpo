package com.tenpo.challenge_tenpo.controllers;

import com.tenpo.challenge_tenpo.dtos.api_call_history.ApiCallHistoryResponse;
import com.tenpo.challenge_tenpo.services.ApiCallHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/api-call-history")
@RequiredArgsConstructor
public class ApiCallHistoryController {
    private final ApiCallHistoryService service;

    @GetMapping
    public ResponseEntity<List<ApiCallHistoryResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
