package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.saucedemo.utils.Log;
import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By cartItems = By.cssSelector(".cart_item");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public int getNumberOfItems() {
        return driver.findElements(cartItems).size();
    }

    public void clickCheckout() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        try {
            btn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        // Checkout page loads async; poll for the first-name field OR the checkout URL.
        long deadline = System.currentTimeMillis() + 20000;
        boolean loaded = false;
        while (System.currentTimeMillis() < deadline) {
            try {
                if (driver.findElement(By.id("first-name")).isDisplayed()
                        || driver.getCurrentUrl().contains("checkout-step-one")) {
                    loaded = true;
                    break;
                }
            } catch (Exception ignored) {
                // element not present yet — keep polling
            }
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        }
        if (!loaded) {
            throw new org.openqa.selenium.TimeoutException(
                    "Checkout page did not load after clicking Checkout. URL=" + driver.getCurrentUrl());
        }
        Log.info("Checkout button clicked, checkout page loaded.");
    }
}
