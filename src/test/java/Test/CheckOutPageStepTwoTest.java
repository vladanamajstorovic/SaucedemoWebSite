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


import static Base.BaseTest.driver;

public class CheckOutPageStepTwoTest extends BaseTest {

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
    public void finishOrderWhenCartIsEmpty() throws InterruptedException {
    inventoryPage.clickCartButton();
    cartPage.clickOnCheckoutTab();
    checkOutPage.addFirstName("Milena");
    checkOutPage.addLastName("Milic");
    checkOutPage.addPostalCode("11000");
    Thread.sleep(2000);
    checkOutPage.clickContinueButton();
    checkOutPageStepTwo.clickOnFinishButton();
    Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-complete.html");
    Assert.assertEquals(checkOutCompletePage.header.getText(),"Thank you for your order!");
    Assert.assertTrue(checkOutCompletePage.backHomeButton.isDisplayed());

}

    @Test (priority = 1)
    public void finishOrderWhenCartIsFull() throws InterruptedException {
        inventoryPage.addRandomItemsToCart(4);
        inventoryPage.clickCartButton();
        cartPage.clickOnCheckoutTab();
        checkOutPage.addFirstName("Milena");
        checkOutPage.addLastName("Milic");
        checkOutPage.addPostalCode("11000");
        wait.until(ExpectedConditions.elementToBeClickable(checkOutPage.continueTab));
        checkOutPage.clickContinueButton();
        checkOutPageStepTwo.clickOnFinishButton();
        wait.until(ExpectedConditions.elementToBeClickable(checkOutCompletePage.backHomeButton));
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-complete.html");
        Assert.assertEquals(checkOutCompletePage.header.getText(),"Thank you for your order!");
        Assert.assertTrue(checkOutCompletePage.backHomeButton.isDisplayed());

    }

    @Test (priority = 1)
    public void totalPriceCounterWorksCorrectlyForDifferentCurrencies() throws InterruptedException {

        inventoryPage.addRandomItemsToCart(4);
        inventoryPage.clickCartButton();
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.checkoutTab));
        cartPage.clickOnCheckoutTab();
        checkOutPage.addFirstName("Milena");
        checkOutPage.addLastName("Milic");
        checkOutPage.addPostalCode("11000");
        Thread.sleep(2000);
        checkOutPage.clickContinueButton();


        //ITEM AMOUNT WITHOUT TAX
        checkOutPageStepTwo.itemAmountWithoutTax();
        System.out.println("Calculated amount without TAX: $" + checkOutPageStepTwo.itemAmountWithoutTax());

        //ITEM DISPLAYED TOTAL
        checkOutPageStepTwo.displayedTotalAmountWithoutTax();
        System.out.println("Displayed amount without TAX: $" + checkOutPageStepTwo.displayedTotalAmountWithoutTax());


        Assert.assertEquals(checkOutPageStepTwo.displayedTotalAmountWithoutTax(), checkOutPageStepTwo.itemAmountWithoutTax());


        //TAX AMOUNT
       checkOutPageStepTwo.calculatedTaxAmount();
        System.out.println("Calculated TAX: $" + checkOutPageStepTwo.calculatedTaxAmount());

        checkOutPageStepTwo.displayedTaxAmount();
        System.out.println("Displayed TAX: $" +checkOutPageStepTwo.displayedTaxAmount());


        Assert.assertEquals(checkOutPageStepTwo.displayedTaxAmount(),checkOutPageStepTwo.calculatedTaxAmount());


        //TOTAL AMOUNT
        checkOutPageStepTwo.displayedTotal();
        System.out.println("Displayed total including TAX: $" + checkOutPageStepTwo.displayedTotal());

        checkOutPageStepTwo.calculatedTotal();
        System.out.println("Calculated Total including TAX: $" + checkOutPageStepTwo.calculatedTotal());



        Assert.assertEquals(checkOutPageStepTwo.displayedTotal(),checkOutPageStepTwo.calculatedTotal());




    }

    @AfterMethod
    public void driverClose() {
        driver.close();
    }

}
