package tests;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.BrowserUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @BeforeTest
    public void setUp() {
        BrowserUtils.getDriver().manage().window().maximize();
    }

    @AfterTest
    public void shutDown() {
        BrowserUtils.getDriver().quit();
    }

    protected List<String> getWindowIds() {
        return new ArrayList<>(BrowserUtils.getDriver().getWindowHandles());
    }

    protected void closeWindow() {
        BrowserUtils.getDriver().close();
    }

    protected void switchToWindow(String id) {
        BrowserUtils.getDriver().switchTo().window(id);
    }

    protected void makeScreenshot(String fileName) {
        String screenshotsPath = System.getProperty("user.dir") + File.separator + FilenameUtils.separatorsToSystem("target/test-output") + File.separator + fileName + ".png";
        TakesScreenshot scrShot = ((TakesScreenshot) BrowserUtils.getDriver());

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(screenshotsPath);

        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            throw new RuntimeException("Could not make screenshot", e);
        }
    }
}
