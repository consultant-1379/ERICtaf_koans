package com.ericsson.cifwk.taf.koans.dependencyInjection.test.operators;

import com.google.inject.ImplementedBy;

@ImplementedBy(OneInstanceOperatorImpl.class)
public interface OneInstanceOperator {
}
