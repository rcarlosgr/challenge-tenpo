package com.tenpo.challenge_tenpo.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.tenpo.challenge_tenpo.dtos.api_call_history.ApiCallHistoryResponse;

import java.util.List;

public interface ApiCallHistoryService {
    List<ApiCallHistoryResponse> getAll();
    void save(String endpoint, JsonNode parameters, JsonNode response, String error);
}
