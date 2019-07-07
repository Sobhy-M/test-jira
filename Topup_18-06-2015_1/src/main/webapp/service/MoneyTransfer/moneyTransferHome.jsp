<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
    
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }

    session = request.getSession();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getTransferToAnotheRrep(session)%></title>
        <script type="text/javascript" src="./js/jquery.js"></script>
        <script type="text/javascript" src="./js/jquery.query-2.1.7.js"></script>
        <script type="text/javascript" src="./js/rainbows.js"></script>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <script>
            function ValidateToAccount()
            {
                var thereturnVal = null;
                var val = document.getElementById("to").value;
                if (val === "")
                {
                    thereturnVal = null;
                }
                thereturnVal = $.post(
                        "MoneyTransferAjax",
                        {CUSTOMER_ID: val,action: "GET_AGENT_TO"},
                        function (res) {
                            var nameResult = res;
                            document.getElementById("agentTo").value = nameResult;
                            return 1;
                        });
                return thereturnVal;
            }
            function checkTransfer(param)
            {

                var amount = document.getElementById("addBal").value;
                var from = document.getElementById("fromuser").value;
                var to = document.getElementById("to").value;
                var theReturnVal;

                $.post(
                        "MoneyTransferAjax",
                        {CUSTOMER_ID: from, TO_CUSTOMER: to, AMOUNT: amount, action: "ADD_TRANSFER_FEES"},
                        function (res) {
                            var feesResult = res;
                            if (parseInt(res) === -2)
                            {

                                showErrorMessage("<%=CONFIG.getTRANSFER_FEES_MinusTwo_Error_Message(session)%>");
                                theReturnVal = -2;
                            } else if (parseInt(res) === -1) {

                                showTransferAgain("<%=CONFIG.getTRANSFER_FEES_MinusOne_Error_Message(session)%>");
                                theReturnVal = -1;
                            } else
                            {
                                document.getElementById("fees").value = feesResult;
                                showFees();
                                theReturnVal = 1;
                                document.getElementById("newBalance").innerHTML = "<%=CONFIG.getNewBalance(session)%>";
                                document.getElementById("balance").innerHTML = ${requestScope.masaryBalance} - document.getElementById("addBal").value - document.getElementById("fees").value;
                                document.getElementById("balance").innerHTML += "<%=CONFIG.getTransferFeesCurrency(session)%>";
                            }
                            param(theReturnVal);
                        }
                );

            }
            function doConfirm()
            {
                var confirm = document.getElementById("btnSubmit1").getAttribute("value") !== "<%=CONFIG.getConfirm(session)%>";
                checkTransfer(function (val) {
                    if (confirm && ValidateToAccount() !== null && validateBalance() === true && val === 1) {

                        document.getElementById("toCustomer").value = document.getElementById("to").value;
                        document.getElementById("agentTo").setAttribute("readonly", "");
                        document.getElementById("addBal").setAttribute("readonly", "");
                        document.getElementById("to").setAttribute("readonly", "");
                        document.getElementById("errorMessageTR").style = style = "display: none";
                        document.getElementById("btnSubmit1").setAttribute("value", "<%=CONFIG.getConfirm(session)%>");

                    } else if (document.getElementById("btnSubmit1").getAttribute("value") === "<%=CONFIG.getConfirm(session)%>")
                    {
                        document.getElementById("agentTo").setAttribute("readonly", "");
                        document.getElementById("btnSubmit1").disabled = true;
                        document.AssignBalanceAgent.submit();
                    }
                })
            }

        </script>
    </head>
    <body class="body">
     <div>
                <c:choose>
                    <c:when test="${lang== ''}">
                        <jsp:include page="../../img/menuList.jsp"></jsp:include>
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="../../img/menuListar.jsp"></jsp:include>
                    </c:otherwise>
                </c:choose>
            </div>
            
            
     
            <form name="AssignBalanceAgent" action="MoneyTransferPayment" method="post" onsubmit="return validateBalance()">
            <input type="hidden" name="btnSubmit" id="btnSubmit" />
            <input type="hidden" name="toCustomer" id="toCustomer" />
            <table >
                <tr>
                    <td scope="col"><%=CONFIG.getFrom(session)%></td>
                    <td >
                    <c:choose>
                    <c:when test="${lang== ''}">
                        ${requestScope.agent.name}
                    </c:when>
                    <c:otherwise>
                       ${requestScope.agent.arabicName}
                    </c:otherwise>
                </c:choose></td>
                </tr>
                <tr>
                    <td scope="col"><%=CONFIG.getID(session)%></td>
                    <td>
                        <input id="fromuser"  type="text" readonly value="${requestScope.agent.pin}">
                    </td>
                </tr>
                <tr>
                    <td scope="col" id="newBalance"><%=CONFIG.getBalance(session)%></td>
                    <td  id="balance">${requestScope.masaryBalance}<%= CONFIG.getTransferFeesCurrency(session)%></td>
                </tr>
                <tr>
                    <td scope="col"><%=CONFIG.getTo(session)%> <%=CONFIG.getID(session)%></td>
                    <td><input type="text" autocomplete="off" id="to" name="<%=CONFIG.PARAM_PAYED_PIN%>" tabindex="1"></td>
                </tr>
                <tr>
                    <td scope="col"><%=CONFIG.getAmount(session)%></td>
                    <td><input  id="addBal" autocomplete="off"  required type="text" name="<%=CONFIG.AMOUNT%>" tabindex="2">
                        <div id="balDiv"></div>
                    </td>
                </tr>
                <tr id="toTR" style="display: none">
                    <td id="fTD" ><%=CONFIG.getTo(session)%></td>
                    <td id="sTD" >
                        <input  id="agentTo"   type="text"  value=""  >
                    </td>
                </tr>


                <tr id="FeesTR" style="display: none">
                    <td id="feesLabel" scope="col"><%=CONFIG.getFees(session)%></td>
                    <td><input  readonly id="fees"  type="text" name="<%=CONFIG.FEES%>" tabindex="3" >
                    </td>
                </tr>

                <tr id="errorMessageTR" style="display: none">
                    <td colspan="2">
                        <div id="ErrorMessage" align="center" style="color: red">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><input type="button"  name="btnSubmit1" id="btnSubmit1" 
                               value="<%=CONFIG.getAssign(session)%>" class="Btn" onclick="doConfirm();">
                    </td>
                    <td>
                        <input type="reset" value="<%=CONFIG.getOperationRetry(session)%>" name="btnback" id="btnBack"/>
                    </td>
                </tr>
            </table>
            <!-- End of Table Content area-->
        </form>
        <div style="clear: both;">&nbsp;</div>
       <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>