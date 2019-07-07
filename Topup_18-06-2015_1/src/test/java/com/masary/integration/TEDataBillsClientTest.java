package com.masary.integration;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.masary.database.manager.BaseManger;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.TEDataBillsResponse;
import com.masary.integration.dto.TeDataBssRequest;
import java.util.ResourceBundle;
import junit.framework.TestCase;
import org.apache.http.client.methods.CloseableHttpResponse;
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
@PrepareForTest({TEDataBillsClient.class, MasaryManager.class, BaseManger.class})
public class TEDataBillsClientTest extends TestCase {

    private String language = "ar";
    private String token = "ODoxMjM0NTY3ODk=";
    private Logger logger;
    private static ResourceBundle rb = ResourceBundle.getBundle("Bundle");
    private TeDataBssRequest teDataBssRequest;
    private TEDataBillsClient teDataBillsClient;

    @BeforeClass
    public static void oneTimeSetup() {
        System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
        System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
    }

    @Before
    public void init() {
        logger = mock(Logger.class);

        teDataBillsClient = new TEDataBillsClientTestable();
        teDataBssRequest = new TeDataBssRequest(10, "0228446920");

        spy(BaseManger.class);
        Whitebox.setInternalState(MasaryManager.class, "logger", logger);

    }

    @Test
    public void testActionDestination_isBss() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");

        boolean isBss = teDataBillsClient.actionDestination("0228446920", token);
        assertFalse("TeData is Bss", isBss);

    }

    @Test
    public void testPaymentBss_should_return_receipt_representatio() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");

        TEDataBillsResponse teDataBillsPaymentResponse = teDataBillsClient.bssPayemnt(teDataBssRequest, token);
        assertNotNull(teDataBillsPaymentResponse);

    }
    
    @Test
    public void testReprint_should_return_reprint_representatio() throws Exception {
        doNothing().when(BaseManger.class, "initDataSource");

        TEDataBillsResponse teDataBillsReprintResponse = teDataBillsClient.rePrint(15498486L, token);
        assertNotNull(teDataBillsReprintResponse);

    }

}

class TEDataBillsClientTestable extends TEDataBillsClient {

    String actionDestinationResponse;
    String paymentResponseJson;

    public TEDataBillsClientTestable() {
        actionDestinationResponse = "{\"validationId\": \"1551257744970\",  \"actionDestination\": 1}";
        paymentResponseJson = "{\n"
                + "  \"eventName\": null,\n"
                + "  \"entityName\": null,\n"
                + "  \"providerStatusCode\": null,\n"
                + "  \"providerStatusMessage\": null,\n"
                + "  \"retrialStatusCode\": null,\n"
                + "  \"retrialStatusMessage\": null,\n"
                + "  \"landLine\": null,\n"
                + "  \"customerName\": null,\n"
                + "  \"customerNumber\": null,\n"
                + "  \"totalDueForRenewal\": null,\n"
                + "  \"newExpiryDateAfterRenewal\": null,\n"
                + "  \"receiptNumber\": null,\n"
                + "  \"retrial\": 0,\n"
                + "  \"bssProviderRequestId\": \"ADSL_Top-up02\",\n"
                + "  \"resultCode\": \"0\",\n"
                + "  \"functionId\": 10100002,\n"
                + "  \"advertisingStatement\": \"Hello From the Other Side :D :D\",\n"
                + "  \"requestId\": 39802,\n"
                + "  \"insertDate\": 1551251666732,\n"
                + "  \"updateDate\": 1551251666776,\n"
                + "  \"appliedFees\": 2,\n"
                + "  \"merchantCommission\": 1,\n"
                + "  \"tax\": 0,\n"
                + "  \"toBepaid\": 12,\n"
                + "  \"masaryCommission\": 0,\n"
                + "  \"requestStatus\": \"SUCCEEDED\",\n"
                + "  \"accountId\": 8,\n"
                + "  \"deviceType\": \"WEB\",\n"
                + "  \"ledgerStatus\": \"COMMITTED\",\n"
                + "  \"ledgerTrxId\": 1551251666755,\n"
                + "  \"ratePlanId\": 261,\n"
                + "  \"transactionAmount\": 11,\n"
                + "  \"globalTrxId\": \"1551251666717\",\n"
                + "  \"targetNumber\": null,\n"
                + "  \"returnDesc\": \"Accept the service request successfully.\",\n"
                + "  \"dest\": \"1\"\n"
                + "}";
    }

    @Override
    protected <T> T json2Object(String jsonResponse, Class<T> classOfT) throws JsonSyntaxException {
        Gson gson = new Gson();
        if (classOfT.getName().contains("TeDataActionDestinationResponse")) {
            return gson.fromJson(actionDestinationResponse, classOfT);
        } else {
            return gson.fromJson(paymentResponseJson, classOfT);
        }

    }

    @Override
    protected int getStatusCode(CloseableHttpResponse httpResponse) {
        return 200;
    }

}
