<%-- 
    Document   : bill_payment
    Created on : Jun 27, 2012, 1:09:08 PM
    Author     : KEMO
--%>

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
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
//    double billBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 1000);
    double billBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), Integer.parseInt(request.getSession().getAttribute("serv_id").toString()));
    Masary_Bill_Type BTC = (Masary_Bill_Type) request.getSession().getAttribute("BTC");
    double fees = Double.parseDouble(request.getSession().getAttribute("fees").toString());
    double billValue = Double.parseDouble(request.getSession().getAttribute("billValue").toString());
    String participantName = new String(request.getSession().getAttribute("participantName").toString());
    String participantNumber = request.getParameter("BILLING_ACCOUNT");

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

    <form name="dobillinquiry" action="SouthDeltaController" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_payment_Inquiry_Req_Confirm%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("serv_id").toString()%>" />
        <input type="hidden" name="ServiceBalance" value="<%=billBalance%>" id="ServiceBalance"/>
        <input type="hidden" name="operation" value="elcPayment" id="operation"/>
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
                            <input type="text" name="BILLING_ACCOUNT" readonly tabindex="1" id="BILLING_ACCOUNT" value="<%= participantNumber %>" style="background-color: #EDEDED;float: right;"/>  
                        </td>
                    </tr>      

                    <tr> 
                        <td>
                            <p align="right"> <%=CONFIG.getMemberName(session)%>:</p>
                        </td>
                        <td>
                            <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%= participantName %>" style="background-color: #EDEDED;float: right;"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getbillAmount(session)%>:</p>
                        </td>
                        <td>
                            <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="<%= billValue %>"   style="background-color: #EDEDED;float: right; ">
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getFees(session)%>:</p>
                        </td>
                        <td>
                            <input type="text" name="Fees" readonly tabindex="1" id="Fees" value="<%= fees%>" style="background-color: #EDEDED;float: right; "/>
                        </td>
                    </tr>

                    <tr> 
                        <td>
                            <p align="right"><%=CONFIG.getTotalAmount(session)%>:</p>
                        </td>
                        <td>
                            <input type="text" name="TotalAmount" readonly tabindex="1" id="TotalAmount" value="<%= billValue + fees %>" style="background-color: #EDEDED;float: right; "/>
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
