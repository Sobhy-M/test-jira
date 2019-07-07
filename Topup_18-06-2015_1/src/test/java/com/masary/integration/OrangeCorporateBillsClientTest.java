/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.database.manager.BaseManger;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.OrangeCorporateInquiryResponse;
import com.masary.integration.dto.OrangeCorporatePaymentResponse;
import com.masary.integration.dto.OrangeCorporateRequest;
import java.io.IOException;
import junit.framework.TestCase;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

/**
 *
 * @author Ahmed Khaled
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({VodafoneTopupDslClient.class, MasaryManager.class, BaseManger.class})
@PowerMockIgnore("javax.net.ssl.*")
public class OrangeCorporateBillsClientTest extends TestCase {

    private String inquiryurl = "/orangecorp/msisdn/1282050759/accountNumber/615442121.6544";
    private String amountinquiryurl = "/orangecorp/paidAmount/300/validationId1/456465413123";
    private String paymenturl = "/orangecorp/payment";
    private String reprinturl = "/orangecorp/reprint/220620827";

    private String language = "ar";
    private String token = "ODoxMjM0NTY3ODk=";

    private CloseableHttpClient httpClient;
    private HttpGet httpGet;

    private OrangeCorporateRequest orangeCorpPaymentRequestDto;

    @Before
    public void init() {
        orangeCorpPaymentRequestDto = new OrangeCorporateRequest();
        orangeCorpPaymentRequestDto.setAccountNumber("6.1245665");
        orangeCorpPaymentRequestDto.setMsisdn("1282050759");
        orangeCorpPaymentRequestDto.setPaidAmount(300);
        orangeCorpPaymentRequestDto.setValidationId("1234654898");
        orangeCorpPaymentRequestDto.setValidationId1("12316598355");
    }

    @BeforeClass
    public static void oneTimeSetup() {
        System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
        System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
    }

    @Test
    public void testInquirySuccessfulFlow() throws Exception {
        Logger logger = mock(Logger.class);
        spy(BaseManger.class);
        doNothing().when(BaseManger.class, "initDataSource");
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);

        OrangeCorporateBillsClient orangeCorporateBillsClient = new OrangeCorporateBillsClientTestable();
        OrangeCorporateInquiryResponse orangeCorporateInquiryResponse = orangeCorporateBillsClient.orangeCorporateInquiry(orangeCorpPaymentRequestDto, language, token);

        assertNotNull(orangeCorporateInquiryResponse);
    }

    @Test
    public void testInquiryAmountSuccessfulFlow() throws Exception {
        Logger logger = mock(Logger.class);
        spy(BaseManger.class);
        doNothing().when(BaseManger.class, "initDataSource");
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);

        OrangeCorporateBillsClient orangeCorporateBillsClient = new OrangeCorporateBillsClientTestable();
        OrangeCorporateInquiryResponse orangeCorporateInquiryAmountResponse = orangeCorporateBillsClient.orangeCorporateAmountInquiry(orangeCorpPaymentRequestDto, language, token);

        assertNotNull(orangeCorporateInquiryAmountResponse);
    }

    @Test
    public void testPaymentSuccessfulFlow() throws Exception {
        Logger logger = mock(Logger.class);
        spy(BaseManger.class);
        doNothing().when(BaseManger.class, "initDataSource");
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);

        OrangeCorporateBillsClient orangeCorporateBillsClient = new OrangeCorporateBillsClientTestable();
        OrangeCorporatePaymentResponse orangeCorporatePaymentResponse = orangeCorporateBillsClient.orangeCorporatPayment(orangeCorpPaymentRequestDto, language, token);

        assertNotNull(orangeCorporatePaymentResponse);
    }
    
    @Test(expected = Exception.class)
    public void testThrowException() throws Exception {
        Logger logger = mock(Logger.class);
        spy(BaseManger.class);
        doNothing().when(BaseManger.class, "initDataSource");
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);
        OrangeCorporateBillsClient orangeCorporateBillsClient = new OrangeCorporateBillsClientTestable();
        OrangeCorporateInquiryResponse orangeCorporateInquiryResponse = orangeCorporateBillsClient.orangeCorporateInquiry(orangeCorpPaymentRequestDto, language, token);
        OrangeCorporateInquiryResponse orangeCorporateInquiryAmountResponse = orangeCorporateBillsClient.orangeCorporateAmountInquiry(orangeCorpPaymentRequestDto, language, token);
        OrangeCorporatePaymentResponse orangeCorporatePaymentResponse = orangeCorporateBillsClient.orangeCorporatPayment(orangeCorpPaymentRequestDto, language, token);

    }

}

class OrangeCorporateBillsClientTestable extends OrangeCorporateBillsClient {

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
                + "  \"providerStatusCode\": \"1\",\n"
                + "  \"providerStatusMessage\": null,\n"
                + "  \"msisdn\": \"1283597569\",\n"
                + "  \"accountNumber\": \"6\",\n"
                + "  \"totalOpenAmount\": 123.2,\n"
                + "  \"paidAmount\": 123.2,\n"
                + "  \"requestId\": 8100,\n"
                + "  \"insertDate\": 1547718668547,\n"
                + "  \"updateDate\": 1547718668871,\n"
                + "  \"appliedFees\": 2,\n"
                + "  \"merchantCommission\": 1,\n"
                + "  \"tax\": 12.32,\n"
                + "  \"toBepaid\": 125.2,\n"
                + "  \"masaryCommission\": 0,\n"
                + "  \"requestStatus\": \"SUCCEEDED\",\n"
                + "  \"accountId\": 8,\n"
                + "  \"deviceType\": \"WEB\",\n"
                + "  \"ledgerStatus\": \"COMMITTED\",\n"
                + "  \"ledgerTrxId\": 1547718668845,\n"
                + "  \"ratePlanId\": 261,\n"
                + "  \"transactionAmount\": 123.2,\n"
                + "  \"globalTrxId\": \"1547718668392\",\n"
                + "  \"targetNumber\": \"1283597569\",\n"
                + "  \"validationId\": \"1547718660273\"\n"
                + "}";
    }

    @Override
    protected int getStatusCode(CloseableHttpResponse httpResponse) {
        return 424;
    }

}
