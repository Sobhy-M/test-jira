/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.BillsDetailsRepresentation;
import com.masary.integration.dto.CommissionPresentation;
import com.masary.integration.dto.SuggestionBillsRepresentation;
import com.masary.integration.dto.PetroTradePaymentRequestDTO;
import com.masary.integration.dto.PetrotradeCounterReadingInquiry;
import com.masary.integration.dto.PetrotradeCounterReadingPayment;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.integration.dto.TransactionRepresentation;
import com.masary.utils.SystemSettingsUtil;
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
 * @author AYA
 */
public class PetroTradeClient {

    public BillsDetailsRepresentation petroTradeInquiry(String mobile, String lang, String token) throws Exception {

        String petroTradeInquiryUrl = SystemSettingsUtil.getInstance().loadProperty("petroTrade.inquiry.url");

        petroTradeInquiryUrl = petroTradeInquiryUrl.replace("{subscriberNumber}", mobile);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        BillsDetailsRepresentation backValidateResponse = new BillsDetailsRepresentation();

        try {

            HttpGet httpGet = new HttpGet(petroTradeInquiryUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");

            CloseableHttpResponse petroTradeInquiryResponseHttp = httpclient.execute(httpGet);
            String petroTradeInquiryResponseJson = EntityUtils.toString(petroTradeInquiryResponseHttp.getEntity(), "Windows-1256");
            MasaryManager.logger.info("PetroTrade Service response is : " + petroTradeInquiryResponseJson);

            Gson gson = new Gson();

            if (petroTradeInquiryResponseHttp.getStatusLine().getStatusCode() == 200) {
                backValidateResponse = gson.fromJson(petroTradeInquiryResponseJson, BillsDetailsRepresentation.class);
            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(petroTradeInquiryResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to enquire from petroTrade service " + errorResponseDTO.getMessage());

                if (errorResponseDTO.getMessage().equals("6181")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_AmountOutOfRangeExceptionAr : CONFIG.ERROR_PETROTRADE_AmountOutOfRangeExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6182")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ProviderNoBillFoundExceptionAr : CONFIG.ERROR_PETROTRADE_ProviderNoBillFoundExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6183")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CallingProviderDBExceptionAr : CONFIG.ERROR_PETROTRADE_CallingProviderDBExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6184")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionAr : CONFIG.ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6185")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionAr : CONFIG.ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6187")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_NoCustomerMatchExceptionAr : CONFIG.ERROR_PETROTRADE_NoCustomerMatchExceptionEn);
                } else {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CommonExceptionAr : CONFIG.ERROR_PETROTRADE_CommonExceptionEn);
                }

            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling petroTrade inquiry service on new system: " + ex.getMessage());
            throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CommonExceptionAr : CONFIG.ERROR_PETROTRADE_CommonExceptionEn);
        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling petroTrade inquiry service on new system: " + ex.getMessage());
            throw new Exception(ex.getMessage());
        } finally {
            httpclient.close();
        }

        return backValidateResponse;
    }

    public CommissionPresentation getPetroTradeCommession(PetroTradePaymentRequestDTO petroTradeRequest, String lang, String token) throws Exception {
        String petroTradeCommessionUrl = SystemSettingsUtil.getInstance().loadProperty("petroTrade.commession.url");

        petroTradeCommessionUrl = petroTradeCommessionUrl.replace("{subscriberNumber}", petroTradeRequest.getSubscriberNumber());
        petroTradeCommessionUrl = petroTradeCommessionUrl.replace("{amount}", petroTradeRequest.getAmount().toString());
        CloseableHttpClient httpclient = HttpClients.createDefault();

        CommissionPresentation backValidateResponse = new CommissionPresentation();

        try {

            HttpGet httpGet = new HttpGet(petroTradeCommessionUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");

            CloseableHttpResponse petroTradeInquiryResponseHttp = httpclient.execute(httpGet);
            String petroTradeInquiryResponseJson = EntityUtils.toString(petroTradeInquiryResponseHttp.getEntity(), "Windows-1256");
            MasaryManager.logger.info("PetroTrade Service response is : " + petroTradeInquiryResponseJson);

            Gson gson = new Gson();

            if (petroTradeInquiryResponseHttp.getStatusLine().getStatusCode() == 200) {
                backValidateResponse = gson.fromJson(petroTradeInquiryResponseJson, CommissionPresentation.class);
            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(petroTradeInquiryResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to get commission from petroTrade service " + errorResponseDTO.getMessage());

                if (errorResponseDTO.getMessage().equals("6181")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_AmountOutOfRangeExceptionAr : CONFIG.ERROR_PETROTRADE_AmountOutOfRangeExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6182")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ProviderNoBillFoundExceptionAr : CONFIG.ERROR_PETROTRADE_ProviderNoBillFoundExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6183")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CallingProviderDBExceptionAr : CONFIG.ERROR_PETROTRADE_CallingProviderDBExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6184")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionAr : CONFIG.ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6185")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionAr : CONFIG.ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6187")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_NoCustomerMatchExceptionAr : CONFIG.ERROR_PETROTRADE_NoCustomerMatchExceptionEn);
                } else {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CommonExceptionAr : CONFIG.ERROR_PETROTRADE_CommonExceptionEn);
                }
            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling petroTrade inquiry service on new system: " + ex.getMessage());
            throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CommonExceptionAr : CONFIG.ERROR_PETROTRADE_CommonExceptionEn);
        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling petroTrade inquiry service on new system: " + ex.getMessage());
            throw new Exception(ex.getMessage());
        } finally {
            httpclient.close();
        }

        return backValidateResponse;
    }

    public SuggestionBillsRepresentation loadSuggestion(PetroTradePaymentRequestDTO petroTradeRequest, String lang, String token) throws Exception {
        String petroTradeCommessionUrl = SystemSettingsUtil.getInstance().loadProperty("petroTrade.loadSuggestion.url");

        petroTradeCommessionUrl = petroTradeCommessionUrl.replace("{subscriberNumber}", petroTradeRequest.getSubscriberNumber());
        petroTradeCommessionUrl = petroTradeCommessionUrl.replace("{amount}", petroTradeRequest.getAmount().toString());
        CloseableHttpClient httpclient = HttpClients.createDefault();

        SuggestionBillsRepresentation backValidateResponse = new SuggestionBillsRepresentation();

        try {

            HttpGet httpGet = new HttpGet(petroTradeCommessionUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");

            CloseableHttpResponse petroTradeInquiryResponseHttp = httpclient.execute(httpGet);
            String petroTradeInquiryResponseJson = EntityUtils.toString(petroTradeInquiryResponseHttp.getEntity(), "Windows-1256");
            MasaryManager.logger.info("PetroTrade Service response is : " + petroTradeInquiryResponseJson);

            Gson gson = new Gson();

            if (petroTradeInquiryResponseHttp.getStatusLine().getStatusCode() == 200) {
                backValidateResponse = gson.fromJson(petroTradeInquiryResponseJson, SuggestionBillsRepresentation.class);
            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(petroTradeInquiryResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to load suggestions from petroTrade service " + errorResponseDTO.getMessage());
                if (errorResponseDTO.getMessage().equals("6181")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_AmountOutOfRangeExceptionAr : CONFIG.ERROR_PETROTRADE_AmountOutOfRangeExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6182")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ProviderNoBillFoundExceptionAr : CONFIG.ERROR_PETROTRADE_ProviderNoBillFoundExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6183")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CallingProviderDBExceptionAr : CONFIG.ERROR_PETROTRADE_CallingProviderDBExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6184")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionAr : CONFIG.ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6185")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionAr : CONFIG.ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6187")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_NoCustomerMatchExceptionAr : CONFIG.ERROR_PETROTRADE_NoCustomerMatchExceptionEn);
                } else {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CommonExceptionAr : CONFIG.ERROR_PETROTRADE_CommonExceptionEn);
                }
            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling petroTrade inquiry service on new system: " + ex.getMessage());
            throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CommonExceptionAr : CONFIG.ERROR_PETROTRADE_CommonExceptionEn);
        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling petroTrade inquiry service on new system: " + ex.getMessage());
            throw new Exception(ex.getMessage());
        } finally {
            httpclient.close();
        }

        return backValidateResponse;
    }

    public TransactionRepresentation petroTradePayment(PetroTradePaymentRequestDTO petroTradeRequest, String lang, String token, String ip) throws Exception {

        String petroTradePaymentUrl = SystemSettingsUtil.getInstance().loadProperty("petroTrade.payment.url");

        CloseableHttpClient httpclient = HttpClients.createDefault();

        TransactionRepresentation petroTradePaymentResponse = new TransactionRepresentation();

        try {

            HttpPost httpPost = new HttpPost(petroTradePaymentUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "Web");
            httpPost.addHeader("ip", ip);
            Gson gson = new Gson();
            String urlParameters = gson.toJson(petroTradeRequest, PetroTradePaymentRequestDTO.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);

            CloseableHttpResponse petroTradePayResponseHttp = httpclient.execute(httpPost);

            String petroTradePayResponseJson = EntityUtils.toString(petroTradePayResponseHttp.getEntity(), "Windows-1256");
            MasaryManager.logger.info("PetroTrade Payment Service response is : " + petroTradePayResponseJson);

            if (petroTradePayResponseHttp.getStatusLine().getStatusCode() == 200) {
                petroTradePaymentResponse = gson.fromJson(petroTradePayResponseJson, TransactionRepresentation.class);
            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(petroTradePayResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to pay at petroTrade service " + errorResponseDTO.getMessage());
                if (errorResponseDTO.getMessage().equals("6181")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_AmountOutOfRangeExceptionAr : CONFIG.ERROR_PETROTRADE_AmountOutOfRangeExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6182")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ProviderNoBillFoundExceptionAr : CONFIG.ERROR_PETROTRADE_ProviderNoBillFoundExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6183")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CallingProviderDBExceptionAr : CONFIG.ERROR_PETROTRADE_CallingProviderDBExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6184")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionAr : CONFIG.ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6185")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionAr : CONFIG.ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6187")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_NoCustomerMatchExceptionAr : CONFIG.ERROR_PETROTRADE_NoCustomerMatchExceptionEn);
                } else {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CommonExceptionAr : CONFIG.ERROR_PETROTRADE_CommonExceptionEn);
                }
            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling petroTrade inquiry service on new system: " + ex.getMessage());
            throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CommonExceptionAr : CONFIG.ERROR_PETROTRADE_CommonExceptionEn);
        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling petroTrade inquiry service on new system: " + ex.getMessage());
            throw new Exception(ex.getMessage());
        } finally {
            httpclient.close();
        }

        return petroTradePaymentResponse;
    }

    public static PetrotradeCounterReadingInquiry petroTradeCounterReadingInquiry(String subscriberNumber, String lang, String token) throws Exception {

        String petroTradeInquiryUrl = SystemSettingsUtil.getInstance().loadProperty("Petrotrade.CounterReading.Inquiry.url");

        petroTradeInquiryUrl = petroTradeInquiryUrl.replace("{subscriberNumber}", subscriberNumber);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        PetrotradeCounterReadingInquiry backValidateResponse = new PetrotradeCounterReadingInquiry();

        try {

            HttpGet httpGet = new HttpGet(petroTradeInquiryUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");

            CloseableHttpResponse petroTradeInquiryResponseHttp = httpclient.execute(httpGet);
            String petroTradeInquiryResponseJson = EntityUtils.toString(petroTradeInquiryResponseHttp.getEntity(), "Windows-1256");
            MasaryManager.logger.info("PetroTrade Service response is : " + petroTradeInquiryResponseJson);

            Gson gson = new Gson();

            if (petroTradeInquiryResponseHttp.getStatusLine().getStatusCode() == 200) {
                backValidateResponse = gson.fromJson(petroTradeInquiryResponseJson, PetrotradeCounterReadingInquiry.class);
            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(petroTradeInquiryResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to enquire from petroTrade service " + errorResponseDTO.getMessage());

                if (errorResponseDTO.getMessage().equals("6181")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_AmountOutOfRangeExceptionAr : CONFIG.ERROR_PETROTRADE_AmountOutOfRangeExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6182")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ProviderNoBillFoundExceptionAr : CONFIG.ERROR_PETROTRADE_ProviderNoBillFoundExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6183")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CallingProviderDBExceptionAr : CONFIG.ERROR_PETROTRADE_CallingProviderDBExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6184")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionAr : CONFIG.ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6185")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionAr : CONFIG.ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6186")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ReadingRegisteredExceptionAr : CONFIG.ERROR_PETROTRADE_ReadingRegisteredExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6187")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_NoCustomerMatchExceptionAr : CONFIG.ERROR_PETROTRADE_NoCustomerMatchExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6188")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_RegisterationReadingNotValidExceptionAr : CONFIG.ERROR_PETROTRADE_RegisterationReadingNotValidExceptionEn);
                } else {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CommonExceptionAr : CONFIG.ERROR_PETROTRADE_CommonExceptionEn);
                }

            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling petroTrade inquiry service on new system: " + ex.getMessage());
            throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CommonExceptionAr : CONFIG.ERROR_PETROTRADE_CommonExceptionEn);
        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling petroTrade inquiry service on new system: " + ex.getMessage());
            throw new Exception(ex.getMessage());
        } finally {
            httpclient.close();
        }

        return backValidateResponse;
    }

    public static PetrotradeCounterReadingPayment petroTradeCounterReadingRegistration(PetroTradePaymentRequestDTO petroTradeRequest, String lang, String token) throws Exception {

        String petroTradePaymentUrl = SystemSettingsUtil.getInstance().loadProperty("Petrotrade.CounterReading.Payment.url");

        CloseableHttpClient httpclient = HttpClients.createDefault();

        PetrotradeCounterReadingPayment petroTradePaymentResponse = new PetrotradeCounterReadingPayment();

        try {

            HttpPost httpPost = new HttpPost(petroTradePaymentUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "Web");
            Gson gson = new Gson();
            String urlParameters = gson.toJson(petroTradeRequest, PetroTradePaymentRequestDTO.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);

            CloseableHttpResponse petroTradePayResponseHttp = httpclient.execute(httpPost);

            String petroTradePayResponseJson = EntityUtils.toString(petroTradePayResponseHttp.getEntity(), "Windows-1256");
            MasaryManager.logger.info("PetroTrade Payment Service response is : " + petroTradePayResponseJson);

            if (petroTradePayResponseHttp.getStatusLine().getStatusCode() == 200) {
                petroTradePaymentResponse = gson.fromJson(petroTradePayResponseJson, PetrotradeCounterReadingPayment.class);
            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(petroTradePayResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to pay at petroTrade service " + errorResponseDTO.getMessage());
                if (errorResponseDTO.getMessage().equals("6181")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_AmountOutOfRangeExceptionAr : CONFIG.ERROR_PETROTRADE_AmountOutOfRangeExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6182")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ProviderNoBillFoundExceptionAr : CONFIG.ERROR_PETROTRADE_ProviderNoBillFoundExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6183")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CallingProviderDBExceptionAr : CONFIG.ERROR_PETROTRADE_CallingProviderDBExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6184")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionAr : CONFIG.ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6185")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionAr : CONFIG.ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6186")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_ReadingRegisteredExceptionAr : CONFIG.ERROR_PETROTRADE_ReadingRegisteredExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6187")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_NoCustomerMatchExceptionAr : CONFIG.ERROR_PETROTRADE_NoCustomerMatchExceptionEn);
                } else if (errorResponseDTO.getMessage().equals("6188")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_RegisterationReadingNotValidExceptionAr : CONFIG.ERROR_PETROTRADE_RegisterationReadingNotValidExceptionEn);
                } else if (errorResponseDTO.getStatus().equals("400") && errorResponseDTO.getMessage().equals("10")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_BadParameterAr : CONFIG.ERROR_PETROTRADE_BadParameterEn);
                } else {
                    throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CommonExceptionAr : CONFIG.ERROR_PETROTRADE_CommonExceptionEn);
                }
            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling petroTrade inquiry service on new system: " + ex.getMessage());
            throw new Exception(lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_CommonExceptionAr : CONFIG.ERROR_PETROTRADE_CommonExceptionEn);
        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling petroTrade inquiry service on new system: " + ex.getMessage());
            throw new Exception(ex.getMessage());
        } finally {
            httpclient.close();
        }

        return petroTradePaymentResponse;
    }

}
