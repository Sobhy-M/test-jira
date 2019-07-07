/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.database.manager.MasaryManager;

import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.integration.dto.TETopupRequest;
import com.masary.integration.dto.TETopupResponse;
import com.masary.utils.SystemSettingsUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author AYA
 */
public class TelecomEgyptTopupClient {
    
      public TETopupResponse TETopup(TETopupRequest teTopupRequest, String lang, String uuid ,String token, String ip) throws Exception {

        String teTopupUrl = SystemSettingsUtil.getInstance().loadProperty("TopupTE.Payment.url");

        CloseableHttpClient httpclient = HttpClients.createDefault();

        TETopupResponse eTopupResponse = new TETopupResponse();
        
        try {

            HttpPost httpPost = new HttpPost(teTopupUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
//            httpPost.addHeader("token", token);
            httpPost.addHeader("deviceType", "Web");
            httpPost.addHeader("ip", ip);
            httpPost.addHeader ("extTrxId", uuid);
            
            Gson gson = new Gson();
            String urlParameters = gson.toJson(teTopupRequest, TETopupRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);
            
            CloseableHttpResponse hataxiPayResponseHttp = httpclient.execute(httpPost);

            String eTopupResponseJson = EntityUtils.toString(hataxiPayResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("TE Topup  Service response is : " + eTopupResponseJson);
            MasaryManager.logger.info("TE Data Service response uuid is : " + uuid);

            if (hataxiPayResponseHttp.getStatusLine().getStatusCode() == 200) {
                eTopupResponse = gson.fromJson(eTopupResponseJson, TETopupResponse.class);
            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(eTopupResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to Make TE Topup service " + errorResponseDTO.getMessage() );
                throw new Exception(errorResponseDTO.getMessage());
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling TE Topup service service on new system: " + ex);
            throw(ex);
        } finally {
            httpclient.close();
        }
        
        return eTopupResponse;
    }

}
