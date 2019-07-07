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
    // MasaryManager manager=new MasaryManager();
%>
<%
    // //System.out.println("BillInquiry_Page");
    session = request.getSession();
    Masary_Bill_Type BTC = MasaryManager.getInstance().getBTC(request.getSession().getAttribute("serv_id").toString());
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getCustomerBillHead(request.getSession())%></title>


    </head>
    <body class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <font style="color: red; font-size: 15px;">${ErrorCode}</font>     
    <div class="content_body"  >
        <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" onsubmit="return validateBillphone();">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_Get_Bill_Inquiry_Res%>" />
            <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("serv_id").toString()%>" />
            <fieldset style="width: 28%; direction: rtl;" align="right">  
                <legend align="right" ><font size="3"><%=(BTC.getBILL_TYPE_ID() == 55551 || BTC.getBILL_TYPE_ID() == 55552) ? "" : CONFIG.getCustomerBillHead(session)%>
                        &nbsp;<%= BTC.getStrBTCName(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>
                <table border="1" width="100%">
                    <tr>
                        <td><p align="right"><%=BTC.get_BILL_LABLE(session)%> : <input id="custTopUpDalanceID" type="text" required name="<%=CONFIG.PARAM_MSISDN%>" tabindex="2"></p>

                            <div id="custTopUpDalanceDiv" style="color: red; font-size: 12.5px;"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="btnSubmit" tabindex="3" value="<%=CONFIG.getGo(session)%>" class="Btn"></td>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                   	<%=request.getSession().getAttribute("serv_id").toString().equals("113") || request.getSession().getAttribute("serv_id").toString().equals("111")|| request.getSession().getAttribute("serv_id").toString().equals("112")
                            ? "ادخل رقم الموبايل المراد الإستعلام عن فاتورته.<br/>" : ((request.getSession().getAttribute("serv_id").toString().equals("124") || request.getSession().getAttribute("serv_id").toString().equals("117") )
                            ? "ادخل رقم التليفون الأرضى يبدأ بكود المحافظة مثل 02 فى القاهرة، ثم اضغط استعلام لمعرفة قيمة الفاتورة المستحقة عليه"
                            : ((request.getSession().getAttribute("serv_id").toString().equals("55551")|| request.getSession().getAttribute("serv_id").toString().equals("55552") ? "برجاء ادخال رقم الموبايل"  :  "ادخل رقم بطاقة الرقم القومى للطالب أو الرقم الجامعى للطالب، ثم اضغط استعلام لمعرفة المصروفات الجامعية المستحقة عليه.")))%>
                                      
                    <%=request.getSession().getAttribute("serv_id").toString().equals("113") ? " ملحوظة :- </br>  •"
                            + " يمكن الإستعلام عن فواتير موبايل إتصالات أو شرائح داتا إتصالات أو خطوط إنترنت إتصالات ADSL."
                            + " " : ""%>
                </details>
            </fieldset> 
        </form>
    </div><!-- End of Table Content area-->
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
