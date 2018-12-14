package com.company.freefx.h2.repository;

import com.company.freefx.h2.model.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Long> {

	List<Conversion> findByTransactionId(String transactionId);

	List<Conversion> findByTransactionDateBetween(Date startDate, Date endDate);


}