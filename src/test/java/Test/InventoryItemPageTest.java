package Test;

import Base.BaseTest;
import Page.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;

public class InventoryItemPageTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        softAssert = new SoftAssert();
        inventoryItemPage = new InventoryItemPage();
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage= new CartPage();
        burgerMenu=new BurgerMenu();


        loginPage.addUsername("standard_user");
        loginPage.addPassword("secret_sauce");
        loginPage.clickOnLoginButton();


    }

    //WITH SOFT ASSERT

    @Test (priority = 2)
    public void verifyAllProductDetailsVisibleForEachItem() {

        int totalItems = inventoryPage.inventoryItems.size();

        for (int i = 0; i <= totalItems - 1; i++) {
            driver.get("https://www.saucedemo.com/inventory-item.html?id=" + i);
            softAssert.assertTrue(inventoryItemPage.itemName.isDisplayed(), " Name not visible for item ID: " + i);
            softAssert.assertTrue(inventoryItemPage.itemDesc.isDisplayed(), " Description not visible for item ID: " + i);
            softAssert.assertTrue(inventoryItemPage.itemPrice.isDisplayed(), " Price not visible for item ID: " + i);
            softAssert.assertTrue(inventoryItemPage.itemImg.isDisplayed(), " Image not visible for item ID: " + i);
            softAssert.assertTrue(inventoryItemPage.addToCartButton.isEnabled(), " Add to Cart button not visible for item ID: " + i);
            softAssert.assertTrue(inventoryItemPage.backToProductsButton.isEnabled(), " Back button not visible for item ID: " + i);
            softAssert.assertTrue(inventoryItemPage.cartIcon.isDisplayed(), " Cart Icon not visible for item ID: " + i);

        }

        softAssert.assertAll();


    }


    @Test (priority = 1)
    public void AddRemoveEachToFromCart() {

        int totalItems = inventoryPage.inventoryItems.size();


        for (int i = 0; i <= totalItems - 1; i++) {
            driver.get("https://www.saucedemo.com/inventory-item.html?id=" + i);
            wait.until(ExpectedConditions.elementToBeClickable(inventoryItemPage.addToCartButton));
            inventoryItemPage.clickAddToCart();
            wait.until(ExpectedConditions.elementToBeClickable(inventoryItemPage.removeButton));
            softAssert.assertTrue(inventoryItemPage.cartBadge.isDisplayed(), " Cart badge not visible for item ID: " + i);
            softAssert.assertEquals(inventoryItemPage.cartBadge.getText(), "1", " Number of items is not showing in the cart for item ID: " + i);
            softAssert.assertTrue(inventoryItemPage.removeButton.isDisplayed(), " Remove button not visible for item ID: " + i);


            inventoryItemPage.clickRemoveFromCart();
            wait.until(ExpectedConditions.elementToBeClickable(inventoryItemPage.addToCartButton));
            softAssert.assertTrue(inventoryItemPage.addToCartButton.isDisplayed(), " Add to cart button not visible for item ID: " + i);


        }

        softAssert.assertAll();
    }

    @Test (priority = 2)
    public void goBackToProductsForEachItem() {
        int totalItems = inventoryPage.inventoryItems.size();


        for (int i = 0; i <= totalItems - 1; i++) {
            driver.get("https://www.saucedemo.com/inventory-item.html?id=" + i);
            wait.until(ExpectedConditions.elementToBeClickable(inventoryItemPage.backToProductsButton));
            inventoryItemPage.clickOnBackToProductsButton();
            wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
            softAssert.assertTrue(inventoryPage.sortDropdown.isDisplayed(), " Sort option not visible for item ID: " + i);
            softAssert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Not on inventory page, for item ID:" + i);


        }
        softAssert.assertAll();


    }

    @Test (priority = 2)
    public void goToCartPageForEachItem()  {
        int totalItems = inventoryPage.inventoryItems.size();


        for (int i = 0; i <= totalItems - 1; i++) {
            driver.get("https://www.saucedemo.com/inventory-item.html?id=" + i);
            wait.until(ExpectedConditions.elementToBeClickable(inventoryItemPage.backToProductsButton));
            inventoryItemPage.clickCartIcon();
            wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/cart.html"));
            softAssert.assertTrue(cartPage.checkoutTab.isDisplayed(), "Checkout not visible for item ID: " + i);


        }
        softAssert.assertAll();

    }


    @AfterMethod
    public void driverClose() {
        driver.close();
    }

}
