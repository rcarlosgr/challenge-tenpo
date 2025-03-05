package com.tenpo.challenge_tenpo.dtos.api_call_history;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiCallHistoryResponse {
    private Long id;
    private String endpoint;
    private JsonNode parameters;
    private JsonNode response;
    private String error;
    private LocalDateTime timestamp;
}
