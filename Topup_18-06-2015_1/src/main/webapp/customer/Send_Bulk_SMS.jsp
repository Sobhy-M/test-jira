<%--
    Document   : Send_Bulk_SMS.jsp
    Created on : 31/03/2014, 11:09:49 ص
    Author     : KEMO
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
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
    String feess = MasaryManager.getInstance().getBulkSMSPrice();
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double masaryBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 1);
    double bulkSmsServiceBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 91);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getSend_Bulk_SMS(session)%></title>
        <style  type="text/css">
            #servDiv1 {
                overflow: auto;
                azimuth: leftwards;
                height: 600px;
                width: 127px;
                overflow-y: scroll;
                border:1px ; 
            }
        </style>
        <script src="img/ga.js" async="" type="text/javascript"></script>
        <script src="img/jszip.js"></script>
        <script src="img/xlsx.js"></script>
        <script src="img/shim.js"></script>
        <script type="text/javascript">
            function doConfirm()
            {
                if (checkIsEmpty()) {

                    if (document.getElementById("btnSubmit1").getAttribute("value") != "<%=CONFIG.getConfirm(session)%>")
                    {
                        document.getElementById("btnSubmit1").setAttribute("value", "<%=CONFIG.getConfirm(session)%>");
                        document.getElementById("textarea").setAttribute("readonly", "");
                        document.getElementById("DeductedAmount").setAttribute("value", "<%=MasaryManager.getInstance().getBulk_SMS_DeductedAmount("", "", 1)%>");
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
            function isNumberKey(evt) {
                var charCode = (evt.which) ? evt.which : event.keyCode;
                document.getElementById("notBrowse").setAttribute("value", '1');
                var mobilesNumber = document.getElementById("MobileId").value;
                if ((mobilesNumber.length == 11 || mobilesNumber.length == 23 || mobilesNumber.length == 35 || mobilesNumber.length == 47 || mobilesNumber.length == 59 ||
                        mobilesNumber.length == 71 || mobilesNumber.length == 83 || mobilesNumber.length == 95 || mobilesNumber.length == 107)) {
                    if (charCode == 44 || charCode == 8 || charCode == 97) {
                        return true;
                    } else {
                        alert('<%=CONFIG.getCOMMA_ONLY(session)%>');
                        return false;
                    }
                } else {
                    if ((charCode >= 48 && charCode <= 57) || charCode == 97) {
                        return true;
                    } else {
                        if (charCode == 8) {
                            return true;
                        }
                        alert('<%=CONFIG.getNUMBER_ONLY(session)%>');
                        return false;
                    }
                }

            }

            function onTextChanged() {
                var arabic = /[\u0600-\u06FF]/;
                var messsage = document.getElementById("textarea").value;
                var language = arabic.test(messsage);
                if (language == true) {
                    language = 'ar';
                } else {
                    language = 'en';
                }
                document.getElementById("mytextarea").setAttribute("value", language);
            }

            function limitText(limitField, limitCount, limitNum) {
                if (limitField.value.length > limitNum) {
                    limitField.value = limitField.value.substring(0, limitNum);
                } else {
                    limitCount.value = limitNum - limitField.value.length;
                }
                //---------------------------------key ppress counter message number----------------
                var messsages = document.getElementById("textarea").value;
                if (messsages != "") {
                    var arabicNum = 60;
                    var englishNum = 140;
                    var countMessageN = 0;
                    var arabic = /[\u0600-\u06FF]/;
                    var language = arabic.test(messsages);
                    if (language == true) {
                        countMessageN = Math.ceil((messsages.length) / arabicNum);
                    } else {
                        countMessageN = Math.ceil((messsages.length) / englishNum);
                    }
                    var nMessage = document.getElementById("nMessage");
                    nMessage.value = countMessageN;
                }
                var mobilesNumbers = document.getElementById("MobileId").value;
                var countMobiles = Math.ceil((mobilesNumbers.length) / 12);
                document.getElementById("nMobiles").value = countMobiles;
                document.getElementById("countmobile").setAttribute("value", countMobiles);
                //---------------------------------END key ppress counter message number----------------
            }
        </script>                
    </head>

    <BODY class="body" onload="document.getElementById('textarea').focus();">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <div ng-app='App' class="container">
            <div ng-controller="PreviewController">
                <form name="SendBulkSMS" id="SendBulkSMS" action="<%=CONFIG.APP_ROOT%>Web" method="post"> <!--onsubmit="return checkIsEmpty()"-->
                <input type="hidden" name="action" value="<%=CONFIG.ACTION_BULK_SMS_TRANS%>" />
                <input type="hidden" name="btnSubmit" id="btnSubmit" />
                <table width="10%">
                    <tr>
                        <td>    
                            <fieldset style="width: 55%; direction: rtl;" align="right">  
                                <legend align="right" ><font size="5"><%=CONFIG.getSend_Bulk_SMS(session)%></font><img src="img/bulksms-icon_copy.ico"  width="20" height="20" > </legend> 
                                <table border="1" width="100%">
                                    <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                                    <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                                    <tr>
                                        <td >
                                            <p align="right"> <%=CONFIG.getEnterMessage(session)%> : 
                                                <textarea style="resize:none" id="textarea" rows="8" cols="35" name="<%=CONFIG.PARAM_TEXTAREA%>" onKeyDown="limitText(TEXTAREA, countdown, 918);"
                                                          onKeyUp="limitText(TEXTAREA, countdown, 918);" onfocus="limitText(TEXTAREA, countdown, 918);" onchange="onTextChanged();" tabindex="1"><%=CONFIG.getFROMFROM(session)%> <%=agent.getName(session)%></textarea><br>
                                                <font size="1">
                                                    <%=CONFIG.getYouHave(session)%><input readonly type="text" name="countdown" size="3" value="918"> <%=CONFIG.getcharLeft(session)%>&nbsp;<%=CONFIG.getMaxChar(session)%></font>
                                            <div id="checkempty"></div> 
                                            </p>
                                            <p align="right">
                                                <input type="radio" id="single" name=myradio value="2" checked><!--<%=CONFIG.getSINGLESMS(session)%>-->
                                                <input readonly type="hidden" name="countdown2" size="3" value="119">
                                                <%=CONFIG.getMobileNumber(session)%> : <br align="right">
                                                <textarea style="resize:none" id="MobileId" rows="3" cols="35" name="<%=CONFIG.PARAM_MSISDN%>" required="" tabindex="2" style="float: left;" onKeyDown="limitText(MSISDN, countdown2, 119);"
                                                          onKeyUp="limitText(MSISDN, countdown2, 119);" onkeypress="return isNumberKey(event)" onchange="onTextChanged();"></textarea><br>
                                                <font size="1"><%=CONFIG.getPHONE_NOTE(session)%></font>    
                                            <div id="enternumber"></div>
                                            <input type="radio" id="bulk" name=myradio value="1" ><!--<%=CONFIG.getBULKSMS(session)%>-->
                                            <%=CONFIG.getChooseFile(session)%> : <input type="file" required="true" id="drop" name="files" size="50" tabindex="3" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" disabled="true"/>           
                                            <br><font size="1"><%=CONFIG.getPHONE_NOTE2(session)%></font> 
                                            <div id="choosefile"></div>
                                            </p>
                                        </td>
                                        <td>
                                            <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text" name="Fees" value="<%= feess%>"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                            <p align="right"><%=CONFIG.getN_MESSAGE(request.getSession())%> :<input type="text" name="nMessage" value="1"  readonly tabindex="6" id="nMessage" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                            <p align="right"><%=CONFIG.getCOUNT_MOBILES(request.getSession())%> :<input type="text" name="nMobiles" value="0"  readonly tabindex="7" id="nMobiles" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                            <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="8" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                            </br>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="hidden" name="checkDrop" id="ISdrop" value=""/>
                                            <input type="submit"  name="btnSubmit1" id="btnSubmit1" tabindex="4" 
                                                   <%=masaryBalance < 0.25 ? "disabled" : ""%>
                                                   value="<%=CONFIG.getSend(session)%>" class="Btn" ><%--onclick="doConfirm();"--%>
                                        </td>
                                        <td><%=CONFIG.getAllowedBalance(request.getSession())%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(bulkSmsServiceBalance)%></td>
                                    </tr>
                                    <input type="hidden" name="mytextarea" id="mytextarea" />
                                    <input type="hidden" id ="countmobile" name="countmobile" value=""/>
                                    <input type="hidden" id ="notBrowse" name="notBrowse" value=""/>
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
                        <td bgcolor="#FF0000">
                            <div  name='servDiv1' id='servDiv1'>
                                <div id="out"> 
                                </div>
                            </div> 
                        </td>
                    </tr>
                </table>                     
                <script>
            var radios = document.getElementsByName('myradio');
            for (var i = 0; i < radios.length; i++) {
                radios[i].onclick = function() {
                    if (this.value == 2) {
                        document.getElementById("drop").disabled = true;
                        document.getElementById("MobileId").disabled = false;
                        document.getElementById("out").innerHTML = "";
                        document.getElementById("drop").value = "";
                        document.getElementById("nMobiles").value = 0;
                        document.getElementById("notBrowse").setAttribute("value", '1');

                    } else {
                        document.getElementById("notBrowse").setAttribute("value", '0');
                        document.getElementById("drop").disabled = false;
                        document.getElementById("MobileId").disabled = true;
                        document.getElementById("MobileId").value = "";
                    }
                };
            }
                </script>
                <script>
                    var rABS = typeof FileReader !== "undefined" && typeof FileReader.prototype !== "undefined" && typeof FileReader.prototype.readAsBinaryString !== "undefined";
                    function fixdata(data) {
                        var o = "", l = 0, w = 10240;
                        for (; l < data.byteLength / w; ++l)
                            o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w)));
                        o += String.fromCharCode.apply(null, new Uint8Array(data.slice(o.length)));
                        return o;
                    }

                    function xlsxworker(data, cb) {
                        var worker = new Worker('img/xlsxworker.js');
                        worker.onmessage = function(e) {
                            switch (e.data.t) {
                                case 'ready':
                                    break;
                                case 'e':
                                    console.error(e.data.d);
                                    break;
                                case 'xlsx':
                                    cb(JSON.parse(e.data.d));
                                    break;
                            }
                        };
                        var arr = rABS ? data : btoa(fixdata(data));
                        worker.postMessage({d: arr, b: rABS});
                    }


                    function to_json(workbook) {
                        var result = {};
                        workbook.SheetNames.forEach(function(sheetName) {
                            var roa = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
                            if (roa.length > 0) {
                                result[sheetName] = roa;
                            }
                        });
                        return result;
                    }

                    function to_csv(workbook) {
                        var result = [];
                        var htmlstring = [];
                        var htmlstring2 = [];
                        workbook.SheetNames.forEach(function(sheetName) {
                            var csv = XLSX.utils.sheet_to_csv(workbook.Sheets[sheetName]);
                            if (csv.length > 0) {
                                result.push(sheetName);
                                result.push(csv);

                                var mobiles = [];
                                var chars = "";

                                for (var i = 0; i < csv.length; i++) {
                                    if (csv[i] == '\n' || csv[i] == ' ' || i == (csv.length - 1)) {

                                        if (i == (csv.length - 1)) {

                                            chars = chars.replace(',', '') + csv[i].replace(',', '');
                                        }
                                        mobiles.push(chars.replace(',', ''));
                                        chars = "";
                                    } else {
                                        chars = chars.replace(',', '') + csv[i].replace(',', '');
                                    }
                                }
                                var arabic = /[\u0600-\u06FF]/;
                                var messsage = document.getElementById("textarea").value;
                                var language = arabic.test(messsage);
                                if (language == true) {
                                    language = 'ar';
                                } else {
                                    language = 'en';
                                }
                                document.getElementById("mytextarea").setAttribute("value", language);
                                htmlstring.push("<table>");//<tr><th>
                                htmlstring.push("<input type=\"hidden\" name=\"sheetname\" value=\"" + sheetName + "\"/>");//</th></tr>
                                for (var i = 0; i < mobiles.length; i++) {
                                    htmlstring.push("<tr><td>" + mobiles[i].replace(',', ''));
                                    htmlstring.push("<input type=\"hidden\" name=\"" + i + "\" value=\"" + mobiles[i] + "\"/></td></tr>");
                                }
                                document.getElementById("countmobile").setAttribute("value", mobiles.length);
                                htmlstring.push("</table>");
                                document.getElementById('nMobiles').value = mobiles.length;
                                document.getElementById("notBrowse").setAttribute("value", '0');
                            }
                        });
                        document.getElementById('ISdrop').value = "done";
                        return htmlstring;
                    }

                    function to_formulae(workbook) {
                        var result = [];
                        workbook.SheetNames.forEach(function(sheetName) {
                            var formulae = XLSX.utils.get_formulae(workbook.Sheets[sheetName]);
                            if (formulae.length > 0) {
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
                        document.getElementById("out").innerHTML = output;
                    }
                    var drop = document.getElementById('drop');
                    function handleDrop(e) {
                        var files = e.target.files;
                        var i, f;
                        for (i = 0, f = files[i]; i != files.length; ++i) {
                            var reader = new FileReader();
                            var name = f.name;
                            reader.onload = function(e) {
                                var data = e.target.result;
                                if (typeof Worker !== 'undefined') {
                                    xlsxworker(data, process_wb);
                                } else {
                                    var wb;
                                    if (rABS) {
                                        wb = XLSX.read(data, {type: 'binary'});
                                    } else {
                                        var arr = fixdata(data);
                                        wb = XLSX.read(btoa(arr), {type: 'base64'});
                                    }
                                    process_wb(wb);
                                }
                            };
                            if (rABS)
                                reader.readAsBinaryString(f);
                            else
                                reader.readAsArrayBuffer(f);
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
                </script>
            </form>
        </div><!-- End content body -->
        <div style="clear: both;">&nbsp;</div>

        <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
    </div><!-- End of Main body-->
</body>
</html>

