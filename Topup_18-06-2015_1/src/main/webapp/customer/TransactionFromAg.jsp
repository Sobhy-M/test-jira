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
    List transFromAg = null;
    List sumOfTrans = null;
    List<CustomerServiceDTO> agentServices = null;
%>
<%
    try {
        transFromAg = (List) session.getAttribute("transFromAg");
        sumOfTrans = (List<Double>) session.getAttribute("sumOfTrans");
        agentServices = (List<CustomerServiceDTO>) session.getAttribute("agentServices");
    } catch (Exception e) {
        MasaryManager.logger.error(e.getMessage());
    }
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
            function submitForm(transTypeId, transID, action, ledgerID) {
                document.getElementById('ledger').value = ledgerID;
                document.getElementById("action").value = action;
                document.getElementById('serv_id').value = transTypeId;
                document.getElementById('transaction_id').value = transID;
                document.form.submit();
            }
        </script>
        <script type="text/javascript">
            function submitFormBulkSMS(transID, action) {
                document.getElementById("action").value = action;
                document.getElementById('transaction_id').value = transID;
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
            function exportToExcel() {
                document.getElementById("action").value = '<%=CONFIG.ACTION_EXPORT_TO_EXCEL%>';
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
            <input type="hidden" name="LEDGER" id="ledger" />
            <div class="content_body"><br><br>
                <h3>
                    <div align="right" >
                        <table style="  border-collapse:separate; border-spacing: 10px; ">
                            <tr>
                                <td>
                                    <table border="1" style="border-color: #AACCDE; " >
                                        <%if (sumOfTrans != null) {
                                                fromM = (Double) sumOfTrans.get(0);
                                                toM = (Double) sumOfTrans.get(1);
                                            }%>
                                        <tr>
                                            <td colspan="3">
                                                <%=CONFIG.getTransactionsTo(session)%> :
                                            </td>
                                            <td><%=myFormatter.format(fromM)%></td>
                                            <td colspan="3">
                                                <%=CONFIG.getTransactionsFrom(session)%> :
                                            </td>
                                            <td><%=myFormatter.format(toM)%></td>
                                        </tr>
                                    </table>
                                </td>
                                <td>
                                    <table border="1" style="border-color: #AACCDE;" >
                                        <tr>
                                            <td>                 
                                                <%=CONFIG.getExportToEXCEL(session)%> <button  type='submit' name='btnSubmit'  style="height: 37px; " onclick="exportToExcel();" ><img src='img/excel.png' alt='Print reciept copy' height='30' width='30'/></button>  
                                                <button disabled type='submit' name='btnSubmit' style="height: 37px; " ><img src='img/pdf.png' alt='Print reciept copy' height='30' width='30'/></button>  
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <br/>
                        <%if (transFromAg.size() != 0) {%>
                        <%= transFromAg.size() > 10 ? "<div class='displayTableFrame'  name='servDiv1' id='servDiv1'>" : ""%> 
                        <table id="t1">
                            <thead>

                            <th><%=CONFIG.getLedgerID(session)%><input id="txtPrjName" name="ledgerID" onkeyup="searchRows('t1')"  size="11" type="text"/>    </th>
                            <th><%=CONFIG.getTransactionNumber(session)%><input id="txtPrjName" onkeyup="searchRows('t1')"  size="11" type="text"/> </th>   
                            <th><%=CONFIG.getDate(session)%></th>
                            <th><%=CONFIG.getFrom(session)%></th>
                            <th><%=CONFIG.getTo(session)%><input id="txtPrjName" onkeyup="searchRows('t1')" size="11" type="text"/></th>
                            <th><%=CONFIG.getType(session)%></th>
                            <th><%=CONFIG.getAmount(session)%></th>
                            <th><%=CONFIG.getComm(session)%></th>
                            <th><%=CONFIG.getFees(session)%></th>
                            <th><%=CONFIG.getTotalAmount(session)%></th>
                            <th><%=CONFIG.getBalance(session)%></th>    
                            <th></th>
                            </thead>
                            <tbody>
                                <%
                                    TransactionDTO trans = null;
                                    try {
                                        for (int i = 0; i < (int) transFromAg.size(); i++) {
                                            trans = (TransactionDTO) transFromAg.get(i);
                                %>
                                <tr>
                                    <td> <input name="LedgerID" value="<%=trans.getLedgerID()%>"/></td>
                                    <th><%=trans.getTransId()%> </th>
                                    <td><%=trans.getDate()%></td>
                                    <td><input name="Customer_name" value="<%=trans.getCustomerPayerName()%>"></td>
                                    <td><%=trans.getCustomerPayedName()%></td>
                                    <td><%=trans.getType()%></td>
                                    <td><%=myFormatter.format(trans.getOrignal_amount())%></td>
                                    <td><%=myFormatter.format(trans.getCommession())%></td>
                                    <td><%=myFormatter.format(trans.getFees())%></td>
                                    <td><%=myFormatter.format(trans.getAmount())%></td>
                                    <td><%=myFormatter.format(trans.getTotal_balance())%></td>
                                    <%
                                        totalAmount += trans.getAmount();
                                        fromM = totalAmount;
                                        if (trans.getPrintedNo() == 1) {%>
                            <input type="hidden" name="<%=CONFIG.PARAM_Transaction_ID%>" />  
                            <input type="hidden" name="<%=CONFIG.PARAM_TAX_AMOUNT%>" />  
                            <input type="hidden" name="<%=CONFIG.PARAM_SELL_TYPE%>"  />
                            <input type="hidden" name="<%=CONFIG.PARAM_MSISDN%>" value="<%= trans.getCustomerPayedName()%>"  />  
                            <input type="hidden" name="secondPrinted" value="secondPrinted" />  

                            <%
                                Boolean isValidReprintVoucher = (Boolean) session.getAttribute("isValidReprintVoucher");
                                if (isValidReprintVoucher != null && isValidReprintVoucher == true) {
                            %> 

                            <td><button  type='submit' name='btnSubmit' alt='Print reciept copy' tabindex='3' value='Print' class='Btn' onclick="submitFormVoucher(<%=trans.getTransId()%>,<%=trans.getAmount()%>, '<%=trans.getSellType()%>', '<%=CONFIG.ACTION_Print_Vouvher_2%>')"><img src='img/report.png' alt='Print reciept copy' height='30' width='30'/></button></td>
                                    <%
                                        }
                                    %>


                            <% } else if (trans.getIsBill() == 1) {%>                            
                            <input type="hidden" name="serv_id" id="serv_id"/>        
                            <input type="hidden" name="transaction_id"  id="transaction_id"/>        
                            <input type="hidden" name="secondPrinted" value="secondPrinted" />  
                            <%
                                Boolean isValidReprintVoucher = (Boolean) session.getAttribute("isValidReprintVoucher");
                                if (isValidReprintVoucher != null && isValidReprintVoucher == true) {
                            %> 
                            <td><button  type='submit' name='btnSubmit' alt='Print reciept copy' tabindex='3' value='Print' class='Btn' onclick="submitForm(<%=trans.getTrxTypeID()%>,<%=trans.getTransId()%>, '<%=CONFIG.ACTION_Print_Reciept%>', <%=trans.getLedgerID()%>)"><img src='img/report.png' alt='Print reciept copy' height='30' width='30'/></button></td>
                                    <%
                                        }
                                    %>        
                                    <% } else if (trans.getTrxTypeID() == 91) {%>                                                              
                            <input type="hidden" name="transaction_id" />
                            <%
                                Boolean isValidReprintVoucher = (Boolean) session.getAttribute("isValidReprintVoucher");
                                if (isValidReprintVoucher != null && isValidReprintVoucher == true) {
                            %> 
                            <td><button  type='submit' name='btnSubmit' alt='Print reciept copy' tabindex='3' value='Print' class='Btn' onclick="submitFormBulkSMS(<%=trans.getTransId()%>, '<%=CONFIG.ACTION_Show_Report%>')"><img src='img/report.png' alt='Print reciept copy' height='30' width='30'/></button></td>
                                    <%
                                        }
                                    %>        
                                    <%     }
                                            }
                                        } catch (Exception ex) {
                                            MasaryManager.logger.error(ex, ex);
                                        }
                                    %>
                            </tr>
                            </tbody></table>
                            <%= transFromAg.size() < 10 ? "</div> " : ""%> 
                            <%}%>
                        <br/>
                    </div><!-- End of Table Content area-->
                    </form>
