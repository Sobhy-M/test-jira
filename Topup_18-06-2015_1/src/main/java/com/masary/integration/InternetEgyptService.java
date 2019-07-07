/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.masary.common.CONFIG;
import static com.masary.common.CONFIG.lang;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.InternetEgyptInquiryResponse;
import com.masary.integration.dto.InternetEgyptPaymentResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;
import java.io.IOException;
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
 * @author Mustafa
 */
public class InternetEgyptService
{

    public InternetEgyptInquiryResponse internetEgyptInquiryWithoutAuthentication(String mobile, String token) throws Exception
    {
        String internetEgyptInquiryUrl = SystemSettingsUtil.getInstance().loadProperty("service.internetEgyptInquiry.url");

        internetEgyptInquiryUrl = internetEgyptInquiryUrl.replace("{mobile}", mobile);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        InternetEgyptInquiryResponse internetEgyptInquiryResponse = new InternetEgyptInquiryResponse();

        StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
        try
        {
            HttpGet httpGet = new HttpGet(internetEgyptInquiryUrl);
            httpGet.addHeader("deviceType", "Web");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("Content-Type", "application/json");
            //  httpGet.addHeader("Accept-Language", "en-US,en;q=0.5");
            MasaryManager.logger.info("Internet egypt service inquiry request: " + internetEgyptInquiryUrl);

            CloseableHttpResponse internetEgyptInquiryHttp = httpclient.execute(httpGet);

            String ResponseJson = EntityUtils.toString(internetEgyptInquiryHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("Internet egypt service response: " + ResponseJson);
            Gson gson = new Gson();
            if (internetEgyptInquiryHttp.getStatusLine().getStatusCode() == 200)
            {

                internetEgyptInquiryResponse = gson.fromJson(ResponseJson, InternetEgyptInquiryResponse.class);

            }
            else
            {

                standardHttpJsonResponseDTO = gson.fromJson(ResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("5"))
                {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                }
                else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("12"))
                {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                }
                else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("15"))
                {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                }
                else if (standardHttpJsonResponseDTO.getStatus().equals("400") && standardHttpJsonResponseDTO.getMessage().equals("200417"))
                {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorNationaIdar : CONFIG.errorNationaId);
                }
                else if (standardHttpJsonResponseDTO.getStatus().equals("404") && standardHttpJsonResponseDTO.getMessage().equals("6132"))
                {
                    throw new IOException(lang.equals("ar") ? CONFIG.ErrorNumberInternetEgyptAr : CONFIG.ErrorNumberInternetEgyptEn);
                }

                else
                {

                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                    throw new Exception(standardHttpJsonResponseDTO.getException()):"",
                }

            }

        }
        catch (IOException ex)
        {
            MasaryManager.logger.error("Error during calling internet egypt service on new system: " + ex, ex);
            throw (ex);
        }
        catch (ParseException ex)
        {
            MasaryManager.logger.error("Error during calling internet egypt service on new system: " + ex, ex);
            throw (ex);
        }
        catch (JsonSyntaxException ex)
        {
            MasaryManager.logger.error("Error during calling internet egypt service on new system: " + ex, ex);
            throw (ex);
        }
        finally
        {
            httpclient.close();
        }

        return internetEgyptInquiryResponse;
    }

    public InternetEgyptPaymentResponse internetEgyptPaymentWithoutAuthentication(String mobile, String token) throws Exception
    {
        String internetEgyptPaymentUrl = SystemSettingsUtil.getInstance().loadProperty("service.internetEgyptPayment.url");

        // internetEgyptInquiryUrl = internetEgyptInquiryUrl.replace("{mobile}", mobile);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        InternetEgyptPaymentResponse internetEgyptPaymentResponse = new InternetEgyptPaymentResponse();

        StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
        try
        {
            HttpPost httpPost = new HttpPost(internetEgyptPaymentUrl);
            httpPost.addHeader("deviceType", "Web");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("Content-Type", "application/json");

            String jsonParams = "{\"phoneNumber\" : \"" + mobile + "\"}";
            StringEntity params = new StringEntity(jsonParams);
            MasaryManager.logger.info("Internet egypt service payment request: " + internetEgyptPaymentUrl + "to number" +mobile);

            httpPost.setEntity(params);
            CloseableHttpResponse internetEgyptPaymentHttp = httpclient.execute(httpPost);

            String ResponseJson = EntityUtils.toString(internetEgyptPaymentHttp.getEntity(), "UTF-8");
            MasaryManager.logger.info("Internet egypt service payment response: " + ResponseJson);
            Gson gson = new Gson();
            if (internetEgyptPaymentHttp.getStatusLine().getStatusCode() == 200)
            {

                internetEgyptPaymentResponse = gson.fromJson(ResponseJson, InternetEgyptPaymentResponse.class);

            }
            else
            {

                standardHttpJsonResponseDTO = gson.fromJson(ResponseJson, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("5"))
                {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                }
                else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("12"))
                {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                }
                else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("15"))
                {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                }
                else if (standardHttpJsonResponseDTO.getStatus().equals("400") && standardHttpJsonResponseDTO.getMessage().equals("200417"))
                {
                    throw new IOException(lang.equals("ar") ? CONFIG.errorNationaIdar : CONFIG.errorNationaId);
                }
                else
                {

                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                    throw new Exception(standardHttpJsonResponseDTO.getException()):"",
                }

            }

        }
        catch (Exception ex)
        {
            MasaryManager.logger.error("Error during calling internet egypt service on new system: " + ex, ex);
            throw (ex);
        }
        finally
        {
            httpclient.close();
        }

        return internetEgyptPaymentResponse;
    }

}
