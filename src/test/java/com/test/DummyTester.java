package com.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pages.BalanceTransfer;
import com.pages.EMICalculatorNavigator;
import com.pages.LoanEligibilityCalculator;
import com.setup.BaseSetup;
import com.Utils2.ReportManager;

public class DummyTester extends ReportManager {
	
	WebDriver driver;
	EMICalculatorNavigator emiCalculator;
	LoanEligibilityCalculator eligibilityCalculator;
	BalanceTransfer balanceTransfer;
	
	
	@BeforeClass
	public void setupDriver() {
		driver = BaseSetup.chromeDriverSetup();

		System.out.println(((RemoteWebDriver) driver).getCapabilities().getBrowserVersion());
		System.out.println(((RemoteWebDriver) driver).getCapabilities().getBrowserName());
	}
	
	/*---------------------------------Test Case 1----------------------------------------------------
	 * created By : Shubham Pedhe
	 * Reviewed By : Akasha KC
	 * Motive : Open browser and open EMI Calculator page
	 * */
	
	@Test(priority = 0)
	public void OpenEMICalculator() throws IOException {
		emiCalculator=new EMICalculatorNavigator(driver);
		emiCalculator.openUrl();
		}

	/*---------------------------------Test Case 2----------------------------------------------------
	 * created By : Shubham Pedhe
	 * Reviewed By : Akasha KC
	 * Motive : Enters Details And calculates and validates the expected result
	 * */
	
	@Test (dependsOnMethods = {"OpenEMICalculator"})
	public void ValidateCalculations() {
		emiCalculator.enterLoanDetails();
	}
	/*---------------------------------Test Case 3----------------------------------------------------
	 * created By : Shubham Pedhe
	 * Reviewed By : Akasha KC
	 * Motive : Enters Details And calculates and validates the expected result for error message
	 * */

	
	@Test(dependsOnMethods = {"OpenEMICalculator","ValidateCalculations"})
	public void ValidateErrorMessage() {
		emiCalculator.enterLoanDetailsInvalid();
	}

	/*---------------------------------Test Case 4----------------------------------------------------
	 * created By : Shubham Pedhe
	 * Reviewed By : Akasha KC
	 * Motive : No inputs are given and validates the expected result for error message
	 * */
	
	@Test (priority = 1, dependsOnMethods = {"OpenEMICalculator","ValidateCalculations","ValidateErrorMessage"})
	public void ValidateErrForNoInputs() {
		emiCalculator.enterLoanDetailsNoInput();
	}
	/*---------------------------------Test Case 5----------------------------------------------------
	 * created By : Shubham Pedhe
	 * Reviewed By : Akasha KC
	 * Motive : Enters Details And calculates and validates the expected result for loan eligibility
	 * */
	
	@Test(dataProvider = "LoanEligibiltyDataPositive", dataProviderClass = com.DataProvider.TestDataProvider.class, dependsOnMethods = {"ValidateErrForNoInputs"})
	public void EligiblilityCalculationTest(String Monthly_Income, String Ongoing_EMI, String Interest_Rate, String Loan_Tenure, String Eligible_Amount, String Monthly_EMI ) throws IOException {
		eligibilityCalculator = new LoanEligibilityCalculator(driver);
		eligibilityCalculator.openUrl();
		eligibilityCalculator.enterEligibilityCalculatorData(Monthly_Income,Ongoing_EMI,Interest_Rate, Loan_Tenure, Eligible_Amount, Monthly_EMI );
	}

	/*---------------------------------Test Case 6----------------------------------------------------
	 * created By : Shubham Pedhe
	 * Reviewed By : Akasha KC
	 * Motive : Enters Details And calculates and validates the expected result for error message for loan eligibility
	 * */
	
//	@Test(dataProvider = "LoanEligibiltyDataNegative", dataProviderClass = com.DataProvider.TestDataProvider.class, dependsOnMethods = {"EligiblilityCalculationTest"})
	@Test(priority = 2, dataProvider = "LoanEligibiltyDataNegative", dataProviderClass = com.DataProvider.TestDataProvider.class)
	public void EligibilityErrTest(String Monthly_Income, String Ongoing_EMI, String Interest_Rate, String Loan_Tenure) {
		eligibilityCalculator.enterEligibilityCalculatorDataWithInvalidData(Monthly_Income, Ongoing_EMI, Interest_Rate, Loan_Tenure);
	}

	/*---------------------------------Test Case 7----------------------------------------------------
	 * created By : Shubham Pedhe
	 * Reviewed By : Akasha KC
	 * Motive : Enters Details And calculates and validates the expected result
	 * */

	@Test(dataProvider = "BalTransferPositiveData", dataProviderClass = com.DataProvider.TestDataProvider.class, dependsOnMethods = {"EligibilityErrTest"})
	public void BalanceTransferTest(String Loan_Amount, String Tenure_Current, String Rate_of_Interest_Current, String Installments_Paid, String Processing_Fees, String Tenure_New, String Rate_of_Interest_New, String Saved_Interest, String Outstanding_Principal, String New_EMI){
		balanceTransfer = new BalanceTransfer(driver);
		balanceTransfer.openUrl();
		balanceTransfer.BTCalculationTest(Loan_Amount, Tenure_Current, Rate_of_Interest_Current, Installments_Paid, Processing_Fees, Tenure_New, Rate_of_Interest_New, Saved_Interest, Outstanding_Principal, New_EMI);
	}
	
	@AfterClass
	public void testEnd() {
		BaseSetup.tearDown();		
	}
}

	

