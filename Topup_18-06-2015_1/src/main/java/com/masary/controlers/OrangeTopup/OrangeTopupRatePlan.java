package com.masary.controlers.OrangeTopup;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.CommissionTransaction;
import com.masary.integration.dto.TopupDenominationDTO;
import com.masary.utils.SystemSettingsUtil;

/**
 * Servlet implementation class OrangeTopupRatePlan
 */
public class OrangeTopupRatePlan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrangeTopupRatePlan() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String denmoID = request.getParameter("denmoID");

		TopupDenominationDTO topupDenominationDTO = getTopupDenominationDTO(request, Long.parseLong(denmoID));

		String ratePlanCommissionsUrl = SystemSettingsUtil.getInstance().loadProperty("new.commissions.rateplan.url");
		String proxyAuthorizationToken = request.getSession().getAttribute("Token").toString();
		String ratePlanCommissionsUrlWithServiceId = ratePlanCommissionsUrl.replace("{serviceId}",
				topupDenominationDTO.getSubsrviceId());
		String ratePlanCommissionsUrlWithAmount = ratePlanCommissionsUrlWithServiceId.replace("{amount}",
				String.valueOf(topupDenominationDTO.getDenominationAmount()));
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {

			HttpGet httpGet = new HttpGet(ratePlanCommissionsUrlWithAmount);

			httpGet.addHeader("Authorization", proxyAuthorizationToken);
			httpGet.addHeader("deviceType", "Web");
			httpGet.addHeader("ip", request.getRemoteAddr());

			CloseableHttpResponse ratePlanCommissionResponse = httpclient.execute(httpGet);

			String ratePlanCommissionResponseBody = EntityUtils.toString(ratePlanCommissionResponse.getEntity(),
					"UTF-8");
			MasaryManager.logger.info("RatePlan Service response is : " + ratePlanCommissionResponseBody);
			Gson gson = new Gson();

			CommissionTransaction commissionTransaction;

			// If RatePlan service responds successfully
			if (ratePlanCommissionResponse.getStatusLine().getStatusCode() == 200) {
				commissionTransaction = gson.fromJson(ratePlanCommissionResponseBody, CommissionTransaction.class);
				// Write returned commissionTransaction in the form of old system response
				out.write(commissionTransaction.toOldDBResponse());
			} else {
				MasaryManager.logger
						.info("Failed to get commission for customer " + request.getSession().getAttribute(""));
				request.getSession().setAttribute("ErrorMessage", "يرجى إعادة المحاولة وإعادة اختيار الخدمة");
				ratePlanCommissionResponseBody = "";
				out.write("-20");
			}

		} catch (Exception ex) {
			MasaryManager.logger.error("Error during calling rateplan on new system" + ex);
			out.write("-20");			
		} finally {
			out.close();
			httpclient.close();

		}
	}

	private TopupDenominationDTO getTopupDenominationDTO(HttpServletRequest request, long dnemoId) {

		List<TopupDenominationDTO> topupDonmenationsList = (List<TopupDenominationDTO>) request.getSession()
				.getAttribute(OrangeTopupProperties.orangDenominationMapName);

		for (TopupDenominationDTO i : topupDonmenationsList) {
			if (i.getDenominationId() == dnemoId) {
				return i;
			}
		}
		return null;
	}

}
