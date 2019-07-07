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
import com.masary.integration.dto.AcceptInquiryRequest;
import com.masary.integration.dto.AcceptInquiryResponse;
import com.masary.integration.dto.AcceptPaymentRequest;
import com.masary.integration.dto.AcceptPaymentResponse;
import junit.framework.TestCase;


@RunWith(PowerMockRunner.class)
@PrepareForTest({ AcceptClient.class, MasaryManager.class, BaseManger.class })
@PowerMockIgnore("javax.net.ssl.*")
public class AcceptClientTest extends TestCase {
	
	@BeforeClass
	public static void oneTimeSetup() {
		System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
		System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
	}

	@Test
	public void testAcceptInquiry_succesResponse_returnInquiryResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		AcceptClient acceptClient = new AcceptClientTestable();
		AcceptInquiryRequest acceptInquiryRequest = new AcceptInquiryRequest ();
		acceptInquiryRequest.setMobileNumber("01148379445");
		acceptInquiryRequest.setBillReference("011111111111111");
		acceptInquiryRequest.setBiller("23113");

		AcceptInquiryResponse acceptInquiryResponse = acceptClient.acceptValidateInquiry(acceptInquiryRequest, "ar", "ODoxMjM0NTY3ODk=",acceptInquiryRequest.getBiller());

		assertNotNull(acceptInquiryResponse);
		assertNotNull(acceptInquiryResponse.getInquiryReferenceId());
		assertNotEquals("", acceptInquiryResponse.getInquiryReferenceId().trim());
	}

	public class AcceptClientTestable extends AcceptClient {
		@Override
		protected CloseableHttpResponse executeGetRequest(CloseableHttpClient httpClient, HttpGet httpGet)
				throws Exception {
			return null;
		}

		@Override
		protected String getJsonFromResponse(CloseableHttpResponse closableHttpResponse)
				throws IOException, ParseException {
			return "{\r\n" + 
					"  \"validationId\": \"12\",\r\n" + 
					"  \"expirationDate\": 1,\r\n" + 
					"  \"appliedFees\": 1,\r\n" + 
					"  \"merchantCommission\": 0.1,\r\n" + 
					"  \"masaryCommisssion\": 0.5,\r\n" + 
					"  \"tax\": 0.5,\r\n" + 
					"  \"toBepaid\": 102.1,\r\n" + 
					"  \"globalTrxId\": \"333\",\r\n" + 
					"  \"transactionAmount\": 101.6,\r\n" + 
					"  \"ratePlanId\": 261,\r\n" + 
					"  \"accountId\": 7090,\r\n" + 
					"  \"billerID\": \"accept\",\r\n" + 
					"  \"billerName\": \"Accept\",\r\n" + 
					"  \"billerCode\": \"acc\",\r\n" + 
					"  \"clientName\": \"Masary\",\r\n" + 
					"  \"dueAmount\": 100,\r\n" + 
					"  \"mobileNo\": \"01063748885\",\r\n" + 
					"  \"paymentReferenceId\": \"paymentrefid\",\r\n" + 
					"  \"inquiryReferenceId\": \"inquiryrefid\",\r\n" + 
					"  \"orderName\": \"Details\"\r\n" + 
					"}";
		}

		@Override
		protected int getStatusCode(CloseableHttpResponse httpResponse) {
			return 200;
		}

	}

	@Test
	public void testPrepareUrl_succesResponse_returnFormattedURL() throws Exception {
		
		String billReference = "011111111111111";
		String biller = "23113";
		String mobileNumber = "01063748885";


		String input = "http://172.16.10.193:9005/accept/inquiry/{billReference}/{biller}/{mobileNumber}";

		String expected = "http://172.16.10.193:9005/accept/inquiry/011111111111111/23113/01063748885";

		AcceptClient acceptClient = new AcceptClient();

		String result = acceptClient.prepareUrl(billReference, biller,mobileNumber ,input);

		assertEquals(expected, result);

	}
	
	@Test(expected = Exception.class)
	public void testAcceptInquiry_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		AcceptClient acceptClient  = new AcceptClientTestableError();

		AcceptInquiryRequest acceptInquiryRequest = new AcceptInquiryRequest ();
		acceptInquiryRequest.setMobileNumber("01148379445");
		acceptInquiryRequest.setBillReference("01111111111");
		acceptInquiryRequest.setBiller("23113");

		acceptClient.acceptValidateInquiry(acceptInquiryRequest, "ar", "ODoxMjM0NTY3ODk=",acceptInquiryRequest.getBiller());

	}

	public class AcceptClientTestableError extends AcceptClient {
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
		protected String prepareUrl(String billReference, String biller,String mobileNumber ,String input) {
			return "";
		}
	}
	
	@Test
	public void testAcceptPayment_succesResponse_returnPaymentResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		AcceptClient acceptClient = new AcceptClientPaymentTest();

		AcceptPaymentRequest acceptPaymentRequest = new AcceptPaymentRequest();
		acceptPaymentRequest.setValidationId("12");

		AcceptPaymentResponse acceptPaymentResponse = acceptClient.acceptValidatePayment(acceptPaymentRequest,"ar","ODoxMjM0NTY3ODk=","23113");

		assertNotNull(acceptPaymentResponse);

	}

	public class AcceptClientPaymentTest extends AcceptClient {

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
					"  \"insertDate\": 1536569301009,\r\n" + 
					"  \"appliedFees\": 10.5,\r\n" + 
					"  \"merchantCommission\": 0.5,\r\n" + 
					"  \"tax\": null,\r\n" + 
					"  \"toBepaid\": 13,\r\n" + 
					"  \"requestStatus\": null,\r\n" + 
					"  \"accountId\": 7090,\r\n" + 
					"  \"deviceType\": \"WeB\",\r\n" + 
					"  \"globalTrxId\": \"333\",\r\n" + 
					"  \"transactionAmount\": 13.5,\r\n" + 
					"  \"ledgerTrxId\": null,\r\n" + 
					"  \"ledgerStatus\": null,\r\n" + 
					"  \"ratePlanId\": null,\r\n" + 
					"  \"billerID\": \"accept\",\r\n" + 
					"  \"billerName\": \"accept\",\r\n" + 
					"  \"clientName\": \"Masary\",\r\n" + 
					"  \"dueAmount\": 12.5,\r\n" + 
					"  \"mobileNo\": \"01145778625\",\r\n" + 
					"  \"paymentReferenceId\": null,\r\n" + 
					"  \"inquiryReferenceId\": \"424\",\r\n" + 
					"  \"updateDate\": 1536569301009\r\n" + 
					"}";
		}

	}

	@Test(expected = Exception.class)
	public void testAcceptPayment_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		AcceptClient acceptClient = new AcceptClientTestableError();

		AcceptPaymentRequest acceptPaymentRequest = new AcceptPaymentRequest();
		acceptPaymentRequest.setValidationId("12");

		acceptClient.acceptValidatePayment(acceptPaymentRequest,"ar","ODoxMjM0NTY3ODk=","23113");
	}
	

	@Test
	public void testAcceptReprint_succesResponse_returnReprintResponse() throws Exception {
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		AcceptClient acceptClient = new AcceptClientTestable();

		AcceptPaymentResponse acceptPaymentResponse = acceptClient.acceptReprint("220620306", "ar", "ODoxMjM0NTY3ODk=", "192","23113");


		assertNotNull(acceptPaymentResponse);

	}
	

	@Test(expected = Exception.class)
	public void testAcceptReprint_ErrorThrown_Exception() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		AcceptClient acceptClient = new AcceptClientTestableError();

		acceptClient.acceptReprint("220620306", "ar", "ODoxMjM0NTY3ODk=", "192","23113");

	}

	@Test
	public void testPrepareReprintUrl_succesResponse_returnFormattedURL() throws Exception {
		String ledgerTrxId = "220620306";

		String input = "http://172.16.10.193:9005/accept/reprint/{ledgerTrxId}";

		String expected = "http://172.16.10.193:9005/accept/reprint/220620306";

		AcceptClient acceptClient = new AcceptClient();

		String result = acceptClient.prepareReprintURL(ledgerTrxId, input);

		assertEquals(expected, result);

	}




}
