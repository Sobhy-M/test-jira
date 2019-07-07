/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.common.CommonCalls;
import com.masary.integration.dto.OrangeGiftsInquiryRepresentation;
import com.masary.integration.dto.OrangeGiftsPaymentRepresentation;
import com.masary.integration.dto.OrangeGiftsRequest;
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
public class OrangeGiftsClient extends CommonCalls {

    private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private Logger logger;
    private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");

    public OrangeGiftsInquiryRepresentation verfiVoucher(String voucherId, String lang, String token) throws Exception {
        this.logger = initLogger();

        String inquiryUrl = loadUrlProperty("OrangeGifts.inquiry.url");
        inquiryUrl = prepareUrl(inquiryUrl, "{voucher}", voucherId);

        logger.info("Orange Gifts inquiry Url:- " + inquiryUrl);

        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(inquiryUrl, token);

            CloseableHttpResponse inquiryResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info("Orange Gifts inquiry Http Response" + inquiryResponseHttp);

            String inquiryResponseJson = getJsonFromResponse(inquiryResponseHttp);

            logger.info("Orange Gifts inquiry JSON" + inquiryResponseJson);

            switch (getStatusCode(inquiryResponseHttp)) {
                case 200:
                    return json2Object(inquiryResponseJson, OrangeGiftsInquiryRepresentation.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(inquiryResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling orange gifts service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling orange gifts service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

    public OrangeGiftsPaymentRepresentation voucherRedemption(OrangeGiftsRequest orangeGiftsRequest, String lang, String token) throws Exception {
        this.logger = initLogger();

        String paymentUrl = loadUrlProperty("OrangeGifts.payment.url");
        logger.info("Orange Gifts payment Url:- " + paymentUrl);

        try {
            this.httpclient = initHttpClient();

            String urlParameters = object2Json(orangeGiftsRequest, OrangeGiftsRequest.class);
            logger.info("Orange Gifts url parameters :- " + urlParameters);

            this.httpPost = initHttpPost(urlParameters, paymentUrl, token);

            CloseableHttpResponse paymentResponseHttp = executePostRequest(httpclient, httpPost);
            logger.info("Orange Gifts payment Http Response" + paymentResponseHttp);

            String paymentResponseJson = getJsonFromResponse(paymentResponseHttp);
            logger.info("Orange Gifts payment JSON" + paymentResponseJson);

            switch (getStatusCode(paymentResponseHttp)) {
                case 200:
                    return json2Object(paymentResponseJson, OrangeGiftsPaymentRepresentation.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(paymentResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling orange gifts service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling orange gifts service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

    public OrangeGiftsPaymentRepresentation reprint(long ledgerTrxId, String lang, String token) throws Exception {
        this.logger = initLogger();

        String reprintUrl = loadUrlProperty("OrangeGifts.reprint.url");
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
                    return json2Object(reprintResponseJson, OrangeGiftsPaymentRepresentation.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(reprintResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling orange gifts service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling orange gifts service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

    private String getErrorMessage(String lang, String message) {
        String errorMessage = "";
        if (message.equals("667001")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeGifts_667001Ar") : rb.getString("MESSAGE_OrangeGifts_667001En");
        } else if (message.equals("667002")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeGifts_667002Ar") : rb.getString("MESSAGE_OrangeGifts_667002En");
        } else if (message.equals("667008") || message.equals("667023")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeGifts_667008_667023Ar") : rb.getString("MESSAGE_OrangeGifts_667008_667023En");
        } else if (message.equals("667009")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeGifts_667009Ar") : rb.getString("MESSAGE_OrangeGifts_667009En");
        } else if (message.equals("667014")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeGifts_667014Ar") : rb.getString("MESSAGE_OrangeGifts_667014En");
        } else if (message.equals("667019")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeGifts_667019Ar") : rb.getString("MESSAGE_OrangeGifts_667019En");
        } else if (message.equals("667020") || message.equals("667024")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeGifts_667020_667024Ar") : rb.getString("MESSAGE_OrangeGifts_667020_667024En");
        } else if (message.equals("667021") || message.equals("667022")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeGifts_667021_667022Ar") : rb.getString("MESSAGE_OrangeGifts_667021_667022En");
        } else if (message.equals("667026")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeGifts_667026Ar") : rb.getString("MESSAGE_OrangeGifts_667026En");
        } else if (message.equals("667027") || message.equals("667028")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_OrangeGifts_667027_667028Ar") : rb.getString("MESSAGE_OrangeGifts_667027_667028En");
        } else {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Commons_ERRORAr") : rb.getString("MESSAGE_Commons_ERROREn");
        }
        return errorMessage += ("[ E " + message + " ]");
    }
}
