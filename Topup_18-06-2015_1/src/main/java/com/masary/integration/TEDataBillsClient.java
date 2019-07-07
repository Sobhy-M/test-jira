/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.common.CONFIG;
import static com.masary.common.CONFIG.lang;
import com.masary.common.CommonCalls;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.integration.dto.TEDataBillsRequest;
import com.masary.integration.dto.TEDataBillsResponse;
import com.masary.integration.dto.TeDataActionDestinationResponse;
import com.masary.integration.dto.TeDataBssRequest;
import com.masary.utils.SystemSettingsUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Tasneem
 */
public class TEDataBillsClient extends CommonCalls {

    private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private Logger logger;

    public TEDataBillsResponse TEInquiry(TEDataBillsRequest teRequest, String token, String uuid) throws Exception {

        String teDataUrl = SystemSettingsUtil.getInstance().loadProperty("TEData.Inquiry.url");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        TEDataBillsResponse teResponse = new TEDataBillsResponse();
//        String custID = teRequest.getCustomerNumber();
        String landLine = teRequest.getLandLine();
        try {
//            teDataUrl = teDataUrl.replace("{customerNumber}", "0");
            teDataUrl = teDataUrl.replace("{landline}", landLine);
            HttpGet httpGet = new HttpGet(teDataUrl);
            httpGet.addHeader("deviceType", "Web");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("extTrxId", uuid);

            MasaryManager.logger.info("TE Data service inquiry request: ");

            CloseableHttpResponse teDataInquiryHttp = httpclient.execute(httpGet);
            String ResponseJson = EntityUtils.toString(teDataInquiryHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("TE Data service response: " + ResponseJson);
            MasaryManager.logger.info("TE Data service inquiry request uuid: " + uuid);
            Gson gson = new Gson();
            if (teDataInquiryHttp.getStatusLine().getStatusCode() == 200) {
                teResponse = gson.fromJson(ResponseJson, TEDataBillsResponse.class);
            } else if (ResponseJson.contains("status")) {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(ResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to Make TE Data inquiry service " + errorResponseDTO.getMessage());
                if (errorResponseDTO.getStatus().equals("14")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.NOTENOUGHBALANCEar : CONFIG.NOTENOUGHBALANCEen);
                } else if (errorResponseDTO.getStatus().equals("25")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("23")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("24")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("28")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.INVALIDTOPUPAMOUNTar : CONFIG.INVALIDTOPUPAMOUNTen);
                } else if (errorResponseDTO.getStatus().equals("25")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("26")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.OrangeCappingExceedLimitAr : CONFIG.OrangeCappingExceedLimit);
                } else if (errorResponseDTO.getStatus().equals("27")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("5")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("12")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("15")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("4")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("6")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                }

            }
        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling TE Data inquiry service on new system: " + ex);
            throw (ex);
        } finally {
            httpclient.close();
        }

        return teResponse;
    }

    public TEDataBillsResponse TEPayment(TEDataBillsRequest teRequest, String token, String uuid) throws Exception {

        String teDataUrl = SystemSettingsUtil.getInstance().loadProperty("TEData.Payment.url");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        TEDataBillsResponse teResponse = new TEDataBillsResponse();
        try {
            HttpPost httpPost = new HttpPost(teDataUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "Web");
            httpPost.addHeader("extTrxId", uuid);
            Gson gson = new Gson();
            String urlParameters = gson.toJson(teRequest, TEDataBillsRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);
            CloseableHttpResponse teDataPayResponseHttp = httpclient.execute(httpPost);
            String eTEDataResponseJson = EntityUtils.toString(teDataPayResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("TE Data Service response is : " + eTEDataResponseJson);
            MasaryManager.logger.info("TE Data Service response uuid is : " + uuid);
            if (teDataPayResponseHttp.getStatusLine().getStatusCode() == 200) {
                teResponse = gson.fromJson(eTEDataResponseJson, TEDataBillsResponse.class);
            } else if (eTEDataResponseJson.contains("status")) {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(eTEDataResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to Make TE Data payment service " + errorResponseDTO.getMessage());
                if (errorResponseDTO.getStatus().equals("14")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.NOTENOUGHBALANCEar : CONFIG.NOTENOUGHBALANCEen);
                } else if (errorResponseDTO.getStatus().equals("25")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("23")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("24")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("28")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.INVALIDTOPUPAMOUNTar : CONFIG.INVALIDTOPUPAMOUNTen);
                } else if (errorResponseDTO.getStatus().equals("25")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("26")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.OrangeCappingExceedLimitAr : CONFIG.OrangeCappingExceedLimit);
                } else if (errorResponseDTO.getStatus().equals("27")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("5")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("12")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("15")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("4")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (errorResponseDTO.getStatus().equals("29") || errorResponseDTO.getStatus().equals("21")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.MobinilTopupRepetedErrorar : CONFIG.MobinilTopupRepetedError);
                } else if (errorResponseDTO.getStatus().equals("6")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                }

            }
        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling TE Data service on new system: " + ex);
            throw (ex);
        } finally {
            httpclient.close();
        }
        return teResponse;
    }

    public TEDataBillsResponse rePrint(long ledgerTrxId, String token) throws Exception {

        String teDataUrl = loadUrlProperty("TEData.Reprint.url");
        teDataUrl = prepareUrl(teDataUrl, "{ledgerTrxId}", String.valueOf(ledgerTrxId));

        try {
            httpclient = initHttpClient();
            httpGet = initHttpGet(teDataUrl, token);
            logger = initLogger();

            logger.info("TE Data service reprint ledgerTrxId: " + ledgerTrxId);

            CloseableHttpResponse reprintResponseHttp = executeGetRequest(httpclient, httpGet);
            String reprintResponseJson = getJsonFromResponse(reprintResponseHttp);

            logger.info("TE Data service response: " + reprintResponseJson);

            switch (getStatusCode(reprintResponseHttp)) {
                case 200:
                    return json2Object(reprintResponseJson, TEDataBillsResponse.class);
                default:
                    logger.info("Failed to reprint");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(reprintResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage("ar", standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);
            }
        } catch (Exception ex) {
            logger.error("Error during calling TE Data inquiry service on new system: " + ex);
            throw (ex);
        } finally {
            httpclient.close();
        }
    }

    public boolean actionDestination(String landLine, String token) throws Exception {
        String teDataUrl = loadUrlProperty("TeDataBss.destination.url");
        teDataUrl = prepareUrl(teDataUrl, "{landLine}", landLine);

        try {
            httpclient = initHttpClient();
            httpGet = initHttpGet(teDataUrl, token);
            this.logger = initLogger();

            logger.info("TE Data service Bss destination landLine: " + landLine);

            CloseableHttpResponse destinationResponseHttp = executeGetRequest(httpclient, httpGet);

            logger.info("TE Data service Bss Http Response: " + destinationResponseHttp.toString());

            String destinationResponseJson = getJsonFromResponse(destinationResponseHttp);

            logger.info("TE Data service Bss destination JSON: " + destinationResponseJson);

            switch (getStatusCode(destinationResponseHttp)) {
                case 200:
                    return json2Object(destinationResponseJson, TeDataActionDestinationResponse.class).getActionDestination() == 0;
                default:
                    logger.info("Failed to reprint");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(destinationResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage("ar", standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);
            }
        } catch (Exception e) {
            logger.error("Error during calling TE Data service on new system: " + e);
            throw (e);
        } finally {
            httpclient.close();
        }
    }

    public TEDataBillsResponse bssPayemnt(TeDataBssRequest teDataBillsRequest, String token) throws Exception {
        String teDataUrl = loadUrlProperty("TeDataBss.payment.url");

        try {
            String urlParameters = object2Json(teDataBillsRequest, TeDataBssRequest.class);

            httpclient = initHttpClient();
            httpPost = initHttpPost(urlParameters, teDataUrl, token);
            logger = initLogger();

            logger.info("TE Data service Bss Payment Body: " + urlParameters);

            CloseableHttpResponse paymentResponseHttp = executePostRequest(httpclient, httpPost);

            logger.info("TE Data service Bss Http Response: " + paymentResponseHttp.toString());

            String paymentResponseJson = getJsonFromResponse(paymentResponseHttp);

            logger.info("TE Data service destination JSON: " + paymentResponseJson);

            switch (getStatusCode(paymentResponseHttp)) {
                case 200:
                    return json2Object(paymentResponseJson, TEDataBillsResponse.class);
                default:
                    logger.info("Failed to payment bss");
                    StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(paymentResponseJson, StandardHttpJsonResponseDTO.class);
                    String errorMessage = getErrorMessage("ar", standardHttpJsonResponseDTO.getMessage());
                    throw new Exception(errorMessage);
            }
        } catch (Exception e) {
            logger.error("Error during calling TE Data service on new system: " + e);
            throw (e);
        } finally {
            httpclient.close();
        }
    }

    private String getErrorMessage(String lang, String message) {
        String errorMessage = "";
        if (message.equals("14")) {
            errorMessage = lang.equals("ar") ? CONFIG.NOTENOUGHBALANCEar : CONFIG.NOTENOUGHBALANCEen;
        } else if (message.equals("25")) {
            errorMessage = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
        } else if (message.equals("23")) {
            errorMessage = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
        } else if (message.equals("24")) {
            errorMessage = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
        } else if (message.equals("28")) {
            errorMessage = lang.equals("ar") ? CONFIG.INVALIDTOPUPAMOUNTar : CONFIG.INVALIDTOPUPAMOUNTen;
        } else if (message.equals("25")) {
            errorMessage = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
        } else if (message.equals("26")) {
            errorMessage = lang.equals("ar") ? CONFIG.OrangeCappingExceedLimitAr : CONFIG.OrangeCappingExceedLimit;
        } else if (message.equals("27")) {
            errorMessage = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
        } else if (message.equals("5")) {
            errorMessage = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
        } else if (message.equals("12")) {
            errorMessage = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
        } else if (message.equals("15")) {
            errorMessage = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
        } else if (message.equals("4")) {
            errorMessage = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
        } else if (message.equals("6")) {
            errorMessage = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
        } else if (message.equals("11726")) {
            errorMessage = lang.equals("ar") ? CONFIG.DuplicatedTransactionAr : CONFIG.DuplicatedTransactionEn;
        } else if (message.equals("52")) {
            errorMessage = lang.equals("ar") ? "غير مسموح بالدفع لنفس الرقم خلال 24 ساعة  " : CONFIG.errorTransaction;
        } else if (message.equals("11715")) {
            errorMessage = lang.equals("ar") ? "عفوا لا يوجد فواتير مستحقة لهذا الرقم" : CONFIG.errorTransaction;
        } else if (message.equals("11716")) {
            errorMessage = lang.equals("ar") ? "غير مسموح بالدفع برجاء الرجوع الى مزود الخدمة" : CONFIG.errorTransaction;
        } else if (message.equals("11719")) {
            errorMessage = lang.equals("ar") ? "عفوا هذا الرقم غير صحيح" : CONFIG.errorTransaction;
        } else if (message.equals("11726")) {
            errorMessage = lang.equals("ar") ? "خطأ أثناء تنفيذ العملية ، عملية دفع مكررة" : CONFIG.errorTransaction;
        } else {
            errorMessage = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
        }
        return errorMessage += ("[ E " + message + " ]");
    }
}
