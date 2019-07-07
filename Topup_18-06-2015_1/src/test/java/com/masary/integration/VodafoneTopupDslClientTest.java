/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import java.io.IOException;

/**
 *
 * @author Ahmed Khaled
 */
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
import com.masary.integration.dto.VodafoneDslDenomination;
import com.masary.integration.dto.VodafoneDslPaymentResponse;
import com.masary.integration.dto.VodafoneTopupDslRequest;

import junit.framework.TestCase;
import org.apache.http.client.methods.CloseableHttpResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({VodafoneTopupDslClient.class, MasaryManager.class, BaseManger.class})
@PowerMockIgnore("javax.net.ssl.*")
public class VodafoneTopupDslClientTest extends TestCase {

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
    public void init() {
        httpClient = HttpClients.createDefault();
        httpGet = new HttpGet();
    }

    @Test
    public void testDenominations_succesResponse_returnDenominations() throws Exception {

        Logger logger = mock(Logger.class);
        spy(BaseManger.class);
        doNothing().when(BaseManger.class, "initDataSource");
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);

        VodafoneTopupDslClient VodafoneTopupDslClient = new VodafoneTopupDslClientTestable();

        VodafoneDslDenomination[] denominations = VodafoneTopupDslClient.getDenominations(language, token);

        assertNotNull(denominations);
        assertTrue(denominations.length > 0);

    }

    @Test
    public void testCharge_succesResponse_returnChargeResponse() throws Exception {

        Logger logger = mock(Logger.class);
        spy(BaseManger.class);
        doNothing().when(BaseManger.class, "initDataSource");
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);

        VodafoneTopupDslClient vodafoneTopupDslClient = new VodafoneTopupDslClientTestable();

        VodafoneTopupDslRequest vodafoneTopupDslRequest = new VodafoneTopupDslRequest();
        vodafoneTopupDslRequest.setDenominationId(2);
        vodafoneTopupDslRequest.setMsisdn("0228446920");

        VodafoneDslPaymentResponse vodafoneDslPaymentResponse = vodafoneTopupDslClient.topupCharge(vodafoneTopupDslRequest, language, token);

        assertNotNull(vodafoneDslPaymentResponse);
        assertTrue(vodafoneTopupDslRequest.getMsisdn().length() <= 10);

    }

    @Test
    public void testReprint_succesResponse_returnReprintResponse() throws Exception {

        Logger logger = mock(Logger.class);
        spy(BaseManger.class);
        doNothing().when(BaseManger.class, "initDataSource");
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);

        VodafoneTopupDslClient vodafoneTopupDslClient = new VodafoneTopupDslClientTestable();

        long ledgerTrxId = 245665321L;

        VodafoneDslPaymentResponse vodafoneDslPaymentResponse = vodafoneTopupDslClient.reprint(ledgerTrxId, language, token);

        assertNotNull(vodafoneDslPaymentResponse);

    }

}

class VodafoneTopupDslClientTestable extends VodafoneTopupDslClient {

    @Override
    protected CloseableHttpResponse executeGetRequest(CloseableHttpClient httpClient, HttpGet httpGet)
            throws Exception {
        return null;
    }

    @Override
    protected String getJsonFromResponse(CloseableHttpResponse closableHttpResponse)
            throws IOException {
        return "{\n"
                + "  \"eventName\": null,\n"
                + "  \"entityName\": null,\n"
                + "  \"requestId\": 52,\n"
                + "  \"requestStatus\": \"SUCCEEDED\",\n"
                + "  \"accountId\": 8,\n"
                + "  \"targetNumber\": \"022222222\",\n"
                + "  \"amount\": 10,\n"
                + "  \"ledgerTransactionId\": 220621146,\n"
                + "  \"ledgerTransactionStatus\": \"COMMITTED\",\n"
                + "  \"deviceType\": \"WEB\",\n"
                + "  \"globalTrxId\": \"1546350391698\",\n"
                + "  \"operatorTransactionId\": \"1546350392467\",\n"
                + "  \"opratorStatusCode\": \"200\",\n"
                + "  \"insertTime\": 1546350392311,\n"
                + "  \"updateTime\": 1546350392467,\n"
                + "  \"appliedFees\": 0,\n"
                + "  \"merchantCommission\": 0.17,\n"
                + "  \"tax\": 0,\n"
                + "  \"transactionAmount\": 9.83,\n"
                + "  \"toBePaid\": 10,\n"
                + "  \"externalTRXId\": null,\n"
                + "  \"rateplanAmount\": 10,\n"
                + "  \"providerAmount\": 7,\n"
                + "  \"advertisementStatment\": \"\"\n"
                + "}";
    }

    @Override
    protected int getStatusCode(CloseableHttpResponse httpResponse) {
        return 200;
    }

}
