package com.comeon.demo.service;

import com.comeon.demo.dao.BatchesDAO;
import com.comeon.demo.dao.EmailsDAO;
import com.comeon.demo.dao.ResourcesDAO;
import com.comeon.demo.dto.DatasetDTO;
import com.comeon.demo.dto.EmailsDTO;
import com.comeon.demo.dto.ResourcesDTO;
import com.comeon.demo.entity.CountedEmail;
import com.comeon.demo.entity.Email;
import com.comeon.demo.entity.Url;
import com.comeon.demo.model.EmailResult;
import com.comeon.demo.utils.Converter;
import com.comeon.demo.utils.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataProcessorService {
    @Autowired
    private EmailsDAO emailsDAO;

    @Autowired
    private ResourcesDAO resourcesDAO;

    @Autowired
    private BatchesDAO batchesDAO;

    public List<EmailResult> getAllCounted() {
        return Converter.convert(batchesDAO.getAllCounted());
    }

    public long getCountByEmail(String email) {
        return batchesDAO.countByEmail(email);
    }

    public void processData(Timestamp batchStart, Timestamp batchEnd) {
        processEmails(batchStart, batchEnd);
        processUrls(batchStart, batchEnd);
    }

    private void processEmails(Timestamp batchStart, Timestamp batchEnd) {
        List<Email> data = emailsDAO.findByPeriod(batchStart, batchEnd);

        data.stream()
                .collect(Collectors.groupingBy(Email::getEmail, Collectors.counting()))
                .forEach((key, value) -> batchesDAO.save(
                        CountedEmail.builder()
                                .email(key)
                                .count(value)
                                .batchStart(batchStart)
                                .batchEnd(batchEnd)
                                .build()));

        data.forEach(email -> {
            email.setProcessed(true);
            emailsDAO.save(email);
        });
    }

    private void processUrls(Timestamp batchStart, Timestamp batchEnd) {
        List<Url> data = resourcesDAO.findByPeriod(batchStart, batchEnd);
        data.forEach(url -> {
            processUrlRecursively(url.getUrl());
            url.setProcessed(true);
            resourcesDAO.save(url);
        });
    }

    public void processUrlRecursively(String url) {
        try {
            RestTemplate restTemplate = new RestTemplateBuilder()
                    .setConnectTimeout(Duration.ofMillis(500))
                    .setReadTimeout(Duration.ofMillis(500))
                    .build();

            ResponseEntity<DatasetDTO> response = restTemplate.getForEntity(url, DatasetDTO.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                DatasetDTO datasetDTO = response.getBody();
                if (datasetDTO != null) {
                    try {
                        EmailsDTO emailsDTO = datasetDTO.getEmails();
                        if (emailsDTO != null && emailsDTO.getEmail().size() > 0) {
                            saveEmails(emailsDTO);
                        }
                        ResourcesDTO resourcesDTO = datasetDTO.getResources();
                        if (resourcesDTO != null && resourcesDTO.getUrl().size() > 0) {
                            resourcesDTO.getUrl().forEach(this::processUrlRecursively);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.debug(e.getMessage());
                    }
                }
            } else {
                log.error(response.getStatusCode().toString());
                log.error(response.getStatusCode().getReasonPhrase());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void saveEmails(EmailsDTO emailsDTO) {
        for (String emailDto : emailsDTO.getEmail()) {
            if (EmailValidator.isValid(emailDto)) {
                Email email = Email.builder()
                        .email(emailDto)
                        .timeStamp(new Timestamp(System.currentTimeMillis()))
                        .build();
                emailsDAO.save(email);
            }
        }
    }
}
