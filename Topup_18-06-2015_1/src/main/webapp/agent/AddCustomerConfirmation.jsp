<%-- 
    Document   : AddCustomer.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="java.util.Map"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null || !role.equals("2")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);
    ArrayList<CustomerServiceDTO> agentServices = (ArrayList<CustomerServiceDTO>) session.getAttribute("services");
    String newActivationCheck = MasaryManager.getInstance().getConfigration("new_activation_process");
    if (newActivationCheck.equals("1")) {
        for (int i = agentServices.size() - 1; i >= 0; --i) {
            if (agentServices.get(i).getServiceID() != 1) {
                agentServices.remove(i);
            }
        }
    }
    session = request.getSession();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAddCustomerConfirmation(session)%></title>
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>

    </head>
    <BODY class="body">
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <form name="AddCustomer" action="AddNewWalletAgentConfirmation" method="POST" onsubmit="return ValidateAddCustForm()" >
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_ADD_CUSTOMER%>"/>
            <input type="hidden" name="gender" value="<%=requestParameters.get("gender")%>"/>
            <input type="hidden" name="AccountType" value="<%=requestParameters.get("AccountType")%>"/>
            <input type="hidden" name="EMAIL_ADDRESS" value="<%=requestParameters.get("EMAIL_ADDRESS")%>"/>
            <input type="hidden" name="FullAddress" value="<%= new String(requestParameters.get("FullAddress").toString().getBytes("ISO-8859-1"), "utf-8")%>"/>
            <input type="hidden" name="HomeGovAddress" value="<%= new String(requestParameters.get("HomeGovAddress").toString().getBytes("ISO-8859-1"), "utf-8")%>"/>
            <input type="hidden" name="HomeCityAddress" value="<%= new String(requestParameters.get("HomeCityAddress").toString().getBytes("ISO-8859-1"), "utf-8")%>" />
            <input type="hidden" name="HomeRegionAddress" value="<%= new String(requestParameters.get("HomeRegionAddress").toString().getBytes("ISO-8859-1"), "utf-8")%>" />
            <input type="hidden" name="WorkFullAddress" value="<%= new String(requestParameters.get("WorkFullAddress").toString().getBytes("ISO-8859-1"), "utf-8")%>"/>
            <input type="hidden" name="WorkGovAddress" value="<%= new String(requestParameters.get("WorkGovAddress").toString().getBytes("ISO-8859-1"), "utf-8")%>"/>
            <input type="hidden" name="WorkCityAddress" value="<%= new String(requestParameters.get("WorkCityAddress").toString().getBytes("ISO-8859-1"), "utf-8")%>" />
            <input type="hidden" name="WorkRegionAddress" value="<%= new String(requestParameters.get("WorkRegionAddress").toString().getBytes("ISO-8859-1"), "utf-8")%>" />
            <input type="hidden" name="MSISDN_ALTERNATIVE" value="<%=requestParameters.get("MSISDN_ALTERNATIVE")%>"/>
            <input type="hidden" name="ShopName" value="<%= new String(requestParameters.get("ShopName").toString().getBytes("ISO-8859-1"), "utf-8")%>" />
            <input type="hidden" name="WorkType" value="<%= new String(requestParameters.get("WorkType").toString().getBytes("ISO-8859-1"), "utf-8")%>" /><input type="hidden" name="SecurityQuestion" value="<%=requestParameters.get("SecurityQuestion")%>" />
            <input type="hidden" name="SecurityAnswer" value="<%= new String(requestParameters.get("SecurityAnswer").toString().getBytes("ISO-8859-1"), "utf-8")%>" />
            <input type="hidden" name="BirthDate" value="<%=requestParameters.get("BirthDate")%>" />
            
            <input type="hidden" id="ImageUrl_1" name="ImageUrl_1" value="<%=requestParameters.get("ImageUrl_1")%>" />
            <input type="hidden" id="ImageUrl_2" name="ImageUrl_2" value="<%=requestParameters.get("ImageUrl_2")%>" />
            <input type="hidden" id="ImageUrl_3" name="ImageUrl_3" value="<%=requestParameters.get("ImageUrl_3")%>" />
            <input type="hidden" id="ImageUrl_4" name="ImageUrl_4" value="<%=requestParameters.get("ImageUrl_4")%>" />
            <input type="hidden" id="ImageUrl_5" name="ImageUrl_5" value="<%=requestParameters.get("ImageUrl_5")%>" />
            
            <div class="content_body">
                <table>
                    <tr>
                        <td><%=CONFIG.getAgentNameInArabic(session)%></td>
                        <td>
                            <input type="text" value="<%= new String(requestParameters.get("FullArabicName").toString().getBytes("ISO-8859-1"), "utf-8")%>"  readonly style="background-color: #EDEDED;width: 300px" id="FullArabicName" name="FullArabicName"/>
                        </td>
                    </tr>
                    <tr>
                        <td><%=CONFIG.getAgentNameInEnglish(session)%></td>
                        <td>
                            <input type="text" readonly value="<%=requestParameters.get("FullEnglishName")%>" style="background-color: #EDEDED;width: 300px"  id="FullEnglishName" name="FullEnglishName"/>
                        </td>
                    </tr>
                    <tr>
                        <td><%=CONFIG.getNationalID(session)%></td>
                        <td><input type="text" value="<%=requestParameters.get("NATIONAL_ID")%>"  id="NATIONAL_ID" name="NATIONAL_ID" readonly style="background-color: #EDEDED;"/></td>
                    </tr>
                    <tr>
                        <td><%=CONFIG.getMobileNumber(session)%></td>
                        <td><input type="text" name="<%=CONFIG.PARAM_MSISDN%>" readonly style="background-color: #EDEDED;" value="<%=requestParameters.get(CONFIG.PARAM_MSISDN)%>"/></td>
                    </tr>
                    <tr>
                        <td><%=CONFIG.getAgnetLandLineNumber(session)%></td>
                        <td><input type="text" readonly style="background-color: #EDEDED;" value="<%=requestParameters.get("LAND_LINE_PHONE")%>" id="LAND_LINE_PHONE" name="LAND_LINE_PHONE"/></td>
                    </tr>
                    <tr>
                        <td><input type="button" dir="rtl" name="btnSubmit" tabindex="2" value="<%=CONFIG.getBack(session)%>" class="Btn" onclick="history.go(-1);
                                return true;"></td>
                        <td><input type="submit" name="btnSubmit"  value="<%=CONFIG.getSave(session)%>" class="Btn"></td>
                    </tr>
                </table>
            </div><!-- End of Table Content area-->
        </form>
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>





