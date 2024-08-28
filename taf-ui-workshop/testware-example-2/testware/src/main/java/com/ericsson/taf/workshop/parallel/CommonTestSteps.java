package com.ericsson.taf.workshop.parallel;

import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.ui.Browser;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.taf.workshop.utils.BrowserDataHolder;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * @author Kirill Shepitko kirill.shepitko@ericsson.com
 *         Date: 19/05/2016
 */
public class CommonTestSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonTestSteps.class);

    protected static final String INIT_BROWSER = "Init browser";

    @Inject
    protected BrowserDataHolder browserDataHolder;

    @TestStep(id = INIT_BROWSER)
    public void initBrowser() {
        LOGGER.info("Initiating browser");
        browserDataHolder.getOrCreateBrowser();
    }

    protected BrowserTab getCurrentBrowserTab() {
        return browserDataHolder.getCurrentBrowserTab();
    }

    protected void setBrowserTab(BrowserTab tab) {
        browserDataHolder.setBrowserTab(tab);
    }

    protected Browser retrieveBrowser() {
        Browser browser = browserDataHolder.getOrCreateBrowser();
        Preconditions.checkState(browser != null,
                "Browser not initialized yet, run CommonSteps.initBrowser() test step to do it");
        return browser;
    }

    protected void openInCurrentBrowserTab(String url) {
        BrowserTab browserTab = getCurrentBrowserTab();
        if (browserTab == null) {
            Browser browser = retrieveBrowser();
            browserTab = browser.open(url);
            setBrowserTab(browserTab);
        } else {
            browserTab.open(url);
        }
    }
}
