package org.ais.jcash.Repo;

import org.ais.jcash.model.TblAuthMatrixDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TblAuthMatrixDetailRepo extends JpaRepository<TblAuthMatrixDetail , Long> {


    List<TblAuthMatrixDetail> findByTblAuthMatrixHeadAuthMatrixHeadId(long authmatrixHeadId);
}
