/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.integration.dto.TopupResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.integration.dto.CommonTopupRequestDTO;
import com.google.gson.Gson;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.utils.SystemSettingsUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author AYA
 */
public class VodafoneTopupHttpClient {
    
     public TopupResponse doTopupTransaction(CommonTopupRequestDTO topupRequest, String lang) throws Exception {

        String vodafoneTopupUrl = SystemSettingsUtil.getInstance().loadProperty("vodafone.topup.url");

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(vodafoneTopupUrl);

        StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();

        Gson gson = new Gson();

        TopupResponse topupresponse = new TopupResponse();

        String urlParameters = gson.toJson(topupRequest, CommonTopupRequestDTO.class);

        String encoded = topupRequest.getToken();
        StringEntity params = new StringEntity(urlParameters);
        httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Authorization", encoded);
        httpPost.addHeader("ip", topupRequest.getIp());
        httpPost.addHeader("deviceType", "Web");
        httpPost.setEntity(params);

        try {
            CloseableHttpResponse response = httpclient.execute(httpPost);

            String json = EntityUtils.toString(response.getEntity(), "UTF-8");

            MasaryManager.logger.info(response.getStatusLine());
            MasaryManager.logger.info("JSON: " + json);

            if (response.getStatusLine().toString().contains("200")) {
                topupresponse = gson.fromJson(json, TopupResponse.class);
                MasaryManager.logger.info("Success transaction vodafone integration  " + topupresponse);

            } else if (json.contains("status")) {
                standardHttpJsonResponseDTO = gson.fromJson(json, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("409") && standardHttpJsonResponseDTO.getMessage().equals("14")) {
//                    throw new Exception("رصيدك غير كافى لاجراء العملية");
                    throw new Exception(lang.equals("ar") ? CONFIG.NOTENOUGHBALANCEar : CONFIG.NOTENOUGHBALANCEen);
//                    throw new Exception(" Account balance is not suffcient");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("417") && standardHttpJsonResponseDTO.getMessage().equals("25")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                    throw new Exception("خطأ أثناء اجراء عملية الشحن لدى اورانج");
//                    throw new Exception("TopupOperatorRetriableFailedException");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("23")) {
//                    throw new Exception("رقم التليفون خاطئ");
//                    throw new Exception("Incorrect mobile number ");
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("423") && standardHttpJsonResponseDTO.getMessage().equals("24")) {
//                    throw new Exception("خطآ فى حساب مصارى لدى اورانج");
//                    throw new Exception("Error related to Masary account in the operator");
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("423") && standardHttpJsonResponseDTO.getMessage().equals("28")) {
//                    throw new Exception("مبلغ الشحن غير صحيح");
//                    throw new Exception("Invalid amount");
                    throw new Exception(lang.equals("ar") ? CONFIG.INVALIDTOPUPAMOUNTar : CONFIG.INVALIDTOPUPAMOUNTen);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("417") && standardHttpJsonResponseDTO.getMessage().equals("25")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                    throw new Exception("خطآ متكرر لدى اورانج, حاول مرة أخرى لاحقآ");
//                    throw new Exception("Retriable error <<try again later>>");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("401")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                    throw new Exception("خطآ أثناء الحصول على بياناتك");
//                    throw new Exception("Error while getting account info ");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("423") && standardHttpJsonResponseDTO.getMessage().equals("26")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.OrangeCappingExceedLimitAr : CONFIG.OrangeCappingExceedLimit);
//                    throw new Exception("عفوآ لقد تخطيت الحد الاقصى المسموح به يوميآ");
//                    throw new Exception("Monthly limit exceeded");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("423") && standardHttpJsonResponseDTO.getMessage().equals("27")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                    throw new Exception("عفوآ لقد تخطيت الحد الاقصى المسموح به شهريآ");
//                    throw new Exception("Daily limit exceeded");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("5")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                    throw new Exception("الخدمة غير متاحة لحسابك");
//                    throw new Exception("Service is inactive on this account");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("12")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                    throw new Exception("12: وقع خطآ أثناء تنفيذ العملية");
//                    throw new Exception("CommonMasaryException 12");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("15")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                    throw new Exception("15: وقع خطآ أثناء تنفيذ العملية");
//                    throw new Exception(standardHttpJsonResponseDTO.getError());
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getException().toLowerCase().contains("token")) {
                    throw new Exception("Token expired exception, please login again.");
//                    throw new Exception("15: وقع خطآ أثناء تنفيذ العملية");
//                    throw new Exception(standardHttpJsonResponseDTO.getError());
                } else if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("4")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                    throw new Exception("عفوآ خدمة الشحن غير متاحة حاليآ");
//                    throw new Exception("Service is inactive");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("429") && standardHttpJsonResponseDTO.getMessage().equals("29") || standardHttpJsonResponseDTO.getMessage().equals("21")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.MobinilTopupRepetedErrorar : CONFIG.MobinilTopupRepetedError);
//                    throw new Exception("غير مسموح باجراء عمليتين لنفس الرقم فى زمن اقل من 6 دقائق");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("416") && standardHttpJsonResponseDTO.getMessage().equals("6")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                    throw new Exception("القيمة المدخلة ليست فى النطاق المسموح به لهذه الخدمة");
//                    throw new Exception("Transaction amount not in service range (Every Serivce has a minimum and maxmum amount make a transaction within");
                } else {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                    throw new Exception(standardHttpJsonResponseDTO.getException());
                }

            } else {
                throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                throw new Exception("خطآ فى الحصول على بيانات لحسابك");
//                throw new Exception("Proxy Service hasn't validated account successfully");
            }

            return topupresponse;

        } // end try
        catch (Exception e) {
            MasaryManager.logger.error("Exception" + e.getMessage());
            MasaryManager.logger.error(e);
            throw (e);
        } finally {
            httpclient.close();
        }
    }
}
