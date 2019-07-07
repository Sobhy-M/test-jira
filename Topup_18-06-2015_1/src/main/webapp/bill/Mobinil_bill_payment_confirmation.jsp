<%--
    Document   : Mobinil_bill_payment_confirmation
    Created on : 15/08/2012, 09:47:36 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%
    MasaryManager.logger.info("BillInquiry_Page");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double billBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 1000);
    Masary_Bill_Type BTC = MasaryManager.getInstance().getBTC(request.getParameter(CONFIG.PARAM_SERVICE_ID));
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://www.java.com/js/deployJava.js"></script>
        <script type="text/javascript">
            function check() {
                var oJavaDetect = null;
                oJavaDetect = deployJava.getJREs();
                var element = document.getElementById('javaLink');
                if (oJavaDetect.length == 0)
                {
                    document.getElementById('javaMsg').innerHTML = "<%=CONFIG.getjavaMessge(session)%>";
                    element.innerHTML = "<%=CONFIG.getjavaMessge(session)%>";
                    element.innerHTML = "<img src='img/javaicon.png' />";
                    element.setAttribute('onclick', 'window.location.href=\'http://e-masary.net//app//getJava\'');
                }
            }
        </script>
        <script>
            function checkParam() {
                var mobileNumber = document.'<%=CONFIG.PARAM_MSISDN%>'.value;
                var amount = document.'<%=CONFIG.AMOUNT%>'.value;
                if (mobileNumber == null || mobileNumber == '' || amount == null || amount == '') {
                    return false;
                }
            }
        </script>
        <script type="text/javascript">
            var ray = {
                ajax: function(st)
                {
                    document.getElementById("btnSubmit").disabled = true;
                    this.show('load');
                },
                hide: function(st)
                {
                    var load = Document.getElementById("load");
                    load.style = "display:none;";
                },
                show: function(el)
                {
                    this.getID(el).style.display = '';
                },
                getID: function(el)
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
        <title><%=CONFIG.getConfirm(session)%>&nbsp;<%=CONFIG.getCUSTOMERBill_Payment(session)%>&nbsp;<%=BTC.getStrBTCName(session)%></title>
    </head>
    <body class="body" onload="ray.hide()">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(session)%></div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
    <%try {
            String Error = request.getSession().getAttribute("ErrorCode").toString();
    %>
    <font style="color: red; font-size: 15px;"><%=request.getSession().getAttribute("ErrorCode").toString()%></font>
        <%
                request.getSession().removeAttribute("ErrorCode");
            } catch (Exception e) {
            }
        %>
    <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" onsubmit="return ray.ajax();
                checkParam();">
        <div class="content_body"  >
            <fieldset style="width: 60%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%=CONFIG.getConfirm(session)%>&nbsp;<%=CONFIG.getCUSTOMERBill_Payment(session)%>&nbsp;<%=BTC.getStrBTCName(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend> 
                <input type="hidden" name="action" value="<%=CONFIG.ACTION_Do_Mobinil_Bill_Payment_Confirm%>" />
                <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("serv_id").toString()%>" />
                <table border="1">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td><p align="right"><%=CONFIG.getAmount(session)%> : <input type="text" name="<%=CONFIG.AMOUNT%>"  value="<%= request.getParameter(CONFIG.AMOUNT)%>" readonly tabindex="1" id="custAmountID" style="background-color: #EDEDED; float: left;"/>
                            <div id="custAmountDiv" name="custAmountDiv"></div></p> 
                            <p align="right"><%=BTC.get_BILL_LABLE(session)%> :<input id="custMobile" type="text" name="<%=CONFIG.PARAM_MSISDN%>" readonly value="<%= request.getParameter(CONFIG.PARAM_MSISDN)%>" tabindex="2" style="background-color: #EDEDED; float: left;">
                            <div id="custMobileDiv"  ></div></p></br>
                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text"   name="Fees" readonly tabindex="1" id="Fees"  value="<%= request.getParameter("Fees")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession"  readonly tabindex="1" id="Commession" value="<%= request.getParameter("Commession")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getBalance_Diff(session)%> :<input type="text"  name="Balance_Diff" readonly tabindex="1" id="Balance_Diff" value="<%= request.getParameter("Balance_Diff")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text"  name="DeductedAmount" readonly tabindex="1" id="DeductedAmount" value="<%= request.getParameter("DeductedAmount")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            </br>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" name="btnSubmit" id="btnSubmit" tabindex="3" value="<%=CONFIG.getConfirm(session)%>" style="float: right">
                            <input type="button" name="MoreInfo" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);">
                        </td>
                        <td><%=CONFIG.getNewBalance(session)%> : <%=myFormatter.format(billBalance - Double.parseDouble(request.getParameter("DeductedAmount")))%></td></tr>
                </table>
                <details open="open">
                    <summary></summary>
                    1-	تأكد أن رقم الموبايل و المبلغ الذين تم إدخالهما صحيحين ، ثم اضغط تأكيد لإتمام العملية.
                </details>
            </fieldset>
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>

