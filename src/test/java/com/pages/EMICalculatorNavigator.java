package com.pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.parameter.PropertyReader;

public class EMICalculatorNavigator extends BasePage {
	
	Actions actions;
	SoftAssert st = new SoftAssert();
	JavascriptExecutor js = (JavascriptExecutor)driver;

	
//	@FindBy(xpath = "//a[normalize-space()='Home Loans']") WebElement emiCalculatorLink;
	@FindBy(id = "amountRequiredEmiCal") WebElement loanAmountField;
	@FindBy(id = "interestRateEmiCal") WebElement interestRateField;
	@FindBy(id = "submitbuttonEmiCalid") WebElement calculateButton;
	@FindBy(xpath = "//input[@type='radio' and @id='emiPropFinalizedNo' and @value='no']") WebElement radioButtonLabel;
	@FindBy(xpath = "//span[@id = 'perMonthEmi']") WebElement elegibleAmmount;
	@FindBy(id = "amountRequiredEmiCalError") WebElement amtErrMessage;
	
	
	public EMICalculatorNavigator(WebDriver driver) {
		super(driver);
		this.actions = new Actions(driver);
	}
	
	public void openUrl() {
		try {
			driver.get(PropertyReader.readProperty("EmiCalculator.Url"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void enterLoanDetails() {
		loanAmountField.clear();
		loanAmountField.sendKeys("5000000");
		interestRateField.clear();
		interestRateField.sendKeys("7.5");	
//		radioButtonLabel.click();
		js.executeScript("document.querySelector(\"body > div > div:nth-child(4) > div.hl__calc__section.emi-calc > div > div.hl__calc__row > div.hl__calc__lf > form > div.mb-form__row.pb38.has-radio > div.has-radio__flx > div:nth-child(2) > label\").click();\r\n"
				+ "");
		calculateButton.click();
		String Ammount  = elegibleAmmount.getText();
		System.out.println(Ammount);
		st.assertEquals(Ammount, "34,961", "Valid test example");
		st.assertAll();
	}	
	public void enterLoanDetailsInvalid() {
		loanAmountField.clear();
		loanAmountField.sendKeys("5000000");
		interestRateField.clear();
		interestRateField.sendKeys("111");
		js.executeScript("document.querySelector(\"body > div > div:nth-child(4) > div.hl__calc__section.emi-calc > div > div.hl__calc__row > div.hl__calc__lf > form > div.mb-form__row.pb38.has-radio > div.has-radio__flx > div:nth-child(2) > label\").click();\r\n"
				+ "");
		
		
//		radioButtonLabel.click();
		calculateButton.click();
	}	
	public void enterLoanDetailsNoInput() {
		loanAmountField.clear();
//		loanAmountField.sendKeys();
		interestRateField.clear();
//		interestRateField.sendKeys();
		/*js.executeScript("document.querySelector(\"body > div > div:nth-child(4) > div.hl__calc__section.emi-calc > div > div.hl__calc__row > div.hl__calc__lf > form > div.mb-form__row.pb38.has-radio > div.has-radio__flx > div:nth-child(2) > label\").click();\r\n"
				+ "");*/
//		radioButtonLabel.click();
		calculateButton.click();
		waitUntilWebElementIsVisible(amtErrMessage);
		st.assertEquals(amtErrMessage.getText(),"LoanAmount should lie between 1,00,000 and 10,00,00,000","Error Message validation");
	}	
}
