package com.ericsson.cifwk.taf.koans.dependencyInjection.test.cases;


import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.koans.dependencyInjection.test.operators.MultifunctionalOperatorImpl;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sandwich.koan.Koan;
import com.sandwich.koan.KoanIncompleteException;
import com.sandwich.util.koan.KoansHelper;
import org.testng.annotations.Test;

import static com.sandwich.util.Assert.assertTrue;

public class InjectMultipleOperatorsIntoOperator extends KoansHelper  {

    @Inject
    private Provider<MultifunctionalOperatorImpl> multifunctionalOperatorProvider;

    /**
     * Operators can be found in dependencyInjection/test/operators
     * Modify MultifunctionalOperatorImpl to inject BooleanOperatorImpl and IntegerOperatorImpl
     */
    @Koan
    @Test
    @TestId(id="CIP-4936_Func_2",title="Injecting Operator into Another Operator")
    public void injectingMultipleOperatorsIntoOperator(){
        MultifunctionalOperatorImpl multifunctionalOperator = null;
        try{
            multifunctionalOperator = multifunctionalOperatorProvider.get();
            assertTrue(multifunctionalOperator.combinedOperation());
        }catch (NullPointerException operatorNotFound){
            throw new KoanIncompleteException("MultifunctionalOperatorImpl cannot be found");
        }
    }
}
