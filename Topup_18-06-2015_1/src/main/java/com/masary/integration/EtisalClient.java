/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.EtisalRequest;
import com.masary.integration.dto.EtisalInquiryResponse;
import com.masary.integration.dto.EtisalPaymentResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;
import java.io.IOException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Ahmed Khaled
 */
public class EtisalClient {

    public EtisalInquiryResponse etisalValidateInquiry(EtisalRequest etisalRequest, String lang, String token) throws Exception {

        String etisalUrl = SystemSettingsUtil.getInstance().loadProperty("etisal.inquiry.url");

//        String proxyAuthorizationToken = gooBackRequest.getToken();
        etisalUrl = etisalUrl.replace("{mobileNumber}", etisalRequest.getPhoneNumber());
        CloseableHttpClient httpclient = HttpClients.createDefault();

        MasaryManager.logger.info("etisal Inquiry Url:- " + etisalUrl);
        EtisalInquiryResponse etisalResponse = new EtisalInquiryResponse();

        try {
            HttpGet httpGet = new HttpGet(etisalUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");
//            httpGet.addHeader("ip", ip);
            MasaryManager.logger.info("etisalUrl:- " + etisalUrl);
            CloseableHttpResponse etisalValidateResponseHttp = httpclient.execute(httpGet);

            String etisalValidateResponseJson = EntityUtils.toString(etisalValidateResponseHttp.getEntity(), "UTF-8");

            MasaryManager.logger.info("Etisal Service response is : " + etisalValidateResponseJson);

            Gson gson = new Gson();

            if (etisalValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
                etisalResponse = gson.fromJson(etisalValidateResponseJson, EtisalInquiryResponse.class);
            } else {
                MasaryManager.logger.info("Failed to get commission");
                StandardHttpJsonResponseDTO  standardHttpJsonResponseDTO= new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(etisalValidateResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("5")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("12")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("15")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("400") && standardHttpJsonResponseDTO.getMessage().equals("200417")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("404") && standardHttpJsonResponseDTO.getMessage().equals("6122")){

                    throw new IOException(lang.equals("ar") ? CONFIG.EtisalNotFoundNumber : CONFIG.EtisalNotFoundNumber);
// throw new Exception(standardHttpJsonResponseDTO.getException()):"",
                }
               
            }

        } catch (Exception ex) {

            MasaryManager.logger.error("Error during calling Etisal service on new system: " + ex, ex);
            throw (ex);
        } finally {
            httpclient.close();
        }
        return etisalResponse;
    }

    public EtisalPaymentResponse etisalPayment(EtisalRequest etisalRequest, String lang, String token) throws Exception {

        String etisalUrl = SystemSettingsUtil.getInstance().loadProperty("etisal.pay.url");

        MasaryManager.logger.info("etisal Payment Url:- " + etisalUrl);
         
        CloseableHttpClient httpclient = HttpClients.createDefault();

        EtisalPaymentResponse etisalPaymentResponse = new EtisalPaymentResponse();

        try {
            HttpPost httpPost = new HttpPost(etisalUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "Web");
//            httpPost.addHeader("ip", ip);
            MasaryManager.logger.info("Content-Type:- " + "application/json" + " Authorization:- " +token +" deviceType:- " +"Web");
            Gson gson = new Gson();

            String urlParameters = gson.toJson(etisalRequest, EtisalRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);

            CloseableHttpResponse etisalPaymentResponseHttp = httpclient.execute(httpPost);

            String etisalPaymentResponseJson = EntityUtils.toString(etisalPaymentResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("Etisal Service response is : " + etisalPaymentResponseJson);

            if (etisalPaymentResponseHttp.getStatusLine().getStatusCode() == 200) {
                etisalPaymentResponse = gson.fromJson(etisalPaymentResponseJson, EtisalPaymentResponse.class);
                MasaryManager.logger.info("Etisal Payment Response:- " + etisalPaymentResponse);
            } else {
                MasaryManager.logger.error("Failed to pay the sent amount");
//                throw new Exception(lang.equals("ar") ? "مشكله اثناء دفع القيمه المرسله" : "Failed to pay the sent amount");
                StandardHttpJsonResponseDTO  standardHttpJsonResponseDTO= new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(etisalPaymentResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("5")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("12")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("15")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("400") && standardHttpJsonResponseDTO.getMessage().equals("200417")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("404") && standardHttpJsonResponseDTO.getMessage().equals("6122")){

                    throw new IOException(lang.equals("ar") ? CONFIG.EtisalNotFoundNumber : CONFIG.EtisalNotFoundNumber);
// throw new Exception(standardHttpJsonResponseDTO.getException()):"",
                }
            }

        } catch (Exception ex) {

            MasaryManager.logger.error("Error during calling Etisal service on new system: " + ex);
            throw (ex);
        } finally {
            httpclient.close();
        }
        return etisalPaymentResponse;
    }

}