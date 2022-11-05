package gr.qa.helperClasses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class BaseObject {

    private final static Logger logger = LogManager.getLogger(SetUp.class);

    public static Properties properties = new Properties();
    public String environment;

    /**
     * Load the appropriate properties files, depending on the environment
     */
    public void loadProperties() {
        logger.info("Loading property files...");
        environment = getEnvironment();
        // depending on the environment load the correct properties file
        if (environment.equals("staging")) {
            // load dynamic data
            loadPropertyFile("src/main/resources/dataStaging.properties");
        }
        else {
            // load dynamic data
            loadPropertyFile("src/main/resources/dataProduction.properties");
        }
        logger.info("Property files were successfully loaded.");
    }

    /**
     * Finds out the environment either from Jenkins or from the xml
     * @return : the environment as a String
     */
    public String getEnvironment() {
        ITestContext iTestContext = Reporter.getCurrentTestResult().getTestContext();
        if (System.getenv("environment") != null) { // if we pass it from Jenkins
            return System.getenv("environment");
        }
        else { // we run it locally and we pass it through the xml
            return iTestContext.getSuite().getParameter("environment");
        }
    }

    /**
     * Loads property files
     * @param filePath : the path where the property file is located
     */
    public void loadPropertyFile(String filePath) {
        logger.info("Filepath is: " + filePath);
        InputStream stream;
        try {
            stream = new FileInputStream(filePath);
            properties.load(new InputStreamReader(stream, "iso-8859-7")); // used to read Greek
        }
        catch (IOException e) {
            e.printStackTrace();
            logger.error("Error loading properties files!");
            throw new RuntimeException();
        }
    }

}
