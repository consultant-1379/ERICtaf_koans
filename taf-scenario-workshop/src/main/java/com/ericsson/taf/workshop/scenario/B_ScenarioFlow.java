package com.ericsson.taf.workshop.scenario;

import com.ericsson.cifwk.taf.TestContext;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.api.TestStepFlowBuilder;
import com.ericsson.taf.workshop.scenario.common.Node;
import com.ericsson.taf.workshop.scenario.common.SUTSimultation;
import com.ericsson.taf.workshop.scenario.common.SutOperator;
import com.ericsson.taf.workshop.scenario.common.TestModule;
import com.ericsson.taf.workshop.scenario.common.TestSteps;
import com.ericsson.taf.workshop.scenario.common.VerySpecialException;
import com.google.common.base.Stopwatch;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.ericsson.cifwk.taf.datasource.TafDataSources.fromCsv;
import static com.ericsson.cifwk.taf.datasource.TafDataSources.shareDataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.DataSources.NODES;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.FLOW1_TS;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.FLOW2_LONG_TS;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.FLOW3_LONG_TS;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.PERFORM_ACTION_ON_NODE;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.THROW_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * To complete each exercise you will need to replace placeholders with code
 */
@Guice(modules = TestModule.class)
public class B_ScenarioFlow {
    @Inject
    private TestContext context;

    @Inject
    private TestSteps testSteps;

    private TestScenarioRunner runner = runner().build();

    @BeforeMethod
    public void setUp() throws Exception {
        // TIP: Make sure to check corresponding Data Source csv files to be aware about its contents
        context.addDataSource(NODES, fromCsv("data/node.csv"));

        // Throw exception if Test Step argument is empty
        System.setProperty("taf.scenario.data-source.validation", "strict");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        SUTSimultation.resetExecutionOrder();
    }

    /**
     * There are 3 flows:
     * flow1 - should run flow copy in 2 parallel threads
     * flow2 and flow3 - should run in parallel with each other
     * <p>
     * TIP: expected execution graph is located in taf-scenario-workshop/src/main/resources/scenarioFlow.svg
     */
    @Test
    public void scenarioFlow() {
        TestStepFlowBuilder flow1 = flow("flow1")
                .addTestStep(annotatedMethod(testSteps, FLOW1_TS))
                //.___()
                ;

        TestStepFlowBuilder flow2 = flow("flow2")
                .addTestStep(annotatedMethod(testSteps, FLOW2_LONG_TS));

        TestStepFlowBuilder flow3 = flow("flow3")
                .addTestStep(annotatedMethod(testSteps, FLOW3_LONG_TS));

        TestScenario scenario = scenario("scenario")
                .addFlow(flow1)
                //.___()
                .build();

        Stopwatch timer = Stopwatch.createStarted();
        runner.start(scenario);
        long timeElapsed = timer.elapsed(TimeUnit.SECONDS);

        assertThat(SUTSimultation.getExecutionOrder().subList(0, 2))
                .overridingErrorMessage("First 2 Test Steps should be from flow1 running in parallel but was:  %s", SUTSimultation.getExecutionOrder())
                .contains(FLOW1_TS, FLOW1_TS);

        assertThat(SUTSimultation.getExecutionOrder().subList(2, 4))
                .overridingErrorMessage("Next 2 Test Steps should be from flow2 and flow3 running in parallel but was:  %s", SUTSimultation.getExecutionOrder())
                .contains(FLOW2_LONG_TS, FLOW3_LONG_TS);

        assertThat(timeElapsed).as("Scenario execution time (Seconds)")
                .isLessThan(3);
    }

    /**
     * Task is to implement cleanup (call of {@link SutOperator#cleanUp(Node)}) that will run for each node. Note that one of
     * Test Steps is producing exception.
     *
     * TIP: for some exercises in {@link #cleanUpForNode()}, {@link #cleanUpForNode2()}, {@link #cleanUpForAllNodes()}
     * you might want to implement Test Step.
     */
    @Test
    public void cleanUpForNode() {
        TestScenario scenario = scenario("scenario")
                .addFlow(
                        flow("flow")
                                .addTestStep(annotatedMethod(testSteps, PERFORM_ACTION_ON_NODE))
                                .addTestStep(annotatedMethod(testSteps, THROW_EXCEPTION))
                                //.___()
                                .withDataSources(dataSource(NODES))
                )
                .build();

        try {
            runner.start(scenario);
        } catch (VerySpecialException e) {
            //expected
        }

        assertExecutionOrder(
                PERFORM_ACTION_ON_NODE + " (SGSN-14B)",
                "Clean up: " + " (SGSN-14B)"
                //fails after first node since flow runs sequentially
        );
    }

    /**
     * Same as {@link #cleanUpForNode()}, but in this example flows will run in parallel, so actions will not
     * stop on first one and. Actions on all 3 nodes will be preformed {@link SutOperator#cleanUp()}).
     * <p>
     * Please note differences and compare execution order with {@link #cleanUpForNode()}.
     *
     * TIP: for some exercises in {@link #cleanUpForNode()}, {@link #cleanUpForNode2()}, {@link #cleanUpForAllNodes()}
     * you might want to implement Test Step.
     */
    @Test
    public void cleanUpForNode2() {
        TestScenario scenario = scenario("scenario")
                .addFlow(
                        flow("flow")
                                .beforeFlow(shareDataSource(NODES))
                                .addTestStep(annotatedMethod(testSteps, PERFORM_ACTION_ON_NODE))
                                .addTestStep(annotatedMethod(testSteps, THROW_EXCEPTION))
                                //.___()
                                .withDataSources(dataSource(NODES))
                                .withVusers(3)
                )
                .build();

        try {
            runner.start(scenario);
        } catch (VerySpecialException e) {
            //expected
        }

        assertExecutedInAnyOrder(
                PERFORM_ACTION_ON_NODE + " (SGSN-14B)",
                "Clean up: " + " (SGSN-14B)",
                PERFORM_ACTION_ON_NODE + " (LTE01ERB)",
                "Clean up: " + " (LTE01ERB)",
                PERFORM_ACTION_ON_NODE + " (LTE08dg2)",
                "Clean up: " + " (LTE08dg2)"
        );
    }

    /**
     * Task is to implement cleanup (call of TestSteps#cleanUp()). In this case cleanup is
     * not dependent on nodes, and should be called once regardless of thread count.
     *
     * TIP: for some exercises in {@link #cleanUpForNode()}, {@link #cleanUpForNode2()}, {@link #cleanUpForAllNodes()}
     * you might want to implement Test Step.
     */
    @Test
    public void cleanUpForAllNodes() {
        TestScenario scenario = scenario("scenario")
                .addFlow(
                        flow("flow")
                                .beforeFlow(shareDataSource(NODES))
                                .addTestStep(annotatedMethod(testSteps, PERFORM_ACTION_ON_NODE))
                                .addTestStep(annotatedMethod(testSteps, THROW_EXCEPTION))
                                //.___()
                                .withDataSources(dataSource(NODES))
                                .withVusers(3)
                )
                .build();

        try {
            runner.start(scenario);
        } catch (VerySpecialException e) {
            //expected
        }

        assertExecutedInAnyOrder(
                PERFORM_ACTION_ON_NODE + " (SGSN-14B)",
                PERFORM_ACTION_ON_NODE + " (LTE01ERB)",
                PERFORM_ACTION_ON_NODE + " (LTE08dg2)",
                "Clean up: " + " (all nodes)"
        );
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
