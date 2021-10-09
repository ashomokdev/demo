package com.comeon.demo.dao;

import com.comeon.demo.entity.BatchedEmails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean
@Repository
public interface BatchesDAO extends CrudRepository<BatchedEmails, Long> {
//    @Query("SELECT m FROM BatchEmails m WHERE m.url LIKE %:url%")
//    List<Url> searchByUrl(@Param("url") String url);
}
