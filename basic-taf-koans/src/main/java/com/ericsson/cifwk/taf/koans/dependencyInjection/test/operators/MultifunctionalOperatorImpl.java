package com.ericsson.cifwk.taf.koans.dependencyInjection.test.operators;

import com.ericsson.cifwk.taf.annotations.Operator;
import com.google.inject.Inject;

import javax.inject.Provider;

@Operator
public class MultifunctionalOperatorImpl implements MultifunctionalOperator {

    @Inject
    Provider<BooleanOperatorImpl> booleanOperatorProvider;

    @Inject
    Provider<IntegerOperatorImpl> integerOperatorProvider;

    public boolean combinedOperation(){
        BooleanOperatorImpl booleanOperator = null; //You will need to instantiate operators
        IntegerOperatorImpl integerOperator = null; //Multiple operators can be used inside one operator
        return booleanOperator.returnTrue() && integerOperator.returnOne()>0;
    }

}
