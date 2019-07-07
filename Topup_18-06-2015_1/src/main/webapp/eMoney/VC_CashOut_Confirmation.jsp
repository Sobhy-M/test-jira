<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
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
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getVodafone_Cash(session)%>-<%=CONFIG.getVodafone_cash_OUT(session)%>-<%=CONFIG.getVC_Cash_Out_Confirmation(session)%></title>
        <script language="javascript" type="text/javascript" src="bill/datetimepicker.js"></script>
        <script type="text/javascript">
            var ray = {
                ajax: function()
                {
                    document.getElementById("btnSubmit").disabled = true;
                    this.show('load');
                },
                hide: function()
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
    </head>
    <BODY class="body" onload="ray.hide();">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <div id="content">
            <center>
                <form name="formElem" id="formElem" action="<%=CONFIG.APP_ROOT%>eMoneyControler" method="POST" onsubmit="return ray.ajax()">
                <fieldset style="width: 54%; direction: rtl;" align="right">  
                    <legend align="right" ><font size="5"><%=CONFIG.getVC_Cash_Out_Confirmation(session)%>&nbsp; </font><img src="img/CashOut.ico"  width="20" height="20" style="background: url('CashOut.ico') no-repeat scroll 100% 50% transparent;"> </legend> 
                    <input type="hidden" name="action" value="<%=CONFIG.CHECKOUT_CONFIRMATION%>" />
                    <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                    <table border="1">
                        <th><%=CONFIG.getINFO_Required(session)%></th>
                        <th><%=CONFIG.getMerchantCommession(session)%></th>
                        <tr>
                            <td><p align="right"><%=CONFIG.getAmount(session)%> : <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custAmountID" value="<%= request.getParameter(CONFIG.AMOUNT)%>" style="background-color: #EDEDED; float: left;"/>
                                <div id="custAmountDiv" name="custAmountDiv"></div></p> 
                                <p align="right"><%=CONFIG.getMobileNumber(session)%> :<input id="custMobile" type="text" name="<%=CONFIG.PARAM_MSISDN%>" readonly value="<%= request.getParameter(CONFIG.PARAM_MSISDN)%>" tabindex="2" style="background-color: #EDEDED; float: left;">
                                <div id="custMobileDiv"  ></div></p></br>
                            </td>
                            <td>
                                <p align="right"><%=CONFIG.getFees(session)%> :<input type="text"   name="Fees" readonly tabindex="1" id="Fees"  value="<%= request.getParameter("Fees")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession"  readonly tabindex="1" id="Commession" value="<%= request.getParameter("Commession")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getAddAmount(session)%> :<input type="text"  name="DeductedAmount" readonly tabindex="1" id="DeductedAmount" value="<%= request.getParameter("DeductedAmount")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                </br>
                            </td>
                        </tr>
                        <tr><td >
                                <input type="submit" name="btnSubmit" id="btnSubmit" tabindex="3" value="<%=CONFIG.getConfirm(session)%>" >
                            </td>
                            <td><input type="button" name="MoreInfo" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);"></td></tr>
                    </table>
                    <details open="open">
                        <summary></summary>

                        1-	يظهر لك الرقم المراد السحب منه .  </br>
                        2-	يظهر لك المبلغ المراد سحبه. </br>
                        3-	يظهر لك عموله السحب الخاصه بالعمليه . </br>
                        4-	لتاكيد تنفيذ العمليه اضغط على زر التاكيد اما للرجوع للتعديل اضغط على زر الرجوع . </br></br>
                        ملحوظة :-  </br>
                        •	عمولة السحب يتم الحصول عليها من مصارى وليس من العميل. </br>
                        •	يجب على العميل بعد تنفيذ طلب السحب أن يقوم بالإتصال بـ (#1*7000*) من اليسار لليمين لإتمام العملية و إدخال الرقم السرى الخاص به للموافقة على عملية السحب منه. </br>

                    </details>

                </fieldset> 
            </form>
        </center>
    </div>
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div>
</body>
</html>

