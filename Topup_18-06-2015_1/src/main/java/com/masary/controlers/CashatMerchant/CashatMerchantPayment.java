package com.masary.controlers.CashatMerchant;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.CashatMerchantClient;
import com.masary.integration.dto.CashatPaymentRequest;
import com.masary.integration.dto.CashatPaymentResponse;


public class CashatMerchantPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String nextPage = "";
        HttpSession session = request.getSession();

        try {
        	 nextPage =cashatPayment(request);

        } catch (Exception ex) {
            MasaryManager.logger.error("ErrorMessage " + ex.getMessage(), ex);
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextPage = CONFIG.PAGE_ERRPR;
        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
            dispatcher.forward(request, response);
        }

    }

	private String cashatPayment(HttpServletRequest request) throws Exception {

    String nextPage = "";
	HttpSession session = request.getSession();
	String lang = "en";
	if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
	} else {
		lang = "ar";
	}
	int accountId = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
	String token = request.getSession().getAttribute("Token").toString();
	String validationId = request.getParameter("validationId");
	CashatPaymentRequest cashatPaymentRequest = new CashatPaymentRequest();
	cashatPaymentRequest.setValidationId(validationId);
	//TODO, update log message with merchantId, validationId, ...etc
	MasaryManager.logger.info("Account "+accountId+" will call the client to pay with the data of "+"validationId"+validationId);	
	CashatMerchantClient cashatClient = new CashatMerchantClient();
	CashatPaymentResponse cashatPaymentResponse =cashatClient.cashatPayment(cashatPaymentRequest, lang, token);
	
	request.setAttribute("cashatPaymentResponse", cashatPaymentResponse);
	
	 if (cashatPaymentResponse == null) {
		 //TODO, update log message to be ERROR instead of INFO, then update the log message content
		 MasaryManager.logger.error("ErrorMessage " + "Account "+accountId+" failed to pay");
         session.setAttribute("ErrorMessage", "حدث خطأ فى تنفيذ العملية");
         nextPage = CONFIG.PAGE_ERRPR;
     } else {
   	     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         DateFormat timeFormat = new SimpleDateFormat("HH:mm");
         Date date1 = new Date(cashatPaymentResponse.getUpdateDate());
         String date = dateFormat.format(date1);
         String time = timeFormat.format(date1);
         session.setAttribute("time", time);
         session.setAttribute("date", date);
    	 nextPage = CashatMerchantProperties.PAGE_CashatMerchant_Payment;

             }
	 return nextPage;
	}
}
