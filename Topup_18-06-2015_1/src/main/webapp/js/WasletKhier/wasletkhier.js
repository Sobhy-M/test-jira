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

function validateAmount()
{
    var validformat = /^[-+]?[0-9]*\.?[0-9]+$/;
    var amount = document.getElementById("Amount").value;
//    alert(document.getElementById("Amount").value);
    if (validformat.test(amount))
    {
        return true;
    } else
    {
        return false;
    }
}
function validateAmount2()
{
    var ValAmount = document.getElementById("Amount").value;
    if (ValAmount === '')
    {
        return  false;
    } else
    {
        if ((parseInt(ValAmount) <= 5000 && parseInt(ValAmount) >= 10) && isNaN(ValAmount) === false && validateAmount() === true)
        {
            return true;

        } else
        {
            return false;
        }
    }
}

function BtnSubmit()
{
    if (validatePhoneNo() === false)
    {
        IsWrongInupt("PhoneNumber");
        document.getElementById("PhoneNumber").scrollIntoView({block: "start"});
        $("#PhoneNumber").notify("من فضلك أدخل رقم موبيل صحيح");
        return false;
    } else {
        IsRightInput("PhoneNumber");
    }
    if (validateAmount2() === false)
    {
        IsWrongInupt("Amount");
        document.getElementById("Amount").scrollIntoView({block: "start"});
        $("#Amount").notify("الحد الادنى للتبرع 10 جنيهات و الاقصى 5000 بدون كسور");
        return false;
    } else {
        IsRightInput("Amount");
    }
    return true;
}