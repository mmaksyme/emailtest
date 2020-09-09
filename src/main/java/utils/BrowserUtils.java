package utils;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String expectedDriver = PropertyProvider.getProperty(Properties.BROWSER_NAME);
            if (expectedDriver == null || expectedDriver.isEmpty()) {
                expectedDriver = "chrome";
            }

            String wdHubUrl = PropertyProvider.getProperty(Properties.WEBDRIVER_HUB_URL);
            boolean isLocal = StringUtils.isBlank(wdHubUrl);

            switch (expectedDriver.toLowerCase()) {
                case "chrome":
                    ChromeOptions co = new ChromeOptions();
                    if (isLocal) {
                        System.setProperty("webdriver.chrome.driver", Objects.requireNonNull(BrowserUtils.class.getClassLoader().getResource("drivers/chromedriver.exe")).getPath());
                        driver = new ChromeDriver(co);
                    } else {
                        driver = buildRemoteWebDriver(wdHubUrl, co);
                    }
                    break;
                case "firefox":
                    FirefoxOptions fo = new FirefoxOptions();
                    if (isLocal) {
                        System.setProperty("webdriver.gecko.driver", Objects.requireNonNull(BrowserUtils.class.getClassLoader().getResource("drivers/geckodriver.exe")).getPath());
                        driver = new FirefoxDriver(fo);
                    } else {
                        driver = buildRemoteWebDriver(wdHubUrl, fo);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser '" + expectedDriver + "'");
            }
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyProvider.getProperty(Properties.IMPLICIT_TIMEOUT)), TimeUnit.SECONDS);
        }

        return driver;
    }

    private static RemoteWebDriver buildRemoteWebDriver(String wdUrl, Capabilities caps) {
        URL url;
        try {
            url = new URL(wdUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Failed to build remote WebDriver URL from string " + wdUrl, e);
        }
        return new RemoteWebDriver(url, caps);
    }
}
