<%-- 
    Document   : bill_payment
    Created on : Jun 27, 2012, 1:09:08 PM
    Author     : KEMO
--%>

<%@page import="com.masary.integration.dto.CommissionPresentation"%>
<%@page import="com.masary.database.dto.CommissionsTransaction"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<% response.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
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
<%
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    CommissionPresentation inquiryCommessionResponse = (CommissionPresentation) session.getAttribute("CommessionResponse");

%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://www.java.com/js/deployJava.js"></script>


        <script type="text/javascript">
            var ray = {
                ajax: function (st)
                {
                    this.show('load');
                },
                hide: function (st)
                {
                    var load = Document.getElementById("load");
                    load.style = "display:none;";
                },
                show: function (el)
                {
                    this.getID(el).style.display = '';
                },
                getID: function (el)
                {
                    return document.getElementById(el);
                }
            }
        </script>
        <style type="text/css">
            #load{
                position:absolute;
                top:50%;
                z-index:1;
                border:3px double #999;
                width:300px;
                height:300px;
                margin-top:-150px;
                margin-left:-150px;
                background:#ffffff;
                top:50%;
                left:50%;
                text-align:center;
                line-height:300px;
                font-family:"Trebuchet MS", verdana, arial,tahoma;
                font-size:18pt;
                background-image:url(img/loading.gif);
                background-position:50% 40%;
            }
        </style>

        <title>تاكيد دفع فواتير بتروتريد</title>
    </head>
    <body onload="ray.hide();">
        <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(session)%></div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <font style="color: red; font-size: 15px;">${ErrorCode}</font>    
    <div class="content_body"  >
        <form name="petroTrade" action="PetroTradePaymentConfirmation" method="POST" >
            <input type="hidden" value="<%=request.getParameter("MemberNumber")%>" name="MemberNumber"/>
            <table border="1" width="76%">
                <th><%=CONFIG.getINFO_Required(session)%></th>     
                <th><%=CONFIG.getMerchantCommession(session)%></th>    
                <tr>
                    <td>
                        <p align="right"> <%=CONFIG.getMemberName(session)%>: <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%= inquiryCommessionResponse.getSubscriberName()%>" style="background-color: #EDEDED;float: left; width: 41%"/></p>
                        <p align="right"><%= CONFIG.getNumberOfBills(request.getSession())%>  <input id="BillsNum" type="text"  name="BillsNum" readonly value="<%= inquiryCommessionResponse.getBillsNumber()%>" style="background-color: #EDEDED;float: left; width: 41%" ></p>
                        <p align="right"><%=CONFIG.getTotalBillsAmount(session)%>: <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="<%= inquiryCommessionResponse.getAmount()%>" style="background-color: #EDEDED;float: left; width: 41%"/></p>
                        <p align="right"><%=CONFIG.getFeesAndTax(session)%>:  <input type="text" name="<%=CONFIG.FEES%>" tabindex="1"  id="custAmountID" value="<%= inquiryCommessionResponse.getAppliedFees() + inquiryCommessionResponse.getTax()%>"   style="background-color: #EDEDED;float: left; width: 41%"/></p>
                        <p align="right"><%=CONFIG.ESED_Confirmation_DesiredAmount1%>:  <input type="text" name="TotalAmount" readonly tabindex="1" id="TotalAmount" value="<%=inquiryCommessionResponse.getToBepaid()%>" style="background-color: #EDEDED;float: left; width: 41%"/></p>
                    </td>
                    <td>
                        <p align="right"><%=CONFIG.getCommessionMaan(request.getSession())%> :<input type="text" name="Commession" value="<%=inquiryCommessionResponse.getMerchantCommission()%>"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left; width: 43%"/></p> 
                        <p align="right"><%=CONFIG.getDeductedAmountMaan(request.getSession())%> :<input type="text" name="deductAmount" value="<%=inquiryCommessionResponse.getTransactionAmount()%>"  readonly tabindex="5"  id="deductAmount" style="background-color: #EDEDED; float: left; width: 43%"/></p> 
                    </td>
                </tr>
                <tr>
                    <td> <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="Btn" onclick="return ray.ajax()" > </td> 
                    <td><input type="button" name="Back" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);"> </td>
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
