package com.masary.integration;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.masary.common.CONFIG;
import com.masary.controlers.GoBus.GoBusProperties;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.GoBusInquiryRepresentation;
import com.masary.integration.dto.GoBusPaymentRepresentation;
import com.masary.integration.dto.GoBusPaymentData;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;

public class GoBusHttpClient {

	private CloseableHttpClient httpClient;

	public GoBusHttpClient(CloseableHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public GoBusPaymentRepresentation doGoBusPayment(GoBusPaymentData goBusRequest) throws Exception {

		MasaryManager.logger.info(goBusRequest.getToken());

		String etisalatTopupUrl = SystemSettingsUtil.getInstance().loadProperty("go.bus.payment.url");
		CloseableHttpClient httpclient = this.httpClient;
		HttpPost httpPost = new HttpPost(etisalatTopupUrl);
		Gson gson = new Gson();

		GoBusPaymentRepresentation topupresponse = new GoBusPaymentRepresentation();

		GoBusPaymentDTO goBusPaymentDTO = new GoBusPaymentDTO(goBusRequest.getValidationId());

		String urlParameters = gson.toJson(goBusPaymentDTO, GoBusPaymentDTO.class);

		String encoded = goBusRequest.getToken();

		StringEntity params = new StringEntity(urlParameters);
		httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.addHeader("Authorization", encoded);
		httpPost.addHeader("ip", goBusRequest.getIp());
		httpPost.addHeader("deviceType", "Web");
		httpPost.setEntity(params);

		try {
			CloseableHttpResponse response = httpclient.execute(httpPost);

			String json = EntityUtils.toString(response.getEntity(), "UTF-8");

			MasaryManager.logger.info(response.getStatusLine());
			MasaryManager.logger.info("JSON: " + json);

			if (response.getStatusLine().getStatusCode() == 200) {
				topupresponse = gson.fromJson(json, GoBusPaymentRepresentation.class);
				MasaryManager.logger.info("Success transaction etisalat integration  " + topupresponse);

			} else {
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
				standardHttpJsonResponseDTO = gson.fromJson(json, StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(goBusRequest.getLang(), standardHttpJsonResponseDTO.getMessage());
				throw new Exception(errorMessage);

			}

			return topupresponse;

		} catch (Exception e) {
			MasaryManager.logger.error("Exception" + e.getMessage());
			MasaryManager.logger.error(e);
			throw (e);
		} finally {
			httpclient.close();
		}
	}

	public GoBusPaymentRepresentation doGoBusReprint(String trxId, String lang, String token, String ip)
			throws Exception {

		MasaryManager.logger.info(token);

		String rePrintUrl = SystemSettingsUtil.getInstance().loadProperty("go.bus.reprint.url");
		rePrintUrl = rePrintUrl.replace("{trxId}", trxId);

		CloseableHttpClient httpclient = this.httpClient;
		HttpGet httpGet = new HttpGet(rePrintUrl);
		Gson gson = new Gson();

		GoBusPaymentRepresentation topupresponse = new GoBusPaymentRepresentation();

		try {
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("Authorization", token);
			httpGet.addHeader("deviceType", "Web");

			CloseableHttpResponse response = httpclient.execute(httpGet);

			String json = EntityUtils.toString(response.getEntity(), "UTF-8");

			MasaryManager.logger.info(response.getStatusLine());
			MasaryManager.logger.info("JSON: " + json);

			if (response.getStatusLine().toString().contains("200")) {
				topupresponse = gson.fromJson(json, GoBusPaymentRepresentation.class);
				MasaryManager.logger.info("Success transaction etisalat integration  " + topupresponse);

			} else {
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
				standardHttpJsonResponseDTO = gson.fromJson(json, StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
				throw new Exception(errorMessage);

			}

			return topupresponse;

		} catch (Exception e) {
			MasaryManager.logger.error("Exception" + e.getMessage());
			MasaryManager.logger.error(e);
			throw (e);
		} finally {
			httpclient.close();
		}
	}

	public GoBusInquiryRepresentation doGoBusInquery(GoBusInqueryDTO goBusInqueryDTO) throws Exception {

		String requestUrl = SystemSettingsUtil.getInstance().loadProperty("go.bus.inquery.url");
		requestUrl = requestUrl.replace("{referanceId}", goBusInqueryDTO.getReferanceNum());

		CloseableHttpClient httpclient = this.httpClient;

		GoBusInquiryRepresentation inqueryRepresentation = new GoBusInquiryRepresentation();

		try {
			HttpGet httpGet = new HttpGet(requestUrl);
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("Authorization", goBusInqueryDTO.getToken());
			httpGet.addHeader("deviceType", "Web");

			MasaryManager.logger.info("Etisalat Topup Denomination URL" + requestUrl);

			CloseableHttpResponse inqueryHttpRepresentation = httpclient.execute(httpGet);

			String inqueryHttpRepresentationJson = EntityUtils.toString(inqueryHttpRepresentation.getEntity(), "UTF-8");

			MasaryManager.logger.info("Etisalat Topup Denominations response is : " + inqueryHttpRepresentationJson);

			Gson gson = new Gson();

			if (inqueryHttpRepresentation.getStatusLine().getStatusCode() == 200) {
				inqueryRepresentation = gson.fromJson(inqueryHttpRepresentationJson, GoBusInquiryRepresentation.class);

			} else {
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
				standardHttpJsonResponseDTO = gson.fromJson(inqueryHttpRepresentationJson,
						StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(goBusInqueryDTO.getLang(),
						standardHttpJsonResponseDTO.getMessage());
				throw new Exception(errorMessage);
			}

		} catch (Exception e) {
			throw e;
		}

		return inqueryRepresentation;

	}

	private String getErrorMessage(String lang, String reason) {

		String errorMessage = "";

		if (reason.equals("1")) {
			errorMessage = lang.equals("ar") ? "هذه المحفظة غير مفعلة، برجاء إعادة تفعيلها ثم المحاولة مرة أخرى"
					: "Account Is Inactive";
		} else if (reason.equals("6401")) {
			errorMessage = lang.equals("ar") ? GoBusProperties.errorCode6401Ar : GoBusProperties.errorCode6401En;

		} else if (reason.equals("6402")) {
			
			errorMessage = lang.equals("ar") ? GoBusProperties.errorCode6402Ar : GoBusProperties.errorCode6402En;

		} else if (reason.equals("6403")) {
			errorMessage = lang.equals("ar") ? GoBusProperties.errorCode6403Ar : GoBusProperties.errorCode6403En;

		} else if (reason.equals("910")) {
			errorMessage = lang.equals("ar") ? GoBusProperties.errorCode910Ar : GoBusProperties.errorCode910En;

		} else {
			errorMessage = lang.equals("ar") ? CONFIG.ESEDInternalServiceErrorAr : CONFIG.ESEDInternalServiceErrorEn;
		}

		return errorMessage += ("[E" + reason + "]");

	}
}
