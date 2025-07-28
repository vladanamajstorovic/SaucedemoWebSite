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

public class InventoryDataProviderTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        burgerMenu = new BurgerMenu();
        excelReader = new ExcelReader("C:\\Users\\vmvma\\Downloads\\Users.xlsx");
        cartPage = new CartPage();

        //loginPage.addUsername("standard_user");
        //loginPage.addPassword("secret_sauce");
        // loginPage.clickOnLoginButton();

    }


    ///  SA DATA PROVIDER ZA TESTIRANJE SVIH KORISNIKA IZ EXCELA SA LOGOUT


    @Test(dataProvider = "excelUsers", dataProviderClass = Base.ExcelDataProvider.class)
    public void testAddToCartForAllUsers(String username, String password) throws InterruptedException {
        Thread.sleep(2000);
        loginPage.addUsername(username);
        loginPage.addPassword(password);
        loginPage.clickOnLoginButton();

        Thread.sleep(2000);
        inventoryPage.addRandomItemsToCart(3);


        Assert.assertTrue(cartPage.removeFromCart.isDisplayed());


    }


    @Test(dataProvider = "excelUsers", dataProviderClass = Base.ExcelDataProvider.class)
    public void allInventoryElementsAreVisibleForAllUsers(String username, String password) throws InterruptedException {
        loginPage.addUsername(username);
        loginPage.addPassword(password);
        loginPage.clickOnLoginButton();

        for (WebElement item : inventoryPage.inventoryItems) {
            wait.until(ExpectedConditions.visibilityOf(item));
            Assert.assertTrue(item.findElement(By.className("inventory_item_img")).isDisplayed());
            Assert.assertTrue(item.findElement(By.className("inventory_item_desc")).isDisplayed());
            Assert.assertTrue(item.findElement(By.className("inventory_item_name")).isDisplayed());
            Assert.assertTrue(item.findElement(By.className("pricebar")).isDisplayed());
        }


    }

    @Test(dataProvider = "excelUsers", dataProviderClass = Base.ExcelDataProvider.class)
    public void compareProductDataForUser(String username, String password) throws InterruptedException {

        loginPage.addUsername("standard_user");
        loginPage.addPassword("secret_sauce");
        loginPage.clickOnLoginButton();

        List<List<String>> standardData = inventoryPage.getAllProductInfoAsList();


        burgerMenu.clickOnBurgerMenuTab();
        Thread.sleep(2000);
        burgerMenu.clickOnLogoutTab();
        Thread.sleep(2000);


        loginPage.addUsername(username);
        loginPage.addPassword(password);
        loginPage.clickOnLoginButton();

        List<List<String>> currentUserData = inventoryPage.getAllProductInfoAsList();

        SoftAssert softAssert = new SoftAssert();


        softAssert.assertEquals(currentUserData.size(), standardData.size(), "Product count mismatch!");

        int minSize = Math.min(currentUserData.size(), standardData.size());
        for (int i = 0; i < minSize; i++) {
            softAssert.assertEquals(currentUserData.get(i), standardData.get(i), "Mismatch at product index " + i);
        }

        System.out.println("Running test for user: " + username);


        softAssert.assertAll();


    }


    @Test(dataProvider = "excelUsers", dataProviderClass = Base.ExcelDataProvider.class)
    public void priceSortingLowToHighForUser(String username, String password) throws InterruptedException {
        loginPage.addUsername(username);
        loginPage.addPassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.selectSortOption("Price (low to high)");
        Thread.sleep(3000);
        List<Double> actualPrices = inventoryPage.getProductPrices();


        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        Thread.sleep(2000);

        Assert.assertEquals(actualPrices, expectedPrices, "Prices are not sorted low to high");
        System.out.println("Running test for user: " + username);

    }


    @Test(dataProvider = "excelUsers", dataProviderClass = Base.ExcelDataProvider.class)
    public void priceSortingHighToLowForUser(String username, String password) {
        loginPage.addUsername(username);
        loginPage.addPassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.selectSortOption("Price (high to low)");
        List<Double> prices = inventoryPage.getProductPrices();

        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());

        Assert.assertEquals(prices, sorted, "Prices are not sorted from high to low.");


    }


    @Test(dataProvider = "excelUsers", dataProviderClass = Base.ExcelDataProvider.class)
    public void testSortByNameAToZForUser(String username, String password) {
        loginPage.addUsername(username);
        loginPage.addPassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.selectSortOption("Name (A to Z)");

        List<String> actualNames = inventoryPage.getAllProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        Assert.assertEquals(actualNames, expectedNames, "Product names are not sorted A to Z.");


    }


    @Test(dataProvider = "excelUsers", dataProviderClass = Base.ExcelDataProvider.class)
    public void testSortByNameZToAForUser(String username, String password) {
        loginPage.addUsername(username);
        loginPage.addPassword(password);
        loginPage.clickOnLoginButton();

        inventoryPage.selectSortOption("Name (Z to A)");
        List<String> actualNames = inventoryPage.getAllProductNames();

        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames, Collections.reverseOrder());

        Assert.assertEquals(actualNames, expectedNames, "Product names are not sorted Z to A.");


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



