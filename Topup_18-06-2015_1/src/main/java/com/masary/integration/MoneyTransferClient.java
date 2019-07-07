/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.MoneyTransferRequest;
import com.masary.integration.dto.MoneyTransferTransactionResp;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;
import java.io.IOException;
import org.apache.http.ParseException;
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
public class MoneyTransferClient {
     public MoneyTransferTransactionResp doMoneyTransfer(MoneyTransferRequest moneyTransferRequest) throws Exception {

        String moneyTransferUrl = SystemSettingsUtil.getInstance().loadProperty("moneyTransfer.deposite.url");

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(moneyTransferUrl);

        StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();

        Gson gson = new Gson();

        MoneyTransferTransactionResp moneyTransferresponse = new MoneyTransferTransactionResp();

        String urlParameters = gson.toJson(moneyTransferRequest, MoneyTransferRequest.class);
        
        StringEntity params = new StringEntity(urlParameters);
        httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("token", moneyTransferRequest.getToken());
        httpPost.addHeader("ip", moneyTransferRequest.getIp());
        httpPost.addHeader("deviceType", "Web");
        httpPost.setEntity(params);

        try {
            CloseableHttpResponse response = httpclient.execute(httpPost);

            String json = EntityUtils.toString(response.getEntity(), "UTF-8");

            MasaryManager.logger.info(response.getStatusLine());
            MasaryManager.logger.info("JSON: " + json);

            if (response.getStatusLine().toString().contains("200")) {
                moneyTransferresponse = gson.fromJson(json, MoneyTransferTransactionResp.class);
                MasaryManager.logger.info("Success transaction vodafone integration  " + moneyTransferresponse);

            } else if (json.contains("status")) {
                standardHttpJsonResponseDTO = gson.fromJson(json, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("409") && standardHttpJsonResponseDTO.getMessage().equals("14")) {
//                    throw new Exception("رصيدك غير كافى لاجراء العملية");
//                    throw new Exception(lang.equals("ar") ? CONFIG.NOTENOUGHBALANCEar : CONFIG.NOTENOUGHBALANCEen);
//                    throw new Exception(" Account balance is not suffcient");
            
            return moneyTransferresponse;

        } }}// end try
        catch (IOException e) {
            MasaryManager.logger.error("Exception" + e.getMessage());
            MasaryManager.logger.error(e);
            throw (e);
        } catch (ParseException e) {
            MasaryManager.logger.error("Exception" + e.getMessage());
            MasaryManager.logger.error(e);
            throw (e);
         } catch (JsonSyntaxException e) {
             MasaryManager.logger.error("Exception" + e.getMessage());
             MasaryManager.logger.error(e);
             throw (e);
         } finally {
            httpclient.close();
        }
    return moneyTransferresponse;
    
}}
