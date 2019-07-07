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
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(request.getSession().getAttribute("SERVICE_ID").toString(), session.getAttribute(CONFIG.PARAM_PIN).toString());
    Double ServiceCost = ratePlan.getApplied_fees()+(ratePlan.getApplied_fees_per()*Double.parseDouble(request.getParameter("donationValue"))/100);
    Double Commession = (ratePlan.getCommission()*Double.parseDouble(request.getParameter("donationValue")))/100 +ratePlan.getFixedAmount();
    Double DonationVal = Double.parseDouble(request.getParameter("donationValue"))+ServiceCost ;
    String Operation = session.getAttribute(CONFIG.OPERATION_ID).toString();
    String ProjectName="";
    String ProjectName2="";
    if(Operation.equals("1"))
    {
        ProjectName = CONFIG.getVirusCProject(request.getSession());
        ProjectName2 = CONFIG.GetVirusC(request.getSession());
    }
    else if(Operation.equals("2"))
    {
        ProjectName = CONFIG.getDevelopmentOfSlumsProject(request.getSession());
        ProjectName2 = CONFIG.GetDevelopmentOfSlums(request.getSession());
    }
    else if(Operation.equals("3"))
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
        <form action="LongLifeEgyptPAYController" method="POST" style="font-weight: bold">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_LONG_LIFE_EGYPT_PAY%>" />
            <h2 align="center" ><%=CONFIG.getLongLifeEgyptProject(request.getSession())%></h2>
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <th colspan="2" style="text-align: center"><p><%=ProjectName%></p></th>
                </tr>
                <tr class="secondTD">
                    <td colspan="2" class="secondTD">
                        <table>
                            <tr>
                                <td style="border: none ; background-color: transparent; padding: 0px" class="secondTD">
                                    <p><%=CONFIG.getWilDonateTo(session)%></p>
                                </td>
                                <td style="border: none ; background-color: transparent; padding: 0px" class="secondTD">
                                    <label>&nbsp;<%=ProjectName2%></label>
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
                                    <input type="text" name="Commession" value="<%=Commession%>" readonly style="background-color: #EDEDED;" >
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
                        <p><%= CONFIG.getDonorPhone(session)%>:</p>
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