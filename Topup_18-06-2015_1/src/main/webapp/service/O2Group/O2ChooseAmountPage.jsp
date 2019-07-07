<%--
    Document   : AddCustomer.jsp
    Created on : 05/03/2017, 11:09:49 ص
    Author     : Pora
--%>

<%@page import="com.masary.integration.dto.O2groupDenominationResponse"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.masary.database.dto.Governorate"%>
<%@page import="com.masary.database.dto.Country"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    List<O2groupDenominationResponse> o2groupDenominationResponse = (List<O2groupDenominationResponse>) request.getAttribute("o2groupDenominationResponse");
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getO2Group(session)%></title>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="./js/AddNewWalletJS/AddNewWalletJS.js"></script>
        <script src="./js/AddNewWalletJS/notify.js"></script>   
    </head>
    <script>
        function setVoucherValue(selected){
            document.getElementById("VoucherValue").value = selected.options[selected.selectedIndex].text;
        }
    </script>

    <BODY class="body"  >
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>

        <form name="AddCustomer" action="O2AmountConfirmationController" method="POST">
            <input type="hidden" id="MSISDN" name="MSISDN" value="<%= request.getParameter("MSISDN")%>"/>
            <input type="hidden" id="ClientName" name="ClientName" value="<%= request.getParameter("ClientName")%>"/>
            <input type="hidden" id="VoucherValue" name="VoucherValue" value="<%= request.getParameter("ClientName")%>"/>

            <div class="content_body">
                <table>
                    <tr>
                        <td>
                            <p><%= CONFIG.getO2GroupAmount(session)%>:</p>
                        </td>

                        <td>
                            <select required name="voucherID" id="voucherID" onchange="setVoucherValue(this)">
                                <option selected value="" ><%= CONFIG.getO2GroupChooseAmount(session)%></option>
                                <%for (O2groupDenominationResponse o : o2groupDenominationResponse) {%>
                                <option value="<%=o.getDenominationID()%>"><%=o.getDenominationValue()%></option><%}%>
                            </select> 
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="<%=CONFIG.getConfirm(session)%>"  id="submitbutton" onclick="return SubmitButton()"  />
                        </td>
                        <td>
                            <input type="button" value="<%=CONFIG.getBack(session)%>"  id="submitbutton" onclick="history.go(-2)"/>
                        </td>
                    </tr>
                </table>

            </div><!-- End of Table Content area-->
        </form>
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
