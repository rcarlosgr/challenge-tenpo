package com.tenpo.challenge_tenpo.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.challenge_tenpo.dtos.api_call_history.ApiCallHistoryResponse;
import com.tenpo.challenge_tenpo.entities.ApiCallHistory;
import com.tenpo.challenge_tenpo.repositories.ApiCallHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ApiCallHistoryServiceImplTest {
    @Mock
    private ApiCallHistoryRepository repository;

    @InjectMocks
    private ApiCallHistoryServiceImpl service;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode sampleJson;
    private ApiCallHistory apiCallHistory;

    @BeforeEach
    void setUp() {
        try {
            sampleJson = objectMapper.readTree("{\"key\":\"value\"}");
        } catch (Exception e) {
            fail("Error al parsear JSON");
        }

        apiCallHistory = ApiCallHistory.builder()
                .id(1L)
                .endpoint("/test")
                .parameters(sampleJson)
                .response(sampleJson)
                .error(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Test
    void testGetAll_ShouldReturnListOfApiCallHistoryResponses() {
        // Arrange
        when(repository.findAll()).thenReturn(List.of(apiCallHistory));

        // Act
        List<ApiCallHistoryResponse> result = service.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(apiCallHistory.getEndpoint(), result.get(0).getEndpoint());
        assertEquals(apiCallHistory.getParameters(), result.get(0).getParameters());
        assertEquals(apiCallHistory.getResponse(), result.get(0).getResponse());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testSave_ShouldSaveApiCallHistory() {
        // Arrange
        String endpoint = "/test";
        String error = "Error message";

        ApiCallHistory historyToSave = ApiCallHistory.builder()
                .endpoint(endpoint)
                .parameters(sampleJson)
                .response(sampleJson)
                .error(error)
                .timestamp(LocalDateTime.now())
                .build();

        when(repository.save(any(ApiCallHistory.class))).thenReturn(historyToSave);

        // Act
        service.save(endpoint, sampleJson, sampleJson, error);

        // Assert
        verify(repository, timeout(1000).times(1)).save(any(ApiCallHistory.class));
    }
}