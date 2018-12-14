package com.company.freefx.controller;

import com.company.freefx.service.ExchangeRateService;
import com.company.freefx.type.ExchangeRateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchangeRate")
@AllArgsConstructor
public class ExchangeRateController {


    private final ExchangeRateService exchangeRateService;

    @PostMapping("/fetchExchangeRate/")
    public ResponseEntity<?> fetchExchangeRate(@RequestBody ExchangeRateRequest request) {

        return exchangeRateService.fetchExchangeRate(request);
    }

}