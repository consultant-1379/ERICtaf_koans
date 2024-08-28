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

import static org.assertj.core.api.Assertions.assertThat;

public class ImplicitCheckExercises {
    /**
     * In this exercise you will need to have a selenium binary for chrome download the latest release
     * from here: https://chromedriver.storage.googleapis.com/index.html
     * when running Ui tests locally you must use
     * the command -Dwebdriver.chrome.driver="Path/to/binary.exe" to specify the path to the selenium binary
     * in the VM options: when you edit configurations.
     */


    // TODO: open page in browser, scroll it down and observe DOM structure (using dev tools of your browser)
    public static final String PAGE = "implicit-checks-pageToBeFixed.html";

    private Browser browser;

    private ViewModel view;

    /**
     * Please be aware of default timeouts:
     *  - implicit wait - 7 seconds
     *  - explicit wait - 15 seconds
     *
     *  Try to troubleshoot failing tests. Sometimes test is to blame. Sometimes explicit wait is required.
     *  But in most cases there is defect in target application to be fixed.
     */
    @Before
    public void setUp() throws Exception {
        browser = UI.newBrowser(BrowserType.CHROME);
        BrowserTab page = browser.open(getHtmlFile(PAGE));
        view = page.getGenericView();
    }

    @After
    public void tearDown() throws Exception {
        browser.close();
        UI.closeAllWindows();
    }

    @Test
    // TODO: make test passing (the button should be clicked finally without exceptions)
    public void regularButton() {
        Button regularButton = view.getButton("#foundAfterScrollingPageDownButtonWithSelectorTypo");
        assertThat(regularButton.getText()).isEqualTo("Click me");
        regularButton.click();
        assertThat(regularButton.getText()).isEqualTo("I was clicked");
    }

    @Test
    // TODO: make test passing (the button should be clicked finally without exceptions)
    public void slowButton() {
        Button unacceptablySlowButton = view.getButton("#slowButton");
        assertThat(unacceptablySlowButton.getText()).isEqualTo("I am slow");
        unacceptablySlowButton.click();
        assertThat(unacceptablySlowButton.getText()).isEqualTo("I was clicked");
        // hint: please use explicit wait with default timeout
    }

    @Test
    // TODO: make test passing (the button should be clicked finally without exceptions)
    public void verySlowButton() {
        Button unacceptablySlowButton = view.getButton("#verySlowButton");
        assertThat(unacceptablySlowButton.getText()).isEqualTo("I am very slow");
        unacceptablySlowButton.click();
        assertThat(unacceptablySlowButton.getText()).isEqualTo("I was clicked");
        // hint: please use explicit wait with custom timeout (usually you never have to do it in your testware)
    }

    @Test
    // TODO: make test passing (the button should be clicked finally without exceptions)
    // please do not use explicit wait as being a bad practice
    public void invisibleButton() {
        Button button = view.getButton("#invisibleButton");
//        assertThat(button.getText()).isEqualTo("I am invisible");
        button.click();
        assertThat(button.getText()).isEqualTo("I was clicked");
        // hint: in this case the problem should be fixed in target application - system under test
    }

    @Test
    // TODO: make test passing (the button should be clicked finally without exceptions)
    // please do not use explicit wait as being a bad practice
    public void hiddenButton() {
        Button button = view.getButton("#hiddenButton");
//        assertThat(button.getText()).isEqualTo("I am hidden");
        button.click();
        assertThat(button.getText()).isEqualTo("I was clicked");
    }

    @Test
    // TODO: make test passing (the button should be clicked finally without exceptions)
    // please do not use explicit wait as being a bad practice
    public void disabledButtonShouldNotBeClicked() {
        Button button = view.getButton("#disabledButton");
        assertThat(button.getText()).isEqualTo("I am disabled");
        button.click();
        assertThat(button.getText()).isEqualTo("I was clicked");
    }

    @Test
    // TODO: make test passing (the button should be clicked finally without exceptions)
    // please do not use explicit wait as being a bad practice
    public void outerButton() {
        Button button = view.getButton("#outerButton");
        assertThat(button.getText()).isEqualTo("I am located outside page");
        button.click();
        assertThat(button.getText()).isEqualTo("I was clicked");
    }

    @Test
    // TODO: make test passing (the button should be clicked finally without exceptions)
    // please do not use explicit wait as being a bad practice
    public void coveredButton() {
        Button button = view.getButton("#coveredButton");
        assertThat(button.getText()).isEqualTo("I am hidden behind other panel");
        button.click();
        assertThat(button.getText()).isEqualTo("I was clicked");
    }

    private String getHtmlFile(String fileName) {
        return "file:///" + new File("src/test/resources/html_pages/" + fileName).getAbsolutePath();
    }

}
