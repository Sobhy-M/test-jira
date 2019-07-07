package com.masary.integration;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.masary.common.CONFIG;
import com.masary.controllers.ZaidElKhier.ZaidElKhierProperties;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.integration.dto.ZaidElKhierCategoriesResponse;
import com.masary.integration.dto.ZaidElKhierPaymentResponse;
import com.masary.integration.dto.ZaidElKhierRequest;
import com.masary.utils.SystemSettingsUtil;

/**
 *
 * @author user
 */
public class ZaidElKhierClient {

	private CloseableHttpClient httpclient;
	private HttpGet httpGet;
	private Logger logger;
	
	public ZaidElKhierClient(CloseableHttpClient httpclient, HttpGet httpGet, Logger logger)
	{
		this.httpclient = httpclient;
		this.httpGet = httpGet;
		this.logger = logger;
	}

	public ZaidElKhierCategoriesResponse[] getCategories(String lang, String token) throws Exception {

        String ZaidElKhierCategoriesUrl = loadUrlProperty("ZaidElKhier.categories.url");

        logger.info("Zaid El Khier Categories Url:- " + ZaidElKhierCategoriesUrl);

        ZaidElKhierCategoriesResponse[] zaidElKhierCategoriesResponse;

        try {
            httpGet.setURI(new URI(ZaidElKhierCategoriesUrl));            
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");

            CloseableHttpResponse zaidElKhierCategoriesValidateResponseHttp = httpclient.execute(httpGet);

            String zaidElKhierCategoriesResponseJson = EntityUtils.toString(zaidElKhierCategoriesValidateResponseHttp.getEntity(), "UTF-8");

            logger.info(zaidElKhierCategoriesResponseJson);

            Gson gson = new Gson();

            if (zaidElKhierCategoriesValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
                zaidElKhierCategoriesResponse = new ZaidElKhierCategoriesResponse[20];
                zaidElKhierCategoriesResponse = gson.fromJson(zaidElKhierCategoriesResponseJson, ZaidElKhierCategoriesResponse[].class);
                return zaidElKhierCategoriesResponse;
            } else {
                if (zaidElKhierCategoriesValidateResponseHttp.getStatusLine().getStatusCode() == 401) {
                    throw new IOException(lang.equals("ar") ? "غير مسموح لك باجراء العملية" : "Unauthorized to perform a transaction");
                }
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(zaidElKhierCategoriesResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getMessage().equals("5")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("12")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("15")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("416")) {
                    throw new IOException(lang.equals("ar") ? ZaidElKhierProperties.Error_ZaidElKhier_416 : ZaidElKhierProperties.Error_ZaidElKhier_416);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("5981")) {
                    throw new IOException(lang.equals("ar") ? ZaidElKhierProperties.Error_ZaidElKhier_5981 : ZaidElKhierProperties.Error_ZaidElKhier_5981);
                } else {
                    throw new IOException(lang.equals("ar") ? ZaidElKhierProperties.Error_ZaidElKhier_DuringTransaction : ZaidElKhierProperties.Error_ZaidElKhier_DuringTransaction);
                }
            }

        } catch (ConnectException ex) {
            logger.error("Error during calling zaidElKhier service on new system: " + ex, ex);
            throw new ConnectException(lang.equals("ar") ? ZaidElKhierProperties.Error_ZaidElKhier_Connection : ZaidElKhierProperties.Error_ZaidElKhier_Connection);
        } catch (Exception e) {
            logger.error("Error during calling zaidElKhier service on new system: " + e, e);
        } finally {
            httpclient.close();
        }

        return null;

    }

    public ZaidElKhierPaymentResponse zaidElKhierPayment(ZaidElKhierRequest zaidElKhierRequest, String lang, String token) throws Exception {
        // complete the code

        String zaidElKhierUrl = SystemSettingsUtil.getInstance().loadProperty("ZaidElKhier.payment.url");
        logger.info("zaidElKhier Payment Url:- " + zaidElKhierUrl + zaidElKhierRequest);

        CloseableHttpClient httpclient = HttpClients.createDefault();

        ZaidElKhierPaymentResponse zaidElKhierPaymentResponse;

        try {
            HttpPost httpPost = new HttpPost(zaidElKhierUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "Web");
            logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

            Gson gson = new Gson();
            String urlParameters = gson.toJson(zaidElKhierRequest, ZaidElKhierRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);

            CloseableHttpResponse zaidElKhierPaymentResponseHttp = httpclient.execute(httpPost);

            String zaidElKhierPaymentResponseJson = EntityUtils.toString(zaidElKhierPaymentResponseHttp.getEntity(), "UTF-8");
            logger.info("zaidElKhier Service response is : " + zaidElKhierPaymentResponseJson);

            if (zaidElKhierPaymentResponseHttp.getStatusLine().getStatusCode() == 200) {
                zaidElKhierPaymentResponse = new ZaidElKhierPaymentResponse();
                zaidElKhierPaymentResponse = gson.fromJson(zaidElKhierPaymentResponseJson, ZaidElKhierPaymentResponse.class);
                logger.info("zaidElKhier Payment Response:- " + zaidElKhierPaymentResponse);
                return zaidElKhierPaymentResponse;
            } else {
            	logger.error("Failed to pay the sent amount");
                if (zaidElKhierPaymentResponseHttp.getStatusLine().getStatusCode() == 401) {
                    throw new IOException(lang.equals("ar") ? "غير مسموح لك باجراء العملية" : "Unauthorized to perform a transaction");
                }
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(zaidElKhierPaymentResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getMessage().equals("5")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("12")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("15")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("416")) {
                    throw new IOException(lang.equals("ar") ? ZaidElKhierProperties.Error_ZaidElKhier_416 : ZaidElKhierProperties.Error_ZaidElKhier_416);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("5981")) {
                    throw new IOException(lang.equals("ar") ? ZaidElKhierProperties.Error_ZaidElKhier_5981 : ZaidElKhierProperties.Error_ZaidElKhier_5981);
                } else {
                    throw new IOException(lang.equals("ar") ? ZaidElKhierProperties.Error_ZaidElKhier_DuringTransaction : ZaidElKhierProperties.Error_ZaidElKhier_DuringTransaction);
                }
            }

        } catch (ConnectException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ConnectException(lang.equals("ar") ? ZaidElKhierProperties.Error_ZaidElKhier_Connection : ZaidElKhierProperties.Error_ZaidElKhier_Connection);
        } catch (Exception ex) {
        	logger.error("Error during calling zaidElKhier service on new system: " + ex, ex);
            throw (ex);
        } finally {
            httpclient.close();
        }
    }

	protected String loadUrlProperty(String propertyName)
	{
		return SystemSettingsUtil.getInstance().loadProperty(propertyName);
	}
	
}