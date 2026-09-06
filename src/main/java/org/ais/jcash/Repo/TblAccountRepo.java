package org.ais.jcash.Repo;

import org.ais.jcash.model.TblAccount;
import org.ais.jcash.model.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TblAccountRepo extends JpaRepository<TblAccount , Long> {

    List<TblAccount> findByTblCompanyCompanyId(long companyId);

    List<TblAccount> findByTblCompanyCompanyIdAndMcStatus(Long companyId, String mcStatus);
}
