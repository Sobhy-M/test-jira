package com.masary.integration;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import com.masary.common.CONFIG;
import com.masary.common.CommonCalls;
import com.masary.integration.dto.ChangePointsDTO;
import com.masary.integration.dto.LoyaltyPointSaveRequest;
import com.masary.integration.dto.LoyaltyPointsSaveResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import java.net.ConnectException;

public class LoyalityPointsClient extends CommonCalls {

    private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private Logger logger;

    public ChangePointsDTO loyalityPointsValidate(String lang, String token) throws Exception {

        String loyalityPointsValidateUrl = loadUrlProperty("LoyalityPoints.validate.url");

        loyalityPointsValidateUrl = prepareUrl(loyalityPointsValidateUrl);

        this.logger = initLogger();

        logger.info("loyality Points Validate Url:- " + loyalityPointsValidateUrl);

        try {
            this.httpclient = initHttpClient();

            this.httpGet = initHttpGet(loyalityPointsValidateUrl, token);

            logger.info("loyalityPointsValidateUrl:- " + loyalityPointsValidateUrl);

            CloseableHttpResponse loyalityValidateResponseHttp = executeGetRequest(this.httpclient, this.httpGet);

            String loyalityValidateResponseJson = getJsonFromResponse(loyalityValidateResponseHttp);

            logger.info("loyalityPointsValidate Service response is : " + loyalityValidateResponseJson);

            switch (getStatusCode(loyalityValidateResponseHttp)) {
                case 200:
                    return json2Object(loyalityValidateResponseJson, ChangePointsDTO.class);
                case 401:
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
                default:
                    logger.info("Failed to get commission");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(loyalityValidateResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);
            }

        } catch (ConnectException ex) {
            logger.error("Error during calling zaidElKhier service on new system: " + ex, ex);
            throw new ConnectException(lang.equals("ar") ? "خطا اثناء الاتصال بالشبكة الرجاء المحاولة لاحقا" : "Connection error please check later");
        } catch (Exception e) {
            logger.error("Exception" + e.getMessage());
            logger.error(e);
            throw (e);
        } finally {
            httpclient.close();
        }

    }

    public LoyaltyPointsSaveResponse loyaltyPointsSavePayment(LoyaltyPointSaveRequest loyaltyPointSaveRequest, String lang, String token)
            throws Exception {

        String loyaltyPointSaveRequestUrl = loadUrlProperty("LoyaltyPoint.SaveRequest.url");
        this.logger = initLogger();
        logger.info("Loyalty Point Save Request Url:- " + loyaltyPointSaveRequestUrl);

        try {
            this.httpclient = initHttpClient();
            logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

            String urlParameters = object2Json(loyaltyPointSaveRequest, LoyaltyPointSaveRequest.class);
            HttpPost httpPost = initHttpPost(urlParameters, loyaltyPointSaveRequestUrl, token);

            CloseableHttpResponse loyaltyPointSaveResponseHttp = executePostRequest(httpclient, httpPost);

            String loyaltyPointSaveResponseJson = getJsonFromResponse(loyaltyPointSaveResponseHttp);
            logger.info("LoyaltyPoints Service response is : " + loyaltyPointSaveResponseJson);

            switch (getStatusCode(loyaltyPointSaveResponseHttp)) {
                case 200:
                    return json2Object(loyaltyPointSaveResponseJson, LoyaltyPointsSaveResponse.class);
                default:
                    logger.info("Failed to get commission");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(loyaltyPointSaveResponseJson,
                            StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);
            }
        } catch (ConnectException ex) {
            logger.error("Error during calling zaidElKhier service on new system: " + ex, ex);
            throw new ConnectException(lang.equals("ar") ? "خطا اثناء الاتصال بالشبكة الرجاء المحاولة لاحقا" : "Connection error please check later");
        } catch (Exception e) {
            logger.error("Exception" + e.getMessage());
            logger.error(e);
            throw (e);
        } finally {
            httpclient.close();
        }

    }

    protected String prepareUrl(String loyalityPointsValidateUrl) {
        return loyalityPointsValidateUrl;
    }

    protected String getErrorMessage(String lang, String message) {
        String errorMessage = "";
        
        if (message.equals("330103")) 
        {
            errorMessage = (lang.equals("ar") ? CONFIG.errorChangePointsE1Ar : CONFIG.errorChangePointsE1EN);
        }
        else if (message.equals("330102")) 
        {
            errorMessage = (lang.equals("ar") ? CONFIG.errorChangePointsE2Ar : CONFIG.errorChangePointsE2EN);
        }
        else if (message.equals("330101")) {
            errorMessage = (lang.equals("ar") ? CONFIG.errorChangePointsE3Ar : CONFIG.errorChangePointsE3EN);
        } 
        else 
        {
            errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
        }
        
        return errorMessage += ("[E" + message + "]");
    }
}
