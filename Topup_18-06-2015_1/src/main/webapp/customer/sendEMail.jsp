<%@page import="com.masary.utils.BuiildExcelFile"%>
<%@page import="com.masary.utils.SendEmail"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.dto.SellVoucherResponse"%>
<%@page import="com.masary.database.dto.BulkVoucherDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFSheet"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFWorkbook"%>
<%@page import="org.apache.poi.ss.usermodel.Cell"%>
<%@page import="org.apache.poi.ss.usermodel.Row"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*,javax.activation.*"%> 

<%
    String email = (String) request.getAttribute("email");
    String isSeconde = (String) request.getAttribute("second");
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();

    ArrayList<SellVoucherResponse> voucherList = (ArrayList<SellVoucherResponse>) session.getAttribute("voucherList");
    ArrayList<BulkVoucherDTO> voucherInfo = (ArrayList<BulkVoucherDTO>) session.getAttribute("voucherInfoList");

    BuiildExcelFile buildFile = new BuiildExcelFile();
    String fileName = buildFile.bulidVoucherExcel(voucherList, voucherInfo);

    boolean emailWillSend = false;

    for (int i = 0; i < voucherInfo.size(); i++) {
        if ((isSeconde != null && isSeconde.equals("second")) || voucherInfo.get(i).isIsFound()) {
            emailWillSend = true;
            SendEmail sendEmail = new SendEmail();
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date().getTime());
            String emailSubject = "Masary cards by email " + date;
            String emailBody1 = "Dear valued customer,\n Attached is a file of the cards requested from Masary.";
            emailBody1 = emailBody1.concat("\n In the attached file, the number to use to recharge is the number in column titled (Voucher recharge code).");
            emailBody1 = emailBody1.concat("\n\n" + "The recharge ways of the three operators are:");
            emailBody1 = emailBody1.concat("\n Vodafone: ").concat(CONFIG.VF_RECHARGE_TYPE).concat("\n Etisalat: ").concat(CONFIG.ET_RECHARGE_TYPE).concat("\n Mobinil: ").concat(CONFIG.MN_RECHARGE_TYPE);
            emailBody1 = emailBody1.concat("\n\n The recharge ways of One Card is:");
            emailBody1 = emailBody1.concat("\n Log into your One Card account, go to (Recharge Your Balance), select (Prepaid Cards), press (Redeem Your Card Now), write (Recharge code) and press (Recharge account).");
            emailBody1 = emailBody1.concat("\n Note: The (Recharge code) is case sensitive.");
            emailBody1 = emailBody1.concat("\n\n The recharge way of Marhaba is:");
            emailBody1 = emailBody1.concat("\n Call 08044, and follow the voice steps.");
            emailBody1 = emailBody1.concat("\n\n --------------------------------------------------------------- ").
                    concat("\n\n Contact Masary at Mobile +201110447711   Tel/Fax +20233362799 / +2037615181").concat("\n\n cc.info@e-masary.com").concat("\n\n 122 Mohey El Din Abou El Ezz, St., Dokki, Giza, Egypt   Box office 12411");
            sendEmail.sendVoucherEmail(email, fileName, emailSubject, emailBody1);
            break;
        }
    }
%> 
<html> 
    <head> 
        <title>Send Email</title> 
    </head> 

    <body class="body"> 
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         
        <%}%>
    </div>
    <form  action="<%=CONFIG.APP_ROOT%>Web" a method="post">
        <input type="hidden" value="<%=email%>" name="email">
        <input type="hidden" value="<%=CONFIG.ACTION_RE_SEND%>" name="action">
        <div class="content_body"><br><br>
            <%if (isSeconde == null || !isSeconde.equals("second")) {%>
            <table id="t1" border="1" >
                <thead>
                <th scope="col"> <%=CONFIG.getVoucherType(session)%></th>
                <th> <%=CONFIG.getAmount_V(session)%></th>
                <th><%=CONFIG.getVoucherNO(session)%></th>
                <th><%=CONFIG.getDone(session)%></th>
                <th><%=CONFIG.getError(session)%></th>
                </thead>
                <tbody>
                    <%
                        try {
                            for (int i = 0; i < voucherInfo.size(); i++) {
                    %>
                    <tr>
                        <th scope="row"><%=voucherInfo.get(i).getService_Name()%></td>
                        <td><%=voucherInfo.get(i).getDenom()%></td>
                        <td><%=voucherInfo.get(i).getVoucherCount()%></td>
                        <td><%=voucherInfo.get(i).isIsFound() ? "<img alt='' height='42' width='42' src='img/Done.png' align='left' />" : "<img alt='' height='42' width='42' src='img/Not_Done.png' align='left' />"%></td>                       
                        <td><%=voucherInfo.get(i).isIsFound() ? "" : voucherInfo.get(i).getReason()%></td>

                    </tr>
                    <%  }
                    %>    
                    <tr> 
                        <td colspan="4" style="margin-right: 15%" ><div align="center"><input type="submit" name="btnSubmit" <%=emailWillSend ? "" : "disabled"%> tabindex="5" align="middle"
                                                                                              value="<%=CONFIG.getRe_Send(session)%>"  class="Btn"></div>
                        </td>
                    </tr>
                    <%
                        } catch (Exception ex) {
                            MasaryManager.logger.error(ex);
                        }
                    %>
                </tbody>

            </table>
            <%}%>
        </div>
    </form>
    <br>
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>