package org.ais.jcash.WsdlT24Api.service.impl;


import org.ais.jcash.WsdlT24Api.dto.*;
import org.ais.jcash.WsdlT24Api.model.*;
import org.ais.jcash.dto.*;
import org.ais.jcash.model.*;

import java.util.List;

public interface WsdlT24Service {

    InternalFundsTransferTitleFetchResponse IftTitleFetch(String accountNumber);
    IBFTTitleFetchResponse IbftTitleFetch(String fromAccount,String toAccount,String toBankIMD,String amount);
    IBFTTitleFetchResponse IbftPayment(String fromAccount, String toAccount, String toBankIMD, String amount);
    BalanceInquiryResponse balanceinquiry(String accountNumber);
    UtilityBillInquiryResponse utilitybillinquiry(UtltyBillInquiry utltyBillInquiry);
    InternalFundsTransferResponse internalFundsTranfer(String fromAccount,String toAccount, String amount );
}
