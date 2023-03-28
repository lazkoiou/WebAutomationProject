package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.DriverManager;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.HoverPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

@Listeners(TestMethodCapture.class)
public class HoverTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(HoverTest.class);

    WebDriver driver;

    String homepageURL = "https://the-internet.herokuapp.com/";
    HoverPage hoverPage = new HoverPage();

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        driver = DriverManager.getDriver();
        hoverPage.setDriverInitElements(driver);
        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPagesEnum.HOVERS.getLinkText())).click();
    }

    @AfterClass
    public void testTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
    }

    @Test
    void hoverProfileNamesTest() {
        List<String> profileUsernames = hoverPage.hoverAndGetUsername();
        logger.info("User profiles: " + profileUsernames);

        assertEquals(profileUsernames.get(0), "user1");
        assertEquals(profileUsernames.get(1), "user2");
        assertEquals(profileUsernames.get(2), "user3");
    }

}
