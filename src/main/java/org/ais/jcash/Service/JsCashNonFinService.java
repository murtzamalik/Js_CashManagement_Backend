package org.ais.jcash.Service;

import org.ais.jcash.dto.*;
import org.ais.jcash.model.*;
import java.util.List;

public interface JsCashNonFinService {

    List<TblMenu> getAllMenuByStatus(String status);

    TblUser checkUserLogin(String userName,String password);

//   List<TblCompanyUser> getCompaniesByUserId(Long userId);

//   TblAuthToken saveAuthToken(TblAuthToken tblAuthToken);

    TblMenu saveMenu(TblMenu tblMenu);

    TblParserHead saveParserhead(TblParserHead tblParserHead);

    TblParserDetail saveParserDetails(TblParserDetail tblParserDetail);

    TblParserCompanyConfig saveParserCompanyConfig(TblParserCompanyConfig tblParserCompanyConfig);

    TblCompanyGroup saveCompanyGroup(TblCompanyGroup tblCompanyGroup);

    TblCompany saveCompanyProfile(TblCompany tblCompanyProfile);

    TblRole saveRole(TblRole tblRole);

    TblUser saveBranchUser(TblUser tblUser);

    List<TblUser> getallBranchuser(Long userId);

    List<TblRole> getAllRolebyStatus(String status);

    List<TblRoleRight> getMenuByRoleId(String roleId);

    TblRoleRight saveRoleRights(TblRoleRight tblRoleRight);

    List<TblCompanyGroup> getAllCompanyGroups();

    List<TblCompany> getAllGroupCompanies(String groupId);

//    TblCompanyAccount saveCompanyAccounts(TblCompanyAccount tblCompanyAccount);

    TblCompanyProduct saveCompanyProducts(TblCompanyProduct tblCompanyProduct);



//    List<TblCompanyAccount> getAllCompanyAccounts(String companyId);

//    List<TblCompanyUser> getAllCompanyUsers(String companyId);

    List<TblUserRole> getRoleAgainstUser(long userId);

    List<TblModule> getRoleWiseModule(List<TblUserRole> roleId);

    List<TblParserDetail> getcompanyproductparser(long companyId , long productId);

    List<TblRoleRight> getRoleAndModuelWisePages(long modulecodeId, List<TblUserRole> roleId);

    TblModule saveModule(TblModule tblModule);

    List<TblMenu> getAllMenuByModuleId(Long moduleId);

    List<TblModule> getAllModule();

//    TblCompanyUser saveCompanyUser(TblCompanyUser tblCompanyUser);

    TblUser saveTblUser(TblUser tblUser);

    TblUserRole saveUserRole(TblUserRole tblUserRole);

    TblUserAccountProduct saveCompanyUserAccountsProduct(TblUserAccountProduct tblUserAccountProduct);

    TblAccount saveCompanyAccounts(TblAccount tblAccount);

    List<TblCompany> getAllCompany();

    List<TblUser> getAlluser(String companyId);

    List<TblUser> getAllusers(String companyId);

    List<TblCompanyProduct> getAllCompanyProduct(String companyId);

    List<TblCompanyProduct> getAllproducts(String companyId);

    List<TblAccount> getAllaccounts(String companyId);

    List<TblAccount> getAllAccounts(String companyId);

    List<TblRoleRight> getAllRoleRightsbyRoleId(String roleId);

    TblUser checkUserPassword(long userId, String currPass);

    int updateUserPassword(long userId, String newPass,long loggedUserId);

    TblUser verifyUserEmail(String userName, String email);

    List<TblProduct> getAllProduct();

    List<TblCompanyProduct> getAllCompanyProducts(String companyId);

    List<TblCompany> getAllCompany(String companyId);

    TblCompany getcompany(String companyId);

    TblCompanyProduct updateCompanyProduct(UpdateCompanyProductsRequest updateCompanyProductsRequest, LoggedUserDetail loggedUserDetail);


    TblCompanyGroup updateComapnyGroup(UpdateCompanyGroupRequest updateCompanyGroupRequest , LoggedUserDetail loggedUserDetail);

    TblCompany updateCompany(UpdateCompanyRequest updateCompanyProfileRequest , LoggedUserDetail loggedUserDetail);

    TblModule updateModule(UpdateModuleReq updateModuleReq , LoggedUserDetail loggedUserDetail);

    TblRole updateRole(UpdateRoleRequest updateRoleRequest, LoggedUserDetail loggedUserDetail);

    TblMenu updateMenu(UpdateMenuRequest updateMenuRequest , LoggedUserDetail loggedUserDetail);

    LkpOtpType getOtpType(String otpCode);

    TblOtp saveOtp(TblOtp tblOtp);

    int  verifyOtp(String otp, long userId, long otpType);

    TblUserLoginHistory saveUserLoginHistory(TblUserLoginHistory tblUserLoginHistory);

    TblUserLoginHistory logoutUser(long userId);

    TblAuthMatrixHead saveTblAuthMatrixHead(TblAuthMatrixHead tblAuthMatrixHead);

    TblAuthMatrixDetail saveTblAuthMatrixDetail(TblAuthMatrixDetail tblAuthMatrixDetail);

    TblCompany getCompanyById(String companyId);

    List<TblUserAccountProduct> getUserAccountProduct(String companyId);

    List<TblAuthMatrixHead> getCompanyAuthMatrix(String companyId);

    TblAuthMatrixHead getAuthMatrix(String authHeadId);

    TblAccount updateCompanyAccounts(UpdateAccountRequest updateAccountRequest, LoggedUserDetail loggedUserDetail);

    TblUserAccountProduct updateCompanyUserAccountsProduct(UpdateUserAccountProductRequest updateUserAccountProductRequest, LoggedUserDetail loggedUserDetail);

    TblUser updateCompanyUser(UpdateUserRequest updateUserRequest, LoggedUserDetail loggedUserDetail);

    List<TblParserHead> getCompanyParser(String companyId);

    TblAuthMatrixHead updateCompanyAuthMatrix(UpdateAuthMatrixHead updateAuthMatrixHead, LoggedUserDetail loggedUserDetail);

    TblAuthMatrixDetail updateCompanyAuthMatrixDetail(UpdateAuthMatrixDetailRequest updateAuthMatrixDetailRequest, LoggedUserDetail loggedUserDetail);

    List<TblParserHead> getAllParsers();

    TblFileHead saveExcelInToDb(TblFileHead tblFileHead);

//    List<TblFileDetail> saveDetailExcelInToDb(TblFileHead tblFileHead);

    TblParserHead updateParserHead(UpdateParserHeadRequest updateParserHeadRequest, LoggedUserDetail loggedUserDetail);

    TblParserDetail updateParserDetail(UpdateParserDetailsRequest updateParserDetailsRequest ,LoggedUserDetail loggedUserDetail);

    TblUser updateBranchUser(UpdateBranchUserRequest updateBranchUserRequest ,LoggedUserDetail loggedUserDetail);

    TblUser authorizeUserAndRole(TblUser tblUser);

    List<TblParserCompanyConfig> getParserCompanyConfig(String companyId);

    TblAuthMatrixHead authorizeAuthMatrix(TblAuthMatrixHead tblAuthMatrixHead);

    List<TblAuthMatrixHead> getBranchAuthMatrix();

    TblAuthMatrixHead getBranchAuthMatrixs(String authMatrixHeadId);

    TblParserHead getParserById(String parserHeadId);

    TblAuthMatrixHead updateBranchAuthMatrixHead(UpdateBranchAuthMatrixHeadRequest updateBranchAuthMatrixHeadRequest, LoggedUserDetail loggedUserDetail);

    TblAuthMatrixDetail updateBranchAuthMatrixDetails(UpdateBranchAuthMatrixDetailRequest updateBranchAuthMatrixDetailRequest, LoggedUserDetail loggedUserDetail);

    TblOtp checkOtpAgainstUser(long userId);

    TblUser getUserById(Long userId);

    TblSmsMsgEmail saveEmail(TblSmsMsgEmail tblSmsMsgEmail);


    TblOtp verifySecurityOtpin(long userId, String otpin);


    TblOtp verifySecurityDeviceCode(long userId, String otpin);

    TblOtp getSecurityOtpin(long userId, String securityPin);

    TblOtp updateOtp(TblOtp wrongupdatetblOtp);

    TblOtp getSecurityOtpForWrongTry(long userId);

    TblTransHead findByUserId(long userId);

    TblTransHead saveInitiateSinglrTransaction(TblTransHead tblTransHead);

    TblCashOverCounter saveInitiateSingleTransaction(TblCashOverCounter tblCashOverCounter);

    TblSmsMsgEmail saveInitiateSingleTransactions(TblSmsMsgEmail tblSmsMsgEmail);

    List<XpinTransactionsResponse> getDataAgainstXpin(long xpin);


    List<TblParserCompanyConfig> getParserCompanyConfigForUpload(long companyId, long productId, long parserId);

    TblCashOverCounter saveRecCocCash(TblCashOverCounter tblCashOverCounter);

    TblTransHead saveRecCocCashTransHead(TblTransHead tblTransHead);

    TblCashOverCounter updateRecCocTransfer(RecCocTransferRequest recCocTransferRequest , LoggedUserDetail loggedUserDetail);

    TblCashOverCounter updateRecCocCash(RecCocCashRequest recCocCashRequest, LoggedUserDetail loggedUserDetail,TblTransHead tblTransHead);

    CustomizedLovAuthCompanyProduct getUserAuthProdutsNature(long userId,long productId);

    TblUser updateAuthorizeBranchUser(UpdateAuthorizationBranchUserRequest updateAuthorizationBranchUserRequest, LoggedUserDetail loggedUserDetail);


    TblAuthMatrixHead updateAuthMatrixRequest(UpdateCompanyAuthMatrixRequest updateCompanyAuthMatrixRequest, LoggedUserDetail loggedUserDetail);

    List<LkpCountry> getAllCountries();

    LkpCountry saveCountry(LkpCountry lkpCountry);

    List<LkpCountry> approveCountries(ApproveCountriesRequest approveCountriesRequest, LoggedUserDetail loggedUserDetail);

    LkpCountry approveCountry(ApproveCountry approveCountry, LoggedUserDetail loggedUserDetail);

    LkpCountry updateCountry(UpdateCountryRequest updateCountryRequest, LoggedUserDetail loggedUserDetail);

    LkpCountry deleteCountry(DeleteCountryRequest deleteCountryRequest, LoggedUserDetail loggedUserDetail);

    List<LkpCountry> deleteCountries(DeleteCountriesRequest deleteCountriesRequest, LoggedUserDetail loggedUserDetail);

    LkpProvince saveProvince(LkpProvince lkpProvince);

    List<LkpProvince> getAllProvinces();

    LkpProvince updateProvince(UpdateProvinceRequest updateProvinceRequest, LoggedUserDetail loggedUserDetail);

    LkpProvince approveProvince(ApproveProvince approveProvince, LoggedUserDetail loggedUserDetail);

    List<LkpProvince> approveProvinces(ApproveProvincesRequest approveProvincesRequest, LoggedUserDetail loggedUserDetail);

    LkpProvince deleteProvince(DeleteProvinceRequest deleteProvinceRequest, LoggedUserDetail loggedUserDetail);

    List<LkpProvince> deleteProvinces(DeleteProvincesRequest deleteProvincesRequest, LoggedUserDetail loggedUserDetail);

    List<LkpCity> getAllCities();

    LkpCity saveCity(LkpCity lkpCity);

    LkpCity updateCity(UpdateCityRequest updateCityRequest, LoggedUserDetail loggedUserDetail);

    LkpCity approveCity(ApproveCityRequest approveCityRequest, LoggedUserDetail loggedUserDetail);

    List<LkpCity> approveCities(ApproveCitiesRequest approveCitiesRequest, LoggedUserDetail loggedUserDetail);

    LkpCity deleteCity(DeleteCityRequest deleteCityRequest, LoggedUserDetail loggedUserDetail);

    List<LkpCity> deleteCities(DeleteCitiesRequest deleteCitiesRequest, LoggedUserDetail loggedUserDetail);
}
