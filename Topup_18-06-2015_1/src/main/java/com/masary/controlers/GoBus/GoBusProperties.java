package com.masary.controlers.GoBus;

import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;

public class GoBusProperties {

	
    public static final String goBusHomePage = "/service/GoBus/goBusHome.jsp";
    public static final String goBusConfirmationPage = "/service/GoBus/goBusConfirmation.jsp";
    public static final String goBusPaymentPage = "/service/GoBus/goBusPayment.jsp";
    public static final String goBusReprintPage = "/service/GoBus/goBusReprint.jsp";
    
    
    private static final String referanceNumberAr = "إدخل رقم المرجع";
    private static final String referanceNumberEn = "Referance Number";
    
    
    private static final String enquiryAr = "إستعلام";
    private static final String enquiryEn = "Enquiry";
    
    
    public static final String cancelAr = "إلغاء";
    public static final String cancelEn = "Cancel";
    
    
    public static final String goBusAr = "جو-باص";
    public static final String goBusEn = "Go-Bus";
    
    
    private static final String tripKindAr = "نوع الرحلة";
    private static final String tripKindEn = "Trip Kind";
    
    private static final String tripDateAr = "تاريخ الرحلة";
    private static final String tripDateEn = "Trip Date";
    
    
    private static final String tripDEtailsAr = "تفاصيل الرحلة";
    private static final String tripDEtailsEn = "Trip Date";
    
    
    private static final String seatNumAr = "رقم المقعد/المقاعد";
    private static final String seatNumEn = "Seat Num";
    
    
    private static final String clientNameAr = "اسم العميل";
    private static final String clientNameEn = "Client Name";
    
    
    
    private static final String reservationAmountAr = "مبلغ الحجز";
    private static final String reservationAmountEn = "Reservation Amount";
    
    
    private static final String reservationAmountAllAr = "إجمالي المبلغ شامل الضريبة";
    private static final String reservationAmountAllEn = "Transaction Amount";
    
    
    
    private static final String printAndCloseAr = "إغلاق و طباعة";
    private static final String printAndCloseEn = "Print and Close";
    
    
    private static final String goTripAr = "ذهـــــاب";
    private static final String goTripEn = "Go";
    
    private static final String backTripAr = "عودة";
    private static final String backTripEn = "Back";
    
    private static final String requiredInfoAr = "البيانات المطلوبة";
    private static final String requiredInfoEn = "Requried Info";
           
    
    public static final String errorCode6401Ar = "يرجى العلم ان هذه الخدمة غير متاحة الآن، برجاء إعادة المحاولة في وقت لاحق ";
    public static final String errorCode6401En = "Service Inactive Try Again Later";
    
    
    public static final String errorCode6402Ar = "خطأ اثناء الاتصال بمقدم الخدمة، برجاء إعادة المحاولة في وقت لاحق";
    public static final String errorCode6402En = "Error During Calling Service Provider";
    
    
    public static final String errorCode6403Ar = "حدث خطأ أثناء تنفيذ العملية رجاء الإتصال بمقدم الخدمة رجاء الإتصال بجو باص";
    public static final String errorCode6403En = "Error During Calling Service Provider Please Call Go-Bus";
    
    
    public static final String errorCode910Ar = "برجاء الاستعلام مرة أخرى";
    public static final String errorCode910En = "Please Make Inquery Again";
    

    public static String getRequiredInfo(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return requiredInfoAr;
        }
        return requiredInfoEn;
    }
    
    public static String getPrintAndClose(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return printAndCloseAr;
        }
        return printAndCloseEn;
    }
    
    
    
    public static String getReservationAmountAll(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return reservationAmountAllAr;
        }
        return reservationAmountAllEn;
    }
    
    
    public static String getReservationAmount(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return reservationAmountAr;
        }
        return reservationAmountEn;
    }
    
    
    public static String getClientName(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return clientNameAr;
        }
        return clientNameEn;
    }
    
    
    
    
    public static String getGoTrip(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return goTripAr;
        }
        return goTripEn;
    }
    
    public static String getBackTrip(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return backTripAr;
        }
        return backTripEn;
    }
    
    public static String getSeatNum(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return seatNumAr;
        }
        return seatNumEn;
    }
    
    
    
    public static String getTripDEtails(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return tripDEtailsAr;
        }
        return tripDEtailsEn;
    }
    
    
    public static String getTripDate(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return tripDateAr;
        }
        return tripDateEn;
    }
    
    
    public static String getTripKind(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return tripKindAr;
        }
        return tripKindEn;
    }
    
    
    public static String getReferanceNumber(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return referanceNumberAr;
        }
        return referanceNumberEn;
    }
    
    
    public static String getEnquiry(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return enquiryAr;
        }
        return enquiryEn;
    }

    
    public static String getCancel(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return cancelAr;
        }
        return cancelEn;
    }
    
    
    public static String getGoBus(HttpSession session) {
        if (session == null || session.getAttribute(CONFIG.lang) == null || session.getAttribute(CONFIG.lang).equals("ar")) {
            return goBusAr;
        }
        return goBusEn;
    }
    
    
    
}
