package com.tests.web.usefulActions;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class ScreenshotAndVideoRecordingOnFailedTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(ScreenshotAndVideoRecordingOnFailedTest.class);

    @Test
    public void failingTestToGetScreenshotAndVideoRecording() {
        DriverManager.get().get("https://google.com");
        logger.info("Failing the test!");
        fail("Intended failure so that a screenshot and a video recording is taken on failure!");
    }

}
