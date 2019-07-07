package com.masary.integration;

import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.masary.database.manager.BaseManger;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.ZaidElKhierCategoriesResponse;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ZaidElKhierClient.class, BaseManger.class, MasaryManager.class})
@PowerMockIgnore("javax.net.ssl.*")
public class ZaidElKhierClientTest extends TestCase
{
	private String language = "ar";
	private String token = "ODoxMjM0NTY3ODk=";
	
	private CloseableHttpClient httpClient;
	private HttpGet httpGet;
	
	@BeforeClass
    public static void oneTimeSetup() {
        System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
        System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
    }
	
	@Before
	public void init()
	{
		httpClient = HttpClients.createDefault();
		httpGet = new HttpGet();
	}
	
	
	@Test
	public void testGetCategories_validParameters_returnListOfCategories() throws Exception
	{
		Logger logger = mock(Logger.class);

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);
		
		ZaidElKhierClient zaidElKhierClient = new TestableZaidElKhierClient(httpClient, httpGet, logger);

		ZaidElKhierCategoriesResponse[] categories = zaidElKhierClient.getCategories(language, token);
		assertTrue(categories.length > 0);
	}
}

class TestableZaidElKhierClient extends ZaidElKhierClient
{
	public TestableZaidElKhierClient(CloseableHttpClient httpclient, HttpGet httpGet, Logger logger)
	{
		super(httpclient, httpGet, logger);
	}

	@Override
	protected String loadUrlProperty(String propertyName)
	{
		return "http://172.16.10.193:9005/ZaidElkier/Categories";
	}
	
}

