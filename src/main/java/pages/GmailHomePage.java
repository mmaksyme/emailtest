package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.constants.GmailHomePageConstants;
import pages.modals.SendEmailModalPage;
import utils.BrowserUtils;

public class GmailHomePage extends AbstractPage {

    public GmailHomePage() {
        super();
    }

    @FindBy(xpath = GmailHomePageConstants.Locators.COMPOSE_EMAIL_BUTTON_XPATH)
    WebElement btnCompose;

    public SendEmailModalPage clickComposeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnCompose));
        btnCompose.click();
        return new SendEmailModalPage();
    }

    public GmailHomePage openFolderAndCheckThatEmailIsPresent(String folderName, String subject) {
        BrowserUtils.getDriver().findElement(By.xpath(String.format(GmailHomePageConstants.Locators.FOLDER_XPATH, folderName))).click();
        wait.until(ExpectedConditions.titleContains(folderName));
        checkThatElementIsPresent(By.xpath(String.format(GmailHomePageConstants.Locators.SEARCH_MESSAGE_BY_TEXT_XPATH, subject)));
        return this;
    }
}
