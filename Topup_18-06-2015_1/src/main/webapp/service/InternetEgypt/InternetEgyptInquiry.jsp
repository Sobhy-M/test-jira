<%-- 
       Document   : bill_internet_egypt_info
    Created on : 03/04/2017, 01:11:19 م
    Author     : Mustafa
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
    MasaryManager.logger.info("InternetEgypt");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    // MasaryManager manager=new MasaryManager();
%>
<%
    // //System.out.println("BillInquiry_Page");
    session = request.getSession();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getCustomerBillHead(request.getSession())%></title>
    </head>
    <body class="body">
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <font style="color: red; font-size: 15px;">${ErrorCode}</font>     
    <div class="content_body"  >
        <form name="dobillinquiry" action="InternetEgyptInquiryContoller" method="POST" >

            <input type="hidden" name="action" value="<%=CONFIG.ACTIONInternetEgyptGETINFO%>" />
            <fieldset style="width: 28%; direction: rtl;" align="right">  
                <legend align="right" ><font size="3"><%= CONFIG.getCustomerBillHead(session)%>
                        &nbsp;<%= CONFIG.getInternetEgyptServiceName(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>
                <table border="1" width="100%">
                    <tr>
                        <td><p align="right"><%=CONFIG.getinternetEgyptTitle(session)%> : <input id="custTopUpDalanceID" maxlength="11" pattern="^[0-9]\d*(\.\d{1,9})?"  type="text" required name="<%=CONFIG.PARAM_MSISDN%>" autocomplete="off" tabindex="2"></p>

                            <div id="custTopUpDalanceDiv" style="color: red; font-size: 12.5px;"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="btnSubmit" tabindex="3" value="<%=CONFIG.getCheck(session)%>" class="Btn"></td>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                    1-	ادخل رقم التليفون الأرضى يبدأ بكود المحافظة مثل 02 فى القاهرة، ثم اضغط استعلام لمعرفة قيمة الفاتورة المستحقة عليه"
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