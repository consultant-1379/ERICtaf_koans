package com.ericsson.taf.workshop.httptool.common;

import com.ericsson.cifwk.taf.TestContext;
import com.ericsson.cifwk.taf.tools.http.HttpTool;

import javax.inject.Inject;

/**
 * Class to retrieve {@link HttpTool}
 * (!) This is mock adapted for workshop. In real testing please use
 * `com.ericsson.oss.testware.security.authentication.tool.TafToolProvider` from `enm-security-test-library`
 */
public final class TafToolProvider {

    public static final String HTTP_TOOL = "tafHttpTool";

    @Inject
    TestContext testContext;

    /**
     * Return HttpTool from Tool.
     * If the HttpTool is not stored in context it returns null.
     *
     * @return {@link HttpTool} Tool
     */
    public HttpTool getHttpTool() {
        return testContext.getAttribute(HTTP_TOOL);
    }

    /**
     * Set HttpTool into context.
     *
     * @param tool HttpTool object
     */
    public void setToolToContext(final HttpTool tool) {
        if (tool == null) {
            testContext.removeAttribute(HTTP_TOOL);
        } else {
            testContext.setAttribute(HTTP_TOOL, tool);
        }
    }
}
