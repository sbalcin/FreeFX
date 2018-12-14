package com.company.freefx;

import com.company.freefx.service.ExchangeRateService;
import com.company.freefx.type.ExchangeRateRequest;
import com.company.freefx.type.ExchangeRateResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeRateTests {

	@Autowired
	ExchangeRateService exchangeRateService;

	@Before
	public void before() {

	}

	@Test
	public void check_sample_exchange_rate() {

		final String sourceCurr = "gbp";
		final String targetCurr = "jpy";

		ExchangeRateRequest request = new ExchangeRateRequest();
		request.setSourceCurreny(sourceCurr);
		request.setTargetCurreny(targetCurr);
		final ResponseEntity<?> responseEntity = exchangeRateService.fetchExchangeRate(request);

		Assert.assertTrue(((ExchangeRateResponse) responseEntity.getBody()).getRate().compareTo(new BigDecimal(0)) > 0);
	}

}