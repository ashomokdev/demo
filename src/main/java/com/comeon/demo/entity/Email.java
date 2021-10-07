package com.comeon.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

@Entity
@Builder
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    /**
     * Time stamp in which email have been collected
     */
    private Timestamp createdTimeStamp;



}
