package com.tests.various.executorServiceExample;

import gr.qa.utilities.concurrency.RecordHandlingCallable;
import gr.qa.utilities.concurrency.ReportWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This example shows how to use an ExecutorService with Callables
 * and a thread pool. This pool waits until a batch of threads is ready,
 * invokes them and then waits until all threads have finished before moving
 * to the next batch.
 */
@Slf4j
public class CallablesWithExecutorServiceTests {

    String csvFile = "csvDataFile.csv";
    int MAX_CONCURRENCY = 4;

    @Test
    public void testWithExecutorService() throws IOException, InterruptedException {
        String filePath = "src/test/resources/dataTestFiles/" + csvFile;

        log.info("****************************************************");
        log.info(" Starting batch file: " + csvFile);
        log.info("****************************************************");

        try (Reader reader = new FileReader(filePath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            // Initializations
            ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONCURRENCY);
            List<Callable<Void>> toRun = new ArrayList<>();
            ReportWrapper reportWrapper = new ReportWrapper();

            // Parse the CSV file and each record will be executed by a thread
            for (CSVRecord csvRecord : csvParser) {
                RecordHandlingCallable recordHandlingCallable = new RecordHandlingCallable(csvRecord, reportWrapper);
                toRun.add(recordHandlingCallable); // adds thread to a thread pool
                runTasksIfPoolReady(executorService, toRun); // when thread pool full, run the threads
            }

            // Run again, just in case some tasks are left before reaching MAX_CONCURRENCY
            runRemainingTasks(executorService, toRun);
            shutDownAndAwaitTermination(executorService);

            log.info("--------------");
            log.info("--- REPORT ---");
            log.info("# of exceptions found: " + reportWrapper.getExceptionsFoundCounter());
            for (String exception : reportWrapper.getExceptionsFoundList()) {
                log.info("Exception: " + exception);
            }

        }
    }

    private void shutDownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                pool.shutdown();
                if (!pool.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                    log.warn("Pool did not terminate!");
                }
            }
        }
        catch (InterruptedException e) {
            pool.shutdown();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * When a thread pool reaches max capacity, invoke all waiting tasks
     * which are waiting in the toRun list
     * @param pool : the thread pool
     * @param tasks : the tasks waiting in the thread pool
     * @throws InterruptedException : Exception if invokeAll() fails
     */
    private void runTasksIfPoolReady(ExecutorService pool, List<Callable<Void>> tasks) throws InterruptedException {
        if (tasks.size() == MAX_CONCURRENCY) {
            pool.invokeAll(tasks);
            tasks.clear();
        }
    }

    /**
     * Invokes all remaining tasks waiting in the toRun list
     * @param pool : the thread pool
     * @param tasks : the tasks waiting in the thread pool
     * @throws InterruptedException : Exception if invokeAll() fails
     */
    private void runRemainingTasks(ExecutorService pool, List<Callable<Void>> tasks) throws InterruptedException {
        pool.invokeAll(tasks);
        tasks.clear();
    }

}
