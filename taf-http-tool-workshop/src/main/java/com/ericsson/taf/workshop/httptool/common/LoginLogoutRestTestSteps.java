package com.ericsson.taf.workshop.httptool.common;

import com.ericsson.cifwk.taf.TestContext;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.tools.http.HttpTool;
import com.ericsson.cifwk.taf.tools.http.HttpToolBuilder;
import com.ericsson.taf.workshop.httptool.A_HttpTool;
import com.google.common.base.Preconditions;

import javax.inject.Inject;

/**
 * Login/Logout REST test steps.
 * Use HttpTool to perform REST requests.
 * (!) This is mock adapted for workshop. In real testing please use
 * `com.ericsson.oss.testware.security.authentication.steps.LoginLogoutRestTestSteps` from `enm-security-test-library`
 */
public class LoginLogoutRestTestSteps {

    public static final String NODES = "NODES";
    public static final String USERS = "USERS";

    public static final String TEST_STEP_LOGIN = "login";
    public static final String TEST_STEP_LOGOUT = "logout";

    public static final String LOGGED_IN_USER = "username";

    @Inject
    TestContext context;

    @Inject
    TafToolProvider tafToolProvider;

    @TestStep(id = TEST_STEP_LOGIN)
    public void login(@Input(USERS) final User user) {
        HttpTool httpTool = tafToolProvider.getHttpTool();

        Preconditions.checkNotNull(user);
        if (httpTool != null) {
            throw new IllegalStateException("Another user logged in: " + httpTool.getCookies().get(LOGGED_IN_USER));
        }

        httpTool = HttpToolBuilder.newBuilder(A_HttpTool.getHost(A_HttpTool.HTTP_BIN)).build();
        httpTool.addCookie(LOGGED_IN_USER, user.getUsername());

        tafToolProvider.setToolToContext(httpTool);
    }

    @TestStep(id = TEST_STEP_LOGOUT)
    public void logout() {
        final HttpTool httpTool = tafToolProvider.getHttpTool();
        Preconditions.checkState(httpTool != null, "Was not logged it");
        httpTool.close();
        tafToolProvider.setToolToContext(null);
    }
}
