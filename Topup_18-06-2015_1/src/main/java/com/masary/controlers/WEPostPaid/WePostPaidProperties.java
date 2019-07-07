/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.WEPostPaid;

/**
 *
 * @author user
 */
public class WePostPaidProperties {

    public static final String PAGE_WePostPaid_HOME = "/service/WePostPaid/HomePage.jsp";
    public static final String PAGE_WePostPaid_INFO = "/service/WePostPaid/InfoPage.jsp";
    public static final String PAGE_WePostPaid_PAYMENT = "/service/WePostPaid/PaymentPage.jsp";
    public static final String Page_WePostPaid_Reprint="/service/WePostPaid/RePrintPage.jsp";
    public static final String WePostPaid_SERVICE_NAME = "فواتير موبايل المصرية للاتصالات";
    public static final String WePostPaid_Inquiry = "الاستعلام عن فاتورة موبايل المصرية للاتصالات";
    public static final String WePostPaid_Inquiry2 = "تأكيد دفع فاتورة موبايل المصرية للاتصالات";
    public static final String WePostPaid_Execute = "تنفيذ";
    public static final String WePostPaid_Confirmation_Label = "بيانات العملية";
    public static final String WePostPaid_Mobile_Number = "رقم المحمول";
    public static final String WePostPaid_Bill_Amount = "القيمة";
    public static final String WePostPaidTrxStatus = "عمليه ناجحه";

    public static final String WePostPaid_Cancel = "إلغاء";
    public static final String MESSAGE_WePostPaid_ERRORAr = "خطأ اثناء تنفيذ العملية";
    public static final String MESSAGE_WePostPaid_ERROREn = "Error while doing transaction";
    public static final String errorWepostPaidConnectionRefusedAr = "الخدمة غير متاحة برجاء المحاولة لاحقا";
    public static final String errorWepostPaidConnectionRefusedEn = "Service Unavailable please try again later";
    public static final String errorTransactionWepostPaidar = "خطأ فى العمليه من مزود الخدمه";
    public static final String errorBalanceTransactionar = "حدث خطأ تقني برجاء المحاولة في وقت لاحق";
    public static final String errorBalanceTransaction = "A technical error occurred please try again later";
    public static final String errorAmountTransactionar = "المبلغ المدخل اقل من اجمالي المديونية، برجاء إدخال القيمة الكلية للمديونية";
    public static final String errorAmountTransaction = "Amount entered less than total indebtedness, please enter the total value of indebtedness";
    public static final String errorMobileTransactionar = "الرقم الذي قمت بإدخاله غير صحيح، برجاء التأكد من الرقم وإعادة المحاولة مرة أخرى";
    public static final String errorMobileTransaction = "The number you entered is incorrect, please confirm the number and try again";
    public static final String errorWeTransactionar = "تعذر الإتصال بمقدم الخدمة WE في الوقت الحالي، يرجى إعادة المحاولة في وقت لاحق";
    public static final String errorWeTransaction = "Could not contact service provider WE at the moment, please try again later";
    public static final String errorBadMobileTransactionar = " برجاء التأكد من الرقم وإعادة المحاولة";

    public static final String errorBadMobileTransaction = "Please confirm the number and try again";

    public static final String errorNoBillsFoundar = "لا توجد فواتير مستحقة لهذا الرقم";
    public static final String errorNoBillsFound = "There are no invoices due for this number";
    public static final String errorTransactionar = "حدث خطأ تقني من جانب شركة WE ، برجاء المحاولة في وقت لاحق ";

    public static final String errorTransaction = "A technical error has occurred by WE, please try again later";
    public static final String errorPaymentar ="غير مسموح بالدفع لنفس الرقم خلال 24 ساعه";
    public static final String errorPayment ="The same number is not allowed within 24 hours";


    

    public static final String DuplicatedTransactionAr = "خطأ اثناء تنفيذ العملية , عملية دفع مكررة";
    public static final String DuplicatedTransactionEn = "Error during execution transaction , Duplicated Transaction";





    public static final String MESSAGE_WePostPaid_ContractDoesnotExist6661Ar = "هذا الرقم غير متواجد بالخدمة";
    public static final String MESSAGE_WePostPaid_ContractDoesnotExist6661En = "Contract does not exist";
    public static final String MESSAGE_WePostPaid_DuplicatePayment6662Ar = "خطأ :- عملية دفع مكررة";
    public static final String MESSAGE_WePostPaid_DuplicatePayment6662En = "Error :- Duplicated payment";
    public static final String MESSAGE_WePostPaid_InsufficientBalance6663Ar = "لا يوجد رصيد كافى";
    public static final String MESSAGE_WePostPaid_InsufficientBalance6663En = "Insufficient balance";
    public static final String MESSAGE_WePostPaid_IntegrationException6664Ar = "خطأ اثناء الاتصال بالخدمة";
    public static final String MESSAGE_WePostPaid_IntegrationException6664En = "Integration exception";
    public static final String MESSAGE_WePostPaid_NonPaymentResponsibleAccount6665Ar = "لا يمكن الدفع لهذا الحساب";
    public static final String MESSAGE_WePostPaid_NonPaymentResponsibleAccount6665En = "No Payment responsible for this account";
    public static final String MESSAGE_WePostPaid_TechnicalError6666Ar = "خطأ تقنى من الخدمة";
    public static final String MESSAGE_WePostPaid_TechnicalError6666En = "Technical error";
    public static final String MESSAGE_WePostPaid_AccountIsnotCorporete6667Ar = "هذا الرقم ليس تابع لاورنج شركات";
    public static final String MESSAGE_WePostPaid_AccountIsnotCorporete6667En = "Account is not a corporete";
    public static final String MESSAGE_WePostPaid_Timeout6668Ar = "نفذ وقت الاتصال بالخدمة";
    public static final String MESSAGE_WePostPaid_Timeout6668En = "Timeout during connection";
    public static final String MESSAGE_WePostPaid_StatusCodeIsNotInTheMap6669Ar = "لا يوجد كود هذا الخطا";
    public static final String MESSAGE_WePostPaid_StatusCodeIsNotInTheMap6669En = "Status code is not in the map";
    public static final String MESSAGE_WePostPaid_PaidAmountOutOfTotalAmountRangeAr = "برجاء العلم ان القيمة المسددة اعلى من قيمة اجمالى الفاتورة";
    public static final String MESSAGE_WePostPaid_PaidAmountOutOfTotalAmountRangeEn = "Paid amount is greater than total bill amount";
    public static final String MESSAGE_WePostPaid_InvalidPaidAmountAr = "القيمة المسددة يجب ان تكون اعلى من 0 وليست بالسالب";
    public static final String MESSAGE_WePostPaid_InvalidPaidAmountEn = "Paid amount is invalid";
    public static final String MESSAGE_WePostPaid_CONNECTONERRORAr = "خطأ اثناء الاتصال بالشبكة . الرجاء المحاولة لاحقاً";
    public static final String MESSAGE_WePostPaid_CONNECTONERROREn = "Connection refused . try again later";
    

}
