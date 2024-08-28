package com.ericsson.cifwk.taf.koans.scenarios.test.cases;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import static com.sandwich.util.Assert.assertEquals;


import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.koans.scenarios.test.steps.ScenarioTestSteps;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;
import com.google.inject.Inject;
import com.sandwich.koan.Koan;
import com.sandwich.koan.KoanIncompleteException;
import com.sandwich.util.koan.KoansHelper;
import org.testng.annotations.Test;

/**
 * This series of koans illustrates the use of data sources with flows and
 * scenarios
 *
 */
public class DataSources extends KoansHelper  {

    @Inject
    ScenarioTestSteps steps;

    /**
     * In this koan we will add 2 flows to a scenario, one of the flows will
     * have a data source and the other will not. The data source has the inputs
     * for both flows, but the data is not available to the second flow
     */
    @Koan
    @Test
    @TestId(id = "CIP-6314_Func_9", title = "Add a flow with data source to a scenario")
    public void createAScenarioAndAddFlowsAndDataSourceToFlow() {
        TestStepFlow loginFlow, promoteUserFlow;

        try {
            loginFlow = flow("login")
                    .addTestStep(annotatedMethod(steps, "login"))
                    .withDataSources(dataSource("userPromotion")).build();

            promoteUserFlow = flow("Promote User").addTestStep(
                    annotatedMethod(steps, "demoteUser")).build();
        } catch (IllegalArgumentException e) {
            throw new KoanIncompleteException(
                    "Cannot find methods annotated with @TestStep with ids login or demoteUser");
        }

        TestScenario scenario = scenario().addFlow(loginFlow)
                .addFlow(promoteUserFlow).build();

        TestScenarioRunner runner = runner().build();
        runner.start(scenario);

        /*
         * The step "demoteUser" takes a string input, "level", but it is not
         * available to it from the data source in the first flow. Each time the
         * method is called the value of level is added to the list, downLevels,
         * if it is not null. Correct the assertion to reflect this.
         */
        assertEquals(_int_, steps.downLevels.size());
    }

    /**
     * This koan is a copy of the previous koan, but we will add the data source
     * "promotions" so that the second flow gets the data it needs.
     */
    @Koan
    @Test
    @TestId(id = "CIP-6314_Func_10", title = "Add a flow with data source to a scenario")
    public void createAScenarioAndAddFlowsAndDataSourcesToFlows() {
        TestStepFlow loginFlow, demoteUserFlow;

        try {
            loginFlow = flow("login")
                    .addTestStep(annotatedMethod(steps, "login"))
                    .withDataSources(dataSource("userPromotion")).build();

            demoteUserFlow = flow("Promote User").addTestStep(
                    annotatedMethod(steps, "demoteUser"))
                    // You can add the method call here
                    .build();
        } catch (IllegalArgumentException e) {
            throw new KoanIncompleteException(
                    "Cannot find methods annotated with @TestStep with ids login or demoteUser");
        }

        TestScenario scenario = scenario().addFlow(loginFlow)
                .addFlow(demoteUserFlow).withDefaultVusers(1).build();

        TestScenarioRunner runner = runner().build();
        runner.start(scenario);

        /*
         * The "demoteUser" step needs a data source with "level" data. Add the
         * data source to the flow. Each time the method is
         * called the value of level is added to the list, downLevels. Correct
         * the assertion to reflect this.
         */
        assertEquals(_int_, steps.downLevels.size());

    }
}
