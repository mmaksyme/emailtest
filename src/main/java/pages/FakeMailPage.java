package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.constants.FakeMailPageConstants;
import utils.BrowserUtils;
import utils.Properties;
import utils.PropertyProvider;

public class FakeMailPage extends AbstractPage {

    public FakeMailPage() {
        super();
    }

    @FindBy(xpath = FakeMailPageConstants.Locators.TEMP_EMAIL_ADDRESS_XPATH)
    WebElement tempEmailAddress;

    @FindBy(id = FakeMailPageConstants.Locators.MAIL_CONTENT_I_FRAME_ID)
    WebElement iFrameMailContent;

    public FakeMailPage open() {
        if (ExpectedConditions.alertIsPresent().apply(BrowserUtils.getDriver()) != null)
            BrowserUtils.getDriver().switchTo().alert().accept();
        BrowserUtils.getDriver().get(PropertyProvider.getProperty(Properties.FAKE_MAIL_PAGE_URL));
        return this;
    }

    public String getTmpMailAddress() {
        waitForPageLoaded();
        return tempEmailAddress.getText();
    }

    public FakeMailPage openEmailBySubjectName(String subjectName) {
        By subjectLocator = By.xpath(String.format(FakeMailPageConstants.Locators.RECEIVED_EMAIL_SUBJECT_XPATH, subjectName));
        wait.until(ExpectedConditions.elementToBeClickable(subjectLocator));
        BrowserUtils.getDriver().findElement(subjectLocator).click();
        return this;
    }

    public FakeMailPage switchToMailFrame() {
        BrowserUtils.getDriver().switchTo().frame(iFrameMailContent);
        return this;
    }

    public FakeMailPage verifyMailContent(String content) {
        switchToMailFrame();
        checkThatElementIsPresent(By.xpath(String.format(FakeMailPageConstants.Locators.ANY_TEXT_XPATH, content)));
        BrowserUtils.getDriver().switchTo().defaultContent();
        return this;
    }

    public FakeMailPage openLinkFromMessage(String linkText) {
        BrowserUtils.getDriver().findElement(By.xpath(String.format(FakeMailPageConstants.Locators.ANY_TEXT_XPATH, linkText))).click();
        return this;
    }
}
