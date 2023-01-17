package com.tests.annotated;

import gr.qa.helperClasses.SetUp;
import gr.qa.helperClasses.annotations.Environment;
import gr.qa.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.HoverPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

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
