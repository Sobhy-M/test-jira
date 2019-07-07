/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var AreaOfCoverage = false,
        InsurancePeriod = false, StartDate = false, FirstName = false,
        MidName = false, LastName = false, PhoneNo = true, BirthDate = false,
        Gender = false, PassportNo = false, address = false, city = false;

function onloadFunction()
{
    document.getElementById("InsuranceForm").reset();
}
function daysInMonth(month, year) {
    return new Date(year, (month + 1), 0).getDate();
}
function calculateEndPeriod() {
    var ThestartDate = document.getElementById("StartDate").value;
    var noDays = document.getElementById("noDays").value;
    var period = noDays.substr(0, noDays.indexOf(" "));
    var result;
    var currentMonthDays, ForwardMonthDays;
    var selectedDay;
    var NewDate;
    if (noDays.includes('D'))
    {
        period = parseInt(noDays) + 1;
        result = new Date(ThestartDate);
        result.setDate(result.getDate() + period);
    }
    else if (noDays.includes('M'))
    {
        period = parseInt(noDays);
        ThestartDate = new Date(ThestartDate);
        selectedDay = ThestartDate.getDate();
        currentMonthDays = daysInMonth(ThestartDate.getMonth(), ThestartDate.getFullYear());

        if (currentMonthDays > 28 && selectedDay > 28)
        {
            NewDate = new Date(ThestartDate);
            NewDate.setDate(ThestartDate.getDate() + (-10));
            result = new Date(NewDate.setMonth(NewDate.getMonth() + period));
            ForwardMonthDays = daysInMonth(result.getMonth(), result.getFullYear());
            result.setDate(ForwardMonthDays);
            result.setDate(result.getDate() + 1);
        }
        else if (currentMonthDays === 28 && selectedDay === 28)
        {
            NewDate = new Date(ThestartDate);
            result = new Date(NewDate.setMonth(NewDate.getMonth() + period));
            ForwardMonthDays = daysInMonth(result.getMonth(), result.getFullYear());
            result.setDate(ForwardMonthDays);
            result.setDate(result.getDate() + 1);

        }
        else
        {
            result = new Date(ThestartDate.setMonth(ThestartDate.getMonth() + period));
            result.setDate(result.getDate() + 1);
        }
    }
    else
    {
        period = parseInt(noDays);
        result = new Date(ThestartDate);
        result.setDate(result.getDate() + 1);
        result.setYear(result.getFullYear() + period);
    }
    var newdate = new Date(result).toISOString().substr(0, 10);
//                ShowDate(document.getElementById("StartDate").value.substr(0, 10),newdate);
    var parts = newdate.split('-');
    document.getElementById("endDate").value = parts[1] + '/' + parts[2] + '/' + parts[0];
}
//            function ShowDate(start , end)
//            {
//                var Startparts = start.split('-');
//                document.getElementById("StartDate").value = Startparts[2] + '/' + Startparts[1] + '/' + Startparts[0];
//            }
function calculateAge() {
    var dateOfBirth = new Date(document.getElementById("BirthDate").value);
    var dateToCalculate = new Date();
    var calculateYear = dateToCalculate.getFullYear();
    var calculateMonth = dateToCalculate.getMonth();
    var calculateDay = dateToCalculate.getDate();
    var birthYear = dateOfBirth.getFullYear();
    var birthMonth = dateOfBirth.getMonth();
    var birthDay = dateOfBirth.getDate();
    var age = calculateYear - birthYear;
    var ageMonth = calculateMonth - birthMonth;
    var ageDay = calculateDay - birthDay;
    if (ageMonth < 0 || (ageMonth === 0 && ageDay < 0)) {
        age = parseInt(age) - 1;
    }
    document.getElementById("age").value = age;
}
function validateCity(input)
{
    var ID = document.getElementById(input);
    if ($(ID).val() === '') {
        city = false;
        $(ID).css('border-color', 'red');
    } else {
        city = true;
        $(ID).css('border-color', '');
    }
}
//            function validateCertificationNo(input)
//            {
//                var ID = document.getElementById(input);
//                if ($(ID).val() === '') {
//                    CertificateNumber = false;
//                    $(ID).css('border-color', 'red');
//                } else {
//                    CertificateNumber = true;
//                    $(ID).css('border-color', '');
//                }
//            }
function validateAreaOfCover(input)
{
    var ID = document.getElementById(input);
    if ($(ID).val() === '') {
        AreaOfCoverage = false;
        $(ID).css('border-color', 'red');
    } else {
        AreaOfCoverage = true;
        $(ID).css('border-color', '');
    }
}
function validatePeriod(input)
{
    var ID = document.getElementById(input);
    if ($(ID).val() === '') {
        InsurancePeriod = false;
        $(ID).css('border-color', 'red');
    } else {
        InsurancePeriod = true;
        $(ID).css('border-color', '');
    }
}
function StartDateNotMatch(element)
{
    var date = document.getElementById(element);
    if ($(date).val() === '')
    {
        onBlurfunction(date);
    } else
    {
        var result = validateDate(element);
        if (result === false)
        {
            StartDate = false;
            IsWrongInupt(element);
        } else
        {
            StartDate = true;
            IsRightInput(element);
            return;
        }
    }
}
function FirstNameNotMatch(element)
{
    var name = document.getElementById(element);
    if ($(name).val() === '')
    {
        onBlurfunction(name);
    } else
    {
        var result = validateName(element);
        if (result === false)
        {
            FirstName = false;
            IsWrongInupt(element);
        } else
        {
            FirstName = true;
            IsRightInput(element);
            return;
        }
    }
}
function MidNameNotMatch(element)
{
    var name = document.getElementById(element);
    if ($(name).val() === '')
    {
        onBlurfunction(name);
    } else
    {
        var result = validateName(element);
        if (result === false)
        {
            MidName = false;
            IsWrongInupt(element);
        } else
        {
            MidName = true;
            IsRightInput(element);
            return;
        }
    }
}
function LastNameNotMatch(element)
{
    var name = document.getElementById(element);
    if ($(name).val() === '')
    {
        onBlurfunction(name);
    } else
    {
        var result = validateName(element);
        if (result === false)
        {
            LastName = false;
            IsWrongInupt(element);
        } else
        {
            LastName = true;
            IsRightInput(element);
            return;
        }
    }
}
function PhoneNotMatch(element)
{
    var phone = document.getElementById(element);
    if ($(phone).val() === '')
    {
        onBlurfunction(element);
    } else {
        var result = validatePhoneNo(element);
        if (result === false)
        {
            PhoneNo = false;
            IsWrongInupt(element);
        } else
        {
            PhoneNo = true;
            IsRightInput(element);
            return;
        }
    }
}
function BirthDateDateNotMatch(element)
{
    var date = document.getElementById(element);
    if ($(date).val() === '')
    {
        onBlurfunction(date);
    } else
    {
        var result = validateDate(element);
        if (result === false)
        {
            BirthDate = false;
            IsWrongInupt(element);
        } else
        {
            BirthDate = true;
            IsRightInput(element);
            return;
        }
    }
}

function AddressNotMatch(element)
{
    var TheAddress = document.getElementById(element);
    if ($(TheAddress).val() === '')
    {
        onBlurfunction(element);
    } else
    {
        var result = ValidateAddress(element);
        if (result === false)
        {
            address = false;
            IsWrongInupt(element);
        } else
        {
            address = true;
            IsRightInput(element);
            return;
        }
    }
}

function validateGender(input)
{
    var ID = document.getElementById(input);
    if ($(ID).val() === '') {
        Gender = false;
        $(ID).css('border-color', 'red');
    } else {
        Gender = true;
        $(ID).css('border-color', '');
    }
}
function PassportNoNotMatch(element)
{
    var Passport = document.getElementById(element);
    if ($(Passport).val() === '')
    {
        onBlurfunction(Passport);
    } else
    {
        var result = ValidatePassportNo(element);
        if (result === false)
        {
            PassportNo = false;
            IsWrongInupt(element);
        } else
        {
            PassportNo = true;
            IsRightInput(element);
            return;
        }
    }
}
function ValidatePassportNo(input) {
    var passportNo = document.getElementById(input).value;
    var Validateformat = /^[a-z0-9]+$/i;
    if (Validateformat.test(passportNo))
    {
        return true;
    } else
    {
        return false;
    }
}
function validateName(input)
{

    var Validateformat = /^([A-Z]){1,15}$/;
    var Name = document.getElementById(input).value;
    if (Validateformat.test(Name))
    {
        return true;
    } else
    {
        var ID1 = document.getElementById('AboutNames');
        ID1.style.visibility = 'visible';
        return false;
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

        var ID1 = document.getElementById('AboutPhoneNo');
        ID1.style.visibility = 'visible';
        return false;
    }
}
function onBlurfunction(ele)
{
    var ID = document.getElementById(ele);
    if ($(ID).val() === '') {
        $(ID).css('border-color', 'red');
    } else {
        $(ID).css('border-color', '');
    }
}
function validateDate(input)
{
    var validformat = /^\d{2}\/\d{2}\/\d{4}$/; //Basic check for format validity
    var date = document.getElementById(input).value;
    if (validformat.test(date))
    {
        return true;
    } else
    {
        return false;
    }
}
function ValidateAddress(input)
{
    //var validformat = /[\u0600-\u06FF\u2150-\u218F]$/; //Basic check for format validity                
    var validformat = /^[\u0621-\u064A0-9 ]+$/; //Basic check for format validity
    var TheAddress = document.getElementById(input).value;
    if (validformat.test(TheAddress))
    {
        return true;
    } else
    {
        return false;
    }
}
function IsWrongInupt(input)
{
    var ID = document.getElementById(input);
    ID.style.backgroundColor = "yellow";
    ID.style.color = "red";
}
function IsRightInput(input)
{
    var ID = document.getElementById(input);
    ID.style.backgroundColor = "";
    ID.style.color = "";
}
$(function () {
    var dateToCalculate = new Date();
    var calculateYear = dateToCalculate.getFullYear();
    var minYear = calculateYear - 85;
    var calculateMonth = dateToCalculate.getMonth();
    var calculateDay = dateToCalculate.getDate();
    $("#StartDate").datepicker({
        dateFormat: 'mm/dd/yy',
        minDate: new Date(calculateYear, calculateMonth, calculateDay),
        changeYear: true,
        changeMonth: true,
        showMonthAfterYear: true
    });
    $("#BirthDate").datepicker({
        dateFormat: 'mm/dd/yy',
        yearRange: "-100:$(calculateYear)",
        minDate: new Date(minYear, calculateMonth, calculateDay),
        maxDate: new Date(calculateYear, calculateMonth, calculateDay - 1),
        changeYear: true,
        changeMonth: true,
        showMonthAfterYear: true

    });
});
function SubmitButton()
{
//                if (CertificateNumber === false)
//                {
//                    var element = document.getElementById("certificationNoDiv");
//                    element.scrollIntoView({block: "start"});
//                    return false;
//                }
    if (AreaOfCoverage === false)
    {
        var element = document.getElementById("AreaOfCover");
        element.scrollIntoView({block: "start"});
        return false;
    }
    if (InsurancePeriod === false)
    {
        var element = document.getElementById("noDays");
        element.scrollIntoView({block: "start"});
        return false;
    }
    if (StartDate === false)
    {
        var element = document.getElementById("StartDate");
        element.scrollIntoView({block: "start"});
        return false;
    }
    if (FirstName === false)
    {
        var element = document.getElementById("FirstName");
        element.scrollIntoView({block: "start"});
        return false;
    }
    if (MidName === false)
    {
        var element = document.getElementById("MiddleName");
        element.scrollIntoView({block: "start"});
        return false;
    }
    if (LastName === false)
    {
        var element = document.getElementById("LastName");
        element.scrollIntoView({block: "start"});
        return false;

    }
    if (PhoneNo === false)
    {
        var element = document.getElementById("phone");
        element.scrollIntoView({block: "start"});
        return false;
    }
    if (BirthDate === false)
    {
        var element = document.getElementById("BirthDate");
        element.scrollIntoView({block: "start"});
        return false;
    }
    if (Gender === false)
    {
        var element = document.getElementById("Gender");
        element.scrollIntoView({block: "start"});
        return false;
    }
    if (PassportNo === false)
    {
        var element = document.getElementById("passportNo");
        element.scrollIntoView({block: "start"});
        return false;
    }
    if (address === false)
    {
        var element = document.getElementById("Address");
        element.scrollIntoView({block: "start"});
        return false;
    }
    if (city === false)
    {
        var element = document.getElementById("City");
        element.scrollIntoView({block: "start"});
        return false;
    }
    return true;
}