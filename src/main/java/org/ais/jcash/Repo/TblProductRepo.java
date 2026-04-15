package org.ais.jcash.Repo;

import org.ais.jcash.model.TblProduct;
import org.ais.jcash.model.TblProductCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblProductRepo extends JpaRepository<TblProduct , Long> {
    @Query(value = "SELECT C.PRODUCT_ID, P.MASTER_PRODUCT_CODE, P.MASTER_PRODUCT_NAME, C.CUST_ACCOUNT_DR,\n" +
            "PC.DOCUMENT_TYPE, PC.DOCUMENT_NO, PC.MOBILE_NO, PC.BENEFICIARY_BANK, PC.BENEFICIARY_ACCOUNT_NO,\n" +
            "PC.BENEFICIARY_ACCOUNT_TITLE, PC.BENEFICIARY_EMAIL, PC.BENEFICIARY_NAME, PC.BENEFICIARY_ADDRESS, PC.PAYMENT_MODE\n" +
            "FROM TBL_PRODUCT P\n" +
            "INNER JOIN TBL_COMPANY_PRODUCT C ON P.PRODUCT_ID = C.PRODUCT_ID\n" +
            "INNER JOIN TBL_USER U ON C.COMPANY_ID = U.COMPANY_ID\n" +
            "INNER JOIN TBL_PRODUCT_COLLECTION PC ON P.PRODUCT_COLLECTION_ID = PC.PRODUCT_COLLECTION_ID\n" +
            "WHERE U.USER_ID = :userId AND P.PRODUCT_NATURE = 'P'",nativeQuery = true)
    List<Object> lovAuthCompanyProduct(@Param("userId") long userId);

    @Query(value = "SELECT C.PRODUCT_ID,PC.PRODUCT_CODE\n" +
            "FROM TBL_PRODUCT P\n" +
            "INNER JOIN TBL_COMPANY_PRODUCT C ON P.PRODUCT_ID = C.PRODUCT_ID\n" +
            "INNER JOIN TBL_USER U ON C.COMPANY_ID = U.COMPANY_ID\n" +
            "INNER JOIN TBL_PRODUCT_COLLECTION PC ON P.PRODUCT_COLLECTION_ID = PC.PRODUCT_COLLECTION_ID\n" +
            "WHERE U.USER_ID = :userId AND P.PRODUCT_NATURE = 'P' AND P.PRODUCT_ID = :productId",nativeQuery = true)
    List<Object> getUserAuthProdutsNature(@Param("userId") long userId,@Param("productId") long productId);



}
