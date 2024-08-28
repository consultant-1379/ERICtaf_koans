package com.ericsson.cifwk.taf.koans.dependencyInjection.test.operators;

import com.ericsson.cifwk.taf.annotations.Operator;
import com.google.inject.Singleton;

@Operator
//Using @Singleton annotation on operator implementation will cause only one
//instance of operator object to be instantiated
public class OneInstanceOperatorImpl implements OneInstanceOperator {
}
