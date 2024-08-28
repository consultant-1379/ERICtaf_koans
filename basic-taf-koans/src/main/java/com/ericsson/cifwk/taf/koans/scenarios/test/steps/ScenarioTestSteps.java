package com.ericsson.cifwk.taf.koans.scenarios.test.steps;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.cifwk.taf.annotations.*;
import com.ericsson.cifwk.taf.koans.scenarios.test.operators.UserMgmtOperator;
import com.google.inject.Inject;

import static org.testng.Assert.*;

public class ScenarioTestSteps {

    public static Object testStepRun = false;
    public int count = 0;
    public static int vUserLoginCount = 0;
    public static int vUserPromoteCount = 0;
    public static int vUserDemoteCount = 0;
    public static List<String> upLevels = new ArrayList<String>();
    public static List<String> downLevels = new ArrayList<String>();
    public static String user;

    Logger log = LoggerFactory.getLogger(ScenarioTestSteps.class);

    @Inject
    private UserMgmtOperator oper;

    /*This is the method which you must annotate with the TestStep annotation as per the instructed in CreateFlow.CreateAFlowAndAddATestStep()
     * Please see the subsequent methods for example */
    public void login(@Input("user") String user) {
        log.info("Logging into the system");
        this.user = user;
        vUserLoginCount++;
        assertEquals(oper.login(), true);
        
    }

    @TestStep(id="findUser")
    public void findUser(@Input("user") String user) {
        log.info("Finding user");
        assertEquals(user,"Patrick");
    }

    @TestStep(id="selectUser")
    public void selectUser(@Input("user") String user) {
        log.info("Promoting user: {}" , user);
        assertTrue(oper.selectUser(user).contains(user));
        testStepRun = true;
    }
    
    @TestStep(id="promoteUser")
    public void promoteUser(@Input("level") String level) {
        synchronized (this) {
            count++;
        }
        vUserPromoteCount++;
        upLevels.add(level);
        log.info("Number of innvocations is {}",count);
    }
    
    @TestStep(id="demoteUser")
    public void demoteUser(@Input("level")String level, @Input("user") String user){
        log.info("Demoting user: {}", user);
        vUserDemoteCount++;
        if(level != null)
            downLevels.add(level);
    }

    /**
     * @return the vUserLoginCount
     */
    public static int getvUserLoginCount() {
        return vUserLoginCount;
    }

    /**
     * @param vUserLoginCount the vUserLoginCount to set
     */
    public static void setvUserLoginCount(int vUserLoginCount) {
        ScenarioTestSteps.vUserLoginCount = vUserLoginCount;
    }

    /**
     * @return the vUserPromoteCount
     */
    public static int getvUserPromoteCount() {
        return vUserPromoteCount;
    }

    /**
     * @param vUserPromoteCount the vUserPromoteCount to set
     */
    public static void setvUserPromoteCount(int vUserPromoteCount) {
        ScenarioTestSteps.vUserPromoteCount = vUserPromoteCount;
    }

    /**
     * @return the vUserDemoteCount
     */
    public static int getvUserDemoteCount() {
        return vUserDemoteCount;
    }

    /**
     * @param vUserDemoteCount the vUserDemoteCount to set
     */
    public static void setvUserDemoteCount(int vUserDemoteCount) {
        ScenarioTestSteps.vUserDemoteCount = vUserDemoteCount;
    }
    
    

}
