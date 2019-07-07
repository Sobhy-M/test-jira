/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var Amount = false, phoneno = false , name=true;
function onloadFunction()
{
    document.getElementById("DonationForm").reset();
}
function trueAmount(){
    Amount = true;
    name=true;
}
function NameNotMatch(element)
{
    var ValAmount = document.getElementById(element).value;
    if (ValAmount === '')
    {
       name=true;
       IsRightInput(element);
    }
    else
    {
        if (validateName(element) === true)
        {
            IsRightInput(element);
            name = true;
            return;

        } else
        {
            name = false;
            IsWrongInupt(element);
        }
    }
}
function validateName(input)
{

    var Validateformat1 = /^([a-zA-Z]+\s)*[a-zA-Z]+$/;
    var Validateformat2 = /^([\u0600-\u06FF]+\s)*[\u0600-\u06FF]+$/;
    var Name = document.getElementById(input).value;
    if (Validateformat1.test(Name) || Validateformat2.test(Name))
    {
        return true;
    } else
    {
        return false;
    }

}

function AmountNotMatch(element)
{
    var ValAmount = document.getElementById(element).value;
    if (ValAmount === '')
    {
        Amount = false;
        onBlurfunction(element);
    }
    else
    {
        if (parseFloat(ValAmount) > 0 && isNaN(ValAmount) === false)
        {
            IsRightInput(element);
            Amount = true;
            return;

        } else
        {
            Amount = false;
            document.getElementById('AboutAmount').style.visibility = 'visible';
            IsWrongInupt(element);
        }
    }
}
function ThePhoneNotMatch(element)
{
    var phone = document.getElementById(element);
    var Valphone = document.getElementById(element).value;

    if (Valphone === '')
    {
        phoneno = false;
        onBlurfunction(element);
    } else {
        var result = validatePhoneNo(element);
        if (result === false)
        {
            phoneno = false;
            IsWrongInupt(element);
        } else
        {
            phoneno = true;
            IsRightInput(element);
            return;
        }
    }
}
function validatePhoneNo(input)
{
    var validformat = /^01\d{9}$/i;
    var phoneno = document.getElementById(input).value;
    if (validformat.test(phoneno))
    {
        return true;
    } else
    {
        document.getElementById('AboutPhoneNumber').style.visibility = 'visible';
        return false;
    }
}
function onBlurfunction(ele)
{
    var ID = document.getElementById(ele);
    var val = document.getElementById(ele).value;
    if (val === '') {
        console.log(val);
        document.getElementById(ele).style.borderColor = "red";
    } else {
        document.getElementById(ele).style.borderColor = "yellow";
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
function SubmitButton()
{
//    alert(document.getElementById("donationValue").value);
    if (name === false)
    {
        var element = document.getElementById("DonatorName");
        element.scrollIntoView({block: "start"});
//        alert(document.getElementById("donationValue").value);
        return false;
    }
    if (phoneno === false)
    {
        var element = document.getElementById("DonatorPhone");
        onBlurfunction("DonatorPhone");
        element.scrollIntoView({block: "start"});
        if (Amount === false)
        {
            onBlurfunction("donationValue");
        }
        return false;
    }
    if (Amount === false)
    {
        var element = document.getElementById("donationValue");
//        alert(document.getElementById("donationValue").value);
        onBlurfunction("donationValue");
        element.scrollIntoView({block: "start"});
        if (phoneno === false)
        {
            onBlurfunction("DonatorPhone");
        }
        return false;
    }
    return true;
}
function SubmitButtonPhone()
{
  
  
    return phoneno;
}
