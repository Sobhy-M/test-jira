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


function validateReferenceNum() {
	
	 var validformat = /^[0-9]+$/;
    var val = document.getElementById("ReferenceNumber").value;
    if (validformat.test(val)) {
 
        return true;
    }
    else {
       
        return false;
       
    }
}


function formSubmit()
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
    
    
    
    
    if (validateReferenceNum() === false)
    {
        IsWrongInupt("ReferenceNumber");
        document.getElementById("ReferenceNumber").scrollIntoView({block: "start"});
        $("#ReferenceNumber").notify("برجاء التأكد من الرقم ثم اعادة المحاولة");
        return false;
    } else {
        IsRightInput("ReferenceNumber");

    }
    return true;



}