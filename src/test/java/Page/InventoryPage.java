package Page;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BaseTest{

        public InventoryPage() {
            PageFactory.initElements(driver, this);
        }

        @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
        public WebElement addToCartButton;

        @FindBy(id = "shopping_cart_container")
        public WebElement cart;




        public void clickAddToCartButton(){
            addToCartButton.click();
        }

        public void clickCartButton(){
            cart.click();
        }


}
