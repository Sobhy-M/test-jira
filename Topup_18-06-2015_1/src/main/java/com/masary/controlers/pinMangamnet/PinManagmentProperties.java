package com.masary.controlers.pinMangamnet;

import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;

public class PinManagmentProperties {

	public static final String CREATE_PIN_JSP = "/service/pinManagment/addPinHome.jsp";
	public static final String PIN_CREATED_JSP = "/service/pinManagment/AddPinConfirmation.jsp";
	public static final String MANAGE_PIN_STATUS = "/service/pinManagment/ManageStatusHome.jsp";
	public static final String MANAGE_PIN_CONFIRMATION = "/service/pinManagment/managePinConfirmation.jsp";
	public static final String PIN_CHANGED_JSP = "/service/pinManagment/ManagePinDone.jsp";

	private static final String insertPinCodeAR = "إدخل كود إغلاق الرصيد";
	private static final String insertPinCodeEn = "Enter Pin Code";

	private static final String manageYourBalanceAr = "تحكم في رصيدك";
	private static final String manageYourBalanceEn = "Manage Your Balance";

	private static final String pinCodeAlreadyCreatedAr = "كود إغلاق الرصيد مفعل لهذه المحفظه  في حال وجود اي مشكله برجاء مراجعة خدمة العملاء عن طريق طلب المساعده او طلب رقم 16994";
	private static final String pinCodeAlreadyCreatedEn = "Pin Code already activiated if there is any issue call masary support 16994";

	private static final String insertPinCodeConfrimationAR = "إعادة إدخال كود إغلاق الرصيد";
	private static final String insertPinCodeConfrimationEn = "ReEnter Pin Code";

	private static final String confirmAr = "تأكيد";
	private static final String confirmEn = "Confirm";

	private static final String okAr = "تأكيد";
	private static final String okEn = "Confirm";

	private static final String pinAndConfirmationNotEqualAr = "كلمة السر غير متطابقة";
	private static final String pinAndConfirmationNotEqualEn = "Pin Code and confirmation not equal";

	private static final String cancelAr = "رجوع";
	private static final String cancelEn = "Back";

	private static final String pinAddedMessageAr = "تم تفعيل الكود هذا الرقم سري للغابة رجاء عدم مشاركته مع اي شخص حتى موظفي شركة مصاري";
	private static final String pinAddedMessageEn = "Pin Code Activated this is very secret code kindly please don't share it with any one";

	private static final String createPinHintAr = "يجب ان يكون طول كود الإغلاق 4 أرقام غيرمتسلسله وغيرمتكرره ولا يمكن استخدام كلمة السر ككود للإغلاق";
	private static final String createPinHintEn = "Pin code length 4 digits without sequence ";

	public static final String errorCode_16_Ar = "الحساب غير موجود";
	public static final String errorCode_16_En = "Account Not Found";

	public static final String errorCode_41002_Ar = "يجب ان يكون طول كود الإغلاق 4 أرقام غيرمتسلسله وغيرمتكرره ولا يمكن استخدام كلمة السر ككود للإغلاق";
	public static final String errorCode_41002_En = "Pin code length 4 digits without sequence ";

	public static final String errorCode_41003_Ar = "كود إغلاق الرصيد مفعل لهذه المحفظه  في حال وجود اي مشكله برجاء مراجعة خدمة العملاء عن طريق طلب المساعده او طلب رقم 16994";
	public static final String errorCode_41003_En = "Pin Code already activiated if there is any issue call masary support 16994";

	public static final String errorCode_41004_Ar = "لم يتم إنشاء كود إدارة الرصيد";
	public static final String errorCode_41004_En = "Pin not created";

	public static final String errorCode_41005_Ar = "لم يتم إنشاء كود إدارة الرصيد";
	public static final String errorCode_41005_En = "Pin not created";

	public static final String errorCode_41006_Ar = "غير مسموح لهذه المحفظة إنشاء كود إدارة الرصيد";
	public static final String errorCode_41006_En = "can't create pin to this wallet";

	public static final String errorCode_41007_Ar = "برجاء التأكد من ادخال الكود الصحيح";
	public static final String errorCode_41007_En = "Please Enter right pin";

	public static final String errorCode_41008_Ar = "برجاء اختيار كلمة سر لا تحتوي على ارقام متكررة او متتابعة";
	public static final String errorCode_41008_En = "Can't create sequance pin";

	private static final String sureToLockBalanceAr = "هل تؤكد إغلاق  الرصيد ؟";
	private static final String sureToLockBalanceEn = "ِAre you sure to lock Balance?";

	private static final String sureToUNLockBalanceAr = "هل تؤكد إعادة فتح الرصيد ؟";
	private static final String sureToUNLockBalanceEn = "Are you sure to reopen balance?";

	private static final String insertTempPinAr = "كود الإغلاق المؤقت للرصيد";
	private static final String insertTempPinEn = "Temp Pin";

	private static final String lockPinDoneAr = "تم عمل إغلاق للرصيد";
	private static final String lockPinDoneEn = "Lock Balance Done";

	private static final String unlockPinDoneAr = "يمكنك أستخدام رصيدك الأن";
	private static final String unlockPinDoneEn = "unLock Balance Done";

	private static final String agreeAR = "موافق";
	private static final String agreeEn = "I Agree";

	public static String getPleaseInsertPin(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return errorCode_41007_Ar;
		}
		return errorCode_41007_En;
	}
	
	public static String getAgree(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return agreeAR;
		}
		return agreeEn;
	}

	public static String getLockPinDone(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return lockPinDoneAr;
		}
		return lockPinDoneEn;
	}

	public static String getUnLockPinDone(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return unlockPinDoneAr;
		}
		return unlockPinDoneEn;
	}

	public static String getTempPin(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return insertTempPinAr;
		}
		return insertTempPinEn;
	}

	public static String getSureToLockBalance(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return sureToLockBalanceAr;
		}
		return sureToLockBalanceEn;
	}

	public static String getSureToUNLockBalance(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return sureToUNLockBalanceAr;
		}
		return sureToUNLockBalanceEn;
	}

	public static String getPinCodeNotCreated(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return errorCode_41004_Ar;
		}
		return errorCode_41004_En;
	}

	public static String getPinAndConfirmationNotEqual(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return pinAndConfirmationNotEqualAr;
		}
		return pinAndConfirmationNotEqualEn;
	}

	public static String getCreatePinHint(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return createPinHintAr;
		}
		return createPinHintEn;
	}

	public static String getPinCodeAlreadyCreated(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return pinCodeAlreadyCreatedAr;
		}
		return pinCodeAlreadyCreatedEn;
	}

	public static String getPinAddedMessage(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return pinAddedMessageAr;
		}
		return pinAddedMessageEn;
	}

	public static String getOk(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return okAr;
		}
		return okEn;
	}

	public static String getCancel(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return cancelAr;
		}
		return cancelEn;
	}

	public static String getConfirm(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return confirmAr;
		}
		return confirmEn;
	}

	public static String getInsertPinCode(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return insertPinCodeAR;
		}
		return insertPinCodeEn;
	}

	public static String getInsertPinCodeConfirmation(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return insertPinCodeConfrimationAR;
		}
		return insertPinCodeConfrimationEn;
	}

	public static String getManageYourBalance(HttpSession session) {
		if (session == null || session.getAttribute(CONFIG.lang) == null
				|| session.getAttribute(CONFIG.lang).equals("ar")) {
			return manageYourBalanceAr;
		}
		return manageYourBalanceEn;
	}

}
