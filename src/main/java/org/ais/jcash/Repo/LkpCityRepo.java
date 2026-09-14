package org.ais.jcash.Repo;

import org.ais.jcash.model.LkpCity;
import org.ais.jcash.model.LkpCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface LkpCityRepo extends JpaRepository<LkpCity, Long> {
    @Query(value = "SELECT *\n" +
            "FROM LKP_CITY\n" +
            "WHERE IS_DELETED = 0",nativeQuery = true)

    List<LkpCity> getAllByIsDeleted(BigDecimal isDeleted);
}
