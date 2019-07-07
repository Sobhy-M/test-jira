<%@page import="org.apache.poi.ss.usermodel.DataFormat"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFWorkbook"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFCellStyle"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.SellVoucherResponse"%>
<%@page import="java.util.ArrayList"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %>
<%@ page import="com.masary.database.dto.BulkVoucherDTO;" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body >
        <table cellpadding="1"  cellspacing="1" border="1" bordercolor="gray">
            <tr>
                <td>Voucher recharge code</td>
                <td>Denomination</td>
                <td>Type</td>
                <td>Voucher serial</td>
            </tr>
            <tr>
                <td>رقم كارت الشحن</td>
                <td>فئة الكارت</td>
                <td>نوع الكارت</td>
                <td>رقم السيريال</td>
            </tr>
            <%
                
                java.util.Date utilDate = new java.util.Date();
                SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHH24mm");
                String fileName = format1.format(utilDate.getTime());
                ArrayList<SellVoucherResponse> voucherList = (ArrayList<SellVoucherResponse>) session.getAttribute("voucherList");
                ArrayList<BulkVoucherDTO> voucherInfo = (ArrayList<BulkVoucherDTO>) session.getAttribute("voucherInfoList");
                if (voucherList != null) {
                    //--------set cell format type to text--------
//                    HSSFWorkbook wb = new HSSFWorkbook();
//                    DataFormat format = wb.createDataFormat();
//                    HSSFCellStyle cellStyle = wb.createCellStyle();
//                    cellStyle.setDataFormat(format.getFormat("text")); // or "text"
//                 cell.setCellStyle(cellStyle);
                    //--------End set cell format type to text--------
                
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".xls");
                    for (int i = 0; i < voucherList.size(); i++) {
                        for (int j = 0; j < voucherList.get(i).getVoucherSerial().size(); j++) {
            %>
			  <% 
                  // Amir 19-8-2015  adding space to card num 
                 // for Voucher Pin to cover the pin missing number
                 // 19- 9 -2015   adding space to Serial        
                   String sPin= voucherList.get(i).getVoucherPin().get(j); 
                   String sSerial=voucherList.get(i).getVoucherSerial().get(j);
                   String sFinalPin="";
                   String sFinalSerial="";
                   
                  int counter1=0;
                  for(int count1=0,count2=4;count1<=sPin.length() && count2<=sPin.length() ;count1++,count2++)
                  {
                      if(count2%4==0)
                      {
                         sFinalPin=sFinalPin+" "+sPin.substring(count1,count2);
                         counter1=count2;
                      }
                      
                                          
                  }
                   if(sPin.length()%4!=0)
                    {
                         sFinalPin=sFinalPin+" "+sPin.substring(counter1,sPin.length());
                    }
                   
                 //  =============== Serial
                     int counter2=0;
                  for(int count1=0,count2=4;count1<=sSerial.length() && count2<=sSerial.length() ;count1++,count2++)
                  {
                      if(count2%4==0)
                      {
                         sFinalSerial=sFinalSerial+" "+sSerial.substring(count1,count2);
                         counter2=count2;
                      }
                      
                                          
                  }
                   if(sSerial.length()%4!=0)
                    {
                         sFinalSerial=sFinalSerial+" "+sSerial.substring(counter2,sSerial.length());
                    }
                   
                   
            
              %> 
            <tr>
                <td><%= sFinalPin%></td>			
                <td><%= voucherInfo.get(i).getDenom()%></td>			
                <td><%= voucherInfo.get(i).getService_Name()%></td>			
                <td><%= sFinalSerial%></td>			
            </tr>
            <%
                        }
                    }
                }
            %>

        </table>

    </body>

</html>