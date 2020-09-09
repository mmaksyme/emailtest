package pages.constants;

public class GmailHomePageConstants {

    public static final class Locators {
        public static final String COMPOSE_EMAIL_BUTTON_XPATH = "//div[text()='Compose']";
        public static final String FOLDER_XPATH = "//a[text()='%s']";
        public static final String SEARCH_MESSAGE_BY_TEXT_XPATH = "//span[1][text()='%s']";
    }

    public static final class Folders {
        public static final String INBOX_FOLDER = "Inbox";
        public static final String SENT_FOLDER = "Sent";
    }
}
