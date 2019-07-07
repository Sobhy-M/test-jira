/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.masary.common.CONFIG;
import com.masary.common.CommonCalls;
import com.masary.database.manager.MasaryManager;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import com.masary.integration.dto.ValuPaymentRequest;
import com.masary.integration.dto.ValuPaymentResponse;
import com.masary.integration.dto.ValuInquiryResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;
import java.io.IOException;
import java.net.ConnectException;
import java.util.ResourceBundle;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author amira
 */
public class ValuClient extends CommonCalls {
    
    
    private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private Logger logger;
    private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");

    public ValuInquiryResponse valuValidateInquiry(String billReference, String lang, String token) throws Exception {

        String valuUrl = SystemSettingsUtil.getInstance().loadProperty("Valu.inquiry.url");

        valuUrl = valuUrl.replace("{billReference}", billReference);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        MasaryManager.logger.info("valu Inquiry Url:- " + valuUrl);
        ValuInquiryResponse valuInquiryResponse = new ValuInquiryResponse();

        try {
            HttpGet httpGet = new HttpGet(valuUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");

            MasaryManager.logger.info("valuUrl:- " + valuUrl);

            CloseableHttpResponse valuValidateResponseHttp = httpclient.execute(httpGet);

            String ValuValidateResponseJson = EntityUtils.toString(valuValidateResponseHttp.getEntity(), "UTF-8");

            MasaryManager.logger.info("Valu Service response is : " + ValuValidateResponseJson);

            Gson gson = new Gson();

            if (valuValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
                valuInquiryResponse = gson.fromJson(ValuValidateResponseJson, ValuInquiryResponse.class);
            } else {
                MasaryManager.logger.info("Failed to get commission");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(ValuValidateResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getMessage().equals("321")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("322")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionValuar3 : CONFIG.errorTransactionValuar3);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("323")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("324")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("325")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("326")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionValuar2 : CONFIG.errorTransactionValuar2);
                } else if (standardHttpJsonResponseDTO.getMessage().equals("327")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionValuar4 : CONFIG.errorTransactionValuar4);
                } else {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
                }

            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling Valu service on new system: " + ex, ex);
            throw new ConnectException(lang.equals("ar") ? CONFIG.errorValuConnectionRefusedAr : CONFIG.errorValuConnectionRefusedEn);
        } catch (IOException ex) {
            MasaryManager.logger.error("Error during calling Valu service on new system: " + ex, ex);
            throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
        } catch (ParseException ex) {
            MasaryManager.logger.error("Error during calling Valu service on new system: " + ex, ex);
            throw new ParseException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
        } catch (JsonSyntaxException ex) {
            MasaryManager.logger.error("Error during calling Valu service on new system: " + ex, ex);
            throw new JsonSyntaxException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
        } finally {
            httpclient.close();
        }
        return valuInquiryResponse;
    }

    public ValuPaymentResponse valuPayment(ValuPaymentRequest inquiryReferenceId, String lang, String token, String uuid) throws Exception {

        String valuUrl = SystemSettingsUtil.getInstance().loadProperty("Valu.payment.url");
        //error
        MasaryManager.logger.info("valu Payment Url:- " + valuUrl + inquiryReferenceId);

        CloseableHttpClient httpclient = HttpClients.createDefault();

        ValuPaymentResponse valuPaymentResponse = new ValuPaymentResponse();

        try {
            HttpPost httpPost = new HttpPost(valuUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "Web");
            httpPost.addHeader("extTrxId", uuid);
//            httpPost.addHeader("ip", ip);
            MasaryManager.logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");
            MasaryManager.logger.info("TE Data service inquiry request uuid: " + uuid);

            Gson gson = new Gson();
            String urlParameters = gson.toJson(inquiryReferenceId, ValuPaymentRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);

            CloseableHttpResponse valuPaymentResponseHttp = httpclient.execute(httpPost);

            String valuPaymentResponseJson = EntityUtils.toString(valuPaymentResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("valu Service response is : " + valuPaymentResponseJson);

            if (valuPaymentResponseHttp.getStatusLine().getStatusCode() == 200) {
                valuPaymentResponse = gson.fromJson(valuPaymentResponseJson, ValuPaymentResponse.class);
                MasaryManager.logger.info("valu Payment Response:- " + valuPaymentResponse);
            } else {
                MasaryManager.logger.error("Failed to pay the sent amount");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(valuPaymentResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("5")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("12")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("15")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("400") && standardHttpJsonResponseDTO.getMessage().equals("200417")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("404") && standardHttpJsonResponseDTO.getMessage().equals("6122")) {
                    throw new IOException(lang.equals("ar") ? CONFIG.EtisalNotFoundNumber : CONFIG.EtisalNotFoundNumber);
                } else {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                }
            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling Valu service on new system: " + ex, ex);
            throw new ConnectException(lang.equals("ar") ? CONFIG.errorValuConnectionRefusedAr : CONFIG.errorValuConnectionRefusedEn);
        } catch (IOException ex) {
            MasaryManager.logger.error("Error during calling Valu service on new system: " + ex, ex);
            throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
        } catch (ParseException ex) {
            MasaryManager.logger.error("Error during calling Valu service on new system: " + ex, ex);
            throw new ParseException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
        } catch (JsonSyntaxException ex) {
            MasaryManager.logger.error("Error during calling Valu service on new system: " + ex, ex);
            throw new JsonSyntaxException(lang.equals("ar") ? CONFIG.DuplicatedTransactionAr : CONFIG.DuplicatedTransactionEn);
        } finally {
            httpclient.close();
        }
        return valuPaymentResponse;
    }
    
    
    
       public ValuPaymentResponse reprint(long ledgerTrxId, String lang, String token) throws Exception {
        this.logger = initLogger();

        String reprintUrl = loadUrlProperty("Valu.reprint.url");
        reprintUrl = prepareUrl(reprintUrl, "{ledgerTrxId}", String.valueOf(ledgerTrxId));
        logger.info("Valu reprint Url:- " + reprintUrl);

        try {
            this.httpclient = initHttpClient();
            this.httpGet = initHttpGet(reprintUrl, token);

            CloseableHttpResponse reprintResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info("Valu reprint Http Response" + reprintResponseHttp);

            String reprintResponseJson = getJsonFromResponse(reprintResponseHttp);

            logger.info("Valu reprint JSON" + reprintResponseJson);

            switch (getStatusCode(reprintResponseHttp)) {
                case 200:
                    return json2Object(reprintResponseJson, ValuPaymentResponse.class);
                default:
                    logger.info("Failed Reprint Valu Response");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(reprintResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = standardHttpJsonResponseDTO.getMessage();
                    throw new Exception(errorMessage);

            }
        } catch (ConnectException ex) {
            logger.error("Error during calling Valu reprint service on new system: ", ex);
            throw new ConnectException(lang.equals("ar") ? rb.getString("errorTransactionValuar5") : rb.getString("MESSAGE_Commons_CONNECTONERROREn"));
        } catch (Exception e) {
            logger.error("Error during calling Valu  reprint service on new system: ", e);
            throw e;
        } finally {
            httpclient.close();
        }
    }

//    private String getErrorMessage(String lang, String message) {
//        String errorMessage = "";
//        if (message.equals("201001")) {
//            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201001Ar") : rb.getString("MESSAGE_Tamweel_201001En");
//        } else if (message.equals("201002")) {
//            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201002Ar") : rb.getString("MESSAGE_Tamweel_201002En");
//        } else if (message.equals("201003")) {
//            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201003Ar") : rb.getString("MESSAGE_Tamweel_201003En");
//        } else if (message.equals("201004")) {
//            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201004Ar") : rb.getString("MESSAGE_Tamweel_201004En");
//        } else if (message.equals("201005")) {
//            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201004Ar") : rb.getString("MESSAGE_Tamweel_201004Ar");
//        } else if (message.equals("201006")) {
//            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201004Ar") : rb.getString("MESSAGE_Tamweel_201004Ar");
//        } else if (message.equals("MESSAGE_Tamweel_14Ar")) {
//            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_14Ar") : rb.getString("MESSAGE_Tamweel_14En");
//        } 
//        else {
//            errorMessage = lang.equals("ar") ? rb.getString("MESSAGE_Tamweel_201004Ar") : rb.getString("MESSAGE_Tamweel_201004Ar");
//        }
//        return errorMessage += ("[ E " + message + " ]");
//    }
    
  
}
