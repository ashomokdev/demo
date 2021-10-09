package com.comeon.demo;

import com.comeon.demo.controller.ProcessDataController;
import com.comeon.demo.service.DataFeederService;
import com.comeon.demo.service.DataProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(ProcessDataController.class)
@TestPropertySource(properties = "logging.level.org.springframework.web=DEBUG")
public class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataFeederService dataFeederService;

    @Test
    public void testPostData() throws Exception {
        //WHEN
        MvcResult result = mockMvc.perform(post("/postData")
                .accept(MediaType.APPLICATION_XML_VALUE)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .content("<?xml version = \"1.0\"?>\n" +
                        "<dataset>\n" +
                        "    <emails>\n" +
                        "        <email>user1@comeon.com</email>\n" +
                        "        <email>user2@comeon.com</email>\n" +
                        "        <email>user3@not-so-cool.com</email>\n" +
                        "    </emails>\n" +
                        "    <resources>\n" +
                        "        <url>http://test.com/new-xml</url>\n" +
                        "        <url>http://test.com/duplicate-emails</url>\n" +
                        "        <url>http://test.com/duplicate-emails</url>\n" +
                        "    </resources>\n" +
                        "</dataset>")).andReturn();

        MockHttpServletResponse response = result.getResponse();

        //THEN
        assertEquals(HttpServletResponse.SC_ACCEPTED, response.getStatus());
        assertNull(response.getErrorMessage());
        assertTrue(response.getContentAsString().contains("Your data posted and will be processed in schedule. Call GET for getting the result"));
    }

    @Test
    public void testGetData() throws Exception {
        //WHEN
        MockHttpServletResponse resultGet = mockMvc.perform(get("/getAllEmailsData")).andReturn().getResponse();

        //THEN
        assertEquals(HttpServletResponse.SC_OK, resultGet.getStatus());
        assertNull(resultGet.getErrorMessage());
    }
}

