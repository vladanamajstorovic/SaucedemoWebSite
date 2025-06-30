package Test;

import Base.BaseTest;
import Page.CartPage;
import Page.CheckOutPage;
import Page.InventoryPage;
import Page.LoginPage;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
        loginPage=new LoginPage();
        inventoryPage=new InventoryPage();
        cartPage=new CartPage();
        checkOutPage=new CheckOutPage();

        loginPage.addUsername("standard_user");
        loginPage.addPassword("secret_sauce");
        loginPage.clickOnLoginButton();

    }


    @Test
    public void removeFromCart() throws InterruptedException {
        inventoryPage.clickAddToCartButton();
        Thread.sleep(1000);
        inventoryPage.clickCartButton();
        Assert.assertTrue(cartPage.removeFromCart.isDisplayed());
        Thread.sleep(1000);
        Assert.assertEquals(cartPage.cartBadge.getText(),"1");
        Assert.assertEquals(cartPage.cartQuantity.getText(),"1");
        Thread.sleep(1000);
        cartPage.clickRemoveFromCart();


        boolean isPresent = false;
        try {
            isPresent=cartPage.cartBadge.isDisplayed();
        }
        catch (Exception e) {

        }
        Assert.assertFalse(isPresent);
    }


    @Test
    public void continueShopping() throws InterruptedException {
        inventoryPage.clickCartButton();
        Thread.sleep(2000);
        cartPage.clickContinueShoppingTab();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(inventoryPage.cart.isDisplayed());
    }


    @Test
    public void goToCheckoutWhenCartIsEmpty() throws InterruptedException {
        inventoryPage.clickCartButton();
        Thread.sleep(2000);
        cartPage.clickOnCheckoutTab();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkOutPage.postalCodeField.isDisplayed());
        Assert.assertTrue(checkOutPage.cancelTab.isDisplayed());
    }


    @Test
    public void goToCheckOutWhenCartIsFull() throws InterruptedException {
        inventoryPage.clickAddToCartButton();
        Thread.sleep(1000);
        inventoryPage.clickAddToCartButton();
        Thread.sleep(1000);
        inventoryPage.clickCartButton();
        cartPage.clickOnCheckoutTab();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkOutPage.postalCodeField.isDisplayed());
        Assert.assertTrue(checkOutPage.cancelTab.isDisplayed());
        Assert.assertEquals(cartPage.cartBadge.getText(),"2");
    }


}
