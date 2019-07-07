<%-- 
    Document   : LongLiveEgyptDonation
    Created on : Mar 13, 2016, 3:44:08 PM
    Author     : Masary
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
%>
<!DOCTYPE html>
<html>
    <head>
        <script src="./js/BCFEJS/BcfeControllerJS.js"></script>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
        <title><%=CONFIG.getBcfeDescription(session)%></title>
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
        
        <form action="BcfeConfirmationController" id="DonationForm" method="POST" style="font-weight: bold">
             <input type="hidden" name="action" value="<%=CONFIG.ACTION_BCFE_Confirmation%>" />
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <th colspan="3" style="text-align: center"><p><%=CONFIG.getBcfeDescription(request.getSession())%></p></th>
                </tr>
                <tr>
                    <td style="border: none">
                        <p><%= CONFIG.getDonorname(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <input name="DonatorName" id="DonatorName" autofocus="true" autocomplete="off" maxlength="100" type="text" onblur="NameNotMatch(this.id)">
                    </td>
                </tr>
                <tr>
                     <td style="border: none">
                        <p><%= CONFIG.getDontaorPhoneNumber(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <div class="requriedclass">
                            <div>
                                <input name="DonatorPhone"  autocomplete="off" type="text" id="DonatorPhone" maxlength="11" title="<%=CONFIG.DonatorPhoneTitle(session)%>"  onblur="ThePhoneNotMatch(this.id);" >
                            </div>
                        </div>
                    </td>
                     <td style="border: none">
                       <img name="AboutPhoneNumber" style="visibility: hidden;cursor: pointer" id="AboutPhoneNumber" class="AboutPhoneNumber" title="<%=CONFIG.getAboutPhoneNo(session)%>" src="./img/help.png">
                     </td>
                </tr>
                <tr>
                     <td style="border: none">
                        <p><%= CONFIG.getDonationValue(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <div class="requriedclass">
                            <div>
                                <input name="donationValue" autocomplete="off" id="donationValue" type="text" required title="<%=CONFIG.getElzakaHomeDonationValTitle(session)%>" onblur="AmountNotMatch(this.id);" >
                            </div>
                        </div>
                    </td>
                    <td style="border: none">
                       <img name="AboutAmount" style="visibility: hidden;cursor: pointer" id="AboutAmount" class="AboutAmount" title="<%=CONFIG.getElzakaHomeDonationValTitle(session)%>" src="./img/help.png">
                     </td>
                </tr>
                <tr>
                    <td colspan="3" class="secondTD">
                        <input type="submit" value="<%=CONFIG.getConfirm(session)%>"  id="submitbutton" onclick="return SubmitButton()"  />
                    </td>
                </tr>
            </table>

        </form>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
