package com.comeon.demo.dao;

import com.comeon.demo.entity.Url;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean
@Repository
public interface ResourcesDAO extends CrudRepository<Url, Long> {
    @Query("SELECT m FROM Url m WHERE m.url LIKE %:url%")
    List<Url> searchByUrl(@Param("url") String url);

    @Query("SELECT m FROM Url m WHERE m.timeStamp BETWEEN :batchStart AND :batchEnd")
    List<Url> findByPeriod(
            @Param("batchStart") Timestamp batchStart,
            @Param("batchEnd") Timestamp batchEnd);
}
