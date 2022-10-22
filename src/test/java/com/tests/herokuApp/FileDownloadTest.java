package com.tests.herokuApp;

import gr.qa.heplerClasses.SetUp;
import gr.qa.pages.herokuapp.FileUploadPage;
import gr.qa.pages.herokuapp.enums.FileDownloadPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class FileDownloadTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(FileDownloadTest.class);

    String homepageURL = "https://the-internet.herokuapp.com/";
    FileDownloadPage fileDownloadPage = new FileDownloadPage();

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        fileDownloadPage.setDriverInitElements(driver);
        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPages.FILE_DOWNLOAD.getLinkText())).click();
    }

    @AfterClass
    public void testTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
    }

    @Test
    void fileDownloadTest() {
        logger.info("** Test case: fileUploadTest - Starting...");

        // download a file and verify
        fileDownloadPage.getFileToDownload().click();
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\testFiles\\downloaded";
        sleep(2000); // wait a few seconds so that the file is downloaded
        File downloadedFile = new File(filePath + "\\some-file.txt");
        assertTrue(downloadedFile.exists());

        // delete the previously downloaded file and verify
        assertTrue(downloadedFile.delete());

        logger.info("** Test case: fileUploadTest - Ending...");
    }

}
