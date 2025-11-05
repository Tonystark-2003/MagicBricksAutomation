package com.DataProvider;

import org.testng.annotations.DataProvider;

import com.parameter.ExcelReader;

public class TestDataProvider {
	@DataProvider(name="LoanEligibiltyData")
	public static Object[][] getLoanEligibiltyData(){
		return ExcelReader.getTestData("Sheet2");
	}
}