package com.comeon.demo.dao;

import com.comeon.demo.entity.Email;
import com.comeon.demo.entity.Url;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean
public interface ResourcesDAO extends CrudRepository<Url, Long> {
    @Query("SELECT m FROM Url m WHERE m.url LIKE %:url%")
    List<Url> searchByUrl(@Param("url") String url);
}
