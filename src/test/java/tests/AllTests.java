package tests;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ExcelDataProviders;
import utils.TestListener;

@Listeners(TestListener.class)
public class AllTests extends BaseTest {
    @Test(dataProvider = "infoForAuthorizationToUsers", dataProviderClass = ExcelDataProviders.class, timeOut = 5000)
    @Severity(SeverityLevel.NORMAL)
    @Owner(value = "Иванов Иван")
    @Link(name = "Site: Swag Labs", url = "https://www.saucedemo.com/")
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
    @Severity(SeverityLevel.NORMAL)
    @Owner(value = "Иванов Иван")
    @Link(name = "Site: Swag Labs", url = "https://www.saucedemo.com/")
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