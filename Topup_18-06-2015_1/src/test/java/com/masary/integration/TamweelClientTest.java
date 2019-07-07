/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.database.manager.BaseManger;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.TamweelInquiryRepresentation;
import com.masary.integration.dto.TamweelPaymentRequest;
import java.util.ResourceBundle;

import junit.framework.TestCase;
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
@PowerMockIgnore("javax.net.ssl.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest({TamweelClient.class, MasaryManager.class, BaseManger.class})
public class TamweelClientTest  extends TestCase {
    private String language = "ar";
    private String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4NTAxIiwicm9sZXMiOiJhbGwgcm9sZXMiLCJleHAiOjE1ODA5ODkyNjF9.4-8IdigBCe28UXeQd462PVYShC09Ebmru-YW_2DIFHL_HzRhPEpG8WM4ecdvrd5z8KKJBkquC9-MMFTZax33AA";

    private Logger logger;
    private TamweelClient tamweelClient;
    private TamweelPaymentRequest tamweelPaymentRequest;
        private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");
     @BeforeClass
    public static void oneTimeSetup() {
        System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
        System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
    }
     @Before
    public void init() {
        logger = mock(Logger.class);
     //   tamweelClient = new OrangeGiftsTestable();
        tamweelPaymentRequest = new TamweelPaymentRequest("1245566","01277007985");

        spy(BaseManger.class);
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);
    }
      @Test
    public void testInquiry_should_return_valid_Code() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");

      TamweelInquiryRepresentation  tamweelInquiryRepresentation = tamweelClient.inquiryCode("123456", language, token);
        //assertTrue(tamweelInquiryRepresentation);
        
    }

  
    
}
