package Test;

import Base.BaseTest;
import Page.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
        burgerMenu=new BurgerMenu();
        loginPage=new LoginPage();
        inventoryPage=new InventoryPage();
        cartPage= new CartPage();

        loginPage.addUsername("standard_user");
        loginPage.addPassword("secret_sauce");
        loginPage.clickOnLoginButton();


    }

    @Test
    public void openBurgerMenu() throws InterruptedException {
        burgerMenu.clickOnBurgerMenuTab();

        Thread.sleep(2000);
        Assert.assertEquals(burgerMenu.burgerMenuWrap.getAttribute("aria-hidden"),"false");

    }

    @Test public void openAllItems() throws InterruptedException {
        burgerMenu.clickOnBurgerMenuTab();
        Thread.sleep(1000);
        burgerMenu.clickOnAllItems();
    }


    @Test public void openAboutSection(
    ) throws InterruptedException {  burgerMenu.clickOnBurgerMenuTab();
        Thread.sleep(1000);
        burgerMenu.clickOnAboutTab();
        Assert.assertEquals(driver.getCurrentUrl(),"https://saucelabs.com/");
    }


    @Test
    public void logout() throws InterruptedException {
        burgerMenu.clickOnBurgerMenuTab();
        Thread.sleep(1000);
        burgerMenu.clickOnLogoutTab();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }

    @Test
    public void resetAppState() throws InterruptedException {
        inventoryPage.clickAddToCartButton();
        burgerMenu.clickOnBurgerMenuTab();
        Thread.sleep(1000);
        burgerMenu.clickOnResetTab();

        boolean isPresent = false;
        try {
            isPresent=cartPage.cartBadge.isDisplayed();
        }
        catch (Exception e) {

        }
        Assert.assertFalse(isPresent);

    }





}
