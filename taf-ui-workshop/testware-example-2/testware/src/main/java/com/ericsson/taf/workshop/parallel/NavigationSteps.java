package com.ericsson.taf.workshop.parallel;

import com.ericsson.cifwk.taf.annotations.TestStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NavigationSteps extends CommonTestSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavigationSteps.class);

    public static final String NAVIGATE_TO_SIGN_IN_SCREEN = "Navigate to Sign In screen";


    @TestStep(id = NAVIGATE_TO_SIGN_IN_SCREEN)
    public void navigateToSignInScreen() {
        LOGGER.info("Navigate to SignIn");
        String loginUrl = TestDataProvider.getFullLoginUrl();
        openInCurrentBrowserTab(loginUrl);
    }

}
