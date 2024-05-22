package steps;

import io.qameta.allure.Step;
import pages.AuthorizationPage;

public class AuthorizationSteps {
    private final AuthorizationPage authorizationPage = new AuthorizationPage();

    @Step("Перейти на страницу авторизации")
    public AuthorizationSteps goToTheAuthorizationPage(String[] expectedTextFields, String buttonName) {
        authorizationPage.checkingTextInputs(expectedTextFields);
        authorizationPage.checkingTheButton(buttonName);
        return this;
    }

    @Step("Авторизоваться в системе пользователем")
    public ProductsSteps logInToTheSystemAs(String login, String password) {
        authorizationPage.clearAllTextInputs();
        authorizationPage.authorization(login, password);
        return new ProductsSteps();
    }
}