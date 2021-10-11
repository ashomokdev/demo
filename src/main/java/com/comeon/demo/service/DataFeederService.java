package com.comeon.demo.service;

import com.comeon.demo.dao.EmailsDAO;
import com.comeon.demo.dao.ResourcesDAO;
import com.comeon.demo.dto.DatasetDTO;
import com.comeon.demo.dto.EmailsDTO;
import com.comeon.demo.dto.ResourcesDTO;
import com.comeon.demo.entity.Email;
import com.comeon.demo.entity.Url;
import com.comeon.demo.utils.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Slf4j
@Service
public class DataFeederService {

    @Autowired
    private EmailsDAO emailsDAO;

    @Autowired
    private ResourcesDAO resourcesDAO;

    public void post(DatasetDTO datasetDTO) {
        try {
            EmailsDTO emailsDTO = datasetDTO.getEmails();
            for (String emailDto : emailsDTO.getEmail()) {
                if (EmailValidator.isValid(emailDto)) {
                    Email email = Email.builder()
                            .email(emailDto)
                            .timeStamp(new Timestamp(System.currentTimeMillis()))
                            .build();
                    emailsDAO.save(email);
                }
            }
            ResourcesDTO resourcesDTO = datasetDTO.getResources();
            for (String urlDto : resourcesDTO.getUrl()) {
                Url url = Url.builder()
                        .url(urlDto)
                        .timeStamp(new Timestamp(System.currentTimeMillis()))
                        .build();
                resourcesDAO.save(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
        }
    }
}
