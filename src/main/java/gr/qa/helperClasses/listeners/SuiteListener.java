package gr.qa.helperClasses.listeners;

import gr.qa.helperClasses.DriverManager;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        DriverManager.setDriver();
    }

    @Override
    public void onFinish(ISuite suite) {
        DriverManager.tearDownDriver();
    }

}
