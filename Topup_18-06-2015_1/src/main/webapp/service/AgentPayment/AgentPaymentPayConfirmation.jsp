<%-- 
    Document   : AgentPaymentPayConfirmation
    Created on : Aug 22, 2016, 12:36:39 PM
    Author     : y
--%>

<%@page import="com.masary.common.CONFIG"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String fees = request.getParameter("commission");
    String amount = request.getParameter("amount");
    Double discount = Double.parseDouble(amount) - Double.parseDouble(fees);
    String companyCode = session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString();
    String rolePage = CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp";
%>
<!DOCTYPE html>
<html>
    <head>
        <title><%=CONFIG.getCompaniesProceeds(session)%></title>
    </head>

    <body>
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>
        <form action="agentPaymentPayController" id="getInfoForm" method="POST" style="font-weight: bold">
            <font style="color: red; font-size: 15px;">${ErrorCode}</font>
            <input type="hidden" name="amount" id="amount" value="<%=request.getParameter("amount")%>" />
            <table style="width: 70% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <td  style="text-align: center"><%=CONFIG.getAgentPaymentInfo(session)%></td>
                    <td  style="text-align: center"><%=CONFIG.getAgentPaymentComputeCommission(session)%></td>
                </tr>

                <tr>
                    <td>
                        <table style="width: 100%">
                            <tr>
                                <td style=" background-color: transparent;border: none">
                                    <table>
                                        <tr>
                                            <td style="border: none ; background-color: transparent">
                                                <p><%=CONFIG.getCompanyCode(session)%>:</p>
                                            </td>
                                            <td style="border: none ; background-color: transparent">
                                                <input readonly style="padding-left: auto;background-color: #EDEDED" value="<%= session.getAttribute("companyName")%>" name="companyName" id="companyName" autofocus="true" autocomplete="off" maxlength="100" type="text" onblur="NameNotMatch(this.id)">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border: none ; background-color: transparent">
                                                <p><%=CONFIG.getRepresentativeName(session)%>:</p>
                                            </td>
                                            <td style="border: none ; background-color: transparent">
                                                <input readonly style="padding-left: auto;background-color: #EDEDED" value="<%= session.getAttribute("repName")%>" name="repName" id="repName" autofocus="true" autocomplete="off" maxlength="100" type="text" onblur="NameNotMatch(this.id)">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border: none ; background-color: transparent">
                                                <% if (companyCode.equals("18234")) {%>
                                                <p><%=CONFIG.getUserAmount(session)%>:</p>
                                                <%} else {%>
                                                <p><%=CONFIG.getAgentPaymentAmountAr(session)%>:</p>
                                                <%}%>
                                            </td>
                                            <td style="border: none ; background-color:  transparent">
                                                <% if (companyCode.equals("18234")) {%>
                                                <input readonly style="padding-left: auto;background-color: #EDEDED" value="<%= request.getParameter("valueFees")%>" name="valueFees" id="amount" autofocus="true" autocomplete="off" maxlength="100" type="text" onblur="NameNotMatch(this.id)">
                                                <%} else {%>
                                                <input readonly style="padding-left: auto;background-color: #EDEDED" value="<%= session.getAttribute(CONFIG.AMOUNT)%>" name="amount1" id="amount" autofocus="true" autocomplete="off" maxlength="100" type="text" onblur="NameNotMatch(this.id)">

                                                <%}%>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                        </table>

                    </td>

                    <td style=" background-color: transparent">
                        <table>
                            <tr>
                                <td style="border: none ; background-color: transparent">
                                    <p><%=CONFIG.getAgentPaymentCommission(session)%>:</p>
                                </td>
                                <td style="border: none ; background-color: transparent">
                                    <input readonly style="padding-left: auto;background-color: #EDEDED" value="<%=fees%>" name="commission" id="commission" autofocus="true" autocomplete="off" maxlength="100" type="text" onblur="NameNotMatch(this.id)">
                                </td>
                            </tr>
                            <tr>
                                <td style="border: none ; background-color: transparent">
                                    <p><%=CONFIG.getAgentPaymentWillDeducted(session)%>:</p>
                                </td>
                                <td style="border: none ; background-color: transparent">
                                    <input readonly style="padding-left: auto;background-color: #EDEDED" value="<%= request.getParameter("payedAmount")%>" name="payedAmount" id="payedAmount" autofocus="true" autocomplete="off" maxlength="100" type="text" onblur="NameNotMatch(this.id)">
                                </td>
                            </tr>
                        </table>
                    </td>

                </tr>

                <tr>
                    <td style="text-align: center" >
                        <input type="submit" value="<%=CONFIG.getConfirm(session)%>" name="payButton"  id="payButton" />
                    </td>
                    <td  style="text-align: center" >
                        <% if (companyCode.equals("18234")) {%>
                        <input type="button" value="الغاء" name="backButton"  id="backButton" onclick="window.location.href = '<%=rolePage%>';"  />

                        <%} else {%>
                        <input type="button" value="<%=CONFIG.getBack(session)%>" name="backButton"  id="backButton" onclick="history.go(-1);" />

                        <%}%>
                    </td>
                </tr>
            </table>

        </form>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>