package com.comeon.demo.dao;

import com.comeon.demo.entity.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//https://www.baeldung.com/spring-boot-testing
//https://stackoverflow.com/questions/34617152/how-to-re-create-database-before-each-test-in-spring
@RunWith(SpringRunner.class)
@DataJpaTest
public class EmailsDAOTest {

    public static final long interval = 300000; //5 mins
    @Autowired
    private EmailsDAO emailsDAO;

    @Test
    public void testCreateEmail() throws Exception {
        assertNotNull(emailsDAO);
        String email = "test@test.com";
        emailsDAO.save(Email.builder().email(email).build());
        assertEquals(1, emailsDAO.findByEmail(email).size());
    }

    @Test
    public void testFindEmailsByPeriod() throws Exception {
        //GIVEN
        String emailFits1 = "test1@test.com";
        String emailFits2 = "test2@test.com";
        String emailNotFits1 = "bad1@test.com";
        String emailNotFits2 = "bad2@test.com";
        long currentTimeMils = System.currentTimeMillis();

        //WHEN
        emailsDAO.save(Email.builder()
                .email(emailFits1)
                .timeStamp(new Timestamp(currentTimeMils - interval))
                .build());

        emailsDAO.save(Email.builder()
                .email(emailFits2)
                .timeStamp(new Timestamp(currentTimeMils - interval + 1))
                .build());
        emailsDAO.save(Email.builder()
                .email(emailFits2)
                .timeStamp(new Timestamp(currentTimeMils - interval + 2))
                .build());

        emailsDAO.save(Email.builder()
                .email(emailNotFits1)
                .timeStamp(new Timestamp(currentTimeMils + interval))
                .build());

        emailsDAO.save(Email.builder()
                .email(emailNotFits2)
                .timeStamp(new Timestamp(currentTimeMils + interval + 1))
                .build());

        //THEN
        List<Email> data = emailsDAO.findByPeriod(
                new Timestamp(currentTimeMils - interval),
                new Timestamp(currentTimeMils));
        assertEquals(3, data.size());
        Map<String, Long> counted = data.stream()
                .collect(Collectors.groupingBy(Email::getEmail, Collectors.counting()));
        assertEquals(2, counted.size());
    }
}