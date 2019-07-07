<%@page import="java.util.Arrays"%>
<%@page import="com.masary.integration.dto.SMS_MisrPackageResponse"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.CurrencyConvertRate"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.EmployeeDTO"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="com.masary.database.dto.ProviderDTO"%>
<%@page import="com.masary.database.dto.CategoryDTO"%>

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    int serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
    request.getSession().setAttribute("SERVICE_ID", serviceId);
    String rolePage = CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp";

    SMS_MisrPackageResponse[] smsMisrPackagesDTO = (SMS_MisrPackageResponse[]) request.getSession().getAttribute("smsMisrPackagesDTO");
    

%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/jquery-1.11.3.min.js"></script>
        <title><%=CONFIG.SMSMISR_SERVICE_NAME%></title>
        <style>
            p{
                width: 105%;
            }
        </style>
        <script type="text/javascript">

        </script>

    </head>
    <BODY class="body" >
        <div>
            <div>        
                <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
                <jsp:include page="../../img/menuList.jsp"></jsp:include>
                <%} else {%>
                <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
                </div>

                <form name="BayCreditCustomer" action="<%=CONFIG.APP_ROOT%>SMSMISRController" method="post" >
                <input type="hidden" name="action" value="<%=CONFIG.ACTION_SMSMISR_GETINFO%>" >
                <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=serviceId%>" >
                <fieldset style="width: 50%; direction: rtl;" align="right">  
                    <legend align="center" ><p align="center" style="font-size: large">البيانات المطلوبة</p></legend> 
                    <table style="width: 100% ; margin-left: auto ; margin-right: auto ;border: #000;border-width: 1" border="1" >
                        <tr>
                            <td>
                                <p align="right" style="font-size: large"><%=CONFIG.SMSMISR_Customer_Code%> :
                                    <input id="code" type="text" required name="customerCode" tabindex="2" >
                                </p>
                            </td>
                        </tr>
                        <tr style="width: 80%">
                            <td style="padding-bottom: 15%">
                                <table style="width: 100%">
                                    <th><p align="center" style="font-size: large">من فضلك إختار باقتك</p></th>
                                    <tr>
                                        <td>
                                            <table style="width: 100% ;">
                                                <% for (SMS_MisrPackageResponse t : smsMisrPackagesDTO) {%> 
                                                <tr>
                                                    <td>
                                                        <p>
                                                            <input type="radio" name="package" required value="<%= t.getPackageId()%>"/>
                                                            <input type="text" name="packagePrice" readonly value="<%= t.getPackagePrice()%> EGP" style="background-color: #EDEDED; float: left;"/>
                                                        </p>
                                                    </td>
                                                    <td>
                                                        <input type="text" name="packageName" readonly value="<%= t.getPackageName()%> SMS" style="background-color: #EDEDED; float: left;"/>
                                                    </td>
                                                </tr>
                                                <%}%>
                                            </table>                        
                                        </td>
                                    </tr>
                                </table>
                            </td>

                        </tr>
                        <tr>
                            <td> <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getCheck(request.getSession())%>"  class="Btn" >
                                <input type="button" name="btnBack" tabindex="4"  value="الغاء" onclick="window.location.href = '<%=rolePage%>';"  class="Btn" style="float: left;">
                            </td>
                        </tr>

                    </table>

                </fieldset> 
            </form>


        </div><!-- End content body -->
        <br>
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </div><!-- End of Main body-->

</body>
</html>