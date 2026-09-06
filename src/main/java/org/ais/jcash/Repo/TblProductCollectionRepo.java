package org.ais.jcash.Repo;


import org.ais.jcash.model.TblProductCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TblProductCollectionRepo extends JpaRepository<TblProductCollection , Long> {



    @Query(value = "SELECT C.*\n" +
            "FROM TBL_PRODUCT_COLLECTION C\n" +
            "INNER JOIN TBL_PRODUCT P ON C.PRODUCT_COLLECTION_ID = P.PRODUCT_COLLECTION_ID\n" +
            "WHERE P.PRODUCT_ID = :productId",nativeQuery = true)
    TblProductCollection getCollectionAgainstProduct(@Param("productId") long productId);

List<TblProductCollection> findByProductCodeIn(List<String> productCodes);

}
