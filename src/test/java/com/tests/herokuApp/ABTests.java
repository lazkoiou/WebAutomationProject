package com.tests.herokuApp;

import gr.qa.heplerClasses.SetUp;
import gr.qa.pages.herokuapp.ABTestingPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPages;
import org.openqa.selenium.By;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ABTests extends SetUp {

    private final static Logger logger = LogManager.getLogger(ABTests.class);

    String homepageURL = "https://the-internet.herokuapp.com/";
    ABTestingPage abTestingPage = new ABTestingPage();

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        abTestingPage.setDriverInitElements(driver);
        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPages.AB_TESTING.getLinkText())).click();
    }

    @AfterClass
    public void testTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
    }

    /**
     * Some standard opt-out mechanisms are usually built into A/B testing platforms.
     * They tend to come in the form of an appended URL or forging a cookie.
     */
    @Test
    void optOutUrlTest() {
        logger.info("** Test case: optOutUrlTest - Starting...");

        // add optOutUrl, refresh and accept the js alert
        driver.get(driver.getCurrentUrl() + "?optimizely_opt_out=true");
        driver.switchTo().alert().accept();
        assertEquals(abTestingPage.getHeading().getText(), "No A/B Test");

        logger.info("** Test case: optOutUrlTest - Ending...");
    }

}
