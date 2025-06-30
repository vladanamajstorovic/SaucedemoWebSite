package Test;

import Base.BaseTest;
import Base.ExcelReader;
import Page.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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


@Test
public void loginWithCorrectUsernameAndPassword() throws InterruptedException {

    for (int i = 1; i <excelReader.getLastRow("Sheet2"); i++) {


        String username = excelReader.getStringData("Sheet2", i, 0);
        String password = excelReader.getStringData("Sheet2", i, 1);
        loginPage.addUsername(username);
        loginPage.addPassword(password);
        loginPage.clickOnLoginButton();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(loginPage.cart.isDisplayed());
        Assert.assertTrue(loginPage.articlePicture.isDisplayed());
        Assert.assertTrue(loginPage.itemPrice.isDisplayed());

        //LOGOUT
        loginPage.clickOnBurgerMenu();
        Thread.sleep(2000);
        loginPage.clickOnLogoutButton();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");

       // driver.navigate().to("https://www.saucedemo.com/");

    }

}

@Test
    public void loginWithLockedOutUser(){
    loginPage.addUsername("locked_out_user");
    loginPage.addPassword("secret_sauce");
    loginPage.clickOnLoginButton();
    Assert.assertTrue(loginPage.error.isDisplayed());
    Assert.assertTrue(loginPage.errorButton.isDisplayed());
    Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/");

}


}
