package com.company.freefx.service;

import com.company.freefx.h2.model.Conversion;
import com.company.freefx.h2.repository.ConversionRepository;
import com.company.freefx.type.ConversionRequest;
import com.company.freefx.type.ConversionResponse;
import com.company.freefx.type.RateProviderResponse;
import com.company.freefx.util.RateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.UUID;

@Service("conversionFXService")
public class ConversionService {

	@Autowired
	ConversionRepository repository;

	private static final Logger log = LoggerFactory.getLogger(ConversionService.class);

	public ResponseEntity<?> convert(ConversionRequest request) {
		try {
			final String sourceCurrency = request.getSourceCurreny();

			final RateProviderResponse rateProviderResponse = RateProvider.fetchCurrencyRate(sourceCurrency, request.getTargetCurreny());

			final BigDecimal rate = RateProvider.fetchSpecifiedRate(rateProviderResponse, sourceCurrency);

			ConversionResponse conversionResponse = new ConversionResponse();
			final BigDecimal targetAmount = request.getSourceAmount().divide(rate, 2, RoundingMode.HALF_DOWN);

			final Conversion conversion = addToConversionHistory(request, targetAmount);

			conversionResponse.setAmount(targetAmount);
			conversionResponse.setTransactionId(conversion.getTransactionId());

			return new ResponseEntity<ConversionResponse>(conversionResponse, HttpStatus.OK);

		}
		catch (Exception e) {
			log.error("convert err => " + e.getMessage());

			if (e instanceof HttpClientErrorException) {
				final String responseStr = ((HttpClientErrorException) e).getResponseBodyAsString();
				return new ResponseEntity<String>(responseStr, HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<String>("Unexpected error" + e.getMessage(), HttpStatus.BAD_REQUEST);

		}
		finally {
		}
	}

	private Conversion addToConversionHistory(ConversionRequest request, BigDecimal targetAmount) {
		try {
			String uuid = UUID.randomUUID().toString();

			Conversion conversion = Conversion
					.builder()
					.transactionId(uuid)
					.sellAmount(request.getSourceAmount())
					.buyAmount(targetAmount)
					.sellCurrency(request.getSourceCurreny())
					.buyCurrency(request.getTargetCurreny())
					.transactionDate(new Date())
					.build();


			repository.save(conversion);
			return conversion;
		}catch (Exception e){
			log.error("addToConversionHistory err => " + e.getMessage());
			throw e;
		}
	}
}
