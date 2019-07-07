<%@page import="com.masary.database.dto.Vodafone_Cash_Receipt"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getVodafone_Cash(session)%>-<%=CONFIG.getVC_CashIn(session)%>-<%=CONFIG.getError(session)%></title>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <div id="content">

            <center>
                <form name="formElem" id="formElem" action="<%=CONFIG.APP_ROOT%>eMoneyControler" method="POST" >
                <fieldset style="width: 28%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%=CONFIG.getError(session)%>&nbsp; </font><img src="img/CashIn.ico"  width="20" height="20" style="background: url('CashIn.ico') no-repeat scroll 100% 50% transparent;"> </legend> 
                <% try {
                        Vodafone_Cash_Receipt receipt = (Vodafone_Cash_Receipt) request.getSession().getAttribute("Receipt");
                        if (!receipt.equals(null)) {
                %>
                    <input type="hidden" name="action" value="<%=CONFIG.CHECKIN_RECEIPT%>" />
                    <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                    <table border="1" style="width: 100%;" >
                        <th><%=CONFIG.getINFO(session)%></th>
                        <tr>
                            <td>
                                <p align="right"><%=CONFIG.getAmount(session)%> : <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custAmountID" value="<%= request.getParameter(CONFIG.AMOUNT)%>" style="background-color: #EDEDED; float: left;"/>
                                <div id="custAmountDiv" name="custAmountDiv"></div></p> 
                                <p align="right"><%=CONFIG.getMobileNumber(session)%> :<input id="custMobile" type="text" name="<%=CONFIG.PARAM_MSISDN%>" readonly value="<%= request.getParameter(CONFIG.PARAM_MSISDN)%>" tabindex="2" style="background-color: #EDEDED; float: left;">
                                <div id="custMobileDiv"  ></div></p>
                                <p align="right"><%=CONFIG.getFees(session)%> :<input type="text"   name="Fees" readonly tabindex="1" id="Fees"  value="<%= request.getParameter("Fees")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession"  readonly tabindex="1" id="Commession" value="<%= request.getParameter("Commession")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text"  name="DeductedAmount" readonly tabindex="1" id="DeductedAmount" value="<%= request.getParameter("DeductedAmount")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <h2><%=CONFIG.getRequestId(session)%> : <%= receipt.getREQUEST_ID()%></h2>
                            </td>
                        </tr>
                        <tr><td><input type="submit" value="<%=CONFIG.getPrinting_Receipt(session)%>" name="Print" width="90" height="90" hspace="10" ></td></tr>
                        
                    </table> 

                    <%}
                    } catch (Exception e) {
                    %>
                    <table border="1" style="width: 100%;" >
                        <tr><td colspan="2"><p align="center"><%=request.getSession().getAttribute("Result")%></p></td></tr>
                    </table> 
                    <%
                        }%>
                    <details open="open">
                        <summary></summary>
                        1.	عندما تظهر لك تقرير بيانات العملية ، سيظهر لك رقم الطلب، يمكنك إستخدام رقم الطلب فى الإستعلام عن حالة العملية.  </br>
                        2.	يمكنك أيضاً طباعة إيصال العملية بالضغط على زرار طباعة إيصال. </br>

                    </details>
                </fieldset> 
            </form>
        </center>

    </div>

    <div style="clear: both;">&nbsp;</div>

    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>

