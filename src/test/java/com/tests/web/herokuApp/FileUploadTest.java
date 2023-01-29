package com.tests.web.herokuApp;

import gr.qa.helperClasses.SetUp;
import gr.qa.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.FileUploadPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


@Listeners(TestMethodCapture.class)
public class FileUploadTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(FileUploadTest.class);

    String homepageURL = "https://the-internet.herokuapp.com/";
    FileUploadPage fileUploadPage = new FileUploadPage();

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        fileUploadPage.setDriverInitElements(driver);
        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPagesEnum.FILE_UPLOAD.getLinkText())).click();
    }

    @AfterClass
    public void testTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
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
