/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.NetriskaInquiryResponse;
import com.masary.integration.dto.NetriskaPaymentRequest;
import com.masary.integration.dto.NetriskaPaymentResponse;
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
 * @author Mustafa Abdelmonem
 */
public class NetriskaClient {
    public NetriskaInquiryResponse netriskaInquiry(String mobile, String amount, String lang, String token, String ip) throws Exception {

        String netriskaInquiryUrl = SystemSettingsUtil.getInstance().loadProperty("netriska.inquiry.url");
        
        netriskaInquiryUrl = netriskaInquiryUrl.replace("{mobileNumber}", mobile);
        netriskaInquiryUrl = netriskaInquiryUrl.replace("{amount}", amount);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        NetriskaInquiryResponse backValidateResponse = new NetriskaInquiryResponse();
        
        try {

            HttpGet httpGet = new HttpGet(netriskaInquiryUrl);
            httpGet.addHeader("Content-Type", "application/json");
//             if(sendTokenHeader){
//                httpGet.addHeader("token", token);
//            }else{
//                httpGet.addHeader("Authorization", token);
//            }
             
            httpGet.addHeader("Authorization", token);
           //     httpGet.addHeader("token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4NTAxIiwicm9sZXMiOiIgQUxMIFJvbGVzIiwianRpIjoiNjA4NjEzIiwiaWF0IjoxNDk0MjQxOTg2fQ.8PPyOdDgEFF2N3inh0LqeoVUm5jsD3DLCZYUxof88Rs8YPlVP1J6BLnqkVY3N3mlexTCzTI8Fc1e3R49WF5p_Q");
            httpGet.addHeader("deviceType", "Web");
            httpGet.addHeader("ip", ip);
            
            CloseableHttpResponse netriskaInquiryResponseHttp = httpclient.execute(httpGet);
            String netriskInquiryResponseJson = EntityUtils.toString(netriskaInquiryResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("Netriska Service response is : " + netriskInquiryResponseJson);
            
            Gson gson = new Gson();

            if (netriskaInquiryResponseHttp.getStatusLine().getStatusCode() == 200) {
                backValidateResponse = gson.fromJson(netriskInquiryResponseJson, NetriskaInquiryResponse.class);
            } else {
                com.masary.integration.dto.StandardHttpJsonResponseDTO errorResponseDTO = new com.masary.integration.dto.StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(netriskInquiryResponseJson, com.masary.integration.dto.StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to enquire from netriska service " + errorResponseDTO.getMessage() );
                throw new Exception(errorResponseDTO.getMessage());
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling netriska inquiry service on new system: " + ex,ex);
            throw(ex);
        } finally {
            httpclient.close();
        }
        
        return backValidateResponse;
    }

   public NetriskaPaymentResponse netriskaPayment(NetriskaPaymentRequest netriskaRequest, String lang, String token, String ip) throws Exception {

        String netriskaPaymentUrl = SystemSettingsUtil.getInstance().loadProperty("netriska.payment.url");

        CloseableHttpClient httpclient = HttpClients.createDefault();

        NetriskaPaymentResponse netriskaPaymentResponse = new NetriskaPaymentResponse();
        
        try {

            HttpPost httpPost = new HttpPost(netriskaPaymentUrl);
            httpPost.addHeader("Content-Type", "application/json");
            //             if(sendTokenHeader){
//                httpGet.addHeader("token", token);
//            }else{
//                httpGet.addHeader("Authorization", token);
//            }
             
            httpPost.addHeader("Authorization", token);
            //httpPost.addHeader("token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4NTAxIiwicm9sZXMiOiIgQUxMIFJvbGVzIiwianRpIjoiNjA4NjEzIiwiaWF0IjoxNDk0MjQxOTg2fQ.8PPyOdDgEFF2N3inh0LqeoVUm5jsD3DLCZYUxof88Rs8YPlVP1J6BLnqkVY3N3mlexTCzTI8Fc1e3R49WF5p_Q");
            httpPost.addHeader("deviceType", "Web");
            httpPost.addHeader("ip", ip);
            
            Gson gson = new Gson();
            String urlParameters = gson.toJson(netriskaRequest, NetriskaPaymentRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);
            
            CloseableHttpResponse netriskaPayResponseHttp = httpclient.execute(httpPost);

            String netriskaPayResponseJson = EntityUtils.toString(netriskaPayResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("Netriska Payment Service response is : " + netriskaPayResponseJson);

            if (netriskaPayResponseHttp.getStatusLine().getStatusCode() == 200) {
                netriskaPaymentResponse = gson.fromJson(netriskaPayResponseJson, NetriskaPaymentResponse.class);
            } else {
                com.masary.integration.dto.StandardHttpJsonResponseDTO errorResponseDTO = new com.masary.integration.dto.StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(netriskaPayResponseJson, com.masary.integration.dto.StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to pay at netriska service " + errorResponseDTO.getMessage() );
                throw new Exception(errorResponseDTO.getMessage());
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling netriska inquiry service on new system: " + ex,ex);
            throw(ex);
        } finally {
            httpclient.close();
        }
        
        return netriskaPaymentResponse;
    }
}
