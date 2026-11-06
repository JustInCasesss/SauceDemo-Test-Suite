package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.saucedemo.utils.Log;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By inventoryContainer = By.id("inventory_container");
    private By productTitle = By.cssSelector(".inventory_item_name");
    private By addToCartButton = By.cssSelector(".btn_primary");
    private By shoppingCartBadge = By.cssSelector(".shopping_cart_badge");
    private By cartIcon = By.id("shopping_cart_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryContainer));
        Log.info("Inventory page loaded.");
    }

    public List<String> getAllProductTitles() {
        return driver.findElements(productTitle).stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }

    public void addFirstItemToCart() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        try {
            btn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        Log.info("First item added to cart.");
    }

    public int getCartBadgeCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartBadge));
            return Integer.parseInt(driver.findElement(shoppingCartBadge).getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickCartIcon() {
        driver.findElement(cartIcon).click();
        Log.info("Cart icon clicked.");
    }
}
