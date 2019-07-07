<%-- 
    Document   : PayLongLifeEgypt
    Created on : Mar 15, 2016, 12:25:35 PM
    Author     : Masary
--%>

<%@page import="java.util.logging.Level"%>
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
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(request.getSession().getAttribute("SERVICE_ID").toString(), session.getAttribute(CONFIG.PARAM_PIN).toString());
    Double Amount = Double.parseDouble(request.getParameter("donationValue"));
    Double DedducedAmount = Amount + ratePlan.getApplied_fees()-ratePlan.getFixedAmount();
    Double ServiceCost = ratePlan.getApplied_fees();
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    String ProjectName="";
    String totalPaiedAmount = request.getParameter("TotalAmountPayable") ;
    int OperationID = Integer.parseInt(session.getAttribute("OPERATION_ID").toString());
    DonationAgentPaymentRespponseDto paymentRespponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationPaymentResponse");
    if(OperationID == 1)
    {
        ProjectName = CONFIG.getElormanSocityProject(session);
    }
    if(OperationID == 2)
    {
        ProjectName = CONFIG.getElormanZakahProject(session);
    }
    if(OperationID == 3)
    {
        ProjectName = CONFIG.getElormanSadkahProject(session);
    }
    if(OperationID == 4)
    {
        ProjectName = CONFIG.getElormanHospitalProject(session);
    }
    if(OperationID == 5)
    {
        ProjectName = CONFIG.getElormanBuffaloProject(session);
    }
    if (OperationID == 6) {
        ProjectName = CONFIG.getElormanSadkahMeatProject(session);
    }
    if (OperationID == 7) {
        ProjectName = CONFIG.getSakOfImportedMeat(session);
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
    }
    if (OperationID == 8) {
        ProjectName = CONFIG.getSaksmallClaves(session);
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
    }
    if (OperationID == 9) {
        ProjectName = CONFIG.getSakOldCalvesMeat(session);
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
    }
     if (OperationID == 104) {
        ProjectName = CONFIG.getSakOfImportedMeat(session);

    }
    if (OperationID == 105) {
        ProjectName = CONFIG.getSakOldCalvesMeat(session);

    }
    if (OperationID == 106) {
        ProjectName = CONFIG.getSaksmallClaves(session);
    }
    if (OperationID ==211) {
        ProjectName = CONFIG.getBoxRamdan90(session);
    }
    if (OperationID ==212) {
        ProjectName = CONFIG.getBoxRamdan150(session);
    }
    if (OperationID ==213) {
        ProjectName = CONFIG.getBoxRamdan220(session);
    }
    if (OperationID == 151) {
        ProjectName = CONFIG.getSakMostawradSa8er1950(session);
    }
    if (OperationID == 152) {
        ProjectName = CONFIG.getSakMostawradKaber2550(session);
    }
%>
<!DOCTYPE html>
<html>
      
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getElormanDescription(session)%></title>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
    </head>
    <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><th colspan="2">شركة مصاري</th></tr>');
                printwindow.document.write('<tr><th colspan="2"><%=CONFIG.getElormanDescription2(request.getSession()) %></th></tr>');
                printwindow.document.write('<tr><th colspan="2"><%=ProjectName %></th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=paymentRespponseDto.getTRANSACTION_ID()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CONFIG.getDonationDate(session)%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time%></td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CONFIG.getDonationTime(session)%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=new String(request.getParameter("DonatorName").getBytes("ISO-8859-1"), "utf-8")%></td><td style = "text-align: right;padding-right:25px;width:50%;" ><%=CONFIG.getElormanDonatorName(session)%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getParameter("DonatorPhone")%></td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CONFIG.getDontaorPhoneNumber(session)%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getParameter("donationValue")%></td><td style="text-align: right;padding-right:25px;width: 50%;"> مبلغ التبرع </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getParameter("donationValue")%></td><td style="text-align: right;padding-right:25px;width: 50%;" ><%=CONFIG._getTotalAmount(session)%></td> </tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> عملية ناجحة</td><td style="text-align: right;padding-right:25px;width: 60%; ">حاله العملية</td> </tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=ServiceCost%></td><td style ="text-align: right;padding-right:25px;width: 50%;" > تكلفة الخدمة </td></tr>');
                
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
                window.location.replace("2.jsp");
            }
        </script>

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
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_ELORMAN_PAY%>" />
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <th colspan="2" style="text-align: center"><p><%=CONFIG.getElormanDescription2(request.getSession()) + ProjectName%></p></th>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getTheDonationAmountInPounds(session)%>:</p>
                    </td>
                    <td>
                        <input  type="text" readonly style="background-color: #EDEDED;" value="<%=request.getParameter("donationValue")%>" >
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
                        <input type="text" name="TotalAmountPayable" value="<%=totalPaiedAmount%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <table>
                            <tr>
                                <td style="border: none">
                                    <p><%= CONFIG.getIsDeddued(session)%>:</p>
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
                        <input type="submit" value="<%=CONFIG.getPrinting(session)%>"  id="submitbutton" onclick="setDivPrint()"  />
                    </td>
                </tr>
            </table>
            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>