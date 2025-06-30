package Test;

import Base.BaseTest;
import Page.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void finishOrderWhenCartIsFull() throws InterruptedException {
        inventoryPage.clickAddToCartButton();
        Thread.sleep(2000);
        inventoryPage.clickAddToCartButton();
        Thread.sleep(2000);
        inventoryPage.clickCartButton();
        cartPage.clickOnCheckoutTab();
        checkOutPage.addFirstName("Milena");
        checkOutPage.addLastName("Milic");
        checkOutPage.addPostalCode("11000");
        Thread.sleep(2000);
        checkOutPage.clickContinueButton();
        checkOutPageStepTwo.clickOnFinishButton();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-complete.html");
        Assert.assertEquals(checkOutCompletePage.header.getText(),"Thank you for your order!");
        Assert.assertTrue(checkOutCompletePage.backHomeButton.isDisplayed());

    }

    @Test
    public void totalPriceCounterWorksCorrectlyForDifferentCurrencies() throws InterruptedException {

        inventoryPage.clickAddToCartButton();
        Thread.sleep(1000);
        inventoryPage.clickAddToCartButton();
        Thread.sleep(1000);
        inventoryPage.clickAddToCartButton();
        Thread.sleep(1000);
        inventoryPage.clickCartButton();
        Thread.sleep(1000);
        cartPage.clickOnCheckoutTab();
        checkOutPage.addFirstName("Milena");
        checkOutPage.addLastName("Milic");
        checkOutPage.addPostalCode("11000");
        Thread.sleep(2000);
        checkOutPage.clickContinueButton();
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));


        //ITEM AMOUNT WITHOUT TAX
        double calculatedTotal = 0.0;

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText(); // npr "$12.99"
            String numericPart = priceText.replaceAll("[^\\d.]", "").trim(); // "12.99"
            double price = Double.parseDouble(numericPart);
            calculatedTotal += price;
        }

        System.out.println("Suma artikala: $" + calculatedTotal);

        Thread.sleep(2000);
        String totalText = checkOutPageStepTwo.summarySubtotalLabel.getText(); // npr "$25.98"
        String totalNumeric = totalText.replaceAll("[^\\d.]", "").trim();
        double displayedTotal= Double.parseDouble(totalNumeric);

        System.out.println("Suma Total: $" + displayedTotal);

        Assert.assertEquals(displayedTotal, calculatedTotal);


        //TAX AMOUNT
        double calculatedTax=calculatedTotal*0.08;
        System.out.println("Tax: $" + calculatedTax);
        double roundedTax = Math.round(calculatedTax * 100.0) / 100.0;

        String totalText1 = checkOutPageStepTwo.summaryTaxLabel.getText();
        String totalNumeric1 = totalText1.replaceAll("[^\\d.]", "").trim();
        double displayedTaxTotal= Double.parseDouble(totalNumeric1);


        Assert.assertEquals(displayedTaxTotal,roundedTax);


        //TOTAL AMOUNT
       Double calculatedTotalAmount=calculatedTotal+roundedTax;


        String totalText2 = checkOutPageStepTwo.summaryTotal.getText();
        String totalNumeric2 = totalText2.replaceAll("[^\\d.]", "").trim();
        double displayedTotalAmount= Double.parseDouble(totalNumeric2);

        Assert.assertEquals(displayedTotalAmount,calculatedTotalAmount);




    }

}
