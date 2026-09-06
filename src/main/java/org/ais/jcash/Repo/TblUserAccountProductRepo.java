package org.ais.jcash.Repo;

import org.ais.jcash.model.TblUserAccountProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TblUserAccountProductRepo extends JpaRepository<TblUserAccountProduct , Long> {

    List<TblUserAccountProduct> findByTblCompanyCompanyId(long companyId);
}
