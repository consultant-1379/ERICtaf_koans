package com.ericsson.cifwk.taf.koans.dependencyInjection.test.operators;

import com.ericsson.cifwk.taf.annotations.Operator;

@Operator
public class IntegerOperatorImpl implements IntegerOperator {

    public int returnOne(){
        return 1;
    }
}
