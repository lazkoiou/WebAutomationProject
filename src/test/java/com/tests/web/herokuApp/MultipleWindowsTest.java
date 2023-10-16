package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.DriverManager;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.multipleWindows.MultipleWindowsPage;
import gr.qa.pages.herokuapp.multipleWindows.NewMultipleWindowsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


@Listeners(TestMethodCapture.class)
public class MultipleWindowsTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(MultipleWindowsTest.class);

    MultipleWindowsPage multipleWindowsPage = new MultipleWindowsPage();
    NewMultipleWindowsPage newMultipleWindowsPage = new NewMultipleWindowsPage();

    @BeforeClass
    public void testSetup() {
        multipleWindowsPage.initializeElements();
        newMultipleWindowsPage.initializeElements();
        multipleWindowsPage.load();
        multipleWindowsPage.isLoaded();
    }
    @AfterClass
    public void testTearDown() {
        // nothing to do here
    }

    @Test
    void openNewWindowTest() {
        String firstTab = DriverManager.get().getWindowHandle();
        multipleWindowsPage.openNewWindowAndSwitchToIt(firstTab);
        newMultipleWindowsPage.isLoaded();
        assertEquals(newMultipleWindowsPage.getNewWindowHeader().getText(), "New Window");
        multipleWindowsPage.closeNewWindowAndSwitchToPrevious(firstTab);
    }

}
