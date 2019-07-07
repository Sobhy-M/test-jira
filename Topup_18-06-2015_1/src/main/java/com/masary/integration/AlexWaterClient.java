/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.AlexWaterInquiryResponse;
import com.masary.integration.dto.AlexWaterRequest;
import com.masary.integration.dto.AlexWaterPaymentResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;
import java.net.ConnectException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Barakat Mostafa
 */
// tag consider
public class AlexWaterClient {

    public static AlexWaterInquiryResponse alexWaterInquiry(AlexWaterRequest alexWaterRequest, String lang, String token) throws Exception {
        String alexWaterInquiryURL = SystemSettingsUtil.getInstance().loadProperty("alexwater.bill.inquiry.url");

        alexWaterInquiryURL = alexWaterInquiryURL.replace("{electricityNumber}", alexWaterRequest.getElectricityNumber());

        CloseableHttpClient httpclient = HttpClients.createDefault();

        AlexWaterInquiryResponse alexWaterInquiryResponse = new AlexWaterInquiryResponse();

        try {
            HttpGet httpGet = new HttpGet(alexWaterInquiryURL);
            httpGet.addHeader("deviceType", "Web");
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);

            MasaryManager.logger.info("alex water service inquiry request: " + alexWaterInquiryURL);

            CloseableHttpResponse alexwaterBackEndInquiryHttp = httpclient.execute(httpGet);
            Gson gson = new Gson();
            String responseJson = EntityUtils.toString(alexwaterBackEndInquiryHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("alex water service response: " + responseJson);

            if (alexwaterBackEndInquiryHttp.getStatusLine().getStatusCode() == 200) {

                alexWaterInquiryResponse = gson.fromJson(responseJson, AlexWaterInquiryResponse.class);

            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(responseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to inquire from alex water  service general Error");
                if (errorResponseDTO.getMessage().equals("99991")) {
                    throw new Exception(lang.equals("ar") ? "تعذر الإتصال بمقدم الخدمة \"شركة مياة الأسكندرية\" في الوقت الحالي، يرجى إعادة المحاولة في وقت لاحق" : "Provider integration failure");
                } else if (errorResponseDTO.getMessage().equals("99992")) {
                    throw new Exception(lang.equals("ar") ? "لا توجد فواتير مستحقة لهذا العميل" : "No due amount found for this number");
                } else if (errorResponseDTO.getMessage().equals("99993")) {
                    throw new Exception(lang.equals("ar") ? "لايمكن الدفع لهذا الرقم، برجاء المتابعة مع مزود الخدمة \" شركة مياة الأسكندرية\"" : "Error code exception");
                } else if (errorResponseDTO.getMessage().contains("99994")) {
                    throw new Exception(lang.equals("ar") ? "برجاء إعادة الإستعلام مرة أخرى" : "No inquiry for this number");
                } else {
                    throw new Exception(lang.equals("ar") ? "خطأ فى تنفيذ العملية" : "Error in transaction");
                }
            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling alexwater inquiry service on new system: " + ex);
            throw new Exception(lang.equals("ar") ? "خطا اثناء الاتصال بالخدمة" : "Error while calling service");
        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling alexwater inquiry service on new system: " + ex);
            throw (ex);
        } finally {
            httpclient.close();
        }

        return alexWaterInquiryResponse;
    }

    public static AlexWaterPaymentResponse alexWaterPayment(AlexWaterRequest alexWaterRequest, String lang, String token) throws Exception {
        String alexWaterPaymentURL = SystemSettingsUtil.getInstance().loadProperty("alexwater.bill.payment.url");
        AlexWaterPaymentResponse alexWaterPaymentResponse = new AlexWaterPaymentResponse();

        try {
            HttpPost httpPost = new HttpPost(alexWaterPaymentURL);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "Web");

            Gson gson = new Gson();

            String urlParameters = gson.toJson(alexWaterRequest, AlexWaterRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);

            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse alexWaterPayResponseHttp = httpclient.execute(httpPost);

            String alexWaterPayResponseJson = EntityUtils.toString(alexWaterPayResponseHttp.getEntity(), "UTF-8");

            MasaryManager.logger.info("alexwater payment response is : " + alexWaterPayResponseHttp);

            if (alexWaterPayResponseHttp.getStatusLine().getStatusCode() == 200) {
                alexWaterPaymentResponse = gson.fromJson(alexWaterPayResponseJson, AlexWaterPaymentResponse.class);

            } else {

                MasaryManager.logger.info("Failed to pay in alexwater service : general Error");
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(alexWaterPayResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to inquire from alex water  service general Error");
                if (errorResponseDTO.getMessage().equals("99991")) {
                    throw new Exception(lang.equals("ar") ? "تعذر الإتصال بمقدم الخدمة \"شركة مياة الأسكندرية\" في الوقت الحالي، يرجى إعادة المحاولة في وقت لاحق" : "Provider integration failure");
                } else if (errorResponseDTO.getMessage().equals("99992")) {
                    throw new Exception(lang.equals("ar") ? "لا توجد فواتير مستحقة لهذا العميل" : "No due amount found for this number");
                } else if (errorResponseDTO.getMessage().equals("99993")) {
                    throw new Exception(lang.equals("ar") ? "لايمكن الدفع لهذا الرقم، برجاء المتابعة مع مزود الخدمة \" شركة مياة الأسكندرية\"" : "Error code exception");
                } else if (errorResponseDTO.getMessage().contains("99994")) {
                    throw new Exception(lang.equals("ar") ? "برجاء إعادة الإستعلام مرة أخرى" : "No inquiry for this number");
                } else {
                    throw new Exception(lang.equals("ar") ? "خطأ فى تنفيذ العملية" : "Error in transaction");
                }

            }
        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling alexwater payment service on new system: " + ex);
            throw new Exception(lang.equals("ar") ? "خطا اثناء الاتصال بالخدمة" : "Error while calling service");
        } catch (Exception e) {
            MasaryManager.logger.error("Failed to pay in alexwater service " + e.getMessage());
            throw new Exception(e.getMessage());
        }

        return alexWaterPaymentResponse;
    }

}
