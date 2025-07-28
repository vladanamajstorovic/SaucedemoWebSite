package Page;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItemPage extends BaseTest {
    public InventoryItemPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_details_name.large_size")
    public WebElement itemName;

    @FindBy(css = ".inventory_details_desc.large_size")
    public WebElement itemDesc;

    @FindBy(className = "inventory_details_price")
    public WebElement itemPrice;

    @FindBy(id = "add-to-cart")
    public WebElement addToCartButton;

    @FindBy(id = "remove")
    public WebElement removeButton;

    @FindBy(className = "inventory_details_img")
    public WebElement itemImg;

    @FindBy(className = "shopping_cart_link")
    public WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    public WebElement cartBadge;

    @FindBy(id = "back-to-products")
    public WebElement backToProductsButton;


    public void clickOnBackToProductsButton() {
        backToProductsButton.click();
    }

    public void getTextFromCartBadge() {
        String numberOfCartItems = cartBadge.getText();
    }


    public void clickAddToCart() {
        addToCartButton.click();
    }

    public void clickRemoveFromCart() {
        removeButton.click();
    }

    public void clickCartIcon() {
        cartIcon.click();
    }




}
