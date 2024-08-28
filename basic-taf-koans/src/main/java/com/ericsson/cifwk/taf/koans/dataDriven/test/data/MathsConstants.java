package com.ericsson.cifwk.taf.koans.dataDriven.test.data;

import com.ericsson.cifwk.taf.annotations.DataSource;

import java.util.Collections;
import java.util.List;
import java.util.Map;


public class MathsConstants {

    //A custom Java Class should be used if you need more control of the test data
    //A single method annotated with @DataSource is required when using a java class as a data source
    public List<Map<String,Object>> dataSource() {
        Map<String, Object> data = Collections.<String, Object>singletonMap("Pi", "3.14");
        return Collections.singletonList(data);
    }

}
