package com.tenpo.challenge_tenpo.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.tenpo.challenge_tenpo.dtos.api_call_history.ApiCallHistoryResponse;
import com.tenpo.challenge_tenpo.entities.ApiCallHistory;
import com.tenpo.challenge_tenpo.repositories.ApiCallHistoryRepository;
import com.tenpo.challenge_tenpo.services.ApiCallHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiCallHistoryServiceImpl implements ApiCallHistoryService {
    private final ApiCallHistoryRepository repository;

    @Override
    public List<ApiCallHistoryResponse> getAll() {
        return repository.findAll()
                .stream().map(this::toResponse)
                .toList();
    }

    @Override
    @Async
    public void save(String endpoint, JsonNode parameters, JsonNode response, String error) {
        ApiCallHistory history = ApiCallHistory.builder()
                .endpoint(endpoint)
                .parameters(parameters)
                .response(response)
                .error(error)
                .timestamp(LocalDateTime.now())
                .build();
        repository.save(history);
    }

    private ApiCallHistoryResponse toResponse(ApiCallHistory entity) {
        return ApiCallHistoryResponse.builder()
                .id(entity.getId())
                .endpoint(entity.getEndpoint())
                .parameters(entity.getParameters())
                .response(entity.getResponse())
                .error(entity.getError())
                .timestamp(entity.getTimestamp())
                .build();
    }
}
