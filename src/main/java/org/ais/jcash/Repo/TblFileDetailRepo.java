package org.ais.jcash.Repo;

import org.ais.jcash.model.TblFileDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblFileDetailRepo extends JpaRepository<TblFileDetail , Long> {



    @Query(value = "SELECT H.FILE_HEAD_ID, H.PARSER_COMPANY_CONFIG_ID, H.REFERENCE01_TITLE,\n" +
            "       H.REFERENCE02_TITLE, H.REFERENCE03_TITLE, H.REFERENCE04_TITLE, H.REFERENCE05_TITLE, H.REFERENCE06_TITLE,\n" +
            "       H.REFERENCE07_TITLE, H.REFERENCE08_TITLE, H.REFERENCE09_TITLE, H.REFERENCE10_TITLE, H.REFERENCE11_TITLE,\n" +
            "       H.REFERENCE12_TITLE, H.REFERENCE13_TITLE, H.REFERENCE14_TITLE,\n" +
            "       D.FILE_DETAIL_ID, D.REFERENCE01, D.REFERENCE02, D.REFERENCE03, D.REFERENCE04, D.REFERENCE05, D.REFERENCE06, \n" +
            "       D.REFERENCE07, D.REFERENCE08, D.REFERENCE09, D.REFERENCE10, D.REFERENCE11, D.REFERENCE12, D.REFERENCE13, D.REFERENCE14\n" +
            "FROM TBL_FILE_HEAD H\n" +
            "INNER JOIN TBL_FILE_DETAIL D ON H.FILE_HEAD_ID = D.FILE_HEAD_ID\n" +
            "INNER JOIN TBL_PARSER_COMPANY_CONFIG C ON H.PARSER_COMPANY_CONFIG_ID = C.PARSER_COMPANY_CONFIG_ID\n" +
            "WHERE C.MC_STATUS = 'A'\n" +
            "AND D.INVOICE_NO = :invoiceNo\n" +
            "AND C.COMPANY_ID = :companyId\n" +
            "AND C.PRODUCT_ID = :productId \n" +
            "AND NVL(D.IS_DEPOSITED,'N') = 'N' ",nativeQuery = true)
    List<Object> getVoucherDetailsAgainstInvoice(@Param("invoiceNo")String invoiceNo,@Param("companyId")long companyId,@Param("productId")long productId);

}
