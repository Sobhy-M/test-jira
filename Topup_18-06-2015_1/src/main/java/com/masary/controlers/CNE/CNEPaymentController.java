package com.masary.controlers.CNE;

import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.CNEClient;
import com.masary.integration.dto.CNEPaymentRequestDTO;
import com.masary.integration.dto.CNEPaymentResponseDTO;

/**
 *
 * @author omar.abdellah
 */
public class CNEPaymentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String nextJSP = "";

        String reciept = "reciept";
        ResourceBundle rb = ResourceBundle.getBundle("Bundle");

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

            CNEPaymentRequestDTO cnePaymentRequestDTO = new CNEPaymentRequestDTO(request.getParameter("validationId"));
            CNEClient cneClient = new CNEClient();

            CNEPaymentResponseDTO paymentResponse = cneClient.payment(cnePaymentRequestDTO, lang, session.getAttribute("Token").toString());

            Date dateValue = new Date(paymentResponse.getCoverageEndDate());

            if (paymentResponse != null) {
                request.setAttribute("paymentResponse", paymentResponse);
                request.setAttribute("action", reciept);

                request.setAttribute("dateValue", dateValue);
                nextJSP = rb.getString("Page_CNE_ConfirmationPage");

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
