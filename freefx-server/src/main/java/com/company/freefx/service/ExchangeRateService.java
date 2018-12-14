package com.company.freefx.service;

import com.company.freefx.type.ExchangeRateRequest;
import com.company.freefx.type.ExchangeRateResponse;
import com.company.freefx.type.RateProviderResponse;
import com.company.freefx.util.RateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@Service("exchangeRateService")
public class ExchangeRateService {

    private static final Logger log = LoggerFactory.getLogger(ExchangeRateService.class);

    public ResponseEntity<?> fetchExchangeRate(ExchangeRateRequest request) {
        try {
			final RateProviderResponse rateProviderResponse = RateProvider.fetchCurrencyRate(request.getSourceCurreny().toUpperCase(), request.getTargetCurreny());

			final BigDecimal rate = RateProvider.fetchSpecifiedRate(rateProviderResponse, request.getSourceCurreny());

			final ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
			exchangeRateResponse.setRate(rate);

			return new ResponseEntity<ExchangeRateResponse>(exchangeRateResponse, HttpStatus.OK);

        } catch (Exception e) {
			log.error("fetchExchangeRate err => " + e.getMessage());

            if (e instanceof HttpClientErrorException) {
                final String responseStr = ((HttpClientErrorException) e).getResponseBodyAsString();
                return new ResponseEntity<String>(responseStr, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<String>("Unexpected error: "  + e.getMessage(), HttpStatus.BAD_REQUEST);

        } finally {

        }
    }




}
