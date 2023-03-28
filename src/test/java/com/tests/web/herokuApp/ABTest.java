package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.ABTestingPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.openqa.selenium.By;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

@Listeners(TestMethodCapture.class)
public class ABTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(ABTest.class);

    String homepageURL = "https://the-internet.herokuapp.com/";
    ABTestingPage abTestingPage = new ABTestingPage();

    @BeforeClass
    public void testSetup() {
        abTestingPage.setDriverInitElements(driver);
        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPagesEnum.AB_TESTING.getLinkText())).click();
    }

    @AfterClass
    public void testTearDown() {
        // nothing to do
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
