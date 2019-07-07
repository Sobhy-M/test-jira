<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%
    try {
        MasaryManager.logger.info("Petrotrade Counter Reading Inquiry ");
        String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
        if (role == null) {
            response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
            return;
        }

        session = request.getSession();
    } catch (Exception e) {
        MasaryManager.logger.error("Error while processing page" + e.getMessage(), e);
    }
%>


<html>
    <head>
        <link
            href="https://cdn.e-masary.net/app/img/style<%=session.getAttribute(CONFIG.lang)%>.css"
            rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPetrotradeCounterReadingInquiry(session)%></title>
        <script type="text/javascript">
            var ray = {
                ajax: function (st) {
                    this.show('load');
                },
                hide: function (st) {
                    var load = Document.getElementById("load");
                    load.style = "display:none;";
                },
                show: function (el) {
                    this.getID(el).style.display = '';
                },
                getID: function (el) {
                    return document.getElementById(el);
                }
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
            <script type="text/javascript"
            src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            %>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%
            } else {
            %>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%
                }
            %>
        </div>
        <font style="color: red; font-size: 15px;">${ErrorCode}</font>
        <div class="content_body">

            <form name="PetrotradeCounterReadingInfo"
                  action="PetroTradeCounterReadingInfoController" method="POST">
                <input type="hidden" name="<%=CONFIG.PARAM_ACTION%>"
                       value="<%=CONFIG.ACTION_GETINFO_PETROTRADE_COUNTERREADING%>" />
                <table>
                    <tr>
                        <td colspan="2" style="text-align: center"><%=CONFIG.getPetrotradeCounterReadingInquiry(request.getSession())%>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getMemberNumber(request.getSession())%></p>
                        </td>
                        <td><input id="MemberNumber" maxlength="30"
                                   title="<%=CONFIG.GetOperationIdTitle_Ar(request.getSession())%>"
                                   type="number" required name="subscriberNumber"></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center"><input
                                type="submit" name="btnSubmit" tabindex="3"
                                value="<%=CONFIG.getCheck(session)%>" class="Btn"></td>
                    </tr>
                    <tr>
                        <td colspan="2">برجاء ادخال رقم المشترك المدون على الفاتورة
                            الخاصة ببتروتريد لتأكيد بيانات المشترك و الاستمرارفى تسجيل قراءة
                            العداد <br /> <br />
                            <p style="font-weight: bold">*تنويه:برجاء العلم أن خدمة تسجيل
                                قراءة العداد لشركة بتروتريد غير متاحة خلال اول 5 أيام من كل شهر
                                بناءً على طلب الشركة</p>
                        </td>
                    </tr>
                </table>

            </form>
        </div>
        <!-- End of Table Content area-->
    </div>
    <!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div>
<!-- End of Main body-->

</body>
</html>

