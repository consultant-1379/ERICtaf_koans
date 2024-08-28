package com.ericsson.cifwk.taf.koans.configuration.test.cases;

import static com.sandwich.util.Assert.assertEquals;


import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.sandwich.koan.Koan;
import com.sandwich.util.koan.KoansHelper;
import org.testng.annotations.Test;

public class UsingSystemProperties extends KoansHelper  {

    /**
     * This Koan demonstrates how to override the ip address of a host using system properties
     * Host 'test' can be found in the host.properties file
     * Modify the ip of 'test' to 10.59.141.6 using system properties
     */
    @Koan
    @Test
    @TestId(id="CIP-4935_Func_4",title="Override Host IP using System Properties")
    public void verifyThatSystemPropertyOverridesIP(){
        Host host = DataHandler.getHostByName("test");
        assertEquals("1.1.1.1",host.getIp());
        //The ip of the host can be overridden by modifying the host.test.ip using System.setProperty
        host = DataHandler.getHostByName("test");
        assertEquals("10.59.141.6",host.getIp());
    }
}
