package com.company.freefx.util;

import com.company.freefx.type.RateProviderResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class RateProvider {

	private static final Logger log = LoggerFactory.getLogger(RateProvider.class);

	static final String uri = "https://api.exchangeratesapi.io/latest?base={base}&symbols={source},{target}";

	public static RateProviderResponse fetchCurrencyRate(String source, String target) throws Exception {
		RateProviderResponse result = null;
		try {

			//TODO this issue must be fixed with double conversion for each currencies like eur=>usd=>try
			if("EUR".equals(target.toUpperCase()))
				throw new Exception("Service provider does not support eur as target currency !");

			Map<String, String> params = new HashMap<String, String>();
			params.put("base", target.toUpperCase());
			params.put("source", source.toUpperCase());
			params.put("target", target.toUpperCase());

			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.getForObject(uri, RateProviderResponse.class, params);

			log.info("fetchCurrencyRate info => " + result.toString());

		}
		catch (Exception e) {
			log.error("fetchCurrencyRate error => " + e.getMessage());
			throw e;
		}
		return result;
	}

	public static BigDecimal fetchSpecifiedRate(RateProviderResponse rateProvider, String currency) {
		BigDecimal rate = null;
		try {
			String value = BeanUtils.getProperty(rateProvider.getRates(), currency.toUpperCase());
			rate = new BigDecimal(value).setScale(3, RoundingMode.HALF_DOWN);
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return rate;
	}

}
