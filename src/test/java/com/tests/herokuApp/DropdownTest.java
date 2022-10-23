package com.tests.herokuApp;

import gr.qa.heplerClasses.SetUp;
import gr.qa.pages.herokuapp.DropdownPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class DropdownTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(DropdownTest.class);

    String homepageURL = "https://the-internet.herokuapp.com/";
    DropdownPage dropdownPage = new DropdownPage();

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        dropdownPage.setDriverInitElements(driver);
        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPagesEnum.DROPDOWN.getLinkText())).click();
    }

    @AfterClass
    public void testTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
    }

    @Test
    void selectOption1FromDropdownTest() {
        logger.info("** Test case: selectOption1FromDropdownTest - Starting...");

        dropdownPage.selectOptionFromDropdown("Option 1");
        assertEquals(dropdownPage.getTextFromSelectedOption(), "Option 1");

        logger.info("** Test case: selectOption1FromDropdownTest - Ending...");
    }

}
