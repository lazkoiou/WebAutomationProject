package com.tests.herokuApp;

import gr.qa.heplerClasses.SetUp;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import gr.qa.pages.herokuapp.framePages.FrameInitialPage;
import gr.qa.pages.herokuapp.framePages.NestedFramesPage;
import gr.qa.pages.herokuapp.framePages.IFramePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class FrameTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(FrameTest.class);

    String homepageURL = "https://the-internet.herokuapp.com/";
    FrameInitialPage frameInitialPage = new FrameInitialPage();
    NestedFramesPage nestedFramesPage = new NestedFramesPage();
    IFramePage iFramePage = new IFramePage();

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        frameInitialPage.setDriverInitElements(driver);
        nestedFramesPage.setDriverInitElements(driver);
        iFramePage.setDriverInitElements(driver);
    }

    @AfterClass
    public void testTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
    }

    @BeforeMethod
    public void navigateBeforeEachTest() {
        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPagesEnum.FRAMES.getLinkText())).click();
    }

    @Test(priority = 1)
    public void nestedFramesVerifyTopLeftTest() {
        logger.info("** Test case: nestedFramesVerifyTopLeftTest - Starting...");

        frameInitialPage.getNestedFramesLink().click();
        assertEquals(nestedFramesPage.findTopLeftFrameHeader(), "LEFT");

        logger.info("** Test case: nestedFramesVerifyTopLeftTest - Ending...");
    }

    @Test(priority = 1)
    public void nestedFramesVerifyTopMiddleTest() {
        logger.info("** Test case: nestedFramesVerifyTopMiddleTest - Starting...");

        frameInitialPage.getNestedFramesLink().click();
        assertEquals(nestedFramesPage.findTopMiddleFrameHeader(), "MIDDLE");

        logger.info("** Test case: nestedFramesVerifyTopMiddleTest - Ending...");
    }

    @Test(priority = 1)
    public void nestedFramesVerifyTopRightTest() {
        logger.info("** Test case: nestedFramesVerifyTopRightTest - Starting...");

        frameInitialPage.getNestedFramesLink().click();
        assertEquals(nestedFramesPage.findTopRightFrameHeader(), "RIGHT");

        logger.info("** Test case: nestedFramesVerifyTopRightTest - Ending...");
    }

    @Test(priority = 1)
    public void nestedFramesVerifyBottomTest() {
        logger.info("** Test case: nestedFramesVerifyBottomTest - Starting...");

        frameInitialPage.getNestedFramesLink().click();
        assertEquals(nestedFramesPage.findBottomFrameHeader(), "BOTTOM");

        logger.info("** Test case: nestedFramesVerifyBottomTest - Ending...");
    }
    
    @Test(priority = 2)
    public void iFrameTest() {
        logger.info("** Test case: iFrameTest - Starting...");

        frameInitialPage.getiFrameLink().click();
        driver.switchTo().frame("mce_0_ifr");
        String beforeText = iFramePage.getEditorText().getText();
        iFramePage.inputTextIntoIFrameEditor("New content is here!");
        String afterText = iFramePage.getEditorText().getText();
        assertNotEquals(beforeText, afterText);

        logger.info("** Test case: iFrameTest - Ending...");
    }

}
