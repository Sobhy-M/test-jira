/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.integration.dto.TopupResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.integration.dto.TopupDenominationDTO;
import com.masary.integration.dto.CommonTopupRepresentationDTO;
import com.masary.integration.dto.CommonTopupRequestDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masary.common.CONFIG;
import com.masary.common.CommonCalls;
import com.masary.database.manager.MasaryManager;
import com.masary.utils.SystemSettingsUtil;
import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Abdelsabor
 */
public class OrangeTopupHttpClient extends CommonCalls{

    private CloseableHttpClient httpClient;

    public OrangeTopupHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public OrangeTopupHttpClient() {
    }

    public TopupResponse doTopupTransaction(CommonTopupRequestDTO topupRequest, String lang) throws Exception {

        String orangeTopupUrl = SystemSettingsUtil.getInstance().loadProperty("orange.topup.url");

        CloseableHttpClient httpclient = this.httpClient;

        HttpPost httpPost = new HttpPost(orangeTopupUrl);

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
                MasaryManager.logger.info("Success transaction orange integration  " + topupresponse);

            } else if (json.contains("status")) {
                standardHttpJsonResponseDTO = gson.fromJson(json, StandardHttpJsonResponseDTO.class);
                if (standardHttpJsonResponseDTO.getStatus().equals("409")
                        && standardHttpJsonResponseDTO.getMessage().equals("14")) {
                    // throw new Exception("رصيدك غير كافى لاجراء العملية");
                    throw new Exception(lang.equals("ar") ? CONFIG.NOTENOUGHBALANCEar : CONFIG.NOTENOUGHBALANCEen);
                    // throw new Exception(" Account balance is not suffcient");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("417")
                        && standardHttpJsonResponseDTO.getMessage().equals("25")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                    // throw new Exception("خطأ أثناء اجراء عملية الشحن لدى اورانج");
                    // throw new Exception("TopupOperatorRetriableFailedException");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("406")
                        && standardHttpJsonResponseDTO.getMessage().equals("23")) {
                    // throw new Exception("رقم التليفون خاطئ");
                    // throw new Exception("Incorrect mobile number ");
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("423")
                        && standardHttpJsonResponseDTO.getMessage().equals("24")) {
                    // throw new Exception("خطآ فى حساب مصارى لدى اورانج");
                    // throw new Exception("Error related to Masary account in the operator");
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("423")
                        && standardHttpJsonResponseDTO.getMessage().equals("28")) {
                    // throw new Exception("مبلغ الشحن غير صحيح");
                    // throw new Exception("Invalid amount");
                    throw new Exception(lang.equals("ar") ? CONFIG.INVALIDTOPUPAMOUNTar : CONFIG.INVALIDTOPUPAMOUNTen);
                } else if (standardHttpJsonResponseDTO.getStatus().equals("417")
                        && standardHttpJsonResponseDTO.getMessage().equals("25")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                    // throw new Exception("خطآ متكرر لدى اورانج, حاول مرة أخرى لاحقآ");
                    // throw new Exception("Retriable error <<try again later>>");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("401")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                    // throw new Exception("خطآ أثناء الحصول على بياناتك");
                    // throw new Exception("Error while getting account info ");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("423")
                        && standardHttpJsonResponseDTO.getMessage().equals("26")) {
                    throw new Exception(
                            lang.equals("ar") ? CONFIG.OrangeCappingExceedLimitAr : CONFIG.OrangeCappingExceedLimit);
                    // throw new Exception("عفوآ لقد تخطيت الحد الاقصى المسموح به يوميآ");
                    // throw new Exception("Monthly limit exceeded");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("423")
                        && standardHttpJsonResponseDTO.getMessage().equals("27")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                    // throw new Exception("عفوآ لقد تخطيت الحد الاقصى المسموح به شهريآ");
                    // throw new Exception("Daily limit exceeded");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("406")
                        && standardHttpJsonResponseDTO.getMessage().equals("5")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                    // throw new Exception("الخدمة غير متاحة لحسابك");
                    // throw new Exception("Service is inactive on this account");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500")
                        && standardHttpJsonResponseDTO.getMessage().equals("12")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                    // throw new Exception("12: وقع خطآ أثناء تنفيذ العملية");
                    // throw new Exception("CommonMasaryException 12");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500")
                        && standardHttpJsonResponseDTO.getMessage().equals("15")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                    // throw new Exception("15: وقع خطآ أثناء تنفيذ العملية");
                    // throw new Exception(standardHttpJsonResponseDTO.getError());
                } else if (standardHttpJsonResponseDTO.getStatus().equals("500")
                        && standardHttpJsonResponseDTO.getException().toLowerCase().contains("token")) {
                    throw new Exception("Token expired exception, please login again.");
                    // throw new Exception("15: وقع خطآ أثناء تنفيذ العملية");
                    // throw new Exception(standardHttpJsonResponseDTO.getError());
                } else if (standardHttpJsonResponseDTO.getStatus().equals("406")
                        && standardHttpJsonResponseDTO.getMessage().equals("4")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                    // throw new Exception("عفوآ خدمة الشحن غير متاحة حاليآ");
                    // throw new Exception("Service is inactive");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("429")
                        && standardHttpJsonResponseDTO.getMessage().equals("29")
                        || standardHttpJsonResponseDTO.getMessage().equals("21")) {
                    throw new Exception(
                            lang.equals("ar") ? CONFIG.MobinilTopupRepetedErrorar : CONFIG.MobinilTopupRepetedError);
                    // throw new Exception("غير مسموح باجراء عمليتين لنفس الرقم فى زمن اقل من 6
                    // دقائق");
                } else if (standardHttpJsonResponseDTO.getStatus().equals("416")
                        && standardHttpJsonResponseDTO.getMessage().equals("6")) {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                    // throw new Exception("القيمة المدخلة ليست فى النطاق المسموح به لهذه الخدمة");
                    // throw new Exception("Transaction amount not in service range (Every Serivce
                    // has a minimum and maxmum amount make a transaction within");
                } else {
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                    // throw new Exception(standardHttpJsonResponseDTO.getException());
                }

            } else {
                throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                // throw new Exception("خطآ فى الحصول على بيانات لحسابك");
                // throw new Exception("Proxy Service hasn't validated account successfully");
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

    public List<TopupDenominationDTO> getOrangeTopupDenomination(String lang, String token) throws Exception {

        String orangeTopupDenominationsURL = loadUrlProperty();

        List<TopupDenominationDTO> orangeTopupDenominations = new ArrayList<TopupDenominationDTO>();

        try {
            HttpGet httpGet = new HttpGet(orangeTopupDenominationsURL);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("Authorization", token);
            httpGet.addHeader("deviceType", "Web");

            MasaryManager.logger.info("Orange Topup Denomination URL" + orangeTopupDenominationsURL);
            CloseableHttpResponse topupDenominationsHttp = this.httpClient.execute(httpGet);

            String topupDenominationsJson = EntityUtils.toString(topupDenominationsHttp.getEntity(), "UTF-8");

            MasaryManager.logger.info("Orange Topup Denominations response is : " + topupDenominationsJson);

            Gson gson = new Gson();
            if (topupDenominationsHttp.getStatusLine().getStatusCode() == 200) {
                orangeTopupDenominations = gson.fromJson(topupDenominationsJson,
                        new TypeToken<List<TopupDenominationDTO>>() {
                }.getType());

            } else {

                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(topupDenominationsJson, StandardHttpJsonResponseDTO.class);
                String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                throw new Exception(errorMessage);
            }

        } catch (Exception e) {
            System.out.println(e);
            throw e;
        } finally {
            this.httpClient.close();
        }

        return orangeTopupDenominations;
    }

    protected String loadUrlProperty() {
        return SystemSettingsUtil.getInstance().loadProperty("orange.topup.denominations");
    }

    public CommonTopupRepresentationDTO doOrangeTopup(CommonTopupRequestDTO topupRequest, String lang)
            throws Exception {

        MasaryManager.logger.info(topupRequest.getToken());

        String orangeTopupURL = SystemSettingsUtil.getInstance().loadProperty("new.orange.topup.url");

        HttpPost httpPost = new HttpPost(orangeTopupURL);

        Gson gson = new Gson();

        CommonTopupRepresentationDTO topupresponse = new CommonTopupRepresentationDTO();

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
            CloseableHttpResponse response = this.httpClient.execute(httpPost);

            String json = EntityUtils.toString(response.getEntity(), "UTF-8");
            if (response.getStatusLine().getStatusCode() == 200) {

                topupresponse = gson.fromJson(json, CommonTopupRepresentationDTO.class);
                MasaryManager.logger.info("Success transaction etisalat integration  " + topupresponse);

            } else {
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
                standardHttpJsonResponseDTO = gson.fromJson(json, StandardHttpJsonResponseDTO.class);
                String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
                throw new Exception(errorMessage);
            }
            return topupresponse;

        } catch (Exception e) {
            MasaryManager.logger.error("Exception" + e);
            throw (e);
        } finally {
            this.httpClient.close();
        }
    }

    private String getErrorMessage(String lang, String reason) {
        String errorMessage = "";

        if (reason.equals("1")) {
            errorMessage = lang.equals("ar") ? "هذه المحفظة غير مفعلة، برجاء إعادة تفعيلها ثم المحاولة مرة أخرى"
                    : "Account Is Inactive";
        } else if (reason.equals("2")) {
            errorMessage = lang.equals("ar") ? CONFIG.topupTransactionErrorAr : CONFIG.topupTransactionErrorEn;
        } else if (reason.equals("3")) {
            errorMessage = lang.equals("ar") ? CONFIG.topupTransactionErrorAr : CONFIG.topupTransactionErrorEn;
        } else if (reason.equals("4")) {
            errorMessage = lang.equals("ar") ? CONFIG.serviceIsInactiveMessageِAr : CONFIG.serviceIsInactiveMessageEn;
        } else if (reason.equals("5")) {
            errorMessage = lang.equals("ar") ? CONFIG.serviceIsInactiveForThisAccountMessageِAr
                    : CONFIG.serviceIsInactiveForThisAccountMessageِEn;
        } else if (reason.equals("6")) {
            errorMessage = lang.equals("ar") ? CONFIG.noTopupDenominationAmounyAr : CONFIG.noTopupDenominationAmounyEn;
        } else if (reason.equals("7")) {
            errorMessage = lang.equals("ar") ? CONFIG.topupTransactionErrorAr : CONFIG.topupTransactionErrorEn;
        } else if (reason.equals("8")) {
            errorMessage = lang.equals("ar") ? CONFIG.topupTransactionErrorAr : CONFIG.topupTransactionErrorEn;
        } else if (reason.equals("9")) {
            errorMessage = lang.equals("ar") ? CONFIG.topupTransactionErrorAr : CONFIG.topupTransactionErrorEn;
        } else if (reason.equals("10")) {
            errorMessage = lang.equals("ar") ? CONFIG.topupTransactionErrorAr : CONFIG.topupTransactionErrorEn;
        } else if (reason.equals("11")) {
            errorMessage = lang.equals("ar") ? CONFIG.topupTransactionErrorAr : CONFIG.topupTransactionErrorEn;
        } else if (reason.equals("12")) {
            errorMessage = lang.equals("ar") ? CONFIG.topupTransactionErrorAr : CONFIG.topupTransactionErrorEn;
        } else if (reason.equals("13")) {
            errorMessage = lang.equals("ar") ? CONFIG.topupTransactionErrorAr : CONFIG.topupTransactionErrorEn;
        } else if (reason.equals("14")) {
            errorMessage = lang.equals("ar") ? CONFIG.Balanc_Messagear : CONFIG.Balanc_Messageen;
        } else if (reason.equals("15")) {
            errorMessage = lang.equals("ar") ? CONFIG.topupTransactionErrorAr : CONFIG.topupTransactionErrorEn;
        } else if (reason.equals("16")) {
            errorMessage = lang.equals("ar") ? CONFIG.topupTransactionErrorAr : CONFIG.topupTransactionErrorEn;
        } else if (reason.equals("17")) {
            errorMessage = lang.equals("ar") ? CONFIG.noTopupDenominationAmounyAr : CONFIG.noTopupDenominationAmounyEn;
        } else if (reason.equals("20")) {
            errorMessage = lang.equals("ar") ? CONFIG.errorDuringCallingOperatorAr
                    : CONFIG.errorDuringCallingOperatorEn;
        } else if (reason.equals("21")) {
            errorMessage = lang.equals("ar") ? CONFIG.cantChargeToTheSameNumberAr : CONFIG.cantChargeToTheSameNumberEn;
        } else if (reason.equals("22")) {
            errorMessage = lang.equals("ar") ? CONFIG.errorDuringCallingOperator_22Ar
                    : CONFIG.errorDuringCallingOperator_22En;
        } else if (reason.equals("23")) {
            errorMessage = lang.equals("ar") ? CONFIG.topupTransactionErrorAr : CONFIG.topupTransactionErrorEn;
        } else if (reason.equals("24")) {
            errorMessage = lang.equals("ar") ? CONFIG.cantChargeToThisAccountAr : CONFIG.cantChargeToThisAccountEn;
        } else if (reason.equals("25")) {
            errorMessage = lang.equals("ar") ? CONFIG.connectionRefusedAr : CONFIG.connectionRefusedEn;
        } else if (reason.equals("28")) {
            errorMessage = lang.equals("ar") ? CONFIG.noTopupDenominationAmounyAr : CONFIG.noTopupDenominationAmounyEn;
        } else if (reason.equals("29")) {
            errorMessage = lang.equals("ar") ? CONFIG.noTopupDenominationAmounyAr
                    : CONFIG.noTopupDenominationAmounyEn;
        } else {
            errorMessage = lang.equals("ar") ? CONFIG.canntChareToTheSameNumberInSixMinsAr
                    : CONFIG.canntChareToTheSameNumberInSixMinsEn;
        }

        return errorMessage += ("[E " + reason + " ]");
    }

    public CommonTopupRepresentationDTO reprintTransaction(String lang, String token, long ledgerTrxId)
            throws Exception {

        String reprintUrl = SystemSettingsUtil.getInstance().loadProperty("orange.topup.reprint.url");
        reprintUrl = reprintUrl.replace("{transactionID}", String.valueOf(ledgerTrxId));

        MasaryManager.logger.info("Orange Topup reprint Url:- " + reprintUrl);

        try {
            this.httpClient = initHttpClient();

            HttpGet httpGet = initHttpGet(reprintUrl, token);

            CloseableHttpResponse httpResponse = executeGetRequest(this.httpClient, httpGet);

            String httpReprintEntity = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

            MasaryManager.logger.info("Orange Topup reprint Service response is : " + httpReprintEntity);

            switch (httpResponse.getStatusLine().getStatusCode()) {
                case 200:
                    return new Gson().fromJson(httpReprintEntity, CommonTopupRepresentationDTO.class);
                case 401:
                    throw new IOException(
                            lang.equals("ar") ? "غير مسموح لك باجراء العملية" : "Unauthorized to perform a transaction");
                default:
                    throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
            }

        } catch (ConnectException ex) {
            MasaryManager.logger.error("Error during calling Orange topup service on new system: " + ex, ex);
            throw new ConnectException(lang.equals("ar") ? "تعذر الاتصال بالشبكة الرجاء المحاولة لاحقا"
                    : "Connection Refused Please try later");
        } catch (Exception e) {
            MasaryManager.logger.info(e);
            throw (e);
        } finally {
            this.httpClient.close();
        }

    }

}
