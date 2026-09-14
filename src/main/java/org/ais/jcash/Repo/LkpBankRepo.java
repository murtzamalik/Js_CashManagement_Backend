package org.ais.jcash.Repo;

import org.ais.jcash.model.LkpBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LkpBankRepo extends JpaRepository<LkpBank , Long> {

    List<LkpBank> findByIsIbftIgnoreCase(String isIbft);

    Optional<LkpBank> findFirstByBankNameIgnoreCase(String bankName);
}
