/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.common.CommonCalls;
import com.masary.integration.dto.RealEstateInquiryRepresentation;
import com.masary.integration.dto.RealEstatePaymentRepresentation;
import com.masary.integration.dto.RealEstatePaymentRequest;
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
public class RealEstateClient extends CommonCalls {

    private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private Logger logger;
    private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");

    public RealEstateInquiryRepresentation inquiryCode(String accountNumber, String lang, String token) throws Exception {
        this.logger = initLogger();
        String inquiryUrl = loadUrlProperty("RealEstate.inquiry.url");
        inquiryUrl = prepareUrl(inquiryUrl, "{accountNumber}", accountNumber);
        logger.info("Real Estate inquiry Url:- " + inquiryUrl);

        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(inquiryUrl, token);

            CloseableHttpResponse inquiryResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info("Real Estate inquiry Http Response" + inquiryResponseHttp);

            String inquiryResponseJson = getJsonFromResponse(inquiryResponseHttp);

            logger.info("Real Estate  inquiry JSON" + inquiryResponseJson);
            switch (getStatusCode(inquiryResponseHttp)) {
                case 200:
                    return json2Object(inquiryResponseJson, RealEstateInquiryRepresentation.class);

                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(inquiryResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling Real Estate  service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling Real Estate  service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }

    }

    public RealEstatePaymentRepresentation payment(RealEstatePaymentRequest realEstatePaymentRequest, String lang, String token) throws Exception {
        this.logger = initLogger();

            String paymentUrl = loadUrlProperty("RealEstate.payment.url");

        logger.info("Real Estate payment Url:- " + paymentUrl);
        try {
            this.httpclient = initHttpClient();

            String urlParameters = object2Json(realEstatePaymentRequest, RealEstatePaymentRequest.class);
            logger.info("Real Estate payment url parameters :- " + urlParameters);

            this.httpPost = initHttpPost(urlParameters, paymentUrl, token);

            CloseableHttpResponse paymentResponseHttp = executePostRequest(httpclient, httpPost);
            logger.info("Real Estate payment Http Response" + paymentResponseHttp);

            String paymentResponseJson = getJsonFromResponse(paymentResponseHttp);
            logger.info("Real Estate payment JSON" + paymentResponseJson);

            switch (getStatusCode(paymentResponseHttp)) {
                case 200:
                    return json2Object(paymentResponseJson, RealEstatePaymentRepresentation.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(paymentResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling Real Estate payment service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling Real Estate  payment service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }

    }

    public RealEstatePaymentRepresentation reprint(long ledgerTrxId, String lang, String token) throws Exception {
        this.logger = initLogger();

        String reprintUrl = loadUrlProperty("RealEstate.reprint.url");
        reprintUrl = prepareUrl(reprintUrl, "{ledgerTrxId}", String.valueOf(ledgerTrxId));
        logger.info("Real Estate reprint Url:- " + reprintUrl);

        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(reprintUrl, token);
            CloseableHttpResponse reprintResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info("Real Estate  reprint Http Response" + reprintResponseHttp);

            String reprintResponseJson = getJsonFromResponse(reprintResponseHttp);

            logger.info("Real Estate  reprint JSON" + reprintResponseJson);

            switch (getStatusCode(reprintResponseHttp)) {
                case 200:
                    return json2Object(reprintResponseJson, RealEstatePaymentRepresentation.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(reprintResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling Real Estate  reprint service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling Real Estate   reprint service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

    private String getErrorMessage(String lang, String message) {

        String errorMessage = "";
        if (message.equals("252601")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_RealEstate_252601Ar") : rb.getString("MESSAGE_RealEstate_252601En");
        } else if (message.equals("252602")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_RealEstate_252602Ar") : rb.getString("MESSAGE_RealEstate_252602En");

        } else if (message.equals("252603")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_RealEstate_252603Ar") : rb.getString("MESSAGE_RealEstate_252603En");

        } else if (message.equals("252604")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_RealEstate_252604Ar") : rb.getString("MESSAGE_RealEstate_252604En");

        } else if (message.equals("252605")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_RealEstate_252605Ar") : rb.getString("MESSAGE_RealEstate_252605En");

        } else if (message.equals("252606")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_RealEstate_252606Ar") : rb.getString("MESSAGE_RealEstate_252606En");

        } else if (message.equals("252607")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_RealEstate_252607Ar") : rb.getString("MESSAGE_RealEstate_252607En");

        } else if (message.equals("252608")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_RealEstate_252608Ar") : rb.getString("MESSAGE_RealEstate_252608En");

        } else if (message.equals("252609")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_RealEstate_252609Ar") : rb.getString("MESSAGE_RealEstate_252608En");

        } else if (message.equals("252610")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_RealEstate_252610Ar") : rb.getString("MESSAGE_RealEstate_252610En");

        } else if (message.equals("252611")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_RealEstate_252611Ar") : rb.getString("MESSAGE_RealEstate_252611En");

        } else {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_RealEstate_252601Ar") : rb.getString("MESSAGE_RealEstate_252601En");
        }
        return errorMessage;
    }

    RealEstatePaymentRepresentation payment(RealEstatePaymentRequest acceptPaymentRequest, String ar, String oDoxMjM0NTY3ODk, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
