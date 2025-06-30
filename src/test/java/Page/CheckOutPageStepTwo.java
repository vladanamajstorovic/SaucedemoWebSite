package Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Base.BaseTest.driver;

public class CheckOutPageStepTwo {

    public CheckOutPageStepTwo() {
        PageFactory.initElements(driver, this);}

    @FindBy (id = "cancel")
    public WebElement cancelButton;

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(className = "cart_item")
    public WebElement cartItem;

    @FindBy(id = "inventory_item_desc")
    public WebElement inventoryItemDescription;

    @FindBy(className = "inventory_item_price")
    public WebElement itemPrice;


    @FindBy(className="summary_subtotal_label")
    public WebElement summarySubtotalLabel;

    @FindBy(className="summary_tax_label")
    public WebElement summaryTaxLabel;

    @FindBy(className="summary_total_label")
    public WebElement summaryTotal;





  public void clickOnFinishButton(){
      finishButton.click();
  }

    public void clickOnCancel(){
        cancelButton.click();
    }





    }












