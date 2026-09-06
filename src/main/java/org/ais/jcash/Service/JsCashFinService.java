package org.ais.jcash.Service;

import org.ais.jcash.dto.*;
import org.ais.jcash.model.TblProductCollection;
import org.ais.jcash.model.TblTransHead;

import java.util.List;

public interface JsCashFinService {

//    TblTransHead branchOnlineDeposit(TblTransHead tblTransHead);

    BranchInvoiceInqResponse branchInvoiceInq(BranchInvoiceInqRequest branchInvoiceInqRequest);

    List<FetchLodgementResponse> fetchLodgementChq(FetchLodgeLiquidationRequest fetchLodgeLiquidationRequest, long branchId);

    List<FetchLodgementResponse> fetchLiquidationChq(FetchLodgeLiquidationRequest fetchLodgeLiquidationRequest, long branchId);

    ProcedureSubmitDocResponse callProcedureSubmitDoc(long transHeadId, long userId);

    TblProductCollection getCollectionAgainstProduct(long productId);


    List<BranchAuthPendingTransaction> getBranchPendingTransaction(long userId);


//    int takeActionOnLodgment(long transHeadId, String lodgementReason,String lodgementStatus,long loggedUserId);
//
//    int takeActionOnLiquidation(long transHeadId, String liquidationReason,String liquidationStatus,long loggedUserId);

    ProcedureSubmitDocResponse callProcedureBranchAuthorizeTransaction(BranchAuthorizeTransactionRequest branchAuthorizeTransactionRequest, long userId);

    List<CompanyPendingAuthTransaction> getCompanyPendingTransaction(long userId);

    List<ReviewTransactionResponse> reviewFinancialTransactions(ReviewTransactionRequest reviewTransactionRequest);

    ReviewTransactionDetailResponse reviewFinancialTransactionsDetails(ReviewTransactionDetailRequest reviewTransactionDetailRequest);

    //  List<ReviewTransactionRequest> reviewFinancialTransactions(ReviewTransactionRequest reviewTransactionRequest);
}
