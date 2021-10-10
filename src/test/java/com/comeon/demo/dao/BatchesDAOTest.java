package com.comeon.demo.dao;

import com.comeon.demo.entity.CountedEmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BatchesDAOTest {

    @Autowired
    private BatchesDAO batchesDAO;

    @Test
    public void testCreateBatch() throws Exception {
        assertNotNull(batchesDAO);
        String email1 = "test1@test.com";
        String email2 = "test2@test.com";
        HashSet<CountedEmail> data = new HashSet<>();
        data.add(CountedEmail.builder().email(email1).count(1).build());
        data.add(CountedEmail.builder().email(email2).count(2).build());
        data.forEach(item->
                batchesDAO.save(
                        CountedEmail.builder()
                                .email(item.getEmail())
                                .count(item.getCount())
                                .build()));

        assertTrue(batchesDAO.findAll().iterator().hasNext());
        assertEquals("test2@test.com", batchesDAO.findAll().iterator().next().getEmail());
    }
}