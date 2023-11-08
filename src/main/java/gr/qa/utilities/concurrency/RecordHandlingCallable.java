package gr.qa.utilities.concurrency;

import org.apache.commons.csv.CSVRecord;

import java.util.concurrent.Callable;

public class RecordHandlingCallable implements Callable<Void> {

    private CSVRecord csvRecord;
    private ReportWrapper reportWrapper;

    // constructor
    public RecordHandlingCallable(CSVRecord csvRecord, ReportWrapper reportWrapper) {
        this.csvRecord = csvRecord;
        this.reportWrapper = reportWrapper;
    }

    @Override
    public Void call() {
        RecordHandler recordHandler = new RecordHandler();
        recordHandler.performRecordHandling(csvRecord, reportWrapper);
        return null;
    }

}
