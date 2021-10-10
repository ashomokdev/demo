package com.comeon.demo.service;

import com.comeon.demo.dao.BatchesDAO;
import com.comeon.demo.dao.EmailsDAO;
import com.comeon.demo.dao.ResourcesDAO;
import com.comeon.demo.entity.CountedEmail;
import com.comeon.demo.entity.Email;
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
        log.info("New batch processing started for requested time period. Time start: "+ batchStart + ", time end: " + batchEnd);

        List<Email> data = emailsDAO.findByPeriod(batchStart, batchEnd);
        data.stream()
                .collect(Collectors.groupingBy(Email::getEmail, Collectors.counting()))
                .forEach((key, value) -> batchesDAO.save(
                        CountedEmail.builder()
                                .email(key)
                                .count(Math.toIntExact((value)))
                                .batchStart(batchStart)
                                .batchEnd(batchEnd)
                                .build()));

//        Map<String, Long> counted = data.stream()
//                .collect(Collectors.groupingBy(Email::getEmail, Collectors.counting()));
    }
}
