package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class DriverFactory {
    private final static String DRIVER_PATH = "src/test/resources/drivers/";
    private static WebDriver driver;

    public static WebDriver getDriver(Browser browser) {
        File file;
        switch (browser) {
            case Chrome:
                file = new File(DRIVER_PATH + "chrome-driver.exe");
                System.setProperty("web-driver.chrome.driver", file.getAbsolutePath());
                driver = new ChromeDriver();
                break;
            case Firefox:
                file = new File(DRIVER_PATH + "firefox-driver.exe");
                System.setProperty("web-driver.firefox.driver", file.getAbsolutePath());
                driver = new FirefoxDriver();
                break;
        }
        return driver;
    }
}
