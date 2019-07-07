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

        <title><%=CONFIG.getCustomerConfBillHead(request.getSession())%></title>
    </head>
    <body onload="ray.hide();">
        <div>
            <script type="text/javascript" src="img/CheckForms.js"></script>
            <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(session)%></div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <font style="color: red; font-size: 15px;">${ErrorCode}</font>    
        <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" >
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_payment_Inquiry_Req_Confirm%>" />
            <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("serv_id").toString()%>" />
            <input type="hidden" name="ServiceBalance" value="<%=billBalance%>" id="ServiceBalance"/>
            <input type="hidden" name="Fees" value="${Fees}" id="Fees"/>

            <div class="content_body"  >
                <fieldset style="width: 70%; direction: rtl;" align="right">  
                    <legend align="right" ><font size="5"><%= BTC.getStrBTCName(session)%></font><img src="img/CashIn.ico"  width="20" height="20"> </legend>               

                    <table border="1" width="100%">
                        <th><%=CONFIG.getINFO_Required(session)%></th>
                        <th><%=CONFIG.getMerchantCommession(session)%></th>
                        <tr>
                            <td>
                                <p align="right"><%=CONFIG.getAssign_v(session)%> : 
                                    <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="<%=request.getParameter("AMOUNT")%>" style="background-color: #EDEDED; float: left;" onchange="onValueChanged();" readonly >
                                </p>

                                <p align="right"><%=BTC.get_BILL_LABLE(session)%> : <input type="text" name="BILLING_ACCOUNT" readonly tabindex="1" id="BILLING_ACCOUNT" value="${BILLING_ACCOUNT}" style="background-color: #EDEDED; float: left;"/></p>                               
                            </td>
                            <td>
                                <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="${Fees}"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="${Commession}"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getBalance_Diff(session)%> :<input type="text" name="Balance_Diff" value="${Balance_Diff}"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="${DeductedAmount}"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            </td>
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
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>
