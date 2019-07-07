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
    var phoneno = document.getElementById("DonatorPhone").value;
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
    var validformat = /^\+?(0|[1-9]\d*)$/;
    var amount = document.getElementById("donationValue").value;
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
    var ValAmount = document.getElementById("donationValue").value;
    if (ValAmount === '')
    {
        return  false;
    }
    else
    {
        if ((parseInt(ValAmount) >= 10) && isNaN(ValAmount) === false && validateAmount() === true)
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
        IsWrongInupt("DonatorPhone");
        document.getElementById("DonatorPhone").scrollIntoView({block: "start"});
        $("#DonatorPhone").notify("من فضلك أدخل رقم موبيل صحيح");
        return false;
    }
    else {
        IsRightInput("DonatorPhone");
    }
    if (validateAmount2() === false)
    {
        IsWrongInupt("donationValue");
        document.getElementById("donationValue").scrollIntoView({block: "start"});
        $("#donationValue").notify("رجاء إدخال قيمة تبرع صحيحة بحد أدنى 10 جنيهات");
        return false;
    }
    else {
        IsRightInput("donationValue");
    }
    return true;
}