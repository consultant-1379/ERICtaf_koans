/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.cifwk.taf.koans.clitools.test.operators;

import com.ericsson.cifwk.taf.annotations.Operator;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.koans.clitools.test.helper.CliKoanHelper.CLICommandHelper;
import com.sandwich.util.koan.KoansHelper;
import static com.sandwich.koan.KoanConstants.__;

@Operator
public class CLIToolsOperatorImpl extends KoansHelper {

    Host host = DataHandler.getHostByName("testCLIServer");
    CLICommandHelper cmdHelper = new CLICommandHelper(host);

    /*
     * DataHandler.getHostByName("hostname") returns host object having particular hostname.
     * Update the following method to create CLICommandHelper with host "testCLIServer".
     */
    public String createObjectWithHost() {
        CLICommandHelper cmdHelper = new CLICommandHelper(_SampleHost_);
        String response = cmdHelper.hostCreated();
        return response;
    }

    /*
     * In this method you will have to update the code to find the correct method that executes a
     * command on a shell. This method leaves the shell open. The command passed is "ls".
     * See javadoc:
     * http://taf.lmera.ericsson.se/taflanding/apidocs/Latest/
     */
    public String executeCommand(String command) {
        String response = cmdHelper.__(command);
        return response;
    }

    /*
     * In this method you will have to update the code to find the correct method that performs a simple
     * execution of a command. The method opens a shell to the server, executes a command(s) and closes the shell.
     * See javadoc: for CLICommandHelper
     * http://taf.lmera.ericsson.se/taflanding/apidocs/Latest/
     */
    public String executeMultipleCommand(String... commands) {
        String response = cmdHelper.__(commands);
        return response;
    }

    /*
     * In this method you will have to update the code to find the correct methods
     * that opens the shell and disconnects from the shell
     */
    public String openAndCloseShell() {
        cmdHelper.__(); /* method to open the shell */
        cmdHelper.execute("cd ../tmp/koans");
        String response = cmdHelper.__("ls");/* method to execute the command */
        cmdHelper.__(); /* method to disconnect from the shell */
        return response;
    }

    /*
     * In this method you need to find the exit value of the command.
     * Replace __ with the method that will give you the exit value.
     */
    public int checkExitCode() {
        cmdHelper.execute("ls");
        int exitCode = cmdHelper.__();
        cmdHelper.disconnect();
        return exitCode;
    }

    /*
     * In this method you will have to update the code to find the correct methods
     * to execute a script and interact with the shell and expect the response
     * The script ("UserDetails.sh") asks for a name and then prints out the name once
     * the user has interacted with the shell
     */
    public String interactWithScript() {
        cmdHelper.openShell();
        cmdHelper.__("/tmp/KoansUserDetails.sh"); /* method to run interactive script */
        cmdHelper.__("Enter your name:"); /* method to expect the output */
        cmdHelper.__(__); /* method to interact with shell, you also need to pass in a user name */
        String response = cmdHelper.__("Your name is "); /* method to expect the output */
        cmdHelper.disconnect();
        return response;
    }

}
