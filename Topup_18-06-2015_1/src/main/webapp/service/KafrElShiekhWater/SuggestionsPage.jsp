<%-- 
    Document   : SuggestionsPage
    Created on : Apr 4, 2019, 12:03:43 PM
    Author     : user
--%>

<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<fmt:bundle basename="Bundle">
    <html>
        <head>
            <link href="https://cdn.e-masary.net/app/img/style${lang}.css" rel="stylesheet" type="text/css">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="Title_KafrElShiekhWater_Suggestions"/></title>
            <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
            <style>
                p{
                    font-size: 17px;
                    font-weight: bold;
                }
                input[type=text] {
                    width: 50%;
                    background-color: #EDEDED;
                    float: right;
                    font-size: 16px;
                }
            </style>
            <script>
                function cancel() {
                    document.getElementById("HOMEFORM").submit();
                }
                function changeselection(id) {

                    if (id === "FirstSuggestion") {
                        document.getElementById("selectedBills").value = document.getElementById("firstAmount").value;

                    } else {
                        document.getElementById("selectedBills").value = document.getElementById("secondAmount").value;
                    }

                }
            </script>

        </head>
        <body class="body" onload="changeselection('FirstSuggestion')">

            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <div>
                <c:choose>
                    <c:when test="${lang== ''}">
                        <jsp:include page="../../img/menuList.jsp"></jsp:include>
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="../../img/menuListar.jsp"></jsp:include>
                    </c:otherwise>
                </c:choose>
            </div>   
            <div class="content_body"  >
                <form id="HOMEFORM" action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>" method="POST" style="display: none;" ></form>
                <form  id="form" name="doinquiry" action="KafrElShiekhWaterConfirmation" method="POST" >
                    <input type="hidden" name="selectedBills" id="selectedBills" />
                    <input type="hidden" name="validationId" id="validationId" value="${param.validationId}"/>
                    <fieldset style="width: 55%; direction: rtl;" align="right">  
                        <legend align="right" ><font size="5"><fmt:message key="Header_KafrElShiekhWater_Suggestions"/></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>
                        <table border="0" width="100%">
                            <th><fmt:message key="requiredInfo"></fmt:message></th>
                                <tr> 
                                    <td colspan="2">  
                                        <p align="right"><fmt:message key="Lable_KafrElShiekhWater_nearestBillsSuggested"/>:</p>
                                    <div align="right">
                                        <p> 
                                            <input type="radio" onchange="changeselection('firstAmount')" checked name="radio" id="FirstSuggestion" style="float: right;" value="suggest">
                                            <fmt:message key="Lable_KafrElShiekhWater_billsCount"/>:
                                            <input type="text"  name="FirstSuggestion" readonly  value="${kafrElShiekhWaterBillsSuggetionsResponse.firstBillsCount}" style="background-color: #EDEDED;float: left;" >
                                            <br>
                                            <br>
                                            <fmt:message key="Lable_KafrElShiekhWater_billsTotalAmountEGP"/>: 
                                            <input type="text" name="firstAmount" tabindex="1"  id="firstAmount" value="${kafrElShiekhWaterBillsSuggetionsResponse.firstBillsAmount}" readonly style="background-color: #EDEDED;float: left"  required >
                                        </p>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"> 
                                    <div align="center" style="font-size: 17px;font-weight: bold;">
                                        <fmt:message key="Lable_KafrElShiekhWater_or"/>
                                    </div>


                                </td> 
                            </tr>
                            <tr> 
                                <td colspan="2">  
                                    <div align="right">
                                        <p> 
                                            <input type="radio" onchange="changeselection('secondAmount')" name="radio" id="secondSuggestion" style="float: right;" value="suggest">
                                            <fmt:message key="Lable_KafrElShiekhWater_billsCount"/>:
                                            <input id="billsCount" type="text"  name="secondSuggestion" readonly  value="${kafrElShiekhWaterBillsSuggetionsResponse.secondbillsCount}" style="background-color: #EDEDED;float: left;" >
                                            <br>
                                            <br>
                                            <fmt:message key="Lable_KafrElShiekhWater_billsTotalAmountEGP"/>: 
                                            <input type="text" name="secondAmount" tabindex="1"  id="secondAmount" value="${kafrElShiekhWaterBillsSuggetionsResponse.secondbillsAmount}"  readonly style="background-color: #EDEDED;float: left" required >
                                        </p>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div align="center">
                                        <input type="submit"  value="<fmt:message key="confirm" />"   style="float: right;" class="btn">
                                        <input type="button"  value="<fmt:message key="cancel"/>" style="float: left;"  onclick="cancel();" class="btn">
                                        <input type="button"  value="<fmt:message key="back"/>"   onclick="history.go(-1);"  class="btn">
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </fieldset> 
                </form>
            </div><!-- End of Table Content area-->
        </div><!-- End content body -->
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

        </body>
    </html>
</fmt:bundle>