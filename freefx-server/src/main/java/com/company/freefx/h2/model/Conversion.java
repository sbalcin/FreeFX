package com.company.freefx.h2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Conversion {

	@Id
	@GeneratedValue
	@Column(name = "Id", nullable = false)
	private Long id;

	private String transactionId;

	private BigDecimal sellAmount;

	private BigDecimal buyAmount;

	private String sellCurrency;

	private String buyCurrency;

	private Date transactionDate;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getSellAmount() {
		return sellAmount;
	}

	public void setSellAmount(BigDecimal sellAmount) {
		this.sellAmount = sellAmount;
	}

	public BigDecimal getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(BigDecimal buyAmount) {
		this.buyAmount = buyAmount;
	}

	public String getSellCurrency() {
		return sellCurrency;
	}

	public void setSellCurrency(String sellCurrency) {
		this.sellCurrency = sellCurrency;
	}

	public String getBuyCurrency() {
		return buyCurrency;
	}

	public void setBuyCurrency(String buyCurrency) {
		this.buyCurrency = buyCurrency;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "Conversion{" + "transactionId='" + transactionId + '\'' + ", sellAmount=" + sellAmount + ", buyAmount=" + buyAmount
				+ ", sellCurrency='" + sellCurrency + '\'' + ", buyCurrency='" + buyCurrency + '\'' + ", transactionDate=" + transactionDate + '}';
	}
}