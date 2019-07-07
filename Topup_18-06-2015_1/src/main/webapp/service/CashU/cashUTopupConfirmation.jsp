<%-- 
    Document   : CashUTopupConfirmation
    Created on : 09/05/2014, 10:36:42 م
    Author     : AYA
--%>

<%@page import="com.masary.database.dto.CashUResponse"%>
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
     CashUResponse cashU_Response = new CashUResponse();
    try {
        cashU_Response = (CashUResponse) session.getAttribute("cashUResponse");
    } catch (Exception e) {
    }
   
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">

        <title><%=CONFIG.getConfirm(request.getSession()) + " " + CONFIG.getCustomerCashUTopUp(request.getSession())%></title>
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
            <%  
            if(request.getParameter(CONFIG.AccountCahsU)!=null){%>
              function  b(){
                 var s=document.getElementById("dmmm");
                 s.style.display= "none";
              }  
            
           <% 
           
            }
            
        String amount= session.getAttribute("Amount").toString();
           %>
            
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
    <BODY class="body" onload="b()">
        <script type="text/javascript" src="../../img/CheckForms.js"></script>
        <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(request.getSession())%></div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <%try {
            String Error = request.getSession().getAttribute("ErrorCode").toString();
    %>
    <font style="color: red; font-size: 15px;"><%=request.getAttribute("ErrorCode").toString()%></font>    
        <%
                request.getSession().removeAttribute("ErrorCode");
            } catch (Exception e) {
            }
        %>
        <form name="AssignBalanceCustomer" action="CashUTopUpController" method="POST" onsubmit="return ray.ajax()">
            <input type="hidden" name="action" value="<%=CONFIG.CashUTopUpResult%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="40" />
        <input type="hidden" name="Fees" value="<%=request.getParameter("Fees")%>" id="Fees"/>
        <!--<input type="hidden" name="<%=CONFIG.AMOUNTEGP%>"  value=""  />-->
        <input type="hidden" name="<%=CONFIG.AMOUNT%>"  value="<%=amount%>"  />
        <fieldset style="width: 60%; direction: rtl;" align="right">  

            <legend align="right" ><font size="5"><%=CONFIG.getConfirm(request.getSession()) + "  " + CONFIG.getCustomerCashUTopUp(request.getSession())%></font> </legend> 
            <table border="1" width="100%">
                <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                <tr>
                    <td>
                        <p align="right" ><%=CONFIG.getCustomerCashUNum(request.getSession())%>:<input id="CustomerChooseAccounttest" type="text" name="<%=CONFIG.AccountCahsU%>" readonly value="<%=cashU_Response.getUserId() %>" tabindex="2" style="background-color: #EDEDED; float: left;  width: 120px;"></p> 
                        <div id="dmmm" ><p align="right"  ><%=CONFIG.getCustomerCashUName(request.getSession())%> <input id="CustomerChooseUserNameTest" type="text" name="<%=CONFIG.UserNameCahsU%>" readonly value="<%=cashU_Response.getUserName()%>" tabindex="2" style="background-color: #EDEDED; float: left;  width: 120px;"></p> 
                        <p align="right"><%=CONFIG.getEmailAddress(request.getSession())%>: <input id="CustomerChooseEmailTest" type="text" name="<%=CONFIG.Email%>" readonly value="<%= cashU_Response.getEmail() %>" tabindex="2" style="background-color: #EDEDED; float: left;  width: 120px;"></p> </div>
                        <p align="right"><%=CONFIG.getCustomerCashUHolderName(request.getSession())%>: <input id="CustomerholderNameTest" type="text" name="<%=CONFIG.holderNameCahsU%>" readonly value="<%=cashU_Response.getHolderName()%>" tabindex="2" style="background-color: #EDEDED; float: left;  width: 120px;"></p> 
                        <p align="right"><%=CONFIG.getAmount(request.getSession())%>: <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custTopUpDalanceID" value="<%= amount%>" style="background-color: #EDEDED; float: left; width: 120px;"/></p> 
                        <!--<p align="right"><%=CONFIG.getAmount(request.getSession())%>:  <input id="custTopUpDalanceID" type="text" name="<%=CONFIG.AMOUNT%>" value="<%= request.getParameter(CONFIG.AMOUNTDolar)%>" tabindex="2" style=" background-color: #EDEDED; float: left; width: 120px;" readonly ></p>-->
                        <div id="custMobileDiv"  ></div></br>
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
                        <input type="button" name="MoreInfo" tabindex="3" value="<%=CONFIG.getBack(request.getSession())%>"  style="float: left" onclick="history.go(-1);" >
                    </td>
                    <td><%=CONFIG.getNewBalance(request.getSession())%> : <%=myFormatter.format(agent.getServiceBalance(33) - Double.parseDouble(request.getParameter("DeductedAmount")))%></td></tr>
            </table>
            <details open="open">
                <summary></summary></br>
                سيظهر لك البيانات التى قمت بإدخالها فى الشاشة الأولى بالإضافة إلى (اسم صاحب الحساب) و (رقم حسابك فى كاش يو) بالإضافة الى المبلغ الى قمت بإختياره بالجنيه المصرى و الرصيد المكافئ له بالدولار بالإضافة إلى تكلفة الخدمة و هى 5 جنيه و بالتالى إجمالى المبلغ المستحق على العميل بالجنيه المصرى، تأكد من صحة البيانات الظاهرة أمامك ، ثم اضغط زرار (تأكيد) و إذا كان هناك خطأ فى البيانات اضغط زرار (رجوع) للتعديل.
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