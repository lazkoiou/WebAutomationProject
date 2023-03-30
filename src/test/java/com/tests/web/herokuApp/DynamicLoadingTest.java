package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.dynamicLoadingPages.DynamicLoadingElementRenderedAfterTheFactPage;
import gr.qa.pages.herokuapp.dynamicLoadingPages.DynamicLoadingHiddenElementPage;
import gr.qa.pages.herokuapp.dynamicLoadingPages.DynamicLoadingPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

@Listeners(TestMethodCapture.class)
public class DynamicLoadingTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(DynamicLoadingTest.class);

    DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage();
    DynamicLoadingHiddenElementPage dynamicLoadingHiddenElementPage = new DynamicLoadingHiddenElementPage();
    DynamicLoadingElementRenderedAfterTheFactPage dynamicLoadingElementRenderedAfterTheFactPage = new DynamicLoadingElementRenderedAfterTheFactPage();

    String expectedUnhiddenText = "Hello World!";

    @BeforeClass
    public void testSetup() {
        dynamicLoadingPage.setDriverInitElements(driver);
        dynamicLoadingHiddenElementPage.setDriverInitElements(driver);
        dynamicLoadingElementRenderedAfterTheFactPage.setDriverInitElements(driver);
    }

    @AfterClass
    public void testTearDown() {
        // nothing to do here
    }

    @BeforeMethod
    public void navigateToStartingURL() {
        logger.info("Navigate to appropriate URL to begin the test case...");
        dynamicLoadingPage.load();
        dynamicLoadingPage.isLoaded();
    }

    /**
     * Example 1: There is a hidden element in the page. This element
     * appears ~ 5 seconds after we click on "Start" button. We need to
     * wait and assert it after it has appeared.
     */
    @Test
    void hiddenElementOnPageTest() {
        dynamicLoadingPage.getHiddenElementExampleLink().click();
        dynamicLoadingHiddenElementPage.isLoaded();
        dynamicLoadingHiddenElementPage.getStartButton().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        dynamicLoadingElementRenderedAfterTheFactPage.isLoaded();
        dynamicLoadingElementRenderedAfterTheFactPage.getStartButton().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(dynamicLoadingHiddenElementPage.getUnhiddenText()));

        assertEquals(dynamicLoadingHiddenElementPage.getUnhiddenText().getText(), expectedUnhiddenText);
    }

}
