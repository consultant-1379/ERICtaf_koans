package com.ericsson.taf.workshop.scenario.common;

/*
 * COPYRIGHT Ericsson (c) 2015.
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

import com.ericsson.cifwk.taf.annotations.Operator;
import com.ericsson.cifwk.taf.datasource.DataRecordBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.CREATE_NODE;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.LOGIN;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.LOGOUT;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.LONG_ACTION;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.PERFORM_ACTION_ON_NODE;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.PERFORM_ACTION_WITH_ALARM;
import static com.ericsson.taf.workshop.scenario.common.TestSteps.StepIds.USER_SPECIFIC_ACTION_ON_NODE;
import static java.lang.String.format;

/**
 * Operators are for accessing SUT using HttpTool or CliTool. For simplicity there no SUT in workshop,
 * so this operator simply calls methods of SUTSimultation.
 */
@Operator
public class SutOperator {
    enum Response {OK, ERROR}

    private static final Logger logger = LoggerFactory.getLogger(TestSteps.class);


    public Response longAction(Node node) {
        try {
            doWork(3);
            String step = format("%s (%s)", LONG_ACTION, node.getNetworkElementId());
            logger.info(step);
            SUTSimultation.call(step);
            return Response.OK;
        } catch (Exception e) {
            return Response.ERROR;
        }
    }

    public Node createNode(String networkElementId) {
        String step = format("%s (%s)", CREATE_NODE, networkElementId);
        logger.info(step);
        SUTSimultation.call(step);

        Node node = new DataRecordBuilder()
                .setField("nodeType", "NEW_NODE")
                .setField("networkElementId", networkElementId)
                .build(Node.class);

        return node;
    }

    public void performActionOnNode(Node node) {
        String step = format("%s (%s)", PERFORM_ACTION_ON_NODE, node.getNetworkElementId());
        logger.info(step);
        SUTSimultation.call(step);
    }

    public void performActionOnAlarm(Alarm alarm) {
        String step = format("%s (%s)", PERFORM_ACTION_WITH_ALARM, alarm.getProblem());
        logger.info(step);
        SUTSimultation.call(step);
    }

    public void performUserAction(User user, Node node) {
        String step = format("%s (%s,%s)", USER_SPECIFIC_ACTION_ON_NODE, node.getNetworkElementId(), user.getUsername());
        logger.info(step);
        SUTSimultation.call(step);
    }

    public void login(String username, String password) {
        String step = format("%s (%s)", LOGIN, username);
        logger.info(step);
        SUTSimultation.call(step);
    }

    public void logout(String user) {
        String step = format("%s (%s)", LOGOUT, user);
        logger.info(step);
        SUTSimultation.call(step);
    }

    public Response sleepAndPush(String testStep) {
        try {
            doWork(2);
            SUTSimultation.call(testStep);
            return Response.OK;
        } catch (Exception e) {
            return Response.ERROR;
        }
    }

    public void push(String testStep) {
        SUTSimultation.call(testStep);
    }

    public void cleanUp() {
        SUTSimultation.call(format("%s (%s)", "Clean up: ", "all nodes"));
    }

    public void cleanUp(Node node) {
        SUTSimultation.call(format("%s (%s)", "Clean up: ", node.getNetworkElementId()));
    }

    private void doWork(int seconds) throws Exception {
        Thread.sleep(seconds * 1000);
    }
}
