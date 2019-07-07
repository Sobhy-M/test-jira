package com.masary.controlers.NewElectricity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.masary.common.CONFIG;
import com.masary.database.dto.AgentDTO;
import com.masary.database.dto.Bill_Request;
import com.masary.database.dto.Bill_Response;
import com.masary.database.dto.LoginDto;
import com.masary.database.dto.Masary_Bill_Type;
import com.masary.database.dto.RatePlanDTO;
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
 * @author Abdelsabor
 */
public class Electricity_InfoController extends HttpServlet {

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
        String action;
        HttpSession session = request.getSession();

        action = request.getParameter(CONFIG.PARAM_ACTION);
        session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
        String nextJSP = "";
        int serviceId;
        try {
            /* TODO output your page here. You may use following sample code. */
            if (!isLogin(session)) {
                if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
                    nextJSP = CONFIG.PAGE_LOGIN;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                    return;
                }
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
            }
            serviceId = Integer.parseInt(session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString());
            String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
            int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
 
            String AccountNumber = request.getParameter("AccountNumber");
            
            ////////////////////////////////////////////////////////////////////////////////////
            Bill_Request bill_Request = new Bill_Request();
            bill_Request.setCUSTOMER_ID(customer_id);
            bill_Request.setBILLING_ACCOUNT(AccountNumber);
            bill_Request.setCHANNEL("WEB");
            bill_Request.setLANG(lang);
            bill_Request.setSERVICE_ID(serviceId);
            Bill_Response bill_Response = (Bill_Response) (MasaryManager.getInstance().Inquiry(bill_Request));
            bill_Response.setBILLING_ACCOUNT(AccountNumber);
//
            if (bill_Response.getSTATUS_CODE() == 200) {
                request.setAttribute("ElectricityBillValue", bill_Response.getAMOUNT());
                session.setAttribute("bill_Response", bill_Response);
                Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(serviceId));
                AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
                RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(String.valueOf(bill_Type.getBILL_TYPE_ID()), agent.getPin());
                session.setAttribute("ratePlan", ratePlan);
                session.setAttribute("BTC", bill_Type);
                nextJSP = CONFIG.ElectricityInquiryInfoPage;
            } else {
                nextJSP = CONFIG.ElectricityInquiry;
                request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + bill_Response.getSTATUS_CODE() + " " + bill_Response.getSTATUS_MESSAGE());
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            nextJSP = CONFIG.PAGE_ERRPR;
            MasaryManager.logger.error("Exception" + e);
            session.setAttribute("ErrorMessage", e.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } finally {
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
