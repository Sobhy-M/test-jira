/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var phone = false, arbicName = false;
function ISvalidFullAddress(input)
{
    var pattern = /^[\u0621-\u064A\u0660-\u0669-0-9 ]+$/;
    if (pattern.test(input) === false)
        return false;
    return true;
}
function validatePhoneNo()
{
    var validformat = /^01\d{9}$/i;
    var phoneno = document.getElementById("MSISDN").value;
    if (validformat.test(phoneno))
    {
        return true;
    } else
    {
        return false;
    }
}
function onchooseAccount()
{
    if (document.getElementById("AccountTypeMerchant").checked === true)
    {
        document.getElementById("WorkType").value = "";
        document.getElementById("ShopName").value = "";
        document.getElementById("WorkType").readOnly = false;
        document.getElementById("WorkType").style.backgroundColor = '#DCDCDC';

        document.getElementById("MerchentTD").style.display = "table-row";
        document.getElementById("workID").style.display = "table-row";
        document.getElementById("workID").style.display = "table-row";
    }
    if (document.getElementById("AccountTypePrivate").checked === true)
    {
        document.getElementById("WorkType").value = "حساب شخصي";
        document.getElementById("ShopName").value = "حساب شخصي";
        document.getElementById("WorkType").readOnly = true;
        document.getElementById("WorkType").style.backgroundColor = '#DCDCDC';
        document.getElementById("MerchentTD").style.display = "none";
        document.getElementById("workID").style.display = "none";
        document.getElementById("workID").style.display = "none";
    }
}
function IsWrongInupt(input)
{
    document.getElementById(input).style.backgroundColor = "yellow";
    document.getElementById(input).style.borderColor = "red";
}
function IsRightInput(input)
{
    document.getElementById(input).style.backgroundColor = "";
    document.getElementById(input).style.borderColor = "";
}
function validateArabicName(input)
{

    var Validateformat = /^([\u0600-\u06FF]+\s)*[\u0600-\u06FF]+$/;
    var Name = document.getElementById(input).value;
    if (Validateformat.test(Name))
    {
        return true;
    } else
    {
        return false;
    }
}
function validateEnglishName(input)
{

    var Validateformat = /^([a-zA-Z]+\s)*[a-zA-Z]+$/;
    var Name = document.getElementById(input).value;
    if (Validateformat.test(Name))
    {
        return true;
    } else
    {
        return false;
    }
}
function IsValidArbicName()
{
    var arabicName1 = false, arabicName2 = false, arabicName3 = false, arabicName4 = false;
    arabicName1 = validateArabicName("ArabicName1");
    if (arabicName1 === false)
    {
        IsWrongInupt("ArabicName1");
        document.getElementById("ArabicName1").scrollIntoView({block: "start"});
        $("#ArabicName1").notify("من فضلك إدخل الإسم صحيح باللغة العربية");
        return false;
    }
    else
    {
        IsRightInput("ArabicName1");
    }


    arabicName2 = validateArabicName("ArabicName2");
    if (arabicName2 === false)
    {
        IsWrongInupt("ArabicName2");
        document.getElementById("ArabicName2").scrollIntoView({block: "start"});
        $("#ArabicName2").notify("من فضلك إدخل الإسم صحيح باللغة العربية");
        return false;
    }
    else
    {
        IsRightInput("ArabicName2");
    }


    arabicName3 = validateArabicName("ArabicName3");
    if (arabicName3 === false)
    {
        IsWrongInupt("ArabicName3");
        document.getElementById("ArabicName3").scrollIntoView({block: "start"});
        $("#ArabicName3").notify("من فضلك إدخل الإسم صحيح باللغة العربية");
        return false;
    }
    else
    {
        IsRightInput("ArabicName3");
    }

    arabicName4 = validateArabicName("ArabicName4");
    if (arabicName4 === false)
    {
        IsWrongInupt("ArabicName4");
        document.getElementById("ArabicName4").scrollIntoView({block: "start"});
        $("#ArabicName4").notify("من فضلك إدخل الإسم صحيح باللغة العربية");
        return false;
    }
    else
    {
        IsRightInput("ArabicName4");
    }
    return true;
}
function IsvalidEnglishName()
{
    var englishName1 = false, englishName2 = false, englishName3 = false, englishName4 = false;
    englishName1 = validateEnglishName("EnglishName1");
    if (englishName1 === false)
    {
        IsWrongInupt("EnglishName1");
        document.getElementById("EnglishName1").scrollIntoView({block: "start"});
        $("#EnglishName1").notify("من فضلك إدخل الإسم صحيح باللغة الإنجليزية");

        return false;
    }
    else
    {
        IsRightInput("EnglishName1");
    }

    englishName2 = validateEnglishName("EnglishName2");
    if (englishName2 === false)
    {
        IsWrongInupt("EnglishName2");
        $("#EnglishName2").notify("من فضلك إدخل الإسم صحيح باللغة الإنجليزية");
        document.getElementById("EnglishName2").scrollIntoView({block: "start"});
        return false;
    }
    else
    {
        IsRightInput("EnglishName2");
    }

    englishName3 = validateEnglishName("EnglishName3");
    if (englishName3 === false)
    {
        IsWrongInupt("EnglishName3");
        $("#EnglishName3").notify("من فضلك إدخل الإسم صحيح باللغة الإنجليزية");
        document.getElementById("EnglishName3").scrollIntoView({block: "start"});
        return false;
    }
    else
    {
        IsRightInput("EnglishName3");
    }


    englishName4 = validateEnglishName("EnglishName4");
    if (englishName4 === false)
    {
        IsWrongInupt("EnglishName4");
        $("#EnglishName4").notify("من فضلك إدخل الإسم صحيح باللغة الإنجليزية");
        document.getElementById("EnglishName4").scrollIntoView({block: "start"});
        return false;
    }
    else
    {
        IsRightInput("EnglishName4");
    }
    return true;
}
$(function () {
    var dateToCalculate = new Date();
    var calculateYear = dateToCalculate.getFullYear() - 21;
    var minYear = calculateYear - 85;
    var calculateMonth = dateToCalculate.getMonth();
    var calculateDay = dateToCalculate.getDate();
    $("#BirthDate").datepicker({
        dateFormat: 'dd/mm/yy',
        yearRange: "-100:$(calculateYear)",
        minDate: new Date(minYear, calculateMonth, calculateDay),
        maxDate: new Date(calculateYear, calculateMonth, calculateDay),
        changeYear: true,
        changeMonth: true,
        showMonthAfterYear: true
    });
});
function ValidateNationalID()
{
    if (document.getElementById("BirthDate").value === "")
    {
        $("#BirthDate").notify("من فضلك إختر تاريخ ميلادك أولا ثم أكتب باقي الرقم القومي");
        document.getElementById("BirthDate").scrollIntoView({block: "start"});
        return false;
    }
    var nID = document.getElementById("NATIONAL_ID_1").value;
    if (nID.length !== 7)
    {
        IsWrongInupt("NATIONAL_ID_1");
        $("#NATIONAL_ID_1").notify("من فضلك أدخل باقي الرقم القومي صحيح و تأكد من النوع");
        document.getElementById("NATIONAL_ID_1").scrollIntoView({block: "start"});
        return false;
    }
    var genderIdent = parseInt(nID.charAt(5));
    if (document.getElementById("genderMale").checked)
    {
        var x = genderIdent % 2;
        if (x === 1)
        {
            IsRightInput("NATIONAL_ID_1");
            return true;
        }
        else
        {
            IsWrongInupt("NATIONAL_ID_1");
            $("#NATIONAL_ID_1").notify("من فضلك أدخل باقي الرقم القومي صحيح و تأكد من النوع");
            document.getElementById("NATIONAL_ID_1").scrollIntoView({block: "start"});
            return false;
        }
    }
    else
    {
        var x = genderIdent % 2;
        if (x === 0)
        {
            IsRightInput("NATIONAL_ID_1");
            return true;
        }
        else
        {
            IsWrongInupt("NATIONAL_ID_1");
            $("#NATIONAL_ID_1").notify("من فضلك أدخل باقي الرقم القومي صحيح و تأكد من النوع");
            document.getElementById("NATIONAL_ID_1").scrollIntoView({block: "start"});
            return false;
        }

    }
    return true;
}
function validateEmail()
{
    var email = document.getElementById("EMAIL_ADDRESS").value;
    var re = /\S+@\S+\.\S+/;
    if (email === "")
    {
        return true;
    }
    if (re.test(email) === false) {
        IsWrongInupt("EMAIL_ADDRESS");
        $("#EMAIL_ADDRESS").notify("من فضلك أدخل إيميل صحيح");
        document.getElementById("EMAIL_ADDRESS").scrollIntoView({block: "start"});
        return false;
    }
    IsRightInput("EMAIL_ADDRESS");
    return true;
}
function BtnSubmit()
{
    phone = validatePhoneNo();
    if (phone === false)
    {
        IsWrongInupt("MSISDN");
        $("#MSISDN").notify("من فضلك إدخل رقم موبيل صحيح مكون من  11 رقم");
        document.getElementById("MSISDN").scrollIntoView({block: "start"});
        return false;
    }
    else
    {
        IsRightInput("MSISDN");
        return phone;
    }
}
function validateAddress()
{
    if (document.getElementById("AccountTypeMerchant").checked === true)
    {
        if (document.getElementById("FullAddress").value === "") {
            IsWrongInupt("FullAddress");
            $("#FullAddress").notify("من فضلك أدخل العنوان التفصيلي الخاص بك");
            document.getElementById("FullAddress").scrollIntoView({block: "start"});
            return false;
        }
        else
        {
            var x = ISvalidFullAddress(document.getElementById("FullAddress").value);
            if (x === false)
            {
                IsWrongInupt("FullAddress");
                $("#FullAddress").notify("من فضلك أدخل العنوان التفصيلي باللغة العربية");
                document.getElementById("FullAddress").scrollIntoView({block: "start"});
                return false;
            }
            IsRightInput("FullAddress");
        }

        if (document.getElementById("HomeGovAddress").value === "") {
            IsWrongInupt("HomeGovAddress");
            $("#HomeGovAddress").notify("من فضلك إختار محافظة السكن");
            document.getElementById("HomeGovAddress").scrollIntoView({block: "start"});
            return false;
        }
        else
        {
            IsRightInput("HomeGovAddress");
        }

        if (document.getElementById("HomeGovAddress").value === "") {
            IsWrongInupt("HomeGovAddress");
            $("#HomeGovAddress").notify("من فضلك إختر مدينة السكن");

            document.getElementById("HomeGovAddress").scrollIntoView({block: "start"});

            return false;
        }
        else
        {
            IsRightInput("HomeGovAddress");
        }

        if (document.getElementById("HomeRegionAddress").value === "") {
            IsWrongInupt("HomeRegionAddress");
            $("#HomeRegionAddress").notify("من فضلك أدخل مكان مركز السكن");
            document.getElementById("HomeRegionAddress").scrollIntoView({block: "start"});
            return false;
        }
        else
        {
            var x = ISvalidFullAddress(document.getElementById("HomeRegionAddress").value);
            if (x === false)
            {
                IsWrongInupt("HomeRegionAddress");
                $("#HomeRegionAddress").notify("من فضلك أدخل العنوان التفصيلي الخاص بك");
                document.getElementById("HomeRegionAddress").scrollIntoView({block: "start"});
                return false;
            }
            IsRightInput("HomeRegionAddress");
        }


        if (document.getElementById("WorkFullAddress").value === "") {
            IsWrongInupt("WorkFullAddress");
            document.getElementById("WorkFullAddress").scrollIntoView({block: "start"});
            $("#WorkFullAddress").notify("من فضلك أدخل عنوان العمل التفصيلي باللغة العربية");
            return false;
        }
        else
        {
            var x = ISvalidFullAddress(document.getElementById("WorkFullAddress").value);
            if (x === false)
            {
                IsWrongInupt("WorkFullAddress");
                $("#WorkFullAddress").notify("من فضلك أدخل العنوان التفصيلي الخاص بك");

                document.getElementById("WorkFullAddress").scrollIntoView({block: "start"});

                return false;
            }
            IsRightInput("WorkFullAddress");
        }
        if (document.getElementById("WorkGovAddress").value === "") {
            IsWrongInupt("WorkGovAddress");
            $("#WorkGovAddress").notify("من فضلك إختار محافظة العمل");
            document.getElementById("WorkGovAddress").scrollIntoView({block: "start"});
            return false;
        }
        else
        {
            IsRightInput("WorkGovAddress");
        }
        if (document.getElementById("WorkCityAddress").value === "") {
            IsWrongInupt("WorkCityAddress");
            $("#WorkCityAddress").notify("من فضلك إختر مدينة العمل");
            document.getElementById("WorkCityAddress").scrollIntoView({block: "start"});
            return false;
        }
        else
        {
            IsRightInput("WorkCityAddress");
        }
        if (document.getElementById("WorkRegionAddress").value === "") {
            IsWrongInupt("WorkRegionAddress");
            $("#WorkRegionAddress").notify("من فضلك أدخل مكان مركز العمل");
            document.getElementById("WorkRegionAddress").scrollIntoView({block: "start"});
            return false;
        }
        else
        {
            var x = ISvalidFullAddress(document.getElementById("WorkRegionAddress").value);
            if (x === false)
            {
                IsWrongInupt("WorkRegionAddress");
                $("#WorkRegionAddress").notify("من فضلك أدخل العنوان الخاص بك باللغة العربية");
                document.getElementById("WorkRegionAddress").scrollIntoView({block: "start"});
                return false;
            }
            IsRightInput("WorkRegionAddress");
        }

        if (document.getElementById("ShopName").value === "") {
            IsWrongInupt("ShopName");
            $("#ShopName").notify("من فضلك ادخل اسم المحل");
            document.getElementById("ShopName").scrollIntoView({block: "start"});
            return false;
        }
        else
        {
            IsRightInput("ShopName");
        }
        
        if (document.getElementById("WorkType").value === "") {
            IsWrongInupt("WorkType");
            $("#WorkType").notify("من فضلك ادخل نوع النشاط");
            document.getElementById("WorkType").scrollIntoView({block: "start"});
            return false;
        }
        else
        {
            var x = ISvalidFullAddress(document.getElementById("WorkType").value);
            if (x === false)
            {
                IsWrongInupt("WorkType");
                $("#WorkType").notify("من فضلك أدخل العنوان الخاص بك باللغة العربية");
                document.getElementById("WorkType").scrollIntoView({block: "start"});
                return false;
            }
            IsRightInput("WorkType");
        }

        return true;
    }


    if (document.getElementById("AccountTypePrivate").checked === true)
    {
        if (document.getElementById("FullAddress").value === "")
        {
            IsWrongInupt("FullAddress");
            $("#FullAddress").notify("من فضلك أدخل العنوان التفصيلي الخاص بك");
            document.getElementById("FullAddress").scrollIntoView({block: "start"});
            return false;
        }
        else
        {
            var x = ISvalidFullAddress(document.getElementById("FullAddress").value);
            if (x === false)
            {
                IsWrongInupt("FullAddress");
                $("#FullAddress").notify("من فضلك أدخل العنوان التفصيلي الخاص بك");
                document.getElementById("FullAddress").scrollIntoView({block: "start"});
                return false;
            }
            IsRightInput("FullAddress");
        }

        if (document.getElementById("HomeGovAddress").value === "") {
            IsWrongInupt("HomeGovAddress");
            $("#HomeGovAddress").notify("من فضلك إختار محافظة السكن");
            document.getElementById("HomeGovAddress").scrollIntoView({block: "start"});
            return false;
        }
        else
        {
            IsRightInput("HomeGovAddress");
        }

        if (document.getElementById("HomeGovAddress").value === "") {
            IsWrongInupt("HomeGovAddress");
            $("#HomeGovAddress").notify("من فضلك إختر مدينة السكن");

            document.getElementById("HomeGovAddress").scrollIntoView({block: "start"});

            return false;
        }
        else
        {
            IsRightInput("HomeGovAddress");
        }

        if (document.getElementById("HomeRegionAddress").value === "") {
            IsWrongInupt("HomeRegionAddress");
            $("#HomeRegionAddress").notify("من فضلك أدخل مكان مركز السكن");

            document.getElementById("HomeRegionAddress").scrollIntoView({block: "start"});

            return false;
        }
        else
        {
            var x = ISvalidFullAddress(document.getElementById("HomeRegionAddress").value);
            if (x === false)
            {
                IsWrongInupt("HomeRegionAddress");
                $("#HomeRegionAddress").notify("من فضلك أدخل العنوان الخاص بك باللغة العربية");
                document.getElementById("HomeRegionAddress").scrollIntoView({block: "start"});
                return false;
            }
            IsRightInput("HomeRegionAddress");
        }
        document.getElementById("WorkFullAddress").value = "";
        document.getElementById("WorkGovAddress").value = "";
        document.getElementById("WorkCityAddress").value = "";
        document.getElementById("WorkRegionAddress").value = "";
        return true;

    }
}
function validateQuestion()
{
    if (document.getElementById("SecurityQuestion").value === "") {
        IsWrongInupt("SecurityQuestion");
        return false;
    }
    if (document.getElementById("SecurityAnswer").value === "") {
        $("#SecurityAnswer").notify("من فضلك أدخل إجابة السؤال السري");

        IsWrongInupt("SecurityAnswer");
        return false;
    }
    return true;
}
function onchangeDate()
{
    var birthdate = document.getElementById("BirthDate").value;
    var res = birthdate.split("/");
    //dd/mm/yy
    var nid = "2" + res[2].substring(2, res[2].length) + res[1] + res[0];
    document.getElementById("NATIONAL_ID_2").value = nid;
}
function AddWalletBtnSubmit()
{
    var arabic_name = IsValidArbicName();
    if (arabic_name === false)
        return false;
    var english_name = IsvalidEnglishName();
    if (english_name === false)
        return false;
    var nID = ValidateNationalID();
    if (nID === false)
        return false;
    var email = validateEmail();
    if (email === false)
        return false;
    var address = validateAddress();
    if (address === false)
        return false;
    var sq = validateQuestion();
    if (sq === false)
        return false;
    return true;
}