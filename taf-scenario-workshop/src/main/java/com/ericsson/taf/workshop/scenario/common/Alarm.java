package com.ericsson.taf.workshop.scenario.common;

import com.ericsson.cifwk.taf.datasource.DataRecord;

public interface Alarm extends DataRecord {
    String getProblem();

    int getAlarmNumber();
}
