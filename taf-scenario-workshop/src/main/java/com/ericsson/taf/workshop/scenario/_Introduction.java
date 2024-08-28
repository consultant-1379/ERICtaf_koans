package com.ericsson.taf.workshop.scenario;

/*
 * COPYRIGHT Ericsson (c) 2015.
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

import com.ericsson.cifwk.taf.TestContext;
import com.ericsson.cifwk.taf.datasource.DataRecord;
import com.ericsson.cifwk.taf.datasource.DataRecordBuilder;
import com.ericsson.cifwk.taf.datasource.TestDataSource;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;
import com.ericsson.cifwk.taf.scenario.api.DataSourceDefinitionBuilder;
import com.ericsson.cifwk.taf.scenario.api.TestStepDefinition;
import com.ericsson.taf.workshop.scenario.common.Alarm;
import com.ericsson.taf.workshop.scenario.common.Node;
import com.ericsson.taf.workshop.scenario.common.SUTSimultation;
import com.ericsson.taf.workshop.scenario.common.SutOperator;
import com.ericsson.taf.workshop.scenario.common.TestModule;
import com.ericsson.taf.workshop.scenario.common.TestSteps;
import com.ericsson.taf.workshop.scenario.common.___;
import com.google.common.collect.ImmutableMap;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.iterable;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * To complete each exercise you will need to replace placeholders with code
 */
@Guice(modules = TestModule.class)
public class _Introduction {
    @Inject
    private TestContext context;

    @Inject
    TestSteps testSteps;

    private TestScenarioRunner runner = runner().build();

    @BeforeMethod
    public void setUp() throws Exception {
        // Throw exception if Test Step argument is empty
        System.setProperty("taf.scenario.data-source.validation", "strict");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        SUTSimultation.resetExecutionOrder();
    }

    /**
     * Create 2 flows:
     * "flow1": With Test Steps
     * 1. {@link TestSteps#login(String, String)} and parameters username="workshop"; password="test"
     * 2. {@link TestSteps#flow1Ts()}
     * 3. {@link TestSteps#logout(String)} parameter username="workshop"
     * "cleanup":
     * Create Test Step that calls {@link SutOperator#cleanUp()}
     * TIP: You may need to inject SutOperator
     */
    @Test
    public void createScenario() {
        TestStepFlow flow1 = ___.___();

        TestStepFlow cleanup = ___.___();


        TestScenario scenario = scenario("scenario")
                .addFlow(
                        ___.<TestStepFlow>___()
                )
                .addFlow(
                        ___.<TestStepFlow>___()
                ).alwaysRun()
                .build();

        runner.start(scenario);

        assertExecutionOrder(
                TestSteps.StepIds.LOGIN + " (workshop)",
                TestSteps.StepIds.FLOW1_TS,
                TestSteps.StepIds.LOGOUT + " (workshop)",
                "Clean up: " + " (all nodes)"
        );
    }

    /**
     * Clarify that node NODE0SE will work correctly if action is performed by multiple (3) users in parallel
     */
    @Test
    public void shouldRunInParallel() throws Exception {
        Node node = new DataRecordBuilder()
                .setField(TestSteps.Parameters.NODE_TYPE, "SHARED_NODE")
                .setField(TestSteps.Parameters.NETWORK_ELEMENT_ID, "NODE0SE")
                .build(Node.class);

        TestScenario scenario = scenario("scenario")
                .addFlow(
                        flow("parallel")
                                .addTestStep(
                                        annotatedMethod(testSteps, TestSteps.StepIds.LONG_ACTION)
                                                .withParameter(TestSteps.DataSources.NODES, node))
                                //.__()
                )
                .build();

        runner.start(scenario);

        assertExecutionOrder(
                TestSteps.StepIds.LONG_ACTION + " (NODE0SE)",
                TestSteps.StepIds.LONG_ACTION + " (NODE0SE)",
                TestSteps.StepIds.LONG_ACTION + " (NODE0SE)"
        );
    }

    /**
     * There information about alarms located in file `resources/data/alarm.csv`
     * Execute Test Step {@link TestSteps#performActionWithAlarm(Alarm)} with each Alarm located in this file
     */
    @Test
    public void dataDriven() {
        TestDataSource<DataRecord> alarmDataSource = ___.___();
        context.addDataSource(TestSteps.DataSources.ALARMS, alarmDataSource);

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
                TestSteps.StepIds.PERFORM_ACTION_WITH_ALARM + " (Link down)",
                TestSteps.StepIds.PERFORM_ACTION_WITH_ALARM + " (Overload)",
                TestSteps.StepIds.PERFORM_ACTION_WITH_ALARM + " (Overheat)"
        );
    }

    /**
     * Node created in {@link TestSteps#createNode(String)} should be passed to sequential step {@link TestSteps#performActionOnNode(Node)}
     * All nodes created in flow "createNodes" {@link TestSteps#createNode(String)} should be processed in flow "performActionsOnNodes"
     *
     * TIP: {@link TestSteps#performActionOnNode(Node)} and {@link TestSteps#longAction(Node)}
     * require @Input Data Record of type {@link Node}.
     * {@link TestSteps#createNode(String)} have @Input String, and return  Data Record {@link Node}
     * TIP: Flow "performActionsOnNodes" has Data Source "NODES" does not exist. You need to create it.
     */
    @Test
    public void passDataBetweenTestStepsAndFlows() {
        List nodeIdsToCreate = newArrayList(
                ImmutableMap.of("networkElementId", "123"),
                ImmutableMap.of("networkElementId", "456"));

        TestScenario scenario = scenario("scenario")
                .addFlow(
                        flow("createNodes")
                                .addTestStep(annotatedMethod(testSteps, TestSteps.StepIds.CREATE_NODE)
                                        //.__()
                                )
                                .addTestStep(annotatedMethod(testSteps, TestSteps.StepIds.PERFORM_ACTION_ON_NODE)
                                        //.__()
                                )
                                .withDataSources(iterable("NODES_TO_CREATE", nodeIdsToCreate))
                )
                .addFlow(
                        flow("performActionsOnNodes")
                                .addTestStep(
                                        annotatedMethod(testSteps, TestSteps.StepIds.LONG_ACTION)
                                )
                                .withDataSources(dataSource(TestSteps.DataSources.NODES))
                )
                .build();

        runner.start(scenario);

        assertExecutionOrder(
                TestSteps.StepIds.CREATE_NODE + " (123)",
                TestSteps.StepIds.PERFORM_ACTION_ON_NODE + " (123)",
                TestSteps.StepIds.CREATE_NODE + " (456)",
                TestSteps.StepIds.PERFORM_ACTION_ON_NODE + " (456)",
                TestSteps.StepIds.LONG_ACTION + " (123)",
                TestSteps.StepIds.LONG_ACTION + " (456)"
        );
    }


    private void assertExecutionOrder(String... expected) {
        assertThat(SUTSimultation.getExecutionOrder())
                .overridingErrorMessage("Wrong execution order!\n  Expected: %s\n  But was:  %s", Arrays.toString(expected), SUTSimultation.getExecutionOrder())
                .containsExactly(expected);
    }

}
