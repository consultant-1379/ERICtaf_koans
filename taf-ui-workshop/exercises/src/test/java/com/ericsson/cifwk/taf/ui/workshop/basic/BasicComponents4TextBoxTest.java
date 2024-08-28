package com.ericsson.cifwk.taf.ui.workshop.basic;

import com.ericsson.cifwk.taf.ui.sdk.TextBox;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BasicComponents4TextBoxTest extends BasicComponentsTest {

    // TODO: please open the page in your browser and review its DOM structure (using dev tools of your browser)
    public static final String PAGE = "basic-ui-components.html";

    /**
     * This test demonstrates operations with text fields.
     */
    @Test
    public void regularTextBox() {

        // TODO: initialize regular text box (from regular text fields panel) using CSS selector - attribute value
        TextBox textBox = null;

        // please DO NOT MODIFY existing code if there is no to-do comments right above the line
        Assertions.assertThat(textBox.getText()).isEqualTo("Regular text field");

        // TODO: clean content by calling according API


        Assertions.assertThat(textBox.getText()).isEmpty();
        textBox.setText("text");
        Assertions.assertThat(textBox.getText()).isEqualTo("text");
    }

    /**
     * This test demonstrates that password and hidden fields could still be accessed.
     */
    @Test
    public void password() {

        // TODO: initialize password box (from regular text fields panel) using CSS selector by input type
        TextBox textBox = null;

        textBox.setText("PasswOrd1");
        Assertions.assertThat(textBox.getText()).isEqualTo("PasswOrd1");

        // initializing hidden text box right below password field (from regular text fields panel)
        TextBox hiddenField = view.getTextBox("#hiddenTextBox");
        Assertions.assertThat(hiddenField.getText()).isEqualTo("Hidden text field");
    }

    /**
     * This test demonstrates that NOT ALL values are accepted by target fields.
     */
    @Test
    public void numberField() {

        // initializing number field containing value 42 (from other text fields panel)
        TextBox numberBox = view.getTextBox("#numberBox");
        Assertions.assertThat(numberBox.getText()).isEqualTo("42");

        // TODO: try to set text value (containing alphabet symbols) into number field


        Assertions.assertThat(numberBox.getText()).isEqualTo("");
        numberBox.setText("314");
        Assertions.assertThat(numberBox.getText()).isEqualTo("314");
    }

}
