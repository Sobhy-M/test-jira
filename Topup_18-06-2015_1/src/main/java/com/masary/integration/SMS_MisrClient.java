/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.masary.common.CONFIG;
import com.masary.integration.dto.SMS_MisrPackageResponse;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.SmsMisrInquiryDTO;
import com.masary.integration.dto.SmsMisrPaymentDTO;
import com.masary.integration.dto.SmsMisrRequest;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;
import java.io.IOException;
import java.net.ConnectException;
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
 * @author Ahmed Khaled
 */
public class SMS_MisrClient {

	public SMS_MisrPackageResponse[] smsMisrGetPackages(String lang, String token) throws Exception {
		String smsMisrPackageUrl = SystemSettingsUtil.getInstance().loadProperty("smsmisr.package.url");

		// String proxyAuthorizationToken = gooBackRequest.getToken();
		CloseableHttpClient httpclient = HttpClients.createDefault();

		MasaryManager.logger.info("smsMisrPackageUrl:- " + smsMisrPackageUrl);
		SMS_MisrPackageResponse[] smsMisrPackageDTO = new SMS_MisrPackageResponse[20];

		try {

			HttpGet httpGet = new HttpGet(smsMisrPackageUrl);
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("Authorization", token);
			httpGet.addHeader("deviceType", "Web");
			CloseableHttpResponse smsMisrPackagesValidateResponseHttp = httpclient.execute(httpGet);

			String smsMisrPackagesValidateResponseJson = EntityUtils
					.toString(smsMisrPackagesValidateResponseHttp.getEntity(), "UTF-8");

			MasaryManager.logger.info("SMS Misr Service response is : " + smsMisrPackagesValidateResponseJson);
			Gson gson = new Gson();

			if (smsMisrPackagesValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
				smsMisrPackageDTO = gson.fromJson(smsMisrPackagesValidateResponseJson, SMS_MisrPackageResponse[].class);
			} else {

				MasaryManager.logger.info("Failed to get Packages");
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
				standardHttpJsonResponseDTO = gson.fromJson(smsMisrPackagesValidateResponseJson,
						StandardHttpJsonResponseDTO.class);
				if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7771")) {

					throw new IOException(
							lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_ERRORAr : CONFIG.MESSAGE_SMSMISR_ERROREn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7772")) {

					throw new IOException(
							lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_ERRORAr : CONFIG.MESSAGE_SMSMISR_ERROREn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7773")) {

					throw new IOException(
							lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_ERRORAr : CONFIG.MESSAGE_SMSMISR_ERROREn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7774")) {

					throw new IOException(lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_InvalidIDAr
							: CONFIG.MESSAGE_SMSMISR_InvalidIDEn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7775")) {

					throw new IOException(
							lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_ERRORAr : CONFIG.MESSAGE_SMSMISR_ERROREn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("500")) {
					throw new ConnectException(lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_CONNECTONERRORAr
							: CONFIG.MESSAGE_SMSMISR_CONNECTONERROREn);
				}
			}

		} catch (ConnectException ex) {
			MasaryManager.logger.error(ex, ex);
			throw new ConnectException(lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_CONNECTONERRORAr
					: CONFIG.MESSAGE_SMSMISR_CONNECTONERROREn);
		} catch (IOException ex) {
			MasaryManager.logger.error(ex, ex);
			throw ex;
		} catch (ParseException ex) {
			MasaryManager.logger.error(ex, ex);
			throw ex;
		} catch (JsonSyntaxException ex) {
			MasaryManager.logger.error(ex, ex);
			throw ex;
		}

		return smsMisrPackageDTO;
	}

	public SmsMisrInquiryDTO smsMisrInquiry(SmsMisrRequest smsMisrRequest, String lang, String token) throws Exception {
		String smsMisrInquiryUrl = SystemSettingsUtil.getInstance().loadProperty("smsmisr.inquiry.url");
		smsMisrInquiryUrl = smsMisrInquiryUrl.replace("{customerCode}", smsMisrRequest.getCustomerCode());
		smsMisrInquiryUrl = smsMisrInquiryUrl.replace("{packageId}", String.valueOf(smsMisrRequest.getPackageId()));

		// String proxyAuthorizationToken = gooBackRequest.getToken();
		CloseableHttpClient httpclient = HttpClients.createDefault();

		MasaryManager.logger.info("smsMisrInquiryUrl:- " + smsMisrInquiryUrl);
		SmsMisrInquiryDTO smsMisrInquiryResponse = new SmsMisrInquiryDTO();

		try {

			HttpGet httpGet = new HttpGet(smsMisrInquiryUrl);
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("Authorization", token);
			httpGet.addHeader("deviceType", "Web");
			CloseableHttpResponse smsMisrInquiryValidateResponseHttp = httpclient.execute(httpGet);

			String smsMisrInquiryValidateResponseJson = EntityUtils
					.toString(smsMisrInquiryValidateResponseHttp.getEntity(), "UTF-8");

			MasaryManager.logger.info("SMS Misr Service response is : " + smsMisrInquiryValidateResponseJson);
			Gson gson = new Gson();

			if (smsMisrInquiryValidateResponseHttp.getStatusLine().getStatusCode() == 200) {
				smsMisrInquiryResponse = gson.fromJson(smsMisrInquiryValidateResponseJson, SmsMisrInquiryDTO.class);
			} else {

				MasaryManager.logger.info("Failed to get Packages");
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
				standardHttpJsonResponseDTO = gson.fromJson(smsMisrInquiryValidateResponseJson,
						StandardHttpJsonResponseDTO.class);
				if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7771")) {

					throw new IOException(
							lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_ERRORAr : CONFIG.MESSAGE_SMSMISR_ERROREn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7772")) {

					throw new IOException(
							lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_ERRORAr : CONFIG.MESSAGE_SMSMISR_ERROREn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7773")) {

					throw new IOException(
							lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_ERRORAr : CONFIG.MESSAGE_SMSMISR_ERROREn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7774")) {

					throw new IOException(lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_InvalidIDAr
							: CONFIG.MESSAGE_SMSMISR_InvalidIDEn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7775")) {

					throw new IOException(
							lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_ERRORAr : CONFIG.MESSAGE_SMSMISR_ERROREn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("500")) {
					throw new ConnectException(lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_CONNECTONERRORAr
							: CONFIG.MESSAGE_SMSMISR_CONNECTONERROREn);
				}
			}

		} catch (ConnectException ex) {
			MasaryManager.logger.error(ex, ex);
			throw new ConnectException(lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_CONNECTONERRORAr
					: CONFIG.MESSAGE_SMSMISR_CONNECTONERROREn);
		} catch (IOException ex) {
			MasaryManager.logger.error(ex, ex);
			throw ex;
		} catch (ParseException ex) {
			MasaryManager.logger.error(ex, ex);
			throw ex;
		} catch (JsonSyntaxException ex) {
			MasaryManager.logger.error(ex, ex);
			throw ex;
		}

		return smsMisrInquiryResponse;
	}

	public SmsMisrPaymentDTO smsMisrPayment(SmsMisrRequest smsMisrRequest, String lang, String token) throws Exception {

		String smsMisrPaymentUrl = SystemSettingsUtil.getInstance().loadProperty("smsmisr.payment.url");

		MasaryManager.logger.info("Sms Misr Payment Url:- " + smsMisrPaymentUrl);

		CloseableHttpClient httpclient = HttpClients.createDefault();

		SmsMisrPaymentDTO smsMisrPaymentResponse = new SmsMisrPaymentDTO();

		try {
			HttpPost httpPost = new HttpPost(smsMisrPaymentUrl);
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.addHeader("Authorization", token);
			httpPost.addHeader("deviceType", "Web");
			MasaryManager.logger.info(
					"Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");
			Gson gson = new Gson();

			String urlParameters = gson.toJson(smsMisrRequest, SmsMisrRequest.class);
			StringEntity params = new StringEntity(urlParameters);
			httpPost.setEntity(params);

			CloseableHttpResponse smsMisrPaymentResponseHttp = httpclient.execute(httpPost);

			String smsMisrPaymentResponseJson = EntityUtils.toString(smsMisrPaymentResponseHttp.getEntity(), "UTF-8");
			MasaryManager.logger.info("Sms Misr Service response is : " + smsMisrPaymentResponseJson);

			if (smsMisrPaymentResponseHttp.getStatusLine().getStatusCode() == 200) {
				smsMisrPaymentResponse = gson.fromJson(smsMisrPaymentResponseJson, SmsMisrPaymentDTO.class);
				MasaryManager.logger.info("Sms Misr Payment Response:- " + smsMisrPaymentResponse);
			} else {
				MasaryManager.logger.error("Failed to pay the sent amount");
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
				standardHttpJsonResponseDTO = gson.fromJson(smsMisrPaymentResponseJson,
						StandardHttpJsonResponseDTO.class);

				if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7771")) {

					throw new IOException(
							lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_ERRORAr : CONFIG.MESSAGE_SMSMISR_ERROREn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7772")) {

					throw new IOException(
							lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_ERRORAr : CONFIG.MESSAGE_SMSMISR_ERROREn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7773")) {

					throw new IOException(
							lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_ERRORAr : CONFIG.MESSAGE_SMSMISR_ERROREn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7774")) {

					throw new IOException(lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_InvalidIDAr
							: CONFIG.MESSAGE_SMSMISR_InvalidIDEn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("424")
						&& standardHttpJsonResponseDTO.getMessage().equals("7775")) {

					throw new IOException(
							lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_ERRORAr : CONFIG.MESSAGE_SMSMISR_ERROREn);

				} else if (standardHttpJsonResponseDTO.getStatus().equals("500")) {
					throw new ConnectException(lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_CONNECTONERRORAr
							: CONFIG.MESSAGE_SMSMISR_CONNECTONERROREn);
				}
			}

		} catch (ConnectException e) {
			MasaryManager.logger.error(e, e);
			throw new IOException(lang.equals("ar") ? CONFIG.MESSAGE_SMSMISR_CONNECTONERRORAr
					: CONFIG.MESSAGE_SMSMISR_CONNECTONERROREn);
		} catch (IOException ex) {

			MasaryManager.logger.error(ex, ex);
			throw ex;
		} catch (ParseException ex) {
			MasaryManager.logger.error(ex, ex);
			throw ex;
		} catch (JsonSyntaxException ex) {
			MasaryManager.logger.error(ex, ex);
			throw ex;
		} finally {
			httpclient.close();
		}
		return smsMisrPaymentResponse;
	}

}
