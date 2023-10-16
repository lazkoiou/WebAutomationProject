package gr.qa.helperClasses.listeners;

import gr.qa.helperClasses.DriverManager;
import org.testng.IClassListener;
import org.testng.ITestClass;

public class ClassListener implements IClassListener {

    @Override
    public void onBeforeClass(ITestClass testClass) {
        // nothing to do
    }

    @Override
    public void onAfterClass(ITestClass testClass) {
        DriverManager.tearDownDriver();
    }

}
