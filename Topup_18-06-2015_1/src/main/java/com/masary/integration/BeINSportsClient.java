package com.masary.integration;



import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;

import com.masary.common.CONFIG;
import com.masary.common.CommonCalls;
import com.masary.integration.dto.BeINSportsInquiryRequestDTO;
import com.masary.integration.dto.BeINSportsInquiryResponseDTO;
import com.masary.integration.dto.BeINSportsPaymentRequestDTO;
import com.masary.integration.dto.BeINSportsPaymentResponseDTO;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;

public class BeINSportsClient extends CommonCalls{
	
	private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private Logger logger;
	
	public  BeINSportsInquiryResponseDTO beINSportsInquiry(BeINSportsInquiryRequestDTO beINSportsinquiryRequest,String lang, String token) throws Exception{
		
        String beINSportsUrl =loadUrlProperty("BeINSports.inquiry.url");
       
        beINSportsUrl = prepareUrl(beINSportsinquiryRequest.getReferenceNumber(), beINSportsinquiryRequest.getMsisdn(), beINSportsUrl);
        
        this.logger = initLogger();

        logger.info("beINSports Inquiry Url:- " + beINSportsUrl);

        try {
        	this.httpclient = initHttpClient();
            
        	this.httpGet = initHttpGet(beINSportsUrl, token);

            logger.info("beINSportsUrl:- " + beINSportsUrl);

            CloseableHttpResponse beINSportsValidateResponseHttp = executeGetRequest(this.httpclient, this.httpGet);

            String beINSportsValidateResponseJson = getJsonFromResponse(beINSportsValidateResponseHttp);

            logger.info("beINSports Service response is : " + beINSportsValidateResponseJson);

            
            switch (getStatusCode(beINSportsValidateResponseHttp)) {
            case 200:
                return json2Object(beINSportsValidateResponseJson, BeINSportsInquiryResponseDTO.class);
            case 401:
                throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
            default:
            	logger.info("Failed to get commission");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(beINSportsValidateResponseJson, StandardHttpJsonResponseDTO.class);
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



	protected String getErrorMessage(String lang, String message) {
		String errorMessage = "";
        if (message.equals("2121")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsAr : CONFIG.errorTransactionBeinSportsEn);
        }
        if (message.equals("2123")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE2Ar : CONFIG.errorTransactionBeinSportsE2En);
        }if (message.equals("2124")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        }if (message.equals("2125")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        }if (message.equals("2126")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        }if (message.equals("2127")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE2Ar : CONFIG.errorTransactionBeinSportsE2En);
        }if (message.equals("2128")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE4Ar : CONFIG.errorTransactionBeinSportsE4En);
        }if (message.equals("2129")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        }if (message.equals("21210")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        }if (message.equals("21211")) {
        	errorMessage =(lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        }if (message.equals("21212")) {
        	errorMessage =(lang.equals("ar") ? CONFIG.errorTransactionBeinSportsE3Ar : CONFIG.errorTransactionBeinSportsE3En);
        }
          
        else {
        	errorMessage =(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
        }
        return errorMessage += ("[E"+message+"]");
	}



	protected String prepareUrl(String referenceNumber, String msisdn, String beINSportsUrl) {
        beINSportsUrl = beINSportsUrl.replace("{referenceNumber}", referenceNumber);
        beINSportsUrl = beINSportsUrl.replace("{msisdn}", msisdn);
		return beINSportsUrl;
	}
	
	
	
	public  BeINSportsPaymentResponseDTO beINSportsPayment (BeINSportsPaymentRequestDTO beINSportsPaymentRequest, String lang, String token) throws Exception
	{
	
		 String beINSportsUrl = loadUrlProperty("BeINSports.payment.url");
		 
	        this.logger = initLogger();

	        logger.info("beINSports Payment Url:- " + beINSportsUrl + beINSportsPaymentRequest);
	        
	       
	        try {
	        	 this.httpclient = initHttpClient();
	         	
	         	String urlParameters = object2Json(beINSportsPaymentRequest, BeINSportsPaymentRequestDTO.class);

	        	this.httpGet = initHttpGet(beINSportsUrl, token);

	            logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

	            HttpPost httpPost = initHttpPost(urlParameters, beINSportsUrl, token);

	            CloseableHttpResponse beINSportsPaymentResponseHttp = executePostRequest(httpclient, httpPost);

	            String beINSportsPaymentResponseJson = getJsonFromResponse(beINSportsPaymentResponseHttp);
	            logger.info("beINSports Service response is : " + beINSportsPaymentResponseJson);

	            switch (getStatusCode(beINSportsPaymentResponseHttp)) {
	            case 200:
	                return json2Object(beINSportsPaymentResponseJson, BeINSportsPaymentResponseDTO.class);
	            case 401:
	                throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
	            default:
	            	logger.info("Failed to get commission");
	                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(beINSportsPaymentResponseJson, StandardHttpJsonResponseDTO.class);
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
	

	public BeINSportsPaymentResponseDTO beINSportsReprint(String ledgerTrxId,String lang,String token,String ip) throws Exception {

        this.logger = initLogger();

		logger.info(token);

		String rePrintUrl = loadUrlProperty("BeINSports.reprint.url");
		rePrintUrl = prepareReprintURL(ledgerTrxId, rePrintUrl);
        this.httpclient = initHttpClient();
		
		try {
        	this.httpGet = initHttpGet(rePrintUrl, token);

			CloseableHttpResponse response =executeGetRequest(this.httpclient, this.httpGet);

			String json = getJsonFromResponse(response);
			
			
		     switch (getStatusCode(response)) {
	            case 200:
	                return json2Object(json, BeINSportsPaymentResponseDTO.class);
	            case 401:
	                throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
	            default:
	            	logger.info("Failed to get commission");
	                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(json, StandardHttpJsonResponseDTO.class);
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
	
	
	
}
