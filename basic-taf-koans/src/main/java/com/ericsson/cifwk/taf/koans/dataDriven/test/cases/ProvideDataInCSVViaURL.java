package com.ericsson.cifwk.taf.koans.dataDriven.test.cases;

import static com.sandwich.koan.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;
import static com.sandwich.util.Assert.assertNotNull;


import com.ericsson.cifwk.taf.annotations.DataDriven;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.TestId;
import com.sandwich.koan.Koan;
import com.sandwich.util.koan.KoansHelper;
import org.testng.annotations.Test;


public class ProvideDataInCSVViaURL extends KoansHelper {

    /**
     *  This Koan illustrates how a datasource can be added via URI
     *  You can view the data sources in the datadriven.properties file which can be found in resources/taf_properties
     *  You will need to determine the correct datasource to use
     */
    @Koan
    @DataDriven(name=__)
    @Test
    @TestId(id="CIP-4934_Func_7",title="Provide Data Source Via URL")
    public void usingURLToProvideDataSource(@Input("csvData") String dataFromURI){
        assertNotNull(dataFromURI);
        assertEquals("somedata",dataFromURI);
    }
}
