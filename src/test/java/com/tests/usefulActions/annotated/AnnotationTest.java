package com.tests.usefulActions.annotated;

import gr.qa.helperClasses.SetUp;
import gr.qa.helperClasses.annotations.Environment;
import gr.qa.listeners.TestMethodCapture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners(TestMethodCapture.class)
public class AnnotationTest extends SetUp {

    private final static Logger logger = LogManager.getLogger(AnnotationTest.class);

    @BeforeClass
    public void testSetup() {
        logger.info("* Test class: " + getClass() + " - Starting...");
    }

    @AfterClass
    public void testTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
    }

    @Test
    @Environment(runIn = {"production"})
    void productionAnnotatedTest() {
        logger.info("This test runs only in production!");
    }

    @Test
    @Environment(runIn = {"staging"})
    void stagingAnnotatedTest() {
        logger.info("This test runs only in staging!");
    }

}
