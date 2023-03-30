package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.FileUploadPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


@Listeners(TestMethodCapture.class)
public class FileUploadTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(FileUploadTest.class);

    FileUploadPage fileUploadPage = new FileUploadPage();

    @BeforeClass
    public void testSetup() {
        fileUploadPage.setDriverInitElements(driver);
        fileUploadPage.load();
        fileUploadPage.isLoaded();
    }

    @AfterClass
    public void testTearDown() {
        // nothing to do here
    }

    @Test
    void fileUploadTest() {
        // upload a file and verify
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\testFiles\\testText.txt";
        logger.info("Filepath to file to be uploaded is: " + filePath);
        fileUploadPage.getBrowseFile().sendKeys(filePath);
        fileUploadPage.getFileSubmitButton().click();
        assertEquals(fileUploadPage.getSuccessMessage().getText(), "File Uploaded!");
    }

}
