<%-- 
    Document   : LegitimacyAssociationPay
    Created on : May 22, 2017, 3:56:50 PM
    Author     : Abdelsabor
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
    DonationAgentPaymentRespponseDto agentPaymentRespponseDto = (DonationAgentPaymentRespponseDto) request.getAttribute("agentPaymentRespponseDto");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    session = request.getSession();
    String feesInquery = request.getParameter("feesInquery");
    String [] arr = feesInquery.split("-");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    Double Amount = Double.parseDouble(request.getParameter("donationValue"));
    Double DedducedAmount = Amount - Double.parseDouble(arr[0]);
    Double ServiceCost = Double.parseDouble(arr[1]);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    String ProjectName="";
    int OperationID = Integer.parseInt(request.getParameter("OPERATION_ID"));
  if(OperationID == 1)
    {
        ProjectName = CONFIG.getLegitimacyAssociationSocityProject(session);
    }
    if(OperationID == 2)
    {
        ProjectName = CONFIG.getLegitimacyAssociationSadkahProject(session);
    }
    if(OperationID == 3)
    {
        ProjectName = CONFIG.getLegitimacyAssociationZakahProject(session);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getLegitimacyAssociationDescription(session)%></title>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
    </head>
            <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/gam3yaShar3ya.png"  width="200" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">الجمعية الشرعية الرئيسية</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> عملية ناجحة</td><td style="text-align: right;padding-right:25px;width: 60%; ">حاله العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=agentPaymentRespponseDto.getTRANSACTION_ID()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم التاجر</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ التبرع</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت التبرع</td> </tr><tr>');
                printwindow.document.write('<tr><th colspan="2">-----------------------------------</th></tr>');
                printwindow.document.write('<tr><td  style="padding-left:15px;width: 50%;"><%=ProjectName%></td><td style ="text-align: right;padding-right:25px;width: 50%;" >نوع التبرع</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= request.getParameter("donationValue")%></td><td style="text-align: right;padding-right:25px;width: 50%;">مبلغ التبرع </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=arr[1]%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >تكلفة الخدمة</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= Double.parseDouble(request.getParameter("donationValue")) + Double.parseDouble(arr[1])%> </td><td style="text-align: right;padding-right:25px;width: 50%;" >إجمالي المبلغ</td> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994 </th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
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
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <th colspan="2" style="text-align: center"><p><%=CONFIG.getLegitimacyAssociationDescription2(request.getSession()) + ProjectName%></p></th>
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
                        <input type="submit" value="<%=CONFIG.getPrinting(session)%>"  id="submitbutton" onclick="setDivPrint();"  />
                    </td>
                </tr>
                <tr style="display: none">
                    <th colspan="2"><img src="./img/gam3yaShar3ya.png" alt="wasletKher" width="50" height="50"></th>
                </tr>
            </table>
            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
