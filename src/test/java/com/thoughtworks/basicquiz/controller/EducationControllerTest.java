package com.thoughtworks.basicquiz.controller;

import com.thoughtworks.basicquiz.model.Education;
import com.thoughtworks.basicquiz.service.EducationService;
import com.thoughtworks.basicquiz.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EducationControllerTest {
    @MockBean
    private EducationService educationService;
    @Autowired
    private TestRestTemplate restTemplate;

    private Education firstEducation;
    @BeforeEach
    public void beforeEach() {
        firstEducation = Education.builder()
                .id(123L)
                .title("whatever")
                .year(2024)
                .description("A")
                .build();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(educationService);
    }

}
