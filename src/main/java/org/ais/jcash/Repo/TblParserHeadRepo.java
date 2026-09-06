package org.ais.jcash.Repo;

import org.ais.jcash.model.TblModule;
import org.ais.jcash.model.TblParserCompanyConfig;
import org.ais.jcash.model.TblParserDetail;
import org.ais.jcash.model.TblParserHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblParserHeadRepo extends JpaRepository<TblParserHead , Long> {

    @Query(value = "select  md.*\n" +
            "from TBL_PARSER_COMPANY_CONFIG m,TBL_PARSER_HEAD md " +
            "where m.parser_head_id = md.parser_head_id\n"+
            "and m.COMPANY_ID = :companyId " +
            "and md.PRODUCT_ID = :productId " , nativeQuery = true)
    TblParserHead getCompanyProductParser(@Param("companyId") Long companyId , @Param("productId") Long productId);

    @Query(value = "SELECT H.*\n" +
            "FROM TBL_PARSER_HEAD H\n" +
            "INNER JOIN TBL_COMPANY_PRODUCT C ON H.PRODUCT_ID = C.PRODUCT_ID\n" +
            "WHERE C.COMPANY_ID = :companyId " , nativeQuery = true)
    List<TblParserHead> lovCompanyParserLink(@Param("companyId") Long companyId);

}
