package steps;

import io.qameta.allure.Step;
import pages.YouCardPage;

public class YouCardSteps {
    private final YouCardPage youCardPage = new YouCardPage();

    public YouCardSteps checkingHeaders(String expectedTitle, String[] expectedHeaderList) {
        youCardPage.checkingThePageTitle(expectedTitle);
        youCardPage.checkingHeaderList(expectedHeaderList);
        youCardPage.checkingForProductParametersMatches();
        return this;
    }

    @Step("Нажать кнопку \"REMOVE\"")
    public YouCardSteps pressOnRemoveButton(String buttonName) {
        youCardPage.pressToButtonRemoveAndCheckingIsItemCount(buttonName);
        return this;
    }

    @Step("Нажать кнопку LOGOUT")
    public AuthorizationSteps clickTheLogoutButton() {
        youCardPage.clickForLogout();
        return new AuthorizationSteps();
    }
}
