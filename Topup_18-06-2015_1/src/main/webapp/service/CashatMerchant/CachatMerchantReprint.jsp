<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="com.masary.controlers.CashatMerchant.CashatMerchantProperties"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<%@page import="com.masary.common.CONFIG"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><%=CashatMerchantProperties.Param_CashatMerchant_ServiceName%></title>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />

        <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10" style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/masarylogoo.jpg"  width="200" height="60"></td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=CashatMerchantProperties.Param_TransactionStatus%></td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CashatMerchantProperties.Param_CashatMerchant_TransactionStatusLabel%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> ${cashatPaymentResponse.globalTrxId}</td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CashatMerchantProperties.Param_CashatMerchant_TransactionIdLabel%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> ${cashatPaymentResponse.accountId}</td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CashatMerchantProperties.Param_CashatMerchant_PointCodeLabel%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> ${date}</td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CashatMerchantProperties.Param_CashatMerchant_TransactionDateLabel%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> ${time}</td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CashatMerchantProperties.Param_CashatMerchant_TransactionTimeLabel%></td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-----------------------------------</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> ${cashatPaymentResponse.companyNameAR}  </td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CashatMerchantProperties.Param_CashatMerchant_CompanyNameLabel%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> ${cashatPaymentResponse.repNationalId}  </td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CashatMerchantProperties.Param_RepId%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> ${cashatPaymentResponse.paymentAmount}  </td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CashatMerchantProperties.Param_Amount%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> ${cashatPaymentResponse.toBepaid}  </td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CashatMerchantProperties.Param_Tobepaied%></td> </tr>');
                printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size: 16px;" ><%=CashatMerchantProperties.Param_Masary_ThanksAr%></th> </tr >');
                printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size:16px; "><%=CashatMerchantProperties.Param_Masary_WebsiteAr%></th> </tr> </table>');
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
            }
        </script>
        <link href="img/style${lang}.css" rel="stylesheet" type="text/css">
    </head>

    <body class="body" >   
        <c:if test="${ROLE != null}">
            <c:choose>
                <c:when test="${lang== ''}">
                    <jsp:include page="../../img/menuList.jsp"></jsp:include>
                </c:when>
                <c:otherwise>
                    <jsp:include page="../../img/menuListar.jsp"></jsp:include>
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>

    <div class="content_body">
        <form action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>"  method="POST">
            <table >
                <tr>
                    <td>
                        <p align="right"><%=CashatMerchantProperties.Param_CashatMerchant_CompanyNameLabel%>:</p>
                    </td>
                    <td>
                        <input type="text" value="${cashatPaymentResponse.companyNameAR}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p align="right"> <%=CashatMerchantProperties.Param_CashatMerchant_NationalIdLabel%>:</p>
                    </td>
                    <td>
                        <input type="text" value="${cashatPaymentResponse.repNationalId}"  readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p align="right"><%=CashatMerchantProperties.Param_CashatMerchant_AmountWithEGPLabel%>:</p>
                    </td>
                    <td>
                        <input type="text" value="${cashatPaymentResponse.paymentAmount}" readonly style="background-color: #EDEDED;" >
                    </td>                           
                </tr>
                <tr>
                    <td>
                        <p align="right"><%=CashatMerchantProperties.Param_CashatMerchant_PointCodeLabel%>:</p>
                    </td>
                    <td>
                        <input type="text" value="${cashatPaymentResponse.accountId}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p align="right"><%=CashatMerchantProperties.Param_CashatMerchant_TransactionIdLabel%>:</p>
                    </td>
                    <td>
                        <input type="text" value="${cashatPaymentResponse.globalTrxId}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p align="right"><%=CashatMerchantProperties.Param_CashatMerchant_TransactionDateLabel%>:</p>
                    </td>
                    <td>
                        <input type="text" value="${date}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p align="right"><%=CashatMerchantProperties.Param_CashatMerchant_TransactionTimeLabel%>:</p>
                    </td>
                    <td>
                        <input type="text" value="${time}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align:center;font-size:16px; padding-right: 30px"><%=CashatMerchantProperties.Param_Masary_Website%></td>

                </tr>
                <tr>
                    <td style="text-align: center" colspan="2">
                        <input type="submit" name="btnSubmit" value="اغلاق وطباعه"  class="Btn" onclick="setDivPrint()" >
                    </td>
                </tr>   
                <tr style="display: none">
                    <th colspan="2"><img src="./img/masarylogoo.jpg" width="50"
                                         height="50"></th>
                </tr>

            </table>
        </form>
    </div><!-- End content body -->


    <div style="clear: both;">&nbsp;</div>

    <div id="footer">
        <jsp:include page="../../img/timeout.jsp"></jsp:include>
    </div>
</div><!-- End of Main body-->

</body>
</html>



