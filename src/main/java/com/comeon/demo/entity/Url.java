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
@Data
@Builder
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue
    private Long id;

    private String url;

    /**
     * Time stamp in which url have been collected
     */
    private Timestamp timeStamp;

    private boolean processed;
}
