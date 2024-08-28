package com.ericsson.cifwk.taf.koans;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.tools.CliCommandResult;
import com.ericsson.cifwk.taf.tools.CliTool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.ericsson.cifwk.taf.koans.common.CliToolWorkshopHelper.__CLI_TOOL_INSTANCE_GAP__;
import static com.ericsson.cifwk.taf.koans.common.CliToolWorkshopHelper.___;
import static com.google.common.truth.Truth.assertThat;

/**
 * This suite of tests demonstrates Simple executor usage. This tool is stateless. Each command executes in a new shell session.
 * All session specific changes are not visible in the next execution.
 * Example:
 *     tool.execute("export test=myTestVar");
 *     tool.execute("printenv test"); //variable value is empty because it was created in previous session
 *
 * The main difference between Local Executor and Simple Executor is that SimpleExecutor executes command on remote host.
 *
 */
public class _2_SimpleExecutor {

    private CliTool cliTool;

    /**
     * This step demonstrates SimpleExecutor initialization
     */
    @Before
    public void init() {
        Host host = DataHandler.getHostByName("gateway");
        //TODO: Replace __CLI_TOOL_INSTANCE_GAP__ with Simple Executor instance and establish connection to predefined 'host'
        cliTool = __CLI_TOOL_INSTANCE_GAP__;
    }

    /**
     * This test demonstrates command result retrieving
     */
    @Test
    public void retrievingCommandResult() {
        CliCommandResult result = cliTool.execute("echo Hello world");
        assertThat(result.isSuccess()).isTrue();

        //TODO: Replace '___' with actual command output
        String actualCommandOutput = ___;
        assertThat(actualCommandOutput).isEqualTo("Hello world");

        //TODO: Replace variable value with actual command exit code
        int actualExitCode = 666;
        assertThat(actualExitCode).isEqualTo(0);
    }

    /**
     * This step demonstrates that the tool is stateless
     */
    @Test
    public void testToolIsStateless() {
        String envVariableValue = "testenv";
        CliCommandResult firstResult = cliTool.execute("printenv test");
        assertThat(firstResult.getOutput()).isEmpty();

        String exportEnv = "export test=" + envVariableValue;
        //TODO: Provide command execution with predefined command in 'exportEnv'.
        //...

        String command = "printenv test";
        //TODO: Provide command execution with predefined command.
        //...


        //TODO: Replace 'envVariableValue' with command actual output
        assertThat(envVariableValue).isEmpty();
    }

    /**
     * This step demonstrates timeout behavior
     * <p>
     * Remember: timeout can't be less than command total time
     */
    @Test
    public void timeoutDemonstration() {
        //TODO: Execute this command successfully
        CliCommandResult result = cliTool.execute("sleep 3", 1);
        assertThat(result.isSuccess()).isTrue();
    }

    @After
    public void tearDown() {
        if (cliTool != null) {
            cliTool.close();
        }
    }
}
