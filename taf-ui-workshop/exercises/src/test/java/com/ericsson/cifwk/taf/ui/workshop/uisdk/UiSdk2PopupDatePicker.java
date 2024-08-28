package com.ericsson.cifwk.taf.ui.workshop.uisdk;

import com.ericsson.cds.uisdk.compositecomponents.datepicker.PopupDatePicker;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.ViewModel;
import org.assertj.core.api.Assertions;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.Date;

import static com.ericsson.cifwk.taf.ui.core.SelectorType.XPATH;

public class UiSdk2PopupDatePicker extends BaseWorkshopTest {

    // TODO: open this page in browser and observe DOM structure of the component (using dev tools of your browser)
    public static final String PAGE = "https://arm1s11-eiffel004.eiffel.gic.ericsson.se:8443/nexus/content/sites/tor/widgets/latest/showcase/index.html#ui-showcase/selectors/popup-date-picker";

    // TODO: please specify XPATH selector for date picker container by text content
    // (hint: you could have a look at UiSdk1DatePicker example, you can also use specific HTML tag instead of '*' or generic '/..' instead of '/parent::div')
    public static final String POPUP_DATE_PICKER_CONTAINER = null;

    /**
     * This test demonstrates how to retrieve SPECIFIC Popup Date Picker on the page.
     */
    @Test
    public void drivePopupDatePickersWithoutViewModel() {
        BrowserTab browserTab = openDefaultPage();

        // using Generic View of the page
        ViewModel genericView = browserTab.getGenericView();

        // if there are several Date Pickers and you want to select specific one
        // then please use selector to specify subsection of the page to search for the component
        PopupDatePicker datePicker = genericView.getViewComponent(XPATH, POPUP_DATE_PICKER_CONTAINER, PopupDatePicker.class);

        // checking that current date is selected
        Date currentDate = new LocalDate().toDate();
        datePicker.selectDate(currentDate);
        Date selectedDate = datePicker.getSelectedDate();
        Assertions.assertThat(selectedDate).isEqualTo(currentDate);

        // selecting date
        Date tomorrow = new LocalDate().plusDays(1).toDate();
        // TODO: select this date that has been initialized

        
        // checking what is selected
        Assertions.assertThat(datePicker.getSelectedDate()).isEqualTo(tomorrow);
    }

    /**
     * This test demonstrates how to work with Popup Date Picker via custom View Model
     *
     * This way of selection is preferred over previous one.
     */
    @Test
    public void drivePopupDatePickerViaViewModel() {
        BrowserTab browserTab = openDefaultPage();

        // Using Custom View of the page
        // TODO: please complete view model implementation
        MyViewModel deliverySection = browserTab.getView(MyViewModel.class);

        // selecting delivery date
        Date expectedDeliveryDate = new LocalDate().plusDays(33).toDate();

        // TODO: set and check the date using view model "business" methods
        Date deliveryDate = null;
        Assertions.assertThat(deliveryDate).isEqualTo(expectedDeliveryDate);
    }

    private BrowserTab openDefaultPage() {
        return browser.open(getDefaultPage());
    }

    public static class MyViewModel extends GenericViewModel {

        // TODO: add UI component mapping
        private PopupDatePicker deliveryDatePicker;

        public Date getDeliveryDate() {
            return deliveryDatePicker.getSelectedDate();
        }

        public void setDeliveryDate(Date date) {
            deliveryDatePicker.selectDate(date);
        }

    }

}
