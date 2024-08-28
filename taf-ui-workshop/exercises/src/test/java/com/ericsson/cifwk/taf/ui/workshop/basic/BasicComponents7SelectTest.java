package com.ericsson.cifwk.taf.ui.workshop.basic;

import com.ericsson.cifwk.taf.ui.sdk.Option;
import com.ericsson.cifwk.taf.ui.sdk.Select;
import com.ericsson.cifwk.taf.ui.spi.UiActions;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.openqa.selenium.Keys;

import java.util.List;

public class BasicComponents7SelectTest extends BasicComponentsTest {

    // TODO: please open the page in your browser and review its DOM structure (using dev tools of your browser)
    public static final String PAGE = "basic-ui-components.html";

    /**
     * This test demonstrates operations with drop-down list.
     */
    @Test
    public void regularDropDown() {

        // initializing drop-down list (from Select Lists panel)
        Select select = view.getSelect("#regularSelect");

        // TODO: check how many options are available (via Select API)
        Integer options = null;
        Assertions.assertThat(options).isEqualTo(3);

        // selecting first option by TEXT
        select.selectByTitle("Option 1");
        Assertions.assertThat(select.getValue()).isEqualTo("option1");

        // TODO: select second option by VALUE


        Assertions.assertThat(select.getText()).isEqualTo("Option 2");
    }

    /**
     * This test demonstrates extra operations with multi-select list.
     */
    @Test
    public void multiSelectList() {

        // initializing multi-select list (from Select Lists panel)
        Select select = view.getSelect("#multiSelect");

        // selecting several values
        select.selectByValue("option1");
        select.selectByValue("option2");

        // getting selected options count
        Integer selectedOptions = select.getSelectedOptions().size();
        Assertions.assertThat(selectedOptions).isEqualTo(2);

        // TODO: clear all selections


        // getting selected options count
        selectedOptions = select.getSelectedOptions().size();
        Assertions.assertThat(selectedOptions).isEqualTo(0);
    }

    /**
     * This test demonstrates that there could be several ways to drive UI components.
     */
    @Test
    public void multipleSelectSimulation() {

        // initializing multi-select list (from Select Lists panel)
        Select select = view.getSelect("#multiSelect");
        List<Option> allOptions = select.getAllOptions();

        // TODO: select several values by clicking them
        for (Option option : allOptions) {

        }

        Assertions.assertThat(select.getSelectedOptions().size()).isEqualTo(3);

        // remove selection if any
        select.clearSelection();

        // selecting value by simulating mouse events
        for (Option option: allOptions) {

            // TODO: simulate mouse button down event


            // simulating mouse button up event
            option.mouseUp();
        }

        // TODO: check and correct how many items were actually selected
        Assertions.assertThat(select.getSelectedOptions().size()).isEqualTo(0);

        // remove selection if any
        select.clearSelection();

        // selecting value by simulating mouse and keyboard events
        UiActions uiActions = browserTab.newActionChain();
        for (Option option: allOptions) {
            uiActions
                    // simulating event of pressing of Ctrl button
                    .keyDown(Keys.CONTROL)

                    .click(option)

                    // TODO: simulate event of releasing of Ctrl button

                    ;

        }
        uiActions.perform();

        Assertions.assertThat(select.getSelectedOptions().size()).isEqualTo(3);
    }

}
