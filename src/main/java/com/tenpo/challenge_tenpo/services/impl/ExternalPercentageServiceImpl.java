package com.tenpo.challenge_tenpo.services.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.tenpo.challenge_tenpo.services.ExternalPercentageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalPercentageServiceImpl implements ExternalPercentageService {
    private final Cache<String, Double> percentageCache;
    int c = 0;
    @Override
    public double getDynamicPercentage() {
        try {
            double percentage = fetchPercentageFromExternalService();
            percentageCache.put("percentage", percentage);
            log.info("Porcentaje de api externa obtenida con éxito: {}", percentage);
            return percentage;
        } catch (Exception e) {
            log.error("Error al obtener porcentaje de la api externa: {}", e.getMessage());
            Double cachedPercentage = percentageCache.getIfPresent("percentage");
            log.info("Se toma porcentaje almacenado en caché: {}", cachedPercentage);
            if (cachedPercentage != null) {
                return cachedPercentage;
            }
            throw new RuntimeException("No se pudo obtener el porcentaje y no hay valores en caché.");
        }
    }

    @Override
    public double fetchPercentageFromExternalService() {
        return 10.0;
    }
}
