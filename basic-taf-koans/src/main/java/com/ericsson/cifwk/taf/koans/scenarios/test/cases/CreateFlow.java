package com.ericsson.cifwk.taf.koans.scenarios.test.cases;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.sandwich.koan.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;


import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.koans.scenarios.test.steps.ScenarioTestSteps;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;
import com.google.inject.Inject;
import com.sandwich.koan.Koan;
import com.sandwich.koan.KoanIncompleteException;
import com.sandwich.util.koan.KoansHelper;
import org.testng.annotations.Test;

/**
 * This series of koans illustrates the creation of flows
 *
 */
public class CreateFlow extends KoansHelper  {

    @Inject
    ScenarioTestSteps scenarioTestSteps;

    /**
     * In this koan you will create a simple flow with the name "addUser" and
     * build it.
     */
    @Koan
    @Test
    @TestId(id = "CIP-6314_Func_1", title = "Create a flow")
    public void createAFlow() {
        TestStepFlow flow = flow(__).build();
        assertEquals("addUser", flow.getName());
    }

    /**
     * In this koan you will create a flow and add the test step "login" to it.
     */
    @Koan
    @Test
    @TestId(id = "CIP-6314_Func_2", title = "Create a Flow and Add a Test Step")
    public void createAFlowAndAddATestStep() {

        /*
         * The class ScenarioTestSteps in scenarios.test.steps package contains
         * the method login. Annotate this method with @TestStep and give it the
         * name "login"
         */
        TestStepFlow flow;
        try {
            flow = flow("login").addTestStep(
                    annotatedMethod(scenarioTestSteps, "login")).build();
        } catch (IllegalArgumentException e) {
            throw new KoanIncompleteException(
                    "Cannot find method annotated with @TestStep(id=\"login\")");
        }

        /*
         * Show you understand the concepts of flows and test steps by
         * correcting the following assertion (hint: you are being asked how
         * many TestSteps are in the flow)
         */
        assertEquals(__, flow.getTestSteps().size());
    }

    /**
     * In this koan you will add multiple test steps to a flow. The test steps
     * you will add are "login" and "findUser"
     */
    @Koan
    @Test
    @TestId(id = "CIP-6314_Func_3", title = "Create a flow and add multiple test steps to it")
    public void createAFlowAndAddMultipleTestSteps() {
        TestStepFlow flow;
        try {
            flow = flow("updateUser")
                    .addTestStep(annotatedMethod(scenarioTestSteps, __))
                    .addTestStep(annotatedMethod(scenarioTestSteps, __))
                    .build();
        } catch (IllegalArgumentException e) {
            throw new KoanIncompleteException(
                    "Cannot find method annotated with @TestStep(id=\"login\") or @TestStep(id=\"findUser\")");
        }

        /*
         * Show you understand the concepts of flows and test steps by
         * correcting the following assertion (hint: you are being asked how
         * many TestSteps are in the flow)
         */
        assertEquals(__, flow.getTestSteps().size());
    }

    /**
     * In this koan you will add a data source to the flow promoteUser. The data
     * source you will add is "userPromotion"
     */
    @Koan
    @Test
    @TestId(id = "CIP-6314_Func_4", title = "Create a flow with multiple test steps and a data source")
    public void createAFlowWithMultipleTestStepsAndADataSource() {

        TestStepFlow flow;

        try {
            flow = flow("promoteUser")
                    .addTestStep(annotatedMethod(scenarioTestSteps, "login"))
                    .addTestStep(annotatedMethod(scenarioTestSteps, "findUser"))
                    .addTestStep(
                            annotatedMethod(scenarioTestSteps, "selectUser"))
                    .withDataSources(dataSource(__)).build();
        } catch (IllegalArgumentException e) {
            throw new KoanIncompleteException(
                    "Cannot find methods annotated with TestStep or dataSource userPromotion");
        }

        /*
         * Show you understand the concept of datasources by correcting the
         * following assertion (hint: you are being asked how many data sources
         * are in the flow
         */
        assertEquals(__, flow.getDataSources().length);
    }

    /**
     * In this koan you will specify multiple vusers. Vusers are added using the
     * withVusers() method. Add 2 vUsers.
     */
    @Koan
    @Test
    @TestId(id = "CIP-6314_Func_5", title = "Create a flow with multiple test steps and runs with multiple Vusers")
    public void createAFlowWithMultipleTestStepsAndVusers() {

        TestStepFlow flow;
        try {
            flow = flow("Parallel Promote User")
                    .addTestStep(annotatedMethod(scenarioTestSteps, "login"))
                    .addTestStep(annotatedMethod(scenarioTestSteps, "findUser"))
                    .addTestStep(
                            annotatedMethod(scenarioTestSteps, "selectUser"))
                    .withVusers(_int_).build();
        } catch (IllegalArgumentException e) {
            throw new KoanIncompleteException(
                    "Cannot find methods annotated with @TestSteps with ids login, findUser or selectUser");
        }

        /*
         * Show you under the concept of vusers by correcting the following
         * assertion (hint: you are being asked how many vusers are in the flow)
         */
        assertEquals(__, flow.getRunOptions().getVUsers());
    }
}
