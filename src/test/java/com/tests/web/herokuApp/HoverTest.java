package com.tests.web.herokuApp;

import com.tests.web.WebBaseTest;
import gr.qa.helperClasses.listeners.TestMethodCapture;
import gr.qa.pages.herokuapp.HoverPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

@Listeners(TestMethodCapture.class)
public class HoverTest extends WebBaseTest {

    private final static Logger logger = LogManager.getLogger(HoverTest.class);

    HoverPage hoverPage = new HoverPage();

    @BeforeClass
    public void testSetup() {
        hoverPage.setDriverInitElements(driver);
        hoverPage.load();
        hoverPage.isLoaded();
    }

    @AfterClass
    public void testTearDown() {
        // nothing to do here
    }

    @Test
    void hoverProfileNamesTest() {
        List<String> profileUsernames = hoverPage.hoverAndGetUsername();
        logger.info("User profiles: " + profileUsernames);

        assertEquals(profileUsernames.get(0), "user1");
        assertEquals(profileUsernames.get(1), "user2");
        assertEquals(profileUsernames.get(2), "user3");
    }

}
