package com.tenpo.challenge_tenpo.services.impl;

import com.tenpo.challenge_tenpo.services.ExternalPercentageService;
import org.springframework.stereotype.Service;

@Service
public class ExternalPercentageServiceImpl implements ExternalPercentageService {
    @Override
    public double getDynamicPercentage() {
        return 10.0;
    }
}
