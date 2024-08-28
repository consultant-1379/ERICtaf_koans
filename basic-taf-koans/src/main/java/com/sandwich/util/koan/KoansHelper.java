package com.sandwich.util.koan;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.guice.OperatorLookupModuleFactory;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;
import org.testng.annotations.Guice;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;

@Guice(moduleFactory = OperatorLookupModuleFactory.class)
public abstract class KoansHelper {

    public static final int _int_ = 99;
    public static final TestStepFlow _TestStepFlow_ = flow("Dummy").build();
    public static final TestScenario _TestScenario_ = scenario().build();
    public static final Host _SampleHost_ = DataHandler.getHostByName("nullServer");
}
