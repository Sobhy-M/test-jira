/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.AuthRequestDTO;
import com.masary.integration.dto.AuthRespDTO;
import com.masary.utils.SystemSettingsUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author hammad
 */
public class AuthServiceClient {
    
    public AuthRespDTO Signin(AuthRequestDTO authRequest, String IP) throws Exception{
        String authSigninUrl = SystemSettingsUtil.getInstance().loadProperty("authentication.signin.url");
        
        CloseableHttpClient httpclient = HttpClients.createDefault();

        AuthRespDTO authResponse = new AuthRespDTO();
        
        try {

            HttpPost httpPost = new HttpPost(authSigninUrl);
            httpPost.addHeader("deviceType", "Web");
            httpPost.addHeader("ip", IP);
            httpPost.addHeader("Content-Type", "application/json");            
            
            Gson gson = new Gson();
            String urlParameters = gson.toJson(authRequest, AuthRequestDTO.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);
            
            CloseableHttpResponse authResponseHttp = httpclient.execute(httpPost);

            String authResponseJson = EntityUtils.toString(authResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("Authentication service response: " + authResponseJson);

            if (authResponseHttp.getStatusLine().getStatusCode() == 200 || authResponseHttp.getStatusLine().getStatusCode() == 202) {
//                MasaryManager.logger.info("code: " + authResponseHttp.getStatusLine().getStatusCode());
                authResponse = gson.fromJson(authResponseJson, AuthRespDTO.class);

//                MasaryManager.logger.info("*****************" + authResponse.getToken());
//                MasaryManager.logger.info("*****************" + authResponse.getIdleTimeInMinutes());
//                MasaryManager.logger.info("*****************" + authResponse.getAccountName());
//                MasaryManager.logger.info("*****************" + authResponse.getAccountId());
//                MasaryManager.logger.info("*****************" + authResponse.getStatus());
            } else if (authResponseHttp.getStatusLine().getStatusCode() == 401){
                MasaryManager.logger.info("Unauthorized login");
                throw new Exception("Unauthorized login, please try again.");
            }else {
//                MasaryManager.logger.info("****************code: " + authResponseHttp.getStatusLine().getStatusCode());
                MasaryManager.logger.info("Failed to get authenticated");
                throw new Exception("Failed to get authenticated, please try again.");
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling authentication service on new system: " + ex);
            throw(ex);
        } finally {
            httpclient.close();
        }
        
        return authResponse;
    }
}
