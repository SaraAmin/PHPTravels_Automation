package com.framework.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.framework.Driver;

public class Screenshot {

	public static String takeScreenshot(Driver driver, String dir, String stepName) {
		File screenshot = ((TakesScreenshot) driver.browser).getScreenshotAs(OutputType.FILE);
		String screenshotPath = dir + "_" + stepName + "_" + getCurrentDateTime() + ".png";
		try {
			FileHandler.copy(screenshot, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotPath;
	}

	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("");
		return customFormat.toString();
	}

}
