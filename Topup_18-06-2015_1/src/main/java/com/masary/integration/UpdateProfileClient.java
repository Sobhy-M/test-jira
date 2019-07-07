package com.masary.integration;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;

import com.masary.common.CommonCalls;
import com.masary.database.dto.LoginDto;
import com.masary.integration.dto.UpdateProfileRequest;
import com.masary.integration.dto.UpdateProfileResponse;

public class UpdateProfileClient extends CommonCalls{
	

	private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private Logger logger;
    
    
	public  UpdateProfileResponse updateProfile (UpdateProfileRequest updateProfileRequest,  String token) throws Exception
	{
	
		 String updateProfileUrl = loadUrlProperty("Update.Profile.url");
		 
	        this.logger = initLogger();

	        logger.info("Update Profile Url:- " + updateProfileUrl + updateProfileRequest);
	        
	       
	        try {
	        	 this.httpclient = initHttpClient();
	         	
	         	String urlParameters = object2Json(updateProfileRequest, UpdateProfileRequest.class);

	        	this.httpGet = initHttpGet(updateProfileUrl, token);

	            logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

	            HttpPost httpPost = initHttpPost(urlParameters, updateProfileUrl, token);

	            CloseableHttpResponse updateProfileResponseHttp = executePostRequest(httpclient, httpPost);

	            String UpdateProfileResponseJson = getJsonFromResponse(updateProfileResponseHttp);
	            logger.info("Update Profile response is : " + UpdateProfileResponseJson);

	            switch (getStatusCode(updateProfileResponseHttp)) {
	            case 200:
	                return json2Object(UpdateProfileResponseJson, UpdateProfileResponse.class);
	                 default:
	            	logger.info("Failed to update profile");
	                throw new Exception();
	        }

	        } catch (Exception e) {
				logger.error("Exception" + e);
				logger.error(e);
				throw (e);
			} finally {
	            httpclient.close();
	        }


	}
	

	

}
