package org.ais.jcash.Repo;

import org.ais.jcash.model.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TblUserRepo extends JpaRepository<TblUser, Long> {

    TblUser findByUserNameAndPassword(String userName, String password);

    List<TblUser> findByTblCompanyCompanyIdAndMcStatus(Long companyId, String mcStatus);
    List<TblUser> findByUserId(Long userId);

    TblUser findByUserIdAndPassword(long userId, String pass);


    @Modifying
    @Query("update TblUser j set " +
            " j.lastupdatedate = :lastupdatedate, " +
            " j.lastupdateuser = :lastupdateuser," +
            "  j.updateindex = NVL(j.updateindex,0) + 1 ,  " +
            " j.password = :password " +
            "  where j.userId = :userId")
    int updateUserPassword(@Param("userId") Long userId, @Param("password") String password,
                           @Param("lastupdatedate") Date lastupdatedate, @Param("lastupdateuser") BigDecimal lastupdateuser);


    @Modifying
    @Query("update TblUser j set " +
            " j.lastupdatedate = :lastupdatedate, " +
            " j.lastupdateuser = :lastupdateuser," +
            "  j.updateindex = NVL(j.updateindex,0) + 1 ,  " +
            " j.password = :password " +
            "  where j.email = :email")
    int confirmUserPassword(@Param("email") String email, @Param("password") String password,
                            @Param("lastupdatedate") Date lastupdatedate, @Param("lastupdateuser") BigDecimal lastupdateuser);


    TblUser findByEmail(String email);

    List<TblUser> findByTblCompanyCompanyId(long companyId);

    TblUser findByUserNameAndEmail(String userName, String email);

    List<TblUser> findByLkpUserTypeUserTypeId(long userTypeId);

}
