package Page;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver, this);}

        @FindBy (css = ".btn.btn_secondary.btn_small.cart_button")
        public WebElement removeFromCart;

        @FindBy (className= "shopping_cart_badge")
        public WebElement cartBadge;

        @FindBy(className = "cart_quantity")
        public WebElement cartQuantity;

    @FindBy(id = "continue-shopping")
     public WebElement continueShoppingTab;

    @FindBy (id = "checkout")
    public WebElement checkoutTab;




        public void clickRemoveFromCart(){
            removeFromCart.click();
        }


      public  void clickContinueShoppingTab(){
            continueShoppingTab.click();
      }


      public void clickOnCheckoutTab(){
            checkoutTab.click();
      }

    }

