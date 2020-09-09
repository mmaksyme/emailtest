package pages.modals;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbstractPage;
import pages.GmailHomePage;
import pages.constants.modals.SendEmailModalPageConstants;

public class SendEmailModalPage extends AbstractPage {

    public SendEmailModalPage() {
        super();
    }

    @FindBy(xpath = SendEmailModalPageConstants.Locators.SEND_TO_XPATH)
    WebElement txtSendTo;

    @FindBy(xpath = SendEmailModalPageConstants.Locators.SUBJECT_XPATH)
    WebElement txtSubject;

    @FindBy(xpath = SendEmailModalPageConstants.Locators.MESSAGE_TEXT_XPATH)
    WebElement txtMessageText;

    @FindBy(xpath = SendEmailModalPageConstants.Locators.BUTTON_SEND_XPATH)
    WebElement btnSend;

    public GmailHomePage sendEmailTo(String to, String subject, String text) {
        txtSendTo.sendKeys(to, Keys.ENTER);
        txtSubject.sendKeys(subject, Keys.ENTER);
        txtMessageText.sendKeys(text, Keys.ENTER);
        btnSend.click();
        return new GmailHomePage();
    }



}
