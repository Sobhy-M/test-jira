package com.masary.controlers.Valu;

import com.masary.common.CONFIG;
import com.masary.integration.ValuClient;
import com.masary.integration.dto.ValuInquiryResponse;
import com.masary.integration.dto.ValuPaymentResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author amira
 */
public class ValuInquiryController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String nextPage = "";
        String lang = "";
        HttpSession session = request.getSession();
//        String amount = request.getParameter(CONFIG.AMOUNT);
//        String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
        String action = request.getParameter(CONFIG.PARAM_ACTION);

        try {
            if (!isLogin(session)) {
                if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
                    nextPage = CONFIG.PAGE_LOGIN;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
                    dispatcher.forward(request, response);
                    return;
                }
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
            }
            /* TODO output your page here. You may use following sample code. */
            if (CONFIG.ACTION_VALU_GETINFO.equals(action)) {
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                    lang = "en";
                } else {
                    lang = "ar";
                }

                String token = session.getAttribute("Token").toString();
                ValuClient valuClient = new ValuClient();
                String subscribeNumber = request.getParameter(CONFIG.PARAM_MSISDN);
//                String UUId = (String) session.getAttribute("uuid");

                ValuInquiryResponse inquiryCommessionResponse = valuClient.valuValidateInquiry(subscribeNumber, lang, token);

                if (inquiryCommessionResponse.getGlobalTrxId() != null || inquiryCommessionResponse.getMobileNo() != null || inquiryCommessionResponse.getDueAmount() != 0.0) {
                    session.setAttribute("inquiryCommessionResponse", inquiryCommessionResponse);
                    nextPage = CONFIG.PAGE_VALU_INFO;
                    session.setAttribute("uuid", "");
                } else {
                    nextPage = CONFIG.PAGE_ERRPR;
//                    session.setAttribute("uuid", "");
                }

            } else if (CONFIG.ACTION_VALU_CONFIRMATION.equals(action)) {
                nextPage = CONFIG.PAGE_VALU_CONFIRMATION2;
                session.setAttribute("uuid", "");
            }
        } catch (Exception ex) {
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextPage = CONFIG.PAGE_ERRPR;
            session.setAttribute("uuid", "");
        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
            dispatcher.forward(request, response);
            out.close();
        }
    }

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
