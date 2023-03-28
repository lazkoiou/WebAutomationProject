package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.DriverManager;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.DropdownPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


@Listeners(TestMethodCapture.class)
public class DropdownTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(DropdownTest.class);

    WebDriver driver;

    String homepageURL = "https://the-internet.herokuapp.com/";
    DropdownPage dropdownPage = new DropdownPage();

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        driver = DriverManager.getDriver();
        dropdownPage.setDriverInitElements(driver);
        // open homepage and go to the testing page
        driver.get(homepageURL);
        driver.findElement(By.linkText(HerokuTestPagesEnum.DROPDOWN.getLinkText())).click();
    }

    @AfterClass
    public void testTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
    }

    @Test
    void selectOption1FromDropdownTest() {
        dropdownPage.selectOptionFromDropdown("Option 1");
        assertEquals(dropdownPage.getTextFromSelectedOption(), "Option 1");
    }

}
