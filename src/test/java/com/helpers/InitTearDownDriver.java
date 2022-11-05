package com.helpers;

import gr.qa.helperClasses.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class InitTearDownDriver extends SetUp {

    private final static Logger logger = LogManager.getLogger(InitTearDownDriver.class);

    @BeforeSuite (alwaysRun = true)
    void initialize() {
        logger.info("Initializing web driver...");
        setupDriver();
    }

    @AfterSuite (alwaysRun = true)
    void tearDown() {
        tearDownDriver();
        logger.info("Tearing down web driver.\n");
    }

}
