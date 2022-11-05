package com.tests.herokuApp;

import gr.qa.helperClasses.SetUp;
import gr.qa.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.BasicAuthPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners(TestMethodCapture.class)
public class BasicAuthTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(NotificationMessageRetryActionsTest.class);

    String homepageURL = "https://the-internet.herokuapp.com/";
    String basicAuthUrl;
    BasicAuthPage basicAuthPage = new BasicAuthPage();
    String expectedMessage = "Congratulations! You must have the proper credentials.";
    String username = "admin";
    String password = "admin";

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        basicAuthPage.setDriverInitElements(driver);
        // open homepage and go to the testing page
        driver.get(homepageURL);
        basicAuthUrl = driver.findElement(By.linkText(HerokuTestPagesEnum.BASIC_AUTH.getLinkText())).getAttribute("href");
    }

    @AfterClass
    public void testTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
    }

    @Test
    public void basicAuthByHttpPrefix() {
        logger.info("** Test case: " + TestMethodCapture.getTestMethod().getMethodName() + " - Starting...");

        String urlWithLoginPrefix = basicAuthUrl.replace("https://", "http://" + username + ":" + password + "@");
        driver.get(urlWithLoginPrefix);
        assertEquals(basicAuthPage.getPageMessage().getText(), expectedMessage);

        logger.info("** Test case: " + TestMethodCapture.getTestMethod().getMethodName() + " - Ending...");
    }

}
