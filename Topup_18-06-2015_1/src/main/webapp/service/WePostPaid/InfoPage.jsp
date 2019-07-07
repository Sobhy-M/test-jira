<%-- 
    Document   : InfoPage
    Created on : Mar 28, 2018, 4:31:44 PM
    Author     : Ahmed Khaled
--%>

<%@page import="com.masary.controlers.WEPostPaid.WePostPaidProperties"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
     String rolePage = CONFIG.APP_ROOT + (String) session.getAttribute(CONFIG.PARAM_ROLE) + ".jsp";
%>
<!DOCTYPE html>
<html>
    <head>
        <link
            href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css"
            rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=WePostPaidProperties.WePostPaid_SERVICE_NAME%></title>
        <style>
            input[type=text] {
                width: 50%;
                background-color: #EDEDED;
                float: right;
                font-size: 16px;
            }
            p {
                font-size: 13.7px;
                font-weight: bold;
            }
            input.btn {
                font-size: 16px;
            }
        </style>
    </head>
    <body class="body">

        <%
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
        %>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%
        } else {
        %>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>
        <%
            }
        %>
    </div>

    <div class="content_body"  >
        <form  action="WeBillsPayment" method="POST" >
                <input type="hidden" name="paymentReferenceId" id="paymentReferenceId" value="${inquiryResponse.msisdn}" />
                        <input type="hidden" name="validationId" id="paymentReferenceId" value="${inquiryResponse.validationId}" />
                                                <input type="hidden" name="tax" id="tax" value="${inquiryResponse.tax}" />
                                                <input type="hidden" name="advStatment" id="tax" value="${paymentResponse.advStatment}" />
                                                
                        
        
            <fieldset style="direction: rtl;" align="right">
                <legend align="right" ><font size="4" style="font-weight: bold"><%=WePostPaidProperties.WePostPaid_Inquiry2%><img src="img/weIcon.jpg"  width="35" height="35"></font></legend>
                <table border="1" style="width: 100%">
                    <th><%=CONFIG.getAgentPaymentInfo(request.getSession())%></th>     
                    <th><%=CONFIG.ESED_Confirmation_Commission_Label%></th>    
                    <tr>
                        <td>
                            <p align="right"> <%=WePostPaidProperties.WePostPaid_Bill_Amount%>: 
                                <input type="text" readonly tabindex="1" value="${inquiryResponse.totalOutStandingFee}" style="background-color: #EDEDED;float: left; width: 70%"/>
                            </p>

                            <p align="right"><%=WePostPaidProperties.WePostPaid_Mobile_Number%>:  
                                <input type="text" name="msisdn" readonly value="0${inquiryResponse.msisdn}" style="background-color: #EDEDED;float: left; width: 70%" >
                            </p>
                        </td>
                        <td style="width: 50%">
                            <p align="right"><%=CONFIG.getServiceCost(request.getSession())%>:
                                <input type="text"  value="${inquiryResponse.appliedFees}"  readonly  style="background-color: #EDEDED; float: left; width: 50%"/>
                            </p> 
                            <p align="right"><%=CONFIG.getCommession(request.getSession())%>:
                                <input type="text"  value="${inquiryResponse.merchantCommission}"  readonly  style="background-color: #EDEDED; float: left; width: 50%"/>
                            </p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%>:
                                <input type="text"  value="${inquiryResponse.transactionAmount}"  readonly  style="background-color: #EDEDED; float: left; width: 50%"/>
                            </p> 
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div align="center">
                                <input type="submit" name="btnSubmit" tabindex="1"  value="<%=CONFIG.getConfirmation(request.getSession())%>"   style="float: right;" class="btn">
                                <input type="button" name="Back" tabindex="4" value="<%=WePostPaidProperties.WePostPaid_Cancel%>"   onclick="window.location.href = '<%=rolePage%>';"  class="btn">
                                <input type="button" name="Cancel" tabindex="6"  value="<%=CONFIG.getBack(request.getSession())%>"  onclick="history.go(-1);" style="float: left;" class="btn">
                            </div>
                        </td>
                    </tr>
                </table>
                <details open="open">
                        <summary></summary>
                            تاكد ان رقم التليفون الذى تم ادخاله صحيح
                    </details>
            </fieldset>


        </form>
    </div><!-- End of Table Content area-->

</div>
<!-- End content body -->

<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div>
<!-- End of Main body-->
</body>
</html>