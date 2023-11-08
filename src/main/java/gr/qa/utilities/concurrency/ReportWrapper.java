package gr.qa.utilities.concurrency;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class ReportWrapper {

    private List<String> exceptionsFoundList = new ArrayList<>();

    private static final Object exceptionLock = new Object(); // A lock for synchronization

    private AtomicInteger exceptionsFoundCounter = new AtomicInteger(0);

    public void addExceptionFound(String exceptionFound) {
        synchronized (exceptionLock) {
            exceptionsFoundList.add(exceptionFound);
        }
    }

    public void increaseExceptionsFoundCounter() {
        exceptionsFoundCounter.incrementAndGet();
    }

}
