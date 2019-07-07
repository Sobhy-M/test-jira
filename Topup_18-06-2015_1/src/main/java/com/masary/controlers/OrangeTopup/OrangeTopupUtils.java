package com.masary.controlers.OrangeTopup;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.impl.client.HttpClients;
import com.masary.common.CONFIG;
import com.masary.integration.OrangeTopupHttpClient;
import com.masary.integration.dto.TopupDenominationDTO;

public class OrangeTopupUtils {

	public void checkDenominationsAvaliablity(HttpServletRequest request) throws Exception {

		String token = request.getSession().getAttribute("Token").toString();
		String lang = "en";
		if (!request.getSession().getAttribute(CONFIG.lang).equals("")) {
			lang = "ar";
		}

		List<TopupDenominationDTO> denominationsList = new ArrayList<TopupDenominationDTO>();

		if (request.getSession().getAttribute(OrangeTopupProperties.orangDenominationMapName) == null) {
			denominationsList = new OrangeTopupHttpClient(HttpClients.createDefault()).getOrangeTopupDenomination(lang,
					token);
			request.getSession().setAttribute(OrangeTopupProperties.orangDenominationMapName, denominationsList);
		} else {
			denominationsList = (List<TopupDenominationDTO>) request.getSession()
					.getAttribute(OrangeTopupProperties.orangDenominationMapName);
		}

	}

	public boolean validateNumber(HttpServletRequest request) {

		String mobileNum = request.getParameter("MSISDN");
		String mobileNumConfirmation = request.getParameter("MSISDN_CONFIRMATION");

		if (!mobileNum.equals(mobileNumConfirmation)) {
			return false;
		}

		String mobileNumRegex = "(01)[0-9]{9}";

		return mobileNum.matches(mobileNumRegex);

	}

	public boolean validateTopupAmount(HttpServletRequest request) {

		List<TopupDenominationDTO> topupDonmenationsList = (List<TopupDenominationDTO>) request.getSession()
				.getAttribute(OrangeTopupProperties.orangDenominationMapName);

		String denmoID = request.getParameter("denmoID");
		for (TopupDenominationDTO i : topupDonmenationsList) {
			if (i.getDenominationId() == Long.parseLong(denmoID)) {
				return true;
			}
		}
		return false;

	}
}
