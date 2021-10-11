package com.comeon.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Builder
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class CountedEmail {
    @Id
    @GeneratedValue
    private Long dataId;

    private Timestamp batchStart;

    private Timestamp batchEnd;

    private String email;

    private long count;
}
