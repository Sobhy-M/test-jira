/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.GooBackRequest;
import com.masary.integration.dto.GooBackValidateResponse;
import com.masary.integration.dto.LedgerBalanceRespDto;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author hammad
 */
public class LedgerClient {
    
    public LedgerBalanceRespDto getAccountBalance(String accountId, String lang, String token, String ip) throws Exception {

        String ledgerBalanceUrl = SystemSettingsUtil.getInstance().loadProperty("ledger.balance.url");
        
        ledgerBalanceUrl = ledgerBalanceUrl.replace("{accountId}", accountId);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        LedgerBalanceRespDto ledgerBalanceResponse = new LedgerBalanceRespDto();
        
        try {

            HttpGet httpGet = new HttpGet(ledgerBalanceUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("token", token);
            httpGet.addHeader("deviceType", "Web");
            httpGet.addHeader("ip", ip);
            CloseableHttpResponse ledgerBalanceResponseHttp = httpclient.execute(httpGet);

            String ledgerBalanceResponseJson = EntityUtils.toString(ledgerBalanceResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("Ledger Service response is : " + ledgerBalanceResponseJson);
            
            Gson gson = new Gson();

            if (ledgerBalanceResponseHttp.getStatusLine().getStatusCode() == 200) {
                ledgerBalanceResponse = gson.fromJson(ledgerBalanceResponseJson, LedgerBalanceRespDto.class);
            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(ledgerBalanceResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to get balance from ledger " + errorResponseDTO.getMessage() );
                throw new Exception(errorResponseDTO.getMessage());
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling ledger service on new system: " + ex, ex);
            throw(ex);
        } finally {
            httpclient.close();
        }
        
        return ledgerBalanceResponse;
    }
}
