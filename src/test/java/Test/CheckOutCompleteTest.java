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

public class CheckOutCompleteTest extends BaseTest {

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
        checkOutPageStepTwo = new CheckOutPageStepTwo();
        checkOutCompletePage = new CheckOutCompletePage();
        burgerMenu = new BurgerMenu();

        loginPage.addUsername("standard_user");
        loginPage.addPassword("secret_sauce");
        loginPage.clickOnLoginButton();


    }

    @Test(priority = 3)

    public void backToHomePage() {
        inventoryPage.clickCartButton();
        cartPage.clickOnCheckoutTab();
        checkOutPage.addFirstName("Milena");
        checkOutPage.addLastName("Milic");
        checkOutPage.addPostalCode("11000");
        wait.until(ExpectedConditions.elementToBeClickable(checkOutPage.continueTab));
        checkOutPage.clickContinueButton();
        checkOutPageStepTwo.clickOnFinishButton();
        wait.until(ExpectedConditions.elementToBeClickable(checkOutCompletePage.backHomeButton));
        checkOutCompletePage.clickHomeButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");


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
