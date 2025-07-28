package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static Base.BaseTest.driver;

public class CheckOutPageStepTwo {

    public CheckOutPageStepTwo() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(className = "cart_item")
    public WebElement cartItem;

    @FindBy(id = "inventory_item_desc")
    public WebElement inventoryItemDescription;

    @FindBy(className = "inventory_item_price")
    public WebElement itemPrice;


    @FindBy(className = "summary_subtotal_label")
    public WebElement summarySubtotalLabel;

    @FindBy(className = "summary_tax_label")
    public WebElement summaryTaxLabel;

    @FindBy(className = "summary_total_label")
    public WebElement summaryTotal;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> priceElements;


    public void clickOnFinishButton() {
        finishButton.click();
    }

    public void clickOnCancel() {
        cancelButton.click();
    }


    public double itemAmountWithoutTax() {

        //ITEM AMOUNT WITHOUT TAX
        double calculatedTotal1 = 0.0;


        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText();
            String numericPart = priceText.replaceAll("[^\\d.]", "").trim();
            double price = Double.parseDouble(numericPart);
            calculatedTotal1 += price;
        }

        double calculatedAmountTotal = Math.round(calculatedTotal1 * 100.0) / 100.0;

        return calculatedAmountTotal;


    }

    public double displayedTotalAmountWithoutTax() {

        String totalText = summarySubtotalLabel.getText();
        String totalNumeric = totalText.replaceAll("[^\\d.]", "").trim();
        double displayedTotal = Double.parseDouble(totalNumeric);


        return displayedTotal;
    }


    public double calculatedTaxAmount() {

        double calculatedTax = itemAmountWithoutTax() * 0.08;
        double roundedTax = Math.round(calculatedTax * 100.0) / 100.0;

        return roundedTax;

    }


    public double displayedTaxAmount() {


        String totalText1 = summaryTaxLabel.getText();
        String totalNumeric1 = totalText1.replaceAll("[^\\d.]", "").trim();
        double displayedTaxTotal = Double.parseDouble(totalNumeric1);

        return displayedTaxTotal;

    }

    public double calculatedTotal() {

        Double calculatedAmount = itemAmountWithoutTax() + calculatedTaxAmount();
        double calculatedTotalAmount = Math.round(calculatedAmount * 100.0) / 100.0;


        return calculatedTotalAmount;
    }


    public double displayedTotal() {

        String totalText2 = summaryTotal.getText();
        String totalNumeric2 = totalText2.replaceAll("[^\\d.]", "").trim();
        double displayedTotalAmount = Double.parseDouble(totalNumeric2);

        return displayedTotalAmount;
    }
}
















