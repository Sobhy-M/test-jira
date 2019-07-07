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
import com.masary.integration.GoBusInqueryDTO;
import com.masary.integration.dto.GoBusInquiryRepresentation;

/**
 * Servlet implementation class GoBusConfirmationController
 */
public class GoBusConfirmationController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoBusConfirmationController()
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

			nextJSP = GoBusProperties.goBusConfirmationPage;

			GoBusInqueryDTO inqueryDTO = getInqueryRequest(request);

			GoBusInquiryRepresentation inquiryRepresentation = makeInquery(inqueryDTO);

			request.setAttribute("inquiryRepresentation", inquiryRepresentation);

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

	private GoBusInqueryDTO getInqueryRequest(HttpServletRequest request)
	{

		String referanceNum = request.getParameter("referanceNum");
		String token = request.getSession().getAttribute("Token").toString();
		String lang = request.getSession().getAttribute("lang").toString();

		GoBusInqueryDTO goBusInqueryDTO = new GoBusInqueryDTO();

		goBusInqueryDTO.setReferanceNum(referanceNum);
		goBusInqueryDTO.setLang(lang);
		goBusInqueryDTO.setToken(token);

		return goBusInqueryDTO;
	}

	private GoBusInquiryRepresentation makeInquery(GoBusInqueryDTO inqueryDTO) throws Exception
	{

		GoBusHttpClient goBusHttpClient = new GoBusHttpClient(HttpClients.createDefault());

		GoBusInquiryRepresentation inquiryRepresentation = goBusHttpClient.doGoBusInquery(inqueryDTO);

		return inquiryRepresentation;

	}

}
