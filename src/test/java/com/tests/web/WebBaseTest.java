package com.tests.web;

import com.tests.web.herokuApp.ABTest;
import gr.qa.helperClasses.PropertiesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.util.Properties;

public class WebBaseTest {

    private final static Logger logger = LogManager.getLogger(ABTest.class);

    public Properties properties;

    @BeforeClass
    public void baseSetUp() {
        logger.info("* Test class: " + getClass() + " - Starting...");
        properties = PropertiesManager.loadProperties();
    }

    @AfterClass
    public void baseTearDown() {
        logger.info("* Test class: " + getClass() + " - Ending...");
    }

}
