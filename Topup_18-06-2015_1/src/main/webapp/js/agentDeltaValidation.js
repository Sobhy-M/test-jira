/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var companyCodeId = "companyCode";
var repCodeId = "repCode";
var amountId = "amount";
var amountConfiromId = "amountConfirom";


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
function validateCompanyCode() {

    var companyCode = document.getElementById(companyCodeId).value;

    if (companyCode === "") {
        IsWrongInupt(companyCodeId);
        return false;
    }
    for (var i = 0; i < companyCode.length; i++) {

        if (companyCode[i] >= '0' && companyCode[i] <= '9') {
            continue;
        } else {

            IsWrongInupt(companyCodeId);
            return false;
        }
    }
    IsRightInput(companyCodeId);
    return true;
}

function validateRepCode() {

    var repCode = document.getElementById(repCodeId).value;
    if (repCode === "") {
//        alert("aefwfwf");
        IsWrongInupt(repCodeId);
        return false;
    } else {

        IsRightInput(repCodeId);
        return true;
    }

    for (var i = 0; i < repCode.length; i++) {

        if (repCode[i] >= '0' && repCode[i] <= '9') {
            continue;
        } else {

            IsWrongInupt(repCodeId);
            return false;

        }
    }

}
function validateAmount() {

    var amount = document.getElementById(amountId).value;
    var companyCode = document.getElementById("companyCode").value;
//    console.log(companyCode);
    if (amount === "") {
        document.getElementById("AmountMessage").innerHTML = 'برجاء ادخال المبلغ بالجنيه';
        IsWrongInupt(amountId);
        return false;
    } else {
        IsRightInput(amountId);
        document.getElementById("AmountMessage").innerHTML = '';
    }

    if (Number(amount) < 1000 && companyCode === "18234") {
        IsWrongInupt(amountId);
        document.getElementById("AmountMessage").innerHTML = 'برجاء ادخال المبلغ بالجنيه بحيث يكون اكبر من 1000 ';
        return false;
    }
    if ((Number(amount) < 1000 | Number(amount) > 50000) && companyCode === "18235") {
        IsWrongInupt(amountId);
        document.getElementById("AmountMessage").innerHTML = 'برجاء ادخال المبلغ بالجنيه بحيث يكون اكبر من 1000واقل من 50000';
        return false;
    }
    for (var i = 0; i < amount.length; i++) {

        if (amount[i] >= '0' && amount[i] <= '9') {
            continue;
        } else {

            IsWrongInupt(amountId);
            return false;

        }
    }
    IsRightInput(amountId);
    return true;

}
function validateAmountConfirmation() {

    var amount = document.getElementById(amountId).value;
    var amountConfirm = document.getElementById(amountConfiromId).value;


    if (amountConfirm === "") {
        IsWrongInupt(amountConfiromId);
        return false;

    }
    if (amount === "") {
        IsWrongInupt(amountId);
        return false;

    }
    for (var i = 0; i < amountConfirm.length; i++) {

        if (amountConfirm[i] >= '0' && amountConfirm[i] <= '9') {
            continue;
        } else {

            IsWrongInupt(amountConfiromId);
            return false;

        }
    }

    if (amount !== amountConfirm) {
        IsWrongInupt(amountConfiromId);
        return false;
    }
    IsRightInput(amountConfiromId);
    return true;


}
function validateBeforeSubmit() {
    if (validateRepCode() === false || validateAmount() === false || validateAmountConfirmation() === false) {
        return false;
    }
    return true;
}

