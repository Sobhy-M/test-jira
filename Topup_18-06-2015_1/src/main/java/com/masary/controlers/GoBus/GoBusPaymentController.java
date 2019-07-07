package com.masary.controlers.GoBus;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.impl.client.HttpClients;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.GoBusHttpClient;
import com.masary.integration.dto.GoBusPaymentData;
import com.masary.integration.dto.GoBusPaymentRepresentation;

/**
 * Servlet implementation class GoBusPaymentController
 */
public class GoBusPaymentController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoBusPaymentController()
	{
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String nextJSP = "";

		try
		{

			GoBusPaymentData paymentData = preparePaymentData(request);
			GoBusPaymentRepresentation paymentRepresentation = makePayment(paymentData);

			request.setAttribute("goBusPaymentRepresentation", paymentRepresentation);
			nextJSP = GoBusProperties.goBusPaymentPage;

		}
		catch (Exception e)
		{
			MasaryManager.logger.info("Error:- " + e.getStackTrace());
			request.getSession().setAttribute("ErrorMessage", e.getMessage());
			nextJSP = CONFIG.PAGE_ERRPR;

		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
	}

	private GoBusPaymentData preparePaymentData(HttpServletRequest request)
	{
		String validationId = request.getParameter("validationId");
		String ip = request.getRemoteAddr();
		String lang = request.getSession().getAttribute("lang").toString();
		String token = request.getSession().getAttribute("Token").toString();

		return new GoBusPaymentData(validationId, token, ip, lang);

	}

	private GoBusPaymentRepresentation makePayment(GoBusPaymentData goBusPaymentData) throws Exception
	{
		GoBusHttpClient goBusHttpClient = new GoBusHttpClient(HttpClients.createDefault());

		return goBusHttpClient.doGoBusPayment(goBusPaymentData);

	}

}
