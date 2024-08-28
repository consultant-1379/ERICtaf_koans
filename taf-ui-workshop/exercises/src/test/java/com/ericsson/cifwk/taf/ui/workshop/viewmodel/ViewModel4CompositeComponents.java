package com.ericsson.cifwk.taf.ui.workshop.viewmodel;

import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.core.UiComponent;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.Label;
import com.ericsson.cifwk.taf.ui.sdk.ViewModel;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ViewModel4CompositeComponents extends BaseWorkshopTest {

    /**
     * When doing tests, open this page in browser to understand its DOM structure (using dev tools of your browser)
     */
    public static final String PAGE_FILE_NAME = "view-model-composite-components.html";

    /**
     * This test demonstrates composite components basic usage.
     * Note: generic view usage in testware is discouraged, used here just for test simplicity.
     */
    @Test
    public void compositeComponentsFromGenericView() {
        BrowserTab browserTab = openDefaultPage();

        // let's imagine that this pojo came as test step parameter from data source
        AddressPojo address = new AddressPojo("Athlone", "Grace Road");

        // in this example we will use generic view
        ViewModel genericView = browserTab.getGenericView();

        // we can select page sub-region (to bind to our composite component later)
        UiComponent deliveryAddressSection = genericView.getViewComponent("#deliveryAddress");

        // by this code we bind page sub-region to our custom component
        // (.as() method is UI component casting, i.e. remapping of HTML elements behind current UI Component to fields of given UI Component class)
        // (alternatively you can define target UI Component class right in the view model method receiving component -
        // AddressForm deliveryAddressForm = genericView.getViewComponent("#deliveryAddress", AddressForm.class);)
        // TODO: please observe AddressForm javadoc and implementation
        AddressForm deliveryAddressForm = deliveryAddressSection.as(AddressForm.class);

        // TODO: please initialize billing address component in similar way
        AddressForm billingAddressForm = null;

        // using same address as delivery and billing address
        deliveryAddressForm.setAddress(address);
        billingAddressForm.setAddress(address);

        // submitting the form
        SubmitFormView submitForm = browserTab.getView(SubmitFormView.class);
        submitForm.submit();

        // checking there is just one notification
        List<String> notifications = submitForm.getNotifications();
        Assertions.assertThat(notifications).hasSize(1);
        String notification = notifications.iterator().next();
        Assertions.assertThat(notification).isEqualTo("Registration completed");
    }

    /**
     * This test demonstrates full screen definition in view model.
     *
     * All UI logic is encapsulated and hidden behind view model API.
     */
    @Test
    public void compositeComponentsFromCustomView() {
        BrowserTab browserTab = openDefaultPage();

        // let's imagine that this pojo came as test step parameter from data source
        AddressPojo address = new AddressPojo("Athlone", "Grace Road");

        // in this example we use view model to define whole page
        // TODO: please complete implementation of UserRegistrationPage
        UserRegistrationPage page = browserTab.getView(UserRegistrationPage.class);

        // using page object "business methods"
        Assertions.assertThat(page.hasValidationErrors()).isFalse();
        Assertions.assertThat(page.isRegistrationCompleted()).isFalse();

        // trying to submit just one address
        page.setDeliveryAddress(address);
        page.submitForm();

        // using page object "business methods"
        Assertions.assertThat(page.hasValidationErrors()).isTrue();
        Assertions.assertThat(page.isRegistrationCompleted()).isFalse();

        // submitting proper form
        page.setBillingAddress(address);
        page.submitForm();

        // checking successful notification
        Assertions.assertThat(page.hasValidationErrors()).isFalse();
        Assertions.assertThat(page.isRegistrationCompleted()).isTrue();
    }

    public static class SubmitFormView extends GenericViewModel {

        @UiComponentMapping("#registerClientButton")
        private Button submitButton;

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

    public static class UserRegistrationPage extends GenericViewModel {

        // TODO: please map it
        private AddressForm deliveryAddressForm;

        @UiComponentMapping("#billingAddress")
        private AddressForm billingAddressForm;

        @UiComponentMapping("#registerClientButton")
        private Button submitButton;

        @UiComponentMapping(".notification")
        private List<Label> notifications;

        public void setDeliveryAddress(AddressPojo deliveryAddress) {
            // TODO: please implement it
        }

        public void setBillingAddress(AddressPojo billingAddress) {
            billingAddressForm.setAddress(billingAddress);
        }

        public void submitForm() {
            submitButton.click();
        }

        // you can have more logic inside your View Model
        public boolean isRegistrationCompleted() {
            return notifications.size() == 1 && notifications.get(0).getText().equals("Registration completed");
        }

        public boolean hasValidationErrors() {
            return getNotifications().size() > 1;
        }

        /**
         * @return empty list if there are no notifications
         */
        // TODO: you don't need to return notification messages once you have higher
        // TODO:     abstraction methods (like isRegistrationCompleted and hasValidationErrors)
        // TODO:     Please remove this method from view model's public API
        public List<String> getNotifications() {
            return newArrayList(Collections2.transform(notifications, new Function<Label, String>(){
                @Override
                public String apply(Label input) {
                    return input.getText();
                }
            }));
        }
    }

    private BrowserTab openDefaultPage() {
        return browser.open(getHtmlFile(PAGE_FILE_NAME));
    }

}
