
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

function validateNationalId()
{
    var validformat = /^\d+$/;
    var nationId = document.getElementById("nationalId").value;
    if (validformat.test(nationId) === true)
    {
    	  if (nationId.length !== 14) {
    	        return false;
    	    }else
    	    {
    	        return true;
    	    }
    
    } else
    {
        return false;
    }
    
 
}

function validateAmount()
{
    var validformat = /^\d+$/;
    var amount1 = document.getElementById("amount").value;
    if (validformat.test(amount1) === true)
    {
    	  if (amount1.length > 9) {
    	        return false;
    	    }else
    	    {
    	        return true;
    	    }
    
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
    } else
    {
            return true;
        
    }
}

function validateCompanies()
{
    var ValAmount = document.getElementById("inputList").value;
    if (ValAmount === '')
    {
        return  false;
    } else
    {
            return true;
        
    }
}


function formSubmit()
{
    if (validateNationalId() === false)
    {
        IsWrongInupt("nationalId");
        document.getElementById("nationalId").scrollIntoView({block: "start"});
        $("#nationalId").notify("رقم البطاقة يجب ان يكون 14 رقم");
        return false;
    } else {
        IsRightInput("nationalId");
    }
    if (validateAmount2() === false)
    {
        IsWrongInupt("amount");
        document.getElementById("amount").scrollIntoView({block: "start"});
        $("#amount").notify("من فضلك تأكد من إدخال القيمة الصحيحة");
        return false;
    } else {
        IsRightInput("amount");
    }

    if (validateAmount() === false)
    {
        IsWrongInupt("amount");
        document.getElementById("amount").scrollIntoView({block: "start"});
        $("#amount").notify("من فضلك تأكد من إدخال القيمة الصحيحة");
        return false;
    } else {
        IsRightInput("amount");
    }
    
    
    if (validateCompanies() === false)
    {
        IsWrongInupt("inputList");
        document.getElementById("inputList").scrollIntoView({block: "start"});
        $("#inputList").notify("من فضلك تأكد من إختيار الشركة");
        return false;
    } else {
        IsRightInput("inputList");
    }

    return true;


}