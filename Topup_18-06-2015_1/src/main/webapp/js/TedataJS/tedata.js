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
    var validformat = /^0[0-9]*$/;
    var phoneno = document.getElementById("SubscriberNumber").value;
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
    var ValAmount = document.getElementById("amount").value;
    if (ValAmount === '')
    {
        return  false;
    } 
   
    return true;
}

function formSubmit()
{
    if (validatePhoneNo() === false)
    {
        IsWrongInupt("SubscriberNumber");
        document.getElementById("SubscriberNumber").scrollIntoView({block: "start"});
        $("#SubscriberNumber").notify("من فضلك أدخل الرقم  صحيح");
        return false;
    } else {
        IsRightInput("SubscriberNumber");
    }
    if (validateAmount2() === false)
    {
        IsWrongInupt("amount");
        document.getElementById("amount").scrollIntoView({block: "start"});
        $("#amount").notify("من فضلك اختر القيمة من القائمة");
        return false;
    } else {
        IsRightInput("amount");
    }


    return true;


}