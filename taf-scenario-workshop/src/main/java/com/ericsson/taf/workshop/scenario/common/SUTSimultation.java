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

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * For simplicity there no SUT in workshop so this class stores state of SUT
 */
public class SUTSimultation {
    private static final Stack<String> executionOrder = new Stack<String>();

    public static void call(String action) {
        executionOrder.push(action);
    }

    /**
     * @return order in which Test Steps were executed
     */
    public static List<String> getExecutionOrder() {
        return Collections.unmodifiableList(executionOrder);
    }

    /**
     * Clear execution order stack to avoid mix with other tests
     */
    public static void resetExecutionOrder() {
        executionOrder.clear();
    }
}
