package org.ais.jcash.Repo;

import org.ais.jcash.model.LkpCountry;
import org.ais.jcash.model.LkpProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface LkpProvinceRepo extends JpaRepository <LkpProvince, Long> {

    @Query(value = "SELECT *\n" +
            "FROM LKP_PROVINCE\n" +
            "WHERE IS_DELETED = 0",nativeQuery = true)

    List<LkpProvince> findAllByIsDeleted(BigDecimal isDeleted);

}
