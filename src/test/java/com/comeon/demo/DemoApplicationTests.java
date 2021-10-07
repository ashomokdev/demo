package com.comeon.demo;

import com.comeon.demo.controller.ProcessDataController;
import com.comeon.demo.service.DataProcessorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(ProcessDataController.class)
@TestPropertySource(properties = "logging.level.org.springframework.web=DEBUG")
public class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataProcessorServiceImpl dataProcessor;

    @Test
    public void postEmptyData() throws Exception {
        mockMvc.perform(post("/postData")
                        .param("xml_data", ""))
                .andExpect(content().string(containsString("empty")));

    }

    @Test
    public void postData() throws Exception {
        mockMvc.perform(post("/postData")
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
                                "</dataset>"))
                .andExpect(content().string(containsString("empty")));
    }
}
