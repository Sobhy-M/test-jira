<%--
    Document   : Mobinil_bill_payment
    Created on : 15/08/2012, 09:09:36 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
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
    MasaryManager.logger.info("BillInquiry_Page");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double billBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 1000);
    Masary_Bill_Type BTC = MasaryManager.getInstance().getBTC(request.getSession().getAttribute("serv_id").toString());
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(request.getSession().getAttribute("serv_id").toString(), agent.getPin());
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getCUSTOMERBill_Payment(session)%>&nbsp;<%=BTC.getStrBTCName(session)%></title>
        <script>
            function onValueChanged() {
                var Amount = document.getElementById("custAmountID");
                var Fees = document.getElementById("Fees");
                var Commession = document.getElementById("Commession");
                var Balance_Diff = document.getElementById("Balance_Diff");
                var DeductedAmount = document.getElementById("DeductedAmount");
                Commession.value = (<%= ratePlan.getFixedAmount()%> + (Number(Amount.value) * <%= ratePlan.getCommission()%> / 100)).toFixed(3);
                Balance_Diff.value = (Number(Amount.value) * <%=(ratePlan.getMasary_commission() / 100)%>).toFixed(3);
                Fees.value = (<%= ratePlan.getApplied_fees()%> + (Number(Amount.value) * <%= ratePlan.getApplied_fees_per()%> / 100)).toFixed(3);
                DeductedAmount.value = (Number(Amount.value) + Number(Fees.value) - Number(Commession.value) + Number(Balance_Diff.value)).toFixed(3);
            }
        </script>
        <script>
            function concatNumber() {
                var number = document.getElementById("govCode").value;
                console.log(number);
                var govCode = document.getElementById("custTopUpMSISDNID").value;
                console.log(govCode);
                document.getElementById("<%=CONFIG.PARAM_MSISDN%>").value = "" + number + govCode;
            }
        </script>
    </head>
    <body class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
    <%try {
            String Error = request.getSession().getAttribute("ErrorCode").toString();
    %>
    <font style="color: red; font-size: 15px;"><%=request.getSession().getAttribute("ErrorCode").toString()%></font>
        <%
                request.getSession().removeAttribute("ErrorCode");
            } catch (Exception e) {
            }
        %>
    <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" onsubmit="return validateMobinilBill(<%=billBalance%>, '<%=request.getSession().getAttribute(CONFIG.lang).toString()%>',<%=Integer.parseInt(request.getSession().getAttribute("serv_id").toString())%>);">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_Do_Bill_Payment%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("serv_id").toString()%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_MSISDN%>" id="<%=CONFIG.PARAM_MSISDN%>" />

        <div class="content_body"  >
            <fieldset style="width: 60%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%=CONFIG.getCUSTOMERBill_Payment(session)%>&nbsp;<%=BTC.getStrBTCName(session)%></font><img src="img/CashIn.ico"  width="20" height="20"> </legend> 
                <table border="1">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td>
                            <%if (request.getSession().getAttribute("serv_id").toString().endsWith("115")) {%>
                            <p align="right">أختر المحافظة :                    
                                <select style="float: left;"
                                        required="" tabindex="2" name="govCode" id="govCode" >   	
                                    <option value="02">القاهره-02</option>
                                    <option value="03">الاسكندريه-03</option>
                                    <option value="069" disabled>جنوب سيناء-69</option>
                                    <option value="095" disabled>الاقصر-95</option>
                                    <option value="097" disabled>اسوان-97</option>
                                    <option value="013" disabled>القليوبيه-13</option>
                                    <option value="015" disabled>العاشر من رمضان-15</option>
                                    <option value="040" disabled>الغربيه-40</option>
                                    <option value="045" disabled>البحيره-45</option>
                                    <option value="045" disabled>مطروح-45</option>
                                    <option value="047" disabled>كفرالشيخ-47</option>
                                    <option value="048" disabled>المنوفيه-48</option>
                                    <option value="050">الدقهليه-50</option>
                                    <option value="055" disabled>الشرقيه-55</option>
                                    <option value="057" disabled>دمياط-57</option>
                                    <option value="062" disabled>السويس-62</option>
                                    <option value="064" disabled>الاسماعيليه-64</option>
                                    <option value="065" disabled>الغردقه-65</option>
                                    <option value="066" disabled>بورسعيد-66</option>
                                    <option value="068" disabled>العريش-68</option>
                                    <option value="082" disabled>بنى سويف-82</option>
                                    <option value="084" disabled>الفيوم-84</option>
                                    <option value="086" disabled>المنيا-86</option>
                                    <option value="088" disabled>اسيوط-88</option>
                                    <option value="092" disabled>الوادى الجديد-92</option>
                                    <option value="093" disabled>سوهاج-93</option>
                                    <option value="096" disabled>قنا-96</option>
                                </select>
                            </p>
                            <%} else {%>
                            <input type="hidden" name="govCode" id="govCode" value="" />
                            <%}%>
                            <p align="right"><%=BTC.get_BILL_LABLE(session).replace("(مع كود المحافظه)", "")%> :
                                <input id="custTopUpMSISDNID" type="text"  required="" tabindex="2" style="float: left;"> 
                            <div id="custMobileDiv"></div></p>
                            <p align="right"><%=CONFIG.getConfirm(session)%> <%=BTC.get_BILL_LABLE(session).replace("(مع كود المحافظه)", "")%> :<input id="custMobileConfirmation" type="text" name="<%=CONFIG.PARAM_MSISDN_CONFIRMATION%>"  required="" tabindex="3" style="float: left;">
                            <div id="custMobileConfirmationDiv" name="custMobileConfirmationDiv"></div></p>
                            <p align="right"><%=CONFIG.getAmount(session)%> : <input type="text" name="<%=CONFIG.AMOUNT%>" required="" tabindex="1" id="custAmountID" onchange="onValueChanged();" style="float: left;" autocomplete ="OFF" />
                            <div id="custAmountDiv" name="custAmountDiv"></div></p> 
                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getBalance_Diff(session)%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            </br>
                        </td>
                    </tr>
                    <tr>
                        <td > <input type="submit" name="btnSubmit" onclick="concatNumber();" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="Btn" <%=billBalance == 0 ? "disabled='true'" : " "%> ></td>
                        <td><%=CONFIG.getAllowedBalance(session)%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(billBalance)%></td>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                    1- ادخل رقم التليفون و قيمة الفاتورة.  </br>
                    ملحوظة :- </br>
                    • يمكن معرفة قيمة فاتورتك عن طريق الإتصال بخدمة عملاء 
                    <%if (request.getSession().getAttribute("serv_id").toString().equals("112")) {%>
                    موبينيل
                    <%} else if (request.getSession().getAttribute("serv_id").toString().equals("111")) {%>
                    فودافون على رقم 888 او استخدم
                    #1*888*
                    <%} else {%>
                    لينك DSL
                    <%}%>
                    ، و فى حالة كانت القيمة المدخلة أقل من قيمة فاتورتك، فلن يتم دفع الفاتورة.
                    <%if (request.getSession().getAttribute("serv_id").toString().equals("115")) {%>
                    </br>    • هذة الدفعة سوف تقوم بتسديد الفواتير بدءا من الفاتورة الاقدم الى الاحدث وسوف يظهر المبلغ فى حسابك اشتراكك خلال يوم عمل.
                    <%}%>
                </details>
            </fieldset> 
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
