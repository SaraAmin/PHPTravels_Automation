# PHPTravels_Automation
This is a simple keyword driven framework automating the registration and login process of [phpTravels](https://www.phptravels.net/) website

## Setup
- After cloning the repo make sure to build the **pom.xml** file to get the required dependencies.</br>
- Make sure to use a java version equal or higher than **1.8**

## Framework structure
- Provided are the framework's classes and configurations property files and TestData, ObjectRepository and Test case excel sheets.</br>
- This is a data driven/keyword driven framework.</br>
- Reporting is done using "Extent Reports" library **(3.1.5)**</br>

## Features and Limitation
**Supported Browsers**<br />
  Chrome and Firefox only.<br />

**Supported files format**</br>
Test Data, Object Repository, and TestScenario files must all be in excel format **(.xlsx)**</br>
Project and 

**Supported Keywords**</br>- Open</br>- Navigate To</br>- Assert Title</br>- Click</br>- Type</br>- Close</br>- verify Text Match (Matching given string with a regex)</br>- Assert Text Equal</br>- Verify Equal</br>- Verify Not Equal

**Parallel Execution is not enabled**</br>

**Screenshots are enabled on failure and attached to report**</br>

**Upon a step failing, and exception occuring, or a failed assertion, **</br>

**Configurations**</br>
1- Project and environment configuration files</br>
   These files can be found in "framework\src\main\java\com\framework\config\"
  > -***Project configuration: Contains the global values that needs to be filled before running the tests.***
  >   - *objectRepositoryPath* = *Full* path of the object repository excel sheet
  >   - *objectRepo_Id* = Targeted objectRepo sheetName
  >   - *testDataPath* = *Full* path of the test Data excel sheet
  >   - *testData_Id* = Targeted test Data sheetName
  >   - *testScenarioPath* = *Full* path of the TestScenario(s) excel sheet
  >   - *chromeDriver* = *Full* path of the chrome driver
  >   - *geckoDriver* = *Full* path of the gecko driver
  >   - *reportPath* = *Full* path of the *Report* folder
  > 
  > -***Environment configuration: Contains the test env properties.***
  >   - *browser* = Name of the browser to be used (Chrome *or* Firefox)
  >   - *objectWait* = Value of timeout to wait for object (in seconds)
  >   - *pageLoadTimeout* = Time waiting for the page to load (in seeconds)
  >   - *implicitWait* = Value of the implicit wait (in seconds)</br>
  
2- Project and environment configuration files</br>
  >   - TestScenario --> where all your test steps will be included</br>(ex: See "framework\src\main\java\com\framework\config\TestScenario.xlsx")
  >   - TestData     --> Where all the testData are included</br>(ex: See "framework\src\main\java\com\framework\config\TestData.xlsx")
  >   - ObjectRepo   --> Contains all the objects needed during execution</br>(ex: See "framework\src\main\java\com\framework\config\ObjectRepository.xlsx")
  
3- TestScenario Structure
Make sure to follow the Same structure if you will include multiple scenario files.
```java
public class ScenarioTest extends TestBase {

	@Test(priority = 1)
	public void TestScript1() throws Exception {
		logger = report.createTest("Registration Test"); //name of the test/scenario in the report
		executor.startExecution(projectConfig.getProperty("testScenarioPath"), "Register"); //Name of the sheet containing the test steps
	}

	@Test(priority = 2)
	public void TestScript2() throws Exception {
		logger = report.createTest("Login Test"); //name of the test/scenario in the report
		executor.startExecution(projectConfig.getProperty("testScenarioPath"), "Login"); //Name of the sheet containing the test steps
	}
}
```
