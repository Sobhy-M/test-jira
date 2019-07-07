<%--
    Document   : EtisalatTopup
    Created on : 06/06/2018, 01:09:17 م
    Author     : Abdelsabor
--%>

<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
    

<%@page import="com.masary.integration.TopupServiceIntegrationStatus"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double etisalatBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 6);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/jquery-1.11.3.min.js"></script>
        
        <title><%=CONFIG.getCustomerMobinilTopUp(request.getSession())%></title>
        
       <script type="text/javascript">
       
       function resetValues() {
    	   var amountDiv = document.getElementById("amountDiv");
           document.getElementById("buttonSubmit").disabled = false;
           document.getElementById("validationDiv").innerHTML = '';
           amountDiv.innerHTML = '';
       }
       $(document).ready(function () {
           $("#topupAmount").change(function () {
               resetValues();
               var amount = document.getElementById("topupAmount").value;
               
                   $.ajax({
                       url: 'OrangeTopupAjaxRequest',
                       data: {
                           amount: amount
                       },
                       type: 'get'
                   }).done(function (responseData) {
                       var arr = responseData.split('-');
                       if(responseData === "-1"){
                    	   document.getElementById("validationDiv").innerHTML = 'لا يوجد  شحن بهذه القيمة';
                           document.getElementById("buttonSubmit").disabled = true;
                       }else {
                    	   if(arr.length == 1){
                    		   
                    		   var arr2 = responseData.split(","); 
                    		   document.getElementById("denmoID").value = arr2[1];
                    		   LoadTaxValue();
                    		   
                    	   }else{
                    		   for (i = 0; i < arr.length; i++) {
                                   var arr2 = arr[i].split(",");
                                   document.getElementById("amountDiv").style.display = "block";
                                   $("#amountDiv").append('<p><input type="radio" onChange="LoadTaxValue()" name="amountValueDiv" class="radioClass" id="' + arr2[1] + '" value="' + arr2[1] + '"  />' + arr2[0] + '</p>');                    
                               }
                    	   }
                       
                       }

                   }).fail(function () {
                       console.log('Failed to get denomantions types');
                   });
               
           });
       });
       
       
        function LoadTaxValue() {  

        	var amount = document.getElementById("topupAmount").value;
        	
            var urlStr = 'OrangeTopupRatePlan';
            
           var amountDivStr = document.getElementById("amountDiv").innerHTML;
           var denmoID = 0;
           
            if(amountDivStr !== ""){
            	denmoID = $('.radioClass:checked').val();
            	document.getElementById("denmoID").value = $('.radioClass:checked').val();
            }else{
            	denmoID = document.getElementById("denmoID").value;
            }

            
                $.ajax({
                    url: urlStr,
                    data: {
                        denmoID: denmoID,
                    },
                    type: 'get'
                }).done(function (responseData) { 
                    if(responseData === "-20"){
                 	   document.getElementById("validationDiv").innerHTML = 'حدث خطأ أثناء تنفيذ العملية [E-20]';
                        document.getElementById("buttonSubmit").disabled = true;
                        ajaxFlag = false;
                    }                   
                    else{
                    	 ajaxFlag = true;
                        var arr = responseData.split('-');
                        $('#Fees').val(arr[6]);
                        $('#Commession').val(arr[3]);
                        $('#servicetax').val(arr[7]);
                        $('#DeductedAmount').val(arr[8]);
                        $('#UserAmount').val(arr[11]);
                        $('#Balance_Diff').val(Number(arr[10]) + Number(arr[8]) * arr[6]);
                    }
                }).fail(function () {
                    ajaxFlag = false;
                    return;
                });

        }
        
        
        function validatePhoneNo()
        {
            var validformat = /^01\d{9}$/i;
            
            
            var MobileNum = document.getElementById("custTopUpMobileId").value;
            var mobileNumConfimation = document.getElementById("custMobileConfirmation").value;
                        
            if (validformat.test(MobileNum) && validformat.test(mobileNumConfimation))
            {
                return true;
            } else
            {
                return false;
            }
            
        }
        
                
        function validateMobileNum(){
        	
        	var MobileNum = document.getElementById("custTopUpMobileId").value;
        	
        	var mobileNumConfimation = document.getElementById("custMobileConfirmation").value;
        	
        	if(validatePhoneNo() === false){
        		document.getElementById("validationDiv").innerHTML = 'رقم الموبيل غير صحيح - تأكد من رقم الموبيل';
                return false;
        	}
        	
        	if(MobileNum === mobileNumConfimation){
        		return true;
        	}
        	else{
        		
        		document.getElementById("validationDiv").innerHTML = 'رقم الموبيل غير صحيح - تأكد من رقم الموبيل';
                return false;
        	}
        	
        }
        
        
        
        </script>
        
        
    </head>
    <BODY class="body">
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>  

                        <div id="validationDiv" style="color: red;font-weight: bold;font-size: medium"></div>

        <form name="BayCreditCustomer" action="OrangeTopupConfirmation" method="POST">
              
        <input type="hidden" name="denmoID" id="denmoID" />
        
        <input type="hidden" name="serviceID" id="serviceID" />
                
        <fieldset style="width: 70%; direction: rtl;" align="right">  
        
            <legend align="right" ><font size="5"><%=CONFIG.getCustomerMobinilTopUp(request.getSession())%></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20" > </legend> 
            
            <table border="1" style="width: 100%">
            
                <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                <tr>
                    <td><p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" name="topupAmount"  required tabindex="1" id="topupAmount" pattern="(?:\d*\.)?\d+" style="float: left;" autocomplete ="OFF" />

					<div id="amountDiv">
					</div>
                                               
                        <p align="right"><%=CONFIG.getMobileNumber(request.getSession())%> : <input id="custTopUpMobileId" maxlength="11"  autocomplete="off" type="text" name="<%=CONFIG.PARAM_MSISDN%>" required="" tabindex="2" style="float: left;">
                        <div id="custTopUpMobileDiv"  ></div></p>
                        <p align="right"><%=CONFIG.getConfirm(request.getSession())%> <%=CONFIG.getMobileNumber(request.getSession())%> :<input  id="custMobileConfirmation" maxlength="12" autocomplete="off" type="text" name="<%=CONFIG.PARAM_MSISDN_CONFIRMATION%>"  required="" tabindex="3" style="float: left;">
                        <div id="custMobileConfirmationDiv" name="custMobileConfirmationDiv"></div></p>
                        
                    </td>
                    
                    <td>
                        <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getServiceTaxe(request.getSession())%> :<input type="text" name="servicetax" value="0"  readonly tabindex="6" id="servicetax" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getBalance_Diff(request.getSession())%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        <p align="right"><%=CONFIG.getUserAmount(request.getSession())%> :<input type="text" name="UserAmount" value="0"  readonly tabindex="7" id="UserAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </br>
                    </td>
                </tr>
                <tr>
                    <td > <input type="submit" name="btnSubmit" id="buttonSubmit" tabindex="4"   value="<%=CONFIG.getGo(request.getSession())%>" onclick="return validateMobileNum()" class="Btn" <%=etisalatBalance == 0 ? "disabled='true'" : " "%> ></td>
                    <td><%=CONFIG.getAllowedBalance(request.getSession())%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(etisalatBalance)%></td>
                </tr>
            </table>
            <details open="open">
                <summary></summary>
                1-	من فضلك ادخل المبلغ المراد شحنه و رقم الموبايل. </br>            
                2-                يظهر لك فى الجهة اليسرى من الشاشة التكلفة التى يدفعها العميل بالإضافة إلى المبلغ و يظهر لك عمولتك على المبلغ بالجنيه و أيضاً يظهر لك المبلغ الذى سوف يتم خصمه من حسابك .</br></br>

                ملحوظة :- </br>
                •المبالغ التى يمكن شحنها فى إتصالات يجب أن تكون مبالغ صحيحة بدون كسور و هى: من 3 ج إلى 10 جنيه بزيادة 1 جنيه مثل 4 ج و 5 ج و هكذا ثم من 15 ج إلى 250 ج بزيادة 5 جنيه مثل 20 و 25 و 30 و هكذا.  	
                <br/>
                •يجب كتابة رقم الموبايل بالكامل (11 رقم) مثل 01112345678.
                أرقام موبايل فودافون غالباً تبدأ بـ011
                إذا كان رقم الموبايل المراد شحنه قد قام بتغيير الشبكة، يجب أن يتم شحنه بإستخدام الخدمة التى انتقل إليها.

                <br/>
                •مثال إذا كنت تريد شحن رقم الموبايل 01221234567 و الذى انتقل إلى شبكة إتصالات يجب الشحن بإستخدام خدمة شحن إتصالات و ليس شحن موبينيل.

            </details>
        </fieldset> 
    </form>
    
</div><!-- End of Table Content area-->


<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
