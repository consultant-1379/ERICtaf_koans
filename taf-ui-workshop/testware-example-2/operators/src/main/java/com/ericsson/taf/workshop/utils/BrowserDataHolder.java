package com.ericsson.taf.workshop.utils;

import com.ericsson.cifwk.taf.TestContext;
import com.ericsson.cifwk.taf.ui.Browser;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.BrowserType;
import com.ericsson.cifwk.taf.ui.UI;

import javax.inject.Inject;

/**
 * @author Kirill Shepitko kirill.shepitko@ericsson.com
 *         Date: 19/05/2016
 */
public class BrowserDataHolder {

    public static final String CONTEXT_BROWSER_TAB = "browserTab";
    public static final String CONTEXT_BROWSER = "browser";

    @Inject
    private TestContext testContext;

    public Browser getOrCreateBrowser() {
        Browser browser = getContextBrowser();
        if (browser == null) {
            browser = openNewBrowser();
            setContextBrowser(browser);
        }
        return browser;
    }

    private void setContextBrowser(Browser browser) {
        testContext.setAttribute(CONTEXT_BROWSER, browser);
    }

    private Browser getContextBrowser() {
        return testContext.getAttribute(CONTEXT_BROWSER);
    }

    private Browser openNewBrowser() {
        return UI.newBrowser(BrowserType.FIREFOX);
    }

    public BrowserTab getCurrentBrowserTab() {
        return testContext.getAttribute(CONTEXT_BROWSER_TAB);
    }

    public void setBrowserTab(BrowserTab tab) {
        testContext.setAttribute(CONTEXT_BROWSER_TAB, tab);
    }

}
