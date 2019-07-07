package com.masary.controlers.mubasher;

import com.masary.common.CONFIG;
import com.masary.database.dto.MubasherResponseDto;
import com.masary.database.manager.MasaryManager;
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
 * @author AYA
 */
public class MubasherVoucher extends HttpServlet {

    private HttpSession session;

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
        try {
            /* TODO output your page here. You may use following sample code. */
            session = request.getSession();
            String nextJSP = "";
            String action;
            action = request.getParameter(CONFIG.PARAM_ACTION);
            if (!isLogin()) {
                if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
                    nextJSP = CONFIG.PAGE_LOGIN;

                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                    return;
                }
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
            }

            if (action.equals(CONFIG.ACTION_CUSTOMER_RECHARGE)) {
                int serviceId = Integer.parseInt(request.getParameter("SERVICE_ID"));
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                nextJSP = CONFIG.PAGEMUBASHERVO;
            } else if (action.equals(CONFIG.ACTION_CUSTOMER_TOPUP)) {

                nextJSP = CONFIG.PAGEMUBASHERCONFIRMATIONVO;
            } else {
                String customerPhone = request.getParameter(CONFIG.PARAM_MSISDN);
                int custId = Integer.parseInt(session.getAttribute(CONFIG.PARAM_PIN).toString());
                String itemid = request.getParameter(CONFIG.itemId);
                MubasherResponseDto[] list = MasaryManager.getInstance().doItemTransaction(custId, itemid, customerPhone);
                if (!String.valueOf(list[0].getTRANSACTION_ID()).equals("null") && list[0].getTRANSACTION_ID()!=0) {

                    session.setAttribute("MubasherList", list);
                    nextJSP = CONFIG.PAGEMUBASHERRECIPTVO;

                } else {
                    session.setAttribute("ErrorMessage", "Mubasher Voucher txn :" + list[0].getERROR_TEXT());
                    nextJSP = "error.jsp";
                }
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } finally {
            out.close();
        }
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

    private boolean isLogin() {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }

}
