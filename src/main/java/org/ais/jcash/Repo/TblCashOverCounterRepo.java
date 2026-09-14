package org.ais.jcash.Repo;

import org.ais.jcash.model.TblCashOverCounter;
import org.ais.jcash.model.TblOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblCashOverCounterRepo extends JpaRepository<TblCashOverCounter, Long> {

    @Query(value = "SELECT C.CASH_OVER_COUNTER_ID, H.TRANS_HEAD_ID, H.COMPANY_ID, H.PRODUCT_ID ,H.BENEFICIARY_NAME, H.BENEFICIARY_ADDRESS, C.MOBILE_NO, DECODE(C.DOCUMENT_TYPE,'C','CNIC','D','DRIVING LICENSE','P','PASSPORT NO') DOCUMENT_TYPE,\n" +
            "C.DOCUMENT_NO, M.COMPANY_NAME REMITTER_NAME, H.TRANS_AMOUNT, TO_CHAR(H.TRANS_DATE,'DD-MON-YYYY') TRANS_DATE\n" +
            "FROM TBL_CASH_OVER_COUNTER C\n" +
            "INNER JOIN TBL_TRANS_HEAD H ON C.TRANS_HEAD_ID1 = H.TRANS_HEAD_ID\n" +
            "INNER JOIN TBL_COMPANY M ON H.COMPANY_ID = M.COMPANY_ID\n" +
            "WHERE C.XPIN = :xpin\n" +
            "AND C.STATUS = 'I'", nativeQuery = true)
    List<Object> getDataAgainstXpin(@Param("xpin") Long xpin);


}
