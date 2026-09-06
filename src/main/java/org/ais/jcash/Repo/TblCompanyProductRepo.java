package org.ais.jcash.Repo;

import org.ais.jcash.model.TblAccount;
import org.ais.jcash.model.TblCompanyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TblCompanyProductRepo extends JpaRepository<TblCompanyProduct , Long> {


    List<TblCompanyProduct> findByTblCompanyCompanyId(Long companyId);

    List<TblCompanyProduct> findByTblCompanyCompanyIdAndMcStatus(Long companyId, String mcStatus);
}
