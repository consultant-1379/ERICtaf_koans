package com.ericsson.cifwk.taf.koans.dataDriven.test.cases;

import static com.sandwich.koan.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;
import static com.sandwich.util.Assert.assertNotNull;
import static com.sandwich.util.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;


import com.ericsson.cifwk.taf.annotations.DataDriven;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.TestId;
import com.sandwich.koan.Koan;
import com.sandwich.util.koan.KoansHelper;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class ProvideDataInCSVForTestCase extends KoansHelper  {
    private static int counter;

    /**
     * This koan is illustrating how to connect data source to test case as easy as it gets
     * We created one for you named "data"
     *
     */
    @Koan
    @DataDriven(name=__) // <- this is the place to edit it
    @Test
    @TestId(id="CIP-4934_Func_1",title="Provide Data From CSV File")
    public void readDataFromCSVFile(@Input("csvData") String dataFromCsv){
        assertNotNull(dataFromCsv);
    }

    /**
     * This koan illustrates how to get particular data from a data source
     * We will be using the "cloumndata" data source
     * We want to retrieve the data in column1
     */
    @Koan
    @DataDriven(name="columndata")
    @Test
    @TestId(id="CIP-4934_Func_2",title="Read Data From Correct Column in CSV File")
    public void readCorrectColumnFromCSVFile(@Input(__) String dataFromCsv){
        assertEquals("DataFromColumn1",dataFromCsv);
    }

    /**
     * This koan illustrates how adding rows to the csv file causes the method to be run multiple times
     * The number of rows of data in csv file corresponds to the number of times the test will run
     * The multipleRows.csv file needs to be modified to make the test run three times
     */
    @Koan
    @DataDriven(name="multirow")
    @Test
    @TestId(id="CIP-4934_Func_3",title="Execute Test Three Times")
    public void testExecutesThreeTimes(@Input("csvData") String dataFromCsv){
        counter++;
        assertEquals(dataFromCsv,dataFromCsv);
    }

    @AfterSuite
    public void after(){
        if(counter!=0){
            assertThat(counter,is(3));
        }
    }
}
