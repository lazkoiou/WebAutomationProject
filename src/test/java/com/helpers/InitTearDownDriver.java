package com.helpers;

import gr.qa.helperClasses.DriverManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class InitTearDownDriver {

    @BeforeSuite (alwaysRun = true)
    void initialize() {
        DriverManager.setDriver();
    }

    @AfterSuite (alwaysRun = true)
    void tearDown() {
        DriverManager.tearDownDriver();
    }

}
