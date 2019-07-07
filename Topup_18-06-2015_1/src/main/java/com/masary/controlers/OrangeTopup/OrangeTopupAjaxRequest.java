package com.masary.controlers.OrangeTopup;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import com.masary.integration.dto.TopupDenominationDTO;

/**
 * Servlet implementation class OrangeTopupAjaxRequest
 */
public class OrangeTopupAjaxRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrangeTopupAjaxRequest() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		List<TopupDenominationDTO> denomnationsList = (List<TopupDenominationDTO>) request.getSession()
				.getAttribute(OrangeTopupProperties.orangDenominationMapName);

		List<String> dnemoList = new ArrayList<String>();
		double amount = Double.parseDouble(request.getParameter("amount"));

		for (TopupDenominationDTO i : denomnationsList) {
			if (i.getDenominationAmount() == amount) {
				String newDnemo = i.getDenominationName() + "," + i.getDenominationId();
				dnemoList.add(newDnemo);
			}
		}

		if (dnemoList.size() >= 1) {
			out.write(StringUtils.join(dnemoList, "-"));
		} else {
			out.write("-1");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
