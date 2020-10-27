package com.thoughtworks.basicquiz.service;

import com.thoughtworks.basicquiz.model.Education;
import com.thoughtworks.basicquiz.model.User;
import com.thoughtworks.basicquiz.repository.EducationRepository;
import com.thoughtworks.basicquiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EducationServiceTest {
    private EducationService educationService;
    @Mock
    private EducationRepository educationRepository;
    private UserRepository userRepository;
    private User user;
    private Education education;
    private List<Education> educationList;


    @BeforeEach
    void setUp() {
        educationService = new EducationService(educationRepository, userRepository);
        user = User.builder()
                .id(123L)
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();

        education = Education.builder()
                .id(233L)
                .title("whatever")
                .year(2024)
                .description("A")
                .user(user)
                .build();

        educationList = new ArrayList<Education>(){{add(education);}};
    }
    @Nested
    class FindByUserId {

        @Nested
        class WhenUserExists {

            @Test
            public void should_return_educations() {
                when(educationRepository.findAllByUserId(123L)).thenReturn(educationList);

                List<Education> foundEducations = educationService.getEducationById(123L);

                assertThat(foundEducations.get(0)).isEqualTo(Education.builder()
                        .id(233L)
                        .title("whatever")
                        .year(2024)
                        .description("A")
                        .user(user)
                        .build());
            }
        }


    }

    @Nested
    class CreateEducation{
        @Nested
        class WhenEducationIsValid {
            @Test
            public void should_return_void() {
                when(educationRepository.save(education));

                educationService.addEducation(user.getId(), education);

//                assertThat(userId).isEqualTo(123L);
            }
        }
    }
}
