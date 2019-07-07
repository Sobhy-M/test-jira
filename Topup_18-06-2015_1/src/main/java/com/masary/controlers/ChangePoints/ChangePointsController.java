package com.masary.controlers.ChangePoints;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.LoyalityPointsClient;
import com.masary.integration.dto.LoyaltyPointSaveRequest;
import com.masary.integration.dto.LoyaltyPointsSaveResponse;

public class ChangePointsController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String nextJSP = "";
        try {
            String lang = "en";
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            } else {
                lang = "ar";
            }
            MasaryManager.logger.info("loyaltyPoints confirmation");

            LoyalityPointsClient loyalityPointsClient = new LoyalityPointsClient();
            LoyaltyPointSaveRequest loyaltyPointSaveRequest = new LoyaltyPointSaveRequest();
            
            String selectedPoints = request.getParameter("selectedPoints");
            loyaltyPointSaveRequest.setWalletPoints(Long.valueOf(selectedPoints));
            
            LoyaltyPointsSaveResponse loyaltyPointsResponse = loyalityPointsClient.loyaltyPointsSavePayment(loyaltyPointSaveRequest, lang, request.getSession().getAttribute("Token").toString());
            request.setAttribute("loyaltyPointsResponse", loyaltyPointsResponse);

            nextJSP = CONFIG.LoyaltyPoints_Save_Page;
        } catch (Exception e) {
            MasaryManager.logger.info("Error:- " + e.getStackTrace());
            request.getSession().setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;

        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }

    }

}
