/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.common.CommonCalls;

import com.masary.integration.dto.TamweelInquiryRepresentation;
import com.masary.integration.dto.TamweelPaymentRepresentation;
import com.masary.integration.dto.TamweelPaymentRequest;

import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.integration.dto.TamweelPartialInquiryRequest;
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
public class TamweelClient extends CommonCalls {

    private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private Logger logger;
    private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");

    public TamweelInquiryRepresentation inquiryCode(String customerCode, String lang, String token) throws Exception {
        this.logger = initLogger();
        String inquiryUrl = loadUrlProperty("Tamweel.inquiry.url");
        inquiryUrl = prepareUrl(inquiryUrl, "{customerCode}", customerCode);

        logger.info("Tamweel inquiry Url:- " + inquiryUrl);
        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(inquiryUrl, token);

            CloseableHttpResponse inquiryResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info("Tamweel inquiry Http Response" + inquiryResponseHttp);

            String inquiryResponseJson = getJsonFromResponse(inquiryResponseHttp);

            logger.info("Tamweel inquiry JSON" + inquiryResponseJson);

            switch (getStatusCode(inquiryResponseHttp)) {
                case 200:
                    return json2Object(inquiryResponseJson, TamweelInquiryRepresentation.class);

                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(inquiryResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling Tamweel service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling Tamweel service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }

    }

    public TamweelInquiryRepresentation tamweelPartialInquiry(TamweelPartialInquiryRequest tamweelPartialInquiryRequest, String lang, String token) throws Exception {
        this.logger = initLogger();

        String partialInquiryUrl = loadUrlProperty("Tamweel.inquiry.partial.url");

        logger.info("Tamweel Partial Inquiry Url:- " + partialInquiryUrl);

        try {
            this.httpclient = initHttpClient();

            String urlParameters = object2Json(tamweelPartialInquiryRequest, TamweelPartialInquiryRequest.class);
            logger.info("Tamweel Partial Inquiry url parameters :- " + urlParameters);

            this.httpPost = initHttpPost(urlParameters, partialInquiryUrl, token);

            CloseableHttpResponse paymentResponseHttp = executePostRequest(httpclient, httpPost);
            logger.info("Tamweel Partial Inquiry Http Response" + paymentResponseHttp);

            String paymentResponseJson = getJsonFromResponse(paymentResponseHttp);
            logger.info("Tamweel Partial Inquiry JSON" + paymentResponseJson);

            switch (getStatusCode(paymentResponseHttp)) {
                case 200:
                    return json2Object(paymentResponseJson, TamweelInquiryRepresentation.class);
                default:
                    logger.info("Failed to get denominations ");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(paymentResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling  Tamweel Partial Inquiry on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling Tamweel Partial Inquiry on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

    public TamweelPaymentRepresentation tamweelPayment(TamweelPaymentRequest tamweelPaymentRequest, String lang, String token) throws Exception {
        this.logger = initLogger();

        String paymentUrl = loadUrlProperty("Tamweel.payment.url");

        logger.info("Tamweel payment Url:- " + paymentUrl);

        try {
            this.httpclient = initHttpClient();

            String urlParameters = object2Json(tamweelPaymentRequest, TamweelPaymentRequest.class);
            logger.info("tamweel payment url parameters :- " + urlParameters);

            this.httpPost = initHttpPost(urlParameters, paymentUrl, token);

            CloseableHttpResponse paymentResponseHttp = executePostRequest(httpclient, httpPost);
            logger.info("Tamweel payment Http Response" + paymentResponseHttp);

            String paymentResponseJson = getJsonFromResponse(paymentResponseHttp);
            logger.info("Tamweel payment JSON" + paymentResponseJson);

            switch (getStatusCode(paymentResponseHttp)) {
                case 200:
                    return json2Object(paymentResponseJson, TamweelPaymentRepresentation.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(paymentResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling Tamweel payment service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling Tamweel  payment service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

    public TamweelPaymentRepresentation reprint(long ledgerTrxId, String lang, String token) throws Exception {
        this.logger = initLogger();

        String reprintUrl = loadUrlProperty("Tamweel.reprint.url");
        reprintUrl = prepareUrl(reprintUrl, "{ledgerTrxId}", String.valueOf(ledgerTrxId));
        logger.info("Tamweel reprint Url:- " + reprintUrl);

        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(reprintUrl, token);

            CloseableHttpResponse reprintResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info("Tamweel reprint Http Response" + reprintResponseHttp);

            String reprintResponseJson = getJsonFromResponse(reprintResponseHttp);

            logger.info("Tamweel reprint JSON" + reprintResponseJson);

            switch (getStatusCode(reprintResponseHttp)) {
                case 200:
                    return json2Object(reprintResponseJson, TamweelPaymentRepresentation.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(reprintResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling Tamweel reprint service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling Tamweel  reprint service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

    private String getErrorMessage(String lang, String message) {
        String errorMessage = "";
        if (message.equals("201001")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201001Ar") : rb.getString("MESSAGE_Tamweel_201001En");
        } else if (message.equals("201002")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201002Ar") : rb.getString("MESSAGE_Tamweel_201002En");
        } else if (message.equals("201003")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201003Ar") : rb.getString("MESSAGE_Tamweel_201003En");
        } else if (message.equals("201004")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201004Ar") : rb.getString("MESSAGE_Tamweel_201004En");
        } else if (message.equals("201005")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201004Ar") : rb.getString("MESSAGE_Tamweel_201004Ar");
        } else if (message.equals("201006")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201004Ar") : rb.getString("MESSAGE_Tamweel_201004Ar");
        } else if (message.equals("MESSAGE_Tamweel_14Ar")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_14Ar") : rb.getString("MESSAGE_Tamweel_14En");
        } 
        else {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201004Ar") : rb.getString("MESSAGE_Tamweel_201004Ar");
        }
        return errorMessage += ("[ E " + message + " ]");
    }
}
