package com.ericsson.taf.workshop.scenario.common;

import com.ericsson.cifwk.taf.datasource.DataRecord;

public interface Node extends DataRecord {

    String getNodeType();

    String getNetworkElementId();
}
