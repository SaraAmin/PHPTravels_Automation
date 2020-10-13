package com.framework;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

	public WebDriver browser;
	public WebDriverWait wait;

	public void initializeBrowser(String browserName, Map<String, String> driversPath, String waitValue) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", driversPath.get(browserName));
			browser = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", driversPath.get(browserName));
			browser = new FirefoxDriver();
		}
		wait = new WebDriverWait(browser, Integer.parseInt(waitValue));
	}
}
