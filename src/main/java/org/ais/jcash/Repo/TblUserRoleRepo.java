package org.ais.jcash.Repo;

import org.ais.jcash.model.TblUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TblUserRoleRepo extends JpaRepository<TblUserRole , Long> {

    List<TblUserRole> findByTblUserUserId(Long userId);
}
