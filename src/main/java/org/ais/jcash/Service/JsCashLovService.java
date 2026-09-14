package org.ais.jcash.Service;

import org.ais.jcash.dto.CustomizedLovAuthCompanyProduct;
import org.ais.jcash.dto.LovResponse;

import java.util.List;

public interface JsCashLovService {


    List<LovResponse> lovRoleRights();

    List<LovResponse> lovMenu();

    List<LovResponse> lovProduct();

    List<LovResponse> lovArea();

    List<LovResponse> lovModule();

    List<LovResponse> lovBank();

    List<LovResponse> lovIbftBank();

    List<LovResponse> lovBaseLocation();

    List<LovResponse> lovBranch();

    List<LovResponse> lovCity();

    List<LovResponse> lovCountry();

    List<LovResponse> lovRegion();

    List<LovResponse> lovRole();

    List<LovResponse> lovCompanyUsers(long companyId);

    List<LovResponse> lovCompanyAcounts(Long companyId);

    List<LovResponse> lovCompanyProducts(Long companyId);

    List<LovResponse> lovUserType();

    List<LovResponse> lovUserLevel();

    List<LovResponse> lovCompanyPraser(String companyId, String productId);

    List<LovResponse> lovAuthProduct(Long companyId);

    List<LovResponse> lovAuthCompany();

    List<LovResponse> lovProductCollection();

    List<LovResponse> lovCompany();

    List<LovResponse> lovAuthCompanyAcounts(Long companyId);


    List<CustomizedLovAuthCompanyProduct> lovAuthCompanyProduct(long userId);

    List<LovResponse> lovPaymentMode();

    List<LovResponse> lovCompanyParserLink(String companyId);
}
