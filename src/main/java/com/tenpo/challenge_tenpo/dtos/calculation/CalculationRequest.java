package com.tenpo.challenge_tenpo.dtos.calculation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculationRequest {
    private double num1;
    private double num2;
}
