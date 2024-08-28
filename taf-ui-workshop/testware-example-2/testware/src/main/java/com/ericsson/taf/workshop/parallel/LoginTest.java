package com.ericsson.taf.workshop.parallel;

import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.scenario.api.TestScenarioBuilder;
import com.ericsson.cifwk.taf.scenario.api.TestStepFlowBuilder;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.*;

/**
 * @author Kirill Shepitko kirill.shepitko@ericsson.com
 *         Date: 19/05/2016
 */
public class LoginTest extends TafTestBase {

    @Inject
    private CommonTestSteps commonSteps;

    @Inject
    private SignInSteps signInSteps;

    @Inject
    private NavigationSteps navigationSteps;

    @Test
    public void login() {
        TestScenarioBuilder scenario = scenario();

        TestStepFlowBuilder navigateToSignInScreen = flow("Navigate to login screen")
                .addTestStep(annotatedMethod(commonSteps, CommonTestSteps.INIT_BROWSER))
                .addTestStep(annotatedMethod(navigationSteps, NavigationSteps.NAVIGATE_TO_SIGN_IN_SCREEN));
        TestStepFlowBuilder loginAsRegularUser = flow("Login as regular user")
                .addTestStep(annotatedMethod(signInSteps, SignInSteps.LOGIN));

        scenario
                .addFlow(navigateToSignInScreen)
                .addFlow(loginAsRegularUser);

        runner().build().start(scenario.build());
    }

}
