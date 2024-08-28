package com.ericsson.cifwk.taf.ui.workshop.uisdk;

import com.ericsson.cds.uisdk.compositecomponents.datepicker.DatePicker;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.ViewModel;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.ericsson.cifwk.taf.ui.core.SelectorType.XPATH;

public class UiSdk1DatePicker extends BaseWorkshopTest {

    /**
     * In this exercise you will need to have a selenium binary for chrome download the latest release
     * from here: https://chromedriver.storage.googleapis.com/index.html
     * when running Ui tests locally you must use
     * the command -Dwebdriver.chrome.driver="Path/to/binary.exe" to specify the path to the selenium binary
     * in the VM options: when you edit configurations.
     */

    // TODO: open this page in browser and observe DOM structure of the component (using dev tools of your browser)
    public static final String PAGE = "https://arm1s11-eiffel004.eiffel.gic.ericsson.se:8443/nexus/content/sites/tor/widgets/latest/showcase/index.html#ui-showcase/selectors/date-picker";

    public static final String CONTAINS_TEXT = "//*[contains(text(),'";

    public static final String SIBLING_TEXT = "The Date Picker widget allows a day";

    public static final String GET_PARENT = "')]/parent::div";

    public static final String DATE_PICKER_CONTAINER = CONTAINS_TEXT + SIBLING_TEXT + GET_PARENT;

    /**
     * This test demonstrates how to retrieve ANY Date Picker on the page.
     */
    @Test
    public void driveDatePickerWithoutViewModel() {
        BrowserTab browserTab = openDefaultPage();

        // using Generic View of the page
        ViewModel genericView = browserTab.getGenericView();

        // just taking the first Date Picker on the page (if there are several ones)
        DatePicker datePicker = genericView.getViewComponent("body", DatePicker.class);

        // since this Date Picker component is bound to body tag
        // focus() method tries to make visible beginning of the page
        // TODO: please check if focus() method does what you expect (NO code modification is required)
        datePicker.focus();

        // checking that current date is selected
        Assertions.assertThat(datePicker.getSelectedDay()).isGreaterThan(0);
    }

    /**
     * This test demonstrates how to retrieve SPECIFIC Date Picker on the page.
     *
     * This way of selection is preferred over previous one.
     */
    @Test
    public void driveDatePickersWithoutViewModel() {
        BrowserTab browserTab = openDefaultPage();

        // using Generic View of the page
        ViewModel genericView = browserTab.getGenericView();

        // if there are several Date Pickers and you want to select specific one
        // then please use selector to specify subsection of the page to search for the component
        DatePicker datePicker = genericView.getViewComponent(XPATH, DATE_PICKER_CONTAINER, DatePicker.class);

        // in this case date picker component is bound to specific subsection of the page
        // TODO: check if focus() method does what you expect
        datePicker.focus();

        // checking that current date is selected
        Assertions.assertThat(datePicker.getSelectedDay()).isGreaterThan(0);
    }

    /**
     * This test demonstrates how to work with Date Picker via custom View Model
     *
     * This way of selection is preferred over previous one.
     */
    @Test
    public void driveDatePickerViaViewModel() {
        BrowserTab browserTab = openDefaultPage();

        // Using Custom View of the page
        MyViewModel deliverySection = browserTab.getView(MyViewModel.class);
        deliverySection.setDeliveryDay(21);

        // checking day selection
        Assertions.assertThat(deliverySection.getDeliveryDay()).isEqualTo(21);
    }

    private BrowserTab openDefaultPage() {
        return browser.open(getDefaultPage());
    }

    public static class MyViewModel extends GenericViewModel {

        @UiComponentMapping(selectorType = XPATH, selector = DATE_PICKER_CONTAINER)
        private DatePicker deliveryDatePicker;

        public int getDeliveryDay() {
            return deliveryDatePicker.getSelectedDay();
        }

        public void setDeliveryDay(int dayOfMonth) {
            deliveryDatePicker.selectDay(dayOfMonth);
        }

    }

}
