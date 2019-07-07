<%-- 
    Document   : PayLongLifeEgypt
    Created on : Mar 15, 2016, 12:25:35 PM
    Author     : Masary
--%>

<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.DonationAgentPaymentRespponseDto"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    session = request.getSession();
    Masary_Bill_Type BTC = MasaryManager.getInstance().getBTC(String.valueOf(session.getAttribute("SERVICE_ID").toString()));
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(request.getSession().getAttribute("SERVICE_ID").toString(), session.getAttribute(CONFIG.PARAM_PIN).toString());
    Double ServiceCost = ratePlan.getApplied_fees()+(ratePlan.getApplied_fees_per()*Double.parseDouble(request.getParameter("TheDonationVal"))/100);
    Double Commession = (ratePlan.getCommission()*Double.parseDouble(request.getParameter("TheDonationVal"))/100)+ratePlan.getFixedAmount();
    Double DedducedAmount = Double.parseDouble(request.getParameter("TheDonationVal"))+(ServiceCost-Commession);
    DonationAgentPaymentRespponseDto paymentRespponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationPaymentResponse");
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    String TheOperation = session.getAttribute(CONFIG.OPERATION_ID).toString();
    String ProjectName="";
    String ProjectName2="";
    if(TheOperation.equals("1"))
    {
        ProjectName = CONFIG.getVirusCProject(request.getSession());
        ProjectName2 = CONFIG.GetVirusC(request.getSession());
    }
    else if(TheOperation.equals("2"))
    {
        ProjectName = CONFIG.getDevelopmentOfSlumsProject(request.getSession());
        ProjectName2 = CONFIG.GetDevelopmentOfSlums(request.getSession());
    }
    else if(TheOperation.equals("3"))
    {
        ProjectName = CONFIG.getRepayDebtorsDebtProject(request.getSession());
        ProjectName2 = CONFIG.GetRepayDebtorsDebt(request.getSession());
    }
    else
    {
         ProjectName = CONFIG.getVirusCtreatmentProject(request.getSession());
         ProjectName2 = CONFIG.GetVirusCTreatment(request.getSession());
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getLongLifeEgyptProject(session)%></title>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
    </head>
    <body>
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>
        <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST" style="font-weight: bold">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_LONG_LIFE_EGYPT_Confirmation%>" />
            <h2 align="center" ><%=CONFIG.getLongLifeEgyptProject(request.getSession())%></h2>
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <th colspan="2" style="text-align: center"><p><%=ProjectName%></p></th>
                </tr>
                <tr>
                    <td style="border: none">
                        <p><%= CONFIG.getTheDonationAmountInPounds(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <input  type="text" readonly style="background-color: #EDEDED;" value="<%=request.getParameter("TheDonationVal")%>" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getServiceCost(session)%>:</p>
                    </td>
                    <td>
                        <input type="text" name="ServiceCost" value="<%=ServiceCost%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getThetotalamountpaid(session)%>:</p>
                    </td>
                    <td>
                        <input type="text" name="TotalAmountPayable" value="<%=request.getParameter("TotalAmountPayable")%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <table>
                            <tr>
                                <td style="border: none">
                                    <p><%= CONFIG.getWillbededucted(session)%>:</p>
                                </td>
                                <td style="border: none">
                                    <input type="text" name="DedducedAmount" value="<%=DedducedAmount%>" readonly style="background-color: #EDEDED;"  >
                                </td>
                                <td style="border: none">
                                    <p><%= CONFIG.getFromTheBalanceOfYouWallet(session)%></p>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <p><%= CONFIG.getServiceState(session)%>:</p>
                    </td>
                    <td>
                        <label><%=CONFIG.getSuccessful(session)%></label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getServiceDate(session)%>:</p>
                    </td>
                    <td>
                        <label><%=date1%></label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getServiceTime(session)%>:</p>
                    </td>
                    <td>
                        <label><%=time%></label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="secondTD">
                        <input type="submit" value="<%=CONFIG.GetOk(session)%>"  id="submitbutton" onclick=""  />
                    </td>
                </tr>
            </table>
            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>