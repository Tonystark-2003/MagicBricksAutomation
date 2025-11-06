package com.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.parameter.PropertyReader;

public class BalanceTransfer extends BasePage{

	//Input Elements
    @FindBy(xpath = "(//input[@id='laltc' and @class = 'inputAdvFinance'])") WebElement loanAmountField;
    @FindBy(xpath = "(//input[@id='rltc' and @class = 'inputAdvFinance'])") WebElement RoIField;
    @FindBy(xpath = "(//input[@id='ltltc' and @class = 'inputAdvFinance'])") WebElement tenureField;
    @FindBy(xpath = "(//input[@id='ipltc' and @class = 'inputAdvFinance'])") WebElement paidEMIField;
    @FindBy(xpath = "(//input[@id='pfltc' and @class = 'inputAdvFinance'])") WebElement processinfFeeField;
    @FindBy(xpath = "(//input[@id='nltltc' and @class = 'inputAdvFinance'])") WebElement newTenureField;
    @FindBy(xpath = "(//input[@id='nrltc' and @class = 'inputAdvFinance'])") WebElement newRoIField;
    
    
    //Output Elements
    @FindBy(id="messageAmountDiv") WebElement savedInterest;
    @FindBy(id="emiTenureSavedResultDiv") WebElement reducedTenure;
    @FindBy(id="emiDiv") WebElement emiField;
    
    //DropDown Error Elements
    @FindBy(xpath = "//div[@id='laltcError']") WebElement loanAmountFieldErr;
    @FindBy(xpath = "//div[@id='ltltcError']") WebElement tenureFieldErr;
    @FindBy(xpath = "//div[@id='rltcError']") WebElement RoIFieldErr;
    @FindBy(xpath = "//div[@id='pfltcError']") WebElement processinfFeeFieldErr;
    @FindBy(xpath = "//div[@id='nltltcError']") WebElement newTenureFieldErr;
    @FindBy(xpath = "//div[@id='nrltcError']") WebElement newRoIFieldErr;
    
    public BalanceTransfer(WebDriver driver) {
    	super(driver);
    }
    
    public void openUrl() {
		try {
			driver.get(PropertyReader.readProperty("Balanceatransfer.Url"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public void BTCalculationTest() {
    	loanAmountField.clear();
    	loanAmountField.sendKeys();
    	RoIField.clear();
    	RoIField.sendKeys();
    	tenureField.clear();
    	tenureField.sendKeys();
    	processinfFeeField.clear();
    	processinfFeeField.sendKeys();
    	newTenureField.clear();
    	newTenureField.sendKeys();
    	newRoIField.clear();
    	newRoIField.sendKeys();
    	
    	
    	
    }
    
    

}
