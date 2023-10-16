package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.DriverManager;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.framePages.FrameInitialPage;
import gr.qa.pages.herokuapp.framePages.NestedFramesPage;
import gr.qa.pages.herokuapp.framePages.IFramePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Listeners(TestMethodCapture.class)
public class FrameTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(FrameTest.class);

    FrameInitialPage frameInitialPage = new FrameInitialPage();
    NestedFramesPage nestedFramesPage = new NestedFramesPage();
    IFramePage iFramePage = new IFramePage();

    @BeforeClass
    public void testSetup() {
        frameInitialPage.initializeElements();
        nestedFramesPage.initializeElements();
        iFramePage.initializeElements();
    }

    @AfterClass
    public void testTearDown() {
        // nothing to do here
    }

    @BeforeMethod
    public void navigateBeforeEachTest() {
        frameInitialPage.load();
        frameInitialPage.isLoaded();
    }

    @Test(priority = 1)
    public void nestedFramesVerifyTopLeftTest() {
        frameInitialPage.getNestedFramesLink().click();
        nestedFramesPage.isLoaded();
        assertEquals(nestedFramesPage.findTopLeftFrameHeader(), "LEFT");
    }

    @Test(priority = 1)
    public void nestedFramesVerifyTopMiddleTest() {
        frameInitialPage.getNestedFramesLink().click();
        nestedFramesPage.isLoaded();
        assertEquals(nestedFramesPage.findTopMiddleFrameHeader(), "MIDDLE");
    }

    @Test(priority = 1)
    public void nestedFramesVerifyTopRightTest() {
        frameInitialPage.getNestedFramesLink().click();
        nestedFramesPage.isLoaded();
        assertEquals(nestedFramesPage.findTopRightFrameHeader(), "RIGHT");
    }

    @Test(priority = 1)
    public void nestedFramesVerifyBottomTest() {
        frameInitialPage.getNestedFramesLink().click();
        nestedFramesPage.isLoaded();
        assertEquals(nestedFramesPage.findBottomFrameHeader(), "BOTTOM");
    }
    
    @Test(priority = 2)
    public void iFrameTest() {
        frameInitialPage.getiFrameLink().click();
        iFramePage.isLoaded();
        DriverManager.get().switchTo().frame("mce_0_ifr");
        String beforeText = iFramePage.getEditorText().getText();
        iFramePage.inputTextIntoIFrameEditor("New content is here!");
        String afterText = iFramePage.getEditorText().getText();
        assertNotEquals(beforeText, afterText);
    }

}
