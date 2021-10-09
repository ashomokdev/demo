package com.comeon.demo.service;

import com.comeon.demo.dao.BatchesDAO;
import com.comeon.demo.dao.EmailsDAO;
import com.comeon.demo.dao.ResourcesDAO;
import com.comeon.demo.entity.BatchedEmails;
import com.comeon.demo.entity.CountedEmail;
import com.comeon.demo.entity.Email;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataProcessor {
    @Autowired
    private EmailsDAO emailsDAO;

    @Autowired
    private ResourcesDAO resourcesDAO;

    @Autowired
    private BatchesDAO batchesDAO;

    public void processData(Timestamp batchStart, Timestamp batchEnd) {

        List<Email> data = emailsDAO.findByPeriod(batchStart, batchEnd);
        Set<CountedEmail> counted = data.stream()
                .collect(Collectors.groupingBy(Email::getEmail, Collectors.counting()))
                .entrySet().stream()
                .map(entry ->
                        CountedEmail.builder()
                                .email(entry.getKey())
                                .count(Math.toIntExact((entry.getValue())))
                                .build())
                .collect(Collectors.toSet());
//        Map<String, Long> counted = data.stream()
//                .collect(Collectors.groupingBy(Email::getEmail, Collectors.counting()));
        BatchedEmails batch =
                BatchedEmails.builder()
                        .batchStart(batchStart)
                        .batchEnd(batchEnd)
                        .emails(counted)
                        .build();

        batchesDAO.save(batch);


//        List<Email> batchData = emailsDAO.findByPeriod(batchStart, batchEnd);

//        emailsDAO.findBetween(batchStart, batchEnd);

//        log.info("New batch processing started for requested time period. Time start: "+ batchStart + ", time end: " + batchEnd);
        //todo
    }
}
