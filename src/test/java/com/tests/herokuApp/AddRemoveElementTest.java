package com.tests.herokuApp;

import gr.qa.helperClasses.SetUp;
import gr.qa.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.AddRemoveElementPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Listeners(TestMethodCapture.class)
public class AddRemoveElementTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(AddRemoveElementTest.class);

    String homepageURL = "https://the-internet.herokuapp.com/";
    AddRemoveElementPage addRemoveElementPage = new AddRemoveElementPage();

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        addRemoveElementPage.setDriverInitElements(driver);
        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPagesEnum.ADD_REMOVE_ELEMENTS.getLinkText())).click();
    }

    @AfterClass
    public void testTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
    }

    @Test(priority = 1)
    public void addElementTest() {
        logger.info("** Test case: " + TestMethodCapture.getTestMethod().getMethodName() + " - Starting...");

        // create a new element
        addRemoveElementPage.getAddElementButton().click();
        assertFalse(driver.findElements(By.cssSelector(".example .added-manually")).isEmpty());

        logger.info("** Test case: " + TestMethodCapture.getTestMethod().getMethodName() + " - Ending...");
    }

    @Test(priority = 2, dependsOnMethods = "addElementTest")
    public void removeElementTest() {
        logger.info("** Test case: removeElementTest - Starting...");

        // delete the element we created
        addRemoveElementPage.getDeleteElementButton().click();
        assertTrue(driver.findElements(By.cssSelector(".example .added-manually")).isEmpty());


        logger.info("** Test case: removeElementTest - Ending...");
    }

}
