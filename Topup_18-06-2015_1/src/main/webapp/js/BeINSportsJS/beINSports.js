/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

function validatePhoneNo()
{
    var validformat = /^01\d{9}$/i;
    var phoneno = document.getElementById("PhoneNumber").value;
    if (validformat.test(phoneno))
    {
        return true;
    } else
    {
        return false;
    }
}


function validateAmount2()
{
    var ValAmount = document.getElementById("subscribtion").value;
    if (ValAmount === '')
    {
        return  false;
    } else
    {
            return true;
        
    }
}
function validateAmount3()
{
    var validformat = /^\d+$/;
    var ValAmount = document.getElementById("subscribtion").value;
    if (validformat.test(ValAmount) === true)
    {
        return true;
    } else
    {
        return false;
    }
}
function formSubmit()
{
    if (validateAmount3() === false)
    {
        IsWrongInupt("subscribtion");
        document.getElementById("subscribtion").scrollIntoView({block: "start"});
        $("#subscribtion").notify("من فضلك تأكد من رقم الأشتراك و حاول مرة اخرى");
        return false;
    } else {
        IsRightInput("subscribtion");
    }
    if (validatePhoneNo() === false)
    {
        IsWrongInupt("PhoneNumber");
        document.getElementById("PhoneNumber").scrollIntoView({block: "start"});
        $("#PhoneNumber").notify("من فضلك أدخل رقم الموبيل بشكل صحيح");
        return false;
    } else {
        IsRightInput("PhoneNumber");
    }
    if (validateAmount2() === false)
    {
        IsWrongInupt("subscribtion");
        document.getElementById("subscribtion").scrollIntoView({block: "start"});
        $("#subscribtion").notify("من فضلك تأكد من إدخال رقم الإشتراك");
        return false;
    } else {
        IsRightInput("subscribtion");
    }


    return true;


}