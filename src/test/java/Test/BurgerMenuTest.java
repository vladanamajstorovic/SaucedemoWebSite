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

public class BurgerMenuTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        burgerMenu = new BurgerMenu();
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();

        loginPage.addUsername("standard_user");
        loginPage.addPassword("secret_sauce");
        loginPage.clickOnLoginButton();


    }

    @Test(priority = 1)
    public void openBurgerMenu() throws InterruptedException {
        burgerMenu.clickOnBurgerMenuTab();
        Thread.sleep(2000);
        Assert.assertEquals(burgerMenu.burgerMenuWrap.getAttribute("aria-hidden"), "false");

    }

    @Test(priority = 3)
    public void openAllItems() {
        burgerMenu.clickOnBurgerMenuTab();
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu.allItems));
        burgerMenu.clickOnAllItems();
    }


    @Test(priority = 3)
    public void openAboutSection() {
        burgerMenu.clickOnBurgerMenuTab();
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu.aboutTab));
        burgerMenu.clickOnAboutTab();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }


    @Test(priority = 1)
    public void logout() {
        burgerMenu.clickOnBurgerMenuTab();
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu.logutTab));
        burgerMenu.clickOnLogoutTab();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test(priority = 3)
    public void resetAppState() {
        inventoryPage.clickAddToCartButton();
        burgerMenu.clickOnBurgerMenuTab();
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu.resetTab));
        burgerMenu.clickOnResetTab();

        boolean isPresent = false;
        try {
            isPresent = cartPage.cartBadge.isDisplayed();
        } catch (Exception e) {

        }
        Assert.assertFalse(isPresent);

    }


    @AfterMethod
    public void logoutUser() {
        driver.close();
    }

}
