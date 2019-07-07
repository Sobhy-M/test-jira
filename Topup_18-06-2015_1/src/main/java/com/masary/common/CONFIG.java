package com.masary.common;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 * Holds Constants and language variables
 *
 * @author Melad
 */
public class CONFIG {

    // <editor-fold defaultstate="collapsed" desc="Common Topup Abdelsabor">
    public static final String topupTransactionErrorAr = "خطأ اثناء تنفيذ العملية برجاء إعادة المحاولة في وقت لاحق ";
    public static final String topupTransactionErrorEn = "Error During Proccessing Transaction Try Again Later";

    public static final String canntChareToTheSameNumberInSixMinsAr = "29 غير مسموح باجراء عمليتين لنفس الرقم فى زمن اقل من 6 دقائق";
    public static final String canntChareToTheSameNumberInSixMinsEn = "Cann't Charge to This Amount into 6 Minutes";

    public static final String connectionRefusedAr = " خطأ اثناء الاتصال بمقدم الخدمة برجاء إعادة المحاولة في وقت لاحق";
    public static final String connectionRefusedEn = "Connection Refused Try Again Later";

    public static final String cantChargeToThisAccountAr = "هذا الرقم غير صحيح، لا يمكن الشحن لهذا الرقم ";
    public static final String cantChargeToThisAccountEn = "Topup Operator Masary Account Related Issue Exception";

    public static final String cantChargeToTheSameNumberAr = "برجاء إعادة المحاولة في وقت لاحق";
    public static final String cantChargeToTheSameNumberEn = "Topup Operator MsisdnJustRechargedException";

    public static final String errorDuringCallingOperator_22Ar = "خطأ اثناء تنفيذ العملية، برجاء المحاولة في وقت لاحق";
    public static final String errorDuringCallingOperator_22En = "Topup Operator Failed Exception";

    public static final String errorDuringCallingOperatorAr = "خطأ اثناء الاتصال بمقدم الخدمة، برجاء إعادة المحاولة في وقت لاحق";
    public static final String errorDuringCallingOperatorEn = "Topup Operators Calling Parent Exception";

    public static final String noTopupDenominationAmounyAr = "لا يوجد شحن بهذه القيمة";
    public static final String noTopupDenominationAmounyEn = "No Topup AmountException";

    public static final String serviceIsInactiveMessageِAr = "يرجى العلم ان هذه الخدمة غير متاحة الآن، برجاء إعادة المحاولة في وقت لاحق ";
    public static final String serviceIsInactiveMessageEn = "Service Inactive Try Again Later";

    public static final String serviceIsInactiveForThisAccountMessageِAr = "هذه الخدمة غير متاحة لهذه المحفظة برجاء المتابعة مع خدمة عملاء مصاري 16994 ";
    public static final String serviceIsInactiveForThisAccountMessageِEn = "Service Inactive For This Account Try Again Later";

    private static final String commonTopupErrorDuringTransactionAr = "حدث خطأ أثناء العملية رجاء التأكيد من رقم الموبيل او قيمة الشحن";
    private static final String commonTopupErrorDuringTransactionEn = "Error During Transaction";

    private static final String pleaseWaitAr = "برجاء الانتظار";
    private static final String pleaseWaitEn = "Please Wait";

    private static final String transactionReportAr = "تقرير العملية";
    private static final String transactionReportEn = "Transaction Report";
    
    public static final String REQUEST_PARAMETERS = "REQUEST_PARAMETERS";

    public static String getCommonTopupErrorDuringTransaction(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null
                || session.getAttribute(CONFIG.lang).equals("ar")) {
            return commonTopupErrorDuringTransactionAr;
        }
        return commonTopupErrorDuringTransactionEn;
    }

    public static String getPleaseWait(String lang) {
        if (lang == null || lang.equals("ar")) {
            return pleaseWaitAr;
        }
        return pleaseWaitEn;
    }

    public static String getTransactionReport(String lang) {
        if (lang == null || lang.equals("ar")) {
            return transactionReportAr;
        }
        return transactionReportEn;
    }

    // </editor-fold>
	public static final String balanceLockedErrorMessageAr = "العملية لم تكتمل ، برجاء اعادة فتح الرصيد قبل محاولة القيام باي عملية العملية";
    public static final String balanceLockedErrorMessageEn = "Transaction not complete balance is locked";
    
    public static final String errorVoucherTransactionEn = "You cann't send voucher sms to this number please retry after 24 hours";
    public static final String errorVoucherTransactionAR = "لا يمكنك إرسال رسالة للرقم في الوقت الحالي برجاء المحاولة بعد 24 ساعة";
    public static final String errorVoucherLimitAR = "لقد تجاوزت عدد الكروت المسموح بها";
    public static final String errorVoucherLimitEn = "You have exceeded the number of cards allowed";

    // <editor-fold defaultstate="collapsed" desc="Universities Abdelsabor">
    public static final String UniversitiesInqueryPage = "/service/Universities/UniversitiesHome.jsp";
    public static final String UniversitiesConfirmation = "/service/Universities/UniversitiesConfirmation.jsp";
    public static final String UniversitiesConfirmation2 = "/service/Universities/UniversitiesConfirmation2.jsp";
    public static final String UniversitiesPaymentPage = "/service/Universities/UniversitiesPayment.jsp";

    private static final String UniServiceAr = "خدمة تحصيل المصروفات الدراسية - الجامعات المصرية";
    private static final String UniServiceEn = "Educational Fees Procurment Service - Egyption Universites";

    private static final String studentCodeAr = "كود الطالب";
    private static final String studentCodeEn = "Student Code";

    private static final String studetNIdAr = "الرقم القومي";
    private static final String studetNIdEn = "Student National ID";

    private static final String studentCodeTitleAr = "من فضلك أدخل الكود الخاص بالطالب";
    private static final String studentCodeTitleEn = "Please Enter Student Code";

    private static final String studetNIdTitleAr = "من فضلك أدخل الرقم القومي الخاص بالطالب";
    private static final String studetNIdTitleEn = "Please Enter Student National ID";

    private static final String SouthVallyUniAr = "جامعة جنوب الوادي";
    private static final String SouthVallyUniEn = "South Vally University";

    private static final String CairoUniAr = "جامعة القاهرة";
    private static final String CairoUniEn = "Cairo University";

    private static final String FayoumUniAr = "جامعة الفيوم";
    private static final String FayoumUniEn = "El-Fayoum University";

    private static final String AinShamsUniAr = "جامعة عين شمس ";
    private static final String AinShamsUniEn = "Ain Shams University";

    private static final String SuezUniAr = "جامعة السويس";
    private static final String SuezUniEn = "Suez University";

    private static final String ElmansoraUniAr = "جامعة المنصورة";
    private static final String ElmansoraUniEn = "El-Mansora University";

    private static final String BeniSuefUniAr = "جامعة بني سويف";
    private static final String BeniSuefUniEn = "Beni Suef University";

    private static final String BeniSuefUniMscAr = "جامعة بني سويف - دراسات عليا";
    private static final String BeniSuefUniMscEn = "Beni Suef University - Post Graduate Studies";

    private static final String suezCanalUniAr = "جامعة قناة السويس";
    private static final String suezCanalUniEn = "Suez Canal University ";

    private static final String FEAUAr = "كلية الهندسة جامعة الإسكندرية";
    private static final String FEAUEn = "Faculty of Engineering Alexandria University";

    private static final String STUDENTNAMEar = "اسم الطالب";
    private static final String STUDENTNAMEen = "Student name";

    private final static String UNINamear = "اسم الجامعه ";
    private final static String UNINameen = "University name";

    private final static String NeededStudnetAmountAr = "المصاريف المطلوبة";
    private final static String NeededStudnetAmountEn = "Requried Amount";

    public static String getFEAU(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FEAUAr;
        }
        return FEAUEn;
    }

    public static String getSuezCanalUni(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return suezCanalUniAr;
        }
        return suezCanalUniEn;
    }

    public static String getBeniSuefUniMsc(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BeniSuefUniMscAr;
        }
        return BeniSuefUniMscEn;
    }

    public static String getUniService(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return UniServiceAr;
        }
        return UniServiceEn;
    }

    public static String getStudentCode(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return studentCodeAr;
        }
        return studentCodeEn;
    }

    public static String getStudetNId(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return studetNIdAr;
        }
        return studetNIdEn;
    }

    public static String getStudentCodeTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return studentCodeTitleAr;
        }
        return studentCodeTitleEn;
    }

    public static String getStudetNIdTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return studetNIdTitleAr;
        }
        return studetNIdTitleEn;
    }

    public static String getSouthVallyUni(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SouthVallyUniAr;
        }
        return SouthVallyUniEn;
    }

    public static String getCairoUni(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CairoUniAr;
        }
        return CairoUniEn;
    }

    public static String getFayoumUni(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FayoumUniAr;
        }
        return FayoumUniEn;
    }

    public static String getAinShamsUni(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AinShamsUniAr;
        }
        return AinShamsUniEn;
    }

    public static String getSuezUni(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SuezUniAr;
        }
        return SuezUniEn;
    }

    public static String getElmansoraUni(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ElmansoraUniAr;
        }
        return ElmansoraUniEn;
    }

    public static String getBeniSuefUni(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BeniSuefUniAr;
        }
        return BeniSuefUniEn;
    }

    public static String getStudentName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return STUDENTNAMEar;
        }
        return STUDENTNAMEen;

    }

    public static String getUNIName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return UNINamear;
        }
        return UNINameen;

    }

    public static String getNeededStudnetAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NeededStudnetAmountAr;
        }
        return NeededStudnetAmountEn;

    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Orange Dsl Abdelsabor">
    public static final String OrangeDslInqueryPage = "/service/OrangeDslBills/OrangeDsl_inquiry.jsp";
    public static final String OrangeDslInqueryResultPage = "/service/OrangeDslBills/OrangeDsl_info.jsp";
    public static final String OrangeDslConfirmationPage = "/service/OrangeDslBills/OrangeDslReciept.jsp";
    public static final String OrangeDslPaymentPage = "/service/OrangeDslBills/OrangeDsll_payment.jsp";
    public static final String BeatElzakahInqueryPage = "/service/BeatElzakah/BeatElzakal_inquiry.jsp";
    public static final String BeatElzakahInqueryResultPage = "/service/BeatElzakah/BeatElzakal_info.jsp";
    public static final String BeatElzakahConfirmationPage = "/service/BeatElzakah/ELZakaHome.jsp";
    public static final String BeatElzakahPaymentPage = "/service/BeatElzakah/BeatElzakal_payment.jsp";
    private static final String minPaymentAmountAr = "أقل قيمة دفع مقبولة :";
    private static final String minPaymentAmountEn = "Minimum Acceptable Payment Amount :";

    public static String GetMinPaymentAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return minPaymentAmountAr;
        }
        return minPaymentAmountEn;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Elzaka Home Abdelsabor">
    private static final String ElzakaHomePhonrNoTitleAr = "من فضلك أدخل رقم موبيل صحيح";
    private static final String ElzakaHomePhonrNoTitleEn = "Please Enter Valid Phone Number";

    public static String GetElzakaHomePhoneNoTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ElzakaHomePhonrNoTitleAr;
        }
        return ElzakaHomePhonrNoTitleEn;
    }

    public static final String CashatAr = "هذا الحساب موقوف حاليا";
    public static final String CashatEn = "Account is inactive";

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="TA7YA MASR Abdelsabor">
    //PayLongLiveEgypt
    public static final String ACTION_LONG_LIFE_EGYPT = "ACTION_LONG_LIFE_EGYPT";
    public static final String ACTION_LONG_LIFE_EGYPT_Confirmation = "ACTION_LONG_LIFE_EGYPT_CONFIRMATION";
    public static final String ACTION_LONG_LIFE_EGYPT_PAY = "ACTION_LONG_LIFE_EGYPT_PAY";

    public static final String LongLifeEgyptPage = "/service/LongLiveEgypt/LongLiveEgyptDonation.jsp";
    public static final String LongLifeEgyptConfirmationPage = "/service/LongLiveEgypt/LongLifeEgyptConfirm.jsp";
    public static final String LongLifeEgyptPAYPage = "/service/LongLiveEgypt/PayLongLifeEgypt.jsp";

    private static final String LongLifeEgyptProjectAr = "خدمة التبرع لمشروع تحيا مصر";
    private static final String LongLifeEgyptProjectEn = "Donate to Long live Egypt Project Service";
    private static final String VirusCProjectAr = "مشروع فيروس سي";
    private static final String VirusCProjectEn = "Virus C Project";
    private static final String DevelopmentOfSlumsProjectAR = "مشروع تطوير العشوائيات";
    private static final String DevelopmentOfSlumsProjectEn = "Development of Slums Project";
    private static final String RepayDebtorsDebtProjectAr = "مشروع تسديد ديون الغاغرمين و الغارمات";
    private static final String RepayDebtorsDebtProjectEn = "Repay Debtors Debt Project";
    private static final String VirusCTreatmentProjectAr = "مشروع علاج فيروس سي";
    private static final String VirusCTreatmentProjectEn = "Virus C Project Treatment";
    private static final String DonornameAr = "إسم المتبرع ( إن وجد )";
    private static final String DonornameEn = "Donator name (If exists)";
    private static final String DonorPhoneAr = "رقم موبايل المتبرع";
    private static final String DonorPhoneEn = "Donator Phone Number (If exists)";
    private static final String DonationValueAr = "قيمة التبرع المطلوبة بالجنيه المصرى";
    private static final String DonationValueEn = "Donation Value in POUNDS";

    private static final String DonationValueConAr = "تأكيد قيمة التبرع";
    private static final String DonationValueConEn = "Donation Value Confirmation";

    private static final String WillDonateToAr = "سوف يتم التبرع الى";
    private static final String WilDonateToEn = "It will be donated to ";
    private static final String TotalAmountPayableAr = "إجمالى المبلغ المستحق الدفع بالجنيه";
    private static final String TotalAmountPayableEn = "Total amount payable in POUNDS";
    private static final String ThetotalamountpaidAr = "اجمالي المبلغ المدفوع بالجنيه";
    private static final String ThetotalamountpaidEn = "Total amount paid in pounds";
    private static final String WillBedeductedAr = "سيتم خصم";
    private static final String WillBedeductedEn = "Will be deducted";
    private static final String FromTheBalanceOfYouWalletAr = "من رصيد محفظتك";
    private static final String FromTheBalanceOfYouWalletEn = "From the balance of Your Wallet";
    private static final String TheDonationValueAr = "قيمة التبرع بالجنية المصري";
    private static final String TheDonationValueEn = "Donation Value In Pounds";
    private static final String TheDonationAmountInPoundsAr = "مبلغ التبرع بالجنيه المصرى";
    private static final String TheDonationAmountInPoundsEn = "The Donation Amount Iin Pounds";
    private static final String TwoPoundsAr = "2 جنية";
    private static final String TwoPoundsEn = "2 Pounds";
    private static final String OnePoundAr = "1 جنية";
    private static final String OnePoundEn = "1 Pound";
    private static final String ServiceStateAr = "حالة العملية";
    private static final String ServiceStateEn = "حالة العملية";
    private static final String SuccessfulAr = "ناجحة";
    private static final String SuccessfulEn = "Successful";
    private static final String ServiceDateAr = "تاريخ العملية";
    private static final String ServiceDateEn = "Service Dateٍ";
    private static final String ServiceTimeAr = "توقيت العملية";
    private static final String ServiceTimeEn = "Service Time";
    private static final String DonationValueTitleAr = "من فضلك أدخل قيمة تبرع أكبر من أو تساوي  10 جنيهات و أصغر من أو تساوي 500 جنيها";
    private static final String DonationValueTitleEn = "Please Enter Donation Value Bigger than or equal 10 L.E and less than or Equal 500 L.E";
    private static final String DonatorPhoneTitleAr = "من فضلك أدخل رقم هاتفك";
    private static final String DonatorPhoneTitleEn = "Please Enter You Phone Number";
    private static final String AboutDonationAmountAr = "قيمة التبرع يجب أن تكون أكبر من أو تساوي 10 جنيهات و أصغر من أو تساوي 500 جنيها";
    private static final String AboutDonationAmountEn = "Donation Amount must be bigger than or equal 10 L.E and less than or equal 500 L.E ";
    private static final String OKAr = "تــم";
    private static final String OKEn = "OK";
    private static final String VirusCAr = " فيروس سي";
    private static final String VirusCEn = "Virus C ";
    private static final String DevelopmentOfSlumsAR = " تطوير العشوائيات";
    private static final String DevelopmentOfSlumsEn = "Development of Slums ";
    private static final String RepayDebtorsDebtAr = " تسديد ديون الغاغرمين و الغارمات";
    private static final String RepayDebtorsDebtEn = "Repay Debtors Debt ";
    private static final String VirusCTreatmentAr = " علاج فيروس سي";
    private static final String VirusCTreatmentEn = "Virus C Project Treatment";

    //mersal service configuration
    public static final String ACTION_MERSAL = "ACTION_MERSAL";
    public static final String ACTION_MERSAL_CONFIRMATION = "ACTION_MERSAL_CONFIRMATION";
    public static final String ACTION_MERSAL_PAY = "ACTION_MERSAL_PAY";
    public static final String MersalPage = "/service/mersal/MersalService.jsp";
    public static final String MersalConfirmationPage = "/service/mersal/MersalConfirmation.jsp";
    public static final String MersalPayPage = "/service/mersal/MersalPay.jsp";

    public static final String MERSAL_SERVICE = "جمعية مرسال الخيريه";
    public static final String MERSAL_CANCER_EN = "Mersal Cancer Center";
    public static final String MERSAL_CANCER_AR = "مركز مرسال للاورام";
    public static final String MERSAL_THERAPY_EN = "Monthly Therapy";
    public static final String MERSAL_THERAPY_AR = "علاج شهري";
    public static final String MERSAL_VOICE_EN = "Voice Story";
    public static final String MERSAL_VOICE_AR = "حكاية صوت";
    public static final String MERSAL_CANCER_SUP_EN = "Support Cancer Patients";
    public static final String MERSAL_CANCER_SUP_AR = "ادعم مرضى الاورام";
    public static final String MERSAL_INISTITUTION_EN = "Mersal Inistitution";
    public static final String MERSAL_INISTITUTION_AR = "مؤسسة مرسال";
    public static final String MERSAL_ZAKAT_FITR_AR = "زكاة  الفطر";
    public static final String MERSAL_ZAKAT_MAL_AR = "زكاة  المال";
    public static final String MERSAL_ZAKAT_FITR_EN = "Zakat al fitr";
    public static final String MERSAL_ZAKAT_MAL_EN = "Zakat al mal";

    //hospital 25 service configuration
    public static final String ACTION_HOSPITAL_25 = "ACTION_HOSPITAL_25";
    public static final String ACTION_HOSPITAL_25_CONFIRMATION = "ACTION_HOSPITAL_25_CONFIRMATION";
    public static final String ACTION_HOSPITAL_25_PAY = "ACTION_HOSPITAL_25_PAY";
    public static final String Hospital25Page = "/service/hospital25/HospitalService.jsp";
    public static final String Hospital25PageConfirmationPage = "/service/hospital25/HospitalConfirmation.jsp";
    public static final String Hospital25PagePayPage = "/service/hospital25/HospitalPay.jsp";

    public static final String HOSPITAL_25_SERVICE = "مؤسسة مستشفى 25 يناير";

    public static final String HOSPITAL_25_DONATION_EN = "General Donation";
    public static final String HOSPITAL_25_DONATION_AR = "تبرع عام للمستشفى";

    public static final String HOSPITAL_25_ZAKAH_EN = "Zakah";
    public static final String HOSPITAL_25_ZAKAH_AR = "زكاه";

    public static final String HOSPITAL_25_SADAKAH_EN = "Sadakat";
    public static final String HOSPITAL_25_SADAKAH_AR = "صدقات";

    public static final String HOSPITAL_25_SEASON1_EN = "Season 1";
    public static final String HOSPITAL_25_SEASON1_AR = "موسمي 1";

    public static final String HOSPITAL_25_SEASON2_EN = "Season 2";
    public static final String HOSPITAL_25_SEASON2_AR = "موسمي 2";

    public static final String HOSPITAL_25_SEASON3_EN = "Season 3";
    public static final String HOSPITAL_25_SEASON3_AR = "موسمي 3";

    public static final String HOSPITAL_25_SEASON4_EN = "Season 4";
    public static final String HOSPITAL_25_SEASON4_AR = "موسمي 4";

    //elber service configuration
    public static final String ACTION_ELBER = "ACTION_ELBER";
    public static final String ACTION_ELBER_CONFIRMATION = "ACTION_ELBER_CONFIRMATION";
    public static final String ACTION_ELBER_PAY = "ACTION_ELBER_PAY";
    public static final String ElBerPage = "/service/elber/ElBerService.jsp";
    public static final String ElBerConfirmationPage = "/service/elber/ElBerConfirmation.jsp";
    public static final String ElBerPayPage = "/service/elber/ElBerPay.jsp";

    public static final String ELBER_SERVICE = "جمعية البر و التقوى الخيريه";

    public static final String ELBER_CHARITY_HOSPITAL_EN = "Hospital Building";
    public static final String ELBER_CHARITY_HOSPITAL_AR = "صدقات - بناء مستشفيات";

    public static final String ELBER_CHARITY_MOSQUE_EN = "Mosque Building";
    public static final String ELBER_CHARITY_MOSQUE_AR = "صدقات - بناء مساجد";

    public static final String ELBER_CHARITY_WELL_EN = "Well Creation";
    public static final String ELBER_CHARITY_WELL_AR = "صدقات - حفر ابار";

    public static final String ELBER_ALMS_HELP_EN = "Poor Help and Support";
    public static final String ELBER_ALMS_HELP_AR = "زكاه - مساعدة الفقراء و الايتام";

    public static final String ELBER_ALMS_THERAPY_EN = "Pateints Help";
    public static final String ELBER_ALMS_THERAPY_AR = "زكاه - معالجة المرضى";

    public static final String ELBER_ALMS_MONEY_EN = "Debt Repayment";
    public static final String ELBER_ALMS_MONEY_AR = "زكاه - سداد الديون";

    public static final String ELBER_ALMS_HOUSE_EN = "Poor House Building";
    public static final String ELBER_ALMS_HOUSE_AR = "زكاه - بناء منازل الفقراء";
    public static final String ELBER_RAMDON_BASKET_EN = "Poor House Building";
    public static final String ELBER_RAMDON_BASKET_AR = "شنطة رمضان  ";
    public static final String AFTAR_SAEAM_EN = "Poor House Building";
    public static final String AFTAR_SAEAM_AR = "وجبة افطار صائم";
    public static final String LHOOM_SADAKAT_EN = "LHOOM SADAKAT";
    public static final String LHOOM_SADAKAT_AR = "لحوم صدقات ";
    //end elber configs

    public static String GetLHoomSadakat(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LHOOM_SADAKAT_AR;
        }
        return LHOOM_SADAKAT_EN;
    }

    public static String GetVirusCTreatment(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VirusCTreatmentAr;
        }
        return VirusCTreatmentEn;
    }

    public static String GetRepayDebtorsDebt(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RepayDebtorsDebtAr;
        }
        return RepayDebtorsDebtEn;
    }

    public static String GetDevelopmentOfSlums(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DevelopmentOfSlumsAR;
        }
        return DevelopmentOfSlumsEn;
    }

    public static String GetVirusC(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VirusCAr;
        }
        return VirusCEn;
    }

    public static String GetOk(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return OKAr;
        }
        return OKEn;
    }

    public static String AboutDonationAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AboutDonationAmountAr;
        }
        return AboutDonationAmountEn;
    }

    public static String DonatorPhoneTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DonatorPhoneTitleAr;
        }
        return DonatorPhoneTitleEn;
    }

    public static String DonationValueTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DonationValueTitleAr;
        }
        return DonationValueTitleEn;
    }

    public static String getServiceTime(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ServiceTimeAr;
        }
        return ServiceTimeEn;
    }

    public static String getServiceDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ServiceDateAr;
        }
        return ServiceDateEn;
    }

    public static String getSuccessful(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SuccessfulAr;
        }
        return SuccessfulEn;
    }

    public static String getServiceState(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ServiceStateAr;
        }
        return ServiceStateEn;
    }

    public static String getTwoPounds(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TwoPoundsAr;
        }
        return TwoPoundsEn;
    }

    public static String getOnePound(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return OnePoundAr;
        }
        return OnePoundEn;
    }

    public static String getTheDonationAmountInPounds(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TheDonationAmountInPoundsAr;
        }
        return TheDonationAmountInPoundsEn;
    }

    public static String getTheDonationValue(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TheDonationValueAr;
        }
        return TheDonationValueEn;
    }

    public static String getFromTheBalanceOfYouWallet(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FromTheBalanceOfYouWalletAr;
        }
        return FromTheBalanceOfYouWalletEn;
    }

    public static String getWillbededucted(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WillBedeductedAr;
        }
        return WillBedeductedEn;
    }

    public static String getThetotalamountpaid(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ThetotalamountpaidAr;
        }
        return ThetotalamountpaidEn;
    }

    public static String getTotalAmountPayable(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TotalAmountPayableAr;
        }
        return TotalAmountPayableEn;
    }

    public static String getWilDonateTo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WillDonateToAr;
        }
        return WilDonateToEn;
    }

    public static String getDonationValue(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DonationValueAr;
        }
        return DonationValueEn;
    }

    public static String getDonationValueCon(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DonationValueConAr;
        }
        return DonationValueConEn;
    }

    public static String getDonorPhone(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DonorPhoneAr;
        }
        return DonorPhoneEn;
    }

    public static String getDonorname(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DonornameAr;
        }
        return DonornameEn;
    }

    public static String getVirusCtreatmentProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VirusCTreatmentProjectAr;
        }
        return VirusCTreatmentProjectEn;
    }

    public static String getRepayDebtorsDebtProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RepayDebtorsDebtProjectAr;
        }
        return RepayDebtorsDebtProjectEn;
    }

    public static String getDevelopmentOfSlumsProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DevelopmentOfSlumsProjectAR;
        }
        return DevelopmentOfSlumsProjectEn;
    }

    public static String getVirusCProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VirusCProjectAr;
        }
        return VirusCProjectEn;
    }

    public static String getLongLifeEgyptProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LongLifeEgyptProjectAr;
        }
        return LongLifeEgyptProjectEn;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="BCFE Abdelsabor">
    public static final String ACTION_BCFE = "ACTION_BCFE_HOME";
    public static final String ACTION_BCFE_Confirmation = "ACTION_BCFE_CONFIRMATION";
    public static final String ACTION_BCFE_PAY = "ACTION_BCFE_PAY";

    public static final String BCFE_Home_Page = "/service/BCFE/BCFEPage.jsp";
    public static final String BCFE_Confirmation_Page = "/service/BCFE/BCFEConfirmation.jsp";
    public static final String BCFE_Pay_Page = "/service/BCFE/BCFEPay.jsp";

    private static final String BcfeDescriptionAr = "خدمة التبرع للمؤسسة المصرية لمكافحة سرطان الثدي";
    private static final String BcFEDescriptionEn = "Donate to Breast Cancer Foundation of Egypt Service";

    private static final String ToElzakahHomeDescriptionAr = " لبيت الزكاة والصدقات المصري";
    private static final String ToElzakahHomeDescriptionEn = "Egyptian House of Zakah and Charity";

    private static final String willDonateToAR = "سوف يتم التبرع";
    private static final String willDonateToEn = "It Will Be Donated";

    private static final String ElzakaHomeDonationValAr = "من فضلك أدخل قيمة التبرع بالجنية";
    private static final String ElzakaHomeDonationValEn = "Please Enter Donation Value With L.E";

    private static final String DontaorPhoneNumberAr = "رقم موبايل المتبرع";
    private static final String DontaorPhoneNumberEn = "Donator Phone Numbber";

    private static final String ToBcfeServiceAr = "للمؤسسة المصرية لمكافحة سرطان الثدي ";
    private static final String ToBcfeServiceEn = "to Breast Cancer Foundation of Egypt Service ";

    public static String getDToBcfeService(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ToBcfeServiceAr;
        }
        return ToBcfeServiceEn;
    }

    public static String getDontaorPhoneNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DontaorPhoneNumberAr;
        }
        return DontaorPhoneNumberEn;
    }

    public static String getElzakaHomeDonationValTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ElzakaHomeDonationValAr;
        }
        return ElzakaHomeDonationValEn;
    }

    public static String getWillDonateTO(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return willDonateToAR;
        }
        return willDonateToEn;
    }

    public static String getToElzakahHome(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ToElzakahHomeDescriptionAr;
        }
        return ToElzakahHomeDescriptionEn;
    }

    public static String getBcfeDescription(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BcfeDescriptionAr;
        }
        return BcFEDescriptionEn;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Elorman Abdelsabor">
    public static final String ACTION_ELORMAN_HOME = "ACTION_ELORMAN_PAGE";
    public static final String ACTION_ELORMAN_Confirmation = "ACTION_ELORMAN_CONFIRMATION";
    public static final String ACTION_ELORMAN_PAY = "ACTION_ELORMAN_PAY";

    /*
     * by Moamen Agent Payment Project 22-8-2016
     */
    public static final String agentPaymentGetInfoPage = "/service/AgentPayment/AgentPaymentGetInfo.jsp";
    public static final String agentPaymentPayConfirmationPage = "/service/AgentPayment/AgentPaymentPayConfirmation.jsp";
    public static final String agentPaymentReceiptPage = "/service/AgentPayment/AgentPaymentReceipt.jsp";
    /* End of  Agent Payment Project 22-8-2016 */
    public static final String ELORMAN_Home_Page = "/service/Elorman/ELORMANPage.jsp";
    public static final String ELORMAN_Confirmation_Page = "/service/Elorman/ELORMANConfirmation.jsp";
    public static final String ELORMAN_Pay_Page = "/service/Elorman/ELORMANPay.jsp";

    private static final String ELORMANDescriptionAr = "خدمة التبرع  لجمعية الأورمان الخيرية";
    private static final String ELORMANDescriptionEn = "Donate To Elorman Charity Service";

    private static final String ELORMANDescription2Ar = "خدمة التبرع لجمعية الأورمان الخيرية - ";
    private static final String ELORMANDescription2En = "Donate To Elorman Charity Service - ";

    private static final String ElormanSocityProjectAr = "تبرع عام لجمعية الأورمان";
    private static final String ElormanSocityProjectEn = "Donation to El-Orman Society";

    private static final String ElormanZakahProjectAr = "تبرع زكاة";
    private static final String ElormanZakahProjectEn = "Zakah Donation";

    private static final String ElormanSadkahProjectAr = "تبرع صدقة";
    private static final String ElormanSadkahProjectEn = "Sadkah Donation";

    private static final String ElormanHospitalProjectAr = "تبرع لمستشفي الأورمان";
    private static final String ElormanHospitalProjectEn = "Elorman hospital Donation";

    private static final String ElormanBuffaloProjectAr = "تبرع جاموسة عشر";
    private static final String ElormanBuffaloProjectEn = "pregnant Buffalo Donation";

    private static final String ElormanSadkatMeatProjectAr = "لحوم صدقات";
    private static final String ElormanSadkatMeatProjectEn = "Sadkah Meat";

    private static final String ToElormanAr = "لجمعية الأورمان الخيرية - ";
    private static final String ToElormanEn = "لجمعية الأورمان الخيرية - ";

    private static final String KindOfDonationAr = "نوع التبرع";
    private static final String KindOfDonationEn = "Kind Of Donation";

    private static final String TheDonationValAr = "قيمة الصك /قيمة التبرع";
    private static final String TheDonationValEn = "The Donation Value";

    private static final String SakImportedMeatAr = "صك لحوم مستوردة";
    private static final String SakImportedMeatEn = "Sak of Imported Meat";

    private static final String SaksmallClavesAr = "صك لحوم بلدي عجول صغيرة";
    private static final String SaksmallClavesEn = "Sak of National Little Calves Meat";

    private static final String SakOldCalvesMeatAr = "صك بلدى كبير 3100 جنيه";
    private static final String SakOldCalvesMeatEn = "Sak of National Old Calves 3100 LE";

    private static final String DonationDateAr = "تاريخ التبرع";
    private static final String DonationDateEn = "Donation Date";

    private static final String DonationTimeAr = "توقيت التبرع";
    private static final String DonationTimeEn = "Donation Time";

    private static final String isDedduedAr = "تم خصم";
    private static final String isDedduedEn = "Has been deducted";

    private static final String _totalAmountAr = "المبلغ الإجمالي";
    private static final String _totalAmountEn = "Total Amount";

    private static final String ElormanDonatorNameAr = "إسم المتبرع";
    private static final String ElormanDonatorNameEn = "Donator Name";

    private static final String BoxRamdan90Ar = "كرتونة رمضان 90 جنيه";
    private static final String BoxRamdan90En = "Box Ramdan 90 LE";

    private static final String BoxRamdan150Ar = "كرتونة رمضان 150 جنيه";
    private static final String BoxRamdan150En = "Box Ramdan 150 LE";

    private static final String BoxRamdan220Ar = "كرتونة رمضان 220 جنيه";
    private static final String BoxRamdan220En = "Box Ramdan 220 LE";

    private static final String sakMostawradSa8er1950Ar = "صك مستورد صغير 1950 جنيه";
    private static final String sakMostawradSa8er1950En = "Sak Mostwarad Sa8er 1950 LE";

    private static final String sakMostawradKaber2550Ar = "صك مستورد كبير 2550 جنيه";
    private static final String sakMostawradKaber2550En = "Sak Mostwarad Kaber 2550 LE";

    private static final String sakBaladyKaber3100Ar = "صك بلدى كبير 3100 جنيه";
    private static final String sakBaladyKaber3100En = "Sak Balady Kaber LE";

    public static String getToElorman(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ToElormanAr;
        }
        return ToElormanEn;
    }

    public static String getElormanDescription(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ELORMANDescriptionAr;
        }
        return ELORMANDescriptionEn;
    }

    public static String getElormanDescription2(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ELORMANDescription2Ar;
        }
        return ELORMANDescription2En;
    }

    public static String getElormanSocityProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ElormanSocityProjectAr;
        }
        return ElormanSocityProjectEn;
    }

    public static String getElormanZakahProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ElormanZakahProjectAr;
        }
        return ElormanZakahProjectEn;
    }

    public static String getElormanSadkahProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ElormanSadkahProjectAr;
        }
        return ElormanSadkahProjectEn;
    }

    public static String getElormanHospitalProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ElormanHospitalProjectAr;
        }
        return ElormanHospitalProjectEn;
    }

    public static String getElormanBuffaloProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ElormanBuffaloProjectAr;
        }
        return ElormanBuffaloProjectEn;
    }

    public static String getElormanSadkahMeatProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ElormanSadkatMeatProjectAr;
        }
        return ElormanSadkatMeatProjectEn;
    }

    public static String getToKindOFDonation(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return KindOfDonationAr;
        }
        return KindOfDonationEn;
    }

    public static String getTheDonationVal(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TheDonationValAr;
        }
        return TheDonationValEn;
    }

    public static String getSakOfImportedMeat(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SakImportedMeatAr;
        }
        return SakImportedMeatEn;
    }

    public static String getBoxRamdan90(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BoxRamdan90Ar;
        }
        return BoxRamdan90En;
    }

    public static String getBoxRamdan150(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BoxRamdan150Ar;
        }
        return BoxRamdan150En;
    }

    public static String getBoxRamdan220(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BoxRamdan220Ar;
        }
        return BoxRamdan220En;
    }

    public static String getSakMostawradSa8er1950(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return sakMostawradSa8er1950Ar;
        }
        return sakMostawradSa8er1950En;
    }

    public static String getSakMostawradKaber2550(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return sakMostawradKaber2550Ar;
        }
        return sakMostawradKaber2550En;
    }

    public static String getSakBaladyKaber3100(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return sakBaladyKaber3100Ar;
        }
        return sakBaladyKaber3100En;
    }

    public static String getSaksmallClaves(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SaksmallClavesAr;
        }
        return SaksmallClavesEn;
    }

    public static String getSakOldCalvesMeat(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SakOldCalvesMeatAr;
        }
        return SakOldCalvesMeatEn;
    }

    public static String _getTotalAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return _totalAmountAr;
        }
        return _totalAmountEn;
    }

    public static String getElormanDonatorName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ElormanDonatorNameAr;
        }
        return ElormanDonatorNameEn;
    }

    public static String getDonationDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DonationDateAr;
        }
        return DonationDateEn;
    }

    public static String getDonationTime(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DonationTimeAr;
        }
        return DonationTimeEn;
    }

    public static String getIsDeddued(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return isDedduedAr;
        }
        return isDedduedEn;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="GIG Project Abdelsabor">
    private static final String OperationTypeAr = "نوع العملية";
    private static final String OperationTypeEN = "Operation Type";
    private static final String OperationStatusAr = "حالة العملية";
    private static final String OperationStatusEN = "Operation Status";
    private static final String InsuredPersonInfoAr = "بيانات الشخص المؤمن عليه";
    private static final String InsuredPersonInfoEn = "The insured person's data";
    private static final String CertificationNoEn = "Certification Number";
    private static final String CertificationNoAR = "رقم الوثيقة";
    private static final String AriaOFCoverEn = "Area Of Cover";
    private static final String AriaOFCoverAR = "منطقة التغطية";
    private static final String PeriodOfInsuranceEn = "Period Of Insurance";
    private static final String PeriodOfInsuranceAR = "فترة التامين";
    private static final String PassportNoEn = "Passport Number and Image";
    private static final String PassportNoAR = "رقم جواز السفر";
    private static final String FirstNameEn = "First Name";
    private static final String FirstNameAR = "الاسم الاول";
    private static final String LastNameEn = " Last Name";
    private static final String LastNameAR = "الاسم الاخير";
    private static final String MiddleNameEn = "Middle Name";
    private static final String MiddleNameAR = "الاسم الاوسط";
    private static final String GenderEn = " Gender";
    private static final String GenderAR = "الجنس";
    private static final String selectGenderEn = "Select Sex";
    private static final String selectGenderAR = "اختر النوع";
    private static final String selectPeriodOFArrEn = " Select Period";
    private static final String selectPeriodOFArrAR = "اختر المدة";
    private static final String selectLocationEn = "Select Location";
    private static final String selectLocationAR = "اختر الاماكن";
    private static final String NewTravelPolisyEn = "New Travel Polisy";
    private static final String NewTravelPolisyAR = "وثيقة تأمين جديدة";
    private static final String NewPolisyEn = "Add new Insurance Polisy";
    private static final String NewPolisyAR = "إضاقة وثيقة جديدة";
    private static final String PassportImageAr = "صورة جواز السفر";
    private static final String PassportImageEN = "Passport Image";
    private static final String PhoneNumberAr = "رقم الهاتف";
    private static final String PhoneNumberEN = "Phone No";
    public static final String ACTION_Make_Insurance = "ACTION_Make_Insurance";
    public static final String ACTION_Make_CONFIRMATION_Insurance = "ACTION_Make_CONFIRMATION_Insurance";
    public static final String ACTION_RESULT_Insurance = "ACTION_RESULT_Insurance";
    public static final String PAGE_GIG = "service/Insurances/NewTravelPolisy.jsp";
    public static final String PAGE_GIG_Confirmation = "service/Insurances/NewTravelPolisyConfirmation.jsp";
    public static final String PAGE_GIG_RESULT = "service/Insurances/TravelPolicyAddingResult.jsp";
    private static final String CertificationTitleAr = "من فضلك إدخل رقم الوثيقة";
    private static final String CertificationTitleEn = "Please Enter Certification Number";
    private static final String AriaOFCoverAr = "من فضلك إختر منطقة التغطية";
    private static final String AriaOFCoverEN = "Please Select Aria of cover";
    private static final String PeriodOfInsuranceTitleAr = "من فضلك إختر فترة التأمين";
    private static final String PeriodOfInsuranceTitleEn = "Please Select Period of Insurance";
    private static final String StartDateTitleAr = "من فضلك إختر تاريخ البداية";
    private static final String StartDateTitleEn = "Please Choose Start Date";
    private static final String AboutStartDateAr = "تاريخ البداية يجب أن يكون اليوم على الأقل";
    private static final String AboutStatrDateEn = "Start Date Must be At least Today";
    private static final String FirstNameTitleAr = "من فضلك أدخل الإسم الأول";
    private static final String FirstNameTitleEn = "Please Enter First Name";
    private static final String MiddleNameTitleAr = "من فضلك أدخل الإسم الأوسط";
    private static final String MiddleNameTitleEn = "Please Enter Middle Name";
    private static final String LastNameTitleAr = "من فضلك أدخل الإسم الأخير";
    private static final String LastNameTitleEn = "Please Enter Last Name";
    private static final String PassportNoTitleAr = "من فضلك أدخل رقم الباسبورت";
    private static final String PassportNoTitleEn = "Please Enter Passport Number";
    private static final String BirthDateTitleAr = "من فضلك إختر تاريخ الميلاد";
    private static final String BirthDateTitleEn = "Please Select Birth Date";
    private static final String AboutBirthDateTitleAr = "تاريخ الميلاد يجب أن يكون أقل من اليوم";
    private static final String AboutBirthDateTitleEn = "Birth Date Must Be less than Today";
    private static final String GenderTitleAr = "من فضلك إختر قيمة";
    private static final String GenderTitleEn = "Please Select Geneder";
    private static final String PhonrNoTitleAr = "من فضلك أدخل رقم الهاتف";
    private static final String PhonrNoTitleEn = "Please Enter Phone Number";
    private static final String AboutNamesAr = "جميع الأسماء يجب أن لا تزيد عن 15 حرف و تحتوي على حروف فقط";
    private static final String AboutNamesEn = "All Names Must not greater than 15 characters and must be numbers and characters only";
    private static final String AboutPhoneNoAr = "رقم الهاتف يجب ان يكون بهذه الصيغة 01xxxxxxxxx";
    private static final String AboutPhoneEn = "phone Number must be in this format 01xxxxxxxxxx";
    private static final String NetAmountAr = "صافي قيمة الوثيقة ";
    private static final String NetAmountEN = "Net Amount";
    private static final String TaxesAr = "الضرائب";
    private static final String TaxesEn = "Taxes";
    private static final String DocumentGrossAmountAr = "قيمة الوثيقة";
    private static final String DocumentGrossAmountEn = "Document Gross Amount";
    private static final String NetGrossamountAr = "الإجمالي";
    private static final String NetGrossamountEn = "Net Gross Amount";
    private static final String ServiceCostAr = "تكلفة الخدمة";
    private static final String ServiceCostEn = "Service Cost";
    private static final String AddressAr = "العنوان";
    private static final String AddressEn = "Address";
    private static final String AddressTitleAr = "من فضلك أدخل عنوانك الحالي";
    private static final String AddressTitleEn = "Please Enter Your Cuurent Address";
    private static final String TheCityAr = "المحافظة";
    private static final String TheCityEn = "City";
    private static final String TheCityTitle = "من فضلك أدخل عنوانك الحالي";
    private static final String TheCityTitleEn = "Please Enter You Current Address";
    /*
     * By keroles to handle orange capping 
     */

    public static final String OrangeCappingExceedLimit = "You have reached the maximum limit for this service";
    public static final String OrangeCappingExceedLimitAr = "لقد وصلت للحد اليومي المسموح به لهذة الخدمة";

    public static String getOperationType(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return OperationTypeAr;
        }
        return OperationTypeAr;
    }

    public static String getOperationStatus(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return OperationStatusAr;
        }
        return OperationStatusEN;
    }

    public static String getTheCityTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TheCityTitle;
        }
        return TheCityTitleEn;
    }

    public static String getTheCity(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TheCityAr;
        }
        return TheCityEn;
    }

    public static String getAddressTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AddressTitleAr;
        }
        return AddressTitleEn;
    }

    public static String getAddress(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AddressAr;
        }
        return AddressEn;
    }

    public static String getServiceCost(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ServiceCostAr;
        }
        return ServiceCostEn;
    }

    public static String getDocumentGrossAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DocumentGrossAmountAr;
        }
        return DocumentGrossAmountEn;
    }

    public static String getGrossamount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NetGrossamountAr;
        }
        return NetGrossamountEn;
    }

    public static String getTaxes(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TaxesAr;
        }
        return TaxesEn;
    }

    public static String getNetAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NetAmountAr;
        }
        return NetAmountEN;
    }

    public static String getAboutPhoneNo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AboutPhoneNoAr;
        }
        return AboutPhoneEn;
    }

    public static String getAboutNames(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AboutNamesAr;
        }
        return AboutNamesEn;
    }

    public static String getPhonrNoTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PhonrNoTitleAr;
        }
        return PhonrNoTitleEn;
    }

    public static String getThePhoneNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PhoneNumberAr;
        }
        return PhoneNumberEN;
    }

    public static String getPassportImage(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PassportImageAr;
        }
        return PassportImageEN;
    }

    public static String getGenderTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return GenderTitleAr;
        }
        return GenderTitleEn;
    }

    public static String getAboutBirthDateTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AboutBirthDateTitleAr;
        }
        return AboutBirthDateTitleEn;
    }

    public static String getBirthDateTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BirthDateTitleAr;
        }
        return BirthDateTitleEn;
    }

    public static String getPassportNoTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PassportNoTitleAr;
        }
        return PassportNoTitleEn;
    }

    public static String getLastNameTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LastNameTitleAr;
        }
        return LastNameTitleEn;
    }

    public static String getMiddleNameTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MiddleNameTitleAr;
        }
        return MiddleNameTitleEn;
    }

    public static String getFirstNameTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FirstNameTitleAr;
        }
        return FirstNameTitleEn;
    }

    public static String getAboutStatrDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AboutStartDateAr;
        }
        return AboutStatrDateEn;
    }

    public static String getStartDateTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return StartDateTitleAr;
        }
        return StartDateTitleEn;
    }

    public static String getPeriodOfInsuranceTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PeriodOfInsuranceTitleAr;
        }
        return PeriodOfInsuranceTitleEn;
    }

    public static String getAriaOFCoverTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AriaOFCoverAr;
        }
        return AriaOFCoverEn;
    }

    public static String getCertificationTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CertificationTitleAr;
        }
        return CertificationTitleEn;
    }

    public static String getNewTravelPolisy(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NewTravelPolisyAR;
        }
        return NewTravelPolisyEn;
    }

    public static String getNewPolisy(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NewPolisyAR;
        }
        return NewPolisyEn;
    }

    public static String getCertificateNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CertificationNoAR;
        }
        return CertificationNoEn;
    }

    public static String getgenderselection(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return selectGenderAR;
        }
        return selectGenderEn;
    }

    public static String getLocationselection(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return selectLocationAR;
        }
        return selectLocationEn;
    }

    public static String getselectionPeriodOFArr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return selectPeriodOFArrAR;
        }
        return selectPeriodOFArrEn;
    }

    public static String getFirstName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FirstNameAR;
        }
        return FirstNameEn;
    }

    public static String getGender(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return GenderAR;
        }
        return GenderEn;
    }

    public static String getMiddleName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MiddleNameAR;
        }
        return MiddleNameEn;
    }

    public static String getLastName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LastNameAR;
        }
        return LastNameEn;
    }

    public static String getPassPortNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PassportNoAR;
        }
        return PassportNoEn;
    }

    public static String getAreaOfCover(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AriaOFCoverAR;
        }
        return AriaOFCoverEn;
    }

    public static String getPeriodOfInsurance(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PeriodOfInsuranceAR;
        }
        return PeriodOfInsuranceEn;
    }

    public static String getInsuredPersonInfo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return InsuredPersonInfoAr;
        }
        return InsuredPersonInfoEn;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="AgentPAyment Mo2men">
    private static final String companiesProceedsAr = "تحصيلات شركات";
    private static final String companiesProceedsEn = "Companies Proceeds";

    private static final String representativeCodeAr = "كود المندوب";
    private static final String representativeCodeEn = "Representative Code";

    private static final String customerCodeAr = "كود العميل";
    private static final String customerCodeEn = "Customer Code";

    private static final String representativeNameAr = "إسم المندوب";
    private static final String representativeNameEn = "Representative Name";

    private static final String CompanyCodeAr = "كود الشركة";
    private static final String CompanyCodeEn = "Company Code";

    private static final String AgentPaymentAmountAr = "المبلغ";
    private static final String AgentPaymentAmountEn = "Amount";

    private static final String AgentPaymentConfirmAmountAr = "تأكيد المبلغ";
    private static final String AgentPaymentConfirmAmountEn = "Amount Confirmation";

    private static final String AgentPaymentInfoAr = "معلومات مطلوبة";
    private static final String AgentPaymentInfoEn = "Required Information";

    private static final String AgentPaymentComputeCommissionAr = "إحسب عمولتك";
    private static final String AgentPaymentComputeCommissionEn = "Compute your commission";

    private static final String AgentPaymentCommissionAr = " عمولتك";
    private static final String AgentPaymentCommissionEn = "your commission";

    private static final String AgentPaymentWillDeductedAr = "سيتم خصم";
    private static final String AgentPaymentWillDeductedEn = "Will be deducted";

    private static final String AgentPaymentCompanyNameAr = "إسم الشركة";
    private static final String AgentPaymentCompanyNameEn = "Company Name";

    private static final String AgentPaymentAmountLEAr = "المبلغ بالجنية";
    private static final String AgentPaymentAmountLEEn = "Amount in L.E";

    private static final String AgentPaymentPinCodeAr = "كود نقطة التحصيل";
    private static final String AgentPaymentPinCodeEn = "Pin Code";

    private static final String AgentPaymentTrxAr = "رقم العملية";
    private static final String AgentPaymentTrxEn = "Transaction Code";

    private static final String AgentPaymentTrxTimeAr = "توقيت العملية";
    private static final String AgentPaymentTrxTimeEn = "Transaction Time";

    private static final String AgentPaymentTrxDateAr = "تاريخ العملية";
    private static final String AgentPaymentTrxDateEn = "Transaction Date";

    public static String getCompaniesProceeds(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return companiesProceedsAr;
        }
        return companiesProceedsEn;
    }

    public static String getRepresentativeCode(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return representativeCodeAr;
        }
        return representativeCodeEn;
    }

    public static String getCustomerCode(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return customerCodeAr;
        }
        return customerCodeEn;
    }

    public static String getCompanyCode(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CompanyCodeAr;
        }
        return CompanyCodeEn;
    }

    public static String getAgentPaymentAmountAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentPaymentAmountAr;
        }
        return AgentPaymentAmountEn;
    }

    public static String getAgentPaymentConfirmAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentPaymentConfirmAmountAr;
        }
        return AgentPaymentConfirmAmountEn;
    }

    public static String getAgentPaymentInfo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentPaymentInfoAr;
        }
        return AgentPaymentInfoEn;
    }

    public static String getAgentPaymentComputeCommission(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentPaymentComputeCommissionAr;
        }
        return AgentPaymentComputeCommissionEn;
    }

    public static String getAgentPaymentCommission(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentPaymentCommissionAr;
        }
        return AgentPaymentCommissionEn;
    }

    public static String getAgentPaymentWillDeducted(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentPaymentWillDeductedAr;
        }
        return AgentPaymentWillDeductedEn;
    }

    public static String getRepresentativeName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return representativeNameAr;
        }
        return representativeNameEn;
    }

    public static String getAgentPaymentCompanyName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentPaymentCompanyNameAr;
        }
        return AgentPaymentCompanyNameEn;
    }

    public static String getAgentPaymentAmountLE(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentPaymentAmountLEAr;
        }
        return AgentPaymentAmountLEEn;
    }

    public static String getAgentPaymentPinCode(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentPaymentPinCodeAr;
        }
        return AgentPaymentPinCodeEn;
    }

    public static String getAgentPaymentTrx(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentPaymentTrxAr;
        }
        return AgentPaymentTrxEn;
    }

    public static String getAgentPaymentTrxTime(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentPaymentTrxTimeAr;
        }
        return AgentPaymentTrxTimeAr;
    }

    public static String getAgentPaymentTrxDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentPaymentTrxDateAr;
        }
        return AgentPaymentTrxDateAr;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Value Added Tax Abdelsabor">
    private static final String AddedTaxAr = "الضريبة المضافة";
    private static final String AddedTaxEn = "Added Tax";

    private static final String UserAmountAr = "القيمة المطلوبة من العميل ";
    private static final String UserAmountEn = "Amount From Customer with tax";

    private static final String TaxValueAr = "قيمة الضريبة";
    private static final String TaxValueEn = "Tax Value";

    public static String getAddedTax(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AddedTaxAr;
        }
        return AddedTaxEn;
    }

    public static String getUserAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return UserAmountAr;
        }
        return UserAmountEn;
    }

    public static String getTaxValue(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TaxValueAr;
        }
        return TaxValueEn;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Add New Wallet Abdelsabor">
    private static final String AgentInfoAR = "بيانات العميل";
    private static final String AgentInfoEn = "Agent Information";

    private static final String AgentNameInArabicAR = "الإسم بالعربي";
    private static final String AgentNameInArabicEn = "Name In Arabic";

    private static final String AgentNameInEnglishAR = "الإسم بالإنجليزي";
    private static final String AgentNameInEnglishEn = "Name In English";

    private static final String AgentFullAddressAr = "عنوان السكن (تفصيلي)";
    private static final String AgentFullAddressEn = "Full Address";

    private static final String AgentFullWorkAddressAr = "عنوان العمل (تفصيلي)";
    private static final String AgentFullWorkAddressEn = "Full Work Address";

    private static final String OtherInfoAr = "بيانات أخرى";
    private static final String OtherInfoEn = "Others";

    private static final String GovAr = "المحافظة";
    private static final String GovEn = "Governate";

    private static final String CityAr = "المدينة/المركز";
    private static final String CityEn = "City";

    private static final String RegionAr = "المنطقة";
    private static final String RegionEn = "Region";

    private static final String AgentAlternativeMobileNumberAr = "رقم الموبيل (2)";
    private static final String AgentAlternativeMobileNumberEn = "Mobile (2)";

    private static final String LandLineNumberAr = "الرقم الأرضي";
    private static final String LandLineNumberEn = "Land Line Number";

    private static final String ShopNameAr = "إسم المحل";
    private static final String ShopNameEn = "Shop Name";

    private static final String WorkTypeAr = "نوع النشاط";
    private static final String WorkTypeEn = "Work Type";

    private static final String AccountTypeAr = "نوع الحساب";
    private static final String AccountTypeEn = "Account Type";

    private static final String AgentGenderAr = "النوع";
    private static final String AgentGenderEn = "Gender";

    private static final String MaleAr = "ذكر";
    private static final String MaleEn = "Male";

    private static final String FemaleAr = "أنثى";
    private static final String FemaleEn = "Female";

    private static final String MerchantAccountAr = "تاجر";
    private static final String MerchantAccountEn = "Merchant";

    private static final String PrivateAccountAr = "شخصي";
    private static final String PrivateAccountEn = "Private";

    private static final String AboutNationalIdAr = "من فضلك أدخل السبع أرقام المتبقية في الرقم القومي";
    private static final String AboutNationalIdEn = "Please Enter The Reminded 7 number of the National ID";

    private static final String selectCityAr = "إختار المدينة";
    private static final String selectCityEn = "Select City";

    public static final String PAGE_ADD_CUSTOMER_ImagesPage = "agent/AddCustomerImages.jsp";

    private static final String idFrontAr = "صورة البطاقة الأمامية";
    private static final String idFrontEn = "ID Front Photo";

    private static final String idBackAr = "صورة البطاقة الخلفية";
    private static final String idBackEn = "ID back Photo";

    private static final String Contract_1Ar = "صورة العقد الأولى";
    private static final String Contract_1En = "First Contract Photo";

    private static final String Contract_2Ar = "صورة العقد الثانية";
    private static final String Contract_2En = "Second Contract Photo ";

    private static final String Contract_3Ar = "صورة العقد الثالثة";
    private static final String Contract_3En = "Third Contract Photo";

    private static final String CustomerExistsExAr = "هذا العميل مسجل لدى شركة مصاري من فضلك أدخل رقم موبيل أخر";
    private static final String CustomerExistsExEn = "This Customer Already Exsits into Masary Please Enter New Mobile Number";

    private static final String FailToUploadCustomerImagesExAr = "حدث خطأ أثناء إضافة العميل حاول في وقت لاحق";
    private static final String FailToUploadCustomerImagesExEn = "Error During adding new Customer Try Again Later";

    private static final String UploadInstruction1Ar = "يجب عليك رفع الصور  كل في المكان الخاص به";
    private static final String UploadInstruction1En = "You should the Images on its buttons";

    private static final String UploadInstruction2Ar = "الحد الأقصى لحجم الصورة 5 ميجا بايت";
    private static final String UploadInstruction2En = "Image max size 5 MB";

    private static final String UploadInstruction3Ar = "يمكن رفع صور العقد او لا";
    private static final String UploadInstruction3En = "you may upload the contract photos or not";

    private static final String UploadInstruction4Ar = "يجب عليك رفع صورة وجهين الطاقة حتى تتمكن من الإستمرار و الحصول على بطاقة جديدة";
    private static final String UploadInstruction4En = "You should Upload the images of you ID sides to continue and create a new Wallet";

    private static final String SelectFileToUploadAR = "إختار الملفات التي تريد رفعها";
    private static final String SelectFileToUploadEn = "Select Files you want to upload";

    private static final String FileSizeErrorAr = "حجم الملف لا يجب أن يزيد عن 2 ميجا بايت";
    private static final String FileSizeErrorEn = "File size should Less than or Equal 2 MB";

    private static final String WalletAddedAr = "تم إضافة المحفظة بنجاح";
    private static final String WalletAddedEn = "Wallet is Added Successfully";

    private static final String UploadAr = "رفع";
    private static final String UploadEn = "Upload";

    private static final String AboutUploadDoneAr = "تم الرفع بنجاح";
    private static final String AboutUploadDoneEn = "Upload Done Successfully";

    private static final String AboutUploadFailAr = "هناك خطأ حدث أثناء رفع الملف";
    private static final String AboutUploadFailEn = "There was an error attempting to upload the file.";

    private static final String UploadIDErrorAr = "لا يمكنك الإستمرار بدون رفع وجهين صورة البطاقة على الأقل";
    private static final String UploadIDErrorEn = "You Can't Continue without uploading the two sides of your ID";

    private static final String ErrorMandatoryFieldsAr = "لا يمكن الإستمرار بدون إدخال كل البيانات الاساسية";
    private static final String ErrorMandatoryFieldsEn = "You Can't Continue without Inserting all mandatory Fields";

    public static String getErrorMandatoryFields(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ErrorMandatoryFieldsAr;
        }
        return ErrorMandatoryFieldsEn;
    }

    public static String getAgentInfo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentInfoAR;
        }
        return AgentInfoEn;
    }

    public static String getAgentNameInArabic(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentNameInArabicAR;
        }
        return AgentNameInArabicEn;
    }

    public static String getAgentNameInEnglish(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentNameInEnglishAR;
        }
        return AgentNameInEnglishEn;
    }

    public static String getAgentFullAddress(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentFullAddressAr;
        }
        return AgentFullAddressEn;
    }

    public static String getAgentFullWorkAddress(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentFullWorkAddressAr;
        }
        return AgentFullWorkAddressEn;
    }

    public static String getOtherInfo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return OtherInfoAr;
        }
        return OtherInfoEn;
    }

    public static String getGov(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return GovAr;
        }
        return GovEn;
    }

    public static String getAgentCity(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CityAr;
        }
        return CityEn;
    }

    public static String getRegion(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RegionAr;
        }
        return RegionEn;
    }

    public static String getMobileNumber_2(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentAlternativeMobileNumberAr;
        }
        return AgentAlternativeMobileNumberEn;
    }

    public static String getAgnetLandLineNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LandLineNumberAr;
        }
        return LandLineNumberEn;
    }

    public static String getShopName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ShopNameAr;
        }
        return ShopNameEn;
    }

    public static String getWorkType(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WorkTypeAr;
        }
        return WorkTypeEn;
    }

    public static String getAgentGender(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AgentGenderAr;
        }
        return AgentGenderEn;
    }

    public static String getAccountType(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AccountTypeAr;
        }
        return AccountTypeEn;
    }

    public static String getMale(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MaleAr;
        }
        return MaleEn;
    }

    public static String getFemale(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FemaleAr;
        }
        return FemaleEn;
    }

    public static String getMerchantAccount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MerchantAccountAr;
        }
        return MerchantAccountEn;
    }

    public static String getPrivateAccount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PrivateAccountAr;
        }
        return PrivateAccountEn;
    }

    public static String getAboutNationalId(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AboutNationalIdAr;
        }
        return AboutNationalIdEn;
    }

    public static String getselectCity(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return selectCityAr;
        }
        return selectCityEn;
    }

    public static String getIdFront(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return idFrontAr;
        }
        return idFrontEn;
    }

    public static String getIdBack(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return idBackAr;
        }
        return idBackEn;
    }

    public static String getFirstContractPhoto(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Contract_1Ar;
        }
        return Contract_1En;
    }

    public static String getSecondContractPhoto(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Contract_2Ar;
        }
        return Contract_2En;
    }

    public static String getThirdContractPhoto(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Contract_3Ar;
        }
        return Contract_3En;
    }

    public static String getCustomerExistsEx(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CustomerExistsExAr;
        }
        return CustomerExistsExEn;
    }

    public static String getFailToUploadCustomerImagesEx(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FailToUploadCustomerImagesExAr;
        }
        return FailToUploadCustomerImagesExEn;
    }

    public static String getUploadInstruction1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return UploadInstruction1Ar;
        }
        return UploadInstruction1En;
    }

    public static String getUploadInstruction2(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return UploadInstruction2Ar;
        }
        return UploadInstruction2En;
    }

    public static String getUploadInstruction3(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return UploadInstruction3Ar;
        }
        return UploadInstruction3En;
    }

    public static String getUploadInstruction4(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return UploadInstruction4Ar;
        }
        return UploadInstruction4En;
    }

    public static String getSelectFileToUploadAR(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SelectFileToUploadAR;
        }
        return SelectFileToUploadEn;
    }

    public static String getFileSizeError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FileSizeErrorAr;
        }
        return FileSizeErrorEn;
    }

    public static String getWalletAdded(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WalletAddedAr;
        }
        return WalletAddedEn;
    }

    public static String getUpload(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return UploadAr;
        }
        return UploadEn;
    }

    public static String getAboutUploadDone(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AboutUploadDoneAr;
        }
        return AboutUploadDoneEn;
    }

    public static String getAboutUploadFail(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AboutUploadFailAr;
        }
        return AboutUploadFailEn;
    }

    public static String getUploadIDError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return UploadIDErrorAr;
        }
        return UploadIDErrorEn;
    }

    //UploadIDErrorAr
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Printing Voucher Abdelsabor">
    private static final String printingMessage1 = "شامل الضريبة المضافة و ضريبة الجدول";
    private static final String printingMessage2 = "شامل الضريبة المضافة ";

    public static String getprintingMessage1() {
        return printingMessage1;
    }

    public static String getprintingMessage2() {
        return printingMessage2;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Imedia Abdelsabor">
    public static final String ImediaInqueryPage = "/service/ImediaStores/ImediaInquiryPage.jsp";
    public static final String ImediaOperationInfoPage = "/service/ImediaStores/ImediaOperationInfo.jsp";
    public static final String ImediaPaymentPage = "/service/ImediaStores/ImediaPaymentPage.jsp";

    private static final String ImediaInquiryAr = "الإستعلام عن آي ميديا ستور";
    private static final String ImediaInquiryEn = "Imedia Stores Inquiry";

    ////////////////////MLN/////////////////////////////////////////
    public static final String ElectricityInquiry = "/service/New_Electricity/Electricity_inquiry.jsp";
    public static final String ElectricityInquiryInfoPage = "/service/New_Electricity/Electricity_info.jsp";
    public static final String ElectricityConfirmation = "/service/New_Electricity/Electricity_PaymentConfirmation.jsp";
    public static final String ElectricityPaymentPage = "/service/New_Electricity/Electricity_payment.jsp";

    private static final String Electricity_InquiryAr = "الإستعلام عن كهرباء ";
    private static final String Electricity_InquiryEN = " Electricity Inquiry";
    private static final String Electronic_pay_numberAr = " رقم السداد الالكترونى";
    private static final String Electronic_pay_numberEN = " Electronic Payment Number";

    private static final String JoinAccount_NumberAr = "رقم حساب المشترك";
    private static final String JoinAccount_NumberEN = "Joint Account Number";
    private static final String ElectricityAr = " كهرباء جنوب القاهرة";
    private static final String ElectricityEN = " Electricity south cairo";

    private static final String ElectricityTitleAr = " فواتير العميل";
    private static final String ElectricityTitleEn = "Customer bills";
    public static final String TotalAmount = "المبلغ المطلوب دفعه شامل مصاريف الخدمة وضريبة القيمة المضافة";

    private static final String IndebtednessAr = " المديونية";
    private static final String IndebtednessEn = "Indebtedness";

    public static String getIndebtednessName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return IndebtednessAr;
        }
        return IndebtednessEn;
    }

    public static String getElectricityName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ElectricityAr;
        }
        return ElectricityEN;
    }

    public static String ElectricityTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ElectricityTitleAr;
        }
        return ElectricityTitleEn;
    }
    private static final String OperationID_Ar = "رقم العملية";
    private static final String OperationID_En = "Operation ID";
    private static final String OperationIdTitle_Ar = "من فضلك أدخل رقم العملية أرقام فقط";
    private static final String OperationIdTitle_En = "Please Enter Operation ID Numbers Only";

    private static final String ImediaStoreTextAr = "iMedia Store";
    private static final String ImediaStoreTextEn = "iMedia Store";

    private static final String ImediaBillValueAr = "قيمة الفاتورة";
    private static final String ImediaBillValueEn = "Bill Value";

    private static final String CloseAr = "إغلاق";
    private static final String CloseEn = "Close";

    private static final String successfulTransactionAr = "عملية ناجحة";
    private static final String successfulTransactionEn = "successful Transaction";

    public static String GetImediaInquiry(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ImediaInquiryAr;
        }
        return ImediaInquiryEn;
    }

    //////////////////////////////////MLN////////////////////////////
    public static String GetElectricity_Inquiry(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Electricity_InquiryAr;
        }
        return Electricity_InquiryEN;
    }

    public static String GetElectronic_pay_number(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Electronic_pay_numberAr;
        }
        return Electronic_pay_numberEN;
    }
    ///////////////////////////////////////////////////////////////////////////////

    public static String GetOperationID(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return OperationID_Ar;
        }
        return OperationID_En;
    }

    public static String GetOperationIdTitle_Ar(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return OperationIdTitle_Ar;
        }
        return OperationIdTitle_En;
    }

    public static String GetImediaStoreText(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ImediaStoreTextAr;
        }
        return ImediaStoreTextEn;
    }

    public static String GetImediaBillValue(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ImediaBillValueAr;
        }
        return ImediaBillValueEn;
    }

    public static String GetClose(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CloseAr;
        }
        return CloseEn;
    }

    public static String GetSuccessfulTransaction(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return successfulTransactionAr;
        }
        return successfulTransactionEn;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Edu Centers Abdelsabor">
    public static final String EduCentersHomePage = "/service/EduCenters/EduCentersHome.jsp";
    public static final String EduCentersConfirmationPage = "/service/EduCenters/EduCentersConfirmation.jsp";
    public static final String EduCentersPaymentPage = "/service/EduCenters/EduCentersPayment.jsp";

    private static final String EduCodeAr = "كود الحجز( رقم الموبايل)";
    private static final String EduCodeEn = "Code(Mobile No or reservation No)";

    private static final String B2bGroup = "B2B Group";
    private static final String B2bGroupoWTOI = "WTOI";
    private static final String B2bGroupoB2BEvents = "B2B Events";

    private static final String Korsatak = "Korsatak";
    private static final String MEC = "MEC";
    private static final String BinarySystems = "Binary Systems";
    private static final String SkillsBank = "Skills Bank";

    private static final String EduCodeTitle_Ar = "من فضلط أدخل كود الحجز أو رقم التليفون";
    private static final String EduCodeTitle_En = "Please Enter Reservation Code or Mobile Number";

    private static final String EduAmount_Ar = "المبلغ";
    private static final String EduAmount_En = "Amount";

    private static final String EduAmountTitle_Ar = "من فضلك أدخل قيمة الحجز";
    private static final String EduAmountTitle_En = "Please Enter Reservation Amount";

    private static final String ServiceType_Ar = "نوع الخدمة";
    private static final String ServiceType_En = "Service Type";

    private static final String EduServiceCostAr = "تكلفة الخدمة";
    private static final String EduServiceCostEn = "Service Cost";

    private static final String EduTotalAmountAr = "إجمالي المبلغ";
    private static final String EduTotalAmountEn = "Total Amount";

    private static final String EduCentersServiceAr = "خدمة تحصيل المصروفات الدراسية - المراكز التعليمية";
    private static final String EduCentersServiceEn = "Educational Fees Procurment Service - Educational Centers";

    public static String getEduCode(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return EduCodeAr;
        }
        return EduCodeEn;
    }

    public static String getEduAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return EduAmount_Ar;
        }
        return EduAmount_En;
    }

    public static String getB2bGroup(HttpSession session) {
        return B2bGroup;
    }

    public static String getB2bGroupoWTOI(HttpSession session) {
        return B2bGroupoWTOI;
    }

    public static String getB2bGroupoB2BEvents(HttpSession session) {
        return B2bGroupoB2BEvents;
    }

    public static String getKorsatak(HttpSession session) {
        return Korsatak;
    }

    public static String getMEC(HttpSession session) {
        return MEC;
    }

    public static String getBinarySystems(HttpSession session) {
        return BinarySystems;
    }

    public static String getSkillsBank(HttpSession session) {
        return SkillsBank;
    }

    public static String getEduCodeTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return EduCodeTitle_Ar;
        }
        return EduCodeTitle_En;
    }

    public static String getEduAmountTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return EduAmountTitle_Ar;
        }
        return EduAmountTitle_En;
    }

    public static String getServiceType(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ServiceType_Ar;
        }
        return ServiceType_En;
    }

    public static String getEduServiceCost(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return EduServiceCostAr;
        }
        return EduServiceCostEn;
    }

    public static String getEduTotalAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return EduTotalAmountAr;
        }
        return EduTotalAmountEn;
    }

    public static String getEduCentersServic(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return EduCentersServiceAr;
        }
        return EduCentersServiceEn;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Waslet Khier Abdelsabor">
    public static final String WasletKhierHomePage = "/service/WasletKhier/wasletKhierHome.jsp";
    public static final String WasletKhierConfirmationPage = "/service/WasletKhier/wasletKhierConfirmation.jsp";
    public static final String WasletKhierPaymentPage = "/service/WasletKhier/wasletKhierPayment.jsp";

    private static final String WKDonationTypeAr = "نوع التبرع";
    private static final String WKDonationTypeEn = "Donation Type";

    private static final String WKPhoneNumberAr = "رقم التليفون";
    private static final String WKPhoneNumberEn = "Phone Number";

    private static final String WKAmountAr = "قيمة الصك /مبلغ التبرع";
    private static final String WKAmountEn = "Amount";

    private static final String WKFirstInstructionAr = "رجاء إدخال قيمة التبرع و رقم الموبيل";
    private static final String WKFirstInstructionEn = "Please Enter Donation Value and Phone Number";

    private static final String WKSecondInstructionAr = "أقل قيمة للتبرع عشرة جنيهات بدون كسور";
    private static final String WKSecondInstructionEn = "Least Donation Amount Ten Pounds without floats";

    private static final String WKThiredInstructionAr = "رجاء التأكد من رقم الموبيل الذي تم إدخاله";
    private static final String WKThiredInstructionEn = "Please Confirm The phone Number You Entered Before";

    private static final String WKCochlearTransplantAr = "زراعة قوقعة";
    private static final String WKCochlearTransplantEn = "Cochlear Transplant";

    private static final String WKHeartOperationsAr = "عمليات القلب";
    private static final String WKHeartOperationsEn = "Heart Operations";

    private static final String WKPROTHESIS_INSTALLATIONAr = "تركيب أطراف";
    private static final String WKPROTHESIS_INSTALLATIONEn = "PROTHESIS INSTALLATION";

    private static final String WKEYES_OPERATIONSAr = "عمليات العيون";
    private static final String WKEYES_OPERATIONSEn = "EYES OPERATIONS";

    private static final String WKPOOR_VILLAGES_CONSTRUCTIONAr = "إعمار قرى فقيرة";
    private static final String WKPOOR_VILLAGES_CONSTRUCTIONEn = "POOR VILLAGES CONSTRUCTION";

    private static final String WKWINTER_BLANKETS_CAMPAIGNAr = "حملة بطاطين الشتاء";
    private static final String WKWINTER_BLANKETS_CAMPAIGNEn = "WINTER BLANKETS CAMPAIGN";
    private static final String WasKher_ODH_BIG_BALADYAr = "صك الأضحية بلدي كبير";
    private static final String WasKher_ODH_BIG_BALADYEn = "WasKher_ODH_BIG_BALADY";
    private static final String WasKher_ODH_SMALL_BALADYAr = "صك الأضحية بلدي صغير ";
    private static final String WasKher_ODH_SMALL_BALADYEn = "WasKher_ODH_SMALL_BALADY";
    private static final String WasKher_ODH_Mostawrad_BALADYAr = "صك الأضحية مستورد";
    private static final String WasKher_ODH_Mostawrad_BALADYEn = "WasKher_ODH_Mostawrad_BALADY";

    private static final String WKORPHANS_CAREAr = "رعاية أيتام";
    private static final String WKORPHANS_CAREEn = "ORPHANS CARE";

    public static String getWKDonationType(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKDonationTypeAr;
        }
        return WKDonationTypeEn;
    }

    public static String getWasKherODHBIGBALADY(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WasKher_ODH_BIG_BALADYAr;
        }
        return WasKher_ODH_BIG_BALADYEn;
    }

    public static String getWasKherODHSmallBALADY(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WasKher_ODH_SMALL_BALADYAr;
        }
        return WasKher_ODH_SMALL_BALADYEn;
    }

    public static String getWasKherODHMostaworadBALADY(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WasKher_ODH_Mostawrad_BALADYAr;
        }
        return WasKher_ODH_Mostawrad_BALADYEn;
    }

    public static String getWKPhoneNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKPhoneNumberAr;
        }
        return WKPhoneNumberEn;
    }

    public static String getWKAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKAmountAr;
        }
        return WKAmountEn;
    }
    public static final String BaitElzakahHomePage = "/service/BaitElzakahOf/BaitElzakahHome.jsp";
    public static final String BaitElzakahConfirmationPage = "/service/BaitElzakahOf/BaitElzakahConfirmation.jsp";
    public static final String BaitElzakahPaymentPage = "/service/BaitElzakahOf/BaitElzakahPayment.jsp";
    private static final String BaitElzakah_ZakahAr = "زكاة";
    private static final String BaitElzakah_ZakahEn = "zakah";
    private static final String BaitElzakah_ZakatFetrAr = "زكاة الفطر";
    private static final String BaitElzakah_ZakatFetrEn = "Zakat ElFetr";
    private static final String BaitElzakah_SadakaAr = "صدقة";
    private static final String BaitElzakah_SadakaEn = "Sadaka";
    private static final String BaitElzakah_WakfSadakaAr = "الوقف (صدقة جارية)";
    private static final String BaitElzakah_WakfSadakaEn = "AlWakf (Sadaka Garya)";

    public static final String ResalaHomePage = "/service/Resala/ResalaHome.jsp";
    public static final String ResalaConfirmationPage = "/service/Resala/ResalaConfirmation.jsp";
    public static final String ResalaPaymentPage = "/service/Resala/ResalaPayment.jsp";
    private static final String ResalaDarAetamAr = "دار ايتام";
    private static final String ResalaDarAetamEn = "Dar Aetam";
    private static final String ResalazakahMalAr = "زكاة مال";
    private static final String ResalaKfartSaemAr = "كفاره صائم";
    private static final String ResalaKfartSaemEn = "Expiation for fasting";

    private static final String ResalazakahMalEn = "zakah Mal";
    private static final String ResalaCriticalSituationsAr = "حالات حرجة وعمليات";
    private static final String ResalaCriticalSituationsEn = "Critical Situations";

    private static final String ResalaOngoingcharityAr = "صدقة جاريه";
    private static final String ResalaOngoingcharityEn = "Ongoing charity";

    private static final String ResalaBahiaCenterAr = "مركز بهيه";
    private static final String ResalaBahiaCenterEn = "Bahia Center";
    private static final String ResalaAftarSaemAr = "افطار صائم";
    private static final String ResalaAftarSaemEn = "Aftar Saem";
    private static final String ResalaRamdanBoxAr = "شنطة رمضان";
    private static final String ResalaRamdanBoxEn = "Ramdan Box";
    private static final String ResalaOdhayaAEEDAr = "اضحية العيد";
    private static final String ResalaOdhayaAEEDEn = "Odhaya AEED";
    private static final String ResalaOdhayaAr = "صك الاضحية - لحوم مستوردة";
    private static final String ResalaEftarSaemAr = "افطار صائم";
    private static final String ResalaEftarSaemEn = "Breakfast fasting";
    
    private static final String ResalaZakahEftarAr = "زكاة فطر";
    private static final String ResalaZakahEftarEn = "Eftar Zakah";

    private static final String ResalaOdhayaEn = "Instrument Of Sacrifice - Imported Meat";
    private static final String ResalaLhoomSadakatAr = "لحوم صدقات ";
    private static final String ResalaLhoomSadakatEn = "Sak Odhaia";
    
    public static String getResalaZakahEftar(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ResalaZakahEftarAr;
        }
        return ResalaZakahEftarEn;
    }
    

    public static String getBaitElzakahZakah(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BaitElzakah_ZakahAr;
        }
        return BaitElzakah_ZakahEn;
    }

    public static String getBaitElzakahZakatFetr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BaitElzakah_ZakatFetrAr;
        }
        return BaitElzakah_ZakatFetrEn;
    }

    public static String getBaitElzakahSadaka(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BaitElzakah_SadakaAr;
        }
        return BaitElzakah_SadakaEn;
    }

    public static String getBaitElzakahWakfSadaka(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BaitElzakah_WakfSadakaAr;
        }
        return BaitElzakah_WakfSadakaEn;
    }

    public static String getResalaDarAetam(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ResalaDarAetamAr;
        }
        return ResalaDarAetamEn;
    }

    public static String getResalaLhoomSadakat(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ResalaLhoomSadakatAr;
        }
        return ResalaLhoomSadakatEn;
    }

    public static String getResalazakahMal(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ResalazakahMalAr;
        }
        return ResalazakahMalEn;
    }

    public static String getResalaKfartSaem(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ResalaKfartSaemAr;
        }
        return ResalaKfartSaemEn;
    }

    public static String getResalaCriticalSituations(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ResalaCriticalSituationsAr;
        }
        return ResalaCriticalSituationsEn;
    }

    public static String getResalaOngoingcharity(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ResalaOngoingcharityAr;
        }
        return ResalaOngoingcharityEn;
    }

    public static String getResalaBahiaCenter(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ResalaBahiaCenterAr;
        }
        return ResalaBahiaCenterEn;
    }

    public static String getResalaAftarSaem(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ResalaAftarSaemAr;
        }
        return ResalaAftarSaemEn;
    }

    public static String getResalaRamdanBox(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ResalaRamdanBoxAr;
        }
        return ResalaRamdanBoxEn;
    }

    public static String getResalaResalaOdhaya(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ResalaOdhayaAr;
        }
        return ResalaOdhayaEn;
    }

    public static String getReliefEftarSaem(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ResalaEftarSaemAr;
        }
        return ResalaEftarSaemEn;
    }

    public static String getResalaOdhayaAEED(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ResalaOdhayaAEEDAr;
        }
        return ResalaOdhayaAEEDEn;
    }

    public static String getWKFirstInstruction(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKFirstInstructionAr;
        }
        return WKFirstInstructionEn;
    }

    public static String getWKSecondInstruction(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKSecondInstructionAr;
        }
        return WKSecondInstructionEn;
    }

    public static String getWKCochlearTransplant(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKCochlearTransplantAr;
        }
        return WKCochlearTransplantEn;
    }

    public static String getWKHeartOperations(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKHeartOperationsAr;
        }
        return WKHeartOperationsEn;
    }

    public static String getWKORPHANS_CARE(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKORPHANS_CAREAr;
        }
        return WKORPHANS_CAREEn;
    }

    public static String getWKPROTHESIS_INSTALLATION(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKPROTHESIS_INSTALLATIONAr;
        }
        return WKPROTHESIS_INSTALLATIONEn;
    }

    public static String getWKEYES_OPERATIONS(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKEYES_OPERATIONSAr;
        }
        return WKEYES_OPERATIONSEn;
    }

    public static String getWKPOOR_VILLAGES_CONSTRUCTION(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKPOOR_VILLAGES_CONSTRUCTIONAr;
        }
        return WKPOOR_VILLAGES_CONSTRUCTIONEn;
    }

    public static String getWKWINTER_BLANKETS_CAMPAIGN(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKWINTER_BLANKETS_CAMPAIGNAr;
        }
        return WKWINTER_BLANKETS_CAMPAIGNEn;
    }

    public static String getWKThiredInstruction(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKThiredInstructionAr;
        }
        return WKThiredInstructionEn;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="O2 Group Abdelsabor">
    public static final String O2GroupHomePage = "/service/O2Group/O2HomePage.jsp";
    public static final String O2GroupConfirmationPage = "/service/O2Group/O2ConfirmationPage.jsp";
    public static final String O2GroupAmountPage = "/service/O2Group/O2ChooseAmountPage.jsp";
    public static final String O2GroupAmountConfirmationPage = "/service/O2Group/O2ChooseAmountConfirmationPage.jsp";
    public static final String O2GroupPaymentPage = "/service/O2Group/O2PaymnetPage.jsp";

    private static final String O2GroupAr = "أوتو - شير";
    private static final String O2GroupEn = "أوتو - شير";

    private static final String O2GroupClientNameAr = "إسم صاحب الحساب";
    private static final String O2GroupClientNameEn = "Client Name";

    private static final String O2GroupPhoneNumberAr = "رقم الموبيل";
    private static final String O2GroupPhoneNumberEn = "Phone Number";

    private static final String O2GroupChooseAmountAr = "يرجى إختيار الفئة المطلوبة";
    private static final String O2GroupChooseAmountEn = "Choose Amount";

    private static final String O2GroupAmountAr = "إختر القيمة";
    private static final String O2GroupAmountEn = "Choose Amount";

    private static final String O2GroupPaidAmountAr = "المبغ المطلوب شامل الرسوم و ضريمة القيمة المضافة";
    private static final String O2GroupPaidAmountEn = "Paid Amount with Vat";

    private static final String O2GroupDeducedAmountAr = "المبلغ المخصوم";
    private static final String O2GroupDeducedAmountEn = "Deduced Amount";

    private static final String O2GroupChargeAmountAr = "فئة الشحن";
    private static final String O2GroupChargeAmountEn = "Charge Amount";

    private static final String O2GroupMobileNotFoundExAr = "رقم الموبيل غير مسجل";
    private static final String O2GroupMobileNotFoundExEn = "Mobile Number Not Found";

    public static String getO2Group(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return O2GroupAr;
        }
        return O2GroupEn;
    }

    public static String getO2GroupClientName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return O2GroupClientNameAr;
        }
        return O2GroupClientNameEn;
    }

    public static String getO2GroupPhoneNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return O2GroupPhoneNumberAr;
        }
        return O2GroupPhoneNumberEn;
    }

    public static String getO2GroupChooseAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return O2GroupChooseAmountAr;
        }
        return O2GroupChooseAmountEn;
    }

    public static String getO2GroupAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return O2GroupAmountAr;
        }
        return O2GroupAmountEn;
    }

    public static String getO2GroupPaidAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return O2GroupPaidAmountAr;
        }
        return O2GroupPaidAmountEn;
    }

    public static String getO2GroupDeducedAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return O2GroupDeducedAmountAr;
        }
        return O2GroupDeducedAmountEn;
    }

    public static String getO2GroupChargeAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return O2GroupChargeAmountAr;
        }
        return O2GroupChargeAmountEn;
    }

    public static String getO2GroupMobileNotFoundEx(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return O2GroupMobileNotFoundExAr;
        }
        return O2GroupMobileNotFoundExEn;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Relief Foundationl Abdelsabor">
    public static final String ReliefFoundationlHomePage = "/service/ReliefFoundationl/ReliefFoundationlHome.jsp";
    public static final String ReliefFoundationlConfirmationPage = "/service/ReliefFoundationl/ReliefFoundationlConfirmation.jsp";
    public static final String ReliefFoundationlPaymentPage = "/service/ReliefFoundationl/ReliefFoundationlPayment.jsp";

    private static final String ReliefFoundationlServiceAr = "مؤسسة الإغاثه والطوارئ";
    private static final String ReliefFoundationlServiceEn = "Relief and Emergency Foundationl";

    private static final String ReliefFoundationlZakahAr = "زكاة مال";
    private static final String ReliefFoundationlZakahEn = "Zakah";

    private static final String ReliefFoundationlSadkahAr = "صدقة جارية";
    private static final String ReliefFoundationlSadkahEn = "OnGoing Sadkah";

    private static final String ReliefFoundationlFoodAr = "إطعام الطعام";
    private static final String ReliefFoundationlFoodEn = "Feed the Food";

    private static final String ReliefFoundationlEyeAr = "عمليات عيون";
    private static final String ReliefFoundationlEyeEn = "Eye operations";

    private static final String ReliefFoundationlVirusCAr = "الكشف عن فيروس سي";
    private static final String ReliefFoundationlVirusCEn = "Virus C Check";

    private static final String ReliefFoundationlRamdanAr = "كرتونة رمضان";
    private static final String ReliefFoundationlRamdanEn = "Ramdan Box";

    private static final String ReliefFoundationlZakahEftarAr = "زكاة الفطر";
    private static final String ReliefFoundationlZakahEftarEn = "Eftar Zakah";

    
    private static final String ReliefFoundationlFairReliefAr = "الإغاثة العادلة";
    private static final String ReliefFoundationlFairReliefEN = "Fair Relief";

    private static final String ReliefFoundationlEducationAr = "التعليم والتأهيل";
    private static final String ReliefFoundationlEducationEn = "Education and Rehabilitation";
	
	private static final String ReliefFoundationlEmaarApoorvillageAR = "إعمار قرية فقيرة";
    private static final String ReliefFoundationlEmaarApoorvillageEn = "Emaar a poor village";
	
	private static final String ReliefFoundationlRamadancartoonAR = "كرتونة رمضان";
    private static final String ReliefFoundationlRamadancartoonEn = "Ramadan cartoon";
	
	private static final String ReliefFoundationlBreakfastFastingAR = "إفطار صائم";
    private static final String ReliefFoundationlBreakfastFastingEn = "Breakfast Fasting";
    
    public static String getReliefFoundationlService(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReliefFoundationlServiceAr;
        }
        return ReliefFoundationlServiceEn;
    }
    
    public static String getReliefFoundationlFairRelief(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReliefFoundationlFairReliefAr;
        }
        return ReliefFoundationlFairReliefEN;
    }
     public static String getReliefFoundationlEducation(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReliefFoundationlEducationAr;
        }
        return ReliefFoundationlEducationEn;
    }
      public static String getReliefFoundationlEmaarApoorvillage(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReliefFoundationlEmaarApoorvillageAR;
        }
        return ReliefFoundationlEmaarApoorvillageEn;
    }
      
      
      public static String getReliefFoundationlRamadancartoon(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReliefFoundationlRamadancartoonAR;
        }
        return ReliefFoundationlRamadancartoonEn;
    }
      
       
      public static String getReliefFoundationlBreakfastFasting(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReliefFoundationlBreakfastFastingAR;
        }
        return ReliefFoundationlBreakfastFastingEn;
    }
    
    
    

    public static String getReliefFoundationlZakah(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReliefFoundationlZakahAr;
        }
        return ReliefFoundationlZakahEn;
    }

    public static String getReliefFoundationlSadkah(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReliefFoundationlSadkahAr;
        }
        return ReliefFoundationlSadkahEn;
    }

    public static String getReliefFoundationlFood(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReliefFoundationlFoodAr;
        }
        return ReliefFoundationlFoodEn;
    }

    public static String getReliefFoundationlEye(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReliefFoundationlEyeAr;
        }
        return ReliefFoundationlEyeEn;
    }

    public static String getReliefFoundationlVirusC(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReliefFoundationlVirusCAr;
        }
        return ReliefFoundationlVirusCEn;
    }

    public static String getReliefFoundationlRamdan(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReliefFoundationlRamdanAr;
        }
        return ReliefFoundationlRamdanEn;
    }

    public static String getReliefFoundationlZakahEftar(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReliefFoundationlZakahEftarAr;
        }
        return ReliefFoundationlZakahEftarEn;
    }
    // </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="Legitimacy Association Abdelsabor">

    public static final String LegitimacyAssociationPage = "/service/LegitimacyAssociation/LegitimacyAssociationPage.jsp";
    public static final String LegitimacyAssociationConfirmationPage = "/service/LegitimacyAssociation/LegitimacyAssociationConfirmation.jsp";
    public static final String LegitimacyAssociationResultPage = "/service/LegitimacyAssociation/LegitimacyAssociationPay.jsp";
    public static final String ACTION_LA_PAGE = "ACTION_LA_PAGE";
    public static final String ACTION_LA_Confirmation = "ACTION_LA_Confirmation";
    public static final String ACTION_LA_PAY = "ACTION_LA_PAY";

    private static final String LegitimacyAssociationDescriptionAr = "خدمة التبرع للجمعية الشرعية الرئيسية";
    private static final String LegitimacyAssociationDescriptionEn = "Donate To Main Legitimacy Association  Service";

    private static final String LegitimacyAssociationDescriptionAr2 = "خدمة التبرع للجمعية الشرعية الرئيسية - ";
    private static final String LegitimacyAssociationDescriptionEn2 = "Donate To Main Legitimacy Association  Service - ";

    private static final String LegitimacyAssociationSocityProjectAr = "تبرع عام للجمعية الشرعية الرئيسية";
    private static final String LegitimacyAssociationProjectEn = "General Donation to Legitimacy Association";

    private static final String LegitimacyAssociationZakahProjectAr = "تبرع زكاة";
    private static final String LegitimacyAssociationZakahProjectEn = "Zakah Donation";

    private static final String LegitimacyAssociationSadkahProjectAr = "تبرع صدقة";
    private static final String LegitimacyAssociationSadkahProjectEn = "Sadkah Donation";

    private static final String ToLegitimacyAssociationAr = "للجمعية الشرعية الرئيسية - ";
    private static final String ToLegitimacyAssociationEn = "To Main Legitimacy Association";

    public static String getLegitimacyAssociationDescription(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LegitimacyAssociationDescriptionAr;
        }
        return LegitimacyAssociationDescriptionEn;
    }

    public static String getLegitimacyAssociationDescription2(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LegitimacyAssociationDescriptionAr2;
        }
        return LegitimacyAssociationDescriptionEn2;
    }

    public static String getLegitimacyAssociationSocityProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LegitimacyAssociationSocityProjectAr;
        }
        return LegitimacyAssociationProjectEn;
    }

    public static String getLegitimacyAssociationZakahProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LegitimacyAssociationZakahProjectAr;
        }
        return LegitimacyAssociationZakahProjectEn;
    }

    public static String getLegitimacyAssociationSadkahProject(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LegitimacyAssociationSadkahProjectAr;
        }
        return LegitimacyAssociationSadkahProjectEn;
    }

    public static String getToLegitimacyAssociation(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ToLegitimacyAssociationAr;
        }
        return ToLegitimacyAssociationEn;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="ElkasrEleeny Abdelsabor">
    public static final String ElKasrEleenyPage = "/service/ElkasrEleeny/ElkasrEleenyHomePage.jsp";
    public static final String ElKasrEleenyConfirmationPage = "/service/ElkasrEleeny/ElkasrEleenyConfirmation.jsp";
    public static final String ElKasrEleenyResultPage = "/service/ElkasrEleeny/ElkasrEleenyPayment.jsp";

    private static final String elKasrEleenyServiceAr = "تبرعات مستشفى القصر العيني";
    private static final String elKasrEleenyServiceEn = "Elkasr Eleeny Hosbital Donations";

    public static String getElKasrEleenyService(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return elKasrEleenyServiceAr;
        }
        return elKasrEleenyServiceEn;
    }
    // </editor-fold>
    /*
     * by Keroles Agent Payment Project 03-09-2015
     */
    public static final String ACTION_AGENT_BILL_INQUIRY = "ACTION_AGENT_BILL_INQUIRY";
    public static final String PAGE_AGENT_INFO = "agentPayment/bill_info.jsp";
    public static final String PAGE_AGENT_INQUIRY = "agentPayment/bill_inquiry.jsp";
    public static final String ACTION_Get_Agent_Inquiry_Res = "Get_Agent_Inquiry_Res";
    public static final String ACTION_AgentPayment_Inquiry = "AgentPayment_Inquiry_Req";
    public static final String ACTION_AgentPayment_Inquiry_Confirm = "AgentPayment_Inquiry_Confirm";
    public static final String AGENT_PAYMENT_PAGE = "agentPayment/bill_payment.jsp";
    private final static String BillSecretCodeEn = "or Secret Code";
    private final static String BillSecretCodeAr = "او الكود السري";
    private final static String codeDetailsEn = "Code Details";
    private final static String codeDetailsAr = "تفاصيل الفاتورة";
    public static final String ACTION_AGENT_PAYMENT = "ACTION_AGENT_PAYMENT";
    public static final String ACTION_Do_Agent_Payment = "Do_Agent_Payment";
    public static final String ACTION_Do_Agent_Payment_Confirm = "Do_Agent_Payment_Confirm";
    public static final String PAGE_Agent_Payment = "agentPayment/agentPayment.jsp";
    public static final String PAGE_Agent_Bill_Payment_Confirm = "agentPayment/agentPayment_confirmation.jsp";
    private final static String BillRefNumORCNen = "Bill Number or Customer Number";
    private final static String BillRefNumORCNar = "رقم الفاتوره او رقم العميل";
    public static final String OPERATION_ID = "OPERATION_ID";
    public static final String PROVIDER_ID = "PROVIDER_ID";
    public static final String PROGRAM = "PROGRAM";
    public static final String PROGRAM_EN = "Donation Type";
    public static final String PROGRAM_ar = "نوع التبرع";
    public static final String Donation_EN = "Donation";
    public static final String Donation_ar = "تبرعات";
    public static final String volunteer_EN = "volunteer name";
    public static final String volunteer_ar = "اسم المتبرع";
    public static final String ACTION_GET_DONATION = "ACTION_GET_DONATION";
    public static final String ACTION_PAYMENT_DONATION = "ACTION_PAYMENT_DONATION";
    public static final String ACTION_GET_DONATION_CONFIRMATION = "ACTION_GET_DONATION_CONFIRMATION";
    public static final String PAGE_DONATION = "service/donations/donation.jsp";
    public static final String PAGE_DONATION_Confirmation = "service/donations/donationConfirmation.jsp";
    public static final String PAGE_PAY_AGENTPAYMENT = "agentPayment/AgentPaymentPrinting.jsp";
    public static final String PAGE_PAY_DONATION = "service/donations/payDonation.jsp";
    private static final String RecieptMessAgentPaymentAr = "لقد تم دفع الفاتورة بنجاح رقم العمليه :";
    private static final String RecieptMessAgentPaymentEn = "Bill has been Payed to Agent with transaction NO: ";
    public static final String PAGE_VIEW_CUSTOMER_TRANSACTION_Printing = "customer/PrintingVoucher.jsp";
    public static final String PAGE_VIEW_CUSTOMER_TRANSACTION_RePrinting = "customer/RePrintingVoucher.jsp";
    private final static String operationRetryEn = "Retry";
    private final static String operationRetryAr = "إعادة العملية";

    public static String getvolunteerName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return volunteer_ar;
        }
        return volunteer_EN;
    }

    public static String getCustomerdonationHead(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERDonationsar;
        }
        return CUSTOMERDonationen;
    }

    public static String getCommessionMaan(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CommessionMaanar;
        }
        return CommessionMaanen;
    }

    public static String getTotalAmountMaan(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TotalAmountMaanar;
        }
        return TotalAmountMaanen;
    }

    public static String getDeductedAmountMaan(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DeductedAmountMaanar;
        }
        return DeductedAmountMaanen;
    }

    public static String getBillSecretCode(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BillSecretCodeAr;
        }
        return BillSecretCodeEn;
    }

    public static String getBillRefNumORCN(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BillRefNumORCNar;
        }
        return BillRefNumORCNen;
    }

    public static String getprogram(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PROGRAM_ar;
        }
        return PROGRAM_EN;
    }

    public static String getdonationName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Donation_ar;
        }
        return Donation_EN;
    }

    public static String getRecieptMessAgentPayment(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessAgentPaymentAr;
        }
        return RecieptMessAgentPaymentEn;
    }

    public static String getcodeDetails(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return codeDetailsAr;
        }
        return codeDetailsEn;
    }

    /*
     * ENd Agent Payment Project 03-09-2015
     */
    public static final int SUCCESS = 0;
    // actions
    public static final String ACTION_AUTHENTICATE = "ACTION_AUTHENTICATE";
    public static final String ACTION_AUTHENTICATE_ANSWER = "ACTION_AUTHENTICATE_ANSWER";
    public static final String ACTION_SEARCH = "ACTION_SEARCH";
    public static final String ACTION_CUST_TRANSFER = "ACTION_CUST_TRANSFER";
    public static final String ACTION_CUST_TOPUP = "ACTION_CUST_TOPUP";
    public static final String ACTION_CUST_VOUCHER_TOPUP = "ACTION_CUST_VOUCHER_TOPUP";
    public static final String ACTION_GET_LAST_TRANSACTIONS = "ACTION_GET_LAST_TRANSACTIONS";
    public static final String ACTION_REPORTS_MOBLIE = "ACTION_REPORTS_MOBLIE";
    public static final String ACTION_GET_ELECT_PILL = "ACTION_GET_ELECT_PILL";
    public static final String ACTION_PAY_ELECT_PILL = "ACTION_PAY_ELECT_PILL";// parameters
    public static final String ACTION_GET_MFI_PILL = "ACTION_GET_MFI_PILL";
    public static final String ACTION_PAY_MFI_PILL = "ACTION_PAY_MFI_PILL";
    public static final String ACTION_AUTHENTICATE_USER = "ACTION_AUTHENTICATE_USER";
    public static final String ACTION_ADD_EMPLOYEE = "ACTION_ADD_EMPLOYEE";
    public static final String ACTION_LOGOUT = "ACTION_LOGOUT";
    public static final String ACTION_GET_VERSION = "ACTION_GET_VERSION";
    public static final String ACTION_GET_JAR = "ACTION_GET_JAR";
    public static final String ACTION_GET_LAST_10 = "ACTION_GET_LAST_10";
    public static final String ACTION_ADMIN_OPERATIONS = "ACTION_ADMIN_OPERATIONS";
    public static final String ACTION_AGENT_OPERATIONS = "ACTION_AGENT_OPERATIONS";
    public static final String ACTION_PURCHASING_ORDER = "ACTION_PURCHASING_ORDER";
    public static final String ACTION_SEND_MAIL_REQUEST = "ACTION_SEND_MAIL_REQUEST";
    public static final String ACTION_CUSTOMER_OPERATIONS = "ACTION_CUSTOMER_OPERATIONS";
    public static final String ACTION_SELL_CREADIT = "ACTION_SELL_CREADIT";
    public static final String ACTION_SELL_CUSTOMER_CREADIT = "ACTION_SELL_CUSTOMER_CREADIT";
    public static final String ACTION_ADD_AGENT = "ACTION_ADD_AGENT ";
    public static final String ACTION_EDIT_AGENT = "ACTION_EDIT_AGENT";
    public static final String ACTION_ASIGN_AGENT_BALANCE = "ACTION_ASIGN_AGENT_BALANCE";
    public static final String ACTION_INCLUDE_TRANS_AG = "ACTION_INCLUDE_TRANS_AG";
    public static final String ACTION_INCLUDE_Earn_List = "ACTION_INCLUDE_Earn_List";
    public static final String ACTION_TREE_LIST_TRANS = "ACTION_TREE_LIST_TRANS";
    public static final String ACTION_ASIGN_AGENT_SERVICE_BALANCE = "ACTION_ASIGN_AGENT_SERVICE_BALANCE";
    public static final String ACTION_MANAGE_AGENT_BALANCE = "ACTION_MANAGE_AGENT_BALANCE";
    public static final String ACTION_MANAGE_AGENT_SERVICE_BALANCE = "ACTION_MANAGE_AGENT_SERVICE_BALANCE";
    public static final String ACTION_MANAGE_AGENT_SERVICE_BALANCE_V = "ACTION_MANAGE_AGENT_SERVICE_BALANCE_V";
    public static final String ACTION_ASSIGN_VOUCHER_TO_CUSTOMER = "ACTION_ASSIGN_VOUCHER_TO_CUSTOMER";
    public static final String ACTION_ASSIGN_AGENT_SERVICE_BALANCE = "ACTION_ASSIGN_AGENT_SERVICE_BALANCE";
    public static final String ACTION_ADD_CUSTOMER = "ACTION_ADD_CUSTOMER";
    public static final String ACTION_RESET_PASSWORD = "ACTION_RESET_PASSWORD";
    public static final String ACTION_ASIGN_CUSTOMER_BALANCE = "ACTION_ASIGN_CUSTOMER_BALANCE";
    public static final String ACTION_VIEW_ALL_SERVICES = "ACTION_VIEW_ALL_SERVICES";
    public static final String ACTION_MANAGE_ROLES = "ACTION_MANAGE_ROLES";
    public static final String ACTION_MANAGE_AGENT = "ACTION_MANAGE_AGENT";
    public static final String ACTION_MANAGE_ADMIN = "ACTION_MANAGE_ADMIN";
    public static final String ACTION_MANAGE_ADMIN_BALANCE = "ACTION_MANAGE_ADMIN_BALANCE";
    public static final String ACTION_REFUND = "ACTION_REFUND";
    public static final String ACTION_CHANGE_EMPLOYEE_STATE = "ACTION_CHANGE_EMPLOYEE_STATE";
    public static final String ACTION_SERVICE_OPERATIONS = "ACTION_SERVICE_OPERATIONS";
    public static final String ACTION_SERVICE_PRICE = "ACTION_SERVICE_PRICE";
    public static final String ACTION_ASIGN_CUSTOMER_BILL = "ACTION_ASIGN_CUSTOMER_BILL";
    public static final String ACTION_CUSTOMER_PAY_BILL = "ACTION_CUSTOMER_PAY_BILL";
    public static final String ACTION_CUSTOMER_RECHARGE = "ACTION_CUSTOMER_RECHARGE";
    public static final String ACTION_CUSTOMER_RECHARGE_MOBILE = "ACTION_CUSTOMER_RECHARGE_MOBILE";
    public static final String ACTION_CUSTOMER_TOPUP = "ACTION_CUSTOMER_TOPUP";
    public static final String ACTION_RE_SEND = "ACTION_RE_SEND";
    public static final String ACTION_CUSTOMER_BULK_VOUCHER = "ACTION_CUSTOMER_BULK_VOUCHER";
    public static final String ACTION_UNIVERSITY_TOPUP = "ACTION_UNIVERSITY_TOPUP";
    public static final String ACTION_MAIN = "ACTION_MAIN";
    public static final String PAGE = "PAGE";
    public static final String LOGIN_IP = "LOGIN_IP";
    public static final String ACTION_LOCK = "LOCK";
    public static final String ACTION_BALANCE_MOBILE = "ACTION_BALANCE_MOBILE";
    public static final String ACTION_CHANGE_LANG = "ACTION_CHANG_LANG";
    public static final String ACTION_CHANGE_LANG_MOBLIE = "ACTION_CHANGE_LANG_MOBLIE";
    public static final String PARAM_FIRST = "FIRST";
    public static final String PARAM_MONTH = "MONTH";
    public static final String PARAM_YEAR = "YEAR";
    public static final String PARAM_OUT = "OUT";
    public static final String PARAM_ADD_AGENT = "PARAM_ADD_AGENT";
    public static final String PARAM_AGENT_ID = "PARAM_AGENT_ID";
    public static String PARAM_EMPLOYEE_ID = "PARAM_EMPLOYEE_ID";
    public static final String PARAM_AUTHENTICATED = "PARAM_AUTHENTICATED";
    public static final String PARAM_ADMIN_BTN = "PARAM_ADMIN_BTN";
    public static final String PARAM_AUTO_ALLOCATE = "PARAM_AUTO_ALLOCATE";
    public static final String PARAM_ADMIN_PAGE = "PARAM_ADMIN_PAGE";
    public static final String PARAM_CUSTOMER_BTN = "PARAM_CUSTOMER_BTN";
    public static final String PARAM_LOGIN_ERROR = "LOGIN_ERROR";
    public static final String PARAM_AGENT_BTN = "PARAM_AGENT_BTN";
    public static final String PARAM_ACTION = "action";
    public static final String TONE_KEY = "TONEKEY";
    public static final String PARAM_FROM_MOBILE = "MOBILE";
    public static final String PARAM_SERVICE_ID = "SERVICE_ID";
    public static final String PARAM_OPERATIONTYPE = "OPERATION_TYPE";
    public static final String PARAM_SERVICE_VALUE = "SERVICE_VALUE";
    public static final String PARAM_SERVICES = "SERVICES";
    public static final String PARAM_SERVICE = "SERVICE";
    public static final String PARAM_SERVICE_NAME = "SERVICENAME";
    public static final String PARAM_SERVICE_BALANCE = "SERVICEBALANCE";
    public static final String PARAM_PIN = "PIN";
    public static final String PARAM_ENC_ID = "ENCRYPTED_ID";
    public static final String PARAM_ENC_PASS = "ENCRYPTED_PASS";
    public static final String PARAM_ADDRESS1 = "ADDRESS1";
    public static final String PARAM_ADDRESS2 = "ADDRESS2";
    public static final String PARAM_COUNTRY = "COUNTRY";
    public static final String PARAM_GOVERNORTE = "GOVERNORTE";
    public static final String PARAM_CITY = "CITY";
    public static final String DONE_en = "Done";
    public static final String DONE_ar = "تم";
    public static final String PARAM_POSTAL_CODE = "POSTAL_CODE";
    public static final String PARAM_TRANSACTION_TYPE = "TRANSACTION_TYPE";
    public static final String PARAM_SERVICE_TYPE = "SERVICE_TYPE";
    public static final String PARAM_PAYER_PIN = "PAYERPIN";
    public static final String PARAM_PAYED_PIN = "PAYEDPIN";
    public static final String PARAM_USERNAME = "USERNAME";
    public static final String PARAM_Activation_Code = "ActivationCode";
    public static final String PARAM_SSO = "SSO";
    public static final String PARAM_USERNAME_ARABIC = "USERNAME_ARABIC";
    public static final String PARAM_FIELD = "PARAM_FIELD";
    public static final String PARAM_VALUE = "PARAM_VALUE";
    public static final String PARAM_USER_ID = "id";
    public static final String PARAM_PASSWORD2 = "PASSWORD10";
    public static final String PARAM_PASSWORD = "PASSWORD";
    public static final String PARAM_NEW_PASSWORD = "newpassword";
    public static final String PARAM_OLD_PASSWORD = "oldpassword";
    public static final String PARAM_CONFIRM_PASSWORD = "PARAM_CONFIRM_PASSWORD";
    public static final String PARAM_MSISDN = "MSISDN";
    public static final String PARAM_MSISDN_ALTERNATIVE = "MSISDN_ALTERNATIVE";
    public static final String PARAM_VOUCHERNO = "VOUCHERNO";
    public static final String PARAM_LAND_LINE_PHONE = "LAND_LINE_PHONE";
    public static final String PARAM_EMAIL_ADDRESS = "EMAIL_ADDRESS";
    public static final String PARAM_CONFIRM_EMAIL_ADDRESS = "CONFIRM_EMAIL_ADDRESS";
    public static final String PARAM_EMAIL_ADDRESS_ALTERNATIVE = "EMAIL_ADDRESS_ALTERNATIVE";
    public static final String PARAM_CONFIRM_EMAIL_ADDRESS_ALTERNATIVE = "CONFIRM_EMAIL_ADDRESS_ALTERNATIVE";
    public static final String PARAM_BIRTH_DATE = "BIRTH_DATE";
    public static final String PARAM_NATIONAL_ID = "NATIONAL_ID";
    public static final String PARAM_PREFERRED_LANGUAGE = "PREFERRED_LANGUAGE";
    public static final String OPERATOR_ID = "operatorID";
    public static final String PARAM_TIMESTAMP = "TIMESTAMP";
    public static final String PARAM_SENDER_TRX_ID = "SENDERTRX";
    public static final String PARAM_TRX_ID = "TRXID";
    public static final String PARAM_TRX_TYPE = "TRXTYPE";
    public static final String FROM_NUMBER = "fromNum";
    public static final String ORIGINATING_COUNTRY = "originating_country";
    public static final String DESTINATION_COUNTRY = "destination_country";
    public static final String PARAM_TEXT = "TEXT";
    public static final String PARAM_OPERATOR_ID = "OPERATOR_ID";
    public static final String PARAM_SHORT_CODE = "SHORT_CODE";
    public static final String PARAM_ENCODING = "ENCODING";
    public static final String PARAM_STUDENT_ID = "STUDENT_ID";
    public static final String PARAM_TO = "to";
    public static final String PARAM_CODE_ID = "codeID";
    public static final String PAYMENT_REFERENCE_CODE = "prcode";
    public static final String PARAM_AREA_CODE = "AREA_CODE";
    public static final String PARAM_NAME = "NAME";
    public static final String PARAM_PHONE = "PHONE";
    public static final String PARAM_QUESTION = "QUESTION";
    public static final String PARAM_SEESSIONTIME = "SEESSIONTIME";
    public static final String PARAM_ANSWER = "ANSWER";
    public static final String PARAM_ANSWER_CAPTCHA = "PARAM_ANSWER_CAPTCHA";
    public static final String PARAM_BALANCE = "BALANCE";
    public static final String PARAM_PROVIDER_ID = "PARAM_PROVIDER_ID";
    public static final String PARAM_CATEGORY_ID = "PARAM_CATEGORY_ID";
    public static final String PARAM_CUSTOMER_ID = "PARAM_CUSTOMER_ID";
    public static final String PARAM_Transaction_ID = "TransID";
    public static final String PARAM_TAX_AMOUNT = "TAX_AMOUNT";
    public static final String PARAM_VOUCHER = "VOUCHER";
    public static final String PARAM_SELL_TYPE = "Sell_Type";
    public static final String ASSIGN = "ASSIGN";
    public static final String MANAGE = "MANAGE";
    public static final String PAY = "PAY";
    //----------------------------------------Vodafone Cash--------------------------------
    public static final String VODAFONE_CASH = "VODAFONE_CASH";
    public static final String BlaBlaTopUp = "BlaBlaTopUp";
    public static final String BlaBlaTopUpResult = "BlaBlaTopUpResult";
    public static final String BlaBlaTopUpConfirmation = "BlaBlaTopUpConfirmation";
    public static final String CHECKOUT = "CHECKOUT";
    public static final String CHECKIN = "CHECKIN";
    public static final String CHECKIN_CONFIRMATION = "CHECKIN_CONFIRMATION";
    public static final String CHECKIN_RECEIPT = "CHECKIN_RECEIPT";
    public static final String CHECKIN_Customer_Info = "CHECKIN_Customer_Info";
    public static final String CHECKOUT_Customer_Info = "CHECKOUT_Customer_Info";
    public static final String CHECKOUT_CONFIRMATION = "CHECKOUT_CONFIRMATION";
    public static final String TRANSACTION_INQUIRY = "TRANSACTION_INQUIRY";
    public static final String TRANSACTION_INQUIRY_FROM_OPERATIONS = "TRANSACTION_INQUIRY_FROM_OPERATIONS";
    public static final String PRINT_RECEIPT_FROM_REPORT = "PRINT_RECEIPT_FROM_REPORT";
    public static final String VC_NAVIGATOR = "VC_NAVIGATOR";
    public static final String VC_Cancel_Transaction = "VC_Cancel_Transaction";
    public static final String PARAM_OPERATION_TYPE = "OPERATION_TYPE";
    public static final String PARAM_OPERATION_ID = "OPERATION_ID";
    public static final String PARAM_Land_Line = "Land_Line";
    public static final String PARAM_MSISDN_CONFIRMATION = "MSISDN_CONFIRMATION";
    public static final String PAGE_BillPayment_Result = "bill/BillPayment_Result.jsp";
    public static final String PAGE_VODAFONE_CASHIN = "eMoney/VC_CashIn.jsp";
    public static final String PAGE_BlaBla_TopUp = "service/BlaBla/BlaBlaTopUp.jsp";
    public static final String PAGE_BlaBla_TopUp_Confirmation = "service/BlaBla/BlaBlaTopupConfirmation.jsp";
    public static final String PAGE_BlaBla_TopUp_Result = "service/BlaBla/BlaBlaTopUpResult.jsp";
    public static final String PAGE_VODAFONE_CASHOUT = "eMoney/VC_CashOut.jsp";
    public static final String PAGE_VODAFONE_TXN_INQUIRY = "eMoney/VC_TXN_Inquiry.jsp";
    public static final String PAGE_VC_CashIn_Confirmation = "eMoney/VC_CashIn_Confirmation.jsp";
    public static final String PAGE_VC_CashIn_Customer_Info = "eMoney/VC_CashIn_Customer_Info.jsp";
    public static final String PAGE_VC_CashOut_Customer_Info = "eMoney/VC_CashOut_Customer_Info.jsp";
    public static final String PAGE_VC_Cash_Out_Confirmation = "eMoney/VC_CashOut_Confirmation.jsp";
    public static final String PAGE_VC_CashIn_Result = "eMoney/VC_CashIn_Result.jsp";
    public static final String PAGE_VC_Check_Out_Result = "eMoney/VC_CheckOut_Result.jsp";
    public static final String PAGE_Vodafone_Cash_RECEIPT = "eMoney/VC_Receipt.jsp";
    //-----------------------------bill(25-06)---------------------------------
    public static final String ACTION_Get_Balance_Sheet_Men = "Get_Balance_Sheet_Men";
    public static final String ACTION_Get_Pin_Change_Men = "Get_Pin_Change_Men";
    public static final String CUSTOMERBillen = "Make Bill check";
    public static final String CUSTOMERBillar = "الإستعلام عن ";
    public static final String CUSTOMERDonationen = "Donation Information";
    public static final String CUSTOMERDonationsar = "معلومات التبرع";
    public static final String Univ_BillHeaden = "Tuitions inquiry for ";
    public static final String Univ_BillHeadar = "الاستعلام عن مصاريف";
    public static final String Univ_Bill_Conf_Headen = "Tuitions inquiry confirmation for ";
    public static final String Univ_Bill_Conf_Headar = " تأكيد الاستعلام عن مصاريف";
    public static final String ACTION_Get_Billers = "Get_Billers";
    public static final String ACTION_Get_Balance_Sheet = "Get_Balance_Sheet";
    public static final String ACTION_ADD_BTC_CONFIRM = "ADD_BTC_CONFIRM";
    public static final String Biller_Nameen = "Biller Name :";
    public static final String Biller_Namear = "أسم المفوتر:";
    public static final String Bill_Nameen = "Bill Name :";
    public static final String Bill_Namear = "أسم الفاتوره:";
    public static final String PMT_Typeen = "PMT Type :";
    public static final String PMT_Typear = "نوع الدفع :";
    public static final String SERVICE_TYPEen = "SERVICE TYPE :";
    public static final String SERVICE_TYPEar = "نوع الخدمه:";
    private final static String ARSERVICENAMEar = "اسم الخدمه عربى";
    private final static String ARSERVICENAMEen = "Service Arabic name";
    public static final String BILL_LABLEen = "BILL LABLE :";
    public static final String BILL_LABLEar = "تسميه الفاتوره :";
    public static final String ARBILL_LABLEen = "BILL Arabic LABLE :";
    public static final String ARBILL_LABLEar = "تسميه الفاتوره عربى :";
    public static final String Partialen = "Part";
    public static final String Partialar = "جزء";
    public static final String Overen = "Over";
    public static final String Overar = "زياده";
    public static final String Fractionen = "Fraction";
    public static final String Fractionar = "كسور";
    public static final String Advanceden = "Advanced";
    public static final String Advancedar = "مقدم";
    public static final String ACTION_payment_Inquiry_Req_Confirm = "payment_Inquiry_Req_Confirm";
    public static final String ACTION_payment_Inquiry_Req_Confirm_Old = "payment_Inquiry_Req_Confirm_Old";
    public static final String ACTION_payment_Inquiry_Req = "payment_Inquiry_Req";
    public static final String ACTION_payment_Inquiry_Req_Old = "payment_Inquiry_Req_Old";
    public static final String Bill_TYPE = "Bill_TYPE";
    public static final String CUSTOMERConfBillen = "Confirm payment bill ";
    public static final String CUSTOMERConfBillar = "تأكيد دفع ";
    private final static String paymenten = "Payment";
    private final static String paymentar = "دفع";
    private final static String Feesar = "تكلفة الخدمة";
    private final static String Feesen = "Service Fees";
    private final static String LowerAmounten = "Lower Amount";
    private final static String LowerAmountar = "القيمة الصغرى";
    private final static String UpperAmounten = "Upper Amount";
    private final static String UpperAmountar = "القيمة العظمى";
    private final static String Dateen = "Date";
    private final static String Datear = "التاريخ";
    private final static String RequestIden = "Request Id";
    private final static String RequestIdar = "رقم الطلب";
    private final static String BillRefNumen = "Bill Reference No";
    private final static String BillRefNumar = "رقم الفاتوره";
    private final static String DuDateen = "DuDate";
    private final static String TodayDateen = "Date Today ";
    private final static String TodayDatear = "تاريخ اليوم";
    private final static String GrossProfitar = "إجمالى الأرباح";
    private final static String GrossProfiten = "Gross Profit";
    private final static String TransNumen = "Transactions Number";
    private final static String TransNumar = "عدد العمليات";
    private final static String DuDatear = "تاريخ الفاتورة";
    private final static String ExpDuDateen = "Expiry Date";
    private final static String ExpDuDatear = "تاريخ إنتهاء الفاتورة";
    private final static String BillStatusen = "Bill Status";
    private final static String BillStatusar = "حاله الفاتوره";
    private final static String BillInquiryStatusen = "Bill Inquiry Status";
    private final static String BillInquiryStatusar = "حاله الاستعلام";
    public static final String PaymentOptionen = "Payment Options :";
    public static final String PaymentOptionar = "خيارات الدفع";
    public static final String ACTION_Get_Bill_Inquiry_Res = "Get_Bill_Inquiry_Res";

    public static final String ACTION_Resharge_Payment = "ACTION_Resharge_Payment";
    public static final String ACTION_Get_Recharge_Inquiry_Res = "ACTION_Get_Recharge_Inquiry_Res";
    private static String BillRecieptMessEn = "Payment done successfully. Print bill now or later via Reports section.";
    private static String voucherMessageEn2 = "In the next page, the voucher will be printed automatically through your default printer, so, check your settings";
    private static String BillRecieptMessAr = "لقد تمت عملية الدفع بنجاح و يمكنك طبع الفاتورة الان او لاحقا عن طريق قائمة العمليات";
    public static final String ACTION_Get_Bill_Transaction = "Get_Bill_Transaction";
    private final static String payeren = "payer";
    private final static String Payedar = "العميل";
    private final static String payerar = "البائع";
    private final static String Payeden = "Payed";
    private final static String custIdar = "رقم المحفظة";
    private final static String custIden = "Customer ID";
    private final static String Servicear = "الخدمه";
    private final static String Serviceen = "Service";
    public static final String ACTION_Print_Reciept = "Print_Reciept";
    public static final String ACTION_EXPORT_TO_EXCEL = "EXPORT_TO_EXCEL";
    public static final String ACTION_EXPORT_TO_EXCEL_1 = "EXPORT_TO_EXCEL_1";
    public static final String ACTION_Print_Vouvher = "Print_Vouvher";
    public static final String ACTION_Print_Vouvher_2 = "Print_Vouvher_2";
    public static final String ACTION_ADD_BTC = "ADD_BTC";
    private static final String Adden = "Add";
    private static final String Addar = "اضف";
    public static final String PAGE_Balance_Sheet = "bill/Balance_Sheet.jsp";
    public static final String PAGE_bill_inquiry = "bill/bill_inquiry.jsp";

    public static final String PAGE_bill_info = "bill/bill_info.jsp";
    public static final String PAGE_bill_info_old = "bill/bill_info_old.jsp";
    public static final String PAGE_bill_payment_confirmation_old = "bill/bill_payment_confirmation_old.jsp";
    public static final String PAGE_bill_payment_confirmation = "bill/bill_payment_confirmation.jsp";
    public static final String PAGE_bill_Recipt = "bill/ReciptBills.jsp";
    public static final String PAGE_OrangeDslRecipt = "bill/OrangeDslReciept.jsp";
    public static final String PAGE_Telecom_Rechsrge_inquiry = "service/Telecom/Telecom_Recharge_inquiry.jsp";
//    public static final String PAGE_Telecom_Rechsrge_info = "service/Telecom/Telecom_Resharge_inquiry.jsp";
    public static final String Telecom_PAYMENT_PAGE = "service/Telecom/Telecom_Recharge.jsp";
    public static final String Telecom_EGYPT_RECHARGEAR = "شحن التليفون الارضي (تليفونت)";
    public static final String Telecom_EGYPT_RECHARGEEN = "Telecom Egypt TopUp(telphonet)";
    public static final String PAGE_Telecom_Rechsrge_confirmation = "service/Telecom/Telecom_Recharge_confirmation.jsp";
    public static final String PAGE_BILL_TRANSACTIONS = "bill/bill_transactions.jsp";
    public static final String PAGE_Billers = "bill/billers.jsp";
    public static final String PAGE_reciept = "bill/reciept.jsp";
    public static final String PAGE_reprint = "bill/reprint.jsp";

    public static final String PAGE_recieptNew = "bill/reciept_New.jsp";
    public static final String ElectricityrecieptJSP = "bill/Electricityreciept.jsp";
    public static final String PAGE_PRINT_VOUCHER = "customer/ViewTransaction_1.jsp";
    public static final String PAGE_VIEW_VOUCHER_SERIAL = "customer/ViewVoucherSerials.jsp";
    public static final String PAGE_NewBiller = "bill/NewBiller.jsp";
    public static final String PAGE_PIN_CHANGE = "Pin_Change.jsp";
    private final static String PinChangeMSGar = "تم تغيير كلمه السر بنجاح ";
    private final static String PinChangeMSGen = "Pin have been changed successfuly";
    private static final String INVALIDInvalidPinen = "Invalid Pin";
    private static final String INVALIDInvalidPinar = "الرقم السرى غير صحيح";
    private static final String PinCONFIRMATIONERRORar = "كلمة السر والتاكيد غير متتطابقين";
    private static final String PinCONFIRMATIONERRORen = "Pin and confirmation doesn't match ";
    private static final String Bill_Notear = "تنبيه : ";
    private static final String Bill_Noteen = "Notification : ";
    public static final String Balanc_Messageen = "You don't have enough balance";
    public static final String Mobile_ErorrEN = "Enter another phone number";
    public static final String Mobile_ErorrAR = "من فضلك ادخل رقم موبيل آخر";
    public static final String Balanc_Messagear = "رصيدك لا يكفى";
    private static final String TotalAmounten = "Total Amount";
    private static final String TotalAmountar = "القيمة الاجماليه";
    private static final String TotalAmountWithTaxen = "Total Amount";
    private static final String TotalAmountWithTaxar = "اجمالي القيمه المطلوبه شاملة تكلفة الخدمه وضريبة القيمة المضافة ";
    private static final String BillPayedMsgen = "Bill payed or error in bill information";
    private static final String BillPayedMsgar = "الفاتوره مدفوعه او انه يوجد خطأ فى بيانات الفاتوره";
    private static final String BillErrorCodeen = "Error :";
    private static final String BillErrorCodear = "خطأ :";
    private static final String CustomerNameen = "Customer Name :";
    private static final String CustomerNamear = " اسم العميل";
    public static final String ACTION_Get_Pin_Change = "Get_Pin_Change";
    public static final String PARAM_OLD_PIN = "OLD_PIN";
    public static final String PARAM_NEW_PIN = "NEW_PIN";
    public static final String PARAM_CONFIRM_NEW_PIN = "CONFIRM_NEW_PIN";
    public static final String ACTION_CUSTOMER_Bill_Inquiry = "ACTION_CUSTOMER_Bill_Inquiry";
    public static final String BILL_PAYMENT_PAGE = "bill/bill_payment.jsp";
    public static final String BILL_PAYMENT_PAGE_OLD = "bill/bill_payment_old.jsp";
    //-----------------------------End-bill(25-06)---------------------------------
    //------------------------South Electricity-------------------------Begin
    private static final String memberNameen = "Customer Name :";
    private static final String memberNamear = " اسم المشترك";
    private static final String memberNumberen = "Member Number ";
    private static final String memberNumberar = " رقم المشترك";
    private static final String billDateen = "Bill Date:";
    private static final String billDatear = " تاريخ الاصدار";
    private static final String billAmounten = "Amount:";
    private static final String billAmountar = " قيمة الفاتورة";
    public static final String ACTION_Delta_Electricity_Inq = "ACTION_Delta_Electricity_Inq";
    public static final String ACTION_Delta_Electricity_pay = "ACTION_Delta_Electricity_pay";
    public static final String ACTION_Delta_Electricity_payConfirm = "ACTION_Delta_Electricity_payConfirm";
    public static final String ACTION_Electricity_CONFIRM = "ACTION_Electricity_CONFIRM";

    public static final String PAGE_Electricity_inquiry = "service/Electricity/bill_inquiry.jsp";
    public static final String PAGE_Electricity_Info = "service/Electricity/bill_inquiry.jsp";

    public static final String PAGE_Electricity_new_inquiry = "service/ElectricityAll/bill_info.jsp";
    public static final String PAGE_ElectricityInquiry = "service/ElectricityAll/bill_inquiry.jsp";
    public static final String PAGE_electricityConfirmation_inquiry = "service/ElectricityAll/electricityConfirmation.jsp";
    public static final String PAGE_ElectricityPayment = "service/ElectricityAll/ElectricityPayment.jsp";
    public static final String PAGE_ElectricityReprint = "service/ElectricityAll/ReprintPage.jsp";

    private static final String balanceCurrentEn = "Your Current Balance:";
    private static final String balanceCurrentAr = "رصيدك الحالي : ";
    private static final String discountEn = "has been discount";
    private static final String discountAr = "تم خصم :  ";
    public final static String INFORNEEDED = "البيانات المطلوبة";

    private static final String electricityAlexandriaEn = "Alexandria Electricity Distribution Company";
    private static final String electricityAlexandriaAr = "شركة الأسكندرية لتوزيع الكهرباء";
    private static final String electricityBehiraEn = "Behira Co. For Electricity Distribution";
    private static final String electricityBehiraAr = "شركة البحيرة لتوزيع الكهرباء";
    private static final String electricityNorthEn = "North Cairo Electricity Distribution Company";
    private static final String electricityNorthAr = "شركة شمال القاهرة لتوزيع الكهرباء  ";
    private static final String electricitySouthDeltaEn = "South Delta Electricity";
    private static final String electricitySouthDeltaAr = "شركة جنوب الدلتا لتوزيع الكهرباء  ";
    private static final String electricityCanalEn = "Canal Electricity";
    private static final String electricityCanalAr = "شركة القناة لتوزيع الكهرباء  ";
    private static final String electricityNorthDeltaEn = "North Delta Electricity";
    private static final String electricityNorthDeltaAr = "شركة شمال الدلتا لتوزيع الكهرباء  ";

    private static final String electricityMiddleEgyptEn = "Middle Egypt Electricity";
    private static final String electricityMiddleEgyptAr = "شركة مصر الوسطى لتوزيع الكهرباء";
    
    private static final String electricitySouthCairoEn = "South Cairo Electricity";
    private static final String electricitySouthCairoAr = "شركة جنوب القاهرة";

    public static final String electricityUpperEgyptAr = "شركة مصر العليا لتوزيع الكهرباء";
    public static final String electricityUpperEgyptEn = "Upper Egypt Electricity";

    public static String getElectricityServiceName(HttpSession session) {

        if (session.getAttribute("SERVICE_ID").equals("99019")) {
            if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
                return electricityAlexandriaAr;
            }
            return electricityAlexandriaEn;
        } else if (session.getAttribute("SERVICE_ID").equals("99021")) {
            if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
                return electricityNorthAr;
            }
            return electricityNorthEn;
        } else if (session.getAttribute("SERVICE_ID").equals("99022")) {
            if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
                return electricityBehiraAr;
            }
            return electricityBehiraEn;
        } else if (session.getAttribute("SERVICE_ID").equals("99014")) {
            if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
                return electricityCanalAr;
            }
            return electricityCanalEn;
        } else if (session.getAttribute("SERVICE_ID").equals("99015")) {
            if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
                return electricityNorthDeltaAr;
            }
            return electricityNorthDeltaEn;
        } else if (session.getAttribute("SERVICE_ID").equals("99018")) {
            if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
                return electricitySouthDeltaAr;
            }
            return electricitySouthDeltaEn;
        } else if (session.getAttribute("SERVICE_ID").equals("99013")) {
            if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
                return electricityUpperEgyptAr;
            }
            return electricityUpperEgyptEn;
        } else if (session.getAttribute("SERVICE_ID").equals("99012")) {
            if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
                return electricityMiddleEgyptAr;
            }
            return electricityMiddleEgyptEn;
        }else if (session.getAttribute("SERVICE_ID").equals("99007")) {
            if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
                return electricitySouthCairoAr;
            }
            return electricitySouthCairoEn;
        }

        return "دفع فواتير الكهرباء";
    }

    public static final String PAGE_Electricity_info = "service/Electricity/bill_info.jsp";
    public static final String PAGE_Electricity_PAYMENT = "service/Electricity/bill_payment.jsp";
    public static final String PAGE_Elect_PAY_confirm = "service/Electricity/bill_payment_confirmation.jsp";

    public static final String billNotFoundMessage = "لا توجد فواتير مستحقة";
    public static final String accountInactiveMessage = "الحساب الخاص بك مقفول حاليا";
    public static final String InternalServerErrorMessage = "الخدمة غير متاحةبرجاء التواصل مع خدمة عملاء مصاري";
    public static final String serviceIsInactiveMessage = "الخدمة موقوفه الان برجاء التجربة في وقت لاحق";
    public static final String serviceIsInactiveAccountMessage = "الخدمة غير متاحة لهذا الحساب";
    public static final String transactionAmountnotInserviceRangeMessage = "هذه القيمة غير متاحة لهذه الخدمة";
    public static final String serviceIsNotAvailableMessage = "الخدمة غير متاحةبرجاء التواصل مع خدمة عملاء مصاري";
    public static final String globalTrxIdIsMissingMessage = "خطا في العملية برجاء التواصل مع خدمة عملاء مصاري";
    public static final String ledgerTransactionExceptionMessage = "خطا في العملية برجاء التواصل مع خدمة عملاء مصاري";
    public static final String inputParameterIsMissingMessage = "خطا في العملية برجاء التواصل مع خدمة عملاء مصاري";
    public static final String deviceTypeIsMissingMessage = "خطا في العملية برجاء التواصل مع خدمة عملاء مصاري";
    public static final String commonMasaryExceptionMessage = "خطا في العملية برجاء التواصل مع خدمة عملاء مصاري";
    public static final String rollBackExceptionMessage = "خطا في العملية برجاء التواصل مع خدمة عملاء مصاري";
    public static final String accountBalanceIsNotsuffcientMessage = "ليس لديك رصيد كافي";
    public static final String BillIsAlreadyPayedMessage = "الفاتورة المستحقة مدفوعه مسبقا";
    private static final String FEESMESSAGEar = "لاحظ ان : رسوم التحويل اعلاه هى 1% من قيمة المبلغ المراد تحويله وسيتم خصمها من رصيدك";
    private static final String FEESMESSAGEen = "1% fees of the transfer amount would be deducted from balance.";

    /*petrotrade counter reading */
    public static final String ACTION_INQUIRY_PETROTRADE_CounterReading = "ACTION_INQUIRY_PETROTRADE_CounterReading";
    public static final String ACTION_GETINFO_PETROTRADE_COUNTERREADING = "ACTION_GETINFO_PETROTRADE_CounterReading";
    public static final String ACTION_PAYMENT_PETROTRADE_COUNTERREADING = "ACTION_PAYMENT_PETROTRADE_CounterReading";
    public static final String PAGE_PETROTRADE_CounterReading_Inquiry = "/service/PetrotradeCounterReading/PetrotradeCounterReadingInquiry.jsp";
    public static final String PAGE_PETROTRADE_CounterReading_Info = "/service/PetrotradeCounterReading/PetrotradeCounterReadingInfo.jsp";
    public static final String PAGE_PETROTRADE_CounterReading_Payment = "/service/PetrotradeCounterReading/PetrotradeCounterReadingPayment.jsp";
    private static final String PETROTRADE_Counter_Reading_INQUIRYAr = "قراءة العداد - بتروتريد";
    private static final String PETROTRADE_Counter_Reading_INQUIRYEN = " Counter Reading -Petrotrade";
    private static final String PETROTRADE_Counter_Reading_INFOAr = "تسجيل القراءة";
    private static final String PETROTRADE_Counter_Reading_INFOEN = " Counter Reading Registration ";
    private static final String PETROTRADE_Counter_Reading_REQIREDINFOAr = " معلومات مطلوبة";
    private static final String PETROTRADE_Counter_Reading_REQIREDINFOEN = " Required Information ";
    private static final String PETROTRADE_Counter_Reading_CUSTOMERNAMEAr = " برجاء ادخال اسم العميل فقط";
    private static final String PETROTRADE_Counter_Reading_CUSTOMERNAMEEN = "Please Enter Customer Name Only ";
    private static final String PETROTRADE_Counter_Reading_CURRENTREADINGAr = "القراءة الحالية";
    private static final String PETROTRADE_Counter_Reading_CURRENTREADINGEN = "Current Reading ";
    private static final String PETROTRADE_Counter_Reading_CONFIRMATIONAr = "تأكيد";
    private static final String PETROTRADE_Counter_Reading_CONFIRMATIONEN = "Confirmation ";

    public static String getPetrotradeCounterReadingInquiry(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PETROTRADE_Counter_Reading_INQUIRYAr;
        }
        return PETROTRADE_Counter_Reading_INQUIRYEN;
    }

    public static String getPetrotradeCounterReadingInfo(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PETROTRADE_Counter_Reading_INFOAr;
        }
        return PETROTRADE_Counter_Reading_INFOEN;
    }

    public static String getPetrotradeCounterReadingRequiredInfo(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PETROTRADE_Counter_Reading_REQIREDINFOAr;
        }
        return PETROTRADE_Counter_Reading_REQIREDINFOEN;
    }

    public static String getCurrentReading(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PETROTRADE_Counter_Reading_CURRENTREADINGAr;
        }
        return PETROTRADE_Counter_Reading_CURRENTREADINGEN;
    }

    public static String getPetrotradeCounterReadingCustomerName(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PETROTRADE_Counter_Reading_CUSTOMERNAMEAr;
        }
        return PETROTRADE_Counter_Reading_CUSTOMERNAMEEN;
    }

    public static String getConfirmation(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PETROTRADE_Counter_Reading_CONFIRMATIONAr;
        }
        return PETROTRADE_Counter_Reading_CONFIRMATIONEN;
    }

    /* petrotrade */
    public static final String ACTION_INQUIRY_PETROTRADE = "ACTION_INQUIRY_PETROTRADE";
    public static final String ACTION_GETINFO_PETROTRADE = "ACTION_GETINFO_PETROTRADE";
    public static final String PAGE_PETROTRADE_HOME = "service/petrotrade/PetrotradeHomePage.jsp";
    public static final String PAGE_PETROTRADE_INFO = "service/petrotrade/PetrotradeInfoPage.jsp";
    public static final String PAGE_PETROTRADE_PAYMENT = "service/petrotrade/PetrotradePaymentPage.jsp";
    public static final String PAGE_PETROTRADE_PAYMENT_CONFIRMATION = "service/petrotrade/PetrotradePaymentConfirmationPage.jsp";
    public static final String PAGE_PETROTRADE_BILLS_SUGGESTATION = "service/petrotrade/PetrotradeSuggestbillsPage.jsp";
    public static final String PAGE_PETROTRADE_Reprint = "bill/Petrotrade_Reprint.jsp";

    private static final String PETROTRADE_INQUIRYAr = "الاستعلام عن الفواتير - بترو تريد ";
    private static final String PETROTRADE_INQUIRYEN = " Petrotrade Inquiry";
    private static final String PETROTRADE_SUGGESTBILLSAr = "اقرب القيم للقيمة المقترحة ";
    private static final String PETROTRADE_SUGGESTBILLSEN = "the nearest Amount";
    private static final String PETROTRADE_INQUIRY_INFOAr = "بيانات الفواتير ";
    private static final String PETROTRADE_INQUIRY_INFOEN = " Bills info";
    private static final String ThetotalbillsamountAr = "اجمالي قيمة الفواتير";
    private static final String ThetotalbillsamountEn = "Total amount paid in pounds";
    public static final String TotalAmountway = "دفع كلي";
    public static final String partAmountWay = "القيمة المراد دفعها(دفع جزئي)";
    public static final String TotalAmountwayar = "دفع كلي";
    public static final String TotalAmountwayen = "Total amount";
    public static final String PartAmountwayar = "القيمة المراد دفعها (دفع جزئى)";
    public static final String PartAmountwayen = "Part amount";
    public static final String NUMBER_OF_BILLSar = "عدد الفواتير المستحقه";
    public static final String NUMBER_OF_BILLSen = "Number of Bills";
    public static final String TANSACTION_DATEar = "تاريخ العمليه";
    public static final String TANSACTION_DATEen = "transaction Date";
    public static final String TANSACTION_TIMEar = "توقيت العمليه";
    public static final String TANSACTION_TIMEen = "transaction time";
    public static final String SumOfTaxAndFeesar = "تكلفه الخدمة شامله ضريبه القيمة المضافه";
    public static final String SumOfTaxAndFeesen = "Fees + Tax";
    public static final String ERROR_PETROTRADE_AmountOutOfRangeExceptionAr = "عفوا العملية غير مقبولة برجاء العلم ان اقل قيمة للدفع";
    public static final String ERROR_PETROTRADE_AmountOutOfRangeExceptionEn = "Amount is out of range";
    public static final String ERROR_PETROTRADE_ProviderNoBillFoundExceptionAr = "رقم المشترك الذي قمت بإدخاله غير صحيح، برجاء التأكد من الرقم وإعادة المحاولة مرة أخرى";
    public static final String ERROR_PETROTRADE_ProviderNoBillFoundExceptionEn = "No bill found for this number";
    public static final String ERROR_PETROTRADE_CallingProviderDBExceptionAr = "تعذر الإتصال بمقدم الخدمة بتروتريد في الوقت الحالي، يرجى إعادة المحاولة في وقت لاحق";
    public static final String ERROR_PETROTRADE_CallingProviderDBExceptionEn = "Calling provider DB";
    public static final String ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionAr = "لا يمكنك الدفع حاليا الرجاء المحاولة لاحقا";
    public static final String ERROR_PETROTRADE_ProviderPaymentFailureExceptionExceptionEn = "Payment failure try later";
    public static final String ERROR_PETROTRADE_AmountLessThanExceptionAr = "عفوا العملية غير مقبولة برجاء العلم ان اقل قيمة للدفع هي قيمة اقل فاتورة";
    public static final String ERROR_PETROTRADE_AmountLessThanExceptionEn = "Amount is less than needed amount";
    public static final String ERROR_PETROTRADE_AmountBiggerThanExceptionAr = "عفوا العملية غير مقبولة برجاء العلم ان اكبر قيمة للدفع هي اجمالي الدفع الفواتير";
    public static final String ERROR_PETROTRADE_AmountBiggerThanExceptionEn = "Amount is bigger than needed amount";
    public static final String ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionAr = "تعذر الإتصال بمقدم الخدمة بتروتريد في الوقت الحالي، يرجى إعادة المحاولة في وقت لاحق";
    public static final String ERROR_PETROTRADE_InvoicesNumberNotMatchExceptionEn = "Invoices numbers does not match the valid amount";
    public static final String ERROR_PETROTRADE_NoCustomerMatchExceptionAr = "رقم المشترك الذي قمت بإدخاله غير صحيح، برجاء التأكد من الرقم وإعادة المحاولة مرة أخرى";
    public static final String ERROR_PETROTRADE_NoCustomerMatchExceptionEn = "No customer found for this number";

    public static final String ERROR_PETROTRADE_BadParameterAr = "برجاء ادخال القيم صحيحة";
    public static final String ERROR_PETROTRADE_BadParameterEn = "Enter a valid reading";
    public static final String ERROR_PETROTRADE_ReadingRegisteredExceptionAr = "لا يمكن تسجيل قراءة العداد الآن بناءً على طلب الشركة";
    public static final String ERROR_PETROTRADE_ReadingRegisteredExceptionEn = "Please try again";
    public static final String ERROR_PETROTRADE_RegisterationReadingNotValidExceptionAr = "عفوا القراءة المدخلة غير صحيحة,برجاء التأكد من ادخال القراءة من واقع المسجل فى العداد";
    public static final String ERROR_PETROTRADE_RegisterationReadingNotValidExceptionEn = "Please check the right registration reading";

    public static final String ERROR_PETROTRADE_CommonExceptionAr = "خطأ اثناء تنفيذ العملية , الخدمة غير متاحة";
    public static final String ERROR_PETROTRADE_CommonExceptionEn = "Service unavialable";

    public static String getTransactionDate(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TANSACTION_DATEar;
        }
        return TANSACTION_DATEen;
    }

    public static String getTransactionTime(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TANSACTION_TIMEar;
        }
        return TANSACTION_TIMEen;
    }

    public static String getPetrotradeSuggestationBill(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PETROTRADE_SUGGESTBILLSAr;
        }
        return PETROTRADE_SUGGESTBILLSEN;
    }

    public static String getPetrotradeInquiry(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PETROTRADE_INQUIRYAr;
        }
        return PETROTRADE_INQUIRYEN;
    }

    public static String getFeesAndTax(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SumOfTaxAndFeesar;
        }
        return SumOfTaxAndFeesen;
    }

    public static String getNumberOfBills(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NUMBER_OF_BILLSar;
        }
        return NUMBER_OF_BILLSen;
    }

    public static String getPaymentWaytotal(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERNumCashUar;
        }
        return CUSTOMERNameCashUen;
    }

    public static String getTotalBillsAmount(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ThetotalbillsamountAr;
        }
        return ThetotalbillsamountEn;
    }

    public static String getPetrotradeInquiryInfo(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PETROTRADE_INQUIRY_INFOAr;
        }
        return PETROTRADE_INQUIRY_INFOEN;
    }

    public static String getPetrotradeTotalAmountWay(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TotalAmountwayar;
        }
        return TotalAmountwayen;
    }

    public static String getPetrotradePartAmountWay(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PartAmountwayar;
        }
        return PartAmountwayen;
    }

    /*petrotrade*/
    public static String getMemberName(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return memberNamear;
        }
        return memberNameen;
    }

    public static String getDiscountName(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return discountAr;
        }
        return discountEn;
    }

    public static String getBalanceCurrentName(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return balanceCurrentAr;
        }
        return balanceCurrentEn;
    }

    public static String getbillAmount(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return billAmountar;
        }
        return billAmounten;
    }

    public static String getMemberNumber(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return memberNumberar;
        }
        return memberNumberen;
    }

    public static String getbillDate(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return billDatear;
        }
        return billDateen;
    }
    //------------------------------------------------------------------End
    //-----------------------------Tony-Mobinil-bill(17-10)-Begin--------------------------------
    public static final String PAGE_Mobinil_Bill_Payment = "bill/Mobinil_bill_payment.jsp";
    public static final String PAGE_Mobinil_Bill_Payment_Confirm = "bill/Mobinil_bill_payment_confirmation.jsp";
    public static final String PAGE_Mobinil_Bil_Reciept = "bill/Mobinil_Bil_Reciept.jsp";
    public static final String ACTION_Do_Bill_Payment = "Do_Bill_Payment";
    public static final String ACTION_Do_Mobinil_Bill_Payment_Confirm = "Do_Mobinil_Bill_Payment_Confirm";
    public static final String CUSTOMERBill_Paymenten = "Payment";
    public static final String CUSTOMERBill_Paymentar = "دفع";
    private static final String PhonenumberMessageen = "The number that you want to pay its bill";
    private static final String PhonenumberMessagear = "رقم الهاتف المراد دفع فاتورتة";
    private static final String PaymentInstructionar = "لا تنسى التنبية على العميل ان الفاتورة سيتم دفعها خلال 24 ساعة";

    public static String getCUSTOMERBill_Payment(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERBill_Paymentar;
        }
        return CUSTOMERBill_Paymenten;
    }

    public static String getMaanRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessMaanAr;
        }
        return RecieptMessMaanEn;
    }

    public static String getMaanRecieptMess2(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessMaan2Ar;
        }
        return RecieptMessMaan2En;
    }

    public static String getTELECOMEGYPT_Resharge(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Telecom_EGYPT_RECHARGEAR;
        }
        return Telecom_EGYPT_RECHARGEEN;
    }

    public static String getfoodbankRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessfoodbankAr;
        }
        return RecieptMessfoodbankEn;
    }

    public static String getElzakHomeRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessElzakHomeAr;
        }
        return RecieptMessElzakHomeEn;
    }

    public static String getOrangeDSLRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessOrangDslAr;
        }
        return RecieptMessOrangDslEn;
    }

    public static String getNoorADSLRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessNoorADSLAr;
        }
        return RecieptMessNoorADSLEn;
    }

    public static String getelectricityMess(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMesselectAr;
        }
        return RecieptMesselectEn;
    }

    public static String getPhonenumberMessage(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PhonenumberMessagear;
        }
        return PhonenumberMessageen;
    }

    public static String getPaymentInstruction(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PaymentInstructionar;
        }
        return PaymentInstructionar;
    }

    /* Petrotrade counter reading */
    //-----------------------------Tony-Mobinil-bill(17-10)-End--------------------------------
    public static final int SKIP = 5;
    public static final String ASSIGN_BILL = "Assign bill";
    // status types
    public static final String STATUS_TYPE_SUCCESS = "success";
    public static final String STATUS_TYPE_ERROR = "error";
    public static final String STATUS_TYPE_WARNING = "warn";
    // status messages
    public static final String STATUS_MESSAGE_SUCCESS = "Succeed";
    public static final String STATUS_MESSAGE_ERROR = "Failed";
    public static final String STATUS_MESSAGE_ERROR_NEED_LOGIN = "You need to login again";
    public static final String AMOUNT = "AMOUNT";
    public static final String FEES = "FEES";
    public static final String MONEY_PAID = "MONEY_PAID";
    public static final String AMOUNTDolar = "AMOUNTDolar";
    public static final String AMOUNTEGP = "AMOUNTEGP";
    public static final String PILLDATE = "PILLDATE";
    // response types
    public static final String PARAM_RESPONSE_TYPE = "responsetype";
    public static final String RESPONSE_TYPE_XML = "xml";
    // application root
    public static final String APP_ROOT = "/topup1/";
    public static final String APP_LOG = "R";
    public static final String VERSION = "VERSION";
    //pages
    public static final String PARAM_ADD_EMPLOYEE = "PARAM_ADD_EMPLOYEE";
    public static final String PARAM_RETAIL_GROUP = "PARAM_RETAIL_GROUP";
    public static final String PAGE_LOGIN = "Login.jsp";
    public static final String PAGE_LOGIN_RESET = "LoginReset.jsp";
    public static final String PAGE_RESET_PASSWORD = "passwordRecovery.jsp";
    public static final String PAGE_LOGIN_MOBILE = "mobile/MLogin.jsp";
    public static final String PAGE_ADD_EMPLOYEE = "customer/AddEmployee.jsp";
    public static final String PAGE_ADD_EMPLOYEE_CONFIRM = "customer/AddEmployeeConfirmation.jsp";
    public static final String PAGE_VIEW_EMPLOYEES = "customer/ViewEmployees.jsp";
    public static final String PARAM_ROLE = "ROLE";
    public static final String PAGE_ADD_AGENT = "admin/AddAgent.jsp";
    public static final String PAGE_VIEW_AGENTS = "admin/ViewAgents.jsp";
    public static final String PAGE_VIEW_SERVICES = "admin/ViewServices.jsp";
    public static final String PAGE_VOUCHER_UPLOAD = "admin/uploadVouchers.jsp";
    public static final String PAGE_VOUCHER_UPLOAD_OUT = "upload/u.jsp";
    public static final String PAGE_VOUCHER_PROVIDERS = "admin/voucherProviders.jsp";
    public static final String PAGE_VOUCHER_VALUES = "admin/voucherValues.jsp";
    public static final String PAGE_ERRPR = "/error.jsp";
    public static final String PAGE_Activation_Code = "ActivationCode.jsp";
    public static final String PAGE_ERRPR_SSO = "error_sso.jsp";
    public static final String PAGE_ASSIGN_AGENTS = "admin/AssignBalance.jsp";
    public static final String PAGE_VIEW_TRANSACTION = "admin/ViewTransaction.jsp";
    public static final String PAGE_TRANSFER_FEES_RESULT = "customer/TransferFees.jsp";
    public static final String PARAM_NEXT_PAGE = "PARAM_NEXT_PAGE";
    public static final String PAGE_VIEW_BANK_ACCOUNT = "customer/ViewBankInformation.jsp";
    public static final String PAGE_VIEW_BANK_ACCOUNT_1 = "customer/ViewBankInformation_1.jsp";
    public static final String PAGE_EARNING_REPORT = "customer/EarningReport.jsp";
    public static final String PAGE_EARNING_REPORT_list = "customer/EarningReportList.jsp";
    public static final String PAGE_VIEW_TREE_TRANSACTION = "agent/ViewTreeList.jsp";
    public static final String PAGE_ADD_CUSTOMER = "agent/AddCustomer.jsp";
    public static final String PAGE_CUSTOMER_TRANSACTION = "customer/CustomerTransaction.jsp";
    public static final String PAGE_CUSTOMER_BILLS = "customer/PayBill.jsp";
    public static final String PAGE_VIEW_CUSTOMERS = "agent/ViewCustomers.jsp";
    public static final String PAGE_MANAGE_ACCOUNT = "agent/ManagePage.jsp";
    public static final String PAGE_MANAGE_CUSTOMER_ACCOUNT = "customer/ManagePage.jsp";
    public static final String PAGE_SEND_MAIL_REQUEST = "customer/sendMailRequest.jsp";
    public static final String PAGE_EXPORT_EXCEL = "customer/excelReport.jsp";
    public static final String PAGE_EXPORT_TRANS_LIST_EXCEL = "customer/exportToExcel.jsp";   // OMNYA_21-09-2014_Export Transaction List to Excel
    public static final String PAGE_EXPORT_TRANS_LIST_EXCEL_1 = "customer/exportToExcel_1.jsp";   // OMNYA_21-09-2014_Export Transaction List to Excel
    public static final String PAGE_VIEW_BULK_RESULT = "customer/viewBulkResult.jsp";
    public static final String PAGE_SEND_MAIL = "customer/sendEMail.jsp";
    public static final String PAGE_MANAGE_EDIT_AGENT = "admin/ManageAgentEdit.jsp";
    public static final String PAGE_MANAGE_SERVICE_ACCOUNT = "service/billers/ManagePage.jsp";
    public static final String PAGE_VIEW_AGENT_TRANSACTION = "agent/ViewTransaction.jsp";
    public static final String PAGE_VIEW_CUSTOMER_TRANSACTION = "customer/ViewTransaction.jsp";
    public static final String PAGE_Printing_Topup_TRANSACTION = "customer/PrintingTopup.jsp";
    public static final String PAGE_VIEW_CUSTOMER_TRANSACTION_MOBILE = "mobile/ViewTransaction.jsp";
    public static final String PAGE_ASSIGN_CUSTOMER = "agent/AssignBalance.jsp";
    public static final String PAGE_VIEW_CUSTOMER_ACCOUNT = "customer/ViewAgentAccount.jsp";
    public static final String PAGE_RECHARGE = "service/etisalat/ReCharge.jsp";
    public static final String PAGE_CUSTOMERTOPUP = "service/etisalat/CustomerTopUp.jsp";
    public static final String PAGE_LASTRANSACTIONS_MOBILE = "mobile/LastTransactions.jsp";//"customer/NotImplemented.jsp";
    public static final String PAGE_CUSTOMERTOPUP_MOBILE = "mobile/CustomerTopUp.jsp";
    public static final String PAGE_CUSTOMERTOPUP_MOBINIL = "service/mobinil/CustomerTopUp.jsp";
    public static final String PAGE_CUSTOMERTOPUP_VOUCHER = "service/voucher/CustomerTopUp.jsp";
    public static final String PAGE_CUSTOMERTOPUP_BULK_VOUCHER = "service/voucher/BulkVoucher.jsp";
    public static final String PAGE_CUSTOMERTOPUP_Extended_BULK_VOUCHER = "service/voucher/ExtendedBulkVoucher.jsp";
    public static final String PAGE_CUSTOMERTOPUP_NTCC = "service/ntcc/CustomerTopUp.jsp";
    public static final String PAGE_CUSTOMERTOPUP_UNIVERSITY = "service/university/CustomerTopUp.jsp";
    public static final String PAGE_CUSTOMERTOPUP_UNIVERSITY_CONFIRM = "service/university/CustomerTopUpConfirmation.jsp";
    public static final String PAGE_CUSTOMERTOPUP_VODAFONE = "service/vodafone/CustomerTopUp.jsp";
    public static final String PAGE_CUSTOMERTOPUP_CONFIRMATION = "service/etisalat/CustomerTopUpConfirmation.jsp";
    public static final String PAGE_CUSTOMERTOPUP_CONFIRMATION_MOBILE = "mobile/CustomerTopUpConfirmation.jsp";
    public static final String PAGE_CUSTOMERTOPUP_CONFIRMATION_MOBINIL = "service/mobinil/CustomerTopUpConfirmation.jsp";
    public static final String PAGE_CUSTOMERTOPUP_CONFIRMATION_VOUCHER = "service/voucher/CustomerTopUpConfirmation.jsp";
    public static final String PAGE_CUSTOMERTOPUP_CONFIRMATION_NTCC = "service/ntcc/CustomerTopUpConfirmation.jsp";
    public static final String PAGE_CUSTOMERTOPUP_CONFIRMATION_VODAFONE = "service/vodafone/CustomerTopUpConfirmation.jsp";
    public static final String PAGE_VIEW_SERVICE_ACCOUNT = "service/billers/ViewAgentAccount.jsp";
    public static final String PAGE_ADD_BILL = "service/billers/AddBill.jsp";
    public static final String PAGE_ASSIGN_CUSTOMER_BILL = "service/billersAssignBill.jsp";
    public static final String PAGE_BAY_CREADET = "service/etisalat/BayCredit.jsp";//"service/etisalat/NotImplemented.jsp";
    public static final String PAGE_SELL_CREADET = "service/etisalat/SellCredit.jsp";//"service/etisalat/NotImplemented.jsp";
    public static final String PAGE_SELL_CREADET_CONFIRM = "service/etisalat/SellCreditConfirmation.jsp";//"service/etisalat/NotImplemented.jsp";
    public static final String PAGE_SELL_CREADET_CUSTOMER = "service/etisalat/SellCustomerCredit.jsp";//"service/etisalat/NotImplemented.jsp";
    public static final String PAGE_SELL_CREADET_CONFIRM_MOBINIL = "service/mobinil/SellCustomerCreditConfirmation.jsp";//"service/etisalat/NotImplemented.jsp";
    public static final String PAGE_SELL_CREADET_CONFIRM_VODAFONE = "service/vodafone/SellCustomerCreditConfirmation.jsp";//"service/etisalat/NotImplemented.jsp";
    public static final String PAGE_SELL_CREADET_CUSTOMER_MOBINIL = "service/mobinil/SellCustomerCredit.jsp";//"service/etisalat/NotImplemented.jsp";
    public static final String PAGE_SELL_CREADET_CUSTOMER_VODAFONE = "service/vodafone/SellCustomerCredit.jsp";//"service/etisalat/NotImplemented.jsp";
    public static final String PAGE_CHECKCREIDET = "service/etisalat/CheckCredit.jsp";
    public static final String PAGE_VIEW_REPS_LIST = "agent/RepsBalancesList.jsp";
    public static final String PAGE_ASSIGN_NEW_REP_BALANCE = "agent/AssignNewBalanceToRep.jsp";
    public static final String PAGE_SELL_CREADET_CUSTOMER_CONFIRM = "service/etisalat/SellCustomerCreditConfirmation.jsp";//"customer/NotImplemented.jsp";
    public static final String TRANSLIST = "TRANSLIST";
    public static final String FALSE = "FALSE";
    public static final String TRUE = "TRUE";
    public static final String MANAGE_ROLES = "admin/ManageAgent.jsp";
    public static final String error = "error";
    public static final String etisalatError = "فشلت العملية";
    public static final String etisalatError1 = "خطأ";
    public static final String etisalatDone = "تمت";
    public static final String etisalatHTTPSuccess = "successful";
    public static final String mobinilDone = "Your request is being processed";
    public static final String vodafoneDone = "Successful transaction";
    public static final String NTCCDone = "200";
    public static final String voucher2MDone = "0: Accepted for delivery";
    public static final String etisalatError2 = "Service Not Available";
    public static final String BadEtisaltCustomer = "Customer not found";
    public static final String BadEtisaltCustomerar = "رقم الهاتف غير موجود";
    public static final String PAGE_ADD_AGENT_CONFIRM = "admin/AddAgentConfirmation.jsp";
    public static final String PAGE_ADD_CUSTOMER_CONFIRM = "agent/AddCustomerConfirmation.jsp";
    public static final String PAGE_NEXT_ADD_CUSTOMER = "agent/AddCustomer2.jsp";
    public static final String PAGE_NEXT_ADD_CUSTOMER3 = "agent/AddCustomer3.jsp";
    public static final String ACTION_GET_CREDITS = "getbalance";
    public static final String ACTION_ORDER_CREDITS = "order";
    public static final String ACTION_CHANGE_PASSWORD = "changepassword";
    public static final String ACTION_CHANGE_SERVICE_BALANCE = "changeServiceBalance";
    public static final String ACTION_Get_TIME = "gettime";
    public static final String ACTION_Get_STATUS = "getstatus";
    public static final String ACTION_TOPUP = "topup";
    public static final String TOPUP_TYPE = "topupType";
    public static final String PAGE_MANAGE_ACCOUNT_ADMIN = "admin/ManagePage.jsp";
    public static final String PAGE_MANAGE_BALANCE = "admin/AdminPortal.jsp";
    public static final String PAGE_REFUND = "admin/refund.jsp";
    public static final String PAGE_SEARCH = "admin/SearchCustomers.jsp";
    public static final String PAGE_SERVICE_PRICE = "admin/ServicePrice.jsp";
    public static final String PAGE_SERVICE_PRICE_CHANGE = "admin/ServicePriceChange.jsp";
    public static final String PAGE_MANAGE_BALANCE_ADMIN = "admin/ManageBalance.jsp";
    public static final String ACTION_TOPUP_MBINIL = "topupmobinil";
    public static final String ACTION_MONEY_TRANSFER = "transfer";
    public static final String ACTION_ADD_POINTS = "addpoint";
    public static final String ACTION_CHECK_CODE = "checkcode";
    public static final String ACTION_ADD_API_CUSTOMER = "addcustomer";
    public static final String ACTION_MONEY_TRANSFER_PAYMENT_REF_CODE = "transfer_prc";
    public static final String ACTION_TOPUP_VODAFONE = "topupvodafone";
    public static final String ACTION_TOPUP_IBT = "topupibt";
    public static final String ACTION_TOPUP_ANY_IBT = "topupanyibt";
    public static final String ACTION_TOPUP_MOBINIL_IBT = "topupmobinilibt";
    public static final String ACTION_TOPUP_ETISALAT_IBT = "topupetisalatibt";
    public static final String ACTION_TOPUP_ANY = "topupany";
    public static final String ACTION_VOUCHER_ETISALAT = "voucheretisalat";
    public static final String ACTION_VOUCHER_ONECARD = "onecardvoucher";
    public static final String VOUCHER_ONECARD_CHOOSE = "إختار قيمة الشحن";
    public static final String ONECARD_INVALID_CUSTOMER_EXCEPTION = "رقم الحساب غير صحيح";
    public static final String ONECARD_INVALID_LIMIT_EXCEDED_EXCEPTION = "تم تخطي القيمة المتاحة لـ وان كارد";
    public static final String ACTION_VOUCHER_FACEBOOK = "facebookvoucher";
    public static final String ACTION_VOUCHER_TAHADI = "tahadivoucher";
    public static final String ACTION_VOUCHER_CROSSFIRE = "crossfirevoucher";
    public static final String ACTION_VOUCHER_CONQUER = "conquervoucher";
    public static final String ACTION_VOUCHER_ARWOLFTEAM = "arabicwolfteamvoucher";
    public static final String ACTION_VOUCHER_ENWOLFTEAM = "englishwolfteamvoucher";
    public static final String ACTION_VOUCHER_SilkRoad = "silkroadvoucher";
    public static final String ACTION_VOUCHER_CASHU = "cashuvoucher";
    public static final String ACTION_VOUCHER_MOBINIL = "vouchermobinil";
    public static final String ACTION_VOUCHER_VODAFONE = "vouchervodafone";
    public static final String ACTION_ADD_TAG = "addtag";
    public static final String ACTION_TRACK_AGENT = "trackagent";
    public static final String PAGE_VIEW_AGENT_SERVICES = "agent/serviceAllocation.jsp";
    public static final String PAGE_VIEW_REPS_BALANCES = "agent/repsBalances.jsp";
    public static final String PAGE_ASSIGN_SERVICE_BALANCE = "agent/AssignBalanceToService.jsp";
    public static final String PAGE_ASSIGN_SERVICE_BALANCE_V = "agent/AssignBalanceToServiceVoucher.jsp";
    public static final String PAGE_ASSIGN_SERVICE_BALANCE_TO_AGENT_MOBILE = "mobile/AssignBalanceToAgentService.jsp";
    public static final String PAGE_ASSIGN_SERVICE_TO_AGENT_BALANCE_CONFIRMATION = "agent/AssignBalanceToAgentServiceConfimation.jsp";
    public static final String PAGE_ASSIGN_SERVICE_TO_AGENT_BALANCE = "agent/AssignBalanceToAgentService.jsp";
    public static final String PAGE_ASSIGN_SERVICE_BALANCE_MOBILE = "mobile/AssignBalanceToService.jsp";
    public static final String PAGE_REPS_REPORT_MOBILE = "mobile/RepsReports.jsp";
    public static final String PAGE_TRACK = "admin/TrackAgent.jsp";
    public static final String PAGE_MAIN_MOBILE = "mobile/main.jsp";
    public static final String PAGE_BALANCE_MOBILE = "mobile/ViewBalance.jsp";
    public static final String lang = "lang";
    public static final String CHECKCREIDET = "معرفة الرصيد بالشريحة";
    public static final String NEWUSERSMS = "Welcome To masary Electronic Payment";
    public static final String NEWAGENTSMS = "You are now a Masary Agent";
    public static final String CONFIRM = "CONFIRM";
    public static final String NEXT = "NEXT";
    public static final String VALID = "VALID";
    public static final String SESSIONID = "SESSIONID";
    public static final String VF_RECHARGE_TYPE = "*858*recharge code#";
    public static final String ET_RECHARGE_TYPE = "*556*recharge code#";
    public static final String MN_RECHARGE_TYPE = "#102*recharge code#";
    public static final String transactionError = "An error occured while processing your transaction.";
    public static final String transactionErrorar = "حدث خطأ اثناء تنفيذ العملية .";
    public static final String errorTransaction = "The Service is temporarily unavailable, Please try again later.";
    public static final String errorTransactionar = "لا يمكن تنفيذ طبلك في هذا الوقت، يرجى المحاولة في وقت لاحق";

    public static final String errorTransactionAboElrishAr = "برجاء اختيار الخدمة الصحيحة";
    public static final String errorTransactionAboElrishEn = "Please choose the correct service";

    public static final String no2MVoucherError = "There is no Vouchers with this amount ";
    public static final String voucherError = "You don't have voucher with this value";
    public static final String voucherErrorar = "عفوا,لايوجد لديك كروت بهذه القيمة";
    private static final String AddAgentar = "اضافة وكيل ";
    private static final String AddAgenten = "Add Agent";
    public static final String NAMEar = "الاسم بالانجيليزى";
    public static final String NAMEen = "NAME";
    public static final String ADDRESS1_en = "Address 1";
    public static final String ADDRESS1_ar = "العنوان 1";
    public static final String ADDRESS2_en = "Address 2";
    public static final String ADDRESS2_ar = "العنوان 2";
    public static final String COUNTRY_en = "Country";
    public static final String COUNTRY_ar = "البلد";
    public static final String GOVERNORTE_en = "Governorte";
    public static final String GOVERNORTE_ar = "المحافظة";
    public static final String CITY_en = "City";
    public static final String CITY_ar = "المنطقة";
    public static final String POSTAL_CODE_en = "Postal Code";
    public static final String POSTAL_CODE_ar = "الرقم البريدى";
    public static final String PASSWORDen = "Password";
    public static final String PASSWORDar = "كلمة السر";
    private final static String ALTERNATIVE_MOBILE_NUMBERen = "Alternative Mobile Number";
    private final static String ALTERNATIVE_MOBILE_NUMBERar = "رقم موبايل آخر";
    private final static String LAND_LINE_NUMBERen = "Land Line Number";
    private final static String LAND_LINE_NUMBERar = "رقم تليفون أراضى";
    private final static String EMAIL_ADDRESSen = "Email Address";
    private final static String EMAIL_ADDRESSar = "البريد الإلكترونى";
    private final static String CONFIRM_EMAIL_ADDRESSen = "Confirm Email Address";
    private final static String CONFIRM_EMAIL_ADDRESSar = " تأكيد البريد الإلكترونى";
    private final static String ALTERNATIVE_EMAIL_ADDRESSen = "Alternative Email Address";
    private final static String ALTERNATIVE_EMAIL_ADDRESSar = "بريد الإلكترونى آخر";
    private final static String CONFIRM_ALTERNATIVE_EMAIL_ADDRESSen = "Confirm Alternative Email Address";
    private final static String CONFIRM_ALTERNATIVE_EMAIL_ADDRESSar = " تأكيد البريد الإلكترونى الآخر";
    private final static String BIRTH_DATEen = "Birth Date";
    private final static String BIRTH_DATEar = "تاريخ الميلاد";
    private final static String NATIONAL_IDen = "National ID";
    private final static String NATIONAL_IDar = "الرقم القومى";
    private final static String REFERRED_LANGUAGEen = "Perefrred Language";
    private final static String REFERRED_LANGUAGEar = "اللغه المفضلة";
    private final static String ACCOUNT_NUMBER_en = "Account Number";
    public final static String ACCOUNT_NUMBER_ar = "رقم الحساب";
    private final static String TRANSACTION_TYPEen = "Transaction Type";
    private final static String TRANSACTION_TYPEar = "نوع العمليات";
    public static final String CONFIRMen = "Confirm";
    public static final String CONFIRMar = "تاكيد";
    public static final String Continueen = "Confirm";
    public static final String Continuear = "استمرار";
    public static final String Editen = "Edit";
    public static final String Editar = "تعديل";
    public static final String MoftahElZeroTopupServicesar = "مفتاح الزيرو";
    public static final String MoftahElZeroTopupServicesen = "Moftah El Zero";
    public static final String VOUCHER2MSERVICEar = "ركن الالعاب";
    public static final String VOUCHER2MServicesen = "Gamer Zone";
//    public static final String MobinilTopupServicesar = "شحن اورنچ";
    public static final String MobinilTopupServicesar = "شحن اورنچ";
    public static final String MobinilTopupServicesen = "Mobinil Topup";
    public static final String MobinilTopupRepetedError = "Repeated Topup Error";
    public static final String MobinilTopupRepetedErrorar = "غير مسموح باجراء عمليتين لنفس الرقم فى زمن اقل من 6 دقائق";
    public static final String VodafoneTopupServicesar = "شحن فودافون";
    public static final String VodafoneTopupServicesen = "Vodafone Topup";
    public static final String EtisalatTopupServicesar = "شحن اتصالات";
    public static final String EtisalatTopupServicesen = "Etisalat Topup";
    public static final String EtisalatTransferServicesar = "تحويل  اتصالات";
    public static final String EtisalatTransferServicesen = "Etisalat Transfer";
    public static final String MobinilTransferServicesar = "تحويل  اورنچ";
    public static final String MobinilTransferServicesen = "Mobinil Transfer";
    public static final String MoneyTransferServicesar = "تحويل رصيد مصاري ";
    public static final String MoneyTransferServicesen = "Masary Money Transfer";
    private final static String CONFIRMPASSORDen = "Confirm Password";
    private final static String CONFIRMPASSORDar = "تاكيد كلمة المرور";
    private final static String ANSWERar = "الاجابة";
    private final static String ANSWERen = "Answer";
    private final static String SERVICESar = "الخدمات ";
    private final static String SERVICESen = "Services";
    private final static String SERVICENAMEar = "اسم الخدمة ";
    private final static String SERVICENAMEen = "Service name";
    private final static String VOUCHERTYPEen = "Voucher Type";
    private final static String VOUCHERTYPEar = "نوع الخدمة";
    ;
    private final static String MOBILENUMBERen = "Mobile Number";
    private final static String MOBILENUMBERar = "رقم الموبايل";
    private final static String AmountByDolarar = "المبلغ بالدولار";
    private final static String AmountByDolaren = "Amount By Dolar";
    private final static String SECURITYQUESTIONen = "Security Question";
    private final static String VOUCHERNOen = "Voucher No.";
    private final static String VOUCHERNOar = "عدد الكروت";
    private final static String SECURITYQUESTIONar = "السؤال السرى";
    private final static String SESSIONTIMEOUTen = "Time Of Session";
    private final static String SESSIONTIMEOUTar = "مدة الجلسة";
    private final static String SELECTONEen = "Select One";
    private final static String SELECTONEar = "اختر سؤال";
    private final static String ADDAGENTCONFIRMar = "تاكيد اضافة وكيل";
    private final static String ADDAGENTCONFIRMen = "Add Agent Confirm";
    private final static String PLEASECONFIRMYOURDATAar = "الرجاء تاكيد بياناتك";
    private final static String PLEASECONFIRMYOURDATAen = "Please Confirm Your Data ";
    private final static String INVALIDEMAILen = "Please enter valid email ";
    private final static String INVALIDEMAILar = "من فضلك ادخل بريد إلكترونى صحيح ";
    private final static String CHOOSEDataar = "من فضلك اختر البيانات المطلوبة";
    private final static String CHOOSEDataen = "Please Choose required data";
    private final static String BACKar = "رجوع";
    private final static String BACKen = "Back";
    private final static String SAVEar = "حفظ";
    private final static String SAVEen = "Save";
    private final static String ASSIGNBALANCETOAGENTar = "تحويل اموال لوكيل";
    private final static String ASSIGNBALANCETOAGENTen = "Assign Balance To Agent";
    private final static String IDar = "حساب مصارى";
    private final static String IDen = "Masary's Account ";
    private final static String BALANCEar = "الرصيد الكلى";
    private final static String BALANCEar_FAA = "الرصيد الحالى";
    private final static String BALANCEen_FAA = "Current Balance";
    private final static String BALANCEen = "Total Balance";
    private final static String REORDER_BALANCEar = "الرصيدالمضاف";
    private final static String REORDER_BALANCEen = "Re-Order Balance";
    private final static String STOP_BALANCEar = "الحد الأدنى";
    private final static String STOP_BALANCEen = "Stop Balance";
    private final static String ADDBALANCEar = "اضافة";
    private final static String ADDBALANCEen = "Add Balance";
    private final static String ASSIGNar = "تخصيص";
    private final static String VoucherValuear = "القيمة";
    private final static String VoucherValueen = "value";
    private final static String oldestBillValuear = "قيمة اقدم فاتورة مستحقة";
    private final static String oldestBillValueen = "value of oldest bill";
    private final static String ASSIGNen = "Assign";
    private final static String ASSIGNTOAGENTar = "تخصيص لعميل";
    private final static String ASSIGNTOAGENTen = "Assign to agent";
    private final static DecimalFormat arabicFormater = new DecimalFormat("###,###.###  جنيه مصرى");
    private final static DecimalFormat transferFormatter = new DecimalFormat("###,###.###  جنيه مصرى");
    private final static DecimalFormat englishFomater = new DecimalFormat("###,###.###  EGP");
    private final static String EDITAGENTar = "تعدبل  الحساب ";
    private final static String EDITAGENTen = "Edit Agent";
    private final static String RESETBALANCEar = "اعادة تصفير الحساب";
    private final static String RESETBALANCEen = "Reset Balance";
    private final static String MANAGEAGENTar = "اعدادات الوكيل";
    private final static String MANAGEAGENTen = "Manage Agent";
    private final static String VIEWAGENTSar = "قائمة الوكلاء";
    private final static String VIEWAGENTSen = "View Agents";
    private final static String STATUSar = "الحالة";
    private final static String STATUSen = "Status";
    private final static String AREYOUSUREar = "هل انت متاءكد  ؟";
    private final static String AREYOUSUREen = "Are you Sure ?";
    public static final String DEACTIVATEen = "Deactivate";
    public static final String DEACTIVATEar = "ايقاف";
    public static final String ACTIVATEen = "Activate";
    private final static String ACTIVATEar = "تنشيط";
    private final static String ADMINar = "المدير";
    private final static String ADMINen = "Administrator";
    private final static String TRANSACTIONSar = "العمليات";
    private final static String TRANSACTIONSen = "Transactions";
    private final static String TRANSACTIONSFROMar = "العمليات من";
    private final static String TRANSACTIONSFROMen = "Transactions From";
    private final static String TRANSACTIONSALLar = "كل العمليات";
    private final static String TRANSACTIONSALLen = "All Transactions";
    private final static String SERVICESALLar = "كل الخدمات";
    private final static String SERVICESALLen = "All Services";
    private final static String TRANSACTIONNUMBERar = "رقم العملية";
    private static final String TRANSACTIONNUMBERen = "Transaction Id";
    private static final String LEDGERIDar = "الرقم المرجعى";
    private static final String LEDGERIDen = "Ledger ID";
    private final static String DATEar = "التاريخ";
    private static final String DATEen = "Date";
    private final static String FROMar = "من";
    private final static String FROMen = "From";
    private final static String TOar = "الى";
    private final static String TOen = "To";
    private final static String Messagear = "الرسالة :";
    private final static String Messageen = "Message : ";
    private final static String AMOUNTar = "المبلغ";
    private final static String O_AMOUNTar = "القيمة";
    private final static String FEESar = "الرسوم";
    private final static String COMMar = "العمولة";
    private final static String AMOUNTar_V = "الفئه";
    private final static String AMOUNTen = "Amount";
    private final static String E_AMOUNTar = "القيمة بالجنية ";
    private final static String E_AMOUNTen = "Amount";
    private final static String O_AMOUNTen = "Orignal_Amount";
    private final static String FEESen = "Fees";
    private final static String COMMen = "Commession";
    private final static String AvailableAmountar = "الكميه المتاحه";
    private final static String AvailableAmounten = "Available Amount";
    private final static String SelectAmountar = "أختر القيمة";
    private final static String SelectAmounten = "Select Amount";
    private final static String AMOUNTen_V = "Denomination";
    private final static String TYPEar = "النوع";
    private final static String TYPEen = "Type";
    private final static String TOTALar = "المجموع";
    private final static String TOTALen = "Total";
    private final static String TRANSACTIONSTOar = "العمليات الى:";
    private final static String TRANSACTIONSTOen = "Tranasctions To:";
    private final static String NETar = "المحصلة";
    private final static String NETen = "Net";
    private final static String TRANSACTIONREPORTar = "تقرير العملية";
    private final static String TRANSACTIONREPORTen = "Transaction Report";
    private final static String ADDCUSTOMERar = "اضافة عميل";
    private final static String ADDCUSTOMERen = "Add Customer";
    private final static String ADDCUSTOMERCONFIRMATIONar = "تاكيد اضافة عميل";
    private final static String ADDCUSTOMERCONFIRMATIONen = "Add Customer Confirmation";
    private final static String ASSIGNBALANCETOCUSTOMERar = "تحويل اموال لعميل";
    private final static String ASSIGNBALANCETOCUSTOMERen = "Assign Balance To Customer";
    private final static String MANAGECUSTOMERBALANCEar = "ادارة الرصيد";
    private final static String MANAGECUSTOMERBALANCEen = "Manage Balance";
    private final static String CHANGEar = "تغيير ";
    private final static String CHANGEen = "Change";
    private final static String SENDar = "إرسال";
    private final static String SENDen = "Send";
    private final static String RESENDar = "إعادة ارسال";
    private final static String RESENDen = "Re-send";
    private final static String MANAGEACCOUNTar = "تعديل الحساب ";
    private final static String MANAGEACCOUNTen = "Manage Account";
    private final static String Help_Requestar = "طلب مساعدة";
    private final static String Help_Requesten = "Help Request";
    private final static String Refund_Trxar = "عمليات معلقة";
    private final static String Refund_Trxen = "Pending transactions";
    private final static String Purchasing_Orderar = "طلب شراء";
    private final static String Purchasing_Orderen = "Purchasing Order";
    private final static String NEWPASSWORDar = "كلمة المرور الجديدة";
    private final static String NEWPASSWORDen = "New Password";
    private final static String OLDPASSWORDar = "كلمة المرور القديمة";
    private final static String OLDPASSWORDen = "Old Password";
    private final static String ACCOUNTINFORMATIONar = "معلومات الحساب";
    private final static String ACCOUNTINFORMATIONen = "Account Information";
    private final static String VIEWCUSTOMERSar = "قائمة العملاء ";
    private final static String VIEWCUSTOMERSen = "View Customers";
    public static final String CUSTOMERTOPUPen = "Make Etisalat TopUp";
    public static final String CUSTOMERTOPUPar = " شحن رصيد اتصالات";
    public static final String TRANSFERTOANOTHERREPen = "Make Masary Money Transfer";
    public static final String TRANSFERTOANOTHERREPar = "تحويل رصيد مصارى";
    public static final String MERCHANTRECHARGEen = "Make Etisalat Transfer";
    public static final String MERCHANTRECHARGEar = "تحويل رصيد اتصالات";
    public static final String MERCHANTRECHARGEMOBINILen = "Make Mobinil Transfer";
    public static final String MERCHANTRECHARGEMOBINILar = "تحويل رصيد اورنچ";
    public static final String MERCHANTRECHARGEVODAFONEen = "Make Vodafone Transfer";
    public static final String MERCHANTRECHARGEVODAFONEar = "تحويل رصيد فودافون";
    public static final String CUSTOMERMOBINILTOPUPen = "Make Mobinil TopUp";
    public static final String CUSTOMERMOBINILTOPUPar = " شحن رصيد اورنچ";
    public static final String CUSTOMERVOUCHER2MTOPUPen = "Gamer Zone";
    public static final String CUSTOMERVOUCHER2MTOPUPar = "ركن الالعاب";
    public static final String CUSTOMERVODAFONETOPUPen = "Make Vodafone TopUp";
    public static final String CUSTOMERVODAFONETOPUPar = " شحن رصيد فودافون";
    public static final String CUSTOMERBlaBlaTOPUPen = "Make BlaBla TopUp";
    public static final String CUSTOMERBlaBlaTOPUPar = "شحن رصيد BlaBla ";
    public static final String CUSTOMERNTCCen = "Make Moftah El Zero TopUp";
    public static final String CUSTOMERNTCCar = " تحويل رصيد مفتاح الزيرو";
    private final static String EXPORTToFILEen = "Export to file";
    private final static String EXPORTToFILEar = "تحميل الكروت فى ملف";
    private final static String EXPORTToEXCELen = "Export to";
    private final static String EXPORTToEXCELar = "إستخراج على هيئة";
    private final static String EXPORTToEXCEL_1en = "Export to";
    private final static String EXPORTToEXCEL_1ar = "إستخراج على هيئة";
    private final static String SENDByEMAILEen = "Send by email";
    private final static String SENDByEMAILEar = "الإرسال عبر الإيميل";
    private final static String WALLETEMAILEar = "إستخدام إيميل المحفظة";
    private final static String WALLETEMAILEen = "Use Wallet's Email";
    private final static String ENTEREMAILEar = "ادخل الإيميل";
    private final static String ENTEREMAILEen = "Enter your email";
    private final static String ENTERAMOUNTar = "ادخل القيمة";
    private final static String ENTERAMOUNTen = "Enter Amount";
    private final static String GOen = "Go";
    private final static String GOar = "تنفـيـذ";
    private final static String NEXTen = "Next";
    private final static String NEXTar = "التالى";
    private final static String AddetionalInfoen = "Addetional Info";
    private final static String AddetionalInfoar = "معلومات إضافية ";
    private final static String NotifyMobilear = "هاتف العميل";
    private final static String NotifyMobileen = "Customer Mobile";
    private final static String NotifyMobileHintar = "هاتف العميل المستخدم فى التنبيهات";
    private final static String NotifyMobileHinten = "Customer mobile number that will receive any notifications ";
    private static String BillRecieptMess1En = "Payment done successfully Transaction No : ";
    private static String BillRecieptMess4En = "Payment done successfully and you have unpaid bills Transaction No : ";
    private static String BillRecieptMess2En = ". Print bill now or later via Reports section.";
    private static String BillRecieptMess3En = "Payment ID : ";
    private static String BillRecieptMess1Ar = "لقد تمت عملية الدفع بنجاح رقم العمليه :";
    private static String RecieptMessfoodbankAr = "لقد تم التبرع لبنك الطعام بنجاح رقم العمليه :";
    private static String RecieptMessfoodbankEn = "Donation has been sent to food bank with transaction NO";
    private static String RecieptMessElzakHomeAr = "لقد تم التبرع لبيت الزكاه بنجاح رقم العمليه :";
    private static String RecieptMessElzakHomeEn = "Donation has been sent to ElZaka Home with transaction NO";
    private static String RecieptMessOrangDslAr = "لقد تم دفع فاتورة اورنج dsl  بنجاح رقم العمليه :";
    private static String RecieptMessOrangDslEn = "Payment done successfully to orange dsl with transaction NO";
    private static String RecieptMessNoorADSLAr = "لقد تم دفع فاتورة  Noor ADSL  بنجاح رقم العمليه :";
    private static String RecieptMessNoorADSLEn = "Payment done successfully to Noor dsl with transaction NO";
    private static String RecieptMesselectAr = "لقد تم دفع فاتورة الكهرباء الخاصه بك  برقم عملية ";
    private static String RecieptMesselectEn = "bill is paied";
    private static String RecieptMessMaanAr = "لقد تم التبرع لمؤسسة معا بنجاح رقم العمليه :";
    private static String RecieptMessMaanEn = "Donation has been sent to Maan with transaction NO";
    private static String RecieptMessMaan2Ar = "رقم العمليه :";
    private static String RecieptMessMaan2En = "transaction NO:";
    private static String BillRecieptMess4Ar = "لقد تمت عملية الدفع بنجاح ولديك فواتير اخري  رقم العمليه :";
    private static String BillRecieptMess2Ar = " و يمكنك طبع الفاتورة الان او لاحقا عن طريق قائمة العمليات";
    private static String BillRecieptMess3Ar = "رقم الدفع : ";
    public static final String NotifyMobile = "Customer_Mobile";
    private final static String SERVICEBASLANCEen = "Service Balance";
    private final static String SERVICEBASLANCEar = "رصيد الخدمة ";
    private final static String SERVICEPRICEen = "Service Price";
    private final static String VoucherCountar = "الكميه المتاحه";
    private final static String VoucherAmountar = "الكميه";
    private final static String VoucherAmounten = "Amount";
    private final static String VoucherCustToar = "رقم العميل";
    private final static String VoucherCustToen = "Customer Id";
    private final static String VoucherCounten = "Available Vouchers";
    private final static String COSTar = "التكلفة";
    private final static String COSTen = "Cost";
    private final static String AssignVoucherResultPart1ar = "لقد خصصت";
    private final static String AssignVoucherResultPart1en = "You Assign ";
    private final static String AssignVoucherResultPart2ar = "للعميل";
    private final static String AssignVoucherResultPart2en = "To Customer";
    private final static String SERVICEPRICEar = "سعر الخدمة ";
    private final static String MAXQUANTATYen = "Max Quantity";
    private final static String MAXQUANTATYar = "القيمة المتاحة";
    private final static String PLEASEWAITar = "برجاء الانتظار";
    private final static String PLEASEWAITen = "Please Wait";
    private final static String AGENTar = "وكيل ";
    private final static String AGENTen = "Agent";
    private final static String LANGUAGEen = "English";
    private final static String LANGUAGEar = "عربى";
    private final static String REPORTSen = "Reports";
    private final static String REPORTSar = "تقارير";
    private final static String LASTTRANSACTIONSen = "Last Transactions";
    private final static String LASTTRANSACTIONSar = "اخر العمليات";
    private final static String WELCOMEar = "مرحبا";
    private final static String WELCOMEen = "Welcome";
    public static final String ACTION_INCLUDE_TRANS_FROMAG = "ACTION_INCLUDE_TRANS_FROMAG";
    public static final String ACTION_INCLUDE_TRANS_FROMCUST = "ACTION_INCLUDE_TRANS_FROMCUST";
    public static final String ACTION_INCLUDE_TRANS_TOAG = "ACTION_INCLUDE_TRANS_TOAG";
    public static final String ACTION_INCLUDE_TRANS_TOCUST = "ACTION_INCLUDE_TRANS_TOCUST";
    public static final String ACTION_START = "START";
    public static final String ACTION_PREVIOUS10 = "PREVIOUS10";
    public static final String ACTION_NEXT10 = "NEXT10";
    public static final String ACTION_END = "END";
    private static final String NEWPRICEen = "New Price";
    private static final String NEWPRICEar = "الجديد السعر";
    private static final String NEWBALANCEen = "New Balance";
    private static final String NEWBALANCEar = "الرصيد الجديد ";
    private static final String ENDen = "End";
    private static final String ENDar = "النهاية";
    private static final String STARTen = "Start";
    private static final String STARTar = "البداية";
    private static final String PREVIOUS10en = "Previous 10";
    private static final String PREVIOUS10ar = "الى الخلف 10";
    private static final String NEXT10en = "Next 10";
    private static final String NEXT10ar = "الى الامام 10 ";
    private static final String VoucherTitlear = "الكروت";
    private static final String VoucherTitleen = "Vouchers";
    private static final String VoucherTitleAlignar = "أرصدة الخدمات";
    private static final String VoucherTitleAlignen = "Services Balance";
    private static final String VIEWPROVIDERSen = "View voucher providers";
    private static final String VIEWPROVIDERSar = "منتجى كروت الشحن";
    private static final String ACCOUNTNOTACTIVEen = "Your account currently not active,Please Conntact Massary Admin for more information";
    private static final String ACCOUNTNOTACTIVEar = "الحساب الخاص بك موقوف حاليا برجاء الاتصال بمصارى";
    private static final String ACCOUNTNOTACTIVEVen = "The Customer You Try To Assign Voucher Is Inactive";
    private static final String ACCOUNTNOTACTIVEVar = "الحساب الخاص بالعميل الذى تحاول تحويل الكروت له موقوف حاليا";
    private static final String CustomerToHasNoServiceErroren = "The Customer You Try To Assign Voucher Hasn't this service";
    private static final String CustomerToHasNoServiceErrorar = "الحساب الخاص بالعميل الذى تحاول تحويل الكروت له ليس لديه هذه الخدمه";
    private static final String FILLINALLTHEDATAen = "Fill in all fields";
    private static final String FILLINALLTHEDATAar = "برجاء ملءجميع البيانات";
    private static final String INVALIDCAPTCHAen = "Invalid Captcha";
    private static final String INVALIDCAPTCHAar = "كود الصورة غير صحيح";
    private static final String INVALIDMOBILENUMBERen = "Invalid mobile number ";
    private static final String INVALIDMOBILENUMBERar = "رقم الموبايل غير صحيح";
    public static final String INVALIDTOPUPAMOUNTen = "Sorry, please enter a valid amount.";
    public static final String INVALIDTOPUPAMOUNTar = "لا يوجد شحن بهذه القيمة";
    private static final String INVALIDQUESTIONORANSWERen = "Invalid question or mobile number ";
    private static final String INVALIDQUESTIONORANSWERar = "السؤال او الاجابة غير صحيحين";
    private static final String PASSWORDLENGTHERRORen = "Password Length  must be between 4 and 20 characters ";
    private static final String PASSWORDLENGTHERRORar = "كلمة السر يجب ان تكون من 4 الى 20 حرف";
    private static final String OLDPASSWORDEMPTYERRORen = "Old password can't be empty ";
    public static final String OLDPASSWORDEMPTYERRORar = "يجب ادخال كلمة السر القديمة";
    private static final String OLDPASSWORDdoesnotMatchERRORen = "Old password you entered does not match old password ";
    public static final String OLDPASSWORDDdoesnotMatchERRORar = "كلمة السر التى تم ادخالها  لا تتطابق مع كلمة السر القديمة";
    public static final String INTERNALERRORen = "Internal Error";
    public static final String INTERNALERRORar = "خطاء غير معروف";
    private static final String PASSWORDCONFIRMATIONERRORar = "كلمة المرور والتاكيد غير متتطابقين";
    private static final String PASSWORDCONFIRMATIONERRORen = "Password and confirmation doesn't match ";
    private static final String QACONFIRMATIONERRORar = "برجاء إملاء السؤال و الاجابة معآ";
    private static final String QACONFIRMATIONERRORen = "Please fill answer and question together. ";
    private static final String AUTOALLOCATEar = "تخصيص لحظي";
    private static final String AUTOALLOCATEen = "Auto Alocate";
    private static final String FIELDar = "الحقل";
    private static final String FIELDen = "Field";
    private static final String CATEGOTYar = "ألنوع";
    private static final String CATEGORYen = "Type";
    private static SimpleDateFormat dateFormatter;
    private static String javaMsgAr = "جهازك ليست به مكون java اللازم لطباعة الكروت،من فضلك قم بتحميله من الصورة التالية ثم قم بتثبيته على جهازك.";
    private static String javaMsgEn = "Your Computer doesn't have the Java Component needed to print evouchers.please download it from the below image and then setup it on your computer.";
    private static String javaMsgAr2 = "اضغط هنا";
    private static String javaMsgEn2 = "press here";
    private static String RECHARGE_NOAR = "كود الشحن";
    private static String RECHARGE_NOEN = "Regarge Number";
    private static String RECHARGE_TYPEAR = "طريقة الشحن";
    private static String RECHARGE_TYPEEN = "Recharge Method";
    private static String SERIALAR = "الرقم المسلسل";
    private static String SERIALEN = "Serial";
    private static String TAXSAR = "ضريبة المبيعات";
    private static String TAXSEN = "Taxs";
    private static String RECHARGE_AMOUNTAR = "قيمة الشحن";
    private static String RECHARGE_AMOUNTEN = "Recharge Amount";
    private static String TAX_FEES_AR = "رسوم التحويل";
    private static String TAX_FEES_EN = "Transfer Fees";
    private static String SELL_TYPEAR = "طريقة البيع";
    private static String SELL_TYPEEN = "Sell Type";
    private static String PRINTINGAR = "طباعة";
    private static String PRINTINGEN = "Printing";
    private static String SMSAR = "رسالة";
    private static String SMSEN = "SMS";
    private static String VIEWAR = "ظهور ع الشاشة";
    private static String VIEWEN = "View on screen";
    //----------------------------------------------------Vodafone Cash--------------------------------------------------------
    public static final String Vodafone_Cashen = "Vodafone Cash";
    public static final String Vodafone_Cashar = "فودافون كاش";
    public static final String VC_CashInen = "Cash IN";
    public static final String VC_CashInar = "أيداع";
    public static final String VC_CashIn_Confirmationen = "Cash IN Confirmation";
    public static final String VC_CashIn_Confirmationar = "تأكيد الايداع";
    public static final String INFO_Requireden = "Required Information";
    public static final String INFO_Requiredar = "معلومات مطلوبه";
    public static final String INFOen = "Transaction Information";
    public static final String INFOar = "معلومات العمليه";
    public static final String MerchantCommessionen = "Your Commession";
    public static final String MerchantCommessionar = "احسب عمولتك";
    public static final String BalanceDiffen = "Difference Between Balances";
    public static final String BalanceDiffar = " فرق سعر الخدمة";
    public static final String ValueAddedar = "الضريبة المضافه";
    public static final String ValueAddeden = " service taxe";
    public static final String TotalAmountMaanen = "Total Amount ";
    public static final String TotalAmountMaanar = "  المبلغ المطلوب من العميل";
    public static final String Commessionen = "Your Commession";
    public static final String Commessionar = " عمولتك";
    public static final String CommessionMaanen = "Your Commession";
    public static final String CommessionMaanar = " عمولة التاجر";
    public static final String DeductedAmounten = "Will Deduct";
    public static final String DeductedAmountar = "سيتم خصم";
    public static final String DeductedAmountMaanen = "Will Deduct";
    public static final String DeductedAmountMaanar = "سيتم خصم من محفظتك";
    public static final String AddAmounten = "Will Add";
    public static final String AddAmountar = "سيتم اضافه";
    private final static String Customer_Infoen = "Customer Information";
    private final static String Customer_Infoar = "بيانات العميل";
    private final static String Printing_Receipten = "Printing Receipt";
    private final static String Printing_Receiptar = "طباعه ايصال";
    private final static String Land_Lineen = "Land-Line Number";
    private final static String Land_Linear = "التليفون الارضى";
    public static final String VC_Cash_Out_Confirmationen = "Cash OUT Confirmation";
    public static final String VC_Cash_Out_Confirmationar = "تاكيد سحب";
    public static final String Vodafone_cash_OUTen = "Cash Out";
    public static final String Vodafone_cash_OUTar = "سحب";
    public static final String Vodafone_cash_Transaction_Inquiryen = "Transaction Inquiry";
    public static final String Vodafone_cash_Transaction_Inquiryar = "استعلام عن طلب";
    private final static String MobileNumberConfirmationen = "Mobile Confirmation";
    private final static String MobileNumberConfirmationar = "تأكيد الموبايل";
    private static final String VC_CashOut_Notefication1en = "Don’t pay before checking the transaction status in (";
    private static final String VC_CashOut_Notefication2en = ") making sure it is (";
    private static final String VC_CashOut_Notefication3en = "Successful Transaction";
    private static final String VC_CashOut_Notefication4en = "), herein you can print the receipt.";
    private static final String VC_CashOut_Notefication1ar = "لا تقم بالدفع للعميل قبل مراجعة حالة العملية من (";
    private static final String VC_CashOut_Notefication2ar = ") و التأكد أنها (";
    private static final String VC_CashOut_Notefication3ar = "عملية ناجحة";
    private static final String VC_CashOut_Notefication4ar = ") و عندئذٍ يمكنك طباعة الإيصال.";
    private static final String VC_Reports_Inquiryen = "Reports & Inquiry";
    private static final String VC_Reports_Inquiryar = "تقارير و استعلام";
    private static final String VC_TXN_AMOUNTen = "Deducted/Added Amount";
    private static final String VC_TXN_AMOUNTar = "القيمة المضافة / المسحوبة";
    private static final String VC_TXN_Main_AMOUNTen = "Transaction Amount";
    private static final String VC_TXN_Main_AMOUNTar = "قيمه العمليه";
    private static final String VC_PaymentCollectionen = "Payments/Collections";
    private static final String VC_PaymentCollectionar = "المدفوعات/التحصيلات";
    private static final String VC_NDFen = "No data found";
    private static final String VC_NDFar = "لاتوجد عمليات";
    private static final String VC_Cancelen = "Cancel Request";
    private static final String VC_Cancelar = "إلغاء العمليه";
    private static final String Checken = "Inquiry";
    private static final String Checkar = "استعلام";
    private static final String Ofen = "of";
    private static final String Ofar = "من";
    private static final String Pageen = "Pages";
    private static final String Pagear = "صفحه";
    private static final String AllowedBalanceen = "Allowed Balance";
    private static final String AllowedBalancear = "الرصيد المتاح";
    //--------------------Added by keroles---------------------------------------
    public static final String exportedData = "Data";
    public static final String exportedDataar = "ارقام المحمول";
    public static final String NUMBER_ONLY = "Please, enter number only";
    public static final String NUMBER_ONLYar = "نرجو كتابه ارقام فقط";
    public static final String COMMA_ONLY = "Please, enter comma after each number";
    public static final String COMMA_ONLYar = "نرجو كتابه فاصله بين كل رقمين";
    public static final String FROMFROM = "From";
    public static final String FROMFROMar = "من";
    public static final String PHONE_NOTE = "Seperate between mobile numbers with a comma ','. (Ex: 012xxx,011xxx,010xxx, ...etc,)";
    public static final String PHONE_NOTEar = "افصل بين ارقام الموبايل بفاصله على السطر',' (مثال: 012xxx,011xxx,010xxx ..الخ)";
    public static final String PHONE_NOTE2 = "Please choose EXCEL file to upload, mobile numbers must be written in the first column of the first sheet";
    public static final String PHONE_NOTE2ar = "قم بإختيار ملف اكسل وتأكد من كتابه ارقام الموبايل فى العمود الاول من الصفحه الاولى للملف";
    private static final String Send_Bulk_SMS = "Send SMS";
    private static final String Send_Bulk_SMSar = "خدمة الرسائل القصيرة";
    private static final String Send_Bulk_SMS_Report = "Send Bulk SMS Report";
    private static final String Send_Bulk_SMS_Reportar = "تقارير خدمة الرسائل القصيرة";
    private static final String ChooseFile = "Choose File";
    private static final String Mobilesar = "الارقام";
    private static final String Mobiles = "Mobiles";
    private static final String ChooseFilear = "اختار الملف";
    private static final String EnterMessage = "Enter Message";
    private static final String EnterMessagear = "الرساله";
    private static final String MaxChar = "(Maximum characters: 918)";
    private static final String MaxCharar = "(الحد الاقصى: 918)";
    private static final String YouHave = "You have ";
    private static final String YouHavear = "لديك ";
    private static final String ChooseLanguage = "Choose Language ";
    private static final String ChooseLanguagear = "اختار اللغه ";
    private static final String BULK_SMS_ERRORen = "Error during doing transaction";
    private static final String BULK_SMS_ERRORar = "حدث خطأ اثناء تنفيذ العمليه ";
    private static final String MOBILE_MUST_STARTWITH_ZEROen = "All mobile numbers should start with 0";
    private static final String MOBILE_MUST_STARTWITH_ZEROar = "جميع ارقام الموبايل لابد ان تبدأ 01";
    private static final String ARABIC = "Arabic ";
    private static final String ARABICar = "عربي";
    private static final String ENGLISH = "English ";
    private static final String ENGLISHar = "انجليزي";
    private static final String charLeft = "characters left.";
    private static final String charLeftar = "متبقيه";
    private static final String BULKSMSen = "Bulk SMS";
    private static final String BULKSMSar = "رساله جماعيه";
    private static final String SINGLESMSen = "Single";
    private static final String SINGLESMSar = "رساله فرديه";
    private static final String TXTonly = "Please upload only .txt extention file";
    private static final String TXTonlyar = "نرجو تحميل ملفات xlsx. فقط";
    public static final String PARAM_CHOOSE_FILE = "FILE";
    public static final String ACTION_BULK_SMS = "ACTION_BULK_SMS";
    public static final String ACTION_BULK_SMS_TRANS = "ACTION_TRANS_BULK_SMS";
    public static final String PAGE_SEND_BULK_SMS = "customer/Send_Bulk_SMS.jsp";//BulkSMSNew
    public static final String PAGE_SEND_BULK_SMS_CONFIRMATION = "customer/Send_Bulk_SMS_Confirmation.jsp";//BulkSMSNew
    public static final String PARAM_TEXTAREA = "TEXTAREA";
    public static final String FROM = "FROM";
    private static final String MESSAGE = "Message";
    private static final String MESSAGEAR = "الرساله";
    private static final String COUNT_MOBILES = "Number of Mobiles";
    private static final String COUNT_MOBILESar = "عدد ارقام التليفون";
    private static final String RESPONSE = "Response";
    private static final String RESPONSEar = "النتيجه";
    private static final String STATUS_CODE = "Status";
    private static final String STATUS_CODEar = "حاله العمليه";
    private static final String REFUND_TXN = "Refund Transaction";
    private static final String REFUND_TXNar = "رقم العمليه المرتجعه";
    private static final String N_MESSAGE = "Number of Messages";
    private static final String N_MESSAGEar = "عدد الرسائل";
    private static final String TO_N_MOBILES = "More than receiver";
    private static final String TO_N_MOBILESar = "اكثر من مستلم";
    private static final String FAILED = "Failed";
    private static final String FAILEDar = "عمليه فاشله";
    private static final String SUCCESSFUL = "Success";
    private static final String SUCCESSFULar = "عمليه ناجحه";
    private static final String PENDING = "Pending";
    private static final String PENDINGar = "تحت التنفيذ";
    private static final String makeSureParticipantNumberAr = "1- من فضلك تأكد ان رقم العداد الذى ادخلته صحيح";
    private static final String makeSureParticipantNumberEn = "1- Please Make sure that participant number is correct.";
    public static final String ACTION_Show_Report = "ACTION_SHOW_REPORT";
    public static final String PAGE_View_bulk_report = "customer/BULK_SMS_REPORT.jsp";

    // Security fix error messages
    // by Tamer Mohamed
    public static final String CUSTOMER_ACCOUNT_SUSPENDED_10_MIN_EN = "Sorry you entered wrong activation code for 3 times, please retry after 10 minutes";
    public static final String CUSTOMER_ACCOUNT_SUSPENDED_10_MIN_AR = " عفوا لقد تم إدخال كود تفعيل غير صحيح 3 مرات يرجى إعادة المحاولة بعد 10 دقائق ";
    public static final String CUSTOMER_ACCOUNT_SUSPENDED_15_MIN_EN = "Sorry you entered wrong activation code for 6 times, please retry after 15 minutes";
    public static final String CUSTOMER_ACCOUNT_SUSPENDED_15_MIN_AR = " عفوا لقد تم إدخال كود تفعيل غير صحيح 6 مرات يرجى إعادة المحاولة بعد 15 دقيقة ";
    public static final String CUSTOMER_ACCOUNT_SUSPENDED_EN = "Sorry you entered wrong activation code for 9 times, please call Masary Customer care 16994 to reactivate your account";
    public static final String CUSTOMER_ACCOUNT_SUSPENDED_AR = "عفوا لقد تم إدخال كود تفعيل غير صحيح 9 مرات يرجى الرجوع لخدمة عملاء مصارى 16994 لإعادة تشغيل حسابك";
    public static final String CUSTOMER_COOKIES_DISABLED_EN = "Kindly make sure that the cookies enabled at your browser";
    public static final String CUSTOMER_COOKIES_DISABLED_AR = " يرجى التأكد من فتح سجل الكوكيز على المتصفح الخاص بك";

    //--------------------End Added by keroles-----------------------------------
    static {
        dateFormatter
                = (SimpleDateFormat) DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        dateFormatter.applyPattern("dd/MM/yyyy HH:mm:ss");
    }
    private static final String MINUSONE_ERROR_MESSAGE_AR = "عفوا الحد الأقصى المسوح به فى العملية هو 1000 جنيها مصريا";
    private static final String MINUSONE_ERROR_MESSAGE_EN = "Maximum allowed transfer amount is 1000 EGP";
    private static final String MINUSTWO_ERROR_MESSAGE_AR = "عفوأ لقد تم استنفاذ عدد مرات التحويل المسوح بها فى اليوم";
    private static final String MINUSTWO_ERROR_MESSAGE_EN = "Maximum number of allowed transfer transaction already reached today";
    private static final String BALANCEERRORar = "المبلغ يجب ان يكون اكبر من 0";
    private static final String BALANCEERRORen = "Amount Can't Be Less Than (0). ";
    private static final String ValidAmountErrorar = "برجاء إدخال عدد صحيح للكروت";
    private static final String ValidAmountErroren = "Sorry,Please Insert Valid Amount. ";
    private static final String VoucherAvalaiblear = "المبلغ أكبر من الكميه المتاحه لديك";
    private static final String VoucherAvalaibleen = "Amount greater than your available amount ";
    private static final String CANNTTRANSFERERRORar = "لا تسطيع التحويل لنفسك";
    private static final String CANNTTRANSFERERRORen = "You Can't transfer to yourself";
    private static final String NOTVALIDIDar = "رقم المحول اليه غير صحيح";
    private static final String NOTVALIDIDen = "Please enter valid Id";
    private static final String NOTVALIDBalnacear = "قيمة الرصيد المحول غير مسموح به";
    private static final String NOTVALIDBalnaceen = "Not allowed Amount";
    private static final String ERRORar = "النتيجه";
    private static final String ERRORen = "Result ";
    public static final String NOTENOUGHBALANCEen = "You don't have enough balance ";
    public static final String NOTENOUGHBALANCEar = "رصيدك الحالى لايكفى";
    private static final String ExceedLimiten = "You can't transfer more than 20000 per one transaction ";
    private static final String ExceedLimitar = "لا يمكنك تحويل اكثر من 20000 فى الصفقه الواحده";
    private static final String ExceedDailyLimiten = "You exceeded the daily transaction limit ";
    private static final String ExceedDailyLimitar = "هذا الرقم تعدى الكميه المسموحه له فى اليوم الواحد";
    private static final String ExceedMonthlyLimiten = "You exceeded the monthly transaction limit ";
    private static final String ExceedMonthlyLimitar = "هذا الرقم تعدى الكميه المسموحه له فى الشهر الواحد";
    private static final String ARABICNAMEar = "الاسم بالعربى";
    private static final String ARABICNAMEen = "Arabic Name";
    private static final String LOGINFILDar = "فشل الدخول الرقم التعريفى او كلمة السر خاطئة";
    private static final String LOGINFILDen = "Login failed, Wrong username or password.";
    private static final String PHONENUMBERer = "Phone number";
    private static final String PHONENUMBERar = "رقم التليفون";
    private static final String AREACODEen = "Area Code";
    private static final String AREACODEar = "كود المحافظة";
    private static final String ANOTHERACCOUNTen = "Another account";
    private static final String ANOTHERACCOUNTar = "حساب اخر";
    private static final String AMOUNTHINTar = "المبلغ المحول يجب أن يكون مبلغاً صحيحاً أكبر من 3 جنيه";
    private static final String AMOUNTHINTer = "The amount should be an integer greater than 3 EGP";
    private static final String PHONEHINTar = "ادخل رقم تليفونك الأرضى المكون من 7 أو 8 أرقام";
    private static final String PHONEHINTer = "Enter a land phone number consisting of 7 or 8 digits";
    private static final String CASHAMOUNTen = "Cash amount";
    private static final String CASHAMOUNTar = "ثمن المبلغ المحول";
    private static final String LOGOUTen = "Logout";
    private static final String LOGOUTar = "خروج";
    private static final String SEARCHen = "Search";
    private static final String SEARCHar = "بحث";
    private static final String DIRar = "rtl";
    private static final String DIRen = "ltr";
    private static final String TEXTDIRar = "right";
    private static final String TEXTDIRen = "left";
    public static final String VOUCHERETISALTervicesar = "كروت اتصالات";
    public static final String VOUCHERMOBINILServicesar = "كروت اورنچ";
    public static final String VOUCHERVODAFONEServicesar = "كروت فودافون";
    public static final String VOUCHERLINKServicesar = "كروت لينك";
    public static final String VOUCHERETISALTervicesen = "Etisalat Voucher";
    public static final String UNIVERSITYServicesar = "مصروفات جامعة القاهرة";
    public static final String UNIVERSITYServicesen = "Cairo University Fees";
    public static final String VOUCHERMOBINILServicesen = "Mobinil Voucher";
    public static final String VOUCHERLINKServicesen = "Link Voucher";
    public static final String VOUCHERVODAFONEServicesen = "Vodafone Voucher";
    private static final String VOUCHERSTATISTICSar = "كروت الشحن المتاحة";
    private static final String VOUCHERSTATISTICSen = "Avilable Vouchers";
    private static final String STUDENTIDen = "Student Id";
    private static final String STUDENTIDar = "رقم الطالب";
    private static String STUDENTSTAGEar = "الفرقة";
    private static String STUDENTSTAGEen = "Stage";
    private static String STUDENTFACar = "الكلية";
    private static String STUDENTFACen = "Faculty";
    private static String REFUNDar = "ارجاع الرصيد";
    private static String REFUNDen = "Refund";
    private static String MAZZIKAar = " مزيكا دوت كوم";
    private static String MAZZIKAen = "Mazika.com";
    private static String SHOFHAar = "شوفها دوت كوم";
    private static String SHOFHAen = "Shofha.com";
    private static String MASRAWYar = "مصراوى دوت كوم";
    private static String MASRAWYen = "Masrawy.com";
    private static String EL3ABar = "العب دوت كوم";
    private static String EL3ABen = "El3ab.com";
    public static final String PARAM_TAG_ID = "tagid";
    public static final String PARAM_TAG_Data = "tagdata";
    private static final String TRACKar = "تتبع";
    private static final String TRACKen = "Tracking";
    private static final String TAGINFOen = "Tag Info";
    private static final String TAGINFOar = "معلومات عن النقطة";
    private static final String TAGIDen = "Tag Id";
    private static final String TAGIDar = "نقطة المراقبة";
    //Link
    public static final String ACTION_LINK_TOPUP = "ACTION_LINK_TOPUP";
    public static final String ACTION_DYNAMICS_TOPUP = "ACTION_DYNAMICS_TOPUP";
    public static final String ACTION_OPEN_VFCASH = "ACTION_OPEN_VFCASH";
    public static final String GET_AGENT_TO = "GET_AGENT_TO";
    public static final String ADD_TRANSFER_FEES = "ADD_TRANSFER_FEES";
    public static final String ACTION_ASSIGN_REP_NEW_BALANCE = "ACTION_ASSIGN_REP_NEW_BALANCE";
    public static final String ACTION_LINK_PAYMENT = "linkpayment";
    public static final String LINK_VALID_RESPONSE = "Valid";
    private static final String LINKWALLETIDen = "Wallet or Transaction Id";
    private static final String LINKWALLETIDar = "رقم المحفظة او العملية";
    private static final String LINKERRORen = "Please enter a valid wallet or transaction id";
    private static final String DYNAMICSERRORen = "Please enter a valid account id";
    private static final String DYNAMICSERRORar = "رقم الحساب غير صحيح";
    private static final String LINKERRORar = "رقم العملية او رقم المحفظة غير صحيح";
    private static final String LINKCOMMITETDERRORen = "Transaction id already commited";
    private static final String LINKCOMMITETDERRORar = "رقم العملية  تم دفعه من قبل";
    public static final String MASARY_LINK_PAYMENT_CODE = "Masary";
    public static final String PARAM_LINK_WALLET_ID = "LINK_WALLET_ID";
    public static final String PARAM_DYNAMICS_ACCOUNT_ID = "DYNAMICS_ACCOUNT_ID";
    public static final String PARAM_LINK_AMOUNT = "LINK_AMOUNT";
    public static final String PARAM_LINK_GUID = "LINK_GUID";
    public static final String PARAM_LINK_MAIL = "LINK_MAIL";
    public static final String PAGE_CUSTOMERTOPUP_LINK = "service/link/CustomerTopUp.jsp";
    public static final String PAGE_CUSTOMERTOPUP_DYNAMICS = "service/dynamics/CustomerTopUp.jsp";
    public static final String PAGE_CUSTOMERTOPUP_LINK_CONFIRM = "service/link/CustomerTopUpConfirmation.jsp";
    public static final String PAGE_CUSTOMERTOPUP_DYNAMICS_CONFIRM = "service/dynamics/CustomerTopUpConfirmation.jsp";
    private static final String EMAILar = "البريد الالكتروني";
    private static final String EMAILen = "E-Mail";
    private static String voucherMessageAr = "عند تأكيد البيع سوف يتم خروج الكارت من حسابك, و لن يتم إسترجاعه مرة أخرى";
    private static String voucherMessageAr2 = "يرجى التأكد من تحميل و تسطيب الجافا الخاصة بطابعة مصارى و المتاحة من خلال هذا الرابط ";
    private static String voucherMessageAr3 = " ثم تفضل بتسطيب الجافا و إعادة تشغيل الجهاز قبل الطباعة و هى خطوة يتم عملها مرة واحدة فقط. ";
    private static String voucherMessageEn3 = "In the next page, the voucher will be printed automatically through your default printer, so, check your settings";
    private static String voucherMessageEn = "On confirmation, the voucher will be discounded from your account and will not be refunded";
    private static String voucherMessageEn4 = "Please wait .. print window will appear";
    private static String voucherMessageAr4 = "برجاء الانتظار حتى ظهور نافذة اختيار الطابعة";
    public static String IS_AUTO_ALLOCATE = "IS_AUTO_ALLOCATE";
    private static String CANADDEMPLOYEEar = "يمكن اضافة موظف";
    private static String CANADDEMPLOYEEen = "Can add an employee";
    private static String RETAILGROUPar = "سلسلة محلات و ليس موزع";
    private static String RETAILGROUPen = "Retail group, not a distributer";
    private static String RESET_PASSWORDar = "اعادة ارسال كلمة المرور";
    private static String RESET_PASSWORDen = "Reset Password";
    private static final String DYNAMICSWALLETIDar = "رقم حساب ديناميكس";
    private static final String DYNAMICSWALLETIDen = "Dynamics Account ID";
    public static final String sent_mail_Purchasing_Order_recipient = "mohamed.abdelhamid@e-masary.com,a.anis@e-masary.com,ahmed.hatem@e-masary.com,sales.admin@e-masary.com;back.office@e-masary.com;mnagy@e-masary.com;b.awaad@e-masary.com";
    public static final String sent_mail_Refund_Trx_recipient = "sales.admin@e-masary.com";
//    public static final String sent_mail_Help_Request_recipient = "n.muhammad@e-masary.com";
    public static final String sent_mail_Help_Request_recipient = "cc.info@e-masary.com;gtork@e-masary.com;mnagy@e-masary.com;b.awaad@e-masary.com";
    public static final String quotaAr = "باقة الانترنت بالجيجابايت";
    public static final String quotaen = "Quota";
    public static final String QUOTA = "QUOTA";
    public static final String GigaByteAr = "جيجا بايت";
    public static final String GigaByteEN = "GigaByte";
    public static final String REENTERAR = "يجب اعادة الدخول للمحفظة لانك تجاوزت مدة التواصل بدون عمليات";
    public static final String REENTEREN = "Please login again to wallet becouse you exceed time out with no transaction";
    public static final String REPRINTAR = "اعاده طباعه الفاتورة";
    public static final String REPRINTEN = "Re Print Receipt";
    private final static String VoucherCurrencyar = "بالجنيه";
    private final static String VoucherCurrencyen = "بالجنيه";
    private final static String TRANSFER_FEES_CURRENCY_AR = " جنيه مصرى";
    private final static String TRANSFER_FEES_CURRENCY_EN = " EGP ";
    private final static String TRANSFER_FEES_TRANS_DETAILS_AR = "قد يستغرق تنفيذ العملية بعض الوقت.";
    private final static String TRANSFER_FEES_TRANS_DETAILS_EN = "Transfer transaction may take some time";
    public static final String PAGE_CUSTOMERVoucher_BlaBla = "service/BlaBla/BlaBla_Voucher.jsp";

    /*------------------------------------------------Start CashU--------------------------------------------------*/
    public static final String CUSTOMERCashUar = "شحن رصيد CashU ";
    public static final String CUSTOMERCashUen = "Make TopUp CashU";
    public static final String CUSTOMERNumCashUar = "رقم حساب CashU ";
    public static final String CUSTOMERNumCashUen = "CashU Account Number";
    public static final String CUSTOMERNameCashUar = "اسم مستخدم CashU :";
    public static final String CUSTOMERNameCashUen = "CashU Customer Name";
    private static final String EMAILCashUar = "البريد الالكتروني";
    private static final String EMAILCashUen = "E-Mail";
    public static final String AccountCahsU = "AccountCahsU";
    public static final String UserNameCahsU = "UserNameCahsU";
    public static final String holderNameCahsU = "holderNameCahsU";
    public static final String holderNameCahsUar = "الاسم المستخدم";
    public static final String holderNameCahsUen = "holder Name CahsU";
    public static final String Email = "Email";
    public static final String PAGE_CashU_TopUp = "service/CashU/cashUTopUp.jsp";
    public static final String PAGE_CashU_TopUp_Confirmation = "service/CashU/cashUTopupConfirmation.jsp";
    public static final String PAGE_CashU_TopUp_Result = "service/CashU/cashUTopUpResult.jsp";
    public static final String CashUTopUp = "CashUTopUp";
    public static final String CashUTopUpResult = "CashUTopUpResult";
    public static final String CashUTopUpConfirmation = "CashUTopUpConfirmation";
    public static final String CUSTOMERNameCashUAccountar = "اسم صاحب الحساب ";
    public static final String CUSTOMERNameCashUAccounten = "Owner account Name";
    public static final String CUSTOMERTelecomEgyptTOPUPar = "شحن رصيد المصرية للاتصالات ";
    public static final String CUSTOMERTelecomEgyptTOPUPEn = "Make Telecom Egypt TopUp ";
    private static final String TelecomPHONENUMBERen = "Phone number with area Code";
    private static final String TelecomPHONENUMBERar = "رقم الهاتف مع كود المحافظة";
    private static final String chooseGovernratear = "اختر المحافظة";
    private static final String chooseGovernrateen = "Choose Governrate";

    private final static String PAY_EN = "Pay";
    private final static String PAY_AR = "دفع";

    public static String getQuota(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return quotaAr;
        }
        return quotaen;
    }

    public static String getGovernrate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return chooseGovernratear;
        }
        return chooseGovernrateen;
    }

    public static String getEnterAMOUNT(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ENTERAMOUNTar;
        }
        return ENTERAMOUNTen;
    }

    public static String getCurrency(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VoucherCurrencyar;
        }
        return VoucherCurrencyen;
    }

    public static String get_E_amount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return E_AMOUNTar;
        }
        return E_AMOUNTen;
    }

    public static String getGigaByte(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return GigaByteAr;
        }
        return GigaByteEN;
    }

    public static String getCustomerCashUTopUp(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERCashUar;
        }
        return CUSTOMERCashUen;
    }

    public static String getCustomerCashUHolderName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return holderNameCahsUar;
        }
        return holderNameCahsUen;
    }

    public static String getCustomerCashUName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERNameCashUar;
        }
        return CUSTOMERNameCashUen;
    }

    public static String getCustomerCashUNum(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERNumCashUar;
        }
        return CUSTOMERNameCashUen;
    }

    public static String getEmailCashU(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return EMAILCashUar;
        }
        return EMAILCashUen;
    }

    /*------------------------------------------------End CashU--------------------------------------------------*/
    public static String getLinkError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LINKERRORar;
        }
        return LINKERRORen;
    }

    public static String getDynamicsError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DYNAMICSERRORar;
        }
        return DYNAMICSERRORen;
    }

    public static String getLinkCommitedBefore(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LINKCOMMITETDERRORar;
        }
        return LINKCOMMITETDERRORen;
    }

    public static String getEmail(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return EMAILar;
        }
        return EMAILen;
    }

    public static String getLinkWalletId(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LINKWALLETIDar;
        }
        return LINKWALLETIDen;

    }

    public static String getDynamicsWalletId(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DYNAMICSWALLETIDar;
        }
        return DYNAMICSWALLETIDen;

    }

    public static String getAddAgent(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AddAgentar;
        }
        return AddAgenten;
    }

    public static String getNext10(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NEXT10ar;
        }
        return NEXT10en;
    }

    public static String getVoucherTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VoucherTitlear;
        }
        return VoucherTitleen;
    }

    public static String getVoucherTitleAlign(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VoucherTitleAlignar;
        }
        return VoucherTitleAlignen;
    }

    public static String getPrevious10(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PREVIOUS10ar;
        }
        return PREVIOUS10en;
    }

    public static String getEnd(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ENDar;
        }
        return ENDen;
    }

    public static String getStart(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return STARTar;
        }
        return STARTen;
    }

    public static String getAgent(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AGENTar;
        }
        return AGENTen;
    }

    public static String getWelcome(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WELCOMEar;
        }
        return WELCOMEen;
    }

    public static String getLanguage(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LANGUAGEen;
        }
        return LANGUAGEar;
    }

    public static String getReports(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return REPORTSar;
        }
        return REPORTSen;
    }

    public static String getLogout(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LOGOUTar;
        }
        return LOGOUTen;
    }

    public static String getServiceBalance(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SERVICEBASLANCEar;
        }
        return SERVICEBASLANCEen;
    }

    public static String getCost(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return COSTar;
        }
        return COSTen;
    }

    public static String getAssignVoucherResultPart1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AssignVoucherResultPart1ar;
        }
        return AssignVoucherResultPart1en;
    }

    public static String getAssignVoucherResultPart2(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AssignVoucherResultPart2ar;
        }
        return AssignVoucherResultPart2en;
    }

    public static String getServicePrice(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SERVICEPRICEar;
        }
        return SERVICEPRICEen;
    }

    public static String getVoucherCount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VoucherCountar;
        }
        return VoucherCounten;
    }

    public static String getVoucherAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VoucherAmountar;
        }
        return VoucherAmounten;
    }

    public static String getVoucherCustTo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VoucherCustToar;
        }
        return VoucherCustToen;
    }

    public static String getMaxQuantaty(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MAXQUANTATYar;
        }
        return MAXQUANTATYen;
    }

    public static String getLastTransactions(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LASTTRANSACTIONSar;
        }
        return LASTTRANSACTIONSen;
    }

    public static String getPleaseWait(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PLEASEWAITar;
        }
        return PLEASEWAITen;
    }

    public static String getTransferToAnotheRrep(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TRANSFERTOANOTHERREPar;
        }
        return TRANSFERTOANOTHERREPen;
    }

    public static String getGo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return GOar;
        }
        return GOen;
    }

    public static String getAccountInformation(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ACCOUNTINFORMATIONar;
        }
        return ACCOUNTINFORMATIONen;
    }

    public static String getCustomerVodafoneTopUp(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERVODAFONETOPUPar;
        }
        return CUSTOMERVODAFONETOPUPen;
    }

    public static String getCustomerBlaBlaTopUp(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERBlaBlaTOPUPar;
        }
        return CUSTOMERBlaBlaTOPUPen;
    }

    public static String getCustomerTelecomEgyptTopUp(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERTelecomEgyptTOPUPar;
        }
        return CUSTOMERTelecomEgyptTOPUPEn;
    }

    public static String getTelecomPhoneNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TelecomPHONENUMBERar;
        }
        return TelecomPHONENUMBERen;
    }

    public static String getCustomerNtccTopUp(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERNTCCar;
        }
        return CUSTOMERNTCCen;
    }

    public static String getCustomerEtisalatTransfer(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MERCHANTRECHARGEar;
        }
        return MERCHANTRECHARGEen;
    }

    public static String getCustomerMobinilTransfer(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MERCHANTRECHARGEMOBINILar;
        }
        return MERCHANTRECHARGEMOBINILen;
    }

    public static String getCustomerVodafoneTransfer(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MERCHANTRECHARGEVODAFONEar;
        }
        return MERCHANTRECHARGEVODAFONEen;
    }

    public static String getCustomerEtisalatTopUp(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERTOPUPar;
        }
        return CUSTOMERTOPUPen;
    }

    public static String getCustomerMobinilTopUp(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERMOBINILTOPUPar;
        }
        return CUSTOMERMOBINILTOPUPen;
    }

    public static String getCustomerVoucher2MTopUp(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERVOUCHER2MTOPUPar;
        }
        return CUSTOMERVOUCHER2MTOPUPen;
    }

    public static String getViewCustomers(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VIEWCUSTOMERSar;
        }
        return VIEWCUSTOMERSen;
    }

    public static String getAssign(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ASSIGNar;
        }
        return ASSIGNen;
    }

    public static String getAssign_v(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VoucherValuear;
        }
        return VoucherValueen;
    }

    public static String getOldestBill(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return oldestBillValuear;
        }
        return oldestBillValueen;
    }

    public static String getAssignToAgent(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ASSIGNTOAGENTar;
        }
        return ASSIGNTOAGENTen;
    }

    public static String getNewPassword(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NEWPASSWORDar;
        }
        return NEWPASSWORDen;
    }

    public static String getOldPassword(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return OLDPASSWORDar;
        }
        return OLDPASSWORDen;
    }

    public static String getManageAccount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MANAGEACCOUNTar;
        }
        return MANAGEACCOUNTen;
    }

    public static String getRequestType(HttpSession session, String type) {
        try {
            if (type.equals("Help Request")) {
                if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
                    return Help_Requestar;
                }
                return Help_Requesten;
            } else if (type.equals("Refund Trx")) {
                if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
                    return Refund_Trxar;
                }
                return Refund_Trxen;
            } else {
                if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
                    return Purchasing_Orderar;
                }
                return Purchasing_Orderen;
            }
        } catch (Exception ex) {
            return "";
        }
    }

    public static String getChange(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CHANGEar;
        }
        return CHANGEen;
    }

    public static String getSend(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SENDar;
        }
        return SENDen;
    }

    public static String getManageCustomerBalance(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MANAGECUSTOMERBALANCEar;
        }
        return MANAGECUSTOMERBALANCEen;
    }

    public static String getAssignBalanceToCustomer(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ASSIGNBALANCETOCUSTOMERar;
        }
        return ASSIGNBALANCETOCUSTOMERen;
    }

    public static String getAddCustomerConfirmation(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ADDCUSTOMERCONFIRMATIONar;
        }
        return ADDCUSTOMERCONFIRMATIONen;
    }

    public static String getAddCustomer(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ADDCUSTOMERar;
        }
        return ADDCUSTOMERen;
    }

    public static String getTransactionReport(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TRANSACTIONREPORTar;
        }
        return TRANSACTIONREPORTen;
    }

    public static String getNet(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NETar;
        }
        return NETen;
    }

    public static String getTransactionsTo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TRANSACTIONSTOar;
        }
        return TRANSACTIONSTOen;
    }

    public static String getTotal(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TOTALar;
        }
        return TOTALen;
    }

    public static String getMessageText(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Messagear;
        }
        return Messageen;
    }

    public static String getAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AMOUNTar;
        }
        return AMOUNTen;
    }

    public static String getO_AMOUNT(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return O_AMOUNTar;
        }
        return O_AMOUNTen;
    }

    public static String getFEES(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FEESar;
        }
        return FEESen;
    }

    public static String getComm(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return COMMar;
        }
        return COMMen;
    }

    public static String getAmount_V(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AMOUNTar_V;
        }
        return AMOUNTen_V;
    }

    public static String getAvailableAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AvailableAmountar;
        }
        return AvailableAmounten;
    }

    public static String getSelectAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SelectAmountar;
        }
        return SelectAmounten;
    }

    public static String getCashAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CASHAMOUNTar;
        }
        return CASHAMOUNTen;
    }

    public static String getDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DATEar;
        }
        return DATEen;
    }

    public static String getTagId(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TAGIDar;
        }
        return TAGIDen;
    }

    public static String getTagInfo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TAGINFOar;
        }
        return TAGINFOen;
    }

    public static String getTo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TOar;
        }
        return TOen;
    }

    public static String getFrom(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FROMar;
        }
        return FROMen;
    }

    public static String getTransactionNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TRANSACTIONNUMBERar;
        }
        return TRANSACTIONNUMBERen;
    }

    public static String getLedgerID(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LEDGERIDar;
        }
        return LEDGERIDen;
    }

    public static String getTransactionsFrom(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TRANSACTIONSFROMar;
        }
        return TRANSACTIONSFROMen;
    }

    public static String getTransactions(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TRANSACTIONSar;
        }
        return TRANSACTIONSen;
    }

    public static String getAdmin(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ADMINar;
        }
        return ADMINen;
    }

    public static String getActivate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ACTIVATEar;
        }
        return ACTIVATEen;
    }

    public static String getResetPassword(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RESET_PASSWORDar;
        }
        return RESET_PASSWORDen;
    }

    public static String getDeactivate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DEACTIVATEar;
        }
        return DEACTIVATEen;
    }

    public static String getAreyouSure(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AREYOUSUREar;
        }
        return AREYOUSUREen;
    }

    public static String getViewAgents(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VIEWAGENTSar;
        }
        return VIEWAGENTSen;
    }

    public static String getStatus(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return STATUSar;
        }
        return STATUSen;
    }

    public static String getType(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TYPEar;
        }
        return TYPEen;
    }

    public static String getManageAgent(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MANAGEAGENTar;
        }
        return MANAGEAGENTen;
    }

    public static String getResetBalance(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RESETBALANCEar;
        }
        return RESETBALANCEen;
    }

    public static String getEditAgent(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return EDITAGENTar;
        }
        return EDITAGENTen;
    }

    public static String getBalance(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BALANCEar;
        }
        return BALANCEen;
    }

    public static String getBalance_FAA(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BALANCEar_FAA;
        }
        return BALANCEen_FAA;
    }

    public static String getOrderBalance(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return REORDER_BALANCEar;
        }
        return REORDER_BALANCEen;
    }

    public static String getStopBalance(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return STOP_BALANCEar;
        }
        return STOP_BALANCEen;
    }

    public static String getDirection(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DIRar;
        }
        return DIRen;
    }

    public static String getTextDirection(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TEXTDIRar;
        }
        return TEXTDIRen;
    }

    public static String getNewBalance(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NEWBALANCEar;
        }
        return NEWBALANCEen;
    }

    public static String getNewPrice(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NEWPRICEar;
        }
        return NEWPRICEen;
    }

    public static String getAddBalance(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ADDBALANCEar;
        }
        return ADDBALANCEen;
    }

    public static String getAddAgentConfirm(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ADDAGENTCONFIRMar;
        }
        return ADDAGENTCONFIRMen;
    }

    public static DecimalFormat getFormater(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return arabicFormater;
        }
        return englishFomater;
    }

    public static String getAssignBalanceToAgent(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ASSIGNBALANCETOAGENTar;
        }
        return ASSIGNBALANCETOAGENTen;
    }

    public static String getID(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return IDar;
        }
        return IDen;
    }

    public static String getSave(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SAVEar;
        }
        return SAVEen;
    }

    public static String getPleaseConfirmYourData(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PLEASECONFIRMYOURDATAar;
        }
        return PLEASECONFIRMYOURDATAen;
    }

    public static String getInvalidEmail(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return INVALIDEMAILar;
        }
        return INVALIDEMAILen;
    }

    public static String getChooseData(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CHOOSEDataar;
        }
        return CHOOSEDataen;
    }

    public static String getBack(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BACKar;
        }
        return BACKen;
    }

    public static String getName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NAMEar;
        }
        return NAMEen;
    }

    public static String getArabicName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ARABICNAMEar;
        }
        return ARABICNAMEen;
    }

    public static String getPassword(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PASSWORDar;
        }
        return PASSWORDen;
    }

    public static String getConfirmPassword(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CONFIRMPASSORDar;
        }
        return CONFIRMPASSORDen;
    }

    public static String getAnswer(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ANSWERar;
        }
        return ANSWERen;
    }

    public static String getServices(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SERVICESar;
        }
        return SERVICESen;
    }

    public static String getServiceName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SERVICENAMEar;
        }
        return SERVICENAMEen;
    }

    public static String getSelectOne(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SELECTONEar;
        }
        return SELECTONEen;
    }

    public static String getSecurityQuestion(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SECURITYQUESTIONar;
        }
        return SECURITYQUESTIONen;
    }

    public static String getSESSIONTIMEOUT(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SESSIONTIMEOUTar;
        }
        return SESSIONTIMEOUTen;
    }

    public static String getViewProviders(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VIEWPROVIDERSar;
        }
        return VIEWPROVIDERSen;
    }

    public static String getMobileNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MOBILENUMBERar;
        }
        return MOBILENUMBERen;
    }

    public static String getAmountByDolar(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AmountByDolarar;
        }
        return AmountByDolaren;
    }

    public static String getConfirm(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CONFIRMar;
        }
        return CONFIRMen;
    }

    public static String getContinue(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Continuear;
        }
        return Continueen;
    }

    public static String getEditting(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Editar;
        }
        return Editen;
    }

    public static String getVoucherMessge(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return voucherMessageAr;
        }
        return voucherMessageEn;
    }

    public static String getVoucherMessge4(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return voucherMessageAr4;
        }
        return voucherMessageEn4;
    }

    public static String getVoucherMessge2(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return voucherMessageAr2;
        }
        return voucherMessageEn2;
    }

    public static String getVoucherMessge3(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return voucherMessageAr3;
        }
        return voucherMessageEn3;
    }

    public static String getAccountNotAcctiveMessage(HttpSession session) {

        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ACCOUNTNOTACTIVEar;
        }
        return ACCOUNTNOTACTIVEen;
    }

    public static String getAccountNotAcctiveMessageV(HttpSession session) {

        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ACCOUNTNOTACTIVEVar;
        }
        return ACCOUNTNOTACTIVEVen;
    }

    public static String getCustomerToHasNoServiceError(HttpSession session) {

        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CustomerToHasNoServiceErrorar;
        }
        return CustomerToHasNoServiceErroren;
    }

    public static String getFillInAllData(HttpSession session) {

        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FILLINALLTHEDATAar;
        }
        return FILLINALLTHEDATAen;
    }

    public static String getInvalidCaptcha(HttpSession session) {

        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return INVALIDCAPTCHAar;
        }
        return INVALIDCAPTCHAen;
    }

    public static String getInvalidMobileNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return INVALIDMOBILENUMBERar;
        }
        return INVALIDMOBILENUMBERen;
    }

    public static String getInvalidTopupAmount(HttpSession session) {

        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return INVALIDTOPUPAMOUNTar;
        }
        return INVALIDTOPUPAMOUNTen;
    }

    public static String getInvalidQuestionOrAnswer(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return INVALIDQUESTIONORANSWERar;
        }
        return INVALIDQUESTIONORANSWERen;
    }

    public static String getInternalError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return INTERNALERRORar;
        }
        return INTERNALERRORen;
    }

    public static String getOldPasswordDoesnotMatchError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return OLDPASSWORDDdoesnotMatchERRORar;
        }
        return OLDPASSWORDdoesnotMatchERRORen;
    }

    public static String getOldPasswordEmptyError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return OLDPASSWORDEMPTYERRORar;
        }
        return OLDPASSWORDEMPTYERRORen;
    }

    public static String getPasswordLengthError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PASSWORDLENGTHERRORar;
        }
        return PASSWORDLENGTHERRORen;
    }

    public static String getPasswordConfirmationError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PASSWORDCONFIRMATIONERRORar;
        }
        return PASSWORDCONFIRMATIONERRORen;
    }

    public static String getConfirmationError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return QACONFIRMATIONERRORar;
        }
        return QACONFIRMATIONERRORen;
    }

    public static String getBalanceError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BALANCEERRORar;
        }
        return BALANCEERRORen;
    }

    public static String getValidAmountError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ValidAmountErrorar;
        }
        return ValidAmountErroren;
    }

    public static String getVoucherAvalaible(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VoucherAvalaiblear;
        }
        return VoucherAvalaibleen;
    }

    public static String getCanntTransferError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CANNTTRANSFERERRORar;
        }
        return CANNTTRANSFERERRORen;
    }

    public static String getCanAddEmployee(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CANADDEMPLOYEEar;
        }
        return CANADDEMPLOYEEen;
    }

    public static String getIsRetailGroup(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RETAILGROUPar;
        }
        return RETAILGROUPen;
    }

    public static String getNotValidId(HttpSession session, boolean balanceCase) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar") && balanceCase == false) {
            return NOTVALIDIDar;
        } else if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar") && balanceCase == true) {

            return NOTVALIDBalnacear;
        } else if (!(session == null) && !(session.getAttribute(CONFIG.lang) == null) && !(session.getAttribute(CONFIG.lang).equals("ar")) && balanceCase == true) {

            return NOTVALIDBalnaceen;
        } else {
            return NOTVALIDIDen;
        }
    }

    public static String getError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ERRORar;
        }
        return ERRORen;
    }

    public static String getNotEnoughBalanceError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NOTENOUGHBALANCEar;
        }
        return NOTENOUGHBALANCEen;
    }

    public static String getExceedLimitError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ExceedLimitar;
        }
        return ExceedLimiten;
    }

    public static String getExceedDailyLimitError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ExceedDailyLimitar;
        }
        return ExceedDailyLimiten;
    }

    public static String getExceedMonthlyLimitError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ExceedMonthlyLimitar;
        }
        return ExceedMonthlyLimiten;
    }

    public static String getErrorTransaction(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return errorTransactionar;
        }
        return errorTransaction;
    }

    public static String getLoginFiled(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LOGINFILDar;
        }
        return LOGINFILDen;
    }

    public static String getBadEtisaltCustomer(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BadEtisaltCustomerar;
        }
        return BadEtisaltCustomer;
    }

    public static String getPhoneNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PHONENUMBERar;
        }
        return PHONENUMBERer;
    }

    public static String getAmountHint(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AMOUNTHINTar;
        }
        return AMOUNTHINTer;
    }

    public static String getPhoneHint(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PHONEHINTar;
        }
        return PHONEHINTer;
    }

    public static String getSearch(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SEARCHar;
        }
        return SEARCHen;
    }

    public static String getVoucherStatistics(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VOUCHERSTATISTICSar;
        }
        return VOUCHERSTATISTICSen;
    }

    public static String getCategory(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CATEGOTYar;
        }
        return CATEGORYen;
    }

    public static String getField(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FIELDar;
        }
        return FIELDen;
    }

    public static String getAutoAllocate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AUTOALLOCATEar;
        }
        return AUTOALLOCATEen;
    }

    public static String getAreaCode(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AREACODEar;
        }
        return AREACODEen;
    }

    public static String getAnotherAccount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ANOTHERACCOUNTar;
        }
        return ANOTHERACCOUNTen;
    }

    public static String getStudentId(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return STUDENTIDar;
        }
        return STUDENTIDen;

    }

    public static String getStudentStage(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return STUDENTSTAGEar;
        }
        return STUDENTSTAGEen;

    }

    public static String getTrack(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TRACKar;
        }
        return TRACKen;

    }

    public static String getStudentFaculty(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return STUDENTFACar;
        }
        return STUDENTFACen;

    }

    public static String getRefund(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return REFUNDar;
        }
        return REFUNDen;

    }

    public static String getMazika(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MAZZIKAar;
        }
        return MAZZIKAen;

    }

    public static String getEl3ab(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return EL3ABar;
        }
        return EL3ABen;

    }

    public static String getShofha(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SHOFHAar;
        }
        return SHOFHAen;

    }

    public static String getMasrawy(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MASRAWYar;
        }
        return MASRAWYen;

    }

    public static String formateTime(Date date) {
        return dateFormatter.format(date);
    }

    public static String getjavaMessge(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return javaMsgAr;
        }
        return javaMsgEn;
    }

    public static String getjavaMessge2(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return javaMsgAr2;
        }
        return javaMsgEn2;
    }
    //----------------bill(25-06)---------------------------

    public static String getCustomerBillHead(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERBillar;
        }
        return CUSTOMERBillen;
    }

    public static String getUniv_BillHead(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Univ_BillHeadar;
        }
        return Univ_BillHeaden;
    }

    public static String getUniv_Bill_Conf_Head(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Univ_Bill_Conf_Headar;
        }
        return Univ_Bill_Conf_Headen;
    }

    public static String getBiller_Name(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Biller_Namear;
        }
        return Biller_Nameen;
    }

    public static String getBill_Name(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Bill_Namear;
        }
        return Bill_Nameen;
    }

    public static String getPMT_Type(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PMT_Typear;
        }
        return PMT_Typeen;
    }

    public static String getSERVICE_TYPE(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SERVICE_TYPEar;
        }
        return SERVICE_TYPEen;
    }

    public static String getARServiceName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ARSERVICENAMEar;
        }
        return ARSERVICENAMEen;
    }

    public static String getBILL_LABLE(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BILL_LABLEar;
        }
        return BILL_LABLEen;
    }

    public static String getARBILL_LABLE(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ARBILL_LABLEar;
        }
        return ARBILL_LABLEen;
    }

    public static String getPartial(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Partialar;
        }
        return Partialen;
    }

    public static String getOver(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Overar;
        }
        return Overen;
    }

    public static String getFraction(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Fractionar;
        }
        return Fractionen;
    }

    public static String getAdvanced(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Advancedar;
        }
        return Advanceden;
    }

    public static String getCustomerConfBillHead(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERConfBillar;
        }
        return CUSTOMERConfBillen;
    }

    public static String getpayment(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return paymentar;
        }
        return paymenten;
    }

    public static String getFees(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Feesar;
        }
        return Feesen;
    }

    public static String getLowerAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LowerAmountar;
        }
        return LowerAmounten;
    }

    public static String getUpperAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return UpperAmountar;
        }
        return UpperAmounten;
    }

    public static String getclientDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Datear;
        }
        return Dateen;
    }

    public static String getRequestId(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RequestIdar;
        }
        return RequestIden;
    }

    public static String getBillRefNum(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BillRefNumar;
        }
        return BillRefNumen;
    }

    public static String getDuDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DuDatear;
        }
        return DuDateen;
    }

    public static String getExDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ExpDuDatear;
        }
        return ExpDuDateen;
    }

    public static String getBillStatus(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BillStatusar;
        }
        return BillStatusen;
    }

    public static String getBillInquiryStatus(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BillInquiryStatusar;
        }
        return BillInquiryStatusen;
    }

    public static String getPaymentOption(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PaymentOptionar;
        }
        return PaymentOptionen;
    }

    public static String getBillRecieptMess(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BillRecieptMessAr;
        }
        return BillRecieptMessEn;
    }

    public static String getpayer(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return payerar;
        }
        return payeren;
    }

    public static String getPayed(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Payedar;
        }
        return Payeden;
    }

    public static String getCUstID(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return custIdar;
        }
        return custIden;
    }

    public static String getService(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Servicear;
        }
        return Serviceen;
    }

    public static String getAdd(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Addar;
        }
        return Adden;
    }

    public static String getPinChangeMSG(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PinChangeMSGar;
        }
        return PinChangeMSGen;
    }

    public static String getInvalidPin(HttpSession session) {

        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return INVALIDInvalidPinar;
        }
        return INVALIDInvalidPinen;
    }

    public static String getPinConfirmationError(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PinCONFIRMATIONERRORar;
        }
        return PinCONFIRMATIONERRORen;
    }

    public static String getBill_Note(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Bill_Notear;
        }
        return Bill_Noteen;
    }

    public static String getBalanc_Message(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Balanc_Messagear;
        }
        return Balanc_Messageen;
    }

    public static String getTotalAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TotalAmountar;
        }
        return TotalAmounten;
    }

    public static String getTotalAmountWithTax(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TotalAmountWithTaxar;
        }
        return TotalAmountWithTaxen;
    }

    public static String getBillPayedMsg(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BillPayedMsgar;
        }
        return BillPayedMsgen;
    }
    //----------------End-bill(25-06)---------------------------

    public static String getBillErrorCode(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BillErrorCodear;
        }
        return BillErrorCodeen;
    }

    public static String getCustomerName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CustomerNamear;
        }
        return CustomerNameen;
    }

    //------------------Omnya-21-01-2013---------------
    public static String getAddress1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ADDRESS1_ar;
        }
        return ADDRESS1_en;
    }

    public static String getAddress2(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ADDRESS2_ar;
        }
        return ADDRESS2_en;
    }

    public static String getCountry(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return COUNTRY_ar;
        }
        return COUNTRY_en;
    }

    public static String getGovernorate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return GOVERNORTE_ar;
        }
        return GOVERNORTE_en;
    }

    public static String getCity(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CITY_ar;
        }
        return CITY_en;
    }

    public static String getPostalCode(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return POSTAL_CODE_ar;
        }
        return POSTAL_CODE_en;
    }

    public static String getAlternativeMobileNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ALTERNATIVE_MOBILE_NUMBERar;
        }
        return ALTERNATIVE_MOBILE_NUMBERen;
    }

    public static String getLandLineNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LAND_LINE_NUMBERar;
        }
        return LAND_LINE_NUMBERen;
    }

    public static String getEmailAddress(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return EMAIL_ADDRESSar;
        }
        return EMAIL_ADDRESSen;
    }

    public static String getConfirmEmailAddress(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CONFIRM_EMAIL_ADDRESSar;
        }
        return CONFIRM_EMAIL_ADDRESSen;
    }

    public static String getAlternativeEmailAddress(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ALTERNATIVE_EMAIL_ADDRESSar;
        }
        return ALTERNATIVE_EMAIL_ADDRESSen;
    }

    public static String getConfirmAlternativeEmailAddress(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CONFIRM_ALTERNATIVE_EMAIL_ADDRESSar;
        }
        return CONFIRM_ALTERNATIVE_EMAIL_ADDRESSen;
    }

    public static String getBirthDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BIRTH_DATEar;
        }
        return BIRTH_DATEen;
    }

    public static String getNationalID(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NATIONAL_IDar;
        }
        return NATIONAL_IDen;
    }

    public static String getPreferredLang(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return REFERRED_LANGUAGEar;
        }
        return REFERRED_LANGUAGEen;
    }

    public static String getAccountNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ACCOUNT_NUMBER_ar;
        }
        return ACCOUNT_NUMBER_en;
    }
    //------------------END----------------
    //------------------OMNYA  07-02-2013----------------

    public static String getTransactionType(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TRANSACTION_TYPEar;
        }
        return TRANSACTION_TYPEen;
    }

    public static String getTransactionsAll(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TRANSACTIONSALLar;
        }
        return TRANSACTIONSALLen;
    }

    public static String getAllServices(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SERVICESALLar;
        }
        return SERVICESALLen;
    }

    public static String getNext(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NEXTar;
        }
        return NEXTen;
    }

    public static String getRecharge_No(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RECHARGE_NOAR;
        }
        return RECHARGE_NOEN;
    }

    public static String getRecharge_Type(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return RECHARGE_TYPEAR;
        }
        return RECHARGE_TYPEEN;
    }

    public static String getSerial(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return SERIALAR;
        }
        return SERIALEN;
    }

    public static String getTax(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return TAXSAR;
        }
        return TAXSEN;
    }

    public static String getRechargeAmount(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return RECHARGE_AMOUNTAR;
        }
        return RECHARGE_AMOUNTEN;
    }

    public static String getSellType(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return SELL_TYPEAR;
        }
        return SELL_TYPEEN;
    }

    public static String getPrinting(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return PRINTINGAR;
        }
        return PRINTINGEN;
    }

    public static String getSMS(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return SMSAR;
        }
        return SMSEN;
    }

    public static String getView(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return VIEWAR;
        }
        return VIEWEN;
    }

    //------------------END----------------
    public static String getAddetionalInfo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AddetionalInfoar;
        }
        return AddetionalInfoen;
    }

    public static String getNotifyMobile(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NotifyMobilear;
        }
        return NotifyMobileen;
    }

    public static String getNotifyMobileHint(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NotifyMobileHintar;
        }
        return NotifyMobileHinten;
    }

    public static String getBillRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BillRecieptMess1Ar;
        }
        return BillRecieptMess1En;
    }

    public static String getBillRecieptMess2(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BillRecieptMess2Ar;
        }
        return BillRecieptMess2En;
    }

    public static String getBillRecieptMess3(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BillRecieptMess3Ar;
        }
        return BillRecieptMess3En;
    }

    public static String getBillRecieptMess4(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BillRecieptMess4Ar;
        }
        return BillRecieptMess4En;
    }
    //----------------------------------------------------Vodafone Cash -------------------------------

    public static String getVodafone_Cash(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Vodafone_Cashar;
        }
        return Vodafone_Cashen;
    }

    public static String getVC_CashIn(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VC_CashInar;
        }
        return VC_CashInen;
    }

    public static String getVC_CashIn_Confirmation(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VC_CashIn_Confirmationar;
        }
        return VC_CashIn_Confirmationen;
    }

    public static String getINFO_Required(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return INFO_Requiredar;
        }
        return INFO_Requireden;
    }

    public static String getMerchantCommession(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MerchantCommessionar;
        }
        return MerchantCommessionen;
    }

    public static String getBalance_Diff(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BalanceDiffar;
        }
        return BalanceDiffen;
    }

    public static String getServiceTaxe(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ValueAddedar;
        }
        return ValueAddeden;
    }

    public static String getCommession(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Commessionar;
        }
        return Commessionen;
    }

    public static String getDeductedAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DeductedAmountar;
        }
        return DeductedAmounten;
    }

    public static String getAddAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AddAmountar;
        }
        return AddAmounten;
    }

    public static String getCustomer_Info(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Customer_Infoar;
        }
        return Customer_Infoen;
    }

    public static String getPrinting_Receipt(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Printing_Receiptar;
        }
        return Printing_Receipten;
    }

    public static String getLand_Line(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Land_Linear;
        }
        return Land_Lineen;
    }

    public static String getINFO(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return INFOar;
        }
        return INFOen;
    }

    public static String getVodafone_cash_OUT(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Vodafone_cash_OUTar;
        }
        return Vodafone_cash_OUTen;
    }

    public static String getMobileNumberConfirmation(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MobileNumberConfirmationar;
        }
        return MobileNumberConfirmationen;
    }

    public static String getVC_Cash_Out_Confirmation(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VC_Cash_Out_Confirmationar;
        }
        return VC_Cash_Out_Confirmationen;
    }

    public static String getVC_CashOut_Notefication1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VC_CashOut_Notefication1ar;
        }
        return VC_CashOut_Notefication1en;
    }

    public static String getVC_CashOut_Notefication2(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VC_CashOut_Notefication2ar;
        }
        return VC_CashOut_Notefication2en;
    }

    public static String getVC_CashOut_Notefication3(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VC_CashOut_Notefication3ar;
        }
        return VC_CashOut_Notefication3en;
    }

    public static String getVC_CashOut_Notefication4(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VC_CashOut_Notefication4ar;
        }
        return VC_CashOut_Notefication4en;
    }

    public static String getVC_Reports_Inquiry(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VC_Reports_Inquiryar;
        }
        return VC_Reports_Inquiryen;
    }

    public static String getVodafone_cash_Transaction_Inquiry(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Vodafone_cash_Transaction_Inquiryar;
        }
        return Vodafone_cash_Transaction_Inquiryen;
    }

    public static String getVC_TXN_Main_AMOUNT(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VC_TXN_Main_AMOUNTar;
        }
        return VC_TXN_Main_AMOUNTen;
    }

    public static String getVC_PaymentCollection(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VC_PaymentCollectionar;
        }
        return VC_PaymentCollectionen;
    }

    public static String getVC_TXN_AMOUNT(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VC_TXN_AMOUNTar;
        }
        return VC_TXN_AMOUNTen;
    }

    public static String getVC_Cancel(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VC_Cancelar;
        }
        return VC_Cancelen;
    }

    public static String getCheck(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Checkar;
        }
        return Checken;
    }

    public static String getVC_NDF(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return VC_NDFar;
        }
        return VC_NDFen;
    }

    public static String getOf(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Ofar;
        }
        return Ofen;
    }

    public static String getPage(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Pagear;
        }
        return Pageen;
    }

    public static String getAllowedBalance(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AllowedBalancear;
        }
        return AllowedBalanceen;
    }

    public static String getVoucherType(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return VOUCHERTYPEar;
        }
        return VOUCHERTYPEen;
    }

    public static String getVoucherNO(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return VOUCHERNOar;
        }
        return VOUCHERNOen;
    }

    public static String getSendByEmail(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return SENDByEMAILEar;
        }
        return SENDByEMAILEen;
    }

    public static String getWalletEmail(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return WALLETEMAILEar;
        }
        return WALLETEMAILEen;
    }

    public static String getEnterEmail(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return ENTEREMAILEar;
        }
        return ENTEREMAILEen;
    }

    public static String getExportToFile(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return EXPORTToFILEar;
        }
        return EXPORTToFILEen;
    }

    public static String getExportToEXCEL(HttpSession session) {   // OMNYA_21-09-2014_Export Transaction List to Excel
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return EXPORTToEXCELar;
        }
        return EXPORTToEXCELen;
    }

    public static String getExportToEXCEL_1(HttpSession session) {   // OMNYA_21-09-2014_Export Transaction List to Excel
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return EXPORTToEXCEL_1ar;
        }
        return EXPORTToEXCEL_1en;
    }

    public static String getDone(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return DONE_ar;
        }
        return DONE_en;
    }

    public static String getRe_Send(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return RESENDar;
        }
        return RESENDen;
    }

    //---------------------Added by keroles in 17-03-2014 For Bulk SMS---------------------- 
    public static String getSend_Bulk_SMS_Report(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Send_Bulk_SMS_Reportar;
        }
        return Send_Bulk_SMS_Report;
    }

    public static String getSend_Bulk_SMS(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Send_Bulk_SMSar;
        }
        return Send_Bulk_SMS;
    }

    public static String getChooseFile(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ChooseFilear;
        }
        return ChooseFile;
    }

    public static String getEnterMessage(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return EnterMessagear;
        }
        return EnterMessage;
    }

    public static String getMaxChar(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MaxCharar;
        }
        return MaxChar;
    }

    public static String getYouHave(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return YouHavear;
        }
        return YouHave;
    }

    public static String getcharLeft(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return charLeftar;
        }
        return charLeft;
    }

    public static String getTXTonly(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TXTonlyar;
        }
        return TXTonly;
    }

    public static String getMobiles(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Mobilesar;
        }
        return Mobiles;
    }

    public static String getChooseLanguage(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ChooseLanguagear;
        }
        return ChooseLanguage;
    }

    public static String getARABIC(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ARABICar;
        }
        return ARABIC;
    }

    public static String getENGLISH(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ENGLISHar;
        }
        return ENGLISH;
    }

    public static String getBULK_SMS_ERROR(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BULK_SMS_ERRORar;
        }
        return BULK_SMS_ERRORen;
    }

    public static String getMOBILE_MUST_STARTWITH_ZERO(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MOBILE_MUST_STARTWITH_ZEROar;
        }
        return MOBILE_MUST_STARTWITH_ZEROen;
    }

    public static String getBULKSMS(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return BULKSMSar;
        }
        return BULKSMSen;
    }

    public static String getSINGLESMS(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SINGLESMSar;
        }
        return SINGLESMSen;
    }

    public static String getMESSAGE(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MESSAGEAR;
        }
        return MESSAGE;
    }

    public static String getCOUNT_MOBILES(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return COUNT_MOBILESar;
        }
        return COUNT_MOBILES;
    }

    public static String getRESPONSE(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RESPONSEar;
        }
        return RESPONSE;
    }

    public static String getSTATUS_CODE(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return STATUS_CODEar;
        }
        return STATUS_CODE;
    }

    public static String getREFUND_TXN(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return REFUND_TXNar;
        }
        return REFUND_TXN;
    }

    public static String getN_MESSAGE(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return N_MESSAGEar;
        }
        return N_MESSAGE;
    }

    public static String getexportedData(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return exportedDataar;
        }
        return exportedData;
    }

    public static String getTO_N_MOBILES(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TO_N_MOBILESar;
        }
        return TO_N_MOBILES;
    }

    public static String getFAILED(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FAILEDar;
        }
        return FAILED;
    }

    public static String getSUCCESSFUL(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SUCCESSFULar;
        }
        return SUCCESSFUL;
    }

    public static String getPENDING(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PENDINGar;
        }
        return PENDING;
    }

    public static String getFROMFROM(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FROMFROMar;
        }
        return FROMFROM;
    }

    public static String getPHONE_NOTE(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PHONE_NOTEar;
        }
        return PHONE_NOTE;
    }

    public static String getPHONE_NOTE2(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PHONE_NOTE2ar;
        }
        return PHONE_NOTE2;
    }

    public static String getNUMBER_ONLY(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NUMBER_ONLYar;
        }
        return NUMBER_ONLY;
    }

    public static String getCOMMA_ONLY(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return COMMA_ONLYar;
        }
        return COMMA_ONLY;
    }

    public static String getREPRINT(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return REPRINTAR;
        }
        return REPRINTEN;
    }

    public static String getReEnter(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return REENTERAR;
        }
        return REENTEREN;
    }

    public static String getTodayDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TodayDatear;
        }
        return TodayDateen;
    }

    public static String getGrossProfit(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return GrossProfitar;
        }
        return GrossProfiten;
    }

    public static String getTransNum(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TransNumar;
        }
        return TransNumen;
    }

    //-------------------End Added by keroles in 17-03-2014 For Bulk SMS---------------------- 
    public static String getTAX_FEES(HttpSession session) {

        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return TAX_FEES_AR;
        }
        return TAX_FEES_EN;
    }

    public static String getTransferFeesCurrency(HttpSession session) {

        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return TRANSFER_FEES_CURRENCY_AR;
        }
        return TRANSFER_FEES_CURRENCY_EN;

    }

    public static String getTRANSFER_FEES_TRANS_DETAILS(HttpSession session) {

        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return TRANSFER_FEES_TRANS_DETAILS_AR;
        }
        return TRANSFER_FEES_TRANS_DETAILS_EN;

    }

    public static String getTRANSFER_FEES_MinusOne_Error_Message(HttpSession session) {

        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return MINUSONE_ERROR_MESSAGE_AR;
        }
        return MINUSONE_ERROR_MESSAGE_EN;

    }

    public static String getTRANSFER_FEES_MinusTwo_Error_Message(HttpSession session) {

        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return MINUSTWO_ERROR_MESSAGE_AR;
        }
        return MINUSTWO_ERROR_MESSAGE_EN;
    }

    public static String getTRANSFER_FEES_1Perecent_Message(HttpSession session) {

        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return FEESMESSAGEar;
        }
        return FEESMESSAGEen;
    }

    public static void setTAX_FEES(String TAX_FEES_AR) {

        CONFIG.TAX_FEES_AR = TAX_FEES_AR;
    }

    // Security Fix error messages
    // by Tamer Mohamed
    public static String getCUSTOMER_ACCOUNT_SUSPENDED_10_MIN(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return CUSTOMER_ACCOUNT_SUSPENDED_10_MIN_AR;
        }

        return CUSTOMER_ACCOUNT_SUSPENDED_10_MIN_EN;
    }// end of method getCUSTOMER_ACCOUNT_SUSPENDED_10_MIN

    public static String getCUSTOMER_ACCOUNT_SUSPENDED_15_MIN(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return CUSTOMER_ACCOUNT_SUSPENDED_15_MIN_AR;
        }

        return CUSTOMER_ACCOUNT_SUSPENDED_15_MIN_EN;
    }// end of method getCUSTOMER_ACCOUNT_SUSPENDED_15_MIN

    public static String getCUSTOMER_ACCOUNT_SUSPENDED(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return CUSTOMER_ACCOUNT_SUSPENDED_AR;
        }

        return CUSTOMER_ACCOUNT_SUSPENDED_EN;
    }// end of method getCUSTOMER_ACCOUNT_SUSPENDED

    public static String getCUSTOMER_COOKIES_DISABLED(HttpSession session) {
        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return CUSTOMER_COOKIES_DISABLED_AR;
        }

        return CUSTOMER_COOKIES_DISABLED_EN;
    }// end of method getCUSTOMER_COOKIES_DISABLED

    public static String getmakeSureParticipantNumber(HttpSession session) {

        if (session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {

            return makeSureParticipantNumberAr;
        }
        return makeSureParticipantNumberEn;

    }

    public static String getOperationRetry(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return operationRetryAr;
        }
        return operationRetryEn;
    }

    public static String getInternetEgyptServiceName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return internetEgyptServiceAr;
        }
        return internetEgyptServiceEN;
    }

    public static String getinternetEgyptTitle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return internetEgyptTitleAr;
        }
        return internetEgyptTitleEN;
    }

    public static String getInternetEgyptReciept(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptInternetEgyptAr;
        }
        return RecieptInternetEgyptEn;
    }

    /**
     * **********************************Start InternetEgypt "Mustafa"
     * ****************************************
     */
    private static final String internetEgyptServiceAr = "انترنت ايجيبت";
    private static final String internetEgyptServiceEN = "Internet Egypt";
    private static final String internetEgyptTitleAr = "رقم التليفون(مع كود المحافظه)";
    private static final String internetEgyptTitleEN = "Phone Number (with governorate Code)";
    private static String RecieptInternetEgyptAr = "لقد تم دفع فاتورة   Internet Egypt  بنجاح رقم العمليه :";
    private static String RecieptInternetEgyptEn = "Payment done successfully to InternetEgypt with transaction NO";

    public static String ErrorNumberInternetEgyptAr = " لا توجد فاتورة لهذا الرقم ";
    public static String ErrorNumberInternetEgyptEn = "No bill found for this number";

    public static final String ACTIONInternetEgyptINQUIRY = "ACTION_Internet_Egypt_INQUIRY";
    public static final String ACTIONInternetEgyptGETINFO = "ACTION_Internet_Egypt_GETINFO";
    public static final String ACTIONInternetEgyptPayment = "ACTION_Internet_Egypt_Payment";
    public static final String ACTIONInternetEgyptConfirmPay = "ACTION_Internet_EgyptConfirmPay";
    public static final String PAGEInternetEgyptINQUIRY = "service/InternetEgypt/InternetEgyptInquiry.jsp";
    public static final String PAGEInternetEgyptGETINFO = "service/InternetEgypt/InternetEgyptlInfo.jsp";
    public static final String PAGEInternetEgyptPAYMENT = "service/InternetEgypt/InternetEgyptPaymentConfirmation.jsp";
    // public static final String PAGEInternetEgyptPAYMENTCONFIRMATION = "service/InternetEgypt/InternetEgyptPaymentConfirmation.jsp";

    /**
     * **********************************End Internet Egypt
     * ****************************************
     */
    /**
     *
     *
     * /**
     * **********************************Start Noor
     * ADSL****************************************
     */
    public static final String ACTIONNOORADSLINQUIRY = "ACTION_NOOR_ADSL_INQUIRY";
    public static final String ACTIONNOORADSLGETINFO = "ACTION_NOOR_ADSL_GETINFO";
    public static final String ACTIONNOORADSLPayment = "ACTION_NOOR_ADSL_Payment";
    public static final String ACTIONNOORADSLConfirmPay = "ACTION_NOOR_ADSL_ConfirmPay";
    public static final String PAGENOOrADSLINQUIRY = "service/ElNoorDsl/NoorAdslInquiry.jsp";
    public static final String PAGENOOrADSLGETINFO = "service/ElNoorDsl/NoorAdslInfo.jsp";
    public static final String PAGENOOrADSLPAYMENT = "service/ElNoorDsl/NoorAdslPayment.jsp";
    public static final String PAGENOOrADSLPAYMENTCONFIRMATION = "service/ElNoorDsl/NoorAdslPaymentConfirmation.jsp";

    /**
     * **********************************End Noor
     * ADSL****************************************
     */
    /**
     * *************************start
     * fager*************************************
     */
    public static final String ACTIONElFagerNQUIRY = "ACTION_ElFager_INQUIRY";
    public static final String ACTIONElFagerGETINFO = "ACTION_ElFager_GETINFO";
    public static final String ACTIONElFagerPayment = "ACTION_ElFager_Payment";
    public static final String ACTIONElFagerConfirmPay = "ACTION_ElFager_ConfirmPay";
    public static final String PAGEElFagerINQUIRY = "service/elFager/ElFagerInquiry.jsp";
    public static final String PAGEElFagerGETINFO = "service/elFager/ElFagerInfo.jsp";
    public static final String PAGEElFagerPAYMENT = "service/elFager/ElFagerPayment.jsp";
    public static final String PAGEElFagerPAYMENTCONFIRMATION = "service/elFager/ElFagerPaymentConfirmation.jsp";
    private static String RecieptMessElfagerADSLAr = "لقد تم دفع فاتورة  الفجر ADSL  بنجاح رقم العمليه :";
    private static String RecieptMessElfagerADSLEn = "Payment done successfully to Elfager dsl with transaction NO";

    public static String getElfagerADSLRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessElfagerADSLAr;
        }
        return RecieptMessElfagerADSLEn;
    }

    /**
     * ****************************************************************
     */
    /**
     * *************************start
     * etisal*************************************
     */
    public static final String ACTIONEtisalNQUIRY = "ACTION_Etisal_INQUIRY";
    public static final String ACTIONEtisalGETINFO = "ACTION_Etisal_GETINFO";
    public static final String ACTIONEtisalPayment = "ACTION_Etisal_Payment";
    public static final String ACTIONEtisalConfirmPay = "ACTION_Etisal_ConfirmPay";
    public static final String PAGEEtisalINQUIRY = "service/Etisal/EtisalInquiry.jsp";
    public static final String PAGEEtisalGETINFO = "service/Etisal/EtisalInfo.jsp";
    public static final String PAGEEtisalPAYMENTCONFIRMATION = "service/Etisal/EtisalPaymentConfirmation.jsp";
    public static final String EtisalServiceName = "فاتورة اتصال";
    public static final String EtisalServiceLabel = "اتصال";
    public static final String EtisalMSISDN = "رقم التليفون(مع كود المحافظة)";
    public static final String EtisalAmount = "القيمة";
    public static final String EtisalExpiryDate = "تاريخ انتهاء الفاتورة";
    public static final String EtisalLabel = "ادخل رقم الموبايل/ التليفون الارضى";
    public static final String EtisalNotFoundNumber = "لا يوجد فاتورة إتصال لهذا الرقم يرجى التاكد من الرقم الصحيح";
    private static String RecieptMessEtisalAr = "لقد تم دفع فاتورة اتصال  بنجاح رقم العمليه :";
    private static String RecieptMessEtisalEn = "Payment done successfully to Etisal with transaction NO";

    public static String getEtisalRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessEtisalAr;
        }
        return RecieptMessEtisalEn;
    }
    /**
     * *************************ALAhly*********************************
     */
    public static final String ALAhlyServiceName = "شركة الأهلي للتمويل العقاري";
    public static final String ALAhalyLabel = "رقم ملف العميل :";
    public static final String ALAhalyAmount = "القيمة المطلوبة: ";
    public static final String PAGE_AlAhly_Inquiry = "/service/AlAhly/AlAhlyInquiry.jsp";
    public static final String PAGE_AlAhly_INFO = "/service/AlAhly/AlAhlyInfo.jsp";
    public static final String PAGE_AlAhly_PAYMENT = "/service/AlAhly/AlAhlyPaymentConfirmation.jsp";
    public static final String ACTION_ALAHLY_NQUIRY = "ACTION_AlAhly_INQUIRY";
    public static final String ACTION_ALAHLY_GETINFO = "ACTION_ALAhly_GETINFO";
    public static final String ACTION_ALAHLY_Payment = "ACTION_ALAhly_Payment";
    /* ****************************************************************
     */
    /**
     * **********************mubasher**********************
     */
    public static final String selectDenmonationAr = "  فئة الكارت ";
    public static final String selectDenmonationEn = "denmonation of voucher";
    public static final String selectvalueVoucherAr = "   قيمة الكارت  ";
    public static final String selectvalueVoucherEn = "value of voucher";
    public static final String PAGEMUBASHERVO = "service/mubasherVoucher/mubasherVoucher.jsp";
    public static final String PAGEMUBASHERRECIPTVO = "service/mubasherVoucher/mubasherReceipt.jsp";
    public static final String PAGEMUBASHERCONFIRMATIONVO = "service/mubasherVoucher/mubasherVoucherConfirmation.jsp";
    public static final String CUSTOMERMUBASHERVOar = "كروت مباشر";
    public static final String CUSTOMERMUBASHERVOen = "mubasher voucher";
    public static final String itemId = "itemId";
    public static final String itemCode = "itemCode";
    public static final String ACTION_CUSTOMER_TOPUP_CONF = "ACTION_CUSTOMER_TOPUP_CONF";

    public static String getSelectValueVoucher(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return selectvalueVoucherAr;
        }
        return selectvalueVoucherEn;
    }

    public static String getSelectDenmonationVoucher(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return selectDenmonationAr;
        }
        return selectvalueVoucherEn;
    }

    public static String getCustomerMubasherVo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMERMUBASHERVOar;
        }
        return CUSTOMERMUBASHERVOen;
    }
    /**
     * ****************************************************
     */

    /**
     * ****************************Misr
     * El**************************************
     */
    public static final String MisrElKherHomePage = "/service/MasrElkher/MisrElkher.jsp";
    public static final String MisrElKherConfirmation = "/service/MasrElkher/MisrElkherConfirmation.jsp";
    public static final String MisrElKherPaymentPage = "/service/MasrElkher/MisrElkherPayment.jsp";
    public static final String ACTION_MISR_ELKHEIR = "ACTION_MISR_ELKHEIR";
    private static String RecieptMessMisrElKherAr = "لقد تم التبرع لمؤسسة مصر الخير بنجاح رقم العمليه :";
    private static String RecieptMessMisrElKherEn = "Donation has been sent to Misr ElKher with transaction NO";
    public static final String errorNationaId = "Nationa id is not available";
    public static final String errorNationaIdar = "برجاء ادخال الرقم القومي صحيح  كما في البطاقة";

    public static String getMisrElKherRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessMisrElKherAr;
        }
        return RecieptMessMisrElKherEn;
    }

    /**
     * ************************************************************************
     */
    /**
     * ******************************Asfon***************************************
     */
    public static final String ExamsAr = " الامتحان";
    public static final String ExamsEn = " Exam";
    public static final String ChooseExamsAr = " اختر الامتحان";
    public static final String ChooseExamsEn = " Choose Exam";
    public static final String selectListExamsAr = " قائمة الامتحانات ";
    public static final String selectListExamEn = "list of Exams";
    public static final String AmountFeesAr = "المبلغ الاصلي + الرسوم شامله ضريبه القيمة المضافه ";
    public static final String AmountFeesEn = "Amount +Fees";
    public static final String AsfonHomePage = "/service/asfonIt/AsfonItHome.jsp";
    public static final String AsfonConfirmation = "/service/asfonIt/AsfonItConfirmation.jsp";
    public static final String AsfonPaymentPage = "/service/asfonIt/AsfonItPayment.jsp";

    public static String getAsfonList(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return selectListExamsAr;
        }
        return selectListExamEn;
    }

    public static String getExam(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ExamsAr;
        }
        return ExamsEn;
    }

    public static String ChooseExam(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ChooseExamsAr;
        }
        return ChooseExamsEn;
    }

    public static String getAmountFees(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AmountFeesAr;
        }
        return AmountFeesEn;
    }

    public static final String GOO_BACK_SERVICE = "خدمة مواصلات جو اند باك مصر";
    public static final String GOO_BACK_PAGE = "/service/GooBack/GooBack.jsp";
    public static final String GOO_BACK_CONFIRMATION = "/service/GooBack/GooBackConfirmation.jsp";
    public static final String GOO_BACK_PAYMENT = "/service/GooBack/GooBackPayment.jsp";
    public static final String ACTION_GOO_BACK = "ACTION_GOO_BACK";
    public static final String ACTION_GOO_BACK_CONFIRM = "ACTION_GOO_BACK_CONFIRM";
    public static final String ACTION_GOO_BACK_PAY = "ACTION_GOO_BACK_PAY";
    private static String RecieptGooBackAr = "لقد تم دفع قيمه خدمه جو اند باك بنجاح";
    private static String RecieptGooBackEn = "Transaction for Goo and Back has been paid successfully";

    public static String getGooBackMsg(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptGooBackAr;
        }
        return RecieptGooBackEn;
    }

    public static String getPay(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PAY_AR;
        }
        return PAY_EN;
    }

    public static final String NETRISKA_SERVICE = "نتريسكا";
    public static final String NETRISKA_SERVICES_AR = "خدمات";
    public static final String NETRISKA_COURSES_AR = "كورسات";
    public static final String NETRISKA_SERVICES_PAGE = "/service/Netriska/Netriska.jsp";
    public static final String NETRISKA_COURSES_PAGE = "/service/Netriska/Netriska.jsp";
    public static final String NETRISKA_CONFIRMATION = "/service/Netriska/NetriskaConfirmation.jsp";
    public static final String NETRISKA_PAYMENT = "/service/Netriska/NetriskaPayment.jsp";
    public static final String ACTION_NETRISKA = "ACTION_NETRISKA";
    public static final String ACTION_NETRISKA_CONFIRM = "ACTION_NETRISKA_CONFIRM";
    public static final String ACTION_NETRISKA_PAY = "ACTION_GOO_BACK_PAY";
    private static String RecieptNetriskaAr = "لقد تم دفع قيمه خدمه نيتريسكا بنجاح ";
    private static String RecieptNetriskaEn = "Transaction for Netriska has been paid successfully";

    public static String getNetriskakMsg(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptNetriskaAr;
        }
        return RecieptNetriskaEn;
    }

    public static final String HATAXI_SERVICE = "خدمة مواصلات هتاكسي";
    public static final String HATAXI_PAGE = "/service/Hataxi/Hataxi.jsp";
    public static final String HATAXI_CONFIRMATION = "/service/Hataxi/HataxiConfirmation.jsp";
    public static final String HATAXI_PAYMENT = "/service/Hataxi/HataxiPayment.jsp";
    public static final String ACTION_HATAXI = "ACTION_HATAXI";
    public static final String ACTION_HATAXI_CONFIRM = "ACTION_HATAXI_CONFIRM";
    public static final String ACTION_HATAXI_PAY = "ACTION_HATAXI_PAY";
    public static String RecieptHataxiAr = "لقد تم دفع خدمة خدمة هاتاكسي بنجاح";
    public static String RecieptHataxiEn = "Transaction for Hataxi has been paid successfully";

    public static final String ONE_CARD_SERVICE = "خدمة كروت وان كارد";
    public static final String ONE_CARD_PAGE = "/service/OneCard/OneCard.jsp";
    public static final String ONE_CARD_CONFIRMATION = "/service/OneCard/OneCardConfirmation.jsp";
    public static final String ONE_CARD_PAYMENT = "/service/OneCard/OneCardPayment.jsp";
    public static final String ACTION_ONE_CARD = "ACTION_ONE_CARD";
    public static final String ACTION_ONE_CARD_CONFIRM = "ACTION_ONE_CARD_CONFIRM";
    public static final String ACTION_ONE_CARD_PAY = "ACTION_ONE_CARD_PAY";
    public static String RecieptOneCardAr = "لقد تم دفع قيمه خدمه وان كارد بنجاح";
    public static String ReciepOneCardEn = "Transaction for One Card has been paid successfully";

    public static final String WatanyaHomePage = "/service/Watanya/watanyaHome.jsp";
    public static final String WatanyaConfirmationPage = "/service/Watanya/watanyaConfirmation.jsp";
    public static final String WatanyaPaymentPage = "/service/Watanya/watanyaPayment.jsp";

    public static final String AlbaqiatAlsalehatHomePage = "/service/AlbaqiatAlsalehat/albaqiatAlsalehatHome.jsp";
    public static final String AlbaqiatAlsalehatConfirmationPage = "/service/AlbaqiatAlsalehat/albaqiatAlsalehatConfirmation.jsp";
    public static final String AlbaqiatAlsalehatPaymentPage = "/service/AlbaqiatAlsalehat/albaqiatAlsalehatPayment.jsp";

    private static final String AScancerAr = "رعاية أطفال مرضى السرطان";
    private static final String AScancerEn = "Care of Children with cancer";

    private static final String ASWaterAr = "توصيل المياة للقرى المحرومة";
    private static final String ASWaterEn = "Water supply to disadvantaged villages";

    private static final String ASAlzheimerAr = "رعاية مرضى الزهايمر";
    private static final String ASAlzheimerEn = "Care of Alzheimer patients";

    private static final String ASAlzheimer2Ar = "تأهيل و تدريب مرضى الزهايمر";
    private static final String ASAlzheimer2En = "Qualifying Alzheimer patients";

    private static final String ASZakahAr = "زكاة مال";
    private static final String ASZakahEn = "Zakah";

    private static final String ASEnsureAr = "كفالة مسني الشوارع";
    private static final String ASEnsureEn = "Ensure street elders";
    private static final String ASODHAIAAr = "صك الأضحيه";
    private static final String ASODHAIAEn = "Ensure street elders";

    private static final String ASHosbitalAr = "مستشفى الباقيات الصالحات";
    private static final String ASHosbitalEn = "Elbakyat El Salhat Hosbital";

    private static final String ASsadkahAr = "صدقة جارية";
    private static final String ASsadkahEn = "OnGoing sadkah";

    public static String getASWater(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ASWaterAr;
        }
        return ASWaterEn;
    }

    public static String getASAlzheimer(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ASAlzheimerAr;
        }
        return ASAlzheimerEn;
    }

    public static String getASAlzheimer2(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ASAlzheimer2Ar;
        }
        return ASAlzheimer2En;
    }

    public static String getASZakah(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ASZakahAr;
        }
        return ASZakahEn;
    }

    public static String getASEnsure(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ASEnsureAr;
        }
        return ASEnsureEn;
    }

    public static String getASASODHAIAAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ASODHAIAAr;
        }
        return ASODHAIAAr;
    }

    public static String getASHosbital(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ASHosbitalAr;
        }
        return ASHosbitalEn;
    }

    public static String getASsadkah(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ASsadkahAr;
        }
        return ASsadkahEn;
    }

    public static String getAScancer(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AScancerAr;
        }
        return AScancerEn;
    }

    /**
     * ****************************************************************
     */
    /**
     * **********************Mostafa Mahmoud Donation**********************
     */
    public static final String MostafaMahmoudHomePage = "/service/MostafaMahmoud/MostafaMahmoudHomePage.jsp";
    public static final String MostafaMahmoudConfirmationPage = "/service/MostafaMahmoud/MostafaMahmoudConfirmationPage.jsp";
    public static final String MostafaMahmoudPaymentPage = "/service/MostafaMahmoud/MostafaMahmoudPaymentPage.jsp";
    private static final String RecieptMessMostafaMahmoudAr = "جمعية مسجد مصطفى محمود";
    private static final String RecieptMessMostafaMahmoudEn = "Mostafa Mahmoud's Mosque association";

    public static String getMostafaMahmoudRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessMostafaMahmoudAr;
        }
        return RecieptMessMostafaMahmoudEn;
    }

    /**
     * **********************Qabasnoor Donation**********************
     */
    public static final String QabasnoorHomePage = "/service/Qabasnoor/QabasnoorHomePage.jsp";
    public static final String QabasnoorConfirmationPage = "/service/Qabasnoor/QabasnoorConfirmationPage.jsp";
    public static final String QabasnoorPaymentPage = "/service/Qabasnoor/QabasnoorPaymentPage.jsp";
    private static final String RecieptMessQabasnoorAr = "جمعية قبس من نور";
    private static final String RecieptMessQabasnoorEn = "Qabas noor association";

    public static String getQabasnoorRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessQabasnoorAr;
        }
        return RecieptMessQabasnoorEn;
    }

    /**
     * **********************Yalla Pay Service**********************
     */
    public static final String ACTION_YALLAPAY_INQUIRY = "ACTION_YALLAPAY_INQUIRY";
    public static final String ACTION_YALLAPAY_INFO = "ACTION_YALLAPAY_INFO";
    public static final String ACTION_YALLAPAY_PAYMENT = "ACTION_YALLAPAY_PAYMENT";
    public static final String PAGE_YALLAPAY_HOMEPAGE = "/service/YallaPay/HomePage.jsp";
    public static final String PAGE_YALLAPAY_INFOPAGE = "/service/YallaPay/InfoPage.jsp";
    public static final String PAGE_YALLAPAY_PAYMENTPAGE = "/service/YallaPay/PaymentPage.jsp";
    public static final String YallaPayServiceName = "يلا باى";
    public static final String YallaPayTrxLabel = "رقم العملية";
    public static final String YallaPayInfoLabel = "بيانات العملية";
    public static final String YallaPayTrxID = "رقم العملية :";
    public static final String YallaPayTrxType = "نوع العملية :";
    public static final String YallaPayTrxAmount = "قيمة العملية :";
    public static final String YallaPayAppliedFees = "تكلفة الخدمة شاملة ضريبة القيمة المضافة :";
    public static final String YallaPayToBePaid = "اجمالى المبلغ المطلوب من العميل :";
    public static final String YallaPayCancelLabel = "إلغاء";
    public static final String yallaPayNotFoundNumberAr = "لا يوجد عملية بهذا الرقم";
    public static final String yallaPayNotFoundNumberEn = "No transactin Found";
    public static final String yallaPayAlreadyPayedAr = "الرقم المستعلم عنه تم دفعه من قبل";
    public static final String yallaPayAlreadyPayedEn = "transaction is already payed";
    public static final String yallaPayConnectionFailedAr = "تعذر الاتصال بالشبكة الرجاء المحاولة لاحقا او الاتصال بخدمة العملاء";
    public static final String yallaPayConnectionFailedEn = "Connection refused please try later or call customer service office";
    public static String yallaPayPaymentFailureAr = "فشلت عملية الدفع الرجاء المحاولة فى وقت لاحق";
    public static String yallaPayPaymentFailureEn = "Payment is failed please try again later";
    private static String RecieptMessYallaPayAr = "لقد تم دفع فاتورة يلا باى  بنجاح رقم العمليه :";
    private static String RecieptMessYallaPayEn = "Payment done successfully to YallaPay with transaction NO";
    public static String yallaPayOutOfRangeAr = "عفوا عملية غير مقبولة برجاء العلم انه  الحد الأدنى 20 جنيها والحد الأقصى 100,000";
    public static String yallaPayOutOfRangeEn = "Trx amount is out of range :- must be more than 20 and less than 100,000";
    public static String yallaPayServiceNotAvailableAr = "الخدمة غير متاحة حاليا الرجاء المحاولة فى وقت لاحق";
    public static String yallaPayServiceNotAvailableEn = "Service instance not available for now please try later";

    public static String getYallaPayRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessYallaPayAr;
        }
        return RecieptMessYallaPayEn;
    }
    /////////////////////////////////TelecomEgypt//////////////////
    public static final String CUSTOMER_TELECOMEGYPT_TOPUPar = " شحن موبايل المصرية للإتصالات";
    public static final String CUSTOMER_TELECOMEGYPT_TOPUPen = "Make Telecom Egypt TopUp";

////
    public static final String Page_Reprint_Bills = "/bill/ReprintBills.jsp";

    /**
     * **********************Telecome Mobile Voucher**********************
     */
    public static final String ACTION_TELECOMEEGYPT_HOME = "ACTION_TELECOMEEGYPT_HOME";
    public static final String ACTION_TELECOMEEGYPT_CONFIRMATION = "ACTION_TELECOMEEGYPT_CONFIRMATION";
    public static final String ACTION_TELECOMEEGYPT_PAYMENT = "ACTION_TELECOMEEGYPT_PAYMENT";
    public static final String PAGE_TELECOMEEGYPT_HOMEPAGE = "/service/TelecomeMobileVoucher/HomePage.jsp";
    public static final String PAGE_TELECOMEEGYPT_CONFIRMATIONPAGE = "/service/TelecomeMobileVoucher/ConfirmationPage.jsp";
    public static final String PAGE_TELECOMEEGYPT_PAYMENTPAGE = "/service/TelecomeMobileVoucher/PaymentPage.jsp";
    public static final String TelecomEgyptServiceName = "كوبون موبايل المصرية للاتصالات";
    public static final String telecomeEgyptProviderIntegrationAr = "لقد تعذر إتمام العمليه بسبب عطل الخدمة من قبل مقدم الخدمة ";
    public static final String telecomeEgyptProviderIntegrationEn = "Provider Integration Error";
    public static final String telecomeEgyptDenominationNotFoundAr = "لقد تعذر إتمام العمليه، برجاء إعادة المحاولة في وقتٍ لاحق";
    public static final String telecomeEgyptDenominationNotFoundEn = "Denomination not found";
    public static final String telecomeEgyptProviderAccountIsNotExistAr = "هذا الرقم غير موجود بالخدمة";
    public static final String telecomeEgyptProviderAccountIsNotExistEn = "Account not Exist";
    public static String telecomeEgyptProviderBalanceIsNotEnoughAr = "لقد تعذر إتمام العمليه، برجاء إعادة المحاولة في وقتٍ لاحق";
    public static String telecomeEgyptProviderBalanceIsNotEnoughEn = "Balance is not enough";
    public static String telecomeEgyptProviderDuplicateRequestAr = "لقد تعذر إتمام العمليه، برجاء إعادة المحاولة في وقتٍ لاحق";
    public static String telecomeEgyptProviderDuplicateRequestEn = "Duplicate transaction error";
    public static String telecomeEgyptProviderGeneralErrorDuringRequestAr = "لقد تعذر إتمام العمليه بسبب عطل الخدمة من قبل مقدم الخدمة ";
    public static String telecomeEgyptProviderGeneralErrorDuringRequestEn = "Error during transaction try again later";
    public static String telecomeEgyptInvalidAccountOrPasswordAr = "لقد تعذر إتمام العمليه، برجاء إعادة المحاولة في وقتٍ لاحق";
    public static String telecomeEgyptInvalidAccountOrPasswordEn = "Invalid account or password";
    public static String telecomeEgyptInvalidVoucherAmountAr = "الرجاء اختيار الفئة الصحيحة";
    public static String telecomeEgyptInvalidVoucherAmountEn = "Invalid Voucher amount";
    public static String telecomeEgyptStatusCodeNotInTheMapStatusCodeAr = "لقد تعذر إتمام العمليه، برجاء إعادة المحاولة في وقتٍ لاحق";
    public static String telecomeEgyptStatusCodeNotInTheMapStatusCodeEn = "No status code found";
    public static String telecomeEgyptServiceNotAvailableAr = "الخدمة غير متاحة حاليا الرجاء المحاولة فى وقت لاحق";
    public static String telecomeEgyptServiceNotAvailableEn = "Service instance not available for now please try later";
    public static String telecomeEgyptDuplicatetrxAr = "برجاء الرجوع الي قائمة العمليات و التأكد من نجاح العملية";
    public static String telecomeEgyptDuplicatetrxEn = "Please check your Transaction list";

    public static String getCustomerTelecomTopUp(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return CUSTOMER_TELECOMEGYPT_TOPUPar;
        }
        return CUSTOMER_TELECOMEGYPT_TOPUPen;
    }
    public static final String PAGE_TelecomEgypt_TOPUP = "/service/TelecomEgyptTopup/TelecomEgyptTopUp.jsp";
    public static final String PAGE_TelecomEgypt_CONFIRMATION = "/service/TelecomEgyptTopup/TelecomEgyptTopUpConfirmation.jsp";
    public static final String PAGE_TelecomEgypt_Recipt = "/service/TelecomEgyptTopup/PrintingTelecomTopup.jsp";
    public static final String ACTION_TELECOMEEGYPT_TOPUP_HOME = "ACTION_TELECOMEEGYPT_TOPUP_HOME";
//    public static final String ACTION_ALAHLY_GETINFO = "ACTION_ALAhly_GETINFO";
//    public static final String ACTION_ALAHLY_Payment = "ACTION_ALAhly_Payment";

//*****************************************TEData Bills************************************
    //public static final String ACTION_TEDATA_Bill_Inquiry_REQ= "TEDATA_Bill_Inquiry_REQ";
    public static final String PAGE_TEDATA_Bill_Inquiry_REQ = "/service/TEData/TEData_Inquiry_Request.jsp";

    // public static final String ACTION_TEDATA_Bill_Inquiry_RESP= "TEDATA_Bill_Inquiry_RESP";
    public static final String PAGE_TEDATA_Bill_Inquiry_RESP = "service/TEData/TEData_Inquiry_Response.jsp";
    public static final String PAGE_TEDATA_Bill_Payment_REQ = "service/TEData/TEData_Payment_Request.jsp";

//************************************Loyalty Points ******************************************
    private final static String LoyaltyPointsar = "نقاط المسابقة";
    private final static String LoyaltyPointsen = "Loyalty Points";

    public static String getLoyaltyPointsName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return LoyaltyPointsar;
        }
        return LoyaltyPointsen;
    }

    public static final String LoyaltyPointsPage = "service/LoyaltyPoints/LoyaltyPoints.jsp";

    /**
     * **********************ESED Service**********************
     */
    public static final String ACTION_ESED_INQUIRY = "ACTION_ESED_INQUIRY";
    public static final String ACTION_ESED_INFO = "ACTION_ESED_INFO";
    public static final String ACTION_ESED_PAYMENT = "ACTION_ESED_PAYMENT";
    public static final String ACTION_ESED_PAYMENT_CONFIRMATION = "ACTION_ESED_PAYMENT_CONFIRMATION";
    public static final String PAGE_ESED_INQUIRY = "/service/ESED/HomePage.jsp";
    public static final String PAGE_ESED_INFO = "/service/ESED/InfoPage.jsp";
    public static final String PAGE_ESED_PAYMENT = "/service/ESED/PaymentPage.jsp";
    public static final String PAGE_ESED_PAYMENT_CONFIRMATION = "/service/ESED/ConfirmationPaymentPage.jsp";
    public static final String ESED_NationalID = "الرقم القومى:";
    public static final String ESED_ClientKey = "كود العميل:";
    public static final String ESED_ClientKey_National = "الرقم القومى";
    public static final String ESED_SERVICE_NAME = "البيانات المطلوبة";
    public static final String ESED_Confirmation = "تأكيد الدفع";
    public static final String ESED_Confirmation_Commission_Label = "حسابك";
    public static final String ESED_Confirmation_Label = "تأكيد البيانات";
    public static final String ESED_Confirmation_Cancel = "إلغاء";
    public static final String ESED_DueDate = "تاريخ القسط المستحق";
    public static final String ESED_TxnAmount = "قيمة القسط";
    public static final String ESED_Info_Lavel = "البيانات المستعلم عنها";
    public static final String ESED_Confirmation_DesiredAmount1 = "المبلغ المطلوب دفعه";
    public static final String ESED_Confirmation_DesiredAmount2 = "شاملا الرسوم وضريبة";
    public static final String ESED_Confirmation_DesiredAmount3 = "القيمة المضافة";
    public static final String ESED_Confirmation_Commission = "تكلفة الخدمة";
    public static final String ESED_Confirmation_DeductedAmount = "المبلغ المخصوم";
    public static final String ESED_Payment_PaidAmount = "المبلغ بالجنيه شاملة الرسوم";
    public static final String ESED_Payment_MerchantID = "رقم البائع";
    public static final String ESED_Payment_TrxID = "رقم العملية";
    public static final String ESED_Payment_TrxTimeDate = "تاريخ وتوقيت العملية";
    public static final String ESED_Payment_Commission = "عمولتك هى";
    public static final String ESED_Payment_DeductedAmount = "تم خصم";
    public static final String ESED_Payment_CurrentBalance = "رصيدك الحالى";
    public static final String PARAM_CLIENTKEY_LEFT = "CLIENTKEY_LEFT";
    public static final String PARAM_CLIENTKEY_RIGHT = "CLIENTKEY_RIGHT";
    public static final String LEFT_CLIENTKEY = "LEFT_CLIENTKEY";
    public static final String ERROR_ESED_WRONGNUMBER = "برجاء التأكد من صحة الرقم القومى/كود العميل";
    public static final String ESEDInquiryFailureAr = "تعذر الاتصال بالشبكة الرجاء المحاولة فى وقت لاحق";
    public static final String ESEDInquiryFailureEn = "Connection refused : please try later";
    public static final String ESEDNotFoundNumberAr = "لا يوجد اقساط لهذا العميل";
    public static final String ESEDNotFoundNumberEn = "No Installment available for this Customer";
    private static final String RecieptMessESEDAr = "الجمعية المصرية لمساعدة صغار الصناع و الحرفيين";
    private static final String RecieptMessESEDEn = "ESED Egyptian Small Enterprise Development Foundation";
    public static final String ESEDInvalidNationalIDAr = "الرقم القومى غير صحيح";
    public static final String ESEDInvalidNationalIDEn = "Invalid national ID";
    public static final String ESEDInvalidNationalIDOrCodeNumberAr = "كود العميل او الرقم القومى غير صحيح";
    public static final String ESEDInvalidNationalIDOrCodeNumberEn = "Invalid national ID or Code number";
    public static final String ESEDCustomerNotFoundAr = "لا يوجد عميل بهذا الرقم";
    public static final String ESEDCustomerNotFoundEn = "Customer not found";
    public static final String ESEDNoInstallmentFoundAr = "لا يوجد اقساط لهذا العميل";
    public static final String ESEDNoInstallmentFoundEn = "No installments found for this customer";
    public static final String ESEDMasaryInquiryFailureAr = "خطأ اثناء تنفيذ الاستعلام";
    public static final String ESEDMasaryInquiryFailureEn = "Masary Inquiry failure";
    public static final String ESEDIntegrationFailureAr = "خطأ اثناء الاتصال حاول فى وقت لاحق";
    public static final String ESEDIntegrationFailureEn = "Integration is failed try again later";
    public static final String ESEDPaymentFailureAr = "خطأ اثناء تنفيذ عملية الدفع";
    public static final String ESEDPaymentFailureEn = "Payment is failed";
    public static final String ESEDClassNotFoundAr = "عملية غير مقبولة يرجى التوجه لأحد مقرات الجمعية لسداد القسط المستحق";
    public static final String ESEDClassNotFoundEn = "Installment date is expired";
    public static final String ESEDServiceNotAvailableAr = "تعذر الاتصال بالشبكة او الخدمة قد تكون معطلة";
    public static final String ESEDServiceNotAvailableEn = "Connection error or service maybe not available";
    public static final String ESEDRecieptMessAr = "لقد تم دفع القسط بنجاح";
    public static final String ESEDRecieptMessEn = "Transaction is succesfully paid";
    public static final String ESEDAmountOutOfRangeAr = "يجب ان تدفع قيمة اقدم قسط اولا";
    public static final String ESEDAmountOutOfRangeEn = "Must pay oldest installment first";
    public static final String ESEDInActiveAccountAr = "الحساب غير نشط حاليا";
    public static final String ESEDInActiveAccountEn = "Account is inactive";
    public static final String ESEDInternalServiceErrorAr = "خطأ اثناء تنفيذ العملية";
    public static final String ESEDInternalServiceErrorEn = "Error while procceding the transaction";
    public static final String ESEDNotAcceptableAr = "عملية غير مقبولة";
    public static final String ESEDNotAcceptableEn = "Not acceptable transaction";
    public static final String ESEDTrxAmountNotInRangeAr = "القيمة خارج الحد المسموح به";
    public static final String ESEDTrxAmountNotInRangeEn = "Trx amount is out of range";
    public static final String ESEDMissingParamAr = "يوجد عامل مفقود";
    public static final String ESEDMissingParamEn = "Missing Parameter";
    public static final String ESEDAccountBalanceAr = "رصيد محفظتك غير كافى";
    public static final String ESEDAccountBalanceEn = "Account balance not enough";

    public static String getESEDRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return RecieptMessESEDAr;
        }
        return RecieptMessESEDEn;
    }

    /**
     * **********************FMF Service**********************
     */
    public static final String ACTION_FMF_INQUIRY = "ACTION_FMF_INQUIRY";
    public static final String ACTION_FMF_GETINFO = "ACTION_FMF_GETINFO";
    public static final String ACTION_FMF_CONFIRMATION = "ACTION_FMF_CONFIRMATION";
    public static final String ACTION_FMF_PAYMENT = "ACTION_FMF_PAYMENT";
    public static final String PAGE_FMF_HOME = "/service/FMF/HomePage.jsp";
    public static final String PAGE_FMF_INFO = "/service/FMF/InfoPage.jsp";
    public static final String PAGE_FMF_CONFIRMATION = "/service/FMF/ConfirmationPage.jsp";
    public static final String PAGE_FMF_PAYMENT = "/service/FMF/PaymentPage.jsp";
    public static final String FMF_CUSTOMER_CODE = "كود العميل";
    public static final String FMF_CUSTOMER_NAME = "اسم العميل";
    public static final String FMF_DUE_DATE = "تاريخ استحقاق القسط";
    public static final String FMF_INSTALLMENT_AMOUNT = "قيمة القسط";
    public static final String FMF_FULL_PAYMENT = "دفع كلى";
    public static final String FMF_PARTIAL_PAYMENT = "دفع جزئى";
    public static final String FMF_TO_BE_PAID = "المبلغ المراد دفعه";
    public static final String FMF_APPLIED_FEES = "تكلفة الخدمة شاملة ضريبة القيمة المضافة";
    public static final String FMF_TOTAL_TOBEPAID = "إجمالى المبلغ المطلوب دفعه";
    public static final String FMF_SERVICE_NAME = "المؤسسة الأولى للتمويل متناهى الصغر";
    public static final String FMFOutOfRangeAr = "المبلغ المطلوب دفعه قد يكون اكبر من 3000";
    public static final String FMFOutOfRangeEn = "To be paid amount is more than 3000";
    public static final String FMFNoInstallmentFoundAr = "كود العميل غير موجود او لا يوجد أقساط مستحقه";
    public static final String FMFNoInstallmentFoundEn = "No available installment for this customer";
    public static final String FMFServiceNotAvailableAr = "تعذر الاتصال بالشبكة او الخدمة قد تكون معطلة";
    public static final String FMFServiceNotAvailableEn = "Connection error or service maybe not available";
    public static final String FMFRecieptMessAr = "لقد تم دفع القسط بنجاح";
    public static final String FMFRecieptMessEn = "Transaction is succesfully paid";
    public static final String FMFAmountOutOfRangeAr = "يجب ان تدفع قيمة اقدم قسط اولا";
    public static final String FMFAmountOutOfRangeEn = "Must pay oldest installment first";
    public static final String FMFInActiveAccountAr = "الحساب غير نشط حاليا";
    public static final String FMFInActiveAccountEn = "Account is inactive";
    public static final String FMFInternalServiceErrorAr = "خطأ اثناء تنفيذ العملية";
    public static final String FMFInternalServiceErrorEn = "Error while procceding the transaction";
    public static final String FMFNotAcceptableAr = "عملية غير مقبولة";
    public static final String FMFNotAcceptableEn = "Not acceptable transaction";
    public static final String FMFTrxAmountNotInRangeAr = "القيمة خارج الحد المسموح به";
    public static final String FMFTrxAmountNotInRangeEn = "Trx amount is out of range";
    public static final String FMFMissingParamAr = "يوجد عامل مفقود";
    public static final String FMFMissingParamEn = "Missing Parameter";
    public static final String FMFAccountBalanceAr = "رصيد محفظتك غير كافى";
    public static final String FMFAccountBalanceEn = "Account balance not enough";

    public static String getFMFRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FMFRecieptMessAr;
        }
        return FMFRecieptMessEn;
    }

    /**
     * **********************SMS MISR Service**********************
     */
    public static final String ACTION_SMSMISR_INQUIRY = "ACTION_SMSMISR_INQUIRY";
    public static final String ACTION_SMSMISR_GETINFO = "ACTION_SMSMISR_GETINFO";
    public static final String ACTION_SMSMISR_CONFIRMATION = "ACTION_SMSMISR_CONFIRMATION";
    public static final String ACTION_SMSMISR_PAYMENT = "ACTION_SMSMISR_PAYMENT";
    public static final String PAGE_SMSMISR_HOME = "/service/SmsMisr/HomePage.jsp";
    public static final String PAGE_SMSMISR_INFO = "/service/SmsMisr/InfoPage.jsp";
    public static final String PAGE_SMSMISR_CONFIRMATION = "/service/SmsMisr/ConfirmationPage.jsp";
    public static final String PAGE_SMSMISR_PAYMENT = "/service/SmsMisr/PaymentPage.jsp";
    public static final String SMSMISR_SERVICE_NAME = "إس إم إس مصر";
    public static final String SMSMISR_Confirmation_Label = "بيانات العملية";
    public static final String SMSMISR_Confirmation_DesiredAmount1 = "المبلغ المطلوب دفعه";
    public static final String SMSMISR_Confirmation_DesiredAmount2 = "شامل ضريبة القيمة المضافة";
    public static final String SMSMISR_Customer_Code = "رقم العميل";
    public static final String SMSMISR_Confirmation_Code = "كود التأكيد";
    public static final String SMSMISR_Applied_Fees = "تكلفة الخدمة";
    public static final String SMSMISR_Commission = "عمولتك";
    public static final String SMSMISR_Deducted_Amount = "سيتم خصم";
    public static final String MESSAGE_SMSMISR_ERRORAr = "خطأ اثناء تنفيذ العملية";
    public static final String SmsMisrRecieptMessAr = "لقد تم الدفع بنجاح";
    public static final String SmsMisrRecieptMessEn = "Transaction Succeded";
    public static final String MESSAGE_SMSMISR_ERROREn = "Error while doing transaction";
    public static final String MESSAGE_SMSMISR_InvalidIDAr = "رقم العميل غير صحيح";
    public static final String MESSAGE_SMSMISR_InvalidIDEn = "Invalid Customer Code";
    public static final String MESSAGE_SMSMISR_CONNECTONERRORAr = "خطأ اثناء الاتصال بالشبكة . الرجاء المحاولة لاحقاً";
    public static final String MESSAGE_SMSMISR_CONNECTONERROREn = "Connection refused . try again later";

    public static String getSmsMisrRecieptMess1(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SmsMisrRecieptMessAr;
        }
        return SmsMisrRecieptMessEn;
    }

    /**
     * ************************** Valu (efgHermes)
     * Service*********************************
     */
    public static final String ACTION_VALU_INQUIRY = "ACTION_VALU_INQUIRY";
    public static final String ACTION_VALU_GETINFO = "ACTION_VALU_GETINFO";
    public static final String ACTION_VALU_Payment = "ACTION_VALU_Payment";
    public static final String ACTION_VALU_CONFIRMATION = "ACTION_VALU_CONFIRMATION";
    public static final String ValuServiceName = "فاليو";
    public static final String ValuDataEntry = "البيانات المطلوبة";
    public static final String ValuInstallmentData = "بيانات الاقساط";
    public static final String ValuPaymentConfirmation = "تأكيد الدفع";
    public static final String ValuLabel = "رقم موبايل العميل";
    public static final String ValuAmount = "القيمة المطلوبة";
    public static final String PaymentAr = "تاكيد الدفع";
    public static final String PaymentEn = "Payment";
    public static final String errorValuAmountAr = "خطأ قيمه الدفع اكبر من قيمه الاقساط";
    public static final String errorValuPaidAmountAr = "اقل مبلغ يمكن ادخاله 20 جنيه ";
    public static final String errorValuPaidAmountEn = "the minimum amount must be more than 20";

    public static final String errorValuAmountEn = "Error!! the value of payment is greater than installment value";
    public static final String entryValuPaymentAr = "ادخل المبلغ المراد دفعه";
    public static final String entryValuPaymentEn = "Enter your Payment";
    public static final String errorValuMobileNumAr = "برجاء التأكد من صحة رقم موبيل العميل ";
    public static final String errorValuMobileNumEn = "Invalid number; must be ten digits";
    public static final String errorValuConnectionRefusedAr = "الخدمة غير متاحة برجاء المحاولة لاحقا";
    public static final String errorValuConnectionRefusedEn = "Service Unavailable please try again later";
    public static final String errorTransactionValuar = "خطأ فى العمليه من مزود الخدمه";
    public static final String errorTransactionValuar2 = "المبلغ المدفوع اكبر من قيمة القسط ، الرجاء ادخال قيمة اقل من او تساوي قيمة القسط";
    public static final String errorTransactionValuar3 = "هذا الرقم ليس مسجل لدى مزود الخدمة (فاليو)";
    public static final String errorTransactionValuar4 = "لا يوجداقساط لهذا الرقم";
    public static final String DuplicatedTransactionAr = "خطأ اثناء تنفيذ العملية , عملية دفع مكررة";
    public static final String DuplicatedTransactionEn = "Error during execution transaction , Duplicated Transaction";
    public static final String PAGE_VALU_Inquiry = "/service/Valu/ValuInquiry.jsp";
    public static final String PAGE_VALU_INFO = "/service/Valu/ValuInfo.jsp";
    public static final String PAGE_VALU_PAYMENT = "/service/Valu/ValuPaymentConfirmation.jsp";
    public static final String PAGE_VALU_CONFIRMATION2 = "/service/Valu/ValuConfirmation.jsp";
    //-------valu cr adding reprint---
     public static final String PAGE_VALU_REPRINT = "/service/Valu/ValuReprint.jsp";
     public static final String errorTransactionValuar5 = "حدث خطأ في تنفيذ العملية نرجو المحاولة بعد قليل";
    //----------------------

    public static String dueAmountAr = "إجمالي قيمة الأقساط المستحق دفعها بالجنيه";
    public static String dueAmountEn = "dueAmount";
    private static String paidAmountEn = "paidAmount";
    private static String paidAmountAr = "المبلغ المدفوع";
    private static String mobileNoEn = "mobileNo";
    private static String mobileNoAr = " رقم موبايل العميل";
    private static String continueAr = "استمرار";
    private static String continueEn = " Continue";
    private static String backAr = " رجوع";
    private static String backEn = " Back";
    private static String merchantCommissionAR = "عموله التاجر";
    private static String merchantCommissionEN = "Merchant Commission";

    public static String geterrorValuPaidAmountAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return errorValuPaidAmountAr;
        }
        return errorValuPaidAmountEn;
    }

    public static String geterrorValuPaidAmountEn(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return errorValuPaidAmountEn;
        }
        return errorValuPaidAmountEn;
    }

    public static String getContinueAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return continueAr;
        }
        return continueEn;
    }

    public static String getContinueEn(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return continueEn;
        }
        return continueEn;
    }

    public static String getmerchantCommissionAR(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return merchantCommissionAR;
        }
        return merchantCommissionEN;
    }

    public static String getmerchantCommissionEN(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return merchantCommissionEN;
        }
        return merchantCommissionEN;
    }

    public static String getBackAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return backAr;
        }
        return backEn;
    }

    public static String getBackEn(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return backEn;
        }
        return backEn;
    }

    public static String getMobileNoAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return mobileNoAr;
        }
        return mobileNoEn;
    }

    public static String getmobileNoEn(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return mobileNoEn;
        }
        return mobileNoEn;
    }

    public static String getDueAmountAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return dueAmountAr;
        }
        return dueAmountEn;
    }

    public static String getDueAmountEn(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return dueAmountEn;
        }
        return dueAmountEn;
    }

    public static String getPaidAmountAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return paidAmountAr;
        }
        return paidAmountEn;
    }

    public static String getPaidAmountEn(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return paidAmountEn;
        }
        return paidAmountEn;
    }

    public static String getPaymentAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PaymentAr;
        }
        return PaymentEn;
    }

    public static String getPaymentEn(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PaymentEn;
        }
        return PaymentEn;
    }

    public static String getErrorValuAmountAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return errorValuAmountAr;
        }
        return errorValuAmountEn;
    }

    public static String getErrorValuAmountEn(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return errorValuAmountEn;
        }
        return errorValuAmountEn;
    }

    public static String getEntryValuPaymentAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return entryValuPaymentAr;
        }
        return entryValuPaymentEn;
    }

    public static String getEntryValuPaymentEn(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return entryValuPaymentEn;
        }
        return entryValuPaymentEn;
    }

    public static String getErrorValuMobileNumAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return errorValuMobileNumAr;
        }
        return errorValuMobileNumEn;
    }

    public static String getErrorValuMobileNumEn(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return errorValuMobileNumEn;
        }
        return errorValuMobileNumEn;
    }

    /**
     * **********************SECI Service**********************
     */
    public static final String Account_En = "Account";
    public static final String Account_Ar = "حسابك";
    public static final String Menu_En = "Menu";
    public static final String Menu_Ar = "القائمة الرئيسية";
    public static final String Donation_En = "General donation ";
    public static final String Donation_Ar = " تبرع عام";
    public static final String Mobile_En = "Mobile number ";
    public static final String Mobile_Ar = " رقم الموبايل ";
    public static final String TotalAmountMan_Ar = "القيمة المطلوبة من العميل";
    public static final String TotalAmountMan_En = "Total amount";
    public static final String DeductedAmountMan_Ar = "سيتم خصم";
    public static final String DeductedAmountMan_En = "Will Deduct";
    public static final String Action_Ar = "تنفيذ";
    public static final String Action_En = "Done";
    public static final String SECIName_Ar = "مستشفى 2020";
    public static final String SECIName_En = "Hospital 2020";
    public static final String SECIDescribtio_En = "Hospital 2020";
    public static final String SECIDescribtio_Ar = "لمستشفى 2020";
    public static final String GetGoSECI_Ar = "تأكيد ";
    public static final String GetGoSECI_En = "Confirm";
    public static final String PhoneNumber_Ar = "رقم الموبايل";
    public static final String PhoneNumber_En = "Mobile Number";
    public static final String AmountAr = "مبلغ التبرع";
    public static final String AmountEn = "Amount";
    public static final String DonationAmountAr = "مبلغ التبرع";
    public static final String DonationAmountEn = "Donation Amount";
    public static final String PAGE_SECI = "/service/SECI/SECIHome.jsp";
    public static final String SECI_Confirmation_Page = "/service/SECI/SECIConfirmation.jsp";
    public static final String SECI_Receipt_Page = "/service/SECI/SECIReceipt.jsp";
    public static final String SECI_Reprint_Page = "/service/SECI/SECIReprint.jsp";
    public static final String AboElRish_Reprint_Page = "/service/AbuElrish/AbuElRishReprint.jsp";

    public static String get_AmountDonation(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DonationAmountAr;
        }
        return DonationAmountEn;
    }

    public static String get_Amount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return WKAmountAr;
        }
        return WKAmountEn;
    }

    /**
     * **********************Abu elresh Service**********************
     */
    public static final String PAGE_AbuElRish_Home = "/service/AbuElrish/AbuElrishHome.jsp";
    public static final String PAGE_AbuElRish_Confirmation = "/service/AbuElrish/AbuElrishConfirmation.jsp";
    public static final String PAGE_AbuElRish_Receipt = "/service/AbuElrish/AbuElrishReceipt.jsp";
    public static final String AbuElRishAr = "مستشفى ابو الريش";
    public static final String AbuElRishEn = "Abu El Rish Hospital";
    public static final String MenuAbuElRishAr = "القائمة الرئيسية";
    public static final String MenuAbuElRishEn = "Menu";
    public static final String PhoneAbuElRishAr = "رقم الموبايل";
    public static final String PhoneAbuElRishEn = "Mobile";
    public static final String donationAbuElRishAr = "مبلغ التبرع";
    public static final String donationAbuElRishEn = "Donation Amount";
    public static final String totalAmountAbuElRishAr = "القيمة المطلوبة من العميل";
    public static final String totalAmountAbuElRishEn = "Required Amount";
    public static final String DoneAbuElRishAr = "تنفيذ";
    public static final String DoneAbuElRishEn = "Done";
    public static final String NameAbuElRishAr = "خدمة التبرع لمستشفى الاطفال الجامعى التخصصى";
    public static final String NameAbuElRishEn = "خدمة التبرع لمستشفى الاطفال الجامعى التخصصى";
    public static final String describtionAbuElRishAr = "ل ابو الريش اليابانى";
    public static final String describtionAbuElRishEn = "ل ابو الريش اليابانى";
    public static final String confirmAbuElRishAr = "تأكيد";
    public static final String confirmAbuElRishEn = "Confirm";
    public static final String DataAbuElRishAr = "حسابك";
    public static final String DataAbuElRishEn = "Account";

    public static String get_PhoneNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PhoneNumber_Ar;
        }
        return PhoneNumber_En;
    }

    public static String getGo_SECI(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return GetGoSECI_Ar;
        }
        return GetGoSECI_En;
    }

    public static String get_SECIDescription(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SECIDescribtio_Ar;
        }
        return SECIDescribtio_En;
    }

    public static String get_SECI(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return SECIName_Ar;
        }
        return SECIName_En;
    }

    public static String get_Done(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Action_Ar;
        }
        return Action_En;
    }

    public static String getDeductedAmountMan(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DeductedAmountMan_Ar;
        }
        return DeductedAmountMan_En;
    }

    public static String getTotalAmountMan(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TotalAmountMan_Ar;
        }
        return TotalAmountMan_En;
    }

    public static String getAccount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Account_En;
        }
        return Account_Ar;
    }

    public static String getMenu(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Menu_Ar;
        }
        return Menu_En;
    }

    public static String getDonation(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Donation_En;
        }
        return Donation_Ar;
    }

    public static String getMobile(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Mobile_En;
        }
        return Mobile_Ar;
    }

    public static String getCommessionMan(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Commessionar;
        }
        return CommessionMaanen;
    }

    public static String getTiltle(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return Commessionar;
        }
        return CommessionMaanen;
    }

    public static String getAbuElRish_Data(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DataAbuElRishAr;
        }
        return DataAbuElRishEn;
    }

    public static String getAbuElRish_Confirmation(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return confirmAbuElRishAr;
        }
        return confirmAbuElRishEn;
    }

    public static String getAbuElRish_Description(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return describtionAbuElRishAr;
        }
        return describtionAbuElRishEn;
    }

    public static String getName_AbuElRish(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return NameAbuElRishAr;
        }
        return NameAbuElRishEn;
    }

    public static String get_Done_AbuElRish(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return DoneAbuElRishAr;
        }
        return DoneAbuElRishEn;
    }

    public static String getTotalAmountManAbuElRish(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return totalAmountAbuElRishAr;
        }
        return totalAmountAbuElRishEn;
    }

    public static String get_AmountDonationAbuElRish(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return donationAbuElRishAr;
        }
        return donationAbuElRishEn;
    }

    public static String get_PhoneNumberAbuElRish(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return PhoneAbuElRishAr;
        }
        return PhoneAbuElRishEn;
    }

    public static String getMenu_AbuElRish(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return MenuAbuElRishAr;
        }
        return MenuAbuElRishEn;
    }

    public static String get_AbuElRish(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AbuElRishAr;
        }
        return AbuElRishEn;
    }

    /**
     * **********************beIN Sports Service**********************
     */
    public static final String PAGE_beINSports_Home = "/service/BeinSports/BeinSportsHome.jsp";
    public static final String PAGE_beINSports_Confirm = "/service/BeinSports/BeinSportsConfirmation.jsp";
    public static final String PAGE_beINSports_Reprint = "/service/BeinSports/Reprint.jsp";

    public static final String beInSportsAr = "beIN";
    public static final String beInSportsEn = "beIN";
    public static final String beInSportsMobileAr = "رقم الموبايل";
    public static final String beInSportsMobileEn = "Mobile";
    public static final String beInSportsSubscribtionAr = "رقم الاشتراك";
    public static final String beInSportsSubscribtionEn = "Subscribtion Number";
    public static final String beInSportsInquiryAr = "استعلام";
    public static final String beInSportsInquiryEn = "Inquiry";
    public static final String beInSportsInfoAr = "بيانات العملية";
    public static final String beInSportsInfoEn = "operation information";
    public static final String beInSportsSubscriberNameAr = "اسم المشترك";
    public static final String beInSportsSubscriberNameEn = "Subscriber Name";
    public static final String beInSportsPackageAr = "باقة";
    public static final String beInSportsPackageEn = "Package";
    public static final String beInSportsPackagedateAr = "تاريخ انتهاء الباقة";
    public static final String beInSportsPackagedateEn = "Package expiration date";
    public static final String beInSportsRequiredAmountAr = "المبلغ المطلوب";
    public static final String beInSportsRequiredAmountEn = "The required amount";
    public static final String beInSportsCosteAr = "تكلفة الخدمة";
    public static final String beInSportsCostEn = "ٍService Cost";
    public static final String beInSportscommissionAr = "عمولة التاجر";
    public static final String beInSportscommissionEn = "Merchant Commission";
    public static final String beInSportsDeductedAmountAr = "سيتم خصم";
    public static final String beInSportsDeductedAmountEn = "Deducted Amount";
    public static final String beInSportsTotalAmountAr = "اجمالى المبلغ المطلوب دفعه";
    public static final String beInSportsTotalAmountEn = "Total Amount";
    public static final String beInSportsconfirmAr = "تأكيد و طباعة";
    public static final String beInSportsconfirmEn = "Confirm and Print";
    public static final String beInSportssubscribeAr = "إشتراك";
    public static final String beInSportssubscribeEn = "Subscribe";
    public static final String beInSportsTransactionIdAr = "رقم العملية";
    public static final String beInSportsMerchantAr = "رقم التاجر";
    public static final String beInSportsClientNameAr = "اسم العميل";
    public static final String beInSportsPackageNameAr = "الباقة";
    public static final String beInSportsAmountAr = "مبلغ الاشتراك";
    public static final String beInSportsAppliesFeesAr = "تكلفة الخدمة شاملة ضريبة القيمة المضافة";
    public static final String beInSportsToBePaidAr = "اجمالى البلغ المطلوب دفعه";
    public static final String beInSportsDateAr = "تاريخ وتوقيت العملية";
    public static final String errorTransactionBeinSportsAr = "برجاء الاستعلام مرة أخرى";
    public static final String errorTransactionBeinSportsEn = "Please inquire again";
    public static final String errorTransactionBeinSportsE2Ar = "هذا الرقم غير صحيح، برجاء التأكد من الرقم وإعادة المحاولة مرة أخرى ";
    public static final String errorTransactionBeinSportsE2En = "This number is invalid please try again";
    public static final String errorTransactionBeinSportsE3Ar = "خطأ اثناء تنفيذ العملية، برجاء المحاولة في وقت لاحق";
    public static final String errorTransactionBeinSportsE3En = "Error during transaction,please try again";
    public static final String errorTransactionBeinSportsE4Ar = "لا توجد اشتراكات مستحقة لهذا الرقم";
    public static final String errorTransactionBeinSportsE4En = "No bill found for this customer";

    public static String get_beInSportsDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsDateAr;
        }
        return beInSportsDateAr;
    }

    public static String get_beInSportsAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsAmountAr;
        }
        return beInSportsAmountAr;
    }

    public static String get_beInSportsAppliesFees(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsAppliesFeesAr;
        }
        return beInSportsAppliesFeesAr;
    }

    public static String get_beInSportsToBePaid(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsToBePaidAr;
        }
        return beInSportsToBePaidAr;
    }

    /**
     * **********************TE Data Top Up Service**********************
     */
    public static final String TEData_Home_Page = "/service/TE-Data-TopUp/TEDataHome.jsp";
    public static final String TEData_Confirmation_Page = "/service/TE-Data-TopUp/TEDataConfirmation.jsp";
    public static final String TEData_Receipt_Page = "/service/TE-Data-TopUp/TEDataReceipt.jsp";
    public static final String TEData_Reprint_Page = "/service/TE-Data-TopUp/TEDataReprint.jsp";
    public static final String TedataNameAr = "وي الانترنت المنزلي";
    public static final String TedataNameEn = "WE Home Internet";
    public static final String TedataChargingCategoryAr = "فئة الشحن";
    public static final String TedataChargingCategoryEn = "Charging category";
    public static final String TedataValueOfChargingCategoryAr = "قيمة فئة الشحن";
    public static final String TedataValueOfChargingCategoryEn = "Value charging category";
    public static final String TedataPhoneNumberAr = "رقم الخط الارضى";
    public static final String TedataPhoneNumberEn = "Phone number";
    public static final String TedataPhoneNumberConfirmationAr = "تأكيد رقم الخط الارضى";
    public static final String TedataPhoneNumberConfirmationEn = "Phone number confirmation";
    public static final String TedataConfirmedDataAr = "معلومات مطلوبة";
    public static final String TedataConfirmedDataEn = "Required data";
    public static final String TedataCategoryValueAr = "قيمة الفئة";
    public static final String TedataCategoryValueEn = "Category value";
    public static final String TedataPaymentAr = "دفع";
    public static final String TedataPaymentEn = "Pay";
    public static final String TedatacancelAr = "إلغاء";
    public static final String TedatacancelEn = "Cancel";
    public static final String TedataWeCharegAr = "شحن WE الانترنت المنزلى";
    public static final String TedataWeChargeEn = "شحن WE الانترنت المنزلى";
    public static final String TedataWeBonusCharegAr = "شحن WE الانترنت المنزلى";
    public static final String TedataWeBonusChargeEn = "شحن WE الانترنت المنزلى";
    public static final String TedataTotalAmountAr = "المبلغ الاجمالى";
    public static final String TedataTotalAmountEn = "Total Price";
    public static final String TedataWalletNumberAr = "رقم المحفظة";
    public static final String TedataWalletNumberEn = "Wallet Number";
    public static final String TedataThanksAr = "شكرا لاستخدامكم مصارى لخدمات الدفع الذكية";
    public static final String TedataThanksEn = "شكرا لاستخدامكم مصارى لخدمات الدفع الذكية";
    public static final String TedataCustomerAr = "خدمة عملاء مصارى: 16994";
    public static final String TedataCustomerEn = "خدمة عملاء مصارى: 16994";
    public static final String TedataWebSiteAr = "www.e-masary.com";
    public static final String TedataWebSiteEn = "www.e-masary.com";
    public static final String TedataChargeAr = "شحن باقة WE الانترنت المنزلى";
    public static final String TedataChargeEn = "شحن باقة WE الانترنت المنزلى";
    public static final String TedataPhoneAr = "رقم التليفون";
    public static final String TedataPhoneEn = "Phone Number";
    public static final String TedataTotalAr = "المبلغ المدفوع";
    public static final String TedataTotalEn = "To be paied";
    public static final String TedataTimeAr = "الوقت";
    public static final String TedataTimeEn = "Time";
    public static final String TedataMerchantNumberAr = "رقم التاجر";
    public static final String TedataMerchantNumberEn = "Merchant Id";
    public static final String TedataDateAndTimeAr = "التاريخ و التوقيت";
    public static final String TedataDateAndTimeEn = "Date and Time";
    public static final String TedataCostAr = "تكلفة الخدمه شاملة ضريبه القيمة المضافة";
    public static final String TedataCostEn = "تكلفة الخدمه شاملة ضريبه القيمة المضافة";
    public static final String TedataSubscriberNumberAr = "رقم المشترك";
    public static final String TedataSubscriberNumberEn = "Subscriber Number";
    public static final String TedataAmountAr = "المبلغ";
    public static final String TedataAmountEn = "Amount";
    public static final String TedataInquiryAr = "استعلام";
    public static final String TedataInquiryEn = "Inquiry";
    public static final String TedataRequiredAmountAr = "المبلغ المطلوب من العميل";
    public static final String TedataRequiredAmountEn = "Required amount from customer";
    public static final String TedataCategoryAr = "فئة الشحن GB";
    public static final String TedataCategoryEn = "Charging Category GB";
    public static final String TedataChooseValueAr = "اختر القيمة";
    public static final String TedataChooseValueEn = "Choose the value";
    public static final String errorTedataproviderAr = "خطأ من مزود الخدمة";
    public static final String errorTedataproviderEn = "Error from provider";
    public static final String errorTedatapackageAr = "برجاء اعادة اختيار الباقة المطلوبه";
    public static final String errorTedatapackageEn = "Please choose the package";
    public static final String errorTedataoperationAr = "خطا في تنفيذ العملية برجاء اعادة المحاولة";
    public static final String errorTedataoperationEn = "Error during calling service please try again";
    public static final String errorTedatachargeAr = "لا يمكن الشحن علي هذا الرقم برجاء الاتصال بشركة TE Data";
    public static final String errorTedatachargeEn = "Provider cannot charge number please call TE Data";
    public static final String errorTedataInvalidAr = "الرقم الذي ادخلته غير صحيح برجاء اعادة ادخال الرقم";
    public static final String errorTedataInvalidEn = " Provider invalid adsl number please try again";
    public static final String TedataWeChareg2Ar = "شحن WE للانترنت المنزلى";
    public static final String TedataWeCharge2En = "شحن WE للانترنت المنزلى";

    public static String get_TEDataName2(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataWeChareg2Ar;
        }
        return TedataWeCharge2En;
    }

    public static String get_TEDataChooseValue(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataChooseValueAr;
        }
        return TedataChooseValueEn;
    }

    public static String get_TEDataCategory(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataCategoryAr;
        }
        return TedataCategoryEn;
    }

    public static String get_TEDataRequiredAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataRequiredAmountAr;
        }
        return TedataRequiredAmountEn;
    }

    public static String get_TEDataInquiry(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataInquiryAr;
        }
        return TedataInquiryEn;
    }

    public static String get_TEDataAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataAmountAr;
        }
        return TedataAmountEn;
    }

    public static String get_TEDataSubscriberNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataSubscriberNumberAr;
        }
        return TedataSubscriberNumberEn;
    }

    public static String get_TEDataCost(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataCostAr;
        }
        return TedataCostEn;
    }

    public static String get_beInSportsPackageName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsPackageNameAr;
        }
        return beInSportsPackageNameAr;
    }

    public static String get_beInSportsClientName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsClientNameAr;
        }
        return beInSportsClientNameAr;
    }

    public static String get_TEDataDateAndTime(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataDateAndTimeAr;
        }
        return TedataDateAndTimeEn;
    }

    public static String get_TEDataMerchantID(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataMerchantNumberAr;
        }
        return TedataMerchantNumberEn;
    }

    public static String get_TEDataTime(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataTimeAr;
        }
        return TedataTimeEn;
    }

    public static String get_TEDataTotal(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataTotalAr;
        }
        return TedataTotalEn;
    }

    public static String get_TEDataPhone(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataPhoneAr;
        }
        return TedataPhoneEn;
    }

    public static String get_TEDataCharge(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataChargeAr;
        }
        return TedataChargeEn;
    }

    public static String get_TEDataThanks(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataThanksAr;
        }
        return TedataThanksEn;
    }

    public static String get_TEDataCustomer(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataCustomerAr;
        }
        return TedataCustomerEn;
    }

    public static String get_TEDataWebSite(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataWebSiteAr;
        }
        return TedataWebSiteEn;
    }

    public static String get_beInSportsMerchant(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsMerchantAr;
        }
        return beInSportsMerchantAr;
    }

    public static String get_beInSportsTransactionId(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsTransactionIdAr;
        }
        return beInSportsTransactionIdAr;
    }

    public static String get_beInSportsSubscriber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportssubscribeAr;
        }
        return beInSportssubscribeEn;
    }

    public static String get_beInSportsConfirm(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsconfirmAr;
        }
        return beInSportsconfirmEn;
    }

    public static String get_TEDataWalletNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataWalletNumberAr;
        }
        return TedataWalletNumberEn;
    }

    public static String get_TEDataTotalPrice(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataTotalAmountAr;
        }
        return TedataTotalAmountEn;
    }

    public static String get_beInSportsRequiredAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsRequiredAmountAr;
        }
        return beInSportsRequiredAmountEn;
    }

    public static String get_TEDataWeBonusCharge(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataWeBonusCharegAr;
        }
        return TedataWeBonusChargeEn;
    }

    public static String get_TEDataWeCharge(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataWeCharegAr;
        }
        return TedataWeChargeEn;
    }

    public static String get_TEDataCancel(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedatacancelAr;
        }
        return TedatacancelEn;
    }

    public static String get_TEDataPayment(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataPaymentAr;
        }
        return TedataPaymentEn;
    }

    public static String get_TEDataCategoryValue(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataCategoryValueAr;
        }
        return TedataCategoryValueEn;
    }

    public static String get_TEDataConfirmedData(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataConfirmedDataAr;
        }
        return TedataConfirmedDataEn;
    }

    public static String get_TEDataName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataNameAr;
        }
        return TedataNameEn;
    }

    public static String get_TedataPhoneNumberConfirmation(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataPhoneNumberConfirmationAr;
        }
        return TedataPhoneNumberConfirmationEn;
    }

    public static String get_TedataPhoneNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataPhoneNumberAr;
        }
        return TedataPhoneNumberEn;
    }

    public static String get_TedataChargingCategory(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataValueOfChargingCategoryAr;
        }
        return TedataValueOfChargingCategoryEn;
    }

    public static String get_Tedata(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TedataChargingCategoryAr;
        }
        return TedataChargingCategoryEn;
    }

    public static String get_beInSportsServiceCost(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsCosteAr;
        }
        return beInSportsCostEn;
    }

    public static String get_beInSportsMerchantCommission(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportscommissionAr;
        }
        return beInSportscommissionEn;
    }

    public static String get_beInSportsDeductedAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsDeductedAmountAr;
        }
        return beInSportsDeductedAmountEn;
    }

    public static String get_beInSportsTotalAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsTotalAmountAr;
        }
        return beInSportsRequiredAmountEn;
    }

    public static String get_beInSportsPackageDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsPackagedateAr;
        }
        return beInSportsPackagedateEn;
    }

    public static String get_beInSportsPackage(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsPackageAr;
        }
        return beInSportsPackageEn;
    }

    public static String get_beInSportsSubscriberName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsSubscriberNameAr;
        }
        return beInSportsSubscriberNameEn;
    }

    public static String get_beInSportsInfo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsInfoAr;
        }
        return beInSportsInfoEn;
    }

    public static String get_beInSportsInquiry(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsInquiryAr;
        }
        return beInSportsInquiryEn;
    }

    public static String get_beInSportsSubscribtion(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsSubscribtionAr;
        }
        return beInSportsSubscribtionEn;
    }

    public static String get_beInSports(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsAr;
        }
        return beInSportsEn;
    }

    public static String get_PhoneNumberBeinSports(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return beInSportsMobileAr;
        }
        return beInSportsMobileEn;
    }

/////////////////////////////////////////////////////Accept////////////////////////////////////////
    public static final String errorAcceptMobileNumAr = "برجاء التأكد من الرقم ثم اعادة المحاوله";
    public static final String errorAcceptMobileNumEn = "Please check the number and try again";
    public static final String ReferenceNumber = "الرقم المرجعى";
    public static final String ReferenceNumberEn = "reference number";
    public static final String acceptCustomerNameAr = "اسم العميل";
    public static final String acceptCustomerNameEn = "Customer name";
    public static final String acceptTransactionDataAr = "بيانات العملية";
    public static final String acceptTransactionDataEn = "Transaction Details";
    public static final String acceptTransactionTypeAr = "نوع العملية";
    public static final String acceptTransactionTypeEn = "Transaction type";
    public static final String acceptAmountAr = "قيمة العملية";
    public static final String acceptAmountEn = "Transaction value";
    public static final String acceptAppliedFeesAr = "تكلفة الخدمة شاملة ضريبة القيمة المضافة";
    public static final String acceptAppliedFeesEn = "Service Cost Including VAT";
    public static final String acceptTotalAmountAr = "اجمالى المبلغ المطلوب من العميل";
    public static final String acceptTotalAmountEn = "Total amount requested from customer";
    public static final String acceptMerchantNumberAr = "رقم التاجر";
    public static final String acceptMerchantNumberEn = "Merchant number";
    public static final String acceptTransactionDate = "تاريخ وتوقيت العملية";
    public static final String acceptDeductedAmountAr = "المبلغ المخصوم من محفظتك";
    public static final String acceptDeductedAmountEn = "The amount deducted from your wallet";
    public static final String acceptPaymentsAr = "مدفوعات اكسبت";
    public static final String acceptPaymentsEn = "Accept";
    public static final String PAGE_ACCEPT_Inquiry = "/service/Accept/AcceptHome.jsp";
    public static final String PAGE_ACCEPT_INFO = "/service/Accept/AcceptInquiry.jsp";
    public static final String PAGE_ACCEPT_PAYMENT = "/service/Accept/AcceptPaymentReceipt.jsp";
    public static final String PAGE_ACCEPT_REPRINT = "/service/Accept/AcceptReprint.jsp";
    public static final String AcceptSWVLAr = "سويفل";
    public static final String AcceptFlyinAr = "فلاى إن";
    public static final String AcceptBuseetAr = "باصيت";
    public static final String AcceptE7gezlyAr = "احجزلى";
    public static final String AcceptNileKayakClubAr = "نادى نايل كاياك";
    public static final String AcceptSWVLEn = "SWVL";
    public static final String AcceptFlyinEn = "Flyin";
    public static final String AcceptBuseetEn = "Buseet";
    public static final String AcceptE7gezlyEn = "E7gezly";
    public static final String AcceptNileKayakClubEn = "Nile Kayak Club";
    public static final String AcceptCancelAr = "إلغاء";
    public static final String AcceptCancelEn = "Cancel";
    public static final String AcceptTotalPaidAmountAr = "اجمالى المبلغ المدفوع";
    public static final String AcceptTotalPaidAmountEn = "Total paid amount";
    public static final String errorAcceptAr = "هذا الرقم غير صحيح، برجاء التأكد من الرقم وإعادة المحاولة مرة أخرى";
    public static final String errorAcceptEn = "Ref is not proper";
    public static final String errorAcceptE1Ar = "خطأ في تنفيذ العملية، برجاء المتابعة مع مقدم الخدمة(E1)";
    public static final String errorAcceptE1En = "Fees amount is not provided";
    public static final String errorAcceptE2Ar = "خطأ في تنفيذ العملية، برجاء المتابعة مع مقدم الخدمة(E2)";
    public static final String errorAcceptE2En = "Installment amount is not found";
    public static final String errorAcceptE3Ar = "خطأ في تنفيذ العملية، برجاء المتابعة مع مقدم الخدمة(E3)";
    public static final String errorAcceptE3En = "Bill is paid successfully but biller is not notified";
    public static final String errorAcceptEAr = "لا توجد فواتير لهذا الرقم";
    public static final String errorAcceptEEn = "Bill had paid before  ";
    public static final String errorAcceptE4Ar = "خطأ في تنفيذ العملية، برجاء المتابعة مع مقدم الخدمة(E4)";
    public static final String errorAcceptE4En = "Problem occurred while processing ,contact our technical support";
    public static final String errorAcceptE5Ar = "خطأ في تنفيذ العملية، برجاء المتابعة مع مقدم الخدمة(E5)";
    public static final String errorAcceptE5En = "Not permitted to pay early repayment";
    public static final String errorAcceptE6Ar = "خطأ في تنفيذ العملية، برجاء المتابعة مع مقدم الخدمة(E6)";
    public static final String errorAcceptE6En = "Not permitted to pay the whole loan amount";
    public static final String errorAcceptE7Ar = "خطأ في تنفيذ العملية، برجاء المتابعة مع مقدم الخدمة(E7)";
    public static final String errorAcceptE7En = "Not permitted to pay fines alone";
    public static final String errorAcceptE8Ar = "خطأ في تنفيذ العملية، برجاء المتابعة مع مقدم الخدمة(E8)";
    public static final String errorAcceptE8En = "Not permitted to pay fines partially";
    public static final String errorAcceptE9Ar = "خطأ في تنفيذ العملية، برجاء المتابعة مع مقدم الخدمة(E9)";
    public static final String errorAcceptE9En = "Not permitted to pay partially";
    public static final String errorAcceptE10Ar = "خطأ في تنفيذ العملية، برجاء المتابعة مع مقدم الخدمة(E10)";
    public static final String errorAcceptE10En = "Not permitted to pay more than the required amount";
    /////////////////////////////////////changePoints////////////////////////////////////////////////////////////////
    public static final String errorChangePointsE1Ar = "عدد النقاط المتاحة للاستبدال اقل من 100 نقطة";
    public static final String errorChangePointsE1EN = "عدد النقاط المتاحة للاستبدال اقل من 100 نقطة";
    public static final String errorChangePointsE2Ar = "برجاء العلم انه قد تم تقديم الطلب خلال هذه الفتره";
    public static final String errorChangePointsE2EN = " برجاء العلم انه قد تم تقديم الطلب خلال هذه الفتره";
    public static final String errorChangePointsE3Ar = "يرجي العلم بان استبدال النقاط يتم في اول عشره ايام فقط من الاشهر الزوجيه";
    public static final String errorChangePointsE3EN = "يرجي العلم بان استبدال النقاط يتم في اول عشره ايام فقط من الاشهر الزوجيه";

///////////////////////////////////////////FastLink/////////////////////////////////////
    public static final String PAGE_FastLink_Home = "/service/FastLink/FastLinkInquiry.jsp";
    public static final String PAGE_FastLink_Info = "/service/FastLink/FastLinkInfo.jsp";
    public static final String PAGE_FastLink_Payment = "/service/FastLink/FastLinkPaymentConfirmation.jsp";
    public static final String PAGE_FastLink_Reprint = "/service/FastLink/FastLinkReprint.jsp";

    public static String get_AcceptTotalPaidAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AcceptTotalPaidAmountAr;
        }
        return AcceptTotalPaidAmountEn;
    }
    public static final String FastLinkNameAR = "فاست لينك";
    public static final String FastLinkNameEn = "Fast Link";
    public static final String FastLinkInquiryAR = "الاستعلام عن فاتوره فاست لينك";
    public static final String FastLinkInquiryEn = "Inquiry about the bill of Fastlink";
    public static final String FastLinkMobileInquiryAr = "ادخل رقم الموبايل/ التليفون الارضي :";
    public static final String FastLinkMobileInquiryEn = "Enter the mobile/ phone number";
    public static final String FastLinkAmountAR = "القيمة";
    public static final String FastLinkAmountEn = "The Amount";
    public static final String FastLinkexpirationDateAr = "تاريخ إنتهاء الفاتوره";
    public static final String FastLinkexpirationDateEn = "Date of expiration of bill";
    public static final String FastLinkBillAr = "فاتورة فاست لينك";
    public static final String FastLinkBillEn = "Fast Link bill";
    public static final String FastLinkBillAmountAr = "قيمة الفاتوره";
    public static final String FastLinkBillAmountEn = "Bill Amount";
    public static final String FastLinkPrintCloseAr = "إغلاق وطباعه";
    public static final String FastLinkPrintCloseEn = "Close and print";
    public static final String FastLinkAplliedFeesAr = "تكلفة الخدمة شاملة ضريبة القيمة المضافة";
    public static final String FastLinkAplliedFeesEn = "Cost of service including VAT";
    public static final String FastLinkDateAr = "تاريخ وتوقيت العملية";
    public static final String FastLinkAmountAr = "المبلغ بالجنيه";
    public static final String FastLinkAmountEN = "The Amount";
    public static final String FastLinkTotalAmountAr = "المبلغ الاجمالي";
    public static final String FastLinkTotalAmountEn = "The total amount";
    public static final String errorFastLinkTransacionAr = "خطا في تنفيذ العملية برجاء المحاولة مرة اخري ";
    public static final String errorFastLinkTransacionEn = "Error executing operation Please try again";
    public static final String errorFastLinkNoBillsFoundAr = "لا توجد فواتير مستحقة";
    public static final String errorFastLinkNoBillsFoundEn = "No bills due";

    public static String get_FastLinkTotalAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkTotalAmountAr;
        }
        return FastLinkTotalAmountEn;
    }

    public static String get_FastLinkAmountAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkAmountAr;
        }
        return FastLinkAmountEN;
    }

    public static String get_FastLinkDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkDateAr;
        }
        return FastLinkDateAr;
    }

    public static String get_FastLinkAplliedFees(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkAplliedFeesAr;
        }
        return FastLinkAplliedFeesEn;
    }

    public static String get_FastLinkPrintClose(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkPrintCloseAr;
        }
        return FastLinkPrintCloseEn;
    }

    public static String get_FastLinkBillAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkBillAmountAr;
        }
        return FastLinkBillAmountEn;
    }

    public static String get_FastLinkBill(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkBillAr;
        }
        return FastLinkBillEn;
    }

    public static String FastLinkexpirationDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkexpirationDateAr;
        }
        return FastLinkexpirationDateEn;
    }

    public static String get_FastLinkAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkAmountAR;
        }
        return FastLinkAmountEn;
    }

    public static String get_FastLinkMobileInquiry(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkMobileInquiryAr;
        }
        return FastLinkMobileInquiryEn;
    }

    public static String get_FastLinkInquiryAR(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkInquiryAR;
        }
        return FastLinkInquiryEn;
    }

    public static String get_FastLinkInquiryEn(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkInquiryEn;
        }
        return FastLinkInquiryEn;
    }

    public static String get_FastLinkNameAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkNameAR;
        }
        return FastLinkNameEn;
    }

    public static String get_FastLinkNameEn(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return FastLinkNameEn;
        }
        return FastLinkNameEn;
    }

    public static String get_AcceptCancel(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AcceptCancelAr;
        }
        return AcceptCancelEn;
    }

    public static String get_AcceptSWVL(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AcceptSWVLAr;
        }
        return AcceptSWVLEn;
    }

    public static String get_AcceptFlyin(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AcceptFlyinAr;
        }
        return AcceptFlyinEn;
    }

    public static String get_AcceptBuseet(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AcceptBuseetAr;
        }
        return AcceptBuseetEn;
    }

    public static String get_AcceptE7gezlyAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AcceptE7gezlyAr;
        }
        return AcceptE7gezlyEn;
    }

    public static String get_AcceptNileKayakClub(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return AcceptNileKayakClubAr;
        }
        return AcceptNileKayakClubEn;
    }

    public static String get_ReferenceNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return ReferenceNumber;
        }
        return ReferenceNumberEn;
    }

    public static String get_acceptDeductedAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return acceptDeductedAmountAr;
        }
        return acceptDeductedAmountEn;
    }

    public static String get_acceptPayments(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return acceptPaymentsAr;
        }
        return acceptPaymentsEn;
    }

    public static String get_acceptMerchantNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return acceptMerchantNumberAr;
        }
        return acceptMerchantNumberEn;
    }

    public static String get_acceptTransactionData(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return acceptTransactionDataAr;
        }
        return acceptTransactionDataEn;
    }

    public static String getErrorAcceptMobileNum(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return errorAcceptMobileNumAr;
        }
        return errorAcceptMobileNumEn;
    }

    public static String get_acceptCustomerName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return acceptCustomerNameAr;
        }
        return acceptCustomerNameEn;
    }

    public static String get_acceptTransactionType(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return acceptTransactionTypeAr;
        }
        return acceptTransactionTypeEn;
    }

    public static String get_acceptAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return acceptAmountAr;
        }
        return acceptAmountEn;
    }

    public static String get_acceptAppliedFees(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return acceptAppliedFeesAr;
        }
        return acceptAppliedFeesEn;
    }

    public static String get_acceptTotalAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return acceptTotalAmountAr;
        }
        return acceptTotalAmountEn;
    }

    //////////////Change Points/////////
    public static final String changePointsNameAR = "استبدال النقاط";
    public static final String changePointsNameEN = "Change Points";
    public static final String changePointsStringAR = "عدد النقاط المطلوب استبدالها";
    public static final String changePointsStringEN = "Number of points you want to Change";
    public static final String changePointsButtonValueAR = "استبدال";
    public static final String changePointsButtonValueEN = "Change";
    public static final String changePointsAllowedPoints = "عدد النقاط المسموح باستبدالها";
    public static final String changePointsAllowedPointsEn = "Number of allowed points";
    public static final String LoyaltyPoints_Home_Page = "/service/ChangePoints/ChangePoints.jsp";
    //  public static final String LoyaltyPointsCancelButton="";
    public static final String LoyaltyPoints_Confirmation_Page = "/service/ChangePoints/LoyaltyPointsConfirmation.jsp";

    public static final String LoyaltyPoints_Save_Page = "/service/ChangePoints/LoyaltyPointsSave.jsp";
    public static final String changePointStatementAR = "في حال طلب استبدال اكثر من 1,000 نقطة";
    public static final String changePointStatement2AR = "برجاء الاتصال بخدمة عملاء مصارى 16994";

    public static String get_changePointsAllowedPoints(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return changePointsAllowedPoints;
        }
        return changePointsAllowedPointsEn;
    }

    public static String get_changePointsName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return changePointsNameAR;
        }
        return changePointsNameEN;
    }

    public static String get_changePointsString(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return changePointsStringAR;
        }
        return changePointsStringEN;
    }

    public static String get_changePointsButtonValue(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return changePointsButtonValueAR;
        }
        return changePointsButtonValueEN;
    }
    /**
     * ************************** Tamweel (efgHermes)
     * Service*********************************
     */
    public static final String ACTION_TAMWEEL_INQUIRY = "ACTION_TAMWEEL_INQUIRY";
    public static final String ACTION_TAMWEEL_GETINFO = "ACTION_TAMWEEL_GETINFO";
    public static final String ACTION_TAMWEEL_Payment = "ACTION_TAMWEEL_Payment";

    public static final String TamweelDataEntry = "أستعلام عن عميل شركة  تمويل";
    public static final String Tamweel = " شركة تمويل للتمويل العقاري";
    public static final String TamweelCodeNumberAR = " كود العميل";
    public static final String TamweelCodeNumberEN = "Loan Account Number";
    public static final String TamweelInstallmentTypeAr = "نوع القسط";
    public static final String TamweelInstallmentTypeEN = "Installment Type";
    public static final String TamweelCustomerNameAr = "اسم العميل";
    public static final String TamweelCustomerNameEN = "Customer Name";
    public static final String TamweelInstallmentDateEn = "Installment Date";
    public static final String TamweelInstallmentDateAR = " تاريخ القسط";
    public static final String TamweelTotalInstallmentAmountEn = "Total Installment Amount";
    public static final String TamweelTotalInstallmentAmountAR = "مبلغ القسط";
    public static final String TamweelLateChargeEn = "Total Installment Amount";
    public static final String TamweelLateChargeAr = "غرامة التاخير ";

    public static final String TamweelAmountEn = "  ";
    public static final String TamweelAmountAR = "مبلغ القسط";
    public static final String TamweelPartialpaymentEn = "Partial payment";
    public static final String TamweelPartialpaymentAR = " دفع جزئي";

    public static final String TamweelTotalAmountEn = "Partial payment";
    public static final String TamweelTotalAmountAr = "دفع كلي";

    public static final String TamweelDataInfoAr = "بيانات العميل";
    private final static String TamweelDataInfoEn = "Customer Information";

    public static final String PAGE_TAMWEEL_Inquiry = "/service/Tamweel/TamweelInquiry.jsp";
    public static final String PAGE_TAMWEEL_INFO = "/service/Tamweel/ValuInfo.jsp";
    public static final String PAGE_TAMWEEL_PAYMENT = "/service/Tamweel/ValuPaymentConfirmation.jsp";
    public static final String PAGE_TAMWEEL_CONFIRMATION2 = "/service/Tamweel/ValuConfirmation.jsp";
    public static final String errorTAMWEELCODENumAr = "برجاء التأكد من صحة رقم  العميل ";
    public static final String errorTAMWEELCODENumEn = "Invalid number; must be ten digits";

    public static String getTamweelDataInfo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TamweelDataInfoAr;
        }
        return TamweelDataInfoEn;
    }

    public static String getErrorTamweelCodeNumAr(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return errorTAMWEELCODENumAr;
        }
        return errorTAMWEELCODENumEn;
    }

    public static String getTamweelCodeNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TamweelCodeNumberAR;
        }
        return TamweelCodeNumberEN;
    }

    public static String getTamweelInstallmentType(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TamweelInstallmentTypeAr;
        }
        return TamweelInstallmentTypeEN;
    }

    public static String getTamweelCustomerName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
        }
        return TamweelCustomerNameEN;
    }

    public static String getTamweelInstallmentDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TamweelInstallmentDateAR;
        }
        return TamweelInstallmentDateEn;
    }

    public static String getTamweelTotalInstallmentAmountAR(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TamweelTotalInstallmentAmountAR;
        }
        return TamweelTotalInstallmentAmountEn;
    }

    public static String getTamweelLateCharge(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TamweelLateChargeAr;
        }
        return TamweelLateChargeEn;
    }

    public static String getTamweelPartialpaymentAR(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TamweelPartialpaymentAR;
        }
        return TamweelPartialpaymentEn;
    }

    public static String getTamweelTotalAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return TamweelTotalAmountAr;
        }
        return TamweelTotalAmountEn;
    }
}
