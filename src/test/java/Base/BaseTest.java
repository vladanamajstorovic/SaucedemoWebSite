package Base;

import Page.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

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

        @BeforeClass
        public void setUp() {
            WebDriverManager.edgedriver().setup();
        }



}
