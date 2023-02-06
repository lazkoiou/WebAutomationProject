package com.tests.web.herokuApp;

import gr.qa.helperClasses.SetUp;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.dynamicLoadingPages.DynamicLoadingElementRenderedAfterTheFactPage;
import gr.qa.pages.herokuapp.dynamicLoadingPages.DynamicLoadingHiddenElementPage;
import gr.qa.pages.herokuapp.dynamicLoadingPages.DynamicLoadingPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

@Listeners(TestMethodCapture.class)
public class DynamicLoadingTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(DynamicLoadingTest.class);

    String homepageURL = "https://the-internet.herokuapp.com/";
    String dynamicPageURL;
    DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage();
    DynamicLoadingHiddenElementPage dynamicLoadingHiddenElementPage = new DynamicLoadingHiddenElementPage();
    DynamicLoadingElementRenderedAfterTheFactPage dynamicLoadingElementRenderedAfterTheFactPage = new DynamicLoadingElementRenderedAfterTheFactPage();

    String expectedUnhiddenText = "Hello World!";

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        dynamicLoadingPage.setDriverInitElements(driver);
        dynamicLoadingHiddenElementPage.setDriverInitElements(driver);
        dynamicLoadingElementRenderedAfterTheFactPage.setDriverInitElements(driver);
        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPagesEnum.DYNAMIC_LOADING.getLinkText())).click();
        dynamicPageURL = driver.getCurrentUrl();
    }

    @AfterClass
    public void testTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
    }

    @BeforeMethod
    public void navigateToStartingURL() {
        logger.info("Navigate to appropriate URL to begin the test case...");
        driver.get(dynamicPageURL);
    }

    /**
     * Example 1: There is a hidden element in the page. This element
     * appears ~ 5 seconds after we click on "Start" button. We need to
     * wait and assert it after it has appeared.
     */
    @Test
    void hiddenElementOnPageTest() {
        dynamicLoadingPage.getHiddenElementExampleLink().click();
        dynamicLoadingHiddenElementPage.getStartButton().click();

        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.visibilityOf(dynamicLoadingHiddenElementPage.getUnhiddenText()));

        assertEquals(dynamicLoadingHiddenElementPage.getUnhiddenText().getText(), expectedUnhiddenText);
    }

    /**
     * Example 2: The element we want to check is not rendered. This element
     * gets rendered ~ 5 seconds after we click on "Start" button. We need to
     * wait and assert it after it has appeared.
     */
    @Test
    void elementRenderedAfterTheFactTest() {
        dynamicLoadingPage.getElementRenderedAfterwardsExampleLink().click();
        dynamicLoadingElementRenderedAfterTheFactPage.getStartButton().click();

        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.visibilityOf(dynamicLoadingHiddenElementPage.getUnhiddenText()));

        assertEquals(dynamicLoadingHiddenElementPage.getUnhiddenText().getText(), expectedUnhiddenText);
    }

}
