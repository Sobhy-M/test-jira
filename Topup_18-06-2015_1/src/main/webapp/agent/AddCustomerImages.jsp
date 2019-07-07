<%--
    Document   : AddCustomer.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="java.util.Map"%>
<%@page import="com.masary.utils.SystemSettingsUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.masary.database.dto.Governorate"%>
<%@page import="com.masary.database.dto.Country"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null || !role.equals("2")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    
    Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);
    
    String encryptedId = (String) request.getSession().getAttribute(CONFIG.PARAM_ENC_ID);
    String encryptedPass = (String) request.getSession().getAttribute(CONFIG.PARAM_ENC_PASS);
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAddCustomer(session)%></title> 
        <script type="text/javascript">
            var url1 = "", url2 = "", url3 = "No Imgage", url4 = "No Imgage", url5 = "No Imgage";
            var n = new Date();
            var date = n.toDateString();
            date = date.replace(/ /g, '');
            function fileSelected(id) {
                var file = document.getElementById(id).files[0];
                if (file) {
                    if (file.size > 1024 * 1024 * 5)
                    {
                        document.getElementById("ErrorUploadMessage").innerHTML = "<%=CONFIG.getFileSizeError(session)%>";
                        document.getElementById("ErrorUploadMessage").style.visibility = "visible";
                        document.getElementById(id).value = "";
                    }
                    else
                    {
                    }
                }
            }
            function uploadFirstImage()
            {
                document.getElementById("UploadDone1").style.visibility = 'hidden';
                var phone = document.getElementById("MSISDN").value;
                var fd = new FormData();
                fd.append("encID", "<%=encryptedId %>" );
                fd.append("encPass", "<%=encryptedPass %>");
                fd.append("buttonIndex", "1");
                fd.append("uploadDate", date);
                fd.append("phoneNum", phone);
                fd.append("fileToUpload1", document.getElementById('fileToUpload1').files[0]);
                var xhr = new XMLHttpRequest();
                xhr.upload.addEventListener("progress", uploadProgress, false);
                xhr.addEventListener("load", uploadComplete1, false);
                xhr.addEventListener("error", uploadFailed, false);
                xhr.addEventListener("abort", uploadCanceled, false);
                xhr.open("POST", "<%=SystemSettingsUtil.getInstance().loadProperty("wallet.upload.url")%>");
                xhr.send(fd);
            }
            function uploadSecondImage()
            {
                document.getElementById("UploadDone2").style.visibility = 'hidden';

                var phone = document.getElementById("MSISDN").value;
                var fd = new FormData();
                fd.append("encID", "<%=encryptedId %>" );
                fd.append("encPass", "<%=encryptedPass %>");
                fd.append("buttonIndex", "2");
                fd.append("uploadDate", date);
                fd.append("phoneNum", phone);
                fd.append("fileToUpload2", document.getElementById('fileToUpload2').files[0]);
                var xhr = new XMLHttpRequest();
                xhr.upload.addEventListener("progress", uploadProgress, false);
                xhr.addEventListener("load", uploadComplete2, false);
                xhr.addEventListener("error", uploadFailed, false);
                xhr.addEventListener("abort", uploadCanceled, false);
                xhr.open("POST", "<%=SystemSettingsUtil.getInstance().loadProperty("wallet.upload.url")%>");
                xhr.send(fd);
            }
            function uploadThirdImage()
            {
                document.getElementById("UploadDone3").style.visibility = 'hidden';

                var phone = document.getElementById("MSISDN").value;
                var fd = new FormData();
                fd.append("encID", "<%=encryptedId %>" );
                fd.append("encPass", "<%=encryptedPass %>");
                fd.append("buttonIndex", "3");
                fd.append("uploadDate", date);
                fd.append("phoneNum", phone);
                fd.append("fileToUpload3", document.getElementById('fileToUpload3').files[0]);
                var xhr = new XMLHttpRequest();
                xhr.upload.addEventListener("progress", uploadProgress, false);
                xhr.addEventListener("load", uploadComplete3, false);
                xhr.addEventListener("error", uploadFailed, false);
                xhr.addEventListener("abort", uploadCanceled, false);
                xhr.open("POST", "<%=SystemSettingsUtil.getInstance().loadProperty("wallet.upload.url")%>");
                xhr.send(fd);
            }
            function uploadFourthImage()
            {
                document.getElementById("UploadDone4").style.visibility = 'hidden';

                var phone = document.getElementById("MSISDN").value;
                var fd = new FormData();
                fd.append("encID", "<%=encryptedId %>" );
                fd.append("encPass", "<%=encryptedPass %>");
                fd.append("buttonIndex", "4");
                fd.append("uploadDate", date);
                fd.append("phoneNum", phone);
                fd.append("fileToUpload4", document.getElementById('fileToUpload4').files[0]);
                var xhr = new XMLHttpRequest();
                xhr.upload.addEventListener("progress", uploadProgress, false);
                xhr.addEventListener("load", uploadComplete4, false);
                xhr.addEventListener("error", uploadFailed, false);
                xhr.addEventListener("abort", uploadCanceled, false);
                xhr.open("POST", "<%=SystemSettingsUtil.getInstance().loadProperty("wallet.upload.url")%>");
                xhr.send(fd);
            }
            function uploadFifthImage()
            {
                document.getElementById("UploadDone5").style.visibility = 'hidden';

                var phone = document.getElementById("MSISDN").value;
                var fd = new FormData();
                fd.append("encID", "<%=encryptedId %>" );
                fd.append("encPass", "<%=encryptedPass %>");
                fd.append("buttonIndex", "5");
                fd.append("uploadDate", date);
                fd.append("phoneNum", phone);
                fd.append("fileToUpload5", document.getElementById('fileToUpload5').files[0]);
                var xhr = new XMLHttpRequest();
                xhr.upload.addEventListener("progress", uploadProgress, false);
                xhr.addEventListener("load", uploadComplete5, false);
                xhr.addEventListener("error", uploadFailed, false);
                xhr.addEventListener("abort", uploadCanceled, false);
                xhr.open("POST", "<%=SystemSettingsUtil.getInstance().loadProperty("wallet.upload.url")%>");
                xhr.send(fd);
            }

            function uploadProgress(evt) {
            }

            function uploadComplete1(evt) {
                /* This event is raised when the server send back a response */
                if (evt.target.responseText !== "")
                {
                    url1 = evt.target.responseText;
                    document.getElementById('ImageUrl_1').value = url1;
                    document.getElementById("UploadDone1").style.visibility = 'visible';
                    return evt.target.responseText;
                }
                return "";
            }
            function uploadComplete2(evt) {
                /* This event is raised when the server send back a response */
                if (evt.target.responseText !== "")
                {
                    url2 = evt.target.responseText;
                    document.getElementById('ImageUrl_2').value = url2;
                    document.getElementById("UploadDone2").style.visibility = 'visible';
                    return evt.target.responseText;
                }
                return "";
            }
            function uploadComplete3(evt) {
                /* This event is raised when the server send back a response */
                if (evt.target.responseText !== "")
                {
                    url3 = evt.target.responseText;
                    document.getElementById('ImageUrl_3').value = url3;
                    document.getElementById("UploadDone3").style.visibility = 'visible';
                    return evt.target.responseText;
                }
                return "";
            }
            function uploadComplete4(evt) {
                /* This event is raised when the server send back a response */
                if (evt.target.responseText !== "")
                {
                    url4 = evt.target.responseText;
                    document.getElementById('ImageUrl_4').value = url4;
                    document.getElementById("UploadDone4").style.visibility = 'visible';
                    return evt.target.responseText;
                }
                return "";
            }
            function uploadComplete5(evt) {
                /* This event is raised when the server send back a response */
                if (evt.target.responseText !== "")
                {
                    url5 = evt.target.responseText;
                    document.getElementById('ImageUrl_5').value = url5;
                    document.getElementById("UploadDone5").style.visibility = 'visible';
                    return evt.target.responseText;
                }
                return "";
            }

            function uploadFailed(evt) {
                alert("<%=CONFIG.getAboutUploadFail(session)%>");
            }

            function uploadCanceled(evt) {
                alert("The upload has been canceled by the user or the browser dropped the connection.");
            }
            function ThebtnSubmit()
            {
                if (url1 !== "" && url2 !== "")
                {
                    return true;
                }
                document.getElementById("ErrorUploadMessage").innerHTML = "<%=CONFIG.getUploadIDError(session)%>";
                document.getElementById("ErrorUploadMessage").style.visibility = "visible";
                return false;
            }
        </script>

    </head>



    <BODY class="body"  >
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <form name="AddCustomer" action="AddNewWalletController" method="POST">
                <div class="content_body">
                    <div id="ErrorUploadMessage" style="visibility: hidden;color: tomato"><%=CONFIG.getUploadIDError(session)%></div>

                <table style="width:70%">
                    <tr>
                        <td style="text-align: center" colspan="4">
                            <label  for="file_ToUpload"><%=CONFIG.getSelectFileToUploadAR(session)%></label><br />
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <%=CONFIG.getIdFront(session)%>
                        </td>
                        <td>
                            <input type="file" accept="image/*" name="fileToUpload1" id="fileToUpload1" onchange="fileSelected(this.id);"/><br>
                        </td>
                        <td>
                            <input type="button"  onclick="uploadFirstImage()" value="<%=CONFIG.getUpload(session)%>" /><br>
                        </td>
                        <td style="border: transparent ; border-collapse: collapse; padding: 0px">
                            <img name="UploadDone1" id="UploadDone1" class="UploadDone1" src="./img/notification_done.png" title="<%=CONFIG.getAboutUploadDone(session)%>" style="visibility: hidden">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%=CONFIG.getIdBack(session)%>
                        </td>
                        <td>
                            <input type="file" accept="image/*" name="fileToUpload2" id="fileToUpload2" onchange="fileSelected(this.id);"/><br>
                        </td>
                        <td>
                            <input type="button" onclick="uploadSecondImage()" value="<%=CONFIG.getUpload(session)%>" /><br>
                        </td>
                        <td style="border: transparent ; border-collapse: collapse; padding: 0px">
                            <img name="UploadDone2" id="UploadDone2" class="UploadDone2" src="./img/notification_done.png" title="<%=CONFIG.getAboutUploadDone(session)%>" style="visibility: hidden">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%=CONFIG.getFirstContractPhoto(session)%>
                        </td>
                        <td>
                            <input type="file" accept="image/*" name="fileToUpload3" id="fileToUpload3" onchange="fileSelected(this.id);"/><br>

                        </td>
                        <td>
                            <input type="button" onclick="uploadThirdImage()" value="<%=CONFIG.getUpload(session)%>" /><br>
                        </td>
                        <td style="border: transparent ; border-collapse: collapse; padding: 0px">
                            <img name="UploadDone3" id="UploadDone3" class="UploadDone3" src="./img/notification_done.png" title="<%=CONFIG.getAboutUploadDone(session)%>" style="visibility: hidden">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%=CONFIG.getSecondContractPhoto(session)%>
                        </td>
                        <td>
                            <input type="file" accept="image/*" name="fileToUpload4" id="fileToUpload4" onchange="fileSelected(this.id);"/><br>

                        </td>
                        <td>
                            <input type="button" onclick="uploadFourthImage()" value="<%=CONFIG.getUpload(session)%>" /><br>
                        </td>
                        <td style="border: transparent ; border-collapse: collapse; padding: 0px">
                            <img name="UploadDone4" id="UploadDone4" class="UploadDone4" src="./img/notification_done.png" title="<%=CONFIG.getAboutUploadDone(session)%>" style="visibility: hidden">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%=CONFIG.getThirdContractPhoto(session)%>
                        </td>
                        <td>
                            <input type="file" accept="image/*" name="fileToUpload5" id="fileToUpload5" onchange="fileSelected(this.id);"/><br>
                        </td>
                        <td>
                            <input type="button" onclick="uploadFifthImage()" value="<%=CONFIG.getUpload(session)%>" /><br>
                        </td>
                        <td style="border: transparent ; border-collapse: collapse; padding: 0px">
                            <img name="UploadDone5" id="UploadDone5" class="UploadDone5" src="./img/notification_done.png" title="<%=CONFIG.getAboutUploadDone(session)%>" style="visibility: hidden">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="border: none ; background-color: transparent">
                            <div>
                                • <%=CONFIG.getUploadInstruction1(session)%><br>
                                • <%=CONFIG.getUploadInstruction2(session)%><br>
                                • <%=CONFIG.getUploadInstruction4(session)%><br>
                                • <%=CONFIG.getUploadInstruction3(session)%><br>
                            </div>
                        </td>
                    </tr>
                </table>
        </form>
        <form name="AddCustomerImages" action="AddNewWalletImgesRequest" method="POST">
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
            <input type="hidden" name="WorkType" value="<%= new String(requestParameters.get("WorkType").toString().getBytes("ISO-8859-1"), "utf-8")%>" />
            <input type="hidden" name="SecurityQuestion" value="<%=requestParameters.get("SecurityQuestion")%>" />
            <input type="hidden" name="SecurityAnswer" value="<%= new String(requestParameters.get("SecurityAnswer").toString().getBytes("ISO-8859-1"), "utf-8")%>" />
            <input type="hidden" name="BirthDate" value="<%=requestParameters.get("BirthDate")%>" />
            <input type="hidden" name="FullArabicName" value="<%= new String(request.getAttribute("FullArabicName").toString().getBytes("ISO-8859-1"), "utf-8")%>" />
            <input type="hidden" name="FullEnglishName" value="<%=request.getAttribute("FullEnglishName")%>" />
            <input type="hidden" name="NATIONAL_ID" value="<%=request.getAttribute("NATIONAL_ID")%>" />
            <input type="hidden" id="<%=CONFIG.PARAM_MSISDN%>" name="<%=CONFIG.PARAM_MSISDN%>" value="<%=requestParameters.get(CONFIG.PARAM_MSISDN)%>" />
            <input type="hidden" name="LAND_LINE_PHONE" value="<%=requestParameters.get("LAND_LINE_PHONE")%>" />


            <input type="hidden" id="ImageUrl_1" name="ImageUrl_1" value="" />
            <input type="hidden" id="ImageUrl_2" name="ImageUrl_2" value="" />
            <input type="hidden" id="ImageUrl_3" name="ImageUrl_3" value="" />
            <input type="hidden" id="ImageUrl_4" name="ImageUrl_4" value="" />
            <input type="hidden" id="ImageUrl_5" name="ImageUrl_5" value="" />

            <!--            <div>
                            <input type="submit" onclick="return ThebtnSubmit()" name="btnSubmit"
                                   value="<%=CONFIG.getNext(session)%>">
                        </div>-->
            <table style="width:70%">
                <tr>
                    <td colspan="4" style="border: none ; background-color: transparent;text-align: center ">
                        <input type="submit" onclick="return ThebtnSubmit()" name="btnSubmit"
                               value="<%=CONFIG.getNext(session)%>">
                    </td>
                </tr>

            </table>
        </form>
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
