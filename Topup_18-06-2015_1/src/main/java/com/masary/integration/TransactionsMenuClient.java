/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.integration.dto.TransactionsMenuResponse;
import com.masary.utils.SystemSettingsUtil;
import java.io.IOException;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Tasneem
 */
public class TransactionsMenuClient {

    public TransactionsMenuResponse viewTransactionsByLedgerID(int transactionsRequestLedger, String token, String ip) throws Exception {

        String transactionsUrl = SystemSettingsUtil.getInstance().loadProperty("Transactions.Ledger.urlByLedgerID") + transactionsRequestLedger;

        CloseableHttpClient httpclient = HttpClients.createDefault();

        TransactionsMenuResponse eTransactionsResponse = new TransactionsMenuResponse();

        try {

            HttpGet httpGet = new HttpGet(transactionsUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");
//            httpGet.addHeader("ip", ip);
            CloseableHttpResponse transactionPayResponseHttp = httpclient.execute(httpGet);
            String eTransactionsResponseJson = EntityUtils.toString(transactionPayResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("Transactions menu response is : " + eTransactionsResponseJson);

            Gson gson = new Gson();
            if (transactionPayResponseHttp.getStatusLine().getStatusCode() == 200) {
                eTransactionsResponse = gson.fromJson(eTransactionsResponseJson, TransactionsMenuResponse.class);

            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(eTransactionsResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to view Transactions menu  " + errorResponseDTO.getMessage());
//                throw new Exception(errorResponseDTO.getMessage());
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling Transactions menu view on new system: " + ex, ex);
            throw (ex);
        } finally {
            httpclient.close();
        }

        return eTransactionsResponse;
    }

    public TransactionsMenuResponse[] viewTransactionsbyPayer(String custId, String from, String to, String token, String ip) throws Exception {

        String transactionsUrl = SystemSettingsUtil.getInstance().loadProperty("Transactions.Ledger.urlByPayer");
//        transactionsUrl = transactionsUrl.replace("{serviceId}", serviceId);
        transactionsUrl = transactionsUrl.replace("{payer}", custId);
        transactionsUrl = transactionsUrl.replace("{from}", from);
        transactionsUrl = transactionsUrl.replace("{to}", to);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        TransactionsMenuResponse eTransactionsResponseList[] = null;

        try {

            HttpGet httpGet = new HttpGet(transactionsUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");
//            httpGet.addHeader("ip", ip);
            CloseableHttpResponse transactionPayResponseHttp = httpclient.execute(httpGet);
            String eTransactionsResponseJson = EntityUtils.toString(transactionPayResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("Transactions menu response is : " + eTransactionsResponseJson);

            Gson gson = new Gson();
            if (transactionPayResponseHttp.getStatusLine().getStatusCode() == 200) {
                eTransactionsResponseList = gson.fromJson(eTransactionsResponseJson, TransactionsMenuResponse[].class);

            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(eTransactionsResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to view Transactions menu  " + errorResponseDTO.getMessage());
//                throw new Exception(errorResponseDTO.getMessage());
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling Transactions menu view on new system: " + ex, ex);
            throw (ex);
        } finally {
            httpclient.close();
        }
        return eTransactionsResponseList;

    }

    public TransactionsMenuResponse[] viewTransactionsbyPayerAndServiceID(String serviceId, String custId, String from, String to, String token, String ip) throws Exception {

        String transactionsUrl = SystemSettingsUtil.getInstance().loadProperty("Transactions.Ledger.urlByServiceId");
        transactionsUrl = transactionsUrl.replace("{serviceId}", serviceId);
        transactionsUrl = transactionsUrl.replace("{payer}", custId);
        transactionsUrl = transactionsUrl.replace("{from}", from);
        transactionsUrl = transactionsUrl.replace("{to}", to);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        TransactionsMenuResponse eTransactionsResponseList[] = null;

        try {

            HttpGet httpGet = new HttpGet(transactionsUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");
//            httpGet.addHeader("ip", ip);
            CloseableHttpResponse transactionPayResponseHttp = httpclient.execute(httpGet);
            String eTransactionsResponseJson = EntityUtils.toString(transactionPayResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("Transactions menu response is : " + eTransactionsResponseJson);

            Gson gson = new Gson();
            if (transactionPayResponseHttp.getStatusLine().getStatusCode() == 200) {
                eTransactionsResponseList = gson.fromJson(eTransactionsResponseJson, TransactionsMenuResponse[].class);

            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(eTransactionsResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to view Transactions menu  " + errorResponseDTO.getMessage());
//                throw new Exception(errorResponseDTO.getMessage());
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling Transactions menu view on new system: " + ex, ex);
            throw (ex);
        } finally {
            httpclient.close();
        }
        return eTransactionsResponseList;

    }
}
