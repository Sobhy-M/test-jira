/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.common.CommonCalls;
import com.masary.integration.dto.CommissionTransaction;
import com.masary.integration.dto.PaymentRequest;
import com.masary.integration.dto.Representation;
import com.masary.integration.dto.DonationType;
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
public class EgyptEyesClient extends CommonCalls {

    private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private Logger logger;
    private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");

    public CommissionTransaction ratePlanCommissions(String serviceId, String amount, String lang, String token) throws ConnectException, Exception {

        this.logger = initLogger();

        String ratePlanUrl = loadUrlProperty("commissions.rateplan.url");
        ratePlanUrl = prepareUrl(ratePlanUrl, "{serviceId}", serviceId, "{amount}", amount);

        logger.info("ratePlanCommissions Url:- " + ratePlanUrl);

        try {
            this.httpclient = initHttpClient();

            this.httpGet = initHttpGet(ratePlanUrl, token);
            logger.info("ratePlanCommissionsurl parameters :- " + ratePlanUrl);

            CloseableHttpResponse paymentResponseHttp = executeGetRequest(httpclient, httpGet);
            logger.info("ratePlanCommissions Http Response" + paymentResponseHttp);

            String paymentResponseJson = getJsonFromResponse(paymentResponseHttp);
            logger.info("ratePlanCommissions JSON" + paymentResponseJson);

            switch (getStatusCode(paymentResponseHttp)) {
                case 200:
                    return json2Object(paymentResponseJson, CommissionTransaction.class);
                default:
                    logger.info("Failed to get denominations ");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(paymentResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling   Partial Inquiry on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling  Partial Inquiry on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

    public Representation payment(PaymentRequest paymentRequest, String lang, String token) throws Exception {
        this.logger = initLogger();

        String paymentUrl = loadUrlProperty("egyptEyes.payment.url");

        logger.info(" payment Url:- " + paymentUrl);

        try {
            this.httpclient = initHttpClient();

            String urlParameters = object2Json(paymentRequest, PaymentRequest.class);
            logger.info("tamweel payment url parameters :- " + urlParameters);

            this.httpPost = initHttpPost(urlParameters, paymentUrl, token);

            CloseableHttpResponse paymentResponseHttp = executePostRequest(httpclient, httpPost);
            logger.info(" payment Http Response" + paymentResponseHttp);

            String paymentResponseJson = getJsonFromResponse(paymentResponseHttp);
            logger.info(" payment JSON" + paymentResponseJson);

            switch (getStatusCode(paymentResponseHttp)) {
                case 200:
                    return json2Object(paymentResponseJson, Representation.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(paymentResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling  payment service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling   payment service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }

    }

    public Representation reprint(long ledgerTrxId, String lang, String token) throws Exception {
        this.logger = initLogger();

        String reprintUrl = loadUrlProperty("egyptEyes.reprint.url");
        reprintUrl = prepareUrl(reprintUrl, "{ledgerTrxId}", String.valueOf(ledgerTrxId));
        logger.info(" reprint Url:- " + reprintUrl);

        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(reprintUrl, token);

            CloseableHttpResponse reprintResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info(" reprint Http Response" + reprintResponseHttp);

            String reprintResponseJson = getJsonFromResponse(reprintResponseHttp);

            logger.info(" reprint JSON" + reprintResponseJson);

            switch (getStatusCode(reprintResponseHttp)) {
                case 200:
                    return json2Object(reprintResponseJson, Representation.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(reprintResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling  reprint service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling   reprint service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

    public DonationType types(String lang, String token) throws Exception {
        this.logger = initLogger();

        String reprintUrl = loadUrlProperty("egyptEyes.types.url");

        logger.info(" reprint Url:- " + reprintUrl);

        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(reprintUrl, token);

            CloseableHttpResponse reprintResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info(" reprint Http Response" + reprintResponseHttp);

            String reprintResponseJson = getJsonFromResponse(reprintResponseHttp);

            logger.info(" reprint JSON" + reprintResponseJson);

            switch (getStatusCode(reprintResponseHttp)) {
                case 200:
                    return json2Object(reprintResponseJson, DonationType.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(reprintResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling  reprint service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling   reprint service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

    private String getErrorMessage(String lang, String message) {
        String errorMessage = "";
        if (message.equals("21")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Donation_21") : rb.getString("MESSAGE_Donation_21");
        } 
       
       else if (message.equals("14")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Donation_14") : rb.getString("MESSAGE_Donation_14");
        } 
       else if (message.equals("911")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Donation_911") : rb.getString("MESSAGE_Donation_911");
        } 
        else {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_DonationAr") : rb.getString("MESSAGE__201004Ar");
        }
        return errorMessage += ("[ E " + message + " ]");
    }

}
