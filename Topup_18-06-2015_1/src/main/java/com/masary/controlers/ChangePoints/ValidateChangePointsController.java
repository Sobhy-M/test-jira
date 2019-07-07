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
import com.masary.integration.dto.ChangePointsDTO;

public class ValidateChangePointsController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String nextJSP = "";

        try {
            if (!isLogin(session)) {

                nextJSP = CONFIG.PAGE_LOGIN;
               
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                dispatcher.forward(request, response);
                return;
            }
            MasaryManager.logger.info("validateLoyaltyPoints check points Of Wallet availabilty");

            validateLoyaltyPoints(request);

            nextJSP = CONFIG.LoyaltyPoints_Home_Page;

        } catch (Exception e) {
            MasaryManager.logger.info("Error:- " + e.getStackTrace());
            request.getSession().setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;

        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }
    }

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }

    private void validateLoyaltyPoints(HttpServletRequest request) throws Exception {

        MasaryManager.logger.info("Check loyalty-validateLoyaltyPoints  avalablity");

        String lang = "en";
        if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
        } else {
            lang = "ar";
        }

        MasaryManager.logger.info("loyalityPoints not found and call client to get it");
        LoyalityPointsClient loyalityPointsClient = new LoyalityPointsClient();

        ChangePointsDTO loyaltyPointsRepresentation = loyalityPointsClient.loyalityPointsValidate(lang, request.getSession().getAttribute("Token").toString());
        request.getSession().setAttribute("points", loyaltyPointsRepresentation.getPoints());

        request.getSession().setAttribute("loyaltyPointsRepresentation", loyaltyPointsRepresentation);
        
    }
    
}