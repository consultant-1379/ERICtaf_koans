package com.ericsson.cifwk.taf.ui.workshop.advanced;

import com.ericsson.cifwk.taf.ui.Browser;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.BrowserType;
import com.ericsson.cifwk.taf.ui.UI;
import com.ericsson.cifwk.taf.ui.core.UiComponentNotFoundException;
import com.ericsson.cifwk.taf.ui.core.UiComponentNotVisibleException;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.ViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ImplicitChecksTest {

    private Browser browser;

    private ViewModel view;

    @Before
    public void setUp() throws Exception {
        browser = UI.newBrowser(BrowserType.CHROME);
        BrowserTab page = browser.open(getHtmlFile("implicit-checks.html"));
        view = page.getGenericView();
    }

    @After
    public void tearDown() throws Exception {
        browser.close();
        UI.closeAllWindows();
    }

    @Test
    public void implicitScrolling() {
        Button button = view.getButton("#foundAfterScrollingPageDown");
        assertEquals("Click me", button.getText());
        button.click();
        assertEquals("I was clicked", button.getText());
    }

    @Test
    public void implicitScrollingInTable() {
        Button button = view.getButton("#foundAfterScrollingTableDown");
        assertEquals("Click me", button.getText());
        button.click();
        assertEquals("I was clicked", button.getText());
    }

    @Test
    public void invisibleButton() {
        try {
            view.getButton("#invisibleButton").click();
            fail();
        } catch(UiComponentNotVisibleException e) {
            assertEquals("MappingBySelector[selector=#invisibleButton, selectorType=DEFAULT]", e.getMessage());
            assertThat(e.getCause().getMessage()).startsWith("element not visible");
        }
    }

    @Test
    public void hiddenButton() {
        try {
            view.getButton("#hiddenButton").click();
            fail();
        } catch(UiComponentNotVisibleException e) {
            assertEquals("MappingBySelector[selector=#hiddenButton, selectorType=DEFAULT]", e.getMessage());
            assertThat(e.getCause().getMessage()).startsWith("element not visible");
        }
    }

    @Test
    public void disabledButtonShouldNotBeClicked() {
        Button button = view.getButton("#disabledButton");
        assertEquals("I am disabled", button.getText());
        button.click();
        assertEquals("I am disabled", button.getText());
    }

    @Test
    public void outerButton() {
        Button button = view.getButton("#outerButton");
        assertEquals("I am located outside page", button.getText());
        try {
            button.click();
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).contains("Element is not clickable at point");
        }
        assertEquals("I am located outside page", button.getText());
    }

    @Test
    public void coveredButton() {
        Button button = view.getButton("#coveredButton");
        assertEquals("I am hidden behind other panel", button.getText());
        try {
            button.click();
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).contains("Element is not clickable at point");
            assertThat(e.getMessage()).contains("Other element would receive the click: <span id=\"panelCoveringButton\"");
        }
        assertEquals("I am hidden behind other panel", button.getText());
    }

    @Test
    public void notExistingButton() {
        Button button = view.getButton("#notExistingButton");
        try {
            button.click();
            fail();
        } catch (UiComponentNotFoundException e) {
            assertThat(e.getMessage()).contains("MappingBySelector[selector=#notExistingButton");
        }
    }

    private String getHtmlFile(String fileName) {
        return "file:///" + new File("src/test/resources/html_pages/" + fileName).getAbsolutePath();
    }

}
