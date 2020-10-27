package com.thoughtworks.basicquiz.controller;

import com.thoughtworks.basicquiz.exception.UserNotExistException;
import com.thoughtworks.basicquiz.model.Education;
import com.thoughtworks.basicquiz.model.User;
import com.thoughtworks.basicquiz.service.EducationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EducationController.class)
@AutoConfigureJsonTesters
public class EducationControllerTest {
    @MockBean
    private EducationService educationService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<List<Education>> educationListJson;
    @Autowired
    private JacksonTester<Education> educationJson;

    private Education firstEducation;
    private List<Education> educationList;

    @BeforeEach
    public void beforeEach() {
        User firstUser = User.builder()
                .id(123L)
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();

        firstEducation = Education.builder()
                .id(233L)
                .title("whatever")
                .year(2024)
                .description("A")
                .user(firstUser)
                .build();

        educationList = new ArrayList<Education>(){{add(firstEducation);}};
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(educationService);
    }

    @Nested
    class GetEducationByUserId{
        @Nested
        class WhenUserExists{
            @Test
            public void should_return_educations() throws Exception {

                when(educationService.getEducationById(123L)).thenReturn(educationList);

                MockHttpServletResponse response = mockMvc.perform(get("/users/{id}/educations", 123L))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse();

                assertThat(response.getContentAsString()).isEqualTo(
                        educationListJson.write(educationList).getJson());

                verify(educationService).getEducationById(123L);
            }
        }

        @Nested
        class WhenUserNotExist {

            @Test
            public void should_return_NOT_FOUND() throws Exception {
                when(educationService.getEducationById(123L)).thenThrow(new UserNotExistException("user not exist"));

                mockMvc.perform(get("/users/{id}/educations", 123L))
                        .andExpect(status().isNotFound())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.message", is("user not exist")));

                verify(educationService).getEducationById(123L);
            }
        }
    }

    @Nested
    class CreateEducation{

        @Nested
        class WhenRequestIsValid{

            @Test
            public void should_create_new_education_and_return_CREATED() throws Exception {

                MockHttpServletRequestBuilder requestBuilder = post("/users/1/educations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(educationJson.write(firstEducation).getJson());

                MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                        .andReturn()
                        .getResponse();

                assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

                verify(educationService, times(1)).addEducation(1L, firstEducation);
            }
        }
    }
}
