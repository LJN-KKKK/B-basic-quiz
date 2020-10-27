package com.thoughtworks.basicquiz.controller;

import com.thoughtworks.basicquiz.exception.ErrorResult;
import com.thoughtworks.basicquiz.exception.UserNotExistException;
import com.thoughtworks.basicquiz.model.User;
import com.thoughtworks.basicquiz.service.UserService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureJsonTesters
public class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<User> userJson;

    private User firstUser;
    private User userNotValid;
    private ErrorResult.ErrorResultBuilder errorResult;

    @BeforeEach
    public void beforeEach() {
        firstUser = User.builder()
                .id(123L)
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();

        userNotValid = User.builder()
                .id(123L)
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();

        errorResult = ErrorResult.builder()
                .timestamp("13:13")
                .status(400)
                .error("BAD_REQUEST")
                .message("name不能为空");
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(userService);
    }

    @Nested
    class GetUserById{
        @Nested
        class WhenUserExists{
            @Test
            public void should_return_user_by_id_with_jsonPath() throws Exception {
                when(userService.getUserById(123L)).thenReturn(firstUser);

                mockMvc.perform(get("/users/{id}", 123L))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.name", is("Panda")));

                verify(userService).getUserById(123L);
            }

            @Test
            public void should_return_user_by_id_with_jacksontester() throws Exception {
                when(userService.getUserById(123L)).thenReturn(firstUser);

                MockHttpServletResponse response = mockMvc.perform(get("/users/{id}", 123))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse();

                assertThat(response.getContentAsString()).isEqualTo(
                        userJson.write(firstUser).getJson());

                verify(userService).getUserById(123L);
            }
        }

        @Nested
        class WhenUserNotExist{
            @Test
            public void should_return_NOT_FOUND() throws Exception{
                when(userService.getUserById(123L)).thenThrow(new UserNotExistException("user not exist"));

                mockMvc.perform(get("/users/{id}", 123L))
                        .andExpect(status().isNotFound())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.message", is("user not exist")));

                verify(userService).getUserById(123L);
            }
        }
    }


    @Nested
    class CreateUser {

        private User newUserRequest;

        @BeforeEach
        public void beforeEach() {
            newUserRequest = User.builder()
                    .name("Panda")
                    .age(24L)
                    .avatar("http://...")
                    .description("A good guy.")
                    .build();
        }

        @Nested
        class WhenRequestIsValid {

            @Test
            public void should_create_new_user_and_return_its_id() throws Exception {
                when(userService.addUser(newUserRequest)).thenReturn(233L);

                MockHttpServletRequestBuilder requestBuilder = post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson.write(newUserRequest).getJson());

                MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                        .andReturn()
                        .getResponse();

                assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

                verify(userService).addUser(newUserRequest);
            }
        }

//        @Nested
//        class WhenRequestIsNotValid {
//            @Test
//            public void should_return_BAD_REQUEST() throws Exception {
//                when(userService.addUser(userNotValid)).thenThrow(new MethodArgumentNotValidException());
//
//                mockMvc.perform(get("/users", 123L))
//                        .andExpect(status().isBadRequest())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(jsonPath("$.message", is("user not exist")));
//
//                verify(userService).getUserById(123L);
//            }
//        }
    }
}
