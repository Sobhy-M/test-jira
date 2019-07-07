package com.masary.integration;

import java.io.IOException;

import org.apache.http.ParseException;
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
import com.masary.integration.dto.SECIRequestDTO;
import com.masary.integration.dto.SECIResponseDTO;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;

public class SeciClient {

	public SECIResponseDTO SECIPayment(SECIRequestDTO SECIRequest, String lang, String token) throws Exception {

		String SECIUrl = SystemSettingsUtil.getInstance().loadProperty("SECI.payment.url");
		MasaryManager.logger.info("SECI Payment Url:- " + SECIUrl + SECIRequest);

		CloseableHttpClient httpclient = HttpClients.createDefault();

		SECIResponseDTO SECIPaymentResponse = new SECIResponseDTO();

		try {
			HttpPost httpPost = new HttpPost(SECIUrl);
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.addHeader("Authorization", token);
			httpPost.addHeader("deviceType", "Web");
			// httpPost.addHeader("ip", ip);
			MasaryManager.logger.info(
					"Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

			Gson gson = new Gson();
			String urlParameters = gson.toJson(SECIRequest, SECIRequestDTO.class);
			StringEntity params = new StringEntity(urlParameters);
			httpPost.setEntity(params);

			CloseableHttpResponse SECIPaymentResponseHttp = httpclient.execute(httpPost);

			String SECIPaymentResponseJson = EntityUtils.toString(SECIPaymentResponseHttp.getEntity(), "UTF-8");
			MasaryManager.logger.info("SECI Service response is : " + SECIPaymentResponseJson);

			if (SECIPaymentResponseHttp.getStatusLine().getStatusCode() == 200) {
				SECIPaymentResponse = gson.fromJson(SECIPaymentResponseJson, SECIResponseDTO.class);
				MasaryManager.logger.info("SECI Payment Response:- " + SECIPaymentResponse);
			} else {
				MasaryManager.logger.error("Failed to pay the sent amount");
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
				standardHttpJsonResponseDTO = gson.fromJson(SECIPaymentResponseJson, StandardHttpJsonResponseDTO.class);
					throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
				
			}

		} catch (Exception ex) {
			MasaryManager.logger.error("Error during calling SECI  service on new system: " + ex);
			throw (ex);
		} finally {
			httpclient.close();
		}
		return SECIPaymentResponse;
	}
}
