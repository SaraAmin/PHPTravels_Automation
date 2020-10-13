package com.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.framework.execution.Executor;
import com.framework.utils.ExcelParser;
import com.framework.utils.Screenshot;

public class TestBase {

	public static Executor executor;
	protected static Properties projectConfig = new Properties();
	protected static Properties envConfig = new Properties();
	protected static Driver driver;
	protected ExtentReports report;
	protected static ExtentTest logger;

	protected static Map<String, String[]> objectRepo;
	protected static Map<String, String> testData;
	static String screenshotsPath;
	static {
		FileInputStream config = null;
		try {
			config = new FileInputStream(".\\src\\main\\java\\com\\framework\\config\\project.properties");
			projectConfig.load(config);
			config.close();
			config = new FileInputStream(".\\src\\main\\java\\com\\framework\\config\\env.properties");
			envConfig.load(config);
			config.close();

			objectRepo = ExcelParser.getObjects(projectConfig.getProperty("objectRepositoryPath"),
					projectConfig.getProperty("objectRepo_Id"));
			testData = ExcelParser.getTestdata(projectConfig.getProperty("testDataPath"),
					projectConfig.getProperty("testData_Id"));
			driver = new Driver();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@BeforeSuite
	public void setUpSuite() {
		String reportPath = projectConfig.getProperty("reportPath") + "\\suiteReport.html";
		screenshotsPath = projectConfig.getProperty("reportPath") + "\\Screenshots\\";
		new File(screenshotsPath).mkdirs();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(new File(reportPath));
		report = new ExtentReports();
		report.attachReporter(htmlReport);
	}

	@BeforeClass
	public void setExecutor() {
		executor = new Executor();
	}

	@AfterClass
	public void tearDown() {
		if (driver.browser != null)
			driver.browser.quit();
	}

	@AfterMethod
	public void afterTestStep(ITestResult result, Method m) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.fail("step failed.", MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.takeScreenshot(driver, screenshotsPath, m.getName()))
					.build());
		}
		report.flush();
		if (driver.browser != null)
			driver.browser.quit();
	}

}
