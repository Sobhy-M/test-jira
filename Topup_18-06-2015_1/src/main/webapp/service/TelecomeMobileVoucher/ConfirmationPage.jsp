<%--
    Document   : ReCharge
    Created on : 12/09/2017, 01:09:17 م
    Author     : Ahmed Khaled
--%>

<%@page import="com.masary.utils.BuildUUID"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="javax.print.PrintService"%>
<%@page import="java.awt.print.PrinterJob"%>
<%@page import="java.util.ArrayList"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    int serviceId = Integer.parseInt((String) session.getAttribute("SERVICE_ID").toString());
    double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), serviceId);
    

%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <style type="text/css">
            a.tooltip {
                outline:none;
            }

            a.tooltip span {
                z-index:10;
                display:none;
                padding:14px 20px;
                margin-top:-30px;
                margin-left:28px;
                width:240px;
                line-height:16px;
                color:#0033CC;
            }
            a.tooltip:hover span {
                display:inline;
                position:absolute;
                color:#0066CC;      
                background:#fffAF0;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.TelecomEgyptServiceName%></title>
        <script type="text/javascript">
            var ray = {
                ajax: function (st)
                {
                    document.getElementById("btnSubmit").disabled = true;
                    this.show('load');
                },
                hide: function (st)
                {
                    var load = document.getElementById("load");
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
                background-image:url(https://cdn.e-masary.net/app/img/loading.gif);
                background-position:50% 40%;
            }
        </style>
    </head>
    <BODY class="body" onload="
            ray.hide();">
        <div>
            <script type="text/javascript" src="img/CheckForms.js"></script>
            <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(request.getSession())%></div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <div class="content_body">
                <div>
                    <form name="AssignBalanceCustomer" action="<%=CONFIG.APP_ROOT%>TelecomeEgyptVoucherController" method="POST" onsubmit="return ray.ajax()">
                    <input type="hidden" name="action" value="<%=CONFIG.ACTION_TELECOMEEGYPT_PAYMENT%>" />
                    <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%=serviceId%>" />
                    <input type="hidden" name="FEES" value="<%=request.getParameter("Fees")%>" />
                    <input type="hidden" name="TopupDenmo" id="TopupDenmo" value="<%= request.getParameter("TopupDenmo")%>" />
                    <input type="hidden" name="topupdemo" id="topupdemo" value="<%= request.getParameter("topupdemo")%>" />
                    <input type="hidden" name="<%=CONFIG.CONFIRM%>" value="True" />
                    <input type="hidden" value="<%=request.getParameter(CONFIG.PARAM_CATEGORY_ID)%>" name="<%=CONFIG.PARAM_CATEGORY_ID%>" >

                    <th><H3 id="javaMsg" style="color: red; font-size: 18px"></H3></th>
                    <th><H3 id="printerMsg" style="color: red; font-size: 18px"></H3></th>
                    <th><a style="color: red; font-size: 18px" id="javaLink"></a></th>
                    <div id="testApplet"></div>
                    <fieldset style="width: 70%; direction: rtl;" align="right">  
                        <legend align="right" ><font size="5"><%=CONFIG.getConfirm(request.getSession()) + " " + CONFIG.TelecomEgyptServiceName + "  "%></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20"  no-repeat scroll 100% 50% transparent;"> </legend> 
                        <table border="1" style="width: 100%">
                            <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                            <th><p>حسابك</p></th>
                            <tr>
                                <td> 

                                    <p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custTopUpDalanceID" value="<%= session.getAttribute("voucherAmount").toString()%>" style="background-color: #EDEDED; float: left;"/></p> 

                                    <div id="custMobileDiv"></div></p>
                                    <p align="right">طريقة البيع : <input type="text" name="Type" readonly tabindex="1" id="Type" value="طباعة" style="background-color: #EDEDED; float: left;"/></p> 
                                    <br><br>
                                </td>
                                <td>
                                    <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text"   name="Fees" readonly tabindex="1" id="Fees"  value="<%= request.getParameter("Fees")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                    <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="Commession"  readonly tabindex="1" id="Commession" value="<%= request.getParameter("Commession")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                    <p align="right"><%=CONFIG.getBalance_Diff(request.getSession())%> :<input type="text"  name="Balance_Diff" readonly tabindex="1" id="Balance_Diff" value="<%= request.getParameter("Balance_Diff")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                    <p align="right"><%=CONFIG.getAddedTax(request.getSession())%> :<input type="text" name="AddedTax" value="<%=request.getParameter("AddedTax")%>"  readonly tabindex="1" id="AddedTax" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                    <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text"  name="DeductedAmount" readonly tabindex="1" id="DeductedAmount" value="<%= request.getParameter("DeductedAmount")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                    <p align="right"><%=CONFIG.getUserAmount(request.getSession())%> :<input type="text"  name="UserAmount" readonly tabindex="1" id="UserAmount" value="<%= request.getParameter("UserAmount")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>

                                    </br>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="submit" name="btnSubmit" id="btnSubmit"  tabindex="3" value="تأكيد وطباعة" style="float: right">
                                    <input type="button" name="MoreInfo" tabindex="3" value="<%=CONFIG.getBack(request.getSession())%>"  style="float: left" onclick="history.go(-1);">
                                </td>
                                <td><%=CONFIG.getNewBalance(request.getSession())%> : <%=myFormatter.format(serviceBalance - Double.parseDouble(request.getParameter("DeductedAmount")))%></td></tr>
                        </table>
                        <details open="open">
                            
                                ملاحظات :- 
                                <br>
                                •	 برجاء التأكد من إن جهازك يدعم تشغيل برنامج "جافا" قبل بدء الطباعة
                                <br>
                                •	برجاء التأكد من عمل الطابعة الخاصة بك قبل بدء الطباعة 

                            <summary></summary>
                        </details>
                    </fieldset>
                </form>
            </div>
        </div>
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </div>
</body>
</html>
