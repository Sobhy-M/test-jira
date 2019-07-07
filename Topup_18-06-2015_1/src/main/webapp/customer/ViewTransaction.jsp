<%--
    Document   : ViewTransaction.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="com.masary.integration.TopupServiceIntegrationStatus"%>
<%@page import="com.masary.utils.SystemSettingsUtil"%>
<%@page import="com.masary.integration.TopupServiceIntegrationStatus"%>
<%@page import="com.masary.database.dto.GenericSellVoucherResponse"%>
<%@page import="com.masary.database.dto.GenericSellVoucherResponse"%>
<%@page import="com.masary.database.dto.SellVoucherResponse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="java.util.Map"%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    
    Map<String, String> requestParameters =	
		(Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);
    
    session = request.getSession();
    String transId = (String) session.getAttribute(CONFIG.PARAM_Transaction_ID);
    String taxAmount = (String) session.getAttribute(CONFIG.PARAM_TAX_AMOUNT);
    String transtype = (String) session.getAttribute(CONFIG.PARAM_TRANSACTION_TYPE);
    taxAmount = taxAmount == null ? "0" : taxAmount;
    TransactionDTO trans ;

    long orangeTopupServiceId =Long.parseLong(SystemSettingsUtil.getInstance().loadProperty("orangeTopup.serviceId"));
    long etisalatTopupServiceId =Long.parseLong(SystemSettingsUtil.getInstance().loadProperty("etisalatTopup.serviceId"));
     long vodafoneTopupServiceId =Long.parseLong(SystemSettingsUtil.getInstance().loadProperty("vodafoneTopup.serviceId"));
    int topupType = Integer.parseInt(request.getAttribute("topupType").toString());
    try {
        if (TopupServiceIntegrationStatus.orangeserviceStatus && topupType==orangeTopupServiceId) {
            trans = (TransactionDTO) request.getAttribute("orangeTopupResponse");

        }  else if (TopupServiceIntegrationStatus.etisalatserviceStatus && topupType == etisalatTopupServiceId) {
            trans = (TransactionDTO) request.getAttribute("etisalatTopupResponse");
        }else if (TopupServiceIntegrationStatus.vodafoneserviceStatus && topupType == vodafoneTopupServiceId) {
            trans = (TransactionDTO) request.getAttribute("vodafoneTopupResponse");
        }else {
            trans = MasaryManager.getInstance().getTransaction(transId); 
            MasaryManager.logger.info("TransId is "+trans.getTransId());
        }

    } catch (Exception ex) {
        session.setAttribute("ErrorMessage", "Detailed error code is:" + ex.getMessage());
        response.sendRedirect(CONFIG.APP_ROOT + "error.jsp");
        ex.printStackTrace();
        return;
    }
    DecimalFormat myFormatter = CONFIG.getFormater(session);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=CONFIG.getTransactionReport(session)%></title>
         
    </head>
    <BODY class="body" >
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <div class="content_body">
                <fieldset style="width: 50%; direction: rtl;" align="right">  
                    <legend align="right" ><font size="5"><%=CONFIG.getTransactionReport(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend> 
                <table border="1" style="width: 100%">
                    <tr>
                        <!-- رقم العمليه -->
                        <th scope="col"><%=CONFIG.getTransactionNumber(session)%></th>
                        <td><%=trans.getTransId()%> </td>
                        <%
                            GenericSellVoucherResponse voucherResponse = (GenericSellVoucherResponse) request.getAttribute("voucherResponse");
                            String s3 = "";
                            String s4 = "";
                            String s2 = "";
                            if (voucherResponse != null) {
                                s3 = voucherResponse.getVoucher();
                                s4 = voucherResponse.getSerialNumber();
                                s2 = "  ";
                            }
                            if (s2.equals("")) {
                                if (trans.getType().toLowerCase().contains("blabla topup")) {
                        %>
                        <th rowspan="12">
                            <div>
                                <applet  code="printApplet" name="0" archive="PrintApp.jar" width="0" height="0" > 
                                    <param name="serviceType" value="BlaBlaTopUp"/>
                                    <param name="v" value="<%=trans.getCustomerPayedName()%>"/>
                                    <param name="amount" value="<%=request.getParameter(CONFIG.AMOUNT)%>"/>          
                                    <param name="TID" value="<%=trans.getTransId()%>"/>
                                    <param name="from" value="<%=trans.getCustomerPayerName().trim().contains(" ") ? trans.getCustomerPayerName().trim().substring(0, trans.getCustomerPayerName().indexOf(" ")) : trans.getCustomerPayerName()%>"/>
                                    <param name="time" value="<%=trans.getDate().substring(11)%>"/>
                                    <param name="date" value="<%=trans.getDate().substring(0, 10)%>"/>
                                </applet>                         
                            </div>
                        </th>  
                        <%}
                        } else {
                            if (session.getAttribute("howSell").equals("Printing")) {
                                String vV = "";
                                String cmd = "";
                                String voucherPins = "";
                                String voucherSerials = "";
                                String isOffer = "";
                                String amounts = "";
                                for (int i = 0; i < voucherResponse.getVouchersData().size(); i++) {
                                    if (i != voucherResponse.getVouchersData().size() - 1) {
                                        voucherPins = voucherPins.concat(voucherResponse.getVouchersData().get(i).getVoucherPin() + "-");
                                        voucherSerials = voucherSerials.concat(voucherResponse.getVouchersData().get(i).getVoucherSn() + "-");
                                        isOffer = isOffer.concat(voucherResponse.getVouchersData().get(i).getIsOffer().toString() + "-");
                                        amounts = amounts.concat(voucherResponse.getVouchersData().get(i).getAmount().toString() + "-");
                                    } else {
                                        voucherPins = voucherPins.concat(voucherResponse.getVouchersData().get(i).getVoucherPin());
                                        voucherSerials = voucherSerials.concat(voucherResponse.getVouchersData().get(i).getVoucherSn());
                                        isOffer = isOffer.concat(voucherResponse.getVouchersData().get(i).getIsOffer().toString());
                                        amounts = amounts.concat(voucherResponse.getVouchersData().get(i).getAmount().toString());
                                    }
                                }
                        %>
                        <th rowspan="12">
                            <div>
                                <applet  code="printApplet" name="0" archive="PrintApp.jar" width="0" height="0" > 
                                    <param name="serviceType" value="<%=trans.getType().toLowerCase().contains("voda") ? "vodafone"
                                            : (trans.getType().toLowerCase().contains("mobi") ? "mobinil"
                                                    : trans.getType().toLowerCase().contains("one-card") ? "onecard" : trans.getType().toLowerCase().contains("marhaba")
                                                                    ? "marhaba" : trans.getType().toLowerCase().contains("etis") ? "etisalat" : trans.getType().toLowerCase().contains("blablavo") ? "blablavo" : "CashU_Voucher")%>"/>
                                    <param name="v" value="<%=vV%>"/>
                                    <param name="cmd" value="<%=cmd%>" />
                                    <param name="voch" value="<%= voucherPins%>"/>
                                    <param name="serial" value="<%= voucherSerials%>"/>
                                    <param name="amount" value="<%=amounts%>"/>   
                                    <param name="FEES" value="<%= (int) Double.parseDouble(request.getParameter("FEES"))%>"/>
                                    <%--<param name="TotalAmount" value="<%= (int) (Double.parseDouble(request.getParameter("FEES")) + trans.getAmount())%>"/>--%>
                                    <param name="TotalAmount" value="<%= (double) (trans.getAmount()+trans.getCommession()+trans.getFees())%>"/>
                                    <param name="TID" value="<%=trans.getTransId()%>"/>
                                    <param name="from" value="<%=trans.getCustomerPayerName().trim().contains(" ") ? trans.getCustomerPayerName().trim().substring(0, trans.getCustomerPayerName().indexOf(" ")) : trans.getCustomerPayerName()%>"/>
                                    <param name="time" value="<%=trans.getDate().substring(11)%>"/>
                                    <param name="date" value="<%=trans.getDate().substring(0, 10)%>"/>
                                    <param name="count" value="<%= voucherResponse.getVouchersData().size()%>"/>  
                                    <param name="IsOffer" value="<%= isOffer%>"/>

                                </applet>
                                <%} else {
                                    }%>
                            </div>
                        </th>
                        <% }%>
                    </tr>
                    <tr>
                        <th scope="col"><%=CONFIG.getFrom(session)%></th>
                            <%  if (session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {%>
                        <td><%=trans.getCustomerPayerName()%></td>
                        <% } else {%>
                        <td> <%=MasaryManager.getInstance().getEmployee((String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)).getEmployeeName(session)%></td>
                        <% }%>
                    </tr>
                    <%if ((session.getAttribute("howSell") == null) || (session.getAttribute("howSell").equals("sms"))) {%>
                    <tr>
                        <th scope="col"><%=CONFIG.getTo(session)%></th>
                        <td><%=trans.getCustomerPayedName()%></td>
                    </tr>
                    <% }%>
                     <tr>
                        <th scope="col"><%=CONFIG.getType(session)%></th>
                        <td><%=trans.getType()%></td>
                    </tr>
                    <tr>
                        <th scope="col"><%=CONFIG.getStatus(session)%></th>
                        <td><%=trans.getStatus()%></td>
                    </tr>

                    <tr>
                        <th scope="col"><%=CONFIG.getRechargeAmount(session)%></th>
                        <td><%=myFormatter.format(trans.getAmount())%></td>
                    </tr>

                    <tr>
                        <th scope="col"><%=CONFIG.getTaxValue(session)%></th>
                        <td><%=requestParameters.get("servicetax")%></td>
                    </tr>

                    <tr>
                        <th scope="col"><%=CONFIG.getUserAmount(session)%></th>
                        <td><%=requestParameters.get("UserAmount")%></td>
                    </tr>

                    <tr>
                        <th scope="col"><%=CONFIG.getDate(session)%></th>
                        <td><%=trans.getDate()%></td>
                    </tr>
                    <%if (s2.equals("")) {
                        } else {
                    %>
                    <tr>
                        <th scope="col"><%= CONFIG.getRecharge_No(session)%></th>
                            <td><%=session.getAttribute("howSell").equals("view") ? voucherResponse.getVouchersData().get(0).getVoucherPin()
                                    : (session.getAttribute("howSell").equals("sms") ? "Sent in SMS" : "Printed")%></td>
                    </tr>
                    <tr>
                        <th scope="col"><%= CONFIG.getSerial(session)%></th>
                        <td><%=session.getAttribute("howSell").equals("view") ? voucherResponse.getVouchersData().get(0).getVoucherSn()
                                : (session.getAttribute("howSell").equals("sms") ? "Sent in SMS" : "Printed")%></td>
                    </tr>

                    <tr>
                        <th scope="col"><%= CONFIG.getRecharge_Type(session)%></th>
                        <td><%= trans.getType().toLowerCase().contains("voda") ? "<img alt='' height='30' width='100' src='img/Vodafone_recharge.png' />"
                                : (trans.getType().toLowerCase().contains("mobi") ? "<img alt='' height='30' width='100' src='img/Mobinil_recharge.png' />"
                                        : (trans.getType().toLowerCase().contains("etis") ? "<img alt='' height='30' width='100' src='img/Etisalat_recharge.png' />" : ""))%></td>
                    </tr>
                    <% }%>
                    <tr>
                    <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%>  method="post"  name="admin_form" id="saveform">
                        <td colspan="2" align="center">
                            <input type="submit" name="btnSubmit" tabindex="5" value="OK" class="Btn">
                        </td>
                    </form>
                    </tr>
                </table>
                <details open="open">
                    <summary>
                        <%=(s2.equals("")) ? "قد يستغرق تنفيذ العملية بعض الوقت." : "يتم طباعة الكارت أو إرساله فى رسالة للعميل. "%>
                        
                        <% session.removeAttribute(CONFIG.REQUEST_PARAMETERS); %>
                    </summary>
                </details>
            </fieldset>
        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>