package com.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;	
		PageFactory.initElements(driver, this);
	}
	
	public void loadUrl(String url) {
		driver.get(url);
	}
	
	public void waitUntilWebElementIsVisible(WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitUntilWebElementIsClickable(WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
    public void waitUntilTextChanges(WebElement element, WebDriver driver) {
        String oldText = element.getText().trim();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> {
            String newText = element.getText().trim();
            return !newText.equals(oldText) && !newText.isEmpty();
        });
    }
}
