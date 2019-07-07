<%-- 
    Document   : bill_payment
    Created on : Jun 27, 2012, 1:09:08 PM
    Author     : KEMO
--%>

<%@page import="com.masary.database.dto.Bill_Response"%>
<%@page import="com.masary.database.dto.CommissionsTransaction"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Bill_Amounts"%>
<%@page import="com.masary.database.dto.Masary_Bill_Response"%>
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
//    double billBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 1000);
    Masary_Bill_Type BTC = (Masary_Bill_Type) request.getSession().getAttribute("BTC");
    Bill_Response bill_response = (Bill_Response) session.getAttribute("commession_response");

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

        <title><%=CONFIG.getCustomerConfBillHead(request.getSession())%></title>
    </head>
    <body onload="ray.hide();">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(session)%></div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <font style="color: red; font-size: 15px;">${ErrorCode}</font>    

    <form name="dobillinquiry" action="Electricity_PaymentController" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_payment_Inquiry_Req_Confirm%>" />
        <input type="hidden" name="Fees" value="<%= request.getParameter("Fees")%>"/>
        <input type="hidden" name="COMMESSION" value="<%= request.getParameter("COMMESSION")%>"/>
        <input type="hidden" name="Bill_Date" value="<%= request.getParameter("Bill_Date")%>"/>
        <div class="content_body"  >
            <fieldset style="width: 50%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%= CONFIG.getCustomerConfBillHead(session)%>&nbsp;<%= BTC.getStrBTCName(session)%></font><img src="img/CashIn.ico"  width="20" height="20"> </legend>               

                <table border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <tr> 
                        <td>
                            <p align="right">
                                <%=CONFIG.getMemberNumber(session)%>:
                            </p>
                        </td>
                        <td>
                            <input type="text" name="BILLING_ACCOUNT" readonly tabindex="1" id="BILLING_ACCOUNT" value="<%= request.getParameter("AccountNumber")%>" style="background-color: #EDEDED;float: right;"/>  
                        </td>
                    </tr>      

                    <tr> 
                        <td>
                            <p align="right"> <%=CONFIG.getMemberName(session)%>:</p>
                        </td>
                        <td>
                            <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%= new String(request.getParameter("CUSTOMER_NAME").getBytes("ISO-8859-1"), "utf-8")%>" style="background-color: #EDEDED;float: right;"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getbillAmount(session)%>:</p>
                        </td>
                        <td>
                            <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="<%= request.getParameter("AMOUNT")%>"   style="background-color: #EDEDED;float: right; ">
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p align="right"><%="عنوان المشترك "%></p>
                        </td>
                        <td>
                            <input id="Address" type="text" value="<%= new String(request.getParameter("Address").getBytes("ISO-8859-1"), "utf-8")%>"  name="Address" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr> 
                        <td>
                            <p align="right"><%=CONFIG.getTotalAmountWithTax(session)%>:</p>
                        </td>
                        <td>
                            <input type="text" name="TotalAmount" readonly tabindex="1" id="TotalAmount" value="<%= Double.parseDouble(request.getParameter("AMOUNT"))+Double.parseDouble(request.getParameter("Fees"))%>" style="background-color: #EDEDED;float: right; "/>
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
                        <%=CONFIG.getmakeSureParticipantNumber(session)%>
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
