<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <%@page import="com.masary.controlers.CashatMerchant.CashatMerchantProperties"%>
    
    <%@page import="com.masary.common.CONFIG"%>
    
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("UTF-8");
    %>
    <%
    	String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
            if (role == null) {
                response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
                return;
            }
    %>

    <link href="img/style${lang}.css" rel="stylesheet" type="text/css">

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <script type="text/javascript" src="img/CheckForms.js"></script>
            <script src="./js/CashatJS/Cashat.js"></script>
            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
            <script src="./js/AddNewWalletJS/notify.js"></script>
            
            <title><%=CashatMerchantProperties.Param_CashatMerchant_ServiceName%></title>
            <style>
                #notice {
                    background: transparent;
                    border-top: transparent !important;
                    border-left: transparent !important;
                    border-right: transparent !important;
                    border-bottom: transparent !important;
                }

   input[type=number]::-webkit-inner-spin-button, 
            input[type=number]::-webkit-outer-spin-button { 
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                margin: 0; 
            }

                p {
                    font-size: medium;
                    font-weight: bold;
                }
            </style>
            <script>
 
            function onload(){
            	
            	var valueCompany = $("#inputList").val();
                var companyId = $('#companyId [value="' + valueCompany + '"]').data('companyvalue');
                document.getElementById("Idcompany").value = companyId;
                console.log('Company Id : ' + companyId + ' ' + 'Company Name : ' + valueCompany);
            }
            


                $(document).ready(function () {
                    $("#inputList").on('input', function () {
                        var valueCompany = $(this).val();
                        var companyId = $('#companyId [value="' + valueCompany + '"]').data('companyvalue');
                        document.getElementById("Idcompany").value = companyId;
                        console.log('Company Id : ' + companyId + ' ' + 'Company Name : ' + valueCompany);
                    });
                });


            </script>
        </head>
        <body class="body" onload="onload()" >
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
            <form id="form" action="CashatMerchantConfirmation" method="POST">
                <input type="hidden" name="cashatMerchentsCompanies" id="cashatMerchentsCompanies" value="${cashatMerchentsCompanies}" /> 
                <input type="hidden" name="Idcompany" id="Idcompany" />
                <div class="content_body">
                    <fieldset style="width: 35%; direction: rtl;" align="right">
                        <legend align="right">
                            <font size="3"><%=CashatMerchantProperties.Param_CashatMerchant_ServiceName%></font>&nbsp;<img
                                src="img/CashIn.ico" width="20" height="20">
                        </legend>
                        <table border="1" width="100%">
                            <th><%=CONFIG.getINFO_Required(session)%></th>
                            <tr>
                                <td>
                                    <p align="right">
                                        <%=CashatMerchantProperties.Param_CashatMerchant_CompanyLabel%>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                                        <input	list="companyId" id="inputList" autocomplete="off">

                            <datalist id="companyId" name="companyId">
                                <option value="0" selected disabled><%=CashatMerchantProperties.Param_CashatMerchant_Select%></option>
                                <c:forEach var="menu" items="${cashatMerchentsCompanies.getCompanies()}">
                                    <option id="${menu.companyID}" data-companyvalue="${menu.companyID}" value ="${menu.companyNameAr}"/>
                                </c:forEach> </datalist>
                            </p>
                            <p align="right"><%=CashatMerchantProperties.Param_CashatMerchant_NationalIdLabel%>
                                &nbsp;&nbsp; 
                                <input type="text" id="nationalId" name="nationalId" required maxlength="14" >
                            <div id="nationalIdDiv" style="color: red"></div>
                            </p>
                            <p align="right"><%=CashatMerchantProperties.Param_CashatMerchant_AmountLabel%>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                                <input type="text" id="amount" name="amount" required maxlength="9">
                            <div id="amountDiv" style="color: red"></div>
                            </p>
                             <c:choose>
                             <c:when test="${cashatMerchentsCompanies.warnSTR != ''}">
                             <p align="right" style="color: red"><%=CashatMerchantProperties.Param_CashatMerchant_NoticeLabel%>:
                             <input id="notice" type="text" value="${cashatMerchentsCompanies.warnSTR}" readonly style="color: red; font-size: medium;">
                              </p>    
                              </c:when>
                             </c:choose>
                
                            </td>
                            </tr>

                            <tr>
                                <td colspan="2">
                                <input type="submit" name="btnSubmit" tabindex="4" value="<%=CashatMerchantProperties.Param_CashatMerchant_DoneLabel%>"  onclick="return formSubmit()" class="Btn"></td>

                            </tr>
                        </table>
                        <details open="open"> <summary> </summary> </details>
                    </fieldset>
                </div>
                <!-- End of Table Content area-->
            </form>

            <div style="clear: both;">&nbsp;</div>
            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </body>
    </html>

