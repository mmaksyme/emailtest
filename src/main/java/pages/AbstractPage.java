package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtils;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractPage {

    public static WebDriverWait wait = new WebDriverWait(BrowserUtils.getDriver(), 30, 500);

    public AbstractPage() {
        PageFactory.initElements(BrowserUtils.getDriver(), this);
    }

    public static void checkThatElementIsPresent(By locator) {
        assertThat(BrowserUtils.getDriver().findElement(locator).isDisplayed()).as("Element by locator " + locator + " is not displayed").isTrue();
    }

    public static void waitForPageLoaded() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

}
