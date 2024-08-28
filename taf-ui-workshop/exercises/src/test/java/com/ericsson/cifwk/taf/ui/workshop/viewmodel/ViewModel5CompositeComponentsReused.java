package com.ericsson.cifwk.taf.ui.workshop.viewmodel;

import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.core.AbstractUiComponent;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.Label;
import com.google.common.base.Preconditions;
import org.junit.Test;

public class ViewModel5CompositeComponentsReused extends BaseWorkshopTest {

    /**
     * When doing tests, open this page in browser to understand its DOM structure (using dev tools of your browser)
     * TODO: please complete tasks inside the page source code, so you better see page structure
     */
    public static final String PAGE_FILE_NAME = "view-model-composite-components-reused.html";

    /**
     * This test demonstrates how composite component
     * could be reused on the same or different pages.
     */
    @Test
    public void reusableCompositeComponents() {
        BrowserTab browserTab = openDefaultPage();

        // let's imagine that this pojo came as test step parameter from data source
        PersonPojo person = new PersonPojo("John", "Kingerlee");
        AddressPojo address = new AddressPojo("Athlone", "Grace Road", "ZIP-123");
        person.setHomeAddress(address);
        person.setWorkAddress(address);

        // filling in the form
        // TODO: please implement MyPage View Model
        MyPage page = browserTab.getView(MyPage.class);
        page.setCompanyAddress(address);
        page.setEmployee(person);
        page.setManager(person);

        // making sure that every section gets filled with some data
        page.register();
        page.checkNotification();
    }

    // This is my page definition
    public static class MyPage extends GenericViewModel {

        @UiComponentMapping("#companyForm")
        private AddressForm addressForm;

        // TODO: please implement PersonComponent and map it
        private PersonComponent employee;

        // TODO: please map it
        private PersonComponent manager;

        @UiComponentMapping("#registerButton")
        private Button registerButton;

        @UiComponentMapping("#notificationsContainer")
        private Label notification;

        public void setCompanyAddress(AddressPojo address) {
            // TODO: complete task inside AddressForm (you shouldn't change HTML in this case)
            addressForm.setAddress(address);
        }

        public void setEmployee(PersonPojo person) {
            employee.setPerson(person);
        }

        public void setManager(PersonPojo person) {
            manager.setPerson(person);
        }

        public void register() {
            registerButton.click();
        }

        public void checkNotification() {
            String registrationResult = notification.getText();
            Preconditions.checkState("OK".equals(registrationResult), "Not all fields were filled: " + registrationResult);
        }
    }

    // TODO: please extract this class into separate file, so it could be reused
    public static class PersonComponent extends AbstractUiComponent {

        @UiComponentMapping(".person")
        private PersonalDataForm names;

        // TODO: please map it
        private AddressForm homeAddress;

        // TODO: please map it
        private AddressForm workAddress;

        public void setPerson(PersonPojo person) {
            names.setUserNames(person.getFirstName(), person.getLastName());
            homeAddress.setAddress(person.getHomeAddress());
            // TODO: please make this method work all the time (without try-catching)
            workAddress.setAddress(person.getWorkAddress());
        }
    }


    private BrowserTab openDefaultPage() {
        return browser.open(getHtmlFile(PAGE_FILE_NAME));
    }

}
