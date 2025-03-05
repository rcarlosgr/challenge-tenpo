package com.tenpo.challenge_tenpo.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.challenge_tenpo.dtos.calculation.CalculationRequest;
import com.tenpo.challenge_tenpo.dtos.calculation.CalculationResponse;
import com.tenpo.challenge_tenpo.exceptions.BadRequestException;
import com.tenpo.challenge_tenpo.services.ApiCallHistoryService;
import com.tenpo.challenge_tenpo.services.ExternalPercentageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculationServiceImplTest {
    @Mock
    private ExternalPercentageServiceImpl externalPercentageService;

    @Mock
    private ApiCallHistoryServiceImpl apiCallHistoryService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CalculationServiceImpl service;

    private CalculationRequest request;
    private JsonNode mockJsonNode;

    @BeforeEach
    void setUp() {
        request = new CalculationRequest(100.0, 50.0);

        try {
            ObjectMapper realMapper = new ObjectMapper();
            mockJsonNode = realMapper.valueToTree(request);
            when(objectMapper.valueToTree(any())).thenReturn(mockJsonNode);
        } catch (Exception e) {
            fail("Error inicializando JSON mockeado");
        }
    }

    @Test
    void testCalculateSumWithPercentage_ShouldReturnCorrectResult() {
        // Arrange
        double percentage = 10.0;
        when(externalPercentageService.getDynamicPercentage()).thenReturn(percentage);

        double expectedSum = 100.0 + 50.0; // 150.0
        double expectedResult = expectedSum + (expectedSum * (percentage / 100));

        // Act
        CalculationResponse response = service.calculateSumWithPercentage(request);

        // Assert
        assertNotNull(response);
        assertEquals(expectedResult, response.getResult());

        verify(externalPercentageService, times(1)).getDynamicPercentage();
        verify(apiCallHistoryService, times(1)).save(anyString(), any(JsonNode.class), any(JsonNode.class), isNull());
    }

    @Test
    void testCalculateSumWithPercentage_ShouldThrowBadRequestException_WhenExceptionOccurs() {
        // Arrange
        when(externalPercentageService.getDynamicPercentage()).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> service.calculateSumWithPercentage(request));

        assertEquals("Error en el cálculo", exception.getMessage());

        verify(apiCallHistoryService, times(1)).save(anyString(), any(JsonNode.class), isNull(), anyString());
    }
}