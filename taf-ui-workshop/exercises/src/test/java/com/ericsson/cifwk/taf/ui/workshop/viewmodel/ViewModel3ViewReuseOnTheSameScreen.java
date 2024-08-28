package com.ericsson.cifwk.taf.ui.workshop.viewmodel;

import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.Label;
import com.ericsson.cifwk.taf.ui.sdk.TextBox;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ViewModel3ViewReuseOnTheSameScreen extends BaseWorkshopTest {

    /**
     * When doing tests, open this page in browser to understand its DOM structure (using dev tools of your browser)
     */
    public static final String PAGE_FILE_NAME = "view-model-composite-components.html";

    /**
     * This test demonstrates that View Models can't be reused on the same screen.
     */
    @Test
    public void itShouldNotWork() {
        BrowserTab browserTab = openDefaultPage();

        // let's imagine that this pojo came as test step parameter from data source
        AddressPojo address = new AddressPojo("Athlone", "Grace Road");

        // TODO: try to REUSE THE SAME View Model class (EITHER AddressFormViewById OR AddressFormViewByClass) for BOTH Delivery AND Billing addresses
        AcceptsAddress deliveryAddressForm = null;
        AcceptsAddress billingAddressForm = null;

        // using same address as delivery and billing address
        deliveryAddressForm.setAddress(address);
        billingAddressForm.setAddress(address);

        // submitting the form
        SubmitFormView submitForm = browserTab.getView(SubmitFormView.class);
        submitForm.submit();

        //TODO: understand why it's not working, no code update is required (we will try to solve similar task using Composite Components in next exercises)
        // checking there is just one notification
        List<String> notifications = submitForm.getNotifications();
        Assertions.assertThat(notifications).hasSize(1);
        String notification = notifications.iterator().next();
        Assertions.assertThat(notification).isEqualTo("Registration completed");
    }

    /**
     * This interface is for convenience only. It is not required for TAF UI.
     */
    public interface AcceptsAddress {
        void setAddress(AddressPojo address);
    }

    public static class AddressFormViewById extends GenericViewModel implements AcceptsAddress {

        @UiComponentMapping("#field1")
        private TextBox city;

        @UiComponentMapping("#field2")
        private TextBox street;

        @UiComponentMapping("#field3")
        private TextBox zip;

        public void setAddress(AddressPojo address) {
            city.setText(address.getCity());
            street.setText(address.getStreet());
            zip.setText(address.getZip());
        }

    }

    public static class AddressFormViewByClass extends GenericViewModel implements AcceptsAddress {

        @UiComponentMapping(".city")
        private TextBox city;

        @UiComponentMapping(".street")
        private TextBox street;

        @UiComponentMapping(".zip")
        private TextBox zip;

        public void setAddress(AddressPojo address) {
            city.setText(address.getCity());
            street.setText(address.getStreet());
            zip.setText(address.getZip());
        }

    }

    public static class SubmitFormView extends GenericViewModel {

        @UiComponentMapping("#registerClientButton")
        private Button submitButton;

        // please notice that multiple notifications are mapped in the same manner are single components
        @UiComponentMapping(".notification")
        private List<Label> notifications;

        public void submit() {
            submitButton.click();
        }

        /**
         * @return empty list if there are no notifications
         */
        public List<String> getNotifications() {
            List<String> notificationsContent = newArrayList();
            for (Label notification : notifications) {
                notificationsContent.add(notification.getText());
            }
            return notificationsContent;
        }

    }

    private BrowserTab openDefaultPage() {
        return browser.open(getHtmlFile(PAGE_FILE_NAME));
    }

}
