package com.masary.integration;

import com.masary.common.CommonCalls;
import com.masary.integration.dto.KafrElShiekhWaterBillsSuggetionsResponse;
import com.masary.integration.dto.KafrElShiekhWaterInquiryResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.integration.dto.KafrElShiekhWaterPaymentResponse;
import com.masary.integration.dto.KafrElShiekhWaterPaymentRequest;
import java.net.ConnectException;
import java.util.ResourceBundle;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;

public class KafrElShiekhWaterClient extends CommonCalls {

    private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private Logger logger;
    private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");

    public KafrElShiekhWaterInquiryResponse billsInquiry(String subscriptionNumber, String mobileNumber, String lang, String token) throws Exception {
        this.logger = initLogger();
        String inquiryUrl = loadUrlProperty("KafrElShiekhWater.inquiry.url");
        inquiryUrl = prepareUrl(inquiryUrl, "{subscriptionNumber}", subscriptionNumber);
        inquiryUrl = prepareUrl(inquiryUrl, "{mobileNumber}", mobileNumber);

        logger.info("Kafr ElShiekh Water inquiry Url:- " + inquiryUrl);
        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(inquiryUrl, token);

            CloseableHttpResponse inquiryResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info("Kafr ElShiekh Water inquiry Http Response" + inquiryResponseHttp);

            String inquiryResponseJson = getJsonFromResponse(inquiryResponseHttp);

            logger.info("Kafr ElShiekh Water inquiry JSON" + inquiryResponseJson);

            switch (getStatusCode(inquiryResponseHttp)) {
                case 200:
                    return json2Object(inquiryResponseJson, KafrElShiekhWaterInquiryResponse.class);

                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(inquiryResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling KafrElShiekhWater service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling KafrElShiekhWater service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }

    }

    public KafrElShiekhWaterBillsSuggetionsResponse billsSuggestions(String validationId, double amount, String lang, String token) throws Exception {
        this.logger = initLogger();
        String suggestionsUrl = loadUrlProperty("KafrElShiekhWater.suggestions.url");
        suggestionsUrl = prepareUrl(suggestionsUrl, "{validationId}", validationId);
        suggestionsUrl = prepareUrl(suggestionsUrl, "{amount}", String.valueOf(amount));

        logger.info("Kafr ElShiekh Water suggestions Url:- " + suggestionsUrl);
        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(suggestionsUrl, token);

            CloseableHttpResponse inquiryResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info("Kafr ElShiekh Water suggestions Http Response" + inquiryResponseHttp);

            String inquiryResponseJson = getJsonFromResponse(inquiryResponseHttp);

            logger.info("Kafr ElShiekh Water suggestions JSON" + inquiryResponseJson);

            switch (getStatusCode(inquiryResponseHttp)) {
                case 200:
                    return json2Object(inquiryResponseJson, KafrElShiekhWaterBillsSuggetionsResponse.class);

                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(inquiryResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling KafrElShiekhWater service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling KafrElShiekhWater service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }

    }

    public KafrElShiekhWaterInquiryResponse commissionInquiry(String validationId, double amount, String lang, String token) throws Exception {
        this.logger = initLogger();
        String commissionUrl = loadUrlProperty("KafrElShiekhWater.commission.url");
        commissionUrl = prepareUrl(commissionUrl, "{validationId}", validationId);
        commissionUrl = prepareUrl(commissionUrl, "{amount}", String.valueOf(amount));

        logger.info("Kafr ElShiekh Water commission Url:- " + commissionUrl);
        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(commissionUrl, token);

            CloseableHttpResponse inquiryResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info("Kafr ElShiekh Water commission Http Response" + inquiryResponseHttp);

            String inquiryResponseJson = getJsonFromResponse(inquiryResponseHttp);

            logger.info("Kafr ElShiekh Water commission JSON" + inquiryResponseJson);

            switch (getStatusCode(inquiryResponseHttp)) {
                case 200:
                    return json2Object(inquiryResponseJson, KafrElShiekhWaterInquiryResponse.class);

                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(inquiryResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling KafrElShiekhWater service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling KafrElShiekhWater service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }

    }

    public KafrElShiekhWaterPaymentResponse payment(KafrElShiekhWaterPaymentRequest kafrElShiekhWaterPaymentRequest, String lang, String token) throws Exception {
        this.logger = initLogger();

        String paymentUrl = loadUrlProperty("KafrElShiekhWater.payment.url");

        logger.info("Kafr ElShiekh Water payment Url:- " + paymentUrl);

        try {
            this.httpclient = initHttpClient();

            String urlParameters = object2Json(kafrElShiekhWaterPaymentRequest, KafrElShiekhWaterPaymentRequest.class);
            logger.info("Kafr ElShiekh Water payment url parameters :- " + urlParameters);

            this.httpPost = initHttpPost(urlParameters, paymentUrl, token);

            CloseableHttpResponse paymentResponseHttp = executePostRequest(httpclient, httpPost);
            logger.info("Kafr ElShiekh Water payment Http Response" + paymentResponseHttp);

            String paymentResponseJson = getJsonFromResponse(paymentResponseHttp);
            logger.info("Kafr ElShiekh Water payment JSON" + paymentResponseJson);

            switch (getStatusCode(paymentResponseHttp)) {
                case 200:
                    return json2Object(paymentResponseJson, KafrElShiekhWaterPaymentResponse.class);
                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(paymentResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling KafrElShiekhWater payment service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling KafrElShiekhWater  payment service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

    public KafrElShiekhWaterPaymentResponse reprint(long ledgerTrxId, String lang, String token) throws Exception {
        this.logger = initLogger();
        String reprintUrl = loadUrlProperty("KafrElShiekhWater.reprint.url");
        reprintUrl = prepareUrl(reprintUrl, "{ledgerTrxId}", String.valueOf(ledgerTrxId));

        logger.info("Kafr ElShiekh Water commission Url:- " + reprintUrl);
        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(reprintUrl, token);

            CloseableHttpResponse inquiryResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info("Kafr ElShiekh Water reprint Http Response" + inquiryResponseHttp);

            String inquiryResponseJson = getJsonFromResponse(inquiryResponseHttp);

            logger.info("Kafr ElShiekh Water reprint JSON" + inquiryResponseJson);

            switch (getStatusCode(inquiryResponseHttp)) {
                case 200:
                    return json2Object(inquiryResponseJson, KafrElShiekhWaterPaymentResponse.class);

                default:
                    logger.info("Failed to get denominations");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(inquiryResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling KafrElShiekhWater service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("MESSAGE_Commons_CONNECTONERRORAr") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling KafrElShiekhWater service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }

    }

    private String getErrorMessage(String lang, String message) {
        String errorMessage = "";
        if (message.equals("66001")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_KafrElShiekhWater_66001Ar") : rb.getString("MESSAGE_KafrElShiekhWater_66001En");
        } else if (message.equals("66002")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_KafrElShiekhWater_66002Ar") : rb.getString("MESSAGE_KafrElShiekhWater_66002En");
        } else if (message.equals("66003")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_KafrElShiekhWater_66003Ar") : rb.getString("MESSAGE_KafrElShiekhWater_66003En");
        } else if (message.equals("66006")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_KafrElShiekhWater_66006Ar") : rb.getString("MESSAGE_KafrElShiekhWater_66006En");
        } else if (message.equals("66007")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_KafrElShiekhWater_66007Ar") : rb.getString("MESSAGE_KafrElShiekhWater_66007En");
        } else if (message.equals("66008")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_KafrElShiekhWater_66008Ar") : rb.getString("MESSAGE_KafrElShiekhWater_66008En");
        }else if (message.equals("66011")) {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_KafrElShiekhWater_66011Ar") : rb.getString("MESSAGE_KafrElShiekhWater_66011En");
        } else {
            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Commons_ERRORAr") : rb.getString("MESSAGE_Commons_ERROREn");
        }
        return errorMessage += ("[ E " + message + " ]");
    }
}
