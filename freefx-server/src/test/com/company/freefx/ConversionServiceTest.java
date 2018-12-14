package com.company.freefx;

import com.company.freefx.service.ConversionService;
import com.company.freefx.type.ConversionRequest;
import com.company.freefx.type.ConversionResponse;
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
public class ConversionServiceTest {

	@Autowired
	ConversionService conversionService;

	@Before
	public void before() {

	}

	@Test
	public void add_conversion_expected_behaviour() {

		final String sourceCurr = "gbp";
		final String targetCurr = "jpy";

		ConversionRequest request = new ConversionRequest();
		request.setSourceCurreny(sourceCurr);
		request.setTargetCurreny(targetCurr);
		request.setSourceAmount(new BigDecimal(100));
		final ResponseEntity<?> responseEntity = conversionService.convert(request);

		final String transactionId = ((ConversionResponse) responseEntity.getBody()).getTransactionId();
		Assert.assertNotNull(transactionId);
	}

}