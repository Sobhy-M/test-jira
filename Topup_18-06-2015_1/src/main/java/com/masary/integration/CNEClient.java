package com.masary.integration;

import com.masary.common.CONFIG;
import com.masary.common.CommonCalls;

import com.masary.integration.dto.CNEPaymentRequestDTO;
import com.masary.integration.dto.CNEPaymentResponseDTO;
import com.masary.integration.dto.CNERequest;
import com.masary.integration.dto.CNEResponse;

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
public class CNEClient extends CommonCalls {

    private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private Logger logger;
    private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");

    public CNEResponse inquiry(CNERequest paymentRequest, String lang, String token) throws Exception {
        this.logger = initLogger();

        String inquiryUrl = loadUrlProperty("CNE.inquiry.url");
        inquiryUrl = prepareUrl(inquiryUrl, paymentRequest);

        logger.info("  Inquiry Url:- " + inquiryUrl);

        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(inquiryUrl, token);     
           
            String urlParameters = object2Json(paymentRequest, CNERequest.class);
            logger.info("  Inquiry url parameters :- " + urlParameters);

            CloseableHttpResponse paymentResponseHttp = executeGetRequest(this.httpclient, this.httpGet);
            logger.info("CNEInquiry Http Response" + paymentResponseHttp);

            String paymentResponseJson = getJsonFromResponse(paymentResponseHttp);
            logger.info("CNEInquiry JSON" + paymentResponseJson);

            switch (getStatusCode(paymentResponseHttp)) {
                case 200:
                    return json2Object(paymentResponseJson, CNEResponse.class);
                default:
                    logger.info("Failed to get denominations ");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(paymentResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling  CNEInquiry on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling CNEInquiry on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

    public CNEPaymentResponseDTO payment(CNEPaymentRequestDTO cnePaymentRequestDTO, String lang, String token) throws Exception {

        String beINSportsUrl = loadUrlProperty("CNE.payment.url");

        this.logger = initLogger();

        logger.info("beINSports Payment Url:- " + beINSportsUrl + cnePaymentRequestDTO);

        try {
            this.httpclient = initHttpClient();

            String urlParameters = object2Json(cnePaymentRequestDTO, CNEPaymentRequestDTO.class);

           logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

            HttpPost httpPost = initHttpPost(urlParameters, beINSportsUrl, token);

            CloseableHttpResponse cnePaymentResponseHttp = executePostRequest(httpclient, httpPost);

            String cnePaymentResponseJson = getJsonFromResponse(cnePaymentResponseHttp);
            logger.info("beINSports Service response is : " + cnePaymentResponseJson);

            switch (getStatusCode(cnePaymentResponseHttp)) {
                case 200:
                    return json2Object(cnePaymentResponseJson, CNEPaymentResponseDTO.class);
                case 401:
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
                default:
                    logger.info("Failed to get commission");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(cnePaymentResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);
            }

        } catch (Exception e) {
            logger.error("Exception" + e.getMessage());
            logger.error(e);
            throw (e);
        } finally {
            httpclient.close();
        }

    }

    public CNEPaymentResponseDTO reprint(long ledgerTrxId, String lang, String token) throws Exception {

        this.logger = initLogger();

        logger.info(token);

        String rePrintUrl = loadUrlProperty("CNE.reprint.url");
        
         rePrintUrl = prepareUrl(rePrintUrl, "{ledgerTrxId}", String.valueOf(ledgerTrxId));
        this.httpclient = initHttpClient();

        try {
            this.httpGet = initHttpGet(rePrintUrl, token);

            CloseableHttpResponse response = executeGetRequest(this.httpclient, this.httpGet);

            String json = getJsonFromResponse(response);

            switch (getStatusCode(response)) {
                case 200:
                    return json2Object(json, CNEPaymentResponseDTO.class);
                case 401:
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
                default:
                    logger.info("Failed to get commission");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(json, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);
            }

        } catch (Exception e) {
            logger.error("Exception" + e.getMessage());
            logger.error(e);
            throw (e);
        } finally {
            httpclient.close();
        }
    }

    protected String getErrorMessage(String lang, String message) {
        String errorMessage = "";
        if (message.equals("2121")) {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsAr : CONFIG.errorTransactionBeinSportsEn);
        }
        if (message.equals("2123")) {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE2Ar : CONFIG.errorTransactionBeinSportsE2En);
        }
        if (message.equals("2124")) {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        }
        if (message.equals("2125")) {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        }
        if (message.equals("2126")) {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        }
        if (message.equals("2127")) {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE2Ar : CONFIG.errorTransactionBeinSportsE2En);
        }
        if (message.equals("2128")) {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE4Ar : CONFIG.errorTransactionBeinSportsE4En);
        }
        if (message.equals("2129")) {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        }
        if (message.equals("21210")) {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        }
        if (message.equals("21211")) {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        }
        if (message.equals("21212")) {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        } else {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
        }
        return errorMessage += ("[E" + message + "]");
    }

    private String prepareUrl(String inquiryUrl, CNERequest paymentRequest) {
    	//TODO, update property name in properties file {subscriberNumber}
        inquiryUrl = inquiryUrl.replace("{subscriberNumber}", String.valueOf(paymentRequest.getSubscriberNumber()));
        inquiryUrl = inquiryUrl.replace("{mobileNumber}", paymentRequest.getMobileNumber());

        return inquiryUrl;
    }

   

  
}
