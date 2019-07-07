/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.HataxiInquiryResponse;
import com.masary.integration.dto.HataxiPaymentRequest;
import com.masary.integration.dto.HataxiPaymentResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author hammad
 */
public class HataxiClient {
    
    public HataxiInquiryResponse hataxiInquiry(String mobile, String amount, String lang, String token, String ip) throws Exception {

        String hataxiInquiryUrl = SystemSettingsUtil.getInstance().loadProperty("hataxi.inquiry.url");
        
        hataxiInquiryUrl = hataxiInquiryUrl.replace("{mobileNumber}", mobile);
        hataxiInquiryUrl = hataxiInquiryUrl.replace("{amount}", amount);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HataxiInquiryResponse backValidateResponse = new HataxiInquiryResponse();
        
        try {

            HttpGet httpGet = new HttpGet(hataxiInquiryUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
//            httpGet.addHeader("token", token);
            httpGet.addHeader("deviceType", "Web");
            httpGet.addHeader("ip", ip);
            
            CloseableHttpResponse hataxiInquiryResponseHttp = httpclient.execute(httpGet);
            String hataxiInquiryResponseJson = EntityUtils.toString(hataxiInquiryResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("Hataxi Service response is : " + hataxiInquiryResponseJson);
            
            Gson gson = new Gson();

            if (hataxiInquiryResponseHttp.getStatusLine().getStatusCode() == 200) {
                backValidateResponse = gson.fromJson(hataxiInquiryResponseJson, HataxiInquiryResponse.class);
            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(hataxiInquiryResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to enquire from hataxi service " + errorResponseDTO.getMessage() );
                throw new Exception(errorResponseDTO.getMessage());
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling Hataxi inquiry service on new system: " + ex);
            throw(ex);
        } finally {
            httpclient.close();
        }
        
        return backValidateResponse;
    }

    public HataxiPaymentResponse hataxiPayment(HataxiPaymentRequest hataxiRequest, String lang, String token, String ip) throws Exception {

        String hataxiPaymentUrl = SystemSettingsUtil.getInstance().loadProperty("hataxi.payment.url");

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HataxiPaymentResponse hataxiPaymentResponse = new HataxiPaymentResponse();
        
        try {

            HttpPost httpPost = new HttpPost(hataxiPaymentUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
//            httpPost.addHeader("token", token);
            httpPost.addHeader("deviceType", "Web");
            httpPost.addHeader("ip", ip);
            
            Gson gson = new Gson();
            String urlParameters = gson.toJson(hataxiRequest, HataxiPaymentRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);
            
            CloseableHttpResponse hataxiPayResponseHttp = httpclient.execute(httpPost);

            String hataxiPayResponseJson = EntityUtils.toString(hataxiPayResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("Hataxi Payment Service response is : " + hataxiPayResponseJson);

            if (hataxiPayResponseHttp.getStatusLine().getStatusCode() == 200) {
                hataxiPaymentResponse = gson.fromJson(hataxiPayResponseJson, HataxiPaymentResponse.class);
            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(hataxiPayResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to pay at hataxi service " + errorResponseDTO.getMessage() );
                throw new Exception(errorResponseDTO.getMessage());
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling Hataxi inquiry service on new system: " + ex);
            throw(ex);
        } finally {
            httpclient.close();
        }
        
        return hataxiPaymentResponse;
    }

}
