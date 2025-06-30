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
        checkOutPageStepTwo=new CheckOutPageStepTwo();
        checkOutCompletePage=new CheckOutCompletePage();

        loginPage.addUsername("standard_user");
        loginPage.addPassword("secret_sauce");
        loginPage.clickOnLoginButton();


    }

    @Test

    public void backToHomePage() throws InterruptedException {
        inventoryPage.clickCartButton();
        cartPage.clickOnCheckoutTab();
        checkOutPage.addFirstName("Milena");
        checkOutPage.addLastName("Milic");
        checkOutPage.addPostalCode("11000");
        Thread.sleep(2000);
        checkOutPage.clickContinueButton();
        checkOutPageStepTwo.clickOnFinishButton();
        Thread.sleep(2000);
        checkOutCompletePage.clickHomeButton();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");


    }
}
