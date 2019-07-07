<%-- 
    Document   : TEData_Inquiry_Response
    Created on : Oct 3, 2017, 12:47:26 PM
    Author     : Tasneem
--%>

<%@page import="com.masary.utils.BuildUUID"%>
<%@page import="java.util.Date"%>
<%@page import="com.masary.integration.dto.TEDataBillsResponse"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
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
     BuildUUID build_uuid =new BuildUUID ();
    String uuid =build_uuid.CreateUUID();
    session.setAttribute("uuid", uuid);
    session = request.getSession();
    TEDataBillsResponse bill_response = (TEDataBillsResponse) session.getAttribute("tedataInquiryResponse");
//    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
//    Date dueDate = new Date(bill_response.getNewExpiryDateAfterRenewal());
//    String dueDateString = dt.format(dueDate);
%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <title>فاتورة تي اي داتا</title>
        <script>
            function disableDoubleSubmission(){
                document.getElementById("btnSubmit").disabled=true;
                document.getElementById("Back").disabled=true;
            }
        </script>
    </head>
    <body class="body" >

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="dobillinquiry" action="TEBills_Payment" method="POST" onsubmit="disableDoubleSubmission()">

            <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="${serv_id}" />
        <div class="content_body"  >
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5">استعلام عن فاتورة تي اي داتا
                    </font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getCustomerName(session)%> : 

                                <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%= bill_response.getCustomerName()%>" style="background-color: #EDEDED; float: left;"/>

                            </p>
                            <p align="right"><%=CONFIG.getAssign_v(session)%> : 
                                <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="<%= bill_response.getTotalDueForRenewal()%>"  onchange="onValueChanged();" style="background-color: #EDEDED; float: left;" readonly >
                            </p>
                            <p align="right">رقم المشترك : <input type="text" name="BILLING_ACCOUNT" readonly tabindex="1" id="BILLING_ACCOUNT" value="<%= request.getParameter("BILLING_ACCOUNT")%>" style="background-color: #EDEDED; float: left;"/></p>         
                            <p align="right"><%=CONFIG.getExDate(session)%> : <input type="text" name="DUE_DATE" readonly tabindex="1" id="DUE_DATE" value="<%= bill_response.getNewExpiryDateAfterRenewal()%>" style="background-color: #EDEDED; float: left; "/></p>                            
                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="<%= bill_response.getAppliedFees()%>"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="<%= bill_response.getMerchantCommission()%>"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="<%= bill_response.getTransactionAmount()%>"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                           <p align="right"><%= CONFIG.getTotalAmountMaan(session)%>:<input value="<%=bill_response.getToBepaid() %>" name="Balance_Diff"  autocomplete="off" readonly type="text" id="Balance_Diff" maxlength="11" style="float: left;background-color: #EDEDED;"></p>

                        </td>
                    </tr> 
                    <tr>
                        <td colspan="2">
                            <input id="btnSubmit" type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="Btn"  >
                            <input id="Back" type="button" name="Back" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);">
                        </td>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                    1-	تأكد أن رقم التليفون الذى تم إدخاله صحيح . 
                </details>
            </fieldset> 
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
