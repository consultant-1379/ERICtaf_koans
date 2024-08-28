package com.ericsson.cifwk.taf.koans.clitools.test.helper;

import org.testng.annotations.Guice;

import static com.sandwich.util.Assert.assertTrue;

import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.guice.OperatorLookupModuleFactory;
import com.sandwich.util.koan.proxy.KoanProxyFactory.KoanDecorator;

@Guice(moduleFactory = OperatorLookupModuleFactory.class)
public class CliKoanHelper {

    public static interface Shell extends com.ericsson.cifwk.taf.tools.cli.Shell, KoanDecorator<String> {

    }

    public static class CLICommandHelper extends com.ericsson.cifwk.taf.tools.cli.CLICommandHelper implements KoanDecorator<String> {

        public Host hostCreated;

        public CLICommandHelper(Host host) {
            super(host);
            hostCreated = host;
            assertTrue(true);
        }

        @Override
        public int __() {
            return -1;
        }

        public String hostCreated() {
            return hostCreated.getIp();
        }
        
        public String __(String... args) {
            return "";
        }

        @Override
        public String __(Object... args) {
            return null;
        }

    }

}
