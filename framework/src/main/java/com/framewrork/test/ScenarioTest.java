package com.framewrork.test;

import org.testng.annotations.Test;

import com.framework.TestBase;

public class ScenarioTest extends TestBase {

	@Test
	public void RegistrationTest() throws Exception {

		logger = report.createTest("Registration Test");
		this.executor.startExecution(projectConfig.getProperty("testScenarioPath"), "Sheet3");
	}
}
