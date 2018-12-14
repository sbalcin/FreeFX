
package com.company.freefx.runner;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;

public class AbstractRunner implements RunnerImplementation {

	private static Logger logger = Logger.getLogger(AbstractRunner.class);

	public static String currentBrowser = "Chrome";
	public static WebDriver driver;


	@Override
	public void run() {

	}

	public String getUri(){
		//return "http://localhost:3000/";
		return "http://37.59.110.96/";
	}

	public void initDriver(){
		driver = getDriver();
	}

	public WebDriver getDriver() {

		if ("Chrome".equals(ExchangeRateRunner.currentBrowser))
			return initChrome();

		if ("FireFox".equals(ExchangeRateRunner.currentBrowser))
			return initFirefox();

		if ("IExplorer".equals(ExchangeRateRunner.currentBrowser))
			return initIExplorer();

		if ("Edge".equals(ExchangeRateRunner.currentBrowser))
			return initEdge();

		return initChrome();
	}

	private WebDriver initIExplorer() {
		logger.info("InternetExplorerDriver has started");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		String iEDriver = new File("drivers").getAbsolutePath() + "/IEDriverServer.exe";
		System.setProperty("webdriver.ie.driver", iEDriver);
		try {
			return new InternetExplorerDriver(capabilities);
		} catch (Exception e) {
			System.setProperty("webdriver.ie.driver", getClass().getClassLoader().getResource("IEDriverServer.exe").getPath());
			return new InternetExplorerDriver(capabilities);
		}
	}


	private WebDriver initFirefox() {
		logger.info("FireFox has started");
		String geckodriver = new File("drivers").getAbsolutePath() + "/firefox.exe";
		System.setProperty("webdriver.gecko.driver", geckodriver);
		try {
			return new FirefoxDriver();
		} catch (Exception e) {
			System.setProperty("webdriver.gecko.driver", getClass().getClassLoader().getResource("firefox.exe").getPath());
			return new FirefoxDriver();
		}
	}

	private WebDriver initEdge() {
		try {
			logger.info("Edge has started");
			String edgedriver = new File("drivers").getAbsolutePath() + "/edgedriver.exe";
			System.setProperty("webdriver.edge.driver", edgedriver);
			return new EdgeDriver();
		} catch (Exception e) {
			System.setProperty("webdriver.edge.driver", getClass().getClassLoader().getResource("edgedriver.exe").getPath());
			return new EdgeDriver();
		}
	}

	private WebDriver initChrome() {
		logger.info("ChromeDriver has started");
		try {
			String chromePath = new File("drivers").getAbsolutePath() + "/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromePath);
			DesiredCapabilities cap = getDesiredCapabilities();
			return new ChromeDriver(cap);
		} catch (Exception e) {
			String chromePath = new File("drivers").getAbsolutePath() + "/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", getClass().getClassLoader().getResource("chromedriver.exe").getPath());
			DesiredCapabilities cap = getDesiredCapabilities();
			return new ChromeDriver(cap);
		}
	}

	private DesiredCapabilities getDesiredCapabilities() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);

		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		return cap;
	}
}
