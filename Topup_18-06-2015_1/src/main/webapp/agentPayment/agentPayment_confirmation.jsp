<%-- 
    Document   : agentPayment
    Created on : Sep 6, 2015, 12:01:08 PM
    Author     : kyanni
--%>

<%@page import="javax.servlet.jsp.jstl.core.Config"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>


<%
    MasaryManager.logger.info("BillInquiry_Page");
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
    ServiceDTO service = MasaryManager.getInstance().getService(request.getSession().getAttribute("SERVICE_ID").toString());
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            function checkParam() {
                var mobileNumber = document.'<%=CONFIG.PARAM_MSISDN%>'.value;
                var amount = document.'<%=CONFIG.AMOUNT%>'.value;
                if (mobileNumber == null || mobileNumber == '' || amount == null || amount == '') {
                    return false;
                }
            }
        </script>
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
        <title><%=CONFIG.getConfirm(session)%>&nbsp;<%=service.getStrServiceName(session)%></title>
        
    </head>
    <body onload="ray.hide()">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(session)%></div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include> <%}%>
        </div>
    <%try {
            String Error = request.getSession().getAttribute("ErrorCode").toString();
    %>
    <font style="color: red; font-size: 15px;"><%=request.getSession().getAttribute("ErrorCode").toString()%></font>
    <%
            request.getSession().removeAttribute("ErrorCode");
        } catch (Exception e) {
        }
    %>
    <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" onsubmit="return ray.ajax();checkParam();">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_Do_Agent_Payment_Confirm%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("SERVICE_ID").toString()%>" />
        <input type="hidden" name="<%=CONFIG.PROVIDER_ID%>" value="<%=request.getSession().getAttribute("PROVIDER_ID").toString()%>" />
        <input type="hidden" name="<%=CONFIG.OPERATION_ID%>" value="<%=request.getSession().getAttribute("OPERATION_ID").toString()%>" />
        <input type="hidden" name="national_ID" value="<%=request.getParameter("national_ID")%>">
        <div class="content_body"  >
            <fieldset style="width: 60%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%=CONFIG.getConfirm(session)%>&nbsp;<%=service.getStrServiceName(session)%></font><img src="img/CashIn.ico"  width="20" height="20"> </legend> 
                <table border="1">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td>

                            <p align="right">
                                <%=CONFIG.getAmount(session)%> : 
                                <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1" id="custAmountID" value="<%= request.getParameter(CONFIG.AMOUNT)%>" readonly tabindex="1" id="custAmountID" style="background-color: #EDEDED; float: left;"/>
                            <div id="custAmountDiv" name="custAmountDiv"></div>
                            </p> 
                            
                            <p align="right"><%=CONFIG.getBillRefNumORCN(session)%> :
                                <input id="custTopUpMSISDNID" type="text" name="<%=CONFIG.PARAM_MSISDN%>" readonly value="<%= request.getParameter(CONFIG.PARAM_MSISDN)%>" tabindex="2" style="background-color: #EDEDED; float: left;"> 
                            <div id="custMobileDiv"></div>
                            </p>
                            </td>
                        
                        <td>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="<%= request.getParameter("Fees")%>" readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="<%= request.getParameter("Commession")%>" readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getBalance_Diff(session)%> :<input type="text" name="Balance_Diff" value="<%= request.getParameter("Balance_Diff")%>" readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="<%= request.getParameter("DeductedAmount")%>" readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            </br>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <input type="submit" name="btnSubmit" id="btnSubmit" tabindex="3" value="<%=CONFIG.getConfirm(session)%>" style="float: right">
                        <input type="button" name="MoreInfo" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);">
                        </td>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                    1-	تأكد أن رقم الموبايل و المبلغ الذين تم إدخالهما صحيحين ، ثم اضغط تأكيد لإتمام العملية.
                </details>
            </fieldset> 
        </div><!-- End of Table Content area-->
    </form>
</body>
</html>
