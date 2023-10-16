package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.DriverManager;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.BasicAuthPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners(TestMethodCapture.class)
public class BasicAuthTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(BasicAuthTest.class);

    BasicAuthPage basicAuthPage = new BasicAuthPage();
    String expectedMessage = "Congratulations! You must have the proper credentials.";
    String username = "admin";
    String password = "admin";

    @BeforeClass
    public void testSetup() {
        basicAuthPage.initializeElements();
        basicAuthPage.load();
        basicAuthPage.isLoaded();
    }

    @AfterClass
    public void testTearDown() {
        // nothing to do here
    }

    @Test
    public void basicAuthByHttpPrefix() {
        String urlWithLoginPrefix = HerokuTestPagesEnum.BASIC_AUTH.getUrl().replace("https://", "http://" + username + ":" + password + "@");
        DriverManager.get().get(urlWithLoginPrefix);
        assertEquals(basicAuthPage.getPageMessage().getText(), expectedMessage);
    }

}
