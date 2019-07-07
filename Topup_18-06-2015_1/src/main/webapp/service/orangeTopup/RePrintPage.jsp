<%-- 
    Document   : alex water payment
    Author     : Ahmed Khaled
--%>

<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<link href="img/style${lang}.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://www.java.com/js/deployJava.js"></script>
<script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><th colspan="2"><img src="./img/Masary.jpg" alt="’Masary Icon"></th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>شحن اورانج</td></tr >');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${reprintResponse.globalTrxId}</td><td style = "text-align: right;padding-right:25px;width:50%;">:رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${reprintResponse.transactionDate}</td><td style = "text-align: right;padding-right:25px;width:50%;">:تاريخ وتوقيت العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${reprintResponse.providerTrxId}</td><td style = "text-align: right;padding-right:25px;width:50%;" >:الرقم المرجعى</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${reprintResponse.msisdn}</td><td style="text-align: right;padding-right:25px;width: 50%;">:رقم العميل</td></tr>');
            <c:if test="${reprintResponse.appliedFees != 0.0}">
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${reprintResponse.appliedFees}</td><td style="text-align: right;padding-right:25px;width: 50%;">:تكلفة الخدمة</td></tr>');
            </c:if>
            <c:if test="${reprintResponse.tax != 0.0}">
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${reprintResponse.tax}</td><td style="text-align: right;padding-right:25px;width: 50%;">:ضريبة القيمة المضافة</td></tr>');
            </c:if>
                printwindow.document.write('<tr><td colspan="2" style="text-align: right;padding-right:25px;width: 50%;">اجمالى المبلغ المدفوع شامل ضريبة القيمة المضافة وتكلفة الخدمة: ${reprintResponse.toBePaid}</td></tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------</th></tr>');
            <c:if test="${reprintResponse.adsWord != ''}">
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>${reprintResponse.adsWord}</td></tr >');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------</th></tr>');
            </c:if>
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>قد يستغرق تنفيذ العملية 24 ساعة فى حالة بطء الشبكة</td></tr >');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------</th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>  شكرا لاستخدامكم مصاري لخدمات الدفع الذكية</td></tr >');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>خدمة عملاء مصاري: 16994</td></tr >');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size:16px;"><p>www.e-masary.com</td></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size:16px;"><p>=نسخة العميل=</td></tr>');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
            }
        </script>
<style type="text/css">
#load {
	position: absolute;
	top: 50%;
	z-index: 1;
	border: 3px double #999;
	width: 300px;
	height: 300px;
	margin-top: -150px;
	margin-left: -150px;
	background: #ffffff;
	top: 50%;
	left: 50%;
	text-align: center;
	line-height: 300px;
	font-family: "Trebuchet MS", verdana, arial, tahoma;
	font-size: 18pt;
	background-image: url(img/loading.gif);
	background-position: 50% 40%;
}
</style>

<title><%=CONFIG.getTransactionReport(request.getSession().getAttribute("lang").equals("") ? "en" : "ar")%></title>
</head>
<body onload="ray.hide();">
	<script type="text/javascript" src="img/CheckForms.js"></script>
	<div id="load" style="display: none;"><%=CONFIG.getPleaseWait(request.getSession().getAttribute("lang").equals("") ? "en" : "ar")%></div>
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
		<form
			action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>"
			method="POST">
			<table border="1" style="width: 40%">
				<tr>
					<td>
						<p align="right">رقم العملية</p>
					</td>
					<td><input type="text" value="${reprintResponse.globalTrxId}"
						readonly style="background-color: #EDEDED;"></td>
				</tr>
				<tr>
					<td>
						<p align="right">الى</p>
					</td>
					<td><input type="text" value="${reprintResponse.msisdn}"
						readonly style="background-color: #EDEDED;"></td>
				</tr>
				<tr>
					<td>
						<p align="right">النوع</p>
					</td>
					<td><input type="text" value="شحن فودافون" readonly
						style="background-color: #EDEDED;"></td>
				</tr>
				<tr>
					<td>
						<p align="right">الحالة</p>
					</td>
					<td><input type="text" value="ناجحة" readonly
						style="background-color: #EDEDED;"></td>
				</tr>
				<tr>
					<td>
						<p align="right">قيمة الشحن</p>
					</td>
					<td><input type="text"
						value="${reprintResponse.transactionAmount}" readonly
						style="background-color: #EDEDED;"></td>
				</tr>
				<tr>
					<td>
						<p align="right">قيمة الضريبة</p>
					</td>
					<td><input type="text" value="${reprintResponse.appliedFees}"
						readonly style="background-color: #EDEDED;"></td>
				</tr>
				<tr>
					<td>
						<p align="right">القيمة المطلوبة من العميل</p>
					</td>
					<td><input type="text" value="${reprintResponse.toBePaid}"
						readonly style="background-color: #EDEDED;"></td>
				</tr>
				<tr>
					<td>
						<p align="right">التاريخ</p>
					</td>
					<td><input type="text"
						value="${reprintResponse.transactionDate}" readonly
						style="background-color: #EDEDED;"></td>
				</tr>
				<tr>
					<td style="text-align: center" colspan="2"><input
						type="submit" name="btnSubmit" value="اغلاق وطباعه" class="Btn"
						onclick="setDivPrint()"></td>
				</tr>
				<tr style="display: none">
					<th colspan="2"><img src="./img/Masary.jpg" width="50"
						height="50"></th>
				</tr>
			</table>
		</form>
	</div>
	<div style="clear: both;">&nbsp;</div>
	<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

</body>
</html>
