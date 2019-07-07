<%-- 
    Document   : VC_CashIn
    Created on : Nov 4, 2013, 3:33:40 PM
    Author     : Aya
--%>
<%@page import="com.masary.database.dto.VC_Commission_Structure"%>
<%@page import="com.masary.database.dto.COMMISSION"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.dto.Vodafone_Cash_Transactions"%>
<%@page import="com.masary.database.dto.Transaction"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    Vodafone_Cash_Transactions list = (Vodafone_Cash_Transactions) request.getSession().getAttribute("List");
    String result = "";
    try {
        result = (String) request.getSession().getAttribute("Result");
        if (result == null) {
            result = "";
        }
    } catch (Exception e) {
        result = "";
    }
    String availableOptions_Key = "";
    try {
        availableOptions_Key = (String) request.getSession().getAttribute("availableOptions_Key");
        if (availableOptions_Key == null) {
            availableOptions_Key = "";
        }
    } catch (Exception e) {
        availableOptions_Key = "";
    }
    int page_v = (Integer) request.getSession().getAttribute("Page");
    int pages = 0;
    int rowCount = list.getROWCOUNT();
    double x = rowCount / 9.0;
    int y = (int) x;
    double z = x - y;
    if (z > 0) {
        pages = ++y;
    } else {
        pages = y;
    }
    int updatedOperationID;
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getVodafone_Cash(session)%>-<%=CONFIG.getVodafone_cash_Transaction_Inquiry(session)%></title>

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
                    <!--<details open="open">-->

                    <legend align="right" ><font size="5"><%=CONFIG.getVodafone_cash_Transaction_Inquiry(session)%>&nbsp; </font><img src="img/Inquiry.ico"  width="20" height="20" > </legend> 
                    <input type="hidden" name="action" value="<%=CONFIG.TRANSACTION_INQUIRY%>" />
                    <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                    <table border="1">
                        <th><%=CONFIG.getSearch(session)%></th>
                        <th><%=CONFIG.getCategory(session)%></th>
                        <tr>
                            <td>
                                <p align="right"><input type="text" name="search" value="<%=request.getParameter("search") == null ? "" : request.getParameter("search")%>"  tabindex="1"  style="alignment-adjust: central"/><div></div></p> 
                            </td>
                            <td>
                                <select   name="availableOptions">
                                    <option value="Mobile" <%=availableOptions_Key != "" && availableOptions_Key.equals("Mobile") ? "selected" : ""%>><%=CONFIG.getMobileNumber(session)%>
                                    </option>
                                    <option value="Request_ID" <%=availableOptions_Key != "" && availableOptions_Key.equals("Request_ID") ? "selected" : ""%>><%=CONFIG.getRequestId(session)%>
                                    </option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td  colspan="2">
                                <input type="submit" name="btnSubmit" tabindex="3" value="<%=CONFIG.getSearch(session)%>"  style="alignment-adjust: central">
                            </td>
                        </tr>
                    </table>
                    <details open="open">
                        <summary></summary>
                        للإستعلام عن عملية أو حالة طلب ، برجاء إختيار (رقم الموبايل) و كتابة رقم الموبايل فى مربع البحث أو إختيار (رقم الطلب) و كتابة رقم الطلب فى مربع البحث.
                    </details>
                </fieldset> <br><br>
            </form>
            <form  name="formElem" id="formElem6" action="<%=CONFIG.APP_ROOT%>eMoneyControler" method="POST">
                <!--<fieldset style="width: 105%; direction: rtl;" align="right">-->  
                <table  border="1" style="width: 120%;">
                    <thead>
                        <tr>
                            <th style="color: #000;"><%=CONFIG.getRequestId(session)%></th>
                            <!--<th style="color: #000;">CREATION DATE</th>-->
                            <th style="color: #000;"><%=CONFIG.getTransactionNumber(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getDate(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getMobileNumber(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getVC_TXN_Main_AMOUNT(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getVC_TXN_AMOUNT(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getType(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getCommession(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getFees(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getVC_PaymentCollection(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getStatus(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getVC_Cancel(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getCheck(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getPrinting_Receipt(session)%></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (rowCount > 0) {
                                for (Transaction txn : list.getTRANSACTIONS()) {
                        %>

                        <tr >
                            <td style="background-color: <%=txn.getTRANSACTION_ID().equals("0") ? "#FFECEC" : "#D1ECD1"%>; color: #000;"><%=txn.getOPERATION_ID()%></td>
                            <!--<th><%=txn.getCREATION_DATE()%></th>-->
                            <td style="background-color: <%=txn.getTRANSACTION_ID().equals("0") ? "#FFECEC" : "#D1ECD1"%>; color: #000;"><%=txn.getTRANSACTION_ID()%></td>
                            <td style="background-color: <%=txn.getTRANSACTION_ID().equals("0") ? "#FFECEC" : "#D1ECD1"%>; color: #000;"><%=txn.getTRANSACTION_DATE()%></td>
                            <td style="background-color: <%=txn.getTRANSACTION_ID().equals("0") ? "#FFECEC" : "#D1ECD1"%>; color: #000;"><%=txn.getCUSTOMER_PHONE()%></td>
                            <td style="background-color: <%=txn.getTRANSACTION_ID().equals("0") ? "#FFECEC" : "#D1ECD1"%>; color: #000;"><%=txn.getMAIN_AMOUNT()%></td>
                            <td style="background-color: <%=txn.getTRANSACTION_ID().equals("0") ? "#FFECEC" : "#D1ECD1"%>; color: #000;"><%=txn.getAMOUNT()%></td>
                            <td style="background-color: <%=txn.getTRANSACTION_ID().equals("0") ? "#FFECEC" : "#D1ECD1"%>; color: #000; text-align: center;"><%=txn.getTRANSACTION_TYPE(session)%></td>
                            <td style="background-color: <%=txn.getTRANSACTION_ID().equals("0") ? "#FFECEC" : "#D1ECD1"%>; color: #000;"><%=txn.getCOMMESSION()%></td>
                            <td style="background-color: <%=txn.getTRANSACTION_ID().equals("0") ? "#FFECEC" : "#D1ECD1"%>; color: #000;"><%=txn.getTRANSACTION_ID().equals("0") ? "0" : txn.getFEES()%></td>
                            <td style="background-color: <%=txn.getTRANSACTION_ID().equals("0") ? "#FFECEC" : "#D1ECD1"%>; color: #000;"><%=txn.getTRANSACTION_ID().equals("0") ? "0" : txn.getTOTAL()%></td>
                            <%
                                try {
                                    updatedOperationID = 0;
                                    updatedOperationID = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_OPERATION_ID).toString());
                                } catch (Exception e) {
                                    updatedOperationID = 0;
                                }
                                if (updatedOperationID == txn.getOPERATION_ID()) {
                                    request.getSession().removeAttribute(CONFIG.PARAM_OPERATION_ID);
                            %>
                            <td style="background-color: #ECEC1C; color: #000;"><%=txn.getSTATUS(session)%></td>
                            <%
                            } else {
                            %>
                            <td style="background-color: <%=txn.getTRANSACTION_ID().equals("0") ? "#FFECEC" : "#D1ECD1"%>; color: #000;"><%=txn.getSTATUS(session)%></td>
                            <%}%>
                            <td align="center">

                                <%
                                    if (txn.getIS_PENDING() == 1) {
                                %>
                                <form name="inq" id="formElem3" action="<%=CONFIG.APP_ROOT%>eMoneyControler" method="POST">
                                    <input type="hidden" name="action" value="<%=CONFIG.VC_Cancel_Transaction%>" />
                                    <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                                    <input type="hidden" name="<%=CONFIG.PARAM_OPERATION_ID%>" value="<%=txn.getOPERATION_ID()%>" />
                                    <input type="submit" name="btnSubmit" tabindex="3" value="" style="background: url(img/Cancel-128.png) no-repeat scroll 0 0 transparent; background-size:20px 20px; border:0; height: 20px; width: 20px;">
                                </form>
                                <% }%>
                            </td>
                            <td align="center">

                                <%
                                    if (txn.getIS_PENDING() == 1 || txn.getIS_PENDING() == 2) {
                                %>
                                <form name="inq" id="formElem3" action="<%=CONFIG.APP_ROOT%>eMoneyControler" method="POST">
                                    <input type="hidden" name="action" value="<%=CONFIG.TRANSACTION_INQUIRY_FROM_OPERATIONS%>" />
                                    <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                                    <input type="hidden" name="<%=CONFIG.PARAM_OPERATION_ID%>" value="<%=txn.getOPERATION_ID()%>" />
                                    <input type="submit" name="btnSubmit" tabindex="3" value="" style="background: url(img/Inquiry_1.png) no-repeat scroll 0 0 transparent; background-size:20px 20px; border:0; height: 20px; width: 20px;">
                                </form>
                                <% }%>
                            </td>
                            
                            <td align="center">
                                    <%if (Integer.parseInt(txn.getTRANSACTION_ID()) > 0) {
                                    %>
                                    <form name="formElem" id="formElem2" action="<%=CONFIG.APP_ROOT%>eMoneyControler" method="POST" >
                                        <input type="hidden" name="action" value="<%=CONFIG.PRINT_RECEIPT_FROM_REPORT%>" />
                                        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                                        <input type="hidden" name="<%=CONFIG.PARAM_Transaction_ID%>" value="<%=txn.getTRANSACTION_ID()%>" />
                                    <input type="submit" name="btnSubmit" tabindex="3" value="" style="background: url(img/receipt-5.png) no-repeat scroll 0 0 transparent; background-size:20px 20px; border:0; height: 20px; width: 20px;"  >
                                </form>
                                    <%                                    }%>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr><td colspan="14" style="background-color: #FFECEC; color: #000; text-align: center;"><%=CONFIG.getVC_NDF(session)%></td></tr>    
                            <%
                                }
                            %>
                        <tr><td colspan="7" style="background-color: #ffffff;"></td>
                            <td style="text-align: center; background-color: #ffffff;">
                                <form name="formElem" id="formElem4" action="<%=CONFIG.APP_ROOT%>eMoneyControler" method="POST" >
                                    <input type="hidden" name="action" value="<%=CONFIG.VC_NAVIGATOR%>" />
                                    <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                                    <input type="hidden" name="Page" value="<%=page_v + 1%>" />
                                    <input type="submit"  name="btnSubmit" <%= page_v == pages || pages == 0 ? "disabled ; hidden = '';" : ""%>  tabindex="3" value="" style="background: url(img/Next.png) no-repeat scroll 0 0 transparent; background-size:20px 20px; border:0; height: 20px; width: 20px;"  >
                                </form>
                            </td>
                            <td style="text-align: center; background-color: #ffffff;">
                                <form name="formElem" id="formElem1" action="<%=CONFIG.APP_ROOT%>eMoneyControler" method="POST" >
                                    <input type="hidden" name="action" value="<%=CONFIG.VC_NAVIGATOR%>" />
                                    <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                                    <input type="hidden" name="Page" value="<%=page_v - 1%>" />
                                    <input type="submit" name="btnSubmit" <%= page_v == 1 ? "disabled ; hidden = '';" : ""%>  tabindex="3" value="" style="background: url(img/Prev.png) no-repeat scroll 0 0 transparent; background-size:20px 20px; border:0; height: 20px; width: 20px;"  >
                                </form>
                            </td><td style="text-align: center; color: #000; background-color: #ffffff;"> <%=page_v%> <%= CONFIG.getOf(session)%>  <%=pages%> <%= CONFIG.getPage(session)%> </td><td colspan="4" style="background-color: #ffffff;"></td></tr>
                    </tbody>
                </table>        
            </form>
        </center>
        <div style="clear: both;">&nbsp;</div>

        <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
    </div>
</body>
</html>

