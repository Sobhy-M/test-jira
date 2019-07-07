<%-- 
    Document   : bill_payment
    Created on : Jun 27, 2012, 1:09:08 PM
    Author     : KEMO
--%>

<%@page import="com.masary.database.dto.Bill_Response"%>
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

    String custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);

    DecimalFormat myFormatter = CONFIG.getFormater(session);
    AgentDTO agent = MasaryManager.getInstance().getAgent(custId);
    Masary_Bill_Type BTC = (Masary_Bill_Type) request.getSession().getAttribute("BTC");
    Bill_Response bill_Response=(Bill_Response)request.getSession().getAttribute("bill_Response");
    //System.out.println("bill response "+bill_Response.getCUSTOMER_NAME());

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

       

        <title><%=CONFIG.getConfirm(session)%><%=CONFIG.getTELECOMEGYPT_Resharge(session)%></title>
    </head>
    <body >
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <!--<div id="load" style="display:none;"><%=CONFIG.getPleaseWait(session)%></div>-->
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

    <font style="color: red; font-size: 15px;">${ErrorCode}</font>    

    <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_Resharge_Payment%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("serv_id").toString()%>" />
        <input type="hidden" name="ServiceBalance" value="<%=agent.getServiceBalance(1000)%>" id="ServiceBalance"/>
        <input type="hidden" name="Fees" value="<%=request.getParameter("Fees")%>" id="Fees"/>
        <input type="hidden" name="<%=CONFIG.QUOTA%>" value="<%=request.getParameter(CONFIG.QUOTA)%>" id="QUOTA"/>
        <input type="hidden" name="<%=CONFIG.NotifyMobile%>" value="${Customer_Mobile}" />
        <div class="content_body"  >
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%=CONFIG.getConfirm(session) %>&nbsp;<%= BTC.getStrBTCName(session)%></font><img src="img/CashIn.ico"  width="20" height="20"> </legend>               

                <table border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td><p align="right"><%=BTC.getBILLER_ID() == 33 ? CONFIG.getStudentName(session) : CONFIG.getCustomerName(session)%> : 

                                <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" style="background-color: #EDEDED; float: left;" value="<%=bill_Response.getCUSTOMER_NAME()%>">

                            </p>
                            <p align="right"><%=CONFIG.get_E_amount(session)%> : 
                                <input type="text" name="<%=CONFIG.MONEY_PAID%>" tabindex="1"  id="custAmountID" 
                                       value="<%=request.getParameter(CONFIG.MONEY_PAID) %>" 
                                       style="background-color: #EDEDED; float: left;" onchange="onValueChanged();">
                            </p>
                            <p align="right"><%=BTC.get_BILL_LABLE(session)%> : <input type="text" name="BILLING_ACCOUNT" readonly tabindex="1" id="BILLING_ACCOUNT" value="${bill_Response.BILLING_ACCOUNT}" style="background-color: #EDEDED; float: left;"/></p>

                            <p align="right"><%=CONFIG.getQuota(session)%> : <input type="text" name="<%=CONFIG.QUOTA%>" readonly tabindex="1" id="Quota" value="<%= request.getParameter(CONFIG.QUOTA) %>" style="background-color: #EDEDED; float: left; width: 25px;"/></p>

                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="${Fees}"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="${Commession}"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getBalance_Diff(session)%> :<input type="text" name="Balance_Diff" value="${Balance_Diff}"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="${DeductedAmount}"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </td>
                    </tr>
                    <tr><td colspan="2"><p align="right"><%=CONFIG.getAddetionalInfo(session)%> : <input type="text" name="DeductedAmount" value="${ADDITIONAL_INFO}"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left; width: 80%"/></p></td></tr>


                    <tr>
                        <td >
                            <p align="right"><%=CONFIG.getNotifyMobile(session)%> : <input type="text" name="<%=CONFIG.NotifyMobile%>" tabindex="1" value="<%=request.getParameter(CONFIG.NotifyMobile).length() > 0 ? request.getParameter(CONFIG.NotifyMobile).toString() : ""%>" id="NotifyMobile" readonly style="background-color: #EDEDED; float: left;"/><div id="NotifyMobileDiv" style="color: red; font-size: 12.5px;" readonly ></div></p></td>
                        <td><%=CONFIG.getAllowedBalance(session)%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(agent.getBalance()) : myFormatter.format(agent.getServiceBalance(1000))%></td>
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
                    1-	تأكد أن رقم التليفون الذى تم إدخاله صحيح ، ثم اضغط تأكيد لإتمام العملية.
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
