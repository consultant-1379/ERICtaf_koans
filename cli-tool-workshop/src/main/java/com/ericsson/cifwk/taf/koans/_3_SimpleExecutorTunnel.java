package com.ericsson.cifwk.taf.koans;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.data.Ports;
import com.ericsson.cifwk.taf.data.User;
import com.ericsson.cifwk.taf.tools.CliCommandResult;
import com.ericsson.cifwk.taf.tools.CliTool;
import com.ericsson.cifwk.taf.tools.CliTools;
import com.ericsson.cifwk.taf.tools.SimpleExecutorBuilder;
import com.ericsson.cifwk.taf.tools.TargetHost;
import org.junit.After;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * This suite of tests demonstrates SimpleExecutor tunneling.
 * Tunneling is required when we need to establish connection with host inside internal network through the gateway.
 * <pre>
 * Example:
 * We have two hosts A and B.
 * Where:
 *      A - gateway
 *      B - host inside internal network.
 * We need get access to the host B. In this case host B is reachable only in internal network.
 * It is possible to get internal network only via gateway host (tunnel).
 *
 *                   Internal network
 *                  ___________________
 *            1    |    2              |
 *   Client ----->(A)------>(B)        |
 *                 |___________________|
 * </pre>
 */
public class _3_SimpleExecutorTunnel {

    public static final String GATEWAY_INTERNAL_ADDRESS = "192.168.0.1";

    private CliTool cliTool;

    /**
     * This step demonstrates connection to host via tunnel
     */
    @Test
    public void testConnectionViaTunnel() {
        Host grafanaHost = DataHandler.getHostByName("grafana");
        Host gatewayHost = DataHandler.getHostByName("gateway");

        //TODO: Create SimpleExecutor and provide connection to 'grafana' host bia gateway;
        //...

        CliCommandResult ifconfigResult = cliTool.execute("ifconfig");
        assertThat(ifconfigResult.getOutput()).contains(String.format("inet addr:%s", grafanaHost.getIp()));

        CliCommandResult result = cliTool.execute("last -i | head -n 1", 10);
        //That assertion is required to be sure that the tool wasn't connected directly to the 'grafana' host
        assertThat(result.getOutput()).contains("still logged in");
        assertThat(result.getOutput()).contains(GATEWAY_INTERNAL_ADDRESS);
    }

    /**
     * This step demonstrates that the tool allows to establish only one level of tunneling.
     * Sequential method withTunnelHost() calls just overwrite gateway (tunnel) host
     * <p>
     * Hint: Remember that the Gateway in internal network use internal address. {@value GATEWAY_INTERNAL_ADDRESS}
     */
    @Test
    public void multipleTunnelingIsImpossible() {
        Host grafanaHost = DataHandler.getHostByName("grafana");
        Host saikuHost = DataHandler.getHostByName("saiku");
        Host gatewayHost = DataHandler.getHostByName("gateway");

        SimpleExecutorBuilder builder = CliTools.simpleExecutor(grafanaHost);
        builder.withTunnelHost(createTargetHostFromHost(saikuHost));
        builder.withTunnelHost(createTargetHostFromHost(gatewayHost));

        cliTool = builder.build();

        //TODO: Replace variable value with gateway internal address to pass the test. Please see task hint
        String tunnelInternalAddress = "666";

        CliCommandResult result = cliTool.execute("last -i | head -n 1", 10);
        assertThat(result.getOutput()).contains("still logged in");
        assertThat(result.getOutput()).contains(tunnelInternalAddress);
    }

    private TargetHost createTargetHostFromHost(Host host) {
        User firstAdminUser = host.getFirstAdminUser();
        return new TargetHost(firstAdminUser.getUsername(), firstAdminUser.getPassword(), host.getIp(), host.getPort(Ports.SSH));
    }

    @After
    public void tearDown() {
        if (cliTool != null) {
            cliTool.close();
        }
    }
}
