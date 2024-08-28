package com.ericsson.taf;

import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.ui.Browser;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.BrowserType;
import com.ericsson.cifwk.taf.ui.UI;
import com.ericsson.cifwk.taf.ui.sdk.ViewModel;
import org.testng.annotations.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Alexey Nikolaenko alexey.nikolaenko@ericsson.com
 *         Date: 17/05/2016
 */
public class FireFoxBrowserTest extends TafTestBase {

    @Test
    public void shouldOpenFirefoxBrowser() {
        Browser browser = UI.newBrowser(BrowserType.FIREFOX);
        BrowserTab open = browser.open("http://atvts3003.athtem.eei.ericsson.se:8882/index.html");

        String title = open.getTitle();

        assertThat(title).isEqualTo("Team Members App");
    }
}



