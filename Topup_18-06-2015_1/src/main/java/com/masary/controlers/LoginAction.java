package com.masary.controlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.dto.AuthenticationResponse;
import com.masary.database.dto.LoginDto;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.LoginActionClient;
import com.masary.integration.dto.LoginRequestDTO;
import com.masary.utils.MasaryEncryption;
import com.masary.utils.SystemSettingsUtil;

import sun.misc.BASE64Encoder;

public class LoginAction {
    	
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
		String nextJSP = "";
		String loginInfoPattern = "@@ip_address=%s@@machine_id=%s@@imsi=%s@@device_type=%s@@sw_version=%s@@browser=%s@@platform=%s@@activate_code=%s@@";

		String username = request.getParameter(CONFIG.PARAM_USERNAME);
		String password = request.getParameter(CONFIG.PARAM_PASSWORD2);
		String appRoot =  CONFIG.APP_ROOT;

		HttpSession session = request.getSession();

		// Proxy Service Authorizatioin Token Header Parameter
		String token = username + ":" + password;
		// Java 8 Base64 encoder
		// String encoded =
		// Base64.getEncoder().encodeToString(token.getBytes());
		// Java 6 BASE64Encoder
		String encoded = new BASE64Encoder().encode(token.getBytes());

		session.setAttribute("Token", encoded);
		String action = request.getParameter(CONFIG.PARAM_ACTION);

		String activationCode = getActivationCode(request);
		String platform = "WEB";
		String loginInfo = "";

		String preLog = "userId=[" + username + "], ";

		if (MasaryManager.getInstance().isTheRequestFromMobile(request.getHeader("User-Agent"))) {
			request.getSession().setAttribute(CONFIG.PARAM_FROM_MOBILE, CONFIG.PARAM_FROM_MOBILE);
			platform = "WAB";
		} // end if

		String lang = "";
		if (session.getAttribute(CONFIG.lang) == null) {
			lang = "ar";
			session.setAttribute(CONFIG.lang, "ar");
		} else {
			lang = session.getAttribute(CONFIG.lang).toString();
		}

		String browser = request.getHeader("User-Agent");
		String ipAddress = request.getRemoteAddr();
		session.setAttribute("ip", ipAddress);
		String machineId = "";

		String imsi = "";
		String deviceType = "";
		String softwareVersion = getSoftwareVersion();
		String realIpAddress = request.getHeader("X-FORWARDED-FOR");
		if (realIpAddress == null) {
			realIpAddress = request.getRemoteAddr();
			MasaryManager.logger.info(preLog + "the real IP behind proxies: " + realIpAddress);
		} // end if

		loginInfo = String.format(loginInfoPattern, ipAddress, machineId, imsi, deviceType, softwareVersion, browser,
				platform, activationCode);

		MasaryManager.logger.info(preLog + "info: " + loginInfo);

		// loginInfo_in Example:
		// @@ip_address=@@machine_id=@@imsi=@@device_type=@@sw_version=@@browser=@@platform=@@
		
        String loginAuthentication = SystemSettingsUtil.getInstance().loadProperty("authentication.service.migration.status");
        LoginDto login = new LoginDto();

		if (loginAuthentication.trim().equalsIgnoreCase("on")) {
		LoginRequestDTO loginDto = new LoginRequestDTO();
		loginDto.setAppRoot(appRoot);
		loginDto.setCustomerID(username);
		loginDto.setLang(lang);
		loginDto.setLoginInfo(loginInfo);
		loginDto.setPassword(password);
		LoginActionClient loginActionClient = new LoginActionClient();
		
			 login = loginActionClient.customerLogin(loginDto, lang, encoded);
			MasaryManager.logger.info(preLog + " Data returned from backend :" + login);
		}
            else {
			 login = MasaryManager.getInstance().customerLogin(username, password, loginInfo);
			 MasaryManager.logger.info(preLog + " Data returned from DB " + login.getResponse());
			}

		AuthenticationResponse authenticationResponse = login.getResponse();

		session.setAttribute(CONFIG.PARAM_ROLE, null);

		if (authenticationResponse.getRole().equals("-1")) {
			session.setAttribute("ErrorMessage", "Admin Authentication Error, Login again");
			nextJSP = CONFIG.PAGE_ERRPR;
		} else if (authenticationResponse.getRole().equals("-5")) {

			session.setAttribute("ErrorMessage",
					"لقد قمت بالدخول على حسابك من جهاز آخر و مازلت موجود بالحساب ، برجاء الخروج من حسابك على الجهاز الآخر لتتمكن من الدخول من هذا الجهاز");

			nextJSP = CONFIG.PAGE_ERRPR;
		} else if (authenticationResponse.getRole().equals("-10")) {

			session.setAttribute("ErrorMessage", "كود التفعيل غير صحيح ");

			nextJSP = CONFIG.PAGE_Activation_Code;
		} else if (authenticationResponse.getRole().equals("-30")) {

			session.setAttribute("ErrorMessage", CONFIG.getCUSTOMER_ACCOUNT_SUSPENDED_10_MIN(session));

			nextJSP = CONFIG.PAGE_ERRPR;
		} else if (authenticationResponse.getRole().equals("-60")) {

			session.setAttribute("ErrorMessage", CONFIG.getCUSTOMER_ACCOUNT_SUSPENDED_15_MIN(session));

			nextJSP = CONFIG.PAGE_ERRPR;
		} else if (authenticationResponse.getRole().equals("-90")) {

			session.setAttribute("ErrorMessage", CONFIG.getCUSTOMER_ACCOUNT_SUSPENDED(session));

			nextJSP = CONFIG.PAGE_ERRPR;
		} else if (authenticationResponse.getRole().equals("-50")) {
			session.setAttribute("ErrorMessage", "Admin Authentication Error, Login again");

			nextJSP = CONFIG.PAGE_ERRPR;
		} else if (authenticationResponse.getRole().equals("-2")) {
			session.setAttribute("ErrorMessage", CONFIG.getAccountNotAcctiveMessage(session));
			nextJSP = CONFIG.PAGE_ERRPR;
		} else {
			nextJSP = authenticationResponse.getRole() + ".jsp";
			session = request.getSession();
			if (session.getAttribute(CONFIG.lang) == null) {
				session.setAttribute(CONFIG.lang, "ar");
			}

			String encryptedId = encrypt(authenticationResponse.getId());
			String encryptedPass = encrypt(password);

			session.setAttribute("Login", login);
			session.setAttribute(CONFIG.PARAM_ENC_ID, encryptedId);
			session.setAttribute(CONFIG.PARAM_ENC_PASS, encryptedPass);
			session.setAttribute(CONFIG.PARAM_ROLE, authenticationResponse.getRole());
			session.setAttribute(CONFIG.PARAM_PIN, authenticationResponse.getId());
			session.setAttribute(CONFIG.PARAM_EMPLOYEE_ID, authenticationResponse.getEmpID());
			session.setAttribute(CONFIG.PARAM_SSO, login.getSSo());

			if (CONFIG.PARAM_FROM_MOBILE.equals(session.getAttribute(CONFIG.PARAM_FROM_MOBILE))) {
				nextJSP = CONFIG.PAGE_MAIN_MOBILE;
			}
		}
		if (nextJSP == null) {
			nextJSP = CONFIG.PAGE_LOGIN;
		}

		MasaryManager.logger.info(preLog + "action=[" + action + "], role=[" + authenticationResponse.getRole()
				+ "], IP=[" + request.getRemoteAddr() + "], Employee=[" + authenticationResponse.getEmpID() + "]");

		return nextJSP;
	
	}

	private String encrypt(String value) {
		String result = "";
		try {
			result = MasaryEncryption.encrypt(value);
		} catch (Exception ex) {
			MasaryManager.logger.error("Error while encrypting user ID and password.. " + ex.getMessage(), ex);
		}

		return result;
	}

	private String getActivationCode(HttpServletRequest request) {
		String activationCode = request.getParameter(CONFIG.PARAM_Activation_Code);

		if (activationCode == null) {
			activationCode = "";
		}

		return activationCode;
	}

	private String getSoftwareVersion() {
		String softwareVersion = CONFIG.APP_ROOT;

		if (softwareVersion == null) {
			softwareVersion = "";
		}

		return softwareVersion;
	}
}
