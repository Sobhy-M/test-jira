
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table cellpadding="1"  cellspacing="1" border="1" bordercolor="gray">
            <thead>
            <th scope="col"> <%=CONFIG.getTransactionNumber(session)%></th>  
            <th> <%=CONFIG.getDate(session)%></th>
            <th><%=CONFIG.getFrom(session)%></th>
            <th><%=CONFIG.getTo(session)%></th>
            <th><%=CONFIG.getType(session)%></th>
            <th><%=CONFIG.getAmount(session)%></th>
            <th><%=CONFIG.getComm(session)%></th>
            <th><%=CONFIG.getFees(session)%></th>
            <th><%=CONFIG.getTotalAmount(session)%></th>
            <th><%=CONFIG.getBalance(session)%></th>    
        </thead>
        <tbody>
            <%
                String fileName = new SimpleDateFormat("yyyyMMddHH24mm").format(new java.util.Date().getTime());
                DecimalFormat myFormatter = CONFIG.getFormater(session);
                MasaryManager.logger.info("Before transFromAg  ");
                List transFromAg = (List) session.getAttribute("transFromAg");
                MasaryManager.logger.info("Before transFromAg  " + transFromAg);
                TransactionDTO trans = null;
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".xls");
                try {
                    for (int i = 0; i < (int) transFromAg.size(); i++) {
                        MasaryManager.logger.info("BEFORE trans");
                        trans = (TransactionDTO) transFromAg.get(i);
                        MasaryManager.logger.info("After trans  " + trans);

            %>
            <tr>
                <th scope="row"><%=trans.getTransId()%> </th>
                <td><%=trans.getDate()%></td>
                <td><%=trans.getCustomerPayerName()%></td>
                <td><%=trans.getCustomerPayedName()%></td>
                <td><%=trans.getType()%></td>
                <td><%=trans.getOrignal_amount()%></td>
                <td><%=trans.getCommession()%></td>
                <td><%=trans.getFees()%></td>
                <td><%=trans.getAmount()%></td>
                <td><%=trans.getTotal_balance()%></td>      
                <%
                        }
                    } catch (Exception ex) {
                        MasaryManager.logger.error(ex.getMessage());
                    }
                %>
            </tr>
        </tbody></table>

</body>

</html>