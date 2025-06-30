package Test;

import Base.BaseTest;
import Base.ExcelReader;
import Page.InventoryPage;
import Page.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class InventoryTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        loginPage=new LoginPage();
        inventoryPage=new InventoryPage();

        loginPage.addUsername("standard_user");
        loginPage.addPassword("secret_sauce");
        loginPage.clickOnLoginButton();

    }


@Test
    public void addToCart() throws InterruptedException {
        Thread.sleep(2000);
        inventoryPage.clickAddToCartButton();
        Thread.sleep(2000);
        inventoryPage.clickAddToCartButton();
        Thread.sleep(2000);
        inventoryPage.clickAddToCartButton();
        Thread.sleep(2000);
        inventoryPage.clickCartButton();
    Assert.assertTrue(cartPage.removeFromCart.isDisplayed());
}



}


