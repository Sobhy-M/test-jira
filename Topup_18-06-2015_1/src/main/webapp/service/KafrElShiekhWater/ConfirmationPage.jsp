<%-- 
    Document   : ConfirmationPage
    Created on : Apr 4, 2019, 12:03:35 PM
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
            <title><fmt:message key="Title_KafrElShiekhWater_Info"/></title>
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
            </script>

        </head>
        <body class="body" onload="changeselection('fullPaymentRadio')">

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
                <form  id="form" name="doinquiry" action="KafrElShiekhWaterPayment" method="POST" >
                    <input type="hidden" name="validationId" id="validationId" value="${kafrElShiekhWaterInquiryResponse.validationId}"/>
                    <fieldset style="width: 60%; direction: rtl;" align="right">  
                        <legend align="right" ><font size="5"><fmt:message key="Header_KafrElShiekhWater_Info"/></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>
                        <table border="1" width="100%">
                            <th><fmt:message key="requiredInfo"></fmt:message></th>
                            <tr>
                                <td colspan="2">
                                    <p align="right"><fmt:message key="Lable_KafrElShiekhWater_subscriberName"/>:
                                        <input type="text" id="subscriberName" name="subscriberName" value="${kafrElShiekhWaterInquiryResponse.subscriberName}" style="float: left;" required readonly />
                                    </p>
                                    <p align="right"><fmt:message key="Lable_KafrElShiekhWater_billsCount"/>:
                                        <input type="text" id="billsCount" name="billsCount" value="${kafrElShiekhWaterInquiryResponse.billsNumber}" style="float: left;" required readonly/>
                                    </p>
                                    <p align="right"><fmt:message key="Lable_KafrElShiekhWater_billsTotalAmountEGP"/>:
                                        <input type="text" id="billsTotalAmountEGP" name="billsTotalAmountEGP" value="${kafrElShiekhWaterInquiryResponse.serviceAmount}" style="float: left;" required readonly/>
                                    </p>
                                    <p align="right"><fmt:message key="FeesAndTax"/>:
                                        <input type="text" id="appliedFees" name="appliedFees" value="${kafrElShiekhWaterInquiryResponse.appliedFees}" style="float: left;" required readonly/>
                                    </p>
                                    <p align="right"><fmt:message key="Lable_KafrElShiekhWater_toBePaid"/>:
                                        <input type="text" id="toBePaid" name="toBePaid" value="${kafrElShiekhWaterInquiryResponse.toBepaid}" style="float: left;" required readonly/>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div align="center">
                                        <input type="submit"  value="<fmt:message key="pay" />"   style="float: right;" class="btn">
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

