<%-- 
    Document   : LongLifeEgyptConfirm
    Created on : Mar 15, 2016, 11:37:21 AM
    Author     : Masary
--%>

<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String custId = null;
    
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(request.getSession().getAttribute("SERVICE_ID").toString(), session.getAttribute(CONFIG.PARAM_PIN).toString());
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    Double DonationVal = Double.parseDouble(request.getParameter("donationValue")) + ratePlan.getApplied_fees();
    Double ServiceCost = ratePlan.getApplied_fees()+(ratePlan.getApplied_fees_per()*Double.parseDouble(request.getParameter("donationValue"))/100);
    Double commission = ratePlan.getFixedAmount()+(ratePlan.getCommission()*Double.parseDouble(request.getParameter("donationValue"))/100);
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getBcfeDescription(session)%></title>
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
        
        <script type="text/javascript">
            function disableDoubleSubmission(){
                document.getElementById("submitbutton").disabled = true;
            }
        </script>
        
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
        <form action="BcfePayController" method="POST" style="font-weight: bold" onsubmit="disableDoubleSubmission()">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_BCFE_PAY%>"/>
            <table style="width: 50% ; margin-left: auto ; margin-right: auto">
                <tr>
                    <th colspan="2" style="text-align: center"><p><%=CONFIG.getBcfeDescription(request.getSession())%></p></th>
                </tr>
                <tr class="secondTD">
                    <td colspan="2" class="secondTD">
                        <table>
                            <tr>
                                <td style="border: none ; background-color: transparent; padding: 0px" class="secondTD">
                                    <p><%=CONFIG.getWillDonateTO(session)%></p>
                                </td>
                                <td style="border: none ; background-color: transparent; padding: 0px" class="secondTD">
                                    <label>&nbsp;<%=CONFIG.getDToBcfeService(session)%></label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%=CONFIG.getTheDonationValue(session)%>:</p>
                    </td>
                    <td>
                        <div>
                            <div>
                                <input type="text" name="donationValue" value="<%=request.getParameter("donationValue")%>" readonly style="background-color: #EDEDED;" >
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%=CONFIG.getServiceCost(session)%></p>
                    </td>
                        <td>
                            <input type="text" name="ServiceCost" value="<%=ServiceCost%>" readonly style="background-color: #EDEDED;" >
                        </td>
                </tr>
                    <tr>
                        <td>
                                    <p><%=CONFIG.getCommessionMaan(session)%></p>
                                </td>
                                <td>
                                    <input type="text" name="commission" value="<%=commission%>" readonly style="background-color: #EDEDED;" >
                                </td>
                            </tr>
                <tr>         
                   <td>
                        <p><%=CONFIG.getTotalAmountPayable(session)%></p>
                   </td>
                                <td>
                                    <div>
                                        <div>
                                            <input type="text" name="TotalAmountPayable" value="<%=DonationVal%>" readonly style="background-color: #EDEDED;" >
                                        <div>
                                    </div>
                                </td>
                           
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getDonorname(session)%>:</p>
                    </td>
                    <td>
                        <input   type="text" name="DonatorName" value="<%=new String(request.getParameter("DonatorName").getBytes("ISO-8859-1"), "utf-8")%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getDontaorPhoneNumber(session)%>:</p>
                    </td>
                    <td>
                        <input type="text" readonly name="DonatorPhone" value="<%=request.getParameter("DonatorPhone")%>" style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <div>
                                <div>
                                    <input type="submit" value="<%=CONFIG.getGo(session)%>"  id="submitbutton" onclick="" />
                                </div>
                        </div>
                    </td>
                    <td>
                        <div>
                            <input type="button" value="<%=CONFIG.getBack(session)%>"  id="submitbutton" onclick="history.go(-1);"  />
                        </div>
                    </td>
                    
                </tr>
            </table>
        </form>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

    </body>
</html>
