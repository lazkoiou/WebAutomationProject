package com.tests.herokuApp;

import gr.qa.heplerClasses.SetUp;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import gr.qa.pages.herokuapp.multipleWindows.MultipleWindowsPage;
import gr.qa.pages.herokuapp.multipleWindows.NewMultipleWindowsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class MultipleWindowsTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(MultipleWindowsTest.class);

    String homepageURL = "https://the-internet.herokuapp.com/";
    MultipleWindowsPage multipleWindowsPage = new MultipleWindowsPage();
    NewMultipleWindowsPage newMultipleWindowsPage = new NewMultipleWindowsPage();

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        multipleWindowsPage.setDriverInitElements(driver);
        newMultipleWindowsPage.setDriverInitElements(driver);
        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPagesEnum.MULTIPLE_WINDOWS.getLinkText())).click();
    }
    @AfterClass
    public void testTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
    }

    @Test
    void openNewWindowTest() {
        logger.info("** Test case: openNewWindowTest - Starting...");

        String firstTab = driver.getWindowHandle();
        multipleWindowsPage.openNewWindowAndSwitchToIt(firstTab);
        assertEquals(newMultipleWindowsPage.getNewWindowHeader().getText(), "New Window");
        multipleWindowsPage.closeNewWindowAndSwitchToPrevious(firstTab);

        logger.info("** Test case: openNewWindowTest - Ending...");
    }

}
