package com.comeon.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Builder
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class CountedEmail {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="batchId", nullable=false)
    private BatchedEmails batchId;
}
