package gr.qa.helperClasses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesManager {

    private final static Logger logger = LogManager.getLogger(PropertiesManager.class);

    public static Properties properties = new Properties();
    public static String environment;


    /**
     * Loads property files
     * @param filePath : the path where the property file is located
     */
    public static void loadPropertyFile(String filePath) {
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

    /**
     * Load the appropriate properties files, depending on the environment
     */
    public static Properties loadProperties() {
        logger.info("Loading property files...");
        environment = getEnvironment();
        // depending on the environment load the correct properties file
        if (environment.equals("staging")) {
            // load dynamic data
            loadPropertyFile("src/main/resources/dataStaging.properties");
            loadPropertyFile("src/main/resources/urlsStaging.properties");
        }
        else {
            // load dynamic data
            loadPropertyFile("src/main/resources/dataProduction.properties");
            loadPropertyFile("src/main/resources/urlsProduction.properties");
        }
        logger.info("Property files were successfully loaded.");
        return properties;
    }

    /**
     * Finds out the environment either from Jenkins or from the xml
     * @return : the environment as a String
     */
    public static String getEnvironment() {
        if (System.getenv("environment") != null) { // if we pass it from Jenkins
            return System.getenv("environment");
        }
        else { // we run it locally and we pass it through the xml
            ITestContext iTestContext = Reporter.getCurrentTestResult().getTestContext();
            return iTestContext.getSuite().getParameter("environment");
        }
    }


    /**
     * This method returns the browser used
     */
    public String getBrowser() {
        String browser;
        ITestContext iTestContext = Reporter.getCurrentTestResult().getTestContext();
        // First check if we pass it as a test parameter
        if (iTestContext.getCurrentXmlTest().getParameter("browser") != null) {
            browser= iTestContext.getCurrentXmlTest().getParameter("browser");
        }
        // Else, check if we pass it from Jenkins
        else if (System.getenv("browser") != null) {
            browser = System.getenv("browser");
        }
        // Lastly, take the browser from the properties file
        else {
            browser = properties.getProperty("browser");
        }
        logger.info("Browser is: " + browser);
        return browser;
    }

}
