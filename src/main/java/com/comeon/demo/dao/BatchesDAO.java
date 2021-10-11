package com.comeon.demo.dao;

import com.comeon.demo.entity.CountedEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean
@Repository
public interface BatchesDAO extends JpaRepository<CountedEmail, Long> {

    @Query("SELECT SUM(m.count) FROM CountedEmail m WHERE m.email=?1")
    long countByEmail(String email);

    @Query("SELECT m.email, SUM(m.count) as count FROM CountedEmail m GROUP BY m.email")
    Object[][] getAllCounted();
}
