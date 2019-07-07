<%--
    Document   : ReCharge
    Created on : 27/06/2009, 01:09:17 م
    Author     : Melad
--%>

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
<%@page import="java.util.Map"%>

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
    int serviceId = Integer.parseInt((String) session.getAttribute(CONFIG.PARAM_SERVICE_ID));
    String serviceName = MasaryManager.getInstance().getService(serviceId, ((ArrayList<CustomerServiceDTO>) session.getAttribute("services"))).getServiceName(session);
    double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), serviceId);
    
    Map<String, String> requestParameters =	
		(Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);
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
        <title><%=serviceName%></title>
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
                    <form name="AssignBalanceCustomer" action="<%=CONFIG.APP_ROOT%>Web" method="POST" onsubmit="return ray.ajax()">
                    <input type="hidden" name="action" value="<%=CONFIG.ACTION_CUSTOMER_TOPUP%>" />
                    <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%=serviceId%>" />
                    <input type="hidden" name="FEES" value="<%=requestParameters.get("Fees")%>" />
                    <input type="hidden" name="TopupDenmo" id="TopupDenmo" value="<%= requestParameters.get("TopupDenmo")%>" />
                    <input type="hidden" name="topupdemo" id="topupdemo" value="<%= requestParameters.get("topupdemo")%>" />
                    <input type="hidden" name="vouchersNumber" id="vouchersNumber" value="<%= requestParameters.get("vouchersNumber")%>" />
                    
                    <input type="hidden" name="<%=CONFIG.CONFIRM%>" value="True" />
                    <input type="hidden" value="<%=requestParameters.get(CONFIG.PARAM_CATEGORY_ID)%>" name="<%=CONFIG.PARAM_CATEGORY_ID%>" >
                    <%if (session.getAttribute("howSell").equals("Printing")) {%>
                    <th><H3 id="javaMsg" style="color: red; font-size: 18px"></H3></th>
                    <th><H3 id="printerMsg" style="color: red; font-size: 18px"></H3></th>
                    <th><a style="color: red; font-size: 18px" id="javaLink"></a></th>
                        <%}%>
                        <%if (session.getAttribute("howSell").equals("Printing")) {%>
                        <%}%>
                    <div id="testApplet"></div>
                    <fieldset style="width: 70%; direction: rtl;" align="right">  
                        <legend align="right" ><font size="5"><%=CONFIG.getConfirm(request.getSession()) + " " + serviceName + "  "%></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20"  no-repeat scroll 100% 50% transparent;"> </legend> 
                        <table border="1" style="width: 100%">
                            <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                            <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                            <tr>
                                <td> 
                                    <%if (requestParameters.get(CONFIG.AMOUNT).equals("5.01") && serviceId == 10) {%>
                                    <p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custTopUpDalanceID" value="5" style="background-color: #EDEDED; float: left;"/></p> 

                                    <%} else if (requestParameters.get(CONFIG.AMOUNT).equals("5.05") && serviceId == 15) {%>
                                    <p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text"  readonly tabindex="1" id="custTopUpDalanceID" value="5" style="background-color: #EDEDED; float: left;"/></p> 
                                    <input type="hidden" name="<%=CONFIG.AMOUNT%>" value="5.05" />   
                                    <%} else if (requestParameters.get(CONFIG.AMOUNT).equals("10.05") && serviceId == 15) {%>
                                    <p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" readonly tabindex="1" id="custTopUpDalanceID" value="10" style="background-color: #EDEDED; float: left;"/></p> 
                                    <input type="hidden" name="<%=CONFIG.AMOUNT%>" value="10.05" />
                                    <%} else if (requestParameters.get(CONFIG.AMOUNT).equals("10.01") && serviceId == 15) {%>
                                    <p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custTopUpDalanceID" value="مارد الدقايق" style="background-color: #EDEDED; float: left;"/></p> 
                                        <%} else if (requestParameters.get(CONFIG.AMOUNT).equals("10.02") && serviceId == 15) {%>
                                    <p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custTopUpDalanceID" value="ماردالشبكات" style="background-color: #EDEDED; float: left;"/></p> 
                                        <%} else if (requestParameters.get(CONFIG.AMOUNT).equals("10.03") && serviceId == 15) {%>
                                    <p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custTopUpDalanceID" value="مارد النت" style="background-color: #EDEDED; float: left;"/></p> 

                                    <%} else if ((requestParameters.get(CONFIG.AMOUNT).equals("5.01") || requestParameters.get(CONFIG.AMOUNT).equals("10.01") || requestParameters.get(CONFIG.AMOUNT).equals("25.01") || requestParameters.get(CONFIG.AMOUNT).equals("15.01")) && serviceId == 17) {%>
                                    <p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custTopUpDalanceID" value="<%= requestParameters.get("TopupDenmo")%>" style="background-color: #EDEDED; float: left;"/></p> 

                                    <%} else {%>
                                    <p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custTopUpDalanceID" value="<%= requestParameters.get("TopupDenmo")%>" style="background-color: #EDEDED; float: left;"/></p> 
                                        <%}%>
                                    <p align="right"><%=CONFIG.getMobileNumber(request.getSession())%> :<input id="custTopUpMobileId" type="text" name="<%=CONFIG.PARAM_MSISDN%>" readonly value="<%= session.getAttribute("howSell").equals("sms") ? requestParameters.get(CONFIG.PARAM_MSISDN) : ""%>" tabindex="2" style="background-color: #EDEDED; float: left;">
                                    <p align="right"><%=CONFIG.getVoucherNO(request.getSession())%> :<input id="custTopUpVoucherNo" type="text" name="<%=CONFIG.PARAM_VOUCHERNO%>" readonly  value="<%= !session.getAttribute("howSell").equals("Printing") ? "1" : requestParameters.get("vouchersNumber")%>" tabindex="2" style="background-color: #EDEDED; float: left;">
                                    <div id="custMobileDiv"></div></p>
                                    <p align="right">طريقة البيع : <input type="text" name="Type" readonly tabindex="1" id="Type" value="<%= session.getAttribute("howSell").equals("sms") ? "رسالة" : (session.getAttribute("howSell").equals("Printing") ? "طباعة" : "ظهور ع الشاشة")%>" style="background-color: #EDEDED; float: left;"/></p> 
                                    <br><br>
                                </td>
                                <td>
                                    <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text"   name="Fees" readonly tabindex="1" id="Fees"  value="<%= requestParameters.get("Fees")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                    <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="Commession"  readonly tabindex="1" id="Commession" value="<%= requestParameters.get("Commession")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                    <p align="right"><%=CONFIG.getBalance_Diff(request.getSession())%> :<input type="text"  name="Balance_Diff" readonly tabindex="1" id="Balance_Diff" value="<%= requestParameters.get("Balance_Diff")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                    <p align="right"><%=CONFIG.getAddedTax(request.getSession())%> :<input type="text" name="AddedTax" value="<%=requestParameters.get("AddedTax")%>"  readonly tabindex="1" id="AddedTax" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                    <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text"  name="DeductedAmount" readonly tabindex="1" id="DeductedAmount" value="<%= requestParameters.get("DeductedAmount")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                    <p align="right"><%=CONFIG.getUserAmount(request.getSession())%> :<input type="text"  name="UserAmount" readonly tabindex="1" id="UserAmount" value="<%= requestParameters.get("UserAmount")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>

                                    </br>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="submit" name="btnSubmit" id="btnSubmit"  tabindex="3" value="<%=CONFIG.getConfirm(request.getSession())%>" style="float: right">
                                    <input type="button" name="MoreInfo" tabindex="3" value="<%=CONFIG.getBack(request.getSession())%>"  style="float: left" onclick="history.go(-1);">
                                </td>
                                <td><%=CONFIG.getNewBalance(request.getSession())%> : <%=myFormatter.format(serviceBalance - Double.parseDouble(requestParameters.get("DeductedAmount")))%></td></tr>
                        </table>
                        <details open="open">
                            <summary></summary>
                            ملحوظه :-  </br>
                            •
                            قبل الطباعة تأكد من انك قمت بتحميل الجافا و تسطيبها و غلق الجهاز و إعادة تشغيله.
                            إذا كنت أول مرة تستخدم الطباعة من مصارى أو قد واجهتك شكلة فى الطباعة من قبل أو قمت بعمل أى تغييرات لها علاقة بالطابعة من فضلك اضغط على زرر (اختبر طابعتك) للتأكد من أن الطابعة تعمل، و إذا لم يطبع لا تضغط على زرار التأكيد و اتصل بمصارى لمعالجة المشكلة فوراً.  
                            <%if (serviceId == 28) {%>
                            سيظهر لك فئة الكارت التى قمت بإختيارها بالجنيه المصرى فى الشاشة الأولى  بالإضافة إلى تكلفة الخدمة و هى 5 جنيه و بالتالى إجمالى المبلغ المستحق على العميل بالجنيه المصرى، تأكد من فئة الكارت التى تريد إصدراها ثم اضغط زرار (تأكيد) و إذا كان هناك خطأ فى فئة الكارت من فضلك اضغط (رجوع) للتعديل
                            <%}%>
                        </details>
                    </fieldset>
                </form>
                
                <% session.removeAttribute(CONFIG.REQUEST_PARAMETERS); %>
                
            </div>
        </div>
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </div>
</body>
</html>