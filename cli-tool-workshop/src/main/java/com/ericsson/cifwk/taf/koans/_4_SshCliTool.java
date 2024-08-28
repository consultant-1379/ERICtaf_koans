package com.ericsson.cifwk.taf.koans;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.data.UserType;
import com.ericsson.cifwk.taf.tools.CliCommandResult;
import com.ericsson.cifwk.taf.tools.CliIntermediateResult;
import com.ericsson.cifwk.taf.tools.CliTool;
import com.ericsson.cifwk.taf.tools.CliToolShell;
import com.ericsson.cifwk.taf.tools.CliTools;
import com.ericsson.cifwk.taf.tools.WaitConditions;
import com.ericsson.cifwk.taf.tools.cli.handlers.impl.RemoteObjectHandler;
import com.google.common.io.Resources;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Random;

import static com.ericsson.cifwk.taf.koans.common.CliToolWorkshopHelper.__CLI_TOOL_SHELL_INSTANCE_GAP__;
import static com.ericsson.cifwk.taf.koans.common.CliToolWorkshopHelper.__WAIT_CONDITION_GAP__;
import static com.ericsson.cifwk.taf.koans.common.CliToolWorkshopHelper.___;
import static com.google.common.truth.Truth.assertThat;

/**
 * This suite of tests demonstrates usage of SshCliTool.
 * This tool is used when it is necessary to execute multiple commands in a single Shell session.
 */
public class _4_SshCliTool {

    private static final String INTERACTIVE_SCRIPT_NAME = "cli-tool-workshop-interactive-bash.sh";
    private static final String SCRIPT_EXEC_PREFIX = "./";
    private CliToolShell cliToolShell;
    private String multipleLinesContent;

    /**
     * This step demonstrates tool initialization
     * */
    @Before
    public void init() throws IOException {
        Host host = DataHandler.getHostByName("gateway");

        //TODO: Replace __CLI_TOOL_SHELL_INSTANCE_GAP__ with CliToolShell instance and establish connection to predefined 'host'
        cliToolShell = __CLI_TOOL_SHELL_INSTANCE_GAP__;

        URL resource = Resources.getResource("content/cli-tool-demo-logs.txt");
        multipleLinesContent = Resources.toString(resource, Charset.forName("utf-8"));
    }

    /**
     * This test demonstrates how to find substring first entry in output.
     * In this case WaitCondition interrupts command output when first entry of expression is detected
     *
     * See factory class WaitConditions.
     */
    @Test
    public void substringWaitCondition() {
        String substring = "FAILURE";
        cliToolShell.writeLine("echo -e '" + multipleLinesContent + "' > cli-tool-demo-logs.txt");

        //TODO: Using factory class WaitConditions create substring WaitCondition. As substring parameter use predefined variable 'substring'
        cliToolShell.writeLine("cat cli-tool-demo-logs.txt", __WAIT_CONDITION_GAP__);
    }

    /**
     * This test demonstrates WaitCondition using regular expression as String. See factory class WaitConditions
     */
    @Test
    public void regularExpressionAsStringWaitCondition() {
        String emailRegularExpression = ".+@.+";
        String thisStringContainsGeneratedEmail = String.format("\"Hello.\nThis string contains generated email %s\nPlease create pattern regular expression to find this email\"", EmailGenerator.generate());
        //TODO: Using factory class WaitConditions create WaitCondition for regular expression. As substring parameter use predefined variable 'emailValidationRegularExpression'
        cliToolShell.writeLine("printf " + thisStringContainsGeneratedEmail, __WAIT_CONDITION_GAP__);
    }

    /**
     * This step demonstrates that the tool is stateful
     *
     * Hint: Remember about stateful and stateless!
     */
    @Test
    public void testToolIsStateful() {
        Host host = DataHandler.getHostByName("gateway");
        CliTool simpleExecutor = CliTools.simpleExecutor(host).build();

        String envVariableValue = "testenv";

        CliCommandResult result1 = simpleExecutor.execute("printenv test");
        assertThat(result1.getOutput()).isEmpty();

        String exportEnv = "export test=" + envVariableValue;
        //TODO: Provide command execution with predefined command in 'exportEnv'. There are two possible ways how you can do that.
        //Hint: Remember about stateful and stateless!
        //...

        String command = "printenv test";
        //TODO: Provide command execution with predefined command. There are two possible ways how you can do that
        //...

        //TODO: Replace '___' with actual command output
        assertThat(___).contains(envVariableValue);
    }

    /**
     * This test demonstrates interactive input without exit code
     * For example you want to execute shell script which requires user input. In this case intermediate result does not have exit code.
     * Exit code available only when script is finished
     * <p>
     * Before you start please review the shell script /resources/scripts/cli-tool-workshop-interactive-bash.sh
     */
    @Test
    public void interactiveInputExampleWithoutExitCode() {
        uploadInteractiveScript();

        /* In this case we are sending command and expect substring */
        cliToolShell.writeLine(SCRIPT_EXEC_PREFIX + INTERACTIVE_SCRIPT_NAME, WaitConditions.substring("Enter any login:"));
        //TODO: Provide interactive steps to finish
        //...

        CliIntermediateResult intermediateResult = cliToolShell.writeLine("any_security_code");

        assertThat(intermediateResult.getOutput()).isEqualTo("Thanks!");
    }

    /**
     * This test demonstrates interactive input with exit code
     * For example you want execute shell script which requires user input; in this case intermediate result does not have exit code.
     * Exit code available only when script is finished
     * <p>
     * Before you start please review the shell script /resources/scripts/cli-tool-workshop-interactive-bash.sh
     */
    @Test
    public void interactiveInputExampleWithExitCode() {
        uploadInteractiveScript();

        /* In this case we are sending command and expect string */
        cliToolShell.writeLine("./" + INTERACTIVE_SCRIPT_NAME, WaitConditions.substring("Enter any login:"));
        cliToolShell.writeLine("any_login", WaitConditions.substring("Enter any password:"));
        cliToolShell.writeLine("any_password", WaitConditions.substring("Enter any security code:"));

        /**
         * execute() can be slower than writeLine() because writeLine() doesn't retrieve exit code
         * use execute when exit code is necessary
         */
        //TODO: Rewrite this code to get result with exit code
        CliIntermediateResult result = cliToolShell.writeLine("any_security_code");

        //TODO: Assert command output
        assertThat(___).isEqualTo("Thanks!");

        //TODO: Assert command exit code
        assertThat(___).isEqualTo(0);
    }

    private void uploadInteractiveScript() {
        Host netsim = DataHandler.getHostByName("gateway");
        RemoteObjectHandler remoteObjectHandler = new RemoteObjectHandler(netsim, netsim.getUsers(UserType.ADMIN).get(0));
        if (!remoteObjectHandler.remoteFileExists("/root/" + INTERACTIVE_SCRIPT_NAME)) {
            remoteObjectHandler.copyLocalFileToRemote("scripts/" + INTERACTIVE_SCRIPT_NAME, "/root/" + INTERACTIVE_SCRIPT_NAME);
        }
        cliToolShell.execute("chmod +x " + INTERACTIVE_SCRIPT_NAME);
    }

    @After
    public void tearDown() {
        if (cliToolShell != null) {
            cliToolShell.close();
        }
    }

    private static class EmailGenerator {

        private static final String[] NAMES = {"john.doe", "paul.gilbert", "mister.x", "spider.man", "hulk2017", "admin", "jackie.chan", "morgan.freeman"};
        private static final String[] DOMAINS = {"gmail.com", "yahoo.com", "inbox.com", "ericsson.com", "facebook.com"};

        static String generate() {
            Random rnd = new Random();
            String name = NAMES[rnd.nextInt(NAMES.length)];
            String domain = DOMAINS[rnd.nextInt(DOMAINS.length)];
            return String.format("%s@%s", name, domain);
        }
    }
}
