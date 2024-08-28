package com.ericsson.cifwk.taf.koans.uitools.test.helper;

import org.testng.annotations.Guice;

import com.ericsson.cifwk.taf.guice.OperatorLookupModuleFactory;
import com.sandwich.util.koan.proxy.KoanProxyFactory.KoanDecorator;
import com.ericsson.cifwk.taf.ui.sdk.Label;

@Guice(moduleFactory = OperatorLookupModuleFactory.class)
public class UiKoanHelper {

    public static class GenericViewModel extends com.ericsson.cifwk.taf.ui.sdk.GenericViewModel implements KoanDecorator<String> {

        @Override
        public int __() {
            // TODO Auto-generated method stub
            return 0;
        }
        
        public Label ___(Object... args){
            return null;
        }


        @Override
        public String __(Object... args) {
            // TODO Auto-generated method stub
            return null;
        }
        
    }

}
