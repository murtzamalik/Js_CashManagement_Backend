package org.ais.jcash.Service.impl;

import org.ais.jcash.Repo.TblDepositDetailRepo;
import org.ais.jcash.Repo.TblFileDetailRepo;
import org.ais.jcash.Repo.TblProductCollectionRepo;
import org.ais.jcash.Repo.TblTransHeadRepo;
import org.ais.jcash.Service.JsCashFinService;
import org.ais.jcash.dto.*;
import org.ais.jcash.model.*;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/31/2022
 * Time: 10:48 AM
 * Project : jcash
 */


@Service
@Transactional(rollbackOn = Exception.class)
public class JsCashFinServiceImpl implements JsCashFinService {

    @PersistenceContext
    @Autowired
    EntityManager em;

    @Autowired
    private TblTransHeadRepo tblTransHeadRepo;

    @Autowired
    private TblDepositDetailRepo tblDepositDetailRepo;

    @Autowired
    private TblFileDetailRepo tblFileDetailRepo;
    @Autowired
    private TblProductCollectionRepo tblProductCollectionRepo;


//    @Override
//    public TblTransHead branchOnlineDeposit(TblTransHead tblTransHead) {
//
//        tblTransHead = tblTransHeadRepo.saveAndFlush(tblTransHead);
//
//        if (tblTransHead != null && tblTransHead.getTransHeadId() > 0) {
//
//            TblDepositDetail tblDepositDetail = null;
//
//            if (tblTransHead.getBranchOnlineDepositDetails() != null && tblTransHead.getBranchOnlineDepositDetails().size() > 0) {
//                for (BranchOnlineDepositDetail branchOnlineDepositDetail : tblTransHead.getBranchOnlineDepositDetails()) {
//
//                    tblDepositDetail = new TblDepositDetail();
//                    tblDepositDetail.setTblTransHead(tblTransHead);
//                    tblDepositDetail.setChequeAmount(branchOnlineDepositDetail.getChequeAmount());
//                    tblDepositDetail.setChequeDate(branchOnlineDepositDetail.getChequeDate());
//                    tblDepositDetail.setChequeNo(branchOnlineDepositDetail.getChequeNo());
//                    tblDepositDetail.setCreateuser(tblTransHead.getCreateuser());
//
//                    LkpBranch lkpBranch = new LkpBranch();
//                    lkpBranch.setBranchId(branchOnlineDepositDetail.getBranchId());
//
//                    tblDepositDetail.setLkpBranch(lkpBranch);
//
//                    tblDepositDetail = tblDepositDetailRepo.saveAndFlush(tblDepositDetail);
//
//                }
//
//            TblFileDetail tblFileDetail = tblFileDetailRepo.findById((tblTransHead.getFileDetailId().longValue())).orElse(null);
//            if (tblFileDetail != null) {
//                tblFileDetail.setIsDeposited("Y");
//                tblFileDetail.setLastupdatedate(new Date());
//                tblFileDetail.setLastupdateuser(tblTransHead.getCreateuser());
//                tblFileDetail.setUpdateindex(tblFileDetail.getUpdateindex() != null ? new BigDecimal(tblFileDetail.getUpdateindex().longValue() + 1) : new BigDecimal(1));
//
//                tblFileDetail = tblFileDetailRepo.saveAndFlush(tblFileDetail);
//            }
//            return tblTransHead;
//
//        } else {
//            return null;
//        }
//    }

    @Override
    public BranchInvoiceInqResponse branchInvoiceInq(BranchInvoiceInqRequest branchInvoiceInqRequest) {
        List<Object> rslt = tblFileDetailRepo.getVoucherDetailsAgainstInvoice(branchInvoiceInqRequest.getInvoiceNo(),
                Long.valueOf(branchInvoiceInqRequest.getCompanyId()), Long.valueOf(branchInvoiceInqRequest.getProductId()));
        if (rslt != null && rslt.size() > 0) {
            BranchInvoiceInqResponse branchInvoiceInqResponse = new BranchInvoiceInqResponse();
            List<String> data = new ArrayList<>();
            HashMap<String, String> resultmap = new HashMap<>();

            for (Object record : rslt) {
                Object[] row = (Object[]) record;
                for (int i = 2, j = 17; i <= 15; i++) {
                    if ((String) row[i] != null) {
                        resultmap.put((String) row[i], (String) row[j]);
                        data.add((String) row[j]);
                    }
                    j++;
                }

                branchInvoiceInqResponse.setFileDetailId(((BigDecimal) row[16]).longValue());

            }

            branchInvoiceInqResponse.setResultmap(resultmap);
            return branchInvoiceInqResponse;

        } else {
            return null;

        }

    }

    @Override
    public List<FetchLodgementResponse> fetchLodgementChq(FetchLodgeLiquidationRequest fetchLodgeLiquidationRequest, long branchId) {


        String sql = "SELECT H.TRANS_HEAD_ID, H.COLLECTION_ACCOUNT_ID, O.COMPANY_NAME, B.BRANCH_NAME, K.BANK_NAME, H.DEPOSIT_SLIP_NO, D.CHEQUE_NO, D.CHEQUE_DATE, H.DEPOSIT_SLIP_AMOUNT\n" +
                "FROM TBL_TRANS_HEAD H\n" +
                "INNER JOIN TBL_PRODUCT P ON H.PRODUCT_ID = P.PRODUCT_ID\n" +
                "INNER JOIN TBL_PRODUCT_COLLECTION C ON P.PRODUCT_COLLECTION_ID = C.PRODUCT_COLLECTION_ID\n" +
                "INNER JOIN TBL_COMPANY O ON H.COMPANY_ID = O.COMPANY_ID\n" +
                "LEFT OUTER JOIN TBL_DEPOSIT_DETAIL D ON H.TRANS_HEAD_ID = D.TRANS_HEAD_ID\n" +
                "LEFT OUTER JOIN LKP_BRANCH B ON D.BRANCH_ID = B.BRANCH_ID\n" +
                "LEFT OUTER JOIN LKP_BANK K ON B.BANK_ID = K.BANK_ID\n" +
                "WHERE H.AUTH_STATUS = 'A'\n" +
                "AND H.LODGEMENT_STATUS IS NULL\n" +
                "AND H.LIQUIDATION_STATUS IS NULL\n" +
                "AND H.DEPOSIT_SLIP_NO = NVL(" + fetchLodgeLiquidationRequest.getInvoiceNo() + ",H.DEPOSIT_SLIP_NO)\n" +
                "AND H.DEPOSIT_SLIP_AMOUNT = NVL(" + fetchLodgeLiquidationRequest.getAmount() + ",H.DEPOSIT_SLIP_AMOUNT)\n" +
                "AND NVL(D.BRANCH_ID,0) = nvl(" + fetchLodgeLiquidationRequest.getBranchId() + ",NVL(D.BRANCH_ID,0))\n" +
                "AND NVL(D.CHEQUE_NO,0) = nvl(" + fetchLodgeLiquidationRequest.getChqNo() + ",nvl(D.CHEQUE_NO,0))\n" +
                "AND H.BRANCH_ID = '" + branchId + "'" +
                " AND C.PRODUCT_CODE = '" + fetchLodgeLiquidationRequest.getProductCollCode() + "'";


        Query query = em.createNativeQuery(sql);

        List<Object> rslt = (List<Object>) query.getResultList();
//        List<Object> rslt = tblTransHeadRepo.fetchLodgementChq(fetchLodgeLiquidationRequest.getInvoiceNo(),fetchLodgeLiquidationRequest.getAmount(),
//        fetchLodgeLiquidationRequest.getBranchId(),fetchLodgeLiquidationRequest.getBranchId(),fetchLodgeLiquidationRequest.getProductCollCode(),branchId);


        if (rslt != null) {
            FetchLodgementResponse fetchLodgementResponse = null;
            List<FetchLodgementResponse> fetchLodgementResponses = new ArrayList<>();
            for (Object record : rslt) {
                Object[] row = (Object[]) record;
                fetchLodgementResponse = new FetchLodgementResponse();

                fetchLodgementResponse.setTransHeadId((BigDecimal) row[0]);
                fetchLodgementResponse.setCollectioAccountId((BigDecimal) row[1]);
                fetchLodgementResponse.setCompanyName((String) row[2]);
                fetchLodgementResponse.setBranchName((String) row[3]);
                fetchLodgementResponse.setBankName((String) row[4]);
                fetchLodgementResponse.setDepositSlipNo((String) row[5]);
                fetchLodgementResponse.setChqNo((String) row[6]);
                fetchLodgementResponse.setChqDate((Date) row[7]);
                fetchLodgementResponse.setDepositSlipAmount((BigDecimal) row[8]);

                fetchLodgementResponses.add(fetchLodgementResponse);

            }
            return fetchLodgementResponses;
        } else {
            return null;
        }
    }

    @Override
    public List<FetchLodgementResponse> fetchLiquidationChq(FetchLodgeLiquidationRequest fetchLodgeLiquidationRequest, long branchId) {

        String sql = "SELECT H.TRANS_HEAD_ID, H.COLLECTION_ACCOUNT_ID, O.COMPANY_NAME, B.BRANCH_NAME, K.BANK_NAME, H.DEPOSIT_SLIP_NO, D.CHEQUE_NO, D.CHEQUE_DATE, H.DEPOSIT_SLIP_AMOUNT\n" +
                "FROM TBL_TRANS_HEAD H\n" +
                "INNER JOIN TBL_PRODUCT P ON H.PRODUCT_ID = P.PRODUCT_ID\n" +
                "INNER JOIN TBL_PRODUCT_COLLECTION C ON P.PRODUCT_COLLECTION_ID = C.PRODUCT_COLLECTION_ID\n" +
                "INNER JOIN TBL_COMPANY O ON H.COMPANY_ID = O.COMPANY_ID\n" +
                "LEFT OUTER JOIN TBL_DEPOSIT_DETAIL D ON H.TRANS_HEAD_ID = D.TRANS_HEAD_ID\n" +
                "LEFT OUTER JOIN LKP_BRANCH B ON D.BRANCH_ID = B.BRANCH_ID\n" +
                "LEFT OUTER JOIN LKP_BANK K ON B.BANK_ID = K.BANK_ID\n" +
                "WHERE H.AUTH_STATUS = 'A'\n" +
                "AND H.LODGEMENT_STATUS = 'A'\n" +
                "AND H.LIQUIDATION_STATUS IS NULL\n" +
                "AND H.DEPOSIT_SLIP_NO = NVL(" + fetchLodgeLiquidationRequest.getAmount() + ",H.DEPOSIT_SLIP_NO)\n" +
                "AND H.DEPOSIT_SLIP_AMOUNT = NVL(" + fetchLodgeLiquidationRequest.getAmount() + ",H.DEPOSIT_SLIP_AMOUNT)\n" +
                "AND NVL(D.BRANCH_ID,0) = nvl(" + fetchLodgeLiquidationRequest.getBranchId() + ",NVL(D.BRANCH_ID,0))\n" +
                "AND NVL(D.CHEQUE_NO,0) = nvl(" + fetchLodgeLiquidationRequest.getAmount() + ",nvl(D.CHEQUE_NO,0))\n" +
                "AND H.BRANCH_ID = '" + branchId + "'" +
                " AND C.PRODUCT_CODE = '" + fetchLodgeLiquidationRequest.getProductCollCode() + "'";


        Query query = em.createNativeQuery(sql);
        List<Object> rslt = (List<Object>) query.getResultList();

        if (rslt != null) {
            FetchLodgementResponse fetchLodgementResponse = null;
            List<FetchLodgementResponse> fetchLodgementResponses = new ArrayList<>();
            for (Object record : rslt) {
                Object[] row = (Object[]) record;
                fetchLodgementResponse = new FetchLodgementResponse();

                fetchLodgementResponse.setTransHeadId((BigDecimal) row[0]);
                fetchLodgementResponse.setCollectioAccountId((BigDecimal) row[1]);
                fetchLodgementResponse.setCompanyName((String) row[2]);
                fetchLodgementResponse.setBranchName((String) row[3]);
                fetchLodgementResponse.setBankName((String) row[4]);
                fetchLodgementResponse.setDepositSlipNo((String) row[5]);
                fetchLodgementResponse.setChqNo((String) row[6]);
                fetchLodgementResponse.setChqDate((Date) row[7]);
                fetchLodgementResponse.setDepositSlipAmount((BigDecimal) row[8]);

                fetchLodgementResponses.add(fetchLodgementResponse);

            }
            return fetchLodgementResponses;
        } else {
            return null;
        }
    }

    @Override
    public ProcedureSubmitDocResponse callProcedureSubmitDoc(long transHeadId, long userId) {
//        PROCEDURE PROC_SUBMIT_DOC(P_TRANS_HEAD_ID IN NUMBER,
//                                P_USER_ID IN NUMBER,
//                                P_AUTH_COMPLETE OUT VARCHAR2,
//                                P_STATUS OUT NUMBER,
//                                P_STATUSDESCR OUT VARCHAR2);

        try {
            Session session = em.unwrap(Session.class);
            final int[] status = new int[1];
            final String[] statusDescr = new String[1];
            ProcedureSubmitDocResponse procedureSubmitDocResponse = new ProcedureSubmitDocResponse();
            session.doWork(new Work() {
                public void execute(Connection connection) throws SQLException {
                    CallableStatement call = connection.prepareCall("{call PKG_FIN_AUTH.PROC_SUBMIT_DOC(?,?,?,?,?) }");

                    call.setLong(1, transHeadId);
                    call.setLong(2, userId);
                    call.registerOutParameter(3, Types.VARCHAR);
                    call.registerOutParameter(4, Types.INTEGER);
                    call.registerOutParameter(5, Types.VARCHAR);

                    call.execute();
                    procedureSubmitDocResponse.setAuthComplete(call.getString(3));
                    procedureSubmitDocResponse.setStatus(call.getInt(4));
                    procedureSubmitDocResponse.setStatusDescr(call.getString(5));
                }
            });

            return procedureSubmitDocResponse;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public TblProductCollection getCollectionAgainstProduct(long productId) {
        return tblProductCollectionRepo.getCollectionAgainstProduct(productId);
    }

    @Override
    public List<BranchAuthPendingTransaction> getBranchPendingTransaction(long userId) {

        List<BranchAuthPendingTransaction> branchAuthPendingTransactions = new ArrayList<>();
        BranchAuthPendingTransaction branchAuthPendingTransaction = null;
        List<Object> rslt = tblTransHeadRepo.fetchAllPendingAuthTransactions(userId);
        if (rslt != null && rslt.size() > 0) {

            for (Object record : rslt) {
                Object[] row = (Object[]) record;
                branchAuthPendingTransaction = new BranchAuthPendingTransaction();
                branchAuthPendingTransaction.setTransHeadId((BigDecimal) row[0]);
                branchAuthPendingTransaction.setAuthDetailId((BigDecimal) row[1]);
                branchAuthPendingTransaction.setProductName((String) row[2]);
                branchAuthPendingTransaction.setCompanyName((String) row[3]);
                branchAuthPendingTransaction.setDepositDate((Date) row[4]);
                branchAuthPendingTransaction.setDepositSlipNo((String) row[5]);
                branchAuthPendingTransaction.setDepositAmount((BigDecimal) row[6]);
                branchAuthPendingTransaction.setAuthDate((Date) row[7]);
                branchAuthPendingTransaction.setAuthComents((String) row[8]);

                branchAuthPendingTransactions.add(branchAuthPendingTransaction);

            }

            return branchAuthPendingTransactions;
        } else {
            return null;

        }


    }

//    @Override
//    public int takeActionOnLodgment(long transHeadId, String lodgementReason, String lodgementStatus, long loggedUserId) {
//        return tblTransHeadRepo.takeActionOnLodgment(transHeadId, lodgementReason, lodgementStatus, new Date(), new Date(), BigDecimal.valueOf(loggedUserId));
//    }
//
//    @Override
//    public int takeActionOnLiquidation(long transHeadId, String liquidationReason, String liquidationStatus, long loggedUserId) {
//        return tblTransHeadRepo.takeActionOnLiquidation(transHeadId, liquidationReason, liquidationStatus, new Date(), new Date(), BigDecimal.valueOf(loggedUserId));
//    }

    @Override
    public ProcedureSubmitDocResponse callProcedureBranchAuthorizeTransaction(BranchAuthorizeTransactionRequest branchAuthorizeTransactionRequest, long userId) {

//        PROCEDURE PROC_AUTHORIZE_DOC(P_TRANS_HEAD_ID IN NUMBER,
//                                     P_AUTH_DETAIL_ID IN NUMBER,
//                                     P_USER_ID IN NUMBER,
//                                     P_ACTION IN VARCHAR2,
//                                     P_AUTH_COMPLETE OUT VARCHAR2,
//                                     P_STATUS OUT NUMBER,
//                                     P_STATUSDESCR OUT VARCHAR2);
        try {
            Session session = em.unwrap(Session.class);
            final int[] status = new int[1];
            final String[] statusDescr = new String[1];
            ProcedureSubmitDocResponse procedureSubmitDocResponse = new ProcedureSubmitDocResponse();
            session.doWork(new Work() {
                public void execute(Connection connection) throws SQLException {
                    CallableStatement call = connection.prepareCall("{call PKG_FIN_AUTH.PROC_AUTHORIZE_DOC(?,?,?,?,?,?,?) }");

                    call.setLong(1, branchAuthorizeTransactionRequest.getTransHeadId());
                    call.setLong(2, branchAuthorizeTransactionRequest.getAuthDetailId());
                    call.setLong(3, userId);
                    call.setString(4, branchAuthorizeTransactionRequest.getStatus());
                    call.registerOutParameter(5, Types.VARCHAR);
                    call.registerOutParameter(6, Types.INTEGER);
                    call.registerOutParameter(7, Types.VARCHAR);

                    call.execute();
                    procedureSubmitDocResponse.setAuthComplete(call.getString(5));
                    procedureSubmitDocResponse.setStatus(call.getInt(6));
                    procedureSubmitDocResponse.setStatusDescr(call.getString(7));
                }
            });

            return procedureSubmitDocResponse;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<CompanyPendingAuthTransaction> getCompanyPendingTransaction(long userId) {
        List<CompanyPendingAuthTransaction> companyPendingAuthTransactions = new ArrayList<>();
        CompanyPendingAuthTransaction companyPendingAuthTransaction = null;
        List<Object> rslt = tblTransHeadRepo.fetchAllCompanyUserPendingAuthTransactions(userId);
        if (rslt != null && rslt.size() > 0) {

            for (Object record : rslt) {
                Object[] row = (Object[]) record;
                companyPendingAuthTransaction = new CompanyPendingAuthTransaction();
                companyPendingAuthTransaction.setTransHeadId((BigDecimal) row[0]);
                companyPendingAuthTransaction.setAuthDetailId((BigDecimal) row[1]);
                companyPendingAuthTransaction.setMasterProductName((String) row[2]);
                companyPendingAuthTransaction.setCompanyName((String) row[3]);
                companyPendingAuthTransaction.setBenName((String) row[4]);
                companyPendingAuthTransaction.setTransDate((Date) row[5]);
                companyPendingAuthTransaction.setDocNo((String) row[6]);
                companyPendingAuthTransaction.setTransAmount((BigDecimal) row[7]);
                companyPendingAuthTransaction.setAuthDate((Date) row[8]);
                companyPendingAuthTransaction.setAuthComments((String) row[9]);

                companyPendingAuthTransactions.add(companyPendingAuthTransaction);

            }

            return companyPendingAuthTransactions;
        } else {
            return null;

        }
    }


    @Override
    public List<ReviewTransactionResponse> reviewFinancialTransactions(ReviewTransactionRequest reviewTransactionRequest) {
        List<ReviewTransactionResponse> reviewTransactionResponses = new ArrayList<>();
        ReviewTransactionResponse reviewTransactionResponse = null;
        List<Object> rslt = tblTransHeadRepo.reviewTransactions();
        if (rslt != null && rslt.size() > 0) {

            for (Object record : rslt) {
                Object[] row = (Object[]) record;
                reviewTransactionResponse = new ReviewTransactionResponse();
                reviewTransactionResponse.setTransHeadId((BigDecimal) row[0]);
                reviewTransactionResponse.setMasterProductName((String) row[1]);
                reviewTransactionResponse.setCompanyName((String) row[2]);
                reviewTransactionResponse.setTransDate((Date) row[3]);
                reviewTransactionResponse.setTransAmount((BigDecimal) row[4]);
                reviewTransactionResponse.setCreateUser((String) row[5]);
                reviewTransactionResponse.setAuthStatus((String) row[6]);
                reviewTransactionResponse.setAuthDate((Date) row[7]);
                reviewTransactionResponse.setAuthUser((String) row[8]);
                reviewTransactionResponse.setAuthComments((String) row[9]);

                reviewTransactionResponses.add(reviewTransactionResponse);

            }

            return reviewTransactionResponses;

        } else {
            return null;

        }
    }

    @Override
    public ReviewTransactionDetailResponse reviewFinancialTransactionsDetails(ReviewTransactionDetailRequest reviewTransactionDetailRequest) {

        ReviewTransactionDetailResponse reviewTransactionDetailResponse = null;
        List<Object> rslt = tblTransHeadRepo.reviewTransactionsDetail(reviewTransactionDetailRequest.getTransHeadId().longValue());
        if (rslt != null && rslt.size() > 0) {

            for (Object record : rslt) {
                Object[] row = (Object[]) record;
                reviewTransactionDetailResponse = new ReviewTransactionDetailResponse();
                reviewTransactionDetailResponse.setDepositDate((Date) row[0]);
                reviewTransactionDetailResponse.setDepositSlipNo((String) row[1]);
                reviewTransactionDetailResponse.setDepositSlipAmount((String) row[2]);
                reviewTransactionDetailResponse.setNoOfCheques((BigDecimal) row[3]);
                reviewTransactionDetailResponse.setNoOfInvoices((BigDecimal) row[4]);
                reviewTransactionDetailResponse.setDepositorCellNo((String) row[5]);
                reviewTransactionDetailResponse.setDepositorEmail((String) row[6]);
                reviewTransactionDetailResponse.setLodgementCdate((Date) row[7]);
                reviewTransactionDetailResponse.setLodgementStatus((String) row[8]);
                reviewTransactionDetailResponse.setLodgementReason((String) row[9]);
                reviewTransactionDetailResponse.setLdUserName((String) row[10]);
                reviewTransactionDetailResponse.setLiquidationDate((Date) row[11]);
                reviewTransactionDetailResponse.setLiquidationStatus((String) row[12]);
                reviewTransactionDetailResponse.setLiquidationReason((String) row[13]);
                reviewTransactionDetailResponse.setBankId((BigDecimal) row[14]);
                reviewTransactionDetailResponse.setBankId((BigDecimal) row[15]);
                reviewTransactionDetailResponse.setBeneficiaryAccountNumber((String) row[16]);
                reviewTransactionDetailResponse.setBeneficiaryAccountTitle((String) row[17]);
                reviewTransactionDetailResponse.setBeneficiaryName((String) row[18]);
                reviewTransactionDetailResponse.setBeneficiaryAddress((String) row[19]);
                reviewTransactionDetailResponse.setBeneficiaryEmail((String) row[20]);
                reviewTransactionDetailResponse.setCustomerReferenceNumber((String) row[21]);
                reviewTransactionDetailResponse.setPaymentModeId((BigDecimal) row[22]);
                reviewTransactionDetailResponse.setBranchId((BigDecimal) row[23]);
                reviewTransactionDetailResponse.setCollectionAccountNumber((String) row[24]);
                reviewTransactionDetailResponse.setDebitAccountNumber((String) row[25]);

            }

            List<ReviewTransactionDetailRequest2> reviewTransactionDetailRequests2 = new ArrayList<>();
            ReviewTransactionDetailRequest2 reviewTransactionDetailRequest2 = null;
            List<Object> rslt2 = tblTransHeadRepo.reviewTransactionsDetail2(reviewTransactionDetailRequest.getTransHeadId().longValue());
            if (rslt2 != null && rslt2.size() > 0) {
                for (Object record1 : rslt2) {
                    Object[] row1 = (Object[]) record1;
                    reviewTransactionDetailRequest2 = new ReviewTransactionDetailRequest2();
                    reviewTransactionDetailRequest2.setRoleDescription((String) row1[0]);
                    reviewTransactionDetailRequest2.setUserName((String) row1[1]);
                    reviewTransactionDetailRequest2.setAuthStatus((String) row1[2]);
                    reviewTransactionDetailRequest2.setAuthComments((String) row1[3]);
                    reviewTransactionDetailRequest2.setAuthDate((Date) row1[4]);
                    reviewTransactionDetailRequest2.setRemarks((String) row1[5]);

                    reviewTransactionDetailRequests2.add(reviewTransactionDetailRequest2);
                }
            }
            reviewTransactionDetailResponse.setAuthDetails(reviewTransactionDetailRequests2);

            return reviewTransactionDetailResponse;
        } else {
            return null;

        }


    }
}
