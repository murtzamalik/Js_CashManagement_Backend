package org.ais.jcash.ExcelHelpers;

import org.ais.jcash.model.TblFileDetail;
import org.ais.jcash.model.TblFileHead;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelFileReaderTblParserHead {

    // Checks if the file type is of Excel of Not
    public boolean checkExcelFormat(MultipartFile myFile) {
        String contentType = myFile.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
    }


    public HashMap<String, List<String>> readDataFromExcelFile(InputStream myInputStream) {

        // List to store the data from the Excel File

        // this object is used to get the names of the Data-Members from the Entity Class


        // this array is used to store the column names that we are reading from Excel File
        List<String> columnNames = new ArrayList<>();
        List<String> columnType = new ArrayList<>();

        try {
            XSSFWorkbook myWorkbook = new XSSFWorkbook(myInputStream);
            // Referencing the Sheet inside the excel file using it's Name
            XSSFSheet mySheet = myWorkbook.getSheetAt(0);

            int rowNumber = 0;

            Iterator<Row> rows = mySheet.iterator();

            // Row Loop ==> Will Go on till the last Row
            while (rows.hasNext()) {
                Row myRow = rows.next();

                if (rowNumber == 0) {
                    // If rowNumber == 0 it means that we are on the First line that in most case is the Header of the Columns
                    Iterator<Cell> cells = myRow.iterator();
                    while (cells.hasNext()) {
                        Cell myCell = cells.next();
                        // Reading Column Headers / Column Names from 1st Row of Excel File
                        columnNames.add(myCell.getStringCellValue());
                        columnType.add(myCell.getCellType().name());

                    }
                    rowNumber++;
                    break;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        // Reading is Complete. Now returning the complete List with all the data.
        if (columnNames.size() > 0 && columnType.size() > 0) {
            HashMap<String, List<String>> map = new HashMap<>();

            map.put("columnName", columnNames);
            map.put("columnType", columnType);

            return map;
        } else {
            return null;
        }
    }


    public List<TblFileDetail> readColumnDataFromExcel(TblFileHead tblFileHead, InputStream myInputStream, int searchColumnIndex) {
        try {
            XSSFWorkbook myWorkbook = new XSSFWorkbook(myInputStream);
            // Referencing the Sheet inside the excel file using it's Name
            XSSFSheet mySheet = myWorkbook.getSheetAt(0);

            List<TblFileDetail> tblFileDetails = new ArrayList<>();

            for (int index = 1; index < mySheet.getPhysicalNumberOfRows(); index++) {

                XSSFRow row = mySheet.getRow(index);
                TblFileDetail tblFileDetail = new TblFileDetail();
                int totalCell = row.getLastCellNum();

                tblFileDetail.setReference01(getCellValue(row, 0));
                tblFileDetail.setReference02(getCellValue(row, 1));
                tblFileDetail.setReference03(getCellValue(row, 2));
                tblFileDetail.setReference04(getCellValue(row, 3));
                tblFileDetail.setReference05(getCellValue(row, 4));
                tblFileDetail.setReference06(getCellValue(row, 5));
                tblFileDetail.setReference07(getCellValue(row, 6));
                tblFileDetail.setReference08(getCellValue(row, 7));
                tblFileDetail.setReference09(getCellValue(row, 8));
                tblFileDetail.setReference10(getCellValue(row, 9));
                tblFileDetail.setReference11(getCellValue(row, 10));
                tblFileDetail.setReference12(getCellValue(row, 11));
                tblFileDetail.setReference13(getCellValue(row, 12));
                tblFileDetail.setReference14(getCellValue(row, 13));
                tblFileDetail.setInvoiceNo(getCellValue(row,searchColumnIndex));

                tblFileDetail.setTblFileHead(tblFileHead);
                tblFileDetail.setCreateuser(tblFileHead.getCreateuser());



                tblFileDetails.add(tblFileDetail);

            }

            return tblFileDetails;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private int convertStringToInt(String str) {
        int result = 0;
        if (str == null || str.isEmpty() || str.trim().isEmpty()) {
            return result;
        }
        result = Integer.parseInt(str);
        return result;
    }

    private String getCellValue(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();
        Cell cell = row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

}
