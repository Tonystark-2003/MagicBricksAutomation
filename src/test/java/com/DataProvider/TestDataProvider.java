package com.DataProvider;

import org.testng.annotations.DataProvider;

import com.parameter.ExcelReader;

public class TestDataProvider {
	
	@DataProvider(name="LoanEligibiltyDataPositive")
	public static Object[][] getLoanEligibiltyDataPositive(){
		return ExcelReader.getTestData("LoanEligibiltyDataPositive");
	}
	
	@DataProvider(name="LoanEligibiltyDataNegative")
	public static Object[][] getLoanEligibiltyDataNegative(){
		return ExcelReader.getTestData("LoanEligibiltyDataNegative");
	}

	@DataProvider(name="BalTransferPositiveData")
    public static Object[][] getBalTransferPositiveData(){
		return ExcelReader.getTestData("BalTransferPositiveData");
    }
}