package com.masary.integration;

import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import com.masary.common.CONFIG;
import com.masary.common.CommonCalls;
import com.google.gson.reflect.TypeToken;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.integration.dto.TEData_Inquiry_Request;
import com.masary.integration.dto.TEData_Inquiry_Response;
import com.masary.integration.dto.TEData_Payment_Request;
import com.masary.integration.dto.TEData_Payment_Response;
import com.masary.integration.dto.TedataDenominationRepresentation;

public class TEDataClient extends CommonCalls {

	private CloseableHttpClient httpclient;
	private HttpGet httpGet;
	private Logger logger;

	public TEData_Inquiry_Response tedataInquiry(TEData_Inquiry_Request tedataInquiryRequest, String lang, String token)
			throws Exception {

		String tedataUrl = loadUrlProperty("Tedata.Inquiry.url");

		tedataUrl = prepareUrl(tedataInquiryRequest.getLandline(), tedataInquiryRequest.getDenominationId(), tedataUrl);

		this.logger = initLogger();
		logger.info("tedata Inquiry Url:- " + tedataUrl);


		try {
			this.httpclient = initHttpClient();
			this.httpGet = initHttpGet(tedataUrl, token);

			logger.info("tedataUrl:- " + tedataUrl);

			CloseableHttpResponse TedataValidateResponseHttp = executeGetRequest(this.httpclient, this.httpGet);

			String TedataValidateResponseJson = getJsonFromResponse(TedataValidateResponseHttp);

			logger.info("tedata Service response is : " + TedataValidateResponseJson);

			switch (getStatusCode(TedataValidateResponseHttp)) {
			case 200:
				return json2Object(TedataValidateResponseJson, TEData_Inquiry_Response.class);
			default:
				logger.info("Failed to get commission");
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(TedataValidateResponseJson,
						StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
				throw new Exception(errorMessage);
			}
		} catch (Exception e) {
			logger.error("Exception" + e.getMessage());
			logger.error(e);
			throw (e);
		} finally {
			httpclient.close();
		}

	}

	protected String prepareUrl(String landline, String denominationId, String tedataUrl) {
		tedataUrl = tedataUrl.replace("{landline}", landline);
		tedataUrl = tedataUrl.replace("{denominationId}", denominationId);
		return tedataUrl;
	}

	public TEData_Payment_Response tedataPayment(TEData_Payment_Request tedataPaymentRequest, String lang, String token)
			throws Exception {

		String tedataUrl = loadUrlProperty("Tedata.Payment.url");
		this.logger = initLogger();
		logger.info("Tedata Payment Url:- " + tedataUrl);

		try {

			this.httpclient = initHttpClient();


			logger.info(
					"Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

			String urlParameters = object2Json(tedataPaymentRequest, TEData_Payment_Request.class);
			HttpPost httpPost = initHttpPost(urlParameters, tedataUrl, token);

			CloseableHttpResponse tedataPaymentResponseHttp = executePostRequest(httpclient, httpPost);

			String tedataPaymentResponseJson = getJsonFromResponse(tedataPaymentResponseHttp);
			logger.info("Tedata Service response is : " + tedataPaymentResponseJson);

			switch (getStatusCode(tedataPaymentResponseHttp)) {
			case 200:
				return json2Object(tedataPaymentResponseJson, TEData_Payment_Response.class);
			default:
				logger.info("Failed to get commission");
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(tedataPaymentResponseJson,
						StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
				throw new Exception(errorMessage);
			}
		} catch (Exception e) {
			logger.error("Exception" + e.getMessage());
			logger.error(e);
			throw (e);
		} finally {
			httpclient.close();
		}

	}

	public List<TedataDenominationRepresentation> getDenomination(String lang, String token) throws Exception {

		String tedataDenominationurl = loadUrlProperty("Tedata.Denomination.url");
		this.logger = initLogger();
		logger.info("tedata Denomination url Url:- " + tedataDenominationurl);

		try {

			this.httpclient = initHttpClient();
			this.httpGet = initHttpGet(tedataDenominationurl, token);

			CloseableHttpResponse tedataValidateResponseHttp = executeGetRequest(this.httpclient, this.httpGet);

			String tedataCategoriesResponseJson = getJsonFromResponse(tedataValidateResponseHttp);

			logger.info("tedata Categories Service response is : " + tedataCategoriesResponseJson);

			switch (getStatusCode(tedataValidateResponseHttp)) {
			case 200:
				return json2Object(tedataCategoriesResponseJson,
						new TypeToken<List<TedataDenominationRepresentation>>() {
						}.getType());
			default:
				logger.info("Failed to get commission");
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(tedataCategoriesResponseJson,
						StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
				throw new Exception(errorMessage);
			}
		} catch (Exception e) {
			logger.error("Exception" + e.getMessage());
			logger.error(e);
			throw (e);
		} finally {
			httpclient.close();
		}


	}

	public TEData_Payment_Response tedataReprint(String ledgerTrxId, String lang, String token, String ip)
			throws Exception {

		this.logger = initLogger();

		logger.info(token);

		String rePrintUrl = loadUrlProperty("Tedata.Reprint.url");
		rePrintUrl = prepareReprintURL(ledgerTrxId, rePrintUrl);
		this.httpclient = initHttpClient();

		try {
			this.httpGet = initHttpGet(rePrintUrl, token);

			CloseableHttpResponse response = executeGetRequest(this.httpclient, this.httpGet);

			String json = getJsonFromResponse(response);

			switch (getStatusCode(response)) {
			case 200:
				return json2Object(json, TEData_Payment_Response.class);
			default:
				logger.info("Failed to get commission");
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(json,
						StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
				throw new Exception(errorMessage);
			}

		} catch (Exception e) {
			logger.error("Exception" + e.getMessage());
			logger.error(e);
			throw (e);
		} finally {
			httpclient.close();
		}
	}

	protected String prepareReprintURL(String ledgerTrxId, String rePrintUrl) {
		return rePrintUrl.replace("{ledgerTrxId}", ledgerTrxId);
	}

	protected String getErrorMessage(String lang, String message) {
		String errorMessage = "";
		if (message.equals("20061")) {
			errorMessage = (lang.equals("ar") ? CONFIG.errorTedataproviderAr : CONFIG.errorTedataproviderEn);
		}
		if (message.equals("20062")) {
			errorMessage = (lang.equals("ar") ? CONFIG.errorTedatapackageAr : CONFIG.errorTedatapackageEn);
		}
		if (message.equals("20064")) {
			errorMessage = (lang.equals("ar") ? CONFIG.errorTedataoperationAr : CONFIG.errorTedataoperationEn);
		}
		if (message.equals("20065")) {
			errorMessage = (lang.equals("ar") ? CONFIG.errorTedatachargeAr : CONFIG.errorTedatachargeEn);
		}
		if (message.equals("20066")) {
			errorMessage = (lang.equals("ar") ? CONFIG.errorTedataoperationAr : CONFIG.errorTedataoperationEn);
		}
		if (message.equals("20067")) {
			errorMessage = (lang.equals("ar") ? CONFIG.errorTedataoperationAr : CONFIG.errorTedataoperationEn);
		}
		if (message.equals("20068")) {
			errorMessage = (lang.equals("ar") ? CONFIG.errorTedatachargeAr : CONFIG.errorTedatachargeEn);
		}
		if (message.equals("20069")) {
			errorMessage = (lang.equals("ar") ? CONFIG.errorTedataInvalidAr : CONFIG.errorTedataInvalidEn);
		}
		if (message.equals("200610")) {
			errorMessage = (lang.equals("ar") ? CONFIG.errorTedatapackageAr : CONFIG.errorTedatapackageEn);
		}
		if (message.equals("200611")) {
			errorMessage = (lang.equals("ar") ? CONFIG.errorTedataInvalidAr : CONFIG.errorTedataInvalidEn);
		}
		if (message.equals("200612")) {
			errorMessage = (lang.equals("ar") ? CONFIG.errorTedataoperationAr : CONFIG.errorTedataoperationEn);
		}

		else {
			errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
		}
		return errorMessage += ("[E " + message + " ]");
	}

}
