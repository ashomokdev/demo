package com.comeon.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class BatchedEmails {
    @Id
    @GeneratedValue
    private Long batchId;

    private Timestamp batchStart;

    private Timestamp batchEnd;

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CountedEmail> emails;
}
