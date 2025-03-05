package com.tenpo.challenge_tenpo.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.challenge_tenpo.dtos.calculation.CalculationRequest;
import com.tenpo.challenge_tenpo.dtos.calculation.CalculationResponse;
import com.tenpo.challenge_tenpo.services.ApiCallHistoryService;
import com.tenpo.challenge_tenpo.services.CalculationService;
import com.tenpo.challenge_tenpo.services.ExternalPercentageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {
    private final ExternalPercentageService externalPercentageService;
    private final ApiCallHistoryService apiCallHistoryService;
    private final ObjectMapper objectMapper;

    @Override
    public CalculationResponse calculateSumWithPercentage(CalculationRequest request) {
        String endpoint = "/api/calculation/sum";
        JsonNode parameters = objectMapper.valueToTree(request) ;
        JsonNode response;
        String error = null;

        try {
            double result = calculate(request.getNum1(), request.getNum2());
            CalculationResponse resp = CalculationResponse.builder().result(result).build();
            response = objectMapper.valueToTree(resp) ;
            apiCallHistoryService.save(endpoint, parameters, response, error);
            return resp;
        } catch (Exception e) {
            error = e.getMessage();
            response = null;
            apiCallHistoryService.save(endpoint, parameters, response, error);
            throw new RuntimeException("Error en el cálculo");
        }


    }

    private double calculate(double num1, double num2) {
        double sum = num1 + num2;
        double percentage = externalPercentageService.getDynamicPercentage();
        return sum + (sum * (percentage / 100));
    }
}
