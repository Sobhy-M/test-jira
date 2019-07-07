<%-- 
    Document   : mubasherReceipt
    Created on : Oct 26, 2016, 2:46:35 PM
    Author     : AYA
--%>

<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.database.dto.MubasherResponseDto"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPrinting_Receipt(request.getSession())%></title>
        <%

            MubasherResponseDto[] list = (MubasherResponseDto[]) request.getSession().getAttribute("MubasherList");

        %>
        <script>
            <%     for (int n = 0; n < list.length; n++) {
                    TransactionDTO trans;
                    try {
                        trans = MasaryManager.getInstance().getTransaction(String.valueOf(list[n].getTRANSACTION_ID()));
                    } catch (Exception ex) {
                        session.setAttribute("ErrorMessage", "Mubasher Voucher txn Detailed error code is:" + ex.getMessage());
                        response.sendRedirect(CONFIG.APP_ROOT + "error.jsp");
                        ex.printStackTrace();
                        return;
                    }
            %>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
//                printwindow.document.write('<tr><th colspan="2">شركة مصاري</th></tr>');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/moubasher.bmp"  width="150" height="62"/></td></tr>');
                printwindow.document.write('<tr><th colspan="2" >(<%= request.getParameter("DenmonationVoucher")%>) كارت مباشر</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= list[n].getORIGINAL_AMOUNT() %></td><td style="text-align: right;padding-right:25px;width: 50%;">المبلغ بالجنية </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= list[n].getTOTAL_FEES() %> </td><td style ="text-align: right;padding-right:25px;width: 50%;" > تكلفة الخدمة </td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= list[n].getCUSTOMER_AMOUNT() %></td><td style="text-align: right;padding-right:25px;width: 50%;" > المبلغ الاجمالي </td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> <%= session.getAttribute(CONFIG.PARAM_PIN)%></td><td style = "text-align: right;padding-right:25px;width:50%;" > رقم المحفظة</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= list[n].getTRANSACTION_ID()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= list[n].getTRANSACTION_DATE() %></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">User Name:</td><td style = "text-align: right;padding-right:25px;width:50%;"><%=list[n].getUSER_ID()%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">Password :</td><td style = "text-align: right;padding-right:25px;width:50%;"><%=list[n].getPASSWORD()%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">Mobile :</td><td style = "text-align: right;padding-right:25px;width:50%;"><%=request.getParameter(CONFIG.PARAM_MSISDN)%></td> </tr><tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>تم ارسال رساله نصيه على الرقم المدخل يحتوي بيانات الكارت  <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2"  ><p>--------------------------------------</p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr>  ');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >خدمه عملاء مباشر : 16717</p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>www.directfn.com.eg</p></th> </tr ></table>');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();

                window.location.replace("2.jsp");
            }<%}%>
        </script>
    </head>
    <body class="body">
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession()
                        .getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>
            <h3 style="color: red; font-size: 18px"><br></h3>
            <% for (int i = 0; i < list.length; i++) {
            %>
        <div>
            <table cellspacing="10" style="font-size: 20px; font-weight: 900;" > 
                <tr><!--<th colspan="2">شركة مصاري</th> --></tr>
                <tr><td colspan="2" style="text-align: center">كروت مباشر</td> </tr>
                <!--<tr><td colspan="2" style="text-align: center"> <img src="img/moubasher.bmp"/></td> </tr>-->
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">حاله العملية</td><td style="padding-left: 10px;">عملية ناجحة</td></tr>
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">تاريخ العملية</td><td style="padding-left: 10px;"> <%= list[i].getTRANSACTION_DATE() %></td></tr>
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">رقم العملية </td><td style="padding-left: 10px;"><%= list[i].getTRANSACTION_ID()%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">القيمة</td><td style="padding-left: 10px;"><%= list[i].getORIGINAL_AMOUNT() %> جنيه</td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">تكلفة الخدمة </td><td style="padding-left: 10px;"><%= list[i].getTOTAL_FEES() %> جنيه</td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">المبلغ الاجمالي </td><td style="padding-left: 10px;"><%= list[i].getCUSTOMER_AMOUNT() %> جنيه</td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم التليفون </td><td style="padding-left: 10px;"><%= request.getParameter(CONFIG.PARAM_MSISDN)%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم المحفظة </td><td style="padding-left: 10px;"><%= session.getAttribute(CONFIG.PARAM_PIN)%></td></tr>
                <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994</th> </tr>
                <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px"><a >خدمه عملاء مباشر : 16717</a></th> </tr>
                <tr> <th colspan="2" style="text-align:center;font-size:16px; padding-right: 30px">www.e-masary.com</th> </tr>
                <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value="طباعه" onclick="setDivPrint();" /></td></tr>
            </table>
        </div>
        <%
            }%>
        <!--</form>-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div 
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
