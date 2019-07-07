package com.masary.controlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;

public class LogoutAction
{
	public String logout(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        int customer_id = Integer.parseInt(session.getAttribute(CONFIG.PARAM_PIN).toString());
        int sso = Integer.parseInt(session.getAttribute(CONFIG.PARAM_SSO).toString());
        MasaryManager.logger.info("action logout " + session.getAttribute(CONFIG.PARAM_PIN));

        MasaryManager.getInstance().logout(customer_id, sso);

        nextJSP = CONFIG.PAGE_LOGIN;

        return nextJSP;
    }
}
