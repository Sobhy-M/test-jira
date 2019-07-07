package com.masary.controlers.OrangeTopup;

import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;

public class OrangeTopupProperties {
	
	public static final String orangeTopupHome = "/service/orangeTopup/CustomerTopUp.jsp";
	public static final String orangeTopupConfirmation = "/service/orangeTopup/CustomerTopUpConfirmation.jsp";
	public static final String orangeTopupPayment = "/service/orangeTopup/CustomerTopupPayment.jsp";


	public static final String orangDenominationMapName = "orange_denominationMap";
	
	private static final String errorDuringTransactionAr = "حدث خطأ أثناء العملية ";
	private static final String errorDuringTransactionEn = "Error During Transaction";
	
		
	private static final String orangeTopupAr = "شحن أورانج";
	private static final String orangeTopupEn = "Orange Topup";
	
	
	
	public static String getErrorDuringTransaction(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return errorDuringTransactionAr;
		}
		return errorDuringTransactionEn;
	}
	
	
	
	public static String getOrangeTopUp(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return orangeTopupAr;
		}
		return orangeTopupEn;
	}
	
	

}
