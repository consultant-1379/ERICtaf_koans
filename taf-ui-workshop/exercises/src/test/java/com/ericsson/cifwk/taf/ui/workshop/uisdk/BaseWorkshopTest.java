package com.ericsson.cifwk.taf.ui.workshop.uisdk;

import com.ericsson.cifwk.taf.ui.Browser;
import com.ericsson.cifwk.taf.ui.BrowserType;
import com.ericsson.cifwk.taf.ui.UI;
import org.junit.After;
import org.junit.Before;

public abstract class BaseWorkshopTest {

    protected Browser browser;

    @Before
    public void setUp() {
        browser = UI.newBrowser(BrowserType.CHROME);
    }

    @After
    public void tearDown() {
        browser.close();
    }

    protected String getDefaultPage() {
        return "https://arm1s11-eiffel004.eiffel.gic.ericsson.se:8443/nexus/content/sites/tor/widgets/latest/showcase/index.html";
    }

}
