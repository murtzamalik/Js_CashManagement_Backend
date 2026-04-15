package org.ais.jcash.Service.impl;

import org.ais.jcash.Repo.*;
import org.ais.jcash.Service.JsCashLovService;
import org.ais.jcash.dto.CustomizedLovAuthCompanyProduct;
import org.ais.jcash.dto.LovResponse;
import org.ais.jcash.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class JsCashLovServiceImpl implements JsCashLovService {

    @Autowired
    private TblRoleRepo tblRoleRepo;

    @Autowired
    private TblMenuRepo tblMenuRepo;

    @Autowired
    private LkpAreaRepo tblAreaRepo;

    @Autowired
    private TblProductRepo tblProductRepo;

    @Autowired
    private TblUserRoleRepo tblUserRoleRepo;

    @Autowired
    private TblModuleRepo tblModuleRepo;

    @Autowired
    private LkpBankRepo lkpBankRepo;

    @Autowired
    private LkpBaseLocationRepo lkpBaseLocationRepo;

    @Autowired
    private LkpBranchRepo lkpBranchRepo;

    @Autowired
    private LkpCityRepo lkpCityRepo;

    @Autowired
    private LkpCountryRepo lkpCountryRepo;

    @Autowired
    private LkpRegionRepo lkpRegionRepo;

    @Autowired
    private TblUserRepo tblUserRepo;

    @Autowired
    private TblAccountRepo tblAccountRepo;

    @Autowired
    private TblCompanyProductRepo tblCompanyProductRepo;

    @Autowired
    private LkpUserTypeRepo lkpUserTypeRepo;

    @Autowired
    private LkpUserAuthLevelRepo lkpUserAuthLevelRepo;

    @Autowired
    private TblParserHeadRepo tblParserHeadRepo;

    @Autowired
    private TblCompanyRepo tblCompanyRepo;

    @Autowired
    private TblProductCollectionRepo tblProductCollectionRepo;

    @Autowired
    private LkpPaymentModeRepo lkpPaymentModeRepo;


    @Override
    public List<LovResponse> lovRoleRights() {

        List<TblRole> results = tblRoleRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblRole result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getRoleId()));
                lovResponse.setCode(result.getRoleDescr());
                lovResponse.setDescription(result.getCheckerComments());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovMenu() {

//        List<TblMenu> results = tblMenuRepo.findByStatus("Y");
        List<TblMenu> results = tblMenuRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblMenu result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getMenuId()));
                lovResponse.setCode(result.getMenuCode());
                lovResponse.setDescription(result.getMenuDescription());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }


    @Override
    public List<LovResponse> lovProduct() {
//        List<TblProduct> results = tblProductRepo.findByStatus("Y");
        List<TblProduct> results = tblProductRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblProduct result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getProductId()));
                lovResponse.setCode(result.getMasterProductCode());
                lovResponse.setDescription(result.getMasterProductName());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovArea() {
//        List<TblArea> results = tblAreaRepo.findByStatus("Y");
        List<LkpArea> results = tblAreaRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpArea result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getAreaId()));
                lovResponse.setCode(result.getAreaCode());
                lovResponse.setDescription(result.getCheckerComments());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }


    @Override
    public List<LovResponse> lovModule() {
        //        List<TblAreacode> results = tblAreacodeRepo.findByStatus("Y");
        List<TblModule> results = tblModuleRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblModule result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getModuleId()));
                lovResponse.setCode(result.getModuleDescr());
                lovResponse.setDescription(result.getModuleDescr());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovRole() {
        List<TblRole> results = tblRoleRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblRole result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getRoleId()));
                lovResponse.setCode(result.getRoleDescr());
                lovResponse.setDescription(result.getRoleDescr());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovBank() {
        List<LkpBank> results = lkpBankRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpBank result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getBankId()));
                lovResponse.setCode(result.getBankCode());
                lovResponse.setDescription(result.getBankName());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovBaseLocation() {
        List<LkpBaseLocation> results = lkpBaseLocationRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpBaseLocation result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getBaseLocationId()));
                lovResponse.setCode(result.getBaseLocationCode());
                lovResponse.setDescription(result.getBaseLocationDescr());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovBranch() {
        List<LkpBranch> results = lkpBranchRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpBranch result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getBranchId()));
                lovResponse.setCode(result.getBranchCode());
                lovResponse.setDescription(result.getBranchName());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovCity() {
        List<LkpCity> results = lkpCityRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpCity result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getCityId()));
                lovResponse.setCode(result.getCityCode());
                lovResponse.setDescription(result.getCityName());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovCountry() {
        List<LkpCountry> results = lkpCountryRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpCountry result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getCountryId()));
                lovResponse.setCode(result.getCountryCode());
                lovResponse.setDescription(result.getCountryName());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovRegion() {
        List<LkpRegion> results = lkpRegionRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpRegion result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getRegionId()));
                lovResponse.setCode(result.getRegionCode());
                lovResponse.setDescription(result.getRegionName());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovCompanyUsers(long companyId) {
//        List<TblUser> results = tblUserRepo.findByTblCompanyCompanyIdAndMcStatus(companyId, "A");
        List<TblUser> results = tblUserRepo.findByTblCompanyCompanyId(companyId);

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblUser result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getUserId()));
                lovResponse.setCode(result.getUserCode());
                lovResponse.setDescription(result.getUserName());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovCompanyAcounts(Long companyId) {
//        List<TblAccount> results = tblAccountRepo.findByTblCompanyCompanyIdAndMcStatus(companyId, "A");
        List<TblAccount> results = tblAccountRepo.findByTblCompanyCompanyId(companyId);

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblAccount result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getAccountId()));
                lovResponse.setCode(result.getAccountNo());
                lovResponse.setDescription(result.getAccountNo());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovCompanyProducts(Long companyId) {
        List<TblCompanyProduct> results = tblCompanyProductRepo.findByTblCompanyCompanyId(companyId);

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblCompanyProduct result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getTblProduct().getProductId()));
                lovResponse.setCode(result.getTblProduct().getMasterProductCode());
                lovResponse.setDescription(result.getTblProduct().getMasterProductName());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovUserType() {
        List<LkpUserType> results = lkpUserTypeRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpUserType result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getUserTypeId()));
                lovResponse.setCode(result.getCode());
                lovResponse.setDescription(result.getDescription());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovUserLevel() {
        List<LkpUserAuthLevel> results = lkpUserAuthLevelRepo.findAll();

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpUserAuthLevel result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getUserAuthLevelId()));
                lovResponse.setCode(result.getCode());
                lovResponse.setDescription(result.getDescription());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }


    @Override
    public List<LovResponse> lovCompanyParserLink(String companyId) {
        List<TblParserHead> results = tblParserHeadRepo.lovCompanyParserLink(Long.valueOf(companyId));

        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblParserHead result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getParserHeadId()));
                lovResponse.setCode(result.getCode());
                lovResponse.setDescription(result.getDescription());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovCompanyPraser(String companyId, String productId) {
        TblParserHead tblParserHead = tblParserHeadRepo.getCompanyProductParser(Long.valueOf(companyId), Long.valueOf(productId));

        if (tblParserHead != null) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = new LovResponse();


            lovResponse.setId(String.valueOf(tblParserHead.getParserHeadId()));
            lovResponse.setCode(tblParserHead.getCode());
            lovResponse.setDescription(tblParserHead.getDescription());

            lovResponses.add(lovResponse);

            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovAuthProduct(Long companyId) {
        List<TblCompanyProduct> results = tblCompanyProductRepo.findByTblCompanyCompanyIdAndMcStatus(companyId, "A");
//        List<TblCompanyProduct> results = tblCompanyProductRepo.findByTblCompanyCompanyId(companyId);
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblCompanyProduct result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getTblProduct().getProductId()));
                lovResponse.setCode(result.getTblProduct().getMasterProductCode());
                lovResponse.setDescription(result.getTblProduct().getMasterProductName());
                lovResponse.setShortText(result.getCustCollectionAccount());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }


    @Override
    public List<LovResponse> lovAuthCompany() {
        List<TblCompany> results = tblCompanyRepo.findByMcStatus("A");
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblCompany result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getCompanyId()));
                lovResponse.setCode(result.getCompanyCode());
                lovResponse.setDescription(result.getCompanyName());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }

    }


    @Override
    public List<LovResponse> lovProductCollection() {
        List<String> productCode = new ArrayList<>();
        productCode.add("CHQIC");
        productCode.add("CHQNC");
        productCode.add("CHQSD");
        List<TblProductCollection> results = tblProductCollectionRepo.findByProductCodeIn(productCode);
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblProductCollection result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getProductCollectionId()));
                lovResponse.setCode(result.getProductCode());
                lovResponse.setDescription(result.getProductName());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }

    }

    @Override
    public List<LovResponse> lovCompany() {
        List<TblCompany> results = tblCompanyRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblCompany result : results) {
                lovResponse = new LovResponse();


                lovResponse.setId(String.valueOf(result.getCompanyId()));
                lovResponse.setCode(result.getCompanyCode());
                lovResponse.setDescription(result.getCompanyName());

                lovResponses.add(lovResponse);
            }
            return lovResponses;
        } else {
            return null;
        }
    }

    @Override
    public List<LovResponse> lovAuthCompanyAcounts(Long companyId) {
        List<TblAccount> results = tblAccountRepo.findByTblCompanyCompanyIdAndMcStatus(companyId, "A");
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (TblAccount result : results) {
                lovResponse = new LovResponse();

                lovResponse.setId(String.valueOf(result.getAccountId()));
                lovResponse.setCode(result.getAccountNo());
                lovResponse.setDescription(result.getAccountNo());

                lovResponses.add(lovResponse);
            }
            return lovResponses;

        } else {
            return null;
        }
    }

    @Override
    public List<CustomizedLovAuthCompanyProduct> lovAuthCompanyProduct(long userId) {

        List<Object> rslt = tblProductRepo.lovAuthCompanyProduct(userId);
        if (rslt != null && rslt.size() > 0) {
            CustomizedLovAuthCompanyProduct customizedLovAuthCompanyProduct = null;
            List<CustomizedLovAuthCompanyProduct> data = new ArrayList<>();

            for (Object record : rslt) {
                Object[] row = (Object[]) record;


                customizedLovAuthCompanyProduct = new CustomizedLovAuthCompanyProduct();
                customizedLovAuthCompanyProduct.setProductId(((BigDecimal) row[0]).longValue());
                customizedLovAuthCompanyProduct.setProductCode((String) row[1]);
                customizedLovAuthCompanyProduct.setMasterProductName((String) row[2]);
                customizedLovAuthCompanyProduct.setCustAccDr((String) row[3]);
                customizedLovAuthCompanyProduct.setDocumentType((String) row[4]);
                customizedLovAuthCompanyProduct.setDocumentNo((String) row[5]);
                customizedLovAuthCompanyProduct.setMobileNo((String) row[6]);
                customizedLovAuthCompanyProduct.setBenBank((String) row[7]);
                customizedLovAuthCompanyProduct.setBenAccNO((String) row[8]);
                customizedLovAuthCompanyProduct.setBenAccTitle((String) row[9]);
                customizedLovAuthCompanyProduct.setBenEmail((String) row[10]);
                customizedLovAuthCompanyProduct.setBenName((String) row[11]);
                customizedLovAuthCompanyProduct.setBenAddress((String) row[12]);
                customizedLovAuthCompanyProduct.setPaymentMode((String) row[13]);
                data.add(customizedLovAuthCompanyProduct);

            }
            return data;
        } else {

            return null;
        }
    }

    @Override
    public List<LovResponse> lovPaymentMode() {
        List<LkpPaymentMode> results = lkpPaymentModeRepo.findAll();
        if (results != null && results.size() > 0) {
            List<LovResponse> lovResponses = new ArrayList<>();
            LovResponse lovResponse = null;
            for (LkpPaymentMode result : results) {
                lovResponse = new LovResponse();


                lovResponse.setId(String.valueOf(result.getPaymentModeId()));
                lovResponse.setCode(result.getPaymentModeCode());
                lovResponse.setDescription(result.getDescription());

                lovResponses.add(lovResponse);
            }
            return lovResponses;
        } else {
            return null;
        }
    }
}
