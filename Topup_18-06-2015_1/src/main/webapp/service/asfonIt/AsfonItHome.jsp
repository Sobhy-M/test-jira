<%-- 
    Document   : bill_inquiry
    Created on : 01/05/2012, 07:10:57 م
    Author     : Michael
--%>

<%@page import="com.masary.integration.dto.AsfonResponse"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%
    MasaryManager.logger.info("Edu centers Home Page");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    String rolePage = CONFIG.APP_ROOT + (String) session.getAttribute(CONFIG.PARAM_ROLE) + ".jsp";
    int serviceId = Integer.parseInt(request.getParameter("SERVICE_ID"));
    String serviceName = "اصفون للتدريب و تقنية المعلومات";
    AsfonResponse[] exams = (AsfonResponse[]) session.getAttribute("exams");
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://cdn.e-masary.net/app/img/jquery-1.11.3.min.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getEduCentersServic(request.getSession())%></title>
        <script>
            function onselectChanged(sel) {
                var value = sel.value;
                if (value !== null && value !== "" && value !== "0" && document.getElementById("AsfonList").value !== "اختر الامتحان") {
                    document.getElementById("itemId").value = sel.options[sel.selectedIndex].id;
                    document.getElementById("itemCode").value = sel.options[sel.selectedIndex].text;
                    getCommission(sel.options[sel.selectedIndex].id);
                } else {
                    document.getElementById("EduAmount").value = '';
                    document.getElementById("commission").value = '';
                    document.getElementById("payedAmount").value = '';
                    document.getElementById("AmountValidation").innerHTML = "برجاء اختيار الامتحان ";
                    document.getElementById("AmountValidation").style.color = "red";
                }
            }
            var ajaxFlag = false;
            function getCommission(itemid) {
                $.ajax({
                    url: 'AsfonIt',
                    data: {
                        itemId: itemid
                    },
                    type: 'get'
                }).done(function (responseData) {
                    if (responseData === '')
                    {
                        ajaxFlag = false;
                        return;
                    } else
                    {
                        ajaxFlag = true;
                    }
                    var obj = JSON.parse(responseData);
//                    console.log(obj);
                    if (obj["merchantCommission"] !== null && obj["merchantCommission"] !== "" && obj["transactionAmount"] !== null && obj["transactionAmount"] !== "") {
                        $('#commission').val(obj["merchantCommission"]);
                        $('#payedAmount').val(obj["transactionAmount"]);
                        $('#EduAmount').val(obj["toBepaid"]);
                    } else {
                        $('#commission').val("0");
                        $('#payedAmount').val("0");
                    }
                }).fail(function () {
                    ajaxFlag = false;
                    console.log('Failed');
                });
            }
            function onclick() {
                window.location.replace("2.jsp");
            }
            function validate() {
                if (document.getElementById("AsfonList").value === "اختر الامتحان" || document.getElementById("AsfonList").value === "" || document.getElementById("AsfonList").value === "0" || document.getElementById("AsfonList").value === null) {
                  
                    document.getElementById("AmountValidation").innerHTML = "برجاء اختيار الامتحان ";
                    document.getElementById("AmountValidation").style.color = "red";
					  return false;
                } else {
                    return true;
                }
            }
            function validateNationalId() {
//                var pattern2 = /(2|3)[0-9][1-9][0-1][1-9][0-3][1-9](01|02|03|04|11|12|13|14|15|16|17|18|19|21|22|23|24|25|26|27|28|29|31|32|33|34|35|88)\d\d\d\d\d/;
                var pattern2 = /\d\d\d\d\d\d\d\d\d\d\d\d\d\d/;
//                2891 01 70101237
                if (document.getElementById("nationalId").value === null || document.getElementById("nationalId").value === " ") {
                    document.getElementById("AmountValidation").innerHTML = "برجاء ادخال الرقم القومي ";
                    document.getElementById("AmountValidation").style.color = "red";
                    return false;
                } else {
                    if (!document.getElementById("nationalId").value.match(pattern2)) {
                        document.getElementById("AmountValidation").innerHTML = "برجاء ادخال الرقم القومي المكون من 14رقم وادخاله صحيح  ";
                        document.getElementById("AmountValidation").style.color = "red";
                        return false;
                        console.log('sssss');
                    } else {
                        return true;
                    }
                }
            }
            function validateAll() {
                if (validateNationalId() && validate()) {
                    return true;
                } else {
                    return false;
                }
            }
        </script>
    </head>
    <body class="body">
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <div class="content_body">
                <form name="EduCentersHome" action="AsfonController" method="POST" >
                    <input type="hidden" name="<%=CONFIG.itemId%>" id="itemId" />
                <input type="hidden" name="<%=CONFIG.itemCode%>" id="itemCode" />
                <input type="hidden" name="action" value="Get_Price_Exam" />
                <table>

                    <tr>
                        <td  style="text-align: center"><%=CONFIG.getAgentPaymentInfo(session)%></td>
                        <td  style="text-align: center"><%=CONFIG.getAgentPaymentComputeCommission(session)%></td>
                    </tr>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td><%=CONFIG.getAsfonList(session)%></td>
                                    <td>
                                        <p align="right">
                                            <select required="true" name="AsfonList" id="AsfonList" style="float: left;" onchange="onselectChanged(this);" >
                                                <option  value="0"  id="0"><%=CONFIG.ChooseExam(request.getSession())%></option>

                                                <%for (AsfonResponse i : exams) {%>
                                                <option value="<%= i.getExamPrice()%>" id="<%= i.getExamId()%>"><%=i.getExamName()%></option><%}%>
                                            </select></p>
                                    </td></tr>
                                <tr>
                                    <td>
                                        <p><%= CONFIG.getAmountFees(session)%></p>
                                    </td>
                                    <td>
                                        <input id="EduAmount" readonly  type="text"  name="<%=CONFIG.AMOUNT%>"  tabindex="2" style="background-color: #EDEDED; float: left;" >
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <p align="right"><%=CONFIG.getStudetNId(request.getSession())%></p>
                                    </td>
                                    <td>
                                        <input  maxlength="14" id="nationalId" type="text"  name="nationalId" required autocomplete="off" autofocus>
                                         <div id="AmountValidation"></div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td style="border: none ; background-color: transparent">
                                        <p><%=CONFIG.getAgentPaymentCommission(session)%>:</p>
                                    </td>
                                    <td style="border: none ; background-color: transparent">
                                        <input readonly style="padding-left: auto;background-color: #EDEDED" name="commission" readonly id="commission"  autocomplete="off" maxlength="100" type="text">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="border: none ; background-color: transparent">
                                        <p><%=CONFIG.getAgentPaymentWillDeducted(session)%>:</p>
                                    </td>
                                    <td style="border: none ; background-color: transparent">
                                        <input  style="padding-left: auto;background-color: #EDEDED" name="payedAmount" id="payedAmount" readonly autocomplete="off" maxlength="100"  type="text" >
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td  style="text-align: right;"><input type="submit" name="btnSubmit"  onclick="return validateAll();" tabindex="3" value="<%=CONFIG.getConfirm(session)%>" class="Btn"></td>
                        <td  style="text-align: left;"><input type="submit" name="btnSubmit" onclick="window.location.href='<%=rolePage %>';" tabindex="3" value="<%=CONFIG.GetClose(session)%>" class="Btn"></td>

                    </tr>

                </table>
            </form>
        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>