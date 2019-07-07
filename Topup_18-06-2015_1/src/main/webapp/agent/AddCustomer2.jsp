<%--
    Document   : AddCustomer.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.masary.database.dto.Governorate"%>
<%@page import="com.masary.database.dto.Country"%>
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
    ArrayList<CustomerServiceDTO> agentServices = (ArrayList<CustomerServiceDTO>) session.getAttribute("services");
    List<Country> countriesList = MasaryManager.getInstance().getCountries((String) request.getSession().getAttribute(CONFIG.lang));
    Object[] governoratesName;
    Object[] governoratesISO;
    session = request.getSession();
    HashMap<String, String> govs = MasaryManager.getInstance().getWalletsGovernorates();
    Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAddCustomer(session)%></title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="./js/AddNewWalletJS/AddNewWalletJS.js"></script>
        <script src="./js/AddNewWalletJS/notify.js"></script>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
    </head>
    <script>
        $(window).load(function () {
            console.log(<%=requestParameters.get("SERVICE_ID")%>);
            $("#HomeGovAddress").on('change', function postinput() {
                $.ajax({
                    url: 'AddWalletAjaxRequest',
                    data: {
                        GovernorateName: $('#HomeGovAddress').val()
                    },
                    type: 'get'
                }).done(function (responseData) {
                    var values = [];
                    values = responseData;
                    if (responseData === '')
                    {
                        return;
                    }
                    else
                    {
                    }
                    var arr = responseData.split('-');
                    document.getElementById("HomeCityAddress").options.length = 0;
                    for (var i = 0; i < arr.length - 1; i++)
                    {
                        var citesArr = arr[i];
                        var arr2 = citesArr.split('%');
                        var x = document.getElementById("HomeCityAddress");
                        var option = document.createElement("option");
                        option.text = arr2[1];
                        option.value = arr2[0];
                        x.add(option);
//                        var x = document.getElementById("HomeCityAddress");
//                        var option = document.createElement("option");
//                        option.text = arr[i];
//                        x.add(option);
                    }
                }).fail(function () {
                    console.log('Failed');
                });
            });
        });


        $(window).load(function () {
            console.log(<%=requestParameters.get("SERVICE_ID")%>);
            $("#WorkGovAddress").on('change', function postinput() {
                $.ajax({
                    url: 'AddWalletAjaxRequest',
                    data: {
                        GovernorateName: $('#WorkGovAddress').val()
                    },
                    type: 'get'
                }).done(function (responseData) {
                    var values = [];
                    values = responseData;
                    if (responseData === '')
                    {
                        return;
                    }
                    else
                    {
                    }
                    var arr = responseData.split('-');
                    document.getElementById("WorkCityAddress").options.length = 0;
                    for (var i = 0; i < arr.length - 1; i++)
                    {
                        var citesArr = arr[i];
                        var arr2 = citesArr.split('%');
                        var x = document.getElementById("WorkCityAddress");
                        var option = document.createElement("option");
                        option.text = arr2[1];
                        option.value = arr2[0];
                        x.add(option);
                    }
                }).fail(function () {
                    console.log('Failed');
                });
            });
        });


    </script>
   
    
    <BODY class="body" >
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <form name="AddCustomer" action="AddNewWalletAgentInformation" method="POST" onsubmit="return ValidateAddCustForm();" >
                <input type="hidden" name="action" value="<%=CONFIG.ACTION_ADD_CUSTOMER%>" />
            <input type="hidden" name="<%=CONFIG.PARAM_ADD_EMPLOYEE%>" value="<%=requestParameters.get(CONFIG.PARAM_ADD_EMPLOYEE)%>" />
            <input type="hidden" name="<%=CONFIG.PARAM_MSISDN%>" id="<%=CONFIG.PARAM_MSISDN%>" value="<%=requestParameters.get(CONFIG.PARAM_MSISDN)%>"/>
            <div class="content_body">
                <table>
                    <tr>
                        <td>
                            <div class="requriedclass">
                                <div>
                                    <%=CONFIG.getAgentGender(session)%>
                                </div>
                            </div>
                        </td>
                        <td>
                            <table style="width: 100%">
                                <tr>
                                    <td  style="border: none;background-color: transparent"><input checked type="radio" id="genderMale" name="gender" value="male"><%=CONFIG.getMale(session)%></td>
                                    <td style="border: none;background-color: transparent"><input type="radio" id="genderFemale" name="gender" value="female"><%=CONFIG.getFemale(session)%></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="requriedclass">
                                <div>
                                    <%=CONFIG.getAccountType(session)%>
                                </div>
                            </div>
                        </td>
                        <td>
                            <table style="width: 100%">
                                <tr>
                                    <td  style="border: none;background-color: transparent"><input  onclick="onchooseAccount()"  checked id="AccountTypeMerchant" type="radio" name="AccountType" value="merchant"><%=CONFIG.getMerchantAccount(session)%></td>
                                    <td style="border: none;background-color: transparent"><input onclick="onchooseAccount()" type="radio" id="AccountTypePrivate" name="AccountType"  value="person"  disabled><%=CONFIG.getPrivateAccount(session)%></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr><td style="text-align: center" colspan="2"><%=CONFIG.getAgentInfo(session)%></td></tr>
                    <tr>
                        <td><%=CONFIG.getAgentNameInArabic(session)%></td>
                        <td>
                            <table>
                                <tr>
                                    <td style="border: none;background-color: transparent">
                                        <div class="requriedclass">
                                            <div >
                                                <input type="text" autocomplete="off"  maxlength="15" title="من فضلك أدخل الإسم باللغة العربية" id="ArabicName1" name="ArabicName1"  style="width: 80px"/>
                                            </div>
                                        </div>
                                    </td>
                                    <td style="border: none;background-color: transparent">
                                        <div class="requriedclass">
                                            <div>
                                                <input type="text" autocomplete="off"  maxlength="15" title="من فضلك أدخل الإسم باللغة العربية" id="ArabicName2" name="ArabicName2"  style="width: 80px"/>
                                            </div>
                                        </div>
                                    </td>
                                    <td style="border: none;background-color: transparent">
                                        <div class="requriedclass">
                                            <div>
                                                <input type="text" autocomplete="off"  maxlength="15" title="من فضلك أدخل الإسم باللغة العربية" id="ArabicName3" name="ArabicName3" style="width: 80px"/>
                                            </div>
                                        </div>
                                    </td>
                                    <td style="border: none;background-color: transparent">
                                        <div class="requriedclass">
                                            <div>
                                                <input type="text" autocomplete="off"  maxlength="15" title="من فضلك أدخل الإسم باللغة العربية" id="ArabicName4" name="ArabicName4"  style="width: 80px"/>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td><%=CONFIG.getAgentNameInEnglish(session)%></td>
                        <td>
                            <table>
                                <tr>
                                    <td style="border: none;background-color: transparent">
                                        <div class="requriedclass">
                                            <div>
                                                <input type="text" autocomplete="off" maxlength="15" title="من فضلك أدخل الإسم باللغة الإنجليزية" id="EnglishName1" name="EnglishName1"  style="width: 80px"/>  
                                            </div>
                                        </div>
                                    </td>
                                    <td style="border: none;background-color: transparent">
                                        <div class="requriedclass">
                                            <div>
                                                <input type="text" autocomplete="off" maxlength="15" title="من فضلك أدخل الإسم باللغة الإنجليزية" id="EnglishName2" name="EnglishName2"  style="width: 80px"/>  
                                            </div>
                                        </div>
                                    </td>
                                    <td style="border: none;background-color: transparent">
                                        <div class="requriedclass">
                                            <div>
                                                <input type="text" autocomplete="off" maxlength="15" title="من فضلك أدخل الإسم باللغة الإنجليزية" id="EnglishName3" name="EnglishName3"  style="width: 80px"/>  
                                            </div>
                                        </div>
                                    </td>
                                    <td style="border: none;background-color: transparent">
                                        <div class="requriedclass">
                                            <div>
                                                <input type="text" autocomplete="off" maxlength="15" title="من فضلك أدخل الإسم باللغة الإنجليزية" id="EnglishName4" name="EnglishName4"  style="width: 80px"/>  
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td><%=CONFIG.getBirthDate(session)%></td>
                        <td>
                            <div class="requriedclass">
                                <div>
                                    <input type="text" autocomplete="off" maxlength="14" readonly title="من فضلك أدخل تاريخ ميلادك" id="BirthDate" name="BirthDate" onchange="onchangeDate()"    />  
                                </div>
                            </div>
                        </td>
                    </tr>                    
                    <tr>
                        <td><%=CONFIG.getNationalID(session)%></td>
                        <td>
                            <table>
                                <tr>
                                    <td style="border: none;background-color: transparent">
                                        <div class="requriedclass">
                                            <div >
                                                <input type="text" autocomplete="off" title="من فضلك أدخل رقم قومي صحيح"  maxlength="7"  id="NATIONAL_ID_1" name="NATIONAL_ID_1"  style="width: 80px"/>
                                            </div>
                                        </div>
                                    </td>
                                    <td style="border: none;background-color: transparent">
                                        <div class="requriedclass">
                                            <div>
                                                <input type="text" autocomplete="off" readonly style="background-color: #EDEDED;" maxlength="7" id="NATIONAL_ID_2" name="NATIONAL_ID_2"  style="width: 80px"/>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <div>
                                    <%=CONFIG.getAboutNationalId(session)%>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td><%=CONFIG.getEmail(session)%></td>
                        <td><input type="text" autocomplete="off" maxlength="50" title="من فضلك أدخل إيميل صحيح"  id="EMAIL_ADDRESS" name="EMAIL_ADDRESS"/></td>
                    </tr>
                    <tr>
                        <td style="text-align: center"  colspan="2"><%=CONFIG.getAddress(session)%></td>
                    </tr>
                    <tr>
                        <td colspan="2" >
                            <table style="width: 100%">
                                <tr>
                                    <td style="border: none;background-color: transparent" colspan="2">
                                        <table style="width: 100%">
                                            <tr>
                                                <td><%=CONFIG.getAgentFullAddress(session)%></td>
                                                <td>
                                                    <div class="requriedclass">
                                                        <div>
                                                            <input type="text" autocomplete="off" style="width: 300px" maxlength="100"   id="FullAddress" name="FullAddress"/>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><%=CONFIG.getGov(session)%></td>
                                                <td>
                                                    <div class="requriedclass">
                                                        <div>
                                                            <select name="HomeGovAddress" id="HomeGovAddress"  onchange="">
                                                                <option selected value="" ><%= CONFIG.getGovernrate(session)%></option>
                                                                <%for (String govKey : govs.keySet()) {%>
                                                                <option value="<%=govKey%>"><%=govs.get(govKey)%></option><%}%>
                                                            </select> 
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><%=CONFIG.getAgentCity(session)%></td>
                                                <td>
                                                    <div class="requriedclass">
                                                        <div>
                                                            <select name="HomeCityAddress"  id="HomeCityAddress"  onchange="">
                                                                <option selected value="" ><%= CONFIG.getselectCity(session)%></option>
                                                            </select> 
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><%=CONFIG.getRegion(session)%></td>
                                                <td>
                                                    <div class="requriedclass">
                                                        <div>
                                                            <input type="text" autocomplete="off" id="HomeRegionAddress" name="HomeRegionAddress" />
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" id="workID" style="border: none;background-color: transparent">
                                        <table style="width: 100%">
                                            <tr>
                                                <td><%=CONFIG.getAgentFullWorkAddress(session)%></td>
                                                <td>
                                                    <div class="requriedclass">
                                                        <div>
                                                            <input type="text" autocomplete="off" style="width: 300px"  id="WorkFullAddress" name="WorkFullAddress"/>     
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><%=CONFIG.getGov(session)%></td>
                                                <td>
                                                    <div class="requriedclass">
                                                        <div>
                                                            <select name="WorkGovAddress" id="WorkGovAddress"  onchange="">
                                                                <option selected value="" ><%= CONFIG.getGovernrate(session)%></option>
                                                                <%for (String govKey : govs.keySet()) {%>
                                                                <option value="<%=govKey%>"><%=govs.get(govKey)%></option><%}%>
                                                            </select> 
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><%=CONFIG.getAgentCity(session)%></td>
                                                <td>
                                                    <div class="requriedclass">
                                                        <div>
                                                            <select name="WorkCityAddress" id="WorkCityAddress"  onchange="">
                                                                <option selected value="" ><%= CONFIG.getselectCity(session)%></option>
                                                            </select> 
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><%=CONFIG.getRegion(session)%></td>
                                                <td>
                                                    <div class="requriedclass">
                                                        <div>
                                                            <input type="text" autocomplete="off" id="WorkRegionAddress" name="WorkRegionAddress"/>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center" colspan="2"><%=CONFIG.getOtherInfo(session)%></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table style="width: 100%">
                                <tr>
                                    <td style="background-color: transparent"><%=CONFIG.getMobileNumber_2(session)%></td>
                                    <td style="background-color: transparent">
                                        <input type="text" autocomplete="off" maxlength="11"  id="MSISDN_ALTERNATIVE" name="MSISDN_ALTERNATIVE"    />
                                    </td>
                                </tr>
                                <tr>
                                    <td style="background-color: transparent"><%=CONFIG.getAgnetLandLineNumber(session)%></td>
                                    <td style="background-color: transparent">
                                        <input type="text" maxlength="10" autocomplete="off"  id="LAND_LINE_PHONE" name="LAND_LINE_PHONE"/>
                                    </td>
                                </tr>
                                <tr id="MerchentTD">
                                    <td  style="background-color: transparent"><%=CONFIG.getShopName(session)%></td>
                                    <td style="background-color: transparent">
                                        <input type="text" maxlength="20" autocomplete="off" id="ShopName" name="ShopName"  />
                                    </td>
                                </tr>
                                <tr>
                                    <td   style="background-color: transparent"><%=CONFIG.getWorkType(session)%></td>

                                    <td  style="background-color: transparent">
                                        <input type="text" autocomplete="off" maxlength="20" id="WorkType" name="WorkType"  />
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td><%=CONFIG.getSecurityQuestion(session)%></td>
                        <td>
                            <div class="requriedclass">
                                <div>
                                    <select name="SecurityQuestion" id="SecurityQuestion" class="text" tabindex="5">
                                        <option value="" selected>- <%=CONFIG.getSelectOne(session)%> -</option>
                                        <option value="Where did you meet your spouse?">Where did you meet your spouse?</option>
                                        <option value="What was the name of your first school?">What was the name of your first school?</option>
                                        <option value="Who was your childhood hero?">Who was your childhood hero?</option>
                                        <option value="What is your favorite pastime?">What is your favorite pastime?</option>
                                        <option value="What is your favorite sports team?">What is your favorite sports team?</option>
                                        <option value="What is your father middle name?">What is your father's middle name?</option>
                                        <option value="What was your high school mascot?">What was your high school mascot?</option>
                                        <option value="What make was your first car or bike?">What make was your first car or bike?</option>
                                        <option value="What is your pets name?">What is your pet's name?</option>
                                    </select>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><%=CONFIG.getAnswer(session)%></td>
                        <td>
                            <div class="requriedclass">
                                <div>
                                    <input type="text" autocomplete="off" id="SecurityAnswer"  name="SecurityAnswer"/>
                                    </select>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center"><input type="submit" onclick="return AddWalletBtnSubmit()" name="btnSubmit" tabindex=""
                                                                          value="<%=CONFIG.getNext(session)%>"></td>
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
