/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

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

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.masary.common.CONFIG;
import com.masary.controlers.WEPostPaid.WePostPaidProperties;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;

import com.masary.integration.dto.WepostPaidInquiryResponse;
import com.masary.integration.dto.WepostPaidPaymentResponse;
import com.masary.integration.dto.WepostPaidRequest;
import com.masary.utils.SystemSettingsUtil;

/**
 *
 * @author Ahmed Khaled
 */
public class WePostPaidClient {

    public static WepostPaidInquiryResponse wePostPaidInquiry(WepostPaidRequest wepostPaidRequest, String lang, String token) throws Exception {
        // complete the code

        String WepostPaidUrl = SystemSettingsUtil.getInstance().loadProperty("WepostPaid.inquiry.url");
        //WepostPaidRequest wepostPaidRequest=new WepostPaidRequest();

        WepostPaidUrl = WepostPaidUrl.replace("{msisdn}", wepostPaidRequest.getMsisdn());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        MasaryManager.logger.info("WepostPaid Inquiry Url:- " + WepostPaidUrl);

        WepostPaidInquiryResponse wepostPaidInquiryResponse = new WepostPaidInquiryResponse();

        try {
            HttpGet httpGet = new HttpGet(WepostPaidUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");

            MasaryManager.logger.info("WepostPaidUrl:- " + WepostPaidUrl);

            CloseableHttpResponse WepostPaidValidateResponseHttp = httpclient.execute(httpGet);

            String WepostPaidValidateResponseJson = EntityUtils.toString(WepostPaidValidateResponseHttp.getEntity(), "UTF-8");

            MasaryManager.logger.info("WepostPaid Service response is : " + WepostPaidValidateResponseJson);

            Gson gson = new Gson();

            if (WepostPaidValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
                wepostPaidInquiryResponse = gson.fromJson(WepostPaidValidateResponseJson, WepostPaidInquiryResponse.class);
            } else {
                MasaryManager.logger.info("Failed to get commission");
                if (WepostPaidValidateResponseHttp.getStatusLine().getStatusCode() == 401) {
                    throw new IOException(lang.equals("ar") ? "غير مسموح لك باجراء العملية" : "Unauthorized to perform a transaction");
                }
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(WepostPaidValidateResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getMessage().equals("2428")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorBalanceTransactionar : WePostPaidProperties.errorBalanceTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("24216")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorAmountTransactionar : WePostPaidProperties.errorAmountTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("2")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorMobileTransactionar : WePostPaidProperties.errorMobileTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("24218")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorWeTransactionar : WePostPaidProperties.errorWeTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("24219")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorBadMobileTransactionar : WePostPaidProperties.errorBadMobileTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("24220")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorNoBillsFoundar : WePostPaidProperties.errorNoBillsFound);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("52")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorPaymentar : WePostPaidProperties.errorPayment);
                }  else {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorTransactionar : WePostPaidProperties.errorTransaction);
                }
            }
        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling WepostPaid service on new system: " + ex, ex);
            throw new ConnectException(lang.equals("ar") ? WePostPaidProperties.errorWepostPaidConnectionRefusedAr : WePostPaidProperties.errorWepostPaidConnectionRefusedEn);
        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling WepostPaid service on new system: " + ex, ex);
            throw (ex);
        }  finally {
            httpclient.close();
        }
        return wepostPaidInquiryResponse;
    }

    public static WepostPaidPaymentResponse wePostPaidPayment(WepostPaidRequest WepostPaidRequest, String lang, String token) throws Exception {

        String WepostPaidUrl = SystemSettingsUtil.getInstance().loadProperty("WepostPaid.payment.url");
        MasaryManager.logger.info("WepostPaid Payment Url:- " + WepostPaidUrl + WepostPaidRequest);

        CloseableHttpClient httpclient = HttpClients.createDefault();

        WepostPaidPaymentResponse wepostPaidPaymentResponse = new WepostPaidPaymentResponse();

        try {
            HttpPost httpPost = new HttpPost(WepostPaidUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "Web");
            MasaryManager.logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

            Gson gson = new Gson();
            String urlParameters = gson.toJson(WepostPaidRequest, WepostPaidRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);

            CloseableHttpResponse WepostPaidPaymentResponseHttp = httpclient.execute(httpPost);

            String WepostPaidPaymentResponseJson = EntityUtils.toString(WepostPaidPaymentResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("WepostPaid Service response is : " + WepostPaidPaymentResponseJson);

            if (WepostPaidPaymentResponseHttp.getStatusLine().getStatusCode() == 200) {
                wepostPaidPaymentResponse = gson.fromJson(WepostPaidPaymentResponseJson, WepostPaidPaymentResponse.class);
                MasaryManager.logger.info("WepostPaid Payment Response:- " + wepostPaidPaymentResponse);
            } else {
                MasaryManager.logger.error("Failed to pay the sent amount");
                if (WepostPaidPaymentResponseHttp.getStatusLine().getStatusCode() == 401) {
                    throw new IOException(lang.equals("ar") ? "غير مسموح لك باجراء العملية" : "Unauthorized to perform a transaction");
                }
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(WepostPaidPaymentResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getMessage().equals("2428")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorBalanceTransactionar : WePostPaidProperties.errorBalanceTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("24216")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorAmountTransactionar : WePostPaidProperties.errorAmountTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("2")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorMobileTransactionar : WePostPaidProperties.errorMobileTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("24218")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorWeTransactionar : WePostPaidProperties.errorWeTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("24219")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorBadMobileTransactionar : WePostPaidProperties.errorBadMobileTransaction);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("24220")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorNoBillsFoundar : WePostPaidProperties.errorNoBillsFound);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("52")) {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorPaymentar : WePostPaidProperties.errorPayment);

                } else {
                    throw new IOException(lang.equals("ar") ? WePostPaidProperties.errorTransactionar : WePostPaidProperties.errorTransaction);
                }
            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling WepostPaid service on new system: " + ex, ex);
            throw new ConnectException(lang.equals("ar") ? WePostPaidProperties.errorWepostPaidConnectionRefusedAr : WePostPaidProperties.errorWepostPaidConnectionRefusedEn);
        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling WepostPaid service on new system: " + ex, ex);
            throw (ex);
        } finally {
            httpclient.close();
        }

        return wepostPaidPaymentResponse;
    }

}
