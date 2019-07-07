<%-- 
    Document   : bill_inquiry
    Created on : 01/05/2012, 07:10:57 م
    Author     : Michael
--%>

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
    MasaryManager.logger.info("Petrotrade inquiry ");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> <%=CONFIG.getPetrotradeInquiry(request.getSession())%></title>
        <script type="text/javascript">
           function checkNumber(){
               document.getElementById("MemberNumber").value;
           }
           
        </script>
        <style type="text/css">
            #load{
                position:absolute;
                top:50%;
                z-index:1;
                border:3px double #999;
                width:300px;
                height:300px;
                margin-top:-150px;
                margin-left:-150px;
                background:#ffffff;
                top:50%;
                left:50%;
                text-align:center;
                line-height:300px;
                font-family:"Trebuchet MS", verdana, arial,tahoma;
                font-size:18pt;
                background-image:url(img/loading.gif);
                background-position:50% 40%;
            }
            input[type=number]::-webkit-inner-spin-button, 
            input[type=number]::-webkit-outer-spin-button { 
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                margin: 0; 
            }
        </style>
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
         
            <form name="PetrotradeInquiry" action="PetroTradeInfoController" method="POST">
                <input type="hidden" name="<%=CONFIG.PARAM_ACTION%>" value="<%=CONFIG.ACTION_GETINFO_PETROTRADE%>" />
                <table>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=CONFIG.getPetrotradeInquiry(request.getSession())%>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getMemberNumber(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="MemberNumber" maxlength="30" title="<%=CONFIG.GetOperationIdTitle_Ar(request.getSession())%>" type="number" required name="MemberNumber" >
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center"><input type="submit" name="btnSubmit" tabindex="3" value="<%=CONFIG.getCheck(session)%>" class="Btn"></td>
                    </tr>
                    <tr><td colspan="2">

                            ان امكن ادخل رقم المشترك المدون علي الفاتورة الخاصه بشركة بتروتريد والضغط علي الاستعلام لاظهار بيانات الفاتورة
                            <br/>
                            <br/>
                            <p style="font-weight: bold">
                                تنويه: برجاء العلم ان خدمة سداد فواتير شركة بترو تريد غير متاحة خلال اول 5 ايام من كل شهر بناء على طلب الشركة
                            </p>
                        </td></tr>
                </table>

            </form>
        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>