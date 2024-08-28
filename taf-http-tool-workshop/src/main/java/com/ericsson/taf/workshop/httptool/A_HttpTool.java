package com.ericsson.taf.workshop.httptool;

import com.ericsson.cifwk.taf.configuration.TafConfigurationProvider;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.data.processor.HostConstructor;
import com.ericsson.cifwk.taf.datasource.TestDataSource;
import com.ericsson.cifwk.taf.tools.http.HttpEndpoint;
import com.ericsson.cifwk.taf.tools.http.HttpResponse;
import com.ericsson.cifwk.taf.tools.http.HttpTool;
import com.ericsson.cifwk.taf.tools.http.HttpToolBuilder;
import com.ericsson.cifwk.taf.tools.http.constants.ContentType;
import com.ericsson.cifwk.taf.tools.http.constants.HttpStatus;
import com.ericsson.taf.workshop.httptool.common.Node;
import com.ericsson.taf.workshop.httptool.common.___;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.ericsson.cifwk.taf.datasource.TafDataSources.fromCsv;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

/**
 * To complete each exercise you will need to replace placeholders with code
 */
public class A_HttpTool {
    public static String HTTP_BIN = "httpbin.org";

    //Set up Jackson
    ObjectMapper jsonMapper = new ObjectMapper();
    TypeReference<HashMap<String, Object>> toMap = new TypeReference<HashMap<String, Object>>() {
    };

    /**
     * Lets start with a simple one to get familiar with the process:
     * <p>
     * Requirement is to connect to www.ericsson.com (defined in hosts.properties.json) and assert that it contains
     * text "A world of communication"
     */
    @Test
    public void doBasicGet() throws Exception {
        HttpEndpoint host = getHost(___.<String>___());

        HttpTool tool = HttpToolBuilder.newBuilder(host)
                .build();

        HttpResponse response = ___.<HttpResponse>___();

        assertThat(response.getResponseCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("A world of communication");
    }

    /**
     * Task is to verify that response has status 200, has executed in less than 3000 seconds, and target server is
     * "Apache Tomcat" (server info is located in header)
     *
     * @throws Exception
     */
    @Test
    public void verifyResponse() throws Exception {
        HttpTool tool = HttpToolBuilder.newBuilder(getHost("www.ericsson.com"))
                .build();

        HttpResponse response = tool.get("/");

        assertThat(response.getBody()).contains("A world of communication");
        assertThat(___.<HttpStatus>___()).isEqualTo(HttpStatus.OK);
        assertThat(___.<Long>___()).isLessThan(3000L);
        assertThat(___.<Map>___()).contains(entry("Server", "Apache Tomcat"));
    }

    /**
     * Task is to test that host HTTP_BIN path "/get" is available both by ip and hostname
     */
    @Test
    public void doHostnameGet() throws Exception {
        HttpEndpoint host = getHost(HTTP_BIN);

        ___.___();
        ___.___();

        HttpResponse getByIp = ___.___();
        HttpResponse getByHost = ___.___();

        assertThat(getRequestUrl(getByIp)).contains("54.175.219.8");
        assertThat(getRequestUrl(getByHost)).contains("httpbin.org");
    }


    /**
     * Task is to connect to host `HTTP_BIN` path `/get` using HTTPS
     */
    @Test
    public void doHttpsGet() throws Exception {
        HttpEndpoint tafHost = getHost(HTTP_BIN);

        HttpTool tool = HttpToolBuilder.newBuilder(tafHost)
                //.__()
                //.__()
                .build();

        HttpResponse response = tool.get("/get");

        assertThat(response.getResponseCode()).isEqualTo(HttpStatus.OK);
        assertThat(getRequestUrl(response)).startsWith("https://");
    }


    /**
     * Task is to execute search for keywords "TAF User Area" on confluence:
     * * Host `confluence-oss.lmera.ericsson.se`
     * * Use HTTPS
     * * Path "dosearchsite.action"
     * * Query Param "queryString" = "TAF User Area"
     * TIP: note that Confluence currently has a self signed certificate
     */
    @Test
    public void doSelfSignedSSLGetWithArguments() throws Exception {
        HttpEndpoint tafHost = getHost("confluence-oss.lmera.ericsson.se");

        HttpTool tool = HttpToolBuilder.newBuilder(tafHost)
                //.__()
                //.__()
                //.__()
                .build();

        HttpResponse response = tool.request()
                //.__()
                .get(___.<String>___());

        assertThat(response.getResponseCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("WELCOME!");
    }

    /**
     * Task is to execute POST request to HTTP_BIN/post with Data Record `node` serialized to JSON
     * TIP: use Jackson {@link #jsonMapper}
     */
    @Test
    public void postJson() throws Exception {
        TestDataSource<Node> csvDataSource = fromCsv("data/node.csv", Node.class);
        Node node = csvDataSource.iterator().next();

        String jsonData = ___.___();

        HttpTool tool = HttpToolBuilder.newBuilder(getHost(HTTP_BIN)).build();
        HttpResponse postResponse = tool.request()
                //.___()
                //.___()
                .post(___.<String>___());

        assertThat(postResponse.getResponseCode()).isEqualTo(HttpStatus.OK);
        HashMap<String, Object> response = jsonMapper.readValue(postResponse.getBody(), toMap);
        // HttpBin return json from post request in `response` field
        HashMap<String, Object> postedJson = (HashMap<String, Object>) response.get("json");
        assertThat(postedJson)
                .contains(entry("id", "idABC"))
                .contains(entry("type", "typeDEF"));
        // HttpBin return request headers in `headers` field
        HashMap<String, Object> postedHeaders = (HashMap<String, Object>) response.get("headers");
        assertThat(postedHeaders)
                .contains(entry("Content-Type", ContentType.APPLICATION_JSON));
    }

    /**
     * Task is to download image from HTTP_BIN/image/png and compare it with sample
     * located in resources directory
     */
    @Test
    public void doDownloadFile() throws Exception {
        HttpTool tool = HttpToolBuilder.newBuilder(getHost(HTTP_BIN)).build();
        HttpResponse response = tool.request()
                .get("/image/png");

        InputStream contentStream = ___.___();
        byte[] downloadedImage = ___.___();

        byte[] sample = Resources.toByteArray(Resources.getResource("sample.png"));
        assertThat(response.getResponseCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getContentType()).isEqualTo("image/png");
        assertThat(downloadedImage).isEqualTo(sample);
    }

    private String getRequestUrl(HttpResponse response) throws java.io.IOException {
        HashMap<String, Object> responseJson = jsonMapper.readValue(response.getBody(), toMap);
        Object url = responseJson.get("url");
        return url.toString();
    }

    public static HttpEndpoint getHost(final String hostname) {
        Properties properties = TafConfigurationProvider.provide().getProperties();

        Map<String, Object> hostProperties = Maps.newHashMap();
        Map<String, Object> ports = Maps.newHashMap();
        hostProperties.put(HostConstructor.PORT, ports);
        for (Map.Entry<Object, Object> property : properties.entrySet()) {
            String key = property.getKey().toString();
            if (key.contains(hostname)) {
                String hostProperty = key.substring(key.lastIndexOf(hostname) + hostname.length() + 1, key.length());
                if (hostProperty.startsWith(HostConstructor.PORT)) {
                    ports.put(hostProperty.replace(HostConstructor.PORT + ".", ""), property.getValue());
                } else {
                    hostProperties.put(hostProperty, property.getValue());
                }
            }
        }

        return HostConstructor.constructHost(hostname, hostProperties);
    }

}
