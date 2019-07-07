<%-- 
    Document   : AddNewTravelPolisy
    Created on : Nov 17, 2015, 11:34:53 AM
    Author     : Aya
--%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@ page import="com.masary.database.manager.MasaryManager , java.io.* , org.apache.commons.fileupload.* , org.apache.commons.fileupload.disk.*,org.apache.commons.fileupload.servlet.*" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    String custId = null;
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="./css/Tables.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="./js/InsuranceGIGJS/GIGJS.js"></script>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
        <title><%=CONFIG.getNewTravelPolisy(request.getSession())%></title>
        <link href="./img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css"/>
    </head>
    <body onload="onloadFunction()">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form action="NewInsuranceController" id="InsuranceForm" method="POST"  style="font-size: 25;font-weight: bold;" enctype="multipart/form-data">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_Make_CONFIRMATION_Insurance%>" />
        <input type="hidden" name="age" id="age" />
        <legend align="right" ><%=CONFIG.getNewTravelPolisy(request.getSession())%></legend> 
        <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
            <tr>
                <th colspan="2" style="text-align: center"><p><%=CONFIG.getNewPolisy(request.getSession())%></p></th>
            </tr>
            <tr>
                <td colspan="2">
                    <table  class="subtable">
                        <tr>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <p> <%= CONFIG.getAreaOfCover(session)%>:</p>
                            </td>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <div class="requriedclass">
                                    <div>
                                        <select  name="AreaOfCover" id="AreaOfCover" required="required" title="<%=CONFIG.getAriaOFCoverTitle(request.getSession())%>" tabindex="2" onblur="validateAreaOfCover(this.id)" onchange="validateAreaOfCover(this.id)">  <option value=""><%=CONFIG.getLocationselection(request.getSession())%></option>
                                            <%for (int i = 0; i < MasaryManager.getInstance().getAreas().size(); i++) {%>
                                            <option><%=MasaryManager.getInstance().getAreas().get(i)%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <p><%= CONFIG.getPeriodOfInsurance(session)%>:</p>
                            </td>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <div class="requriedclass">
                                    <div>
                                        <select id="noDays" name="PeriodOfInsurance" tabindex="3" onchange="calculateEndPeriod(), validatePeriod(this.id)"  required title="<%=CONFIG.getPeriodOfInsuranceTitle(request.getSession())%>"  onblur="validatePeriod(this.id)"> 
                                            <option value=""><%= CONFIG.getselectionPeriodOFArr(request.getSession())%></option> 
                                            <%for (int i = 0; i < MasaryManager.getInstance().getPeriods().size(); i++) {%>
                                            <option><%=MasaryManager.getInstance().getPeriods().get(i)%> </option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <table class="subtable" style="padding: 0px">
                        <tr style="height: border-box">
                            <td style="border: none ; background-color: transparent ; border-collapse: collapse; padding: 0px">
                                <p><%= CONFIG.getStart(session)%>:</p>
                            </td>
                            <td style="border: none ; background-color: transparent;">
                                <div class="requriedclass">
                                    <div>
                                        <input type="text" id="StartDate" name="StartDate" autocomplete="off" required title="<%=CONFIG.getStartDateTitle(request.getSession())%>"  value=""
                                               onchange="onBlurfunction(this.id), calculateEndPeriod(), StartDateNotMatch(this.id)" 
                                               onblur="onBlurfunction(this.id), StartDateNotMatch(this.id)"/>
                                    </div>
                                </div>
                            </td>    
                            <td style="border: none ; background-color: transparent ; border-collapse: collapse; padding: 0px">
                                <img name="AboutStartDate" class="AboutStartDate" src="./img/help.png" title="<%=CONFIG.getAboutStatrDate(session)%>" style="cursor: pointer">
                            </td>
                        </tr>
                        <tr>
                            <td style="border: none ; background-color: transparent;border-collapse: collapse; padding: 0px">
                                <p><%= CONFIG.getEnd(session)%>:</p>
                            </td>
                            <td style="border: none ; background-color: transparent">
                                <div class="requriedclass">
                                    <div>
                                        <input type="text" id="endDate" readonly name="endDate" />
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr> 
                <th colspan="2" style="text-align: center"><p><%=CONFIG.getInsuredPersonInfo(request.getSession())%></p></th>
            </tr> 
            <tr>
                <td>
                    <table class="subtable">
                        <tr>
                            <td style="border: none ; background-color: transparent;padding: 0px">
                                <p><%= CONFIG.getFirstName(session)%>:</p>
                            </td>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <div class="requriedclass">
                                    <div>
                                        <input type="text" autocomplete="off" id="FirstName" maxlength="15" name="FirstName" required title="<%=CONFIG.getFirstNameTitle(request.getSession())%>" onchange="FirstNameNotMatch(this.id)" onblur="FirstNameNotMatch(this.id), onBlurfunction(this.id)">
                                    </div>
                                </div>
                            </td>
                            <td style="border: none ; background-color: transparent">
                                <img name="AboutNames" style="visibility: hidden;cursor: pointer" id="AboutNames" class="AboutNames" title="<%=CONFIG.getAboutNames(session)%>" src="./img/help.png">
                            </td>
                        </tr>
                        <tr>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <p><%= CONFIG.getMiddleName(session)%>:</p>
                            </td>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <div class="requriedclass">
                                    <div>
                                        <input type="text" autocomplete="off" id="MiddleName" maxlength="15" name="MiddleName" required title="<%=CONFIG.getMiddleNameTitle(request.getSession())%>" onchange="MidNameNotMatch(this.id)" onblur="MidNameNotMatch(this.id), onBlurfunction(this.id)">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <p><%= CONFIG.getLastName(session)%>:</p>
                            </td>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <div class="requriedclass">
                                    <div>
                                        <input type="text" autocomplete="off"  name="LastName" maxlength="15" id="LastName" required title="<%=CONFIG.getLastNameTitle(request.getSession())%>" onchange="LastNameNotMatch()(this.id)" onblur="LastNameNotMatch(this.id), onBlurfunction(this.id)">
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <table class="subtable" style="padding: 0">
                        <tr>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <p><%=CONFIG.getThePhoneNumber(session)%>:</p>                                
                            </td>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <div class="requriedclass">
                                    <div>
                                        <input type="text" autocomplete="off" maxlength="11" name="phone" id="phone" class="phone"  title="<%=CONFIG.getPhonrNoTitle(session)%>" onblur="PhoneNotMatch(this.id)">
                                    </div>
                                </div>
                            </td>
                            <td style="border: none ; background-color: transparent">
                                <img style="visibility: hidden;cursor: pointer" class="AboutPhoneNo" id="AboutPhoneNo" title="<%=CONFIG.getAboutPhoneNo(session)%>" src="./img/help.png">
                            </td>
                        </tr>
                        <tr>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <p><%= CONFIG.getBirthDate(session)%>:</p>
                            </td>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <div class="requriedclass">
                                    <div>
                                        <input type="text" autocomplete="off" name="BirthDate" id="BirthDate" required title="<%=CONFIG.getBirthDateTitle(request.getSession())%>" 
                                               value="" onchange="onBlurfunction(this.id), calculateAge(), BirthDateDateNotMatch(this.id)"
                                               onblur="onBlurfunction(this.id), BirthDateDateNotMatch(this.id)">
                                    </div>
                                </div>
                            </td>
                            <td style="border: none ; background-color: transparent">
                                <img class="AboutBirthDate" title="<%=CONFIG.getAboutBirthDateTitle(session)%>" src="./img/help.png" style="cursor: pointer">
                            </td>
                        </tr>
                        <tr>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <p><%=CONFIG.getAddress(session)%>:</p>
                            </td>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <div class="requriedclass">
                                    <div>
                                        <input type="text" autocomplete="off"  name="Address" maxlength="50" id="Address" required title="<%=CONFIG.getAddressTitle(request.getSession())%>" onchange="AddressNotMatch(this.id),onBlurfunction(this.id)" onblur="AddressNotMatch(this.id), onBlurfunction()">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <p><%= CONFIG.getPassPortNumber(session)%>:</p>
                            </td>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <div class="requriedclass">
                                    <div>
                                        <input type="text" autocomplete="off" maxlength="30" name="PassPortNumber"  required title="<%=CONFIG.getPassportNoTitle(request.getSession())%>" onchange="PassportNoNotMatch(this.id);" id="passportNo" onblur="PassportNoNotMatch(this.id), onBlurfunction(this.id)">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <p><%=CONFIG.getTheCity(session)%>:</p>
                            </td>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <div class="requriedclass">
                                    <div>
                                        <select id="City" name="City"  required title="<%=CONFIG.getTheCityTitle(request.getSession())%>" onchange="validateCity(this.id)" onblur="validateCity(this.id)"> 
                                            <option value=""><%=CONFIG.getGovernorate(request.getSession())%></option> 
                                            <%for (String gov : MasaryManager.getInstance().getGovernorates("AR", 63).values()) {%>
                                            <option><%=gov%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                            </td>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <p><%= CONFIG.getGender(session)%>:</p>
                            </td>
                            <td style="border: none ; background-color: transparent; padding: 0px">
                                <div class="requriedclass">
                                    <div>
                                        <select required="true" id="Gender" name="Gender" required title="<%=CONFIG.getGenderTitle(request.getSession())%>" onchange="validateGender(this.id)" onblur="validateGender(this.id), onBlurfunction(this.id)"> 
                                            <option value=""><%=CONFIG.getgenderselection(request.getSession())%></option>
                                            <option>Male</option><option>Female</option></select>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <th colspan="2">
            <div class="secondTD">
                <input type="submit" value="<%=CONFIG.getConfirm(session)%>"  id="submitbutton" onclick="return SubmitButton()"  />
            </div>
            </th>
            </tr>
        </table>
    </form>
</div>
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>