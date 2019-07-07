<%-- 
    Document   : BlaBlaTopUp
    Created on : May 8, 2014, 11:54:05 AM
    Author     : Aya
--%>

<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.logging.Level"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.masary.database.dto.Product"%>
<%@page import="com.masary.database.dto.BlaBlaProducts_Res"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="com.masary.BlaBla_Manager"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.CurrencyConvertRate"%>
<%@page import="com.masary.database.dto.AgentDTO"%>

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    String custId = null;
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    AgentDTO agent = MasaryManager.getInstance().getAgent(custId);
    RatePlanDTO ratePlan = (RatePlanDTO) request.getSession().getAttribute("ratePlan");

%>

<%
    CurrencyConvertRate coRate = new CurrencyConvertRate();
    double rate = coRate.SendRequestandConvert(1);
    //System.out.print("rate"+rate);
%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getCustomerBlaBlaTopUp(request.getSession())%></title>
        <script>
            function onValueChanged() {
                var Amount = document.getElementById("custTopUpDalanceID");
                var Fees = document.getElementById("Fees");
                var Commession = document.getElementById("Commession");
                var Balance_Diff = document.getElementById("Balance_Diff");
                var DeductedAmount = document.getElementById("DeductedAmount");
                var AmountDolar = document.getElementById("custTopUpAmountDol");
                var custTopUpDalanceID = document.getElementById("custTopUpDalanceID");
                Commession.value = (<%= ratePlan.getFixedAmount()%> + (Number(Amount.value)  * <%= ratePlan.getCommission()%> / 100)).toFixed(3);
                Balance_Diff.value = (Number(Amount.value) * <%=(ratePlan.getMasary_commission() / 100)%>).toFixed(3);
                Fees.value = (<%= ratePlan.getApplied_fees()%> + (Number(Amount.value) * <%= ratePlan.getApplied_fees_per()%> / 100)).toFixed(3);
                AmountDolar.value = Math.floor(Number((custTopUpDalanceID.options[custTopUpDalanceID.selectedIndex]).text) / <%=rate%>);
                DeductedAmount.value = (Number(Amount.value) + Number(Fees.value) - Number(Commession.value) + Number(Balance_Diff.value)).toFixed(3);

            }


        </script>
    </head>
    <body class="body">
        <!--    <BODY class="body">-->
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="BayCreditCustomer" action="BlaBlaTopUController" method="POST" onsubmit="return validateBlaBlaRechargeCustomer()">
            <input type="hidden" name="action" value="<%=CONFIG.BlaBlaTopUpConfirmation%>" />
        <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%=request.getParameter("SERVICE_ID")%>" />

        <fieldset style="width: 55%; direction: rtl;" align="right">  
            <legend align="right" ><font size="5"><%=CONFIG.getCustomerBlaBlaTopUp(request.getSession())%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend> 
            <table border="1" width="100%">
                <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                <tr>
                    <td><p align="right"><%=CONFIG.getAmount(request.getSession())%> :
                            <select id="custTopUpDalanceID" onchange="onValueChanged();" name="<%=CONFIG.AMOUNT%>"  >
                                <option ><%=CONFIG.getSelectAmount(request.getSession())%></option>
                                <%
                                    String nonce = BlaBla_Manager.getNonce();
//                                    // Generate hashed password
                                    MessageDigest messageDigest;
                                    try {
                                        messageDigest = MessageDigest.getInstance("SHA1");

                                        String hashedPassword = new String(BlaBla_Manager.byteArrayToHexString(messageDigest.digest(BlaBla_Manager.password.getBytes())));
//                                        //System.out.println(hashedPassword);
                                        hashedPassword = new String(BlaBla_Manager.byteArrayToHexString(messageDigest.digest((nonce + ":" + hashedPassword).getBytes())));
//                                        //System.out.println(hashedPassword);
                                        // Prepare request
                                        String urlParameters = "username=" + BlaBla_Manager.username + "&password=" + hashedPassword + "&nonce=" + nonce;
                                        String url = "http://distributors.blablacash.com/webservices/getBlaBlaProducts";
                                        String Response = BlaBla_Manager.SendRequest(url, urlParameters);
//                                        //System.out.println("Response : " + Response);
                                        Gson gson = new Gson();
                                        BlaBlaProducts_Res BlaBlaProducts_res = gson.fromJson(Response, BlaBlaProducts_Res.class);
//                                        //System.out.println(BlaBlaProducts_res.getStatus());
                                        for (Product Product : BlaBlaProducts_res.getValue()) {

//                                            //System.out.println(Product.getId());
                                %>
                                <%--<%=(int) Math.floor((rate * (Product.getSellingPrice())))%>--%>
                                <option id="<%=Product.getId()%>">

                                    <%=(int) Math.ceil(Product.getSellingPrice() * rate)%>
                                </option>
                                <%
                                        }
                                    } catch (Exception ex) {
                                        //System.out.println(ex.getMessage());
                                    }
                                %>
                            </select>
                        <div id="custTopUpDalanceDiv" name="custAmountDiv"></div>


                        </p> 
                        <p align="right"><%=CONFIG.getAmountByDolar(request.getSession())%> : <input id="custTopUpAmountDol" type="text" 
                                                                                                     name="<%=CONFIG.AMOUNTDolar%>" required=""  tabindex="2" style="float: left;" readonly>
                        <p align="right"><%=CONFIG.getMobileNumber(request.getSession())%> : <input id="custTopUpMobileId" type="text" 
                                                                                                    name="<%=CONFIG.PARAM_MSISDN%>" required="" tabindex="2" style="float: left;">

                        <div id="custTopUpMobileDiv"  ></div></p>
                        <p align="right"><%=CONFIG.getConfirm(request.getSession())%> <%=CONFIG.getMobileNumber(request.getSession())%> :<input id="custMobileConfirmation" type="text" name="<%=CONFIG.PARAM_MSISDN_CONFIRMATION%>"  required="" tabindex="3" style="float: left;">
                        <div id="custMobileConfirmationDiv" name="custMobileConfirmationDiv"></div></p>

                    </td>
                    <td>
                        <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getBalance_Diff(request.getSession())%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </br>
                    </td>
                </tr>
                <tr>
                    <td > <input type="submit" name="btnSubmit" tabindex="4"  onclick="checkMobilNumber();" value="<%=CONFIG.getGo(request.getSession())%>" class="Btn" <%=agent.getServiceBalance(33) == 0 ? "disabled='true'" : " "%> ></td>
                    <td><%=CONFIG.getAllowedBalance(request.getSession())%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(agent.getBalance()) : myFormatter.format(agent.getServiceBalance(33))%></td>
                </tr>
            </table>
            <details open="open">
                <summary></summary>
                <br>
                1-	من فضلك ادخل المبلغ المراد شحنه و رقم حسابك فى BlaBla و هو رقم الموبايل المسجل فى حساب العميل لدى BlaBla  مع إضافة رقم 2 فى اول الرقم مثل 201001234567. </br>            
                2-يظهر لك فى الجهة اليسرى من الشاشة عمولتك على المبلغ بالجنيه و أيضاً يظهر لك المبلغ الذى سوف يتم خصمه من حسابك و المبلغ الذى سيضاف فى حساب العميل لدى BlaBla بالدولار .  </br>

                ملحوظة :- </br>
                •المبالغ التى يمكن شحنها فى خدمة شحن BlaBla يجب أن تكون مبالغ صحيحة بدون كسور و هى:
                <br/>
                <br>
                <table border="1" width="40%"> 


                    <%
                        // Generate nonce
                        nonce = BlaBla_Manager.getNonce();
                        // Generate hashed password
//                        MessageDigest messageDigest;
                        try {
                            nonce = BlaBla_Manager.getNonce();
                            messageDigest = MessageDigest.getInstance("SHA1");

                            String hashedPassword = new String(BlaBla_Manager.byteArrayToHexString(messageDigest.digest(BlaBla_Manager.password.getBytes())));
//                            //System.out.println(hashedPassword);
                            hashedPassword = new String(BlaBla_Manager.byteArrayToHexString(messageDigest.digest((nonce + ":" + hashedPassword).getBytes())));
//                            //System.out.println(hashedPassword);
                            // Prepare request
                            String urlParameters = "username=" + BlaBla_Manager.username + "&password=" + hashedPassword + "&nonce=" + nonce;
                            String url = "http://distributors.blablacash.com/webservices/getBlaBlaProducts";
                            String Response = BlaBla_Manager.SendRequest(url, urlParameters);
//                            //System.out.println("Response : " + Response);
                            Gson gson = new Gson();

                            BlaBlaProducts_Res BlaBlaProducts_res = gson.fromJson(Response, BlaBlaProducts_Res.class);
//                            //System.out.println(BlaBlaProducts_res.getStatus());
                            for (Product Product : BlaBlaProducts_res.getValue()) {
//                 //System.out.println(Product.getId());
                    %>
                    <tr>
                        <td style="padding:0px 0px;"><%=Product.getSellingPrice()%></td>

                        <td style="padding:0px 0px;"><%= (int) Math.ceil(rate * Product.getSellingPrice())%></td>
                    </tr>
                    <%
                            }
                        } catch (Exception ex) {
                        }
                    %>
                </table>
            </details>
        </fieldset> 
    </form>

</div><!-- End content body -->



<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>
