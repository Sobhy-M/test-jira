<%-- 
    Document   : LegitimacyAssociationPage
    Created on : May 22, 2017, 3:56:12 PM
    Author     : Abdelsabor
--%>

<%@page import="com.masary.common.CONFIG"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String custId = null;
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    String ProjectName = "";
    int OperationID = Integer.parseInt(request.getParameter("OPERATION_ID"));
    if (OperationID == 1) {
        ProjectName = CONFIG.getLegitimacyAssociationSocityProject(session);
    }
    if (OperationID == 2) {
        ProjectName = CONFIG.getLegitimacyAssociationSadkahProject(session);
    }
    if (OperationID == 3) {
        ProjectName = CONFIG.getLegitimacyAssociationZakahProject(session);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <script src="./js/gam3yaShar3ya/gam3yaShar3ya.js"></script>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="./js/AddNewWalletJS/notify.js"></script>  
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />

        <title><%=CONFIG.getLegitimacyAssociationDescription(session)%></title>
    </head>
    <body onload="onloadFunction()">
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>

        <form action="LegitimacyAssociationConfirmationController" id="DonationForm" method="POST" style="font-weight: bold">
            <input type="hidden" name="OPERATION_ID" value="<%=request.getParameter("OPERATION_ID")%>" />
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <th colspan="3" style="text-align: center"><p><%=CONFIG.getLegitimacyAssociationDescription2(request.getSession()) + ProjectName%></p></th>
                </tr>
                <tr>
                    <td style="border: none">
                        <p><%= CONFIG.getDontaorPhoneNumber(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <div class="requriedclass">
                            <div>
                                <input name="DonatorPhone"  autocomplete="off" type="text" id="DonatorPhone" maxlength="11" title="<%=CONFIG.DonatorPhoneTitle(session)%>" >
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td style="border: none">
                        <p><%= CONFIG.getDonationValue(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <div class="requriedclass">
                            <div>
                                <input name="donationValue" autocomplete="off" id="donationValue" type="text" required title="<%=CONFIG.getElzakaHomeDonationValTitle(session)%>" >
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" class="secondTD">
                        <input type="submit" value="<%=CONFIG.getConfirm(session)%>"  id="submitbutton" onclick="return BtnSubmit()"  />
                    </td>
                </tr>
            </table>

        </form>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
