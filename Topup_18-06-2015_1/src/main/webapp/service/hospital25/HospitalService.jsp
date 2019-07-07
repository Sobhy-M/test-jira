<%-- 
    Document   : Mersal Service
    Created on : Dec 04, 2016, 12:44:08 PM
    Author     : hammad
--%>

<%@page import="com.masary.common.CONFIG"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String custId = null;
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    String Operation = session.getAttribute(CONFIG.OPERATION_ID).toString();
    String ProjectName="";
    if (Operation.equals("1")) {
        ProjectName = CONFIG.HOSPITAL_25_DONATION_AR;
    } else if (Operation.equals("2")) {
        ProjectName = CONFIG.HOSPITAL_25_ZAKAH_AR;
    } else if (Operation.equals("3")) {
        ProjectName = CONFIG.HOSPITAL_25_SADAKAH_AR;
    } else if (Operation.equals("4")) {
        ProjectName = CONFIG.HOSPITAL_25_SEASON1_AR;
    } else if (Operation.equals("5")) {
        ProjectName = CONFIG.HOSPITAL_25_SEASON2_AR;
    } else if (Operation.equals("6")) {
        ProjectName = CONFIG.HOSPITAL_25_SEASON3_AR;
    } else {
        ProjectName = CONFIG.HOSPITAL_25_SEASON4_AR;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <script src="./js/LongLiveEgyptJS/LongLiveDonationController.js"></script>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
        <title><%=CONFIG.HOSPITAL_25_SERVICE %></title>
        <script>
            function checkAmount(){
                var amount = document.getElementById('donationValue').value.toString();
                if(amount.indexOf(".") !== -1){
                    document.getElementById('donationValue').setCustomValidity('هذه الخدمه لا تقبل الكسور، برجاء ادخال رقم صحيح');
                }else{
                    document.getElementById('donationValue').setCustomValidity('');
                }
            }
        </script>
    </head>
       <body onload="onloadFunction()">
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>
        
        <form action="HospitalConfirmationController" id="DonationForm" method="POST" style="font-weight: bold">
             <input type="hidden" name="action" value="<%=CONFIG.ACTION_HOSPITAL_25_CONFIRMATION %>" />
             <h2 align="center" ><%=CONFIG.HOSPITAL_25_SERVICE %></h2>
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <!--<th colspan="3" style="text-align: center"><p></p></th>-->
                    
                    <td style="border: none">
                        <p>نوع التبرع: </p>
                    </td>
                    <td style="border: none">
                        <div>
                            <div>
                                <input readonly autocomplete="off" type="text" value="<%=ProjectName %>" >
                            </div>
                        </div>
                    </td>
                    
                </tr>
                <tr>
                     <td style="border: none">
                        <p><%= CONFIG.getDonorPhone(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <div class="requriedclass">
                            <div>
                                <input name="DonatorPhone"  autocomplete="off" type="text" id="DonatorPhone" maxlength="11" title="<%=CONFIG.DonatorPhoneTitle(session)%>"  onblur="ThePhoneNotMatch(this.id);" >
                            </div>
                        </div>
                    </td>
                     <td style="border: none">
                       <img name="AboutPhoneNumber" style="visibility: hidden;cursor: pointer" id="AboutPhoneNumber" class="AboutPhoneNumber" title="<%=CONFIG.getAboutPhoneNo(session)%>" src="./img/help.png">
                     </td>
                </tr>
                <tr>
                    <td style="border: none">
                        <p><%= CONFIG.getDonationValue(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <div class="requriedclass">
                            <div>
                                <input name="donationValue" autocomplete="off" id="donationValue" type="number" required title="برجاء ادخال قيمه صحيحه اكبر من او تساوي 1"  onblur="checkAmount();">
                            </div>
                        </div>
                    </td>
                    <td style="border: none">
                        <img name="AboutAmount" style="visibility: hidden;cursor: pointer" id="AboutAmount" class="AboutAmount" title="<%=CONFIG.getAboutPhoneNo(session)%>" src="./img/help.png">
                    </td>
                </tr>
                <tr>
                    <td colspan="3" class="secondTD">
                        <input type="submit" value="<%=CONFIG.getConfirm(session)%>"  id="submitbutton"  />
                    </td>
                </tr>
            </table>

        </form>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
