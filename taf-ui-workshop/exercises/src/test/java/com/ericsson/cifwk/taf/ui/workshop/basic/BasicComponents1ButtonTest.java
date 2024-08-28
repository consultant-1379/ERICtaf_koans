package com.ericsson.cifwk.taf.ui.workshop.basic;

import com.ericsson.cifwk.taf.ui.sdk.Button;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import sun.misc.VM;

public class BasicComponents1ButtonTest extends BasicComponentsTest {

    /**
     * In this exercise you will need to have a selenium binary for chrome download the latest release
     * from here: https://chromedriver.storage.googleapis.com/index.html
     * when running Ui tests locally you must use
     * the command -Dwebdriver.chrome.driver="Path/to/binary.exe" to specify the path to the selenium binary
     * in the VM options: when you edit configurations.
     */

    // TODO: please open the page in your browser and review its DOM structure (using dev tools of your browser)
    public static final String PAGE = "basic-ui-components.html";

    /**
     * This test demonstrates initializing Button component from generic view and
     * asserting the title of the button using AssertJ assertions.
     */
    @Test
    public void regularButton() {

        // initializing button #1 using CSS selector for <button> tag
        Button button = view.getButton("button");

        // please DO NOT MODIFY existing code if there is no to-do comments right above the line
        Assertions.assertThat(button.getText()).isEqualTo("I am button tag");

        // TODO: click the button


        // TODO: ASSERT that title of the button has been changed
        String buttonTitle = null;
        Assertions.assertThat(buttonTitle).isEqualTo("I was clicked");
    }

    /**
     * This test demonstrates there could be other implementation of the button on HTML level and
     * that TAF framework works with both of them.
     */
    @Test
    public void inputButton() {

        // TODO: initialize button #2 (from Buttons panel) using element id
        Button button = null;

        Assertions.assertThat(button.getText()).isEqualTo("I am input of button type");
        button.click();

        // TODO: assert that title of the button has been changed

    }

    /**
     * This test demonstrates that HTML anchor (tag &lt;a&gt;) could be treated as button as well.
     */
    @Test
    public void linkButton() {

        // TODO: initialize button #3 (from Buttons panel) using element classes selector
        Button button = null;

        Assertions.assertThat(button.getText()).isEqualTo("I am link button");
        button.click();
        Assertions.assertThat(button.getText()).isEqualTo("I was clicked");
    }

    /**
     * This test demonstrates that any clickable HTML element could be treated as button.
     */
    @Test
    public void clickableLabel() {

        // TODO: initialize button #4 (from Buttons panel) using combination of tag and class selectors
        Button button = null;

        Assertions.assertThat(button.getText()).isEqualTo("I am clickable label");
        button.click();
        Assertions.assertThat(button.getText()).isEqualTo("I was clicked");
    }

}
