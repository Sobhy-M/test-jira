<%-- 
    Document   : MersalConfirmation
    Created on : Dec 04, 2016, 12:44:08 PM
    Author     : hammad
--%>

<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String custId = null;
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(request.getSession().getAttribute("SERVICE_ID").toString(), session.getAttribute(CONFIG.PARAM_PIN).toString());
    Double ServiceCost = ratePlan.getApplied_fees() + ((ratePlan.getApplied_fees_per() / 100) * Double.parseDouble(request.getParameter("donationValue")));
    Double DonationVal = Double.parseDouble(request.getParameter("donationValue")) + ServiceCost;
    Double commission = ratePlan.getFixedAmount() + ((ratePlan.getCommission() / 100) * Double.parseDouble(request.getParameter("donationValue")));
    String Operation = session.getAttribute(CONFIG.OPERATION_ID).toString();
    
    String ProjectName = "";
    if (Operation.equals("1")) {
        ProjectName = CONFIG.HOSPITAL_25_DONATION_AR;
    } else if (Operation.equals("2")) {
        ProjectName = CONFIG.HOSPITAL_25_ZAKAH_AR;
    } else if (Operation.equals("3")) {
        ProjectName = CONFIG.HOSPITAL_25_SADAKAH_AR;
    } else if (Operation.equals("4")) {
        ProjectName = CONFIG.HOSPITAL_25_SEASON1_AR;
    } else if (Operation.equals("5")) {
        ProjectName = CONFIG.HOSPITAL_25_SEASON2_AR;
    } else if (Operation.equals("6")) {
        ProjectName = CONFIG.HOSPITAL_25_SEASON3_AR;
    } else {
        ProjectName = CONFIG.HOSPITAL_25_SEASON4_AR;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.HOSPITAL_25_SERVICE%></title>
        <script type="text/javascript" src="img/CheckForms.js"></script>
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
        <form action="HospitalPayController" method="POST" style="font-weight: bold">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_HOSPITAL_25_PAY%>" />
            <h2 align="center" ><%=CONFIG.HOSPITAL_25_SERVICE%></h2>
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <!--<th colspan="3" style="text-align: center"><p></p></th>-->
                    
                    <td style="border: none">
                        <p>نوع التبرع: </p>
                    </td>
                    <td style="border: none">
                        <div>
                            <div>
                                <input readonly autocomplete="off" type="text" value="<%=ProjectName %>" >
                            </div>
                        </div>
                    </td>
                    
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getDonorPhone(session)%>:</p>
                    </td>
                    <td>
                        <input type="text" readonly name="DonatorPhone" value="<%=request.getParameter("DonatorPhone")%>" style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%=CONFIG.getTheDonationValue(session)%>:</p>
                    </td>
                    <td>
                        <div>
                            <div>
                                <input type="text" name="TheDonationVal" value="<%=request.getParameter("donationValue")%>" readonly style="background-color: #EDEDED;" >
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
                        <input type="text" name="Commession" value="<%=commission%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                
                <tr>         
                    <td>
                        <p><%=CONFIG.getTotalAmountPayable(session)%></p>
                    </td>
                    <td>
                        <input type="text" name="TotalAmountPayable" value="<%=DonationVal%>" readonly style="background-color: #EDEDED;" >
                    </td>

                </tr>

                <tr>         
                    <td>
                        <p><%=CONFIG.getWillbededucted(session)%></p>
                    </td>
                    <td>
                        <input type="text" name="TotalAmountPayable" value="<%=(DonationVal-commission-ServiceCost) %>" readonly style="background-color: #EDEDED;" >
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