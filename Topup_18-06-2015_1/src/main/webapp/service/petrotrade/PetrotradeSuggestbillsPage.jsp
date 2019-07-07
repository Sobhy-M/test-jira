<%-- 
    Document   : bill_inquiry
    Created on : 01/05/2012, 07:10:57 م
    Author     : Michael
--%>

<%@page import="com.masary.integration.dto.SuggestionBillsRepresentation"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%
    //  MasaryManager.logger.info("BillInquiry_Page");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();

    SuggestionBillsRepresentation suggestionBillsRepresentation = (SuggestionBillsRepresentation) session.getAttribute("suggestionBillsRepresentation");
    String rolePage = CONFIG.APP_ROOT + (String) session.getAttribute(CONFIG.PARAM_ROLE) + ".jsp";

%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://cdn.e-masary.net/app/img/jquery-1.11.3.min.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPetrotradeInquiryInfo(request.getSession())%></title>
        <script>
            function changeselection(id) {

                if (id === "FirstSuggestion") {

                    document.getElementById("AMOUNT").value = document.getElementById("AMOUNT1").value;


                } else {
                    document.getElementById("AMOUNT").value = document.getElementById("AMOUNT2").value;

                }

            }
            
            function onload(){
                document.getElementById("AMOUNT").value = document.getElementById("AMOUNT1").value;
            }
            
        </script>
    </head>
    <body class="body" onload="onload()">
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <font style="color: red; font-size: 15px;">${ErrorCode}</font>     
        <div class="content_body">
            <form name="petrotradeInfo" action="PetroTradePayment" method="POST">
                <input type="hidden" value="<%=request.getParameter("MemberNumber")%>" name="MemberNumber"/>
                <input type="hidden" id="AMOUNT" name="<%=CONFIG.AMOUNT%>"/>
                <table style="width: 40%">
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=CONFIG.getPetrotradeSuggestationBill(request.getSession())%>
                        </td>
                    </tr>

                    <tr> <td colspan="2">  <p align="right"><input type="radio" onchange="changeselection(this.id)" checked name="radio" id="FirstSuggestion"><%=CONFIG.getNumberOfBills(session)%> <input id="billsCount" type="text"  name="FirstSuggestion" readonly  value="<%= suggestionBillsRepresentation.getFristSuggestionbillsCount()%>" style="width: 105px;background-color: #EDEDED;" ></p></td></tr>

                    <tr>
                        <td ><p align="right"><%=CONFIG.getTotalBillsAmount(session)%> : </p></td>
                        <td>  <input type="text" name="AMOUNT1" tabindex="1"  id="AMOUNT1" readonly style="background-color: #EDEDED;" value="<%= suggestionBillsRepresentation.getFristSuggestionbillsAmount()%>" required >
                    </tr>

                    <tr> <td colspan="2"><p align="right"><input type="radio" readonly  name="radio" onchange="changeselection(this.id)" id="SecondSuggestion"><%=CONFIG.getNumberOfBills(session)%> <input id="BillsNum" type="text" name="SecondSuggestion"  value="<%= suggestionBillsRepresentation.getSecondSuggestionbillsCount()%>"  style="width: 105px;background-color: #EDEDED;" ></p></td></tr>

                    <tr>
                        <td><p align="right"><%=CONFIG.getTotalBillsAmount(session)%> : </p></td>
                        <td>  <input type="text" name="AMOUNT2" tabindex="1"  id="AMOUNT2" readonly style="background-color: #EDEDED;"  value="<%= suggestionBillsRepresentation.getSecondSuggestionbillsAmount()%>" required >
                    </tr>



                    <tr>
                        <td colspan="3">
                            <div align="center">
                                <input type="submit" name="btnSubmit"  value="<%=CONFIG.getpayment(session)%>" class="Btn" style="float: right;">
                                <input type="button" name="btnBack"  value="<%=CONFIG.getBack(session)%>" class="Btn" onclick="history.go(-1)">
                                <input type="button" name="btncancel" onclick="window.location.href = '<%=rolePage%>';" tabindex="3" value="إلغاء" class="Btn" style="float: left;">
                            </div>
                        </td>
                    </tr>

                </table>

            </form>

        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
