package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.SortableDataTablesPage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

@Listeners(TestMethodCapture.class)
public class SortableDataTablesTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(SortableDataTablesTest.class);

    SortableDataTablesPage sortableDataTablesPage = new SortableDataTablesPage();

    @BeforeClass
    public void testSetup() {
        sortableDataTablesPage.setDriverInitElements(driver);
        sortableDataTablesPage.load();
        sortableDataTablesPage.isLoaded();
    }

    @AfterClass
    public void testTearDown() {
        // nothing to do here
    }

    @Test
    void noAttributesSortableTableTest() {
        // click on the "Due" header to sort table based on this column
        sortableDataTablesPage.getDueColumnHeader().click();

        List<Double> dueList = sortableDataTablesPage.makeListOfDueValues();
        logger.info("List: " + dueList);
        assertTrue(sortableDataTablesPage.verifyAscendingSorting(dueList));
    }

}
