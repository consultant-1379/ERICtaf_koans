package com.ericsson.cifwk.taf.ui.workshop.viewmodel;

import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.Label;
import com.ericsson.cifwk.taf.ui.sdk.TextBox;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static java.lang.String.format;

public class ViewModel1Basics extends BaseWorkshopTest {

    /**
     * When doing tests, open this page in browser to understand its DOM structure (using dev tools of your browser)
     */
    public static final String PAGE_FILE_NAME = "view-model-simple.html";


    /**
     * This test demonstrates new approach of fetching TAF UI components
     * using Custom View (object with annotated fields, which are bound to according selectors).
     */
    @Test
    public void viewModelApproach() {
        BrowserTab browserTab = openDefaultPage();

        // Using Custom View of the page
        // TODO: please go to MyPage class to review implementation details
        MyPage customView = browserTab.getView(MyPage.class);

        // retrieving user name field (from custom view)
        TextBox userNameField = customView.getUserNameField();

        // setting user name
        String userName = "Student X";
        userNameField.setText(userName);

        // TODO: retrieve notification label (from custom view)
        Label notification = null;

        // optionally checking that there is no notification
        Assertions.assertThat(notification.isDisplayed()).isFalse();

        // retrieving submit button (from custom view) and clicking it
        Button submitButton = customView.getSubmitButton();
        submitButton.click();

        // optionally checking that there is notification
        Assertions.assertThat(notification.isDisplayed()).isTrue();
        Assertions.assertThat(notification.getText()).isEqualTo(format("Thank you, %s!", userName));
    }

    /**
     * This test demonstrates the power of View Models.
     * You can hide logic of working with UI components behind clean API (your custom view model).
     *
     * Usually your View Model shouldn't have getters to UI components,
     * but implement user/business actions inside custom View Model.
     */
    @Test
    public void viewModelEnriched() {
        BrowserTab browserTab = openDefaultPage();

        // Using Custom View of the page
        // TODO: please go to MyFeatureRichPage class to review (and complete) its implementation
        MyFeatureRichPage userForm = browserTab.getView(MyFeatureRichPage.class);

        // optionally checking that there is NO notification
        // TODO: please notice that there is no notification component at this moment, but we still can execute visibility check method on that component, no code update is required
        Assertions.assertThat(userForm.hasNotification()).isFalse();

        // setting user name and submitting the form
        String userName = "Student X";
        userForm.setUserName(userName);
        userForm.submit();

        // optionally checking that there is notification
        // TODO: please notice that userForm is aware of page update and it SHOULD NOT be REinitialized, no code update is required
        Assertions.assertThat(userForm.hasNotification()).isTrue();

        // checking notification CONTENT
        Assertions.assertThat(userForm.getNotification()).isEqualTo(format("Thank you, %s!", userName));

        // TODO: please compare test code with previous one (and observe how much cleaner it became), no code update is required
    }

    /**
     * This is first version of our page view.
     *
     * Fields will be fetched automatically using selectors in annotation parameter.
     *
     * Fields will be fetched lazily. It means that you can select element
     * which currently (at the moment of View object creation) doesn't exist.
     * It will be searched on the page on first UI activity (with the component).
     */
    public static class MyPage extends GenericViewModel {

        @UiComponentMapping("#userName")
        private TextBox userNameField;

        @UiComponentMapping("#submitButton")
        private Button submitButton;

        // TODO: please add mapping to notification message
        private Label notification;

        public TextBox getUserNameField() {
            return userNameField;
        }

        public Button getSubmitButton() {
            return submitButton;
        }

    }

    /**
     * This is first version of our page view.
     *
     * Fields will be fetched automatically using selectors in annotation parameter.
     *
     * Fields will be fetched lazily. It means that you can select element
     * which currently (at the moment of View object creation) doesn't exist.
     * It will be searched on the page on first UI activity (with the component).
     */
    public static class MyFeatureRichPage extends GenericViewModel {

        @UiComponentMapping("#userName")
        private TextBox userNameField;

        @UiComponentMapping("#submitButton")
        private Button submitButton;

        @UiComponentMapping(".notification")
        private Label notification;

        public void setUserName(String userName) {
            // TODO: please implement this method
        }

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
