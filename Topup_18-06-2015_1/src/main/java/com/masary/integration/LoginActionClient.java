package com.masary.integration;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;

import com.masary.common.CommonCalls;
import com.masary.database.dto.LoginDto;
import com.masary.exceptions.InvalidLoginException;
import com.masary.integration.dto.LoginRequestDTO;

public class LoginActionClient  extends CommonCalls {
	

	private CloseableHttpClient httpclient;
    private HttpGet httpGet;
    private Logger logger;
	
	
	public  LoginDto customerLogin (LoginRequestDTO loginDto, String lang, String token) throws Exception
	{
	
		 String loginUrl = loadUrlProperty("Login.url");
		 
	        this.logger = initLogger();

	        logger.info("Login Url:- " + loginUrl + loginDto);
	        
	       
	        try {
	        	 this.httpclient = initHttpClient();
	         	
	         	String urlParameters = object2Json(loginDto, LoginRequestDTO.class);

	        	this.httpGet = initHttpGet(loginUrl, token);

	            logger.info("Content-Type:- " + "application/json" + " Authorization:- " + token + " deviceType:- " + "Web");

	            HttpPost httpPost = initHttpPost(urlParameters, loginUrl, token);

	            CloseableHttpResponse loginResponseHttp = executePostRequest(httpclient, httpPost);

	            String loginResponseJson = getJsonFromResponse(loginResponseHttp);
	            logger.info("login response is : " + loginResponseJson);

	            switch (getStatusCode(loginResponseHttp)) {
	            case 200:
                    {
	                return json2Object(loginResponseJson, LoginDto.class);
                    }
                    case 401:
                    {
                        logger.info("Failed to log in");
	                throw new InvalidLoginException("Invalid Username or Password");
                    }
                        
	            default:
                    {
	            	logger.info("Failed to log in");
	                throw new Exception();
                    }
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
