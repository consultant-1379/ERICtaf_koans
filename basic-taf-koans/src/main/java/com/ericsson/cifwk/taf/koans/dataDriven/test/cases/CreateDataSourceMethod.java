package com.ericsson.cifwk.taf.koans.dataDriven.test.cases;

import static com.sandwich.koan.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;


import com.ericsson.cifwk.taf.annotations.DataDriven;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.TestId;
import com.sandwich.koan.Koan;
import com.sandwich.util.koan.KoansHelper;
import org.testng.annotations.Test;

public class CreateDataSourceMethod extends KoansHelper {

    /**
     * This koan illustrates how to add a data source from a java class
     * The class MathsConstants can be found in dataDriven/test/data
     * Modify this file so that it returns data
     * You can view the data sources in the datadriven.properties file which can be found in resources/taf_properties
     * You then need to select the correct data source to use
     */
    @Koan
    @DataDriven(name=__)
    @Test
    @TestId(id="CIP-4934_Func_8",title="Provide Data From Custom Data Source")
    public void createDataSource(@Input("Pi") String dataFromJava){
        assertEquals("3.14",dataFromJava);
    }

}
