package com.framewrork.test;

import org.testng.annotations.Test;

import com.framework.TestBase;

public class ScenarioTest extends TestBase {

	@Test(priority = 1)
	public void registrationTest() throws Exception {

		logger = report.createTest("Registration Test");
		executor.startExecution(projectConfig.getProperty("testScenarioPath"), "Register");
	}

	@Test(priority = 2)
	public void loginTest() throws Exception {

		logger = report.createTest("Login Test");
		executor.startExecution(projectConfig.getProperty("testScenarioPath"), "Login");
	}
}
