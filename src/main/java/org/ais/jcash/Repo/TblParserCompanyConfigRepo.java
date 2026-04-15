package org.ais.jcash.Repo;

import org.ais.jcash.model.TblParserCompanyConfig;
import org.ais.jcash.model.TblParserHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TblParserCompanyConfigRepo extends JpaRepository<TblParserCompanyConfig , Long> {
    List<TblParserCompanyConfig> findByTblCompanyCompanyId(Long companyId);


    List<TblParserCompanyConfig> findByTblCompanyCompanyIdAndTblProductProductIdAndTblParserHeadParserHeadId(long companyId, long productId, long parserId);
}
