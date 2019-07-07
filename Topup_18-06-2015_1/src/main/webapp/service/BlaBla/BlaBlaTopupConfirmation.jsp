<%-- 
    Document   : BlaBlaTopupConfirmation
    Created on : 09/05/2014, 10:36:42 م
    Author     : AYA
--%>

<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    String custId = null;
    if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    AgentDTO agent = MasaryManager.getInstance().getAgent(custId);
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">

        <title><%=CONFIG.getConfirm(request.getSession()) + " " + CONFIG.getCustomerBlaBlaTopUp(request.getSession())%></title>
        <script type="text/javascript">
            var ray = {
                ajax: function(st)
                {
                    document.getElementById("btnSubmit").disabled = true;
                    this.show('load');
                },
                hide: function(st)
                {
                    var load = Document.getElementById("load");
                    load.style = "display:none;";
                    F
                },
                show: function(el)
                {
                    this.getID(el).style.display = '';
                },
                getID: function(el)
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
    </head>
    <BODY class="body" onload="ray.hide();">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(request.getSession())%></div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="AssignBalanceCustomer" action="BlaBlaTopUController" method="POST" onsubmit="return ray.ajax()">
        <input type="hidden" name="action" value="<%=CONFIG.BlaBlaTopUpResult%>" />
        <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="33" />
        <fieldset style="width: 60%; direction: rtl;" align="right">  

            <legend align="right" ><font size="5"><%=CONFIG.getConfirm(request.getSession()) + "  " + CONFIG.getCustomerBlaBlaTopUp(request.getSession())%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend> 
            <table border="1" width="100%">
                <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                <tr>
                    <td><p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custTopUpDalanceID" value="<%= request.getParameter(CONFIG.AMOUNT)%>" style="background-color: #EDEDED; float: left;"/></p> 
                        <p align="right"><%=CONFIG.getMobileNumber(request.getSession())%> :<input id="custTopUpMobileId" type="text" name="<%=CONFIG.PARAM_MSISDN%>" readonly value="<%= request.getParameter(CONFIG.PARAM_MSISDN)%>" tabindex="2" style="background-color: #EDEDED; float: left;">
                        <div id="custMobileDiv"  ></div></p></br>
                    </td>
                    <td>
                        <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text"   name="Fees" readonly tabindex="1" id="Fees"  value="<%= request.getParameter("Fees")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="Commession"  readonly tabindex="1" id="Commession" value="<%= request.getParameter("Commession")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getBalance_Diff(request.getSession())%> :<input type="text"  name="Balance_Diff" readonly tabindex="1" id="Balance_Diff" value="<%= request.getParameter("Balance_Diff")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text"  name="DeductedAmount" readonly tabindex="1" id="DeductedAmount" value="<%= request.getParameter("DeductedAmount")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="btnSubmit" id="btnSubmit" tabindex="3" value="<%=CONFIG.getConfirm(request.getSession())%>" style="float: right">
                        <input type="button" name="MoreInfo" tabindex="3" value="<%=CONFIG.getBack(request.getSession())%>"  style="float: left" onclick="history.go(-1);">
                    </td>
                    <td><%=CONFIG.getNewBalance(request.getSession())%> : <%=myFormatter.format(agent.getServiceBalance(33)- Double.parseDouble(request.getParameter("DeductedAmount") ))%></td></tr>
            </table>
            <details open="open">
                <summary></summary>
                1-	لتأكيد تنفيذ العملية، اضغط على زرار التاكيد أما للرجوع للتعديل اضغط على زرار الرجوع . . </br></br>
                ملحوظه :-  </br>
                تأكد من أن المبلغ الذى أدخلته و رقم الموبايل التابع لحساب العميل صحيحين و أنك أدخلت رقم الموبايل يبدأ برقم 2..
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