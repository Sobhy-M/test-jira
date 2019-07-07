<%-- 
    Document   : bill_payment
    Created on : Jun 27, 2012, 1:09:08 PM
    Author     : KEMO
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Bill_Amounts"%>
<%@page import="com.masary.database.dto.Masary_Bill_Response"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
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
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double billBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 1000);
    Masary_Bill_Type BTC = (Masary_Bill_Type) request.getSession().getAttribute("BTC");
%>
<%
    Masary_Bill_Response bill_Response = new Masary_Bill_Response();
    try {
        bill_Response = (Masary_Bill_Response) session.getAttribute("bill_Info");
    } catch (Exception e) {
    }
    Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(request.getSession().getAttribute("serv_id").toString());
    double billamount = 0;
    String Cur = "";
    double fees = 0;
    String feesCur = "";
    for (Masary_Bill_Amounts amount : bill_Response.getAmounts()) {
        if (amount.getBILL_SUMM_AMT_CODE().equals("TotalAmtDue")) {
            billamount = amount.getCUR_AMOUNT();
            Cur = amount.getCUR_CODE();
        }
        if (amount.getBILL_SUMM_AMT_CODE().equals("Fees")) {
            fees = amount.getCUR_AMOUNT();
            feesCur = amount.getCUR_CODE();
        }
    }
    XMLGregorianCalendarConverter xMLGregorianCalendarConverter = new XMLGregorianCalendarConverter();
    String newdateform = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(xMLGregorianCalendarConverter.asDate(bill_Response.getCLIENT_DATE()));
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://www.java.com/js/deployJava.js"></script>
        <script type="text/javascript">
            //var oJavaDetect = deployJava.versionCheck("1.5") ; 
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

        <script type="text/javascript">
            var ray = {
                ajax: function(st)
                {
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

        <title>Payment Confirmation</title>
    </head>
    <body onload="ray.hide();">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(session)%></div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
    <%if (request.getSession().getAttribute("paymentreq").toString().equals("Y")) {%> 
    <div><H3 id="javaMsg" style="color: red; font-size: 18px"></H3></div>
    <div><a style="color: red; font-size: 18px" id="javaLink"></a></div>
        <%}%>
        <%try {
                String Error = request.getSession().getAttribute("ErrorCode").toString();
%>
    <font style="color: red; font-size: 15px;"><%=request.getSession().getAttribute("ErrorCode").toString()%></font>    
    <%
            request.getSession().removeAttribute("ErrorCode");
        } catch (Exception e) {
        }
    %>
    <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" onsubmit="return validateBillBayCredit(<%=bill_Type.isIS_FRAC_ACC()%>)">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_payment_Inquiry_Req_Confirm_Old%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("serv_id").toString()%>" />
        <input type="hidden" name="ServiceBalance" value="<%=billBalance%>" id="ServiceBalance"/>
        <input type="hidden" name="Fees" value="<%=fees%>" id="Fees"/>
        <input type="hidden" name="<%=CONFIG.NotifyMobile%>" value=<%=request.getParameter(CONFIG.NotifyMobile).length() > 0 ? request.getParameter(CONFIG.NotifyMobile).toString() : "01009912516"%> />
        <div class="content_body"  >
            <fieldset style="width: 60%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%=BTC.getBILLER_ID() == 33 ? CONFIG.getUniv_Bill_Conf_Head(session) : CONFIG.getCustomerConfBillHead(session)%>&nbsp;<%= BTC.getStrBTCName(session)%></font><img src="img/CashIn.ico"  width="20" height="20"> </legend>               

                <table border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td><p align="right"><%=BTC.getBILLER_ID() == 33 ? CONFIG.getStudentName(session) : CONFIG.getCustomerName(session)%> : 
                                <%if (bill_Response.getCustomer_name().equals(null) || bill_Response.getCustomer_name().toLowerCase().contains("not available")) {
                                    } else {%>
                                <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%=bill_Response.getCustomer_name()%>" style="background-color: #EDEDED; float: left;"/>
                                <%}%>
                            </p>
                            <p align="right"><%=CONFIG.getAssign_v(session)%> : 
                                <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="<%=request.getSession().getAttribute("amount").toString()%>" style="background-color: #EDEDED; float: left;" onchange="onValueChanged();" <%=BTC.isIS_ADV_ACC() || BTC.isIS_OVER_ACC() || BTC.isIS_PART_ACC() ? "" : "readonly"%> >
                            </p>
                            <p align="right"><%=BTC.get_BILL_LABLE(session)%> : <input type="text" name="BILLING_ACCOUNT" readonly tabindex="1" id="BILLING_ACCOUNT" value="<%=bill_Response.getBILLING_ACCOUNT()%>" style="background-color: #EDEDED; float: left;"/></p>
                                <%try {
                                        if (bill_Response.getDUE_DATE().equals(null) || bill_Response.getDUE_DATE().equals("2012-07-01")) {
                                        } else {
                                %>
                            <p align="right"><%=CONFIG.getDuDate(session)%> : <input type="text" name="DUE_DATE" readonly tabindex="1" id="DUE_DATE" value="<%=bill_Response.getDUE_DATE()%>" style="background-color: #EDEDED; float: left;"/></p>
                                <%}
                                    } catch (Exception c) {
                                    }%>
                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="<%= request.getParameter("Fees")%>"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="<%= request.getParameter("Commession")%>"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getBalance_Diff(session)%> :<input type="text" name="Balance_Diff" value="<%= request.getParameter("Balance_Diff")%>"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="<%= request.getParameter("DeductedAmount")%>"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <%=CONFIG.getPartial(session)%>&nbsp;<input type="checkbox" name="checkname" onClick="return false;" <%=BTC.isIS_PART_ACC() ? "checked" : ""%>>
                            &nbsp;<%=CONFIG.getOver(session)%>&nbsp;<input type="checkbox" name="checkname" onClick="return false;" <%=BTC.isIS_OVER_ACC() ? "checked" : ""%>>
                            &nbsp;<%=CONFIG.getFraction(session)%>&nbsp;<input type="checkbox" name="checkname" onClick="return false;" <%=BTC.isIS_FRAC_ACC() ? "checked" : ""%>>
                            &nbsp;<%=CONFIG.getAdvanced(session)%>&nbsp;<input type="checkbox" name="checkname" onClick="return false;" <%=BTC.isIS_ADV_ACC() ? "checked" : ""%>>
                        </td>
                    </tr>
                    <%
                        try {
                            if (!bill_Response.getAdditional_Info().equals(null)) {%>
                    <tr><td colspan="2"><p align="right"><%=CONFIG.getAddetionalInfo(session)%> : <%=bill_Response.getAdditional_Info()%></p></td></tr>
                    <%
                            }
                        } catch (Exception c) {
                        }%>

                    <tr>
                        <td >
                            <p align="right"><%=CONFIG.getNotifyMobile(session)%> : <input type="text" name="<%=CONFIG.NotifyMobile%>" tabindex="1" value="<%=request.getParameter(CONFIG.NotifyMobile).length() > 0 ? request.getParameter(CONFIG.NotifyMobile).toString() : ""%>" id="NotifyMobile" readonly style="background-color: #EDEDED; float: left;"/><div id="NotifyMobileDiv" style="color: red; font-size: 12.5px;" readonly ></div></p></td>
                        <td><%=CONFIG.getAllowedBalance(session)%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(billBalance)%></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getConfirm(session)%>" class="Btn" onclick="return ray.ajax()" >
                            <input type="button" name="Back" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);">
                        </td>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                    1-	تأكد أن رقم الموبايل الذى تم إدخاله صحيح ، ثم اضغط تأكيد لإتمام العملية.
                </details>
            </fieldset> 
            </table>
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
