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
import com.masary.integration.dto.EsedInquiryResponse;
import com.masary.integration.dto.EsedPaymentRequest;
import com.masary.integration.dto.EsedPaymentResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
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
public class ESEDClient {

    public EsedInquiryResponse esedValidateInquiry(String nationalIdOrCodeNumber, String lang, String token) throws Exception {

        String esedUrl = SystemSettingsUtil.getInstance().loadProperty("esed.inquiry.url");

//        String proxyAuthorizationToken = gooBackRequest.getToken();
        esedUrl = esedUrl.replace("{nationalIdOrCodeNumber}", nationalIdOrCodeNumber);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        MasaryManager.logger.info("ESED Inquiry Url:- " + esedUrl);
        EsedInquiryResponse esedResponse = new EsedInquiryResponse();

        try {
            HttpGet httpGet = new HttpGet(esedUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");
            MasaryManager.logger.info("esed Url:- " + esedUrl);
            CloseableHttpResponse esedValidateResponseHttp = httpclient.execute(httpGet);

            String esedResponseJson = EntityUtils.toString(esedValidateResponseHttp.getEntity(), "UTF-8");

            MasaryManager.logger.info("ESED Service response is : " + esedResponseJson);

            Gson gson = new Gson();

            if (esedValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
                esedResponse = gson.fromJson(esedResponseJson, EsedInquiryResponse.class);
                esedResponse.setStatusCode(esedValidateResponseHttp.getStatusLine().getStatusCode());
            } else {
                MasaryManager.logger.info("Failed to get commission");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(esedResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6161")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDInvalidNationalIDAr : CONFIG.ESEDInvalidNationalIDEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6162")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDInvalidNationalIDOrCodeNumberAr : CONFIG.ESEDInvalidNationalIDOrCodeNumberEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6163")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDCustomerNotFoundAr : CONFIG.ESEDCustomerNotFoundEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6164")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDNoInstallmentFoundAr : CONFIG.ESEDNoInstallmentFoundEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6165")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDMasaryInquiryFailureAr : CONFIG.ESEDMasaryInquiryFailureEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6166")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDIntegrationFailureAr : CONFIG.ESEDIntegrationFailureEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6167")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDPaymentFailureAr : CONFIG.ESEDPaymentFailureEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6168")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDClassNotFoundAr : CONFIG.ESEDClassNotFoundEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("423") && standardHttpJsonResponseDTO.getMessage().equals("1")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDInActiveAccountAr : CONFIG.ESEDInActiveAccountEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("500")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDServiceNotAvailableAr : CONFIG.ESEDServiceNotAvailableAr);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("4")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDNotAcceptableAr : CONFIG.ESEDNotAcceptableEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("416") && standardHttpJsonResponseDTO.getMessage().equals("6")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDAmountOutOfRangeAr : CONFIG.ESEDAmountOutOfRangeEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("400") && standardHttpJsonResponseDTO.getMessage().equals("10")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDMissingParamAr : CONFIG.ESEDMissingParamEn);

                } else {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDServiceNotAvailableAr : CONFIG.ESEDServiceNotAvailableEn);

                }

            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling ESED service on new system: " + ex, ex);
            throw new IOException(lang.equals("ar") ? CONFIG.ESEDServiceNotAvailableAr : CONFIG.ESEDServiceNotAvailableEn);
        } catch (IOException ex) {

            MasaryManager.logger.error("Error during calling ESED service on new system: " + ex, ex);
            throw ex;
        } catch (ParseException ex) {
            MasaryManager.logger.error("Error during calling ESED service on new system: " + ex, ex);
            throw ex;
        } catch (JsonSyntaxException ex) {
            MasaryManager.logger.error("Error during calling ESED service on new system: " + ex, ex);
            throw ex;
        } finally {
            httpclient.close();
        }
        return esedResponse;
    }

    public EsedPaymentResponse esedPayment(EsedPaymentRequest esedPaymentRequest, String lang, String token) throws Exception {

        String esedUrl = SystemSettingsUtil.getInstance().loadProperty("esed.payment.url");

        MasaryManager.logger.info("esed Payment Url:- " + esedUrl);

        CloseableHttpClient httpclient = HttpClients.createDefault();

        EsedPaymentResponse esedPaymentResponse = new EsedPaymentResponse();

        try {
            HttpPost httpPost = new HttpPost(esedUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "WEB");
            MasaryManager.logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");
            Gson gson = new Gson();

            String urlParameters = gson.toJson(esedPaymentRequest, EsedPaymentRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);

            CloseableHttpResponse esedPaymentResponseHttp = httpclient.execute(httpPost);

            String esedPaymentResponseJson = EntityUtils.toString(esedPaymentResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("esed Service response is : " + esedPaymentResponseJson);

            if (esedPaymentResponseHttp.getStatusLine().getStatusCode() == 200) {
                esedPaymentResponse = gson.fromJson(esedPaymentResponseJson, EsedPaymentResponse.class);
                esedPaymentResponse.setStatusCode(esedPaymentResponseHttp.getStatusLine().getStatusCode());
                MasaryManager.logger.info("esed Payment Response:- " + esedPaymentResponse);
            } else {
                MasaryManager.logger.error("Failed to pay the sent amount");
//                throw new Exception(lang.equals("ar") ? "مشكله اثناء دفع القيمه المرسله" : "Failed to pay the sent amount");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(esedPaymentResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6161")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDInvalidNationalIDAr : CONFIG.ESEDInvalidNationalIDEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6162")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDInvalidNationalIDOrCodeNumberAr : CONFIG.ESEDInvalidNationalIDOrCodeNumberEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6163")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDCustomerNotFoundAr : CONFIG.ESEDCustomerNotFoundEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6164")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDNoInstallmentFoundAr : CONFIG.ESEDNoInstallmentFoundEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6165")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDMasaryInquiryFailureAr : CONFIG.ESEDMasaryInquiryFailureEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6166")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDIntegrationFailureAr : CONFIG.ESEDIntegrationFailureEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6167")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDPaymentFailureAr : CONFIG.ESEDPaymentFailureEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6168")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDClassNotFoundAr : CONFIG.ESEDClassNotFoundEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("423") && standardHttpJsonResponseDTO.getMessage().equals("1")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDInActiveAccountAr : CONFIG.ESEDInActiveAccountEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("500")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDServiceNotAvailableAr : CONFIG.ESEDServiceNotAvailableAr);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("4")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDNotAcceptableAr : CONFIG.ESEDNotAcceptableEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("416") && standardHttpJsonResponseDTO.getMessage().equals("6")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDAmountOutOfRangeAr : CONFIG.ESEDAmountOutOfRangeEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("400") && standardHttpJsonResponseDTO.getMessage().equals("10")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDMissingParamAr : CONFIG.ESEDMissingParamEn);

                } else {

                    throw new IOException(lang.equals("ar") ? CONFIG.ESEDServiceNotAvailableAr : CONFIG.ESEDServiceNotAvailableEn);

                }
            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling esed service on new system: " + ex);
            throw new IOException(lang.equals("ar") ? CONFIG.ESEDServiceNotAvailableAr : CONFIG.ESEDServiceNotAvailableEn);
        } catch (IOException ex) {

            MasaryManager.logger.error("Error during calling esed service on new system: " + ex);
            throw ex;
        } catch (ParseException ex) {
            MasaryManager.logger.error("Error during calling esed service on new system: " + ex);
            throw ex;
        } catch (JsonSyntaxException ex) {
            MasaryManager.logger.error("Error during calling esed service on new system: " + ex);
            throw ex;
        } finally {
            httpclient.close();
        }
        return esedPaymentResponse;
    }

}
