package com.framework.execution;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.framework.TestBase;

public class Actions extends TestBase {

	// object, action, data
	static ITestResult testResult;

	@Test
	public static void open(String... parameters) {
		String browser = "";
		try {
			Map<String, String> driversPath = new HashMap<>();
			driversPath.put("chrome", projectConfig.getProperty("chromeDriver"));
			driversPath.put("firefox", projectConfig.getProperty("geckoDriver"));
			driver.initializeBrowser(envConfig.getProperty("browser"), driversPath, envConfig.getProperty("Wait"));
			logger.pass("Browser '" + browser + "' opened successfully.");
		} catch (Exception e) {
			logger.fail("Browser '" + browser + "' did not open.");
			Assert.fail();
		}
	}

	@Test
	public static void navigateto(String... parameters) {
		String testdata = "";
		try {
			testdata = testData.get(parameters[2]);
			driver.browser.navigate().to(testdata);
			logger.pass("Successfully navigated to '" + testdata + "'");
		} catch (Exception e) {
			logger.fail("Failed to navigate to '" + testdata + "'");
			Assert.fail();
		}
	}

	@Test
	public static void asserttitle(String... parameters) {
		String testdata = testData.get(parameters[2]);
		String title = driver.browser.getTitle();
		Assert.assertEquals(title, testdata);
		logger.pass("Assert Title: " + testdata + "(TD) = " + title + "(pageTitle) passed.");
	}

	@Test
	public static void asserttextequal(String... parameters) {
		String testdata = testData.get(parameters[2]);
		WebElement object = null;
		String text = "";
		try {
			object = driver.wait.until(ExpectedConditions.visibilityOfElementLocated(geObject(parameters[0])));
			text = object.getText();
		} catch (Exception e) {
			Assert.fail();
		}

		Assert.assertEquals(text, testdata);
		logger.pass("Assert Text: " + testdata + "(TD) = " + text + "(Object.textAttribute) passed.");
	}

	public static void verifynotequal(String... parameters) {
		if (testData.get(parameters[0]).equals(testData.get(parameters[2]))) {
			logger.warning("Verify Text Match failed. Value of '" + parameters[0] + "'(" + testData.get(parameters[0])
					+ ") m the value of '" + parameters[2] + "'(" + testData.get(parameters[2]) + ").");
		} else {
			logger.warning("Verify Text Match passed. Value of '" + parameters[0] + "'(" + testData.get(parameters[0])
					+ ") does not match the value of '" + parameters[2] + "'(" + testData.get(parameters[2]) + ").");
		}
	}

	@Test
	public static void click(String... parameters) {
		try {
			WebElement object = driver.wait
					.until(ExpectedConditions.visibilityOfElementLocated(geObject(parameters[0])));
			object.click();
			logger.pass("Action 'Click' executed on object '" + parameters[0] + "'.");
		} catch (Exception e) {
			logger.fail("Failed to execute action 'Click' on object '" + parameters[0] + "'.");
			Assert.fail();
		}
	}

	@Test
	public static void close(String... parameters) {
		driver.browser.close();
	}

	@Test
	public static void type(String... parameters) {
		try {
			WebElement object = driver.wait
					.until(ExpectedConditions.visibilityOfElementLocated(geObject(parameters[0])));
			object.sendKeys(testData.get(parameters[2]));
			logger.pass("Action 'Type' executed on object '" + parameters[0] + "'.");
		} catch (Exception e) {
			logger.fail("Failed to execute action 'Type' on object '" + parameters[0] + "'.");
			Assert.fail();
		}
	}

	@Test
	public static void verifytextmatch(String... parameters) {
		String testdata = "";
		try {
			testdata = testData.get(parameters[2]);
			if (validateOverRegex(testData.get(parameters[0]), testdata)) {
				logger.pass("Verify Text Match Passed. Value '" + testdata + "'(TD) matches the provided regex '"
						+ parameters[0] + "'.");
			} else {
				logger.warning("Verify Text Match failed. Value '" + testdata
						+ "'(TD) does not match the provided regex '" + parameters[0] + "'.");
			}
		} catch (Exception e) {
			logger.warning("Verify Text Match failed. Value '" + testdata + "'(TD) does not match the provided regex '"
					+ parameters[0] + "'.");
		}
	}

	public static boolean validateOverRegex(String input, String regex) {
		try {
			Pattern regexPattern;
			regexPattern = Pattern.compile(regex);
			return regexPattern.matcher(input).matches();
		} catch (Exception e) {
			return false;
		}
	}

	public static By geObject(String objectName) throws Exception {
		String[] object = objectRepo.get(objectName);
		String locatorName = object[0];
		String locatorValue = object[1];

		if (locatorName.equalsIgnoreCase("id")) {
			return By.id(locatorValue);
		} else if (locatorName.equalsIgnoreCase("name")) {
			return By.name(locatorValue);
		}
		if (locatorName.equalsIgnoreCase("tagname") || locatorName.equalsIgnoreCase("tag")) {
			return By.tagName(locatorValue);
		}
		if (locatorName.equalsIgnoreCase("className") || locatorName.equalsIgnoreCase("class")) {
			return By.className(locatorValue);
		}
		if (locatorName.equalsIgnoreCase("linktext") || locatorName.equalsIgnoreCase("link")) {
			return By.linkText(locatorValue);
		}
		if (locatorName.equalsIgnoreCase("xpath")) {
			return By.xpath(locatorValue);
		}
		if (locatorName.equalsIgnoreCase("cssselector") || locatorName.equalsIgnoreCase("css")) {
			return By.cssSelector(locatorValue);
		}
		if (locatorName.equalsIgnoreCase("partiallinktext")) {
			return By.partialLinkText(locatorValue);
		} else {
			throw new Exception("Locator type '" + locatorName + "' not defined.");
		}
	}

}
