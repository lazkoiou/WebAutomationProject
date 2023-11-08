package gr.qa.utilities.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

public class RecordHandler {

    private final static Logger logger = LogManager.getLogger(RecordHandler.class);

    // constructor
    public RecordHandler() {
        // empty for now
    }

    /**
     * Here, the csv record handling takes place
     * @param csvRecord : the csv record
     * @param reportWrapper : a wrapper that contains the results we need to report
     */
    public void performRecordHandling(CSVRecord csvRecord, ReportWrapper reportWrapper) {

        logger.info("Thread " + Thread.currentThread().getId() + " is working on userId: " + csvRecord.get(0));
        String entry = csvRecord.get(0);
        long startTime = Calendar.getInstance().getTimeInMillis();

        try {
            // Do whatever record handling we need here - for the sake of the example we just log something
            logger.info("Working on it...");
            logger.info("Done!");
        }
        catch (Exception e) {
                logger.info("Exception while checking csvRecord: " + entry);
                e.printStackTrace();
                reportWrapper.addExceptionFound(entry);

        }
        finally {
            long endTime = Calendar.getInstance().getTimeInMillis();
            logger.info("Thread {} execution time - {}", Thread.currentThread().getId(), endTime - startTime);
        }

    }

}
