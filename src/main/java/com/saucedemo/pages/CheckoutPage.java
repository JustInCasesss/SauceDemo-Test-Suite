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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Poll until element is visible or a timeout (ms) elapses. Handles slow page loads in CI.
    private boolean waitForVisible(By locator, long timeoutMs) {
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (System.currentTimeMillis() < deadline) {
            try {
                if (driver.findElement(locator).isDisplayed()) return true;
            } catch (Exception ignored) {
                // not present yet
            }
            try { Thread.sleep(400); } catch (InterruptedException ignored) {}
        }
        return false;
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
        if (!waitForVisible(finishButton, 20000)) {
            throw new org.openqa.selenium.TimeoutException(
                    "Overview page did not load after Continue. URL=" + driver.getCurrentUrl());
        }
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
        if (!waitForVisible(thankYouMessage, 20000)) {
            throw new org.openqa.selenium.TimeoutException(
                    "Order-complete page did not load after Finish. URL=" + driver.getCurrentUrl());
        }
        return driver.findElement(thankYouMessage).getText();
    }
}
