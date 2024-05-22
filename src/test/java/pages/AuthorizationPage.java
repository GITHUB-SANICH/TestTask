package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorizationPage extends BasePage {
    @FindBy(xpath = "//input[@id='password'][@type='password']")
    private WebElement textInputPassword;
    @FindBy(xpath = "//input[@id='user-name'][@type='text']")
    private WebElement textInputLogin;

    @FindBy(xpath = "//input[@type='text'] |//input[@type='password']")
    private List<WebElement> textItemsForm;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement loginButton;

    public AuthorizationPage() {
        super();
    }

    public void checkingTextInputs(String[] expectedTextFields) {
        for (int i = 0; i < textItemsForm.size(); i++) {
            assertThat(textItemsForm.stream().map(e -> e.getAttribute("placeholder")).collect(Collectors.toList()).toString())
                    .as("The field named \"" + expectedTextFields[i] + "\" was not found!").contains(expectedTextFields[i]);
            assertThat(textItemsForm.get(i).isDisplayed())
                    .as("The field named \"" + expectedTextFields[i] + "\" was not displayed!").isTrue();
        }
    }

    public void checkingTheButton(String expected) {
        assertThat(loginButton.getAttribute("value"))
                .as("The button named \"" + expected + "\" was not found!").isEqualToIgnoringCase(expected);
        assertThat(loginButton.isDisplayed()).as("This button has not been displayed!").isTrue();
    }

    public void clearAllTextInputs() {
        for (WebElement inputText : textItemsForm) {
            inputText.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        }
    }

    public void authorization(String login, String password) {
        textInputLogin.sendKeys(login);
        textInputPassword.sendKeys(password);
        String authorizationPage = driver.getCurrentUrl();
        loginButton.click();
        assertThat(authorizationPage)
                .as("Authorization was not successful!").isNotEqualTo(driver.getCurrentUrl());
    }
}
