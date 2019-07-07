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
import com.masary.integration.dto.FMFInquiryResponse;
import com.masary.integration.dto.FMFCommissionResponse;
import com.masary.integration.dto.FMFPaymentRequest;
import com.masary.integration.dto.FMFTotalPaymentResponse;
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
public class FMFClient {

    public FMFInquiryResponse fmfInquiry(String customerCode, String lang, String token) throws Exception {

        String fmfUrl = SystemSettingsUtil.getInstance().loadProperty("fmf.inquiry.url");

        fmfUrl = fmfUrl.replace("{customerCode}", customerCode);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        MasaryManager.logger.info("fmf Inquiry Url:- " + fmfUrl);
        FMFInquiryResponse fmfInquiryResponse = new FMFInquiryResponse();

        try {
            HttpGet httpGet = new HttpGet(fmfUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");
            MasaryManager.logger.info("fmfUrl:- " + fmfUrl);
            CloseableHttpResponse fmfValidateResponseHttp = httpclient.execute(httpGet);

            String fmfValidateResponseJson = EntityUtils.toString(fmfValidateResponseHttp.getEntity(), "UTF-8");

            MasaryManager.logger.info("fmf Json Inquiry response is : " + fmfValidateResponseJson);

            Gson gson = new Gson();

            if (fmfValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
                fmfInquiryResponse = gson.fromJson(fmfValidateResponseJson, FMFInquiryResponse.class);
                fmfInquiryResponse.setStatusCode(fmfValidateResponseHttp.getStatusLine().getStatusCode());
                MasaryManager.logger.info("fmf Inquiry Response:- " + fmfInquiryResponse);
            } else {
                MasaryManager.logger.info("Failed to get commission");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(fmfValidateResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6231")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFNoInstallmentFoundAr : CONFIG.FMFNoInstallmentFoundEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6232")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFAmountOutOfRangeAr : CONFIG.FMFAmountOutOfRangeEn);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("423") && standardHttpJsonResponseDTO.getMessage().equals("1")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFInActiveAccountAr : CONFIG.FMFInActiveAccountEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("500")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFServiceNotAvailableAr : CONFIG.FMFServiceNotAvailableAr);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("4")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFNotAcceptableAr : CONFIG.FMFNotAcceptableEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("416") && standardHttpJsonResponseDTO.getMessage().equals("6")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFAmountOutOfRangeAr : CONFIG.FMFAmountOutOfRangeEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("400") && standardHttpJsonResponseDTO.getMessage().equals("10")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFMissingParamAr : CONFIG.FMFMissingParamEn);

                } else {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFServiceNotAvailableAr : CONFIG.FMFServiceNotAvailableEn);

                }
            }

        } catch (ConnectException e) {
            MasaryManager.logger.error(e, e);
            throw new IOException(lang.equals("ar") ? CONFIG.FMFServiceNotAvailableAr : CONFIG.FMFServiceNotAvailableEn);
        } catch (IOException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        } catch (ParseException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        } catch (JsonSyntaxException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        } finally {
            httpclient.close();
        }
        return fmfInquiryResponse;
    }

    public FMFCommissionResponse fmfCommission(FMFPaymentRequest paymentRequest, String lang, String token) throws Exception {
        String fmfUrl = SystemSettingsUtil.getInstance().loadProperty("fmf.commission.url");

        fmfUrl = fmfUrl.replace("{customerCode}", paymentRequest.getIdentificationNumber());
        fmfUrl = fmfUrl.replace("{amount}", paymentRequest.getAmount().toString());
        CloseableHttpClient httpclient = HttpClients.createDefault();

        MasaryManager.logger.info("fmf Partial Payment Url:- " + fmfUrl);
        FMFCommissionResponse fmfPaymentResponse = new FMFCommissionResponse();

        try {
            HttpGet httpGet = new HttpGet(fmfUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");
            MasaryManager.logger.info("fmfUrl:- " + fmfUrl);
            CloseableHttpResponse fmfValidateResponseHttp = httpclient.execute(httpGet);

            String fmfValidateResponseJson = EntityUtils.toString(fmfValidateResponseHttp.getEntity(), "UTF-8");

            MasaryManager.logger.info("fmf Json Inquiry response is : " + fmfValidateResponseJson);

            Gson gson = new Gson();

            if (fmfValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
                fmfPaymentResponse = gson.fromJson(fmfValidateResponseJson, FMFCommissionResponse.class);
                fmfPaymentResponse.setStatusCode(fmfValidateResponseHttp.getStatusLine().getStatusCode());
                MasaryManager.logger.info("fmf Partial Payment Response:- " + fmfPaymentResponse);
            } else {
                MasaryManager.logger.info("Failed to get commission");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(fmfValidateResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6231")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFNoInstallmentFoundAr : CONFIG.FMFNoInstallmentFoundEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6232")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFAmountOutOfRangeAr : CONFIG.FMFAmountOutOfRangeEn);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("423") && standardHttpJsonResponseDTO.getMessage().equals("1")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFInActiveAccountAr : CONFIG.FMFInActiveAccountEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("500")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFServiceNotAvailableAr : CONFIG.FMFServiceNotAvailableAr);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("4")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFNotAcceptableAr : CONFIG.FMFNotAcceptableEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("416") && standardHttpJsonResponseDTO.getMessage().equals("6")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFAmountOutOfRangeAr : CONFIG.FMFAmountOutOfRangeEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("400") && standardHttpJsonResponseDTO.getMessage().equals("10")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFMissingParamAr : CONFIG.FMFMissingParamEn);

                } else {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFServiceNotAvailableAr : CONFIG.FMFServiceNotAvailableEn);

                }
            }

        } catch (ConnectException e) {
            MasaryManager.logger.error(e, e);
            throw new IOException(lang.equals("ar") ? CONFIG.FMFServiceNotAvailableAr : CONFIG.FMFServiceNotAvailableEn);
        } catch (IOException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        } catch (ParseException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        } catch (JsonSyntaxException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        } finally {
            httpclient.close();
        }
        return fmfPaymentResponse;
    }

    public FMFTotalPaymentResponse fmfPayment(FMFPaymentRequest paymentRequest, String lang, String token) throws Exception {

        String fmfUrl = SystemSettingsUtil.getInstance().loadProperty("fmf.payment.url");

        CloseableHttpClient httpclient = HttpClients.createDefault();

        MasaryManager.logger.info("fmf total payment Url:- " + fmfUrl);
        FMFTotalPaymentResponse fmfTotalPaymentResponse = new FMFTotalPaymentResponse();

        try {
            HttpPost httpPost = new HttpPost(fmfUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "WEB");

            MasaryManager.logger.info("fmfUrl:- " + fmfUrl);

            Gson gson = new Gson();

            String urlParameters = gson.toJson(paymentRequest, FMFPaymentRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);

            CloseableHttpResponse fmfValidateResponseHttp = httpclient.execute(httpPost);
            String fmfValidateResponseJson = EntityUtils.toString(fmfValidateResponseHttp.getEntity(), "UTF-8");

            MasaryManager.logger.info("fmf total payment response is : " + fmfValidateResponseJson);

            if (fmfValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
                fmfTotalPaymentResponse = gson.fromJson(fmfValidateResponseJson, FMFTotalPaymentResponse.class);
                fmfTotalPaymentResponse.setStatusCode(fmfValidateResponseHttp.getStatusLine().getStatusCode());
                MasaryManager.logger.info("fmf Total Payment Response:- " + fmfTotalPaymentResponse);
            } else {
                MasaryManager.logger.info("Failed to get commission");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(fmfValidateResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6231")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFNoInstallmentFoundAr : CONFIG.FMFNoInstallmentFoundEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("424") && standardHttpJsonResponseDTO.getMessage().equals("6232")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFAmountOutOfRangeAr : CONFIG.FMFAmountOutOfRangeEn);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("423") && standardHttpJsonResponseDTO.getMessage().equals("1")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFInActiveAccountAr : CONFIG.FMFInActiveAccountEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("500")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFServiceNotAvailableAr : CONFIG.FMFServiceNotAvailableAr);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("4")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFNotAcceptableAr : CONFIG.FMFNotAcceptableEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("416") && standardHttpJsonResponseDTO.getMessage().equals("6")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFAmountOutOfRangeAr : CONFIG.FMFAmountOutOfRangeEn);

                } else if (standardHttpJsonResponseDTO.getStatus().equals("400") && standardHttpJsonResponseDTO.getMessage().equals("10")) {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFMissingParamAr : CONFIG.FMFMissingParamEn);

                } else {

                    throw new IOException(lang.equals("ar") ? CONFIG.FMFServiceNotAvailableAr : CONFIG.FMFServiceNotAvailableEn);

                }
            }

        } catch (ConnectException e) {
            MasaryManager.logger.error(e, e);
            throw new IOException(lang.equals("ar") ? CONFIG.FMFServiceNotAvailableAr : CONFIG.FMFServiceNotAvailableEn);
        } catch (IOException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        } catch (ParseException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        } catch (JsonSyntaxException ex) {
            MasaryManager.logger.error(ex, ex);
            throw ex;
        } finally {
            httpclient.close();
        }
        return fmfTotalPaymentResponse;
    }

}
