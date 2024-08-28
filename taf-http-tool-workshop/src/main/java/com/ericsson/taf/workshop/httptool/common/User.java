package com.ericsson.taf.workshop.httptool.common;

import com.ericsson.cifwk.taf.datasource.DataRecord;

public interface User extends DataRecord {
   String getUsername();

   String getPassword();

   String[] getRoles();
}
