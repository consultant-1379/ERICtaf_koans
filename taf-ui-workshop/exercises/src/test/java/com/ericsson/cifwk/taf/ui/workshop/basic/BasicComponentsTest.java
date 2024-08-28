package com.ericsson.cifwk.taf.ui.workshop.basic;

import com.ericsson.cifwk.taf.ui.Browser;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.BrowserType;
import com.ericsson.cifwk.taf.ui.UI;
import com.ericsson.cifwk.taf.ui.sdk.ViewModel;
import com.google.common.base.Preconditions;
import org.junit.After;
import org.junit.Before;

import java.net.URL;

public abstract class BasicComponentsTest {

    protected Browser browser;

    protected BrowserTab browserTab;

    protected ViewModel view;

    @Before
    public void setUp() {
        browser = UI.newBrowser(BrowserType.CHROME);
        browserTab = browser.open(getHtmlFile(getPage()));
        view = browserTab.getGenericView();
    }

    @After
    public void tearDown() {
        browser.close();
    }

    public String getPage() {
        return "basic-ui-components.html";
    }

    protected String getHtmlFile(String fileName) {
        URL resource = BasicComponentsTest.class.getResource("/html_pages/" + fileName);
        Preconditions.checkNotNull(resource, String.format("File %s is not found in class path", fileName));
        String path = resource.getPath();
        return "file:///" + path;
    }

}
