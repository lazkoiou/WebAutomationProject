package com.tests.web.usefulActions;

import gr.qa.helperClasses.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class ScreenshotAndVideoRecordingOnFailedTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(ScreenshotAndVideoRecordingOnFailedTest.class);

    @Test
    public void failingTestToGetScreenshotAndVideoRecording() {
        driver.get("https://google.com");
        logger.info("Failing the test!");
        fail("Intended failure so that a screenshot and a video recording is taken on failure!");
    }

}
