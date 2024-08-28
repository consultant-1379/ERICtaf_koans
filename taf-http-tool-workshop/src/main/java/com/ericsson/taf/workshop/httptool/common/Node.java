package com.ericsson.taf.workshop.httptool.common;

import com.ericsson.cifwk.taf.datasource.DataRecord;

public interface Node extends DataRecord {

    String getType();

    String getId();
}
