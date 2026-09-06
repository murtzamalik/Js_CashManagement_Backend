package org.ais.jcash.Repo;

import org.ais.jcash.model.TblOtp;
import org.ais.jcash.model.TblRoleRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TblOtpRepo extends JpaRepository<TblOtp,Long> {

    TblOtp findByOtpin(String otpin);


    TblOtp findByUserIdAndOtpinAndLkpOtpTypeOtpTypeId(BigDecimal userId,String otpin,long otpTypeId);


    @Modifying
    @Query("update TblOtp j set " +
            " j.lastupdatedate = :lastupdatedate, " +
            " j.lastupdateuser = :lastupdateuser," +
            "  j.updateindex = NVL(j.updateindex,0) + 1 ,  " +
            " j.isVerified = 'Y' " +
            "  where j.userId = :userId and j.lkpOtpType.otpTypeId = :otpTypeId and j.otpin = :otpin ")
    int updateOtpStatus(@Param("userId") BigDecimal userId, @Param("otpTypeId") Long otpTypeId,@Param("otpin") String otpin,
                           @Param("lastupdatedate") Date lastupdatedate, @Param("lastupdateuser") BigDecimal lastupdateuser);



    @Query(value = "SELECT *\n" +
            "FROM TBL_OTP\n" +
            "WHERE OTP_TYPE_ID = 2\n" +
            "AND USER_ID = :userId \n" +
            "AND NVL(IS_EXPIRED,'N') = 'N'\n" +
            "AND SYSDATE BETWEEN EFFECTIVE_FROM AND EFFECTIVE_TO", nativeQuery = true)
    TblOtp checkOtpAgainstUser(@Param("userId") Long userId);

    @Query(value = "SELECT *\n" +
            "FROM TBL_OTP\n" +
            "WHERE OTP_TYPE_ID = 3\n" +
            "AND USER_ID = :userId\n" +
            "AND OTPIN = :otpin\n" +
            "AND NVL(IS_VERIFIED,'N') = 'N'\n" +
            "AND SYSDATE BETWEEN EFFECTIVE_FROM AND EFFECTIVE_TO ", nativeQuery = true)
    TblOtp verifySecurityOtpin(@Param("userId") Long userId , @Param("otpin") String otpin);


    @Query(value = "SELECT *\n" +
            "FROM TBL_OTP\n" +
            "WHERE OTP_TYPE_ID = 2\n" +
            "AND USER_ID = :userId\n" +
            "AND OTPIN = :otpin\n" +
            "AND NVL(IS_EXPIRED,'N') = 'N'\n" +
            "AND SYSDATE BETWEEN EFFECTIVE_FROM AND EFFECTIVE_TO ", nativeQuery = true)
    TblOtp verifySecurityDeviceCode(@Param("userId") Long userId , @Param("otpin") String otpin);

    @Query(value = "SELECT *\n" +
            "FROM TBL_OTP\n" +
            "WHERE OTP_TYPE_ID = 3\n" +
            "AND USER_ID = :userId \n" +
            "AND IS_VERIFIED IS NULL\n" +
            "AND IS_EXPIRED IS NULL\n" +
            "AND SYSDATE BETWEEN EFFECTIVE_FROM AND EFFECTIVE_TO ", nativeQuery = true)
    TblOtp getSecurityOtpin(@Param("userId") Long userId);



    @Query(value = "SELECT *\n" +
            "FROM TBL_OTP\n" +
            "WHERE OTP_TYPE_ID = 3\n" +
            "AND USER_ID = :userId\n" +
            "AND NVL(IS_VERIFIED,'N') = 'N'\n" +
            "AND SYSDATE BETWEEN EFFECTIVE_FROM AND EFFECTIVE_TO\n" +
            "ORDER BY OTP_ID DESC FETCH FIRST 1 ROW ONLY ", nativeQuery = true)
    TblOtp getSecurityOtpForWrongTry(@Param("userId") Long userId);
}
