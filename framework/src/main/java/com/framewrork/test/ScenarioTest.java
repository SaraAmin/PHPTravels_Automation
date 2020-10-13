package com.framewrork.test;

import org.testng.annotations.Test;

import com.framework.TestBase;

public class ScenarioTest extends TestBase {

	@Test
	public void registrationTest() throws Exception {

		logger = report.createTest("Registration Test");
		executor.startExecution(projectConfig.getProperty("testScenarioPath"), "Register");
	}

	@Test
	public void loginTest() throws Exception {

		logger = report.createTest("Login Test");
		executor.startExecution(projectConfig.getProperty("testScenarioPath"), "Login");
	}
}
