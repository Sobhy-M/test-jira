package com.masary.integration;

import static org.junit.Assert.assertNotEquals;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;

import java.io.IOException;
import java.util.List;

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
import com.masary.integration.BeINSportsClientTest.BeINSportsClientTestableError;
import com.masary.integration.dto.BeINSportsPaymentRequestDTO;
import com.masary.integration.dto.TEData_Inquiry_Request;
import com.masary.integration.dto.TEData_Inquiry_Response;
import com.masary.integration.dto.TEData_Payment_Request;
import com.masary.integration.dto.TEData_Payment_Response;
import com.masary.integration.dto.TedataDenominationRepresentation;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ TEDataClient.class, MasaryManager.class, BaseManger.class })
@PowerMockIgnore("javax.net.ssl.*")
public class TEDataClientTest extends TestCase {

	@BeforeClass
	public static void oneTimeSetup() {
		System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
		System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
	}

	@Test
	public void testTEDataInquiry_succesResponse_returnInquiryResponse() throws Exception {

		Logger logger = mock(Logger.class);
		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		TEDataClient tedataClient = new TEDataClientTestable();
		TEData_Inquiry_Request tedataInquiryRequest = new TEData_Inquiry_Request();
		tedataInquiryRequest.setLandline("0224529992");
		tedataInquiryRequest.setDenominationId("8603");
		TEData_Inquiry_Response tedataInquiryResponse = tedataClient.tedataInquiry(tedataInquiryRequest, "ar","ODoxMjM0NTY3ODk=");
		assertNotNull(tedataInquiryResponse);
		assertNotNull(tedataInquiryResponse.getValidationId());
		assertNotEquals("", tedataInquiryResponse.getValidationId().trim());

	}

	public class TEDataClientTestable extends TEDataClient {

		@Override
		protected CloseableHttpResponse executeGetRequest(CloseableHttpClient httpClient, HttpGet httpGet)
				throws Exception {
			return null;
		}

		@Override
		protected String getJsonFromResponse(CloseableHttpResponse closableHttpResponse)
				throws IOException, ParseException {
			return "{\r\n" + "  \"validationId\": \"1532856105701\",\r\n" + "  \"adslNumber\": \"24529992\",\r\n"
					+ "  \"providerStatusCode\": \"-1\",\r\n" + "  \"customerName\": null,\r\n"
					+ "  \"amount\": 1.2768,\r\n" + "  \"denominationId\": \"8603\",\r\n"
					+ "  \"areaCode\": \"02\",\r\n" + "  \"appliedFees\": 2,\r\n" + "  \"merchantCommission\": 1,\r\n"
					+ "  \"masaryCommission\": 1,\r\n" + "  \"tax\": 0.12768,\r\n"
					+ "  \"toBepaid\": 3.2767999999999997,\r\n" + "  \"globalTrxId\": \"1532856105701\",\r\n"
					+ "  \"transactionAmount\": 1.2768,\r\n" + "  \"ratePlanId\": 261,\r\n"
					+ "  \"accountId\": 8501,\r\n" + "  \"expirationDate\": 1\r\n" + "}";
		}

		@Override
		protected int getStatusCode(CloseableHttpResponse httpResponse) {
			return 200;
		}

	}

	@Test
	public void testPrepareUrl_succesResponse_returnFormattedURL() throws Exception {
		String landline = "0224529992";
		String denominationId = "8603";

		String input = "http://172.16.10.193:9005/tedatatopup/inquiry/{landline}/{denominationId}";

		String expected = "http://172.16.10.193:9005/tedatatopup/inquiry/0224529992/8603";

		TEDataClient tedataClient = new TEDataClient();

		String result = tedataClient.prepareUrl(landline, denominationId, input);

		assertEquals(expected, result);

	}

	@Test(expected = Exception.class)
	public void testTedataInquiry_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		TEDataClient tedataClient = new TedataClientTestableError();

		TEData_Inquiry_Request tedataInquiryRequest = new TEData_Inquiry_Request();
		tedataInquiryRequest.setLandline("0224529992");
		tedataInquiryRequest.setDenominationId("8603");
		tedataClient.tedataInquiry(tedataInquiryRequest, "ar", "ODoxMjM0NTY3ODk=");

	}

	public class TedataClientTestableError extends TEDataClient {
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
		protected String prepareUrl(String referenceNumber, String msisdn, String beINSportsUrl) {
			return "";
		}
	}

	
	@Test
	public void testTedataPayment_succesResponse_returnPaymentResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		TEDataClient tedataClient = new TedataClientPaymentTest();

		TEData_Payment_Request tedataPaymentRequest = new TEData_Payment_Request();
		tedataPaymentRequest.setValidationId("1532867936824");

		TEData_Payment_Response tedataPaymentResponse = tedataClient.tedataPayment(tedataPaymentRequest,"ar","ODoxMjM0NTY3ODk=");

		assertNotNull(tedataPaymentResponse);

	}

	public class TedataClientPaymentTest extends TEDataClient {

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
			return "{\r\n" + 
					"  \"eventName\": null,\r\n" + 
					"  \"entityName\": null,\r\n" + 
					"  \"providerStatusCode\": null,\r\n" + 
					"  \"customerName\": null,\r\n" + 
					"  \"adslNumber\": \"24529992\",\r\n" + 
					"  \"receiptNumber\": 56062877,\r\n" + 
					"  \"invoiceNumber\": 58721867,\r\n" + 
					"  \"insertDate\": 1532867949987,\r\n" + 
					"  \"updateDate\": 1532867951810,\r\n" + 
					"  \"packageAmount\": 1.2768,\r\n" + 
					"  \"packageId\": \"8603\",\r\n" + 
					"  \"packageQuota\": \"100\",\r\n" + 
					"  \"appliedFees\": 2,\r\n" + 
					"  \"tax\": 0.12768,\r\n" + 
					"  \"toBepaid\": 3.2767999999999997,\r\n" + 
					"  \"requestStatus\": \"SUCCEEDED\",\r\n" + 
					"  \"ledgerStatus\": \"COMMITTED\",\r\n" + 
					"  \"accountId\": 8501,\r\n" + 
					"  \"deviceType\": \"WEB\",\r\n" + 
					"  \"globalTrxId\": \"1532867949950\",\r\n" + 
					"  \"transactionAmount\": 1.2768,\r\n" + 
					"  \"ledgerTrxId\": 1532867950180,\r\n" + 
					"  \"ratePlanId\": 261,\r\n" + 
					"  \"targetNumber\": \"24529992\",\r\n" + 
					"  \"merchantCommission\": 1\r\n" + 
					"}";
		}

	}

	@Test(expected = Exception.class)
	public void testTedataPayment_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		TEDataClient tedataClient = new TedataClientTestableError();

		TEData_Payment_Request tedataPaymentRequest = new TEData_Payment_Request();
		tedataPaymentRequest.setValidationId("1532856105701");
		tedataClient.tedataPayment(tedataPaymentRequest,"ar", "ODoxMjM0NTY3ODk=");

	}

	
	@Test
	public void testTedataReprint_succesResponse_returnReprintResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		TEDataClient tedataClient = new TedataClientPaymentTest();

		TEData_Payment_Response tedataPaymentResponse=tedataClient.tedataReprint("1532867950180","ar","ODoxMjM0NTY3ODk=", "192");
				
				
		assertNotNull(tedataPaymentResponse);

	}
	
	@Test(expected = Exception.class)
	public void testTedataReprint_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		TEDataClient tedataClient = new TedataClientTestableError();

		tedataClient.tedataReprint("1532867950180","ar", "ODoxMjM0NTY3ODk=", "192");
		
	}


	@Test
	public void testPrepareReprintUrl_succesResponse_returnFormattedURL() throws Exception {
		
		String ledgerTrxId = "1532867950180";

		String input = "http://172.16.10.193:9005/tedatatopup/reprint/{ledgerTrxId}";

		String expected = "http://172.16.10.193:9005/tedatatopup/reprint/1532867950180";

		TEDataClient tedataClient = new TEDataClient();

		String result = tedataClient.prepareReprintURL(ledgerTrxId, input);
				
		assertEquals(expected, result);

	}
	
	
	@Test
	public void testTEDataDenomination_succesResponse_returnDenominationResponse() throws Exception {

		Logger logger = mock(Logger.class);
		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		TEDataClient tedataClient = new TedataClientDenominationTest();
		
		tedataClient.getDenomination("ar","ODoxMjM0NTY3ODk=");

		assertNotNull(tedataClient);
		
		
	}
	
	public class TedataClientDenominationTest extends TEDataClient {

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
			return "[{\r\n" + 
					"    \"denominationId\": \"8603\",\r\n" + 
					"    \"denominationName\": \"WE - Recharge Quota - 100GB\",\r\n" + 
					"    \"quota\": \"100\",\r\n" + 
					"    \"amount\": \"1.27680000000000\"\r\n" + 
					"  },\r\n" + 
					"  {\r\n" + 
					"    \"denominationId\": \"8600\",\r\n" + 
					"    \"denominationName\": \"WE - Recharge Quota - 10GB\",\r\n" + 
					"    \"quota\": \"10\",\r\n" + 
					"    \"amount\": \"1.27680000000000\"\r\n" + 
					"  },\r\n" + 
					"  {\r\n" + 
					"    \"denominationId\": \"8604\",\r\n" + 
					"    \"denominationName\": \"WE - Recharge Quota - 200GB\",\r\n" + 
					"    \"quota\": \"200\",\r\n" + 
					"    \"amount\": \"114.00000000000000\"\r\n" + 
					"  },\r\n" + 
					"  {\r\n" + 
					"    \"denominationId\": \"8601\",\r\n" + 
					"    \"denominationName\": \"WE - Recharge Quota - 25GB\",\r\n" + 
					"    \"quota\": \"25\",\r\n" + 
					"    \"amount\": \"1.27680000000000\"\r\n" + 
					"  },\r\n" + 
					"  {\r\n" + 
					"    \"denominationId\": \"8605\",\r\n" + 
					"    \"denominationName\": \"WE - Recharge Quota - 300GB\",\r\n" + 
					"    \"quota\": \"300\",\r\n" + 
					"    \"amount\": \"1.27680000000000\"\r\n" + 
					"  },\r\n" + 
					"  {\r\n" + 
					"    \"denominationId\": \"8606\",\r\n" + 
					"    \"denominationName\": \"WE - Recharge Quota - 500GB\",\r\n" + 
					"    \"quota\": \"500\",\r\n" + 
					"    \"amount\": \"1.27680000000000\"\r\n" + 
					"  },\r\n" + 
					"  {\r\n" + 
					"    \"denominationId\": \"8602\",\r\n" + 
					"    \"denominationName\": \"WE - Recharge Quota - 50GB\",\r\n" + 
					"    \"quota\": \"50\",\r\n" + 
					"    \"amount\": \"1.27680000000000\"\r\n" + 
					"  },\r\n" + 
					"  {\r\n" + 
					"    \"denominationId\": \"8599\",\r\n" + 
					"    \"denominationName\": \"WE - Recharge Quota - 5GB\",\r\n" + 
					"    \"quota\": \"5\",\r\n" + 
					"    \"amount\": \"1.27680000000000\"\r\n" + 
					"  },\r\n" + 
					"  {\r\n" + 
					"    \"denominationId\": \"8759\",\r\n" + 
					"    \"denominationName\": \"Extra Quota - 100GB\",\r\n" + 
					"    \"quota\": \"100\",\r\n" + 
					"    \"amount\": \"114.00000000000000\"\r\n" + 
					"  },\r\n" + 
					"  {\r\n" + 
					"    \"denominationId\": \"8757\",\r\n" + 
					"    \"denominationName\": \"Extra Quota - 20GB\",\r\n" + 
					"    \"quota\": \"20\",\r\n" + 
					"    \"amount\": \"34.20000000000000\"\r\n" + 
					"  },\r\n" + 
					"  {\r\n" + 
					"    \"denominationId\": \"8758\",\r\n" + 
					"    \"denominationName\": \"Extra Quota - 50GB\",\r\n" + 
					"    \"quota\": \"50\",\r\n" + 
					"    \"amount\": \"68.40000000000000\"\r\n" + 
					"  }]";
		}

	}
	
	
	@Test(expected = Exception.class)
	public void testTedataDenomination_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		TEDataClient tedataClient = new TedataClientTestableError();

				
	    tedataClient.getDenomination("ar","ODoxMjM0NTY3ODk=");

	

	}
	
	
	
	
	
	
	
	
	
	
	
	
}
