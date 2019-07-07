package com.masary.controlers.component;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.impl.client.HttpClients;
import com.masary.common.CONFIG;
import com.masary.controlers.OrangeTopup.OrangeTopupProperties;
import com.masary.controlers.OrangeTopup.OrangeTopupUtils;
import com.masary.integration.OrangeTopupHttpClient;
import com.masary.integration.dto.CommonTopupRepresentationDTO;
import com.masary.integration.dto.CommonTopupRequestDTO;

public class OrangeTopupComponent {

	public String getConfirmationJsp(HttpServletRequest request) {

		String nextJSP = "";

		OrangeTopupUtils orangeTopupUtils = new OrangeTopupUtils();

		if (!orangeTopupUtils.validateTopupAmount(request) || !orangeTopupUtils.validateNumber(request)) {
			nextJSP = CONFIG.PAGE_ERRPR;
			request.getSession().setAttribute("ErrorMessage", CONFIG.getCommonTopupErrorDuringTransaction(request.getSession()));
		} else {
			nextJSP = OrangeTopupProperties.orangeTopupConfirmation;
		}

		return nextJSP;
	}
	
	
	public CommonTopupRequestDTO prepareCommonTopupRequestDTO(HttpServletRequest request) {

		String denominationId = request.getParameter("denmoID");
		String msisdn = request.getParameter(CONFIG.PARAM_MSISDN);

		CommonTopupRequestDTO topupRequestDTO = new CommonTopupRequestDTO();
		topupRequestDTO.setDenominationID(Long.parseLong(denominationId));
		topupRequestDTO.setMsisdn(msisdn);
		topupRequestDTO.setToken(request.getSession().getAttribute("Token").toString());
		topupRequestDTO.setIp(request.getRemoteAddr());

		return topupRequestDTO;
	}

	public CommonTopupRepresentationDTO doPaymentTransaction(HttpServletRequest request,
			CommonTopupRequestDTO topupRequestDTO) throws Exception {

		String lang = (String) request.getSession().getAttribute("lang");
		OrangeTopupHttpClient orangeTopupHttpClient = new OrangeTopupHttpClient(HttpClients.createDefault());
		return orangeTopupHttpClient.doOrangeTopup(topupRequestDTO, lang);
	}
	
	
}
