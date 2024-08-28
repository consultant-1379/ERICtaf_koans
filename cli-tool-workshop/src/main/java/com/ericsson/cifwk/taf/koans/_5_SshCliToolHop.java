package com.ericsson.cifwk.taf.koans;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.tools.CliToolShell;
import com.ericsson.cifwk.taf.tools.CliTools;
import com.ericsson.cifwk.taf.tools.SshShellBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.ericsson.cifwk.taf.koans.common.CliToolWorkshopHelper.__HOST_GAP__;
import static com.google.common.truth.Truth.assertThat;

/**
 * This suite of tests demonstrates how to establish ssh connection to the specified host.
 * It is possible to establish connection via multiple hosts.
 *
 * Example:
 * We have three hosts A, B and C.
 * We need get access to host C. In this case host B is reachable from host A and host C is reachable from host B
 * (A)----->(B)----->(C)
 */
public class _5_SshCliToolHop {

    private CliToolShell cliTool;

    private String saikuIp;
    private String gatewayIp;

    /**
     * This step demonstrates how to establish ssh connection
     */
    @Before
    public void setUp() throws Exception {
        Host gatewayHost = DataHandler.getHostByName("gateway");
        Host saikuHost = DataHandler.getHostByName("saiku");

        //TODO: Replace __HOST_GAP__ constant with entry host
        SshShellBuilder builder = CliTools.sshShell(__HOST_GAP__);
        cliTool = builder.build();

        //TODO: Provide 'hop' to 'saiku' host
        //...

        this.saikuIp = saikuHost.getIp();
        this.gatewayIp = gatewayHost.getIp();
    }

    @Test
    public void testSuccessfulHop() {
        assertThat(cliTool.execute("exit").getOutput()).contains(String.format("Connection to %s closed.", saikuIp));
        assertThat(cliTool.execute("ifconfig", 10).getOutput()).contains(String.format("inet addr:%s", gatewayIp));
    }


    @Test
    public void testConnectionViaMultipleHosts() {
        Host grafana = DataHandler.getHostByName("grafana");

        //TODO: Add ssh connection to 'grafana' host
        //...

        assertThat(cliTool.execute("exit").getOutput()).contains(String.format("Connection to %s closed.", grafana.getIp()));
        assertThat(cliTool.execute("exit").getOutput()).contains(String.format("Connection to %s closed.", saikuIp));
        assertThat(cliTool.execute("ifconfig").getOutput()).contains(String.format("inet addr:%s", gatewayIp));
    }

    @After
    public void tearDown() {
        if (cliTool != null) {
            cliTool.close();
        }
    }

}
