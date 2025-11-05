package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BalanceTransfer {

    @FindBy(xpath = "(//input[@id='laltc' and @class = 'inputAdvFinance'])") WebElement loanAmountField;
    @FindBy(xpath = "(//input[@id='rltc' and @class = 'inputAdvFinance'])") WebElement interestRateField;
    @FindBy(xpath = "((//input[@id='ltltc' and @class = 'inputAdvFinance'])") WebElement tenureField;



}
