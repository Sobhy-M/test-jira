package com.masary.integration;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;

import com.masary.common.CONFIG;
import com.masary.common.CommonCalls;
import com.masary.integration.dto.AcceptInquiryRequest;
import com.masary.integration.dto.AcceptInquiryResponse;
import com.masary.integration.dto.AcceptPaymentRequest;
import com.masary.integration.dto.AcceptPaymentResponse;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;


	
	public class AcceptClient extends CommonCalls{
		
		private CloseableHttpClient httpclient;
	    private HttpGet httpGet;
	    private Logger logger;

    public AcceptInquiryResponse acceptValidateInquiry(AcceptInquiryRequest acceptInquiryRequest, String lang, String token, String serviceId) throws Exception {

    	String acceptInquiryUrl ="" ;
    	
    	if (serviceId.equals("23111")) {
             acceptInquiryUrl =loadUrlProperty("Accept.inquery.url");
        } else if (serviceId.equals("23112")) {
             acceptInquiryUrl =loadUrlProperty("Busset.inquery.url");
        } else if (serviceId.equals("23113")) {
             acceptInquiryUrl =loadUrlProperty("swvl.inquery.url");
        } else if (serviceId.equals("23115")) {
             acceptInquiryUrl =loadUrlProperty("nileKayaClub.inquery.url");
        } else if (serviceId.equals("23114")) {
             acceptInquiryUrl =loadUrlProperty("flyin.inquery.url");
        } else if (serviceId.equals("23116")) {
             acceptInquiryUrl =loadUrlProperty("e7gzly.inquery.url");
        }
    	 
        acceptInquiryUrl = prepareUrl(acceptInquiryRequest.getBillReference(),acceptInquiryRequest.getBiller(),acceptInquiryRequest.getMobileNumber(), acceptInquiryUrl);
        
        this.logger = initLogger();

        logger.info("accept Inquiry Url:- " + acceptInquiryUrl);

        try {
        	this.httpclient = initHttpClient();
            
        	this.httpGet = initHttpGet(acceptInquiryUrl, token);

            logger.info("acceptInquiryUrl:- " + acceptInquiryUrl);

            CloseableHttpResponse acceptValidateResponseHttp = executeGetRequest(this.httpclient, this.httpGet);

            String acceptSportsValidateResponseJson = getJsonFromResponse(acceptValidateResponseHttp);

            logger.info("Accept Service response is : " + acceptSportsValidateResponseJson);

            
            switch (getStatusCode(acceptValidateResponseHttp)) {
            case 200:
                return json2Object(acceptSportsValidateResponseJson, AcceptInquiryResponse.class);

            default:
            	logger.info("Failed to get commission");
                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(acceptSportsValidateResponseJson, StandardHttpJsonResponseDTO.class);
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
        if (message.equals("231111")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorAcceptAr : CONFIG.errorAcceptEn);
        }
        if (message.equals("231112")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorAcceptE1Ar : CONFIG.errorAcceptE1En);
        }if (message.equals("231113")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorAcceptE3Ar : CONFIG.errorAcceptE3En);
        }if (message.equals("231114")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorAcceptEAr : CONFIG.errorAcceptEEn);
        }if (message.equals("231115")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorAcceptE4Ar : CONFIG.errorAcceptE4En);
        }if (message.equals("231116")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorAcceptE5Ar : CONFIG.errorAcceptE5En);
        }if (message.equals("231117")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorAcceptE6Ar : CONFIG.errorAcceptE6En);
        }if (message.equals("231118")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorAcceptE7Ar : CONFIG.errorAcceptE7En);
        }if (message.equals("231119")) {
        	errorMessage = (lang.equals("ar") ? CONFIG.errorAcceptE8Ar : CONFIG.errorAcceptE8En);
        }if (message.equals("2311110")) {
        	errorMessage =(lang.equals("ar") ? CONFIG.errorAcceptE9Ar : CONFIG.errorAcceptE9En);
        }if (message.equals("2311111")) {
        	errorMessage =(lang.equals("ar") ? CONFIG.errorAcceptE10Ar : CONFIG.errorAcceptE10En);
        }if (message.equals("2311112")) {
        	errorMessage =(lang.equals("ar") ? CONFIG.errorAcceptE2Ar : CONFIG.errorAcceptE2En);
        }if (message.equals("2311113")) {
        	errorMessage =(lang.equals("ar") ? CONFIG.errorAcceptAr : CONFIG.errorAcceptEn);
        }
          
        else {
        	errorMessage =(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransactionar);
        }
        return errorMessage += ("[E"+message+"]");
	}


    	
    
    protected String prepareUrl(String billRefrence,String biller,String mobileNumber ,String acceptInquiryUrl) {
    	acceptInquiryUrl = acceptInquiryUrl.replace("{billReference}", billRefrence);
    	acceptInquiryUrl = acceptInquiryUrl.replace("{biller}", biller);
    	acceptInquiryUrl = acceptInquiryUrl.replace("{mobileNumber}", mobileNumber);

		return acceptInquiryUrl;
	}
    
	public  AcceptPaymentResponse acceptValidatePayment (AcceptPaymentRequest acceptPaymentRequest, String lang, String token, String serviceId) throws Exception
	{
		String acceptPaymentUrl ="";
		if (serviceId.equals("23111")) {
			acceptPaymentUrl =loadUrlProperty("Accept.payment.url");
       } else if (serviceId.equals("23112")) {
    	   acceptPaymentUrl =loadUrlProperty("Busset.payment.url");
       } else if (serviceId.equals("23113")) {
    	   acceptPaymentUrl =loadUrlProperty("swvl.payment.url");
       } else if (serviceId.equals("23115")) {
    	   acceptPaymentUrl =loadUrlProperty("nileKayaClub.payment.url");
       } else if (serviceId.equals("23114")) {
    	   acceptPaymentUrl =loadUrlProperty("flyin.payment.url");
       } else if (serviceId.equals("23116")) {
    	   acceptPaymentUrl =loadUrlProperty("e7gzly.payment.url");
       }
	
		 
	        this.logger = initLogger();

	        logger.info("Accept Payment Url:- " + acceptPaymentUrl + acceptPaymentRequest);
	        
	       
	        try {
	        	 this.httpclient = initHttpClient();
	         	
	         	String urlParameters = object2Json(acceptPaymentRequest, AcceptPaymentRequest.class);

	        	this.httpGet = initHttpGet(acceptPaymentUrl, token);

	            logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

	            HttpPost httpPost = initHttpPost(urlParameters, acceptPaymentUrl, token);

	            CloseableHttpResponse acceptPaymentResponseHttp = executePostRequest(httpclient, httpPost);

	            String acceptPaymentResponseJson = getJsonFromResponse(acceptPaymentResponseHttp);
	            logger.info("Accept Service response is : " + acceptPaymentResponseJson);

	            switch (getStatusCode(acceptPaymentResponseHttp)) {
	            case 200:
	                return json2Object(acceptPaymentResponseJson, AcceptPaymentResponse.class);
	         	            default:
	            	logger.info("Failed to get commission");
	                StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = json2Object(acceptPaymentResponseJson, StandardHttpJsonResponseDTO.class);
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
	

	public AcceptPaymentResponse acceptReprint(String ledgerTrxId,String lang,String token,String ip,String serviceId) throws Exception {

        this.logger = initLogger();

		logger.info(token);
		
		String rePrintUrl="";
		
		if (serviceId.equals("23111")) {
			rePrintUrl =loadUrlProperty("Accept.reprint.url");
       } else if (serviceId.equals("23112")) {
    	   rePrintUrl =loadUrlProperty("Busset.reprint.url");
       } else if (serviceId.equals("23113")) {
    	   rePrintUrl =loadUrlProperty("swvl.reprint.url");
       } else if (serviceId.equals("23115")) {
    	   rePrintUrl =loadUrlProperty("nileKayaClub.reprint.url");
       } else if (serviceId.equals("23114")) {
    	   rePrintUrl =loadUrlProperty("flyin.reprint.url");
       } else if (serviceId.equals("23116")) {
    	   rePrintUrl =loadUrlProperty("e7gzly.reprint.url");
       }

		rePrintUrl = prepareReprintURL(ledgerTrxId, rePrintUrl);
        this.httpclient = initHttpClient();
		
		try {
        	this.httpGet = initHttpGet(rePrintUrl, token);

			CloseableHttpResponse response =executeGetRequest(this.httpclient, this.httpGet);

			String json = getJsonFromResponse(response);
			
			
		     switch (getStatusCode(response)) {
	            case 200:
	                return json2Object(json, AcceptPaymentResponse.class);
	           
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