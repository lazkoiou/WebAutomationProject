package gr.qa.helperClasses.listeners;

import gr.qa.helperClasses.BaseObject;
import gr.qa.helperClasses.annotations.Environment;
import gr.qa.tools.FileUtilities;
import gr.qa.tools.ScreenshotTool;
import gr.qa.tools.VideoRecordingTool;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener {

    private final static Logger logger = LogManager.getLogger(TestListener.class);

    long startTime;
    String recordingPath;

    @Override
    public void onTestStart(ITestResult itr) {
        // Log test case starting info
        logger.info("** Test case: " + itr.getName() + " - Starting...");
        startTime = System.nanoTime();
        // Start test case video recording
        recordingPath = VideoRecordingTool.getRecordingPath(itr);
        VideoRecordingTool.startRecording(recordingPath, itr);
        // Check if test has an @Environment annotation
        // Current environment
        String currentEnvironment = BaseObject.getEnvironment();
        // Get the environment annotation if any - else null
        Annotation envAnn = itr.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Environment.class);
        Environment environmentFromAnnotation = (Environment) envAnn;
        // Check if test needs to skipped
        if (environmentFromAnnotation != null && !Arrays.asList(environmentFromAnnotation.runIn()).contains(currentEnvironment)) {
            logger.info("Skipping '" + itr.getMethod() + "'. It is set to run on the following environments: "
                    + Arrays.asList(environmentFromAnnotation.runIn()) + " but our current environment is: " + currentEnvironment);
            throw new SkipException("Skip test!");
        }
    }

    @Override
    public void onTestFailure(ITestResult itr) {
        // Log test case ending info
        long elapsedTime = System.nanoTime() - startTime;
        long elapsedTimeInSec = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        logger.info("** Test case: " + itr.getName() + " - Ending...");
        logger.info("TEST FAILED! Elapsed time: " + elapsedTimeInSec);
        // Get a screenshot to be able to see what went wrong
        byte[] imageBytes = ScreenshotTool.getScreenshot(itr.getName());
        // Add image attachment to allure
        logger.info("Attaching screenshot to allure...");
        InputStream imageStream = new ByteArrayInputStream(Objects.requireNonNull(imageBytes));
        // TODO: For some reason the screenshot is not attached in allure report
        Allure.addAttachment("Screenshot on failure", imageStream);
        // Stop the test video recording
        VideoRecordingTool.stopRecording(itr);
        FileUtilities.getLatestFileInDirectory(recordingPath);
        byte[] videoBytes = VideoRecordingTool.getLatestRecordingForCurrentTest(itr);
        logger.info("Attaching test video recording to allure...");
        InputStream videoStream = new ByteArrayInputStream(Objects.requireNonNull(videoBytes));
        // An embedded video would be attached to allure with the below code
        // Allure.addAttachment("Test video", "video/mp4", "videoStream, "mp4");
        // However, there is a known bug, where on test failure, the video is not attached
        // We will use the code below to add a downloadable video attachment
        // TODO: For some reason the test video recording is not attached in allure report
        Allure.addAttachment("Test video", videoStream);
        logger.info("You can find the test video recording at: \"" + recordingPath + "\"");
    }

    @Override
    public void onTestSuccess(ITestResult itr) {
        // Log test case ending info
        long elapsedTime = System.nanoTime() - startTime;
        long elapsedTimeInSec = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        logger.info("** Test case: " + itr.getName() + " - Ending...");
        logger.info("TEST SUCCESS! Elapsed time: " + elapsedTimeInSec);
        logger.info("Deleting test video recording, since test passed successfully...");
        VideoRecordingTool.stopRecording(itr);
        File recordingToDelete = FileUtilities.getLatestFileInDirectory(recordingPath);
        if (recordingToDelete.delete()) {
            logger.info("Test recording has been successfully deleted.");
        } else {
            logger.error("Test recording could not be deleted!");
        }
    }

}
