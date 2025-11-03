package com.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.parameter.PropertyReader;

public class EMICalculatorNavigator extends BasePage {
	
	Actions actions;
	
	
//	@FindBy(xpath = "//a[normalize-space()='Home Loans']") WebElement emiCalculatorLink;
	@FindBy(id = "amountRequiredEmiCal") WebElement loanAmountField;
	@FindBy(id = "interestRateEmiCal") WebElement interestRateField;
	@FindBy(id = "submitbuttonEmiCalid") WebElement calculateButton;
	@FindBy(className = "has-radio__flx__cel--lbl") WebElement radioButtonLabel;
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
		radioButtonLabel.click();
		calculateButton.click();
	}	
}
