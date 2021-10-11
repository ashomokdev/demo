package com.comeon.demo.service;

import com.comeon.demo.dao.EmailsDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class DataProcessorServiceTest {

    @Autowired
    private EmailsDAO emailsDAO;

    @Test
    public void testProcessUrl() {
        //GIVEN
        String testUrl = "https://lux-buy.com/lux-buy-test/test_comeon_remove_me.xml";

        //WHEN
        DataProcessorService dataProcessorService = new DataProcessorService();
        dataProcessorService.processUrlRecursively(testUrl, emailsDAO);

        //THEN
        assertTrue(emailsDAO.findAll().iterator().hasNext());
    }
}