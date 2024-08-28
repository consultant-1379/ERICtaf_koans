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
 * This series of koans illustrates the use of vusers with flows and scenarios
 *
 */
public class VUsers extends KoansHelper  {

    @Inject
    ScenarioTestSteps steps;

    /**
     * In this koan we will see how the number of vusers specified on a scenario
     * is available to a flow added to the scenario
     */
    @Koan
    @Test
    @TestId(id = "CIP-6314_Func_11", title = "Create a scenario and execute with multiple Vusers")
    public void createAScenarioAndExecuteWithMultipleVUsers() {

        TestStepFlow promoteUserFlow;
        try {
            promoteUserFlow = flow("Promote User")
                    .addTestStep(annotatedMethod(steps, "login"))
                    .addTestStep(annotatedMethod(steps, "findUser"))
                    .addTestStep(annotatedMethod(steps, "selectUser"))
                    .addTestStep(annotatedMethod(steps, "promoteUser"))
                    .withDataSources(dataSource("userPromotion"))
                    .build();
        } catch (IllegalArgumentException e) {
            throw new KoanIncompleteException(
                    "Cannot find methods annotated with @TestStep");
        }

        TestScenario scenario = scenario().addFlow(promoteUserFlow)
                .withDefaultVusers(2).build();

        TestScenarioRunner runner = runner().build();
        runner.start(scenario);

        /*
         * The flow will inherit the number of vusers from the scenario. The
         * vUserCount property is incremented each time the login method is
         * called in a scenario, correct the assertion accordingly.
         * (hint: the method is called by each vuser for each row of data)
         */
        assertEquals(_int_, ScenarioTestSteps.getvUserLoginCount());
        ScenarioTestSteps.setvUserPromoteCount(0);
        ScenarioTestSteps.setvUserLoginCount(0);
    }

    /**
     * In this koan we will add 2 flows to a scenario, one of the flows will
     * have vusers and the other will not.
     */
    @Koan
    @Test
    @TestId(id = "CIP-6314_Func_12", title = "Add flow with Vusers specified to a Scenario")
    public void createAScenarioAndAddVusersToFlow() {
        TestStepFlow loginFlow, demoteUserFlow;

        try {
            loginFlow = flow("login")
                    .addTestStep(annotatedMethod(steps, "login")).withVusers(2)
                    .build();

            demoteUserFlow = flow("Demote User").addTestStep(
                    annotatedMethod(steps, "demoteUser")).build();
        } catch (IllegalArgumentException e) {
            throw new KoanIncompleteException(
                    "Cannot find methods annotated with @TestSteps with ids login or demoteUser");
        }

        TestScenario scenario = scenario().addFlow(loginFlow)
                .addFlow(demoteUserFlow).build();

        TestScenarioRunner runner = runner().build();
        runner.start(scenario);

        /*
         * The "demoteUser" has 0 vusers and won't inherit vusers from the
         * other flow. Therefore the default behaviour of 1 vuser kicks in
         * The vUserPromoteCount is incremented everytime the
         * promoteUser method is called, correct the assertion accordingly
         */
        assertEquals(_int_, ScenarioTestSteps.getvUserDemoteCount());
        ScenarioTestSteps.setvUserLoginCount(0);
    }

    /**
     * In this koan we will illustrate the precedence of vusers set on a
     * flow level and a scenario level
     */
    @Koan
    @Test
    @TestId(id = "CIP-6314_Func_13", title = "Demonstrate the precedence of Vusers")
    public void createAScenarioWithVUsers() {
        TestStepFlow loginFlow, promoteUserFlow;

        try {
            loginFlow = flow("login")
                    .addTestStep(annotatedMethod(steps, "login"))
                    .withDataSources(dataSource("userPromotion"))
                    .withVusers(3).build();

            promoteUserFlow = flow("Promote User")
                    .addTestStep(annotatedMethod(steps, "promoteUser"))
                    .withDataSources(dataSource("userPromotion"))
                    .build();
        } catch (IllegalArgumentException e) {
            throw new KoanIncompleteException(
                    "Cannot find methods annotated with @TestSteps with ids login or promoteUser");
        }

        TestScenario scenario = scenario().addFlow(loginFlow)
                .addFlow(promoteUserFlow)
                .withDefaultVusers(2)
                .build();

        TestScenarioRunner runner = runner().build();
        runner.start(scenario);

        /* Each count variable is incremented every time the relevant method is called.
         * The vusers set on a flow takes precedence
         * Correct the assertion based on how many vusers each flow has
         * Hint: don't forget that the count variable will be incremented for each entry in the data source for each
         * vuser*/
        assertEquals(_int_, ScenarioTestSteps.getvUserPromoteCount());
        assertEquals(_int_, ScenarioTestSteps.getvUserLoginCount());

    }
}
