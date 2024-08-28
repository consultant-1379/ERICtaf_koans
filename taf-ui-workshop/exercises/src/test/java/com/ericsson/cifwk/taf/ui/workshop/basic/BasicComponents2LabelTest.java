package com.ericsson.cifwk.taf.ui.workshop.basic;

import com.ericsson.cifwk.taf.ui.core.SelectorType;
import com.ericsson.cifwk.taf.ui.sdk.Label;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BasicComponents2LabelTest extends BasicComponentsTest {

    // TODO: please open the page in your browser and review its DOM structure (using dev tools of your browser)
    public static final String PAGE = "basic-ui-components.html";

    /**
     * This test demonstrates that any TAF UI Component (including Label.java) is clickable.
     */
    @Test
    public void clickableLabel() {
        Label label = view.getLabel("span.button");

        // please DO NOT MODIFY existing code if there is no to-do comments right above the line
        Assertions.assertThat(label.getText()).isEqualTo("I am clickable label");

        // TODO: try click the label


        Assertions.assertThat(label.getText()).isEqualTo("I was clicked");
    }

    /**
     * This test demonstrates that TAF UI component Label could be initialized from other HTML tags as well.
     */
    @Test
    public void everythingIsLabel() {
        Label label = view.getLabel(".link.button");
        Assertions.assertThat(label.getText()).isEqualTo("I am link button");

        // TODO: try click the label


        Assertions.assertThat(label.getText()).isEqualTo("I was clicked");
    }

    /**
     * This test demonstrates that TAF UI component Label could be initialized even from buttons HTML elements and
     * still could be clickable and proper label of the button could be retrieved.
     */
    @Test
    public void buttonsAreLabelsAsWell() {

        Label label = view.getLabel("#inputButton");
        Assertions.assertThat(label.getText()).isEqualTo("I am input of button type");
        label.click();
        Assertions.assertThat(label.getText()).isEqualTo("I was clicked");

        // TODO: initialize button #1 as label (from Buttons panel) using CSS selector for <button> tag
        label = null;

        Assertions.assertThat(label.getText()).isEqualTo("I am button tag");
        label.click();
        Assertions.assertThat(label.getText()).isEqualTo("I was clicked");
    }

    /**
     * This test demonstrates that formatting tags don't affect label content.
     */
    @Test
    public void formattedContent() {


        // initializing label containing bold and italic strings (from Labels panel) using XPATH selector by content
        final String CONTENT = "Formatted content";
        Label label = view.getLabel(SelectorType.XPATH, "//div[contains(text(),'" + CONTENT + "')]");

        // TODO: please try to guess before execution what actual content label will have (and write String constant below)
        String content = null;

        Assertions.assertThat(label.getText()).isEqualTo(content);
    }

    /**
     * This test demonstrates that getText() method aggregates content of child HTML elements.
     */
    @Test
    public void aggregatedContent() {

        // TODO: initialize label containing nested span HTML element (from Labels panel) using XPATH selector by content + parent selector
        final String CONTENT = "nested content";
        Label label = view.getLabel(SelectorType.XPATH, "");

        // checking that label aggregates content of child HTML elements
        String labelContent = "Nested content containing nested content.";
        Assertions.assertThat(label.getText()).isEqualTo(labelContent);
    }

}
