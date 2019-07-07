<%-- 
    Document   : NewBiller
    Created on : Jun 18, 2012, 4:35:41 PM
    Author     : KEMO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
            session = request.getSession();
%>
<%
    Masary_Bill_Type bill_Type = (Masary_Bill_Type) request.getSession().getAttribute("New_BTC");
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Biller</title>
    </head>
    <body class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
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
    <form name="AddNewBiller" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" onsubmit="return validateBillerForm()">

        <input type="hidden" name="action" value="<%=CONFIG.ACTION_ADD_BTC_CONFIRM%>" />
        <input type="hidden" name="BILLER_ID" value="<%=bill_Type.getBILLER_ID()%>" />
        <input type="hidden" name="BILL_TYPE_ID" value="<%=bill_Type.getBILL_TYPE_ID()%>" />
        <div class="content_body"  ><br><br> 
            <table>
                <tr >
                    <th scope="col" ><%=CONFIG.getBiller_Name(session)%></th>
                    <td><%=bill_Type.getBILLER_ID()%></td>
                    <th rowspan="11">
                        <input type="submit" name="btnSubmit" tabindex="3" value="<%=CONFIG.getConfirm(session)%>" class="Btn">
                    </th>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getBill_Name(session)%></th>
                    <td><%= bill_Type.getBILL_NAME()%></td>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getPMT_Type(session)%></th>
                    <td><%=bill_Type.getPMT_TYPE()%></td>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getSERVICE_TYPE(session)%></th>
                    <td><%= bill_Type.getSERVICE_TYPE()%></td>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getServiceName(session)%></th>
                    <td><%= bill_Type.getSERVICE_NAME()%></td>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getARServiceName(session)%></th>
                    <td>
                        <input type="text" name="ServiceName" tabindex="1" id="ServiceName"/>
                        <br/>
                        <div id="CustTopUpCredHelp" >
                        </div>
                        <div id="ServiceNameDiv" style="color: red; font-size: 12.5px;"></div>
                    </td>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getBILL_LABLE(session)%></th>
                    <td><%= bill_Type.getBILL_LABLE()%></td>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getARBILL_LABLE(session)%></th>
                    <td>
                        <input type="text" name="BILL_LABLE" tabindex="1" id="BILL_LABLE"/>
                        <br/>
                        <div id="CustTopUpCredHelp" >
                        </div>
                        <div id="BILL_LABLEDIV" style="color: red; font-size: 12.5px;"></div>
                    </td>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getPartial(session)%></th>
                    <td><%= bill_Type.isIS_PART_ACC()%></td>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getFraction(session)%></th>
                    <td><%= bill_Type.isIS_FRAC_ACC()%></td>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getOver(session)%></th>
                    <td><%= bill_Type.isIS_OVER_ACC()%></td>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getAdvanced(session)%></th>
                    <td><%= bill_Type.isIS_ADV_ACC()%></td>
                </tr>
            </table>
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
