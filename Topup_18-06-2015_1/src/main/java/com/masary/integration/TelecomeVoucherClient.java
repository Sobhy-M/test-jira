/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.integration.dto.TelecomeDenominationsDTO;
import com.masary.integration.dto.TelecomeVoucherPaymentRequest;
import com.masary.integration.dto.TelecomeVoucherPaymentResponse;
import com.masary.utils.SystemSettingsUtil;
import java.io.IOException;
import java.net.ConnectException;
import org.apache.http.ParseException;
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
public class TelecomeVoucherClient {

    public static final String StaticToken = "eyJpcCI6IjQxLjI5LjEwMC4yNTgiLCJkZXZpY2VUeXBlIjoiV0VCIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiI3MDkwIiwicm9sZXMiOiJSRVAsTUVSQ0hBTlQiLCJqdGkiOiIwOThjZGU3YS03MzY1LTRmZTYtOTcwYi01NTBlNTY5YzJiODUiLCJpYXQiOjE0OTYzMTQ2NDgsImV4cCI6MTQ5NjMxNTI0OH0.DizrLc8xO_JoFF-daNbsjz8hjbEsP6jbMISBMbf2IKWkqh5LZbNkilPQZSc5GNdEV5L-QsypB6wvxDDfVoXhsw";

    public TelecomeDenominationsDTO[] telecomeGetDemominations(String lang,String uuid, String token, String ip) throws Exception {
        String telecomeDenominationsUrl = SystemSettingsUtil.getInstance().loadProperty("te.denominations.url");

//        String proxyAuthorizationToken = gooBackRequest.getToken();
        CloseableHttpClient httpclient = HttpClients.createDefault();

        MasaryManager.logger.info("telecome Denominations Url Inquiry Url:- " + telecomeDenominationsUrl);
        TelecomeDenominationsDTO[] telecomeDenominationsDTO = new TelecomeDenominationsDTO[20];

        try {

            HttpGet httpGet = new HttpGet(telecomeDenominationsUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("token", StaticToken);
            httpGet.addHeader("deviceType", "WEB");
            httpGet.addHeader("ip", ip);
            httpGet.addHeader("GlobalTrxId", "1");
            httpGet.addHeader("extTrxId", uuid);
            MasaryManager.logger.info("telecomeDenominationsUrl:- " + telecomeDenominationsUrl);
            CloseableHttpResponse telecomeDenominationsValidateResponseHttp = httpclient.execute(httpGet);

            String telecomeDenominationsValidateResponseJson = EntityUtils.toString(telecomeDenominationsValidateResponseHttp.getEntity(), "UTF-8");

            MasaryManager.logger.info("telecomeDenominations Service response is : " + telecomeDenominationsValidateResponseJson);
            MasaryManager.logger.info("telecomeDenominations Service response UUID is : " + uuid);
            Gson gson = new Gson();

            if (telecomeDenominationsValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
                telecomeDenominationsDTO = gson.fromJson(telecomeDenominationsValidateResponseJson, TelecomeDenominationsDTO[].class);
            } else {

                MasaryManager.logger.info("Failed to get commission");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(telecomeDenominationsValidateResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6511")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptProviderIntegrationAr : CONFIG.telecomeEgyptProviderIntegrationEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("6512")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptDenominationNotFoundAr : CONFIG.telecomeEgyptDenominationNotFoundEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6513")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptProviderAccountIsNotExistAr : CONFIG.telecomeEgyptProviderAccountIsNotExistAr);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6514")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptProviderBalanceIsNotEnoughAr : CONFIG.telecomeEgyptProviderBalanceIsNotEnoughEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6515")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptProviderDuplicateRequestAr : CONFIG.telecomeEgyptProviderDuplicateRequestEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6516")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptProviderGeneralErrorDuringRequestAr : CONFIG.telecomeEgyptProviderGeneralErrorDuringRequestEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6517")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptInvalidAccountOrPasswordAr : CONFIG.telecomeEgyptInvalidAccountOrPasswordEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6518")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptInvalidVoucherAmountAr : CONFIG.telecomeEgyptInvalidVoucherAmountEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6519")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptStatusCodeNotInTheMapStatusCodeAr : CONFIG.telecomeEgyptStatusCodeNotInTheMapStatusCodeEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("500")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptServiceNotAvailableAr : CONFIG.telecomeEgyptServiceNotAvailableEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("65110")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptProviderIntegrationAr : CONFIG.telecomeEgyptProviderIntegrationEn);

                }
            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error(ex, ex);
            throw new ConnectException(lang.equals("ar") ? CONFIG.telecomeEgyptServiceNotAvailableAr : CONFIG.telecomeEgyptServiceNotAvailableEn);
        } catch (IOException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        } catch (ParseException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        } catch (JsonSyntaxException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        }

        return telecomeDenominationsDTO;
    }

    public TelecomeVoucherPaymentResponse telecomeVoucherPayment(TelecomeVoucherPaymentRequest paymentRequest, String lang, String uuid,String token, String ip) throws Exception {

        String telecomeVoucherUrl = SystemSettingsUtil.getInstance().loadProperty("te.payment.url");

        MasaryManager.logger.info("telecome voucher Payment Url:- " + telecomeVoucherUrl);

        CloseableHttpClient httpclient = HttpClients.createDefault();

        TelecomeVoucherPaymentResponse telecomeVoucherPaymentResponse = new TelecomeVoucherPaymentResponse();

        try {
            HttpPost httpPost = new HttpPost(telecomeVoucherUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "Web");
            httpPost.addHeader("ip", ip);
            httpPost.addHeader("extTrxId", uuid);
            MasaryManager.logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");
            Gson gson = new Gson();

            String urlParameters = gson.toJson(paymentRequest, TelecomeVoucherPaymentRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);

            CloseableHttpResponse telecomeVoucherlPaymentResponseHttp = httpclient.execute(httpPost);

            String telecomeVoucherPaymentResponseJson = EntityUtils.toString(telecomeVoucherlPaymentResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("telecome Voucher JSON response is : " + telecomeVoucherPaymentResponseJson);
            
            if (telecomeVoucherlPaymentResponseHttp.getStatusLine().getStatusCode() == 200) {
                telecomeVoucherPaymentResponse = gson.fromJson(telecomeVoucherPaymentResponseJson, TelecomeVoucherPaymentResponse.class);
                MasaryManager.logger.info("telecome Voucher Payment Response:- " + telecomeVoucherPaymentResponse);
            } else {
                

                MasaryManager.logger.info("Failed to get commission");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(telecomeVoucherPaymentResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6511")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptProviderIntegrationAr : CONFIG.telecomeEgyptProviderIntegrationEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("6512")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptDenominationNotFoundAr : CONFIG.telecomeEgyptDenominationNotFoundEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6513")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptProviderAccountIsNotExistAr : CONFIG.telecomeEgyptProviderAccountIsNotExistAr);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6514")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptProviderBalanceIsNotEnoughAr : CONFIG.telecomeEgyptProviderBalanceIsNotEnoughEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6515")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptProviderDuplicateRequestAr : CONFIG.telecomeEgyptProviderDuplicateRequestEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6516")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptProviderGeneralErrorDuringRequestAr : CONFIG.telecomeEgyptProviderGeneralErrorDuringRequestEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6517")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptInvalidAccountOrPasswordAr : CONFIG.telecomeEgyptInvalidAccountOrPasswordEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6518")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptInvalidVoucherAmountAr : CONFIG.telecomeEgyptInvalidVoucherAmountEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6519")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptStatusCodeNotInTheMapStatusCodeAr : CONFIG.telecomeEgyptStatusCodeNotInTheMapStatusCodeEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("500")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptServiceNotAvailableAr : CONFIG.telecomeEgyptServiceNotAvailableEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("65110")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptProviderIntegrationAr : CONFIG.telecomeEgyptProviderIntegrationEn);

                }else if (standardHttpJsonResponseDTO.getStatus().equals("429") ) {

                    throw new IOException(lang.equals("ar") ? CONFIG.telecomeEgyptDuplicatetrxAr : CONFIG.telecomeEgyptDuplicatetrxEn);

                }
           
                
            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error(ex, ex);
            throw new ConnectException(lang.equals("ar") ? CONFIG.telecomeEgyptServiceNotAvailableAr : CONFIG.telecomeEgyptServiceNotAvailableEn);
        } catch (IOException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        } catch (ParseException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        } catch (JsonSyntaxException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        }

        return telecomeVoucherPaymentResponse;
    }

}
