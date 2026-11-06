package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.saucedemo.utils.Log;
import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By zipField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By thankYouMessage = By.cssSelector(".complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterCheckoutInfo(String firstName, String lastName, String zip) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(zipField).clear();
        driver.findElement(zipField).sendKeys(zip);
        Log.info("Checkout info entered.");
    }

    public void clickContinue() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        try {
            btn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton));
        Log.info("Continue button clicked, overview page loaded.");
    }

    public void clickFinish() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        try {
            btn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        Log.info("Finish button clicked.");
    }

    public String getThankYouMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(thankYouMessage)).getText();
    }
}
