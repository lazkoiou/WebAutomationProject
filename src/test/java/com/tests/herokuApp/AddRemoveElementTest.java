package com.tests.herokuApp;

import gr.qa.heplerClasses.SetUp;
import gr.qa.pages.herokuapp.AddRemoveElementPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class AddRemoveElementTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(AddRemoveElementTest.class);

    String homepageURL = "https://the-internet.herokuapp.com/";

    @Test
    void addRemoveElementTest() {
        logger.info("Starting test case addRemoveElementTest...");
        AddRemoveElementPage addRemoveElementPage = new AddRemoveElementPage();
        addRemoveElementPage.setDriverInitElements(driver);

        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPages.ADD_REMOVE_ELEMENTS.getLinkText())).click();

        // create a new element
        addRemoveElementPage.getAddElementButton().click();
        assertFalse(driver.findElements(By.cssSelector(".example .added-manually")).isEmpty());

        // delete the element we created
        addRemoveElementPage.getDeleteElementButton().click();
        assertTrue(driver.findElements(By.cssSelector(".example .added-manually")).isEmpty());

        logger.info("Ended test case addRemoveElementTest.");
    }

}
