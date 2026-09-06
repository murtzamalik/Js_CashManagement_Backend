package org.ais.jcash.Repo;

import org.ais.jcash.model.TblUserLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;

public interface TblUserLoginHistoryRepo extends JpaRepository<TblUserLoginHistory,Long> {



}
