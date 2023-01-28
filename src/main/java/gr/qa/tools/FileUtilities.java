package gr.qa.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class FileUtilities {

    private final static Logger logger = LogManager.getLogger(FileUtilities.class);

    /**
     * Deletes a file located in the provided path
     * @param path : path of the file to be deleted
     */
    public static void deleteFile(String path) {
        File fileToDelete = new File(path);
        if (fileToDelete.delete()) {
            logger.info("Deleted the file: " + fileToDelete.getName());
        }
        else {
            String errorMessage = fileToDelete.exists() ? "is in use by another app" : "does not exist";
            logger.error("Deletion failed because: " + errorMessage);
        }
    }

    /**
     * Returns the latest file in a directory
     * @param dirPath : directory with files
     * @return : the latest modified file of a given directory
     */
    public static File getLatestFileInDirectory(String dirPath) {
        File directory = new File(dirPath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;
        if (files != null) {
            for (File file : files) { // Search for the last file that has been modified
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }
        return chosenFile;
    }

}
