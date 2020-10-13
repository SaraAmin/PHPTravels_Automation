package com.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

	public WebDriver browser;
	public WebDriverWait wait;

	public void initializeBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\VDFData\\RD\\Repos\\SKDF\\Release\\drivers\\chromedriver.exe");
			browser = new ChromeDriver();
		}
		wait = new WebDriverWait(browser, 30);
	}
}
