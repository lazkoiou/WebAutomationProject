package com.tests.herokuApp;

import gr.qa.heplerClasses.SetUp;
import gr.qa.pages.herokuapp.AddRemoveElementPage;
import gr.qa.pages.herokuapp.FileUploadPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class FileUploadTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(FileUploadTest.class);

    String homepageURL = "https://the-internet.herokuapp.com/";

    @Test
    void fileUploadTest() {
        logger.info("Starting test case fileUploadTest...");
        FileUploadPage fileUploadPage = new FileUploadPage();
        fileUploadPage.setDriverInitElements(driver);

        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPages.FILE_UPLOAD.getLinkText())).click();

        // upload a file and verify
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\testFiles\\testText.txt";
        logger.info("Filepath to file to be uploaded is: " + filePath);
        fileUploadPage.getBrowseFile().sendKeys(filePath);
        fileUploadPage.getFileSubmitButton().click();
        assertEquals(fileUploadPage.getSuccessMessage().getText(), "File Uploaded!");

        logger.info("Ended test case fileUploadTest.");
    }

}
