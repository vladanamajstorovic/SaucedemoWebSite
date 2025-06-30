package Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Base.BaseTest.driver;

public class CheckOutPage {

    public CheckOutPage() {
        PageFactory.initElements(driver, this);}

    @FindBy(id = "postal-code")
    public WebElement postalCodeField;

    @FindBy(id = "cancel")
    public WebElement cancelTab;


    @FindBy(id = "first-name")
    public WebElement firstNameTab;

    @FindBy(id = "last-name")
    public WebElement lastNameTab;

    @FindBy(id = "continue")
    public WebElement continueTab;

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(className = "cart_item")
    public WebElement cartItem;

    @FindBy(className = "summary_subtotal_label")
    public WebElement summary;


    public void addFirstName(String firstname){
        firstNameTab.sendKeys(firstname);
    }

    public void addLastName(String lastname){
        lastNameTab.sendKeys(lastname);
    }

    public void addPostalCode(String postalcode){
        postalCodeField.sendKeys(postalcode);
    }

    public void clickContinueButton(
    ){
        continueTab.click();
    }

    public void clickOnFinishButton(){
        finishButton.click();
    }

}
