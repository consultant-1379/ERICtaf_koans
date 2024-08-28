package com.ericsson.taf.workshop.parallel;

import com.ericsson.cifwk.taf.TestContext;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.TextBox;
import com.ericsson.cifwk.taf.ui.sdk.ViewModel;

import javax.inject.Inject;

public class SignInSteps extends CommonTestSteps {

    public static final String SIGN_IN_FAIL = "Unsuccessful Sign In";
    public static final String IS_SIGN_IN_SCREEN = "Verify Sign In screen";
    public static final String LOGIN = "Sign In to Application";

    @Inject
    private TestContext context;

    @TestStep(id = LOGIN)
    public void signIn() {
        BrowserTab tab = getCurrentBrowserTab();
        ViewModel genericView = tab.getGenericView();
        TextBox userNameBox = genericView.getTextBox(".ebInput.eb_wMargin.eaLogin-Holder-loginUsername");
        TextBox passwordBox = genericView.getTextBox(".ebInput.eb_wMargin.eaLogin-Holder-loginPassword");
        userNameBox.setText("taf");
        passwordBox.setText("taf");
        Button loginButton = genericView.getButton(".ebBtn.eb_wMargin.eaLogin-Holder-formButton");
        loginButton.click();
    }

}
