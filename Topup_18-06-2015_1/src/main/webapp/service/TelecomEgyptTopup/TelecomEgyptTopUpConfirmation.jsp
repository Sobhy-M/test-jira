<%--
    Document   : ReCharge
    Created on : 27/06/2009, 01:09:17 م
    Author     : Melad
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
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
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double vodafonBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 10);
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getConfirm(request.getSession()) + " " + CONFIG.getCustomerTelecomTopUp(request.getSession())%></title>
        <script type="text/javascript">
            var ray = {
                ajax: function (st)
                {
                    document.getElementById("btnSubmit").disabled = true;
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
                background-image:url(https://cdn.e-masary.net/app/img/loading.gif);
                background-position:50% 40%;
            }
        </style>
    </head>
    <BODY class="body" onload="ray.hide();">
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
        <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(request.getSession())%></div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="AssignBalanceCustomer" action="TeTopupController" method="POST" onsubmit="return ray.ajax()">
        <input type="hidden" name="<%=CONFIG.CONFIRM%>" value="True" />
        <fieldset style="width: 70%; direction: rtl;" align="right">  
            <legend align="right" ><font size="5"><%=CONFIG.getConfirm(request.getSession()) + "  " + CONFIG.getCustomerTelecomTopUp(request.getSession())%></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20" > </legend> 
            <table border="1" style="width: 100%">
                <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                <tr>
                    <td><p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custTopUpDalanceID" value="<%= new String(request.getParameter(CONFIG.AMOUNT).getBytes("ISO-8859-1"), "utf-8")%>" style="background-color: #EDEDED; float: left;"/></p> 
                        <p align="right"><%=CONFIG.getMobileNumber(request.getSession())%> :<input id="custMobileConfirmation" type="text" name="<%=CONFIG.PARAM_MSISDN%>" readonly value="<%= request.getParameter(CONFIG.PARAM_MSISDN)%>" tabindex="2" style="background-color: #EDEDED; float: left;">
                        <div id="custMobileDiv"  ></div></p></br>
                    </td>
                    <td>
                        <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text"   name="Fees" readonly tabindex="1" id="Fees"  value="<%= request.getParameter("Fees")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="Commession"  readonly tabindex="1" id="Commession" value="<%= request.getParameter("Commession")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getServiceTaxe(request.getSession())%> :<input type="text" name="servicetax"  readonly tabindex="6" id="servicetax"  value="<%= request.getParameter("servicetax") %>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getBalance_Diff(request.getSession())%> :<input type="text"  name="Balance_Diff" readonly tabindex="1" id="Balance_Diff" value="<%= request.getParameter("Balance_Diff")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text"  name="DeductedAmount" readonly tabindex="1" id="DeductedAmount" value="<%= request.getParameter("DeductedAmount")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        <p align="right"><%=CONFIG.getUserAmount(request.getSession())%> :<input type="text"  name="UserAmount" readonly tabindex="1" id="UserAmount" value="<%= request.getParameter("UserAmount")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>

                        </br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="btnSubmit" id="btnSubmit" tabindex="3" value="تأكيد وطباعة" style="float: right" onclick="">
                        <input type="button" name="MoreInfo" tabindex="3" value="<%=CONFIG.getBack(request.getSession())%>"  style="float: left" onclick="history.go(-1);">
                    </td>
                    <td><%=CONFIG.getNewBalance(request.getSession())%> : <%=myFormatter.format(vodafonBalance - Double.parseDouble(request.getParameter("DeductedAmount")))%></td></tr>
            </table>
            <details open="open">
                <summary></summary>
                1-	لتأكيد تنفيذ العملية، اضغط على زر التاكيد أما للرجوع للتعديل اضغط على زر الرجوع . </br></br>
                ملحوظه :-  </br>
                تأكد من أن المبلغ الذى أدخلته و رقم الموبايل صحيحين و أنك إخترت الشبكة المطابقة لنوع خط الموبايل.
            </details>

        </fieldset>
    </form>

    <!-- content body -->
</div>
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>