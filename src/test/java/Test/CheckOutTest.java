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


public class CheckOutTest extends BaseTest {
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
    public void addInformationAndContinueWhenCartIsFull() throws InterruptedException {
        inventoryPage.addRandomItemsToCart(3);
        Thread.sleep(2000);
        inventoryPage.clickCartButton();
        cartPage.clickOnCheckoutTab();
        checkOutPage.addFirstName("Milena");
        checkOutPage.addLastName("Milic");
        checkOutPage.addPostalCode("11000");
        Thread.sleep(2000);
        checkOutPage.clickContinueButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(checkOutPage.finishButton.isDisplayed());
        Assert.assertTrue(checkOutPage.cartItem.isDisplayed());
        Assert.assertTrue(checkOutPage.summary.isDisplayed());
    }

    @Test(priority = 3)
    public void addInformationAndContinueWhenCartIsEmpty() throws InterruptedException {
        inventoryPage.clickCartButton();
        cartPage.clickOnCheckoutTab();
        checkOutPage.addFirstName("Milena");
        checkOutPage.addLastName("Milic");
        checkOutPage.addPostalCode("11000");
        Thread.sleep(2000);
        checkOutPage.clickContinueButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(checkOutPage.finishButton.isDisplayed());
        Assert.assertTrue(checkOutPage.summary.isDisplayed());
    }


    @AfterMethod
    public void logoutUser() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu.burgerMenuTab));
        burgerMenu.clickOnBurgerMenuTab();
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu.logutTab));
        burgerMenu.clickOnLogoutTab();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));
        driver.close();
    }

}