package com.company.freefx;

import com.company.freefx.type.RateProviderResponse;
import com.company.freefx.util.RateProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class RateProviderTests {

	@Before
	public void before(){

	}

	@Test
	public void fetch_currency_rate_expected_behaviour_Test() throws Exception {

		final String sourceCurr = "try";
		final String targetCurr = "usd";

		RateProviderResponse response = RateProvider.fetchCurrencyRate(sourceCurr, targetCurr);

		Assert.assertNotNull(response);
	}

	@Test
	public void check_specified_rate_is_sensible() throws Exception {

		final String sourceCurr = "try";
		final String targetCurr = "usd";

		RateProviderResponse response = RateProvider.fetchCurrencyRate(sourceCurr, targetCurr);
		final BigDecimal rate = RateProvider.fetchSpecifiedRate(response, "usd");

		Assert.assertTrue(rate.compareTo(new BigDecimal(0)) < 0);
	}
}