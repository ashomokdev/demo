package com.comeon.demo.dao;

import com.comeon.demo.entity.Email;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean
public interface EmailsDAO extends CrudRepository<Email, Long> {
    @Query("SELECT m FROM Email m WHERE m.email LIKE %:email%")
    List<Email> searchByEmail(@Param("email") String email);
}
