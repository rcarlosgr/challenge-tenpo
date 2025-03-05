package com.tenpo.challenge_tenpo.services.impl;

import com.tenpo.challenge_tenpo.services.CalculationService;
import com.tenpo.challenge_tenpo.services.ExternalPercentageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {
    private final ExternalPercentageService externalPercentageService;

    @Override
    public double calculateSumWithPercentage(double num1, double num2) {
        double sum = num1 + num2;
        double percentage = externalPercentageService.getDynamicPercentage();
        return sum + (sum * (percentage / 100));
    }
}
