package org.ais.jcash.Repo;

import org.ais.jcash.model.LkpOtpType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LkpOtpTypeRepo extends JpaRepository<LkpOtpType,Long> {

    LkpOtpType findByCode(String code);
}
