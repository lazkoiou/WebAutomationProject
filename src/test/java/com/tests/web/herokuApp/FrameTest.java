package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.DriverManager;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import gr.qa.pages.herokuapp.framePages.FrameInitialPage;
import gr.qa.pages.herokuapp.framePages.NestedFramesPage;
import gr.qa.pages.herokuapp.framePages.IFramePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Listeners(TestMethodCapture.class)
public class FrameTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(FrameTest.class);

    WebDriver driver;

    String homepageURL = "https://the-internet.herokuapp.com/";
    FrameInitialPage frameInitialPage = new FrameInitialPage();
    NestedFramesPage nestedFramesPage = new NestedFramesPage();
    IFramePage iFramePage = new IFramePage();

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        driver = DriverManager.getDriver();
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
        frameInitialPage.getNestedFramesLink().click();
        assertEquals(nestedFramesPage.findTopLeftFrameHeader(), "LEFT");
    }

    @Test(priority = 1)
    public void nestedFramesVerifyTopMiddleTest() {
        frameInitialPage.getNestedFramesLink().click();
        assertEquals(nestedFramesPage.findTopMiddleFrameHeader(), "MIDDLE");
    }

    @Test(priority = 1)
    public void nestedFramesVerifyTopRightTest() {
        frameInitialPage.getNestedFramesLink().click();
        assertEquals(nestedFramesPage.findTopRightFrameHeader(), "RIGHT");
    }

    @Test(priority = 1)
    public void nestedFramesVerifyBottomTest() {
        frameInitialPage.getNestedFramesLink().click();
        assertEquals(nestedFramesPage.findBottomFrameHeader(), "BOTTOM");
    }
    
    @Test(priority = 2)
    public void iFrameTest() {
        frameInitialPage.getiFrameLink().click();
        driver.switchTo().frame("mce_0_ifr");
        String beforeText = iFramePage.getEditorText().getText();
        iFramePage.inputTextIntoIFrameEditor("New content is here!");
        String afterText = iFramePage.getEditorText().getText();
        assertNotEquals(beforeText, afterText);
    }

}
