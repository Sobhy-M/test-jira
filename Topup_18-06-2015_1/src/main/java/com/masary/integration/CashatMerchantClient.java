package com.masary.integration;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import com.masary.common.CONFIG;
import com.masary.common.CommonCalls;
import com.masary.controlers.CashatMerchant.CashatMerchantProperties;
import com.masary.integration.dto.CashatCompniesDTO;
import com.masary.integration.dto.CashatInquiryRequest;
import com.masary.integration.dto.CashatInquiryResponse;
import com.masary.integration.dto.CashatPaymentRequest;
import com.masary.integration.dto.CashatPaymentResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;

public class CashatMerchantClient extends CommonCalls {
	
	private CloseableHttpClient httpclient;
	private HttpGet httpGet;
	private Logger logger;

	public CashatInquiryResponse cashatInquiry(CashatInquiryRequest cashatInquiryRequest, String lang, String token)
			throws Exception {

		String cashatUrl = loadUrlProperty("CashatMerchant.inquery.url");
		cashatUrl = prepareUrl(cashatInquiryRequest, cashatUrl);
		this.logger = initLogger();
		
		try {
			this.httpclient = initHttpClient();
			this.httpGet = initHttpGet(cashatUrl, token);
			CloseableHttpResponse cashatValidateResponseHttp = executeGetRequest(this.httpclient, this.httpGet);
			String cashatValidateResponseJson = getJsonFromResponse(cashatValidateResponseHttp);
			switch (getStatusCode(cashatValidateResponseHttp)) {
			case 200:
				return json2Object(cashatValidateResponseJson, CashatInquiryResponse.class);
			default:
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(cashatValidateResponseJson,StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
				throw new Exception(errorMessage);
			}
		} catch (Exception e) {
			logger.error("Exception" + e.getMessage(), e);
			throw (e);
		} finally {
			httpclient.close();
		}

	}

	protected String prepareUrl(CashatInquiryRequest cashatInquiryRequest, String cashatUrl) {

		cashatUrl = cashatUrl.replace("{companyId}", String.valueOf(cashatInquiryRequest.getCompanyID()));
		cashatUrl = cashatUrl.replace("{repNationalId}", cashatInquiryRequest.getRepNationalId());
		cashatUrl = cashatUrl.replace("{amount}", String.valueOf(cashatInquiryRequest.getAmount()));

		return cashatUrl;
	}
	
	public CashatPaymentResponse cashatPayment(CashatPaymentRequest cashatPaymentRequest, String lang, String token)
			throws Exception {

		String cashatUrl = loadUrlProperty("CashatMerchant.payment.url");
		this.logger = initLogger();

		try {
			this.httpclient = initHttpClient();
			String urlParameters = object2Json(cashatPaymentRequest, CashatPaymentRequest.class);
			HttpPost httpPost = initHttpPost(urlParameters, cashatUrl, token);
			CloseableHttpResponse cashatPaymentResponseHttp = executePostRequest(httpclient, httpPost);
			String cashatPaymentResponseJson = getJsonFromResponse(cashatPaymentResponseHttp);
			switch (getStatusCode(cashatPaymentResponseHttp)) {
			case 200:
				return json2Object(cashatPaymentResponseJson, CashatPaymentResponse.class);
			default:
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(cashatPaymentResponseJson,StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
				throw new Exception(errorMessage);
			}
		} catch (Exception e) {
			logger.error("Exception" + e.getMessage(),e);
			throw (e);
		} finally {
			httpclient.close();
		}

	}

	
	public CashatCompniesDTO getCompanies(String lang, String token) throws Exception {

		String cashatCompaniesurl = loadUrlProperty("CashatMerchant.company.url");
		this.logger = initLogger();

		try {
			this.httpclient = initHttpClient();
			this.httpGet = initHttpGet(cashatCompaniesurl, token);
			CloseableHttpResponse cashatValidateResponseHttp = executeGetRequest(this.httpclient, this.httpGet);
			String cashatCompaniesResponseJson = getJsonFromResponse(cashatValidateResponseHttp);
			switch (getStatusCode(cashatValidateResponseHttp)) {
			case 200:
                return json2Object(cashatCompaniesResponseJson, CashatCompniesDTO.class);
				default:
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(cashatCompaniesResponseJson,StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
				throw new Exception(errorMessage);
			}
		} catch (Exception e) {
			logger.error("Exception" + e.getMessage(), e);
			throw (e);
		} finally {
			httpclient.close();
		}


	}
	
	
	public CashatPaymentResponse cashatReprint(String ledgerTrxId, String lang, String token, String ip)
			throws Exception {

		this.logger = initLogger();
		String rePrintUrl = loadUrlProperty("CashatMerchant.reprint.url");
		rePrintUrl = prepareReprintURL(ledgerTrxId, rePrintUrl);
		this.httpclient = initHttpClient();
		
		try {
			this.httpGet = initHttpGet(rePrintUrl, token);
			CloseableHttpResponse response = executeGetRequest(this.httpclient, this.httpGet);
			String json = getJsonFromResponse(response);
			switch (getStatusCode(response)) {
			case 200:
				return json2Object(json, CashatPaymentResponse.class);
			default:
				StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(json,StandardHttpJsonResponseDTO.class);
				String errorMessage = getErrorMessage(lang, standardHttpJsonResponseDTO.getMessage());
				throw new Exception(errorMessage);
			}

		} catch (Exception e) {
			logger.error("Exception" + e.getMessage(),e);
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
		if (message.equals("44401001")) {
			errorMessage = (lang.equals("ar") ? CashatMerchantProperties.errorCashatNotFoundAr : CashatMerchantProperties.errorCashatNotFoundEn);
		}
		else if (message.equals("44401002")) {
			errorMessage = (lang.equals("ar") ? CashatMerchantProperties.errorCashatRepNotFoundAr : CashatMerchantProperties.errorCashatRepNotFoundEn);
		}
		else if (message.equals("44401003")) {
			errorMessage = (lang.equals("ar") ? CashatMerchantProperties.errorCashatRepNotFoundAr : CashatMerchantProperties.errorCashatRepNotFoundEn2);
		}
		else if (message.equals("44401004")) {
			errorMessage = (lang.equals("ar") ? CashatMerchantProperties.errorCashatRepNotFoundAr : CashatMerchantProperties.errorCashatRepNotFoundEn);
		}
		else if (message.equals("44401005")) {
			errorMessage = (lang.equals("ar") ? CashatMerchantProperties.errorCashatCompanyNotFoundAr : CashatMerchantProperties.errorCashatNotFoundEn);
		}
		else if (message.equals("10")) {
			errorMessage = (lang.equals("ar") ? CashatMerchantProperties.errorCashatBadParameterAr : CashatMerchantProperties.errorCashatBadParameterEn);
		}
		else if (message.equals("20")) {
			return "20";
		}
		else if (message.equals("14")) {
			errorMessage = (lang.equals("ar") ? CONFIG.Balanc_Messagear : CONFIG.Balanc_Messageen);
		}
		else if (message.equals("1")) {
			errorMessage = (lang.equals("ar") ? CONFIG.CashatAr : CONFIG.CashatEn);
		}
		else {
			errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
		}
		return errorMessage += ("[E " + message + " ]");
	}


}
