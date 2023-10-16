package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.DriverManager;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.AddRemoveElementPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Listeners(TestMethodCapture.class)
public class AddRemoveElementTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(AddRemoveElementTest.class);

    AddRemoveElementPage addRemoveElementPage = new AddRemoveElementPage();

    @BeforeClass
    public void testSetup() {
        addRemoveElementPage.initializeElements();
        addRemoveElementPage.load();
        addRemoveElementPage.isLoaded();
    }

    @AfterClass
    public void testTearDown() {
        // nothing to do here
    }

    @Test(priority = 1)
    public void addElementTest() {
        // create a new element
        addRemoveElementPage.getAddElementButton().click();
        assertFalse(DriverManager.get().findElements(By.cssSelector(".example .added-manually")).isEmpty());
    }

    @Test(priority = 2, dependsOnMethods = "addElementTest")
    public void removeElementTest() {
        // delete the element we created
        addRemoveElementPage.waitUntilVisible(addRemoveElementPage.getDeleteElementButton()).click();
        assertTrue(DriverManager.get().findElements(By.cssSelector(".example .added-manually")).isEmpty());
    }

}
