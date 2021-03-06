<%-- 
    Document   : bill_inquiry
    Created on : 01/05/2012, 07:10:57 م
    Author     : Michael
--%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
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
    Masary_Bill_Type BTC = MasaryManager.getInstance().getBTC(request.getSession().getAttribute("serv_id").toString());
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
         <script src="./js/ElzakaHome/ElzakaHomeJS.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getCustomerBillHead(request.getSession())%></title>
    </head>
    <body class="body">
        <div>
            <script type="text/javascript" src="img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")){%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>
        <font style="color: red; font-size: 15px;">${ErrorCode}</font>     
        <div class="content_body"  >
            <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" onsubmit="return validateBillphone();">
                <input type="hidden" name="action" value="<%=CONFIG.ACTION_Get_Bill_Inquiry_Res%>" />
                <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("serv_id").toString()%>" />
                <fieldset style="width: 28%; direction: rtl;" align="right">  
                    <legend align="right" ><font size="3"><%= BTC.getStrBTCName(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>
                    <table border="1" width="100%">
                        <tr>
                            <td><p align="right"><%=BTC.get_BILL_LABLE(session)%> : <input id="custTopUpDalanceID"  type="text" title="<%=CONFIG.GetElzakaHomePhoneNoTitle(session)%>" required onblur="ThePhoneNotMatch(this.id)"  maxlength="11" name="<%=CONFIG.PARAM_MSISDN%>" autocomplete="off" tabindex="2"></p>

                                <div id="custTopUpDalanceDiv" style="color: red; font-size: 12.5px;"></div>
                            </td>
                        </tr>
                        <tr>
                            <td><input type="submit" name="btnSubmit" tabindex="3" value="<%=CONFIG.getCheck(session)%>" class="Btn" onclick="return SubmitButton()"> </td>
                        </tr>
                    </table>
                    <details open="open">
                        <summary></summary>
                        <span style="white-space: nowrap;"><%=CONFIG.GetElzakaHomePhoneNoTitle(session)%></span>
                    </details>
                </fieldset> 
            </form>
        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
