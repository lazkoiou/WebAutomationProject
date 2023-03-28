package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.DriverManager;
import gr.qa.helperClasses.PropertiesManager;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.ABTestingPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.openqa.selenium.By;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Properties;

import static org.testng.Assert.assertEquals;

@Listeners(TestMethodCapture.class)
public class ABTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(ABTest.class);

    String homepageURL = "https://the-internet.herokuapp.com/";
    ABTestingPage abTestingPage = new ABTestingPage();

    WebDriver driver;

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        properties = PropertiesManager.loadProperties();
        driver = DriverManager.getDriver();
        abTestingPage.setDriverInitElements(driver);
        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPagesEnum.AB_TESTING.getLinkText())).click();
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
        // add optOutUrl, refresh and accept the js alert
        driver.get(driver.getCurrentUrl() + "?optimizely_opt_out=true");
        driver.switchTo().alert().accept();
        assertEquals(abTestingPage.getHeading().getText(), "No A/B Test");
    }

}
