package com.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pages.EMICalculatorNavigator;
import com.setup.BaseSetup;
import com.utils.ReportManager;

public class DummyTester extends ReportManager {
	
	WebDriver driver;
	EMICalculatorNavigator emiCalculator;
	@BeforeClass
	public void setupDriver() {
		driver = BaseSetup.chromeDriverSetup();
	}
	
	@Test
	public void test() throws IOException {
		emiCalculator=new EMICalculatorNavigator(driver);
		emiCalculator.openUrl();
		}
	
	@Test(dependsOnMethods = {"test"})
	public void test2() {
		emiCalculator.enterLoanDetails();
	}
	@Test(dependsOnMethods = {"test","test2"})
	public void noInputTest() {
		emiCalculator.enterLoanDetailsNoInput();
	}
	
//	@AfterClass
//	public void testEnd() {
//		BaseSetup.tearDown();
//	}
}

	

