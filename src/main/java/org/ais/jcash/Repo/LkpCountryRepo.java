package org.ais.jcash.Repo;

import org.ais.jcash.model.LkpCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface LkpCountryRepo extends JpaRepository<LkpCountry, Long> {

    @Query(value = "SELECT *\n" +
            "FROM LKP_COUNTRY\n" +
            "WHERE IS_DELETED = 0",nativeQuery = true)
    List<LkpCountry> getAllByIsDeleted(BigDecimal isDeleted);
}
