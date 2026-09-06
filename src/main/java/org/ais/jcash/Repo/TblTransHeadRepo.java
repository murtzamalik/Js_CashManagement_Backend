package org.ais.jcash.Repo;

import org.ais.jcash.model.TblTransHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TblTransHeadRepo extends JpaRepository<TblTransHead, Long> {



//    @Query(value = "SELECT H.TRANS_HEAD_ID, H.COLLECTION_ACCOUNT_ID, O.COMPANY_NAME, B.BRANCH_NAME, K.BANK_NAME, H.DEPOSIT_SLIP_NO, D.CHEQUE_NO, D.CHEQUE_DATE, H.DEPOSIT_SLIP_AMOUNT\n" +
//            "FROM TBL_TRANS_HEAD H\n" +
//            "INNER JOIN TBL_PRODUCT P ON H.PRODUCT_ID = P.PRODUCT_ID\n" +
//            "INNER JOIN TBL_PRODUCT_COLLECTION C ON P.PRODUCT_COLLECTION_ID = C.PRODUCT_COLLECTION_ID\n" +
//            "INNER JOIN TBL_COMPANY O ON H.COMPANY_ID = O.COMPANY_ID\n" +
//            "LEFT OUTER JOIN TBL_DEPOSIT_DETAIL D ON H.TRANS_HEAD_ID = D.TRANS_HEAD_ID\n" +
//            "LEFT OUTER JOIN LKP_BRANCH B ON D.BRANCH_ID = B.BRANCH_ID\n" +
//            "LEFT OUTER JOIN LKP_BANK K ON B.BANK_ID = K.BANK_ID\n" +
//            "WHERE H.AUTH_STATUS = 'P'\n" +
//            "AND H.LODGEMENT_STATUS IS NULL\n" +
//            "AND H.LIQUIDATION_STATUS IS NULL\n" +
//            "AND H.DEPOSIT_SLIP_NO = nvl(:invoiceNo,H.DEPOSIT_SLIP_NO)\n" +
//            "AND H.DEPOSIT_SLIP_AMOUNT = nvl(:amount,H.DEPOSIT_SLIP_AMOUNT)\n" +
//            "AND NVL(D.BRANCH_ID,0) = nvl(:branchId,nvl(D.BRANCH_ID,0))\n" +
//            "AND NVL(D.CHEQUE_NO,0) = nvl(:chqNo,nvl(D.CHEQUE_NO,0))\n" +
//            "AND H.BRANCH_ID = :loggedUserBranchId\n" +
//            "AND C.PRODUCT_CODE = :productCollCode",nativeQuery = true)
//    List<Object> fetchLodgementChq(@Param("invoiceNo")String invoiceNo,@Param("amount") BigDecimal amount,
//                                   @Param("branchId")BigDecimal branchId,@Param("chqNo") String chqNo,
//                                   @Param("productCollCode")String productCollCode,@Param("loggedUserBranchId")BigDecimal loggedUserBranchId);
//
//
//
//    @Query(value = "SELECT H.TRANS_HEAD_ID, H.COLLECTION_ACCOUNT_ID, O.COMPANY_NAME, B.BRANCH_NAME, K.BANK_NAME, H.DEPOSIT_SLIP_NO, D.CHEQUE_NO, D.CHEQUE_DATE, H.DEPOSIT_SLIP_AMOUNT \n" +
//            "FROM TBL_TRANS_HEAD H\n" +
//            "INNER JOIN TBL_PRODUCT P ON H.PRODUCT_ID = P.PRODUCT_ID\n" +
//            "INNER JOIN TBL_PRODUCT_COLLECTION C ON P.PRODUCT_COLLECTION_ID = C.PRODUCT_COLLECTION_ID\n" +
//            "LEFT OUTER JOIN TBL_DEPOSIT_DETAIL D ON H.TRANS_HEAD_ID = D.TRANS_HEAD_ID\n" +
//            "WHERE --H.AUTH_STATUS = 'P'\n" +
//            " H.LODGEMENT_STATUS = 'A'\n" +
//            "AND H.LIQUIDATION_STATUS IS NULL\n" +
//            "AND H.DEPOSIT_SLIP_NO = NVL(:invoiceNo,H.DEPOSIT_SLIP_NO)\n" +
//            "AND H.DEPOSIT_SLIP_AMOUNT = NVL(:amount,H.DEPOSIT_SLIP_AMOUNT)\n" +
//            "AND NVL(D.BRANCH_ID,0) = NVL(:branchId,NVL(D.BRANCH_ID,0))\n" +
//            "AND NVL(D.CHEQUE_NO,0) = NVL(:chqNo,NVL(D.CHEQUE_NO,0))\n" +
//            "AND C.PRODUCT_CODE = :productCollCode ",nativeQuery = true)
//    List<Object> fetchLiquidationChq(@Param("invoiceNo")String invoiceNo,@Param("amount") BigDecimal amount,
//                                     @Param("branchId")long branchId,@Param("chqNo")String chqNo,
//                                     @Param("productCollCode")String productCollCode);

    @Query(value = "SELECT T.TRANS_HEAD_ID, D.AUTH_DETAIL_ID, P.MASTER_PRODUCT_NAME, C.COMPANY_NAME, T.DEPOSIT_DATE, T.DEPOSIT_SLIP_NO, T.DEPOSIT_SLIP_AMOUNT, D.AUTH_DATE, D.AUTH_COMMENTS\n" +
            "FROM TBL_TRANS_HEAD T\n" +
            "INNER JOIN TBL_PRODUCT P ON T.PRODUCT_ID = P.PRODUCT_ID\n" +
            "INNER JOIN TBL_COMPANY C ON T.COMPANY_ID = C.COMPANY_ID\n" +
            "INNER JOIN TBL_AUTH_HEAD H ON T.TRANS_HEAD_ID = H.TRANS_HEAD_ID\n" +
            "INNER JOIN TBL_AUTH_DETAIL D ON H.AUTH_HEAD_ID = D.AUTH_HEAD_ID\n" +
            "INNER JOIN TBL_USER U ON T.BRANCH_ID = U.BRANCH_ID\n" +
            "INNER JOIN TBL_USER_ROLE R ON U.USER_ID = R.USER_ID AND D.ROLE_ID = R.ROLE_ID\n" +
            "WHERE H.AUTH_STATUS = 'I' AND D.AUTH_STATUS = 'I' AND D.LASTUPDATEDATE IS NULL AND U.USER_ID = :userId",nativeQuery = true)
    List<Object> fetchAllPendingAuthTransactions(@Param("userId")long userId);


//    @Modifying
//    @Query("update TblTransHead j set " +
//            " j.lastupdatedate = :lastupdatedate, " +
//            " j.lastupdateuser = :lastupdateuser," +
//            "  j.updateindex = NVL(j.updateindex,0) + 1 ,  " +
//            " j.lodgementReason = :lodgementReason, "+
//            " j.lodgementStatus = :lodgementStatus, "+
//            " j.lodgementCdate = :lodgementCdate, " +
//            " j.lodgementTblUser.userId = :lastupdateuser " +
//            "  where j.transHeadId = :transHeadId")
//    int takeActionOnLodgment(@Param("transHeadId") Long transHeadId, @Param("lodgementReason") String lodgementReason, @Param("lodgementStatus") String lodgementStatus,
//                           @Param("lastupdatedate") Date lastupdatedate , @Param("lodgementCdate") Date lodgementCdate, @Param("lastupdateuser") BigDecimal lastupdateuser);

//    @Modifying
//    @Query("update TblTransHead j set " +
//            " j.lastupdatedate = :lastupdatedate, " +
//            " j.lastupdateuser = :lastupdateuser," +
//            "  j.updateindex = NVL(j.updateindex,0) + 1 ,  " +
//            " j.liquidationReason = :liquidationReason, "+
//            " j.liquidationStatus = :liquidationStatus, "+
//            " j.liquidationCdate = :liquidationCdate, " +
//            " j.liquidationTblUser.userId = :lastupdateuser " +
//            "  where j.transHeadId = :transHeadId")
//    int takeActionOnLiquidation(@Param("transHeadId") Long transHeadId, @Param("liquidationReason") String liquidationReason, @Param("liquidationStatus") String liquidationStatus,
//                             @Param("lastupdatedate") Date lastupdatedate , @Param("liquidationCdate") Date liquidationCdate, @Param("lastupdateuser") BigDecimal lastupdateuser);












    @Query(value = "SELECT T.TRANS_HEAD_ID, D.AUTH_DETAIL_ID, P.MASTER_PRODUCT_NAME, C.COMPANY_NAME, T.BENEFICIARY_NAME,T.TRANS_DATE, O.DOCUMENT_NO, T.TRANS_AMOUNT, D.AUTH_DATE, D.AUTH_COMMENTS\n" +
            "FROM TBL_TRANS_HEAD T\n" +
            "INNER JOIN TBL_PRODUCT P ON T.PRODUCT_ID = P.PRODUCT_ID\n" +
            "INNER JOIN TBL_COMPANY C ON T.COMPANY_ID = C.COMPANY_ID\n" +
            "INNER JOIN TBL_AUTH_HEAD H ON T.TRANS_HEAD_ID = H.TRANS_HEAD_ID\n" +
            "INNER JOIN TBL_AUTH_DETAIL D ON H.AUTH_HEAD_ID = D.AUTH_HEAD_ID\n" +
            "INNER JOIN TBL_USER U ON D.USER_ID = U.USER_ID\n" +
            "LEFT OUTER JOIN TBL_CASH_OVER_COUNTER O ON T.TRANS_HEAD_ID = O.TRANS_HEAD_ID1\n" +
            "WHERE H.AUTH_STATUS = 'I' AND D.AUTH_STATUS = 'I' AND D.LASTUPDATEDATE IS NULL AND U.USER_ID = :userId",nativeQuery = true)
    List<Object> fetchAllCompanyUserPendingAuthTransactions(long userId);







//    @Query(value = "SELECT T.TRANS_HEAD_ID, P.MASTER_PRODUCT_NAME, C.COMPANY_NAME, T.TRANS_DATE, T.TRANS_AMOUNT, U.USER_NAME CREATEUSER,\n" +
//            "DECODE(T.AUTH_STATUS,'S','NOT SUBMITTED','I','SUBMITTED FOR APPROVAL','A','APPROVED','R','REJECTED') AUTH_STATUS, T.AUTH_DATE, A.USER_NAME AUTH_USER, T.AUTH_COMMENTS\n" +
//            "FROM TBL_TRANS_HEAD T\n" +
//            "INNER JOIN TBL_PRODUCT P ON T.PRODUCT_ID = P.PRODUCT_ID\n" +
//            "INNER JOIN TBL_COMPANY C ON T.COMPANY_ID = C.COMPANY_ID\n" +
//            "INNER JOIN TBL_USER U ON T.CREATEUSER = U.USER_ID\n" +
//            "LEFT OUTER JOIN TBL_USER A ON T.AUTH_USER_ID = A.USER_ID\n" +
//            "WHERE T.COMPANY_ID = :companyId\n" +
//            "AND T.PRODUCT_ID = NVL(:productId,T.PRODUCT_ID)\n" +
//            "AND T.TRANS_DATE BETWEEN NVL(:fromDate,T.TRANS_DATE) AND NVL(:toDate,T.TRANS_DATE)\n" +
//            "AND T.AUTH_STATUS = NVL(:authStatus,T.AUTH_STATUS)",nativeQuery = true)
//    List<Object> reviewTransactions(@Param("companyId") long companyId,@Param("productId") Long productId,
//                                    @Param("fromDate") Date fromDate,@Param("toDate") Date toDate,
//                                    @Param("authStatus") String authStatus);


    @Query(value = "SELECT T.TRANS_HEAD_ID, P.MASTER_PRODUCT_NAME, C.COMPANY_NAME, T.TRANS_DATE, T.TRANS_AMOUNT, U.USER_NAME CREATEUSER,\n" +
            "DECODE(T.AUTH_STATUS,'S','NOT SUBMITTED','I','SUBMITTED FOR APPROVAL','A','APPROVED','R','REJECTED') AUTH_STATUS, T.AUTH_DATE, A.USER_NAME AUTH_USER, T.AUTH_COMMENTS\n" +
            "FROM TBL_TRANS_HEAD T\n" +
            "INNER JOIN TBL_PRODUCT P ON T.PRODUCT_ID = P.PRODUCT_ID\n" +
            "INNER JOIN TBL_COMPANY C ON T.COMPANY_ID = C.COMPANY_ID\n" +
            "INNER JOIN TBL_USER U ON T.CREATEUSER = U.USER_ID\n" +
            "LEFT OUTER JOIN TBL_USER A ON T.AUTH_USER_ID = A.USER_ID\n" +
            "WHERE T.COMPANY_ID = 111\n" +
            "AND T.PRODUCT_ID = NVL(NULL,T.PRODUCT_ID)\n" +
            "AND T.TRANS_DATE BETWEEN NVL(NULL,T.TRANS_DATE) AND NVL(NULL,T.TRANS_DATE)\n" +
            "AND T.AUTH_STATUS = NVL(NULL,T.AUTH_STATUS)",nativeQuery = true)
    List<Object> reviewTransactions();




    @Query(value = "SELECT T.DEPOSIT_DATE, T.DEPOSIT_SLIP_NO, T.DEPOSIT_SLIP_AMOUNT, T.NO_OF_CHEQUES, T.NO_OF_INVOICES, T.DEPOSITOR_CELL_NO, T.DEPOSITOR_EMAIL,\n" +
            "T.LODGEMENT_CDATE, T.LODGEMENT_STATUS, T.LODGEMENT_REASON, LD.USER_NAME LODGEMENT_USER,\n" +
            "T.LIQUIDATION_CDATE, T.LIQUIDATION_STATUS, T.LIQUIDATION_REASON, LQ.USER_NAME LIQUIDATION_USER,\n" +
            "B.BANK_NAME BENEFICIARY_BANK, T.BENEFICIARY_ACCOUNT_NO, T.BENEFICIARY_ACCOUNT_TITLE, T.BENEFICIARY_NAME, T.BENEFICIARY_ADDRESS, T.BENEFICIARY_EMAIL,\n" +
            "T.CUSTOMER_REFERENCE, P.DESCRIPTION PAYMENT_MODE, BR.BRANCH_NAME TRANS_BRANCH, AC.ACCOUNT_NO COLLECTION_ACCOUNT_NO, AD.ACCOUNT_NO DEBIT_ACCOUNT_NO\n" +
            "FROM TBL_TRANS_HEAD T\n" +
            "LEFT OUTER JOIN LKP_BANK B ON T.BENEFICIARY_BANK_ID = B.BANK_ID\n" +
            "LEFT OUTER JOIN LKP_PAYMENT_MODE P ON T.PAYMENT_MODE_ID = P.PAYMENT_MODE_ID\n" +
            "LEFT OUTER JOIN LKP_BRANCH BR ON T.BRANCH_ID = BR.BRANCH_ID\n" +
            "LEFT OUTER JOIN TBL_ACCOUNT AC ON T.COLLECTION_ACCOUNT_ID = AC.ACCOUNT_ID\n" +
            "LEFT OUTER JOIN TBL_ACCOUNT AD ON T.DR_ACCOUNT_ID = AC.ACCOUNT_ID\n" +
            "LEFT OUTER JOIN TBL_USER LD ON T.LODGEMENT_USER_ID = LD.USER_ID\n" +
            "LEFT OUTER JOIN TBL_USER LQ ON T.LIQUIDATION_USER_ID = LQ.USER_ID\n" +
            "WHERE T.TRANS_HEAD_ID = :transHeadId ",nativeQuery = true)
    List<Object> reviewTransactionsDetail(@Param("transHeadId") Long transHeadId);











//    @Query(value = "SELECT T.DEPOSIT_DATE, T.DEPOSIT_SLIP_NO, T.DEPOSIT_SLIP_AMOUNT, T.NO_OF_CHEQUES, T.NO_OF_INVOICES, T.DEPOSITOR_CELL_NO, T.DEPOSITOR_EMAIL,\n" +
//            "T.LODGEMENT_CDATE, T.LODGEMENT_STATUS, T.LODGEMENT_REASON, LD.USER_NAME LODGEMENT_USER,\n" +
//            "T.LIQUIDATION_CDATE, T.LIQUIDATION_STATUS, T.LIQUIDATION_REASON, LQ.USER_NAME LIQUIDATION_USER,\n" +
//            "B.BANK_NAME BENEFICIARY_BANK, T.BENEFICIARY_ACCOUNT_NO, T.BENEFICIARY_ACCOUNT_TITLE, T.BENEFICIARY_NAME, T.BENEFICIARY_ADDRESS, T.BENEFICIARY_EMAIL,\n" +
//            "T.CUSTOMER_REFERENCE, P.DESCRIPTION PAYMENT_MODE, BR.BRANCH_NAME TRANS_BRANCH, AC.ACCOUNT_NO COLLECTION_ACCOUNT_NO, AD.ACCOUNT_NO DEBIT_ACCOUNT_NO\n" +
//            "FROM TBL_TRANS_HEAD T\n" +
//            "LEFT OUTER JOIN LKP_BANK B ON T.BENEFICIARY_BANK_ID = B.BANK_ID\n" +
//            "LEFT OUTER JOIN LKP_PAYMENT_MODE P ON T.PAYMENT_MODE_ID = P.PAYMENT_MODE_ID\n" +
//            "LEFT OUTER JOIN LKP_BRANCH BR ON T.BRANCH_ID = BR.BRANCH_ID\n" +
//            "LEFT OUTER JOIN TBL_ACCOUNT AC ON T.COLLECTION_ACCOUNT_ID = AC.ACCOUNT_ID\n" +
//            "LEFT OUTER JOIN TBL_ACCOUNT AD ON T.DR_ACCOUNT_ID = AC.ACCOUNT_ID\n" +
//            "LEFT OUTER JOIN TBL_USER LD ON T.LODGEMENT_USER_ID = LD.USER_ID\n" +
//            "LEFT OUTER JOIN TBL_USER LQ ON T.LIQUIDATION_USER_ID = LQ.USER_ID\n" +
//            "WHERE T.TRANS_HEAD_ID = :transHeadId ",nativeQuery = true)
//    List<Object> reviewTransactionsDetail2(@Param("transHeadId") Long transHeadId);




    @Query(value = "SELECT R.ROLE_DESCR, U.USER_NAME, D.AUTH_STATUS, D.AUTH_COMMENTS, D.AUTH_DATE, D.REMARKS\n" +
            "FROM TBL_AUTH_HEAD H\n" +
            "INNER JOIN TBL_AUTH_DETAIL D ON H.AUTH_HEAD_ID = D.AUTH_HEAD_ID\n" +
            "LEFT OUTER JOIN TBL_ROLE R ON D.ROLE_ID = R.ROLE_ID\n" +
            "LEFT OUTER JOIN TBL_USER U ON D.USER_ID = U.USER_ID\n" +
            "WHERE H.TRANS_HEAD_ID = :transHeadId\n" +
            "ORDER BY D.AUTH_DETAIL_ID",nativeQuery = true)
    List<Object> reviewTransactionsDetail2(@Param("transHeadId") Long transHeadId);




}
