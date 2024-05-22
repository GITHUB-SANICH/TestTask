package steps;

import io.qameta.allure.Step;
import pages.ProductsPage;

public class ProductsSteps {
    private final ProductsPage productsPage = new ProductsPage();

    @Step("Проверить страницу \"PRODUCTS\"")
    public ProductsSteps checkTheProductsPage(String expectedTitle, String expectedOptionName, String expectedButtonName) {
        productsPage.checkingThePageTitle(expectedTitle);
        productsPage.checkingTheActiveShopBasket();
        productsPage.checkTheSortButton(expectedOptionName);
        productsPage.checkingTheDisplayOfProductsInAlphabeticalOrder();
        productsPage.checkingTheActiveButtonAndName(expectedButtonName);
        return this;
    }

    @Step("Нажать на \"ADD TO CART\" у всех товаров")
    public ProductsSteps pressAllButtonsForItemsAddToCart(String buttonName) {
        productsPage.pressAllButtonsAddToCart(buttonName);
        return this;
    }

    @Step("Нажать на \"REMOVE\" у всех товаров")
    public ProductsSteps pressAllButtonsForItemsRemove(String buttonName) {
        productsPage.pressAllButtonsRemove(buttonName);
        return this;
    }

    @Step("Перейти в фильтры и нажать на сортировку \"NAME Z TO A\"")
    public ProductsSteps goToTheFiltersAndClickOnTheSortNameZToA(String buttonName) {
        productsPage.pressAllButtonsNameZToA(buttonName);
        return this;
    }

    @Step("Нажать на кнопку вызова меню в левом верхнем углу")
    public ProductsSteps clickOnTheMenuButtonInTheUpperLeftCorner(String[] expectedListElements) {
        productsPage.checkingTheMenuForActiveItems(expectedListElements);
        return this;
    }

    @Step("Нажать на \"ADD TO CART\" на любом товаре")
    public ProductsSteps pressOnAddToCartOnAnyProduct(String buttonName, int buttonOrder, String expectedButtonName, String expectedCounterValue) {
        productsPage.clickToButtonAddToCart(buttonName, buttonOrder);
        productsPage.checkingTheCounter(expectedCounterValue);
        productsPage.checkingTheButtonName(expectedButtonName, buttonOrder);
        return this;
    }

    @Step("Нажать на иконку с корзиной")
    public YouCardSteps clickOnTheBasketIcon(String desiredButton) {
        productsPage.creatingListInfoAboutSelectedItems(desiredButton);
        productsPage.clickForShoppingCartButton();
        return new YouCardSteps();
    }

    @Step("Нажать кнопку LOGOUT")
    public AuthorizationSteps clickTheLogoutButton() {
        productsPage.clickForLogout();
        return new AuthorizationSteps();
    }
}
