/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.common.CONFIG;
import com.masary.common.CommonCalls;
import com.masary.controllers.VodafoneDSL.VodafoneDSLProperties;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.integration.dto.VodafoneDslDenomination;
import com.masary.integration.dto.VodafoneDslPaymentResponse;
import com.masary.integration.dto.VodafoneTopupDslRequest;
import java.net.ConnectException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;

/**
 *
 * @author Ahmed Khaled
 */
public class VodafoneTopupDslClient extends CommonCalls {

    private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private Logger logger;

    public VodafoneDslDenomination[] getDenominations(String lang, String token) throws Exception {

        String VodafoneDslDenominationsUrl = loadUrlProperty("VodafoneTopupDsl.denominations.url");

        this.logger = initLogger();

        logger.info("vodafone topup dsl denominations Url:- " + VodafoneDslDenominationsUrl);

        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(VodafoneDslDenominationsUrl, token);

            CloseableHttpResponse vodafoneDslDenominationsValidateResponseHttp = executeGetRequest(httpclient, httpGet);

            String vodafoneDslDenominationsResponseJson = getJsonFromResponse(vodafoneDslDenominationsValidateResponseHttp);

            logger.info(vodafoneDslDenominationsResponseJson);

            switch (getStatusCode(vodafoneDslDenominationsValidateResponseHttp)) {
                case 200:
                    return json2Object(vodafoneDslDenominationsResponseJson, VodafoneDslDenomination[].class);

                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(vodafoneDslDenominationsResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling vodafone topup dsl service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? VodafoneDSLProperties.Error_VodafoneDsl_ConnectionRefusedAr
                    : VodafoneDSLProperties.Error_VodafoneDsl_ConnectionRefusedEn);
        } catch (Exception e) {
            logger.error("Error during calling vodfaone topup dsl service on new system: ", e);
            throw new Exception(lang.equals("ar") ? CONFIG.transactionErrorar : CONFIG.transactionError);
        } finally {
            httpclient.close();
        }
    }

    public VodafoneDslPaymentResponse topupCharge(VodafoneTopupDslRequest vodafoneTopupDslRequest, String lang,
            String token) throws Exception {

        String VodafoneDslChargeUrl = loadUrlProperty("VodafoneTopupDsl.charge.url");

        this.logger = initLogger();

        logger.info("vodafone topup dsl charge Url:- " + VodafoneDslChargeUrl);

        try {
            this.httpclient = initHttpClient();

            String urlParameters = object2Json(vodafoneTopupDslRequest, VodafoneTopupDslRequest.class);

            this.httpPost = initHttpPost(urlParameters, VodafoneDslChargeUrl, token);

            CloseableHttpResponse vodafoneDslChargeValidateResponseHttp = executePostRequest(httpclient, httpPost);

            String vodafoneDslChargeResponseJson = getJsonFromResponse(vodafoneDslChargeValidateResponseHttp);

            logger.info(vodafoneDslChargeResponseJson);

            switch (getStatusCode(vodafoneDslChargeValidateResponseHttp)) {
                case 200:
                    return json2Object(vodafoneDslChargeResponseJson, VodafoneDslPaymentResponse.class);

                default:
                    logger.info("Failed to charge vodafone dsl");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(vodafoneDslChargeResponseJson,
                            StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling vodafone topup dsl service on new system: " + ex, ex);
            throw new ConnectException(lang.equals("ar") ? VodafoneDSLProperties.Error_VodafoneDsl_ConnectionRefusedAr
                    : VodafoneDSLProperties.Error_VodafoneDsl_ConnectionRefusedEn);
        } catch (Exception e) {
            logger.error("Error during calling vodfaone topup dsl service on new system: ", e);
            throw new Exception(lang.equals("ar") ? CONFIG.transactionErrorar : CONFIG.transactionError);
        } finally {
            httpclient.close();
        }
    }

    public VodafoneDslPaymentResponse reprint(long ledgerTrxId, String lang, String token) throws Exception {

        String VodafoneDslReprintUrl = loadUrlProperty("VodafoneTopupDsl.reprint.url");
        VodafoneDslReprintUrl = VodafoneDslReprintUrl.replace("{ledgerTrxId}", String.valueOf(ledgerTrxId));

        this.logger = initLogger();

        logger.info("vodafone topup dsl reprint Url:- " + VodafoneDslReprintUrl);

        try {
            this.httpclient = initHttpClient();

            this.httpGet = initHttpGet(VodafoneDslReprintUrl, token);

            CloseableHttpResponse vodafoneDslReprintValidateResponseHttp = executeGetRequest(httpclient, httpGet);

            String vodafoneDslReprintResponseJson = getJsonFromResponse(vodafoneDslReprintValidateResponseHttp);

            logger.info(vodafoneDslReprintResponseJson);

            switch (getStatusCode(vodafoneDslReprintValidateResponseHttp)) {
                case 200:
                    return json2Object(vodafoneDslReprintResponseJson, VodafoneDslPaymentResponse.class);

                default:
                    logger.info("Failed to charge vodafone dsl");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(vodafoneDslReprintResponseJson,
                            StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling vodafone topup dsl service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? VodafoneDSLProperties.Error_VodafoneDsl_ConnectionRefusedAr
                    : VodafoneDSLProperties.Error_VodafoneDsl_ConnectionRefusedEn);
        } catch (Exception e) {
            logger.error("Error during calling vodfaone topup dsl service on new system: ", e);
            throw new Exception(lang.equals("ar") ? CONFIG.transactionErrorar : CONFIG.transactionError);
        } finally {
            httpclient.close();
        }
    }

    protected String getErrorMessage(String lang, String message) {
        String errorMessage = "";
        if (message.equals("10")) {
            errorMessage = (lang.equals("ar") ? VodafoneDSLProperties.Error_VodafoneDsl_BadParameterAr : VodafoneDSLProperties.Error_VodafoneDsl_BadParameterEn);
        } else if (message.equals("14")) {
            errorMessage = (lang.equals("ar") ? CONFIG.Balanc_Messagear : CONFIG.Balanc_Messageen);
        } else if (message.equals("20") || message.equals("21") || message.equals("22") || message.equals("23")
                || message.equals("24") || message.equals("25") || message.equals("28")) {
            errorMessage = (lang.equals("ar") ? VodafoneDSLProperties.Error_VodafoneDsl_ProviderIssuesAr : VodafoneDSLProperties.Error_VodafoneDsl_ProviderIssuesEn);
        } else if (message.equals("30")) {
            errorMessage = (lang.equals("ar") ? VodafoneDSLProperties.Error_VodafoneDsl_WrongDenominationAr : VodafoneDSLProperties.Error_VodafoneDsl_WrongDenominationEn);
        } else if (message.equals("29")) {
            errorMessage = (lang.equals("ar") ? VodafoneDSLProperties.Error_VodafoneDsl_RepeatedCustomerAmountAr : VodafoneDSLProperties.Error_VodafoneDsl_RepeatedCustomerAmountEn);
        } else if (message.equals("1010001")) {
            errorMessage = (lang.equals("ar") ? VodafoneDSLProperties.Error_VodafoneDsl_WrongMsisdnAr : VodafoneDSLProperties.Error_VodafoneDsl_WrongMsisdnEn);
        } else {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
        }
        return errorMessage += ("[E " + message + " ]");
    }
}
