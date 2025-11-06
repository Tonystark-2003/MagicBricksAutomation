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
	LoanEligibilityCalculator eligibilityCalculator;
	
	
	@BeforeClass
	public void setupDriver() {
		driver = BaseSetup.chromeDriverSetup();

		System.out.println(((RemoteWebDriver) driver).getCapabilities().getBrowserVersion());
		System.out.println(((RemoteWebDriver) driver).getCapabilities().getBrowserName());
	}
	
	/*---------------------------------Test Case 1----------------------------------------------------
	 * created By : Shubham Pedhe
	 * Reviewed By : Akasha KC
	 * Motive : Open browser and open EMI CalCulator page
	 * */
	
	@Test(priority = 0)
	public void OpenEMICalculator() throws IOException {
		emiCalculator=new EMICalculatorNavigator(driver);
		emiCalculator.openUrl();
		}
	
	@Test (dependsOnMethods = {"OpenEMICalculator"})
	public void ValidateCalculations() {
		emiCalculator.enterLoanDetails();
	}
	
	@Test(dependsOnMethods = {"OpenEMICalculator","ValidateCalculations"})
	public void ValidateErrorMessage() {
		emiCalculator.enterLoanDetailsInvalid();
	}
	
	@Test (priority = 1, dependsOnMethods = {"OpenEMICalculator","ValidateCalculations","ValidateErrorMessage"})
	public void ValidateErrForNoInputs() {
		emiCalculator.enterLoanDetailsNoInput();
	}
	
	@Test(dataProvider = "LoanEligibiltyDataPositive", dataProviderClass = com.DataProvider.TestDataProvider.class, dependsOnMethods = {"ValidateErrForNoInputs"})
	public void EligiblilityCalculationTest(String Monthly_Income, String Ongoing_EMI, String Interest_Rate, String Loan_Tenure, String Eligible_Amount, String Monthly_EMI ) throws IOException {
		eligibilityCalculator = new LoanEligibilityCalculator(driver);
		eligibilityCalculator.openUrl();
		eligibilityCalculator.enterEligibilityCalculatorData(Monthly_Income,Ongoing_EMI,Interest_Rate, Loan_Tenure, Eligible_Amount, Monthly_EMI );
	}
	
//	@Test(dataProvider = "LoanEligibiltyDataNegative", dataProviderClass = com.DataProvider.TestDataProvider.class, dependsOnMethods = {"EligiblilityCalculationTest"})
	@Test(priority = 2, dataProvider = "LoanEligibiltyDataNegative", dataProviderClass = com.DataProvider.TestDataProvider.class)
	public void EligibilityErrTest(String Monthly_Income, String Ongoing_EMI, String Interest_Rate, String Loan_Tenure) {
		eligibilityCalculator.enterEligibilityCalculatorDataWithInvalidData(Monthly_Income, Ongoing_EMI, Interest_Rate, Loan_Tenure);
	}
	
	@AfterClass
	public void testEnd() {
		BaseSetup.tearDown();		
	}
}

	

