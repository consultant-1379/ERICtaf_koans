package com.ericsson.taf.workshop.scenario.common;

import com.ericsson.cifwk.taf.TafTestContext;
import com.ericsson.cifwk.taf.TestContext;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * Simplification for workshop.
 * In real testsware you need to use `com.ericsson.cifwk.taf.TafTestBase` instead.
 */
public class TestModule extends AbstractModule {
    @Provides
    public TestContext provideTestContext() {
        return TafTestContext.getContext();
    }

    @Override
    protected void configure() {
    }
}
