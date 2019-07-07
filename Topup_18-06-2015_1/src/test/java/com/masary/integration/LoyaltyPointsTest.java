package com.masary.integration;

import static org.junit.Assert.assertNotEquals;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.masary.database.manager.BaseManger;
import com.masary.database.manager.MasaryManager;

import com.masary.integration.dto.ChangePointsDTO;

import com.masary.integration.dto.LoyaltyPointSaveRequest;
import com.masary.integration.dto.LoyaltyPointsSaveResponse;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ FastLinkClientTest.class, MasaryManager.class, BaseManger.class })
@PowerMockIgnore("javax.net.ssl.*")

public class LoyaltyPointsTest extends TestCase{
	@BeforeClass
	public static void oneTimeSetup() {
		System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
		System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
	}
	@Test
	public void testLoyaltyPointsValidation_succesResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		LoyalityPointsClient loyaltyPointsClient = new LoyaltyPointsClientTestable();
 
		ChangePointsDTO loyaltyPointsValidationResponse = loyaltyPointsClient
				.loyalityPointsValidate( "ar", "ODoxMjM0NTY3ODk=");

		assertNotNull(loyaltyPointsValidationResponse);
	}

	public class LoyaltyPointsClientTestable extends LoyalityPointsClient {
		@Override
		protected CloseableHttpResponse executeGetRequest(CloseableHttpClient httpClient, HttpGet httpGet)
				throws Exception {
			return null;
		}

		@Override
		protected String getJsonFromResponse(CloseableHttpResponse closableHttpResponse)
				throws IOException, ParseException {
			return "{\r\n    +\"walletPoints\": 200,\r\n    \"points\": [\r\n        100,\r\n        200,\r\n        300,\r\n        400,\r\n        500,\r\n        600,\r\n        700,\r\n        800,\r\n        900,\r\n        1000\r\n    ]\r\n}"; 
		}

		@Override
		protected int getStatusCode(CloseableHttpResponse httpResponse) {
			return 200;
		}

	}


	@Test(expected = Exception.class)
	public void testLoyaltyPointsValidation_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		LoyalityPointsClient loyalityPointsClient = new LoyaltyPointsClientTestableError(); 

		loyalityPointsClient.loyalityPointsValidate("ar", "ODoxMjM0NTY3ODk=");

	}

	public class LoyaltyPointsClientTestableError extends LoyalityPointsClient { 
		@Override
		protected CloseableHttpResponse executeGetRequest(CloseableHttpClient httpClient, HttpGet httpGet)
				throws Exception {
			return null;
		}

		@Override
		protected String getJsonFromResponse(CloseableHttpResponse closableHttpResponse)
				throws IOException, ParseException {
			return null;
		}

		@Override
		protected int getStatusCode(CloseableHttpResponse httpResponse) {
			return 100;
		}

		@Override
		protected String prepareUrl( String loyaltyUrl) { 
			return "";
		}
	}
	@Test
	public void testLoyaltyPoints_SavesuccesResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		LoyalityPointsClient loyalityPointsClient = new LoyaltyPointsSaveTest(); 

		LoyaltyPointSaveRequest loyaltyPointSaveRequest = new LoyaltyPointSaveRequest(); 
		loyaltyPointSaveRequest.setWalletPoints(200);
 
		LoyaltyPointsSaveResponse loyaltyPointsSaveResponse = loyalityPointsClient
				.loyaltyPointsSavePayment(loyaltyPointSaveRequest, "ar", "ODoxMjM0NTY3ODk=");

		assertNotNull(loyaltyPointsSaveResponse); 

	}

	@Test(expected = Exception.class)
	public void testLoyaltyPointsSave_ErrorThrown_Exception() throws Exception { 

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);
 
		LoyalityPointsClient loyalityPointsClient= new LoyaltyPointsClientTestableError();

		LoyaltyPointSaveRequest loyaltyPointSaveRequest = new LoyaltyPointSaveRequest();
		loyaltyPointSaveRequest.setWalletPoints(200);

		loyalityPointsClient.loyaltyPointsSavePayment(loyaltyPointSaveRequest,"ar", "ODoxMjM0NTY3ODk=");

	}

	public class LoyaltyPointsSaveTest extends LoyalityPointsClient {

		@Override
		protected CloseableHttpResponse executePostRequest(CloseableHttpClient httpClient, HttpPost httpPost)
				throws Exception {
			return null;
		}

		@Override
		protected int getStatusCode(CloseableHttpResponse httpResponse) {
			return 200;
		}

		@Override
		protected String getJsonFromResponse(CloseableHttpResponse closableHttpResponse)
				throws IOException, ParseException {
			return"{\r\n    \"referenceNumber\": \"1545906230678\",\r\n    \"walletNumber\": 8,\r\n    \"requestDate\": 1545906230670,\r\n    \"points\": 100\r\n}";
		}

	}

	
}



