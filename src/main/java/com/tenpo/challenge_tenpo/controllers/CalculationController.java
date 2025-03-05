package com.tenpo.challenge_tenpo.controllers;

import com.tenpo.challenge_tenpo.dtos.calculation.CalculationRequest;
import com.tenpo.challenge_tenpo.dtos.calculation.CalculationResponse;
import com.tenpo.challenge_tenpo.services.CalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculation")
@RequiredArgsConstructor
public class CalculationController {
    private final CalculationService calculationService;

    @PostMapping("/sum")
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        CalculationResponse resp = calculationService.calculateSumWithPercentage(request);
        return ResponseEntity.ok(resp);
    }
}
