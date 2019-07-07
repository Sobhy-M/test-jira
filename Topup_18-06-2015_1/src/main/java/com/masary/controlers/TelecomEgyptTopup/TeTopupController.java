/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.TelecomEgyptTopup;

import com.masary.common.CONFIG;
import com.masary.integration.TelecomEgyptTopupClient;
import com.masary.integration.dto.TETopupRequest;
import com.masary.integration.dto.TETopupResponse;
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
public class TeTopupController extends HttpServlet {

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
        HttpSession session = request.getSession();
        String nextPage = "";
        String amount = "";
        try {
            /* TODO output your page here. You may use following sample code. */
            amount = request.getParameter("AMOUNT");
            TelecomEgyptTopupClient TETopupClient = new TelecomEgyptTopupClient();
            TETopupRequest topupRequest = new TETopupRequest();
            topupRequest.setAmount(Long.parseLong(amount));
            topupRequest.setMobileNumber(request.getParameter(CONFIG.PARAM_MSISDN));
            String token = session.getAttribute("Token").toString();
            String UUId = (String) session.getAttribute("uuid");
            TETopupResponse topupPresentation = TETopupClient.TETopup(topupRequest, "ar",UUId, token, request.getRemoteAddr());
            session.setAttribute("CommessionResponse", topupPresentation);
            nextPage = CONFIG.PAGE_TelecomEgypt_Recipt;

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
            dispatcher.forward(request, response);
            session.setAttribute("uuid", "");
        } catch (Exception ex) {
            if (ex.getMessage().equals("6501")) {
                session.setAttribute("ErrorMessage", "  لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق 6501");
            } else if (ex.getMessage().equals("6502")) {
                session.setAttribute("ErrorMessage", "6502".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق   "));
            } else if (ex.getMessage().equals("6503")) {
                session.setAttribute("ErrorMessage", "6503".concat("  لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق"));
            } else if (ex.getMessage().equals("6504")) {
                session.setAttribute("ErrorMessage", "6504".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق"));
            } else if (ex.getMessage().equals("6505")) {
                session.setAttribute("ErrorMessage", "6505".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق"));
            } else if (ex.getMessage().equals("6506")) {
                session.setAttribute("ErrorMessage", "6506".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق"));
            } else if (ex.getMessage().equals("6507")) {
                session.setAttribute("ErrorMessage", "6507".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق"));
            } else if (ex.getMessage().equals("6508")) {
                session.setAttribute("ErrorMessage", "6508".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق"));
            } else if (ex.getMessage().equals("6509")) {
                session.setAttribute("ErrorMessage", "6509".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق"));
            } else if (ex.getMessage().equals("65010")) {
                session.setAttribute("ErrorMessage", "65010".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق"));
            } else if (ex.getMessage().equals("65011")) {
                session.setAttribute("ErrorMessage", "65011".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق"));
            } else if (ex.getMessage().equals("65012")) {
                session.setAttribute("ErrorMessage", "65012".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق"));
            } else if (ex.getMessage().equals("65013")) {
                session.setAttribute("ErrorMessage", "65013".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق"));
            } else if (ex.getMessage().equals("65014")) {
                session.setAttribute("ErrorMessage", "65014".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق"));
            } else if (ex.getMessage().equals("65015")) {
                session.setAttribute("ErrorMessage", "65015".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق"));
            } else if (ex.getMessage().equals("65016")) {
                session.setAttribute("ErrorMessage", "65016".concat("قيمة الشحن خطأ برجاء اعادة كتابه القيمة بشكل صحيح "));
            } else if (ex.getMessage().equals("65017")) {
                session.setAttribute("ErrorMessage", "65017".concat("رقم الموبايل غير صحيح  "));
            } else if (ex.getMessage().equals("65018")) {
                session.setAttribute("ErrorMessage", "65018".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق  "));
            } else if (ex.getMessage().equals("65019")) {
                session.setAttribute("ErrorMessage", "65019".concat("لقد تعذر اتمام العملية برجاءاعادة كتابه رقم الموبايل بشكل صحيح "));
            } else if (ex.getMessage().equals("6")) {
                session.setAttribute("ErrorMessage", "6".concat("لقد تعذر اتمام العملية برجاء المحاولة في وقت لاحق   "));
            }else if (ex.getMessage().equals("429")) {
                session.setAttribute("ErrorMessage", "429".concat("برجاء الرجوع الي قائمة العمليات و التأكد من نجاح العملية   "));
            }
            else {
                session.setAttribute("ErrorMessage", ex.getMessage());
            }
            nextPage = CONFIG.PAGE_ERRPR;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
            dispatcher.forward(request, response);
            session.setAttribute("uuid", "");
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

}
