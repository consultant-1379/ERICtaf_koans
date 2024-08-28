package com.ericsson.taf.workshop.httptool.common;

import com.ericsson.cifwk.taf.scenario.TestStepFlow;

import javax.inject.Inject;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.ericsson.taf.workshop.httptool.common.LoginLogoutRestTestSteps.USERS;

/**
* (!) This is mock adapted for workshop. In real testing please use
* `com.ericsson.oss.testware.security.authentication.flows.LoginLogoutRestFlows` from `enm-security-test-library`
*/
public class LoginLogoutRestFlows {
    @Inject
    LoginLogoutRestTestSteps loginLogoutTestSteps;

    /**
     * Perform login operation using HttpTool and set that in user session.
     *
     * @return flow which performs login
     * @see LoginLogoutRestTestSteps#login(User)
     */

    public TestStepFlow login() {
        return flow("Login")
                .addTestStep(annotatedMethod(loginLogoutTestSteps, LoginLogoutRestTestSteps.TEST_STEP_LOGIN))
                .withDataSources(dataSource(USERS))
                .build();
    }

    /**
     * Perform logout operation using {@code HttpTool}
     *
     * @return flow which performs logout
     * @see LoginLogoutRestTestSteps#logout()
     */
    public TestStepFlow logout() {
        return flow("Logout")
                .addTestStep(annotatedMethod(loginLogoutTestSteps, LoginLogoutRestTestSteps.TEST_STEP_LOGOUT))
                .withDataSources(dataSource(USERS))
                .build();
    }
}
