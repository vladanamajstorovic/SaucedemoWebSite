package Page;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BurgerMenu extends BaseTest {
    public BurgerMenu() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenuTab;

    @FindBy(id = "react-burger-cross-btn")
    public WebElement crossButton;

    @FindBy(className = "bm-menu-wrap")
    public WebElement burgerMenuWrap;

    @FindBy(id = "inventory_sidebar_link")
    public WebElement allItems;

    @FindBy(id = "about_sidebar_link")
    public WebElement aboutTab;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logutTab;

    @FindBy(id = "reset_sidebar_link")
    public WebElement resetTab;


    public void clickOnBurgerMenuTab() {
        burgerMenuTab.click();
    }

    public void clickOnAllItems() {
        allItems.click();
    }

    public void clickOnAboutTab() {
        aboutTab.click();
    }

    public void clickOnLogoutTab() {
        logutTab.click();
    }

    public void clickOnResetTab() {
        resetTab.click();
    }
}
