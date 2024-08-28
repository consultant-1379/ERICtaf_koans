package com.ericsson.cifwk.taf.koans.configuration.test.cases;

import static com.sandwich.koan.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;


import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.data.HostType;
import com.sandwich.koan.Koan;
import com.sandwich.koan.KoanIncompleteException;
import com.sandwich.util.koan.KoansHelper;
import org.testng.annotations.Test;

import java.util.List;

public class RetrieveHostInformation extends KoansHelper {

    /**
     * In this koan we are looking for one host of a particular type
     * The hosts can be found in host.properties.json file in resources/taf_properties
     * Modify the koan with the expected ip
     */
    @Koan
    @Test
    @TestId(id="CIP-4935_Func_1",title="Retrieve One Host")
    public void getOneHost(){
        Host host = DataHandler.getHostByType(HostType.MS);
        assertEquals(__,host.getIp());
    }

    /**
     * This koan illustrated how to retrieve multiple hosts of the same type
     * Modify the host.properties.json file so that you retrieve two hosts of type SC1
     * Modify the koan with the ip addresses of the two hosts
     */
    @Koan
    @Test
    @TestId(id="CIP-4935_Func_2",title="Retrieve Multiple Hosts")
    public void getMultipleHosts(){
        List<Host> hosts = DataHandler.getAllHostsByType(HostType.SC1);
        assertEquals(2,hosts.size());
        assertEquals(__,hosts.get(0).getIp());
        assertEquals(__,hosts.get(1).getIp());
    }

    /**
     * In this koan we will return a node
     * The node object is same as host object, the only difference is that the node object doesn’t have child property nodes.
     * DataHandler.getSpecificNode retrieves a node of a specific type from a specific host
     * Modify the below koan to return the node jboss1 from host sc1
     */
    @Koan
    @Test
    @TestId(id="CIP-4935_Func_3",title="Retrieve JBOSS Node")
    public void verifyJBOSSNode(){
        String nodeIP;
        String nodeName;
        Host node = null;
        try{
            nodeIP = node.getIp();
            nodeName = node.getHostname();
        }catch (NullPointerException nodeNotFound){
            throw new KoanIncompleteException("Cannot find node");
        }
        assertEquals("0.0.0.2",nodeIP);
        assertEquals("jboss1",nodeName);
    }


}
