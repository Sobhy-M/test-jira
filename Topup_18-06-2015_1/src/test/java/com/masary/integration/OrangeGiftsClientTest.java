/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.google.gson.JsonSyntaxException;
import com.masary.database.manager.BaseManger;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.OrangeGiftsInquiryRepresentation;
import com.masary.integration.dto.OrangeGiftsPaymentRepresentation;
import com.masary.integration.dto.OrangeGiftsRequest;
import java.util.ResourceBundle;
import junit.framework.TestCase;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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

@PowerMockIgnore("javax.net.ssl.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest({OrangeGiftsClient.class, MasaryManager.class, BaseManger.class})
public class OrangeGiftsClientTest extends TestCase {

    private String language = "ar";
    private String token = "ODoxMjM0NTY3ODk=";

    private Logger logger;
    private OrangeGiftsClient orangeGiftsClient;
    private OrangeGiftsRequest orangeGiftsRequest;

    private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");

    @BeforeClass
    public static void oneTimeSetup() {
        System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
        System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
    }

    @Before
    public void init() {
        logger = mock(Logger.class);
        orangeGiftsClient = new OrangeGiftsTestable();
        orangeGiftsRequest = new OrangeGiftsRequest("1245566", "45649854", "01277007985");

        spy(BaseManger.class);
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);
    }

    @Test
    public void testInquiry_should_return_valid_Code() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");

        OrangeGiftsInquiryRepresentation orangeGiftsInquiryRepresentation = orangeGiftsClient.verfiVoucher("123456", language, token);
        assertTrue(orangeGiftsInquiryRepresentation.isResult());
        assertTrue(orangeGiftsInquiryRepresentation.isVouchersStatusIsFilled());
    }

    @Test
    public void testPayment_should_return_reciept_representation() throws Exception {

        doNothing().when(BaseManger.class, "initDataSource");

        OrangeGiftsPaymentRepresentation orangeGiftsPaymentRepresentation = orangeGiftsClient.voucherRedemption(orangeGiftsRequest, language, token);
        assertTrue(!orangeGiftsPaymentRepresentation.getPhoneNumber().equals(""));
    }

    @Test
    public void testReprint_should_return_reciept_representation() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");

        OrangeGiftsPaymentRepresentation orangeGiftsPaymentRepresentation = orangeGiftsClient.reprint(123456L, language, token);
        assertTrue(!orangeGiftsPaymentRepresentation.getPhoneNumber().equals(""));
    }
}

class OrangeGiftsTestable extends OrangeGiftsClient {

    private OrangeGiftsInquiryRepresentation orangeGiftsInquiryRepresentation;
    private OrangeGiftsPaymentRepresentation orangeGiftsPaymentRepresentation;

    public OrangeGiftsTestable() {
        orangeGiftsInquiryRepresentation = new OrangeGiftsInquiryRepresentation();
        orangeGiftsInquiryRepresentation.setExpirationDate(123456789423L);
        orangeGiftsInquiryRepresentation.setRequestID(124578955L);
        orangeGiftsInquiryRepresentation.setResult(true);
        orangeGiftsInquiryRepresentation.setValidationId("45664231899");
        orangeGiftsInquiryRepresentation.setVoucherAmount(10.0);
        orangeGiftsInquiryRepresentation.setVoucherID(15645L);
        orangeGiftsInquiryRepresentation.setVouchersStatusIsFilled(true);

        orangeGiftsPaymentRepresentation = new OrangeGiftsPaymentRepresentation();
        orangeGiftsPaymentRepresentation.setAccountId(8L);
        orangeGiftsPaymentRepresentation.setPhoneNumber("01277007985");
        orangeGiftsPaymentRepresentation.setGlobalTrxId("1245648999756");
        orangeGiftsPaymentRepresentation.setAmount(10);
        orangeGiftsPaymentRepresentation.setVoucherID("54698798");

    }

    @Override
    protected <T> T json2Object(String jsonResponse, Class<T> classOfT) throws JsonSyntaxException {

        if (classOfT.getName().contains("OrangeGiftsInquiryRepresentation")) {
            return (T) orangeGiftsInquiryRepresentation;
        } else if (classOfT.getName().contains("OrangeGiftsPaymentRepresentation")) {
            return (T) orangeGiftsPaymentRepresentation;
        } else {
            return null;
        }

    }

    @Override
    protected int getStatusCode(CloseableHttpResponse httpResponse) {
        return 200;
    }

}
