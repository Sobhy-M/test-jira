<%@page import="com.masary.integration.dto.PetrotradeCounterReadingInquiry"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%
    MasaryManager.logger.info("Petrotrade Counter Reading Info ");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();

    PetrotradeCounterReadingInquiry petrotradeCounterReadingInquiry = (PetrotradeCounterReadingInquiry) session.getAttribute("petroTradeCounterReadingInquiry");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> <%=CONFIG.getPetrotradeCounterReadingInfo(request.getSession())%></title>
        <script type="text/javascript">
            var ray = {
                ajax: function (st)
                {
                    this.show('load');
                },
                hide: function (st)
                {
                    var load = Document.getElementById("load");
                    load.style = "display:none;";
                },
                show: function (el)
                {
                    this.getID(el).style.display = '';
                },
                getID: function (el)
                {
                    return document.getElementById(el);
                }
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
    <body  class="body">
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    
            <%}%>
        </div>
        <font style="color: red; font-size: 15px;">${ErrorCode}</font>     
        <div class="content_body">

            <form name="PetrotradeCounterReadingPayment" action="PetroTradeCounterReadingPaymentController" method="POST">
                <table>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=CONFIG.getPetrotradeCounterReadingInfo(request.getSession())%>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" style="text-align: right">
                            <%=CONFIG.getPetrotradeCounterReadingRequiredInfo(request.getSession())%>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getMemberName(request.getSession())%></p>
                        </td>
                        <td>
                            <input title="<%=CONFIG.getPetrotradeCounterReadingCustomerName(request.getSession())%>" style="background-color: #EDEDED;" readonly type="text" value="<%= petrotradeCounterReadingInquiry.getSubscriberName()%>">
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getCurrentReading(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="CurrentReading"  title="<%=CONFIG.GetOperationIdTitle_Ar(request.getSession())%>"  type="number" required name="reading">
                        </td>
                    </tr>

                    <tr>

                        <td> <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getConfirmation(session)%>" class="Btn" onclick="return ray.ajax()" > </td> 
                        <td><input type="button" name="Back" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);"> </td>
                    </tr>

                    <tr><td colspan="2">

                            برجاء ادخال القراءة الحالية للعداد من واقع المسجل فى العداد الخاص بالمشترك                                                                                                                                                
                            <br/>
                            <br/>

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