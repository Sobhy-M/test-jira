// Declaring required variables
var digits = "01023456789";
// non-digit characters which are allowed in phone numbers
var phoneNumberDelimiters = "()- ";
// characters which are allowed in international phone numbers
// (a leading + is OK)
var validWorldPhoneChars = phoneNumberDelimiters + "+";
// Minimum no of digits in an international phone no.
var minDigitsInIPhoneNumber = 11;

function isInteger(s)
{
    var i;
    for (i = 0; i < s.length; i++)
    {
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9")))
            return false;
    }
    // All characters are numbers.
    return true;
}
function trim(s)
{
    var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not a whitespace, append to returnString.
    for (i = 0; i < s.length; i++)
    {
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (c != " ")
            returnString += c;
    }
    return returnString;
}
function stripCharsInBag(s, bag)
{
    var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++)
    {
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1)
            returnString += c;
    }
    return returnString;
}

function checkInternationalPhone(strPhone) {
    var bracket = 3
    strPhone = trim(strPhone)
    if (strPhone.indexOf("+") > 1)
        return false
    if (strPhone.indexOf("-") != -1)
        bracket = bracket + 1
    if (strPhone.indexOf("(") != -1 && strPhone.indexOf("(") > bracket)
        return false
    var brchr = strPhone.indexOf("(")
    if (strPhone.indexOf("(") != -1 && strPhone.charAt(brchr + 2) != ")")
        return false
    if (strPhone.indexOf("(") == -1 && strPhone.indexOf(")") != -1)
        return false
    s = stripCharsInBag(strPhone, validWorldPhoneChars);
    return (isInteger(s) && s.length >= minDigitsInIPhoneNumber);
}
function ValidateAddCustForm2() {
    var errChk = 0;
    var answerDiv = document.getElementById("AnswerDiv");
    var answer = document.AddCustomer.ANSWER;
    //     ------------------------- The Answer Validation ---------------------------

    if (answer == null || answer.value == "") {
//        answerDiv.innerHTML = "<br/> Please Enter A Valid Answer.<br/>";
        answerDiv.innerHTML = "<br/>من فضلك ادخل اجابة صحيحة<br/>";
        errChk = 1;
    }

    if (answer.value.length > 100) {
//        answerDiv.innerHTML = "<br/> The Answer Can't be more Than 100 Characters. <br/>";
        answerDiv.innerHTML = "<br/>الاجابة لا تزيد عن 100 حرف <br/>";
        errChk = 1;
    }
    if (errChk == 1) {
        return false;
    }
    return true;
}

function ValidateAddCustForm() {
    var errChk = 0;
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

    //    get the Div

    var nameDiv = document.getElementById("AdCustNameDiv");
    var nameArabicDiv = document.getElementById("AdCustArabicNameDiv");
    var phoneDiv = document.getElementById("AdCustPhoneDiv");
    var alPhoneDiv = document.getElementById("AdCustPhone2Div");
    var landLinePhoneDiv = document.getElementById("AdCustLandNoDiv");
    var goverDiv = document.getElementById("GoverDiv");
//    var answerDiv = document.getElementById("AnswerDiv");
    var address1Div = document.getElementById("AdAddress1Div");
    var address2Div = document.getElementById("AdAddress2Div");
    var emailDiv = document.getElementById("AdCustEmailDiv");
    var cEmailDiv = document.getElementById("AdCEmailDiv");
    var aEmailDiv = document.getElementById("AdCustEmail2Div");
    var cAltEmailDiv = document.getElementById("AdCEmail2Div");
    var nationalIDDiv = document.getElementById("AdCustNationalIDDiv");
    var birthDateDiv = document.getElementById("AdCustBirthDateDiv");
    var postalCodeDiv = document.getElementById("AdPostalCodeDiv");
    //    set the div with null
    nameDiv.innerHTML = "";
    phoneDiv.innerHTML = "";
    //    get  value
    var name = document.AddCustomer.USERNAME;
    var arabic_name = document.AddCustomer.USERNAME_ARABIC;
    var Phone = document.AddCustomer.MSISDN;
    var alPhone = document.AddCustomer.MSISDN_ALTERNATIVE;
    var landLinePhone = document.AddCustomer.LAND_LINE_PHONE;
    var gover = document.AddCustomer.GOVERNORTE;
//    var answer = document.AddCustomer.ANSWER;
    var address1 = document.AddCustomer.ADDRESS1;
    var address2 = document.AddCustomer.ADDRESS2;
    var emailAddress = document.AddCustomer.EMAIL_ADDRESS;
    var confirmEmailAddress = document.AddCustomer.CONFIRM_EMAIL_ADDRESS;
    var altEmailAddress = document.AddCustomer.EMAIL_ADDRESS_ALTERNATIVE;
    var confirmAEmailAddress = document.AddCustomer.CONFIRM_EMAIL_ADDRESS_ALTERNATIVE;
    var nationalID = document.AddCustomer.NATIONAL_ID;
    var birthDate = document.AddCustomer.BIRTH_DATE;
    var postalCode = document.AddCustomer.POSTAL_CODE;

    //     ------------------------- The Name Validation ---------------------------
    //    validate the username is not null
    if (name.value == null || name.value == "") {
//        nameDiv.innerHTML = "<br/> Please Enter A Valid UserName.<br/>";
        nameDiv.innerHTML = "<br/>من فضلك ادخل اسم صحيح<br/>";
        errChk = 1;
    }
    //    validate the username not less than 5 charachters
    if (name.length < 5) {
//        nameDiv.innerHTML = nameDiv.innerHTML + "The UserName Can't be Less Than 5 Characters<br/>";
        nameDiv.innerHTML = nameDiv.innerHTML + "اسم المستخدم لا يقل عن 5 احرف<br/>";
        errChk = 1;
    }
    //     ------------------------- The Arabic Name Validation ---------------------------
    //    validate the username is not null
    if (arabic_name == null || arabic_name.value == "") {
//        nameArabicDiv.innerHTML = "<br/> Please Enter A Valid Arabic UserName.<br/>";
        nameArabicDiv.innerHTML = "<br/>من فضلك ادخل اسم (عربى )صحيح<br/>";
        errChk = 1;
    }
    //    validate the username not less than 5 charachters
    if (arabic_name.length < 5) {
//        nameArabicDiv.innerHTML = nameArabicDiv.innerHTML + "The Arabic UserName Can't be Less Than 5 Characters<br/>";
        nameArabicDiv.innerHTML = nameArabicDiv.innerHTML + "اسم المستخدم لا يقل عن 5 احرف<br/>";
        errChk = 1;
    }

    // -----------------------Validate Address not null && not more than 60---------------------------------
    if (address1 == null || address1.value == "") {
//        address1Div.innerHTML = "<br/> Please Enter A Valid Address <br/>";
        address1Div.innerHTML = "<br/> من فضلك ادخل عنوان صحيح<br/>";
        errChk = 1;
    }
    if (address1.value.length > 60) {
//        address1Div.innerHTML = address1Div.innerHTML + " The Address Can't be more Than 60 Characters You Can Complete in Address2 <br/>";
        address1Div.innerHTML = address1Div.innerHTML + "العنوان لا يزيد عن 60 حرف بإمكانك الاكمال فى الخانة التالية<br/>";
        errChk = 1;
    }
    if (address2 !== null || address2.value !== "") {
        if (address2.value.length > 60) {
//            address2Div.innerHTML = address2Div.innerHTML + " The Address Can't be more Than 60 Characters <br/>";
            address2Div.innerHTML = address2Div.innerHTML + "العنوان لا يزيد عن 60 حرف<br/>";
            errChk = 1;
        }
    }
    //    ----------------------------------------------------------------------
    if (gover == null || gover.value == "") {
//        goverDiv.innerHTML = "<br/> Please Select one of Governorate <br/>";
        goverDiv.innerHTML = "<br/>من فضلك اختر محافظتك<br/>";
        errChk = 1;
    }
    // -----------------------Validate Email Address not null ---------------------------------
    if (emailAddress == null || emailAddress.value == "") {
//        emailDiv.innerHTML = "<br/> Please Enter A Valid Email Address <br/>";
        emailDiv.innerHTML = "<br/>من فضلك ادخل بريد إلكترونى صحيح<br/>";
        errChk = 1;
    }

    if (reg.test(emailAddress.value) == false) {
//        emailDiv.innerHTML = "<br/> Please Enter A Valid Email Address <br/>";
        emailDiv.innerHTML = "<br/>من فضلك ادخل بريد إلكترونى صحيح<br/>";
        errChk = 1;
    }
    if (confirmEmailAddress == null || confirmEmailAddress.value == "") {
//        cEmailDiv.innerHTML = "<br/> Please Enter A Valid Email Address <br/>";
        cEmailDiv.innerHTML = "<br/>من فضلك ادخل بريد إلكترونى صحيح<br/>";
        errChk = 1;
    }
    if (confirmEmailAddress.value !== emailAddress.value) {
//        cEmailDiv.innerHTML = "<br/> The email you entered do not match. Please try again. <br/>";
        cEmailDiv.innerHTML = "<br/>البريد الإلكترونى غير متطابق من فضلك ادخله مرة اخرى<br/>";
        errChk = 1;
    }
    //    if(altEmailAddress !== null || altEmailAddress.value !== "" ){
    //        if(confirmAEmailAddress == null || confirmAEmailAddress.value == "" ){
    //            cAltEmailDiv.innerHTML = "<br/> Please Enter A Valid Email Address <br/>";
    //            errChk = 1;
    //        }
    //    if(confirmAEmailAddress.value !==  altEmailAddress.value ){
    //        cAltEmailDiv.innerHTML = "<br/> The email you entered do not match. Please try again. <br/>";
    //        errChk = 1;
    //    }
    //    }

    //    ----------------------------------------------------------------------
    // -----------------------Validate National ID not null---------------------------------
    if (nationalID == null || nationalID.value == "") {
//        nationalIDDiv.innerHTML = "<br/> Please Enter A Valid National ID <br/>";
        nationalIDDiv.innerHTML = "<br/>من فضلك ادخل رقم قومى صحيح <br/>";
        errChk = 1;
    }
    if (nationalID.value.length < 14) {
//        nationalIDDiv.innerHTML = "<br/> Please Enter A Valid National ID <br/>";
        nationalIDDiv.innerHTML = "<br/>من فضلك ادخل رقم قومى صحيح <br/>";
        errChk = 1;
    }


    // -----------------------Validate Land Line not null---------------------------------
    if (landLinePhone !== null || landLinePhone.value !== "") {
        if (landLinePhone.value.length > 12) {
//            landLinePhoneDiv.innerHTML = "<br/> Please Enter A Valid Land Line Phone <br/>";
            landLinePhoneDiv.innerHTML = "<br/>من فضلك ادخل رقم تليفون صحيح<br/>";
            errChk = 1;
        }
    }

    //    ----------------------------------------------------------------------
    // -----------------------Validate BirthDate not execced max length---------------------------------
    if (birthDate !== null || birthDate.value !== "") {
        if (birthDate.value.length > 15) {
//            birthDateDiv.innerHTML = "<br/> Please Enter A Valid Birth Date <br/>";
            birthDateDiv.innerHTML = "<br/> من فضلك ادخل تاريخ ميلاد صحيح <br/>";
            errChk = 1;
        }
    }
    //    ----------------------------------------------------------------------
    // -----------------------Validate Postal Code not execced max length---------------------------------
    if (postalCode !== null || postalCode.value !== "") {
        if (postalCode.value.length > 20) {
//            postalCodeDiv.innerHTML = "<br/> Please Enter A Valid Postal Code <br/>";
            postalCodeDiv.innerHTML = "<br/>من فضلك ادخل رقم بريدى صحيح <br/>";
            errChk = 1;
        }
    }

    //    ----------------------------------------------------------------------
//    //     ------------------------- The Answer Validation ---------------------------
//
//    if (answer == null || answer.value == "") {
//        answerDiv.innerHTML = "<br/> Please Enter A Valid Answer.<br/>";
//        errChk = 1;
//    }
//
//    //    ----------------------------------------------------------------------
    //    --------------------- Validate The Mobile Number ---------------------

    if ((Phone.value == null) || (Phone.value == "")) {
        phoneDiv.innerHTML = "من فضلك ادخل رقم موبيل آخر<br/>";
        //        alert("Please Enter your Phone Number")
        //        Phone.focus()
        //        return false
        errChk = 1;
    }
    if (Phone.value.length < 11) {
        phoneDiv.innerHTML = phoneDiv.innerHTML + "رقم الموبيل لا يقل عن 11 رقم<br/>";
        //        alert("Phone Number Can't Be Less Than 10 Digits");
        //        Phone.focus();
        //        return false;
        errChk = 1;
    }

    if (Phone.value.length > 11) {
        phoneDiv.innerHTML = phoneDiv.innerHTML + "رقم الموبيل لا يزيد عن 11 رقم<br/>";
        //        alert("Phone Number Can't Be Greater Than 10 Digits");
        //        Phone.focus();
        //        return false;
        errChk = 1;
    }
    if (checkInternationalPhone(Phone.value) == false) {
        phoneDiv.innerHTML = phoneDiv.innerHTML + "من فضلك ادخل رقم موبيل صحيح<br/>";
        //        alert("Please Enter a Valid Phone Number")
        //        Phone.value=""
        //        Phone.focus()
        //        return false
        errChk = 1;
    }
    //--------------------------------------------------------------------------
    //    --------------------- Validate The Alternative Mobile Number ---------------------
    if (alPhone.value.length > 0) {
        if (alPhone.value.length < 11) {
//            alPhoneDiv.innerHTML = alPhoneDiv.innerHTML + "Phone Number Can't Be Less Than 11 Digits<br/>";
            alPhoneDiv.innerHTML = alPhoneDiv.innerHTML + "رقم الموبيل لا يقل عن 11 رقم<br/>";
            errChk = 1;
        }
        if (alPhone.value.length > 11) {
//            alPhoneDiv.innerHTML = alPhoneDiv.innerHTML + "Phone Number Can't Be Greater Than 11 Digits<br/>";
            alPhoneDiv.innerHTML = alPhoneDiv.innerHTML + "رقم الموبيل لا يزيد عن 11 رقم<br/>";
            errChk = 1;
        }
        if (checkInternationalPhone(alPhone.value) == false) {
//            alPhoneDiv.innerHTML = alPhoneDiv.innerHTML + "Please Enter a Valid Phone Number<br/>";
            alPhoneDiv.innerHTML = alPhoneDiv.innerHTML + "من فضلك ادخل رقم موبيل صحيح<br/>";
            errChk = 1;
        }
    }
    //--------------------------------------------------------------------------
    if (errChk == 1) {
        return false;
    }
    return true;
}

function ValidateFormAddAgent() {
    document.getElementById("btnSubmit").disabled = true;
    document.getElementById("nameDiv").innerHTML = "";
    document.getElementById("passDiv1").innerHTML = "";
    document.getElementById("passDiv2").innerHTML = "";
    document.getElementById("phoneDiv").innerHTML = "";

    var Pass = document.AddAgent.PASSWORD;
    var Pass2 = document.AddAgent.PARAM_CONFIRM_PASSWORD;
    var Phone = document.AddAgent.MSISDN
    var name = document.AddAgent.USERNAME
    var errChk = 0;

    if ((name.value == null) || (name.value == "")) {
//        document.getElementById("nameDiv").innerHTML = "Please Enter Agent Name<br/>";
        document.getElementById("nameDiv").innerHTML = "من فضلك ادخل اسم الوكيل<br/>";
        errChk = 1;
        //        alert("Please Enter agent Name")
        //        return false
    }

    if ((name.length < 5 && name.length > 0)) {
        document.getElementById("nameDiv").innerHTML = document.getElementById("nameDiv").innerHTML + "الأسم قصير جدآ<br/>";
        errChk = 1;
        //        alert("Please Enter agent Name")
        //        return false
    }

    if (Pass.value == null || Pass.value == "") {
//        document.getElementById("passDiv1").innerHTML = "Please Enter Valid Password<br/>";
        document.getElementById("passDiv1").innerHTML = "من فضلك ادخل كلمة مرور صحيح<br/>";
        errChk = 1;
        //        Pass.focus();
        //        pass.value ="";
        //        return false;
    }

    if (Pass.value.length < 4) {
        // alert(document.getElementById("passDiv1").innerHTML)
//        document.getElementById("passDiv1").innerHTML = document.getElementById("passDiv1").innerHTML + "Password Can't Be Less Than 4 Charachters<br/>"
        document.getElementById("passDiv1").innerHTML = document.getElementById("passDiv1").innerHTML + "كلمة المرور لا يقل عن 4 احرف<br/>"
        errChk = 1;
        //        alert("Password Can't Be Less Than 4 Charachters");
        //        Pass.focus();
        //        Pass.value = "";
        //        Pass2.value = "";
        //        return false;
    }

    if (Pass.value.length > 20) {
//        document.getElementById("passDiv1").innerHTML = document.getElementById("passDiv1").innerHTML + "Password Can't Be Greater Than 20 Charachters";
        document.getElementById("passDiv1").innerHTML = document.getElementById("passDiv1").innerHTML + "كلمة المرور لا يزيد عن 20 احرف<br/>";
        //        alert("Password Can't Be Greater Than 20 Charachters");
        //        Pass.focus();
        //        Pass.value = "";
        //        Pass2.value = "";
        //        return false;
        errChk = 1;
    }
    if (Pass2.value == null || Pass2.value == "") {
//        document.getElementById("passDiv2").innerHTML = "Please Enter The Confirmation Password";
        document.getElementById("passDiv2").innerHTML = "من فضلك اعد إدخال كلمة المرور";
        errChk = 1;
    }

    if (Pass.value != Pass2.value) {
//        document.getElementById("passDiv2").innerHTML = document.getElementById("passDiv2").innerHTML + "Un Compatable Password<br/>";
        document.getElementById("passDiv2").innerHTML = document.getElementById("passDiv2").innerHTML + "كلمة المرور غير متطابقة<br/>";
        //        alert("Please Check Your Password again");
        //        Pass.focus();
        Pass.value = "";
        Pass2.value = "";
        //        return false;
        errChk = 1;
    }


    if ((Phone.value == null) || (Phone.value == "")) {
//        document.getElementById("phoneDiv").innerHTML = "Please Enter your Phone Number<br/>";
        document.getElementById("phoneDiv").innerHTML = "من فضلك ادخل رقم موبيل آخر<br/>";
        //        alert("Please Enter your Phone Number")
        //        Phone.focus()
        //        return false
        errChk = 1;
    }
    if (Phone.value.length < 11) {
//        document.getElementById("phoneDiv").innerHTML = document.getElementById("phoneDiv").innerHTML + "Phone Number Can't Be Less Than 11 Digits<br/>";
        document.getElementById("phoneDiv").innerHTML = document.getElementById("phoneDiv").innerHTML + "رقم الموبيل لا يقل عن 11 رقم<br/>";
        //        alert("Phone Number Can't Be Less Than 10 Digits");
        //        Phone.focus();
        //        return false;
        errChk = 1;
    }
    if (Phone.value.length > 16) {
//        document.getElementById("phoneDiv").innerHTML = document.getElementById("phoneDiv").innerHTML + "Phone Number Can't Be Greater Than 16 Digits<br/>";
        document.getElementById("phoneDiv").innerHTML = document.getElementById("phoneDiv").innerHTML + "رقم الموبيل لا يزيد عن 11 رقم<br/>";
        //        alert("Phone Number Can't Be Greater Than 10 Digits");
        //        Phone.focus();
        //        return false;
        errChk = 1;
    }
    if (checkInternationalPhone(Phone.value) == false) {
//        document.getElementById("phoneDiv").innerHTML = document.getElementById("phoneDiv").innerHTML + "Please Enter a Valid Phone Number<br/>";
        document.getElementById("phoneDiv").innerHTML = document.getElementById("phoneDiv").innerHTML + "من فضلك ادخل رقم موبيل صحيح<br/>";
        //        alert("Please Enter a Valid Phone Number")
        //        Phone.value=""
        //        Phone.focus()
        //        return false
        errChk = 1;
    }
    if (errChk == 1) {
        return false
    }
    return true
}

function ValidateFormUpdateAgent() {
    document.getElementById("UpAgNameDiv").innerHTML = "";
    document.getElementById("UpAgphoneDiv").innerHTML = "";
    var Phone = document.getElementById("UpAgTelephoneId");
    var errChk = 0;
    var name = document.getElementById("UpAgName");
    if ((name.value == null) || (name.value == "")) {
//        document.getElementById("UpAgNameDiv").innerHTML = "Please Enter Agent Name<br/>";
        document.getElementById("UpAgNameDiv").innerHTML = "من فضلك ادخل اسم الوكيل<br/>";
        errChk = 1;
        //        alert("Please Enter agent Name")
        //        return false
    }
    if ((name.length < 5 && name.length > 0)) {
//        document.getElementById("UpAgNameDiv").innerHTML = document.getElementById("UpAgNameDiv").innerHTML + "Very Short Name <br/>";
        document.getElementById("UpAgNameDiv").innerHTML = document.getElementById("UpAgNameDiv").innerHTML + "الأسم قصير جدآ<br/>";
        errChk = 1;
        //        alert("Please Enter agent Name")
        //        return false
    }
    if ((Phone.value == null) || (Phone.value == "")) {
//        document.getElementById("UpAgphoneDiv").innerHTML = "Please Enter your Phone Number<br/>";
        document.getElementById("UpAgphoneDiv").innerHTML = "من فضلك ادخل رقم موبيل <br/>";
        //        alert("Please Enter your Phone Number")
        //        Phone.focus()
        //        return false
        errChk = 1;
    }
    if (Phone.value.length < 11) {
        document.getElementById("UpAgphoneDiv").innerHTML = document.getElementById("UpAgphoneDiv").innerHTML + "رقم الموبايل غير صحيح<br/>";
        //        alert("Phone Number Can't Be Less Than 10 Digits");
        //        Phone.focus();
        //        return false;
        errChk = 1;
    }
    if (Phone.value.length > 16) {
        document.getElementById("UpAgphoneDiv").innerHTML = document.getElementById("UpAgphoneDiv").innerHTML + "رقم الموبايل غير صحيح<br/>";
        //        alert("Phone Number Can't Be Greater Than 10 Digits");
        //        Phone.focus();
        //        return false;
        errChk = 1;
    }
    if (checkInternationalPhone(Phone.value) == false) {
        document.getElementById("UpAgphoneDiv").innerHTML = document.getElementById("UpAgphoneDiv").innerHTML + "رقم الموبايل غير صحيح<br/>";
        //        alert("Please Enter a Valid Phone Number")
        //        Phone.value=""
        //        Phone.focus()
        return false
        errChk = 1;
    }
    if (errChk == 1) {
        return false
    }
    return true
}

function validateBalance()
{
    document.getElementById("btnSubmit").disabled = true;
    var errChk = 0;
    var adBalance = document.getElementById("addBal");
    if (adBalance.value === 0 || adBalance.value < 0 || adBalance.value === null) {
//        window.alert("validate balance = false");

        document.getElementById("balDiv").innerHTML = "<br>من فضلك ادخل قيمة صحيحة.<br/>";
        errChk = 1;
    }
    if (!isInteger(adBalance.value)) {
//        window.alert("validate balance = false");
        document.getElementById("balDiv").innerHTML = "<br>من فضلك ادخل قيمة صحيحة.<br/>";
        errChk = 1;
    }

    if (errChk === 1) {
        return false;
    }
//                                                                window.alert("validate balance = ture");

    return true;
}

function showTransferAgain(message)
{
    document.getElementById("errorMessageTR").style.display = "table-row";
    document.getElementById("ErrorMessage").innerHTML = message;
}

function showErrorMessage(message)
{
    //errorMessageTR
    document.getElementById("errorMessageTR").style.display = "table-row";
    document.getElementById("ErrorMessage").innerHTML = message;
    document.getElementById("btnSubmit1").style.visibility = "hidden";
    document.getElementById("btnBack").style.visibility = "visible";

}
function showFees()
{
    document.getElementById("toTR").style.display = "table-row";
    document.getElementById("FeesTR").style.display = "table-row";
}
function backToHome()
{
    window.location.replace("2.jsp");
}


function validateTransfer() {

    var fees = document.getElementById("fees");

    if (fees = "-1.0")
        return false;
    else
        return true;
}

function validateBillBayCredit(isfrac)
{
    var errChk = 0;
    var balanceVal = document.getElementById("custTopUpDalanceID");
    var balanceDiv = document.getElementById("custTopUpDalanceDiv");
    var mobileDiv = document.getElementById("custTopUpMobileDiv");
    var mobileValue = document.getElementById("custTopUpMobileId");
    var ServiceBalance = document.getElementById("ServiceBalance");
    var Fees = document.getElementById("Fees");
    //    alert(balanceDiv.innerHTML)
    balanceDiv.innerHTML = "";
    if (balanceVal.value === "")
    {
        balanceDiv.innerHTML = "<br>من فضلك ادخل قيمة صحيحة.</br>";
        errChk = 1;
        return false;
    }
    if ((!isInteger(balanceVal.value) && !isfrac))
    {
        balanceDiv.innerHTML = "<br>الفاتورة لا تقبل كسور </br>";
        errChk = 1;
        return false;
    }

    if (!mobileValue.value === "") {
        if (!checkInternationalPhone(mobileValue.value))
        {
            mobileDiv.innerHTML = "<br>رقم غير صحيح </br>";
            errChk = 1;
            return false;
        }
    }
    if (errChk === 1) {
        return false;
    }
    return true;
}
function validateBillerForm()
{
    var errChk = 0;
    var ServiceName = document.getElementById("ServiceName");
    var BILL_LABLE = document.getElementById("BILL_LABLE");
    var BILL_LABLEDIV = document.getElementById("BILL_LABLEDIV");
    var ServiceNameDiv = document.getElementById("ServiceNameDiv");
    //    alert(balanceDiv.innerHTML)
    balanceDiv.innerHTML = "";
    if (ServiceName.value == "")
    {
        ServiceNameDiv.innerHTML = "<br>من فضلك ادخل اسم صحيح</br>";
        errChk = 1;
        return false;
    }
    if (BILL_LABLE.value == "")
    {
        BILL_LABLEDIV.innerHTML = "<br>من فضلك ادخل اسم صحيح</br>";
        errChk = 1;
        return false;
    }
    if (errChk == 1) {
        return false;
    }
    return true;
}

function ValidatePhone(text) {
    var regexObj2 = /\(?([0-9]{2})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
    var regexObj1 = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
    if (text.match(regexObj2) || text.match(regexObj1)) {
        return true;
    } else {
        return false;
    }
}

function ValidateMobileNo(text) {
    var regexObj = /01\d\d\d\d\d\d\d\d\d/;
    if (text.match(regexObj)) {
        return true;
    } else {
        return false;
    }
}

function validateBillphone()
{
    var errChk = 0;
    var balanceVal = document.getElementById("custTopUpDalanceID");
    var balanceDiv = document.getElementById("custTopUpDalanceDiv");
    balanceDiv.innerHTML = "";
    var serviceID = document.getElementById("ServiceID").value;
    if (serviceID == 99001 || serviceID == 99002 || serviceID == 99003 || serviceID == 99004 || serviceID == 99006 || serviceID == 22889 || serviceID == 22877 || serviceID == 99999) {
        if (!isInteger(balanceVal.value) || balanceVal.value == "" || balanceVal.value.length < 5)
        {
            balanceDiv.innerHTML = "<br>لرقم غير صحيح</br>";
            errChk = 1;
        }
    } else
    {
        if (!isInteger(balanceVal.value) || balanceVal.value == "" || (!ValidatePhone(balanceVal.value) && !ValidateMobileNo(balanceVal.value)) || balanceVal.value.length < 6)
        {
            balanceDiv.innerHTML = "<br>الرقم غير صحيح</br>";
            errChk = 1;
        }
    }
    if (errChk == 1) {
        return false;
    }
    return true;
}

function checkPhoneWithAreaCode() {
    var mobileValue = document.getElementById("custTopUpMobileId");
    var PhoneConfDiv = document.getElementById("custMobileConfirmationDiv");
    var Mobile_Confirmation = document.getElementById("custMobileConfirmation");

    if (mobileValue.value !== Mobile_Confirmation.value)
    {
        PhoneConfDiv.innerHTML = "ارقام الموبايل غير متطابقه";
        Mobile_Confirmation.focus();
        return false;
    }
    if (!isInteger(mobileValue.value) || mobileValue.value == "" || Mobile_Confirmation.value == "" || mobileValue.value.length < 6 || Mobile_Confirmation.value.length < 6 ||
            !isInteger(Mobile_Confirmation.value) || !ValidatePhone(mobileValue.value) || !ValidatePhone(Mobile_Confirmation.value)) {
        PhoneConfDiv.innerHTML = "الرقم غير صحيح";
        return false;

    }
    return true;

}

function validateMobinilBill(Balance, lan, serviceType)
{
    var PhonenumberERRMessageen = "Please enter a valid phone number";
    var PhonenumberERRMessagear = "رقم الهاتف خطأ برجاء ادخال الرقم الصحيح";
    var PhonenumberERRMessage = "رقم الهاتف خطأ برجاء ادخال الرقم الصحيح";
    var BalanceERRMessageen = "You don't have enogh balance";
    var BalanceERRMessagear = "رصيد الخدمة غير كافى لسداد الفاتورة";
    var BalanceERRMessage = "رصيد الخدمة غير كافى لسداد الفاتورة";
    var BalanceEmpERRMessageen = "Please enter a valid amount";
    var BalanceEmpERRMessagear = "برجاء ادخال قيمة الفاتورة المراد سدادها";
    var BalanceEmpERRMessage = "برجاء ادخال قيمة الفاتورة المراد سدادها";
    var Amount = document.getElementById("custAmountID");
    var AmountDiv = document.getElementById("custAmountDiv");
    var PhoneDiv = document.getElementById("custMobileDiv");
    var PhoneConfDiv = document.getElementById("custMobileConfirmationDiv");
    var Mobile = document.getElementById("custTopUpMSISDNID");
    var Mobile_Confirmation = document.getElementById("custMobileConfirmation");
    if (!isInteger(Amount.value)) {
        AmountDiv.innerHTML = "برجاء ادخال رقم صحيح بدون كسور";
        Amount.focus();
        return false;
    }


    if (Amount.value < 10) {
        AmountDiv.innerHTML = "الحد الادنى 10 جنيه";
        Amount.focus();
        return false;
    }

    if (Amount.value == 0 || Amount.value > Balance)
    {
        PhoneDiv.innerHTML = BalanceERRMessage;
        return false;
    }

    if (!isInteger(Mobile.value) || Mobile.value == "" || (serviceType == 115 && Mobile.value.length < 6) || (serviceType == 112 && Mobile.value.length < 11))
    {
        PhoneDiv.innerHTML = "برجاء ادخال رقم موبايل صحيح";
        Mobile.focus();
        return false;
    }

    if (Mobile.value != Mobile_Confirmation.value)
    {
        PhoneConfDiv.innerHTML = "ارقام الموبايل غير متطابقه";
        Mobile_Confirmation.focus();
        return false;
    }

    if (lan == "ar") {
        PhonenumberERRMessage = PhonenumberERRMessagear;
        BalanceERRMessage = BalanceERRMessagear;
        BalanceEmpERRMessage = BalanceEmpERRMessagear;
    } else {
        PhonenumberERRMessage = PhonenumberERRMessageen;
        BalanceERRMessage = BalanceERRMessageen;
        BalanceEmpERRMessage = BalanceEmpERRMessageen;
    }

    return true;
}


function validateServiceAssign()
{
    var errChk = 0;
    //    get the Div
    var balanceDiv = document.getElementById("BalanceDiv");
    //    set the div with null
    balanceDiv.innerHTML = "";
    //    get  valueس

    var balance = document.AssignBalanceAgent.AMOUNT;
    //     ------------------------- The balance Validation ---------------------------
    if (balance.value == null || balance.value == "") {
        balanceDiv.innerHTML = "<br>من فضلك ادخل قيمة صحيحة.<br>";
        errChk = 1;
    }

    //    validate the username not less than 5 charachters
    if (!isInteger(balance.value)) {
        balanceDiv.innerHTML = balanceDiv.innerHTML + "<br>من فضلك ادخل قيمة صحيحة.<br>";
        errChk = 1;
    }

    if (errChk == 1) {
        return false;
    }
    return true;
}
function validateCustomerTransaction()
{
    if (isInteger(document.AssignBalanceCustomer.AMOUNT.value))
        return true;
    else {
        alert("من فضلك ادخل قيمة صحيحة")

        return false;
    }
    if (isInteger(document.AssignBalanceCustomer.PAYEDPIN.value))
        return true;
    //alert("Enter valid customer pin or phone number");
    alert(" رقم الموبايل غير صحيح ");
    return false;

}

function validateBill()
{
    if (!isInteger(document.AssignBill.BALANCE.value))
    {
        alert("من فضلك ادخل قيمة صحيحة");
        return false;
    }
    var monthField = document.AssignBill.MONTH.value;
    if (!(isInteger(monthField) && monthField > 0 && monthField < 13))
    {
        // alert("Enter Valid month from 1 to 12");
        alert("ادخل شهر صحيح");
        return false;
    }
    var yearField = document.AssignBill.YEAR.value;
    if (!isInteger(yearField))
    {
        // alert("Enter Valid year ");
        alert("ادخل سنه صحيحة");
        return false;
    }
    return true;

}

function validateBayCreditCustomer()
{
    var balanceVal = document.getElementById("custTopUpDalanceID");
    var balanceDiv = document.getElementById("custTopUpDalanceDiv");
    var mobileDiv = document.getElementById("custTopUpMobileDiv");
    var mobileValue = document.getElementById("custTopUpMobileId");
    var PhoneConfDiv = document.getElementById("custMobileConfirmationDiv");
    var Mobile_Confirmation = document.getElementById("custMobileConfirmation");
    //    alert(balanceDiv.innerHTML)
    balanceDiv.innerHTML = "";
    if (!isInteger(balanceVal.value) || balanceVal.value == "")
    {
        balanceDiv.innerHTML = "<br>لا يوجد شحن بهذه القيمة</br>";
        return false;
    }

    if (balanceVal.value < 0 || balanceVal.value > 250) {
        balanceDiv.innerHTML += "<br>من فضلك ادخل مبلغ اكثر من 1 و اقل من 250</br>";
        return false;
    }

    if (!checkInternationalPhone(mobileValue.value))
    {
        mobileDiv.innerHTML = "<br> رقم الموبايل غير صحيح </br>";
        return false;
    }
    if (!checkInternationalPhone(Mobile_Confirmation.value))
    {
        PhoneConfDiv.innerHTML = "برجاء ادخال رقم موبايل صحيح";
        Mobile_Confirmation.focus();
        return false;
    }
    if (mobileValue.value !== Mobile_Confirmation.value)
    {
        PhoneConfDiv.innerHTML = "ارقام الموبايل غير متطابقه";
        Mobile_Confirmation.focus();
        return false;
    }
    return true;
}
function validateBlaBlaRechargeCustomer()
{
    var balanceVal = document.getElementById("custTopUpDalanceID");
    var balanceDiv = document.getElementById("custTopUpDalanceDiv");
    var mobileDiv = document.getElementById("custTopUpMobileDiv");
    var mobileValue = document.getElementById("custTopUpMobileId");
    var PhoneConfDiv = document.getElementById("custMobileConfirmationDiv");
    var Mobile_Confirmation = document.getElementById("custMobileConfirmation");
    //    alert(balanceDiv.innerHTML)
    balanceDiv.innerHTML = "";
    if (!isInteger(balanceVal.value) || balanceVal.value == "")
    {
        balanceDiv.innerHTML = "<br>لا يوجد شحن بهذه القيمة</br>";
        return false;
    }

    if (balanceVal.value < 0) {
        balanceDiv.innerHTML += "<br>من فضلك ادخل مبلغ اكثر من 1 و اقل من 250</br>";
        return false;
    }

    if (!checkInternationalPhone(mobileValue.value))
    {
        mobileDiv.innerHTML = "<br> رقم الموبايل غير صحيح </br>";
        return false;
    }
    if (!checkInternationalPhone(Mobile_Confirmation.value))
    {
        PhoneConfDiv.innerHTML = "برجاء ادخال رقم موبايل صحيح";
        Mobile_Confirmation.focus();
        return false;
    }
    if (mobileValue.value !== Mobile_Confirmation.value)
    {
        PhoneConfDiv.innerHTML = "ارقام الموبايل غير متطابقه";
        Mobile_Confirmation.focus();
        return false;
    }
    return true;
}
function validateEtisalatTransfer()
{
    var balanceVal = document.getElementById("custTopUpDalanceID");
    var balanceDiv = document.getElementById("custTopUpDalanceDiv");
    //    alert(balanceDiv.innerHTML)
    balanceDiv.innerHTML = "";
    if (balanceVal.value < 1 || balanceVal.value > 100000) {
        balanceDiv.innerHTML = "<p>من فضلك ادخل مبلغ اكثر من 1 و اقل من 100000 </p>";
        return false;
    }

    if (!isInteger(document.BayCreditCustomer.AMOUNT.value) || document.BayCreditCustomer.AMOUNT.value == "")
    {
        alert("لا يوجد شحن بهذه القيمة");
        return false;
    }
    if (!checkInternationalPhone(document.BayCreditCustomer.MSISDN.value))
    {
        alert("رقم الموبايل غير صحيح");
        return false;
    }

    return true;
}

function validateAssignBalanceAgent() {

    var errChk = 0;
    //    get the Div
    var balanceDiv = document.getElementById("BalanceDiv");
    //    set the div with null
    balanceDiv.innerHTML = "";
    //    get  valueس

    var balance = document.AssignBalanceAgent.BALANCE;
    //     ------------------------- The balance Validation ---------------------------
    if (balance.value == null || balance.value == "") {
        balanceDiv.innerHTML = "<br/> لا يوجد شحن بهذه القيمة<br/>";
        errChk = 1;
    }

    //    validate the username not less than 5 charachters
    if (!isInteger(balance)) {
        balanceDiv.innerHTML = balanceDiv.innerHTML + "لا يوجد شحن بهذه القيمة<br/>";
        errChk = 1;
    }

    if (errChk == 1) {
        return false;
    }
    return true;
}

function validateBayCreditCustomerZero()
{
    errChk = 0;
    var balanceVal = document.getElementById("custTopUpDalanceID");
    var phoneVal = document.getElementById("custTopUpPhoneId");
    var balanceDiv = document.getElementById("custTopUpDalanceDiv");
    var phoneDiv = document.getElementById("custTopUpPhoneDiv");
    //    alert(balanceDiv.innerHTML)
    balanceDiv.innerHTML = "";
    if (balanceVal.value < 3) {
//        balanceDiv.innerHTML = "<br>Transfered Value Can't Be less Than 3</br>";
        balanceDiv.innerHTML = "<br>قيمة الشحن لا تقل عن 1 جنيه</br>";
        errChk = 1;
        //        return false;
    }

    if (!isInteger(document.BayCreditCustomer.AMOUNT.value) || document.BayCreditCustomer.AMOUNT.value == "")
    {
        balanceDiv.innerHTML = balanceDiv.innerHTML + "<br>لا يوجد شحن بهذه القيمة</br>";
        errChk = 1
        //        alert("Enter Valid amount ");
        //        return false;
    }
    var phoneNumber = phoneVal.value;
    if (phoneNumber.length < 7 || phoneNumber.length > 8)
    {
        //        alert(phoneNumber.length);
        //        phoneDiv.innerHTML = "<br>Enter Valid Phone Number </br>";
        //  phoneDiv.innerHTML = "<br>Phone Number Can't be less 7 digits or greater than 8 digits </br>";
        phoneDiv.innerHTML = "<br>من فضلك ادخل رقم تليفون صحيح</br>";
        errChk = 1;
        //        alert("Enter Valid Phone number ");
        //        return false;
    }

    if (phoneNumber) {

    }
    if (errChk == 1) {
        return false;
    }

    return true;
}
//------------------------------------------Vodafone Cash--------------------------------------------
function checkVodafoneCashForm(form) {

    var AmountDiv = document.getElementById("custAmountDiv");
    var PhoneDiv = document.getElementById("custMobileDiv");
    var PhoneConfDiv = document.getElementById("custMobileConfirmationDiv");
    var Mobile = document.getElementById("custMobile");
    var Mobile_Confirmation = document.getElementById("custMobileConfirmation");

    if (!isInteger(form.AMOUNT.value)) {
        AmountDiv.innerHTML = "برجاء ادخال رقم صحيح بدون كسور";
        form.AMOUNT.focus();
        return false;
    }
    if (form.AMOUNT.value < 5 || form.AMOUNT.value > 3000) {
        AmountDiv.innerHTML = " الحد الأدنى 5 جنيه و الأقصى 3000 جنيه";
        form.AMOUNT.focus();
        return false;
    }

    if (!checkInternationalPhone(Mobile.value))
    {

        PhoneDiv.innerHTML = "برجاء ادخال رقم موبايل صحيح";
        Mobile.focus();
        return false;
    }
    if (!checkInternationalPhone(Mobile_Confirmation.value))
    {
        PhoneConfDiv.innerHTML = "برجاء ادخال رقم موبايل صحيح";
        Mobile_Confirmation.focus();
        return false;
    }
    if (Mobile.value != Mobile_Confirmation.value)
    {
        PhoneConfDiv.innerHTML = "ارقام الموبايل غير متطابقه";
        Mobile_Confirmation.focus();
        return false;
    }
    return true;
}
function checkVodafoneCheck_outForm(form) {
    var AmountDiv = document.getElementById("custAmountDiv_Check_out");
    var PhoneDiv = document.getElementById("custMobileDiv_Check_out");
    var PhoneConfDiv = document.getElementById("custMobileConfirmationDiv_Check_out");
    var Mobile = document.getElementById("custMobile_Check_out");
    var Mobile_Confirmation = document.getElementById("custMobileConfirmation_Check_out");

    if (!isInteger(form.AMOUNT.value)) {
        AmountDiv.innerHTML = "برجاء ادخال رقم صحيح بدون كسور";
        form.AMOUNT.focus();
        return false;
    }
    if (form.AMOUNT.value < 5 || form.AMOUNT.value > 3000) {
        AmountDiv.innerHTML = " الحد الأدنى 5 جنيه و الأقصى 3000 جنيه";
        form.AMOUNT.focus();
        return false;
    }

    if (!checkInternationalPhone(Mobile.value))
    {

        PhoneDiv.innerHTML = "برجاء ادخال رقم موبايل صحيح";
        Mobile.focus();
        return false;
    }
    if (!checkInternationalPhone(Mobile_Confirmation.value))
    {
        PhoneConfDiv.innerHTML = "برجاء ادخال رقم موبايل صحيح";
        Mobile_Confirmation.focus();
        return false;
    }
    if (Mobile.value != Mobile_Confirmation.value)
    {
        PhoneConfDiv.innerHTML = "ارقام الموبايل غير متطابقه";
        Mobile_Confirmation.focus();
        return false;
    }
    return true;
}

