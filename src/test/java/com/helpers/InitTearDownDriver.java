package com.helpers;

import gr.qa.helperClasses.SetUp;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class InitTearDownDriver extends SetUp {

    @BeforeSuite (alwaysRun = true)
    void initialize() {
        setupDriver();
    }

    @AfterSuite (alwaysRun = true)
    void tearDown() {
        tearDownDriver();
    }

}
