package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import steps.AuthorizationSteps;
import utils.DriverFactory;

import static utils.PropertyReader.getBrowser;
import static utils.PropertyReader.getURL;

public abstract class BaseTest {

    private static WebDriver driver;
    AuthorizationSteps steps;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.getDriver(getBrowser());
        driver.get(getURL());
        steps = new AuthorizationSteps();
    }

    @AfterMethod
    public void clearToLocalStorageAndGoToStarLink() {
        if (driver instanceof WebStorage) {
            WebStorage webStorage = (WebStorage) driver;
            webStorage.getSessionStorage().clear();
            webStorage.getLocalStorage().clear();
        }
        if (!driver.getCurrentUrl().equals(getURL())) {
            driver.get(getURL());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }
}