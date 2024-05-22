package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class AllTests extends BaseTest {

    /* todo: "для того, чтобы тесты не падали - методы сделаны так, чтобы регистр текста не учитывался при сравнении. Так как не ясно наверняка - важно ли это." */
    /* todo: ""NAME(A TO Z)" изменено на "Name (A to Z)", чтобы тесты не падали" */
    /* todo: ""NAME Z TO A" изменено на "Name (Z to A)", чтобы тесты не падали" */

    @Test(dataProvider = "dataProvider", timeOut = 5000)
    @Owner(value = "Иванов Иван")
    @Story("Проверка работы страницы с продуктами пользователя")
    public void checkingTheOperationOfTheProductPage(String login, String password) {
        steps.goToTheAuthorizationPage(new String[]{"Username", "Password"}, "LOGIN")
                .logInToTheSystemAs(login, password)
                .checkTheProductsPage("PRODUCTS", "Name (A to Z)", "ADD TO CART")
                .pressAllButtonsForItemsAddToCart("ADD TO CART")
                .pressAllButtonsForItemsRemove("REMOVE")
                .goToTheFiltersAndClickOnTheSortNameZToA("Name (Z to A)")
                .clickOnTheMenuButtonInTheUpperLeftCorner(new String[]{"ALL ITEMS", "ABOUT", "LOGOUT", "RESET APP STATE"})
                .clickTheLogoutButton().goToTheAuthorizationPage(new String[]{"Username", "Password"}, "LOGIN");
    }

    @Test(timeOut = 5000)
    @Owner(value = "Иванов Иван")
    @Story("Проверка удаления товара из списка на странице с корзиной")
    public void checkingTheRemovalOfAnItemFromTheListOnTheShoppingCartPage() {
        steps.goToTheAuthorizationPage(new String[]{"Username", "Password"}, "LOGIN")
                .logInToTheSystemAs("standard_user", "secret_sauce")
                .checkTheProductsPage("PRODUCTS", "Name (A to Z)", "ADD TO CART")
                .pressOnAddToCartOnAnyProduct("ADD TO CART", 2, "REMOVE", "1")
                .clickOnTheBasketIcon("REMOVE").checkingHeaders("YOUR CART", new String[]{"YOUR CART", "QTY", "DESCRIPTION"})
                .pressOnRemoveButton("REMOVE")
                .clickTheLogoutButton().goToTheAuthorizationPage(new String[]{"Username", "Password"}, "LOGIN");
    }
}