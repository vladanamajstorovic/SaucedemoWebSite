package Page;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.*;

public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public WebElement addToCartButton;

    @FindBy(id = "shopping_cart_container")
    public WebElement cart;

    @FindBy(className = "shopping_cart_badge")
    public WebElement cartBadge;

    @FindBy(className = "inventory_item_name")
    public WebElement itemName;

    @FindBy(className = "inventory_item")
    public List<WebElement> inventoryItems;

    @FindBy(className = "product_sort_container")
    public WebElement sortDropdown;

    @FindBy(className = "inventory_item_img")
    public WebElement itemImg;

    @FindBy(className = "inventory_item_desc")
    public WebElement itemDesc;

    @FindBy(className = "pricebar")
    public WebElement priceBar;

    @FindBy(css = ".btn_inventory")
    public List<WebElement> allAddToCartButtons;


    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(className = "inventory_item_desc")
    private List<WebElement> itemDescriptions;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(css = ".pricebar button")
    private List<WebElement> itemButtons;

    @FindBy(css = "button.btn_inventory")
    public List<WebElement> addRemoveButtons;


    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public void clickCartButton() {
        cart.click();
    }


    public List<List<String>> getAllProductInfoAsList() {
        List<List<String>> productData = new ArrayList<>();

        for (WebElement item : inventoryItems) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            String desc = item.findElement(By.className("inventory_item_desc")).getText();
            String price = item.findElement(By.className("inventory_item_price")).getText();
            String imgSrc = item.findElement(By.cssSelector(".inventory_item_img img")).getAttribute("src");

            productData.add(Arrays.asList(name, desc, price, imgSrc));
        }

        return productData;
    }

    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement item : inventoryItems) {
            String priceText = item.findElement(By.className("inventory_item_price")).getText(); // e.g. "$29.99"
            double price = Double.parseDouble(priceText.replace("$", ""));
            prices.add(price);
        }
        return prices;
    }


    public void selectSortOption(String optionText) {
        Select filter = new Select(sortDropdown);
        filter.selectByVisibleText(optionText);
    }

    public List<String> getAllProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement item : inventoryItems) {
            names.add(item.findElement(By.className("inventory_item_name")).getText());
        }
        return names;
    }


    public void addRandomItemsToCart(int numberOfItemsWanted) {
        Set<Integer> randomIndexes = new HashSet<>();
        Random rand = new Random();

        while (randomIndexes.size() < numberOfItemsWanted) {
            randomIndexes.add(rand.nextInt(allAddToCartButtons.size()));
        }

        for (int index : randomIndexes) {
            WebElement button = allAddToCartButtons.get(index);
            button.click();
        }

    }

    public boolean areAllElementsVisiblePerItem() {
        for (WebElement item : inventoryItems) {
            WebElement name = item.findElement(By.className("inventory_item_name"));
            WebElement description = item.findElement(By.className("inventory_item_desc"));
            WebElement price = item.findElement(By.className("inventory_item_price"));
            WebElement button = item.findElement(By.cssSelector(".pricebar button"));

            if (!(name.isDisplayed() && description.isDisplayed() &&
                    price.isDisplayed() && button.isDisplayed())) {
                return false;
            }
        }
        return true;
    }


    public void addAllItemsToCart() {
        for (WebElement button : addRemoveButtons) {
            if (button.getText().equalsIgnoreCase("Add to cart")) {
                button.click();
            }
        }
    }

    public void removeAllItemsFromCart() {
        for (WebElement button : addRemoveButtons) {
            if (button.getText().equalsIgnoreCase("Remove")) {
                button.click();
            }
        }
    }


}
