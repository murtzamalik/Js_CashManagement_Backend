package org.ais.jcash.Service.billpay;

import org.ais.jcash.WsdlT24Api.model.UtilityBillInquiryResponse;
import org.ais.jcash.WsdlT24Api.service.T24MockSupport;
import org.ais.jcash.dto.billpay.BillPayCategoryDto;
import org.ais.jcash.dto.billpay.BillPayCompanyDto;
import org.ais.jcash.dto.billpay.BillPayFetchRequest;
import org.ais.jcash.dto.billpay.BillPayPayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Pakistan-style bill pay catalogue + mock fetch/pay until real biller host is connected.
 */
@Service
public class BillPayCatalogueService {

    @Autowired
    private T24MockSupport t24MockSupport;

    private static final List<BillPayCategoryDto> CATEGORIES = new ArrayList<>();
    private static final List<BillPayCompanyDto> COMPANIES = new ArrayList<>();

    static {
        CATEGORIES.add(new BillPayCategoryDto("UTILITY", "Utility Bills", "Electricity, Gas & Water", "pi-bolt"));
        CATEGORIES.add(new BillPayCategoryDto("MOBILE", "Mobile / Telco", "Prepaid & postpaid mobile", "pi-mobile"));
        CATEGORIES.add(new BillPayCategoryDto("INTERNET", "Internet / Broadband", "ISP & cable TV", "pi-wifi"));
        CATEGORIES.add(new BillPayCategoryDto("EDUCATION", "Education", "Schools, colleges & universities", "pi-book"));
        CATEGORIES.add(new BillPayCategoryDto("GOVERNMENT", "Government", "Taxes, challans & fees", "pi-building"));
        CATEGORIES.add(new BillPayCategoryDto("INSURANCE", "Insurance", "Life & health premiums", "pi-shield"));

        // Utility
        COMPANIES.add(new BillPayCompanyDto("KE", "K-Electric", "UTILITY", "Consumer Number"));
        COMPANIES.add(new BillPayCompanyDto("LESCO", "LESCO", "UTILITY", "Reference Number"));
        COMPANIES.add(new BillPayCompanyDto("MEPCO", "MEPCO", "UTILITY", "Reference Number"));
        COMPANIES.add(new BillPayCompanyDto("IESCO", "IESCO", "UTILITY", "Reference Number"));
        COMPANIES.add(new BillPayCompanyDto("SSGC", "SSGC (Gas)", "UTILITY", "Consumer Number"));
        COMPANIES.add(new BillPayCompanyDto("SNGPL", "SNGPL (Gas)", "UTILITY", "Consumer Number"));
        COMPANIES.add(new BillPayCompanyDto("KWSC", "Karachi Water (KW&SB)", "UTILITY", "Consumer Number"));

        // Mobile
        COMPANIES.add(new BillPayCompanyDto("JAZZ", "Jazz", "MOBILE", "Mobile Number"));
        COMPANIES.add(new BillPayCompanyDto("TELNOR", "Telenor", "MOBILE", "Mobile Number"));
        COMPANIES.add(new BillPayCompanyDto("UFONE", "Ufone", "MOBILE", "Mobile Number"));
        COMPANIES.add(new BillPayCompanyDto("ZONG", "Zong", "MOBILE", "Mobile Number"));

        // Internet
        COMPANIES.add(new BillPayCompanyDto("PTCL", "PTCL", "INTERNET", "Account / Phone Number"));
        COMPANIES.add(new BillPayCompanyDto("NAYATEL", "Nayatel", "INTERNET", "Account Number"));
        COMPANIES.add(new BillPayCompanyDto("STORM", "StormFiber", "INTERNET", "Account Number"));

        // Education
        COMPANIES.add(new BillPayCompanyDto("LUMS", "LUMS", "EDUCATION", "Student / Voucher ID"));
        COMPANIES.add(new BillPayCompanyDto("NUST", "NUST", "EDUCATION", "Student ID"));
        COMPANIES.add(new BillPayCompanyDto("IBA", "IBA Karachi", "EDUCATION", "Student ID"));
        COMPANIES.add(new BillPayCompanyDto("BEACON", "Beaconhouse", "EDUCATION", "Student ID"));

        // Government
        COMPANIES.add(new BillPayCompanyDto("FBR", "FBR Tax Payment", "GOVERNMENT", "PSID / Payment ID"));
        COMPANIES.add(new BillPayCompanyDto("EXCISE", "Excise & Taxation", "GOVERNMENT", "Challan Number"));
        COMPANIES.add(new BillPayCompanyDto("NADRA", "NADRA Fees", "GOVERNMENT", "Tracking / Token ID"));
        COMPANIES.add(new BillPayCompanyDto("TRAFFIC", "Traffic Challan", "GOVERNMENT", "Challan Number"));

        // Insurance
        COMPANIES.add(new BillPayCompanyDto("EFU", "EFU Life", "INSURANCE", "Policy Number"));
        COMPANIES.add(new BillPayCompanyDto("JLI", "Jubilee Life", "INSURANCE", "Policy Number"));
    }

    public List<BillPayCategoryDto> categories() {
        return CATEGORIES;
    }

    public List<BillPayCompanyDto> companies(String categoryCode) {
        if (categoryCode == null || categoryCode.trim().isEmpty()) {
            return COMPANIES;
        }
        String code = categoryCode.trim().toUpperCase(Locale.ROOT);
        return COMPANIES.stream()
                .filter(c -> code.equalsIgnoreCase(c.getCategoryCode()))
                .collect(Collectors.toList());
    }

    public BillPayCompanyDto findCompany(String companyCode) {
        if (companyCode == null) {
            return null;
        }
        return COMPANIES.stream()
                .filter(c -> companyCode.equalsIgnoreCase(c.getCode()))
                .findFirst()
                .orElse(null);
    }

    public UtilityBillInquiryResponse fetchBill(BillPayFetchRequest request) {
        BillPayCompanyDto company = findCompany(request.getCompanyCode());
        String consumer = normalizeConsumer(request.getConsumerNumber());
        String fromAccount = request.getFromAccount() == null || request.getFromAccount().isEmpty()
                ? "0000000000"
                : request.getFromAccount();

        YearMonth month = YearMonth.now();
        LocalDate due = month.atEndOfMonth();
        String amountWithin = mockAmount(consumer);
        String amountAfter = String.valueOf(Integer.parseInt(amountWithin) + 150);

        UtilityBillInquiryResponse response = new UtilityBillInquiryResponse();
        response.setFromAccount(fromAccount);
        response.setUtilityCompanyCode(request.getCompanyCode());
        response.setUtilityConsumerNumber(consumer);
        response.setSubscriberNameOrConnectionType(mockSubscriber(company, consumer));
        response.setBillingMonth(month.format(DateTimeFormatter.ofPattern("yyyyMM")));
        response.setPaymentDueDate(due.format(DateTimeFormatter.BASIC_ISO_DATE));
        response.setTotalAmountPayableWithinDueDate(amountWithin);
        response.setTotalAmountPayableAfterDueDate(amountAfter);
        response.setBillStatus("UNPAID");
        response.setNetCED("0");
        response.setNetWithholdingTax("0");
        return response;
    }

    public Map<String, Object> payBill(BillPayPayRequest request) {
        Map<String, Object> result = new LinkedHashMap<>();
        String rrn = String.format("%06d", new Random().nextInt(1_000_000));
        result.put("status", "SUCCESS");
        result.put("rrn", rrn);
        result.put("companyCode", request.getCompanyCode());
        result.put("companyName", request.getCompanyName());
        result.put("consumerNumber", normalizeConsumer(request.getConsumerNumber()));
        result.put("amount", request.getAmount());
        result.put("fromAccount", request.getFromAccount());
        result.put("billingMonth", request.getBillingMonth());
        result.put("subscriberName", request.getSubscriberName());
        result.put("custRef", request.getCustRef() != null ? request.getCustRef() : "BILL-" + rrn);
        result.put("mockMode", t24MockSupport.isMockEnabled());
        result.put("message", t24MockSupport.isMockEnabled()
                ? "Bill payment accepted (MOCK). Real biller host not connected."
                : "Bill payment submitted.");
        return result;
    }

    public Map<String, Object> enrichFetch(UtilityBillInquiryResponse bill, BillPayFetchRequest request) {
        Map<String, Object> data = new HashMap<>();
        BillPayCompanyDto company = findCompany(request.getCompanyCode());
        data.put("fromAccount", bill.getFromAccount());
        data.put("utilityCompanyCode", bill.getUtilityCompanyCode());
        data.put("utilityConsumerNumber", bill.getUtilityConsumerNumber());
        data.put("subscriberNameOrConnectionType", bill.getSubscriberNameOrConnectionType());
        data.put("billingMonth", bill.getBillingMonth());
        data.put("paymentDueDate", bill.getPaymentDueDate());
        data.put("totalAmountPayableWithinDueDate", bill.getTotalAmountPayableWithinDueDate());
        data.put("totalAmountPayableAfterDueDate", bill.getTotalAmountPayableAfterDueDate());
        data.put("billStatus", bill.getBillStatus());
        data.put("netCED", bill.getNetCED());
        data.put("netWithholdingTax", bill.getNetWithholdingTax());
        data.put("companyName", company != null ? company.getName() : request.getCompanyCode());
        data.put("categoryCode", company != null ? company.getCategoryCode() : request.getCategoryCode());
        data.put("mockMode", t24MockSupport.isMockEnabled());
        return data;
    }

    private String normalizeConsumer(String consumerNumber) {
        if (consumerNumber == null) {
            return "";
        }
        return consumerNumber.replaceAll("\\s+", "").toUpperCase(Locale.ROOT);
    }

    private String mockSubscriber(BillPayCompanyDto company, String consumer) {
        String suffix = consumer.length() >= 4 ? consumer.substring(consumer.length() - 4) : consumer;
        String companyName = company != null ? company.getName() : "Biller";
        return "MOCK CUSTOMER " + suffix + " / " + companyName;
    }

    private String mockAmount(String consumer) {
        int base = 500;
        if (consumer != null && !consumer.isEmpty()) {
            base += Math.abs(consumer.hashCode() % 4500);
        }
        return String.valueOf(base);
    }
}
