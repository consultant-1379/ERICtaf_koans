package com.ericsson.taf.workshop.parallel;

import com.ericsson.cifwk.taf.configuration.TafConfiguration;
import com.ericsson.cifwk.taf.configuration.TafConfigurationProvider;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.data.Ports;
import com.google.common.base.Preconditions;

/**
 * @author Kirill Shepitko kirill.shepitko@ericsson.com
 *         Date: 19/05/2016
 */
public class TestDataProvider {

    public static String getFullLoginUrl() {
        return getAbsoluteUrl("loginUrl");
    }

    public static String getFullMainPageUrl() {
        return getAbsoluteUrl("mainPageUrl");
    }

    public static String getRelativeLoginUrl() {
        return getRelativeUrl("loginUrl");
    }

    public static String getRelativeMainPageUrl() {
        return getRelativeUrl("mainPageUrl");
    }

    private static String getRelativeUrl(String relativeUrlNameInConfig) {
        TafConfiguration config = TafConfigurationProvider.provide();
        return config.getString(relativeUrlNameInConfig);
    }

    private static String getAbsoluteUrl(String relativeUrlNameInConfig) {
        Preconditions.checkArgument(relativeUrlNameInConfig != null);
        Host uiSdkAppHost = DataHandler.getHostByName("uiSdkApp");
        String ip = uiSdkAppHost.getIp();
        Integer httpPort = uiSdkAppHost.getPort(Ports.HTTP);
        return String.format("http://%s:%d/%s", ip, httpPort, getRelativeUrl(relativeUrlNameInConfig));
    }

}