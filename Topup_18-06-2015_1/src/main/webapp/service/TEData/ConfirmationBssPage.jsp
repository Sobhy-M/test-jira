<%-- 
    Document   : ConfirmationBssPage
    Created on : Feb 24, 2019, 1:11:35 PM
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
            <title><fmt:message key="Title_TeDataBills_Confirmation"></fmt:message></title>
            <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
            <script>
                var ajaxFlag = false;
                function cancel() {
                    document.getElementById("HOMEFORM").submit();
                }
                $(window).on("load",function () {
                    $.ajax({
                        url: 'RatePlanCommissions',
                        data: {
                            SERVICE_ID: ${serv_id},
                            AMOUNT: ${param.amount}
                        },
                        type: 'get'
                    }).done(function (responseData) {
                        console.log("done" + ajaxFlag);
                        ajaxFlag = true;
                        if (responseData !== "") {
                            console.log("done" + responseData);
                            var arr = responseData.split('-');
                            $('#appliedFees').val(arr[6]);
                            $('#commission').val(arr[3]);
                            $('#deductedAmount').val(arr[8]);
                            $('#toBePaid').val(arr[11]);
                        } else {
                            ajaxFlag = false;
                            console.log("else" + ajaxFlag);
                            return;
                        }
                    }).fail(function () {
                        ajaxFlag = false;
                        console.log("fail" + ajaxFlag);
                        console.log(ajaxFlag);
                        return;
                    });
                });
            </script>
            <style>
                p{
                    font-size: 17px;
                    font-weight: bold;
                }
                input[type=text]{
                    background-color: #EDEDED;
                    float: left;
                }
            </style>
        </head>
        <body class="body">
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
            <div class="content_body">
                <form id="HOMEFORM" action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>" method="POST" style="display: none;" ></form>
                <form action="TeDataBssPayment" method="POST" >
                    <fieldset style="width: 72%; direction: rtl;" align="right">  
                        <table border="1" width="100%">
                            <th><fmt:message key="Title_TeDataBills_PaymentConfirmation"/></th>     
                            <th><fmt:message key="Title_TeDataBills_Calculations"/></th>    
                            <tr>
                                <td>
                                    <p align="right"><fmt:message key="Lable_TeDataBills_PhoneNumber"/> :
                                        <input type="text" readonly name="phoneNumber" value="${param.phoneNumber}" />
                                    </p>

                                    <p align="right"><fmt:message key="Lable_TeDataBills_RequiredAmount"/> :
                                        <input type="text" id="amount" name="amount" value="${param.amount}" readonly />
                                    </p>
                                    <p align="right"><fmt:message key="Lable_TeDataBills_AppliedFees"/> :
                                        <input type="text" id="appliedFees" name="appliedFees" readonly  />
                                    </p>
                                    <p align="right"><fmt:message key="Lable_TeDataBills_toBePaid"/> :
                                        <input type="text" id="toBePaid" name="toBePaid" readonly  />
                                    </p>
                                </td>
                                <td>
                                    <p align="right"><fmt:message key="Lable_TeDataBills_Commission"/> :
                                        <input type="text" id="commission" name="commission" readonly />
                                    </p> 
                                    <p align="right"><fmt:message key="Lable_TeDataBills_DeductedAmount"/> :
                                        <input type="text" id="deductedAmount" name="deductedAmount"  readonly  />
                                    </p> 
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
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
            <div style="clear: both;">&nbsp;</div>
            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
            </body>
        </html>
</fmt:bundle>