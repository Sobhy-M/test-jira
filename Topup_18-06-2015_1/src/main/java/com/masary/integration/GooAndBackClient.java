/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.CommonTopupRequestDTO;
import com.masary.integration.dto.GooBackPayResponse;
import com.masary.integration.dto.GooBackRequest;
import com.masary.integration.dto.GooBackValidateResponse;
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
public class GooAndBackClient {

    public GooBackValidateResponse goAndBackTxnValidation(GooBackRequest gooBackRequest, String lang, String token, String ip) throws Exception {

        String gooBackUrl = SystemSettingsUtil.getInstance().loadProperty("gooback.validate.url");

//        String proxyAuthorizationToken = gooBackRequest.getToken();
        gooBackUrl = gooBackUrl.replace("{mobile}", gooBackRequest.getMsisdn());
        gooBackUrl = gooBackUrl.replace("{amount}", String.valueOf(gooBackRequest.getAmount()));
        CloseableHttpClient httpclient = HttpClients.createDefault();

        GooBackValidateResponse backValidateResponse = new GooBackValidateResponse();

        try {

            HttpGet httpGet = new HttpGet(gooBackUrl);
            httpGet.addHeader("Content-Type", "application/json");
//            httpGet.addHeader("token", token);
            httpGet.addHeader("Authorization", token);

            httpGet.addHeader("deviceType", "Web");
            httpGet.addHeader("ip", ip);
            CloseableHttpResponse gooBackValidateResponseHttp = httpclient.execute(httpGet);

            String gooBackValidateResponseJson = EntityUtils.toString(gooBackValidateResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("GooAndBack Service response is : " + gooBackValidateResponseJson);

            Gson gson = new Gson();

            if (gooBackValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
                backValidateResponse = gson.fromJson(gooBackValidateResponseJson, GooBackValidateResponse.class);
            } else {
                MasaryManager.logger.info("Failed to get commission");
                throw new Exception(lang.equals("ar") ? "هناك مشكله في خدمه جو اند باك اثناء احتساب العموله" : "Failed to connect to Goo and Back to get commission");
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling GooBack service on new system: " + ex, ex);
            throw (ex);
        } finally {
            httpclient.close();
        }

        return backValidateResponse;
    }

    public GooBackPayResponse goAndBackTxnPayment(GooBackRequest gooBackRequest, String lang, String token, String ip) throws Exception {

        String gooBackUrl = SystemSettingsUtil.getInstance().loadProperty("gooback.pay.url");

//        String proxyAuthorizationToken = gooBackRequest.getToken();
//        gooBackUrl = gooBackUrl.replace("{mobile}", gooBackRequest.getMsisdn());
//        gooBackUrl = gooBackUrl.replace("{amount}", String.valueOf(gooBackRequest.getAmount()));
        CloseableHttpClient httpclient = HttpClients.createDefault();

        GooBackPayResponse backPayResponse = new GooBackPayResponse();

        try {

            HttpPost httpPost = new HttpPost(gooBackUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);

//            httpPost.addHeader("token", token);
            httpPost.addHeader("deviceType", "Web");
            httpPost.addHeader("ip", ip);

            Gson gson = new Gson();
            String urlParameters = gson.toJson(gooBackRequest, GooBackRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);

            CloseableHttpResponse gooBackPayResponseHttp = httpclient.execute(httpPost);

            String gooBackPayResponseJson = EntityUtils.toString(gooBackPayResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("GooAndBack Service response is : " + gooBackPayResponseJson);

            if (gooBackPayResponseHttp.getStatusLine().getStatusCode() == 200) {
                backPayResponse = gson.fromJson(gooBackPayResponseJson, GooBackPayResponse.class);
            } else {
                MasaryManager.logger.info("Failed to pay the sent amount");
                throw new Exception(lang.equals("ar") ? "مشكله اثناء دفع القيمه المرسله" : "Failed to pay the sent amount");
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling GooBack service on new system: " + ex);
            throw (ex);
        } finally {
            httpclient.close();
        }

        return backPayResponse;
    }
}
