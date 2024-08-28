package com.ericsson.taf.workshop.scenario;

import com.ericsson.cifwk.taf.TestContext;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.taf.workshop.scenario.common.BadTestSteps;
import com.ericsson.taf.workshop.scenario.common.TestModule;
import com.ericsson.taf.workshop.scenario.common.VerySpecialException;
import com.ericsson.taf.workshop.scenario.common.___;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static com.ericsson.cifwk.taf.datasource.TafDataSources.fromCsv;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_DS;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_FM;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_FV;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_GR;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_IP;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_KR;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_ML;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_OO;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_PD;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_PL;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_RP;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_TC;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_TS;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_XA;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_XV;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_YF;
import static com.ericsson.taf.workshop.scenario.common.BadTestSteps.STEP_ZT;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.DataSources.NODES;
import static org.testng.Assert.assertTrue;

/**
 * To complete each exercise you will need to replace placeholders with code
 */
@Guice(modules = TestModule.class)
public class C_Debugging {
    @Inject
    private TestContext context;

    @Inject
    private BadTestSteps badTestSteps;

    private TestScenarioRunner runner = runner().build();

    @BeforeMethod
    public void setUp() throws Exception {
        // TIP: Make sure to check corresponding Data Source csv files to be aware about its contents
        context.addDataSource(NODES, fromCsv("data/node.csv"));
    }

    /**
     * In complex scenario, its might be hard to tell which Test Step hangs the flow.
     * <p>
     * Using real time scenario Graph find which Test Step Fails.
     * <p>
     * Replace placeholders with hanging Test Step name, Data Record and vUser
     *
     * TIP: Flow and SubFlow execution is synchronized i.e. next SubFlow and Flow will not start until all vUsers finish
     * previous one.
     */
    @Test
    public void realTimeDebugGraph() throws Exception {
        TestScenario scenario = scenario(
                ___.<String>___()
        )
                .addFlow(
                        flow("flow1")
                                .addTestStep(annotatedMethod(badTestSteps, STEP_XV))
                                .split(flow("flow2")
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_XA))
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_FV))
                                        .withVusers(2))
                                .addSubFlow(flow("flow2")
                                        .addSubFlow(
                                                flow("flow6")
                                                        .addTestStep(annotatedMethod(badTestSteps, STEP_ZT))
                                                        .addTestStep(annotatedMethod(badTestSteps, STEP_KR))
                                                        .addTestStep(annotatedMethod(badTestSteps, STEP_FM))
                                                        .addTestStep(annotatedMethod(badTestSteps, STEP_YF))
                                                        .addTestStep(annotatedMethod(badTestSteps, STEP_PD))
                                        ))
                                .addSubFlow(flow("flow2")
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_PL))
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_TC)))
                                .split(flow("flow4")
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_TS))
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_RP))
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_ML))
                                        .withVusers(3))
                                .withDataSources(dataSource(NODES))
                                .withVusers(2)
                )
                .build();

        // comment next line out when you find out which test step is hanging
        runner.start(scenario);

        assertTrue(badTestSteps.assertHangingTestStep(
                ___.<String>___(), //Step name
                ___.<String>___(), //Failing node NetworkElementId
                ___.<Integer>___() //vUser
        ));
    }

    /**
     * In complex scenario, its might be hard to tell which test step produces exception.
     * <p>
     * Using scenario Graph find which Test Step Fails.
     * <p>
     * Replace placeholders with failing Test Step name, Data Record and vUser
     */
    @Test
    public void debugGraph() throws Exception {
        TestScenario scenario = scenario(
                ___.<String>___()
        )
                .addFlow(
                        flow("flow1")
                                .addTestStep(annotatedMethod(badTestSteps, STEP_XV))
                                .split(flow("flow2")
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_XA))
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_FV))
                                        .withVusers(2))
                                .addSubFlow(flow("flow2")
                                        .split(flow("flow4")
                                                        .addTestStep(annotatedMethod(badTestSteps, STEP_GR)
                                                        )
                                                        .addTestStep(annotatedMethod(badTestSteps, STEP_YF))
                                                        .split(flow("flow7")
                                                                        .addTestStep(annotatedMethod(badTestSteps, STEP_FM))
                                                                        .addTestStep(annotatedMethod(badTestSteps, STEP_OO))
                                                                        .withVusers(2),
                                                                flow("flow0")
                                                                        .addTestStep(annotatedMethod(badTestSteps, STEP_DS))
                                                        )
                                                        .addTestStep(annotatedMethod(badTestSteps, STEP_PD)),
                                                flow("flow6")
                                                        .addTestStep(annotatedMethod(badTestSteps, STEP_KR))
                                                        .addTestStep(annotatedMethod(badTestSteps, STEP_IP))))
                                .addSubFlow(flow("flow2")
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_PL))
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_TC)))
                                .split(flow("flow4")
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_TS))
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_RP))
                                        .addTestStep(annotatedMethod(badTestSteps, STEP_ML))
                                        .withVusers(3))
                                .withDataSources(dataSource(NODES))
                                .withVusers(4)
                )
                .build();
        try {
            runner.start(scenario);
        } catch (VerySpecialException e) {
            //expected
        }

        assertTrue(badTestSteps.assertFailingTestStep(
                ___.<String>___(), //Step name
                ___.<String>___(), //Failing node NetworkElementId
                ___.<Integer>___() //vUser
        ));
    }
}
