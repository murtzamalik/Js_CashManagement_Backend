package org.ais.jcash.controller;


import io.swagger.annotations.Api;
import org.ais.jcash.ExcelHelpers.ExcelFileReaderTblParserHead;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.dto.CompanyProductParserRequest;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.model.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:01 PM
 * Project : jcash
 */


@Api(value = "JS Cash Management File Upload Api ", description = "POST FILE UPLOAD API For JS Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/customerFileUpload")
public class CustomerFileUploadApi extends AbstractApi {


    @Autowired
    private ExcelFileReaderTblParserHead excelFileReaderTblParserHead;

    @Autowired
    private JsCashNonFinService jsCashNonFinService;


    @RequestMapping(value = "/productWiseBulkUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> readExcelFileTblParserHead(@RequestBody CompanyProductParserRequest companyProductParserRequest, HttpServletRequest request) throws IOException {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCustomerFileUploadApi \n METHOD == productWiseBulkUpload(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblParserDetail> tblParserDetail = jsCashNonFinService.getcompanyproductparser(loggedUserDetail.getCompanyId(), companyProductParserRequest.getProductId());
                if (tblParserDetail != null) {

                    int searchColumnIndex = 0;
                    InputStream myInputStream = new ByteArrayInputStream(Base64.decodeBase64(companyProductParserRequest.getFileBase64()));
                    HashMap<String, List<String>> excelColumns = excelFileReaderTblParserHead.readDataFromExcelFile(myInputStream);
                    if (excelColumns != null && excelColumns.size() > 0) {
                        List<String> authenticColumnNames = new ArrayList<>();
                        List<String> authenticColumnType = new ArrayList<>();
                        List<String> columnNames = excelColumns.get("columnName");
                        List<String> columnType = excelColumns.get("columnType");

                        if (tblParserDetail.size() == columnNames.size()) {
                            int size = tblParserDetail.size();
                            for (int i = 0; i < size; i++) {

//                                boolean isJohnAlive = tblParserDetail.stream().anyMatch(o -> columnNames.contains(o.getColumnName()));

                                for (int j = 0; j < size; j++) {

                                    if (tblParserDetail.get(i).getColumnName().equalsIgnoreCase(columnNames.get(j)) &&
                                            tblParserDetail.get(i).getColumnType().equalsIgnoreCase(columnType.get(j))) {

                                        authenticColumnNames.add(columnNames.get(i));
                                        authenticColumnType.add(columnType.get(i));
                                        if (tblParserDetail.get(i).getIsSearchable().equalsIgnoreCase("Y")) {
                                            searchColumnIndex = j;
                                        }
                                    }

                                }

                            }

                            if (authenticColumnNames.size() != columnNames.size()) {
                                HashMap<String, List<String>> errorMap = new HashMap<>();
                                errorMap.put("ColumnName", authenticColumnNames);
                                errorMap.put("Columntype", authenticColumnType);
                                LOG.info("\n EXITING THIS METHOD == saveModule(); OF CLASS = JsCashCustomerFileUploadApiApi \n\n\n");
                                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Parser Configuration Error Column Name Or Column Type Mis-Match", errorMap);

                            }

                            TblFileHead tblFileHead = new TblFileHead();

                            tblFileHead.setFileName(companyProductParserRequest.getFileName());
                            tblFileHead.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));


                            tblFileHead = setColumnTitleParserDetailSizeWise(columnNames, tblFileHead);


                            TblCompany tblCompany = new TblCompany();
                            TblParserHead tblParserHead = new TblParserHead();
                            List<TblParserCompanyConfig> tblParserCompanyConfig = jsCashNonFinService.getParserCompanyConfigForUpload(loggedUserDetail.getCompanyId(), companyProductParserRequest.getProductId(),companyProductParserRequest.getParserId());

                            tblCompany.setCompanyId(loggedUserDetail.getCompanyId());
                            tblParserHead.setParserHeadId(companyProductParserRequest.getParserId());

                            tblFileHead.setTblParserCompanyConfig(tblParserCompanyConfig.get(0));

                            InputStream myInputStream2 = new ByteArrayInputStream(Base64.decodeBase64(companyProductParserRequest.getFileBase64()));
                            List<TblFileDetail> tblFileDetails = excelFileReaderTblParserHead.readColumnDataFromExcel(tblFileHead, myInputStream2, searchColumnIndex);
                            if (tblFileDetails != null && tblFileDetails.size() > 0) {


                                tblFileHead = jsCashNonFinService.saveExcelInToDb(tblFileHead);
//                                tblFileHead.setFileDetails(tblFileDetails);
//                                List<TblFileDetail> saveTblFileDetails = jsCashNonFinService.saveDetailExcelInToDb(tblFileHead);


                                if (tblFileHead != null && tblFileHead.getFileHeadId() > 0) {
                                    LOG.info("\n EXITING THIS METHOD == productWiseBulkUpload(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                                    return getResponseFormat(HttpStatus.OK, "Excel Uploaded Successfully.", tblFileHead);
                                } else {
                                    LOG.info("\n EXITING THIS METHOD == productWiseBulkUpload(); OF CLASS = JsCashCustomerFileUploadApiApi \n\n\n");
                                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Saving Data From Excel. Please Retry", "Error While Saving Data From Excel. Please Retry");

                                }

                            } else {
                                LOG.info("\n EXITING THIS METHOD == productWiseBulkUpload(); OF CLASS = JsCashCustomerFileUploadApiApi \n\n\n");
                                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Extracting Data From Excel .Please Retry.", "Error While Extracting Data From Excel .Please Retry.");

                            }

                        } else {
                            LOG.info("\n EXITING THIS METHOD == productWiseBulkUpload(); OF CLASS = JsCashCustomerFileUploadApiApi \n\n\n");
                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Parser Configuration and File Upload Columns Are Different..!!", "No Parser Found Against Company");
                        }
                    } else {
                        LOG.info("\n EXITING THIS METHOD == productWiseBulkUpload(); OF CLASS = JsCashCustomerFileUploadApiApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Reading Excel", "Error While Reading Excel");
                    }
                } else {
                    LOG.info("\n EXITING THIS METHOD == productWiseBulkUpload(); OF CLASS = JsCashCustomerFileUploadApiApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Parser Found Against Company", "No Parser Found Against Company");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == productWiseBulkUpload(); OF CLASS = JsCashCustomerFileUploadApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCustomerFileUploadApi \n METHOD == productWiseBulkUpload();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == productWiseBulkUpload(); OF CLASS = JsCashCustomerFileUploadApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }
    }

    private TblFileHead setColumnTitleParserDetailSizeWise(List<String> columnNames, TblFileHead tblFileHead) {
        if (columnNames.size() == 1) {
            tblFileHead.setReference01Title(columnNames.get(0));
        } else if (columnNames.size() == 2) {
            tblFileHead.setReference01Title(columnNames.get(0));
            tblFileHead.setReference02Title(columnNames.get(1));
        } else if (columnNames.size() == 3) {
            tblFileHead.setReference01Title(columnNames.get(0));
            tblFileHead.setReference02Title(columnNames.get(1));
            tblFileHead.setReference03Title(columnNames.get(2));
        } else if (columnNames.size() == 4) {
            tblFileHead.setReference01Title(columnNames.get(0));
            tblFileHead.setReference02Title(columnNames.get(1));
            tblFileHead.setReference03Title(columnNames.get(2));
            tblFileHead.setReference04Title(columnNames.get(3));
        } else if (columnNames.size() == 5) {
            tblFileHead.setReference01Title(columnNames.get(0));
            tblFileHead.setReference02Title(columnNames.get(1));
            tblFileHead.setReference03Title(columnNames.get(2));
            tblFileHead.setReference04Title(columnNames.get(3));
            tblFileHead.setReference05Title(columnNames.get(4));
        } else if (columnNames.size() == 6) {
            tblFileHead.setReference01Title(columnNames.get(0));
            tblFileHead.setReference02Title(columnNames.get(1));
            tblFileHead.setReference03Title(columnNames.get(2));
            tblFileHead.setReference04Title(columnNames.get(3));
            tblFileHead.setReference05Title(columnNames.get(4));
            tblFileHead.setReference06Title(columnNames.get(5));
        } else if (columnNames.size() == 7) {
            tblFileHead.setReference01Title(columnNames.get(0));
            tblFileHead.setReference02Title(columnNames.get(1));
            tblFileHead.setReference03Title(columnNames.get(2));
            tblFileHead.setReference04Title(columnNames.get(3));
            tblFileHead.setReference05Title(columnNames.get(4));
            tblFileHead.setReference06Title(columnNames.get(5));
            tblFileHead.setReference07Title(columnNames.get(6));
        } else if (columnNames.size() == 8) {
            tblFileHead.setReference01Title(columnNames.get(0));
            tblFileHead.setReference02Title(columnNames.get(1));
            tblFileHead.setReference03Title(columnNames.get(2));
            tblFileHead.setReference04Title(columnNames.get(3));
            tblFileHead.setReference05Title(columnNames.get(4));
            tblFileHead.setReference06Title(columnNames.get(5));
            tblFileHead.setReference07Title(columnNames.get(6));
            tblFileHead.setReference08Title(columnNames.get(7));
        } else if (columnNames.size() == 9) {
            tblFileHead.setReference01Title(columnNames.get(0));
            tblFileHead.setReference02Title(columnNames.get(1));
            tblFileHead.setReference03Title(columnNames.get(2));
            tblFileHead.setReference04Title(columnNames.get(3));
            tblFileHead.setReference05Title(columnNames.get(4));
            tblFileHead.setReference06Title(columnNames.get(5));
            tblFileHead.setReference07Title(columnNames.get(6));
            tblFileHead.setReference08Title(columnNames.get(7));
            tblFileHead.setReference09Title(columnNames.get(8));
        } else if (columnNames.size() == 10) {
            tblFileHead.setReference01Title(columnNames.get(0));
            tblFileHead.setReference02Title(columnNames.get(1));
            tblFileHead.setReference03Title(columnNames.get(2));
            tblFileHead.setReference04Title(columnNames.get(3));
            tblFileHead.setReference05Title(columnNames.get(4));
            tblFileHead.setReference06Title(columnNames.get(5));
            tblFileHead.setReference07Title(columnNames.get(6));
            tblFileHead.setReference08Title(columnNames.get(7));
            tblFileHead.setReference09Title(columnNames.get(8));
            tblFileHead.setReference10Title(columnNames.get(9));
        } else if (columnNames.size() == 11) {
            tblFileHead.setReference01Title(columnNames.get(0));
            tblFileHead.setReference02Title(columnNames.get(1));
            tblFileHead.setReference03Title(columnNames.get(2));
            tblFileHead.setReference04Title(columnNames.get(3));
            tblFileHead.setReference05Title(columnNames.get(4));
            tblFileHead.setReference06Title(columnNames.get(5));
            tblFileHead.setReference07Title(columnNames.get(6));
            tblFileHead.setReference08Title(columnNames.get(7));
            tblFileHead.setReference09Title(columnNames.get(8));
            tblFileHead.setReference10Title(columnNames.get(9));
            tblFileHead.setReference11Title(columnNames.get(10));
        } else if (columnNames.size() == 12) {
            tblFileHead.setReference01Title(columnNames.get(0));
            tblFileHead.setReference02Title(columnNames.get(1));
            tblFileHead.setReference03Title(columnNames.get(2));
            tblFileHead.setReference04Title(columnNames.get(3));
            tblFileHead.setReference05Title(columnNames.get(4));
            tblFileHead.setReference06Title(columnNames.get(5));
            tblFileHead.setReference07Title(columnNames.get(6));
            tblFileHead.setReference08Title(columnNames.get(7));
            tblFileHead.setReference09Title(columnNames.get(8));
            tblFileHead.setReference10Title(columnNames.get(9));
            tblFileHead.setReference11Title(columnNames.get(10));
            tblFileHead.setReference12Title(columnNames.get(11));
        } else if (columnNames.size() == 13) {
            tblFileHead.setReference01Title(columnNames.get(0));
            tblFileHead.setReference02Title(columnNames.get(1));
            tblFileHead.setReference03Title(columnNames.get(2));
            tblFileHead.setReference04Title(columnNames.get(3));
            tblFileHead.setReference05Title(columnNames.get(4));
            tblFileHead.setReference06Title(columnNames.get(5));
            tblFileHead.setReference07Title(columnNames.get(6));
            tblFileHead.setReference08Title(columnNames.get(7));
            tblFileHead.setReference09Title(columnNames.get(8));
            tblFileHead.setReference10Title(columnNames.get(9));
            tblFileHead.setReference11Title(columnNames.get(10));
            tblFileHead.setReference12Title(columnNames.get(11));
            tblFileHead.setReference13Title(columnNames.get(12));
        } else if (columnNames.size() == 14) {
            tblFileHead.setReference01Title(columnNames.get(0));
            tblFileHead.setReference02Title(columnNames.get(1));
            tblFileHead.setReference03Title(columnNames.get(2));
            tblFileHead.setReference04Title(columnNames.get(3));
            tblFileHead.setReference05Title(columnNames.get(4));
            tblFileHead.setReference06Title(columnNames.get(5));
            tblFileHead.setReference07Title(columnNames.get(6));
            tblFileHead.setReference08Title(columnNames.get(7));
            tblFileHead.setReference09Title(columnNames.get(8));
            tblFileHead.setReference10Title(columnNames.get(9));
            tblFileHead.setReference11Title(columnNames.get(10));
            tblFileHead.setReference12Title(columnNames.get(11));
            tblFileHead.setReference13Title(columnNames.get(12));
            tblFileHead.setReference14Title(columnNames.get(13));
        } else {
            return null;
        }
        return tblFileHead;
    }


}