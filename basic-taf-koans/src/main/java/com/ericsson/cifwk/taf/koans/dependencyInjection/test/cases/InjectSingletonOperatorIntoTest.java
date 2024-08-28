package com.ericsson.cifwk.taf.koans.dependencyInjection.test.cases;


import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.koans.dependencyInjection.test.operators.OneInstanceOperator;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sandwich.koan.Koan;
import com.sandwich.util.koan.KoansHelper;
import org.testng.annotations.Test;

import static com.sandwich.util.Assert.assertEquals;

public class InjectSingletonOperatorIntoTest extends KoansHelper  {

    @Inject
    private Provider<OneInstanceOperator> operator;

    /**
     * Modify the SingletonOperator so that singletonOperator and singletonOperator1 are equal
     * Operators can be found in dependencyInjection/test/operators
     */
    @Koan
    @Test
    @TestId(id="CIP-4936_Func_1",title="Using Singleton Operator")
    public void compareOperatorInstance(){
        OneInstanceOperator firstInstance = operator.get();
        OneInstanceOperator secondInstance = operator.get();
        assertEquals(firstInstance,secondInstance);
    }

}
