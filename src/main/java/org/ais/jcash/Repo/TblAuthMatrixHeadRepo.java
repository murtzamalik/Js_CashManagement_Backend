package org.ais.jcash.Repo;

import org.ais.jcash.model.TblAuthMatrixHead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TblAuthMatrixHeadRepo extends JpaRepository<TblAuthMatrixHead , Long> {


    List<TblAuthMatrixHead> findByTblCompanyCompanyId(long companyId);

    List<TblAuthMatrixHead> findByTblCompanyCompanyIdIsNull();
}
