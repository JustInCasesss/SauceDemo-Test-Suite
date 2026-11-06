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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        Log.info("Checkout button clicked, checkout page loaded.");
    }
}
