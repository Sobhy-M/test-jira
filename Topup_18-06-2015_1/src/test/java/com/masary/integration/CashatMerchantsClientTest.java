package com.masary.integration;

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
import com.masary.integration.dto.CashatInquiryRequest;
import com.masary.integration.dto.CashatInquiryResponse;
import com.masary.integration.dto.CashatPaymentRequest;
import com.masary.integration.dto.CashatPaymentResponse;
import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ TEDataClient.class, MasaryManager.class, BaseManger.class })
@PowerMockIgnore("javax.net.ssl.*")
public class CashatMerchantsClientTest extends TestCase {

	@BeforeClass
	public static void oneTimeSetup() {
		System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
		System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
	}

	@Test
	public void testCashatInquiry_succesResponse_returnInquiryResponse() throws Exception {

		Logger logger = mock(Logger.class);
		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		CashatClient cashatClient = new CashatClientTestable();
		CashatInquiryRequest cashatInquiryRequest = new CashatInquiryRequest();
		cashatInquiryRequest.setCompanyID(1L);
		cashatInquiryRequest.setRepNationalId("12345678912345");
		cashatInquiryRequest.setAmount(2);
		CashatInquiryResponse cashatInquiryResponse = cashatClient.cashatInquiry(cashatInquiryRequest, "ar",
				"ODoxMjM0NTY3ODk=");
		assertNotNull(cashatInquiryResponse);
		assertNotNull(cashatInquiryResponse.getCompanyID());
		assertNotNull(cashatInquiryResponse.getRepNationalId());
		assertNotNull(cashatInquiryResponse.getAmount());

	}

	public class CashatClientTestable extends CashatClient {

		@Override
		protected CloseableHttpResponse executeGetRequest(CloseableHttpClient httpClient, HttpGet httpGet)
				throws Exception {
			return null;
		}

		@Override
		protected String getJsonFromResponse(CloseableHttpResponse closableHttpResponse)
				throws IOException, ParseException {
			return "{\r\n" + "  \"eventName\": null,\r\n" + "  \"entityName\": null,\r\n"
					+ "  \"globalTrxId\": \"1\",\r\n" + "  \"companyID\": 1,\r\n" + "  \"companyName\": \"نسلة\",\r\n"
					+ "  \"repId\": 1233455,\r\n" + "  \"repName\": \"Mai\",\r\n" + "  \"companyNameAr\": null,\r\n"
					+ "  \"repNationalId\": \"12345678912345\",\r\n" + "  \"amount\": null,\r\n"
					+ "  \"requestId\": 0,\r\n" + "  \"insertDate\": 1540297099606,\r\n"
					+ "  \"updateDate\": 1540297099606,\r\n" + "  \"appliedFees\": 0.5,\r\n"
					+ "  \"merchantCommission\": 0,\r\n" + "  \"tax\": 0.5,\r\n" + "  \"toBepaid\": 1000.5,\r\n"
					+ "  \"masaryCommission\": 0,\r\n" + "  \"requestStatus\": null,\r\n"
					+ "  \"accountId\": 1123453,\r\n" + "  \"deviceType\": \"Web\",\r\n"
					+ "  \"ledgerStatus\": null,\r\n" + "  \"ledgerTrxId\": 12344,\r\n" + "  \"ratePlanId\": null,\r\n"
					+ "  \"transactionAmount\": 1000,\r\n" + "  \"paymentAmount\": 20\r\n" + "}";
		}

		@Override
		protected int getStatusCode(CloseableHttpResponse httpResponse) {
			return 200;
		}

	}

	@Test
	public void testPrepareUrl_succesResponse_returnFormattedURL() throws Exception {
		CashatInquiryRequest cashatInquiryRequest = new CashatInquiryRequest();
		cashatInquiryRequest.setCompanyID(1);
		cashatInquiryRequest.setRepNationalId("12345678912345");
		cashatInquiryRequest.setAmount(20);

		String input = "http://172.16.10.193:9005/CashatMerchants/inquiry/{companyId}/{repNationalId}/{amount}";

		String expected = "http://172.16.10.193:9005/CashatMerchants/inquiry/1/12345678912345/20";

		CashatClient cashatClient = new CashatClient();

		String result = cashatClient.prepareUrl(cashatInquiryRequest, input);

		assertEquals(expected, result);

	}

	@Test(expected = Exception.class)
	public void testCashatInquiry_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		CashatClient cashatClient = new CashatClientTestableError();
		CashatInquiryRequest cashatInquiryRequest = new CashatInquiryRequest();
		cashatInquiryRequest.setCompanyID(1);
		cashatInquiryRequest.setRepNationalId("1234545jk");
		cashatInquiryRequest.setAmount(25);
		cashatClient.cashatInquiry(cashatInquiryRequest, "ar", "ODoxMjM0NTY3ODk=");

	}

	public class CashatClientTestableError extends CashatClient {
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
		protected String prepareUrl(CashatInquiryRequest cashatInquiryRequest, String cashatUrl) {
			return "";
		}
	}

	////////////////////////////////////////////////
	@Test
	public void testCashatPayment_succesResponse_returnPaymentResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		CashatClient cashatClient = new CashatClientPaymentTest();

		CashatPaymentRequest cashatPaymentRequest = new CashatPaymentRequest();
		cashatPaymentRequest.setValidationId("1");
		CashatPaymentResponse cashatPaymentResponse = cashatClient.cashatPayment(cashatPaymentRequest, "ar",
				"ODoxMjM0NTY3ODk=");
		assertNotNull(cashatPaymentResponse);

	}

	public class CashatClientPaymentTest extends CashatClient {

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
			return "{\r\n" + "  \"eventName\": null,\r\n" + "  \"entityName\": null,\r\n"
					+ "  \"appliedFees\": 0.5,\r\n" + "  \"merchantCommission\": null,\r\n" + "  \"tax\": 0.5,\r\n"
					+ "  \"toBepaid\": 1000.5,\r\n" + "  \"transactionAmount\": 1000,\r\n"
					+ "  \"ratePlanId\": null,\r\n" + "  \"requestStatus\": null,\r\n" + "  \"accountId\": 1123453,\r\n"
					+ "  \"deviceType\": \"Web\",\r\n" + "  \"globalTrxId\": \"1\",\r\n"
					+ "  \"ledgerTrxId\": 12344,\r\n" + "  \"ledgerStatus\": null,\r\n"
					+ "  \"updateDate\": 1540306443086,\r\n" + "  \"companyId\": 1,\r\n"
					+ "  \"companyName\": \"نسلة\",\r\n" + "  \"companyNameAr\": null,\r\n"
					+ "  \"repId\": 111111123,\r\n" + "  \"repName\": \"Mai\",\r\n" + "  \"repNationalId\": \"1\",\r\n"
					+ "  \"paymentAmount\": 10000,\r\n" + "  \"transactionId\": null,\r\n"
					+ "  \"insertDate\": 1540306443086\r\n" + "}";
		}

	}

	@Test(expected = Exception.class)
	public void testCashatPayment_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		CashatClient cashatClient = new CashatClientTestableError();
		CashatPaymentRequest cashatPaymentRequest = new CashatPaymentRequest();
		cashatPaymentRequest.setValidationId("1");
		cashatClient.cashatPayment(cashatPaymentRequest, "ar", "ODoxMjM0NTY3ODk=");

	}

	//////////////////////////////////////////////////////////////////////

	@Test
	public void testCashatReprint_succesResponse_returnReprintResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		CashatClient cashatClient = new CashatClientPaymentTest();

		CashatPaymentResponse cashatPaymentResponse = cashatClient.cashatReprint("1532867950180", "ar",
				"ODoxMjM0NTY3ODk=", "192");

		assertNotNull(cashatPaymentResponse);

	}

	@Test(expected = Exception.class)
	public void testTedataReprint_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		CashatClient cashatClient = new CashatClientTestableError();

		cashatClient.cashatReprint("1532867950180", "ar", "ODoxMjM0NTY3ODk=", "192");
	}

	@Test
	public void testPrepareReprintUrl_succesResponse_returnFormattedURL() throws Exception {

		String ledgerTrxId = "1532867950180";

		String input = "http://172.16.10.193:9005/CashatMerchants/reprint/{ledgerTrxId}";

		String expected = "http://172.16.10.193:9005/CashatMerchants/reprint/1532867950180";

		CashatClient cashatClient = new CashatClient();

		String result = cashatClient.prepareReprintURL(ledgerTrxId, input);

		assertEquals(expected, result);

	}

	@Test
	public void testCashatDenomination_succesResponse_returnDenominationResponse() throws Exception {

		Logger logger = mock(Logger.class);
		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		CashatClient cashatClient = new CashatClientDenominationTest();

		cashatClient.getCompanies("ar", "ODoxMjM0NTY3ODk=");

		assertNotNull(cashatClient);

	}

	public class CashatClientDenominationTest extends CashatClient {

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
					"  \"companies\": [\r\n" + 
					"    {\r\n" + 
					"      \"companyID\": 1,\r\n" + 
					"      \"companyName\": \"Nestla\",\r\n" + 
					"      \"companyNameAr\": \"Nestla\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"companyID\": 2,\r\n" + 
					"      \"companyName\": \"Chipsy\",\r\n" + 
					"      \"companyNameAr\": \"Chipsy\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"companyID\": 3,\r\n" + 
					"      \"companyName\": \"Foods\",\r\n" + 
					"      \"companyNameAr\": \"Fine Foods\"\r\n" + 
					"    },\r\n" + 
					"    {\r\n" + 
					"      \"companyID\": 4,\r\n" + 
					"      \"companyName\": \"Corona\",\r\n" + 
					"      \"companyNameAr\": \"Corona\"\r\n" + 
					"    }\r\n" + 
					"  ],\r\n" + 
					"  \"warnSTR\": \"\",\r\n" + 
					"  \"minTransactionAmount\": 1000,\r\n" + 
					"  \"maxTransactionAmount\": 999999999\r\n" + 
					"}";
		}

	}

	@Test(expected = Exception.class)
	public void testCashatDenomination_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		CashatClient cashatClient = new CashatClientTestableError();

		cashatClient.getCompanies("ar", "ODoxMjM0NTY3ODk=");

	}

}
