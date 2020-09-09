package pages.constants;

public class FakeMailPageConstants {
    public static final class Locators {
        public static final String TEMP_EMAIL_ADDRESS_XPATH = "//span[text()='Temp mail:']/following-sibling::span[contains(text(), '@getnada.com')]";
        public static final String RECEIVED_EMAIL_SUBJECT_XPATH = "//div[text()='%s']";
        public static final String MAIL_CONTENT_I_FRAME_ID = "idIframe";
        public static final String ANY_TEXT_XPATH = "//*[text()='%s']";
    }
}
