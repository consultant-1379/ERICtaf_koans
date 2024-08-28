package com.ericsson.cifwk.taf.koans.dataDriven.test.cases;

import static com.sandwich.koan.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;


import com.ericsson.cifwk.taf.annotations.DataDriven;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.TestId;
import com.sandwich.koan.Koan;
import com.sandwich.util.koan.KoansHelper;
import org.testng.annotations.Test;


/**
 * This series of koans illustrates how the same csv file can be used in multiple test cases
 * But each test case can use different data within the file
 * The data source being used is 'multicolumn'
 */
public class ProvideDataInCSVForAllTestCases extends KoansHelper {

    /**
     * In this koan you will need to use the data from columns 1, 2 and 3.
     */
    @Koan
    @DataDriven(name="multicolumn")
    @Test
    @TestId(id="CIP-4934_Func_4",title="Provide Data For Multiple Tests from One CSV File (Using columns 1,2 & 3)")
    public void usingColumn123(@Input(__) String column1,
                               @Input(__) String column2,
                               @Input(__) String column3){
        assertEquals(__,column1);
        assertEquals(__,column2);
        assertEquals(__,column3);
    }

    /**
     * In this koan you will need to use the data from columns 1, 3 and 6.
     */
    @Koan
    @DataDriven(name="multicolumn")
    @Test
    @TestId(id="CIP-4934_Func_5",title="Provide Data For Multiple Tests from One CSV File (Using columns 1,3 & 6)")
    public void usingColumn136(@Input(__) String column1,
                               @Input(__) String column3,
                               @Input(__) String column6){
        assertEquals(__,column1);
        assertEquals(__,column3);
        assertEquals(__,column6);

    }

    /**
     * In this koan you will need to use the data from columns 2, 4 and 5.
     */
    @Koan
    @DataDriven(name="multicolumn")
    @Test
    @TestId(id="CIP-4934_Func_6",title="Provide Data For Multiple Tests from One CSV File (Using columns 2,4 & 5)")
    public void usingColumn245(@Input(__) String column2,
                               @Input(__) String column4,
                               @Input(__) String column5){
        assertEquals(__,column2);
        assertEquals(__,column4);
        assertEquals(__,column5);

    }
}
