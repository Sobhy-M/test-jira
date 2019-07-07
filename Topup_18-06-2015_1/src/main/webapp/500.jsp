<%-- 
    Document   : 500
    Created on : Nov 10, 2017, 3:11:52 AM
    Author     : tamer
--%>

<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage = "true" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Error 500</title>
        <meta http-equiv="keywords" content="">
        <meta http-equiv="description" content="Error 500">
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    </head>

    <body>
        <% 
             StringBuffer ex = new StringBuffer();
        
        	 ex.append(exception.getMessage());
        	 ex.append("\n");
             
             for(StackTraceElement element : exception.getStackTrace())
             {
            	 ex.append(element.toString()); 
            	 ex.append("\n");
             }
             
             MasaryManager.logger.error("Exception while processing request" + ex.toString());
             
        %>
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0" >
            <tr>
                <td colspan="3" bgcolor="#F3F3F3">
                    <!--<img src="img/trans.gif" height="1" width="1">-->
                </td>
            </tr>
        </table>

        <table height="600" width="100%">
            <tr align="center">
                <td class="headline">Error 500 - Internal Server Error</td>
            </tr>
            <tr align="center">
                <td>
                    <pre>from RFC 2068/10.5.1 - 500 Internal Server Error

The server encountered an unexpected condition which 
prevented it from fulfilling the request. 
                    </pre>
                </td>
            </tr>
        </table>
    </body>
</html>

