<%-- 
    Document   : InfoPage
    Created on : Jul 12, 2017, 12:58:42 PM
    Author     : Ahmed Khaled
--%>

<%@page import="com.masary.integration.dto.SmsMisrInquiryDTO"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.util.Date"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>


<%
    String rolePage = CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp";
    SmsMisrInquiryDTO smsMisrInquiryResponse = (SmsMisrInquiryDTO) request.getSession().getAttribute("smsMisrInquiryResponse");
    String confirmationCode = smsMisrInquiryResponse.getConfirmationCode();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= CONFIG.getCustomerBillHead(session)%>&nbsp;<%= CONFIG.SMSMISR_SERVICE_NAME%></title>
        <style>
            input[type=text] {
                width: 45%;
                background-color: #EDEDED;
                float: left;
                padding-left: 40px;
                margin-left: 50px;
            }
            input[type=number] {
                width: 2px;
                background-color: #EDEDED;
                float: left;
                padding-left: 150px;
                margin-left: 160px;
                text-align: right;
            }
            p{
                font-size: 17px;
                margin-right: 20px;
                font-weight: bold;
            }
        </style>

    </head>
    <body class="body" >

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="dobillinquiry" action="SMSMISRController" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_SMSMISR_CONFIRMATION%>" />
        <div class="content_body"  >
            <fieldset style="width: 50%; direction: rtl;" align="right">  
                <legend align="right" ><font size="4"><%= CONFIG.SMSMISR_SERVICE_NAME%></font><img src="img/CashIn.ico"  width="20" height="20" ></legend>               
                <table  border="1" width="100%">
                    <tr>
                        <td width="50%">
                            <p align="right"><%=CONFIG.SMSMISR_Confirmation_Code%> : 
                                <input type="text" name="confirmationCode" readonly tabindex="1"  value="<%=confirmationCode%>" />
                            </p>

                        </td>

                    </tr>
                    <tr>
                        <td colspan="3">
                            <div align="center">
                                <input type="submit" name="btnSubmit" tabindex="1"  value="<%=CONFIG.getContinue(session)%>" style="float: right" class="Btn"  >
                                <input type="button" name="Back" tabindex="2" value="<%=CONFIG.getBack(session)%>"   onclick="history.go(-1);">
                                <input type="button" name="btnBack" tabindex="3"  value="الغاء" onclick="window.location.href = '<%=rolePage%>';"  class="Btn" style="float: left;">
                            </div>
                        </td>
                    </tr>
                </table>
            </fieldset> 
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
