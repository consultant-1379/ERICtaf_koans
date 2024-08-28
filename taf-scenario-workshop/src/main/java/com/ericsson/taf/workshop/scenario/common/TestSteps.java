package com.ericsson.taf.workshop.scenario.common;

import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.taf.workshop.scenario.common.SutOperator.Response;

import javax.inject.Inject;

import static com.ericsson.taf.workshop.scenario.common.SutOperator.Response.OK;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.DataSources.ALARMS;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.Parameters.NETWORK_ELEMENT_ID;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.Parameters.PASSWORD;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.Parameters.USERNAME;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.CREATE_NODE;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.FLOW1_TS;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.FLOW2_LONG_TS;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.LOGIN;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.LOGOUT;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.PERFORM_ACTION_ON_NODE;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.PERFORM_ACTION_WITH_ALARM;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.THROW_EXCEPTION;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestSteps {

    public static class DataSources {
        public static final String NODES = "NODES";
        public static final String USERS = "USERS";
        public static final String ALARMS = "ALARMS";
    }

    public static class StepIds {
        public static final String USER_SPECIFIC_ACTION_ON_NODE = "USER_SPECIFIC_ACTION_ON_NODE";
        public static final String PERFORM_ACTION_ON_NODE = "PERFORM_ACTION_ON_NODE";
        public static final String CREATE_NODE = "CREATE_NODE";
        public static final String PERFORM_ACTION_WITH_ALARM = "PERFORM_ACTION_WITH_ALARM";
        public static final String THROW_EXCEPTION = "THROW_EXCEPTION";
        public static final String LOGIN = "LOGIN";
        public static final String LOGOUT = "LOGOUT";
        public static final String LONG_ACTION = "StepIds.LONG_ACTION";
        public static final String FLOW1_TS = "FLOW1_TS";
        public static final String FLOW2_LONG_TS = "FLOW2_LONG_TS";
        public static final String FLOW3_LONG_TS = "FLOW3_LONG_TS";
    }
    
    public static class Parameters {
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String NETWORK_ELEMENT_ID = "networkElementId";
        public static final String NODE_TYPE = "nodeType";
    }

    @Inject
    SutOperator sutOperator;

    @TestStep(id = StepIds.LONG_ACTION)
    public void longAction(@Input(DataSources.NODES) Node node) {
        Response response = sutOperator.longAction(node);
        assertThat(response).isEqualTo(OK);
    }

    @TestStep(id = CREATE_NODE)
    public Node createNode(@Input(NETWORK_ELEMENT_ID) String networkElementId) {
        Node createdNode = sutOperator.createNode(networkElementId);
        assertThat(createdNode.getNetworkElementId()).isEqualTo(networkElementId);
        return createdNode;
    }

    /**
     * TIP: Username and password are fields in USERS (user.csv) data source
     */
    @TestStep(id = LOGIN)
    public void login(@Input(USERNAME) String username, @Input(PASSWORD) String password) {
        sutOperator.login(username, password);
    }

    @TestStep(id = LOGOUT)
    public void logout(@Input(USERNAME) String username) {
        sutOperator.logout(username);
    }

    @TestStep(id = PERFORM_ACTION_ON_NODE)
    public void performActionOnNode(@Input(DataSources.NODES) Node node) {
        sutOperator.performActionOnNode(node);
    }

    @TestStep(id = PERFORM_ACTION_WITH_ALARM)
    public void performActionWithAlarm(@Input(ALARMS) Alarm node) {
        sutOperator.performActionOnAlarm(node);
    }

    @TestStep(id = THROW_EXCEPTION)
    public void throwException() {
        throw new VerySpecialException();
    }

    @TestStep(id = StepIds.USER_SPECIFIC_ACTION_ON_NODE)
    public void performUserAction(@Input(DataSources.USERS) User user, @Input(DataSources.NODES) Node node) {
        sutOperator.performUserAction(user, node);
    }

    @TestStep(id = FLOW1_TS)
    public void flow1Ts() {
        sutOperator.push(FLOW1_TS);
    }

    @TestStep(id = FLOW2_LONG_TS)
    public void flow2LongTs() {
        Response response = sutOperator.sleepAndPush(FLOW2_LONG_TS);
        assertThat(response).isEqualTo(OK);
    }

    @TestStep(id = StepIds.FLOW3_LONG_TS)
    public void flow3LongTs() {
        Response response = sutOperator.sleepAndPush(StepIds.FLOW3_LONG_TS);
        assertThat(response).isEqualTo(OK);
    }
}
