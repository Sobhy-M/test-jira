<%-- 
    Document   : AddNewTravelPolisy
    Created on : Nov 17, 2015, 11:34:53 AM
    Author     : Aya
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page import="com.masary.database.manager.MasaryManager , java.io.* , org.apache.commons.fileupload.* , org.apache.commons.fileupload.disk.*,org.apache.commons.fileupload.servlet.*" %>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
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
    double resultArr[] = MasaryManager.getInstance().getAmount(session.getAttribute("AreaOfCover").toString(), session.getAttribute("PeriodOfInsurance").toString(), Integer.parseInt(session.getAttribute("age").toString()));
    DecimalFormat df = new DecimalFormat("##.##");
    Double netamount = (resultArr[0] + (resultArr[0] * .02));
    Double Fees = Double.parseDouble(df.format(resultArr[0] * .02));
    netamount = Double.parseDouble(df.format(netamount));
%>
<link href="../../img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getNewTravelPolisy(request.getSession())%></title>
        <script type="text/javascript">

            function onloadFunction(){
                document.getElementById("ConfirmationForm").reset();
            }
            
            function disableDoubleSubmission(){
                document.getElementById("submitbutton").disabled = true;
            }
        </script>
    </head>

    <body onload="onloadFunction()">
        <style>
            .secondTD{
                text-align: center;
                margin-left: auto;
                margin-right: auto;
            }
        </style>
        <script type="text/javascript" src="../../img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form action="NewInsuranceController" method="POST" id="ConfirmationForm" onload="onloadFunction()" onsubmit="disableDoubleSubmission()" enctype="multipart/form-data">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_RESULT_Insurance%>" >
        <input type="hidden" name="age" id="age" value="<%= session.getAttribute("age")%>" />
        <fieldset style="width: 55%; direction: rtl;" align="right">  
            <legend align="right" ><font size="5"><%= CONFIG.getConfirm(session)%> <%=CONFIG.getNewTravelPolisy(request.getSession())%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend> 
            <table>
                <tr><th style="text-align: center" colspan="3"><%=CONFIG.getNewPolisy(request.getSession())%></th></tr>
                <tr>
                    <td class="secondTD">
                        <p><%= CONFIG.getAreaOfCover(session)%>:</p>
                        <input  readonly  value="<%= session.getAttribute("AreaOfCover")%> " name="AreaOfCover" style="float: left;background-color: #EDEDED;"/>
                    </td>
                    <td class="secondTD">
                        <p><%= CONFIG.getPeriodOfInsurance(session)%>:</p> 
                        <input value="<%= session.getAttribute("PeriodOfInsurance")%> " name="PeriodOfInsurance"  readonly style="float: left;background-color: #EDEDED;" id="noDays" />
                    </td>
                    <td class="secondTD">
                        <p><%= CONFIG.getStart(session)%>:</p>
                        <input type="text" id="StartDate" readonly style="float: left;background-color: #EDEDED;" value="<%= session.getAttribute("StartDate")%>" name="StartDate">
                    </td>
                </tr>
                <tr>
                    <td class="secondTD">
                        <p><%= CONFIG.getEnd(session)%>:</p>
                        <input type="text" id="endDate" readonly style="float: left;background-color: #EDEDED;" value="<%= session.getAttribute("endDate")%>" name="endDate">
                    </td>
                    <td class="secondTD">
                        <p><%= CONFIG.getNetAmount(session)%>:</p>
                        <input type="text" id="NetAmount" readonly style="float: left;background-color: #EDEDED;"  name="NetAmount" value="<%=resultArr[1]%> "  >
                    </td>
                   
                    <td class="secondTD">
                        <p><%= CONFIG.getTaxes(session)%>:</p>
                        <input type="text" id="Taxes" readonly style="float: left;background-color: #EDEDED;"  name="Taxes" value="<%= resultArr[2]%>" >
                    </td>
                </tr>
                <tr>
                     <td class="secondTD">
                        <p><%= CONFIG.getDocumentGrossAmount(session)%>:</p>
                        <input type="text" id="amount" readonly style="float: left;background-color: #EDEDED;" name="amount" value="<%= resultArr[0]%>">
                    </td>
                    <td class="secondTD">
                        <p><%=CONFIG.getServiceCost(session)%>:</p>
                        <input type="text" id="ServiceCost" readonly style="background-color: #EDEDED;" name="ServiceCost" value="<%=Fees%>">
                    </td>
                     <td class="secondTD">
                        <p><%= CONFIG.getGrossamount(session)%>:</p>
                        <input type="text" id="Finalamount" readonly style="background-color: #EDEDED;" name="Finalamount" value="<%=netamount%>">
                    </td>                                                                              
                </tr>
                <tr class="secondTD" ><th style="text-align: center" colspan="3"> <%=CONFIG.getAccountInformation(request.getSession())%></th></tr>
                <tr>
                    <td class="secondTD"> 
                        <p><%= CONFIG.getFirstName(session)%>:</p>
                        <input  type="text" readonly style="float: left; background-color: #EDEDED;" name="FirstName" value="<%= session.getAttribute("FirstName")%>"/>
                    </td>
                    <td class="secondTD"> 
                        <p><%= CONFIG.getMiddleName(session)%>:</p>
                        <input type="text" readonly style="float: left;background-color: #EDEDED;" name="MiddleName" value="<%= session.getAttribute("MiddleName")%>"/>
                    </td>
                    <td class="secondTD">
                        <p><%= CONFIG.getLastName(session)%>:</p>
                        <input type="text" readonly style="float: left;background-color: #EDEDED;" name="LastName" value="<%= session.getAttribute("LastName")%>"/>
                    </td>
                </tr>
                <tr>
                    <td class="secondTD"> 
                        <p><%= CONFIG.getPassPortNumber(session)%>:</p>
                        <input type="text" readonly style="float: left; background-color: #EDEDED;" id="passportNo" name="PassPortNumber"  value="<%= session.getAttribute("PassPortNumber")%>" />
                    </td>
                    <td class="secondTD">
                        <p><%= CONFIG.getBirthDate(session)%>:</p>
                        <input type="text" readonly style="float: left;background-color: #EDEDED;" name="BirthDate" value="<%= session.getAttribute("BirthDate")%>"/>
                    </td>
                    <td class="secondTD"> 
                        <p><%= CONFIG.getGender(session)%>:</p>
                        <input type="test" readonly style="float: left;background-color: #EDEDED;" name="Gender" value="<%= session.getAttribute("Gender")%>"/>
                    </td>
                <tr>
                    <td colspan="3">
                        <div class="secondTD">
                            <input type="submit" value="<%=CONFIG.getGo(session)%>" id="submitbutton" />
                        </div>
                    </td>
                </tr>
            </table>
            <input  type="hidden" id="Address" name="Address" value="<%= session.getAttribute("Address")%>"/>
            <input type="hidden" id="City" name="City" value="<%= session.getAttribute("City")%>" />
            <input type="hidden" id="phone" name="phone" value="<%=session.getAttribute("phone")%>"/>
        </fieldset>
    </form>
</body>
</html>