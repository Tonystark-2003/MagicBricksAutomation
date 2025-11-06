package com.pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.parameter.PropertyReader;

public class LoanEligibilityCalculator extends BasePage {

	SoftAssert st = new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor) driver;

	// Input Fields
	@FindBy(id = "incomePerMonthEliCal")
	WebElement monthlyIncomeField;
	@FindBy(id = "existingLoanEliCal")
	WebElement existingLoanField;
	@FindBy(id = "interestRateEliCal")
	WebElement interestRateField;
	@FindBy(xpath = "//label[@for='emiPropFinalizedNo']")
	WebElement radioButtonLabel;
	@FindBy(xpath = "//a[@id='submitbuttonEliCalid']")
	WebElement calculateButton;
	@FindBy(id = "loanAmtResultDiv")
	WebElement eligibleLoanAmountResult;
	@FindBy(xpath = "//li[@class='select__list--option' and text()='5 yrs']") 
	WebElement tenureyrs5;
	@FindBy(xpath = "//li[@class='select__list--option' and text()='30 yrs']") 
	WebElement tenureyrs30;

	// Error Messages
	@FindBy(xpath = "//span[@id='interestRateEliCalError']")
	WebElement interestRateErrorMessage;
	@FindBy(xpath = "//div[@class='hl__banner__close']")
	WebElement bannerCloseButton;
	@FindBy(xpath = "//span[@id='incomePerMonthEliCalError']")
	WebElement monthlyIncomeErrorMessage;
	

	public LoanEligibilityCalculator(WebDriver driver) {
		super(driver);
	}

	public void openUrl() {
		try {
			driver.get(PropertyReader.readProperty("EligiblityCalculator.Url"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enterEligibilityCalculatorData(String Monthly_Income, String Ongoing_EMI, String Interest_Rate,
			String Loan_Tenure, String Eligible_Amount, String Monthly_EMI) {
		monthlyIncomeField.clear();
		monthlyIncomeField.sendKeys(Monthly_Income);
		existingLoanField.clear();
		existingLoanField.sendKeys(Ongoing_EMI);
		interestRateField.clear();
		interestRateField.sendKeys(Interest_Rate);
//		radioButtonLabel.click();
		js.executeScript(
				"document.querySelector(\"body > div > div:nth-child(4) > div.hl__calc__section > div > div.hl__calc__row > form > div.mb-form__row.pb38.has-radio > div.has-radio__flx > div:nth-child(2) > label\").click();\r\n");
		if (bannerCloseButton.isDisplayed()) {
//			bannerCloseButton.click();
			js.executeScript(
					"document.querySelector(\"#compare-offers-bottom-banner > div.hl__banner__close\").click();\r\n");
		}
				
		calculateButton.click();
		waitUntilWebElementIsClickable(eligibleLoanAmountResult);

		st.assertEquals(eligibleLoanAmountResult.getText(), Eligible_Amount, "Eligible Loan Amount Validation");
		st.assertAll();
	}

	public void enterEligibilityCalculatorDataWithNoData() {
		monthlyIncomeField.clear();
//		monthlyIncomeField.sendKeys();
		existingLoanField.clear();
//		existingLoanField.sendKeys("20000");
		interestRateField.clear();
//		interestRateField.sendKeys("10.5");
//		radioButtonLabel.click();
		js.executeScript(
				"document.querySelector(\"body > div > div:nth-child(4) > div.hl__calc__section > div > div.hl__calc__row > form > div.mb-form__row.pb38.has-radio > div.has-radio__flx > div:nth-child(2) > label\").click();\r\n");
		calculateButton.click();

		waitUntilWebElementIsVisible(monthlyIncomeErrorMessage);
		try {
			st.assertEquals(monthlyIncomeErrorMessage.getText(),
					PropertyReader.readProperty("MonthlyIncomeErrorMessage"), "Monthly Income Error Message Validaton");
		} catch (IOException e) {
			e.printStackTrace();
		}

		waitUntilWebElementIsVisible(interestRateErrorMessage);
		try {
			st.assertEquals(interestRateErrorMessage.getText(), PropertyReader.readProperty("interestRateEliCalError"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void enterEligibilityCalculatorDataWithInvalidData(String Monthly_Income, String Ongoing_EMI,
			String Interest_Rate, String Loan_Tenure) {
		String tenure = Loan_Tenure;
		monthlyIncomeField.clear();
		monthlyIncomeField.sendKeys(Monthly_Income);
		existingLoanField.clear();
		existingLoanField.sendKeys(Ongoing_EMI);
		interestRateField.clear();
		interestRateField.sendKeys(Interest_Rate);
//		radioButtonLabel.click();
		js.executeScript(
				"document.querySelector(\"body > div > div:nth-child(4) > div.hl__calc__section > div > div.hl__calc__row > form > div.mb-form__row.pb38.has-radio > div.has-radio__flx > div:nth-child(2) > label\").click();\r\n");
		calculateButton.click();
		System.out.println(tenure);
		
		if(tenure == "5") {
			tenureyrs5.click();
		}else if(tenure == "30") {
			tenureyrs30.click();
		}

		waitUntilWebElementIsVisible(monthlyIncomeErrorMessage);
		try {
			st.assertEquals(monthlyIncomeErrorMessage.getText(),
					PropertyReader.readProperty("MonthlyIncomeErrorMessage"), "Monthly Income Error Message Validaton");
		} catch (IOException e) {
			e.printStackTrace();
		}
		waitUntilWebElementIsVisible(interestRateErrorMessage);
		try {
			st.assertEquals(interestRateErrorMessage.getText(), PropertyReader.readProperty("interestRateEliCalError"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
