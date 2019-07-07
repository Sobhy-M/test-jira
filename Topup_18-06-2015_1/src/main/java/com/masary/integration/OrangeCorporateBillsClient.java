/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.common.CommonCalls;
import com.masary.integration.dto.OrangeCorporateInquiryResponse;
import com.masary.integration.dto.OrangeCorporatePaymentResponse;
import com.masary.integration.dto.OrangeCorporateRequest;
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
 * @author Ahmed Khaled
 */
public class OrangeCorporateBillsClient extends CommonCalls {

    private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private Logger logger;
    private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");

    public OrangeCorporateInquiryResponse orangeCorporateInquiry(OrangeCorporateRequest orangeCorpRequest, String lang, String token) throws Exception {

        String inquiryUrl = loadUrlProperty("OrangeCorporate.inquiry.url");

        inquiryUrl = inquiryUrl.replace("{msisdn}", orangeCorpRequest.getMsisdn());
        inquiryUrl = inquiryUrl.replace("{accountNumber}", orangeCorpRequest.getAccountNumber());

        this.logger = initLogger();

        logger.info("OrangeCorporate Inquiry Url:- " + inquiryUrl);

        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(inquiryUrl, token);

            CloseableHttpResponse OrangeCorporateValidateResponseHttp = executeGetRequest(httpclient, httpGet);

            String OrangeCorporateResponseJson = getJsonFromResponse(OrangeCorporateValidateResponseHttp);

            logger.info("Orange Corporate Service response is : " + OrangeCorporateResponseJson);

            switch (getStatusCode(OrangeCorporateValidateResponseHttp)) {
                case 200:
                    return json2Object(OrangeCorporateResponseJson, OrangeCorporateInquiryResponse.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(OrangeCorporateResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);
            }

        } catch (ConnectException ex) {
            logger.error("Error during calling Orange Corporate service on new system: " + ex, ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_CONNECTONERRORAr") : rb.getString("MESSAGE_OrangeCorporateBills_CONNECTONERROREn"));
        } catch (Exception ex) {
            logger.error("Error during calling Orange Corporate service on new system: " + ex, ex);
            throw new Exception(ex.getMessage());
        } finally {
            httpclient.close();
        }
    }

    public OrangeCorporateInquiryResponse orangeCorporateAmountInquiry(OrangeCorporateRequest orangeCorpRequest, String lang, String token) throws Exception {

        String amountInquiryUrl = loadUrlProperty("OrangeCorporate.amountInquiry.url");

        amountInquiryUrl = amountInquiryUrl.replace("{paidAmount}", Double.toString(orangeCorpRequest.getPaidAmount()));
        amountInquiryUrl = amountInquiryUrl.replace("{validationId1}", orangeCorpRequest.getValidationId1());

        this.logger = initLogger();

        logger.info("OrangeCorporate Amount Inquiry Url:- " + amountInquiryUrl);

        try {
            this.httpGet = initHttpGet(amountInquiryUrl, token);
            this.httpclient = initHttpClient();

            CloseableHttpResponse orangeCorporateValidateResponseHttp = executeGetRequest(httpclient, httpGet);

            String OrangeCorporateResponseJson = getJsonFromResponse(orangeCorporateValidateResponseHttp);

            logger.info("Orange Corporate Service response is : " + OrangeCorporateResponseJson);

            switch (getStatusCode(orangeCorporateValidateResponseHttp)) {
                case 200:
                    return json2Object(OrangeCorporateResponseJson, OrangeCorporateInquiryResponse.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(OrangeCorporateResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);
            }

        } catch (ConnectException ex) {
            logger.error("Error during calling Orange Corporate service on new system: " + ex, ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_CONNECTONERRORAr") : rb.getString("MESSAGE_OrangeCorporateBills_CONNECTONERROREn"));
        } catch (Exception ex) {
            logger.error("Error during calling Orange Corporate service on new system: " + ex, ex);
            throw new Exception(ex.getMessage());
        } finally {
            httpclient.close();
        }
    }

    public OrangeCorporatePaymentResponse orangeCorporatPayment(OrangeCorporateRequest orangeCorporateRequest, String lang, String token) throws Exception {

        String paymentUrl = loadUrlProperty("OrangeCorporate.payment.url");

        this.logger = initLogger();

        logger.info("orange Corporate Payment Url:- " + paymentUrl);

        try {
            this.httpclient = initHttpClient();

            String urlParameters = object2Json(orangeCorporateRequest, OrangeCorporateRequest.class);

            this.httpPost = initHttpPost(urlParameters, paymentUrl, token);

            logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

            CloseableHttpResponse orangeCorporatePaymentResponseHttp = executePostRequest(httpclient, httpPost);

            String orangeCorporatePaymentResponseJson = getJsonFromResponse(orangeCorporatePaymentResponseHttp);

            logger.info("orange Corporate Service response is : " + orangeCorporatePaymentResponseJson);

            switch (getStatusCode(orangeCorporatePaymentResponseHttp)) {
                case 200:
                    return json2Object(orangeCorporatePaymentResponseJson, OrangeCorporatePaymentResponse.class);

                default:
                    logger.info("Failed to charge vodafone dsl");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(orangeCorporatePaymentResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);
            }

        } catch (ConnectException ex) {
            logger.error("Error during calling Orange Corporate service on new system: " + ex, ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_CONNECTONERRORAr") : rb.getString("MESSAGE_OrangeCorporateBills_CONNECTONERROREn"));
        } catch (Exception ex) {
            logger.error("Error during calling Orange Corporate service on new system: " + ex, ex);
            throw new Exception(ex.getMessage());
        } finally {
            httpclient.close();
        }
    }

    public OrangeCorporatePaymentResponse orangeCorporateReprint(long ledgerTrxId, String lang, String token) throws Exception {

        String reprintUrl = loadUrlProperty("OrangeCorporate.reprint.url");

        reprintUrl = reprintUrl.replace("{ledgerTrxId}", String.valueOf(ledgerTrxId));

        this.logger = initLogger();

        logger.info("OrangeCorporate reprint Url:- " + reprintUrl);

        try {
            this.httpGet = initHttpGet(reprintUrl, token);
            this.httpclient = initHttpClient();

            CloseableHttpResponse orangeCorporateValidateResponseHttp = executeGetRequest(httpclient, httpGet);

            String OrangeCorporateResponseJson = getJsonFromResponse(orangeCorporateValidateResponseHttp);

            logger.info("Orange Corporate Service response is : " + OrangeCorporateResponseJson);

            switch (getStatusCode(orangeCorporateValidateResponseHttp)) {
                case 200:
                    return json2Object(OrangeCorporateResponseJson, OrangeCorporatePaymentResponse.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(OrangeCorporateResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);
            }
        } catch (ConnectException ex) {
            logger.error("Error during calling Orange Corporate service on new system: " + ex, ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_CONNECTONERRORAr") : rb.getString("MESSAGE_OrangeCorporateBills_CONNECTONERROREn"));
        } catch (Exception ex) {
            logger.error("Error during calling Orange Corporate service on new system: " + ex, ex);
            throw new Exception(ex.getMessage());
        } finally {
            httpclient.close();
        }
    }

    protected String getErrorMessage(String lang, String message) {
        String errorMessage = "";
        if (message.equals("6661")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_ContractDoesnotExist6661Ar") : rb.getString("MESSAGE_OrangeCorporateBills_ContractDoesnotExist6661En");
        } else if (message.equals("6662")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_DuplicatePayment6662Ar") : rb.getString("MESSAGE_OrangeCorporateBills_DuplicatePayment6662En");
        } else if (message.equals("6663")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_InsufficientBalance6663Ar") : rb.getString("MESSAGE_OrangeCorporateBills_InsufficientBalance6663En");
        } else if (message.equals("6664")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_IntegrationException6664Ar") : rb.getString("MESSAGE_OrangeCorporateBills_IntegrationException6664En");
        } else if (message.equals("6665")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_StatusCodeIsNotInTheMap6669Ar") : rb.getString("MESSAGE_OrangeCorporateBills_StatusCodeIsNotInTheMap6669En");
        } else if (message.equals("6666")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_TechnicalError6666Ar") : rb.getString("MESSAGE_OrangeCorporateBills_TechnicalError6666En");
        } else if (message.equals("6667")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_AccountIsnotCorporete6667Ar") : rb.getString("MESSAGE_OrangeCorporateBills_AccountIsnotCorporete6667En");
        } else if (message.equals("6668")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_Timeout6668Ar") : rb.getString("MESSAGE_OrangeCorporateBills_Timeout6668En");
        } else if (message.equals("6669")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_StatusCodeIsNotInTheMap6669Ar") : rb.getString("MESSAGE_OrangeCorporateBills_StatusCodeIsNotInTheMap6669En");
        } else if (message.equals("6670")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_ShortTimePaying6670Ar") : rb.getString("MESSAGE_OrangeCorporateBills_ShortTimePaying6670En");
        } else if (message.equals("10")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_BillPayedBefore10Ar") : rb.getString("MESSAGE_OrangeCorporateBills_BillPayedBefore10En");
        } else if (message.equals("2")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_NoInquiryForPayment2Ar") : rb.getString("MESSAGE_OrangeCorporateBills_NoInquiryForPayment2En");
        } else {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeCorporateBills_ERRORAr") : rb.getString("MESSAGE_OrangeCorporateBills_ERROREn");
        }
        return errorMessage += ("[E " + message + " ]");
    }

}
