package com.company.freefx;

import com.company.freefx.service.ConversionHistoryService;
import com.company.freefx.service.ConversionService;
import com.company.freefx.type.ConversionHistoryRequest;
import com.company.freefx.type.ConversionHistoryResponse;
import com.company.freefx.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConversionHistoryTest extends ConversionServiceTest{

	@Autowired
	ConversionHistoryService conversionHistoryService;

	@Autowired
	ConversionService conversionService;


	@Test
	public void h2_db_is_working() {

		ConversionHistoryRequest request = new ConversionHistoryRequest();
		request.setTransactionStartDate(DateUtil.addDayToDate(new Date(), -5).getTime());

		final ResponseEntity<?> responseEntity = conversionHistoryService.fetchConversions(request);

		Assert.assertNotNull(((ConversionHistoryResponse) responseEntity.getBody()).getConversions());
	}

	@Test
	public void conversion_history_expected_behaviour() {

		add_conversion_expected_behaviour();

		ConversionHistoryRequest request = new ConversionHistoryRequest();
		request.setTransactionStartDate(DateUtil.addDayToDate(new Date(), -1).getTime());

		final ResponseEntity<?> responseEntity = conversionHistoryService.fetchConversions(request);

		Assert.assertTrue(((ConversionHistoryResponse) responseEntity.getBody()).getConversions().size() > 0);
	}

}