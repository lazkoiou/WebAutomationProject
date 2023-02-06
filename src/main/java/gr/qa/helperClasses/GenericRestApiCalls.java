package gr.qa.helperClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GenericRestApiCalls extends BaseObject {

    private final static Logger logger = LogManager.getLogger(GenericRestApiCalls.class);

    private CloseableHttpClient httpClient;

    /**
     * Serializes DTO to JSON
     * @param DTO : DTO to be converted into JSON
     * @return : JSON (String)
     */
    public String serializeDtoToJson(Object DTO) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json =  gson.toJson(DTO);
        logger.info("DTO converted to JSON: \n" + json);
        return json;
    }

    /**
     * De-serializes a Json to a DTO
     * @param json : Json to be de-serialized
     * @param classOfT : class of the DTO we want it to be de-serialized to
     * @param <T> : generic type of DTO class
     * @return : the DTO in which Json has been de-serialized to
     */
    public <T> T deSerializeJsonToDto(String json, Class<T> classOfT) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, classOfT);
    }

    /**
     * Executes a GET request
     * @param endpointURL : the endpoint URL we want to hit
     * @return : the response in JSON (String) if request is successful - else null
     */
    public String getRequest(String endpointURL) {
        logger.info("Executing GET request to endpoint '" + endpointURL + "'");
        httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(endpointURL);
        try { // Execute request and get response
            HttpResponse httpResponse = httpClient.execute(request);
            String httpResponseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            String responseStatus = httpResponse.getStatusLine().toString();
            logger.info("Response status: " + responseStatus);
            if (responseStatus.contains("HTTP/1.1 200") || responseStatus.contains("HTTP/1.1 201")) {
                logger.info("Request successful.");
                return httpResponseBody;
            }
            else {
                logger.error("Response unsuccessful!");
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Request failed!");
            logger.error("Error message: " + e.getMessage());
        }
        return null;
    }

    /**
     * Execute a POST request
     * @param endpointURL : url of the endpoint we want to execute the  POST request to
     * @param jsonParam : JSON passed as a parameter in the POST request
     * @return : JSON response if POST request is executed successfully, else null
     */
    public String postRequest(String endpointURL, String jsonParam) {
        logger.info("Executing POST request to endpoint '" + endpointURL + "'");
        httpClient = HttpClientBuilder.create().build();
        HttpPost httpPostRequest = new HttpPost(endpointURL);
        StringEntity params = new StringEntity(jsonParam, StandardCharsets.UTF_8); // UTF_8 needed for Greek characters
        httpPostRequest.addHeader("content-type", "application/json");
        httpPostRequest.setEntity(params);
        HttpResponse httpResponse;
        try {
            httpResponse = httpClient.execute(httpPostRequest);
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            String responseStatus = httpResponse.getStatusLine().toString();
            logger.info("Response status: " + responseStatus);
            if (responseStatus.contains("HTTP/1.1 200") || responseStatus.contains("HTTP/1.1 201")) {
                logger.info("Request successful.");
                return responseBody;
            }
            else {
                logger.error("Response unsuccessful!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Request failed!");
            logger.error("Error message: " + e.getMessage());
        }
        return null;
    }

    /**
     * Execute a PUT request
     * @param endpointURL : url of the endpoint we want to execute the  PUT request to
     * @param jsonParam : JSON passed as a parameter in the PUT request
     * @return : JSON response if PUT request is executed successfully, else null
     */
    public String putRequest(String endpointURL, String jsonParam) {
        logger.info("Executing PUT request to endpoint '" + endpointURL + "'");
        httpClient = HttpClientBuilder.create().build();
        HttpPut httpPutRequest = new HttpPut(endpointURL);
        StringEntity params = new StringEntity(jsonParam, StandardCharsets.UTF_8); // UTF_8 needed for Greek characters
        httpPutRequest.addHeader("content-type", "application/json");
        httpPutRequest.setEntity(params);
        HttpResponse httpResponse;
        try {
            httpResponse = httpClient.execute(httpPutRequest);
            String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            String responseStatus = httpResponse.getStatusLine().toString();
            logger.info("Response status: " + responseStatus);
            if (responseStatus.contains("HTTP/1.1 200") || responseStatus.contains("HTTP/1.1 201")) {
                logger.info("Request successful.");
                return responseBody;
            }
            else {
                logger.error("Response unsuccessful!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Request failed!");
            logger.error("Error message: " + e.getMessage());
        }
        return null;
    }

    /**
     * Executes a DELETE request
     * @param endpointURL : the endpoint URL we want to hit
     * @return : JSON response if DELETE request is executed successfully, else null
     */
    public String deleteRequest(String endpointURL) {
        logger.info("Executing DELETE request to endpoint '" + endpointURL + "'");
        httpClient = HttpClientBuilder.create().build();
        HttpDelete request = new HttpDelete(endpointURL);
        try { // Execute request and get response
            HttpResponse httpResponse = httpClient.execute(request);
            String httpResponseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            String responseStatus = httpResponse.getStatusLine().toString();
            logger.info("Response status: " + responseStatus);
            if (responseStatus.contains("HTTP/1.1 200") || responseStatus.contains("HTTP/1.1 201")) {
                logger.info("Request successful.");
                return httpResponseBody;
            }
            else {
                logger.error("Response unsuccessful!");
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Request failed!");
            logger.error("Error message: " + e.getMessage());
        }
        return null;
    }

}
