package org.ais.jcash.WsdlT24Api.service;

import org.ais.jcash.WsdlT24Api.model.IBFTTitleFetchResponse;
import org.ais.jcash.WsdlT24Api.model.InternalFundsTransferResponse;
import org.ais.jcash.WsdlT24Api.model.InternalFundsTransferTitleFetchResponse;
import org.ais.jcash.dto.LovResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * In-process T24 / IBFT mock when real host is not connected.
 * Controlled by {@code jcash.t24.mock-enabled} and {@code jcash.t24.mock-fallback-on-error}.
 */
@Service
public class T24MockSupport {

    @Value("${jcash.t24.mock-enabled:true}")
    private boolean mockEnabled;

    @Value("${jcash.t24.mock-fallback-on-error:true}")
    private boolean mockFallbackOnError;

    private static final Map<String, String> IBAN_TITLE_DIRECTORY = new LinkedHashMap<>();

    static {
        IBAN_TITLE_DIRECTORY.put("PK36SCBL0000001123456702", "AHMED ALI KHAN");
        IBAN_TITLE_DIRECTORY.put("PK70HABB0000123456789012", "FATIMA BIBI");
        IBAN_TITLE_DIRECTORY.put("PK12MEZN0000009988776655", "DFS DEMO TRADERS");
        IBAN_TITLE_DIRECTORY.put("PK99MOCK0000000000000001", "MOCK BENEFICIARY ONE");
        IBAN_TITLE_DIRECTORY.put("PK99MOCK0000000000000002", "MOCK BENEFICIARY TWO");
    }

    public boolean isMockEnabled() {
        return mockEnabled;
    }

    public boolean isMockFallbackOnError() {
        return mockFallbackOnError;
    }

    public Map<String, Object> status() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("mockEnabled", mockEnabled);
        map.put("mockFallbackOnError", mockFallbackOnError);
        map.put("mode", mockEnabled ? "MOCK" : "LIVE");
        map.put("sampleIbans", IBAN_TITLE_DIRECTORY.keySet());
        return map;
    }

    public IBFTTitleFetchResponse mockIbftTitleFetch(String fromAccount, String toAccount, String toBankIMD, String amount) {
        String iban = normalizeIban(toAccount);
        IBFTTitleFetchResponse response = new IBFTTitleFetchResponse();
        response.setFromAccount(fromAccount);
        response.setToAccount(iban);
        response.setToBankIMD(toBankIMD);
        response.setAmount(amount);
        response.setToAccountTitle(resolveTitle(iban));
        response.setToBankName(resolveBankName(toBankIMD));
        response.setToBranchName("MOCK MAIN BRANCH");
        return response;
    }

    public InternalFundsTransferTitleFetchResponse mockIftTitleFetch(String accountNumber) {
        InternalFundsTransferTitleFetchResponse response = new InternalFundsTransferTitleFetchResponse();
        response.setAccountNumber(accountNumber);
        response.setAccountTitle("MOCK IFT ACCOUNT " + safeSuffix(accountNumber));
        response.setBranchName("MOCK CMS BRANCH");
        response.setResponseCode("1");
        return response;
    }

    public InternalFundsTransferResponse mockInternalFundsTransfer(String fromAccount, String toAccount, String amount) {
        InternalFundsTransferResponse response = new InternalFundsTransferResponse();
        response.setFromAccount(fromAccount);
        response.setToAccount(toAccount);
        response.setAmount(amount);
        return response;
    }

    /**
     * IBFT payment mock — mirrors IFT payment shape until real IBFT pay API is wired.
     */
    public InternalFundsTransferResponse mockIbftPayment(String fromAccount, String toAccount, String amount) {
        return mockInternalFundsTransfer(fromAccount, toAccount, amount);
    }

    public List<LovResponse> mockIbftBanks() {
        List<LovResponse> banks = new ArrayList<>();
        banks.add(bank("9001", "HBL", "Habib Bank Limited", "00458401"));
        banks.add(bank("9002", "UBL", "United Bank Limited", "01158701"));
        banks.add(bank("9003", "MCB", "MCB Bank Limited", "01027501"));
        banks.add(bank("9004", "MEZN", "Meezan Bank Limited", "01290501"));
        banks.add(bank("9005", "SCBL", "Standard Chartered Bank", "00947801"));
        banks.add(bank("9006", "JSBL", "JS Bank Limited", "00658601"));
        return banks;
    }

    private LovResponse bank(String id, String code, String name, String imd) {
        LovResponse lov = new LovResponse();
        lov.setId(id);
        lov.setCode(code);
        lov.setDescription(name);
        lov.setLongText(imd);
        lov.setShortText("Y");
        return lov;
    }

    private String resolveTitle(String iban) {
        if (IBAN_TITLE_DIRECTORY.containsKey(iban)) {
            return IBAN_TITLE_DIRECTORY.get(iban);
        }
        return "MOCK BENEFICIARY " + safeSuffix(iban);
    }

    private String resolveBankName(String imd) {
        if (imd == null || imd.trim().isEmpty()) {
            return "MOCK BANK";
        }
        for (LovResponse b : mockIbftBanks()) {
            if (imd.equals(b.getLongText())) {
                return b.getDescription();
            }
        }
        return "MOCK BANK (" + imd + ")";
    }

    private String normalizeIban(String toAccount) {
        if (toAccount == null) {
            return "";
        }
        return toAccount.replaceAll("\\s+", "").toUpperCase(Locale.ROOT);
    }

    private String safeSuffix(String value) {
        if (value == null || value.length() < 4) {
            return "0000";
        }
        return value.substring(value.length() - 4);
    }
}
