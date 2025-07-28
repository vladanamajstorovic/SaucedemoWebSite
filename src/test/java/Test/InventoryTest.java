package Test;

import Base.BaseTest;
import Base.ExcelReader;
import Page.BurgerMenu;
import Page.CartPage;
import Page.InventoryPage;
import Page.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        burgerMenu = new BurgerMenu();
        excelReader = new ExcelReader("C:\\Users\\vmvma\\Downloads\\Users.xlsx");
        cartPage = new CartPage();
        softAssert = new SoftAssert();
        loginPage.addUsername("standard_user");
        loginPage.addPassword("secret_sauce");
        loginPage.clickOnLoginButton();

    }


    @Test(priority = 1)
    public void addToCart() {
        wait.until(ExpectedConditions.visibilityOf(inventoryPage.addToCartButton));
        inventoryPage.clickAddToCartButton();
        Assert.assertTrue(cartPage.removeFromCart.isDisplayed());
    }


    @Test(priority = 2)
    public void allInventoryElementsAreVisible() {

        wait.until(ExpectedConditions.visibilityOf(inventoryPage.itemName));
        Assert.assertTrue(inventoryPage.areAllElementsVisiblePerItem(), "Not all item elements are visible.");


    }


    @Test(priority = 4)
    public void compareProductDataAcrossUsers() throws InterruptedException {


        List<List<String>> standardData = inventoryPage.getAllProductInfoAsList();
        Thread.sleep(2000);
        // Logout
        burgerMenu.clickOnBurgerMenuTab();
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu.logutTab));
        burgerMenu.clickOnLogoutTab();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));

        // LOGIN AS SECOND USER
        loginPage.addUsername("problem_user");
        loginPage.addPassword("secret_sauce");
        loginPage.clickOnLoginButton();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));

        List<List<String>> problemData = inventoryPage.getAllProductInfoAsList();
        Thread.sleep(2000);


        softAssert.assertEquals(problemData.size(), standardData.size(), "Product count mismatch.");
        Thread.sleep(2000);


        for (int i = 0; i < standardData.size(); i++) {
            List<String> expected = standardData.get(i);
            List<String> actual = problemData.get(i);
            Thread.sleep(2000);
            softAssert.assertEquals(actual, expected, "Mismatch at product index " + i);
        }

        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void priceSortingLowToHigh() {

        inventoryPage.selectSortOption("Price (low to high)");

        List<Double> actualPrices = inventoryPage.getProductPrices();

        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices, "Prices are not sorted low to high");
    }


    @Test(priority = 4)
    public void priceSortingHighToLow() {
        inventoryPage.selectSortOption("Price (high to low)");
        List<Double> prices = inventoryPage.getProductPrices();

        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());

        Assert.assertEquals(prices, sorted, "Prices are not sorted from high to low.");
    }

    @Test(priority = 4)
    public void testSortByNameAToZ() {
        inventoryPage.selectSortOption("Name (A to Z)");

        List<String> actualNames = inventoryPage.getAllProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        Assert.assertEquals(actualNames, expectedNames, "Product names are not sorted A to Z.");
    }

    @Test(priority = 4)
    public void testSortByNameZToA() {

        inventoryPage.selectSortOption("Name (Z to A)");
        List<String> actualNames = inventoryPage.getAllProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames, Collections.reverseOrder());

        Assert.assertEquals(actualNames, expectedNames, "Product names are not sorted Z to A.");
    }


    @Test(priority = 1)
    public void testAddAndRemoveAllItems() {
        inventoryPage.addAllItemsToCart();
        int cartCount = Integer.parseInt(inventoryPage.cartBadge.getText());
        Assert.assertEquals(inventoryPage.addRemoveButtons.size(), cartCount);


        inventoryPage.removeAllItemsFromCart();
        Assert.assertEquals(inventoryPage.addToCartButton.getText(), "Add to cart");

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






