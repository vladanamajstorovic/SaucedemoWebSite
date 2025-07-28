package Test;

import Base.BaseTest;
import Base.ExcelDataProvider;
import Base.ExcelReader;
import Page.LoginPage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.io.IOException;
import java.time.Duration;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        loginPage=new LoginPage();
        excelReader= new ExcelReader("C:\\Users\\vmvma\\Downloads\\Users.xlsx");

}


@Test (priority = 1 )
public void loginAndLogoutWithCorrectUsernameAndPassword() {

    for (int i = 1; i <excelReader.getLastRow("Sheet2"); i++) {


        String username = excelReader.getStringData("Sheet2", i, 0);
        String password = excelReader.getStringData("Sheet2", i, 1);
        loginPage.addUsername(username);
        loginPage.addPassword(password);
        loginPage.clickOnLoginButton();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(loginPage.cart.isDisplayed());
        Assert.assertTrue(loginPage.articlePicture.isDisplayed());
        Assert.assertTrue(loginPage.itemPrice.isDisplayed());

        //LOGOUT
        loginPage.clickOnBurgerMenu();
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.logoutButton));
        loginPage.clickOnLogoutButton();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));

        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");


    }

}

@Test (priority = 2)
    public void loginWithLockedOutUser(){
    wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));
    wait.until(ExpectedConditions.visibilityOf(loginPage.usernameField));
    loginPage.addUsername("locked_out_user");
    loginPage.addPassword("secret_sauce");
    loginPage.clickOnLoginButton();
    Assert.assertTrue(loginPage.error.isDisplayed());
    Assert.assertTrue(loginPage.errorButton.isDisplayed());
    Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");

}





// LOGIN WITH DATA PROVIDER

    @Test (dataProvider = "excelUsers", dataProviderClass = ExcelDataProvider.class)
    public void loginWithValidUsers(String username, String password) throws InterruptedException {
        loginPage.addUsername(username);
        loginPage.addPassword(password);
        loginPage.clickOnLoginButton();

        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
        wait.until(ExpectedConditions.visibilityOf(loginPage.cart));
        wait.until(ExpectedConditions.visibilityOf(loginPage.articlePicture));
        wait.until(ExpectedConditions.visibilityOf(loginPage.itemPrice));


        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(loginPage.cart.isDisplayed(), "Cart icon not visible");
        Assert.assertTrue(loginPage.articlePicture.isDisplayed(), "Product image not visible");
        Assert.assertTrue(loginPage.itemPrice.isDisplayed(), "Price not visible");
        Thread.sleep(2000);
        //LOGOUT
        loginPage.clickOnBurgerMenu();
        Thread.sleep(2000);
        loginPage.clickOnLogoutButton();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");

}


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
