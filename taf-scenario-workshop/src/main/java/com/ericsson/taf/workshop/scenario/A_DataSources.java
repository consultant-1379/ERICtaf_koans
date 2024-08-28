package com.ericsson.taf.workshop.scenario;

import com.ericsson.cifwk.taf.TestContext;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.api.DataSourceDefinitionBuilder;
import com.ericsson.cifwk.taf.scenario.api.TestStepDefinition;
import com.ericsson.taf.workshop.scenario.common.SUTSimultation;
import com.ericsson.taf.workshop.scenario.common.TestModule;
import com.ericsson.taf.workshop.scenario.common.TestSteps;
import com.ericsson.taf.workshop.scenario.common.___;
import com.google.common.base.Stopwatch;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.ericsson.cifwk.taf.datasource.TafDataSources.fromCsv;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.DataSources.NODES;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.DataSources.USERS;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.LOGIN;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.LOGOUT;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.LONG_ACTION;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.PERFORM_ACTION_ON_NODE;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.USER_SPECIFIC_ACTION_ON_NODE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * To complete each exercise you will need to replace placeholders with code
 */
@Guice(modules = TestModule.class)
public class A_DataSources {
    @Inject
    private TestContext context;

    @Inject
    private TestSteps testSteps;

    private TestScenarioRunner runner = runner().build();

    @BeforeMethod
    public void setUp() throws Exception {
        // TIP: Make sure to check corresponding Data Source csv files to be aware about its contents
        context.addDataSource(NODES, fromCsv("data/node.csv"));
        context.addDataSource(USERS, fromCsv("data/user.csv"));

        // Throw exception if Test Step argument is empty
        System.setProperty("taf.scenario.data-source.validation", "strict");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        SUTSimultation.resetExecutionOrder();
    }

    /**
     * Lets start with a simple one to get familiar with process:
     * <p>
     * Requirement is to repeat Test Step {@link TestSteps.StepIds#PERFORM_ACTION_ON_NODE} for each node stored in NODES Data Source
     */
    @Test
    public void shouldRepeatForEachNode() {
        TestScenario scenario = scenario("scenario")
                .addFlow(
                        flow("flow")
                                .addTestStep(
                                        ___.<TestStepDefinition>___()
                                )
                                .withDataSources(
                                        ___.<DataSourceDefinitionBuilder[]>___()
                                )
                )
                .build();

        runner.start(scenario);

        assertExecutionOrder(
                PERFORM_ACTION_ON_NODE + " (SGSN-14B)",
                PERFORM_ACTION_ON_NODE + " (LTE01ERB)",
                PERFORM_ACTION_ON_NODE + " (LTE08dg2)"
        );
    }

    /**
     * Check that all users with role OPERATOR can perform action on any node
     * <p>
     * In this exercise:
     * 1. USERS Data Source should be filtered to only produce Users with role OPERATOR
     * 2. Each user will need to get _any_ node, if there's more Nodes than Users execution should stop when last user
     * performed action on node.
     */
    @Test
    public void shouldRepeatForFilteredUsers() throws Exception {
        TestScenario scenario = scenario("scenario")
                .addFlow(
                        flow("flow")
                                .addTestStep(annotatedMethod(testSteps, USER_SPECIFIC_ACTION_ON_NODE))
                                .withDataSources(
                                        ___.<DataSourceDefinitionBuilder[]>___()
                                )
                )
                .build();

        runner.start(scenario);

        assertExecutionOrder(
                USER_SPECIFIC_ACTION_ON_NODE + " (SGSN-14B,user1)",
                USER_SPECIFIC_ACTION_ON_NODE + " (LTE01ERB,user2)"
        );
    }

    /**
     * Check that every user will be able to access all node types.
     * <p>
     * Flow needs to login, run {@link TestSteps.StepIds#USER_SPECIFIC_ACTION_ON_NODE} on all node types then logout
     */
    @Test
    public void userNodeMultiplication() throws Exception {
        TestScenario scenario = scenario("scenario")
                .addFlow(
                        flow("flow")
                                .addTestStep(annotatedMethod(testSteps,
                                        ___.<String>___()
                                ))
                                .addSubFlow(
                                        flow("subFlow")
                                                .addTestStep(annotatedMethod(testSteps,
                                                        ___.<String>___()
                                                ))
                                                .withDataSources(
                                                        ___.<DataSourceDefinitionBuilder[]>___()
                                                )
                                )
                                .addTestStep(annotatedMethod(testSteps,
                                        ___.<String>___()
                                ))
                                .withDataSources(
                                        ___.<DataSourceDefinitionBuilder[]>___()
                                )
                )
                .build();

        runner.start(scenario);

        assertExecutionOrder(
                LOGIN + " (admin)",
                USER_SPECIFIC_ACTION_ON_NODE + " (SGSN-14B,admin)",
                USER_SPECIFIC_ACTION_ON_NODE + " (LTE01ERB,admin)",
                USER_SPECIFIC_ACTION_ON_NODE + " (LTE08dg2,admin)",
                LOGOUT + " (admin)",
                LOGIN + " (user1)",
                USER_SPECIFIC_ACTION_ON_NODE + " (SGSN-14B,user1)",
                USER_SPECIFIC_ACTION_ON_NODE + " (LTE01ERB,user1)",
                USER_SPECIFIC_ACTION_ON_NODE + " (LTE08dg2,user1)",
                LOGOUT + " (user1)",
                LOGIN + " (user2)",
                USER_SPECIFIC_ACTION_ON_NODE + " (SGSN-14B,user2)",
                USER_SPECIFIC_ACTION_ON_NODE + " (LTE01ERB,user2)",
                USER_SPECIFIC_ACTION_ON_NODE + " (LTE08dg2,user2)",
                LOGOUT + " (user2)"
        );
    }

    /**
     * Now we need to process really slow task (takes up to 3 seconds!), but we have requirement to run scenario
     * in 5 seconds or less.
     */
    @Test
    public void runInParallel() {
        TestScenario scenario = scenario("scenario")
                .addFlow(
                        flow("flow")
                                .beforeFlow(
                                        ___.<Runnable>___()
                                )
                                .addTestStep(annotatedMethod(testSteps, LONG_ACTION))
                                //.___()
                                .withDataSources(dataSource(NODES))

                )
                .build();

        Stopwatch timer = Stopwatch.createStarted();
        runner.start(scenario);
        long timeElapsed = timer.elapsed(TimeUnit.SECONDS);

        //Because we run Test Steps in parallel we can't predict in which order it will be executed
        assertExecutedInAnyOrder(
                LONG_ACTION + " (SGSN-14B)",
                LONG_ACTION + " (LTE01ERB)",
                LONG_ACTION + " (LTE08dg2)"
        );

        assertThat(timeElapsed).as("Scenario execution time (Seconds)")
                .isLessThan(5);
    }

    private void assertExecutionOrder(String... expected) {
        assertThat(SUTSimultation.getExecutionOrder())
                .overridingErrorMessage("Wrong execution order!\n  Expected: %s\n  But was:  %s", Arrays.toString(expected), SUTSimultation.getExecutionOrder())
                .containsExactly(expected);
    }

    private void assertExecutedInAnyOrder(String... expected) {
        assertThat(SUTSimultation.getExecutionOrder())
                .overridingErrorMessage("Wrong test steps executed!\n  Expected: %s\n  But was:  %s", Arrays.toString(expected), SUTSimultation.getExecutionOrder())
                .hasSize(expected.length);
        assertThat(SUTSimultation.getExecutionOrder())
                .overridingErrorMessage("Wrong test steps executed!\n  Expected: %s\n  But was:  %s", Arrays.toString(expected), SUTSimultation.getExecutionOrder())
                .contains(expected);
    }
}
