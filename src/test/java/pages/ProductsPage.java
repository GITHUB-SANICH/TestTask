package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.PropertyReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsPage extends BasePage {

    @FindBy(xpath = "//div[@class='bm-burger-button']/button[@id='react-burger-menu-btn']")
    WebElement burgerButton;
    @FindBy(xpath = "//div[@class='bm-menu-wrap']/div[@class='bm-menu']/nav/a")
    List<WebElement> navItemList;
    @FindBy(xpath = "//div[@class='bm-menu-wrap']/div[@class='bm-menu']/nav/a[@id='logout_sidebar_link']")
    WebElement logoutButton;
    @FindBy(xpath = "//span[@class='title']")
    private WebElement titlePage;
    @FindBy(xpath = "//*[@id='shopping_cart_container']/a")
    private WebElement shoppingCartButton;
    @FindBy(xpath = "//*[@id='shopping_cart_container']/a/span[@class='shopping_cart_badge']")
    private WebElement counterForShoppingCartButton;
    @FindBy(xpath = "//*[@id='header_container']//select[@class='product_sort_container']")
    private WebElement sortButton;
    @FindBy(xpath = "//*[@id='inventory_container']//div[@class='inventory_item']//div[@class='pricebar']/button")
    private List<WebElement> productBlockButtons;
    @FindBy(xpath = "//*[@id='inventory_container']//div[@class='inventory_item']//div[@class='inventory_item_label']/a")
    private List<WebElement> listOfItemNames;


    public ProductsPage() {
        super();
    }

    public void checkingThePageTitle(String expectedTitle) {
        assertThat(titlePage.getText())
                .as("The header with the name \"" + expectedTitle + "\" was not found!").isEqualToIgnoringCase(expectedTitle);
        assertThat(titlePage.isDisplayed())
                .as("The title with the name \"" + expectedTitle + "\" not displayed!").isTrue();
    }

    public void checkingTheActiveShopBasket() {
        assertThat(shoppingCartButton.isDisplayed() & shoppingCartButton.isEnabled())
                .as("This button has not been displayed or this button is not active!").isTrue();
    }

    public void checkTheSortButton(String expectedOptionName) {
        assertThat(sortButton.findElement(By.xpath("./option[text()='" + expectedOptionName + "']")).getText())
                .as("The option named \"" + expectedOptionName + "\" was not found!!").isEqualTo(expectedOptionName);
        assertThat(sortButton.findElement(By.xpath("./option[text()='" + expectedOptionName + "']")).isDisplayed())
                .as("The button named \"" + expectedOptionName + "\" not displayed!").isTrue();
    }

    public void checkingTheDisplayOfProductsInAlphabeticalOrder() {
        List<String> listInAlphabeticalOrder = listOfItemNames.stream().map(WebElement::getText).sorted().collect(Collectors.toList());
        for (int i = 0; i < listOfItemNames.size(); i++) {
            assertThat(listOfItemNames.get(i).getText())
                    .as("The listNav of products is not sorted alphabetically!").isEqualTo(listInAlphabeticalOrder.get(i));
        }
    }

    public void checkingTheActiveButtonAndName(String expectedButtonName) {
        for (WebElement buttonForProductBlock : productBlockButtons) {
            assertThat(buttonForProductBlock.getText())
                    .as("The button named \"" + expectedButtonName + "\" was not found!").isEqualToIgnoringCase(expectedButtonName);
            assertThat(buttonForProductBlock.isEnabled())
                    .as("This button is not active!").isTrue();
        }
    }

    public void pressAllButtonsAddToCart(String buttonName) {
        for (int i = 0; i < productBlockButtons.size(); i++) {
            assertThat(productBlockButtons.get(i).getText())
                    .as("The button named \"" + buttonName + "\" was not found!").isEqualToIgnoringCase(buttonName);
            productBlockButtons.get(i).click();
            assertThat(Integer.decode(counterForShoppingCartButton.getText()))
                    .as("The number of clicks and items in the shopping cart does not match!").isEqualTo(i + 1);
        }
    }

    public void pressAllButtonsRemove(String buttonName) {
        for (int i = 0, count = productBlockButtons.size(); 0 == count; count--, i++) {
            assertThat(productBlockButtons.get(i).getText())
                    .as("The button named \"" + buttonName + "\" was not found!").isEqualToIgnoringCase(buttonName);
            productBlockButtons.get(i).click();
            assertThat(Integer.decode(counterForShoppingCartButton.getText()))
                    .as("The number of clicks and items in the shopping cart does not match!").isEqualTo(count - 1);
        }
    }

    public void pressAllButtonsNameZToA(String buttonName) {
        assertThat(sortButton.findElement(By.xpath("./option[text()='" + buttonName + "']")).getText())
                .as("The button named \"" + buttonName + "\" was not found!!").isEqualTo(buttonName);
        sortButton.findElement(By.xpath("./option[text()='" + buttonName + "']")).click();

        List<String> listingIsNotAlphabeticalOrder = listOfItemNames.stream().map(WebElement::getText).sorted(Collections.reverseOrder()).collect(Collectors.toList());
        for (int i = 0; i < listOfItemNames.size(); i++) {
            assertThat(listOfItemNames.get(i).getText())
                    .as("The listNav of products is sorted alphabetically!").isEqualTo(listingIsNotAlphabeticalOrder.get(i));
        }
    }

    public void checkingTheMenuForActiveItems(String[] expectedListElements) {
        burgerButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(navItemList.get(navItemList.size() - 1)));
        for (int i = 0; i < navItemList.size(); i++) {
            assertThat(navItemList.stream().map(WebElement::getText).collect(Collectors.toList()).toString())
                    .as("A link named \"" + expectedListElements[i] + "\" was not found!").containsIgnoringCase(expectedListElements[i]);
            assertThat(navItemList.get(i).isEnabled()).as("The field named \"" + expectedListElements[i] + "\" was not enabled!").isTrue();
        }
    }

    public void clickToButtonAddToCart(String buttonName, int buttonOrder) {
        assertThat(productBlockButtons.get(buttonOrder - 1).getText())
                .as("The button named \"" + buttonName + "\" was not found!!").isEqualToIgnoringCase(buttonName);
        productBlockButtons.get(buttonOrder - 1).click();
    }

    public void checkingTheCounter(String expectedCounterValue) {
        assertThat(counterForShoppingCartButton.getText())
                .as("The number of clicks and items in the shopping cart does not match!").isEqualTo(expectedCounterValue);
    }

    public void checkingTheButtonName(String expectedButtonName, int buttonOrder) {
        assertThat(productBlockButtons.get(buttonOrder - 1).getText())
                .as("The expected (\"" + expectedButtonName + "\") and actual name of the button do not match!").isEqualToIgnoringCase(expectedButtonName);
    }

    public void creatingListInfoAboutSelectedItems(String desiredButton) {
        String[] arrayString;
        for (WebElement button : productBlockButtons) {
            if (button.getText().equalsIgnoreCase(desiredButton)) {
                WebElement parent = button.findElement(By.xpath("./parent::div//parent::div[@class='inventory_item_description']"));
                String itemTitle = parent.findElement(By.xpath("./div[@class='inventory_item_label']/a")).getText();
                String itemDesc = parent.findElement(By.xpath(".//div[@class='inventory_item_desc']")).getText();
                String itemPrice = parent.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText();
                arrayString = new String[]{itemTitle, itemDesc, itemPrice};
                PropertyReader.setParamsItemsList("paramStringForProductPage", Arrays.toString(arrayString), false);
            }
        }
    }

    public void clickForShoppingCartButton() {
        shoppingCartButton.click();
    }

    public void clickForLogout() {
        if (!navItemList.get(navItemList.size() - 1).isDisplayed()) {
            burgerButton.click();
            wait.until(ExpectedConditions.visibilityOfAllElements(navItemList.get(navItemList.size() - 1)));
        }
        logoutButton.click();
    }
}
