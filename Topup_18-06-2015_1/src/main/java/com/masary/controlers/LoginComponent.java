package com.masary.controlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.masary.common.CONFIG;
import com.masary.common.Captch;
import com.masary.database.manager.MasaryManager;
import com.masary.exceptions.InvalidLoginException;
import javax.xml.bind.DatatypeConverter;
import nl.captcha.Captcha;

public class LoginComponent {

    private LoginAction loginAction;
    private UserLoginCheckAction userLoginCheckAction;
    private LogoutAction logoutAction;
    private ResetPasswordAction resetPasswordAction;

    public LoginComponent(LoginAction loginAction, UserLoginCheckAction userLoginCheckAction, LogoutAction logoutAction,
            ResetPasswordAction resetPasswordAction) {
        this.loginAction = loginAction;
        this.userLoginCheckAction = userLoginCheckAction;
        this.logoutAction = logoutAction;
        this.resetPasswordAction = resetPasswordAction;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nextJSP = "";
        String action;
        HttpSession session = request.getSession();

        action = request.getParameter(CONFIG.PARAM_ACTION);

        try {

            if (!userLoginCheckAction.isLogin(session.getAttribute(CONFIG.PARAM_PIN))) {
                if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
                    nextJSP = CONFIG.PAGE_LOGIN;
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                    return;
                }
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
            }

            String captchaText = (String) session.getAttribute("captchaText");
            String captchaAnswer = request.getParameter("captchaAnswer");

            if (captchaText != null && captchaAnswer != null) {
                if (CONFIG.ACTION_AUTHENTICATE_USER.equals(action) && captchaText.equals(captchaAnswer)) {
                    nextJSP = loginAction.login(request, response);
                } else {
                    nextJSP = CONFIG.PAGE_LOGIN;
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                    return;
                }
            } else {
                if (CONFIG.ACTION_LOGOUT.equals(action) && (captchaText == null && captchaAnswer == null)) {
                    MasaryManager.logger.info("CONFIG.ACTION_LOGOUT logout " + session.getAttribute(CONFIG.PARAM_PIN) + ""
                            + request.getRemoteAddr());
                    nextJSP = logoutAction.logout(request);
                } else if (CONFIG.ACTION_RESET_PASSWORD.equals(action) && (captchaText == null && captchaAnswer == null)) {
                    nextJSP = resetPasswordAction.resetPassword(request);
                }

            }

            try {
                session.setAttribute(CONFIG.PAGE, nextJSP);
            } catch (Exception e) {

            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } // end try
        catch (InvalidLoginException e) {
            MasaryManager.logger.error("Error while processing request " + e.getMessage(), e);
            session.setAttribute("ErrorMessage", "Admin Authentication Error, Login again");
            nextJSP = CONFIG.PAGE_ERRPR;
            RequestDispatcher dispatcher = request.getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (NullPointerException e) {
            MasaryManager.logger.error("Error while processing request " + e.getMessage(), e);
            session.setAttribute("ErrorMessage", "Admin Authentication Error, Login again");
            nextJSP = CONFIG.PAGE_ERRPR;
            RequestDispatcher dispatcher = request.getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            MasaryManager.logger.error("Error while processing request " + e.getMessage(), e);
        } // end catch

    }

}
