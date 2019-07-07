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
import com.masary.integration.dto.BeINSportsInquiryRequestDTO;
import com.masary.integration.dto.BeINSportsInquiryResponseDTO;
import com.masary.integration.dto.BeINSportsPaymentRequestDTO;
import com.masary.integration.dto.BeINSportsPaymentResponseDTO;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ BeINSportsClient.class, MasaryManager.class, BaseManger.class })
@PowerMockIgnore("javax.net.ssl.*")
public class BeINSportsClientTest extends TestCase {

	@BeforeClass
	public static void oneTimeSetup() {
		System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
		System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
	}

	@Test
	public void testBeINSportsInquiry_succesResponse_returnInquiryResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		BeINSportsClient beINSportsClient = new BeINSportsClientTestable();

		BeINSportsInquiryRequestDTO beINSportsInquiryRequestDTO = new BeINSportsInquiryRequestDTO();
		beINSportsInquiryRequestDTO.setMsisdn("01148379445");
		beINSportsInquiryRequestDTO.setReferenceNumber("1215454664564");

		BeINSportsInquiryResponseDTO beINSportsInquiryResponse = beINSportsClient
				.beINSportsInquiry(beINSportsInquiryRequestDTO, "ar", "ODoxMjM0NTY3ODk=");

		assertNotNull(beINSportsInquiryResponse);
		assertNotNull(beINSportsInquiryResponse.getValidationId());
		assertNotEquals("", beINSportsInquiryResponse.getValidationId().trim());
	}

	public class BeINSportsClientTestable extends BeINSportsClient {
		@Override
		protected CloseableHttpResponse executeGetRequest(CloseableHttpClient httpClient, HttpGet httpGet)
				throws Exception {
			return null;
		}

		@Override
		protected String getJsonFromResponse(CloseableHttpResponse closableHttpResponse)
				throws IOException, ParseException {
			return "{\r\n" + "  \"masaryCommission\": 0,\r\n" + "  \"merchantCommission\": 0,\r\n"
					+ "  \"fromAccountId\": 0,\r\n" + "  \"serviceName\": null,\r\n"
					+ "  \"providerCategory\": null,\r\n" + "  \"ratePlanId\": 0,\r\n" + "  \"appliedFees\": 0,\r\n"
					+ "  \"tax\": 0,\r\n" + "  \"transactionAmount\": 0,\r\n" + "  \"toBepaid\": 0,\r\n"
					+ "  \"serviceAmount\": 0,\r\n" + "  \"validationId\": \"1530701506944\",\r\n"
					+ "  \"expirationDate\": 0,\r\n" + "  \"clientName\": \"Alaa Tarek\",\r\n"
					+ "  \"billNumber\": \"12\",\r\n" + "  \"fees\": 1,\r\n" + "  \"minAmount\": 5,\r\n"
					+ "  \"ePayBillRecID\": \"55555\",\r\n" + "  \"sequence\": \"1\",\r\n"
					+ "  \"curCode\": \"818\",\r\n" + "  \"referenceNumber\": \"10000\",\r\n"
					+ "  \"billRefInfo\": null,\r\n" + "  \"msisdn\": null,\r\n" + "  \"amount\": 5,\r\n"
					+ "  \"programName\": \"beInSports\",\r\n" + "  \"coverageEndDate\": \"7/2/2018\"\r\n" + "}";
		}

		@Override
		protected int getStatusCode(CloseableHttpResponse httpResponse) {
			return 200;
		}

	}

	@Test
	public void testPrepareUrl_succesResponse_returnFormattedURL() throws Exception {
		String referenceNumber = "123456789";
		String msisdn = "01009057238";

		String input = "http://172.16.10.193:9005/beInSports/inquiry/{referenceNumber}/{msisdn}";

		String expected = "http://172.16.10.193:9005/beInSports/inquiry/123456789/01009057238";

		BeINSportsClient beINSportsClient = new BeINSportsClient();

		String result = beINSportsClient.prepareUrl(referenceNumber, msisdn, input);

		assertEquals(expected, result);

	}

	@Test(expected = Exception.class)
	public void testBeINSportsInquiry_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		BeINSportsClient beINSportsClient = new BeINSportsClientTestableError();

		BeINSportsInquiryRequestDTO beINSportsInquiryRequestDTO = new BeINSportsInquiryRequestDTO();
		beINSportsInquiryRequestDTO.setMsisdn("01148379445");
		beINSportsInquiryRequestDTO.setReferenceNumber("1215454664564");

		beINSportsClient.beINSportsInquiry(beINSportsInquiryRequestDTO, "ar", "ODoxMjM0NTY3ODk=");

	}

	public class BeINSportsClientTestableError extends BeINSportsClient {
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
	public void testBeINSportsPayment_succesResponse_returnPaymentResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		BeINSportsClient beINSportsClient = new BeINSportsClientTestable();

		BeINSportsPaymentRequestDTO beINSportsPaymentRequest = new BeINSportsPaymentRequestDTO();
		beINSportsPaymentRequest.setValidationId("1531313153035");

		BeINSportsPaymentResponseDTO beINSportsPaymentResponse = beINSportsClient
				.beINSportsPayment(beINSportsPaymentRequest, "ar", "ODoxMjM0NTY3ODk=");

		assertNotNull(beINSportsPaymentResponse);

	}

	@Test(expected = Exception.class)
	public void testBeINSportsPayment_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		BeINSportsClient beINSportsClient = new BeINSportsClientTestableError();

		BeINSportsPaymentRequestDTO beINSportsPaymentRequest = new BeINSportsPaymentRequestDTO();
		beINSportsPaymentRequest.setValidationId("1531313153035");

		beINSportsClient.beINSportsPayment(beINSportsPaymentRequest, "ar", "ODoxMjM0NTY3ODk=");

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
			return "{\r\n" + "  \"eventName\": null,\r\n" + "  \"entityName\": null,\r\n"
					+ "  \"referenceNumber\": \"10570256932\",\r\n" + "  \"billAmount\": 456,\r\n"
					+ "  \"clientName\": \"MOHAMED FATHY EL SAYED\",\r\n" + "  \"transactionId\": 7102,\r\n"
					+ "  \"insertDate\": 1531313195576,\r\n" + "  \"updateDate\": 1531313197084,\r\n"
					+ "  \"appliedFees\": 0,\r\n" + "  \"merchantCommission\": 2.371,\r\n" + "  \"tax\": 0,\r\n"
					+ "  \"toBepaid\": 456,\r\n" + "  \"requestStatus\": \"SUCCEEDED\",\r\n"
					+ "  \"ledgerStatus\": \"COMMITTED\",\r\n" + "  \"accountId\": 8501,\r\n"
					+ "  \"deviceType\": \"WEB\",\r\n" + "  \"globalTrxId\": \"1531313195555\",\r\n"
					+ "  \"transactionAmount\": 453.629,\r\n" + "  \"ledgerTrxId\": 220620281,\r\n"
					+ "  \"billNumber\": \"1807111446424565 - 1\",\r\n" + "  \"targetNumber\": \"10570256932\",\r\n"
					+ "  \"paybillRequestId\": \"edf552e6-e45e-42e0-b340-55b22f8c50be\",\r\n"
					+ "  \"paymentId\": \"1531313195576\",\r\n" + "  \"paymentRefInfo\": \"0\",\r\n"
					+ "  \"programName\": \"beIN Access\",\r\n" + "  \"coverageEndDate\": \"01/02/2019\",\r\n"
					+ "  \"serviceId\": \"23011\",\r\n" + "  \"rqUID\": \"1531313195576\"\r\n" + "}";
		}

	}

	@Test(expected = Exception.class)
	public void testBeINSportsReprint_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		BeINSportsClient beINSportsClient = new BeINSportsClientTestableError();

		beINSportsClient.beINSportsReprint("220620306", "ar", "ODoxMjM0NTY3ODk=", "192");

	}

	@Test
	public void testBeINSportsReprint_succesResponse_returnReprintResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		BeINSportsClient beINSportsClient = new BeINSportsClientTestable();

		BeINSportsPaymentResponseDTO beINSportsPaymentResponse = beINSportsClient.beINSportsReprint("220620306", "ar",
				"ODoxMjM0NTY3ODk=", "192");

		assertNotNull(beINSportsPaymentResponse);

	}

	@Test
	public void testPrepareReprintUrl_succesResponse_returnFormattedURL() throws Exception {
		String ledgerTrxId = "220620306";

		String input = "http://172.16.10.193:9005/beInSports/reprint/{ledgerTrxId}";

		String expected = "http://172.16.10.193:9005/beInSports/reprint/220620306";

		BeINSportsClient beINSportsClient = new BeINSportsClient();

		String result = beINSportsClient.prepareReprintURL(ledgerTrxId, input);

		assertEquals(expected, result);

	}

}