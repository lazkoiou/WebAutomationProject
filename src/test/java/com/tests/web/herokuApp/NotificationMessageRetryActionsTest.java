package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.NotificationMessagePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Listeners(TestMethodCapture.class)
public class NotificationMessageRetryActionsTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(NotificationMessageRetryActionsTest.class);

    NotificationMessagePage notificationMessagePage = new NotificationMessagePage();

    @BeforeClass
    public void testSetup() {
        notificationMessagePage.setDriverInitElements(driver);
        notificationMessagePage.load();
        notificationMessagePage.isLoaded();
    }

    @AfterClass
    public void testTearDown() {
        // nothing to do here
    }

    @Test
    void retryActionUntilSuccessfulMessageTest() {
        assertTrue(notificationMessagePage.retryIfActionUnsuccessful());
    }

}
