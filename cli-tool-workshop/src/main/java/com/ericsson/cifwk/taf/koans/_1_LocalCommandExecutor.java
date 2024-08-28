package com.ericsson.cifwk.taf.koans;

import com.ericsson.cifwk.taf.tools.CliCommandResult;
import com.ericsson.cifwk.taf.tools.CliTool;
import org.junit.Before;
import org.junit.Test;

import static com.ericsson.cifwk.taf.koans.common.CliToolWorkshopHelper.__BOOLEAN_GAP__;
import static com.ericsson.cifwk.taf.koans.common.CliToolWorkshopHelper.__CLI_TOOL_INSTANCE_GAP__;
import static com.ericsson.cifwk.taf.koans.common.CliToolWorkshopHelper.___;
import static com.google.common.truth.Truth.assertThat;

/**
 * This suite of tests demonstrates usage of LocalCommandExecutor.
 * LocalCommandExecutor allows you to execute commands on local machine.
 *
 */
public class _1_LocalCommandExecutor {

    private static final String TEXT = "Hello";

    private CliTool localCliTool;

    /**
     * This step demonstrates LocalCliTool initialization.
     *
     * Hint: See CliTools factory class before you start
     */
    @Before
    public void init() {
        //TODO: Replace __CLI_TOOL_INSTANCE_GAP__ with Local CliTool instance
        localCliTool = __CLI_TOOL_INSTANCE_GAP__;
        assertThat(localCliTool).isNotNull();
    }

    /**
     * This test demonstrates command execution and retrieving command output
     */
    @Test
    public void commandExecutionAndGettingResult() {
        CliCommandResult commandResult = localCliTool.execute("echo Hello World");

        //TODO: Retrieve command result and replace '___' with actual command output
        assertThat(___).contains(TEXT);
    }

    /**
     * This test demonstrates command execution without any errors
     */
    @Test
    public void commandExecutionSuccessful() {
        CliCommandResult commandResult = localCliTool.execute("echo Hello World");

        //TODO: Check if command execution successful
        assertThat(__BOOLEAN_GAP__).isTrue();
    }

    /**
     * This test demonstrates command execution with custom timeout
     */
    @Test
    public void commandExecutionWithCustomTimeout() {
        String commandWithTimeout = isWindows() ? "waitfor longLoopCommand /t 3" : "sleep 3";

        //TODO: fix execution timeout to pass the test. Remember that the timeout must be greater than command execution time
        localCliTool.execute(commandWithTimeout, 2);
    }

    private boolean isWindows() {
        return System.getProperty("os.name").startsWith("Windows");
    }

}
