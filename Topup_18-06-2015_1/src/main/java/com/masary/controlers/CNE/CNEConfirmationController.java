package com.masary.controlers.CNE;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.CNEClient;
import com.masary.integration.dto.CNERequest;
import com.masary.integration.dto.CNEResponse;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author omar.abdellah
 */
public class CNEConfirmationController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        ResourceBundle rb = ResourceBundle.getBundle("Bundle");
        String nextJSP = "";
        String confirmation = "confirmation";
        try {
            if (!isLogin(session)) {
                nextJSP = CONFIG.PAGE_LOGIN;
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                dispatcher.forward(request, response);
                return;
            }
            String lang = "en";
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            } else {
                lang = "ar";
            }
            CNERequest paymentRequest = new CNERequest(request.getParameter("PhoneNumber"), request.getParameter("subscribtion"));
            CNEClient cneClient = new CNEClient();
            CNEResponse representation = cneClient.inquiry(paymentRequest, lang, session.getAttribute("Token").toString());

            Date dateValue = new Date(representation.getCoverageEndDate());

            if (representation != null) {
                request.setAttribute("inquiryResponse", representation);
                request.setAttribute("dateValue", dateValue);
                nextJSP = rb.getString("Page_CNE_ConfirmationPage");
                request.setAttribute("action", confirmation);
            } else {

                nextJSP = CONFIG.PAGE_ERRPR;
            }

        } catch (Exception e) {
            MasaryManager.logger.error("Error During Calling tamweel ", e);
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;

        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        }
    }

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
