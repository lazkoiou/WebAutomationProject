package gr.qa.tools;

import com.google.common.io.Files;
import gr.qa.helperClasses.SetUp;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotTool {

    private final static Logger logger = LogManager.getLogger(ScreenshotTool.class);

    /**
     * Takes a screenshot and returns it
     * @param testName : the test name which called the getScreenshot method
     * @return : the screenshot in bytes
     */
    public static byte[] getScreenshot(String testName) {
        if (SetUp.driver != null) {
            SimpleDateFormat sdfImage = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            SimpleDateFormat sdfFolder = new SimpleDateFormat("yyyy_MM_dd");
            String formattedDateImage = sdfImage.format(new Date());
            String formattedDateFolder = sdfFolder.format(new Date());
            String path = "src/main/resources/images/";
            String namePrefix = "images_";
            String filepath = path + namePrefix + formattedDateFolder + "/" + testName + "_" + formattedDateImage + ".png";
            File src = ((TakesScreenshot) SetUp.driver).getScreenshotAs(OutputType.FILE);
            logger.info("Screenshot taken: " + filepath);
            try {
                FileUtils.copyFile(src, new File(filepath));
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("Screenshot could not be taken. Exception thrown!");
                logger.error("Error message: " + e.getMessage());
            }
            try {
                return Files.toByteArray(src);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("Error message: " + e.getMessage());
            }
            logger.info("If we reached here, an exception during taking the screenshot was thrown!");
        }
        else {
            logger.info("WebDriver is null. Screenshot cannot be taken!");
        }
        return null;
    }

}
