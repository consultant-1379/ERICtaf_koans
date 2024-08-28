package com.ericsson.cifwk.taf.ui.workshop.basic;

import com.ericsson.cifwk.taf.ui.Browser;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.ViewModel;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloEricssonTest {


    /**
     * When doing tests, open this page in browser to understand its DOM structure (using dev tools of your browser)
     */
    public static final String PAGE = "http://www.ericsson.com/thecompany";

    private Browser browser;

    @Before
    public void setUp() {

        // TODO: Initialize Chrome browser using UI.java as factory

    }

    @After
    public void tearDown() {

        // TODO: Close browser

    }

    @Test
    public void goHome() {
        BrowserTab browserTab = browser.open(PAGE);

        // TODO: Initialize generic view of current page
        ViewModel genericView = null;

        // TODO: Initialize Ericsson Logo button and click it
        Button ericssonLogo = null;

        // TODO: Get URL of the current page in address bar
        String currentUrl = "";

        // checking that browser tab navigated to home page
        Assertions.assertThat(currentUrl).isEqualTo("https://www.ericsson.com/");
    }

}
