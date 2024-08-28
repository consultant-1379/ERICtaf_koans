package com.ericsson.cifwk.taf.ui.workshop.uisdk;

import com.ericsson.cds.uisdk.compositecomponents.UiSdkDropDownMenu;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.core.SelectorType;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.MessageBox;
import com.ericsson.cifwk.taf.ui.sdk.TextBox;
import org.junit.Test;

import java.util.List;

import static com.ericsson.cifwk.taf.ui.workshop.uisdk.UiSdk5DropDownTest.MyViewModel.*;
import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * @author Mihails Volkovs mihails.volkovs@ericsson.com
 *         Date: 06.07.2016
 */
public class UiSdk5DropDownTest extends BaseWorkshopTest {

    // TODO: open this page in browser and observe DOM structure of the component (using dev tools of your browser)
    public static final String PAGE = "https://arm1s11-eiffel004.eiffel.gic.ericsson.se:8443/nexus/content/sites/tor/widgets/latest/showcase/index.html#ui-showcase/selectors/dropdown";

    private BrowserTab browserTab;

    /**
     * This test demonstrates basic operations with drop down via custom View Model as well as handling alert popups.
     */
    @Test
    public void dropDown() {

        BrowserTab browserTab = browser.open(PAGE);

        // Using Custom View of the page
        // TODO: please complete tasks inside MyViewModel class
        MyViewModel actionsSection = browserTab.getView(MyViewModel.class);

        // checking drop down content
        List<String> actions = actionsSection.getActions();
        assertThat(actions).containsExactly(ITEM1, ITEM2, ITEM3);

        // selecting drop down option
        actionsSection.triggerFirstAction();

        // closing message box
        MessageBox alert = browserTab.getMessageBox();
        assertEquals("message 1", alert.getText());
        alert.clickOk();

        // Sometimes there are issues in compatibility of newest versions of Browser and Web Driver. If that is the case please upgrade Web Driver as defined below
        // TODO: to make it working in newest Chrome please upgrade Wev Driver binary (located in exercises/target/selenium-drivers/ folder)
        // E.g. instead of version 2.19 please use newer version, e.g. 2.22 or 2.24.
        // Please download driver for your system, unzip it and rename the binary:
        //   http://chromedriver.storage.googleapis.com/2.19/chromedriver_linux32.zip -> chrome-linux-x32
        //   http://chromedriver.storage.googleapis.com/2.19/chromedriver_linux64.zip -> chrome-linux-x64
        //   http://chromedriver.storage.googleapis.com/2.19/chromedriver_mac32.zip -> chrome-mac-x32
        //   http://chromedriver.storage.googleapis.com/2.19/chromedriver_win32.zip -> chrome-win-x32

    }

    /**
     * This test demonstrates how nested components could be mapped by content.
     */
    @Test
    public void generateComponent() {

        browserTab = browser.open(PAGE);

        // Using Custom View of the page
        // TODO: please complete tasks inside MyViewModel class
        MyViewModel actionsSection = browserTab.getView(MyViewModel.class);

        // checking options before widget update
        assertThat(actionsSection.getActions()).containsExactly(ITEM1, ITEM2, ITEM3);

        // updating widget
        actionsSection.generateWidget();

        // checking options again
        assertThat(actionsSection.getActions()).containsExactly("Message 1", "Message 2", "Message 3");
    }

    public static class MyViewModel extends GenericViewModel {

        public static final String ITEM1 = "Alert \"message 1\"";

        public static final String ITEM2 = "Alert \"message 2\"";

        public static final String ITEM3 = "Alert \"message 3\"";

        public static final String DROP_DOWN_CONTAINER = "//div[contains(text(),'The Button Dropdown widget')]/..";

        public static final String TRY_BUTTON_XPATH = DROP_DOWN_CONTAINER + "//span[@e-id=\"edit\"]";

        // TODO: please map 'Apply' button
        // note: this could be done later before executing 'generateComponent()' test
        public static final String APPLY_BUTTON_XPATH = "//*";

        public static final String CODE_INPUT_XPATH = DROP_DOWN_CONTAINER + "//code[@e-id=\"code\"]";

        @UiComponentMapping(selectorType = SelectorType.XPATH, selector = DROP_DOWN_CONTAINER)
        private UiSdkDropDownMenu myDropDown;

        @UiComponentMapping(selectorType = SelectorType.XPATH, selector = TRY_BUTTON_XPATH)
        private Button tryButton;

        @UiComponentMapping(selectorType = SelectorType.XPATH, selector = APPLY_BUTTON_XPATH)
        private Button applyButton;

        @UiComponentMapping(selectorType = SelectorType.XPATH, selector = CODE_INPUT_XPATH)
        private TextBox codeTextBox;

        /**
         * Returns all options for "Actions"
         */
        public List<String> getActions() {

            // TODO: please implement this
            return newArrayList();
        }

        /**
         * Triggers first action from list of of "Actions"
         */
        public void triggerFirstAction() {

            // TODO: please implement it
        }

        /**
         * Generates simple widget dynamically
         */
        public void generateWidget() {

            // edit mode
            tryButton.click();

            // update code
            codeTextBox.clear();
            codeTextBox.setText("var dropdownWidget = new Dropdown({\n" +
                    "    caption: 'Messages',\n" +
                    "    items: [\n" +
                    "        {name: 'Message 1'},\n" +
                    "        {name: 'Message 2'},\n" +
                    "        {name: 'Message 3'},\n" +
                    "    ]\n" +
                    "});\n" +
                    "\n" +
                    "dropdownWidget.attachTo(this.getElement());");

            // apply changes
            applyButton.click();
        }

    }

}
