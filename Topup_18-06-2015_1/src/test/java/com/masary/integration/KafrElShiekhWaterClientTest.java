package com.masary.integration;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.masary.database.manager.BaseManger;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.KafrElShiekhWaterBillsSuggetionsResponse;
import com.masary.integration.dto.KafrElShiekhWaterInquiryResponse;
import com.masary.integration.dto.KafrElShiekhWaterPaymentRequest;
import com.masary.integration.dto.KafrElShiekhWaterPaymentResponse;
import com.masary.integration.dto.RealEstateInquiryRepresentation;
import com.masary.integration.dto.SuggestionBillsRepresentation;
import java.io.IOException;
import static junit.framework.TestCase.assertNotNull;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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

@RunWith(PowerMockRunner.class)
@PrepareForTest({RealEstateClient.class, MasaryManager.class, BaseManger.class})
@PowerMockIgnore("javax.net.ssl.*")
public class KafrElShiekhWaterClientTest {

    private String language = "ar";
    private String token = "ODoxMjM0NTY3ODk=";
    private Logger logger;

    private KafrElShiekhWaterClient kafrElshiekhWaterClient;
    private KafrElShiekhWaterPaymentRequest kafrElShiekhWaterPaymentRequest;

    @BeforeClass
    public static void oneTimeSetup() {
        System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
        System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
    }

    @Before
    public void init() {
        logger = mock(Logger.class);

        kafrElshiekhWaterClient = new KafrElshiekhWaterClientTestable();
        spy(BaseManger.class);
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);
        
        kafrElShiekhWaterPaymentRequest = new KafrElShiekhWaterPaymentRequest("4564564564");

    }

    @Test
    public void testInquiry_succesResponse_returnInquiryResponse() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");

        KafrElShiekhWaterInquiryResponse inquiryResponse = kafrElshiekhWaterClient.billsInquiry("4545646", "01148379445", language, token);

        assertNotNull(inquiryResponse);

    }
    
    @Test
    public void testLoadSuggestions_succesResponse_returnBillsSuggestionResponse() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");

        KafrElShiekhWaterBillsSuggetionsResponse billsSuggetionResponse = kafrElshiekhWaterClient.billsSuggestions("4545646", 50.0, language, token);

        assertNotNull(billsSuggetionResponse);

    }
    
    @Test
    public void testCommissionInquiry_succesResponse_returnCommissionInquiryResponse() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");

        KafrElShiekhWaterInquiryResponse commissionInquiryResponse = kafrElshiekhWaterClient.commissionInquiry("456456",94.0, language, token);

        assertNotNull(commissionInquiryResponse);

    }
    
     @Test
    public void testPayment_succesResponse_returnPaymentResponse() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");

        KafrElShiekhWaterPaymentResponse paymentResponse = kafrElshiekhWaterClient.payment(kafrElShiekhWaterPaymentRequest, language, token);

        assertNotNull(paymentResponse);

    }

    class KafrElshiekhWaterClientTestable extends KafrElShiekhWaterClient {

        @Override
        protected <T> T json2Object(String jsonResponse, Class<T> classOfT) throws JsonSyntaxException {
            if (classOfT.getName().contains("KafrElShiekhWaterInquiryResponse")) {
                jsonResponse = "{\n"
                        + "  \"validationId\": \"1555606093174001\",\n"
                        + "  \"subscriberName\": \"شركة مصر للتأمين\",\n"
                        + "  \"subscriptionNumber\": \"7753082\",\n"
                        + "  \"mobileNumber\": \"01148379445\",\n"
                        + "  \"billsNumber\": 22,\n"
                        + "  \"totalBillsAmount\": 2565,\n"
                        + "  \"oldestBillsAmount\": 32,\n"
                        + "  \"masaryCommission\": 3.51,\n"
                        + "  \"merchantCommission\": 3.49,\n"
                        + "  \"ratePlanId\": 261,\n"
                        + "  \"appliedFees\": 7,\n"
                        + "  \"tax\": 0.98,\n"
                        + "  \"transactionAmount\": 2569.49,\n"
                        + "  \"toBepaid\": 2572.98,\n"
                        + "  \"serviceAmount\": 2565,\n"
                        + "  \"billsDetails\": [\n"
                        + "    {\n"
                        + "      \"billAmount\": 32,\n"
                        + "      \"billDate\": \"7-2017\",\n"
                        + "      \"billId\": \"2928641707082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 62,\n"
                        + "      \"billDate\": \"8-2017\",\n"
                        + "      \"billId\": \"2061708082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 62,\n"
                        + "      \"billDate\": \"9-2017\",\n"
                        + "      \"billId\": \"86721709082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 62,\n"
                        + "      \"billDate\": \"10-2017\",\n"
                        + "      \"billId\": \"161231710082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 174,\n"
                        + "      \"billDate\": \"12-2017\",\n"
                        + "      \"billId\": \"353391712082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 150,\n"
                        + "      \"billDate\": \"1-2018\",\n"
                        + "      \"billId\": \"429531801082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 150,\n"
                        + "      \"billDate\": \"2-2018\",\n"
                        + "      \"billId\": \"505781802082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 150,\n"
                        + "      \"billDate\": \"3-2018\",\n"
                        + "      \"billId\": \"592381803082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 150,\n"
                        + "      \"billDate\": \"4-2018\",\n"
                        + "      \"billId\": \"669241804082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 151,\n"
                        + "      \"billDate\": \"5-2018\",\n"
                        + "      \"billId\": \"3001841805082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 151,\n"
                        + "      \"billDate\": \"6-2018\",\n"
                        + "      \"billId\": \"3080201806082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 231,\n"
                        + "      \"billDate\": \"7-2018\",\n"
                        + "      \"billId\": \"3468721807082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 238,\n"
                        + "      \"billDate\": \"8-2018\",\n"
                        + "      \"billId\": \"3546561808082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 238,\n"
                        + "      \"billDate\": \"9-2018\",\n"
                        + "      \"billId\": \"3624411809082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 238,\n"
                        + "      \"billDate\": \"10-2018\",\n"
                        + "      \"billId\": \"3704001810082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 200,\n"
                        + "      \"billDate\": \"11-2018\",\n"
                        + "      \"billId\": \"789\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 238,\n"
                        + "      \"billDate\": \"11-2018\",\n"
                        + "      \"billId\": \"3782851811082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 24,\n"
                        + "      \"billDate\": \"12-2018\",\n"
                        + "      \"billId\": \"3931681812082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 39,\n"
                        + "      \"billDate\": \"1-2019\",\n"
                        + "      \"billId\": \"4009681901082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 57,\n"
                        + "      \"billDate\": \"2-2019\",\n"
                        + "      \"billId\": \"4088821902082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 57,\n"
                        + "      \"billDate\": \"3-2019\",\n"
                        + "      \"billId\": \"4166941903082\"\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 57,\n"
                        + "      \"billDate\": \"4-2019\",\n"
                        + "      \"billId\": \"4245781904082\"\n"
                        + "    }\n"
                        + "  ],\n"
                        + "  \"inquiryId\": \"4564513248966\",\n"
                        + "  \"accountId\": 8,\n"
                        + "  \"oldestBillDate\": \"7-2017\",\n"
                        + "  \"newestBillDate\": \"4-2019\",\n"
                        + "  \"billDate\": \"4-2019\",\n"
                        + "  \"billinfo\": [\n"
                        + "    {\n"
                        + "      \"billAmount\": 32,\n"
                        + "      \"billId\": 2928641707082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 62,\n"
                        + "      \"billId\": 2061708082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 62,\n"
                        + "      \"billId\": 86721709082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 62,\n"
                        + "      \"billId\": 161231710082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 174,\n"
                        + "      \"billId\": 353391712082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 150,\n"
                        + "      \"billId\": 429531801082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 150,\n"
                        + "      \"billId\": 505781802082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 150,\n"
                        + "      \"billId\": 592381803082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 150,\n"
                        + "      \"billId\": 669241804082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 151,\n"
                        + "      \"billId\": 3001841805082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 151,\n"
                        + "      \"billId\": 3080201806082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 231,\n"
                        + "      \"billId\": 3468721807082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 238,\n"
                        + "      \"billId\": 3546561808082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 238,\n"
                        + "      \"billId\": 3624411809082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 238,\n"
                        + "      \"billId\": 3704001810082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 200,\n"
                        + "      \"billId\": 789\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 238,\n"
                        + "      \"billId\": 3782851811082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 24,\n"
                        + "      \"billId\": 3931681812082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 39,\n"
                        + "      \"billId\": 4009681901082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 57,\n"
                        + "      \"billId\": 4088821902082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 57,\n"
                        + "      \"billId\": 4166941903082\n"
                        + "    },\n"
                        + "    {\n"
                        + "      \"billAmount\": 57,\n"
                        + "      \"billId\": 4245781904082\n"
                        + "    }\n"
                        + "  ]\n"
                        + "}";
                Gson gson = new Gson();
                return gson.fromJson(jsonResponse, classOfT);
            } else if (classOfT.getName().contains("KafrElShiekhWaterPaymentResponse")) {
                jsonResponse = "{\n"
                        + "  \"subscriberName\": \"شركة مصر للتأمين\",\n"
                        + "  \"subscriptionNumber\": \"7753082\",\n"
                        + "  \"masaryCommission\": 3.51,\n"
                        + "  \"merchantCommission\": 3.49,\n"
                        + "  \"ratePlanId\": 261,\n"
                        + "  \"appliedFees\": 7,\n"
                        + "  \"tax\": 0.98,\n"
                        + "  \"transactionAmount\": 2569.49,\n"
                        + "  \"toBepaid\": 2572.98,\n"
                        + "  \"serviceAmount\": 2565,\n"
                        + "  \"inquiryId\": \"4564513248966\",\n"
                        + "  \"accountId\": 8,\n"
                        + "  \"billDate\": \"4-2019\",\n"
                        + "  \"insertDate\": 1555606509649,\n"
                        + "  \"updateDate\": 1555606509742,\n"
                        + "  \"requestStatus\": \"SUCCEEDED\",\n"
                        + "  \"ledgerStatus\": \"COMMITTED\",\n"
                        + "  \"deviceType\": \"WEB\",\n"
                        + "  \"globalTrxId\": \"1555606508953001\",\n"
                        + "  \"ledgerTrxId\": 220622554\n"
                        + "}";
                Gson gson = new Gson();
                return gson.fromJson(jsonResponse, classOfT);
            } else if (classOfT.getName().contains("KafrElShiekhWaterBillsSuggetionsResponse")) {
                jsonResponse = "{\n"
                        + "  \"firstBillsAmount\": 32,\n"
                        + "  \"firstBillsCount\": 1,\n"
                        + "  \"secondbillsAmount\": 94,\n"
                        + "  \"secondbillsCount\": 2\n"
                        + "}";
                Gson gson = new Gson();
                return gson.fromJson(jsonResponse, classOfT);
            } else {
                return null;
            }

        }

        @Override
        protected int getStatusCode(CloseableHttpResponse httpResponse) {
            return 200;
        }

    }
}
