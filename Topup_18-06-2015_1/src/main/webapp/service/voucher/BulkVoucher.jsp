<%-- 
    Document   : BulkVoucher
    Created on : Feb 19, 2014, 10:24:53 AM
    Author     : omnya
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.BulkVoucherDTO"%>
<%@page import="java.sql.Array"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.EmployeeDTO"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
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
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    List<ProviderDTO> providers = null;
    String finalOwner = "1";
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    providers = MasaryManager.getInstance().getVoucherProvidersForBulk(agent.getPin());
    
    ArrayList<BulkVoucherDTO> list = new ArrayList<BulkVoucherDTO>();
    finalOwner = agent.isAutoAllocate().equals("F") ? "1" : finalOwner;
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Bulk Voucher</title>
        <script type="text/javascript">
            function getWalletEmail() {
                document.getElementById("email").value = '<%=MasaryManager.getInstance().getWalletEmail(agent.getPin())%>';
            }
        </script>            
        <script type="text/javascript">
            var voucherType = new Array();
            var voucherName = new Array();
            var denom = new Array();
            var voucherCount = new Array();
            var voucherList = new Array();
            function puchValue(service, serviceName, vDenom, vCount) {
                var voucherList = new Array();
                voucherType.push(service);
                voucherName.push(serviceName);
                denom.push(vDenom);
                voucherCount.push(vCount);
                voucherList.push(voucherType);
                voucherList.push(voucherName);
                voucherList.push(denom);
                voucherList.push(voucherCount);
                document.getElementById('voucherList').value = voucherList;
            }
            function puchLastValue() {
                var table = document.getElementById('dataTable');
                var rowCount = table.rows.length;
                var service = table.rows[rowCount - 1].cells[0].children[0].options[table.rows[rowCount - 1].cells[0].children[0].selectedIndex].id;
                var serviceName = table.rows[rowCount - 1].cells[0].children[0].value;
                var denom = table.rows[rowCount - 1].cells[1].children[0].value;
                var voucherCount = table.rows[rowCount - 1].cells[2].children[0].value;
                puchValue(service, serviceName, denom, voucherCount);
            }
        </script>
        <script>
            function setButtonVal(value) {
                document.getElementById("butValue").value = value;
                if (value === 'Excel') {
                    document.getElementById("email").value = '';
                }
            }
            function validat() {
                var serviceID = document.getElementById('serviceID').value;
                var denom = document.getElementById('custTopUpDalanceID').value;
                var count = document.getElementById('custTopUpVoucherNo').value;
                var errChk = 0;
                if (serviceID === '' || denom === '' || count === '') {
                    msg.innerHTML = "<br/>" + '<%=CONFIG.getChooseData(request.getSession())%>' + "<br/>";
                    errChk = 1;
                } else {
                    var butValue = document.getElementById("butValue").value;
                    if (butValue === 'E-mail') {
                        var email = document.getElementById("email").value;
                        var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
                        if (reg.test(email) === false) {
                            msg.innerHTML = "<br/>" + '<%=CONFIG.getInvalidEmail(request.getSession())%>' + "<br/>";
                            errChk = 1;
                        }
                    }
                }
                if (errChk === 1) {
                    return false;
                }
                return true;
            }
        </script>

        <script type="text/javascript">
            function loadMaxCount() {
                document.getElementById('custTopUpVoucherNo').value = 1;
            <% for (ProviderDTO provider1 : providers) {%>
                var serviceID = document.BayCreditCustomer.serviceID.options[document.BayCreditCustomer.serviceID.selectedIndex].id;
                if (serviceID === <%=provider1.getId()%>) {
                    if(serviceID!==50){
            <%for (Object value : ((CategoryDTO) provider1.getCategories().get(0)).getAvilableValues()) {%>
                    var denom = document.BayCreditCustomer.custTopUpDalanceID.value;
                    if (denom == <%=value%>) {
            <%int count = MasaryManager.getInstance().getvouchercount(provider1.getId(), Integer.parseInt(finalOwner), Double.parseDouble(value.toString()));%>
            <%if (count < 50) {%>
                        document.getElementById("custTopUpVoucherNo").max = <%=count%>;
            <%} else {%>
                        document.getElementById("custTopUpVoucherNo").max = "50";
            <%}%>
                    }
            <%}%>
                }
            <%}%>
            }}
        </script>

        <script type="text/javascript">
            function loadProviderDenom()
            {
                document.getElementById('custTopUpVoucherNo').value = 1;
            <% for (ProviderDTO provider : providers) {
                    Object[] values = ((CategoryDTO) provider.getCategories().get(0)).getAvilableValues();
                    if (values != null) {%>
            <%=provider.getServiceNameEn().replace(" ", "_").replace("-", "_")%> = new Array;
            <%=provider.getServiceNameEn().replace(" ", "_").replace("-", "_") + "_C"%> = new Array;
            <% for (int i = 0; i < values.length; i++) {%>
            <%=provider.getServiceNameEn().replace(" ", "_").replace("-", "_")%> [<%= i%>] = "<%=(String) values[i]%>";
            <%int count = MasaryManager.getInstance().getvouchercount(provider.getId(), Integer.parseInt(finalOwner), Double.parseDouble(values[i].toString()));%>
            <%=provider.getServiceNameEn().replace(" ", "_").replace("-", "_") + "_C"%> [<%= i%>] = '<%=(count == 0) ? true : false%>';
            <%}%>
            <%}%>
            <%}%>
                var selectedBoxValue = document.BayCreditCustomer.serviceID.value;
                var disable;
                var i;
                var j;
                var provLength = eval(selectedBoxValue).length;
                removeSelectboxOption();
                for (i = 0; i < document.BayCreditCustomer.serviceID.options.length; i++) {
                    if (selectedBoxValue === document.BayCreditCustomer.serviceID.options[i].value) {
                        var disable = document.BayCreditCustomer.serviceID.options[i].title;
                        document.BayCreditCustomer.custTopUpDalanceID.options[0] = new Option('', '');
                        for (j = 0; j < provLength; j++) {
                            document.BayCreditCustomer.custTopUpDalanceID.options[j + 1] = new Option(eval(selectedBoxValue)[j], eval(selectedBoxValue)[j]);
                            if (eval(disable)[j] === 'true') {
                                document.BayCreditCustomer.custTopUpDalanceID.options[j + 1].setAttribute("disabled", true);
                            }
                        }
                    }
                }
            }
            function removeSelectboxOption()
            {
                var i;
                for (i = 0; i < document.BayCreditCustomer.custTopUpDalanceID.options.length; i++)
                {
                    document.BayCreditCustomer.custTopUpDalanceID.remove(i);
                }
            }
        </script>  

        <script type="text/javascript">
            function addRow(tableID) {
                var table = document.getElementById(tableID);
                var rowCount = table.rows.length;
                if (rowCount < 20) {
                    var service = table.rows[rowCount - 1].cells[0].children[0].options[table.rows[rowCount - 1].cells[0].children[0].selectedIndex].id;
                    var serviceName = table.rows[rowCount - 1].cells[0].children[0].value;
                    var denom = table.rows[rowCount - 1].cells[1].children[0].value;
                    var voucherCount = table.rows[rowCount - 1].cells[2].children[0].value;
                    puchValue(service, serviceName, denom, voucherCount);
                    if (service != "" && denom != "") {
                        var row = table.insertRow(rowCount);
                        var colCount = table.rows[0].cells.length;
                        for (var i = 0; i < colCount; i++) {
                            var newcell = row.insertCell(i);
                            newcell.innerHTML = table.rows[rowCount - 1].cells[i].innerHTML;
                            switch (newcell.childNodes[0].type) {
                                case "text":
                                    newcell.childNodes[0].value = "";
                                    break;
                                case "checkbox":
                                    newcell.childNodes[0].checked = false;
                                    break;
                                case "select-one":
                                    newcell.childNodes[0].selectedIndex = 0;
                                    break;
                            }
                        }
                    }
                    table.rows[rowCount - 1].cells[0].children[0].id = '';
                    table.rows[rowCount - 1].cells[1].children[0].id = '';
                    table.rows[rowCount - 1].cells[2].children[0].id = '';
                }
            }
            function deleteRow(tableID) {
                var row = tableID.parentNode.parentNode;
                row.parentNode.removeChild(row);
                puchValue(service, serviceName, denom, voucherCount);
            }
        </script>  

    </head>
    <BODY class="body" >
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         
        <%}%>
    </div>
    <form name="BayCreditCustomer" action="<%=CONFIG.APP_ROOT%>Web" method="post"  onSubmit="puchLastValue();
                return validat();">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_CUSTOMER_BULK_VOUCHER%>" />
        <input type="hidden" name="voucherList" id="voucherList"  />
        <input type="hidden" name="butValue" id="butValue"  />
        <h2><div id="msg" style="color: #C00000 ;"></div> <h2>
                <fieldset <%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "style='width: 70%; direction: ltr;' align='left'" : "style='width: 70%; direction: rtl;' align='right'"%> >  
                    <legend align="right" ><font size="5"></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20" > </legend> 
                    <table border="1" width="100%">
                        <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                        <tr>
                            <td><table  width="100%" >
                                    <th width="22%" style="text-align:center"><%=CONFIG.getVoucherType(request.getSession())%></th>
                                    <th width="16%" style="text-align:center"><%=CONFIG.getAmount_V(request.getSession())%></th>
                                    <th width="18%" style="text-align:center"><%=CONFIG.getVoucherNO(request.getSession())%></th>
                                    <th width="15%" style="text-align:center"></th>
                                    <table id="dataTable" width="100%" >
                                        <tr>
                                            <td width="10%">
                                                <select style="margin-right: 25%"  name="<%=CONFIG.TOPUP_TYPE%>" tabindex="1"  id="serviceID" onchange="loadProviderDenom();">
                                                    <option></option>
                                                    <%              for (ProviderDTO provider : providers) {
                                                    %>
                                                    <option value="<%=provider.getServiceNameEn().replace(" ", "_").replace("-", "_")%>"
                                                            id="<%=provider.getId()%>"  
                                                            title="<%=provider.getServiceNameEn().replace(" ", "_").replace("-", "_") + "_C"%>">
                                                        <%=request.getSession().getAttribute(CONFIG.lang).equals("") ? provider.getServiceNameEn() : provider.getServiceNameAr()%>
                                                    </option>
                                                    <%
                                                        }
                                                    %>
                                                </select> 
                                            </td>
                                            <td width="10%"> 
                                                <select style="margin-right: 27%"  name="<%=CONFIG.AMOUNT%>" tabindex="1" id="custTopUpDalanceID" onchange="loadMaxCount();" >                                       
                                                </select> 
                                                <div id="denomDiv"></div>
                                            </td>
                                            <td width="10%">
                                                <input style="margin-right: 37%"  id="custTopUpVoucherNo" value="1" name="<%=CONFIG.PARAM_VOUCHERNO%>"  tabindex="2" type="number" min="1"  max="50"  >
                                            </td>
                                            <td width="10%">
                                                <INPUT style="margin-right: 40%"  align="center" type="button" value="+" onclick="addRow('dataTable')" />
                                            </td>
                                        </tr>                            
                                    </table>
                                    <tfoot>
                                        <tr> 
                                            <td><input  style="width: 31%;text-align:center;margin-right: 2%"  type="submit" name="btnSubmit" tabindex="4" onclick="setButtonVal('E-mail');"  value="<%=CONFIG.getSendByEmail(request.getSession())%>" class="Btn">
                                                <input <%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "style='width: 31%;text-align:left'" : "style='width: 31%;text-align:right'"%> type="text" name="email" id="email" tabindex="4"  value="<%=CONFIG.getEnterEmail(request.getSession())%>" >
                                                <input style="width: 31%;text-align:center;margin-left: 3%" type="button" name="btnSubmit"  tabindex="4"  value="<%=CONFIG.getWalletEmail(request.getSession())%>" class="Btn" onclick="getWalletEmail()" >
                                            </td>
                                        </tr> 
                                        <tr> 
                                            <td> 
                                                <input style="width: 30%;text-align:center; margin-right: 2%" type="submit"  name="btnSubmit" onclick="setButtonVal('Excel');"  tabindex="4"  value="<%=CONFIG.getExportToFile(request.getSession())%>" class="Btn" >
                                                <output style="margin-right: 30%" > <%=CONFIG.getAllowedBalance(request.getSession())%> : <%=myFormatter.format(customerBalance)%> </output>
                                            </td>
                                        </tr>    

                                    </tfoot>
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