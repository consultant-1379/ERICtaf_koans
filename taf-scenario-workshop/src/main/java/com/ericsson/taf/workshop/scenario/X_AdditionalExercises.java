package com.ericsson.taf.workshop.scenario;

import com.ericsson.cifwk.taf.TestContext;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;
import com.ericsson.taf.workshop.scenario.common.SUTSimultation;
import com.ericsson.taf.workshop.scenario.common.TestModule;
import com.ericsson.taf.workshop.scenario.common.TestSteps;
import com.google.common.base.Stopwatch;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.ericsson.cifwk.taf.datasource.TafDataSources.copyDataSource;
import static com.ericsson.cifwk.taf.datasource.TafDataSources.fromCsv;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runnable;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.DataSources.NODES;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.LONG_ACTION;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * If you successfully completed all previous exercises, heres additional
 */
@Guice(modules = TestModule.class)
public class X_AdditionalExercises {
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
     * There are 3 subflows:
     * 1. `addDataRecord` add one Data Record to Data Source
     * 2. `processDataRecord1` needs to process NODES in parallel
     * 3. `processDataRecord2` also needs to process same Data Source in parallel
     *
     * TIP: Information that helps solve this exercise is located in `Taf Scenario Guidelines` presentation
     */
    @Test
    public void dataSourceModification() {
        TestStepFlow addNewRecord = flow("addDataRecord")
                .beforeFlow(copyDataSource(NODES)) //make DS modifiable
                .addTestStep(runnable(new Runnable() {
                    @Override
                    public void run() {
                        context.dataSource(NODES).addRecord()
                                .setField("networkElementId", "NEW_NODE")
                                .setField("nodeType", "NEW");
                    }
                })).build();

        TestStepFlow processDataRecord1 = flow("processDataRecord1")
                //.___()
                //.___()
                .addTestStep(annotatedMethod(testSteps, LONG_ACTION))
                .withDataSources(dataSource(NODES))
                .build();

        TestStepFlow processDataRecord2 = flow("processDataRecord2")
                //.___()
                //.___()
                .addTestStep(annotatedMethod(testSteps, LONG_ACTION))
                .withDataSources(dataSource(NODES))
                .build();

        TestScenario scenario = scenario("scenario")
                .addFlow(
                        flow("mainFlow")
                                .addSubFlow(addNewRecord)
                                //.___(processDataRecord1)
                                //.___(processDataRecord2)
                )
                .build();

        Stopwatch timer = Stopwatch.createStarted();

        runner.start(scenario);

        long timeElapsed = timer.elapsed(TimeUnit.SECONDS);

        assertExecutedInAnyOrder(
                LONG_ACTION + " (SGSN-14B)", //processDataRecord1
                LONG_ACTION + " (LTE01ERB)", //processDataRecord1
                LONG_ACTION + " (LTE08dg2)", //processDataRecord1
                LONG_ACTION + " (NEW_NODE)", //processDataRecord1
                LONG_ACTION + " (SGSN-14B)", //processDataRecord2
                LONG_ACTION + " (LTE01ERB)", //processDataRecord2
                LONG_ACTION + " (LTE08dg2)", //processDataRecord2
                LONG_ACTION + " (NEW_NODE)" //processDataRecord2
        );

        assertThat(timeElapsed).as("Scenario execution time (Seconds)")
                .isLessThan(10);
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
