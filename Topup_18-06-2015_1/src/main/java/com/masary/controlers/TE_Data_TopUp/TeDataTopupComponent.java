package com.masary.controlers.TE_Data_TopUp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.masary.database.manager.MasaryManager;
import com.masary.integration.TEDataClient;
import com.masary.integration.dto.TEData_Inquiry_Request;
import com.masary.integration.dto.TEData_Inquiry_Response;
import com.masary.integration.dto.TEData_Payment_Request;
import com.masary.integration.dto.TEData_Payment_Response;
import com.masary.integration.dto.TedataDenominationRepresentation;

public class TeDataTopupComponent {

	public TEData_Inquiry_Request getInquiryRequest(HttpServletRequest request) {

		MasaryManager.logger.info("tedata-topup Inquiry and get Denomination which had been chosen");
		TEData_Inquiry_Request inqueryRequest = new TEData_Inquiry_Request();
		inqueryRequest.setLandline(request.getParameter("SubscriberNumber"));
		String quota = request.getParameter("quota");

		List<TedataDenominationRepresentation> tedataDenominationRepresentation = (List<TedataDenominationRepresentation>) request
				.getSession().getAttribute("tedataDenominationRepresentation");
		for (TedataDenominationRepresentation denomination : tedataDenominationRepresentation) {
			if (denomination.getQuota().equals(quota)) {
				inqueryRequest.setDenominationId(denomination.getDenominationId());
			}

		}


		return inqueryRequest;

	}

	public TEData_Inquiry_Response makeInquiry(TEData_Inquiry_Request request, String lang, String token)
			throws Exception {
		
		MasaryManager.logger.info("tedata-topup Inquiry");
		TEDataClient tedataClient = new TEDataClient();
		return tedataClient.tedataInquiry(request, lang, token);


	}

	public TEData_Payment_Response makePayment(String validationId, String lang, String token) throws Exception {

		MasaryManager.logger.info("tedata-topup Payment");
		TEData_Payment_Request tedataPaymentRequest = new TEData_Payment_Request();
		tedataPaymentRequest.setValidationId(validationId);
		TEDataClient tedataClient = new TEDataClient();
		return tedataClient.tedataPayment(tedataPaymentRequest, lang, token);

	}

}
