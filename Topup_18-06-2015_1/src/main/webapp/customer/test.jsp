<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
            String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
            if (role == null) {
                response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
                return;
            }

%>
<%
            session = request.getSession();
            String transId = (String) session.getAttribute(CONFIG.PARAM_Transaction_ID);
            TransactionDTO trans;
            try {
                trans = MasaryManager.getInstance().getTransaction(transId);          
                
            } catch (Exception ex) {
                session.setAttribute("ErrorMessage",
                        "Detailed error code is:" + ex.getMessage());
                response.sendRedirect(CONFIG.APP_ROOT + "error.jsp");
                ex.printStackTrace();
                return;
            }
            DecimalFormat myFormatter = CONFIG.getFormater(session);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=CONFIG.getTransactionReport(session)%></title>
        <script type="text/javascript">
            function zPrint(oTgt)
                {
                    oTgt.focus();
                    oTgt.print();
                }
        </script>
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
    </div>
    <div class="content_body"><br><br>
        
        
        <table>
            <tr>
                <!-- رقم العمليه -->
                <th scope="col"><%=CONFIG.getTransactionNumber(session)%></th>
                <td ><%=trans.getTransId()%> </td>
                <th rowspan="9">
                    <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method=post  name="admin_form" id="saveform">
                        <input type="submit" name="btnSubmit" tabindex="5"
                               value="OK" class="Btn">
                    </form>
                </th>
                <th rowspan="9">
                    <%

            String s2 = request.getParameter("v_and_s").toLowerCase().trim();
            String s3="";
            String s4="";
            if(s2.equals("")){
                s3=" ";
                s4=" ";
                }else{
                s3 = s2.substring(s2.indexOf("r:")+2, s2.indexOf(" ")).trim();
             s4 = s2.substring(s2.indexOf("l:")+2, s2.length()).trim();
                }
            %>

            <%
            if(s2.equals("")){
                }else{
            %>
                    <div>
            <%if (session.getAttribute("howSell").equals("print")) {
                String vV = "";
                String cmd = "";
                                            
            if (trans.getType().toLowerCase().contains("voda")) {  %>
            <applet  code="printAppletVod" archive="PrintApp.jar" width="55" height="30"> 
            <param name="v" value="<%=vV %>">
            <param name="cmd" value="<%=cmd %>" >
            <param name="voch" value=<%=s3%>>
            <param name="serial" value=<%=s4%>>
            <param name="amount" value=<%=myFormatter.format(trans.getAmount())%>>          
            <param name="TID" value=<%=trans.getTransId()%>>
            <param name="from" value= <%=trans.getCustomerPayerName()%>>
            <param name="time" value=<%=trans.getDate().substring(11)%>>
            <param name="date" value=<%=trans.getDate().substring(0,10)%>>
            </applet>
            
           <% } if(trans.getType().toLowerCase().contains("mobi")) { %>
            <applet  code="printAppletMob" archive="PrintApp.jar" width="55" height="30"> 
            <param name="v" value="<%=vV %>">
            <param name="cmd" value="<%=cmd %>" >
            <param name="voch" value=<%=s3%>>
            <param name="serial" value=<%=s4%>>
            <param name="amount" value=<%=myFormatter.format(trans.getAmount())%>>          
            <param name="TID" value=<%=trans.getTransId()%>>
            <param name="from" value= <%=trans.getCustomerPayerName()%>>
            <param name="time" value=<%=trans.getDate().substring(11)%>>
            <param name="date" value=<%=trans.getDate().substring(0,10)%>>          
            </applet>     
            
             <%} if(trans.getType().toLowerCase().contains("etis"))  { %>
            <applet  code="printAppletEtis" archive="PrintApp.jar" width="55" height="30"> 
            <param name="v" value="<%=vV %>">
            <param name="cmd" value="<%=cmd %>" >
            <param name="voch" value=<%=s3%>>
            <param name="serial" value=<%=s4%>>
            <param name="amount" value=<%=myFormatter.format(trans.getAmount())%>>           
            <param name="TID" value=<%=trans.getTransId()%>>
            <param name="from" value= <%=trans.getCustomerPayerName()%>>
            <param name="time" value=<%=trans.getDate().substring(11)%>>
            <param name="date" value=<%=trans.getDate().substring(0,10)%>>
            </applet>               
             <%}%>
                      
<!--            <br/><br/>
            يمكنك طباعة الايصال بالضغط على الزر الأيمن للماوس ثم اختيار امر طباعه او print-->
            <%} else {%>
            <%}%>
        </div>
        <% }%>
        
                </th>

            </tr>
            <tr>
                <th scope="col"><%=CONFIG.getFrom(session)%></th>
                <%  if(session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")){ %>
                <td><%=trans.getCustomerPayerName()%></td>
                <% }else{ %>
               <td> <%=MasaryManager.getInstance().getEmployee((String)session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)).getEmployeeName(session) %></td>
                <% } %>
            </tr>
             <%if(s2.equals("")){ %>
             <tr>
                <th scope="col"><%=CONFIG.getTo(session)%></th>
                <td><%=trans.getCustomerPayedName()%></td>
            </tr>
                <% }else{
            } %>
            <tr>
                <th scope="col"><%=CONFIG.getType(session)%></th>
                <td><%=trans.getType()%></td>
            </tr>
            <tr>
                <th scope="col"><%=CONFIG.getStatus(session)%></th>
                <td><%=trans.getStatus()%></td>
            </tr>

            <tr>
                <th scope="col"><%=CONFIG.getAmount(session)%></th>
                <td><%=myFormatter.format(trans.getAmount())%></td>
            </tr>
            <tr>
                <th scope="col"><%=CONFIG.getDate(session)%></th>
                <td><%=trans.getDate()%></td>

            </tr>
            <%if(s2.equals("")){
                }else{ %>
            <tr>
                <th scope="col">Voucher</th>
<!--                <td><%= s3 %></td>-->
                <td>Printed</td>

            </tr>
            <tr>
                <th scope="col">Serial</th>
<!--                <td><%= s4 %></td>-->
                <td>Printed</td>

            </tr>
            <% } %>
        </table>
    </div><!-- End of Table Content area-->
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>