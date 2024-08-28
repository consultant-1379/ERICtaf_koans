package com.ericsson.cifwk.taf.ui.workshop.viewmodel;

import com.ericsson.cifwk.taf.ui.Browser;
import com.ericsson.cifwk.taf.ui.BrowserType;
import com.ericsson.cifwk.taf.ui.UI;
import org.junit.After;
import org.junit.Before;

import java.io.File;

abstract class BaseWorkshopTest {

    protected Browser browser;

    @Before
    public void setUp() {
        browser = UI.newBrowser(BrowserType.CHROME);
    }

    @After
    public void tearDown() {
        browser.close();
    }

    protected String getHtmlFile(String fileName) {
        return "file:///" + new File("src/test/resources/html_pages/" + fileName).getAbsolutePath();
    }

}
