<%-- 
    Document   : LegitimacyAssociationConfirmation
    Created on : May 22, 2017, 3:54:28 PM
    Author     : Abdelsabor
--%>

<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String custId = null;    
    String feesInquery = request.getAttribute("feesInquery").toString();
    String [] arr = feesInquery.split("-");
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
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
    Double DonationVal = Double.parseDouble(request.getParameter("donationValue")) + Double.parseDouble(arr[1]);
    Double ServiceCost = Double.parseDouble(arr[1]);
    Double commission = Double.parseDouble(arr[0]);
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getLegitimacyAssociationDescription(session)%></title>
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
        <form action="LegitimacyAssociationPayController" method="POST" style="font-weight: bold" onsubmit="disableDoubleSubmission()">
            <input type="hidden" name="OPERATION_ID" value="<%=request.getParameter("OPERATION_ID")%>" />
            <input type="hidden" name="feesInquery" value="<%=feesInquery%>" />
            <table style="width: 50% ; margin-left: auto ; margin-right: auto">
                <tr>
                    <th colspan="2" style="text-align: center"><p><%=CONFIG.getLegitimacyAssociationDescription2(request.getSession()) + ProjectName%></p></th>
                </tr>
                <tr class="secondTD">
                    <td colspan="2" class="secondTD">
                        <table>
                            <tr>
                                <td style="border: none ; background-color: transparent; padding: 0px" class="secondTD">
                                    <p><%=CONFIG.getWillDonateTO(session)%></p>
                                </td>
                                <td style="border: none ; background-color: transparent; padding: 0px" class="secondTD">
                                    <label>&nbsp;<%=CONFIG.getToLegitimacyAssociation(request.getSession()) + ProjectName%></label>
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
