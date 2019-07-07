<%-- 
    Document   : BULK_SMS_REPORT
    Created on : Apr 28, 2014, 4:50:08 PM
    Author     : KEMO
--%>

<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.BulkSMSReportDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.common.CONFIG"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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

    List<BulkSMSReportDTO> bulkSMSReportlist =  (List<BulkSMSReportDTO>) request.getSession().getAttribute("BulkList");
     
    int page_v = 1;
    int pages = 0;
    int rowCount = bulkSMSReportlist.size()+1;
    double x = rowCount / 9.0;
    int y = (int) x;
    double z = x - y;
    if (z > 0) {
        pages = ++y;
    } else {
        pages = y;
    }
    
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getSend_Bulk_SMS_Report(session)%></title>

    </head>
    <body>
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <div id="content">
            <center>
        <form  name="formElem" id="formElem6" action="<%=CONFIG.APP_ROOT%>customer/BULK_SMS_REPORT.jsp" method="POST">
                <!--<fieldset style="width: 105%; direction: rtl;" align="right">-->  
                <table  border="1" style="width: 60%;">
                    <thead>
                        <tr>
                            <th style="color: #000;"><%=CONFIG.getRequestId(session)%></th>
                            
                            <th style="color: #000;"><%=CONFIG.getMESSAGE(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getCOUNT_MOBILES(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getRESPONSE(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getMobileNumber(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getSTATUS_CODE(session)%></th>
                            <th style="color: #000;"><%=CONFIG.getREFUND_TXN(session)%></th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if (rowCount > 0) {
                                for (BulkSMSReportDTO txn : bulkSMSReportlist) {
                        %>

                        <tr >
                            <td style="background-color: #D1ECD1; color: #000;"><%=txn.getRequestID()%></td>
                            
                            <td style="background-color: #D1ECD1; color: #000;"><%=txn.getMessage()%></td>
                            <td style="background-color: #D1ECD1; color: #000;"><%=txn.getCountMobiles()%></td>
                            <td style="background-color: #D1ECD1; color: #000;"><%=txn.getResponse()%></td>
                            <td style="background-color: #D1ECD1; color: #000;"><%=txn.getMsisdn()%></td>
                            <%
                                String statusTXN = "";
                                /*
                                if(txn.getStatusCode() == 200 ){
                                    statusTXN = CONFIG.getSUCCESSFUL(session);
                                } else if (txn.getStatusCode() == 200 ){
                                    if(txn.getNretry() < 10){
                                        statusTXN = CONFIG.getPENDING(session);
                                    } else if (txn.getNretry() == 10){
                                        statusTXN = CONFIG.getFAILED(session);
                                    }
                                }
 *                              */
                                if(txn.getStatusCode() == -200 && txn.getNretry() < 10){
                                    statusTXN = CONFIG.getPENDING(session);
                                } else if (txn.getStatusCode() == -200 && txn.getNretry() == 10){
                                    statusTXN = CONFIG.getFAILED(session);
                                } else if (txn.getStatusCode() == 200) {
                                    statusTXN = CONFIG.getSUCCESSFUL(session);
                                }
                            %>
                            <td style="background-color: #D1ECD1; color: #000;"><%=statusTXN%></td>
                            <td style="background-color: #D1ECD1; color: #000;"><%=txn.getRefundTxn()%></td>
                          
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr><td colspan="14" style="background-color: #FFECEC; color: #000; text-align: center;"><%=CONFIG.getVC_NDF(session)%></td></tr>    
                            <%
                                }
                            %>
                        
                    </tbody>
                </table>        
            </form>
        </center>
        <div style="clear: both;">&nbsp;</div>
        </div>
    </body>
</html>
