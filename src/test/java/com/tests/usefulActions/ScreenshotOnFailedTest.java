package com.tests.usefulActions;

import com.tests.herokuApp.HoverTest;
import gr.qa.helperClasses.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class ScreenshotOnFailedTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(HoverTest.class);

    @Test
    public void failingTestToGetScreenshot() {
        driver.get("https://google.com");
        logger.info("Failing the test!");
        fail("Intended failure so that a screenshot is taken on failure!");
    }

}
