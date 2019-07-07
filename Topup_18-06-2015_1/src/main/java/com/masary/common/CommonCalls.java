package com.masary.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.CashatCompniesInfo;
import com.masary.integration.dto.ChangePointsDTO;
import com.masary.integration.dto.TedataDenominationRepresentation;
import com.masary.utils.SystemSettingsUtil;

public class CommonCalls {

    /**
     *
     * @param propertyName load property from used properties file
     * @return proertValue
     */
    protected String loadUrlProperty(String propertyName) {
        return SystemSettingsUtil.getInstance().loadProperty(propertyName);
    }

    /**
     *
     * @param property given url to be replaced in the first index then path
     * variables in odd then param value in even
     * @return Full URL
     */
    protected String prepareUrl(String... property) {
        for (int i = 1; i < property.length - 1; i++) {
            if (i % 2 != 0) {
                property[0] = property[0].replace(property[i], property[i + 1]);
            }
        }
        return property[0];
    }

    /**
     *
     * @param closableHttpResponse parse http response to JSON string
     * @return JSON String
     * @throws IOException
     * @throws ParseException
     */
    protected String getJsonFromResponse(CloseableHttpResponse closableHttpResponse) throws IOException, ParseException {
        return EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
    }

    /**
     *
     * @param url
     * @param token add headers for HTTP GET method
     * @return httpGet
     * @throws URISyntaxException
     */
    protected HttpGet initHttpGet(String url, String token) throws URISyntaxException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-Type", "application/json");
        httpGet.addHeader("Authorization", token);
        httpGet.addHeader("deviceType", "Web");
        return httpGet;
    }

    /**
     *
     * @param urlParameters
     * @param url
     * @param token add headers and prepare body for HTTP POST method
     * @return httpPost
     * @throws UnsupportedEncodingException
     */
    protected HttpPost initHttpPost(String urlParameters, String url, String token) throws UnsupportedEncodingException {
        StringEntity params = new StringEntity(urlParameters);
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Authorization", token);
        httpPost.addHeader("deviceType", "Web");
        httpPost.setEntity(params);
        return httpPost;
    }

    /**
     *
     * @return httpClient initiate http client for socket connection
     * @throws Exception
     */
    protected CloseableHttpClient initHttpClient() throws Exception {
        return HttpClients.createDefault();
    }

    /**
     *
     * @param httpClient
     * @param httpGet execute HTTP GET method request to a socket
     * @return httpGet connection result
     * @throws Exception
     */
    protected CloseableHttpResponse executeGetRequest(CloseableHttpClient httpClient, HttpGet httpGet) throws Exception {
        return httpClient.execute(httpGet);
    }

    /**
     *
     * @param httpClient
     * @param httpPost execute HTTP POST method request to a socket
     * @return httpPost connection result
     * @throws Exception
     */
    protected CloseableHttpResponse executePostRequest(CloseableHttpClient httpClient, HttpPost httpPost) throws Exception {
        return httpClient.execute(httpPost);
    }

    /**
     *
     * @param httpResponse retrieve status line to get status code
     * @return HTTP status code
     */
    protected int getStatusCode(CloseableHttpResponse httpResponse) {
        return httpResponse.getStatusLine().getStatusCode();
    }

    /**
     *
     * @param <T>
     * @param jsonResponse
     * @param classOfT
     * parsing string JSON to type of given classOfT 
     * @return Class Object
     * @throws JsonSyntaxException
     */
    protected <T> T json2Object(String jsonResponse, Class<T> classOfT) throws JsonSyntaxException {
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, classOfT);
    }

    protected List<TedataDenominationRepresentation> json2Object(String tedataCategoriesResponseJson, Type type) {
        Gson gson = new Gson();

        return gson.fromJson(tedataCategoriesResponseJson, type);

    }

    protected List<ChangePointsDTO> json2Objectloyality(String loyalityValidateResponseJson, Type type) {
        Gson gson = new Gson();

        return gson.fromJson(loyalityValidateResponseJson, type);

    }

    protected List<CashatCompniesInfo> json2Objectcashat(String cashatCompaniesResponseJson, Type type) {
        Gson gson = new Gson();

        return gson.fromJson(cashatCompaniesResponseJson, type);

    }
/**
 * 
 * @param <T>
 * @param object
 * @param classOfT
 * @return parsing  type of given classOfT to string JSON 
 * @throws JsonSyntaxException 
 */
    protected <T> String object2Json(Object object, Class<T> classOfT) throws JsonSyntaxException {
        Gson gson = new Gson();
        return gson.toJson(object, classOfT);
    }
/**
 * logger initialization
 * @return Logger
 */
    protected Logger initLogger() {
        return MasaryManager.logger;
    }

}
