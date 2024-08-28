package com.ericsson.cifwk.taf.ui.workshop.basic;

import com.ericsson.cifwk.taf.ui.sdk.RadioButton;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BasicComponents6RadioButtonTest extends BasicComponentsTest {

    // TODO: please open the page in your browser and review its DOM structure (using dev tools of your browser)
    public static final String PAGE = "basic-ui-components.html";

    /**
     * This test demonstrates operations with radio buttons.
     */
    @Test
    public void regularRadioButton() {
        RadioButton radioButton = view.getRadioButton("#radioButton");
        Assertions.assertThat(radioButton.getValue()).isEqualTo("value 2");
        Assertions.assertThat(radioButton.isSelected()).isFalse();

        // TODO: select RadioButton radio


        Assertions.assertThat(radioButton.isSelected()).isTrue();
    }

}
