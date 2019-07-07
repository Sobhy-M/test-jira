/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.utils;

import com.masary.database.dto.BulkVoucherDTO;
import com.masary.database.dto.SellVoucherResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author omnya
 */
public class BuiildExcelFile {
 
    public String bulidVoucherExcel( ArrayList<SellVoucherResponse> voucherList, ArrayList<BulkVoucherDTO> voucherInfo){
         HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("Sample sheet");
    java.util.Date utilDate = new java.util.Date();
    SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHH24mm");
    String fileName = format1.format(utilDate.getTime());
    int rownum = 0;
    Row row = sheet.createRow(rownum++);
    int cellnum = 0;
    Cell cell = row.createCell(cellnum++);
    cell.setCellValue("Voucher recharge code");
    cell = row.createCell(cellnum++);
    cell.setCellValue("Denomination");
    cell = row.createCell(cellnum++);
    cell.setCellValue("Type");
    cell = row.createCell(cellnum++);
    cell.setCellValue("Voucher serial");
    row = sheet.createRow(rownum++);
    cellnum = 0;
    cell = row.createCell(cellnum++);
    cell.setCellValue("رقم كارت الشحن");
    cell = row.createCell(cellnum++);
    cell.setCellValue("فئة الكارت");
    cell = row.createCell(cellnum++);
    cell.setCellValue("نوع الكارت");
    cell = row.createCell(cellnum++);
    cell.setCellValue("رقم السيريال");
    if (voucherList != null) {
        for (int i = 0; i < voucherList.size(); i++) {
            if (voucherList.get(i).getVoucherSerial() != null && !voucherList.get(i).getVoucherSerial().isEmpty()) {
                for (int j = 0; j < voucherList.get(i).getVoucherSerial().size(); j++) {
                    row = sheet.createRow(rownum++);
                    cellnum = 0;
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(voucherList.get(i).getVoucherPin().get(j));
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(voucherInfo.get(i).getDenom());
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(voucherInfo.get(i).getService_Name());
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(voucherList.get(i).getVoucherSerial().get(j));
                }
            }
        }
    }
    try {
        FileOutputStream outFile =
                new FileOutputStream(new File(fileName + ".xls"));
        workbook.write(outFile);
        outFile.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return fileName;
    }
}
