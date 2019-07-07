package com.masary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.dto.BulkVoucherDTO;
import com.masary.database.dto.GenericSellVoucherResponse;
import com.masary.database.dto.RatePlanDTO;
import com.masary.database.dto.SellVoucherResponse;
import com.masary.database.dto.TransactionDTO;
import com.masary.database.dto.TransactionInfoDTO;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.EtisalatTopupHttpClient;
import com.masary.integration.OrangeTopupHttpClient;
import com.masary.integration.TopupServiceIntegrationStatus;
import com.masary.integration.VodafoneTopupHttpClient;
import com.masary.integration.dto.CommonTopupRequestDTO;
import com.masary.integration.dto.TopupResponse;
import com.masary.utils.HttpServletRequestProxy;
import com.masary.utils.SystemSettingsUtil;

public class Web extends HttpServlet
{

	private static final long serialVersionUID = -3411261980657965603L;

	private void processGetRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		HttpSession session = request.getSession();
		String action = request.getParameter(CONFIG.PARAM_ACTION);
		String nextJSP = "";

		if (!isLogin(request)) {
			if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
				nextJSP = CONFIG.PAGE_LOGIN;

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
				dispatcher.forward(request, response);
				return;
			}
			session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
		}
		
		if (CONFIG.ACTION_CUSTOMER_RECHARGE.equals(action)) 
		{
			nextJSP = customerRecharge(request);
		}
		else if (CONFIG.ACTION_BULK_SMS.equals(action)) {
			nextJSP = serviceOperations(request);
		}
		
		try {
			session.setAttribute(CONFIG.PAGE, nextJSP);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
			dispatcher.forward(request, response);
		}
		catch (Exception e) {
			MasaryManager.logger.error(e.getStackTrace());
		}
	}
	
	private void processPostRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{

	HttpSession session = request.getSession();
	String action = request.getParameter(CONFIG.PARAM_ACTION);
	String nextJSP = "";

	if (!isLogin(request)) {
		if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
			nextJSP = CONFIG.PAGE_LOGIN;

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
			dispatcher.forward(request, response);
			return;
		}
		session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
	}

	if (CONFIG.ACTION_SERVICE_OPERATIONS.equals(action)) {
		nextJSP = serviceOperations(request);
	}
	else if (CONFIG.ACTION_ASIGN_CUSTOMER_BILL.equals(action)) {
		nextJSP = assignCustomerBill(request);
	}
	else if (CONFIG.ACTION_CUSTOMER_RECHARGE.equals(action)) {
		nextJSP = customerRecharge(request);
	}
	else if (CONFIG.ACTION_CUSTOMER_RECHARGE_MOBILE.equals(action)) {
		nextJSP = customerRechargeMobile(request);
	}
	else if (CONFIG.ACTION_SELL_CUSTOMER_CREADIT.equals(action)) {
		nextJSP = sellCustomerCreadit(request);
	}
	else if (CONFIG.ACTION_CUSTOMER_TOPUP.equals(action)) {
		nextJSP = customerTopup(request);
	}
	else if (CONFIG.ACTION_SELL_CREADIT.equals(action)) {
		nextJSP = sellCreadit(request);
	}
	else if (CONFIG.ACTION_MAIN.equals(action)) {
		nextJSP = mainMobile();
	}
	else if (CONFIG.ACTION_INCLUDE_TRANS_FROMAG.equals(action)) {
		transIncludeFrom(request);
	}
	else if (CONFIG.ACTION_INCLUDE_TRANS_TOAG.equals(action)) {
		transIncludeTo(request);
	}
	else if (CONFIG.ACTION_INCLUDE_TRANS_FROMCUST.equals(action)) {
		nextJSP = transIncludeFromCust(request);
	}
	else if (CONFIG.ACTION_INCLUDE_TRANS_TOCUST.equals(action)) {
		nextJSP = transIncludeToCust(request);
	}
	else if (CONFIG.ACTION_DYNAMICS_TOPUP.equals(action)) {
		nextJSP = dynamicsWalletTopup(request);
	}
	else if (CONFIG.ACTION_CUSTOMER_BULK_VOUCHER.equals(action)) {
		nextJSP = bulkVoucher(request);
	}
	else if (CONFIG.ACTION_BULK_SMS_TRANS.equals(action)) { // added by
															// keroles
		nextJSP = BulkSMSTransfer(request);
	}
	else if (CONFIG.ACTION_BULK_SMS.equals(action)) {
		nextJSP = serviceOperations(request);
	}
	try {
		session.setAttribute(CONFIG.PAGE, nextJSP);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
		dispatcher.forward(request, response);
	}
	catch (Exception e) {
		MasaryManager.logger.error(e.getStackTrace());
	}
}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	    processGetRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	    HttpServletRequestProxy httpServletRequestProxy = new HttpServletRequestProxy();
	    httpServletRequestProxy.setRequest(request);
	    
	    processPostRequest(httpServletRequestProxy, response);
	}

	@Override
	public String getServletInfo()
	{
		return "Short description";

	}

	private String serviceOperations(HttpServletRequest request)
	{
		String nextJSP = "";
		
		String btn = request.getParameter(CONFIG.PARAM_AGENT_BTN);
		String action = request.getParameter(CONFIG.PARAM_ACTION);
		
		MasaryManager.logger.info(action + " " + btn + " IP " + request.getRemoteAddr());
		if (btn.equals("Manage Service Account")) {
			nextJSP = CONFIG.PAGE_MANAGE_SERVICE_ACCOUNT;
		}
		else if (btn.equals("Bulk SMS")) { // added by keroles
			nextJSP = CONFIG.PAGE_SEND_BULK_SMS;
		}
		else if (btn.equals("View Transactions")) {
			nextJSP = CONFIG.PAGE_VIEW_SERVICE_ACCOUNT;
		}
		else if (btn.equals("Add Bills")) {
			nextJSP = CONFIG.PAGE_ADD_BILL;
		}
		
		return nextJSP;
	}

	private String assignCustomerBill(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String nextJSP = "";
		
		Enumeration e = request.getParameterNames();
		String name = null, custId = null;
		while (e.hasMoreElements()) {
			custId = (String) e.nextElement();
			name = request.getParameter(custId);
			if (name.equals(CONFIG.ASSIGN_BILL)) {
				break;
			}
		}
		String balance = request.getParameter(CONFIG.PARAM_BALANCE);
		if (balance == null) {
			session.setAttribute(CONFIG.PARAM_CUSTOMER_ID, custId);
			nextJSP = CONFIG.PAGE_ASSIGN_CUSTOMER_BILL;
		}
		else {
			custId = (String) session.getAttribute(CONFIG.PARAM_CUSTOMER_ID);
			String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
			String month = (String) request.getParameter(CONFIG.PARAM_MONTH);
			String year = (String) request.getParameter(CONFIG.PARAM_YEAR);
			try {
				MasaryManager.getInstance().addBill(custId, id, balance, month, year);
				nextJSP = CONFIG.PAGE_ADD_BILL;
			}
			catch (Exception ex) {
				MasaryManager.logger.error(ex.getStackTrace());
				session.setAttribute("ErrorMessage", "Can't add bill with the same month ");
				nextJSP = CONFIG.PAGE_ERRPR;
			}
		}
		
		return nextJSP;
	}

	private String customerRecharge(HttpServletRequest request) throws UnsupportedEncodingException
	{
		String nextJSP = "";
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String btn = new String(request.getParameter(CONFIG.PARAM_CUSTOMER_BTN).getBytes("8859_1"), "UTF8");
		if (btn.equals(CONFIG.getCustomerEtisalatTransfer(session))) {
			nextJSP = CONFIG.PAGE_SELL_CREADET_CUSTOMER;
		}
		else if (btn.equals(CONFIG.getTransferToAnotheRrep(session))) {
			nextJSP = CONFIG.PAGE_SELL_CREADET;
		}
		else if (btn.equals(CONFIG.CUSTOMERTOPUPen)) {
			String serviceId = request.getParameter(CONFIG.PARAM_SERVICE_ID);
			RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(serviceId,
					(String) session.getAttribute(CONFIG.PARAM_PIN));
			if (serviceId.equals("6")) {
				nextJSP = CONFIG.PAGE_CUSTOMERTOPUP;
			}
			else if (serviceId.equals("7")) {
				nextJSP = CONFIG.PAGE_SELL_CREADET_CUSTOMER;
			}
			else if (serviceId.equals("8")) {
				ArrayList<Double> orangeDenominations = (ArrayList<Double>) session.getAttribute("orangeDenominations");
				if (orangeDenominations == null) {
					orangeDenominations = MasaryManager.getInstance()
							.getOperatorDenominations(Integer.parseInt(serviceId));
				}
				// //System.out.println(".................................. " +
				// orangeDenominations.size());
				session.setAttribute("orangeDenominations", orangeDenominations);
				nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_MOBINIL;
			}
			else if (serviceId.equals("9")) {
				nextJSP = CONFIG.PAGE_SELL_CREADET_CUSTOMER_MOBINIL;
			}
			else if (serviceId.equals("10")) {
				nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_VODAFONE;
			}
			else if (serviceId.equals("11")) {
				nextJSP = CONFIG.PAGE_SELL_CREADET_CUSTOMER_VODAFONE;
			}
			else if (serviceId.equals("12")) {
				nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_NTCC;
			}
			else if (serviceId.equals("13")) {
				nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_LINK;
			}
			else if (serviceId.equals("26")) {
				nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_DYNAMICS;
			}
			else if (serviceId.equals("34")) {
				session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
				nextJSP = CONFIG.PAGE_CUSTOMERVoucher_BlaBla;
			}
			else {
				session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
				nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_VOUCHER;
			}
			session.setAttribute("ratePlan", ratePlan);
		}
		else if (btn.equals(CONFIG.CHECKCREIDET)) {
			nextJSP = CONFIG.PAGE_CHECKCREIDET;
		}

		MasaryManager.logger.info(nextJSP + " IP " + request.getRemoteAddr());
		return nextJSP;
	}

	private String customerRechargeMobile(HttpServletRequest request) throws UnsupportedEncodingException
	{
		String nextJSP = "";
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String action = request.getParameter(CONFIG.PARAM_ACTION);
		
		String btn = new String(request.getParameter(CONFIG.PARAM_CUSTOMER_BTN).getBytes("8859_1"), "UTF8");
		if (btn.equals(CONFIG.getCustomerEtisalatTransfer(session))) {
			nextJSP = CONFIG.PAGE_SELL_CREADET_CUSTOMER;
		}
		else if (btn.equals(CONFIG.getTransferToAnotheRrep(session))) {
			nextJSP = CONFIG.PAGE_SELL_CREADET;
		}
		else if (btn.equals(CONFIG.CUSTOMERTOPUPen)) {
			session.setAttribute(CONFIG.PARAM_SERVICE_ID, request.getParameter(CONFIG.PARAM_SERVICE_ID));
			nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_MOBILE;
		}
		else if (btn.equals(CONFIG.CHECKCREIDET)) {
			nextJSP = CONFIG.PAGE_CHECKCREIDET;
		}
		MasaryManager.logger.info(action + " " + btn + " " + nextJSP + " IP " + request.getRemoteAddr());
		
		return nextJSP;
	}

	private String sellCustomerCreadit(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String nextJSP = "";
		String action = request.getParameter(CONFIG.PARAM_ACTION);
		
		try {
			String custID = (String) session.getAttribute(CONFIG.PARAM_PIN);
			String amount = request.getParameter(CONFIG.AMOUNT);
			String msisdn = request.getParameter(CONFIG.PARAM_MSISDN);
			if (MasaryManager.getInstance().getCustomerInfo(custID).getCurrentBalance() < Double.parseDouble(amount)) {
				throw new Exception(CONFIG.getNotEnoughBalanceError(session));
			}
			if (request.getParameter(CONFIG.CONFIRM) == null) {
				nextJSP = CONFIG.PAGE_SELL_CREADET_CUSTOMER_CONFIRM;
			}
			else {
				request.removeAttribute(CONFIG.CONFIRM);
				try {
					String transId = String.valueOf(MasaryManager.getInstance().transferCredit(custID, msisdn, amount,
							(String) session.getAttribute(CONFIG.lang)));
					if (transId.equals("-10")) {
						session.setAttribute("ErrorMessage", CONFIG.getErrorTransaction(session));
						nextJSP = CONFIG.PAGE_ERRPR;
					}
					else if (transId.equals("-9")) {
						session.setAttribute("ErrorMessage", CONFIG.getBadEtisaltCustomer(session));
						nextJSP = CONFIG.PAGE_ERRPR;
					}
					else {
						session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
						request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_CUSTOMER_BILLS);
						nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION + "?v_and_s=" + " ";
					}
				}
				catch (Exception ex) {
					MasaryManager.logger.error(ex.getStackTrace());
					MasaryManager.logger.error(ex.getMessage());
					session.setAttribute("ErrorMessage", ex.getMessage());
					nextJSP = CONFIG.PAGE_ERRPR;
				}
			}
			MasaryManager.logger.info(action + " " + msisdn + " IP " + request.getRemoteAddr());
		}
		catch (Exception ex) {
			session.setAttribute("ErrorMessage", ex.getMessage());
			nextJSP = CONFIG.PAGE_ERRPR;
			MasaryManager.logger.error(ex.getMessage());
			MasaryManager.logger.error(ex.getStackTrace());
		}
		
		return nextJSP;
	}

	private String customerTopup(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String action = request.getParameter(CONFIG.PARAM_ACTION);
		String nextJSP = "";
		
		Integer orangeTopupServiceId = null;
		Integer etisalatTopupServiceId = null;
		Integer vodafoneTopupServiceId = null;
		try {
			orangeTopupServiceId = Integer
					.parseInt(SystemSettingsUtil.getInstance().loadProperty("orangeTopup.serviceId"));
			etisalatTopupServiceId = Integer
					.parseInt(SystemSettingsUtil.getInstance().loadProperty("etisalatTopup.serviceId"));
			vodafoneTopupServiceId = Integer
					.parseInt(SystemSettingsUtil.getInstance().loadProperty("vodafoneTopup.serviceId"));
			MasaryManager.logger.info("Topup service Id is " + orangeTopupServiceId);
			MasaryManager.logger.info("Topup service Id is " + etisalatTopupServiceId);
			MasaryManager.logger.info("Topup service Id is " + vodafoneTopupServiceId);
		}
		catch (NumberFormatException ex) {
			Logger.getLogger(TopupServiceIntegrationStatus.class.getName()).log(Level.SEVERE, null, ex);
		}

		String custID = (String) session.getAttribute(CONFIG.PARAM_PIN);
		String count = request.getParameter("vouchersNumber");
		String catId = request.getParameter(CONFIG.PARAM_CATEGORY_ID);
		String msisdn = "";
		String payedID = request.getParameter(CONFIG.PARAM_PAYED_PIN);
		int topupType = Integer.parseInt(request.getParameter(CONFIG.TOPUP_TYPE));
		String amount = request.getParameter(CONFIG.AMOUNT);
		
		if (topupType == 15) {
			try {
				amount = new String(request.getParameter(CONFIG.AMOUNT).getBytes("ISO-8859-1"), "utf-8");
				
				String topupdemo = request.getParameter("topupdemo");
				
				if (amount.equals("مارد 5 جنيه دقائق لكل الشبكات")) {
					amount = "5.01";
				}
				
				if (amount.equals("مارد 5 جنيه فليكسات")) {
					amount = "5.02";
				}

				if (topupdemo != null
					&& !topupdemo.equals("")
					&& !topupdemo.equals("null")) {
					amount = topupdemo;
				}

			}
			catch (UnsupportedEncodingException ex) {
				MasaryManager.logger.error(ex.getMessage());
			}
		}
		if (topupType == 10 && amount.equals("7") || topupType == 10 && amount.equals("3.5")) {
			amount = request.getParameter("TopupDenmo");
		}
		if (topupType == 6 && amount.equals("5") || topupType == 6 && amount.equals("10")
				|| topupType == 6 && amount.equals("15") || topupType == 6 && amount.equals("25") || topupType == 6 && amount.equals("7")) {
			amount = request.getParameter("TopupDenmo");
		}
		if (topupType == 17 && (amount.equals("10.01") || amount.equals("25.01") || amount.equals("15.01")
				|| amount.equals("5.01") || amount.equals("7.01"))) {
			amount = request.getParameter("TopupDenmo");
		}
		String howToSell = "";
		String print = "Y";
		String employeeID = (String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
		if (request.getParameter(CONFIG.CONFIRM) == null) {
			howToSell = request.getParameter("HowToSell");
		}
		else {
			howToSell = (String) session.getAttribute("howSell");
		}
		if (topupType != 15 && topupType != 16 && topupType != 28 && topupType != 17 && topupType != 30
				&& topupType != 18 && topupType != 31 && topupType != 34) {
			howToSell = "sms";
			print = "N";
			msisdn = request.getParameter(CONFIG.PARAM_MSISDN);
		}
		else if (howToSell.equals("sms")) {
			print = "N";
			msisdn = request.getParameter(CONFIG.PARAM_MSISDN);
		}
		else if (howToSell.equals("Printing")) {
			msisdn = "WEB";
		}
		else {
			msisdn = "View";
		}
		try {
			if (!employeeID.equals("-1")) {
				if (MasaryManager.getInstance().getAllowedServiceBalance(employeeID, topupType) < Double
						.parseDouble(amount)) {
					throw new Exception("رصيدك الحالي في هذه الخدمه لا يسمح باجراء العمليه");
				}
			}

			if (payedID == null || payedID.equals("")) {
				payedID = "";
				if (amount.isEmpty() || (msisdn.isEmpty() && howToSell.equals("Printing"))) {
					throw new Exception(CONFIG.getFillInAllData(session));
				}
				if (topupType != 1 && topupType != 12 && topupType != 13) {
					if (howToSell.equals("sms")) {
						if (!MasaryManager.getInstance().ValidatePhoneNumber(msisdn)) {
							throw new Exception(CONFIG.getInvalidMobileNumber(session));
						}
						if (!MasaryManager.getInstance().ValidateTopupType(msisdn, topupType)) {
							throw new Exception(CONFIG.getInvalidMobileNumber(session));
						}
					}

					if (!MasaryManager.getInstance().ValidateTopupAmount(topupType, amount)) {
						throw new Exception(CONFIG.getInvalidTopupAmount(session));
					}

				}
				else if (topupType != 13 && Double.parseDouble(amount) < 1) {
					throw new Exception(CONFIG.getBalanceError(session));
				}
			}
			double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(custID, topupType);
			if (employeeID.equals("-1")) {
				if (serviceBalance < Double.parseDouble(amount)) {
					throw new Exception(CONFIG.getNotEnoughBalanceError(session));
				}
			}
			int accepted_trx = MasaryManager.getInstance().acceptedTransacrion(msisdn, amount, custID);
			if (accepted_trx == 1) {
				MasaryManager.logger.error(CONFIG.getExceedLimitError(session));
				throw new Exception(CONFIG.getExceedLimitError(session));
			}
			else if (accepted_trx == 2) {
				MasaryManager.logger.error(CONFIG.getExceedDailyLimitError(session));
				throw new Exception(CONFIG.getExceedDailyLimitError(session));
			}
			else if (accepted_trx == 3) {
				MasaryManager.logger.error(CONFIG.getExceedMonthlyLimitError(session));
				throw new Exception(CONFIG.getExceedMonthlyLimitError(session));
			}
			if (request.getParameter(CONFIG.CONFIRM) == null) {
				session.setAttribute(CONFIG.PARAM_FIRST, CONFIG.PARAM_FIRST);
				if (CONFIG.PARAM_FROM_MOBILE.equals(session.getAttribute(CONFIG.PARAM_FROM_MOBILE))) {
					nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_CONFIRMATION_MOBILE;
				}
				else {
					switch (topupType)
					{
					case 6:
						nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_CONFIRMATION;
						break;
					case 7:
						nextJSP = CONFIG.PAGE_SELL_CREADET_CUSTOMER_CONFIRM;
						break;
					case 8:
						nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_CONFIRMATION_MOBINIL;
						break;
					case 9:
						nextJSP = CONFIG.PAGE_SELL_CREADET_CONFIRM_MOBINIL;
						break;
					case 10:
						nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_CONFIRMATION_VODAFONE;
						break;
					case 11:
						nextJSP = CONFIG.PAGE_SELL_CREADET_CONFIRM_VODAFONE;
						break;
					case 12:
						nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_CONFIRMATION_NTCC;
						break;
					default:
						session.setAttribute("howSell", howToSell);
						nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_CONFIRMATION_VOUCHER;
						break;
					}
				}
			}
			else {

				MasaryManager.logger.info("Success tramnsaction...");
				request.removeAttribute(CONFIG.CONFIRM);
				if (CONFIG.PARAM_FIRST.equals(session.getAttribute(CONFIG.PARAM_FIRST))) {
					session.setAttribute(CONFIG.PARAM_FIRST, null);
					try {
						String transId;
						String[] res = new String[2];
						GenericSellVoucherResponse voucherResponse = new GenericSellVoucherResponse();
						TransactionInfoDTO TXNResults = new TransactionInfoDTO();
						String voucherAndSerial = " ";
						if (topupType == 1) {
							if (msisdn.equals(custID)) {
								throw new Exception(CONFIG.getCanntTransferError(session));
							}
							transId = String.valueOf(MasaryManager.getInstance().addBalance(msisdn, custID, amount));
						}
						else {
							if (!payedID.isEmpty()) {
								transId = String.valueOf(MasaryManager.getInstance().addBalanceToAgentService(custID,
										payedID, String.valueOf(topupType), amount));
							}
							if (howToSell.equals("sms")) {
								if (!MasaryManager.getInstance().ValidatePhoneNumber(msisdn)) {
									throw new Exception(CONFIG.getInvalidMobileNumber(session));
								}
							}

							if (MasaryManager.getInstance().isVoucherService(topupType) == 0) {
								MasaryManager.logger.info(" OrangeTopupServiceIntegrationStatus.orangeserviceStatus"
										+ TopupServiceIntegrationStatus.orangeserviceStatus);
								MasaryManager.logger.info(" EtisalatTopupServiceIntegrationStatus.etisalatserviceStatus"
										+ TopupServiceIntegrationStatus.etisalatserviceStatus);
								
								MasaryManager.logger.info(" VodafoneTopupServiceIntegrationStatus.vodafoneserviceStatus"
										+ TopupServiceIntegrationStatus.vodafoneserviceStatus);
								
								request.setAttribute("topupType", topupType);
								if (TopupServiceIntegrationStatus.orangeserviceStatus
										&& topupType == orangeTopupServiceId) {
									OrangeTopupHttpClient orangeTopup = new OrangeTopupHttpClient();
									CommonTopupRequestDTO topupRequestDTO = new CommonTopupRequestDTO();
									TopupResponse orangeTopupResponse = new TopupResponse();
									topupRequestDTO.setMsisdn(msisdn);
									topupRequestDTO.setAmount(Double.parseDouble(amount));
									// topupRequestDTO.setUsername(custID);
									topupRequestDTO.setToken(session.getAttribute("Token").toString());
									topupRequestDTO.setIp(request.getRemoteAddr());
									String lang = session.getAttribute(CONFIG.lang).toString();
									if (lang == null || lang.trim().equals("")) {
										lang = "ar";
									}

									orangeTopupResponse = orangeTopup.doTopupTransaction(topupRequestDTO, lang);
									MasaryManager.logger
											.info("Success etisalat Topup Response  " + orangeTopupResponse.toString());

									TXNResults.setTransaction_ID(Long.parseLong(orangeTopupResponse.getGlobalTrxId()));
									TXNResults.setAmount(orangeTopupResponse.getAmount());
									session.setAttribute(CONFIG.PARAM_TRANSACTION_TYPE,
											request.getParameter(CONFIG.TOPUP_TYPE));
									Date date = new Date(orangeTopupResponse.getUpdateTime());
									Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
									// format.format(date);
									TransactionDTO trans = new TransactionDTO(orangeTopupResponse.getGlobalTrxId(),
											custID, orangeTopupResponse.getTargetNumber(),
											orangeTopupResponse.getAmount(), format.format(date), "Fully processed",
											"Orange Topup", orangeTopupResponse.getMerchantCommission());
									MasaryManager.logger
											.info("Success transaction orange integration  " + trans.toString());
									request.setAttribute("orangeTopupResponse", trans);

								}
								else if (TopupServiceIntegrationStatus.etisalatserviceStatus
										&& topupType == etisalatTopupServiceId) {
									EtisalatTopupHttpClient etisalatTopup = new EtisalatTopupHttpClient();
									CommonTopupRequestDTO topupRequestDTO = new CommonTopupRequestDTO();
									TopupResponse topupResponse = new TopupResponse();
									topupRequestDTO.setMsisdn(msisdn);
									topupRequestDTO.setAmount(Double.parseDouble(amount));
									// topupRequestDTO.setUsername(custID);
									topupRequestDTO.setToken(session.getAttribute("Token").toString());
									topupRequestDTO.setIp(request.getRemoteAddr());
									String lang = session.getAttribute(CONFIG.lang).toString();
									if (lang == null || lang.trim().equals("")) {
										lang = "ar";
									}

									topupResponse = etisalatTopup.doTopupTransaction(topupRequestDTO, lang);
									MasaryManager.logger
											.info("Success etisalat Topup Response  " + topupResponse.toString());

									TXNResults.setTransaction_ID(Long.parseLong(topupResponse.getGlobalTrxId()));
									TXNResults.setAmount(topupResponse.getAmount());
									session.setAttribute(CONFIG.PARAM_TRANSACTION_TYPE,
											request.getParameter(CONFIG.TOPUP_TYPE));
									Date date = new Date(topupResponse.getUpdateTime());
									Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
									// format.format(date);
									TransactionDTO trans = new TransactionDTO(topupResponse.getGlobalTrxId(), custID,
											topupResponse.getTargetNumber(), topupResponse.getAmount(),
											format.format(date), "Fully processed", "Etisalat Topup",
											topupResponse.getMerchantCommission());
									MasaryManager.logger
											.info("Success transaction etisalat integration  " + trans.toString());
									request.setAttribute("etisalatTopupResponse", trans);

								}
								else if (TopupServiceIntegrationStatus.vodafoneserviceStatus
										&& topupType == vodafoneTopupServiceId) {
									VodafoneTopupHttpClient vodafoneTopup = new VodafoneTopupHttpClient();
									CommonTopupRequestDTO topupRequestDTO = new CommonTopupRequestDTO();
									TopupResponse topupResponse = new TopupResponse();
									topupRequestDTO.setMsisdn(msisdn);
									topupRequestDTO.setAmount(Double.parseDouble(amount));
									// topupRequestDTO.setUsername(custID);
									topupRequestDTO.setToken(session.getAttribute("Token").toString());
									topupRequestDTO.setIp(request.getRemoteAddr());
									String lang = session.getAttribute(CONFIG.lang).toString();
									if (lang == null || lang.trim().equals("")) {
										lang = "ar";
									}

									topupResponse = vodafoneTopup.doTopupTransaction(topupRequestDTO, lang);
									MasaryManager.logger
											.info("Success vodafone Topup Response  " + topupResponse.toString());

									TXNResults.setTransaction_ID(Long.parseLong(topupResponse.getGlobalTrxId()));
									TXNResults.setAmount(topupResponse.getAmount());
									session.setAttribute(CONFIG.PARAM_TRANSACTION_TYPE,
											request.getParameter(CONFIG.TOPUP_TYPE));
									Date date = new Date(topupResponse.getUpdateTime());
									Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
									// format.format(date);
									TransactionDTO trans = new TransactionDTO(topupResponse.getGlobalTrxId(), custID,
											topupResponse.getTargetNumber(), topupResponse.getAmount(),
											format.format(date), "Fully processed", "vodafone Topup",
											topupResponse.getMerchantCommission());
									MasaryManager.logger
											.info("Success transaction vodafone integration  " + trans.toString());
									request.setAttribute("vodafoneTopupResponse", trans);

								}
								else {
									TXNResults = MasaryManager.getInstance().transferCustomerTopUp(custID, msisdn,
											amount, topupType, catId, (String) session.getAttribute(CONFIG.lang));
								}

								MasaryManager.logger.info("Success tramnsaction0 Id " + TXNResults.getTransaction_ID());
								MasaryManager.logger.info("Success tramnsaction00 Id " + TXNResults.getAmount());
								// transId = String.valueOf((long)
								// TXNResults[0]);
								transId = String.valueOf(TXNResults.getTransaction_ID());
								MasaryManager.logger.info("Success tramnsaction Id " + transId);
								voucherAndSerial = " ";
							}
							else {
								if (howToSell.equals("sms")) {
									voucherResponse = MasaryManager.getInstance().do_Generic_Bulk_transaction(
											session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")
													? Integer.parseInt(custID)
													: Integer.parseInt(
															(String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)),
											Double.parseDouble(amount), topupType, howToSell, msisdn, "", "",
											(String) session.getAttribute(CONFIG.lang), Integer.parseInt(count));
								}
								else {
									voucherResponse = MasaryManager.getInstance().do_Generic_Bulk_transaction(
											session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")
													? Integer.parseInt(custID)
													: Integer.parseInt(
															(String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)),
											Double.parseDouble(amount), topupType, howToSell, msisdn, "", "",
											(String) session.getAttribute(CONFIG.lang), Integer.parseInt(count));
								}
								if (voucherResponse.getTransId().startsWith("-")) {
									throw new Exception(voucherResponse.getStatusMSG());
								}
								
								transId = String.valueOf(voucherResponse.getTransId());
								request.setAttribute("voucherResponse", voucherResponse);
							}
						}
						if (transId.equals("-10")) {
							session.setAttribute("ErrorMessage", CONFIG.getErrorTransaction(session));
							nextJSP = CONFIG.PAGE_ERRPR;
						}
						else if (transId.equals("-9")) {
							session.setAttribute("ErrorMessage", CONFIG.getBadEtisaltCustomer(session));
							nextJSP = CONFIG.PAGE_ERRPR;
						}
						else if (transId.equals("-41")) {
							session.setAttribute("ErrorMessage", session.getAttribute("lang").toString().equals("ar") ? CONFIG.balanceLockedErrorMessageAr : CONFIG.balanceLockedErrorMessageEn);
							nextJSP = CONFIG.PAGE_ERRPR;
						}
						else {
							MasaryManager.logger.info("Success tramnsaction2 Id ... " + transId);
							session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
							MasaryManager.logger.info("Success tramnsaction3 Id from session ... "
									+ session.getAttribute(CONFIG.PARAM_Transaction_ID));
							session.setAttribute(CONFIG.PARAM_TAX_AMOUNT, String.valueOf(TXNResults.getAmount()));
							request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_CUSTOMER_BILLS);
							if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
								MasaryManager.getInstance().insertEmployeeTransactions(
										session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).toString(), transId);
							}
							if (CONFIG.PARAM_FROM_MOBILE.equals(session.getAttribute(CONFIG.PARAM_FROM_MOBILE))) {
								nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION_MOBILE;
							}
							else // nextJSP =
									// CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION;
							{
								if (topupType == 10 || topupType == 6 || topupType == 8) {
									session.setAttribute(CONFIG.PARAM_TAX_AMOUNT, request.getParameter("servicetax"));
									// nextJSP =
									// CONFIG.PAGE_Printing_Topup_TRANSACTION;
									nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION;

								}
								else {
									nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION_Printing;
								}
							}
						}

					}
					catch (Exception ex) {
						MasaryManager.logger.error(ex);
						MasaryManager.logger.error(ex.getStackTrace());
						if (ex.toString().contains("ORA-20139")) {
							session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
							nextJSP = CONFIG.PAGE_ERRPR_SSO;
						}
						else if (ex.toString().toLowerCase().contains("token")) {
							request.setAttribute("ErrorMessage",
									"رمز الجلسه غير صالح للاستخدام، برجاء الدخول مره اخرى");
							nextJSP = CONFIG.PAGE_LOGIN;
						}
						else {
							// request.setAttribute("ErrorMessage", "رمز الجلسه
							// غير صالح للاستخدام، برجاء الدخول مره اخرى");
							// nextJSP = CONFIG.PAGE_LOGIN;
							session.setAttribute("ErrorMessage", ex.getMessage());
							MasaryManager.logger.error(ex);
							MasaryManager.logger.error(ex.getStackTrace());
							nextJSP = CONFIG.PAGE_ERRPR;
						}

					}
				}
				else {
					nextJSP = (String) session.getAttribute(CONFIG.PAGE);
				}
			}
		}
		catch (NumberFormatException nfe) {
			session.setAttribute("ErrorMessage", CONFIG.getBalanceError(session));
			nextJSP = CONFIG.PAGE_ERRPR;
			MasaryManager.logger.error(nfe.getMessage());
		}
		catch (Exception ex) {
			MasaryManager.logger.error(ex.getMessage());
			if (ex.getMessage().contains("ORA-20139")) {
				session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
				nextJSP = CONFIG.PAGE_ERRPR_SSO;
			}
			else {
				session.setAttribute("ErrorMessage", ex.getMessage());
				MasaryManager.logger.error(ex);
				MasaryManager.logger.error(ex.getStackTrace());
				nextJSP = CONFIG.PAGE_ERRPR;
			}

		}
		MasaryManager.logger.info(action + " " + msisdn + " " + topupType + " IP " + request.getRemoteAddr());
		
		return nextJSP;
	}

	private String sellCreadit(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String action = request.getParameter(CONFIG.PARAM_ACTION);
		String nextJSP = "";
		
		String agentId = (String) session.getAttribute(CONFIG.PARAM_PIN);
		String amount = request.getParameter(CONFIG.AMOUNT);
		String paiedPin = request.getParameter(CONFIG.PARAM_PAYED_PIN);
		if (amount.equals("") || paiedPin.equals("")) {
			session.setAttribute("ErrorMessage", CONFIG.getFillInAllData(session));
			nextJSP = CONFIG.PAGE_ERRPR;
		}
		else if (request.getParameter(CONFIG.CONFIRM) == null) {
			nextJSP = CONFIG.PAGE_SELL_CREADET_CONFIRM;
		}
		else {
			request.removeAttribute(CONFIG.CONFIRM);
			try {
				String transId = String.valueOf(MasaryManager.getInstance().addBalance(paiedPin, agentId, amount));
				session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
				request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_CUSTOMER_BILLS);
				nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION + "?v_and_s=" + " ";
			}
			catch (Exception ex) {
				MasaryManager.logger.error(ex);
				session.setAttribute("ErrorMessage", ex.getMessage());
				nextJSP = CONFIG.PAGE_ERRPR;
			}
		}
		MasaryManager.logger.info(action + " " + paiedPin + " IP " + request.getRemoteAddr());
		
		return nextJSP;
	}

	private String mainMobile()
	{
		return CONFIG.PAGE_MAIN_MOBILE;
	}

	private boolean isLogin(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		return session.getAttribute(CONFIG.PARAM_PIN) != null;
	}

	private void transIncludeFrom(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		
		request.getSession().removeAttribute("include");
		request.getSession().removeAttribute("transFromAg");
		request.getSession().removeAttribute("transToAg");
		String agentID = (String) request.getSession().getAttribute("custIdForAccSession");
		String dayFrom = request.getParameter("dayfrom");
		String monthFrom = request.getParameter("monthfrom");
		String yearFrom = request.getParameter("yearfrom");
		String dayTo = request.getParameter("dayto");
		String monthTo = request.getParameter("monthto");
		String yearTo = request.getParameter("yearto");
		int dayToo = Integer.parseInt(dayTo) + 1;
		session.setAttribute("custIdForAccSession", agentID);
		List transFromAg = MasaryManager.getInstance().getTransactionsByPayerAndDate(agentID, dayFrom, monthFrom,
				yearFrom, String.valueOf(dayToo), monthTo, yearTo);
		session.setAttribute("transFromAg", transFromAg);
		session.setAttribute("include", "transFromAg");
	}

	private void transIncludeTo(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		
		request.getSession().removeAttribute("include");
		request.getSession().removeAttribute("transFromAg");
		request.getSession().removeAttribute("transToAg");
		String agentID = (String) request.getSession().getAttribute("custIdForAccSession");
		String dayFrom = request.getParameter("dayfrom");
		String monthFrom = request.getParameter("monthfrom");
		String yearFrom = request.getParameter("yearfrom");
		String dayTo = request.getParameter("dayto");
		String monthTo = request.getParameter("monthto");
		String yearTo = request.getParameter("yearto");
		int dayToo = Integer.parseInt(dayTo) + 1;
		session.setAttribute("custIdForAccSession", agentID);
		List transToAg = MasaryManager.getInstance().getTransactionsByPayedAndDate(agentID, dayFrom, monthFrom,
				yearFrom, String.valueOf(dayToo), monthTo, yearTo);
		session.setAttribute("transToAg", transToAg);
		session.setAttribute("include", "transToAg");
	}

	private String transIncludeFromCust(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String nextJSP = "";
		
		request.getSession().removeAttribute("include");
		request.getSession().removeAttribute("transFromAg");
		request.getSession().removeAttribute("transToAg");
		String agentID = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
		String empID = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
		String isFromEmployeeOnly = request.getParameter("fromEmployeeOnly").toString();
		String dayFrom = request.getParameter("dayfrom");
		String monthFrom = request.getParameter("monthfrom");
		String yearFrom = request.getParameter("yearfrom");
		String dayTo = request.getParameter("dayto");
		String monthTo = request.getParameter("monthto");
		String yearTo = request.getParameter("yearto");
		int dayToo = Integer.parseInt(dayTo) + 1;
		session.setAttribute("custIdForAccSession", agentID);
		List transFromAg = null;
		if (isFromEmployeeOnly.equals("yes")) {
			transFromAg = MasaryManager.getInstance().getTransactionsByEmployeePayerAndDate(agentID, dayFrom, monthFrom,
					yearFrom, String.valueOf(dayToo), monthTo, yearTo);
		}
		else if (empID.equals("-1")) {
			transFromAg = MasaryManager.getInstance().getTransactionsByPayerAndDate(agentID, dayFrom, monthFrom,
					yearFrom, String.valueOf(dayToo), monthTo, yearTo);
		}
		else {
			transFromAg = MasaryManager.getInstance().getTransactionsByPayerAndDateForEmployee(agentID, empID, dayFrom,
					monthFrom, yearFrom, String.valueOf(dayToo), monthTo, yearTo);
		}
		session.setAttribute("transFromAg", transFromAg);
		session.setAttribute("include", "transFromAg");
		request.setAttribute("fromEmployeeOnly", isFromEmployeeOnly);
		nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_ACCOUNT;
		
		return nextJSP;
	}

	private String transIncludeToCust(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String nextJSP = "";
		
		request.getSession().removeAttribute("include");
		request.getSession().removeAttribute("transFromAg");
		request.getSession().removeAttribute("transToAg");
		String agentID = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
		String dayFrom = request.getParameter("dayfrom");
		String monthFrom = request.getParameter("monthfrom");
		String yearFrom = request.getParameter("yearfrom");
		String dayTo = request.getParameter("dayto");
		String monthTo = request.getParameter("monthto");
		String yearTo = request.getParameter("yearto");
		int dayToo = Integer.parseInt(dayTo) + 1;
		session.setAttribute("custIdForAccSession", agentID);
		List transToAg = MasaryManager.getInstance().getTransactionsByPayedAndDate(agentID, dayFrom, monthFrom,
				yearFrom, String.valueOf(dayToo), monthTo, yearTo);
		session.setAttribute("transToAg", transToAg);
		session.setAttribute("include", "transToAg");
		request.setAttribute("fromEmployeeOnly", "no");
		nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_ACCOUNT;
		
		return nextJSP;
	}

	private String dynamicsWalletTopup(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String nextJSP = "";
		
		String walletId = request.getParameter(CONFIG.PARAM_DYNAMICS_ACCOUNT_ID);
		String confirm = request.getParameter(CONFIG.CONFIRM);

		if (walletId.trim().isEmpty()) {
			MasaryManager.logger.error("Enter a valid account ID");
			session.setAttribute("ErrorMessage", CONFIG.getDynamicsError(session));
			nextJSP = CONFIG.PAGE_ERRPR;
		}
		else if (confirm == null) {
			try {

				URL url = new URL(
						"http://www.razytech.com/dynamic/MasaryApi/CheckId.php?username=dynamic_system&password=1acced5854e1416b571c4b7fbd474fef&paidid="
								+ walletId);
				HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
				httpCon.setDoOutput(true);
				OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
				String inputLine;
				String responseResult = "";
				while ((inputLine = in.readLine()) != null) {
					responseResult = responseResult + inputLine;
				}
				out.close();
				in.close();
				httpCon.disconnect();
				if (responseResult.trim().toLowerCase().contains("success")) {
					String[] responseParts = responseResult.split(":");
					String lastPart = responseParts[responseParts.length - 1];
					String[] resp = lastPart.split("\\|");
					String mobileNumber = resp[2];
					String userName = resp[3];
					boolean existInMasary = MasaryManager.getInstance().isExistInMasary(mobileNumber);
					if (existInMasary) {
						nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_DYNAMICS_CONFIRM;
					}
					else {
						String newCustID = MasaryManager.getInstance().addCustomerFromAPI("928", userName, userName,
								mobileNumber, "Y", "What was the name of your first school?", "any");
						URL url2 = new URL(
								"http://www.razytech.com/dynamic/MasaryApi/Createwallet.php?username=dynamic_system&password=1acced5854e1416b571c4b7fbd474fef&paidid="
										+ walletId + "&walletid=" + newCustID);
						HttpURLConnection httpCon2 = (HttpURLConnection) url2.openConnection();
						httpCon2.setDoOutput(true);
						OutputStreamWriter out2 = new OutputStreamWriter(httpCon2.getOutputStream());
						BufferedReader in2 = new BufferedReader(new InputStreamReader(httpCon2.getInputStream()));
						out2.close();
						in2.close();
						httpCon2.disconnect();
						nextJSP = CONFIG.PAGE_CUSTOMERTOPUP_DYNAMICS_CONFIRM;
					}
				}
				else {
					session.setAttribute("ErrorMessage", CONFIG.getDynamicsError(session));
					nextJSP = CONFIG.PAGE_ERRPR;
				}
			}
			catch (Exception ex) {
				MasaryManager.logger.error(ex.getMessage());
			}
		}
		else {
			try {
				String custID = (String) session.getAttribute(CONFIG.PARAM_PIN);
				double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(custID, 1);
				int amount = 100;
				if (serviceBalance < amount) {
					throw new Exception(CONFIG.getNotEnoughBalanceError(session));
				}
				long transId = MasaryManager.getInstance().addBalance("928", custID, Integer.toString(amount));
				String transactionID = String.valueOf(transId);
				session.setAttribute(CONFIG.PARAM_Transaction_ID, transactionID);
				request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_VIEW_AGENTS);
				MasaryManager.logger.info("Try to get response from Razt-Tech ...");
				URL url = new URL(
						"http://www.razytech.com/dynamic/MasaryApi/MasaryPayment.php?username=dynamic_system&password=1acced5854e1416b571c4b7fbd474fef&paidid="
								+ walletId + "&amount=" + String.valueOf(amount) + "&transactionno=" + transactionID);
				HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
				httpCon.setDoOutput(true);
				OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
				String inputLine;
				String responseResult = "";
				while ((inputLine = in.readLine()) != null) {
					responseResult = responseResult + inputLine;
				}
				out.close();
				in.close();
				httpCon.disconnect();
				MasaryManager.logger.info("Response received from Razt-Tech is .. " + responseResult);
				if (responseResult.trim().toLowerCase().contains("success")) {
					String[] responses = responseResult.split(":");
					MasaryManager.logger.info("Sent transaction to Dynamics " + responses[2]);
					MasaryManager.getInstance().saveDynamicsTXN(custID, walletId, transactionID, responses[2]);
					nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION + "?v_and_s=" + " ";
				}
				else {
					MasaryManager.logger.info("Fail Response received from Razt-Tech on wallet " + custID);
					nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION + "?v_and_s=" + " ";
				}
			}
			catch (Exception ex) {
				MasaryManager.logger.error(ex);
				session.setAttribute("ErrorMessage", ex.getMessage());
				nextJSP = CONFIG.PAGE_ERRPR;
			}
		}
		
		return nextJSP;
	}

	private String bulkVoucher(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String nextJSP = "";
		
		Object voucherArr[] = request.getParameterValues("voucherList");
		String howToSell = (String) request.getParameter("butValue");
		session.removeAttribute("voucherList");
		String custID = (String) session.getAttribute(CONFIG.PARAM_PIN);
		SellVoucherResponse voucherResponse = null;
		String mail = (String) request.getParameter("email");
		request.setAttribute("email", mail);
		String msisdn = !"".equals(mail) ? mail : "Excel";
		if (voucherArr.length != 0) {
			ArrayList<BulkVoucherDTO> voucherInfoList = new ArrayList<BulkVoucherDTO>();
			ArrayList<SellVoucherResponse> voucherList = new ArrayList<SellVoucherResponse>();
			String[] attr = voucherArr[0].toString().split(",");
			int[] attrVoucherID = new int[attr.length / 4];
			String[] attrVoucherName = new String[attr.length / 4];
			double[] attrVoucherDenom = new double[attr.length / 4];
			int[] attrVoucherCount = new int[attr.length / 4];
			for (int i = 0; i < attr.length / 4; i++) {
				attrVoucherID[i] = Integer.parseInt(attr[i]);
				attrVoucherName[i] = attr[i + attr.length / 4];
				attrVoucherDenom[i] = Double.parseDouble(attr[i + 2 * (attr.length / 4)]);
				attrVoucherCount[i] = Integer.parseInt(attr[i + 3 * (attr.length / 4)]);
			}
			BulkVoucherDTO bVoucher = null;
			for (int i = 0; i < attrVoucherID.length; i++) {
				try {
					bVoucher = new BulkVoucherDTO();
					voucherResponse = new SellVoucherResponse();
					bVoucher.setService_id(attrVoucherID[i]);
					bVoucher.setService_Name(attrVoucherName[i]);
					bVoucher.setDenom(attrVoucherDenom[i]);
					bVoucher.setVoucherCount(attrVoucherCount[i]);
					voucherInfoList.add(bVoucher);
					voucherResponse = MasaryManager.getInstance()
							.do_Bulk_transaction(
									session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")
											? Integer.parseInt(custID)
											: Integer.parseInt((String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)),
									bVoucher.getDenom(), bVoucher.getService_id(), howToSell, msisdn, "", "",
									(String) session.getAttribute(CONFIG.lang), bVoucher.getVoucherCount());
					bVoucher.setIsFound(Integer.parseInt(voucherResponse.getTransId()) > 0);
					bVoucher.setReason(voucherResponse.getStatusMSG());
					voucherList.add(voucherResponse);
				}
				catch (Exception ex) {
					MasaryManager.logger.error(ex);
				}
				session.setAttribute("voucherList", voucherList);
				session.setAttribute("voucherInfoList", voucherInfoList);
			}
		}
		if (howToSell.equals("E-mail")) {
			nextJSP = CONFIG.PAGE_SEND_MAIL;
		}
		else {
			nextJSP = CONFIG.PAGE_VIEW_BULK_RESULT;
		}
		
		return nextJSP;
	}

	private String BulkSMSTransfer(HttpServletRequest request) throws UnsupportedEncodingException
	{
		HttpSession session = request.getSession();
		String action = request.getParameter(CONFIG.PARAM_ACTION);
		String nextJSP = "";
		
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> mobileList = new ArrayList<String>();
		String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
		String countMobile = request.getParameter("countmobile");
		String notBrowse = request.getParameter("notBrowse");
		String sheetname = request.getParameter("sheetname");
		String message = new String(request.getParameter(CONFIG.PARAM_TEXTAREA).getBytes("ISO-8859-1"), "UTF-8")
				.replace("\r\n", " ");
		String language = request.getParameter("mytextarea");
		String mobile = null;
		if (notBrowse.equals("1")) {
			String mobilesN = request.getParameter("MSISDN");
			String[] s = mobilesN.split(",");
			mobileList.addAll(Arrays.asList(s));
			countMobile = String.valueOf(s.length);
		}
		else {
			for (int i = 0; i < Integer.parseInt(countMobile); i++) {
				mobile = request.getParameter(String.valueOf(i));
				mobile = mobile.replace("\n", "").replace("\r", "");
				mobile = mobile.replace(",", "");
				mobileList.add(mobile);
			}
		}
		session.setAttribute("countmobile", countMobile);
		try {
			if (MasaryManager.getInstance().getCustomerInfo(id).getCurrentBalance() < MasaryManager.getInstance()
					.getBulk_SMS_DeductedAmount(message, language, Integer.parseInt(countMobile))) {
				throw new Exception(CONFIG.getNotEnoughBalanceError(session));
			}
			if (request.getParameter(CONFIG.CONFIRM) != null) {
				MasaryManager.logger.info(action + " " + id + " IP " + request.getRemoteAddr() + "Number of Mobiles "
						+ countMobile + " lang " + language);
				for (int i = 0; i < mobileList.size(); i++) {
					if (!mobileList.get(i).startsWith("01")) {
						MasaryManager.logger.error(
								"ERROR Mobiles not started with 01 number " + i + " Mobile " + mobileList.get(i));
						session.setAttribute("ErrorMessage", CONFIG.getMOBILE_MUST_STARTWITH_ZERO(session));
						nextJSP = CONFIG.PAGE_ERRPR;
						return nextJSP;
					}
				}
				String transId = String.valueOf(MasaryManager.getInstance().SendBulkSMS(id, message, mobileList,
						language, sheetname, Integer.parseInt(countMobile)));

				if (Integer.parseInt(transId) == 0 || Integer.parseInt(transId) == -1) {
					MasaryManager.logger.error("ERROR transaction id 0 or -1");
					session.setAttribute("ErrorMessage", CONFIG.getBULK_SMS_ERROR(session));
					nextJSP = CONFIG.PAGE_ERRPR;
				}
				else if (Integer.parseInt(transId) == -2) {
					MasaryManager.logger.error("ERROR transaction Id -2");
					session.setAttribute("ErrorMessage", CONFIG.getMOBILE_MUST_STARTWITH_ZERO(session));
					nextJSP = CONFIG.PAGE_ERRPR;
				}
				else {
					session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
					request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_VIEW_AGENTS);
					nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION + "?voucherResponse=" + " ";
				}
			}
			else {
				session.setAttribute("mobiles", mobileList);
				nextJSP = CONFIG.PAGE_SEND_BULK_SMS_CONFIRMATION;
				return nextJSP;
			}
		}
		catch (NumberFormatException ex) {
			MasaryManager.logger.error(ex);
			session.setAttribute("ErrorMessage", CONFIG.getBULK_SMS_ERROR(session));
			nextJSP = CONFIG.PAGE_ERRPR;
		}
		catch (Exception ex) {
			MasaryManager.logger.error(ex);
			if (ex.getMessage().contains("ORA-20139")) {
				session.setAttribute("ErrorMessage", ex.getMessage());
				nextJSP = CONFIG.PAGE_ERRPR_SSO;
			}
			else {
				session.setAttribute("ErrorMessage", ex.getMessage());
				nextJSP = CONFIG.PAGE_ERRPR;
			}
		}
		
		return nextJSP;
	}
}