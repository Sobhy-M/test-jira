<%-- 
    Document   : newjspmubasherVoucherConfirmation
    Created on : Oct 26, 2016, 2:46:08 PM
    Author     : AYA
--%>

<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getConfirm(request.getSession()) + "  " + CONFIG.getCustomerMubasherVo(request.getSession())%></title>
    </head>
    <body>
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
        <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(request.getSession())%></div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="AssignBalanceCustomer" action="MubasherVoucher" method="POST" onsubmit="return ray.ajax()">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_CUSTOMER_TOPUP_CONF%>" />
        <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%= session.getAttribute(CONFIG.PARAM_SERVICE_ID)%>" />
        <input type="hidden" name="<%=CONFIG.itemId%>" value="<%=request.getParameter(CONFIG.itemId)%>" />
        <fieldset style="width: 70%; direction: rtl;" align="right">  

            <legend align="right" ><font size="5"><%=CONFIG.getConfirm(request.getSession()) + "  " + CONFIG.getCustomerMubasherVo(request.getSession())%></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20" > </legend> 
            <table border="1" style="width: 100%">
                <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                <tr>
                    <td><p align="right"><%=CONFIG.getSelectDenmonationVoucher(request.getSession())%> : <input type="text" name="DenmonationVoucher" readonly tabindex="1" id="custTopUpDalanceID" value="<%= request.getParameter("itemCode")%>" style="background-color: #EDEDED; float: left;"/></p> 
                        <p align="right"><%=CONFIG.getSelectValueVoucher(request.getSession())%> : <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custTopUpDalanceID" value="<%= request.getParameter(CONFIG.AMOUNT)%>" style="background-color: #EDEDED; float: left;"/></p> 
                        <p align="right"><%=CONFIG.getMobileNumber(request.getSession())%> :<input id="custTopUpMobileId" type="text" name="<%=CONFIG.PARAM_MSISDN%>" readonly value="<%= request.getParameter(CONFIG.PARAM_MSISDN)%>" tabindex="2" style="background-color: #EDEDED; float: left;">
                        <div id="custMobileDiv"  ></div></p></br>
                    </td>
                    <td>
                        <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text"   name="Fees" readonly tabindex="1" id="Fees"  value="<%= request.getParameter("Fees")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="Commession"  readonly tabindex="1" id="Commession" value="<%= request.getParameter("Commession")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text"  name="DeductedAmount" readonly tabindex="1" id="DeductedAmount" value="<%= request.getParameter("DeductedAmount")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        <p align="right"><%=CONFIG.getUserAmount(request.getSession())%> :<input type="text"  name="UserAmount" readonly tabindex="1" id="UserAmount" value="<%= request.getParameter("UserAmount")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>

                        </br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="btnSubmit" id="btnSubmit" tabindex="3" value="<%=CONFIG.getpayment(request.getSession())%>" style="float: right" onclick="">
                        <input type="button" name="MoreInfo" tabindex="3" value="<%=CONFIG.getBack(request.getSession())%>"  style="float: left" onclick="history.go(-1);">
                    </td>
                    <td></td></tr>
            </table>
           

        </fieldset>
    </form>

    <!-- content body -->
</div>
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
