package com.thoughtworks.basicquiz.repository;

import com.thoughtworks.basicquiz.model.Education;
import com.thoughtworks.basicquiz.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class EducationRepositoryTest {

    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void should_return_educations_when_id_exists() {
        User user = User.builder()
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();

        entityManager.persistAndFlush(Education.builder()
                .title("whatever")
                .year(2024)
                .description("A")
                .build());

        List<Education> foundEducations = educationRepository.findAllByUserId(1L);
        assertThat(foundEducations.get(0)).isEqualTo(Education.builder()
                .id(1L)
                .title("whatever")
                .year(2024)
                .description("A")
//                .user(user)
                .build());
    }
}
