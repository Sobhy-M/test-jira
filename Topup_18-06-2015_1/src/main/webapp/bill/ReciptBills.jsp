<%-- 
    Document   : bill_payment_confirmation
    Created on : 01/05/2012, 07:12:20 م
    Author     : Michael
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="com.masary.database.dto.Bill_Response"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Payment_Response"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>


<%
	String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
	if (role == null) {
		response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
		return;
	}

	
	session = request.getSession();
	Masary_Bill_Type BTC = MasaryManager.getInstance()
			.getBTC(String.valueOf(request.getSession().getAttribute("serv_id").toString()));
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat timeFormat = new SimpleDateFormat("HH:mm");
	//get current date time with Date()
	Date date = new Date();
	String date1 = dateFormat.format(date);
	String time = timeFormat.format(date);
	Bill_Response billResonse = (Bill_Response) request.getSession().getAttribute("bill_Response");
	String BillDate = "";
	if (BTC.getBILL_TYPE_ID() != 112 && BTC.getBILL_TYPE_ID() != 111) {
		BillDate = billResonse.getBILL_DATE().substring(0, 4) + "-" + billResonse.getBILL_DATE().substring(4);
	}
%>
<link
	href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
	
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=CONFIG.getPrinting_Receipt(request.getSession())%></title>



<script>

            function gotoHome()
            {
                window.location.replace("2.jsp");
            }

            function setDivPrint() {

                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="7"  style="font-size: 15px; font-weight: 700; width: 350px" >');
                <%if (request.getSession().getAttribute("serv_id").toString().equals("124")) {%>
                printwindow.document.write('<tr><th colspan="2"><img src="./img/Masary.jpg" alt="’Masary Icon" width="150" height="60"></th></tr>');
                printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center">We</th></tr>');
                printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center">الخط الارضى</th></tr>');
               <%} else {%>
               printwindow.document.write('<tr><th colspan="2"><img src="./img/Masary.jpg" alt="’Masary Icon" width="auto" height="auto"></th></tr>');
               <%}%>
                
                
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> ${bill_Response.BILLING_ACCOUNT}</td><td style="text-align: right;padding-right:25px;width: 60%;">رقم التليفون</td> </tr>');
            <%if (!request.getSession().getAttribute("serv_id").toString().equals("112")
					&& !request.getSession().getAttribute("serv_id").toString().equals("111")) {%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> <%=new String(request.getParameter("Customer_name").getBytes("ISO-8859-1"), "UTF-8")%> </td><td style="text-align: right;padding-right:25px;width: 60%; ">اسم العميل</td> </tr>');
            <%}%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${bill_Response.AMOUNT}</td><td style = "text-align: right;padding-right:25px;width:50%;">قيمة الفاتورة</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${Fees} </td><td style="text-align: right;padding-right:25px;width: 50%;">تكلفة الخدمة </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${bill_Response.AMOUNT+Fees} </td><td style="text-align: right;padding-right:25px;width: 50%;">المبلغ الكلي </td> </tr>');
                printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier; text-align: center;width: 100%;" colspan="2">------------------------------</th></tr>');
            <%if (request.getSession().getAttribute("serv_id").toString().equals("124")) {%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=BillDate%></td><td style="text-align: right;padding-right:25px;width: 50%;" > تاريخ الفاتورة </td></tr>');
            <%}
			if (request.getSession().getAttribute("serv_id").toString().equals("117")) {%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${bill_Response.BILL_DATE}</td><td style="text-align: right;padding-right:20px;width: 50%;"> تاريخ انتهاء الفاتورة </td></tr>');
            <%}%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> ${bill_Response.TRANSACTION_ID}</td><td style = "text-align: right;padding-right:25px;width:50%;"> رقم العملية </td> </tr>');
            <%if (request.getSession().getAttribute("serv_id").toString().equals("117")
					|| request.getSession().getAttribute("serv_id").toString().equals("111")) {%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${bill_Response.PROVIDER_TRANSACTION_ID}</td><td style="text-align:right;padding-right:20px;width: 50%; "> رقم الطلب </td></tr>');
            <%}%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">  <%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%></td><td style="text-align: right;padding-right:25px;width: 50%; ">البائع</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> عملية ناجحة</td><td style="text-align: right;padding-right:25px;width: 50%; ">حاله العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> <%=date1%></td><td style="text-align: right;padding-right:25px;width: 50%;" > التاريخ </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=time%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" > الوقت </td></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >------------------------------------------</th> </tr >');
                <%if (request.getSession().getAttribute("serv_id").toString().equals("124")) {%>
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> te.eg للاشتراك حدث بياناتك من خلال   <p></th> <p></th> </tr >');
              <%}%>;
                printwindow.document.write('<tr> <th style="font-family:courier;font-size:14px;text-align:center;" colspan="2">قد يستغرق تنفيذ العمليه ساعتين في حاله بطئ الشبكات</th> </tr>');
                printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier;text-align: center;width: 100%" colspan="3">شكرا لاستخدامكم مصارى لخدمات الدفع الذكية</th> </tr>');
                printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier; text-align: center;width: 100%" colspan="3">خدمة عملاء مصارى: 16994</th></tr></table>');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();

            }

</script>

</head>
<body class="body" onload="setDivPrint()">
	<script type="text/javascript" src="img/CheckForms.js"></script>
	<%
		if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
	%>
	<jsp:include page="../img/menuList.jsp"></jsp:include>
	<%
		} else {
	%>
	<jsp:include page="../img/menuListar.jsp"></jsp:include>
	<%
		}
	%>
	</div>

	<div>
		<form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST"
			style="font-weight: bold">
			<table cellspacing="10" cellpadding="4"
				style="font-size: 20px; font-weight: 900;">
				<tr>
					<th colspan="2" style="text-align: center;">شركة مصاري</th>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center; font-size: 16px;"><%=BTC.getStrBTCName(session)%></th>
				</tr>
				<tr>
					<td style="width: 50%; padding-right: 30px; text-align: right;">رقم
						التليفون</td>
					<td style="padding-left: 10px;">${bill_Response.BILLING_ACCOUNT}</td>
				</tr>
				<%
					if (!request.getSession().getAttribute("serv_id").toString().equals("112")
							&& !request.getSession().getAttribute("serv_id").toString().equals("111")) {
				%>
				<tr>
					<td style="width: 50%; padding-right: 30px; text-align: right;">اسم
						العميل</td>
					<td style="padding-left: 10px;"><%=new String(request.getParameter("Customer_name").getBytes("ISO-8859-1"), "UTF-8")%></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td style="width: 50%; padding-right: 30px; text-align: right;">قيمة
						الفاتورة</td>
					<td style="padding-left: 10px;">${bill_Response.AMOUNT}</td>
				</tr>
				<tr>
					<td style="width: 50%; padding-right: 30px; text-align: right;">تكلفة
						الخدمة</td>
					<td style="padding-left: 10px;">${Fees}</td>
				</tr>
				<tr>
					<td style="width: 50%; padding-right: 30px; text-align: right;">المبلغ
						الاجمالي</td>
					<td style="padding-left: 10px;">${bill_Response.AMOUNT + Fees}</td>
				</tr>
				<tr>
					<td style="width: 50%; padding-right: 30px; text-align: right;">حاله
						العملية</td>
					<td style="padding-left: 10px;">عملية ناجحة</td>
				</tr>
				<tr>
					<td style="width: 50%; padding-right: 30px; text-align: right;">رقم
						العملية</td>
					<td style="padding-left: 10px;">${bill_Response.TRANSACTION_ID}</td>
				</tr>
				<%
					if (request.getSession().getAttribute("serv_id").toString().equals("124")) {
				%>
				<tr>
					<td style="width: 50%; padding-right: 30px; text-align: right;">
						تاريخ الفاتورة</td>
					<td style="padding-left: 10px;"><%=BillDate%></td>
				</tr>
				<%
					} else if (request.getSession().getAttribute("serv_id").toString().equals("117")) {
				%>
				<tr>
					<td style="width: 50%; padding-right: 30px; text-align: right;">
						تاريخ انتهاء الفاتورة</td>
					<td style="padding-left: 10px;">${bill_Response.BILL_DATE}</td>
				</tr>
				<%
					}
				%>
				<%
					if (request.getSession().getAttribute("serv_id").toString().equals("117")
							|| request.getSession().getAttribute("serv_id").toString().equals("111")) {
				%>
				<tr>
					<td style="width: 50%; padding-right: 30px; text-align: right;">
						رقم الطلب</td>
					<td style="padding-left: 10px;">${bill_Response.PROVIDER_TRANSACTION_ID}</td>
				</tr>
				<%
					}
				%>
				<tr>
					<td style="padding-left: 15px; width: 50%;">البائع</td>
					<td style="text-align: right; padding-right: 25px; width: 50%;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%></td>
				</tr>
				<tr>
					<td style="width: 50%; padding-right: 30px; text-align: right;">التاريخ</td>
					<td style="padding-left: 10px;"><%=time%></td>
				</tr>
				<tr>
					<td style="width: 50%; padding-right: 30px; text-align: right;">الوقت
					</td>
					<td style="padding-left: 10px;"><%=date1%></td>
				</tr>
				<tr>
					<th colspan="2"
						style="text-align: right; font-size: 16px; padding-right: 30px">قد
						يستغرق تنفيذ العمليه ساعتين في حاله بطئ الشبكات</th>
				</tr>
				<tr>
					<th colspan="2"
						style="text-align: right; font-size: 16px; padding-right: 30px">شكرا
						لاستخدامكم مصارى لخدمات الدفع الذكية</th>
				</tr>
				<tr>
					<th colspan="2"
						style="text-align: right; font-size: 16px; padding-right: 30px">خدمة
						عملاء مصارى: 16994</th>
				</tr>
				<tr>
					<th colspan="2"
						style="text-align: center; font-size: 16px; padding-right: 30px">www.e-masary.com</th>
				</tr>


			</table>

			 <img src="./img/WeLandLine.jpg" alt="skills Bank Icon" width="50" height="50"
				style="visibility: hidden;"> 
                         <img src="./img/Masary.jpg"  alt="skills Bank Icon" width="50" height="50"
				style="visibility: hidden;">
                         <input type="submit" id="print"
				value="OK" />
                         
            <input type="submit" id="printButton"  value="print" onclick="setDivPrint()" />

		</form>
	</div>
	</div>
	<!-- End content body -->
	<div style="clear: both;">&nbsp;</div>
	<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
	</div>
	<!-- End of Main body-->
</body>
</html>
