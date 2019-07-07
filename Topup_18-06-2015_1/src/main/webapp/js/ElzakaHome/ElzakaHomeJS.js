/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var  phoneno = false;

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
    var validformat =/^010\d{8}$/i ;
    var validformat2 =/^012\d{8}$/i ;
    var validformat3 =/^011\d{8}$/i ;
    var phoneno = document.getElementById(input).value;
    if (validformat.test(phoneno) || validformat2.test(phoneno) || validformat3.test(phoneno) )
    {
        return true;
    } else
    {
        return false;
    }
}
function onBlurfunction(ele)
{
    var ID = document.getElementById(ele);
    var val = document.getElementById(ele).value;
    if (val === '') {
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
    if (phoneno === false)
    {
        return false;
    }
    return true;
}
