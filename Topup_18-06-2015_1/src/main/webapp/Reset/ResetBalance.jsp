<%-- 
    Document   : ResetBalance
    Created on : 26/12/2011, 04:26:35 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.dto.CustomerDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List Box Demo</title>
<script type="text/javascript" src="option.js"></script>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
  </head>

  <body class="body" onload="createListObjects()">
      <div align="center">
    <table border="1">
        <tr>
          <td align="center">availableOptions</td>
          <td></td>
          <td align="center">SelectedOptions</td>
        </tr>
      <tr>
        <td><select size="10" id="availableOptions" name="availableOptions">
                <% List<CustomerDTO> custlist =MasaryManager.getInstance().getAllCustomers();
                for (CustomerDTO Customer : custlist) {%>
                <option value="<%=Customer.getCustomerID()%>">
            <%=Customer.getCustomerName()%>
          </option>
<%}%>
<!--          <option value="option3">
            option3
          </option>

          <option value="option5">
            option5
          </option>

          <option value="option7">
            option7
          </option>-->
        </select> </td>

        <td valign="top"><button onclick="addAll()">&gt;&gt;&gt;</button>
        <br>
         <button onclick="addAttribute()">&gt;</button>
        <br>

        <br>
         <button onclick="delAttribute()">&lt;</button>
        <br>
         <button onclick="delAll()">&lt;&lt;&lt;</button> </td>
        <td><select size="10" id="selectedOptions" name="selectedOptions">
<!--          <option value="option2">
            option2
          </option>

          <option value="option4">
            option4
          </option>

          <option value="option6">
            option6
          </option>

          <option value="option8">
            option8
          </option>-->
        </select> </td>
      </tr>

      <tr>
          <td></td><td colspan="2" align="right"><button onclick="showSelected()">Submit</button> </td>
      </tr>
    </table>
      </div>




<div align="center">
    <table border="1">
        <tr>
          <td align="center">availableServices</td>
          <td></td>
          <td align="center">SelectedServices</td>
        </tr>
      <tr>
          <td><select size="10" id="availableServiceOptions" name="availableServiceOptions" onclick="createListObjects1()">
                <% List<ServiceDTO> Servlist =MasaryManager.getInstance().getAllServices();
                for (ServiceDTO Service : Servlist) {%>
                <option value="<%= Service.getIdService() %>">
            <%= Service.getStrServiceNameArabic() %>
          </option>
<%}%>
<!--          <option value="option3">
            option3
          </option>

          <option value="option5">
            option5
          </option>

          <option value="option7">
            option7
          </option>-->
        </select> </td>

        <td valign="top"><button onclick="addAll1()">&gt;&gt;&gt;</button>
        <br>
         <button onclick="addAttribute1()">&gt;</button>
        <br>

        <br>
         <button onclick="delAttribute1()">&lt;</button>
        <br>
         <button onclick="delAll1()">&lt;&lt;&lt;</button> </td>
        <td><select size="10" id="selectedServiceOptions" name="selectedServiceOptions">
<!--          <option value="option2">
            option2
          </option>

          <option value="option4">
            option4
          </option>

          <option value="option6">
            option6
          </option>

          <option value="option8">
            option8
          </option>-->
        </select> </td>
      </tr>

      <tr>
          <td></td><td colspan="2" align="right"><button onclick="showSelected1()">Submit</button> </td>
      </tr>
    </table>
      </div>
      </div>
  </body>
</html>

