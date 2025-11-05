package com.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pages.EMICalculatorNavigator;
import com.pages.LoanEligibilityCalculator;
import com.setup.BaseSetup;
import com.Utils2.ReportManager;

public class DummyTester extends ReportManager {
	
	WebDriver driver;
	EMICalculatorNavigator emiCalculator;
	@BeforeClass
	public void setupDriver() {
		driver = BaseSetup.chromeDriverSetup();

		System.out.println(((RemoteWebDriver) driver).getCapabilities().getBrowserVersion());
		System.out.println(((RemoteWebDriver) driver).getCapabilities().getBrowserName());
	}
	
	@Test(enabled = false)
	public void test() throws IOException {
		emiCalculator=new EMICalculatorNavigator(driver);
		emiCalculator.openUrl();
		}
	
	@Test(enabled = false)
	public void test2() {
		emiCalculator.enterLoanDetails();
	}
	
	@Test(enabled = false)
	public void enterLoanDetailsInvalid() {
		emiCalculator.enterLoanDetailsNoInput();
	}
	
	@Test (enabled = false)
	public void noInputTest() {
		emiCalculator.enterLoanDetailsNoInput();
	}
	
	@Test(dataProvider = "LoanEligibiltyData", dataProviderClass = com.DataProvider.TestDataProvider.class)
	public void EligiblilityCalculatorTest(String Monthly_Income, String Ongoing_EMI, String Interest_Rate, String Loan_Tenure, String Eligible_Amount, String Monthly_EMI ) throws IOException {
		LoanEligibilityCalculator eligibilityCalculator = new LoanEligibilityCalculator(driver);
		eligibilityCalculator.openUrl();
		eligibilityCalculator.enterEligibilityCalculatorData(Monthly_Income,Ongoing_EMI,Interest_Rate,	Loan_Tenure,	Eligible_Amount,	Monthly_EMI );
//		eligibilityCalculator.enterEligibilityCalculatorDataWithNoData();
//		eligibilityCalculator.enterEligibilityCalculatorDataWithInvalidData();
	}
	
	@AfterClass
	public void testEnd() {
		BaseSetup.tearDown();
	}
}

	

