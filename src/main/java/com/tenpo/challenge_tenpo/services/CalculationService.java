package com.tenpo.challenge_tenpo.services;

import com.tenpo.challenge_tenpo.dtos.calculation.CalculationRequest;
import com.tenpo.challenge_tenpo.dtos.calculation.CalculationResponse;

public interface CalculationService {
    CalculationResponse calculateSumWithPercentage(CalculationRequest request);
}
