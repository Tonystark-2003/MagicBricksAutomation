package com.pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.parameter.PropertyReader;

public class BalanceTransfer extends BasePage{

    SoftAssert st = new SoftAssert();
    JavascriptExecutor js = (JavascriptExecutor) driver;


	//Input Elements
    @FindBy(xpath = "(//input[@id='laltc' and @class = 'inputAdvFinance'])") WebElement loanAmountField;
    @FindBy(xpath = "(//input[@id='rltc' and @class = 'inputAdvFinance'])") WebElement RoIField;
    @FindBy(xpath = "(//input[@id='ltltc' and @class = 'inputAdvFinance'])") WebElement tenureField;
    @FindBy(xpath = "(//input[@id='ipltc' and @class = 'inputAdvFinance'])") WebElement paidEMIField;
    @FindBy(xpath = "(//input[@id='pfltc' and @class = 'inputAdvFinance'])") WebElement processinfFeeField;
    @FindBy(xpath = "(//input[@id='nltltc' and @class = 'inputAdvFinance'])") WebElement newTenureField;
    @FindBy(xpath = "(//input[@id='nrltc' and @class = 'inputAdvFinance'])") WebElement newRoIField;
    @FindBy(xpath = "//input[@value='Compare']") WebElement compareButton;
    
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
    
    public void BTCalculationTest(String Loan_Amount, String Tenure_Current, String Rate_of_Interest_Current, String Installments_Paid, String Processing_Fees, String Tenure_New, String Rate_of_Interest_New, String Saved_Interest, String Outstanding_Principal, String New_EMI
 ) {
    	
        loanAmountField.clear();
    	loanAmountField.sendKeys(Loan_Amount);
    	RoIField.clear();
    	RoIField.sendKeys(Rate_of_Interest_Current);
    	tenureField.clear();
    	tenureField.sendKeys(Tenure_Current);
        paidEMIField.clear();
    	paidEMIField.sendKeys(Installments_Paid);
    	processinfFeeField.clear();
    	processinfFeeField.sendKeys(Processing_Fees);
    	newTenureField.clear();
    	newTenureField.sendKeys(Tenure_New);
    	newRoIField.clear();
    	newRoIField.sendKeys(Rate_of_Interest_New);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", compareButton);
        compareButton.click();
//        waitUntilTextChanges(savedInterest, driver);
        st.assertEquals(savedInterest.getText().replaceAll(",","").trim(), Saved_Interest, "Saved Interest does not match");
        st.assertEquals(emiField.getText().replaceAll(",","").trim(), New_EMI, "New EMI does not match");
        st.assertAll();  	
            	
    }
    
    

}
