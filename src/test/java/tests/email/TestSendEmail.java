package tests.email;

import helpers.CommonHelpers;
import helpers.api.RandomLinkAPI;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.FakeMailPage;
import pages.GmailLoginPage;
import pages.constants.GmailHomePageConstants;
import tests.BaseTest;

import java.util.Arrays;
import java.util.List;

public class TestSendEmail extends BaseTest {
    private FakeMailPage fakeMailPage = new FakeMailPage();
    private GmailLoginPage loginPage = new GmailLoginPage();

    @Test(description = "Send email to tmp mailbox")
    @Parameters({"catLink", "dogLink", "foxLink"})
    public void testSendEmail(String catLink, String dogLink, String foxLink) {
        String subject = CommonHelpers.generateUniqueName("Subject Name");

        LOGGER.info("Step 1");
        String tmpMail = fakeMailPage.open().getTmpMailAddress();

        LOGGER.info("Step 2");
        String randomCatLink = new RandomLinkAPI(catLink).getRandomLink();
        String randomDogLink = new RandomLinkAPI(dogLink).getRandomLink();
        String randomFoxLink = new RandomLinkAPI(foxLink).getRandomLink();

        LOGGER.info("Step 3");
        loginPage.open()
                .login()
                .clickComposeButton()
                .sendEmailTo(tmpMail, subject, randomCatLink + " " + randomDogLink + " " + randomFoxLink)
                .openFolderAndCheckThatEmailIsPresent(GmailHomePageConstants.Folders.SENT_FOLDER, subject);

        LOGGER.info("Steps 4-5");
        fakeMailPage.open()
                .openEmailBySubjectName(subject);

        List<String> randomLinks = Arrays.asList(randomCatLink, randomDogLink, randomFoxLink);

        randomLinks.forEach(link -> {
            fakeMailPage.verifyMailContent(link).
                    switchToMailFrame().openLinkFromMessage(link);
            List<String> windowIds = getWindowIds();
            switchToWindow(windowIds.get(1));
            String fileName = getClass().getSimpleName() + link.replaceAll("[^a-zA-Z ]", "");
            makeScreenshot(fileName);
            closeWindow();
            switchToWindow(windowIds.get(0));
        });
    }

}
