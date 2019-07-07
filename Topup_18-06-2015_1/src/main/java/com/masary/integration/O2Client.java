/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.O2groupAcountDetailsResponse;
import com.masary.integration.dto.O2groupDenominationResponse;
import com.masary.integration.dto.O2groupInquiryResponse;
import com.masary.integration.dto.O2groupPayementResponse;
import com.masary.integration.dto.O2groupPaymentDto;
import com.masary.utils.SystemSettingsUtil;
import java.io.IOException;
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
 * @author Abdelsabor
 */
public class O2Client {

    public O2groupAcountDetailsResponse getAccountDetails(String mobileNumber, String token, String ip) throws Exception {
        String accountDetailsUrl = SystemSettingsUtil.getInstance().loadProperty("O2.accountDetails.url");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        O2groupAcountDetailsResponse o2groupAcountDetailsResponse = new O2groupAcountDetailsResponse();
        accountDetailsUrl = accountDetailsUrl.replace("{mobileNumber}", mobileNumber);
        try {
            HttpGet httpGet = new HttpGet(accountDetailsUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
//            httpGet.addHeader("token",token);
            httpGet.addHeader("deviceType", "Web");
            httpGet.addHeader("ip", ip);
            CloseableHttpResponse o2GroupAccountResponseHttp = httpclient.execute(httpGet);
            String ResponseJson = EntityUtils.toString(o2GroupAccountResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("O2Group getAccountDetails response is : " + ResponseJson);

            Gson gson = new Gson();

            if (o2GroupAccountResponseHttp.getStatusLine().getStatusCode() == 200) {
                o2groupAcountDetailsResponse = gson.fromJson(ResponseJson, O2groupAcountDetailsResponse.class);
            } else {
                com.masary.integration.dto.StandardHttpJsonResponseDTO errorResponseDTO = new com.masary.integration.dto.StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(ResponseJson, com.masary.integration.dto.StandardHttpJsonResponseDTO.class);
                if (errorResponseDTO.getMessage().equals("5014")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("ProviderInquiryFailureException");
                } else if (errorResponseDTO.getMessage().equals("5013")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("DenominationNotFoundException");
                } else if (errorResponseDTO.getMessage().equals("5012")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("ProviderPaymentFailureException");
                } else if (errorResponseDTO.getMessage().equals("5011")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("ProviderIntegrationException");
                } else {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception(errorResponseDTO.getMessage());

                }
            }
        } catch (IOException ex) {
            MasaryManager.logger.error("Error during calling O2 service on new system: " + ex, ex);
            throw (ex);
        } catch (Exception e) {
            MasaryManager.logger.error("Error during calling O2 service on new system: " + e);
            throw new Exception(e.getMessage());
        } finally {
            httpclient.close();
        }
        return o2groupAcountDetailsResponse;

    }

    public List<O2groupDenominationResponse> getVouchersDenominationList(String token, String ip) throws Exception {
        String DenominationDetails = SystemSettingsUtil.getInstance().loadProperty("O2.denomination.url");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        List<O2groupDenominationResponse> o2groupDenominationResponse = null;

        try {
            HttpGet httpGet = new HttpGet(DenominationDetails);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
//httpGet.addHeader("token",token);
            httpGet.addHeader("deviceType", "Web");
            httpGet.addHeader("ip", ip);
            CloseableHttpResponse o2groupDenominationResponseHttp = httpclient.execute(httpGet);
            String ResponseJson = EntityUtils.toString(o2groupDenominationResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("O2Group getVouchersDenominationList response is : " + ResponseJson);
            Gson gson = new Gson();

            if (o2groupDenominationResponseHttp.getStatusLine().getStatusCode() == 200) {
                O2groupDenominationResponse[] denemoArr = gson.fromJson(ResponseJson, O2groupDenominationResponse[].class);
                o2groupDenominationResponse = Arrays.asList(denemoArr);
            } else {
                com.masary.integration.dto.StandardHttpJsonResponseDTO errorResponseDTO = new com.masary.integration.dto.StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(ResponseJson, com.masary.integration.dto.StandardHttpJsonResponseDTO.class);
                if (errorResponseDTO.getMessage().equals("5014")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("ProviderInquiryFailureException");
                } else if (errorResponseDTO.getMessage().equals("5013")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("DenominationNotFoundException");
                } else if (errorResponseDTO.getMessage().equals("5012")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("ProviderPaymentFailureException");
                } else if (errorResponseDTO.getMessage().equals("5011")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("ProviderIntegrationException");
                } else {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception(errorResponseDTO.getMessage());

                }
            }
        } catch (IOException ex) {
            MasaryManager.logger.error("Error during calling O2 service on new system: " + ex, ex);
            throw (ex);
        } catch (Exception e) {
            MasaryManager.logger.error("Error during calling O2 service on new system: " + e);
            throw new Exception(e.getMessage());
        } finally {
            httpclient.close();
        }

        return o2groupDenominationResponse;
    }

    public O2groupInquiryResponse doO2GroupInquery(String token, String ip, String voucherID, String accountID) throws Exception {
        String inqueryUrl = SystemSettingsUtil.getInstance().loadProperty("O2.inquiry.url");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        O2groupInquiryResponse o2groupInquiryResponse = new O2groupInquiryResponse();
        inqueryUrl = inqueryUrl.replace("{accouontId}", accountID);
        inqueryUrl = inqueryUrl.replace("{denominationId}", voucherID);

        try {
            HttpGet httpGet = new HttpGet(inqueryUrl);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
//            httpGet.addHeader("token", token);
            httpGet.addHeader("deviceType", "Web");
            httpGet.addHeader("ip", ip);
            CloseableHttpResponse o2groupInqueryResponseHttp = httpclient.execute(httpGet);
            String ResponseJson = EntityUtils.toString(o2groupInqueryResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("O2Group doO2GroupInquery response is : " + ResponseJson);
            Gson gson = new Gson();

            if (o2groupInqueryResponseHttp.getStatusLine().getStatusCode() == 200) {
                o2groupInquiryResponse = gson.fromJson(ResponseJson, O2groupInquiryResponse.class);
            } else {
                com.masary.integration.dto.StandardHttpJsonResponseDTO errorResponseDTO = new com.masary.integration.dto.StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(ResponseJson, com.masary.integration.dto.StandardHttpJsonResponseDTO.class);
                if (errorResponseDTO.getMessage().equals("5014")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("ProviderInquiryFailureException");
                } else if (errorResponseDTO.getMessage().equals("5013")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("DenominationNotFoundException");
                } else if (errorResponseDTO.getMessage().equals("5012")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("ProviderPaymentFailureException");
                } else if (errorResponseDTO.getMessage().equals("5011")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("ProviderIntegrationException");
                } else {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception(errorResponseDTO.getMessage());

                }
            }
        } catch (IOException ex) {
            MasaryManager.logger.error("Error during calling O2 service on new system: " + ex, ex);
            throw (ex);
        } catch (Exception e) {
            MasaryManager.logger.error("Error during calling O2 service on new system: " + e);
            throw new Exception(e.getMessage());
        } finally {
            httpclient.close();
        }

        return o2groupInquiryResponse;

    }

    public O2groupPayementResponse doO2GroupPayment(String token, String ip, String voucherID, String accountID) throws Exception {
        String inqueryUrl = SystemSettingsUtil.getInstance().loadProperty("O2.payment.url");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        O2groupPayementResponse o2groupPayementResponse = new O2groupPayementResponse();
        Gson gson = new Gson();
        try {
            HttpPost httpPost = new HttpPost(inqueryUrl);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Authorization", token);
//            httpPost.addHeader("token",token);
            httpPost.addHeader("deviceType", "Web");
            httpPost.addHeader("ip", ip);
            O2groupPaymentDto o2groupPaymentDto = new O2groupPaymentDto();
            o2groupPaymentDto.setO2GroupAccountId(accountID);
            o2groupPaymentDto.setDenominationId(Long.parseLong(voucherID));
            String paymentGson = gson.toJson(o2groupPaymentDto, O2groupPaymentDto.class);

            StringEntity params = new StringEntity(paymentGson);
            httpPost.setEntity(params);

            CloseableHttpResponse o2groupInqueryResponseHttp = httpclient.execute(httpPost);

            String ResponseJson = EntityUtils.toString(o2groupInqueryResponseHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("O2Group doO2GroupPayment response is : " + ResponseJson);

            if (o2groupInqueryResponseHttp.getStatusLine().getStatusCode() == 200) {
                o2groupPayementResponse = gson.fromJson(ResponseJson, O2groupPayementResponse.class);
            } else {
                com.masary.integration.dto.StandardHttpJsonResponseDTO errorResponseDTO = new com.masary.integration.dto.StandardHttpJsonResponseDTO();
                errorResponseDTO = gson.fromJson(ResponseJson, com.masary.integration.dto.StandardHttpJsonResponseDTO.class);
                if (errorResponseDTO.getMessage().equals("5014")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("ProviderInquiryFailureException");
                } else if (errorResponseDTO.getMessage().equals("5013")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("DenominationNotFoundException");
                } else if (errorResponseDTO.getMessage().equals("5012")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("ProviderPaymentFailureException");
                } else if (errorResponseDTO.getMessage().equals("5011")) {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception("ProviderIntegrationException");
                } else {
                    MasaryManager.logger.info("Failed to get voucher list " + errorResponseDTO.getMessage());
                    throw new Exception(errorResponseDTO.getMessage());

                }
            }
        } catch (IOException ex) {
            MasaryManager.logger.error("Error during calling O2 service on new system: " + ex, ex);
            throw (ex);
        } catch (Exception e) {
            MasaryManager.logger.error("Error during calling O2 service on new system: " + e);
            throw new Exception(e.getMessage());
        } finally {
            httpclient.close();
        }

        return o2groupPayementResponse;

    }

}
