package com.comeon.demo.dao;

import com.comeon.demo.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean
@Repository
public interface EmailsDAO extends CrudRepository<Email, Long> {
    @Query("SELECT m FROM Email m WHERE m.email LIKE %:email%")
    List<Email> findByEmail(@Param("email") String email);

    @Query("SELECT m FROM Email m WHERE m.timeStamp BETWEEN :batchStart AND :batchEnd")
    List<Email> findByPeriod(
            @Param("batchStart") Timestamp batchStart,
            @Param("batchEnd") Timestamp batchEnd);

    @Query("SELECT COUNT(m) FROM Email m WHERE m.email=?1")
    long countByEmail(String email);

////SELECT * FROM EMAIL
////SELECT EMAIL, COUNT(EMAIL) FROM EMAIL WHERE TIME_STAMP BETWEEN '2021-10-09 09:38:27' AND '2021-10-09 09:38:28' GROUP BY EMAIL
//    @Query("SELECT m.email, " +
//            "COUNT(m) from Email m " +
//            "WHERE m.timeStamp BETWEEN :batchStart AND :batchEnd " +
//            "GROUP BY m.email" )
//    Object[] findEmailsByPeriod(
//            @Param("batchStart") Timestamp batchStart,
//            @Param("batchEnd") Timestamp batchEnd);
}
