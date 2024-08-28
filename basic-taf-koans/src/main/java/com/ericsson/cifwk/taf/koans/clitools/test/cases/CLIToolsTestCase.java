package com.ericsson.cifwk.taf.koans.clitools.test.cases;

import static com.sandwich.koan.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;
import static com.sandwich.util.Assert.assertTrue;

import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.koans.clitools.test.helper.CliKoanHelper;
import com.ericsson.cifwk.taf.koans.clitools.test.operators.CLIToolsOperatorImpl;
import com.google.inject.Inject;
import com.sandwich.koan.Koan;
import org.testng.annotations.Test;

public class CLIToolsTestCase extends CliKoanHelper  {

    @Inject
    CLIToolsOperatorImpl cliToolsOper;

    /**
     * In this koan you will instantiate a CLICommandHelper object
     * Update the operator (CLIToolsOperatorImpl.java) to add the correct host
     * (HINT: replace _SampleHost_ with a host object in the createObjectWithHost() method).
     */
    @Koan
    @Test
    @TestId(id = "CIP-6981_Func_1", title = "create object with host")
    public void createObjectWithHost() {
        String response = cliToolsOper.createObjectWithHost();
        assertEquals(response, "atclvm1170.athtem.eei.ericsson.se");
    }

    /**
     * In this koan you will execute a command using the CLICommandHelper
     * (HINT: You will need to update the executeCommand() method in operator CLIToolsOperatorImpl)
     */
    @Koan
    @Test
    @TestId(id = "CIP-6981_Func_2", title = "execute command")
    public void executeCommand() {
        String response = cliToolsOper.executeCommand("ls");
        assertTrue(response.contains("testFile"));
    }

    /**
     * In this koan you will execute multiple commands using the CLICommandHelper
     * (HINT: You will need to update the executeMultipleCommand() method in operator CLIToolsOperatorImpl)
     */
    @Koan
    @Test
    @TestId(id = "CIP-6981_Func_3", title = "execute multiple commands")
    public void executeMultipleCommands() {
        String response = cliToolsOper.executeMultipleCommand("cd ../tmp", "ls", "echo Hello World");
        assertTrue(response.contains("Hello World"));
    }

    /**
     * In this koan you will execute commands to open, close and disconnect the shell using the CLICommandHelper
     * (HINT: You will need to update the openAndCloseShell() method in operator CLIToolsOperatorImpl)
     */
    @Koan
    @Test
    @TestId(id = "CIP-6981_Func_4", title = "execute single command one by one")
    public void openCloseAndDisconnectShell() {
        String response = cliToolsOper.openAndCloseShell();
        assertTrue(response.contains("SampleText"));
    }

    /**
     * In this koan you will check the shell exit response code
     * (HINT: You will need to update the checkExitCode() method in operator CLIToolsOperatorImpl)
     */
    @Koan
    @Test
    @TestId(id = "CIP-6981_Func_5", title = "check exit code of command")
    public void checkExitCode() {
        int response = cliToolsOper.checkExitCode();
        assertEquals(response, 0);
    }

    /**
     * In this koan you will interact with the prompt. A script will be executed which asks for your name
     * and you must find the appropriate methods to interact with the shell and expect the response.
     * (HINT: You will need to update the interactWithScript() method in operator CLIToolsOperatorImpl)
     */
    @Koan
    @Test
    @TestId(id = "CIP-6981_Func_6", title = "interact with the prompt")
    public void interactWithPrompt() {
        String response = cliToolsOper.interactWithScript();
        assertTrue(response.contains(__)); //Replace __ with the user name passed to the shell in the method interactWithScript() in the operator CLIToolsOperatorImpl
    }
}
