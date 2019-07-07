<%--
    Document   : ViewBankInformation.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.*"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    List transFromAg_1;
    List sumOfTrans;
    List<CustomerServiceDTO> agentServices;
%>
<%
    transFromAg_1 = (List) session.getAttribute("transFromAg_1");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    double totalAmount = 0;
    double fromM = 0;
    double toM = 0;
%>
<html>
    <head>
        <style  type="text/css">
            #servDiv1 {
                overflow: auto;
                azimuth: leftwards;
                height: 50%;
                width: 1050px;
                overflow-y: scroll;
                border:1px ; 
            }
        </style>
        <script type="text/javascript">
            function TransListFn(Date) {

                var xmlhttp;
                if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari/
                    xmlhttp = new XMLHttpRequest();
                } else {// code for IE6, IE5
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange = function()
  {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        document.getElementById("myDiv").innerHTML = xmlhttp.responseText;
                    }
                }
                xmlhttp.open("GET", "Login.jsp", true);
                xmlhttp.send();
            
    }
        </script>


        <script type="text/javascript">
            function submitForm(transTypeId, transID, action) {
                document.getElementById("action").value = action;
                document.getElementsByName('serv_id')[0].value = transTypeId;
                document.getElementsByName('transaction_id')[0].value = transID;
                document.form.submit();
            }
        </script>

        <script type="text/javascript">
            function TransListFn(Date) {
                document.getElementById("action").value = action;
                document.getElementsByName('serv_id')[0].value = transTypeId;
                document.getElementsByName('transaction_id')[0].value = transID;
                document.form.submit();
            }
        </script>
        <script type="text/javascript">
            function TransListFn(Date) {
                document.getElementById("action").value = action;
                document.getElementsByName('serv_id')[0].value = transTypeId;
                document.getElementsByName('transaction_id')[0].value = transID;
                document.form.submit();
            }
        </script>
        <script type="text/javascript">
            function TransListFn(Date) {
                document.getElementById("action").value = action;
                document.getElementsByName('serv_id')[0].value = transTypeId;
                document.getElementsByName('transaction_id')[0].value = transID;
                document.form.submit();
            }
        </script>
        <script type="text/javascript">
            function TransListFn(Date) {
                document.getElementById("action").value = action;
                document.getElementsByName('serv_id')[0].value = transTypeId;
                document.getElementsByName('transaction_id')[0].value = transID;
                document.form.submit();
            }
        </script>
        <script type="text/javascript">
            function TransListFn(Date) {
                document.getElementById("action").value = action;
                document.getElementsByName('serv_id')[0].value = transTypeId;
                document.getElementsByName('transaction_id')[0].value = transID;
                document.form.submit();
            }
        </script>
        <script type="text/javascript">
            function TransListFn(Date) {
                document.getElementById("action").value = action;
                document.getElementsByName('serv_id')[0].value = transTypeId;
                document.getElementsByName('transaction_id')[0].value = transID;
                document.form.submit();
            }
        </script>
        <script type="text/javascript">
            function TransListFn(Date) {
                document.getElementById("action").value = action;
                document.getElementsByName('serv_id')[0].value = transTypeId;
                document.getElementsByName('transaction_id')[0].value = transID;
                document.form.submit();
            }
        </script>
        <script type="text/javascript">
            function submitFormBulkSMS(transID, action) {
                document.getElementById("action").value = action;
                document.getElementsByName('transaction_id')[0].value = transID;
                document.form.submit();
            }
        </script>
        <script type="text/javascript">
            function submitFormVoucher(trans_ID, amount, sellType, action) {
                document.getElementById("action").value = action;
                document.getElementsByName('TransID')[0].value = trans_ID;
                document.getElementsByName('TAX_AMOUNT')[0].value = amount;
                document.getElementsByName('Sell_Type')[0].value = sellType;
                document.form.submit();
            }
        </script> 
        <script type="text/javascript">
            function exportToExcel_1() {
                document.getElementById("action").value = '<%=CONFIG.ACTION_EXPORT_TO_EXCEL_1%>';
                document.form.submit();
            }
        </script> 
        <script type="text/javascript">
            function searchRows(tblId) {
                var tbl = document.getElementById(tblId);
                var headRow = tbl.rows[0];
                var arrayOfHTxt = new Array();
                var arrayOfHtxtCellIndex = new Array();

                for (var v = 0; v < headRow.cells.length; v++) {
                    if (headRow.cells[v].getElementsByTagName('input')[0]) {
                        var Htxtbox = headRow.cells[v].getElementsByTagName('input')[0];
                        if (Htxtbox.value.replace(/^\s+|\s+$/g, '') != '') {
                            arrayOfHTxt.push(Htxtbox.value.replace(/^\s+|\s+$/g, ''));
                            arrayOfHtxtCellIndex.push(v);
                        }
                    }
                }
                var j = tbl.rows.length;
                for (var i = 1; i < tbl.rows.length; i++) {
                    tbl.rows[i].style.display = 'table-row';
                    for (var v = 0; v < arrayOfHTxt.length; v++) {
                        var CurCell = tbl.rows[i].cells[arrayOfHtxtCellIndex[v]];
                        var CurCont = CurCell.innerHTML.replace(/<[^>]+>/g, "");
                        var reg = new RegExp(arrayOfHTxt[v] + ".*", "i");
                        if (CurCont.match(reg) == null) {
                            tbl.rows[i].style.display = 'none';
                            j = j - 1;
                        }
                    }
                }
                document.getElementById('servDiv1').style.height = j * 65;
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getTransactions(session)%></title>
    </head>
    <BODY class="body">
        <form  action="<%=CONFIG.APP_ROOT%>walletServices" method="post">
            <input type="hidden" name="action" id="action"/>  
            <div class="content_body"><br><br>
                <h3>
                    <div align="right" >
                        <table style="  border-collapse:separate; border-spacing: 10px; ">
                            <tr>


                                <td>
                                    <table border="1" style="border-color: #AACCDE;" >
                                    <tr>
                                            <td>                 
                                                <%=CONFIG.getExportToEXCEL_1(session)%> <button  type='submit' name='btnSubmit'  style="height: 37px; " onclick="exportToExcel_1();" ><img src='img/excel.png' alt='Print reciept copy' height='30' width='30'/></button>  
                                              
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <br/>
                        <%if (transFromAg_1.size() != 0) {%>
                        <%= transFromAg_1.size() > 10 ? "<div class='displayTableFrame'  name='servDiv1' id='servDiv1'>" : ""%> 
                        <table id="t1" align="center" >
                            <thead>
                            <th></th>
                            <th><%=CONFIG.getTodayDate(session)%></th>
                            <th><%=CONFIG.getGrossProfit(session)%></th>
                            <th><%=CONFIG.getTransNum(session)%></th>
                            </thead>
                            <tbody>
                                <%
                                    EarnListDTO Elist = null;
                                    try {
                                        for (int i = 0; i < (int) transFromAg_1.size(); i++) {
                                            Elist = (EarnListDTO) transFromAg_1.get(i);
                                %>
                                <tr>
                                    <th></th>
                                    <td><a href="<%=CONFIG.APP_ROOT%>walletServices?action=ACTION_AGENT_OPERATIONS&PARAM_AGENT_BTN=View+Transactions&isflag=1&date=<%=Elist.getDate()%>" ><%=Elist.getDate()%></a> </td>
                                    <td><%=Elist.getAllEarn()%></td>
                                    <td><%=Elist.getTransNum()%></td>
                                    <%
                                            }
                                        } catch (Exception ex) {
                                            MasaryManager.logger.error(ex);
                                        }
                                    %>
                                </tr>
                            </tbody></table>
                            <%= transFromAg_1.size() < 10 ? "</div> " : ""%> 
                            <%}%>
                        <br/>
                    </div><!-- End of Table Content area-->
         </form>
        </html>