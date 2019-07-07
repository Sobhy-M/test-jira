<%@ page language="java" contentType="text/html; charset=windows-1256"
         pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="img/c_config_mobile.js"></script>
<script type="text/javascript" src="img/c_smartmenus.js"></script>
<link href="style.css" rel="stylesheet" type="text/css">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
            String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
            List transactions = MasaryManager.getInstance().getTodayTransactionsByPayer((String) request.getSession().getAttribute(CONFIG.PARAM_PIN));
            session=request.getSession();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getLastTransactions(session)%> </title>
    </head>

    <body>

        <ul  id="Menu1" class="M">

            <%
            for (TransactionDTO transaction : (List<TransactionDTO>) transactions)
            {
            %>

            <li> <a style="text-align:center" href="<%=CONFIG.APP_ROOT%>walletServices?action=ACTION_GET_LAST_TRANSACTIONS&TransID=<%=transaction.getTransId()%>" ><%=transaction.getTransId()%>>><%=transaction.getType()%></a>            </li>
            <%}
            %>


        </ul>
        <form action="<%=CONFIG.APP_ROOT%>walletServices" method=post  name="admin_form" id="saveform">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_MAIN%>" />
            <input type="submit" name="btnSubmit" tabindex="5"
                   value="<%=CONFIG.getBack(session)%>" class="Btn">
        </form>
</body>
</html>
