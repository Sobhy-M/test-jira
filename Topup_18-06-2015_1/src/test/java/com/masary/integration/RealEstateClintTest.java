/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.database.manager.BaseManger;
import com.masary.database.manager.MasaryManager;

import com.masary.integration.dto.RealEstateInquiryRepresentation;
import com.masary.integration.dto.RealEstatePaymentRepresentation;
import com.masary.integration.dto.RealEstatePaymentRequest;

import java.io.IOException;
import java.util.ResourceBundle;
import junit.framework.TestCase;

import static junit.framework.TestCase.assertNotNull;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.junit.Before;

import org.junit.BeforeClass;
import org.junit.Test;
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
 * @author omar.abdellah
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({RealEstateClient.class, MasaryManager.class, BaseManger.class})
@PowerMockIgnore("javax.net.ssl.*")
public class RealEstateClintTest extends TestCase {

    private String language = "ar";
    private String token = "ODoxMjM0NTY3ODk=";
    private Logger logger;
    private RealEstateClient realEstateClient;
    private RealEstatePaymentRequest realEstatePaymentRequest;
    private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");

    @BeforeClass
    public static void oneTimeSetup() {
        System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
        System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
    }

    @Before
    public void init() {
        logger = mock(Logger.class);

        realEstateClient = new RealEstateTestable();
        realEstatePaymentRequest = new RealEstatePaymentRequest("0228446920");

        spy(BaseManger.class);
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);

    }

    @Test
    public void testrealEstaltInquiry_succesResponse_returnInquiryResponse() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");
        RealEstateInquiryRepresentation realEstateInquiryRepresentation = realEstateClient.inquiryCode("123", "ar", "ODoxMjM0NTY3ODk=");

        assertNotNull(realEstateInquiryRepresentation);

    }

    private static class RealEstateTestable extends RealEstateClient {

        @Override
        protected CloseableHttpResponse executeGetRequest(CloseableHttpClient httpClient, HttpGet httpGet)
                throws Exception {
            return null;
        }

        @Override
        protected String getJsonFromResponse(CloseableHttpResponse closableHttpResponse)
                throws IOException, ParseException {
            return "{\r\n"
                    + "  \"validationId\": \"12\",\r\n"
                    + "  \"expirationDate\": 1,\r\n"
                    + "  \"appliedFees\": 1,\r\n"
                    + "  \"merchantCommission\": 0.1,\r\n"
                    + "  \"masaryCommisssion\": 0.5,\r\n"
                    + "  \"tax\": 0.5,\r\n"
                    + "  \"toBepaid\": 102.1,\r\n"
                    + "  \"globalTrxId\": \"333\",\r\n"
                    + "  \"transactionAmount\": 101.6,\r\n"
                    + "  \"ratePlanId\": 261,\r\n"
                    + "  \"accountId\": 7090,\r\n"
                    + "  \"billerID\": \"accept\",\r\n"
                    + "  \"billerName\": \"Accept\",\r\n"
                    + "  \"billerCode\": \"acc\",\r\n"
                    + "  \"clientName\": \"Masary\",\r\n"
                    + "  \"dueAmount\": 100,\r\n"
                    + "  \"mobileNo\": \"01063748885\",\r\n"
                    + "  \"paymentReferenceId\": \"paymentrefid\",\r\n"
                    + "  \"inquiryReferenceId\": \"inquiryrefid\",\r\n"
                    + "  \"orderName\": \"Details\"\r\n"
                    + "}";
        }

        @Override
        protected int getStatusCode(CloseableHttpResponse httpResponse) {
            return 200;
        }

        @Test(expected = Exception.class)
        public void testAcceptInquiry_ErrorThrown_Exception() throws Exception {

            Logger logger = mock(Logger.class);

            spy(BaseManger.class);
            doNothing().when(BaseManger.class, "initDataSource");
            Whitebox.setInternalState(MasaryManager.class, "logger", logger);

            RealEstateClient realEstateClient = new RealEstateClintTest.RealEstateTestable();

            String accountNumber = "011111111111111";
            realEstateClient.inquiryCode(accountNumber, "ar", "ODoxMjM0NTY3ODk=");

        }
    }

    public class RealEstateClientTestableError extends RealEstateClient {

        String actionDestinationResponse;
        String paymentResponseJson;

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

        protected String prepareUrl(String billReference, String input) {
            return "";
        }
    }

    @Test
    public void testAcceptPayment_succesResponse_returnPaymentResponse() throws Exception {

        spy(BaseManger.class);
        doNothing().when(BaseManger.class, "initDataSource");
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);

        realEstatePaymentRequest = new RealEstatePaymentRequest("12");

        RealEstatePaymentRepresentation acceptPaymentResponse = realEstateClient.payment(realEstatePaymentRequest, "ar", "ODoxMjM0NTY3ODk=");

        assertNotNull(acceptPaymentResponse);

    }

    @Test
    public void testPrepareReprintUrl_succesResponse_returnFormattedURL() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");
        RealEstatePaymentRepresentation realEstatePaymentRepresentation = realEstateClient.reprint(220620306, language, token);

        assertNotNull(realEstatePaymentRepresentation);
    }

}
