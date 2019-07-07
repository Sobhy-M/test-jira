package com.masary.controlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;

import nl.captcha.Captcha;

public class ResetPasswordAction
{
	public String resetPassword(HttpServletRequest request)
	{
		String nextJSP = "";
		HttpSession session = request.getSession();
		String sQuestion = request.getParameter(CONFIG.PARAM_QUESTION);
		String sAnswer = request.getParameter(CONFIG.PARAM_ANSWER);
		String msisdn = request.getParameter(CONFIG.PARAM_MSISDN);
		Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
		String captchaAnswer = request.getParameter(CONFIG.PARAM_ANSWER_CAPTCHA);
		try
		{
			if (sQuestion.isEmpty() || sAnswer.isEmpty() || msisdn.isEmpty())
			{
				throw new Exception(CONFIG.getFillInAllData(session));
			}
			else
			{
				if (!captcha.isCorrect(captchaAnswer))
				{
					throw new Exception(CONFIG.getInvalidCaptcha(session));
				}
				String password = MasaryManager.getInstance().resetPasword(msisdn, sQuestion, sAnswer);
				if (password.equals("-1"))
				{
					throw new Exception(CONFIG.getInvalidMobileNumber(session));
				}
				if (password.equals("-2"))
				{
					throw new Exception(CONFIG.getInvalidQuestionOrAnswer(session));
				}
				if (password.equals("error"))
				{
					throw new Exception(CONFIG.getInternalError(session));
				}
				MasaryManager.getInstance().sendSMS(msisdn,
						"Your new password is " + password + " please login and change it ");
				nextJSP = CONFIG.PAGE_LOGIN_RESET;
			}
		}
		catch (Exception ex)
		{
			MasaryManager.logger.error(ex.getMessage(), ex);
			session.setAttribute("ErrorMessage", ex.getMessage());
			nextJSP = CONFIG.PAGE_RESET_PASSWORD;
		}

		return nextJSP;
	}
}
