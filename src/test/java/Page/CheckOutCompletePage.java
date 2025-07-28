package Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Base.BaseTest.driver;

public class CheckOutCompletePage {
    public CheckOutCompletePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "complete-header")
    public WebElement header;

    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;

    public void clickHomeButton() {
        backHomeButton.click();
    }


}
