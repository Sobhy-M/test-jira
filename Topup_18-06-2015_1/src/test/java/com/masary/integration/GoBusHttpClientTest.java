package com.masary.integration;

import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import com.google.gson.Gson;
import com.masary.database.manager.BaseManger;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.GoBusInquiryRepresentation;
import com.masary.integration.dto.GoBusPaymentData;
import com.masary.integration.dto.GoBusPaymentRepresentation;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ OrangeTopupHttpClient.class, BaseManger.class, MasaryManager.class })
@PowerMockIgnore("javax.net.ssl.*")
public class GoBusHttpClientTest extends TestCase {

	private String lang = "ar";
	private String token = "ODoxMjM0NTY3ODk=";

	@BeforeClass
	public static void oneTimeSetup() {
		System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
		System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
	}

	@Before
	public void init() {

	}

	@Test
	public void testGoGoBusPayment_validParameters_returnGoBusPaymentRepresentation() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");

		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		CloseableHttpClient closeableHttpClient = mock(CloseableHttpClient.class);
		CloseableHttpResponse closeableHttpResponse = mock(CloseableHttpResponse.class);
		HttpEntity httpEntity = mock(HttpEntity.class);

		StatusLine statusLine = mock(StatusLine.class);

		GoBusPaymentData goBusPaymentData = new GoBusPaymentData("123456", token, "localHost", lang);

		GoBusPaymentRepresentation goBusPaymentRepresentation = new GoBusPaymentRepresentation();
		goBusPaymentRepresentation.setProviderTransactionNumber("123456");

		String response = new Gson().toJson(goBusPaymentRepresentation);

		InputStream jsonResponse = new ByteArrayInputStream(response.getBytes());

		when(statusLine.getStatusCode()).thenReturn(200);
		when(closeableHttpResponse.getEntity()).thenReturn(httpEntity);
		when(closeableHttpResponse.getStatusLine()).thenReturn(statusLine);
		when(httpEntity.getContent()).thenReturn(jsonResponse);
		when(closeableHttpClient.execute(Mockito.isA(HttpPost.class))).thenReturn(closeableHttpResponse);

		GoBusHttpClient busHttpClient = new GoBusHttpClient(closeableHttpClient);

		GoBusPaymentRepresentation busPaymentRepresentation = busHttpClient.doGoBusPayment(goBusPaymentData);

		assertEquals("123456", busPaymentRepresentation.getProviderTransactionNumber());

	}

	@Test(expected = ClientProtocolException.class)
	public void testGoGoBusPayment_validParameters_throw_ClientProtocolException() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");

		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		CloseableHttpClient closeableHttpClient = mock(CloseableHttpClient.class);
		CloseableHttpResponse closeableHttpResponse = mock(CloseableHttpResponse.class);
		HttpEntity httpEntity = mock(HttpEntity.class);

		StatusLine statusLine = mock(StatusLine.class);

		GoBusPaymentData goBusPaymentData = new GoBusPaymentData("123456", token, "localHost", lang);

		GoBusPaymentRepresentation goBusPaymentRepresentation = new GoBusPaymentRepresentation();
		goBusPaymentRepresentation.setProviderTransactionNumber("123456");

		String response = new Gson().toJson(goBusPaymentRepresentation);

		InputStream jsonResponse = new ByteArrayInputStream(response.getBytes());

		when(statusLine.getStatusCode()).thenReturn(200);
		when(closeableHttpResponse.getEntity()).thenReturn(httpEntity);
		when(closeableHttpResponse.getStatusLine()).thenReturn(statusLine);
		when(httpEntity.getContent()).thenReturn(jsonResponse);
		when(closeableHttpClient.execute(Mockito.isA(HttpPost.class))).thenThrow(ClientProtocolException.class);

		GoBusHttpClient busHttpClient = new GoBusHttpClient(closeableHttpClient);

		GoBusPaymentRepresentation busPaymentRepresentation = busHttpClient.doGoBusPayment(goBusPaymentData);

	}

	@Test
	public void testDoGoBusInquery_validParameters_returnGoBusInquiryRepresentation() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");

		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		CloseableHttpClient closeableHttpClient = mock(CloseableHttpClient.class);
		CloseableHttpResponse closeableHttpResponse = mock(CloseableHttpResponse.class);
		HttpEntity httpEntity = mock(HttpEntity.class);

		StatusLine statusLine = mock(StatusLine.class);

		GoBusInquiryRepresentation busInquiryRepresentation = new GoBusInquiryRepresentation();
		busInquiryRepresentation.setProviderTransactionNumber("123456");

		String response = new Gson().toJson(busInquiryRepresentation);

		InputStream jsonResponse = new ByteArrayInputStream(response.getBytes());

		when(statusLine.getStatusCode()).thenReturn(200);
		when(closeableHttpResponse.getEntity()).thenReturn(httpEntity);
		when(closeableHttpResponse.getStatusLine()).thenReturn(statusLine);
		when(httpEntity.getContent()).thenReturn(jsonResponse);
		when(closeableHttpClient.execute(Mockito.isA(HttpGet.class))).thenReturn(closeableHttpResponse);

		GoBusHttpClient busHttpClient = new GoBusHttpClient(closeableHttpClient);

		GoBusInqueryDTO goBusInqueryDTO = new GoBusInqueryDTO();
		goBusInqueryDTO.setReferanceNum("123456789");
		goBusInqueryDTO.setLang(lang);
		goBusInqueryDTO.setToken(token);

		GoBusInquiryRepresentation goBusInquiryRepresentation = busHttpClient.doGoBusInquery(goBusInqueryDTO);

		assertEquals("123456", goBusInquiryRepresentation.getProviderTransactionNumber());

	}

	@Test(expected = ClientProtocolException.class)
	public void testDoGoBusInquery_validParameters__clientProtocolException() throws Exception {

		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");

		Whitebox.setInternalState(MasaryManager.class, "logger", logger);

		CloseableHttpClient closeableHttpClient = mock(CloseableHttpClient.class);
		CloseableHttpResponse closeableHttpResponse = mock(CloseableHttpResponse.class);
		HttpEntity httpEntity = mock(HttpEntity.class);

		StatusLine statusLine = mock(StatusLine.class);

		GoBusInquiryRepresentation busInquiryRepresentation = new GoBusInquiryRepresentation();
		busInquiryRepresentation.setProviderTransactionNumber("123456");

		String response = new Gson().toJson(busInquiryRepresentation);

		InputStream jsonResponse = new ByteArrayInputStream(response.getBytes());

		when(statusLine.getStatusCode()).thenReturn(200);
		when(closeableHttpResponse.getEntity()).thenReturn(httpEntity);
		when(closeableHttpResponse.getStatusLine()).thenReturn(statusLine);
		when(httpEntity.getContent()).thenReturn(jsonResponse);
		when(closeableHttpClient.execute(Mockito.isA(HttpGet.class))).thenThrow(ClientProtocolException.class);

		GoBusHttpClient busHttpClient = new GoBusHttpClient(closeableHttpClient);

		GoBusInqueryDTO goBusInqueryDTO = new GoBusInqueryDTO();
		goBusInqueryDTO.setReferanceNum("123456789");
		goBusInqueryDTO.setLang(lang);
		goBusInqueryDTO.setToken(token);

		GoBusInquiryRepresentation goBusInquiryRepresentation = busHttpClient.doGoBusInquery(goBusInqueryDTO);


	}

}
