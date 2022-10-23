package com.tests.herokuApp;

import gr.qa.heplerClasses.SetUp;
import gr.qa.pages.herokuapp.NotificationMessagePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class NotificationMessageRetryActionsTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(NotificationMessageRetryActionsTest.class);

    String homepageURL = "https://the-internet.herokuapp.com/";
    NotificationMessagePage notificationMessagePage = new NotificationMessagePage();

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        notificationMessagePage.setDriverInitElements(driver);
        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPagesEnum.NOTIFICATION_MESSAGES.getLinkText())).click();
    }

    @AfterClass
    public void testTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
    }

    @Test
    void retryActionUntilSuccessfulMessageTest() {
        logger.info("** Test case: retryActionUntilSuccessfulMessageTest - Starting...");

        assertTrue(notificationMessagePage.retryIfActionUnsuccessful());

        logger.info("** Test case: retryActionUntilSuccessfulMessageTest - Ending...");
    }

}
