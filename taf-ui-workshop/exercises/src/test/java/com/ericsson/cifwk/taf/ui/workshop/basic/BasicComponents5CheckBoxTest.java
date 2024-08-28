package com.ericsson.cifwk.taf.ui.workshop.basic;

import com.ericsson.cifwk.taf.ui.sdk.CheckBox;
import com.ericsson.cifwk.taf.ui.sdk.Label;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BasicComponents5CheckBoxTest extends BasicComponentsTest {

    // TODO: please open the page in your browser and review its DOM structure (using dev tools of your browser)
    public static final String PAGE = "basic-ui-components.html";

    /**
     * This test demonstrates that there are usually multiple ways of executing UI activity.
     */
    @Test
    public void regularCheckBox() {

        // initializing first check box "Button" (from Checkboxes panel)
        CheckBox buttonLearnedCheckBox = view.getCheckBox("#buttonCheckBox");
        Assertions.assertThat(buttonLearnedCheckBox.isSelected()).isTrue();

        // TODO: uncheck the checkbox (via CheckBox API)


        Assertions.assertThat(buttonLearnedCheckBox.isSelected()).isFalse();

        Label buttonLearnedLabel = view.getLabel("#labelForButtonCheckBox");
        Assertions.assertThat(buttonLearnedLabel.getId()).isEqualTo("labelForButtonCheckBox");

        // TODO: check the checkbox (via according label clicking)


        Assertions.assertThat(buttonLearnedCheckBox.isSelected()).isTrue();

        // TODO: uncheck the checkbox (via checkbox clicking)


        Assertions.assertThat(buttonLearnedCheckBox.isSelected()).isFalse();

        CheckBox checkBoxLearned = view.getCheckBox("#checkBox");
        checkBoxLearned.select();
    }

}
