package com.company.freefx.service;

import com.company.freefx.h2.model.Conversion;
import com.company.freefx.h2.repository.ConversionRepository;
import com.company.freefx.type.ConversionHistoryRequest;
import com.company.freefx.type.ConversionHistoryResponse;
import com.company.freefx.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("conversionHistoryService")
public class ConversionHistoryService {

	@Autowired
	ConversionRepository repository;

	private static final Logger log = LoggerFactory.getLogger(ConversionHistoryService.class);

	public ResponseEntity<?> fetchConversions(ConversionHistoryRequest request) {
		try {
			final List<Conversion> conversions = new ArrayList<>();
			if (StringUtils.isNotBlank(request.getTransactionId())) {
				repository.findByTransactionId(request.getTransactionId()).forEach(conversion -> {
					log.info(conversion.toString());
					conversions.add(conversion);
				});
			}
			else if (request.getTransactionStartDate() != null) {
				final Date startDate = DateUtil.millisToDate(request.getTransactionStartDate());
				final Date endDate = DateUtil.millisToDate(
						request.getTransactionEndDate() == null ? (new Date().getTime()) : request.getTransactionEndDate());
				repository.findByTransactionDateBetween(startDate, endDate).forEach(conversion -> {
					log.info(conversion.toString());
					conversions.add(conversion);
				});
			}

			final ConversionHistoryResponse response = new ConversionHistoryResponse();
			final List<Conversion> collect = conversions.stream().sorted((o1, o2) -> o1.getTransactionDate().
					compareTo(o2.getTransactionDate())).collect(Collectors.toList());
			response.setConversions(collect);
			return new ResponseEntity<ConversionHistoryResponse>(response, HttpStatus.OK);

		}
		catch (Exception e) {
			log.error("convert err => " + e.getMessage());

			if (e instanceof HttpClientErrorException) {
				String responseStr = ((HttpClientErrorException) e).getResponseBodyAsString();
				return new ResponseEntity<String>(responseStr, HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<String>("Unexpected error", HttpStatus.BAD_REQUEST);

		}
		finally {
		}
	}
}
