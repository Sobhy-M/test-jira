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
import com.masary.integration.dto.FastLinkPaymentRequest;
import com.masary.integration.dto.FastLinkInquiryRequest;
import com.masary.integration.dto.FastLinkInquiryResponse;
import com.masary.integration.dto.FastLinkPaymentResponse;

import junit.framework.TestCase;


@RunWith(PowerMockRunner.class)
@PrepareForTest({ FastLinkClientTest.class, MasaryManager.class, BaseManger.class })
@PowerMockIgnore("javax.net.ssl.*")


public class FastLinkClientTest extends TestCase{
	
	@BeforeClass
	public static void oneTimeSetup() {
		System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
		System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
	}

	@Test
	public void testFastLinkInquiry_succesResponse_returnInquiryResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		FastLinkClient fastLinkClient = new FastLinkClientTestable();
  
		FastLinkInquiryRequest fastLinkInquiryRequestDTO = new FastLinkInquiryRequest();
		fastLinkInquiryRequestDTO.setMsisdn("2310885");
		//fastLinkInquiryRequestDTO.setReferenceNumber("1215454664564");
 
		FastLinkInquiryResponse fastLinkInquiryResponse = fastLinkClient
				.fastLinkInquiry(fastLinkInquiryRequestDTO.getMsisdn(), "ar", "ODoxMjM0NTY3ODk=");

		assertNotNull(fastLinkInquiryResponse);
		assertNotNull(fastLinkInquiryResponse.getValidationId());
		assertNotEquals("", fastLinkInquiryResponse.getValidationId().trim());
	}

	public class FastLinkClientTestable extends FastLinkClient {
		@Override
		protected CloseableHttpResponse executeGetRequest(CloseableHttpClient httpClient, HttpGet httpGet)
				throws Exception {
			return null;
		}

		@Override
		protected String getJsonFromResponse(CloseableHttpResponse closableHttpResponse)
				throws IOException, ParseException {
			return "{\r\n" +
                    "  \"validationId\": \"1539087451543\",\r\n" +
                    "  \"expirationDate\": 1,\r\n" +
                    "  \"clientName\": \"Mohamed Ibrahim Ismail\",\r\n" +
                    "  \"phoneNumber\": \"2310885\",\r\n" +
                    "  \"startDate\": 1539087454017,\r\n" +
                    "  \"endDate\": 1539087454017,\r\n" +
                    "  \"billAmount\": 93,\r\n" +
                    "  \"clientId\": 411,\r\n" +
                    "  \"appliedFees\": 3.5,\r\n" +
                    "  \"merchantCommission\": 1.4,\r\n" +
                    "  \"masaryCommisssion\": 2.1,\r\n" +
                    "  \"tax\": 0.49,\r\n" +
                    "  \"toBepaid\": 96.99,\r\n" +
                    "  \"globalTrxId\": \"1539087451543\",\r\n" +
                    "  \"transactionAmount\": 95.59,\r\n" +
                    "  \"ratePlanId\": 261,\r\n" +
                    "  \"accountId\": 8501,\r\n" +
                    "  \"appliedFeesAddedTax\": 3.99\r\n" +
                    "}\r\n" +
                    "";
		}

		@Override
		protected int getStatusCode(CloseableHttpResponse httpResponse) {
			return 200;
		}

	}

	@Test
	public void testPrepareUrl_succesResponse_returnFormattedURL() throws Exception {
		//String referenceNumber = "123456789";
		String msisdn = "2310885";

		String input = "http://172.16.10.193:9005/fastlink/inquiry/{phoneNumber}";

		String expected = "http://172.16.10.193:9005/fastlink/inquiry/2310885";

		FastLinkClient fastLinkClient = new FastLinkClient();

		String result = fastLinkClient.prepareUrl(msisdn, input);

		assertEquals(expected, result);

	}

	@Test(expected = Exception.class)
	public void testFastLinkInquiry_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		FastLinkClient fastLinkClient = new FastLinkClientTestableError();

		FastLinkInquiryRequest fastLinkInquiryRequestDTO = new FastLinkInquiryRequest();
		fastLinkInquiryRequestDTO.setMsisdn("2310885");
	//	beINSportsInquiryRequestDTO.setReferenceNumber("1215454664564");

		fastLinkClient.fastLinkInquiry(fastLinkInquiryRequestDTO.getMsisdn(), "ar", "ODoxMjM0NTY3ODk=");

	}

	public class FastLinkClientTestableError extends FastLinkClient {
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
		protected String prepareUrl(String msisdn, String fastLinkUrl) {
			return "";
		}
	}
	@Test
	public void testFastLinkPayment_succesResponse_returnPaymentResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		FastLinkClient fastLinkClient = new FastLinkClientTestable();

		FastLinkPaymentRequest fastLinkPaymentRequest = new FastLinkPaymentRequest();
		fastLinkPaymentRequest.setValidationId("1539087451543");
 
		FastLinkPaymentResponse fastLinkPaymentResponse = fastLinkClient
				.fastLinkPayment(fastLinkPaymentRequest, "ar", "ODoxMjM0NTY3ODk=");

		assertNotNull(fastLinkPaymentResponse);

	}

	@Test(expected = Exception.class)
	public void testBeINSportsPayment_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		FastLinkClient fastLinkClient= new FastLinkClientTestableError();

		FastLinkPaymentRequest fastLinkPaymentRequest = new FastLinkPaymentRequest();
		fastLinkPaymentRequest.setValidationId("1539087451543");

		fastLinkClient.fastLinkPayment(fastLinkPaymentRequest, "ar", "ODoxMjM0NTY3ODk=");

	}

	public class BeINSportsClientPaymentTest extends BeINSportsClient {

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
			return"{\r\n" +
                    "  \"eventName\": null,\r\n" +
                    "  \"entityName\": null,\r\n" +
                    "  \"clientName\": \"Mohamed Ibrahim Ismail\",\r\n" +
                    "  \"phoneNumber\": \"2310885\",\r\n" +
                    "  \"insertDate\": 1539087582913,\r\n" +
                    "  \"startDate\": null,\r\n" +
                    "  \"endDate\": 1539087571873,\r\n" +
                    "  \"billAmount\": 93,\r\n" +
                    "  \"clientId\": 411,\r\n" +
                    "  \"appliedFees\": 3.5,\r\n" +
                    "  \"merchantCommission\": 1.4,\r\n" +
                    "  \"tax\": 0.49,\r\n" +
                    "  \"toBepaid\": 96.99,\r\n" +
                    "  \"requestStatus\": \"SUCCEEDED\",\r\n" +
                    "  \"accountId\": 8501,\r\n" +
                    "  \"deviceType\": \"WEB\",\r\n" +
                    "  \"globalTrxId\": \"1539087569569\",\r\n" +
                    "  \"transactionAmount\": 95.59,\r\n" +
                    "  \"ledgerTrxId\": 220620829,\r\n" +
                    "  \"ratePlanId\": 261,\r\n" +
                    "  \"appliedFeesAddedTax\": 3.99\r\n" +
                    "}";
		}

	}

	@Test(expected = Exception.class)
	public void testFastLinkReprint_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		FastLinkClient fastLinkClient = new FastLinkClientTestableError();

		fastLinkClient.fastLinkReprint("220620829", "ar", "ODoxMjM0NTY3ODk=", "192");

	}

	@Test
	public void testBeINSportsReprint_succesResponse_returnReprintResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		FastLinkClient fastLinkClient = new FastLinkClientTestable();

		FastLinkPaymentResponse fastLinkPaymentResponse = fastLinkClient.fastLinkReprint("220620829", "ar",
				"ODoxMjM0NTY3ODk=", "192");

		assertNotNull(fastLinkPaymentResponse);

	}

	@Test
	public void testPrepareReprintUrl_succesResponse_returnFormattedURL() throws Exception {
		String ledgerTrxId = "220620829";

		String input = "http://172.16.10.193:9005/fastlink/reprint/{ledgerTrxId}";

		String expected = "http://172.16.10.193:9005/fastlink/reprint/220620829";

		FastLinkClient fastLinkClient = new FastLinkClient();

		String result = fastLinkClient.prepareReprintURL(ledgerTrxId, input);

		assertEquals(expected, result);

	}

}
