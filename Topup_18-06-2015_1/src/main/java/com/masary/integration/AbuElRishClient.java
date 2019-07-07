package com.masary.integration;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.AbuElRishRequestDTO;
import com.masary.integration.dto.AbuElRishResponseDTO;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class AbuElRishClient {

	public AbuElRishResponseDTO AbuElRishPayment(AbuElRishRequestDTO abuelrishRequest, String lang, String token)
			throws Exception {
		String AbuElRishUrl = SystemSettingsUtil.getInstance().loadProperty("AbuElRish.Payment.url");
		MasaryManager.logger.info("AbuElRish Payment Url:- " + AbuElRishUrl + abuelrishRequest);

		CloseableHttpClient httpclient = HttpClients.createDefault();

		AbuElRishResponseDTO AbuElRishPaymentResponse = new AbuElRishResponseDTO();

		try {
			HttpPost httpPost = new HttpPost(AbuElRishUrl);
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.addHeader("Authorization", token);
			httpPost.addHeader("deviceType", "Web");
			MasaryManager.logger.info(
					"Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

			Gson gson = new Gson();
			String urlParameters = gson.toJson(abuelrishRequest, AbuElRishRequestDTO.class);
			StringEntity params = new StringEntity(urlParameters);
			httpPost.setEntity(params);

			CloseableHttpResponse AbuElRishPaymentResponseHttp = httpclient.execute(httpPost);

			String AbuElRishPaymentResponseJson = EntityUtils.toString(AbuElRishPaymentResponseHttp.getEntity(),
					"UTF-8");
			MasaryManager.logger.info("AbuElRish Service response is : " + AbuElRishPaymentResponseJson);

			if (AbuElRishPaymentResponseHttp.getStatusLine().getStatusCode() == 200) {
				AbuElRishPaymentResponse = gson.fromJson(AbuElRishPaymentResponseJson, AbuElRishResponseDTO.class);
				MasaryManager.logger.info("AbuElRish Payment Response:- " + AbuElRishPaymentResponse);
			} else {
				MasaryManager.logger.error("Failed to pay the sent amount");

				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
				standardHttpJsonResponseDTO = gson.fromJson(AbuElRishPaymentResponseJson,
						StandardHttpJsonResponseDTO.class);
				if (standardHttpJsonResponseDTO.getStatus().equals("5991")) {
					throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionAboElrishAr
							: CONFIG.errorTransactionAboElrishEn);
				} else {
					throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
				}
			}

		} catch (Exception ex) {
			MasaryManager.logger.error("Error during calling AbuElRish  service on new system: " + ex);
			throw (ex);
		} finally {
			httpclient.close();
		}
		return AbuElRishPaymentResponse;
	}
}
