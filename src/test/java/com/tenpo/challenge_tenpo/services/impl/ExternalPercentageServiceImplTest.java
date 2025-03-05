package com.tenpo.challenge_tenpo.services.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.tenpo.challenge_tenpo.exceptions.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExternalPercentageServiceImplTest {
    @Mock
    private Cache<String, Double> percentageCache;

    @InjectMocks
    private ExternalPercentageServiceImpl service;

    @Test
    void testGetDynamicPercentage_ShouldReturnFetchedPercentage() {
        // Arrange
        double expectedPercentage = 10.0;

        // Act
        double result = service.getDynamicPercentage();

        // Assert
        assertEquals(expectedPercentage, result);
        verify(percentageCache, times(1)).put("percentage", expectedPercentage);
    }

    @Test
    void testGetDynamicPercentage_ShouldReturnCachedPercentage_WhenFetchFails() {
        // Arrange
        double cachedPercentage = 15.0;
        when(percentageCache.getIfPresent("percentage")).thenReturn(cachedPercentage);

        ExternalPercentageServiceImpl spyService = spy(service);
        doThrow(new RuntimeException("Error externo")).when(spyService).fetchPercentageFromExternalService();

        // Act
        double result = spyService.getDynamicPercentage();

        // Assert
        assertEquals(cachedPercentage, result);
        verify(percentageCache, times(1)).getIfPresent("percentage");
    }

    @Test
    void testGetDynamicPercentage_ShouldThrowException_WhenFetchFailsAndCacheIsEmpty() {
        // Arrange
        when(percentageCache.getIfPresent("percentage")).thenReturn(null);

        ExternalPercentageServiceImpl spyService = spy(service);
        doThrow(new RuntimeException("Error externo")).when(spyService).fetchPercentageFromExternalService();

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, spyService::getDynamicPercentage);
        assertEquals("No se pudo obtener el porcentaje y no hay valores en caché.", exception.getMessage());

        verify(percentageCache, times(1)).getIfPresent("percentage");
    }
}