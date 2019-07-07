
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.dto.EarnListDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table cellpadding="1"  cellspacing="1" border="1" bordercolor="gray">
            <thead>
            <th scope="col"><%=CONFIG.getTodayDate(session)%></th>  
            <th><%=CONFIG.getGrossProfit(session)%></th>
            <th><%=CONFIG.getTransNum(session)%></th>
        </thead>
        <tbody>
            <% try {
                    String fileName = new SimpleDateFormat("yyyyMMddHH24mm").format(new java.util.Date().getTime());
                    DecimalFormat myFormatter = CONFIG.getFormater(session);
                    List transFromAg_1 = (List) session.getAttribute("transFromAg_1");
                    EarnListDTO eliDTO = null;
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".xls");

                    for (int i = 0; i < (int) transFromAg_1.size(); i++) {
                        eliDTO = (EarnListDTO) transFromAg_1.get(i);
            %>
            <tr>

                <th scope="row"><%=eliDTO.getDate()%> </th>
                <td><%=eliDTO.getAllEarn()%></td>
                <td><%=eliDTO.getTransNum()%></td>   
                <%
                        }
                    } catch (Exception ex) {
                        MasaryManager.logger.error(ex);
                        System.out.println("EX" + ex.getMessage());
                    }
                %>
            </tr>
        </tbody>
    </table>

</body>

</html>