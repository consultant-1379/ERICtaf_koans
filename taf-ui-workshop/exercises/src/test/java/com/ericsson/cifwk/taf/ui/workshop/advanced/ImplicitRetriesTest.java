package com.ericsson.cifwk.taf.ui.workshop.advanced;

import com.ericsson.cifwk.taf.ui.Browser;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.BrowserType;
import com.ericsson.cifwk.taf.ui.UI;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.ViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Implicitly retries until asynchronous element is present, visible and clickable
 */
public class ImplicitRetriesTest {

    private Browser browser;

    private ViewModel view;
    
    @Before
    public void setUp() throws Exception {
        browser = UI.newBrowser(BrowserType.CHROME);
        BrowserTab page = browser.open(getHtmlFile("implicit-retries.html"));
        view = page.getGenericView();
    }

    @After
    public void tearDown() throws Exception {
        browser.close();
        UI.closeAllWindows();
    }

    @Test
    public void asynchronousElement() {
        Button button = view.getButton("#asyncButton");
        assertEquals("I was absent", button.getText());
        button.click();
        assertEquals("I was clicked", button.getText());
    }

    @Test
    public void invisibleElement() {
        Button button = view.getButton("#invisibleButton");
        assertEquals("", button.getText()); // since element is invisible, there is no title available
        button.click();
        assertEquals("I was clicked", button.getText());
    }

    @Test
    public void hiddenElement() {
        Button button = view.getButton("#hiddenButton");
        assertEquals("", button.getText()); // since element is invisible, there is no title available
        button.click();
        assertEquals("I was clicked", button.getText());
    }

    @Test
    public void disabledElement() {
        Button button = view.getButton("#disabledButton");
        assertEquals("I was disabled", button.getText());
        button.click();
        assertEquals("I was clicked", button.getText());
    }

    @Test
    public void outerElement() {
        Button button = view.getButton("#outerButton");
        assertEquals("I was located outside page", button.getText());
        button.click();
    }

    @Test
    public void coveredElement() {
        Button button = view.getButton("#coveredButton");
        assertEquals("I was hidden behind other panel", button.getText());
        button.click();
    }

    private String getHtmlFile(String fileName) {
        return "file:///" + new File("src/test/resources/html_pages/" + fileName).getAbsolutePath();
    }

}
