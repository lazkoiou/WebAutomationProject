package com.tests.web.herokuApp;

import gr.qa.helperClasses.DriverManager;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.NotificationMessagePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


@Listeners(TestMethodCapture.class)
public class NotificationMessageRetryActionsTest {

    private final static Logger logger = LogManager.getLogger(NotificationMessageRetryActionsTest.class);

    WebDriver driver;

    String homepageURL = "https://the-internet.herokuapp.com/";
    NotificationMessagePage notificationMessagePage = new NotificationMessagePage();

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        driver = DriverManager.getDriver();
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
        assertTrue(notificationMessagePage.retryIfActionUnsuccessful());
    }

}
