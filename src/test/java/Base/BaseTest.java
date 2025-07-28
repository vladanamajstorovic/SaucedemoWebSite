package Base;

import Page.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class BaseTest {

        public static WebDriver driver;
        public WebDriverWait wait;
        public LoginPage loginPage;
        public InventoryPage inventoryPage;
        public CartPage cartPage;
        public ExcelReader excelReader;
        public CheckOutPage checkOutPage;
        public CheckOutPageStepTwo checkOutPageStepTwo;
        public CheckOutCompletePage checkOutCompletePage;
        public BurgerMenu burgerMenu;
        public ExcelDataProvider excelDataProvider;
        public SoftAssert softAssert;
        public InventoryItemPage inventoryItemPage;


        @BeforeClass
        public void setUp() {
            WebDriverManager.chromedriver().setup();
        }



}
