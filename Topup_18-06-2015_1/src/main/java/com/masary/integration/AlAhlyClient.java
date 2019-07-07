/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.AlAhlyPaymentRequest;
import com.masary.integration.dto.AlAhlyResponse;
import com.masary.integration.dto.OneCardPaymentRequest;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;
import java.io.IOException;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author AYA
 */
public class AlAhlyClient {

    public AlAhlyResponse alAhlyValidateInquiry(String customerPoNumber, String lang, String token) throws Exception {

        String alAhlyUrl = SystemSettingsUtil.getInstance().loadProperty("AlAhly.inquiry.url");

//        String proxyAuthorizationToken = gooBackRequest.getToken();
        alAhlyUrl = alAhlyUrl.replace("{customerPoNumber}", customerPoNumber);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        MasaryManager.logger.info("alAhly Inquiry Url:- " + alAhlyUrl);
        AlAhlyResponse alAhlyResponse = new AlAhlyResponse();

        try {
            HttpGet httpGet = new HttpGet(alAhlyUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");
//            httpGet.addHeader("ip", ip);
            MasaryManager.logger.info("alAhlyUrl:- " + alAhlyUrl);
            CloseableHttpResponse alAhlyValidateResponseHttp = httpclient.execute(httpGet);

            String alAhlyValidateResponseJson = EntityUtils.toString(alAhlyValidateResponseHttp.getEntity(), "UTF-8");

            MasaryManager.logger.info("AlAhly Service response is : " + alAhlyValidateResponseJson);

            Gson gson = new Gson();

            if (alAhlyValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
                alAhlyResponse = gson.fromJson(alAhlyValidateResponseJson, AlAhlyResponse.class);
            } else {
                MasaryManager.logger.info("Failed to get commission");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(alAhlyValidateResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("5")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("12")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("15")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("400") && standardHttpJsonResponseDTO.getMessage().equals("200417")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("404") && standardHttpJsonResponseDTO.getMessage().equals("6122")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.EtisalNotFoundNumber : CONFIG.EtisalNotFoundNumber);
// throw new Exception(standardHttpJsonResponseDTO.getException()):"",
                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6272")) {

                    throw new IOException("Customer profile not found. Please check customer profile and try again.");
// throw new Exception(standardHttpJsonResponseDTO.getException()):"",
                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6271")) {

                    throw new IOException("Installment already paid.");
// throw new Exception(standardHttpJsonResponseDTO.getException()):"",
                }

            }

        } catch (IOException ex) {

            MasaryManager.logger.error("Error during calling A service on new system: " + ex, ex);
            throw (ex);
        } catch (ParseException ex) {
            MasaryManager.logger.error("Error during calling AlAhly service on new system: " + ex, ex);
            throw (ex);
        } catch (JsonSyntaxException ex) {
            MasaryManager.logger.error("Error during calling AlAhly service on new system: " + ex, ex);
            throw (ex);
        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling AlAhly service on new system: " + ex, ex);
            throw (ex);
        } finally {
            httpclient.close();
        }
        return alAhlyResponse;
    }

    public AlAhlyResponse alAhlyPayment(AlAhlyPaymentRequest customerPoNumber, String lang, String token) throws Exception {

        String alAhlyUrl = SystemSettingsUtil.getInstance().loadProperty("AlAhly.payment.url");
        MasaryManager.logger.info("alAhly Payment Url:- " + alAhlyUrl + customerPoNumber);

        CloseableHttpClient httpclient = HttpClients.createDefault();

        AlAhlyResponse alAhlyPaymentResponse = new AlAhlyResponse();

        try {
            HttpPost httpPost = new HttpPost(alAhlyUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "Web");
//            httpPost.addHeader("ip", ip);
            MasaryManager.logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

            Gson gson = new Gson();
            String urlParameters = gson.toJson(customerPoNumber, AlAhlyPaymentRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);

            CloseableHttpResponse alAhlyPaymentResponseHttp = httpclient.execute(httpPost);

            String alAhlyPaymentResponseJson = EntityUtils.toString(alAhlyPaymentResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("AlAhly Service response is : " + alAhlyPaymentResponseJson);

            if (alAhlyPaymentResponseHttp.getStatusLine().getStatusCode() == 200) {
                alAhlyPaymentResponse = gson.fromJson(alAhlyPaymentResponseJson, AlAhlyResponse.class);
                MasaryManager.logger.info("AlAhly Payment Response:- " + alAhlyPaymentResponse);
            } else {
                MasaryManager.logger.error("Failed to pay the sent amount");
//                throw new Exception(lang.equals("ar") ? "مشكله اثناء دفع القيمه المرسله" : "Failed to pay the sent amount");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(alAhlyPaymentResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("5")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("12")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("15")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("400") && standardHttpJsonResponseDTO.getMessage().equals("200417")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("404") && standardHttpJsonResponseDTO.getMessage().equals("6122")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.EtisalNotFoundNumber : CONFIG.EtisalNotFoundNumber);
                } else {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                }
            }

        } catch (IOException ex) {

            MasaryManager.logger.error("Error during calling Alahly Payment service on new system: " + ex);
            throw (ex);
        } catch (ParseException ex) {
            MasaryManager.logger.error("Error during calling Alahly Payment service on new system: " + ex);
            throw (ex);
        } catch (JsonSyntaxException ex) {
            MasaryManager.logger.error("Error during calling Alahly Payment service on new system: " + ex);
            throw (ex);
        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling Alahly Payment service on new system: " + ex);
            throw (ex);
        } finally {
            httpclient.close();
        }
        return alAhlyPaymentResponse;
    }
}
