package com.ericsson.cifwk.taf.koans.scenarios.test.cases;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import static com.sandwich.util.Assert.assertEquals;

import org.testng.annotations.Test;


import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.koans.scenarios.test.steps.ScenarioTestSteps;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;
import com.google.inject.Inject;
import com.sandwich.koan.Koan;
import com.sandwich.koan.KoanIncompleteException;
import com.sandwich.util.koan.KoansHelper;

/**
 * This series of koans illustrates the creation and use of scenarios
 */
public class CreateScenario extends KoansHelper  {

    @Inject
    ScenarioTestSteps steps;

    /**
     * In this koan you will add 3 vUsers to the scenario. To add vusers you
     * need to call the method withDefaultVusers() and pass the number of vusers to it.
     */
    @Koan
    @Test
    @TestId(id = "CIP-6314_Func_7", title = "Create a Scenario that will run with multiple Vusers")
    public void createAScenarioAndAddVUsers() {
        TestStepFlow promoteUserFlow;
        try {
            promoteUserFlow = flow("Promote User")
                    .addTestStep(annotatedMethod(steps, "login"))
                    .addTestStep(annotatedMethod(steps, "promoteUser"))
                    .withDataSources(dataSource("singleUserPromotion"))
                    .build();
        } catch (IllegalArgumentException e) {
            throw new KoanIncompleteException("Cannot find methods annotated with @TestStep with ids login or promoteUser");
        }

        /*
         * Correct the withVusers() method call in the following piece of code
         * to get the koan to pass (hint: replace "_int_" with the correct
         * number of vusers)
         */
        TestScenario scenario = scenario()
                .addFlow(promoteUserFlow)
                .withDefaultVusers(_int_)
                .build();

        TestScenarioRunner runner = runner().build();
        runner.start(scenario);

        assertEquals(3, steps.count);
    }

    /**
     * In this koan you will execute a scenario.
     */
    @Koan
    @Test
    @TestId(id = "CIP-6314_Func_8", title = "Execute a Scenario")
    public void createAScenarioAndRun() {
        TestStepFlow promoteUserFlow;
        try {
            promoteUserFlow = flow("Promote User")
                    .addTestStep(annotatedMethod(steps, "login"))
                    .addTestStep(annotatedMethod(steps, "findUser"))
                    .addTestStep(annotatedMethod(steps, "selectUser"))
                    .withDataSources(dataSource("userPromotion"))
                    .withVusers(2).build();
        } catch (IllegalArgumentException e) {
            throw new KoanIncompleteException("Cannot find methods annotated with @TestStep with ids login, findUser or selectUser");
        }

        assertEquals(3, promoteUserFlow.getTestSteps().size());

        TestScenario promoteUserScenario = scenario()
                .addFlow(promoteUserFlow)
                .build();

        TestScenarioRunner runner = runner().build();
        // To execute a scenario call the method start() on the runner, passing the scenario to it.
        //add promoteUserScenario to start method
        runner.start(_TestScenario_);

        assertEquals(true, ScenarioTestSteps.testStepRun);
    }
}
