package gr.qa.pages.herokuapp;

import gr.qa.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SortableDataTablesPage extends BasePage {

    private final static Logger logger = LogManager.getLogger(SortableDataTablesPage.class);

    // Table 1

    @FindBy(css = "#table1 thead tr th:nth-of-type(4)")
    private WebElement dueColumnHeader;

    @FindBy(css = "#table1 tbody tr td:nth-of-type(4)")
    private List<WebElement> dueColumnValues;

    public WebElement getDueColumnHeader() {
        return dueColumnHeader;
    }

    public List<WebElement> getDueColumnValues() {
        return dueColumnValues;
    }

    /**
     * Gets all "Due" column values, removes "$", converts them to
     * doubles and adds them into a list
     * @return : list with all "Due" values
     */
    public List<Double> makeListOfDueValues() {
        List<Double> dueList = new ArrayList<>();
        dueColumnValues.forEach(value -> {
            dueList.add(Double.parseDouble(value.getText().replace("$", "")));
        });
        return dueList;
    }

    /**
     * Verifies that the list is sorted by ascending order
     * @param list : list to be checked
     * @return : true if sorted by ascending order, false otherwise
     */
    public boolean verifyAscendingSorting(List<Double> list) {
        double previous = 0, current;
        for (double value : list) {
            current = value;
            logger.info("Previous: " + previous + ", Current: " + current);
            if (current < previous) {
                return false;
            }
            previous = current;
        }
        return true;
    }

}
