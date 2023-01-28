package gr.qa.tools;

import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecordingTool {

    private final static Logger logger = LogManager.getLogger(VideoRecordingTool.class);

    private static ScreenRecorder screenRecorder;

    /**
     * Starts the video recording
     * @param pathToSaveRecording : path to save the test video recording
     */
    public static void startRecording(String pathToSaveRecording, ITestResult itr) {
        logger.info("Starting test video recording for test: " + itr.getName());
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();
        logger.info("Path to save recording: " + pathToSaveRecording);
        try {
            screenRecorder = new ScreenRecorder(
                    graphicsConfiguration,
                    graphicsConfiguration.getBounds(),
                    new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                            QualityKey, 1.0f,
                            KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                    null,
                    new File(pathToSaveRecording));
            screenRecorder.start();
        }
        catch (AWTException | IOException e) {
            e.printStackTrace();
            logger.error("Error starting video recorder!");
            logger.error("Error message: " + e.getMessage());
        }
    }

    /**
     * Stops the video recording
     */
    public static void stopRecording(ITestResult itr) {
        try {
            logger.info("Stopping test video recording for test: " + itr.getName());
            screenRecorder.stop();
        }
        catch (IOException e) {
            e.printStackTrace();
            logger.error("Error stopping video recording!");
            logger.error("Error message: " + e.getMessage());
        }
    }

    /**
     * Creates the path to save the current test video recording
     */
    public static String getRecordingPath(ITestResult itr) {
        SimpleDateFormat sdfVideo = new SimpleDateFormat("yyyy_MM_dd");
        String formattedCurrentDateForVideoFolder = sdfVideo.format(new Date()); // Use this to create a subdirectory for videos
        String className = itr.getTestContext().getAllTestMethods()[0].getInstance().getClass().getSimpleName();
        String testName = itr.getMethod().getMethodName();
        String path = "src/main/resources/videos/videos_";
        return path + formattedCurrentDateForVideoFolder + "/" + className + "/" + testName + "/";
    }

    /**
     * Gets the latest video recording for current test scenario
     * @return : the latest test video recording file
     */
    public static byte[] getLatestRecordingForCurrentTest(ITestResult itr) {
        String recordingPath = getRecordingPath(itr);
        File latestRecording = FileUtilities.getLatestFileInDirectory(recordingPath);
        try {
            return Files.toByteArray(latestRecording);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Error in getting the latest test video recording!");
            logger.error("Error message: " + e.getMessage());
        }
        return null;
    }

}
