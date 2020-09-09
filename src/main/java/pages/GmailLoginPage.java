package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.constants.GmailLoginPageConstants;
import utils.BrowserUtils;
import utils.Properties;
import utils.PropertyProvider;

public class GmailLoginPage extends AbstractPage {

    public GmailLoginPage() {
        super();
    }

    @FindBy(xpath = GmailLoginPageConstants.Locators.XPATH_LOGIN)
    public WebElement txtLogin;

    @FindBy(xpath = GmailLoginPageConstants.Locators.XPATH_PASSWORD)
    public WebElement txtPassword;

    public GmailLoginPage open() {
        BrowserUtils.getDriver().get(PropertyProvider.getProperty(Properties.GMAIL_LOGIN_PAGE_URL));
        return this;
    }

    public GmailHomePage login() {
        login(PropertyProvider.getProperty(Properties.GMAIL_LOGIN), PropertyProvider.getProperty(Properties.GMAIL_PASSWORD));
        return new GmailHomePage();
    }

    public GmailHomePage login(String login, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(txtLogin));
        txtLogin.sendKeys(login, Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(txtPassword));
        txtPassword.sendKeys(password, Keys.ENTER);

        waitForPageLoaded();
        return new GmailHomePage();
    }
}
