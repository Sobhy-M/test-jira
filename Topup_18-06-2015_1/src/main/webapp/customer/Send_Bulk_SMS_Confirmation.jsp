<%--
    Document   : Send_Bulk_SMS.jsp
    Created on : 31/03/2014, 11:09:49 ص
    Author     : KEMO
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
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
    ArrayList<String> mobileList = new ArrayList<String>();
    mobileList = (ArrayList<String>)session.getAttribute("mobiles");           
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double masaryBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 1);
    double bulkSmsServiceBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 91);
    String mobilesNoFrom1Form = "";
    if(request.getParameter(CONFIG.PARAM_MSISDN) == null){
        mobilesNoFrom1Form = "";
    } else {
        mobilesNoFrom1Form = request.getParameter(CONFIG.PARAM_MSISDN);
    }
    double DeductedAmount = MasaryManager.getInstance().getBulk_SMS_DeductedAmount(new String(request.getParameter(CONFIG.PARAM_TEXTAREA).getBytes("ISO-8859-1"), "UTF-8").replace("\r\n", " "), request.getParameter("mytextarea"), Integer.parseInt((String)session.getAttribute("countmobile")));
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getConfirm(request.getSession()) + " " + CONFIG.getSend_Bulk_SMS(session)%></title>
        <style  type="text/css">
            #servDiv1 {
                overflow: auto;
                azimuth: far-left;
                height: 600px;
                width: 127px;
                overflow-y: scroll;
                border:1px ; 
            }
        </style>
        <script type="text/javascript">
            var ray = {
                ajax: function(st)
                {
                    document.getElementById("btnSubmit1").disabled = true;
                    this.show('load');
                },
                hide: function(st)
                {
                    var load = Document.getElementById("load");
                    load.style = "display:none;";
                },
                show: function(el)
                {
                    this.getID(el).style.display = '';
                },
                getID: function(el)
                {
                    return document.getElementById(el);
                }
            }
        </script>
        <script src="img/ga.js" async="" type="text/javascript"></script>
        <script src="img/jszip.js"></script>
        <script src="img/xlsx.js"></script>
        <script src="img/shim.js"></script>
        <script type="text/javascript">
            var arabic = /[\u0600-\u06FF]/;
            //alert(arabic.test(string)); // displays true
            function doConfirm()
            {
                if (checkIsEmpty()) {
                    if (document.getElementById("btnSubmit1").getAttribute("value") != "<%=CONFIG.getConfirm(session)%>")
                    {  
                        var messsage = document.getElementById("textarea").value;
                        var countMobile = document.getElementById("countmobile").value;
                        var language = arabic.test(messsage);            
                        if (language == true){
                            language = 'ar';
                        } else {
                            language = 'en';
                        }
                        
                        document.getElementById("mytextarea").setAttribute("value", language);
                        
                        document.getElementById("btnSubmit1").setAttribute("value", "<%=CONFIG.getConfirm(session)%>");
                        document.getElementById("textarea").setAttribute("readonly", "");
                        
                        document.getElementById("checkempty").innerHTML = "";
                        document.getElementById("choosefile").innerHTML = "";
                    }
                    else
                    {
                        document.getElementById("checkempty").innerHTML = "";
                        document.getElementById("choosefile").innerHTML = "";
                        document.getElementById("btnSubmit1").disabled = true;
                        document.SendBulkSMS.submit();
                    }
                }
            }
        </script>
        <script language="javascript" type="text/javascript">
            function limitText(limitField, limitCount, limitNum) {
                if (limitField.value.length > limitNum) {
                    limitField.value = limitField.value.substring(0, limitNum);
                } else {
                    limitCount.value = limitNum - limitField.value.length;
                }
            }
        </script>                
    </head>

    <BODY class="body" onload="document.getElementById('textarea').focus();">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(request.getSession())%></div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <div ng-app='App' class="container">
        <div ng-controller="PreviewController">
        <form name="SendBulkSMS" action="<%=CONFIG.APP_ROOT%>Web" method="post" onsubmit="return ray.ajax()">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_BULK_SMS_TRANS%>" />
        <input type="hidden" name="<%=CONFIG.CONFIRM%>" value="True" />
        <input type="hidden" name="btnSubmit" id="btnSubmit" />
            <table width="70%">
            <tr>
             <td>    
            <fieldset style="width: 55%; direction: rtl;" align="right">  
            <legend align="right" ><font size="5"><%=CONFIG.getConfirm(request.getSession()) + "  " + CONFIG.getSend_Bulk_SMS(session)%></font><img src="img/bulksms-icon_copy.ico"  width="20" height="20" > </legend> 
            
            <table border="1" width="100%">
                <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                <tr>
                    
                    <td >
                        
                        <p align="right"> <%=CONFIG.getEnterMessage(session)%> : 
                            <textarea style="resize:none" id="textarea" readonly rows="8" cols="35" name="<%=CONFIG.PARAM_TEXTAREA%>" onKeyDown="limitText(TEXTAREA,countdown,918);" 
                                 onfocus="limitText(TEXTAREA,countdown,918);" onKeyUp="limitText(TEXTAREA,countdown,918);"><%=new String(request.getParameter(CONFIG.PARAM_TEXTAREA).getBytes("ISO-8859-1"), "UTF-8")%></textarea><br>
                        <font size="1"><%=CONFIG.getMaxChar(session)%><br>
                            <%=CONFIG.getYouHave(session)%><input readonly type="text" name="countdown" size="3" value="918"> <%=CONFIG.getcharLeft(session)%></font>
                            <%--</form>--%>
                        <div id="checkempty"></div> 
                        </p>
                        
                        <p align="right">
                            
                            <!--                        </p>-->
                            <input type="radio" id="single" name=myradio value="2" checked disabled="true"><!--<%=CONFIG.getSINGLESMS(session)%>-->
<!--                            <p align="right">-->
                            <input readonly type="hidden" name="countdown2" size="3" value="119">
                            <%=CONFIG.getMobileNumber(session)%> : <textarea style="resize:none" id="MobileId" rows="3" cols="35" name="<%=CONFIG.PARAM_MSISDN%>" disabled="true" required="" tabindex="2" style="float: left;" onKeyDown="limitText(MSISDN,countdown2,119);"
                                  onKeyUp="limitText(MSISDN,countdown2,119);" disabled="true" style="background-color: #EDEDED; float: left;"><%=mobilesNoFrom1Form%></textarea>
                                  <font size="1"><%=CONFIG.getPHONE_NOTE(session)%></font>    
                                  <div id="enternumber"></div>
                        
<!--                        </p>-->
                            
                            <input type="radio" id="bulk" name=myradio value="1" disabled="true"><!--<%=CONFIG.getBULKSMS(session)%>-->
<!--                            <p align="right">-->
                        <%=CONFIG.getChooseFile(session)%> : <input type="file" required="true" id="drop" name="files" size="50" tabindex="1" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" value="kemo" disabled="true" style="background-color: #EDEDED;"/>           
                        <br><font size="1"><%=CONFIG.getPHONE_NOTE2(session)%></font> 
                        <div id="choosefile"></div>
                        
                        </p>
                        
                    </td>
                    <td>
                        <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text" name="Fees" value="<%=request.getParameter("Fees")%>"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getN_MESSAGE(request.getSession())%> :<input type="text" name="nMessage" value="<%=request.getParameter("nMessage")%>"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getCOUNT_MOBILES(request.getSession())%> :<input type="text" name="nMobiles" value="<%=request.getParameter("nMobiles")%>"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text" name="DeductedAmount" value="<%=MasaryManager.getInstance().getBulk_SMS_DeductedAmount(new String(request.getParameter(CONFIG.PARAM_TEXTAREA).getBytes("ISO-8859-1"), "UTF-8").replace("\r\n", " "), request.getParameter("mytextarea"), Integer.parseInt((String)session.getAttribute("countmobile")))%>"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </br>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <input type="hidden" name="checkDrop" id="ISdrop" value=""/>
                            <input type="submit"  name="btnSubmit1" id="btnSubmit1" tabindex="3" 
                                           <%=masaryBalance < DeductedAmount ? "disabled" : ""%>
                                           value="<%=CONFIG.getConfirm(session)%>" class="Btn"><%--onclick="doConfirm();"--%>
                    </td>
                    <td><%=CONFIG.getAllowedBalance(request.getSession())%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(bulkSmsServiceBalance)%></td>
                </tr>
                <input type="hidden" name="mytextarea" id="mytextarea" value="<%=request.getParameter("mytextarea")%>"/>
            </table>
<details open="open">
                <summary></summary>
                1-	أكتب نص الرسالة. </br>            
	  2-	 الرساله سوف تصل من رقم 6020 نرجو ترك اسم المرسل داخل الرساله.</br>	
                3-         أكتب رقم المحمول المرسل اليه ( 11 رقم ) يبدء بــ 01.</br>
	  4-       أضغط على أرسال ليتم عرض شاشة تأكيد بعدد الرسائل و المبلغ الذى سوف يخصم من رصيدكم. </br>
	  5-     أضغط على تأكيد ليتم عرض بيان ملخص للعملية بعد التنفيذ، مع العلم ان التنفيذ فى حالة أرسال الرساله لعدد كبير من أرقام المحمول قد يستغرق حتى نصف ساعة بعد التاكيد. </br>
	  6-       يمكنك مراجعة حالة العملية من التقارير مع اظهار بيان حالة الرسالة لكل رقم مرسل أليه على حدى. </br></br>
                ملاحظات :- </br>
	<b><font color="red">
                •	انتبه كل الرسائل مراقبه ومسجله لدي شركه مصاري.  	  
	</b></font></br>
                •	عدد الرسالة الواحدة 160 حرف للغة الإنجليزية و 70 حرف للغة العربية.<br/>
                •أسم المرسل المكتوب داخل الرسالة يمكن تعديله بالحذف او الإضافة.<br/>
                •تأكد من بدء كل الأرقام بصفر.<br/>
            </details>

        </fieldset>
            
            </td>
             <td>
                  <div  name='servDiv1' id='servDiv1'>
                        
<!--                 <table>-->
<!--                     <tr>-->
<!--                         <td>-->
                 <div id="out">
                        
                        <table><!--
                            <tr><th>
                                
                            </th></tr>-->
                            <%
                            for(int i=0; i<mobileList.size();i++){
                                %>
                         <tr><td>  
                         <%=mobileList.get(i)%>        
                         <input type="hidden" name="<%=i%>" value="<%=mobileList.get(i)%>"/></td></tr>
                         <%
                     }
                            %>
                            
                        </table>
                    </div>
<!--                         </td>-->
<!--                     </tr>-->
<!--                 </table>-->
            </div> 
             </td>
            </tr>
         </table>
            
                <input type="hidden" name="sheetname" value="<%=request.getParameter("sheetname")%>"/>
                <input type="hidden" name="countmobile" value="<%=(String)session.getAttribute("countmobile")%>"/>
                <input type="hidden" name="mytextarea" id="mytextarea" value="<%=request.getParameter("mytextarea")%>"/>
                <input type="hidden" id ="notBrowse" name="notBrowse" value="<%=request.getParameter("noBrowse")%>"/>
                    
            <%--<div id="list"></div>--%>
         
         <script>
             
             var rABS = typeof FileReader !== "undefined" && typeof FileReader.prototype !== "undefined" && typeof FileReader.prototype.readAsBinaryString !== "undefined";
             function fixdata(data) {
                 var o = "", l = 0, w = 10240;
                 for(; l<data.byteLength/w; ++l)
                     o+=String.fromCharCode.apply(null,new Uint8Array(data.slice(l*w,l*w+w)));
                 o+=String.fromCharCode.apply(null, new Uint8Array(data.slice(o.length)));
                 return o;
             }
             
             function xlsxworker(data, cb) {
                 var worker = new Worker('img/xlsxworker.js');
                 worker.onmessage = function(e) {
                     switch(e.data.t) {
                         case 'ready': break;
                         case 'e': console.error(e.data.d); break;
                         case 'xlsx': cb(JSON.parse(e.data.d)); break;
                     }
                 };
                 var arr = rABS ? data : btoa(fixdata(data));
                 worker.postMessage({d:arr,b:rABS});
             }
             
             
             function to_json(workbook) {
                 var result = {};
                 workbook.SheetNames.forEach(function(sheetName) {
                     var roa = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
                     if(roa.length > 0){
                         result[sheetName] = roa;
                     }
                 });
                 return result;
             }
             
             function to_csv(workbook) {
                 var result = [];
                 var htmlstring = [];
                 workbook.SheetNames.forEach(function(sheetName) {
                     var csv = XLSX.utils.sheet_to_csv(workbook.Sheets[sheetName]);
                     if(csv.length > 0){
                     result.push(sheetName);
//                     result.push("");
                     result.push(csv);
                     
                     var mobiles = [];
                     var chars="";
                     
                     for(var i=0; i<csv.length;i++){
                         if(csv[i] == '\n' || csv[i] == ' ' || i == (csv.length-1)){
                             
                             if(i == (csv.length-1)){
                                
                                 chars = chars + csv[i];
                             }
                             mobiles.push(chars);
                             chars = "";
                         }else{
                             chars = chars + csv[i];
                         }
                     }
                     
                     htmlstring.push("<table>");  //<tr><td>
//                     htmlstring.push(sheetName);
                     htmlstring.push("<input type=\"hidden\" name=\"sheetname\" value=\""+ sheetName +"\"/>");//</td></tr>
                     for(var i=0; i<mobiles.length;i++){
                         htmlstring.push("<tr><td>"+mobiles[i]);
//                         htmlstring.push("</td></tr>")
                         htmlstring.push("<input type=\"hidden\" name=\""+i+"\" value=\""+ mobiles[i] +"\"/></td></tr>");
                     }
                     htmlstring.push("<input type=\"hidden\" id =\"countmobile\" name=\"countmobile\" value=\""+ mobiles.length +"\"/>");
                     htmlstring.push("</table>");
//                     alert(htmlstring);
                     }
                 });
                 document.getElementById('ISdrop').value = "done";
//                 return result.join("\n");
                 return htmlstring;
             }
             
             function to_formulae(workbook) {
                 var result = [];
                 workbook.SheetNames.forEach(function(sheetName) {
                     var formulae = XLSX.utils.get_formulae(workbook.Sheets[sheetName]);
                     if(formulae.length > 0){
                         result.push("SHEET: " + sheetName);
                         result.push("");
                         result.push(formulae.join("\n"));
                     }
                 });
                 return result.join("\n");
             }
             
             var tarea = document.getElementById('b64data');
             function b64it() {
                 var wb = XLSX.read(tarea.value, {type: 'base64'});
                 process_wb(wb);
             }
             
             function process_wb(wb) {
                 var output = [];
                 
                 output = to_csv(wb);
                 
//                 if(out.innerText === undefined) out.textContent = output;
//                 else 
                     document.getElementById("out").innerHTML = output;
             }
             
             var drop = document.getElementById('drop');
             
             function handleDrop(e) {
//                 e.stopPropagation();
//                 e.preventDefault();
//                 var files = e.dataTransfer.files;
                 var files = e.target.files;
                 var i,f;
                 for (i = 0, f = files[i]; i != files.length; ++i) {
                     var reader = new FileReader();
                     var name = f.name;
                     reader.onload = function(e) {
                         var data = e.target.result;
                         if(typeof Worker !== 'undefined') {
                             xlsxworker(data, process_wb);
                         } else {
                             var wb;
                             if(rABS) {
                                 wb = XLSX.read(data, {type: 'binary'});
                             } else {
                                 var arr = fixdata(data);
                                 wb = XLSX.read(btoa(arr), {type: 'base64'});
                             }
                             process_wb(wb);
                             
                         }
                     };
                     if(rABS) reader.readAsBinaryString(f);
                     else reader.readAsArrayBuffer(f);
                 }
             }
             
             function handleDragover(e) {
                 e.stopPropagation();
                 e.preventDefault();
                 e.dataTransfer.dropEffect = 'copy';
             }
             
            var change = document.getElementById('drop');
            if (change.addEventListener) {
                change.addEventListener('change', handleDragover, false);
                change.addEventListener('change', handleDragover, false);
                change.addEventListener('change', handleDrop, false);
            }
//             if(drop.addEventListener) {
//                 drop.addEventListener('dragenter', handleDragover, false);
//                 drop.addEventListener('dragover', handleDragover, false);
//                 drop.addEventListener('drop', handleDrop, false);
//             }
        </script>
            
<!--        </div> End of Table Content area-->
    </form>
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>

