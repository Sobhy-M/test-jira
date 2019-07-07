<%-- 
    Document   : ReCharge
    Created on : 27/06/2009, 01:09:17 م
    Author     : Melad
--%>

<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%
            String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
            if (role == null) {
                response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
                return;
            }
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>خدمات  اتصالات   </title>
        <script type="text/javascript">
            var ray={
                ajax:function(st)
                {
                    this.show('load');
                },
                hide:function(st)
                {
                    var load=Document.getElementById("load");
                    load.style="display:none;";F
                },

                show:function(el)
                {
                    this.getID(el).style.display='';
                },
                getID:function(el)
                {
                    return document.getElementById(el);
                }
            }
        </script>
        <style type="text/css">
            #load{
                position:absolute;
                top:50%;
                z-index:1;
                border:3px double #999;
                width:300px;
                height:300px;
                margin-top:-150px;
                margin-left:-150px;
                background:#ffffff;
                top:50%;
                left:50%;
                text-align:center;
                line-height:300px;
                font-family:"Trebuchet MS", verdana, arial,tahoma;
                font-size:18pt;
                background-image:url(img/loading.gif);
                background-position:50% 40%;
            }
        </style>
    </head>
    <BODY class="body" onload="ray.hide();">
        <div id="load" style="display:none;">برجاء الانتظار </div>

  <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
    </div>
                <div class="content_body"><br><br>
                    <form action="<%=CONFIG.APP_ROOT%>Web" method=post  name="customer_form" id="saveform">
                        <table >
                            <input type="hidden" name="action" value="<%= CONFIG.ACTION_CUSTOMER_RECHARGE%>">
                            <th rowspan="5"><img  src="img/etisalat.jpg"> </th>
                            <!--                 <th  scope="row">
                                <input type="submit" value= "شراء مخزون جديد" name="<%=CONFIG.PARAM_CUSTOMER_BTN%>" />
                            </th>

                            --->
                            <tr  scope="row">
                                <td><input type="submit" value="<%=CONFIG.TransferToAnotherRep%>" name="<%=CONFIG.PARAM_CUSTOMER_BTN%>" /></td>
                            </tr>
                            <%
            if (!role.equals("1")) {
                            %>

                            <tr scope="row">
                                <td><input type="submit" value="<%=CONFIG.MerchantRecharge%>" name="<%=CONFIG.PARAM_CUSTOMER_BTN%>" /></td>
                            </tr>
                            <%
            }
            if (role.equals("1")) {
                            %>

                            <tr scope="row">
                                <td><input type="submit" value="<%=CONFIG.CHECKCREIDET%>" name="<%=CONFIG.PARAM_CUSTOMER_BTN%>" onclick="return ray.ajax()" /></td>
                            </tr>
                            <%
            }
            if (!role.equals("1")) {
                            %>
                            <tr scope="row">
                                <td><input type="submit" value="<%=CONFIG.CustomerTopUp%>" name="<%=CONFIG.PARAM_CUSTOMER_BTN%>" /></td>
                            </tr>
                            <%
            }
                            %>
                        </table>
                    </form>
                    <!-- content body -->
                </div><!-- End of Table Content area-->
            </div><!-- End content body -->



            <div style="clear: both;">&nbsp;</div>

                        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->

    </body>
</html>
