package org.ais.jcash.Repo;

import org.ais.jcash.model.TblCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TblCompanyRepo extends JpaRepository<TblCompany , Long> {

    List<TblCompany> findByTblCompanyGroupCompanyGroupId(Long groupId);
    List<TblCompany> findBycompanyId(Long companyId);
    List<TblCompany> findByMcStatus(String status);

}
