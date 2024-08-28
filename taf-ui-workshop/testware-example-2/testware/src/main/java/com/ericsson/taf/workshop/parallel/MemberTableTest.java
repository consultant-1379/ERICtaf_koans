package com.ericsson.taf.workshop.parallel;

import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;
import com.ericsson.cifwk.taf.scenario.api.TestScenarioBuilder;
import com.ericsson.cifwk.taf.scenario.api.TestStepFlowBuilder;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;

/**
 * @author Kirill Shepitko kirill.shepitko@ericsson.com
 *         Date: 19/05/2016
 */
public class MemberTableTest extends TafTestBase {

    @Inject
    private CommonTestSteps commonSteps;

    @Inject
    private SignInSteps signInSteps;

    @Inject
    private NavigationSteps navigationSteps;

    @Inject
    private MemberTableTestSteps memberTableTestSteps;

    @Test
    public void verifyUserCanInteractWithMultipleTableRows() {
        TestStepFlowBuilder flow = flow("Deleting 2 team members")
                .addTestStep(annotatedMethod(memberTableTestSteps, MemberTableTestSteps.DELETE_SOME_TEAM_MEMBERS));
        loginAndRunFlow(flow.build());
    }

    @Test
    public void verifyUserCanNavigateWithBrowserNavigationButtons() {
        TestStepFlowBuilder flow = flow("Navigate using page buttons")
                .addTestStep(annotatedMethod(memberTableTestSteps, MemberTableTestSteps.NAVIGATE_USING_PAGE_BUTTONS));
        loginAndRunFlow(flow.build());
    }

    @Test
    public void verifyUserCanSuccessfullyRefreshPage() {
        TestStepFlowBuilder flow = flow("Refresh page")
                .addTestStep(annotatedMethod(memberTableTestSteps, MemberTableTestSteps.REFRESH_PAGE));
        loginAndRunFlow(flow.build());
    }

    @Test
    public void verifyUserCanGetMessageBox() {
        TestStepFlowBuilder flow = flow("Manage message box")
                .addTestStep(annotatedMethod(memberTableTestSteps, MemberTableTestSteps.MANAGE_MESSAGE_BOX));
        loginAndRunFlow(flow.build());
    }

    private void loginAndRunFlow(TestStepFlow testStepFlow) {
        TestScenarioBuilder scenario = scenario();

        TestStepFlowBuilder navigateToSignInScreen = flow("Navigate to login screen")
                .addTestStep(annotatedMethod(commonSteps, CommonTestSteps.INIT_BROWSER))
                .addTestStep(annotatedMethod(navigationSteps, NavigationSteps.NAVIGATE_TO_SIGN_IN_SCREEN));
        TestStepFlowBuilder loginAsRegularUser = flow("Login as regular user")
                .addTestStep(annotatedMethod(signInSteps, SignInSteps.LOGIN));

        scenario
                .addFlow(navigateToSignInScreen)
                .addFlow(loginAsRegularUser)
                .addFlow(testStepFlow);

        runner().build().start(scenario.build());
    }

}
