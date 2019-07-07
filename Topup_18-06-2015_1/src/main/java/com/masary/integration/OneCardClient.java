/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.OneCardDenominationResponse;
import com.masary.integration.dto.OneCardInquiryResponse;
import com.masary.integration.dto.OneCardPaymentRequest;
import com.masary.integration.dto.OneCardPaymentResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author hammad
 */
public class OneCardClient {
    
    public List<OneCardDenominationResponse> getVouchersDenominationList(String lang, String token, String ip) throws Exception {

        String listVoucherUrl = SystemSettingsUtil.getInstance().loadProperty("onecard.voucher.url");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        List<OneCardDenominationResponse> oneCardDenoResponse = new ArrayList<OneCardDenominationResponse>();
        
        try {

            HttpGet httpGet = new HttpGet(listVoucherUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
//            httpGet.addHeader("token", token);
            httpGet.addHeader("deviceType", "Web");
            httpGet.addHeader("ip", ip);
            CloseableHttpResponse oneCardDenoResponseHttp = httpclient.execute(httpGet);

            String oneCardDenoResponseJson = EntityUtils.toString(oneCardDenoResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("OneCard voucher list response is : " + oneCardDenoResponseJson);
            
            Gson gson = new Gson();

            if (oneCardDenoResponseHttp.getStatusLine().getStatusCode() == 200) {
                OneCardDenominationResponse[] denoArray  = gson.fromJson(oneCardDenoResponseJson, OneCardDenominationResponse[].class);
                oneCardDenoResponse = Arrays.asList(denoArray);
            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(oneCardDenoResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage() );
                throw new Exception(errorResponseDTO.getMessage());
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling OneCard service on new system: " + ex, ex);
            throw(ex);
        } finally {
            httpclient.close();
        }
        
        return oneCardDenoResponse;
    }
    
    public OneCardInquiryResponse oneCardInquiry(String denominationId, String lang, String token, String ip) throws Exception {

        String inquiryUrl = SystemSettingsUtil.getInstance().loadProperty("onecard.inquiry.url");

        inquiryUrl = inquiryUrl.replace("{denominationid}", denominationId);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        OneCardInquiryResponse oneCardInquiryResponse = new OneCardInquiryResponse();
        
        try {

            HttpGet httpGet = new HttpGet(inquiryUrl);
            httpGet.addHeader("Content-Type", "application/json");
//            httpGet.addHeader("token", token);
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");
            httpGet.addHeader("ip", ip);
            CloseableHttpResponse oneCardInquiryResponseHttp = httpclient.execute(httpGet);

            String oneCardInquiryResponseJson = EntityUtils.toString(oneCardInquiryResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("OneCard inquiry response is : " + oneCardInquiryResponseJson);
            
            Gson gson = new Gson();

            if (oneCardInquiryResponseHttp.getStatusLine().getStatusCode() == 200) {
                oneCardInquiryResponse = gson.fromJson(oneCardInquiryResponseJson, OneCardInquiryResponse.class);
            } else {
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(oneCardInquiryResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to enquire one card " + errorResponseDTO.getMessage() );
                throw new Exception(errorResponseDTO.getMessage());
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling OneCard service on new system: " + ex, ex);
            throw(ex);
        } finally {
            httpclient.close();
        }
        
        return oneCardInquiryResponse;
    }

    public OneCardPaymentResponse oneCardPayment(OneCardPaymentRequest oneCardRequest, String lang, String token, String ip) throws Exception {

        String paymentUrl = SystemSettingsUtil.getInstance().loadProperty("onecard.payment.url");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        OneCardPaymentResponse oneCardPayResponse = new OneCardPaymentResponse();
        
        try {

            HttpPost httpPost = new HttpPost(paymentUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("deviceType", "Web");
            httpPost.addHeader("ip", ip);
            
            Gson gson = new Gson();
            String urlParameters = gson.toJson(oneCardRequest, OneCardPaymentRequest.class);
            StringEntity params = new StringEntity(urlParameters);
            httpPost.setEntity(params);
            
            CloseableHttpResponse oneCardPayResponseHttp = httpclient.execute(httpPost);

            String oneCardPayResponseJson = EntityUtils.toString(oneCardPayResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("OneCard payment response is : " + oneCardPayResponseJson);

            if (oneCardPayResponseHttp.getStatusLine().getStatusCode() == 200) {
                oneCardPayResponse = gson.fromJson(oneCardPayResponseJson, OneCardPaymentResponse.class);
            }else{
                StandardHttpJsonResponseDTO errorResponseDTO = new StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(oneCardPayResponseJson, StandardHttpJsonResponseDTO.class);
                MasaryManager.logger.info("Failed to pay one card " + errorResponseDTO.getMessage());
                
                if(errorResponseDTO.getMessage().equals("1")){
                    throw new Exception("الحساب مغلق");
                }else if(errorResponseDTO.getMessage().equals("4")){
                    throw new Exception("الخدمه متوقفه حاليا، برجاء المحاوله لاحقا");
                }else if(errorResponseDTO.getMessage().equals("6")){
                    throw new Exception("لا يمكن شحن هذه القيمه");
                }else if(errorResponseDTO.getMessage().equals("7")){
                    throw new Exception("الخدمه غير متاحه حاليا");
                }else if(errorResponseDTO.getMessage().equals("6141")){
                    throw new Exception("لا يوجد شحن بهذه الفئه");
                }else if(errorResponseDTO.getMessage().equals("6142")){
                    throw new Exception("مشكله فنيه من موفر الخدمه، برجاء المحاوله لاحقا");
                }else if(errorResponseDTO.getMessage().equals("6143")){
                    throw new Exception("مشكله فنيه من موفر الخدمه، برجاء المحاوله لاحقا");
                }else if(errorResponseDTO.getMessage().equals("6144")){
                    throw new Exception("رقم عميل وان كارد غير صحيح");
                }else if(errorResponseDTO.getMessage().equals("6145")){
                    throw new Exception("لا يوجد رصيد كافي لشحن حساب وان كارد");
                }else{
                    throw new Exception("خطأ عام اثناء شحن حساب وان كارد");
                }
            }

        } catch (Exception ex) {
            MasaryManager.logger.error("Error during calling OneCard service on new system: " + ex);
            throw(ex);
        } finally {
            httpclient.close();
        }
        
        return oneCardPayResponse;
    }

}
