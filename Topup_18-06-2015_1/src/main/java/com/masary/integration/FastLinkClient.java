package com.masary.integration;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;

import com.masary.common.CONFIG;
import com.masary.common.CommonCalls;
import com.masary.integration.dto.FastLinkPaymentRequest;
import com.masary.integration.dto.FastLinkPaymentResponse;
import com.masary.integration.dto.FastLinkInquiryRequest;
import com.masary.integration.dto.FastLinkInquiryResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;

public class FastLinkClient  extends CommonCalls{
	
	private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private Logger logger;
	
	public  FastLinkInquiryResponse fastLinkInquiry(String msisdn,String lang, String token) throws Exception{
		 
        String fastLinkUrl =loadUrlProperty("FastLink.inquiry.url");
       
        fastLinkUrl = prepareUrl(msisdn, fastLinkUrl);
         
        this.logger = initLogger();

        logger.info("fastLink Inquiry Url:- " + fastLinkUrl);

        try {
        	this.httpclient = initHttpClient();
            
        	this.httpGet = initHttpGet(fastLinkUrl, token);

            logger.info("fastLinkUrl:- " + fastLinkUrl);

            CloseableHttpResponse fastLinkValidateResponseHttp = executeGetRequest(this.httpclient, this.httpGet);

            String fastLinkValidateResponseJson = getJsonFromResponse(fastLinkValidateResponseHttp);

            logger.info("fastLink Service response is : " + fastLinkValidateResponseJson);

            
            switch (getStatusCode(fastLinkValidateResponseHttp)) {
            case 200:
                return json2Object(fastLinkValidateResponseJson, FastLinkInquiryResponse.class);
            case 401:
                throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
            default:
            	logger.info("Failed to get commission");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(fastLinkValidateResponseJson, StandardHttpJsonResponseDTO.class);
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
        if (message.equals("61301")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorFastLinkTransacionAr : CONFIG.errorFastLinkTransacionEn);
        }
        if (message.equals("61302")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorFastLinkNoBillsFoundAr : CONFIG.errorFastLinkNoBillsFoundEn);

        }
        else {
        	errorMessage =(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
        }
        return errorMessage += ("[E"+message+"]");
	}



	protected String prepareUrl(String msisdn, String fastLinkUrl) {
        fastLinkUrl = fastLinkUrl.replace("{phoneNumber}", msisdn);
		return fastLinkUrl;
	}
	
	
	public  FastLinkPaymentResponse fastLinkPayment (FastLinkPaymentRequest fastLinkPaymentRequest, String lang, String token) throws Exception
	{
	  
		 String fastLinkUrl = loadUrlProperty("FastLink.payment.url");
		 
	        this.logger = initLogger();

	        logger.info("FastLink Payment Url:- " + fastLinkUrl + fastLinkPaymentRequest);
	        
	       
	        try {
	        	 this.httpclient = initHttpClient();
	         	
	         	String urlParameters = object2Json(fastLinkPaymentRequest, FastLinkPaymentRequest.class);

	        	this.httpGet = initHttpGet(fastLinkUrl, token); 

	            logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

	            HttpPost httpPost = initHttpPost(urlParameters, fastLinkUrl, token);

	            CloseableHttpResponse fastLinkPaymentResponseHttp = executePostRequest(httpclient, httpPost);
 
	            String fastLinkPaymentResponseJson = getJsonFromResponse(fastLinkPaymentResponseHttp);
	            logger.info("FastLink Service response is : " + fastLinkPaymentResponseJson);

	            switch (getStatusCode(fastLinkPaymentResponseHttp)) {
	            case 200:
	                return json2Object(fastLinkPaymentResponseJson, FastLinkPaymentResponse.class);
	            case 401:
	                throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionValuar : CONFIG.errorTransactionValuar);
	            default:
	            	logger.info("Failed to get commission");
	                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(fastLinkPaymentResponseJson, StandardHttpJsonResponseDTO.class);
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
	

	public FastLinkPaymentResponse fastLinkReprint(String ledgerTrxId,String lang,String token,String ip) throws Exception {

        this.logger = initLogger();

		logger.info(token);

		String rePrintUrl = loadUrlProperty("FastLink.reprint.url");
		rePrintUrl = prepareReprintURL(ledgerTrxId, rePrintUrl);
        this.httpclient = initHttpClient();
		
		try {
        	this.httpGet = initHttpGet(rePrintUrl, token);

			CloseableHttpResponse response =executeGetRequest(this.httpclient, this.httpGet);

			String json = getJsonFromResponse(response);
			
			
		     switch (getStatusCode(response)) {
	            case 200:
	                return json2Object(json, FastLinkPaymentResponse.class);
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
