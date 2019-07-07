/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.database.manager.BaseManger;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.EtisalatGiftsInquiryRepresentation;
import com.masary.integration.dto.EtisalatGiftsInquiryRequest;
import com.masary.integration.dto.EtisalatGiftsPaymentRepresentation;
import com.masary.integration.dto.EtisalatGiftsRequest;
import com.masary.integration.dto.OrangeGiftsInquiryRepresentation;
import com.masary.integration.dto.OrangeGiftsPaymentRepresentation;
import com.masary.integration.dto.OrangeGiftsRequest;
import java.util.ResourceBundle;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertTrue;
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
@PrepareForTest({EtisalatGiftsClient.class, MasaryManager.class, BaseManger.class})

/**
 *
 * @author omar.abdellah
 */
public class EtisalatGiftsClientTest extends TestCase {

    private String lang = "ar";
    private String token = "ODoxMjM0NTY3ODk=";
    private Logger logger;
    private EtisalatGiftsClient etisalatGiftsClient;
    private EtisalatGiftsRequest etisalatGiftsRequest;
    private EtisalatGiftsInquiryRequest etisalatGiftsInquiryRequest;
    private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");

    @BeforeClass
    public static void oneTimeSetup() {
        System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
        System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
    }

    @Before
    public void init() {
        logger = mock(Logger.class);
        etisalatGiftsClient = new EtisalatGiftsClient();

        etisalatGiftsRequest = new EtisalatGiftsRequest("1");

        etisalatGiftsInquiryRequest = new EtisalatGiftsInquiryRequest("1598197893", "1010", "10");
        spy(BaseManger.class);
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);
    }

    @Test
    public void testInquiry_should_return_valid_Code() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");

        EtisalatGiftsInquiryRepresentation etisalatGiftsInquiryRepresentation = etisalatGiftsClient.verfiVoucher(etisalatGiftsInquiryRequest, lang, token);
        assertNotNull(etisalatGiftsInquiryRepresentation);

    }

    @Test
    public void testPayment_should_return_reciept_representation() throws Exception {

        doNothing().when(BaseManger.class, "initDataSource");

        EtisalatGiftsPaymentRepresentation etisalatGiftsPaymentRepresentation = etisalatGiftsClient.voucherRedemption(etisalatGiftsRequest, lang, token);
        assertNotNull(etisalatGiftsPaymentRepresentation);
    }

    @Test
    public void testReprint_should_return_reciept_representation() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");

        EtisalatGiftsPaymentRepresentation etisalatGiftsPaymentRepresentation = etisalatGiftsClient.reprint(123456L, lang, token);
        assertNotNull(etisalatGiftsPaymentRepresentation);
    }

    @Test(expected = Exception.class)
    public void testInquiry_should_return_Invalid_Code() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");
        etisalatGiftsInquiryRequest = new EtisalatGiftsInquiryRequest("232322", "1010", "10");
        EtisalatGiftsInquiryRepresentation etisalatGiftsInquiryRepresentation = etisalatGiftsClient.verfiVoucher(etisalatGiftsInquiryRequest, lang, token);
        assertNotNull(etisalatGiftsInquiryRepresentation);

    }
    
     @Test(expected = Exception.class)
    public void testInquiry_should_return_Invalid_Amount() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");
        etisalatGiftsInquiryRequest = new EtisalatGiftsInquiryRequest("1598197893", "1010", "40");
        EtisalatGiftsInquiryRepresentation etisalatGiftsInquiryRepresentation = etisalatGiftsClient.verfiVoucher(etisalatGiftsInquiryRequest, lang, token);
        assertNotNull(etisalatGiftsInquiryRepresentation);

    }
    @Test(expected = Exception.class)
    public void testInquiry_should_return_Redeemed () throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");
        etisalatGiftsInquiryRequest = new EtisalatGiftsInquiryRequest("1598197893", "1010", "10");
        EtisalatGiftsInquiryRepresentation etisalatGiftsInquiryRepresentation = etisalatGiftsClient.verfiVoucher(etisalatGiftsInquiryRequest, lang, token);
        assertNotNull(etisalatGiftsInquiryRepresentation);

    }
}
