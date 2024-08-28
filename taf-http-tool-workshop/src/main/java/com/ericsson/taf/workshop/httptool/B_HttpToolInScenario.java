package com.ericsson.taf.workshop.httptool;

import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.TestContext;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;
import com.ericsson.cifwk.taf.tools.http.HttpResponse;
import com.ericsson.cifwk.taf.tools.http.HttpTool;
import com.ericsson.taf.workshop.httptool.common.LoginLogoutRestFlows;
import com.ericsson.taf.workshop.httptool.common.LoginLogoutRestTestSteps;
import com.ericsson.taf.workshop.httptool.common.TafToolProvider;
import com.ericsson.taf.workshop.httptool.common.___;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Stack;

import static com.ericsson.cifwk.taf.datasource.TafDataSources.fromCsv;
import static com.ericsson.cifwk.taf.datasource.TafDataSources.shareDataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import static com.ericsson.taf.workshop.httptool.common.LoginLogoutRestTestSteps.LOGGED_IN_USER;
import static com.ericsson.taf.workshop.httptool.common.LoginLogoutRestTestSteps.NODES;
import static com.ericsson.taf.workshop.httptool.common.LoginLogoutRestTestSteps.USERS;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * To complete each exercise you will need to replace placeholders with code
 */
public class B_HttpToolInScenario extends TafTestBase {
    public static final String EXECUTE_REQUEST = "EXECUTE_REQUEST";

    @Inject
    private TestContext context;

    @Inject
    private LoginLogoutRestFlows loginLogoutRestFlows;

    @Inject
    private LoginLogoutRestTestSteps logoutRestTestSteps;

    @Inject
    private TafToolProvider httpToolProvider;

    private TestScenarioRunner runner = runner().build();

    private final Stack<String> executionOrder = new Stack<String>();

    // Setting up Jackson
    ObjectMapper jsonMapper = new ObjectMapper();
    TypeReference<HashMap<String, Object>> toMap = new TypeReference<HashMap<String, Object>>() {
    };

    @BeforeMethod
    public void setUp() throws Exception {
        // TIP: Make sure to check corresponding Data Source csv files to be aware about its contents
        context.addDataSource(NODES, fromCsv("data/node.csv"));
        context.addDataSource(USERS, fromCsv("data/user.csv"));

        executionOrder.clear();
    }

    /**
     * Task is to select one of flows:
     * {@link #getSubFlowWithoutLogin()} - relies that Http Tool was logged in previous Test Step
     * {@link #getSubFlowWithLogin()} - independent flow, has login steps inside
     *
     * NOTE: Tool is unable to login twice
     */
    @Test
    public void loginSubflow() throws Exception {
        TestScenario scenario = scenario("scenario")
                .addFlow(
                        flow("flow")
                                .addTestStep(annotatedMethod(logoutRestTestSteps, LoginLogoutRestTestSteps.TEST_STEP_LOGIN))
                                .addSubFlow(___.<TestStepFlow>___())
                                .addTestStep(annotatedMethod(logoutRestTestSteps, LoginLogoutRestTestSteps.TEST_STEP_LOGOUT))
                                .withDataSources(
                                        dataSource(NODES),
                                        dataSource(USERS)
                                )
                )
                .build();

        runner.start(scenario);

        assertThat(executionOrder).contains("admin", "user1", "user2");
    }

    /**
     * Task is to select which of the following flows should be used in the split:
     * {@link #getSubFlowWithoutLogin()} - relies that Http Tool was logged in previous Test Step
     * {@link #getSubFlowWithLogin()} - independent flow, has login steps inside
     *
     * NOTE: Tool is unable to login twice
     */
    @Test
    public void loginSplit() throws Exception {
        TestScenario scenario = scenario("scenario")
                .addFlow(
                        flow("flow")
                                .addTestStep(annotatedMethod(logoutRestTestSteps, LoginLogoutRestTestSteps.TEST_STEP_LOGIN))
                                .split(___.<TestStepFlow>___())
                                .addTestStep(annotatedMethod(logoutRestTestSteps, LoginLogoutRestTestSteps.TEST_STEP_LOGOUT))
                                .withDataSources(
                                        dataSource(NODES),
                                        dataSource(USERS)
                                )
                )
                .build();

        runner.start(scenario);

        assertThat(executionOrder).contains("admin", "user1", "user2");
    }

    /**
     * This is extra task for those who completed all others.
     * Task is to fix failing scenario.
     */
    @Test
    public void fixLoginFlows() throws Exception {
        TestScenario scenario = scenario("scenario")
                .addFlow(
                        flow("flow")
                                .beforeFlow(shareDataSource(NODES), shareDataSource(USERS))

                                .addSubFlow(loginLogoutRestFlows.login())
                                .addSubFlow(getSubFlowWithoutLogin())
                                .addSubFlow(loginLogoutRestFlows.logout())

                                .withVusers(3)
                                .withDataSources(
                                        dataSource(NODES),
                                        dataSource(USERS)
                                )
                )
                .build();

        runner.start(scenario);

        assertThat(executionOrder).contains("admin", "user1", "user2");
    }

    private TestStepFlow getSubFlowWithoutLogin() {
        return flow("Flow without login")
                .addTestStep(annotatedMethod(this, EXECUTE_REQUEST))
                .build();
    }

    private TestStepFlow getSubFlowWithLogin() {
        return flow("Flow without login")
                .addTestStep(annotatedMethod(logoutRestTestSteps, LoginLogoutRestTestSteps.TEST_STEP_LOGIN))
                .addTestStep(annotatedMethod(this, EXECUTE_REQUEST))
                .addTestStep(annotatedMethod(logoutRestTestSteps, LoginLogoutRestTestSteps.TEST_STEP_LOGOUT))
                .build();
    }

    @TestStep(id = EXECUTE_REQUEST)
    public void executeRequest() throws Exception {
        HttpTool httpTool = httpToolProvider.getHttpTool();
        Preconditions.checkNotNull(httpTool, "Http Tool was not logged in");
        HttpResponse response = httpTool.get("/cookies");

        HashMap<String, Object> returned = jsonMapper.readValue(response.getBody(), toMap);
        HashMap<String, Object> cookies = (HashMap<String, Object>) returned.get("cookies");
        Object loggedInUser = cookies.get(LOGGED_IN_USER);

        Preconditions.checkNotNull(loggedInUser, "Http Tool was not logged in");
        executionOrder.push(loggedInUser.toString());
    }
}
