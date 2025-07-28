package Test;

import Base.BaseTest;
import Page.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        checkOutPage = new CheckOutPage();
        burgerMenu = new BurgerMenu();

        loginPage.addUsername("standard_user");
        loginPage.addPassword("secret_sauce");
        loginPage.clickOnLoginButton();

    }


    @Test(priority = 1)
    public void removeFromCart() throws InterruptedException {
        inventoryPage.clickAddToCartButton();
        wait.until(ExpectedConditions.elementToBeClickable(inventoryPage.cart));
        inventoryPage.clickCartButton();
        Assert.assertTrue(cartPage.removeFromCart.isDisplayed());
        wait.until(ExpectedConditions.textToBePresentInElement(cartPage.cartBadge, "1"));
        Assert.assertEquals(cartPage.cartBadge.getText(), "1");
        Assert.assertEquals(cartPage.cartQuantity.getText(), "1");
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.removeFromCart));
        cartPage.clickRemoveFromCart();


        boolean isPresent = false;
        try {
            isPresent = cartPage.cartBadge.isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertFalse(isPresent);
    }


    @Test(priority = 2)
    public void continueShopping() {
        inventoryPage.clickCartButton();
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.continueShoppingTab));
        cartPage.clickContinueShoppingTab();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(inventoryPage.cart.isDisplayed());
    }


    @Test(priority = 4)
    public void goToCheckoutWhenCartIsEmpty() {
        inventoryPage.clickCartButton();
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.checkoutTab));
        cartPage.clickOnCheckoutTab();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-step-one.html"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkOutPage.postalCodeField.isDisplayed());
        Assert.assertTrue(checkOutPage.cancelTab.isDisplayed());
    }


    @Test(priority = 1)
    public void goToCheckOutWhenCartIsFull() throws InterruptedException {
        inventoryPage.addRandomItemsToCart(2);
        Thread.sleep(2000);
        inventoryPage.clickCartButton();
        cartPage.clickOnCheckoutTab();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-step-one.html"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkOutPage.postalCodeField.isDisplayed());
        Assert.assertTrue(checkOutPage.cancelTab.isDisplayed());
        Assert.assertEquals(cartPage.cartBadge.getText(), "2");
    }


    @AfterMethod
    public void logoutUser() {
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu.burgerMenuTab));
        burgerMenu.clickOnBurgerMenuTab();
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu.logutTab));
        burgerMenu.clickOnLogoutTab();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));
        driver.close();
    }
}
