/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.common.CommonCalls;
import com.masary.integration.dto.EtisalatGiftsInquiryRepresentation;
import com.masary.integration.dto.EtisalatGiftsInquiryRequest;

import com.masary.integration.dto.EtisalatGiftsPaymentRepresentation;
import com.masary.integration.dto.EtisalatGiftsRequest;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import java.net.ConnectException;
import java.util.ResourceBundle;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;

/**
 *
 * @author omar.abdellah
 */
public class EtisalatGiftsClient extends CommonCalls {

    private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private Logger logger;
    private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");

    public EtisalatGiftsInquiryRepresentation verfiVoucher(EtisalatGiftsInquiryRequest etisalatGiftsInquiryRequest, String lang, String token) throws ConnectException, Exception {
        this.logger = initLogger();

        String inquiryUrl = loadUrlProperty("EtisalatGifts.inquiry.url");

        inquiryUrl = prepareUrl(inquiryUrl, etisalatGiftsInquiryRequest);

        logger.info("Etisalat Gifts inquiry Url:- " + inquiryUrl);
        try {
            this.httpclient = initHttpClient();

            this.httpGet = initHttpGet(inquiryUrl, token);

            CloseableHttpResponse inquiryResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info("Orange Gifts inquiry Http Response" + inquiryResponseHttp);

            String inquiryResponseJson = getJsonFromResponse(inquiryResponseHttp);

            logger.info("Orange Gifts inquiry JSON" + inquiryResponseJson);

            switch (getStatusCode(inquiryResponseHttp)) {
                case 200:
                    return json2Object(inquiryResponseJson, EtisalatGiftsInquiryRepresentation.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(inquiryResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling etisalat gifts service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling etisalat gifts service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }

    }

    public EtisalatGiftsPaymentRepresentation voucherRedemption(EtisalatGiftsRequest etisalatGiftsRequest, String lang, String token) throws ConnectException, Exception {

        this.logger = initLogger();

        String paymentUrl = loadUrlProperty("EtisalatGifts.payment.url");
        logger.info("Orange Gifts payment Url:- " + paymentUrl);

        try {
            this.httpclient = initHttpClient();

            String urlParameters = object2Json(etisalatGiftsRequest, EtisalatGiftsRequest.class);
            logger.info("Orange Gifts url parameters :- " + urlParameters);

            this.httpPost = initHttpPost(urlParameters, paymentUrl, token);

            CloseableHttpResponse paymentResponseHttp = executePostRequest(httpclient, httpPost);
            logger.info("Orange Gifts payment Http Response" + paymentResponseHttp);

            String paymentResponseJson = getJsonFromResponse(paymentResponseHttp);
            logger.info("Orange Gifts payment JSON" + paymentResponseJson);

            switch (getStatusCode(paymentResponseHttp)) {
                case 200:
                    return json2Object(paymentResponseJson, EtisalatGiftsPaymentRepresentation.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(paymentResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling Etisalat gifts service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling Etisalat gifts service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }

    }

    public EtisalatGiftsPaymentRepresentation reprint(long ledgerTrxId, String lang, String token) throws Exception {
        this.logger = initLogger();

        String reprintUrl = loadUrlProperty("EtisalatGifts.reprint.url");
        reprintUrl = prepareUrl(reprintUrl, "{ledgerTrxId}", String.valueOf(ledgerTrxId));
        logger.info("Orange Gifts reprint Url:- " + reprintUrl);

        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(reprintUrl, token);

            CloseableHttpResponse reprintResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info("Orange Gifts reprint Http Response" + reprintResponseHttp);

            String reprintResponseJson = getJsonFromResponse(reprintResponseHttp);

            logger.info("Orange Gifts reprint JSON" + reprintResponseJson);

            switch (getStatusCode(reprintResponseHttp)) {
                case 200:
                    return json2Object(reprintResponseJson, EtisalatGiftsPaymentRepresentation.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(reprintResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling Etisalat gifts service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling Etisalat gifts service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

    private String getErrorMessage(String lang, String message) {
        String errorMessage = "";
        if (message.equals("668001")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_EtisalatGifts_668001Ar") : rb.getString("MESSAGE_EtisalatGifts_668001_En");
        } else if (message.equals("668002")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_EtisalatGifts_668002Ar") : rb.getString("MESSAGE_EtisalatGifts_668002_En");
        } else if (message.equals("668003")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_EtisalatGifts_668003Ar") : rb.getString("MESSAGE_EtisalatGifts_668003Ar");
        } else {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_EtisalatGifts_668004Ar") : rb.getString("MESSAGE_EtisalatGifts_668004Ar");
        }
        return errorMessage += ("[ E " + message + " ]");
    }

    private String prepareUrl(String inquiryUrl, EtisalatGiftsInquiryRequest etisalatGiftsInquiryRequest) {

        inquiryUrl = inquiryUrl.replace("{voucherCode}", String.valueOf(etisalatGiftsInquiryRequest.getVoucherCode()));
        inquiryUrl = inquiryUrl.replace("{mobileNumber}", etisalatGiftsInquiryRequest.getMobileNumber());
        inquiryUrl = inquiryUrl.replace("{voucherAmount}", String.valueOf(etisalatGiftsInquiryRequest.getVoucherAmount()));

        return inquiryUrl;
    }

}
