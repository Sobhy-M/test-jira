<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.integration.dto.FastLinkPaymentResponse"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    
    
    
    FastLinkPaymentResponse fastLinkPaymentResponse=(FastLinkPaymentResponse) request.getAttribute("fastLinkPaymentResponse");
    
    
    double amount= fastLinkPaymentResponse.getBillAmount();
    double appliedFees= fastLinkPaymentResponse.getAppliedFees();
    double toBepaid=fastLinkPaymentResponse.getToBepaid();
    double deductedAmount=fastLinkPaymentResponse.getTransactionAmount();
    String customerName=  fastLinkPaymentResponse.getClientName();
    double appliedFeesAddedTax= fastLinkPaymentResponse.getAppliedFeesAddedTax();
   String phoneNumber=fastLinkPaymentResponse.getPhoneNumber();
   String globalTrxId=fastLinkPaymentResponse.getGlobalTrxId();
%>

<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPrinting_Receipt(request.getSession())%></title>
        <script>
            function setDivPrint() {
               var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="./img/masarylog.png"  width="200" height="115"></td></tr>');        
                printwindow.document.write('<tr><th colspan="2"><%=CONFIG.get_FastLinkNameAr(request.getSession())%></th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=globalTrxId%></td><td style="text-align: right;padding-right:25px;width: 50%;">:رقم العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= date1%>  <%= time%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >:<%=CONFIG.get_FastLinkDate(request.getSession())%></td></tr>');
                printwindow.document.write('<tr><th colspan="2">________________________________________ </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=phoneNumber%></td><td style = "text-align: right;padding-right:25px;width:50%;" >:<%=CONFIG.getPhoneNumber(request.getSession())%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=customerName%></td><td style = "text-align: right;padding-right:25px;width:50%;">:<%=CONFIG.getCustomerName(request.getSession())%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=amount%></td><td style = "text-align: right;padding-right:25px;width:50%;">:<%=CONFIG.get_FastLinkAmountAr(request.getSession())%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=appliedFeesAddedTax%></td><td style = "text-align: right;padding-right:25px;width:50%;">:<%=CONFIG.get_FastLinkAplliedFees(request.getSession())%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=toBepaid%></td><td style = "text-align: right;padding-right:25px;width:50%;">:<%=CONFIG.get_FastLinkTotalAmount(request.getSession())%></td> </tr><tr>');
                printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> قد يستغرق تنفيذ العملية ساعتين فى حالة بطئ الشبكات <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> شكرا لاستخدامكم مصارى لخدمات الدفع الذكية <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> خدمة عملاء مصارى:16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
                window.location.replace("2.jsp");
            }
        </script>
     </head>
    <body class="body">
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>
        <div>
         <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <th colspan="2" style="text-align: center"><p><%=CONFIG.get_FastLinkBill(request.getSession())%></p></th>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.get_FastLinkBillAmount(session)%>:</p>
                    </td>
                    <td>
                        <input  type="text" readonly style="background-color: #EDEDED;" value="<%=amount %>" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getServiceCost(session)%>:</p>
                    </td>
                    <td>
                        <input type="text" name="ServiceCost" value="<%=appliedFees%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getThetotalamountpaid(session)%>:</p>
                    </td>
                    <td>
                        <input type="text" name="TotalAmountPayable" value="<%=toBepaid %>" readonly style="background-color: #EDEDED;" >
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
                                    <input type="text" name="DedducedAmount" value="<%=deductedAmount %>" readonly style="background-color: #EDEDED;"  >
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
                               <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value="<%= CONFIG.get_FastLinkPrintClose(session)%>" onclick="setDivPrint();" /></td></tr>

            </table>
        </div>
                        <img style="visibility: hidden" src="./img/masarylog.png"/>
        
        <!--</form>-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
