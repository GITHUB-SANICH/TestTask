package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.PropertyReader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class YouCardPage extends BasePage {
    @FindBy(xpath = "//div[@class='bm-burger-button']/button[@id='react-burger-menu-btn']")
    WebElement burgerButton;
    @FindBy(xpath = "//div[@class='bm-menu-wrap']/div[@class='bm-menu']/nav/a")
    List<WebElement> navItemList;
    @FindBy(xpath = "//div[@class='bm-menu-wrap']/div[@class='bm-menu']/nav/a[@id='logout_sidebar_link']")
    WebElement logoutButton;
    @FindBy(xpath = "//span[@class='title']")
    private WebElement titlePage;
    @FindBy(xpath = "//span[@class='title']| //*[@id='cart_contents_container']//child::div[@class='cart_quantity_label' ] |//div[@class='cart_desc_label']")
    private List<WebElement> listOfHeaders;
    @FindBy(xpath = "//div[@id='cart_contents_container']//div[@class='cart_item_label']/a")
    private List<WebElement> listOfItemNames;
    @FindBy(xpath = "//div[@id='cart_contents_container']//div[@class='cart_item_label']/div[@class='inventory_item_desc']")
    private List<WebElement> listOfDescs;
    @FindBy(xpath = "//div[@id='cart_contents_container']//div[@class='cart_item_label']//div[@class='inventory_item_price']")
    private List<WebElement> listOfItemPrices;
    @FindBy(xpath = "//div[@class='cart_item']//div[@class='cart_item_label']//button")
    private List<WebElement> productBlockButtons;

    public void checkingThePageTitle(String expectedTitle) {
        assertThat(titlePage.getText())
                .as("The header with the name \"" + expectedTitle + "\" was not found!").isEqualToIgnoringCase(expectedTitle);
        assertThat(titlePage.isDisplayed())
                .as("The title with the name \"" + expectedTitle + "\" not displayed!").isTrue();
    }

    public void checkingHeaderList(String[] expectedHeaderList) {
        for (String header : expectedHeaderList) {
            assertThat(listOfHeaders.stream().map(WebElement::getText).collect(Collectors.toList()).toString())
                    .as("The header with the name \"" + header + "\" was not found!").containsIgnoringCase(header);
        }
    }

    public void checkingForProductParametersMatches() {
        String[] paramArray = new String[0];
        for (int i = 0; i < listOfItemNames.size(); i++) {
            paramArray = new String[]{
                    listOfItemNames.get(i).getText(),
                    listOfDescs.get(i).getText(),
                    listOfItemPrices.get(i).getText()};
        }
        String paramsFromBasketPage = Arrays.toString(paramArray);
        String expectedParamsFromProductPage = PropertyReader.getParamsItemsList("paramStringForProductPage");
        PropertyReader.deleteParamsItemsList("paramStringForProductPage");

        assertThat(paramsFromBasketPage)
                .as("The parameters of the selected products do not match the parameters of the products in the shopping cart.").isEqualTo(expectedParamsFromProductPage);
    }

    public void pressToButtonRemoveAndCheckingIsItemCount(String buttonName) {
        assertThat(productBlockButtons.get(0).getText())
                .as("The button named \"" + buttonName + "\" was not found!!").isEqualToIgnoringCase(buttonName);
        int startCountButtonRemove = productBlockButtons.size();
        productBlockButtons.get(0).click();
        assertThat(productBlockButtons.size())
                .as("The number of items in the basket has not changed!").isNotEqualTo(startCountButtonRemove);
    }

    public void clickForLogout() {
        if (!navItemList.get(navItemList.size() - 1).isDisplayed()) {
            burgerButton.click();
            wait.until(ExpectedConditions.visibilityOfAllElements(navItemList.get(navItemList.size() - 1)));
        }
        logoutButton.click();
    }
}


