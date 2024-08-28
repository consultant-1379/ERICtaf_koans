package com.ericsson.cifwk.taf.ui.workshop.viewmodel;

import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.Label;
import com.ericsson.cifwk.taf.ui.sdk.TextBox;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ViewModel2MultipleViews extends BaseWorkshopTest {

    /**
     * When doing tests, open this page in browser to understand its DOM structure (using dev tools of your browser)
     */
    public static final String PAGE_FILE_NAME = "view-model-multiple.html";

    /**
     * This test demonstrates that there could be multiple logic views
     * representing different sections of the page.
     *
     * It might look an over-engineering to split page into several view models.
     * But it could help you to focus on just single section of the page,
     * understand dependencies on other sections (and, as a result, come up with better page design).
     *
     * You can also use view models as page definition building blocks
     * and REUSE them in different pages or even in different applications
     * (e.g.if some complex common component is used in both applications).
     */
    @Test
    public void viewModelApproach() {
        BrowserTab browserTab = openDefaultPage();

        // let's imagine that this pojo came as test step parameter from data source
        AddressPojo address = new AddressPojo("Athlone", "Grace Road");

        // TODO: please go to PersonalDataSection class to complete implementation
        PersonalDataSection personalDataSection = browserTab.getView(PersonalDataSection.class);
        personalDataSection.setUserNames("John", "Kingerlee");

        // TODO: please go to AddressForm class to complete implementation
        AddressForm addressForm = browserTab.getView(AddressForm.class);
        addressForm.setAddress(address);

        // TODO: please go to SubmitForm class to complete implementation
        SubmitForm submitForm = browserTab.getView(SubmitForm.class);
        submitForm.submit();

        // TODO: check notification message
        Assertions.assertThat(false).isTrue();
    }


    public static class PersonalDataSection extends GenericViewModel {

        @UiComponentMapping("#firstName")
        private TextBox firstName;

        @UiComponentMapping("#lastName")
        private TextBox lastName;

        public void setUserNames(String firstName, String lastName) {
            // TODO: please implement this method
        }

    }

    public static class AddressForm extends GenericViewModel {

        @UiComponentMapping("#city")
        private TextBox city;

        @UiComponentMapping("#street")
        private TextBox street;

        @UiComponentMapping("#zip")
        private TextBox zip;

        public void setAddress(AddressPojo address) {
            // TODO: please implement this method
        }

    }

    public static class SubmitForm extends GenericViewModel {

        @UiComponentMapping("#registerClientButton")
        private Button submitButton;

        @UiComponentMapping(".notification")
        private Label notification;

        public void submit() {
            // TODO: please implement this method
        }

        public boolean hasNotification() {
            // TODO: please implement this method
            return false;
        }

        public String getNotification() {
            // TODO: please implement this method
            return null;
        }

    }

    private BrowserTab openDefaultPage() {
        return browser.open(getHtmlFile(PAGE_FILE_NAME));
    }

}
