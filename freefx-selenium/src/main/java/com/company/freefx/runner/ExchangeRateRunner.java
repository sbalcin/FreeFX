
package com.company.freefx.runner;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ExchangeRateRunner extends AbstractRunner {

	private static Logger logger = Logger.getLogger(ExchangeRateRunner.class);

	public static void main(String[] args) throws InterruptedException {

		ExchangeRateRunner runner = new ExchangeRateRunner();
		runner.run();
	}

	@Override
	public void run() {
		try {
			super.run();

			ExchangeRateRunner exchangeRateRunner = new ExchangeRateRunner();
			initDriver();

			driver.get(getUri());

			Thread.sleep(2000);


			driver.findElement(By.id("sourceCurrency")).sendKeys("TRY");
			Thread.sleep(500);

			driver.findElement(By.id("sellAmount")).sendKeys("1000");
			Thread.sleep(500);

			driver.findElement(By.id("targetCurreny")).sendKeys("GBP");
			Thread.sleep(500);

			driver.findElement(By.id("getQuotebutton")).click();

			Thread.sleep(3000);

			driver.findElement(By.id("conversionConfirmBtn")).click();

			Thread.sleep(2000);

			logger.info("New Conversion added successfully");
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			driver.close();
		}
	}
}
