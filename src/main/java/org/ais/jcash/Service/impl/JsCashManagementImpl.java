package org.ais.jcash.Service.impl;

import org.ais.jcash.Repo.*;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.dto.*;
import org.ais.jcash.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 3:46 PM
 * Project : jcash
 */


@Service
@Transactional(rollbackOn = Exception.class)
public class JsCashManagementImpl implements JsCashNonFinService {


    @Autowired
    private TblMenuRepo tblMenuRepo;

    @Autowired
    private TblParserHeadRepo tblParserHeadRepo;

    @Autowired
    private TblParserDetailRepo tblParserDetailRepo;

    @Autowired
    private TblUserRepo tblUserRepo;

    @Autowired
    private TblRoleRepo tblRoleRepo;

    @Autowired
    private TblRoleRightRepo tblRoleRightsRepo;

    @Autowired
    private TblCompanyGroupRepo tblCompanyGroupRepo;

    @Autowired
    private TblCompanyRepo tblCompanyProfileRepo;

    @Autowired
    private TblParserCompanyConfigRepo tblParserCompanyConfigRepo;

    @Autowired
    private TblCompanyProductRepo tblCompanyProductsRepo;

    @Autowired
    private TblUserRoleRepo tblUserRoleRepo;

    @Autowired
    private TblModuleRepo tblModuleRepo;

    @Autowired
    private TblUserAccountProductRepo tblUserAccountProductRepo;

    @Autowired
    private TblAccountRepo tblAccountRepo;

    @Autowired
    private TblCompanyRepo tblCompanyRepo;

    @Autowired
    private TblProductRepo tblProductRepo;

    @Autowired
    private LkpOtpTypeRepo lkpOtpTypeRepo;

    @Autowired
    private TblOtpRepo tblOtpRepo;

    @Autowired
    private TblUserLoginHistoryRepo tblUserLoginHistoryRepo;

    @Autowired
    private TblAuthMatrixHeadRepo tblAuthMatrixHeadRepo;

    @Autowired
    private TblAuthMatrixDetailRepo tblAuthMatrixDetailRepo;

    @Autowired
    private TblFileHeadRepo tblFileHeadRepo;

    @Autowired
    private TblFileDetailRepo tblFileDetailRepo;

    @Autowired
    private TblTransHeadRepo tblTransHeadRepo;

    @Autowired
    private TblSmsMsgEmailRepo tblSmsMsgEmailRepo;

    @Autowired
    private TblCashOverCounterRepo tblCashOverCounterRepo;

    @Autowired
    private LkpCountryRepo lkpCountryRepo;

    @Autowired
    private LkpProvinceRepo lkpProvinceRepo;

    @Autowired
    private LkpCityRepo lkpCityRepo;

    @Override
    public List<TblMenu> getAllMenuByStatus(String status) {
        return tblMenuRepo.findAll();
    }


    @Override
    public TblUser checkUserLogin(String userName, String password) {
        return tblUserRepo.findByUserNameAndPassword(userName, password);
    }


    @Override
    public TblMenu saveMenu(TblMenu tblMenu) {
        return tblMenuRepo.save(tblMenu);
    }

    @Override
    public TblRole saveRole(TblRole tblRole) {
        return tblRoleRepo.save(tblRole);
    }

    @Override
    public List<TblRole> getAllRolebyStatus(String status) {
        return tblRoleRepo.findAll();
    }

    @Override
    public List<TblRoleRight> getMenuByRoleId(String roleId) {
        return tblRoleRightsRepo.findAll();
    }

    @Override
    public TblRoleRight saveRoleRights(TblRoleRight tblRoleRights) {
        return tblRoleRightsRepo.save(tblRoleRights);
    }

    @Override
    public TblCompanyGroup saveCompanyGroup(TblCompanyGroup tblCompanyGroup) {
        return tblCompanyGroupRepo.save(tblCompanyGroup);
    }

    @Override
    public TblCompany saveCompanyProfile(TblCompany tblCompanyProfile) {
        return tblCompanyProfileRepo.save(tblCompanyProfile);
    }

    @Override
    public List<TblCompanyGroup> getAllCompanyGroups() {
        return tblCompanyGroupRepo.findAll();
    }

    @Override
    public List<TblCompany> getAllGroupCompanies(String groupId) {
        return tblCompanyProfileRepo.findByTblCompanyGroupCompanyGroupId(Long.valueOf(groupId));
    }

    @Override
    public TblCompanyProduct saveCompanyProducts(TblCompanyProduct tblCompanyProduct) {
        return tblCompanyProductsRepo.save(tblCompanyProduct);
    }

    @Override
    public List<TblCompanyProduct> getAllCompanyProduct(String companyId) {

        return tblCompanyProductsRepo.findByTblCompanyCompanyId(Long.valueOf(companyId));
    }

    @Override
    public List<TblUserRole> getRoleAgainstUser(long userId) {
        return tblUserRoleRepo.findByTblUserUserId(userId);
    }

    @Override
    public List<TblModule> getRoleWiseModule(List<TblUserRole> roleId) {

        List<Long> roleIds = new ArrayList<>();
        for (TblUserRole tblUserRole : roleId) {
            roleIds.add(tblUserRole.getTblRole().getRoleId());
        }
        return tblModuleRepo.getRoleWiseModule(roleIds);
    }

    @Override
    public List<TblRoleRight> getRoleAndModuelWisePages(long modulecodeId, List<TblUserRole> roleId) {
        List<Long> roleIds = new ArrayList<>();
        for (TblUserRole tblUserRole : roleId) {
            roleIds.add(tblUserRole.getTblRole().getRoleId());
        }
        return tblRoleRightsRepo.getRoleAndModuelWisePages(modulecodeId, roleIds);
    }

    @Override
    public TblModule saveModule(TblModule tblModule) {
        return tblModuleRepo.save(tblModule);
    }

    @Override
    public List<TblMenu> getAllMenuByModuleId(Long moduleId) {
        return tblMenuRepo.getMenuByModuleId(moduleId);
    }

    @Override
    public List<TblModule> getAllModule() {
        return tblModuleRepo.findAll();
    }

    @Override
    public TblUser saveTblUser(TblUser tblUser) {
        tblUser = tblUserRepo.save(tblUser);
        if (tblUser != null && tblUser.getUserId() > 0) {
            TblUserRole tblUserRole = new TblUserRole();
            TblRole tblRole = new TblRole();

//            tblRole.setRoleId(tblUser.getRoleId());


            tblUserRole.setTblUser(tblUser);
            tblUserRole.setTblRole(tblRole);
            tblUserRole.setCreateuser(tblUser.getCreateuser());

            tblUserRoleRepo.save(tblUserRole);

            return tblUser;

        } else {
            return null;
        }
    }

    @Override
    public List<TblUser> getAlluser(String companyId) {
//        return tblUserRepo.findByTblCompanyCompanyIdAndMcStatus(Long.valueOf(companyId), "A");

        List<TblUser> tblUsers = tblUserRepo.findByTblCompanyCompanyId(Long.valueOf(companyId));
        if (tblUsers != null && tblUsers.size() > 0) {
            for (TblUser tblUser : tblUsers) {
                List<TblUserRole> tblUserRole = tblUserRoleRepo.findByTblUserUserId(tblUser.getUserId());

//                tblUser.setTblRole(tblUserRole.get(0).getTblRole());


            }

            return tblUsers;
        } else {
            return null;
        }

    }

    @Override
    public List<TblCompanyProduct> getAllproducts(String companyId) {
        return tblCompanyProductsRepo.findByTblCompanyCompanyId(Long.valueOf(companyId));
    }

    @Override
    public List<TblAccount> getAllaccounts(String companyId) {
        return tblAccountRepo.findByTblCompanyCompanyId(Long.valueOf(companyId));
    }


    @Override
    public TblUserRole saveUserRole(TblUserRole tblUserRole) {
        return tblUserRoleRepo.save(tblUserRole);
    }

    @Override
    public TblUserAccountProduct saveCompanyUserAccountsProduct(TblUserAccountProduct tblUserAccountProduct) {
        return tblUserAccountProductRepo.save(tblUserAccountProduct);
    }


    @Override
    public List<TblCompany> getAllCompany() {
        return tblCompanyRepo.findAll();
    }

    @Override
    public TblAccount saveCompanyAccounts(TblAccount tblAccount) {
        return tblAccountRepo.save(tblAccount);
    }

    @Override
    public List<TblRoleRight> getAllRoleRightsbyRoleId(String roleId) {
        return tblRoleRightsRepo.findByTblRoleRoleId(Long.valueOf(roleId));
    }

    @Override
    public TblUser checkUserPassword(long userId, String currPass) {
        return tblUserRepo.findByUserIdAndPassword(userId, currPass);
    }

    @Override
    public int updateUserPassword(long userId, String newPass, long loggedUserId) {
        return tblUserRepo.updateUserPassword(userId, newPass, new Date(), BigDecimal.valueOf(loggedUserId));
    }


    @Override
    public TblUser verifyUserEmail(String userName, String email) {
        return tblUserRepo.findByUserNameAndEmail(userName, email);
    }

    @Override
    public List<TblProduct> getAllProduct() {
        return tblProductRepo.findAll();
    }

    @Override
    public TblCompanyProduct updateCompanyProduct(UpdateCompanyProductsRequest updateCompanyProductsRequest, LoggedUserDetail loggedUserDetail) {
        TblCompanyProduct tblCompanyProduct = tblCompanyProductsRepo.findById(updateCompanyProductsRequest.getCompanyProductId()).orElse(null);

        tblCompanyProduct.setCourierId(updateCompanyProductsRequest.getCourierId());
        tblCompanyProduct.setCustAccountCr(updateCompanyProductsRequest.getCustAccountCr());
        tblCompanyProduct.setCustAccountDr(updateCompanyProductsRequest.getCustAccountDr());
        tblCompanyProduct.setCustCollectionAccount(updateCompanyProductsRequest.getCustCollectionAccount());
        tblCompanyProduct.setEnrichedData(updateCompanyProductsRequest.getEnrichedData());
        tblCompanyProduct.setGuaranteedFund(updateCompanyProductsRequest.getGuaranteedFund());
        tblCompanyProduct.setInterestRecoveryAccount(updateCompanyProductsRequest.getInterestRecoveryAccount());
        tblCompanyProduct.setClosed(updateCompanyProductsRequest.getClosed());
        tblCompanyProduct.setClosedDate(updateCompanyProductsRequest.getClosedDate());
        tblCompanyProduct.setClosedTill(updateCompanyProductsRequest.getClosedTill());
        tblCompanyProduct.setClosureReason(updateCompanyProductsRequest.getClosureReason());
        tblCompanyProduct.setDealerListRequired(updateCompanyProductsRequest.getDealerListRequired());
        tblCompanyProduct.setDeferredUpto(updateCompanyProductsRequest.getDeferredUpto());
        tblCompanyProduct.setDiscountable(updateCompanyProductsRequest.getDiscountable());
        tblCompanyProduct.setDocumentReceived(updateCompanyProductsRequest.getDocumentReceived());
        tblCompanyProduct.setFundCreditDays(updateCompanyProductsRequest.getFundCreditDays());
        tblCompanyProduct.setNarration(updateCompanyProductsRequest.getNarration());
        tblCompanyProduct.setOffsitePrinting(updateCompanyProductsRequest.getOffsitePrinting());
        tblCompanyProduct.setOnlineInvoice(updateCompanyProductsRequest.getOnlineInvoice());
        tblCompanyProduct.setReopeningDate(updateCompanyProductsRequest.getReopeningDate());
        tblCompanyProduct.setServiceRecoveryAccount(updateCompanyProductsRequest.getServiceRecoveryAccount());
        tblCompanyProduct.setStationaryId(updateCompanyProductsRequest.getStationaryId());
        tblCompanyProduct.setLastupdatedate(new Date());
        tblCompanyProduct.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblCompanyProduct.setUpdateindex(tblCompanyProduct.getUpdateindex() != null ? new BigDecimal(tblCompanyProduct.getUpdateindex().longValue() + 1) : new BigDecimal(1));


        return tblCompanyProductsRepo.save(tblCompanyProduct);
    }

    @Override
    public TblCompanyGroup updateComapnyGroup(UpdateCompanyGroupRequest updateCompanyGroupRequest, LoggedUserDetail loggedUserDetail) {
        TblCompanyGroup tblCompanyGroup = tblCompanyGroupRepo.findById(updateCompanyGroupRequest.getCompanyGroupId()).orElse(null);


        tblCompanyGroup.setAccountNo(updateCompanyGroupRequest.getAccountNo());
        tblCompanyGroup.setAddress1(updateCompanyGroupRequest.getAddress1());
        tblCompanyGroup.setAddress2(updateCompanyGroupRequest.getAddress2());
        tblCompanyGroup.setAddress3(updateCompanyGroupRequest.getAddress3());
        tblCompanyGroup.setCisNo(updateCompanyGroupRequest.getCisNo());
        tblCompanyGroup.setClosureReason(updateCompanyGroupRequest.getClosureReason());
        tblCompanyGroup.setContactNo(updateCompanyGroupRequest.getContactNo());
        tblCompanyGroup.setContactPerson(updateCompanyGroupRequest.getContactPerson());
        tblCompanyGroup.setCreditLimit(updateCompanyGroupRequest.getCreditLimit());
        tblCompanyGroup.setDiscountable(updateCompanyGroupRequest.getDiscountable());
        tblCompanyGroup.setEmail(updateCompanyGroupRequest.getEmail());
        tblCompanyGroup.setFaxNo(updateCompanyGroupRequest.getFaxNo());
        tblCompanyGroup.setGroupCode(updateCompanyGroupRequest.getGroupCode());
        tblCompanyGroup.setGroupName(updateCompanyGroupRequest.getGroupName());
        tblCompanyGroup.setMobileNo(updateCompanyGroupRequest.getMobileNo());
        tblCompanyGroup.setOutstandingAmount(updateCompanyGroupRequest.getOutstandingAmount());
        tblCompanyGroup.setRelationship(updateCompanyGroupRequest.getRelationship());
        tblCompanyGroup.setClosedDate(updateCompanyGroupRequest.getClosedDate());
        tblCompanyGroup.setClosedTill(updateCompanyGroupRequest.getClosedTill());
        tblCompanyGroup.setReopeningDate(updateCompanyGroupRequest.getReopeningDate());
        tblCompanyGroup.setClosed(updateCompanyGroupRequest.getClosed());
        tblCompanyGroup.setLastupdatedate(new Date());
        tblCompanyGroup.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblCompanyGroup.setUpdateindex(tblCompanyGroup.getUpdateindex() != null ? new BigDecimal(tblCompanyGroup.getUpdateindex().longValue() + 1) : new BigDecimal(1));
        tblCompanyGroup.setUrl(updateCompanyGroupRequest.getUrl());
        return tblCompanyGroupRepo.save(tblCompanyGroup);
    }

    @Override
    public TblCompany updateCompany(UpdateCompanyRequest updateCompanyProfileRequest, LoggedUserDetail loggedUserDetail) {

        TblCompany tblCompany = tblCompanyRepo.findById(updateCompanyProfileRequest.getCompanyId()).orElse(null);


        tblCompany.setAccountNo(updateCompanyProfileRequest.getAccountNo());
        tblCompany.setAccountingEntryType(updateCompanyProfileRequest.getAccountingEntryType());
        tblCompany.setUrl(updateCompanyProfileRequest.getUrl());
        tblCompany.setUbCustomer(updateCompanyProfileRequest.getUbCustomer());
        tblCompany.setTelexNo(updateCompanyProfileRequest.getTelexNo());
        tblCompany.setRelationshipManager(updateCompanyProfileRequest.getRelationshipManager());
        tblCompany.setProductManager(updateCompanyProfileRequest.getProductManager());
        tblCompany.setOutstandingAmount(updateCompanyProfileRequest.getOutstandingAmount());
        tblCompany.setMobileNo(updateCompanyProfileRequest.getMobileNo());
        tblCompany.setFaxNo1(updateCompanyProfileRequest.getFaxNo1());
        tblCompany.setFaxNo2(updateCompanyProfileRequest.getFaxNo2());
        tblCompany.setFaxNo3(updateCompanyProfileRequest.getFaxNo3());
        tblCompany.setEmail(updateCompanyProfileRequest.getEmail());
        tblCompany.setCustomerType(updateCompanyProfileRequest.getCustomerType());
        tblCompany.setContactNo(updateCompanyProfileRequest.getContactNo());
        tblCompany.setCompanyName(updateCompanyProfileRequest.getCompanyName());
        tblCompany.setCompanyCode(updateCompanyProfileRequest.getCompanyCode());
        tblCompany.setClosureReason(updateCompanyProfileRequest.getClosureReason());
        tblCompany.setBackupEmail1(updateCompanyProfileRequest.getBackupEmail1());
        tblCompany.setBackupEmail2(updateCompanyProfileRequest.getBackupEmail2());
        tblCompany.setBackupContact(updateCompanyProfileRequest.getBackupContact());
        tblCompany.setAddress1(updateCompanyProfileRequest.getAddress1());
        tblCompany.setAddress2(updateCompanyProfileRequest.getAddress2());
        tblCompany.setAddress3(updateCompanyProfileRequest.getAddress3());
        tblCompany.setAccountingEntryType(updateCompanyProfileRequest.getAccountingEntryType());
        tblCompany.setAccountingEntryRequest(updateCompanyProfileRequest.getAccountingEntryRequest());
        tblCompany.setCreditLimit(updateCompanyProfileRequest.getCreditLimit());
        tblCompany.setUbCompanyCode(updateCompanyProfileRequest.getUbCompanyCode());
        tblCompany.setDiscountable(updateCompanyProfileRequest.getDiscountable());
        tblCompany.setClosed(updateCompanyProfileRequest.getClosed());
        tblCompany.setClosedDate(updateCompanyProfileRequest.getClosedDate());
        tblCompany.setClosedTill(updateCompanyProfileRequest.getClosedTill());
        tblCompany.setReopeningDate(updateCompanyProfileRequest.getReopeningDate());
        tblCompany.setLastupdatedate(new Date());
        tblCompany.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblCompany.setUpdateindex(tblCompany.getUpdateindex() != null ? new BigDecimal(tblCompany.getUpdateindex().longValue() + 1) : new BigDecimal(1));

        return tblCompanyRepo.save(tblCompany);
    }

    @Override
    public TblModule updateModule(UpdateModuleReq updateModuleReq, LoggedUserDetail loggedUserDetail) {

        TblModule tblModule = tblModuleRepo.findById(updateModuleReq.getModuleId()).orElse(null);

        tblModule.setModuleDescr(updateModuleReq.getModuleDescr());
        tblModule.setLastupdatedate(new Date());
        tblModule.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblModule.setUpdateindex(tblModule.getUpdateindex() != null ? new BigDecimal(tblModule.getUpdateindex().longValue() + 1) : new BigDecimal(1));

        return tblModuleRepo.save(tblModule);
    }

    @Override
    public TblRole updateRole(UpdateRoleRequest updateRoleRequest, LoggedUserDetail loggedUserDetail) {

        TblRole tblRole = tblRoleRepo.findById(updateRoleRequest.getRoleId()).orElse(null);

        tblRole.setRoleDescr(updateRoleRequest.getRoleDescr());
        tblRole.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblRole.setLastupdatedate(new Date());
        tblRole.setUpdateindex(tblRole.getUpdateindex() != null ? new BigDecimal(tblRole.getUpdateindex().longValue() + 1) : new BigDecimal(1));

        return tblRoleRepo.save(tblRole);
    }

    @Override
    public TblMenu updateMenu(UpdateMenuRequest updateMenuRequest, LoggedUserDetail loggedUserDetail) {

        TblMenu tblMenu = tblMenuRepo.findById(updateMenuRequest.getMenuId()).orElse(null);

        tblMenu.setMenuDescription(updateMenuRequest.getMenuDescription());
        tblMenu.setIconName(updateMenuRequest.getIconName());
        tblMenu.setIconPath(updateMenuRequest.getIconPath());
        tblMenu.setMenuCode(updateMenuRequest.getMenuCode());
        tblMenu.setMenuPath(updateMenuRequest.getMenuPath());
        tblMenu.setMenuType(updateMenuRequest.getMenuType());
        tblMenu.setLastupdatedate(new Date());
        tblMenu.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        tblMenu.setUpdateindex(tblMenu.getUpdateindex() != null ? new BigDecimal(tblMenu.getUpdateindex().longValue() + 1) : new BigDecimal(1));

        return tblMenuRepo.save(tblMenu);

    }


    @Override
    public List<TblCompany> getAllCompany(String companyId) {
        return tblCompanyRepo.findBycompanyId(Long.valueOf(companyId));
    }

    @Override
    public TblCompany getcompany(String companyId) {
        return tblCompanyRepo.findById(Long.valueOf(companyId)).orElse(null);
    }

    @Override
    public List<TblUser> getAllusers(String companyId) {
        return tblUserRepo.findByTblCompanyCompanyId(Long.valueOf(companyId));
    }

    @Override
    public List<TblAccount> getAllAccounts(String companyId) {
        return tblAccountRepo.findByTblCompanyCompanyId(Long.parseLong(companyId));
    }

    @Override
    public List<TblCompanyProduct> getAllCompanyProducts(String companyId) {
        return tblCompanyProductsRepo.findByTblCompanyCompanyId(Long.valueOf(companyId));
    }

    @Override
    public LkpOtpType getOtpType(String otpCode) {
        return lkpOtpTypeRepo.findByCode(otpCode);
    }

    @Override
    public TblOtp saveOtp(TblOtp tblOtp) {

        TblOtp oldOtp = tblOtpRepo.getSecurityOtpin(tblOtp.getUserId().longValue());
        if (oldOtp != null) {

            oldOtp.setIsExpired("Y");
            oldOtp.setLastupdatedate(new Date());
            oldOtp.setLastupdateuser(tblOtp.getUserId());
            oldOtp.setUpdateindex(oldOtp.getUpdateindex() != null ? new BigDecimal(oldOtp.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            tblOtpRepo.saveAndFlush(oldOtp);

        }

        return tblOtpRepo.saveAndFlush(tblOtp);
    }

    @Override
    public int verifyOtp(String otp, long userId, long otpType) {
        TblOtp tblOtp = tblOtpRepo.findByUserIdAndOtpinAndLkpOtpTypeOtpTypeId(new BigDecimal(userId), otp, otpType);
        if (tblOtp != null && tblOtp.getOtpId() > 0) {
            return tblOtpRepo.updateOtpStatus(BigDecimal.valueOf(userId), otpType, otp, new Date(), new BigDecimal(userId));
        } else {
            return 0;
        }
    }

    @Override
    public TblUserLoginHistory saveUserLoginHistory(TblUserLoginHistory tblUserLoginHistory) {
        return tblUserLoginHistoryRepo.save(tblUserLoginHistory);
    }

    @Override
    public TblUserLoginHistory logoutUser(long loginId) {
        TblUserLoginHistory tblUserLoginHistory = tblUserLoginHistoryRepo.findById(loginId).orElse(null);
        if (tblUserLoginHistory != null) {
            tblUserLoginHistory.setLogoutDate(new Date());
            return tblUserLoginHistoryRepo.save(tblUserLoginHistory);
        } else {
            return null;
        }

    }

    @Override
    public TblAuthMatrixHead saveTblAuthMatrixHead(TblAuthMatrixHead tblAuthMatrixHead) {
        return tblAuthMatrixHeadRepo.save(tblAuthMatrixHead);
    }

    @Override
    public TblAuthMatrixDetail saveTblAuthMatrixDetail(TblAuthMatrixDetail tblAuthMatrixDetail) {
        return tblAuthMatrixDetailRepo.save(tblAuthMatrixDetail);
    }

    @Override
    public TblCompany getCompanyById(String companyId) {
        return tblCompanyRepo.findById(Long.valueOf(companyId)).orElse(null);
    }

    @Override
    public List<TblUserAccountProduct> getUserAccountProduct(String companyId) {
        return tblUserAccountProductRepo.findByTblCompanyCompanyId(Long.valueOf(companyId));
    }

    @Override
    public List<TblAuthMatrixHead> getCompanyAuthMatrix(String companyId) {
        List<TblAuthMatrixHead> tblAuthMatrixHeads = tblAuthMatrixHeadRepo.findByTblCompanyCompanyId(Long.valueOf(companyId));
        if (tblAuthMatrixHeads != null && tblAuthMatrixHeads.size() > 0) {
            for (TblAuthMatrixHead tblAuthMatrixHead : tblAuthMatrixHeads) {
                List<TblAuthMatrixDetail> tblAuthMatrixDetails = tblAuthMatrixDetailRepo.findByTblAuthMatrixHeadAuthMatrixHeadId(tblAuthMatrixHead.getAuthMatrixHeadId());
//                tblAuthMatrixHead.setAuthMatrixDetails(tblAuthMatrixDetails);
            }
            return tblAuthMatrixHeads;
        } else {
            return null;
        }
    }

    @Override
    public TblParserHead saveParserhead(TblParserHead tblParserHead) {
        return tblParserHeadRepo.save(tblParserHead);
    }

    @Override
    public TblParserDetail saveParserDetails(TblParserDetail tblParserDetail) {
        return tblParserDetailRepo.save(tblParserDetail);
    }

    @Override
    public TblParserCompanyConfig saveParserCompanyConfig(TblParserCompanyConfig tblParserCompanyConfig) {
        return tblParserCompanyConfigRepo.save(tblParserCompanyConfig);
    }

    @Override
    public TblUser saveBranchUser(TblUser tblUser) {
        tblUser = tblUserRepo.save(tblUser);
        if (tblUser != null && tblUser.getUserId() > 0) {
            TblUserRole tblUserRole = new TblUserRole();
            TblRole tblRole = new TblRole();

//            tblRole.setRoleId(tblUser.getRoleId());


            tblUserRole.setTblUser(tblUser);
            tblUserRole.setTblRole(tblRole);
            tblUserRole.setCreateuser(tblUser.getCreateuser());

            tblUserRoleRepo.save(tblUserRole);

            return tblUser;

        } else {
            return null;
        }
    }

    @Override
    public List<TblUser> getallBranchuser(Long userTypeId) {
        List<TblUser> tblUsers = tblUserRepo.findByLkpUserTypeUserTypeId(Long.valueOf(userTypeId));

        if (tblUsers != null && tblUsers.size() > 0) {
            for (TblUser tblUser : tblUsers) {
                List<TblUserRole> tblUserRole = tblUserRoleRepo.findByTblUserUserId(tblUser.getUserId());

//                tblUser.setTblRole(tblUserRole.get(0).getTblRole());


            }

            return tblUsers;
        } else {
            return null;
        }
    }

    @Override
    public TblAuthMatrixHead getAuthMatrix(String authHeadId) {
        TblAuthMatrixHead tblAuthMatrixHead = tblAuthMatrixHeadRepo.findById(Long.valueOf(authHeadId)).orElse(null);
        if (tblAuthMatrixHead != null) {
            List<TblAuthMatrixDetail> tblAuthMatrixDetails = tblAuthMatrixDetailRepo.findByTblAuthMatrixHeadAuthMatrixHeadId(tblAuthMatrixHead.getAuthMatrixHeadId());
//            tblAuthMatrixHead.setAuthMatrixDetails(tblAuthMatrixDetails);


            return tblAuthMatrixHead;
        } else {
            return null;
        }
    }

    @Override
    public TblAccount updateCompanyAccounts(UpdateAccountRequest updateAccountRequest, LoggedUserDetail loggedUserDetail) {

        TblAccount tblAccount = tblAccountRepo.findById(updateAccountRequest.getAccountId()).orElse(null);
        if (tblAccount != null) {


            tblAccount.setAccountName(updateAccountRequest.getAccountName());
            tblAccount.setAccountNo(updateAccountRequest.getAccountNo());
            tblAccount.setAllowEntry(updateAccountRequest.getAllowEntry());
            tblAccount.setClosed(updateAccountRequest.getClosed());
            tblAccount.setClosedDate(updateAccountRequest.getClosedDate());
            tblAccount.setClosedTill(updateAccountRequest.getClosedTill());
            tblAccount.setClosureReason(updateAccountRequest.getClosureReason());
            tblAccount.setEntity(updateAccountRequest.getEntity());
            tblAccount.setHostAccountNo(updateAccountRequest.getHostAccountNo());
            tblAccount.setReopeningDate(updateAccountRequest.getReopeningDate());
            tblAccount.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblAccount.setLastupdatedate(new Date());
            tblAccount.setUpdateindex(tblAccount.getUpdateindex() != null ? new BigDecimal(tblAccount.getUpdateindex().longValue() + 1) : new BigDecimal(1));

            LkpBank lkpBank = new LkpBank();
            LkpBranch lkpBranch = new LkpBranch();

            lkpBank.setBankId(updateAccountRequest.getBankId());
            lkpBranch.setBranchId(updateAccountRequest.getBranchId());

            tblAccount.setLkpBank(lkpBank);
            tblAccount.setLkpBranch(lkpBranch);

            return tblAccountRepo.saveAndFlush(tblAccount);

        } else {
            return null;
        }
    }

    @Override
    public TblUserAccountProduct updateCompanyUserAccountsProduct(UpdateUserAccountProductRequest updateUserAccountProductRequest, LoggedUserDetail loggedUserDetail) {

        TblUserAccountProduct tblUserAccountProduct = tblUserAccountProductRepo.findById(updateUserAccountProductRequest.getUserAccountProductId()).orElse(null);

        if (tblUserAccountProduct != null) {

            tblUserAccountProduct.setIsActive(updateUserAccountProductRequest.getIsActive());


            TblAccount tblAccount = new TblAccount();
            TblUser tblUser = new TblUser();
            TblProduct tblProduct = new TblProduct();


            tblAccount.setAccountId(updateUserAccountProductRequest.getAccountId());
            tblUser.setUserId(updateUserAccountProductRequest.getUserId());
            tblProduct.setProductId(updateUserAccountProductRequest.getProductId());

            tblUserAccountProduct.setTblAccount(tblAccount);
            tblUserAccountProduct.setTblProduct(tblProduct);
            tblUserAccountProduct.setTblUser(tblUser);

            tblUserAccountProduct.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblUserAccountProduct.setLastupdatedate(new Date());
            tblUserAccountProduct.setUpdateindex(tblUserAccountProduct.getUpdateindex() != null ? new BigDecimal(tblUserAccountProduct.getUpdateindex().longValue() + 1) : new BigDecimal(1));


            return tblUserAccountProductRepo.saveAndFlush(tblUserAccountProduct);


        } else {
            return null;
        }
    }

    @Override
    public TblUser updateCompanyUser(UpdateUserRequest updateUserRequest, LoggedUserDetail loggedUserDetail) {

        TblUser tblUser = tblUserRepo.findById(updateUserRequest.getUserId()).orElse(null);

        if (tblUser != null) {

            tblUser.setContactNo(updateUserRequest.getContactNo());
            tblUser.setDepartment(updateUserRequest.getDepartment());
            tblUser.setDesignation(updateUserRequest.getDesignation());
            tblUser.setEmail(updateUserRequest.getEmail());
            tblUser.setEmployeeNo(updateUserRequest.getEmployeeNo());
            tblUser.setProfileExpiry(updateUserRequest.getProfileExpiry());
            tblUser.setUserCode(updateUserRequest.getUserCode());
            tblUser.setUserGroup(updateUserRequest.getUserGroup());
            tblUser.setUserName(updateUserRequest.getUserName());
            tblUser.setPassword(updateUserRequest.getPassword());


            LkpBaseLocation lkpBaseLocation = new LkpBaseLocation();
            LkpUserType lkpUserType = new LkpUserType();

            lkpBaseLocation.setBaseLocationId(updateUserRequest.getBaseLocation());
            lkpUserType.setUserTypeId(updateUserRequest.getUserTypeId());

            tblUser.setLkpUserType(lkpUserType);
            tblUser.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblUser.setLastupdatedate(new Date());
            tblUser.setUpdateindex(tblUser.getUpdateindex() != null ? new BigDecimal(tblUser.getUpdateindex().longValue() + 1) : new BigDecimal(1));

            TblUser user = tblUserRepo.saveAndFlush(tblUser);
            if (user != null) {

                List<TblUserRole> tblUserRoles = tblUserRoleRepo.findByTblUserUserId(user.getUserId());

                TblUserRole tblUserRole = tblUserRoles.get(0);

                TblRole tblRole = new TblRole();

                tblRole.setRoleId(updateUserRequest.getRoleId());

                tblUserRole.setTblRole(tblRole);

                tblUserRoleRepo.saveAndFlush(tblUserRole);

                return tblUser;

            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    @Override
    public List<TblParserHead> getCompanyParser(String companyId) {
        List<TblParserCompanyConfig> tblParserCompanyConfigs = tblParserCompanyConfigRepo.findByTblCompanyCompanyId(Long.valueOf(companyId));

        if (tblParserCompanyConfigs != null && tblParserCompanyConfigs.size() > 0) {
            List<TblParserHead> tblParserHeads = new ArrayList<>();
            for (TblParserCompanyConfig tblParserCompanyConfig : tblParserCompanyConfigs) {
                List<TblParserDetail> tblParserDetails = tblParserDetailRepo.findByTblParserHeadParserHeadIdOrderByParserDetailIdAsc(tblParserCompanyConfig.getTblParserHead().getParserHeadId());
//                tblParserCompanyConfig.getTblParserHead().setParserDetails(tblParserDetails);
                tblParserHeads.add(tblParserCompanyConfig.getTblParserHead());

            }

            return tblParserHeads;

        } else {
            return null;
        }
    }


    @Override
    public TblAuthMatrixHead updateCompanyAuthMatrix(UpdateAuthMatrixHead updateAuthMatrixHead, LoggedUserDetail loggedUserDetail) {

        TblAuthMatrixHead tblAuthMatrixHead = tblAuthMatrixHeadRepo.findById(updateAuthMatrixHead.getAuthMatrixHeadId()).orElse(null);
        if (tblAuthMatrixHead != null) {

            tblAuthMatrixHead.setFromAmount(new BigDecimal(updateAuthMatrixHead.getFromAmount()));
            tblAuthMatrixHead.setToAmount(new BigDecimal(updateAuthMatrixHead.getToAmount()));
            tblAuthMatrixHead.setIsSequential(updateAuthMatrixHead.getIsSequential());

            TblProduct tblProduct = new TblProduct();

            tblProduct.setProductId(updateAuthMatrixHead.getProductId());

            tblAuthMatrixHead.setTblProduct(tblProduct);
            tblAuthMatrixHead.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblAuthMatrixHead.setLastupdatedate(new Date());
            tblAuthMatrixHead.setUpdateindex(tblAuthMatrixHead.getUpdateindex() != null ? new BigDecimal(tblAuthMatrixHead.getUpdateindex().longValue() + 1) : new BigDecimal(1));

            return tblAuthMatrixHeadRepo.saveAndFlush(tblAuthMatrixHead);

        } else {
            return null;
        }
    }

    @Override
    public TblAuthMatrixDetail updateCompanyAuthMatrixDetail(UpdateAuthMatrixDetailRequest updateAuthMatrixDetailRequest, LoggedUserDetail loggedUserDetail) {
        TblAuthMatrixDetail tblAuthMatrixDetail = tblAuthMatrixDetailRepo.findById(updateAuthMatrixDetailRequest.getAuthMatrixDetailId()).orElse(null);

        if (tblAuthMatrixDetail != null) {

            tblAuthMatrixDetail.setSrNo(new BigDecimal(updateAuthMatrixDetailRequest.getSrNo()));


            TblUser tblUser = new TblUser();
            LkpUserAuthLevel tblUserLevel = new LkpUserAuthLevel();


            tblUser.setUserId(updateAuthMatrixDetailRequest.getUserId());
            tblUserLevel.setUserAuthLevelId(updateAuthMatrixDetailRequest.getUserLevelId());

            tblAuthMatrixDetail.setTblUser(tblUser);
            tblAuthMatrixDetail.setLkpUserAuthLevel(tblUserLevel);

            return tblAuthMatrixDetailRepo.saveAndFlush(tblAuthMatrixDetail);

        } else {
            return null;
        }
    }

    @Override
    public List<TblParserDetail> getcompanyproductparser(long companyId, long productId) {
        TblParserHead tblParserHead = tblParserHeadRepo.getCompanyProductParser(companyId, productId);
        if (tblParserHead != null) {
            List<TblParserDetail> tblParserDetails = tblParserDetailRepo.findByTblParserHeadParserHeadIdOrderByParserDetailIdAsc(tblParserHead.getParserHeadId());
            return tblParserDetails;
        } else {
            return null;
        }
    }

    @Override
    public List<TblParserHead> getAllParsers() {

        List<TblParserHead> tblParserHeads = tblParserHeadRepo.findAll();
        if (tblParserHeads != null) {

            for (TblParserHead tblParserHead : tblParserHeads) {
                List<TblParserDetail> tblParserDetails = tblParserDetailRepo.findByTblParserHeadParserHeadIdOrderByParserDetailIdAsc(tblParserHead.getParserHeadId());
//                tblParserHead.setParserDetails(tblParserDetails);
            }
            return tblParserHeads;

        } else {
            return null;
        }
    }

    @Override
    public TblFileHead saveExcelInToDb(TblFileHead tblFileHead) {

        tblFileHead = tblFileHeadRepo.save(tblFileHead);
        if (tblFileHead != null && tblFileHead.getFileHeadId() > 0) {
            return tblFileHead;
        } else {
            return null;
        }

    }

//    @Override
//    public List<TblFileDetail> saveDetailExcelInToDb(TblFileHead tblFileHead) {
//        if (tblFileHead != null && tblFileHead.getFileHeadId() > 0) {
//            for (TblFileDetail tblFileDetail : tblFileHead.getFileDetails()) {
//                tblFileDetail.setTblFileHead(tblFileHead);
//                tblFileDetail = tblFileDetailRepo.save(tblFileDetail);
//            }
//            return tblFileHead.getFileDetails();
//        } else {
//            return null;
//        }
//    }

    @Override
    public TblParserHead updateParserHead(UpdateParserHeadRequest updateParserHeadRequest, LoggedUserDetail loggedUserDetail) {

        TblParserHead tblParserHead = tblParserHeadRepo.findById(updateParserHeadRequest.getParserHeadId()).orElse(null);

        if (tblParserHead != null) {
            tblParserHead.setCode(updateParserHeadRequest.getCode());
            tblParserHead.setDescription(updateParserHeadRequest.getDescription());
            tblParserHead.setIsActive(updateParserHeadRequest.getIsActive());
            tblParserHead.setLastupdatedate(new Date());
            tblParserHead.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblParserHead.setUpdateindex(tblParserHead.getUpdateindex() != null ? new BigDecimal(tblParserHead.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            return tblParserHeadRepo.save(tblParserHead);
        } else {
            return null;
        }
    }

    @Override
    public TblParserDetail updateParserDetail(UpdateParserDetailsRequest updateParserDetailsRequest, LoggedUserDetail loggedUserDetail) {

        TblParserDetail tblParserDetail = tblParserDetailRepo.findById(updateParserDetailsRequest.getParserDetailId()).orElse(null);
        if (tblParserDetail != null) {

            tblParserDetail.setColumnName(updateParserDetailsRequest.getColumnName());
            tblParserDetail.setColumnType(updateParserDetailsRequest.getColumnType());
            tblParserDetail.setFixedLength(updateParserDetailsRequest.getFixedLength());
            tblParserDetail.setIsMandatory(updateParserDetailsRequest.getIsMandatory());
            tblParserDetail.setMaxLength(updateParserDetailsRequest.getMaxLength());
            tblParserDetail.setMinLength(updateParserDetailsRequest.getMinLength());
            tblParserDetail.setLastupdatedate(new Date());
            tblParserDetail.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblParserDetail.setUpdateindex(tblParserDetail.getUpdateindex() != null ? new BigDecimal(tblParserDetail.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            return tblParserDetailRepo.save(tblParserDetail);

        } else {
            return null;
        }
    }

    @Override
    public TblUser updateBranchUser(UpdateBranchUserRequest updateBranchUserRequest, LoggedUserDetail loggedUserDetail) {
        TblUser tblUser = tblUserRepo.findById(updateBranchUserRequest.getUserId()).orElse(null);

        if (tblUser != null) {

            tblUser.setContactNo(updateBranchUserRequest.getContactNo());
            tblUser.setDepartment(updateBranchUserRequest.getDepartment());
            tblUser.setContactNo(updateBranchUserRequest.getContactNo());
            tblUser.setEmail(updateBranchUserRequest.getEmail());
            tblUser.setEmployeeNo(updateBranchUserRequest.getEmployeeNo());
            tblUser.setProfileExpiry(updateBranchUserRequest.getProfileExpiry());
            tblUser.setUserCode(updateBranchUserRequest.getUserCode());
            tblUser.setUserName(updateBranchUserRequest.getUserName());
            tblUser.setUserGroup(updateBranchUserRequest.getUserGroup());
            tblUser.setDesignation(updateBranchUserRequest.getDesignation());
            tblUser.setLastupdatedate(new Date());
            tblUser.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblUser.setUpdateindex(tblUser.getUpdateindex() != null ? new BigDecimal(tblUser.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            return tblUserRepo.save(tblUser);
        } else {
            return null;
        }
    }

    @Override
    public TblUser authorizeUserAndRole(TblUser tblUser) {
        tblUser = tblUserRepo.saveAndFlush(tblUser);
        if (tblUser != null) {
            List<TblUserRole> tblUserRoles = tblUserRoleRepo.findByTblUserUserId(tblUser.getUserId());
            for (TblUserRole tblUserRole : tblUserRoles) {
                tblUserRole.setMcStatus(tblUser.getMcStatus());
                tblUserRole.setCheckDate(new Date());
                tblUserRole.setCheckerComments(tblUser.getCheckerComments());
                tblUserRole.setCheckerId(BigDecimal.valueOf(tblUser.getUserId()));
                tblUserRole.setLastupdatedate(new Date());
                tblUserRole.setLastupdateuser(BigDecimal.valueOf(tblUser.getUserId()));
                tblUserRole.setUpdateindex(tblUserRole.getUpdateindex() != null ? new BigDecimal(tblUserRole.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            }

            tblUserRoleRepo.saveAllAndFlush(tblUserRoles);

            return tblUser;

        } else {
            return null;
        }
    }

    @Override
    public List<TblParserCompanyConfig> getParserCompanyConfig(String companyId) {
        return tblParserCompanyConfigRepo.findByTblCompanyCompanyId(Long.valueOf(companyId));
    }

    @Override
    public TblAuthMatrixHead authorizeAuthMatrix(TblAuthMatrixHead tblAuthMatrixHead) {
        tblAuthMatrixHead = tblAuthMatrixHeadRepo.saveAndFlush(tblAuthMatrixHead);
        if (tblAuthMatrixHead != null) {

            List<TblAuthMatrixDetail> tblAuthMatrixDetails = tblAuthMatrixDetailRepo.findByTblAuthMatrixHeadAuthMatrixHeadId(tblAuthMatrixHead.getAuthMatrixHeadId());
            if (tblAuthMatrixDetails != null && tblAuthMatrixDetails.size() > 0) {
                for (TblAuthMatrixDetail tblAuthMatrixDetail : tblAuthMatrixDetails) {
                    tblAuthMatrixDetail.setLastupdatedate(new Date());
                    tblAuthMatrixDetail.setLastupdateuser(tblAuthMatrixHead.getLastupdateuser());
                    tblAuthMatrixDetail.setUpdateindex(tblAuthMatrixDetail.getUpdateindex() != null ? new BigDecimal(tblAuthMatrixDetail.getUpdateindex().longValue() + 1) : new BigDecimal(1));

                }
                tblAuthMatrixDetailRepo.saveAllAndFlush(tblAuthMatrixDetails);
            }
            return tblAuthMatrixHead;

        } else {
            return null;
        }
    }

    @Override
    public List<TblAuthMatrixHead> getBranchAuthMatrix() {
        List<TblAuthMatrixHead> tblAuthMatrixHeads = tblAuthMatrixHeadRepo.findByTblCompanyCompanyIdIsNull();
        if (tblAuthMatrixHeads != null && tblAuthMatrixHeads.size() > 0) {
            for (TblAuthMatrixHead tblAuthMatrixHead : tblAuthMatrixHeads) {
                List<TblAuthMatrixDetail> tblAuthMatrixDetails = tblAuthMatrixDetailRepo.findByTblAuthMatrixHeadAuthMatrixHeadId(tblAuthMatrixHead.getAuthMatrixHeadId());
//                tblAuthMatrixHead.setAuthMatrixDetails(tblAuthMatrixDetails);
            }

            return tblAuthMatrixHeads;
        } else {
            return null;
        }
    }

    @Override
    public TblAuthMatrixHead getBranchAuthMatrixs(String authMatrixHeadId) {
        TblAuthMatrixHead tblAuthMatrixHead = tblAuthMatrixHeadRepo.findById(Long.valueOf(authMatrixHeadId)).orElse(null);
        if (tblAuthMatrixHead != null) {
            List<TblAuthMatrixDetail> tblAuthMatrixDetails = tblAuthMatrixDetailRepo.findByTblAuthMatrixHeadAuthMatrixHeadId(tblAuthMatrixHead.getAuthMatrixHeadId());
//            tblAuthMatrixHead.setAuthMatrixDetails(tblAuthMatrixDetails);
            return tblAuthMatrixHead;
        } else {
            return null;
        }
    }

    @Override
    public TblParserHead getParserById(String parserHeadId) {
        TblParserHead tblParserHead = tblParserHeadRepo.findById(Long.valueOf(parserHeadId)).orElse(null);
        if (tblParserHead != null) {
            List<TblParserDetail> tblParserDetails = tblParserDetailRepo.findByTblParserHeadParserHeadIdOrderByParserDetailIdAsc(tblParserHead.getParserHeadId());
//            tblParserHead.setParserDetails(tblParserDetails);
            return tblParserHead;

        } else {
            return null;
        }
    }

    @Override
    public TblAuthMatrixHead updateBranchAuthMatrixHead(UpdateBranchAuthMatrixHeadRequest updateBranchAuthMatrixHeadRequest, LoggedUserDetail loggedUserDetail) {
        TblAuthMatrixHead tblAuthMatrixHead = tblAuthMatrixHeadRepo.findById(updateBranchAuthMatrixHeadRequest.getAuthMatrixHeadId()).orElse(null);

        if (tblAuthMatrixHead != null) {

            tblAuthMatrixHead.setFromAmount(updateBranchAuthMatrixHeadRequest.getFromAmount());
            tblAuthMatrixHead.setToAmount(updateBranchAuthMatrixHeadRequest.getToAmount());
            tblAuthMatrixHead.setIsSequential(updateBranchAuthMatrixHeadRequest.getIsSequential());
            tblAuthMatrixHead.setLastupdatedate(new Date());
            tblAuthMatrixHead.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblAuthMatrixHead.setUpdateindex(tblAuthMatrixHead.getUpdateindex() != null ? new BigDecimal(tblAuthMatrixHead.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            return tblAuthMatrixHeadRepo.save(tblAuthMatrixHead);
        } else {
            return null;
        }
    }

    @Override
    public TblAuthMatrixDetail updateBranchAuthMatrixDetails(UpdateBranchAuthMatrixDetailRequest updateBranchAuthMatrixDetailRequest, LoggedUserDetail loggedUserDetail) {
        TblAuthMatrixDetail tblAuthMatrixDetail = tblAuthMatrixDetailRepo.findById(updateBranchAuthMatrixDetailRequest.getAuthMatrixDetailId()).orElse(null);

        if (tblAuthMatrixDetail != null) {
            TblRole tblRole = new TblRole();
            LkpUserAuthLevel lkpUserAuthLevel = new LkpUserAuthLevel();
            tblRole.setRoleId(updateBranchAuthMatrixDetailRequest.getRoleId());
            lkpUserAuthLevel.setUserAuthLevelId(updateBranchAuthMatrixDetailRequest.getUserAuthLevelId());
            tblAuthMatrixDetail.setTblRole(tblRole);
            tblAuthMatrixDetail.setLkpUserAuthLevel(lkpUserAuthLevel);
            tblAuthMatrixDetail.setSrNo(updateBranchAuthMatrixDetailRequest.getSrNo());
            tblAuthMatrixDetail.setLastupdatedate(new Date());
            tblAuthMatrixDetail.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblAuthMatrixDetail.setUpdateindex(tblAuthMatrixDetail.getUpdateindex() != null ? new BigDecimal(tblAuthMatrixDetail.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            return tblAuthMatrixDetailRepo.save(tblAuthMatrixDetail);

        } else {
            return null;
        }
    }

    @Override
    public TblOtp checkOtpAgainstUser(long userId) {
        return tblOtpRepo.checkOtpAgainstUser(userId);

    }

    @Override
    public TblUser getUserById(Long userId) {
        return tblUserRepo.findById(userId).orElse(null);
    }

    @Override
    public TblSmsMsgEmail saveEmail(TblSmsMsgEmail tblSmsMsgEmail) {
        return tblSmsMsgEmailRepo.save(tblSmsMsgEmail);
    }

    @Override
    public TblOtp verifySecurityOtpin(long userId, String otpin) {
        return tblOtpRepo.verifySecurityOtpin(userId, otpin);
    }

    @Override
    public TblOtp verifySecurityDeviceCode(long userId, String otpin) {
        return tblOtpRepo.verifySecurityDeviceCode(userId, otpin);
    }

    @Override
    public TblOtp getSecurityOtpin(long userId, String securityPin) {
        return tblOtpRepo.getSecurityOtpin(userId);
    }

    @Override
    public TblOtp updateOtp(TblOtp wrongupdatetblOtp) {
        return tblOtpRepo.saveAndFlush(wrongupdatetblOtp);
    }

    @Override
    public TblOtp getSecurityOtpForWrongTry(long userId) {
        return tblOtpRepo.getSecurityOtpForWrongTry(userId);
    }

    @Override
    public TblTransHead findByUserId(long userId) {
        return tblTransHeadRepo.findById(userId).orElse(null);
    }

    @Override
    public TblTransHead saveInitiateSinglrTransaction(TblTransHead tblTransHead) {
        return tblTransHeadRepo.saveAndFlush(tblTransHead);
    }

    @Override
    public TblCashOverCounter saveInitiateSingleTransaction(TblCashOverCounter tblCashOverCounter) {
        return tblCashOverCounterRepo.saveAndFlush(tblCashOverCounter);
    }

    @Override
    public TblSmsMsgEmail saveInitiateSingleTransactions(TblSmsMsgEmail tblSmsMsgEmail) {
        return tblSmsMsgEmailRepo.saveAndFlush(tblSmsMsgEmail);
    }

    @Override
    public List<XpinTransactionsResponse> getDataAgainstXpin(long xpin) {
        List<Object> rslt = tblCashOverCounterRepo.getDataAgainstXpin(xpin);
        if (rslt != null && rslt.size() > 0) {
            XpinTransactionsResponse xpinTransactionsResponse = null;
            List<XpinTransactionsResponse> xpinTransactionsResponses = new ArrayList<>();

            for (Object record : rslt) {
                Object[] row = (Object[]) record;

                xpinTransactionsResponse = new XpinTransactionsResponse();
                xpinTransactionsResponse.setCashoverCounterId(((BigDecimal) row[0]).longValue());
                xpinTransactionsResponse.setTransheadId(((BigDecimal) row[1]).longValue());
                xpinTransactionsResponse.setCompanyid(((BigDecimal) row[2]).longValue());
                xpinTransactionsResponse.setProductId(((BigDecimal) row[3]).longValue());
                xpinTransactionsResponse.setBenName((String) row[4]);
                xpinTransactionsResponse.setBenAddress((String) row[5]);
                xpinTransactionsResponse.setMobileNo((String) row[6]);
                xpinTransactionsResponse.setDocType((String) row[7]);
                xpinTransactionsResponse.setDocNo((String) row[8]);
                xpinTransactionsResponse.setRemitterName((String) row[9]);
                xpinTransactionsResponse.setTransAmount((BigDecimal) row[10]);
                xpinTransactionsResponse.setTransdate((String) row[11]);

                xpinTransactionsResponses.add(xpinTransactionsResponse);
            }
            return xpinTransactionsResponses;
        } else {

            return null;
        }


    }

    @Override
    public List<TblParserCompanyConfig> getParserCompanyConfigForUpload(long companyId, long productId, long parserId) {
        return tblParserCompanyConfigRepo.findByTblCompanyCompanyIdAndTblProductProductIdAndTblParserHeadParserHeadId(companyId, productId, parserId);
    }

    public TblCashOverCounter saveRecCocCash(TblCashOverCounter tblCashOverCounter) {
        return tblCashOverCounterRepo.saveAndFlush(tblCashOverCounter);
    }

    @Override
    public TblTransHead saveRecCocCashTransHead(TblTransHead tblTransHead) {
        return tblTransHeadRepo.saveAndFlush(tblTransHead);
    }

    @Override
    public TblCashOverCounter updateRecCocTransfer(RecCocTransferRequest recCocTransferRequest, LoggedUserDetail loggedUserDetail) {
        TblCashOverCounter tblCashOverCounter = tblCashOverCounterRepo.findById(Long.valueOf(loggedUserDetail.getUserId())).orElse(null);

        if (tblCashOverCounter != null) {

            TblTransHead tblTransHead = new TblTransHead();

            tblCashOverCounter.setPaymentMode(recCocTransferRequest.getPaymentMode());
            tblCashOverCounter.setAccountNo(recCocTransferRequest.getAccountNo());
            tblCashOverCounter.setAccountTitle(recCocTransferRequest.getAccountTitle());
            tblCashOverCounter.setBranchCode(recCocTransferRequest.getBranchCode());
            tblCashOverCounter.setStatus("P");
//            tblCashOverCounter.setTransHeadId2(BigDecimal.valueOf(tblTransHead.getTransHeadId()));
            tblCashOverCounter.setLastupdatedate(new Date());
            tblCashOverCounter.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblCashOverCounter.setUpdateindex(tblCashOverCounter.getUpdateindex() != null ? new BigDecimal(tblCashOverCounter.getUpdateindex().longValue() + 1) : new BigDecimal(1));

            return tblCashOverCounterRepo.saveAndFlush(tblCashOverCounter);

        } else {
            return null;
        }
    }


    @Override
    public TblCashOverCounter updateRecCocCash(RecCocCashRequest recCocCashRequest, LoggedUserDetail loggedUserDetail, TblTransHead tblTransHead) {
        TblCashOverCounter tblCashOverCounter = tblCashOverCounterRepo.findById(recCocCashRequest.getCashoverCounterId()).orElse(null);

        if (tblCashOverCounter != null) {

            tblCashOverCounter.setPaymentMode(recCocCashRequest.getPaymentMode());
            tblCashOverCounter.setStatus("P");
//            tblCashOverCounter.setTransHeadId2(BigDecimal.valueOf(tblTransHead.getTransHeadId()));
            tblCashOverCounter.setExpiryDate(recCocCashRequest.getExpiryDate());
            if (tblCashOverCounter.getPaymentMode().equalsIgnoreCase("T")) {
                tblCashOverCounter.setAccountNo(recCocCashRequest.getIftAccountNo());
                tblCashOverCounter.setAccountTitle(recCocCashRequest.getIftAccountTitle());
                tblCashOverCounter.setBranchCode(recCocCashRequest.getIftBranchCode());
            }
            tblCashOverCounter.setLastupdatedate(new Date());
            tblCashOverCounter.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblCashOverCounter.setUpdateindex(tblCashOverCounter.getUpdateindex() != null ? new BigDecimal(tblCashOverCounter.getUpdateindex().longValue() + 1) : new BigDecimal(1));

            return tblCashOverCounterRepo.saveAndFlush(tblCashOverCounter);

        } else {
            return null;
        }
    }

    @Override
    public CustomizedLovAuthCompanyProduct getUserAuthProdutsNature(long userId, long productId) {
        List<Object> rslt = tblProductRepo.getUserAuthProdutsNature(userId, productId);
        if (rslt != null && rslt.size() > 0) {
            CustomizedLovAuthCompanyProduct customizedLovAuthCompanyProduct = null;


            for (Object record : rslt) {
                Object[] row = (Object[]) record;

                customizedLovAuthCompanyProduct = new CustomizedLovAuthCompanyProduct();
                customizedLovAuthCompanyProduct.setProductId(((BigDecimal) row[0]).longValue());
                customizedLovAuthCompanyProduct.setProductCode((String) row[1]);


            }
            return customizedLovAuthCompanyProduct;
        } else {

            return null;
        }
    }

    @Override
    public TblUser updateAuthorizeBranchUser(UpdateAuthorizationBranchUserRequest updateAuthorizationBranchUserRequest, LoggedUserDetail loggedUserDetail) {

        TblUser tblUser = tblUserRepo.findById(updateAuthorizationBranchUserRequest.getUserId()).orElse(null);

        if (tblUser != null) {

            tblUser.setCheckerComments(updateAuthorizationBranchUserRequest.getCheckerComments());
            tblUser.setCheckerId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblUser.setMcStatus(updateAuthorizationBranchUserRequest.getMcStatus());

            tblUser.setLastupdatedate(new Date());
            tblUser.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblUser.setUpdateindex(tblUser.getUpdateindex() != null ? new BigDecimal(tblUser.getUpdateindex().longValue() + 1) : new BigDecimal(1));

            tblUser = tblUserRepo.saveAndFlush(tblUser);

            List<TblUserRole> tblUserRoles = tblUserRoleRepo.findByTblUserUserId(tblUser.getUserId());
            for (TblUserRole tblUserRole : tblUserRoles) {
                tblUserRole.setCheckerComments(updateAuthorizationBranchUserRequest.getCheckerComments());
                tblUserRole.setCheckerId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                tblUserRole.setMcStatus(updateAuthorizationBranchUserRequest.getMcStatus());

                tblUserRole.setLastupdatedate(new Date());
                tblUserRole.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                tblUserRole.setUpdateindex(tblUserRole.getUpdateindex() != null ? new BigDecimal(tblUserRole.getUpdateindex().longValue() + 1) : new BigDecimal(1));

            }
            tblUserRoleRepo.saveAllAndFlush(tblUserRoles);

            return tblUser;
        }
        return null;
    }

    @Override
    public TblAuthMatrixHead updateAuthMatrixRequest(UpdateCompanyAuthMatrixRequest updateCompanyAuthMatrixRequest, LoggedUserDetail loggedUserDetail) {
        TblAuthMatrixHead tblAuthMatrixHead = tblAuthMatrixHeadRepo.findById(updateCompanyAuthMatrixRequest.getAuthMatrixHeadId()).orElse(null);
        if (tblAuthMatrixHead != null) {

            tblAuthMatrixHead.setMcStatus(updateCompanyAuthMatrixRequest.getMcStatus());
            tblAuthMatrixHead.setCheckerId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblAuthMatrixHead.setCheckerComments(updateCompanyAuthMatrixRequest.getCheckerComments());

            tblAuthMatrixHead.setLastupdatedate(new Date());
            tblAuthMatrixHead.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            tblAuthMatrixHead.setUpdateindex(tblAuthMatrixHead.getUpdateindex() != null ? new BigDecimal(tblAuthMatrixHead.getUpdateindex().longValue() + 1) : new BigDecimal(1));


            tblAuthMatrixHead = tblAuthMatrixHeadRepo.saveAndFlush(tblAuthMatrixHead);

            return tblAuthMatrixHead;

        } else {
            return null;
        }

    }

    @Override
    public List<LkpCountry> getAllCountries() {

        return lkpCountryRepo.getAllByIsDeleted(BigDecimal.valueOf(1));
    }

    @Override
    public LkpCountry saveCountry(LkpCountry lkpCountry) {
        return lkpCountryRepo.save(lkpCountry);
    }

    @Override
    public List<LkpCountry> approveCountries(ApproveCountriesRequest approveCountriesRequest, LoggedUserDetail loggedUserDetail) {
        List<LkpCountry> lkpCountries = new ArrayList<>();

        for (long countryId : approveCountriesRequest.getCountryId()) {
            LkpCountry lkpCountry = lkpCountryRepo.findById(countryId).orElse(null);

            if (lkpCountry.getIsDeleted().longValue() == 0 && lkpCountry.getMcStatus().equals("S")) {

                lkpCountry.setMcStatus("A");
                lkpCountry.setLastupdatedate(new Date());
                lkpCountry.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                lkpCountry.setUpdateindex(lkpCountry.getUpdateindex() != null ? new BigDecimal(lkpCountry.getUpdateindex().longValue() + 1) : new BigDecimal(1));

                lkpCountries.add(lkpCountryRepo.saveAndFlush(lkpCountry));
            } else {

            }

        }
        return lkpCountries;
    }

    @Override
    public LkpCountry approveCountry(ApproveCountry approveCountry, LoggedUserDetail loggedUserDetail) {

        LkpCountry lkpCountry = lkpCountryRepo.findById(approveCountry.getCountryId()).orElse(null);

        if (lkpCountry.getIsDeleted().longValue() == 0 && lkpCountry.getMcStatus().equals("S")) {

            lkpCountry.setLastupdatedate(new Date());
            lkpCountry.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            lkpCountry.setUpdateindex(lkpCountry.getUpdateindex() != null ? new BigDecimal(lkpCountry.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            lkpCountry.setMcStatus("A");

            return lkpCountryRepo.saveAndFlush(lkpCountry);
        }
        else {
            return null;
        }

    }

    @Override
    public LkpCountry updateCountry(UpdateCountryRequest updateCountryRequest, LoggedUserDetail loggedUserDetail) {

        LkpCountry lkpCountry = lkpCountryRepo.findById(updateCountryRequest.getCountryId()).orElse(null);

        if(lkpCountry!=null){

        lkpCountry.setCountryCode(updateCountryRequest.getCountryCode());
        lkpCountry.setCountryName(updateCountryRequest.getCountryName());
        lkpCountry.setIsActive(updateCountryRequest.getIsActive());
        lkpCountry.setMcStatus(updateCountryRequest.getMcStatus());
        lkpCountry.setCheckerId(lkpCountry.getCheckerId());
        lkpCountry.setCheckerComments(updateCountryRequest.getCheckerComments());
        lkpCountry.setCheckDate(updateCountryRequest.getCheckDate());
        lkpCountry.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        lkpCountry.setCreatedate(updateCountryRequest.getCreateDate());
        lkpCountry.setLastupdateuser(updateCountryRequest.getLastUpdateUser());
        lkpCountry.setLastupdatedate(updateCountryRequest.getLastUpdateDate());
        lkpCountry.setUpdateindex(lkpCountry.getUpdateindex() != null ? new BigDecimal(lkpCountry.getUpdateindex().longValue() + 1) : new BigDecimal(1));
        lkpCountry.setIsDeleted(updateCountryRequest.getIsDeleted());

        return lkpCountryRepo.saveAndFlush(lkpCountry);

    }
        else {
        return null;
        }
    }

    @Override
    public LkpCountry deleteCountry(DeleteCountryRequest deleteCountryRequest, LoggedUserDetail loggedUserDetail) {

        LkpCountry lkpCountry = lkpCountryRepo.findById(deleteCountryRequest.getCountryId()).orElse(null);

        if (lkpCountry.getIsDeleted().longValue() == 0 && lkpCountry.getMcStatus().equals("S")) {

            lkpCountry.setLastupdatedate(new Date());
            lkpCountry.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            lkpCountry.setUpdateindex(lkpCountry.getUpdateindex() != null ? new BigDecimal(lkpCountry.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            lkpCountry.setIsDeleted(BigDecimal.valueOf(1));

            return lkpCountryRepo.saveAndFlush(lkpCountry);
        }
        else {
            return null;
        }
    }

    @Override
    public List<LkpCountry> deleteCountries(DeleteCountriesRequest deleteCountriesRequest, LoggedUserDetail loggedUserDetail) {
        List<LkpCountry> lkpCountries = new ArrayList<>();
        for (long countryId : deleteCountriesRequest.getCountryId()) {

            LkpCountry lkpCountry = lkpCountryRepo.findById(countryId).orElse(null);

            if (lkpCountry.getIsDeleted().longValue() == 0 && lkpCountry.getMcStatus().equals("S")) {

                lkpCountry.setIsDeleted(BigDecimal.valueOf(1));
                lkpCountry.setLastupdatedate(new Date());
                lkpCountry.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                lkpCountry.setUpdateindex(lkpCountry.getUpdateindex() != null ? new BigDecimal(lkpCountry.getUpdateindex().longValue() + 1) : new BigDecimal(1));

                lkpCountries.add(lkpCountryRepo.saveAndFlush(lkpCountry));

            }

            else {

            }
        }
        return lkpCountries;
    }

    @Override
    public LkpProvince saveProvince(LkpProvince lkpProvince) {
        return lkpProvinceRepo.save(lkpProvince);
    }

    @Override
    public List<LkpProvince> getAllProvinces() {
        return lkpProvinceRepo.findAllByIsDeleted(BigDecimal.valueOf(1));
    }

    @Override
    public LkpProvince updateProvince(UpdateProvinceRequest updateProvinceRequest, LoggedUserDetail loggedUserDetail) {
        LkpProvince lkpProvince = lkpProvinceRepo.findById(updateProvinceRequest.getProvinceId()).orElse(null);

        lkpProvince.setProvinceCode(updateProvinceRequest.getProvinceCode());
        lkpProvince.setProvinceName(updateProvinceRequest.getProvinceName());
        lkpProvince.setIsActive(updateProvinceRequest.getIsActive());
        lkpProvince.setIsDeleted(updateProvinceRequest.getIsDeleted());
        lkpProvince.setMcStatus(updateProvinceRequest.getMcStatus());
        lkpProvince.setCheckerId(updateProvinceRequest.getCheckerId());
        lkpProvince.setCheckerComments(updateProvinceRequest.getCheckerComments());
        lkpProvince.setCheckDate(updateProvinceRequest.getCheckDate());
        lkpProvince.setCheckDate(updateProvinceRequest.getCheckDate());
        lkpProvince.setCreatedate(updateProvinceRequest.getCreateDate());
        lkpProvince.setLastupdatedate(new Date());
        lkpProvince.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        lkpProvince.setUpdateindex(lkpProvince.getUpdateindex() != null ? new BigDecimal(lkpProvince.getUpdateindex().longValue() + 1) : new BigDecimal(1));


        LkpCountry lkpCountry = new LkpCountry();
        lkpCountry.setCountryId(updateProvinceRequest.getCountryId().longValue());

        return lkpProvinceRepo.saveAndFlush(lkpProvince);
    }

    @Override
    public LkpProvince approveProvince(ApproveProvince approveProvince, LoggedUserDetail loggedUserDetail) {
        LkpProvince lkpProvince = lkpProvinceRepo.findById(approveProvince.getProvinceId()).orElse(null);

        if (lkpProvince.getIsDeleted().longValue() == 0 && lkpProvince.getMcStatus().equals("S")) {

            lkpProvince.setLastupdatedate(new Date());
            lkpProvince.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            lkpProvince.setUpdateindex(lkpProvince.getUpdateindex() != null ? new BigDecimal(lkpProvince.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            lkpProvince.setMcStatus("A");

            return lkpProvinceRepo.saveAndFlush(lkpProvince);
        }
        else {
            return null;
        }
    }

    @Override
    public List<LkpProvince> approveProvinces(ApproveProvincesRequest approveProvincesRequest, LoggedUserDetail loggedUserDetail) {
        List<LkpProvince> lkpProvinces = new ArrayList<>();

        for (long countryId : approveProvincesRequest.getProvinceId()) {
            LkpProvince lkpProvince = lkpProvinceRepo.findById(countryId).orElse(null);

            if (lkpProvince.getIsDeleted().longValue() == 0 && lkpProvince.getMcStatus().equals("S")) {

                lkpProvince.setMcStatus("A");
                lkpProvince.setLastupdatedate(new Date());
                lkpProvince.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                lkpProvince.setUpdateindex(lkpProvince.getUpdateindex() != null ? new BigDecimal(lkpProvince.getUpdateindex().longValue() + 1) : new BigDecimal(1));

                lkpProvinces.add(lkpProvinceRepo.saveAndFlush(lkpProvince));
            } else {

            }

        }
        return lkpProvinces;
    }

    @Override
    public LkpProvince deleteProvince(DeleteProvinceRequest deleteProvinceRequest, LoggedUserDetail loggedUserDetail) {

        LkpProvince lkpProvince = lkpProvinceRepo.findById(deleteProvinceRequest.getProvinceId()).orElse(null);

        if (lkpProvince.getIsDeleted().longValue() == 0 && lkpProvince.getMcStatus().equals("S")) {

            lkpProvince.setLastupdatedate(new Date());
            lkpProvince.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            lkpProvince.setUpdateindex(lkpProvince.getUpdateindex() != null ? new BigDecimal(lkpProvince.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            lkpProvince.setIsDeleted(BigDecimal.valueOf(1));

            return lkpProvinceRepo.saveAndFlush(lkpProvince);

            }
        else {
            return null;
        }
    }

    @Override
    public List<LkpProvince> deleteProvinces(DeleteProvincesRequest deleteProvincesRequest, LoggedUserDetail loggedUserDetail) {
        List<LkpProvince> lkpProvinces = new ArrayList<>();
        for (long provinceId : deleteProvincesRequest.getProvinceId()) {

            LkpProvince lkpProvince = lkpProvinceRepo.findById(provinceId).orElse(null);

            if (lkpProvince.getIsDeleted().longValue() == 0 && lkpProvince.getMcStatus().equals("S")) {

                lkpProvince.setIsDeleted(BigDecimal.valueOf(1));
                lkpProvince.setLastupdatedate(new Date());
                lkpProvince.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                lkpProvince.setUpdateindex(lkpProvince.getUpdateindex() != null ? new BigDecimal(lkpProvince.getUpdateindex().longValue() + 1) : new BigDecimal(1));

                lkpProvinces.add(lkpProvinceRepo.saveAndFlush(lkpProvince));

            }

            else {

            }
        }
        return lkpProvinces;
    }

    @Override
    public List<LkpCity> getAllCities() {
        return lkpCityRepo.getAllByIsDeleted(BigDecimal.valueOf(1));
    }

    @Override
    public LkpCity saveCity(LkpCity lkpCity) {
        return lkpCityRepo.save(lkpCity);
    }

    @Override
    public LkpCity updateCity(UpdateCityRequest updateCityRequest, LoggedUserDetail loggedUserDetail) {
        LkpCity lkpCity = lkpCityRepo.findById(updateCityRequest.getProvinceId()).orElse(null);

        lkpCity.setCityCode(updateCityRequest.getCityCode());
        lkpCity.setCityName(updateCityRequest.getCityName());
        lkpCity.setIsActive(updateCityRequest.getIsActive());
        lkpCity.setLastupdatedate(new Date());
        lkpCity.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
        lkpCity.setUpdateindex(lkpCity.getUpdateindex() != null ? new BigDecimal(lkpCity.getUpdateindex().longValue() + 1) : new BigDecimal(1));


        LkpProvince lkpProvince = new LkpProvince();
        lkpProvince.setProvinceId(updateCityRequest.getProvinceId());

        return lkpCityRepo.saveAndFlush(lkpCity);
    }

    @Override
    public LkpCity approveCity(ApproveCityRequest approveCityRequest, LoggedUserDetail loggedUserDetail) {
        LkpCity lkpCity = lkpCityRepo.findById(approveCityRequest.getCityId()).orElse(null);

        if (lkpCity.getIsDeleted().longValue() == 0 && lkpCity.getMcStatus().equals("S")) {

            lkpCity.setLastupdatedate(new Date());
            lkpCity.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            lkpCity.setUpdateindex(lkpCity.getUpdateindex() != null ? new BigDecimal(lkpCity.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            lkpCity.setMcStatus("A");

            return lkpCityRepo.saveAndFlush(lkpCity);
        }
        else {
            return null;
        }
    }

    @Override
    public List<LkpCity> approveCities(ApproveCitiesRequest approveCitiesRequest, LoggedUserDetail loggedUserDetail) {
        List<LkpCity> lkpCities = new ArrayList<>();

        for (long cityId : approveCitiesRequest.getCityId()) {
            LkpCity lkpCity = lkpCityRepo.findById(cityId).orElse(null);

            if (lkpCity.getIsDeleted().longValue() == 0 && lkpCity.getMcStatus().equals("S")) {

                lkpCity.setMcStatus("A");
                lkpCity.setLastupdatedate(new Date());
                lkpCity.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                lkpCity.setUpdateindex(lkpCity.getUpdateindex() != null ? new BigDecimal(lkpCity.getUpdateindex().longValue() + 1) : new BigDecimal(1));

                lkpCities.add(lkpCityRepo.saveAndFlush(lkpCity));
            } else {

            }

        }
        return lkpCities;
    }

    @Override
    public LkpCity deleteCity(DeleteCityRequest deleteCityRequest, LoggedUserDetail loggedUserDetail) {

        LkpCity lkpCity = lkpCityRepo.findById(deleteCityRequest.getCityId()).orElse(null);

        if (lkpCity.getIsDeleted().longValue() == 0 && lkpCity.getMcStatus().equals("S")) {

            lkpCity.setLastupdatedate(new Date());
            lkpCity.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
            lkpCity.setUpdateindex(lkpCity.getUpdateindex() != null ? new BigDecimal(lkpCity.getUpdateindex().longValue() + 1) : new BigDecimal(1));
            lkpCity.setIsDeleted(BigDecimal.valueOf(1));

            return lkpCityRepo.saveAndFlush(lkpCity);

        }
        else {
            return null;
        }
    }

    @Override
    public List<LkpCity> deleteCities(DeleteCitiesRequest deleteCitiesRequest, LoggedUserDetail loggedUserDetail) {
        List<LkpCity> lkpCities = new ArrayList<>();
        for (long cityId : deleteCitiesRequest.getCityId()) {

            LkpCity lkpCity = lkpCityRepo.findById(cityId).orElse(null);

            if (lkpCity.getIsDeleted().longValue() == 0 && lkpCity.getMcStatus().equals("S")) {

                lkpCity.setIsDeleted(BigDecimal.valueOf(1));
                lkpCity.setLastupdatedate(new Date());
                lkpCity.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                lkpCity.setUpdateindex(lkpCity.getUpdateindex() != null ? new BigDecimal(lkpCity.getUpdateindex().longValue() + 1) : new BigDecimal(1));

                lkpCities.add(lkpCityRepo.saveAndFlush(lkpCity));

            }

            else {

            }
        }
        return lkpCities;
    }
}


