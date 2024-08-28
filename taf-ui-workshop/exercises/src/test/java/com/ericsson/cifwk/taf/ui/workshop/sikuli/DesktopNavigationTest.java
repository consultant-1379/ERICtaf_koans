package com.ericsson.cifwk.taf.ui.workshop.sikuli;

import com.ericsson.cifwk.taf.ui.Browser;
import com.ericsson.cifwk.taf.ui.BrowserSetup;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.DesktopNavigator;
import com.ericsson.cifwk.taf.ui.DesktopWindow;
import com.ericsson.cifwk.taf.ui.UI;
import com.ericsson.cifwk.taf.ui.core.GenericPredicate;
import com.ericsson.cifwk.taf.ui.sdk.ViewModel;
import org.junit.Test;

/**
 * Created by eniakel on 08/07/2016.
 */

//TODO: open this page in browser and observe DOM structure of the component (using dev tools of your browser)
// note: url is: http://atvts3003.athtem.eei.ericsson.se:8882/login
public class DesktopNavigationTest {

    //TODO: Login and logout of the system using Desktop navigator. (Note: tests should be run against the grid)
    @Test
    public void testImageSearchRemotely() {
        // Using default browser setup (from properties)
        Browser browser = UI.newBrowserWithImageRecognition(BrowserSetup.build());
        //TODO: open page URL
        BrowserTab browserTab = null;

        // TODO: instantiate DesktopNavigator and DesktopWindow, based on browser and images in sample-web-app
        DesktopNavigator desktopNavigator = null;
        DesktopWindow currentWindow = null;
        final ViewModel screen = currentWindow.getGenericView();
        // TODO: enter username and password, and submit, using images
        // Please note: Desktop View Model currently supports only getTextBox() and getViewComponent() methods

        browserTab.waitUntil(new GenericPredicate() {
            @Override
            public boolean apply() {
                // TODO: wait until one of the protected page components appears
                return false;
            }
        });

        screen.getViewComponent("logout_button.png").click();
        screen.getViewComponent("ok_button.png").click();
        browser.close();
    }
}
