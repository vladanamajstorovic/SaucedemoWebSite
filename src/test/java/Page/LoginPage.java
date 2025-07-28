package Page;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage extends BaseTest {

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    public WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;


    @FindBy(id= "login-button")
    public WebElement loginButton;

    @FindBy(id = "shopping_cart_container")
    public WebElement cart;

    @FindBy(id = "item_4_img_link")
    public WebElement articlePicture;

    @FindBy(className = "inventory_item_price")
    public WebElement itemPrice;

    @FindBy(css = ".error-message-container.error")
    public WebElement error;

    @FindBy(css = ".svg-inline--fa.fa-times-circle.fa-w-16.error_icon")
    public WebElement errorButton;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenu;



    public void addUsername(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void addPassword(String password){
        usernameField.clear();
        passwordField.sendKeys(password);
    }

    public void  clickOnLoginButton(){
        loginButton.click();
    }

    public void clickOnLogoutButton(){
        logoutButton.click();
    }

    public void clickOnBurgerMenu(){
        burgerMenu.click();
    }


}
