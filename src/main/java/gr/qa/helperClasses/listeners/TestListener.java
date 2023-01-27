package gr.qa.helperClasses.listeners;

import gr.qa.helperClasses.BaseObject;
import gr.qa.helperClasses.annotations.Environment;
import gr.qa.tools.QaTools;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener {

    private final static Logger logger = LogManager.getLogger(TestListener.class);

    long startTime;

    @Override
    public void onTestStart(ITestResult itr) {
        // Log test case starting info
        logger.info("** Test case: " + itr.getName() + " - Starting...");
        startTime = System.nanoTime();
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
        try {
            byte[] imageBytes = QaTools.getScreenshot(itr.getName());
            if (imageBytes != null) { // add image attachment to allure
                logger.info("Attaching to allure...");
                InputStream imageStream = new ByteArrayInputStream(imageBytes);
                // TODO: For some reason the screenshot is not attached in allure report
                Allure.addAttachment("Screenshot on failure", imageStream);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("Something went wrong with the Screenshot!");
            logger.error("Error message: " + e.getMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult itr) {
        // Log test case ending info
        long elapsedTime = System.nanoTime() - startTime;
        long elapsedTimeInSec = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        logger.info("** Test case: " + itr.getName() + " - Ending...");
        logger.info("TEST SUCCESS! Elapsed time: " + elapsedTimeInSec);
    }

}
