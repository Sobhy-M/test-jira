/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.integration.dto.YallaPayInquiryResponse;
import com.masary.integration.dto.YallaPayPaymentRequest;
import com.masary.integration.dto.YallaPayPaymentResponse;
import com.masary.utils.SystemSettingsUtil;
import java.io.IOException;
import java.net.ConnectException;
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
public class YallaPayClient {

    public YallaPayInquiryResponse yallaPayValidateInquiry(String trxID, String lang, String token, String ip) throws Exception {

        String yallaPayUrl = SystemSettingsUtil.getInstance().loadProperty("yallapay.inquiry.url");

//        String proxyAuthorizationToken = gooBackRequest.getToken();
        yallaPayUrl = yallaPayUrl.replace("{transactionId}", trxID);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        MasaryManager.logger.info("yallaPay Inquiry Url:- " + yallaPayUrl);
        YallaPayInquiryResponse yallaPayInquiryResponse = new YallaPayInquiryResponse();

        try {
            HttpGet httpGet = new HttpGet(yallaPayUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");
            httpGet.addHeader("ip", ip);
            httpGet.addHeader("GlobalTrxId", "15");
            MasaryManager.logger.info("yallaPayUrl:- " + yallaPayUrl);
            CloseableHttpResponse yallaPayValidateResponseHttp = httpclient.execute(httpGet);

            String yallaPayValidateResponseJson = EntityUtils.toString(yallaPayValidateResponseHttp.getEntity(), "UTF-8");

            MasaryManager.logger.info("yallaPay Service response is : " + yallaPayValidateResponseJson);

            Gson gson = new Gson();

            if (yallaPayValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
                yallaPayInquiryResponse = gson.fromJson(yallaPayValidateResponseJson, YallaPayInquiryResponse.class);
                MasaryManager.logger.info("yallaPay Inquiry Response:- " + yallaPayInquiryResponse);
//                if (Integer.parseInt(yallaPayInquiryResponse.getBalanceStatus()) >=  1) {
//                    throw new IOException(lang.equals("ar") ? CONFIG.yallaPayAlreadyPayedAr : CONFIG.yallaPayAlreadyPayedEn);
//                }
            } else {
                MasaryManager.logger.info("Failed to get commission");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(yallaPayValidateResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6221")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.yallaPayNotFoundNumberAr : CONFIG.yallaPayNotFoundNumberEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("No message available")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.yallaPayConnectionFailedAr : CONFIG.yallaPayConnectionFailedEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("416") && standardHttpJsonResponseDTO.getMessage().equals("6")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.yallaPayOutOfRangeAr : CONFIG.yallaPayOutOfRangeEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6223")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.yallaPayAlreadyPayedAr : CONFIG.yallaPayAlreadyPayedEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("7")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.yallaPayServiceNotAvailableAr : CONFIG.yallaPayServiceNotAvailableEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6224")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.yallaPayConnectionFailedAr : CONFIG.yallaPayConnectionFailedAr);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("500")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.yallaPayConnectionFailedAr : CONFIG.yallaPayConnectionFailedAr);

                }
            }

        } catch (ConnectException e) {
            MasaryManager.logger.error(e, e);
            throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
        } catch (Exception ex) {

            MasaryManager.logger.error(ex, ex);
            throw ex;
        } finally {
            httpclient.close();
        }
        return yallaPayInquiryResponse;
    }

    public YallaPayPaymentResponse yallaPayPayment(YallaPayPaymentRequest trxID, String lang, String token, String ip) throws Exception {

        String yallaPayUrl = SystemSettingsUtil.getInstance().loadProperty("yallapay.payment.url");

        MasaryManager.logger.info("yallaPay Payment Url:- " + yallaPayUrl);

        CloseableHttpClient httpclient = HttpClients.createDefault();

        YallaPayPaymentResponse yallaPayPaymentResponse = new YallaPayPaymentResponse();

        try {
            HttpPost httpPost = new HttpPost(yallaPayUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "Web");
            httpPost.addHeader("ip", ip);
            httpPost.addHeader("GlobalTrxId", "15");
            MasaryManager.logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");
            Gson gson = new Gson();

            String urlParameters = gson.toJson(trxID, YallaPayPaymentRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);

            CloseableHttpResponse yallaPayPaymentResponseHttp = httpclient.execute(httpPost);

            String yallaPayPaymentResponseJson = EntityUtils.toString(yallaPayPaymentResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("yallaPay Service response is : " + yallaPayPaymentResponseJson);

            if (yallaPayPaymentResponseHttp.getStatusLine().getStatusCode() == 200) {
                yallaPayPaymentResponse = gson.fromJson(yallaPayPaymentResponseJson, YallaPayPaymentResponse.class);
                MasaryManager.logger.info("yallaPay Payment Response:- " + yallaPayPaymentResponse);
            } else {
                MasaryManager.logger.error("Failed to pay the sent amount");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(yallaPayPaymentResponseJson, StandardHttpJsonResponseDTO.class);

                if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6222")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.yallaPayPaymentFailureAr : CONFIG.yallaPayPaymentFailureEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6223")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.yallaPayAlreadyPayedAr : CONFIG.yallaPayAlreadyPayedEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6224")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.yallaPayConnectionFailedAr : CONFIG.yallaPayConnectionFailedEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("409") && standardHttpJsonResponseDTO.getMessage().equals("14")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("500")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.yallaPayConnectionFailedAr : CONFIG.yallaPayConnectionFailedAr);

                }
            }

        } catch (ConnectException e) {
            MasaryManager.logger.error(e, e);
            throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
        } catch (Exception ex) {

            MasaryManager.logger.error(ex, ex);
            throw ex;
        } finally {
            httpclient.close();
        }
        return yallaPayPaymentResponse;
    }

}
