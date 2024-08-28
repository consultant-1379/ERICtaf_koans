package com.ericsson.cifwk.taf.koans.dependencyInjection.test.operators;

import com.ericsson.cifwk.taf.annotations.Operator;

@Operator
public class BooleanOperatorImpl implements BooleanOperator {

    public boolean returnTrue(){
        return true;
    }
}
